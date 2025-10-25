package com.nexchal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class FileUtil {
	
	private final String uploadPath = "C:\\upload\\nexchal";

	public void upload(MultipartFile[] files) {
		
		// 1-1. 파일이 null이거나 파일이 없으면 그냥 return
		if(files == null || files.length == 0) {
			return;
		}
		
		for(MultipartFile file : files) {
			
			// 1-2. files에서 뽑아낸 file 사이즈가 0이면 for loop를 벗어난다.
			if(file.getSize() == 0) {
				continue;  // Tip : continue는 반복문의 현재 단계를 중단, break는 반복문 전체를 중단
			}
			
			// 1-3. 파일이 존재하면 파일 이름을 가져온다.
			String fileName = file.getOriginalFilename();
			
			// 1-4. 파일명을 저장하기 위해 사용할 UUID를 이용한 랜덤 값을 만든다.
			String uuid = UUID.randomUUID().toString();
			
			// 1-5. 1-4 + 1-3으로 저장될 파일 이름을 생성
			String saveFileName = uuid + "_" + fileName;
			
			// 1-6.
			try {
				// 1-7. 파일 데이터를 읽기 위해 InputStream 가져오기
				InputStream in = file.getInputStream();
				
				// 1-8. 업로드될 파일이 어디에 저장 될지 지정.
				OutputStream out = new FileOutputStream(uploadPath + File.separator + saveFileName);
				
				// 1-9. 파일 복사
				FileCopyUtils.copy(in, out);
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
}
