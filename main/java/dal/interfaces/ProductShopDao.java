package dal.interfaces;

import dal.Dao;
import entity.Product;
import entity.ProductShop;
import entity.Shop;

import java.sql.SQLException;
import java.util.List;

public interface ProductShopDao extends Dao<ProductShop, ProductShop> {

    Shop cheapestShop(String productName) throws SQLException;

    List<String> setOfProductsForMoney (Shop shop, Double money) throws SQLException;

    Double batchProductOrder(Product product, Shop shop, int count) throws SQLException;
}



