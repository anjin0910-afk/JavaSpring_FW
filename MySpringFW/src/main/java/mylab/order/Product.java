package mylab.order;

public class Product {
    private String id;
    private String name;
    private int price;

    // 기본 생성자 (Setter Injection용)
    public Product() {
        System.out.println(this.getClass().getName() + " 기본생성자 호출됨!");
    }

    // 오버로딩 생성자 (Constructor Injection용)
    public Product(String id, String name, int price) {
        System.out.println(this.getClass().getName() + " 오버로딩 생성자 호출됨!");
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}
