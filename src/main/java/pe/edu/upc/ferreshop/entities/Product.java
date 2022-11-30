package pe.edu.upc.ferreshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "products",
            cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column( name ="brand")
    private byte[] brand;

    public Product() {

    }


}
