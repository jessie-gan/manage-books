package jessie.booksmanage.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import jessie.booksmanage.service.BookService;
import jessie.booksmanage.utils.ReadExcel;
import jessie.booksmanage.utils.SendToJson;

@Controller
@Namespace("/admin")
@ParentPackage("jessie.booksmanage")
@Scope("request")
public class AdminMultipleAddBooksAction {


	private String filepath;
	private File[] upload;// 实际上传文件
    private String[] uploadContentType; // 文件的内容类型
    private String[] uploadFileName; // 上传文件名

	String bookNum;
	String bookName;
	String author;
	String bookPress;
	String summary;
	File file;
	@Autowired
	BookService bookService;
	@Action(value = "addBooks", results = { @Result(name = "success", type = "json") })
	public String addBooks() {
		String realpath = ServletActionContext.getServletContext().getRealPath("/upload") ;//获取服务器路径
        String[] targetFileName = uploadFileName;
        for (int i = 0; i < upload.length; i++) {
        	  File target = new File(realpath, targetFileName[i]);
              try {
				FileUtils.copyFile(upload[i], target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
              //这是一个文件复制类copyFile()里面就是IO操作，如果你不用这个类也可以自己写一个IO复制文件的类    
        	} 
//		Map<String,Object> map = new HashMap<String,Object>();
//		file = new File(filepath);
//		try {
//			String temp = ReadExcel.getContent(file);
//			String info[] = ReadExcel.split(temp);
//			for (int i = 0; i < info.length / 5; i++) {
//				int j = i * 5;
//				this.bookNum = info[j];
//				this.bookName = info[j+1];
//				this.author = info[j+2];
//				this.bookPress=info[j+3];
//				this.summary = info[j+4];
//				bookService.Addbook(bookNum, bookName, author, bookPress, summary);
//				map.put("msg", "批量添加图书成功！");
//				SendToJson.send(ServletActionContext.getResponse(), map);
//			}
//			return "success";
//
//		} catch (Exception e) {
//			map.put("msg", "文件读取失败，添加失败！");
//			SendToJson.send(ServletActionContext.getResponse(), map);
//			return "error";
//		}
        return "success";
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
}
