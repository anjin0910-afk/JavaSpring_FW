package mylab.order;

public class OrderService {
    private ShoppingCart shoppingCart;

    public OrderService() {
        System.out.println(this.getClass().getName() + " 기본생성자 호출됨!");
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String placeOrder() {
        if (shoppingCart == null || shoppingCart.getProducts() == null) {
            return "장바구니가 비어 있습니다.";
        }
        int totalPrice = shoppingCart.getTotalPrice();
        int itemCount = shoppingCart.getProducts().size();
        return "주문 완료! 상품 수: " + itemCount + ", 총 금액: " + totalPrice + "원";
    }

    @Override
    public String toString() {
        return "OrderService [shoppingCart=" + shoppingCart + "]";
    }
}
