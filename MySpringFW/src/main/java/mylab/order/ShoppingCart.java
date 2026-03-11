package mylab.order;

import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        System.out.println(this.getClass().getName() + " 기본생성자 호출됨!");
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getTotalPrice() {
        int total = 0;
        if (products != null) {
            for (Product product : products) {
                total += product.getPrice();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "ShoppingCart [products=" + products + ", totalPrice=" + getTotalPrice() + "]";
    }
}
