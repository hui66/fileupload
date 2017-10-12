package cn.Steven;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @ClassName:FileUploadController.java
 * @Description:
 * @author Steven
 * @date:2017年7月28日 上午9:08:44
 */
@Controller
public class FileUploadController {

	@RequestMapping("/file")
	public String file(){
		return "/file";
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()) {
			try {
				String path = "D:\\test\\";		//设置文件的保存路径
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(path + file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
				return "You failed to upload," + e.getMessage();
			}catch(IOException e) {
				e.printStackTrace();
				return "You failed to upload," + e.getMessage();
			}
			return "upload successful";
		}else {
			return "You failed to upload,because the file was empty";
		}
	}
	
	@RequestMapping("/mutifile")
	public String mutifile() {
		return "mutifile";
	}
	
	/**
	 * 多文件上传
	 */
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for(int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if(!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();
				}catch(Exception e) {
					stream = null;
					return "You failed to upload" + i + ":" + e.getMessage();
				}
			}else {
				return "You failed to upload" + i + "because the file was empty";
			}
		}
		return "upload successful";
	}
}
