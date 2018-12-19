package client;

import entity.Product;
import entity.ProductShop;
import entity.Shop;
import service.ProductService;
import service.ProductShopService;
import service.Session;
import service.ShopService;

public class Main {

    public static void main(String[] args) {

        Session session = new Session();
        session.initialize();

        ProductService productService = new ProductService(session);
        ProductShopService productShopService = new ProductShopService(session);
        ShopService shopService = new ShopService(session);

        Shop shop = new Shop();
        shop.setName("Ашан");
        shop.setIdShop(3);

        Product product = new Product();
        product.setName("Сидр Strongbow");

        ProductShop productShop = new ProductShop();
        productShop.setIdShop(4);
        productShop.setCost(190);
        productShop.setCount(15);
        productShop.setName("Сидр Strongbow");

        //shopService.addShops(shop);
        //productService.createProduct(product);

        //productShopService.addProductShop(productShop);
        //productShopService.checkShopAndProduct(productShop);
        //productShopService.updateProductShop(productShop);

        //productService.getProductById("Телевизор PHILIPS");

        //shopService.deleteShop(shop);
        //productService.deleteProduct(product);
        //productShopService.deleteProductShop(productShop);

        //shopService.updateShop(shop);
        //shopService.getShopById(2);

        //productService.printAllProducts();
        //shopService.printAllShops();

        //productShopService.printAllProductShops();


        //shopService.cheapestShop(product);
        //productShopService.setOfProductsForMoney(shop, 100);
        //productShopService.batchProductOrder(product, shop, 10);

        //productService.createProduct(product);
        //shopService.createShop(shop);
        //productShopService.deliveryOfProducts(product, shop, 399, 50);
    }
}
