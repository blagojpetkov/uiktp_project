package mk.ukim.finki.backend.web;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import mk.ukim.finki.backend.models.ShoppingCart;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.models.stripe.ChargeRequest;
import mk.ukim.finki.backend.service.ShoppingCartService;
import mk.ukim.finki.backend.service.UserService;
import mk.ukim.finki.backend.service.impl.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;


    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public ChargeController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/charge")
    public String charge(@RequestParam(required = false) double amount,
                         @RequestParam(required = false) String stripeToken,
                         @RequestParam(required = false) String stripeTokenType,
                         @RequestParam(required = false) String stripeEmail)
            throws StripeException {
        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setAmount((int)amount);
        chargeRequest.setStripeToken(stripeToken);
        chargeRequest.setStripeEmail(stripeEmail);
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        User user = userService.getAuthenticatedUser();
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUser(user.getId());
        shoppingCart.getCourses().clear();
        shoppingCartService.save(shoppingCart);
        return "redirect:/courses/shopping_cart?success=true";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
