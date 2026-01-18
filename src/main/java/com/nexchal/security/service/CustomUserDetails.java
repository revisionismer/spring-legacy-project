package com.nexchal.security.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nexchal.user.domain.UserVO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final UserVO userVO;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 1-7. 권한이 없거나 null이면 return null
		if(userVO.getRoles() == null || userVO.getRoles().isEmpty()) {
			return null;
		}
		
		return userVO.getRoles()  // 1-8. userVO에서 List<UserAuthVO> 형태를 꺼내서
			  		 .stream()  // 1-9. stream으로 형태를 바꿔주고(아무 형태도 아님)
			  		 .map(auth -> new SimpleGrantedAuthority(auth.getRole()))  // 1-10. map으로 돌려서 해당 객체를 차례대로 SimpleGrantedAuthority 형태로 감싸서 반환한 다음 
			  		 .collect(Collectors.toList());  // 1-11. SimpleGrantedAuthority 형태로 변환된 데이터를 Collectors를 이용해 List형태로 만들어서 Collection에 담아서 반환.
	}

	@Override
	public String getPassword() {
		// 1-6. UserVO에서 password 반환
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		// 1-5. UserVO에서 username 반환
		return userVO.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 1-4. 이 계정 만료된거 아니지? : false -> true
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	    // 1-3. 이 계정 잠긴거 아니지? : false -> true
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 1-2. 이 계정 만료된거 아닌거 신뢰할만 하지? : false -> true
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 1-1. 이 계정 사용 가능 하지? : false -> true
		return true;
	}

}
