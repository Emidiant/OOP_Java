package entity;

public class Shop {

    private String name;
    private int idShop;
    private String address;

    public Shop(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (idShop != shop.idShop) return false;
        if (name != null ? !name.equals(shop.name) : shop.name != null) return false;
        return address != null ? address.equals(shop.address) : shop.address == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + idShop;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public String toCSVformat() {
        return idShop + "," + name;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", idShop=" + idShop +
                ", adress='" + address + '\'' +
                '}';
    }
}
