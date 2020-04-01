//package com.app.manager.utils;
//
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Main {
//
//	//����������ȡ���ݿ⣬��������ж����ݣ�������
//	public static void main(String[] args) {
//		FetchData Fdata = new FetchData();   //java�Ժ����ĵ���Ҫ��������Ӧ�Ķ����ٵ���
//		Bayes bys = new Bayes();
//        ArrayList<ArrayList<String>> dataSet = null; //ѵ�������б�
//        ArrayList<String> testSet = null; //��������
//        try{
//        	System.out.println("�����ݿ����ѵ�����ݣ�");
//        	dataSet = Fdata.fetch_traindata();   //��ȡѵ�����ݼ���
//        	System.out.println("������������ݣ�");
//        	Scanner cin = new Scanner(new BufferedInputStream(System.in));  //�ӱ�׼��������ж�ȡ��������
//    		while(cin.hasNext())  //֧�ֶ����������ݶ�ȡ
//    		{
//    			String str = cin.nextLine();   //�ȶ���һ��
//    			testSet = Fdata.read_testdata(str);//����һ�н����ַ����ָ������󷵻ؿɱ䳤��������
//    			//System.out.println(testSet);  //����м���
//    			String ans = bys.bys_Main(dataSet, testSet);  //���ñ�Ҷ˹������
//            	if(ans.equals("yes")) System.out.println("Yes!!! He's likely to be a diabetes!");  //������
//            	else System.out.println("No!!! It is NOT likely to be a diabetes!!!");
//    		}
//        	cin.close();
//        }catch (IOException e) {  //�����쳣
//            e.printStackTrace();
//        }
//	}
//
//}