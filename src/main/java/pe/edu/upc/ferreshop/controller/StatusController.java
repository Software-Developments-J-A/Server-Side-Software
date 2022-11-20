package pe.edu.upc.ferreshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.*;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.export.ProductExcelExporter;
import pe.edu.upc.ferreshop.export.StatusExcelExporter;
import pe.edu.upc.ferreshop.repository.OrderRepository;
import pe.edu.upc.ferreshop.repository.StatusRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})

public class StatusController {

   @Autowired
    private OrderRepository orderRepository;
   @Autowired
    private StatusRepository statusRepository;

    public StatusController(OrderRepository orderRepository, StatusRepository statusRepository) {
        this.orderRepository = orderRepository;
        this.statusRepository = statusRepository;
    }

    @Transactional
    @GetMapping("/status/{id}")
    public ResponseEntity<Status> searchStatusById(@PathVariable Long id) {
        Status status= statusRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found status with id="+id));
        return new ResponseEntity<Status>(status,HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/status")
    public ResponseEntity<Status> save(@RequestBody Status status) {
        Status newStatus= Status.builder().name(status.getName()).description(status.getDescription()).build();
        statusRepository.save(newStatus);
        return new ResponseEntity<Status>(newStatus,HttpStatus.CREATED);
    }


    @Transactional
    @PutMapping("/status/{id}")
    public ResponseEntity<Status> update(@RequestBody Status status, @PathVariable Long id) {
        Status statusUpdate= statusRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found status with id="+id));

        statusUpdate.setName(status.getName());

        return new ResponseEntity<Status>(statusRepository.save(statusUpdate),HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/status/{id}")
    public ResponseEntity<Status> delete(@PathVariable Long id) {
        statusRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_status";
        response.setHeader(headerKey, headerValue);

        List<Status> statuses = statusRepository.findAll();

        StatusExcelExporter excelExporter = new StatusExcelExporter(
                statuses);

        excelExporter.export(response);
    }


}
