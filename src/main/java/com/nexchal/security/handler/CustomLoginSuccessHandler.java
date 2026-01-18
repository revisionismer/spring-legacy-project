package com.nexchal.security.handler;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
	
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.nexchal.security.constant.UserEnum;

import lombok.extern.log4j.Log4j2;
	
@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("==================================");
		log.info("============ LoginSuccessHandler ===========");
			
		// 1-1.
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			
		// 1-2. 권한을 String형으로 담을 객체.
		List<String> roleNames = new ArrayList<>();
			
		// 1-3. 1-1에서 가져온 권한들을 반복문을 돌려 String 형태로 뽑아낸 후 1-2에 저장.
		authorities.forEach( (auth) -> {
			String authName = auth.getAuthority();
			
			roleNames.add(authName);
				
			log.info("==============" + roleNames + "=================");
		});
			
		// 1-4. 1-2에 저장된 권한 중에 해당 ROLE_ADMIN 권한이 있는지 검사
		if(roleNames.contains(UserEnum.ROLE_ADMIN.toString())) {
			response.sendRedirect("/sample/admin");
			return;
		}
			
		// 1-5. 1-2에 저장된 권한 중에 해당 ROLE_USER 권한이 있는지 검사
		if(roleNames.contains(UserEnum.ROLE_USER.toString())) {
			response.sendRedirect("/sample/user");
			return;
		}
			
		log.info("==================================");
	}
	
}
