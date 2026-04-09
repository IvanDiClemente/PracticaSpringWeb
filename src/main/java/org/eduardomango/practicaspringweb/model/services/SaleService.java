package org.eduardomango.practicaspringweb.model.services;

import lombok.*;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.exceptions.ProductNotFoundException;
import org.eduardomango.practicaspringweb.model.exceptions.RepeatedIDException;
import org.eduardomango.practicaspringweb.model.exceptions.SaleNotFoundException;
import org.eduardomango.practicaspringweb.model.exceptions.UserNotFoundException;
import org.eduardomango.practicaspringweb.model.repositories.IRepository;
import org.eduardomango.practicaspringweb.model.repositories.ProductRepository;
import org.eduardomango.practicaspringweb.model.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@Service
public class SaleService{
    private ProductService serviceProduct;
    private UserService serviceUser;
    private IRepository<SaleEntity> sales;



    public String listarVentas(){
        return sales.findAll().toString();
    }

    public void registrarVenta(long IDProducto, long idCliente, long cantidad){
        ProductEntity product;
        UserEntity user;
        try{
            if((product=serviceProduct.findById(IDProducto))!=null){
                if((user=serviceUser.findById(idCliente))!=null){
                    save(SaleEntity.builder().id(1L).products(serviceProduct.findById(IDProducto))
                            .client(serviceUser.findById(idCliente)).saleDate(LocalDate.now()).build());
                }else{
                    throw new UserNotFoundException("Oh no bro");
                }
            }else{
                throw new ProductNotFoundException("Oh no bro");
            }
        }catch(ProductNotFoundException | UserNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public List<SaleEntity> findAll() {
        return sales.findAll();
    }

    public void save(SaleEntity sale) {
        try{
            for(SaleEntity e:sales.findAll()){
                if(e.getId()==sale.getId()){
                    throw new RepeatedIDException();
                }
            }
            sales.save(sale);
        }catch (RepeatedIDException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(SaleEntity sale){
        try{
            for(SaleEntity e:sales.findAll()){
                if(e.getId()==sale.getId()){
                    sales.delete(sale);
                    return;
                }
            }
            throw new SaleNotFoundException("Oh no bro");
        }catch(SaleNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(SaleEntity sale) {
        try{
            for(SaleEntity e:sales.findAll()){
                if(e.getId()==sale.getId()){
                    sales.update(sale);
                    return;
                }
            }
            throw new SaleNotFoundException("Oh no bro");
        }catch (SaleNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}