package mylab.order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    @Test
    public void testShoppingCart() {
        // shoppingCart 객체가 Null이 아닌지 검증
        assertNotNull(shoppingCart);
        System.out.println("ShoppingCart: " + shoppingCart);

        // shoppingCart.getProducts().size() 검증
        assertEquals(2, shoppingCart.getProducts().size());

        // 첫 번째 상품이 "노트북"인지 검증
        assertEquals("노트북", shoppingCart.getProducts().get(0).getName());

        // 두 번째 상품이 "스마트폰"인지 검증
        assertEquals("스마트폰", shoppingCart.getProducts().get(1).getName());

        System.out.println("Product1: " + shoppingCart.getProducts().get(0));
        System.out.println("Product2: " + shoppingCart.getProducts().get(1));
    }

    @Test
    public void testOrderService() {
        // orderService 객체가 Null이 아닌지 검증
        assertNotNull(orderService);
        System.out.println("OrderService: " + orderService);

        // orderService의 shoppingCart가 Null이 아닌지 검증
        assertNotNull(orderService.getShoppingCart());

        // 주문 기능 테스트
        String result = orderService.placeOrder();
        System.out.println("주문 결과: " + result);
        assertNotNull(result);
        assertTrue(result.contains("주문 완료"));
    }
}
