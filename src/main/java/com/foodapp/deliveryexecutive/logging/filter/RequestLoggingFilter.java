package com.foodapp.deliveryexecutive.logging.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter that adds request context to MDC for logging.
 * This allows all logs within a request to be correlated.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String TRACE_ID_HEADER = "X-Trace-ID";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            // Get or generate request ID
            String requestId = request.getHeader(REQUEST_ID_HEADER);
            if (requestId == null || requestId.isEmpty()) {
                requestId = UUID.randomUUID().toString().substring(0, 8);
            }

            // Get or generate trace ID
            String traceId = request.getHeader(TRACE_ID_HEADER);
            if (traceId == null || traceId.isEmpty()) {
                traceId = UUID.randomUUID().toString();
            }

            // Add to MDC
            MDC.put("requestId", requestId);
            MDC.put("traceId", traceId);
            MDC.put("requestUri", request.getRequestURI());
            MDC.put("requestMethod", request.getMethod());
            MDC.put("clientIp", getClientIpAddress(request));

            // Add to response headers for client correlation
            response.setHeader(REQUEST_ID_HEADER, requestId);
            response.setHeader(TRACE_ID_HEADER, traceId);

            filterChain.doFilter(request, response);

        } finally {
            // Clean up MDC
            MDC.remove("requestId");
            MDC.remove("traceId");
            MDC.remove("requestUri");
            MDC.remove("requestMethod");
            MDC.remove("clientIp");
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        return request.getRemoteAddr();
    }
}
