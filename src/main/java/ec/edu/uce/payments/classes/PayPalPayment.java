package ec.edu.uce.payments.classes;

import ec.edu.uce.payments.jpa.Entities.PaymentMethod;
import ec.edu.uce.payments.jpa.Services.PaymentMethodService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@QualifierPay("PayPal")
public class PayPalPayment extends Payment {

    @Inject
    private PaymentMethodService paymentMethodService;


    private PaymentMethod paymentMethod;

    public PayPalPayment() {
        super();

    }

    @PostConstruct
    public void init() {
        System.out.println("PayPalPayment init");
        paymentMethod = paymentMethodService.findByIdS("Paypal");
        this.description = paymentMethod.getDescription();
        this.detail = paymentMethod.getDetails();
    }

    @Override
    public String pay(Payment payment) {
        return String.format("PayPal Pay " + '\'' +
                "detail = '" + payment.getDetail() + '\'' +
                ", description = " + payment.getDescription());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("PayPalPayment Destroy");
    }
}
