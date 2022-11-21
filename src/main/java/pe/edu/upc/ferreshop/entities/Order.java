package pe.edu.upc.ferreshop.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(name="client_name", length=50,nullable = false)
    private String client_name;

    @Column(name="phone", length=11,nullable = false)
    private Long phone;

    @Column(name="distric", length=50,nullable = false)
    private String distric;

    @Column(name="price", length=10,nullable = false)
    private String price;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_ORDER_PRODUCT"))
    private Product product;

    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetail> details;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private Status status;

    public Order() {}

    public Order(Long id, LocalDateTime orderDate, String client_name, Long phone, String distric, String price, Product product, List<OrderDetail> details, Status status) {
        this.id = id;
        this.orderDate = orderDate;
        this.client_name = client_name;
        this.phone = phone;
        this.distric = distric;
        this.price = price;
        this.product = product;
        this.details = details;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getDistric() {
        return distric;
    }

    public void setDistric(String distric) {
        this.distric = distric;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
