package dal.dalSql;

import dal.interfaces.ShopDao;
import entity.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopDaoImpl implements ShopDao {

    public void add(Shop shop) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO Shop (Name, Address, idShop) VAlUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, shop.getName());
            preparedStatement.setString(2, shop.getAddress());
            preparedStatement.setInt(3, shop.getIdShop());

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

    public List<Shop> getAll() throws SQLException {
        Connection connection = DbConnection.getConnection();
        List<Shop> shopList = new ArrayList<Shop>();

        String sql = "Select idShop, Name, Address from Shop";

        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Shop shop = new Shop();
                shop.setIdShop(resultSet.getInt("idShop"));
                shop.setName(resultSet.getString("Name"));
                shop.setAddress(resultSet.getString("Address"));
                shopList.add(shop);
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
        return shopList;
    }

    public Shop getById(Integer id) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "Select Name, Address, idShop from Shop where idShop =?";

        Shop shop = new Shop();
        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                shop.setName(resultSet.getString("Name"));
                shop.setAddress(resultSet.getString("Address"));
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

    public void update(Shop shop) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "Update Shop set [Name]=?, Address = ? where idShop=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, shop.getName());
            preparedStatement.setString(2, shop.getAddress());

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

    public void delete(Shop shop) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "Delete from Shop where idShop=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, shop.getIdShop());

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
