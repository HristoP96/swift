package Market;

import java.util.ArrayList;
import java.util.List;

public final class Person {

    private String _name;
    private double _balance;
    private final List<Product> _basket;

    public Person(String name, double balance) {
        this.setName(name);
        this.setBalance(balance);
        _basket = new ArrayList<>();

    }

    public void buyProduct(Product product) {
        if (!(_balance >= product.getPrice())) {
            throw new IllegalArgumentException(_name + " can't afford " + product.getName());
        }
        _basket.add(product);
        System.out.println(_name + " bought " + product.getName());
        _balance -= product.getPrice();
    }

    public void setBalance(double money) {
        if (!(money >= 0)) {

            throw new IllegalArgumentException("Balance can't be negative!");
        }
        this._balance = money;

    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be negative");
        }
        this._name = name;
    }

    public String getName() {
        return this._name;
    }

    public double getBalance() {

        return this._balance;

    }

    @Override
    public String toString() {
        String result = _name + " - ";
        if (_basket.isEmpty()) {
            throw new NullPointerException(result + "Nothing bought");
        }
        for (int i = 0; i < _basket.size()-1; i++) {
            result+= _basket.get(i).getName() + ", ";
        }
        result+= _basket.get(_basket.size()-1).getName();
        return result;//Pesho Milk
    }

}
