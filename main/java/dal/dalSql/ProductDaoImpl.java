package dal.dalSql;

import dal.interfaces.ProductDao;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    public void add(Product product) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Product (Name) VAlUES(?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
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

    public List<Product> getAll() throws SQLException {
        Connection connection = DbConnection.getConnection();
        List<Product> productList = new ArrayList<Product>();

        String sql = "Select Name from Product";

        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("Name"));
                productList.add(product);
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

        return productList;
    }

    public Product getById(String id) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "Select Name from Product where Name=?";

        Product product = new Product();
        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                product.setName(resultSet.getString("Name"));
            }

            //preparedStatement.executeUpdate();
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

        return product;
    }


    public void update(Product product) {
        System.out.println("Это нецелесообразно!!!!");

    }

    public void delete(Product product) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "Delete from Product where [Name]=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, product.getName());

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
}
