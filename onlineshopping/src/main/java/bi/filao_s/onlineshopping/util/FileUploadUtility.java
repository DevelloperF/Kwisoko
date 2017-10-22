package bi.filao_s.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	// ABS for Absolute
	private static final String ABS_PATH = "D:\\Kwisoko\\Kwisoko\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		// Get the real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images");
		logger.info(REAL_PATH);

		// To make sure all the directory exists
		// Please create the directories
		if (!new File(ABS_PATH).exists()) {
			// Create the directories
			new File(ABS_PATH).mkdirs();

		}
		if (!new File(REAL_PATH).exists()) {
			// Create the directories
			new File(REAL_PATH).mkdirs();
		}
		try {
			// Server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			// Project directory upload
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
		} catch (IOException e) {
		}

	}
}
