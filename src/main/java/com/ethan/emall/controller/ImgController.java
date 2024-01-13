package com.ethan.emall.controller;

import java.io.File;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/img")
public class ImgController {

	@PostMapping("/upload")
	public String uploadImg(@RequestParam("file") MultipartFile file) {

		String fileName = file.getOriginalFilename();

		String filePath = "/Users/linyusheng/Desktop/Img/";

		try {
			file.transferTo(new File(filePath+fileName));
			return "成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "失敗";
		}
	}

}
