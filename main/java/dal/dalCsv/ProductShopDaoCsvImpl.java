package dal.dalCsv;

import dal.dalSql.DbConnection;
import dal.interfaces.ProductShopDao;
import entity.Product;
import entity.ProductShop;
import entity.Shop;
import au.com.bytecode.opencsv.CSVReader;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ProductShopDaoCsvImpl implements ProductShopDao {

    private File file = new File("src\\main\\resources\\productShop.csv");

    public void add(ProductShop object) {
        List<String> productShopList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;

            boolean flag = false;
            while ((nextline = reader.readNext()) != null) {
                String line = nextline[0];
                if (nextline[0].equals(object.getName())){
                    boolean flag1 = false;
                    //нашли столбец с названием, теперь дописываем в конец строки
                    for (int i = 1; i < nextline.length; i += 3){

                        if (nextline[i].equals(String.valueOf(object.getIdShop()))){

                            int newCont = object.getCount() + Integer.valueOf(nextline[i+1]);
                            line = line + "," + nextline[i] + "," + newCont + "," + object.getCost();
                            flag1 = true;
                            System.out.println("id совпал");
                        }else{
                            line = line + "," + nextline[i] + "," + nextline[i+1] + "," + nextline[i+2];
                        }
                    }
                    if (!flag1) {
                        line = line + "," + object.toCSVformat() + "\n";
                    }else{
                        line = line + "\n";
                    }
                    productShopList.add(line);
                    flag = true;
                }else {
                    for (int i = 1; i < nextline.length; i++){
                        line = line + "," + nextline[i];
                    }
                    line = line + "\n";
                    productShopList.add(line);
                }
            }

            PrintWriter writer = new PrintWriter(file);

            for (String i : productShopList){
                writer.print(i);
            }
            writer.close();

            //если не нашли
            if (!flag){
                try (FileWriter fileWriter = new FileWriter(file, true)) {
                    fileWriter.write(object.toCSVformatWithName() + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<ProductShop> getAll() {
       List<ProductShop> productShopList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {
                for (int i = 1; i < nextline.length; i += 3){
                    ProductShop productShop = new ProductShop();
                    productShop.setName(nextline[0]);
                    productShop.setIdShop(Integer.valueOf(nextline[i]));
                    productShop.setCount(Integer.valueOf(nextline[i+1]));
                    productShop.setCost(Double.valueOf(nextline[i+2]));
                    productShopList.add(productShop);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productShopList;
    }

    public ProductShop getById(ProductShop pShop) {
        //передаём id магазина и name товара. проверяем соотвествие
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {
                ProductShop productShop = new ProductShop();
                if (nextline[0].equals(pShop.getName())){
                    for (int i = 1; i < nextline.length; i += 3) {
                        productShop.setName(nextline[0]);
                        if (Integer.valueOf(nextline[i]) == pShop.getIdShop()) {
                            productShop.setIdShop(Integer.valueOf(nextline[i]));
                            productShop.setCount(Integer.valueOf(nextline[i + 1]));
                            productShop.setCost(Double.valueOf(nextline[i + 2]));
                            return productShop;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(ProductShop pShop) {
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;
            List<String> productShopList = new ArrayList<>();
            while ((nextline = reader.readNext()) != null) {
                String list = nextline[0];
                if (nextline[0].equals(pShop.getName())){
                    for (int i = 1; i < nextline.length; i += 3) {
                        if (Integer.valueOf(nextline[i]) == pShop.getIdShop()) {
                            nextline[i] = String.valueOf(pShop.getIdShop());
                            nextline[i+1] = String.valueOf(pShop.getCount());
                            nextline[i+2] = String.valueOf(pShop.getCost());
                            list = list + "," + nextline[i] + "," + nextline[i+1] + "," + nextline[i+2];
                        }else{
                            list = list + "," + nextline[i] + "," + nextline[i+1] + "," + nextline[i+2];
                        }

                    }
                    list = list + "\n";
                    productShopList.add(list);
                }
                else{
                    for (int i = 1; i < nextline.length; i += 3) {
                        list = list + "," + nextline[i] + "," + nextline[i+1] + "," + nextline[i+2];
                    }
                    list = list + "\n";
                    productShopList.add(list);
                }
            }

            PrintWriter writer = new PrintWriter(file);

            for (String i : productShopList){
                writer.print(i);
            }
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(ProductShop productShop) {
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;
            List<String> productShopList = new ArrayList<>();
            while ((nextline = reader.readNext()) != null) {
                String list = nextline[0];
                if (nextline[0].equals(productShop.getName())){
                    for (int i = 1; i < nextline.length; i += 3) {
                        if (Integer.valueOf(nextline[i]) != productShop.getIdShop()) {
                            list = list + "," + nextline[i] + "," + nextline[i + 1] + "," + nextline[i + 2];
                        }
                    }
                    if(!list.equals(nextline[0])){
                        list = list + "\n";
                        productShopList.add(list);
                    }
                }
                else{
                    for (int i = 1; i < nextline.length; i += 3) {
                        list = list + "," + nextline[i] + "," + nextline[i+1] + "," + nextline[i+2];
                    }
                    list = list + "\n";
                    productShopList.add(list);
                }
            }

            PrintWriter writer = new PrintWriter(file);

            for (String i : productShopList){
                writer.print(i);
            }
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Shop cheapestShop(String productName) {
        Shop shop = new Shop();
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;
            double min = 999999999;

            while ((nextline = reader.readNext()) != null) {
                if (nextline[0].equals(productName)){
                    for (int i = 1; i < nextline.length; i += 3) {
                        if (Double.valueOf(nextline[i+2]) < min) {
                            min = Double.valueOf(nextline[i+2]);
                            shop.setIdShop(Integer.valueOf(nextline[i]));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shop;
    }

    @Override
    public List<String> setOfProductsForMoney(Shop shop, Double money) {
        List<String> productShopList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {
                String line = nextline[0];//имя продукта
                for (int i = 1; i < nextline.length; i += 3) {
                    int counter = 0;
                    if (Integer.valueOf(nextline[i]).equals(shop.getIdShop())) {
                        counter = (int) (money / Double.valueOf(nextline[i+2]));
                        if (counter > 0){
                            if (counter > Integer.valueOf(nextline[i+1])){
                                line = line + " " + nextline[i+1];
                                productShopList.add(line);
                            }else{
                                line = line + " " + counter;
                                productShopList.add(line);
                            }
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productShopList;
    }
    //Купить партию товаров в магазине (параметры - сколько каких товаров купить,
    //метод возвращает общую стоимость покупки либо её невозможность, если товара не хватает)

    //на вход получаем строку товара, сравниваем количество имеющихся с нужным, если подходит,
    // умножаем цену на нужное количество и выводим в лист с id магазина,
    // которое меняем на название методом. возможно, надо апдейтить строку, понижая количество товара в магазине
    @Override
    public Double batchProductOrder(Product product, Shop shop, int count) {
        Double productShopList = null;

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {
                if (nextline[0].equals(product.getName())){
                    for (int i = 1; i < nextline.length; i += 3) {
                        if (Integer.valueOf(nextline[i+1]) >= count) {
                            if (Integer.valueOf(nextline[i]) == shop.getIdShop()){
                                double totalCost = count*Double.valueOf(nextline[i+2]);
                                int idShop = Integer.valueOf(nextline[i]);

                                productShopList = totalCost;
                                ProductShop productShop = new ProductShop();
                                productShop.setName(product.getName());
                                productShop.setCost(Double.valueOf(nextline[i+2]));
                                productShop.setIdShop(idShop);
                                if (Integer.valueOf(nextline[i+1]) == count){
                                    productShop.setCount(count);
                                    delete(productShop);
                                    System.out.println("delete " + productShop.toString());
                                }else{
                                    productShop.setCount(Integer.valueOf(nextline[i+1]) - count);
                                    update(productShop);
                                    System.out.println("update " + productShop.toString());
                                }
                            }
                        }
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productShopList;
    }
}
