package mk.ukim.finki.backend.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import mk.ukim.finki.backend.models.stripe.ChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.apiKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest)
            throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }

//    public Customer createCustomer(String email, String token) throws StripeException {
//        Stripe.apiKey = apiKey;
//        Map<String, Object> params = new HashMap<>();
//        params.put("email", email);
//        params.put("source", token);
//        return Customer.create(params);
//    }
}