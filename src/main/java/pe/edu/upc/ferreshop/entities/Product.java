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

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column( name ="brand")
    private byte[] brand;

    public Product() {

    }

    public Product(Long id, String name, String summary, Long quantity, Long price, boolean status, Business business, Category category, byte[] brand) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.business = business;
        this.category = category;
        this.brand = brand;
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
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public byte[] getBrand() {
        return brand;
    }

    public void setBrand(byte[] brand) {
        this.brand = brand;
    }
}
