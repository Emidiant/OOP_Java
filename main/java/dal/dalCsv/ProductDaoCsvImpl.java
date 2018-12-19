package dal.dalCsv;

import dal.dalSql.DbConnection;
import dal.interfaces.ProductDao;
import entity.Product;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoCsvImpl implements ProductDao {

    private File file = new File("src\\main\\resources\\product.csv");

    private static ColumnPositionMappingStrategy setColumnMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Product.class);
        String[] columns = new String[] {"name"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

    public void add(Product object) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(object.toCSVformat() + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAll() {
        ArrayList<Product> shopList = new ArrayList<Product>();

        CsvToBean csv = new CsvToBean();
        String csvFilename = "src\\main\\resources\\product.csv";
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(csvFilename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List list = csv.parse(setColumnMapping(), csvReader);
        for (Object object : list) {
            Product product = (Product) object;
            shopList.add(product);
        }
        return shopList;
    }

    public Product getById(String id) {
        Product product = new Product();

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {

                if (nextline[0].equals(id)){
                    product.setName(nextline[0]);
                    return product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Product object) {
        System.out.println("Нецелесообразно");
    }

    public void delete(Product object) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!String.valueOf(line.split(",")[0]).equals(object.getName())) {
                    lines.add(line + '\n');
                }else{

                }
            }

            PrintWriter fileWriter = new PrintWriter(file);
            for (String i : lines){
                fileWriter.print(i);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
