package pe.edu.upc.ferreshop.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.ferreshop.entities.Order;
import pe.edu.upc.ferreshop.entities.OrderLine;
import pe.edu.upc.ferreshop.entities.Product;
import pe.edu.upc.ferreshop.exception.GeneralServiceException;
import pe.edu.upc.ferreshop.exception.NoDataFoundException;
import pe.edu.upc.ferreshop.exception.ValidateServiceException;
import pe.edu.upc.ferreshop.repository.OrderLineRepository;
import pe.edu.upc.ferreshop.repository.OrderRepository;
import pe.edu.upc.ferreshop.repository.ProductRepository;
import pe.edu.upc.ferreshop.validators.OrderValidator;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@Service
public class OrderServices{

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderLineRepository orderLineRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<Order> findAll(Pageable page){
        try {
            return orderRepo.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public Order findById(Long id) {
        try {
            return orderRepo.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

   /* public void delete(Long id) {
        try {
            Order order = orderRepo.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));

            orderRepo.delete(order);

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }*/

    @Transactional
    public Order save(Order order) {
        try {
            OrderValidator.save(order);

           /* User user= UserPrincipal.getCurrentUser();*/

            double total = 0;
            for(OrderLine line : order.getLines()) {
                Product product = productRepo.findById(line.getProduct().getId())
                        .orElseThrow(/*() -> new NoDataFoundException("No existe el producto " + line.getProduct().getId())*/);

                line.setPrice(product.getPrice());
                line.setTotal(product.getPrice() * line.getQuantity());
                total += line.getTotal();
            }
            order.setTotal(total);
            order.getLines().forEach(line -> line.setOrder(order));

            //Create Order
           /* if(order.getId() == null) {
                order.setUser(user);
                order.setRegDate(LocalDateTime.now());
                return orderRepo.save(order);
            }*/

            //Update Order
            Order savedOrder = orderRepo.findById(order.getId())
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
            //RegDate no se cambia, se mantiene la de creacion
           /* order.setRegDate(savedOrder.getRegDate());*/
            List<OrderLine> deletedLines = savedOrder.getLines();//Obtiene las lineas asociadas a la order obtenida
            deletedLines.removeAll(order.getLines());//Elimina las lineas asociadas a la orden
            orderLineRepo.deleteAll(deletedLines);

            return orderRepo.save(order);
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
