package entity;

public class ProductShop {

    private int idShop;
    private String name;
    private double cost;
    private int count;

    public ProductShop(){

    }

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductShop that = (ProductShop) o;

        if (idShop != that.idShop) return false;
        if (cost != that.cost) return false;
        if (count != that.count) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idShop;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + count;
        return result;
    }

    public String toCSVformatWithName() {
        return name + "," + idShop + "," + count + "," + cost;
    }
    public String toCSVformat() {
        return idShop + "," + count + "," + cost;
    }

    @Override
    public String toString() {
        return "ProductShop{" +
                "idShop=" + idShop +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", count=" + count +
                '}';
    }
}
