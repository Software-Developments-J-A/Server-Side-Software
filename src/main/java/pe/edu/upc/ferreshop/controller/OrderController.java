package pe.edu.upc.ferreshop.controller;


import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.dto.OrderProcDTO;
import pe.edu.upc.ferreshop.entities.*;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.repository.OrderRepository;
import pe.edu.upc.ferreshop.repository.StatusRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})

public class OrderController {
/*
    private final OrderRepository orderRepository;

    private final StatusRepository statusRepository;

    public OrderController(OrderRepository orderRepository, StatusRepository statusRepository) {
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
    }
    @Transactional
    @PostMapping("/orders")
    public ResponseEntity<Order> save(@RequestParam("orderdate") LocalDateTime orderdate,
                                      @RequestParam("price") String price,
                                      @RequestParam("status_id") Long statusId)throws IOException {

        Order order = new Order();
        order.setOrderDate(orderdate);
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
    @Transactional(readOnly = true)
    @GetMapping("/orders/search/date")
    public ResponseEntity<List<Order>> searchByDates(@RequestParam(value = "datestart") String datestart, @RequestParam(value = "dateend") String dateend){
        List<Order> orders =orderRepository.searchByDates(
                LocalDateTime.parse(datestart), LocalDateTime.parse(dateend));
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/orders/search/others")
    public ResponseEntity<List<Order>> searchByOthers(@RequestParam(value = "id") String id, @RequestParam(value = "name") String fullName){
        List<Order> orders   = orderRepository.search(id,fullName);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/orders")
    public ResponseEntity<Order> save(@Valid @RequestBody Order order) {
        order.getDetails().forEach(det -> det.setOrder(order));
        Order newOrder=orderRepository.save(order);
        return new ResponseEntity<Order>(newOrder,HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/orders/callProcedure")
    public ResponseEntity<List<OrderProcDTO>> callProcOrFunction(){
        List<OrderProcDTO> orders = new ArrayList<>();
        orderRepository.callProcedureOrFunction().forEach(x -> {
            OrderProcDTO dto = new OrderProcDTO();
            dto.setQuantity((Integer) x[0]);
            dto.setOrderdate((LocalDateTime) x[1]);
            orders.add(dto);
        });
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable("id") Long id,
            @RequestBody Order order){
        Order orderUpdate= orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found order with id="+id));

        orderUpdate.setOrderDate(order.getOrderDate());
        orderUpdate.setPrice(order.getPrice());

        return new ResponseEntity<Order>(orderRepository.save(orderUpdate),HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") Long id){
        orderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
