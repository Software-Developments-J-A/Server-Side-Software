package pe.edu.upc.ferreshop.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table  (name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",length =60,nullable = false)
    private String title;
    @Column(name = "brand",length =60,nullable = false)
    private String brand;
    @Column(name = "summary",length =60,nullable = false)
    private String summary;

    private Long price;
    //@ManyToOne
    //@JoinColumn(name = "user_id", nullable = false)
    //@JsonIgnore
    //private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

  //  public User getUser() {
    //    return user;
    //}

    //public void setUser(User user) {
      //  this.user = user;
    //}
}
