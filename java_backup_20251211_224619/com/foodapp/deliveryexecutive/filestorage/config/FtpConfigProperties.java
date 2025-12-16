/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  org.springframework.boot.context.properties.ConfigurationProperties
 *  org.springframework.context.annotation.Configuration
 */
package com.foodapp.deliveryexecutive.filestorage.config;

import lombok.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="ftp")
public class FtpConfigProperties {
    private String host;
    private int port = 21;
    private String username;
    private String password;
    private String baseDir = "/";
    private String baseUrl;
    private int bufferSize = 0x100000;
    private int connectTimeout = 10000;
    private int dataTimeout = 30000;
    private int maxTotalConnections = 10;
    private boolean passiveMode = true;

    @Generated
    public FtpConfigProperties() {
    }

    @Generated
    public String getHost() {
        return this.host;
    }

    @Generated
    public int getPort() {
        return this.port;
    }

    @Generated
    public String getUsername() {
        return this.username;
    }

    @Generated
    public String getPassword() {
        return this.password;
    }

    @Generated
    public String getBaseDir() {
        return this.baseDir;
    }

    @Generated
    public String getBaseUrl() {
        return this.baseUrl;
    }

    @Generated
    public int getBufferSize() {
        return this.bufferSize;
    }

    @Generated
    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    @Generated
    public int getDataTimeout() {
        return this.dataTimeout;
    }

    @Generated
    public int getMaxTotalConnections() {
        return this.maxTotalConnections;
    }

    @Generated
    public boolean isPassiveMode() {
        return this.passiveMode;
    }

    @Generated
    public void setHost(String host) {
        this.host = host;
    }

    @Generated
    public void setPort(int port) {
        this.port = port;
    }

    @Generated
    public void setUsername(String username) {
        this.username = username;
    }

    @Generated
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated
    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    @Generated
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Generated
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Generated
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    @Generated
    public void setDataTimeout(int dataTimeout) {
        this.dataTimeout = dataTimeout;
    }

    @Generated
    public void setMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    @Generated
    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FtpConfigProperties)) {
            return false;
        }
        FtpConfigProperties other = (FtpConfigProperties)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getPort() != other.getPort()) {
            return false;
        }
        if (this.getBufferSize() != other.getBufferSize()) {
            return false;
        }
        if (this.getConnectTimeout() != other.getConnectTimeout()) {
            return false;
        }
        if (this.getDataTimeout() != other.getDataTimeout()) {
            return false;
        }
        if (this.getMaxTotalConnections() != other.getMaxTotalConnections()) {
            return false;
        }
        if (this.isPassiveMode() != other.isPassiveMode()) {
            return false;
        }
        String this$host = this.getHost();
        String other$host = other.getHost();
        if (this$host == null ? other$host != null : !this$host.equals(other$host)) {
            return false;
        }
        String this$username = this.getUsername();
        String other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        String this$baseDir = this.getBaseDir();
        String other$baseDir = other.getBaseDir();
        if (this$baseDir == null ? other$baseDir != null : !this$baseDir.equals(other$baseDir)) {
            return false;
        }
        String this$baseUrl = this.getBaseUrl();
        String other$baseUrl = other.getBaseUrl();
        return !(this$baseUrl == null ? other$baseUrl != null : !this$baseUrl.equals(other$baseUrl));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof FtpConfigProperties;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getPort();
        result = result * 59 + this.getBufferSize();
        result = result * 59 + this.getConnectTimeout();
        result = result * 59 + this.getDataTimeout();
        result = result * 59 + this.getMaxTotalConnections();
        result = result * 59 + (this.isPassiveMode() ? 79 : 97);
        String $host = this.getHost();
        result = result * 59 + ($host == null ? 43 : $host.hashCode());
        String $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $baseDir = this.getBaseDir();
        result = result * 59 + ($baseDir == null ? 43 : $baseDir.hashCode());
        String $baseUrl = this.getBaseUrl();
        result = result * 59 + ($baseUrl == null ? 43 : $baseUrl.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "FtpConfigProperties(host=" + this.getHost() + ", port=" + this.getPort() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", baseDir=" + this.getBaseDir() + ", baseUrl=" + this.getBaseUrl() + ", bufferSize=" + this.getBufferSize() + ", connectTimeout=" + this.getConnectTimeout() + ", dataTimeout=" + this.getDataTimeout() + ", maxTotalConnections=" + this.getMaxTotalConnections() + ", passiveMode=" + this.isPassiveMode() + ")";
    }
}
