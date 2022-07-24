package api.resources;

import database.dao.UserDao;
import jakarta.transaction.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserResourceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao dao;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testCreateOrUpdateUser() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException {
        UserDTO dto = new UserDTO();
        dto.setName("NewUserName");

        UserDTO dto2 = new UserDTO();
        dto2.setName("NewUserName");

        userService.createOrUpdateUser(dto);
        userService.createOrUpdateUser(dto2);
    }

}
