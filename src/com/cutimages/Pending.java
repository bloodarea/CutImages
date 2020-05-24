package com.cutimages;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Pending {
	private BufferedImage Source;	//源文件
	private Image Preview;	//预览图
	private BufferedImage Result;	//结果图
	private double RatioX;
	private double RatioY;
	private final int PreviewW = 550;
	private final int PreviewH = 400;
	
	public void getRatio() {	//获取比例
		RatioX = (double)PreviewW/(double)Source.getWidth();
		RatioY = (double)PreviewH/(double)Source.getHeight();
	}
	
	public int[] getOxy(int x,int y) {	//按比例将预览图坐标还原成真实坐标
		int oX = (int)(x / RatioX);
		int oY = (int)(y / RatioY);
		int[] arr = {oX,oY};
		return arr;
	}
	
	public Pending() {
	}

	public Pending(BufferedImage source) {
		setSource(source);
	}

	public BufferedImage getSource() {
		return Source;
	}

	public void setSource(BufferedImage source) {
		Source = source;
		setPreview(source.getScaledInstance(PreviewW, PreviewH, Image.SCALE_FAST));
		getRatio();
	}

	public Image getPreview() {
		return Preview;
	}

	public void setPreview(Image preview) {
		Preview = preview;
	}
	
	public BufferedImage getResult() {
		return Result;
	}

	public void setResult(BufferedImage result) {
		Result = result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			Pending Pe = (Pending)obj;
			return this.Source == Pe.Source;
		}
		return false;
	}

	public double getRatioX() {
		return RatioX;
	}

	public void setRatioX(double ratioX) {
		RatioX = ratioX;
	}

	public double getRatioY() {
		return RatioY;
	}

	public void setRatioY(double ratioY) {
		RatioY = ratioY;
	}
	
	
}
