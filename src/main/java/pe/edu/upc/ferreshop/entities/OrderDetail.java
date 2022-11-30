package pe.edu.upc.ferreshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_DETAIL"))
    private Order order;

    @Column
    private Long quantity;

    @Column
    private Long subtotal;


   @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "produc_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT"))
    private Product products;

}
