package org.eduardomango.practicaspringweb.model.services;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.exceptions.ProductNotFoundException;
import org.eduardomango.practicaspringweb.model.exceptions.SaleNotFoundException;
import org.eduardomango.practicaspringweb.model.exceptions.UserNotFoundException;
import org.eduardomango.practicaspringweb.model.repositories.IRepository;
import org.eduardomango.practicaspringweb.model.repositories.ProductRepository;
import org.eduardomango.practicaspringweb.model.repositories.UserRepository;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class SaleService{
    private ProductService serviceProduct;
    private UserService serviceUser;
    private IRepository<SaleEntity> sales;



    public void listarVentas(){
        System.out.println(sales.toString());
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
        }catch(ProductNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch(UserNotFoundException a){
            System.out.println(a.getMessage());
            a.printStackTrace();
        }
    }


    public List<SaleEntity> findAll() {
        return sales.findAll();
    }

    public void save(SaleEntity sale) {
        sales.save(sale);
    }

    public void delete(SaleEntity sale) {
        try{
            for(SaleEntity e:sales.findAll()){
                if(e.getId()==sale.getId()){
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
                }
            }
            throw new SaleNotFoundException("Oh no bro");
        }catch (SaleNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}