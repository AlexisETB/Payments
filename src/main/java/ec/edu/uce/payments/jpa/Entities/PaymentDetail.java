package ec.edu.uce.payments.jpa.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment")
public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserP user;

    @ManyToMany
    @JoinTable(
            name = "payment_producto",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Product> products = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "metodo_pago_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private double totalAmount;

    public void calculateTotalAmount() {
        this.totalAmount = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }


    public PaymentDetail() {}

    public PaymentDetail(UserP user, PaymentMethod paymentMethod, double totalAmount) {
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.products = new ArrayList<>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserP getUser() {
        return user;
    }

    public void setUser(UserP user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}