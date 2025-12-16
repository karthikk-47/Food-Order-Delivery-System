package com.foodapp.deliveryexecutive.payments.service;

import com.foodapp.deliveryexecutive.payments.dto.SettlementDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SettlementService {
    private final List<SettlementDTO> store = new ArrayList<SettlementDTO>();

    public List<SettlementDTO> getSettlementsForHomemaker(Long homemakerId) {
        ArrayList<SettlementDTO> result = new ArrayList<SettlementDTO>();
        for (SettlementDTO s : this.store) {
            if (!s.getHomemakerId().equals(homemakerId)) continue;
            result.add(s);
        }
        return result;
    }

    public SettlementDTO settleHomemaker(Long homemakerId, SettlementDTO dto) {
        dto.setHomemakerId(homemakerId);
        dto.setSettled(true);
        if (dto.getPeriodStart() == null) {
            dto.setPeriodStart(LocalDate.now().minusDays(7L));
        }
        if (dto.getPeriodEnd() == null) {
            dto.setPeriodEnd(LocalDate.now());
        }
        dto.setId(Long.valueOf(this.store.size() + 1));
        this.store.add(dto);
        return dto;
    }

    public List<SettlementDTO> getPendingSettlements() {
        ArrayList<SettlementDTO> result = new ArrayList<SettlementDTO>();
        for (SettlementDTO s : this.store) {
            if (s.isSettled()) continue;
            result.add(s);
        }
        return result;
    }
}
