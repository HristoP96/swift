
package Market;


public class Product {

    private String _name;
    private double _price;

    public Product(String name, double price) {
        this._setName(name);
        this.setPrice(price);
    }

    public void _setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be negative");
        }
        this._name = name;
    }

    private void setPrice(double setPrice) {
        if (!(setPrice >= 0)) {
            throw new IllegalArgumentException("Price can't be negative!");
        }
        _price = setPrice;
    }

    public String getName() {
        return _name;
    }

    protected double getPrice() {

        return _price;

    }

}
