package com.cutimages;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import com.messagewindow.MessageWindow;

import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Label;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Panel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

import java.awt.List;
import java.awt.ScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	private int RangeValue = 1;	//������λ��ֵ
	
	private boolean bWorB = false;	//�ü�����ɫ
	
	private Pending p;	//Ԥ��ͼ�����
	
	private int[] cXY = new int[4];	//X,Y,��,��
	
	private LinkedList<String> FileList = new LinkedList<String>();	//�ļ��б�
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					frame.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JList ListFileShow;
	private JLabel labelSource;
	private JLabel labelResult;
	private JLabel labelCut;
	private JTextArea textareaMes;
	private JSlider sliderDegree;
	
	public MainWindow() {
		setFocusable(true);
		setTitle("\u88C1\u526A\u56FE\u7247 CutImage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1090, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				int x = labelCut.getX();
				int y = labelCut.getY();
				int h = labelCut.getHeight();
				int w = labelCut.getWidth();
				switch (e.getKeyCode()) {
				case 37:	//�����ƶ�
					if(x > labelSource.getX()) {
						labelCut.setBounds(x-RangeValue,y,w,h);
						if(labelCut.getX() < labelSource.getX()) {
							labelCut.setBounds(labelSource.getX(), y, w, h);
						}
					}
					break;
				case 38:	//�����ƶ�
					if(y > labelSource.getY()) {
						labelCut.setBounds(x,y-RangeValue,w,h);
						if(labelCut.getY() < labelSource.getY()) {
							labelCut.setBounds(x, labelSource.getY(), w, h);
						}
					}
					break;
				case 39:	//�����ƶ�
					if(x + labelCut.getWidth() < labelSource.getX() + labelSource.getWidth()) {
						labelCut.setBounds(x+RangeValue,y,w,h);
						if(labelCut.getX() + labelCut.getWidth() > labelSource.getX() + labelSource.getWidth()) {
							labelCut.setBounds(labelSource.getX() + labelSource.getWidth() - labelCut.getWidth(), y, w, h);
						}
					}
					break;
				case 40:	//�����ƶ�
					if(y + labelCut.getHeight() < labelSource.getY() + labelSource.getHeight()) {
						labelCut.setBounds(x,y+RangeValue,w,h);
						if(labelCut.getY() + labelCut.getHeight() > labelSource.getY() + labelSource.getHeight()) {
							labelCut.setBounds(x, labelSource.getY() + labelSource.getHeight() - labelCut.getHeight(), w, h);
						}
					}
					break;
				case 83:	//���Ӹ߶�
					if(y + labelCut.getHeight() < labelSource.getY() + labelSource.getHeight()) {
						labelCut.setBounds(x, y, w, h+RangeValue);
					}
					break;
				case 87:	//���ٸ߶�
					if(labelCut.getHeight() > 10) {
						labelCut.setBounds(x, y, w, h-RangeValue);
					}
					break;
				case 68:	//���ӿ��
					if(x + labelCut.getWidth() < labelSource.getX() + labelSource.getWidth()) {
						labelCut.setBounds(x, y, w+RangeValue, h);
					}
					break;
				case 65:	//���ٿ��
					if(labelCut.getWidth() > 10) {
						labelCut.setBounds(x, y, w-RangeValue, h);
					}
					break;
				case 81:	//���òü�����ɫ
					if(bWorB) {
						bWorB = false;
						labelCut.setBorder(new LineBorder(Color.black));
						labelCut.setForeground(Color.black);
					}else {
						bWorB = true;
						labelCut.setBorder(new LineBorder(Color.white));
						labelCut.setForeground(Color.white);
					}
					break;
				case 90:	//�ƶ���-1
					sliderDegree.setValue(sliderDegree.getValue()-1);
					break;
				case 88:	//�ƶ���+1
					sliderDegree.setValue(sliderDegree.getValue()+1);
					break;
				case 67:	//���ٲü�Img
					cutImage();
					break;
				case 86:
					saveImage();
					break;
				case 82:	//�б�ѡ�������ϵ�1��
					if(ListFileShow.getSelectedIndex() > 0) {
						ListFileShow.setSelectedIndex(ListFileShow.getSelectedIndex() - 1);
					}
					break;
				case 70:	//�б�ѡ�������µ�1��
					ListFileShow.setSelectedIndex(ListFileShow.getSelectedIndex() + 1);
					break;
				}
			}
		});
		
		sliderDegree = new JSlider();
		sliderDegree.setFocusable(false);
		sliderDegree.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				RangeValue = sliderDegree.getValue();
			}
		});
		sliderDegree.setMaximum(20);
		sliderDegree.setMinimum(1);
		sliderDegree.setValue(10);
		sliderDegree.setBounds(647, 373, 170, 40);
		contentPane.add(sliderDegree);
		
		labelCut = new JLabel("Cut Area");
		labelCut.setForeground(Color.BLACK);
		labelCut.setOpaque(false);
		labelCut.setHorizontalAlignment(SwingConstants.CENTER);
		labelCut.setBounds(225, 277, 64, 64);
		labelCut.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(labelCut);
		
		labelSource = new JLabel("Not Found Source");
		labelSource.setBounds(10, 10, 550, 400);
		labelSource.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(labelSource);
		labelSource.setHorizontalAlignment(SwingConstants.CENTER);
		labelSource.setFont(new Font("����", Font.PLAIN, 40));
		
		labelResult = new JLabel("Not Result");
		labelResult.setHorizontalAlignment(SwingConstants.CENTER);
		labelResult.setFont(new Font("����", Font.PLAIN, 40));
		labelResult.setBounds(567, 10, 250, 350);
		labelResult.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(labelResult);
		
		JButton buttonRead = new JButton("\u8BFB\u53D6");
		buttonRead.setFocusable(false);
		buttonRead.setFont(new Font("����", Font.PLAIN, 15));
		buttonRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileList = Functions.getFileList(Functions.getRunPath()+"\\source");
				Object[] FL = FileList.toArray();
				ListFileShow.setListData(FL);
				if(!FileList.isEmpty()) {
					textareaMes.setText("��ȡ�ɹ�������ȡ����"+FileList.size()+"��ͼƬ�ļ�");
				}else {
					textareaMes.setText("��ȡʧ�ܣ��뽫ͼƬ�ļ�����source�ļ�����");
				}
			}
		});
		buttonRead.setBounds(986, 10, 78, 26);
		contentPane.add(buttonRead);
		
		JButton buttonDirectory = new JButton("\u76EE\u5F55");
		buttonDirectory.setFocusable(false);
		buttonDirectory.setFont(new Font("����", Font.PLAIN, 15));
		buttonDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Functions.openFile(Functions.getRunPath());
				} catch (IOException e1) {
					MessageWindow.getMessageWindow("�ļ�Ŀ¼�����ڣ�", null);
				}
			}
		});
		buttonDirectory.setBounds(986, 46, 78, 26);
		contentPane.add(buttonDirectory);
		
		JButton buttonExplain = new JButton("\u8BF4\u660E");
		buttonExplain.setFocusable(false);
		buttonExplain.setFont(new Font("����", Font.PLAIN, 15));
		buttonExplain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageWindow.getMessageWindow("�ƶ�:��������\n��С��WASD �ÿ�ɫ��Q", null);
			}
		});
		buttonExplain.setBounds(986, 82, 78, 26);
		contentPane.add(buttonExplain);
		
		JButton buttonCut = new JButton("\u88C1\u526AC");
		buttonCut.setFocusable(false);
		buttonCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutImage();
			}
		});
		buttonCut.setFont(new Font("����", Font.PLAIN, 15));
		buttonCut.setBounds(986, 118, 78, 26);
		contentPane.add(buttonCut);
		
		ListFileShow = new JList();
		ListFileShow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListFileShow.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(ListFileShow.getSelectedIndex() != -1) {
					readImage();
				}
			}
			
		});
		ListFileShow.setFocusable(false);
		ListFileShow.setBounds(823, 14, 153, 346);
		
		ScrollPane scrollPaneFileList = new ScrollPane();
		scrollPaneFileList.setFocusable(false);
		scrollPaneFileList.setBounds(823, 10, 157, 350);
		scrollPaneFileList.add(ListFileShow);
		contentPane.add(scrollPaneFileList);
		
		JLabel lblNewLabel = new JLabel("\u79FB\u52A8\u91CF\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(570, 370, 86, 40);
		contentPane.add(lblNewLabel);
		
		textareaMes = new JTextArea();
		textareaMes.setFocusable(false);
		textareaMes.setEditable(false);
		textareaMes.setLineWrap(true);
		textareaMes.setRows(3);
		contentPane.add(textareaMes);
		textareaMes.setFont(new Font("����", Font.PLAIN, 16));
		textareaMes.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textareaMes.setBounds(823, 373, 241, 40);
		
		JButton buttonSave = new JButton("\u4FDD\u5B58V");
		buttonSave.setFocusable(false);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveImage();
			}
		});
		buttonSave.setFont(new Font("����", Font.PLAIN, 15));
		buttonSave.setFocusable(false);
		buttonSave.setBounds(986, 154, 78, 26);
		contentPane.add(buttonSave);
	}
	
	private void initialize() {
		Functions.createDir("source");
		Functions.createDir("output");
		labelCut.setBounds(labelCut.getX(), labelSource.getY(), labelCut.getWidth(), labelSource.getHeight());
	}
	
	private void readImage() {
		labelSource.setText(null);
		String FilePath = Functions.getRunPath() + "\\source\\" + FileList.get(ListFileShow.getSelectedIndex());
		try {
			p = new Pending(Functions.getImage(FilePath));
			textareaMes.setText("Դ�ļ���ȡ�ɹ���\n��С����" + p.getSource().getWidth() +" �ߣ�" + p.getSource().getHeight());
		} catch (IOException e1) {
			MessageWindow.getMessageWindow("ͼƬ��ȡʧ�ܣ�", null);
		}
		labelSource.setIcon(new ImageIcon(p.getPreview()));
	}
	
	private void saveImage() {
		if(p == null) {
			MessageWindow.getMessageWindow("����ʧ�ܣ�δ��ȡԴ�ļ�", null);
			return;
		}else if(p.getResult() == null) {
			MessageWindow.getMessageWindow("����ʧ�ܣ�δ�ü�ͼƬ", null);
			return;
		}
		try {
			Functions.saveImg(p.getResult(), "jpg", Functions.getRunPath()+"\\output\\IMG_"+Functions.getDateName()+".jpg");
			ListFileShow.setSelectedIndex(ListFileShow.getSelectedIndex() + 1);
		} catch (IOException e) {
			MessageWindow.getMessageWindow("����ʧ�ܣ�", null);
		}
	}
	
	private void getcXY() {
		cXY[0] = labelCut.getX()-labelSource.getX();
		cXY[1] = labelCut.getY()-labelSource.getY();
		cXY[2] = labelCut.getWidth();
		cXY[3] = labelCut.getHeight();
	}
	
	private void cutImage() {
		if(p == null) {
			MessageWindow.getMessageWindow("�ü�ʧ�ܣ�δ��ȡԴ�ļ�", null);
			return;
		}
		labelResult.setText(null);
		getcXY();
		int[] XorY = p.getOxy(cXY[0], cXY[1]);
		int[] WorH = p.getOxy(cXY[2], cXY[3]);
		p.setResult(p.getSource().getSubimage(XorY[0], XorY[1], WorH[0], WorH[1]));
		textareaMes.setText("�ü��ɹ���\n��С����" + WorH[0] +" �ߣ�" + WorH[1]);
		p.setPreview(p.getResult().getScaledInstance(250, 300, Image.SCALE_FAST));
		labelResult.setIcon(new ImageIcon(p.getPreview()));
	}
}
