import com.engineeringdigest.journalApp.entity.User;
import com.engineeringdigest.journalApp.repository.UserRepository;
import com.engineeringdigest.journalApp.service.UserDetailServiceIMPL;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class UserDetailServiceIMPLTests {

   @Mock
   private UserRepository userRepository;

   @InjectMocks
   private UserDetailServiceIMPL userDetailService;

   @Test
   void loadUserByUsernameTest() {

       User appUser = new User("one","123");
//        appUser.setUsername("one");
//        appUser.setPassword("123");
       appUser.setRoles(List.of("USER"));

       when(userRepository.findByUsername(anyString()))
               .thenReturn(appUser);

       UserDetails userDetails = userDetailService.loadUserByUsername("one");

       assertEquals("one", userDetails.getUsername());
   }
}
