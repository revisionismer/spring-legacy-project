package com.nexchal.security;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nexchal.config.security.constant.UserEnum;
import com.nexchal.user.domain.UserAuthVO;
import com.nexchal.user.domain.UserVO;
import com.nexchal.user.mappers.UserMapper;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"
})
@Log4j2
public class UserTests {

	@Autowired // xml로 설정되어 있어서 주입 가능
	PasswordEncoder passwordEncoder;
	
	@Autowired(required = false) // xml로 설정되어 있지 않아서 required false;
	UserMapper userMapper;
	
	@Test
	public void testUserInsert() {
		log.info("========== 사용자 생성 테스트 =========");
		
		// 관리자 1명
		UserVO adminVO = new UserVO();
		
		adminVO.setUsername("admin");
		adminVO.setPassword(passwordEncoder.encode("1234"));
		adminVO.setName("관리자");
		adminVO.setEmail("admin@test.com");
		adminVO.setDeleteYn(false);
		
		List<UserAuthVO> admin_authList = new ArrayList<>();
		
		UserAuthVO adm_ROLE_USER = new UserAuthVO();
		adm_ROLE_USER.setUsername(adminVO.getUsername());
		adm_ROLE_USER.setRole(UserEnum.ROLE_USER.toString());
		
		admin_authList.add(adm_ROLE_USER);
		
		UserAuthVO adm_ROLE_ADMIN = new UserAuthVO();
		adm_ROLE_ADMIN.setUsername(adminVO.getUsername());
		adm_ROLE_ADMIN.setRole("ROLE_ADMIN");
		
		admin_authList.add(adm_ROLE_ADMIN);
		
		userMapper.joinUser(adminVO);
		
		admin_authList.stream().forEach((authVO) -> {
			userMapper.insertUserAuth(authVO);
		});
		
		// 사용자 100명
		for(int i = 1; i <= 100; i++) {
			UserVO userVO = new UserVO();
			
			userVO.setUsername("user" + i);
			userVO.setPassword(passwordEncoder.encode("1234"));
			userVO.setName("유저" + i);
			userVO.setEmail("user" + i + "@test.com");
			userVO.setDeleteYn(false);
		
			List<UserAuthVO> user_authList = new ArrayList<>();
			
			UserAuthVO ROLE_USER = new UserAuthVO();
			ROLE_USER.setUsername("user" + i);
			ROLE_USER.setRole("ROLE_USER");
			
			user_authList.add(ROLE_USER);
			
			userMapper.joinUser(userVO);

			user_authList.stream().forEach((authVO) -> {
				userMapper.insertUserAuth(authVO);
			});
			
		}
		
		log.info("============================");
		
	}
	
	@Test
	public void testResultMapSelect() {
		String username = "admin";
		
		log.info(userMapper.selectUser(username));
	}
}
