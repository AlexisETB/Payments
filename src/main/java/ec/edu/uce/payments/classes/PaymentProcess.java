package ec.edu.uce.payments.classes;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped

public class PaymentProcess {


    public String processPayment(IPay payment) {
        if (payment instanceof Payment){
            Payment payment1 = (Payment) payment;
            return payment.pay(payment1);
        }else return null;

    }
}