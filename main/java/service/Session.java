package service;

import dal.dalCsv.ProductDaoCsvImpl;
import dal.dalCsv.ProductShopDaoCsvImpl;
import dal.dalCsv.ShopDaoCsvImpl;
import dal.dalSql.ProductDaoImpl;
import dal.dalSql.ProductShopDaoImpl;
import dal.dalSql.ShopDaoImpl;
import dal.interfaces.ProductDao;
import dal.interfaces.ProductShopDao;
import dal.interfaces.ShopDao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Session {

    public ProductDao productDao;
    public ProductShopDao productShopDao;
    public ShopDao shopDao;

    public ProductDao getProductDao() {
        return productDao;
    }

    public ProductShopDao getProductShopDao() {
        return productShopDao;
    }

    public ShopDao getShopDao() {
        return shopDao;
    }

    public void initialize(){
        Properties property = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("C:\\Users\\User\\IdeaProjects\\ProductShop\\src\\main\\resources\\config.properties");

            property.load(fis);


            if (property.getProperty("type").equals("database")){
                System.out.println("Connected with DataBase");
                productDao = new ProductDaoImpl();
                shopDao = new ShopDaoImpl();
                productShopDao = new ProductShopDaoImpl();
            }else{
                System.out.println("Connected with CSV file");
                productDao = new ProductDaoCsvImpl();
                shopDao = new ShopDaoCsvImpl();
                productShopDao = new ProductShopDaoCsvImpl();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
