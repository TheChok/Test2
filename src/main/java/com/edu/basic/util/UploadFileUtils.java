package com.edu.basic.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	//로깅을 위한 변수
	private static final Logger logger 
		= LoggerFactory.getLogger(UploadFileUtils.class);

	//파일을 업로드하면 호출되는 메소드
	//날짜별로 디렉토리를 만들어서 파일을 올리기로 한다.
	public static String uploadFile(String uploadPath
			, String originalName, byte[] fileData) 
					throws Exception {
		// uuid 발급
		UUID uid = UUID.randomUUID();

		//uuid를 추가한 파일 이름
		//파일이름이 같으면 덮어쓰기를 하게 되므로 uuid를 추가한다.
		String savedName = uid.toString() + "_" + originalName;
		
		// 업로드할 디렉토리 생성한다.
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath 
				+ savedPath, savedName);

		//파일을 업로드하게 되면 서버의 임시 디렉토리에 업로드가 된다.
		//FileCopyUtils.copy를 사용하면 지정한 디렉토리에 저장할 수 있다. 
		// 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);

		//파일의 확장자를 검사해야 이미지파일인지 아닌지를 구분할 수 있다.
		//어떤 파일은 콤마가 여러개 있기 때문에 마지막 마침표 이후가 확장자인지를 구분해야 한다.
		// 파일의 확장자 검사
		// a.jpg / aaa.bbb.ccc.jpg
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;

		//이미지 파일은 썸네일을 생성
		//MediaUtils.getMediaType(formatName)에 마지막 확장자를 넣어서 검사하면 이미지 파일인지 알 수 있다.
		if (MediaUtils.getMediaType(formatName) != null) {
			// 썸네일 생성
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else { //이미지 파일이 아니면 아이콘을 생성한다.
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadedFileName;
	} // End - public static String uploadFile(String uploadPath, String originalName, byte[] fileData)

	//아이콘 생성
	private static String makeIcon(String uploadPath
			, String path, String fileName) throws Exception {
		// 아이콘의 이름
		String iconName = uploadPath + path + File.separator 
				+ fileName;
		// 아이콘 이름을 리턴한다.
		// File.separatorChar : 디렉토리 구분자
		// 윈도우 \ , 유닉스(리눅스) /
		return iconName.substring(uploadPath.length())
				.replace(File.separatorChar, '/');
	} // End - private static String makeIcon(String uploadPath, String path, String fileName)

	//썸네일 생성
	private static String makeThumbnail(String uploadPath
			, String path, String fileName) throws Exception {
		// 이미지를 읽기 위한 버퍼
		BufferedImage sourceImg = ImageIO.read(
				new File(uploadPath + path, fileName));
		
		// 100픽셀 단위의 썸네일 생성
		// Scalr.resize : 원본 이미지보다 축소하기 위해서 사용
		// 높이를 100픽셀로 맞추면 가로 사이즈는 자동으로 맞춰진다.
		BufferedImage destImg = Scalr.resize(
				sourceImg, Scalr.Method.AUTOMATIC
				, Scalr.Mode.FIT_TO_HEIGHT, 100);

		// 썸네일의 이름
		// s_가 붙으면 썸네일, 안붙으면 원본파일
		String thumbnailName = uploadPath + path 
				+ File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(
				fileName.lastIndexOf(".") + 1);
		// 썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		// 썸네일의 이름을 리턴함
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	} // End - private static String makeThumbnail(String uploadPath, String path, String fileName)

	// 경로를 계산하는 메소드
	// DecimalFormat("00") : 10보다 작은 경우 자리수를 맞추기 위해서
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator 
				+ cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator 
				+ new DecimalFormat("00").format(
						cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator 
				+ new DecimalFormat("00").format(cal.get(
						Calendar.DATE));
		
		// 디렉토리 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info(datePath);
		return datePath;
	} // End - private static String calcPath(String uploadPath)
	
	// 가변 사이즈 매개 변수 마침표3개(...)
	// String... 에 uploadPath
	// paths[0] 에 yearPath
	// paths[1] 에 monthPath
	// paths[2] 에 datePath 가 들어간다.
	private static void makeDir(String uploadPath, String... paths) {
		//디렉토리가 존재하면 만들지 않고 통과
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) { // 디렉토리가 존재하지 않으면
				dirPath.mkdir(); // 디렉토리 생성
			}
		}
	}
}