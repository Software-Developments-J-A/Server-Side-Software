package pe.edu.upc.ferreshop.entities;

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
    @Column(name="status")
    private boolean status;

    public Product() {
    }

    public Product(String name, String summary, String brand, boolean status) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.brand = brand;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", brand='" + brand + '\'' +
                ", status=" + status +
                '}';
    }
}
