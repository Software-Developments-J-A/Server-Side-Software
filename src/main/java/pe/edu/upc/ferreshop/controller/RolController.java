package pe.edu.upc.ferreshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ferreshop.entities.Product;
import pe.edu.upc.ferreshop.entities.Rol;
import pe.edu.upc.ferreshop.exception.ResourceNotFoundException;
import pe.edu.upc.ferreshop.export.ProductExcelExporter;
import pe.edu.upc.ferreshop.export.RolExcelExporter;
import pe.edu.upc.ferreshop.repository.RolRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> getAllRoles(){
        List<Rol> roles=rolRepository.findAll();
        return new ResponseEntity<List<Rol>>(roles, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Rol> getRollById(@PathVariable("id") Long id){
        Rol rol= rolRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro el rol con id="+id));


        return new ResponseEntity<Rol>(rol,HttpStatus.OK);
    }
    @PostMapping("/roles")
    public ResponseEntity<Rol> createRol( @RequestBody Rol rol){
        Rol newRol=rolRepository.save(new Rol(rol.getName(),rol.getDescription()));
        return new ResponseEntity<Rol>(newRol,HttpStatus.CREATED);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable("id") Long id,@RequestBody Rol rol){
        Rol rolUpdate= rolRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro el rol con id="+id));

        rolUpdate.setName(rol.getName());
        rolUpdate.setDescription(rol.getDescription());

        return new ResponseEntity<Rol>(rolRepository.save(rolUpdate),HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<HttpStatus> deleteRol(@PathVariable("id") Long id){rolRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/roles/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_rol";
        response.setHeader(headerKey, headerValue);

        List<Rol> roles = rolRepository.findAll();

        RolExcelExporter excelExporter = new RolExcelExporter(
                roles);

        excelExporter.export(response);
    }


}
