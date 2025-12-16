package com.foodapp.deliveryexecutive.payments.controller;

import com.foodapp.deliveryexecutive.payments.dto.CreateContactRequest;
import com.foodapp.deliveryexecutive.payments.dto.CreateContactResponse;
import com.foodapp.deliveryexecutive.payments.dto.CreateFundAccountRequest;
import com.foodapp.deliveryexecutive.payments.dto.CreateFundAccountResponse;
import com.foodapp.deliveryexecutive.payments.dto.PayoutRequest;
import com.foodapp.deliveryexecutive.payments.dto.PayoutResponse;
import com.foodapp.deliveryexecutive.payments.service.PaymentsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/payments"})
public class PaymentsController {
    @Autowired
    PaymentsApi paymentApi;

    @PostMapping(value={"/createFundAccount"})
    public CreateFundAccountResponse createFundAccount(@RequestBody CreateFundAccountRequest req) throws Exception {
        return this.paymentApi.createFundAccount(req);
    }

    @PostMapping(value={"/createContact"})
    @ResponseBody
    public CreateContactResponse createContact(@RequestBody CreateContactRequest req) throws Exception {
        return this.paymentApi.createContact(req);
    }

    @PostMapping(value={"/payout"})
    public PayoutResponse payout(@RequestBody PayoutRequest req) throws Exception {
        return this.paymentApi.makePayout(req);
    }
}
