package com.nexchal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nexchal.board.domain.AttachFileVO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@Component
@Log4j2
public class FileUtil {
	
	private final String uploadPath = "C:\\upload\\nexchal";

	public List<AttachFileVO> upload(MultipartFile[] files) {
		
		List<AttachFileVO> fileList = new ArrayList<>();
		
		// 1-1. 파일이 null이거나 파일이 없으면 그냥 return
		if(files == null || files.length == 0) {
			return fileList;
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
			
			// 2-1. 파일명에서 .뒤에 확장자를 가져온다.
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			// 2-2. 확장자 정규 표현식
			String regex = "^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)";
			
			// 2-3. 2-2의 확장자 정규 표현식을 이용하여 해당 .뒤에 문자가 위의 표현식 안에 포함된 문자열인지 체크하고  해당 파일이 등록된 확장자로된 이미지 파일일 경우에만 파일로 저장하게 한다.
			if(!suffix.matches(regex)) {
				continue;
			}
			
			// 1-6.
			try {
				// 1-7. 파일 데이터를 읽기 위해 InputStream 가져오기
				InputStream in = file.getInputStream();
				
				// 1-8. 업로드될 파일이 어디에 저장 될지 지정.
				OutputStream out = new FileOutputStream(uploadPath + File.separator + saveFileName);
				
				// 1-9. 파일 복사
				FileCopyUtils.copy(in, out);
				
				// 2-4. 2-1.2-2.2-3을 통과한 이미지 파일이라면 썸네일 이미지를 만든다. 
				Thumbnails.of(new File(uploadPath + File.separator + saveFileName)).size(200, 200)
						  .toFile(uploadPath + File.separator + "s_" + saveFileName);
				
				// 3-1. 
				AttachFileVO attachFileVO = new AttachFileVO();
				attachFileVO.setUuid(uuid);
				attachFileVO.setFilename(fileName);
				
				// 3-2.
				fileList.add(attachFileVO);
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		// 3-3. 업로드된 파일 리스트 정보 반환
		return fileList;
	}
}
