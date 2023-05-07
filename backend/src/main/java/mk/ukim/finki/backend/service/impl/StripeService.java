package mk.ukim.finki.backend.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.apiKey}")
    private String apiKey;

    public Charge chargeCreditCard(String token, BigDecimal amount, String description) throws StripeException {
        Stripe.apiKey = apiKey;
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValue());
        params.put("currency", "usd");
        params.put("description", description);
        params.put("source", token);
        return Charge.create(params);
    }

    public Customer createCustomer(String email, String token) throws StripeException {
        Stripe.apiKey = apiKey;
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("source", token);
        return Customer.create(params);
    }
}