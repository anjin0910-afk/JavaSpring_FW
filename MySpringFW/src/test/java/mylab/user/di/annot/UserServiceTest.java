package mylab.user.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void testUserService() {
        // UserService가 Not Null 인지 검증
        assertNotNull(userService);

        // userService.getUserRepository()가 Not Null 인지 검증
        assertNotNull(userService.getUserRepository());

        // userService.getUserRepository().getDbType() 값이 MySQL 인지 비교
        assertEquals("MySQL", userService.getUserRepository().getDbType());

        // userService.getSecurityService()가 Not Null 인지 검증
        assertNotNull(userService.getSecurityService());

        // userService.registerUser() 메서드가 True 인 검증
        assertTrue(userService.registerUser("user01", "홍길동", "password123"));
    }
}
