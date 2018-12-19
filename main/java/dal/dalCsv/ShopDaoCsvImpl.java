package dal.dalCsv;

import dal.dalSql.DbConnection;
import dal.interfaces.ShopDao;
import entity.Shop;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopDaoCsvImpl implements ShopDao {
    private File file = new File("src\\main\\resources\\shop.csv");

    public void add(Shop object) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(object.toCSVformat() + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static ColumnPositionMappingStrategy setColumnMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Shop.class);
        String[] columns = new String[] {"idShop", "name", "address"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

    public List<Shop> getAll() {
        ArrayList<Shop> shopList = new ArrayList<Shop>();

        CsvToBean csv = new CsvToBean();
        String csvFilename = "src\\main\\resources\\shop.csv";
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(csvFilename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List list = csv.parse(setColumnMapping(), csvReader);
        for (Object object : list) {
            Shop shop = (Shop) object;
            shopList.add(shop);
        }
        return shopList;
    }

    public Shop getById(Integer id) {
        Shop shop = new Shop();

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)
        ){
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {
               if (Integer.valueOf(nextline[0]) == id && nextline[0] != ""){
                    shop.setName(nextline[1]);
                    shop.setIdShop(Integer.valueOf(nextline[0]));
                    return shop;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Shop object) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (Integer.valueOf(line.split(",")[0]).equals(object.getIdShop())) {
                    line = object.toCSVformat();
                }
                lines.add(line + '\n');
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

    public void delete(Shop object) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!Integer.valueOf(line.split(",")[0]).equals(object.getIdShop())) {
                    lines.add(line + '\n');
                }else {

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
