package service;

import dal.interfaces.ProductShopDao;
import dal.interfaces.ShopDao;
import entity.Product;
import entity.Shop;

import java.sql.SQLException;
import java.util.List;

public class ShopService {

    private ShopDao shopDao;
    private ProductShopDao productShopDao;

    public ShopService(Session session){
        this.shopDao = session.getShopDao();
        this.productShopDao = session.getProductShopDao();
    }

    public void printAllShops(){
        try {
            List list = shopDao.getAll();
            for (Object shop : list){
                System.out.println(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addShops(Shop shop){
        try {
            shopDao.add(shop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //создать магазин
    public void createShop(Shop shop)  {
        try {
            shopDao.add(shop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShop(Shop shop){
        try {
            shopDao.delete(shop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateShop(Shop shop){
        try {
            shopDao.update(shop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getShopById(Integer id){
        try {
            Shop shop = shopDao.getById(id);
            if (shop == null){
                System.out.println("Магазин не найден");
            }else{
                System.out.println(shop.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Найти магазин, в котором определенный товар самый дешевый
    public void cheapestShop(Product product){
        try {
            Shop getShopId = productShopDao.cheapestShop(product.getName());
            Shop shop = shopDao.getById(getShopId.getIdShop());
            //shopDao.getById(shop.getIdShop());
            System.out.println(shop.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
