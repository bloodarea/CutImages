package com.cutimages;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Functions {
	//获取启动路径
	public static String getRunPath() {
		return System.getProperty("user.dir");
	}
	
	//获取路径的文件列表
    public static LinkedList<String> getFileList(String path) {
        File file = new File(path);
        LinkedList<String> ll = new LinkedList<String>();
        
        File[] fileList = file.listFiles();
        
        for(File f:fileList) {
        	if(f.isFile()) {
                String fileName = f.getName();
                if(!fileName.endsWith(".jpg") //	判断图片格式
                		&& !fileName.endsWith(".png") 
                		&& !fileName.endsWith(".bmp") 
                		&& !fileName.endsWith(".tga")) {
                	break;
                }
                ll.add(fileName);  //完整路径： path+"\\"+fileName
        	}
        }
//        for (int i = 0; i < fileList.length; i++) {
//            if (fileList[i].isFile()) {
//                String fileName = fileList[i].getName();
//                ll.add(path+"\\"+fileName);            
//            }
//        }
        return ll;
    }
    
    //打开文件夹目录
    public static void openFile(String path) throws IOException {
    	Desktop.getDesktop().open(new File(path));
    }
    
    //按时间生成一个字符串 2020429_156295
	public static String getDateName() {
		String result = null;
		Calendar c = Calendar.getInstance();
		String SystemTime = System.currentTimeMillis() + "";
		result = c.get(Calendar.YEAR) + "";
		result += c.get(Calendar.MONTH) + 1;
		result += c.get(Calendar.DAY_OF_MONTH);
		result += "_";
		result += SystemTime.substring(SystemTime.length() - 8,SystemTime.length() - 2);
		return result;
	}
	
	//写入图片
	public static void saveImg(BufferedImage img,String extent,String path) throws IOException {
		ImageIO.write(img, extent, new File(path));
	}
	
	//读取图片
	public static BufferedImage getImage(String imagePath) throws IOException {
		return ImageIO.read(new File(imagePath));
	}
	
	//生成文件夹
	public static boolean createDir(String dirName) {
		File Path = new File(getRunPath() + "\\" + dirName);
    	if(!Path.exists()) {
    		Path.mkdir();
    		return true;
    	}
    	return false;
	}
	//保留N位小数
	public static double ToNBit(double num,int bit) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(bit);
		return Double.parseDouble(nf.format(num));
	}
}
