package mk.ukim.finki.backend.web;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import mk.ukim.finki.backend.service.impl.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/charge")
public class ChargeController {

    @Autowired
    private StripeService stripeService;

    @PostMapping
    public Charge chargeCreditCard(@RequestParam String token, @RequestParam BigDecimal amount, @RequestParam String description) throws StripeException {
        return stripeService.chargeCreditCard(token, amount, description);
    }
}
