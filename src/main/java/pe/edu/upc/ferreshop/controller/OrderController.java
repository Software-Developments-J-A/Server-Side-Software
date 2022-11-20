package pe.edu.upc.ferreshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.*;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.repository.OrderRepository;
import pe.edu.upc.ferreshop.repository.StatusRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})

public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StatusRepository statusRepository;

    public OrderController(OrderRepository orderRepository, StatusRepository statusRepository) {
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
    }

    @PostMapping("/orders")
    @Transactional
    public ResponseEntity<Order> save(@RequestParam("date") String date,
                                      @RequestParam("price") String price,
                                      @RequestParam("status_id") Long statusId)throws IOException {

        Order order = new Order();
        order.setDate(date);
        order.setPrice(price);

        Status status    = statusRepository.findById(statusId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found status with id="+statusId));

        if( status!=null) {
            order.setStatus(status);
        }

        Order orderSaved=orderRepository.save(order);

        return new ResponseEntity<Order>(orderSaved, HttpStatus.CREATED);
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders=orderRepository.findAll();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        Order order= orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found order with id="+id));
        return new ResponseEntity<Order>(order,HttpStatus.OK);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable("id") Long id,
            @RequestBody Order order){
        Order orderUpdate= orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found order with id="+id));

        orderUpdate.setDate(order.getDate());
        orderUpdate.setPrice(order.getPrice());

        return new ResponseEntity<Order>(orderRepository.save(orderUpdate),HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") Long id){
        orderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
