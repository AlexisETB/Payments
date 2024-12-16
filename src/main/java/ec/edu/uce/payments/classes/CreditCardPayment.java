package ec.edu.uce.payments.classes;

import ec.edu.uce.payments.jpa.Entities.PaymentMethod;
import ec.edu.uce.payments.jpa.Services.PaymentMethodService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@QualifierPay("CreditCard")
public class CreditCardPayment extends Payment {

    @Inject
    private PaymentMethodService paymentMethodService;


    private PaymentMethod paymentMethod;

    public CreditCardPayment() {
        super();

    }



    @PostConstruct
    public void init() {
        System.out.println("CreditCardPayment init");
        paymentMethod = paymentMethodService.findByIdS("CreditCard");
        this.description = paymentMethod.getDescription();
        this.detail = paymentMethod.getDetails();
    }

    @Override
    public String pay(Payment payment) {
        return String.format("CreditCard Pay " + '\'' +
                "detail = '" + payment.getDetail() + '\'' +
                ", description = " + payment.getDescription());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("CreditCardPayment Destroy");
    }
}
