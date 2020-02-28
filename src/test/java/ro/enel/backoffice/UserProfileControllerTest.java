package ro.enel.backoffice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.enel.backoffice.controller.UserProfileController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class UserProfileControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.standaloneSetup(new UserProfileController())
				.build();
	}

	@Test
	@WithMockUser(username="user", password="pass")
	public void testUserInfo() throws Exception {
		mockMvc
				.perform(get("/v1/users/info"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
