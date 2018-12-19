package dal.dalSql;

import dal.interfaces.ProductShopDao;
import entity.Product;
import entity.ProductShop;
import entity.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductShopDaoImpl implements ProductShopDao {

    public void add(ProductShop productShop) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        ProductShop pShop = getById(productShop);
        if (pShop != null){
            int count1 = pShop.getCount();
            int count2 = productShop.getCount();
            productShop.setCount(count1+count2);
            update(productShop);
        }else {
            String sql = "INSERT INTO ProductShop (idShop, Name, Cost, Count) VAlUES(?, ?, ?, ?)";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, productShop.getIdShop());
                preparedStatement.setString(2, productShop.getName());
                preparedStatement.setDouble(3, productShop.getCost());
                preparedStatement.setInt(4, productShop.getCount());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }
    }

    public List<ProductShop> getAll() throws SQLException {
        Connection connection = DbConnection.getConnection();
        List<ProductShop> productShopList = new ArrayList<ProductShop>();

        String sql = "Select idShop, Name, Сost, Count from ProductShop";

        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ProductShop productShop = new ProductShop();
                productShop.setIdShop(resultSet.getInt("idShop"));
                productShop.setName(resultSet.getString("Name"));
                productShop.setCost(resultSet.getDouble("Cost"));
                productShop.setCount(resultSet.getInt("Count"));
                productShopList.add(productShop);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return productShopList;
    }

    public ProductShop getById(ProductShop productShopCheck) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql ="Select idShop, Name, Cost, Count from ProductShop where idShop=? and Name=?";

        ProductShop productShop = new ProductShop();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productShopCheck.getIdShop());
            preparedStatement.setString(2, productShopCheck.getName());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                productShop.setIdShop(resultSet.getInt("idShop"));
                productShop.setName(resultSet.getString("Name"));
                productShop.setCost(resultSet.getDouble("Cost"));
                productShop.setCount(resultSet.getInt("Count"));
                return productShop;
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return null;
    }

    public void update(ProductShop productShop) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "Update ProductShop set Count=?, Cost = ? where idShop=? and Name=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productShop.getCount());
            preparedStatement.setDouble(2, productShop.getCost());
            preparedStatement.setInt(3, productShop.getIdShop());
            preparedStatement.setString(4, productShop.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }

    }

    public void delete(ProductShop productShop) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "Delete from ProductShop where idShop=? and Name=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, productShop.getIdShop());
            preparedStatement.setString(2, productShop.getName());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    public Shop cheapestShop(String productName) throws SQLException {
        Shop shop = new Shop();
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "select idShop from ProductShop "+
                "where Name = ? and cost = (select min(cost) from ProductShop where Name = ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                shop.setIdShop(resultSet.getInt("idShop"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }

        return shop;
    }

    @Override
    public List<String> setOfProductsForMoney(Shop shop, Double money) throws SQLException {
        //select Name, Count, Cost from ProductShop where idShop = 1 and 88/cost >= 1
        List<String> productShopList = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select Name, Count, Cost from ProductShop where idShop = ? and ?/cost >= 1";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, shop.getIdShop());
            preparedStatement.setDouble(2, money);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                int count = resultSet.getInt("Count");
                double cost = resultSet.getDouble("Cost");
                int counter = (int) (money/cost);
                if (counter > count){
                    counter = count;
                }
                String line = name + " " + counter;
                productShopList.add(line);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }

        return productShopList;
    }

    @Override
    public Double batchProductOrder(Product product, Shop shop, int count) throws SQLException {
        ProductShop productShop = new ProductShop();
        productShop.setIdShop(shop.getIdShop());
        productShop.setName(product.getName());
        ProductShop pShop = getById(productShop);
        Double totalCost = null;
        if (pShop.getCount() >= count){
            totalCost = count * pShop.getCost();

            if (pShop.getCount() == count){
                delete(pShop);
            }else{
                pShop.setCount(pShop.getCount() - count);
                update(pShop);
            }
        }
        return totalCost;
    }

    /*public ProductShop getByIdShopAndNameProduct(int idShop, String Name) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        String sql ="Select idShop, Name, Cost, Count from ProductShop where idShop=? and Name=?";

        ProductShop productShop = new ProductShop();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idShop);
            preparedStatement.setString(2, Name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                productShop.setIdShop(resultSet.getInt("idShop"));
                productShop.setName(resultSet.getString("Name"));
                productShop.setCost(resultSet.getInt("Cost"));
                productShop.setCount(resultSet.getInt("Count"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return productShop;
    }*/
}
