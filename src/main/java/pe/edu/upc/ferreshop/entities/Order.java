package pe.edu.upc.ferreshop.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date", length=60,nullable = false)
    private String date;

    @Column(name="price", length=10,nullable = false)
    private String price;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
    @JsonIgnore

    private Status status;

    public Order() {
    }

    public Order(Long id, String date, String price, Product product, Status status) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.product = product;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", product='" + product + '\'' +
                ", status=" + status +
                '}';
    }

}
