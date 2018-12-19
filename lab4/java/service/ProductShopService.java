package service;

import dal.interfaces.ProductDao;
import dal.interfaces.ProductShopDao;
import dal.interfaces.ShopDao;
import entity.Product;
import entity.ProductShop;
import entity.Shop;

import java.sql.SQLException;
import java.util.List;

public class ProductShopService {

    private ProductShopDao productShopDao;
    private ShopDao shopDao;
    private ProductDao productDao;

    public ProductShopService(Session session){
        this.productShopDao = session.getProductShopDao();
        this.shopDao = session.getShopDao();
        this.productDao = session.getProductDao();
    }

    public void printAllProductShops(){
        try {
            List list = productShopDao.getAll();
            for (Object productShop : list){
                System.out.println(productShop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProductShop(ProductShop productShop){
        try {
            productShopDao.add(productShop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductShop(ProductShop productShop){
        try {
            productShopDao.delete(productShop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductShop(ProductShop productShop){
        try {
            productShopDao.update(productShop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkShopAndProduct(ProductShop productShop){
        try {
            ProductShop pShop = productShopDao.getById(productShop);
            if (pShop != null) {
                System.out.println(pShop.toString());
            }else{
                System.out.println("нет такой пары");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Купить партию товаров в магазине (параметры - сколько каких товаров купить,
    // метод возвращает общую стоимость покупки либо её невозможность, если товара не хватает)
    public void batchProductOrder(Product proroduct, Shop shop, int count){
        try {
            Double result = productShopDao.batchProductOrder(proroduct, shop, count);
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Понять, какие товары можно купить в магазине на некоторую сумму (например, на
    //100 рублей можно купить три мороженки или две шоколадки)
    public void setOfProductsForMoney(Shop shop, double money){
        try {
            List<String> pShopList = productShopDao.setOfProductsForMoney(shop, money);
            for (String i : pShopList){
                System.out.println(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //завезти партию товара в магазин
    public void deliveryOfProducts(Product product, Shop shop, double cost, int count) {

        try {
            if (shopDao.getById(shop.getIdShop()).getIdShop() == shop.getIdShop()){
                System.out.println("Магазин найден");
                if (productDao.getById(product.getName()).getName() != null){
                    System.out.println("Товар найден");

                    ProductShop productShop = new ProductShop();
                    productShop.setCost(cost);
                    productShop.setCount(count);
                    productShop.setIdShop(shop.getIdShop());
                    productShop.setName(product.getName());
                    productShopDao.add(productShop);

                }else {
                    System.out.println("Товар не создан!");
                }
            }
            else{
                System.out.println("Магазин не создан!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
