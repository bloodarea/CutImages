package com.messagewindow;

public class test{

	public static void main(String[] args) {
		MessageWindow.getMessageWindow("��ȷ�ϣ���",new Message() {
			
			@Override
			public void Click(int ID) {
				switch(ID) {
				case 1:
					System.out.println("�����ȷ��");
					break;
				case 2:
					System.out.println("�����ȡ��");
					break;
				}
			}
		});
		
	}
}
