package com.foodapp.deliveryexecutive.payments.controller;

import com.foodapp.deliveryexecutive.payments.dto.SettlementDTO;
import com.foodapp.deliveryexecutive.payments.service.SettlementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/api/settlements"})
public class SettlementController {
    @Autowired
    private SettlementService settlementService;

    @GetMapping(value={"/homemaker/{id}"})
    public ResponseEntity<List<SettlementDTO>> getSettlementsForHomemaker(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(this.settlementService.getSettlementsForHomemaker(id));
    }

    @PostMapping(value={"/homemaker/{id}/settle"})
    public ResponseEntity<SettlementDTO> settleForHomemaker(@PathVariable(value="id") Long id, @RequestBody SettlementDTO dto) {
        return ResponseEntity.ok(this.settlementService.settleHomemaker(id, dto));
    }

    @GetMapping(value={"/admin/pending"})
    public ResponseEntity<List<SettlementDTO>> getPendingSettlements() {
        return ResponseEntity.ok(this.settlementService.getPendingSettlements());
    }
}
