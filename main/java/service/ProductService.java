package service;

import dal.interfaces.ProductDao;
import entity.Product;

import java.sql.SQLException;
import java.util.List;


public class ProductService {

    private ProductDao productDao;

    public ProductService(Session session){
        this.productDao = session.getProductDao();
    }

    public void printAllProducts(){
        try {
            List list = productDao.getAll();
            for (Object shop : list){
                System.out.println(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //создать товар
    public void createProduct(Product product) {
        try {
            productDao.add(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Product product){
        try {
            productDao.delete(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getProductById(String id){
        try {
            Product product = productDao.getById(id);
            if (product == null){
                System.out.println("Товар не найден");
            }else{
                System.out.println(product.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
