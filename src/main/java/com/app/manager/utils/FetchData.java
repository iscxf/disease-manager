//package com.app.manager.utils;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class FetchData {
//
//	//�������ݿ⣬��ȡѵ������
//	//���룺���ݿ�
//	//������ɱ䳤����
//	public ArrayList<ArrayList<String>> fetch_traindata(){
//		ArrayList<ArrayList<String>> dataSet = new ArrayList<ArrayList<String>>();  //������
//
//		Connection conn;    //���ض����ݿ�����ӣ��Ự���ı���
//		String driver = "com.mysql.jdbc.Driver";  //���������������������˽⣩
//		String url = "jdbc:mysql://localhost:3306/bayes";  //ָ��Ҫ���ʵ����ݿ⣡ע�������������ݿ�����
//		String user = "root";   //navicat for sql���õ��û���
//		String password = "123456";  //navicat for sql���õ�����
//		try{
//			Class.forName(driver);  //��class���ض�̬���ӿ⡪����������
//			conn = DriverManager.getConnection(url,user,password);  //������Ϣ�������ݿ�
//			if(!conn.isClosed())
//				System.out.println("Succeeded connecting to the Database!");
//
//			Statement statement = conn.createStatement();  //��statement ��ִ��sql���
//			String sql = "select * from traindata";   //����sql����еĲ�ѯĳ����ע������emp�Ǳ���������
//			ResultSet rs = statement.executeQuery(sql);  //���ڷ��ؽ��
//
//			String str = null;
//			while(rs.next()){  //һֱ�������һ����
//				ArrayList<String> s= new ArrayList<String>();
//				str = rs.getString("Sex");  //�ֱ��ȡ��Ӧ��λ����Ϣ���뵽�ɱ䳤������
//				s.add(str);
//				str = rs.getString("gaoxuezhi");
//				s.add(str);
//				str = rs.getString("gaoxueya");
//				s.add(str);
//				str = rs.getString("guohoupizhi");
//				s.add(str);
//				str = rs.getString("jiazuyichuan");
//				s.add(str);
//				str = rs.getString("isdiabetes");
//				s.add(str);
//				dataSet.add(s);  //����dataSet
//				//System.out.println(s);  ����м�������
//			}
//			rs.close();
//			conn.close();
//		}catch(ClassNotFoundException e){  //catch��ͬ�Ĵ�����Ϣ��������
//			System.out.println("Sorry,can`t find the Driver!");
//			e.printStackTrace();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			System.out.println("���ݿ�ѵ�����ݶ�ȡ�ɹ���");
//		}
//		return dataSet;
//	}
//
//
//	public ArrayList<String> read_testdata(String str) throws IOException  //���û������һ�����ַ����ָ�����ɿɱ䳤����
//	{
//		ArrayList<String> testdata=new ArrayList<String>();  //������
//		StringTokenizer tokenizer = new StringTokenizer(str);  //���ǽ��ѧϰ��StringTokenizer���ͣ��������˽⣩
//		while (tokenizer.hasMoreTokens()) {
//			testdata.add(tokenizer.nextToken());
//		}
//		return testdata;
//	}
//}
