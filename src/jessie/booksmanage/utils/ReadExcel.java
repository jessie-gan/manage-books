package jessie.booksmanage.utils;

import java.io.File;
import java.io.FileInputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {
	public static String getContent(File f) throws Exception {
		FileInputStream fis = new FileInputStream(f);
		StringBuilder str = new StringBuilder();
		jxl.Workbook rwb = Workbook.getWorkbook(fis);
		Sheet[] sheet = rwb.getSheets();
		for (int i = 0; i < sheet.length; i++) {
			Sheet rs = rwb.getSheet(i);
			for (int j = 1; j < rs.getRows(); j++) {
				Cell[] cells = rs.getRow(j);
				for (int k = 0; k < rs.getColumns(); k++) {
					str.append(cells[k].getContents()+"#");
				}
			}
		}
		fis.close();
		return str.toString();
	}
	public static String[] split(String str){
		return str.split("\\#");
	}
	public static void main(String args[]){
		File file  = new File("C:\\Users\\Administrator\\Desktop\\book_example.xls");
		try {
			//getContent(file);
			String str[] = split(getContent(file));
			System.out.println(str[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1");
	}
}
