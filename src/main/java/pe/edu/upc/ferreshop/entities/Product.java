package pe.edu.upc.ferreshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", length=60, nullable = false)
    private String name;
    @Column(name="summary", length=255, nullable = false)
    private String summary;
    @Column(name="brand", length=255, nullable = false)
    private String brand;
    @Column(name="quantity",nullable = false)
    private Long quantity;
    @Column(name="price",nullable = false)
    private Long price;
    @Column(name="status")
    private boolean status;


    @ManyToOne
    @JoinColumn(name="business_id",nullable = false)
    @JsonIgnore
    private Business business;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnoreProperties ( {"hibernateLazyInitializer", "handler"})
    @JsonIgnore

    private Category category;

    public Product() {
    }

    public Product(String name, String summary, String brand,Long quantity,Long price, boolean status, Business business, Category category) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.category= category;
        this.business = business;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   public Business getBusiness() {
        return  business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category =category; }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", brand='" + brand + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", status=" + status + '\'' +
                ", business=" + business +
                ", category=" + category +
                '}';
    }
}
