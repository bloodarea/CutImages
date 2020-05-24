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
	//��ȡ����·��
	public static String getRunPath() {
		return System.getProperty("user.dir");
	}
	
	//��ȡ·�����ļ��б�
    public static LinkedList<String> getFileList(String path) {
        File file = new File(path);
        LinkedList<String> ll = new LinkedList<String>();
        
        File[] fileList = file.listFiles();
        
        for(File f:fileList) {
        	if(f.isFile()) {
                String fileName = f.getName();
                if(!fileName.endsWith(".jpg") //	�ж�ͼƬ��ʽ
                		&& !fileName.endsWith(".png") 
                		&& !fileName.endsWith(".bmp") 
                		&& !fileName.endsWith(".tga")) {
                	break;
                }
                ll.add(fileName);  //����·���� path+"\\"+fileName
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
    
    //���ļ���Ŀ¼
    public static void openFile(String path) throws IOException {
    	Desktop.getDesktop().open(new File(path));
    }
    
    //��ʱ������һ���ַ��� 2020429_156295
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
	
	//д��ͼƬ
	public static void saveImg(BufferedImage img,String extent,String path) throws IOException {
		ImageIO.write(img, extent, new File(path));
	}
	
	//��ȡͼƬ
	public static BufferedImage getImage(String imagePath) throws IOException {
		return ImageIO.read(new File(imagePath));
	}
	
	//�����ļ���
	public static boolean createDir(String dirName) {
		File Path = new File(getRunPath() + "\\" + dirName);
    	if(!Path.exists()) {
    		Path.mkdir();
    		return true;
    	}
    	return false;
	}
	//����NλС��
	public static double ToNBit(double num,int bit) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(bit);
		return Double.parseDouble(nf.format(num));
	}
}
