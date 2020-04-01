package com.app.manager.utils;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bayes {
	public String predictClass(ArrayList<ArrayList<String >> trainList,ArrayList<String> testList){
		Map<String, ArrayList<ArrayList<String>>> resultMap = dataSet(trainList);
		double mMax = 0.0;
		
		 String finalResult  = "";
		for(int i = 0;i < resultMap.size();i++){
			double mCurrent = 0.0;
			String key = "";
			if(i == 0){
				key = "yes";
			}else{
				key = "no";
			}
			ArrayList<ArrayList<String>> temp = resultMap.get(key);
			mCurrent = culCofV(temp.size(),trainList.size());
			System.out.println("trace first mCurrent:"+ mCurrent);
			for(int j = 0;j < testList.size();j++){
				 
				double pv = culPofV(temp,testList.get(j),j);
				System.out.println("trace index:"+ j + ",pv:"+ pv);
				mCurrent = DecimalCalculate.mul(mCurrent, pv);
				System.out.println("trace index:"+ j + ",mCurrent:"+ mCurrent);
			}
			System.out.println("trace mMax:"+ mMax + " ,mCurrent:"+ mCurrent);
			if(mMax <= mCurrent){
				if(i == 0){
					finalResult = "yes";
					System.out.println("trace yes mMax:"+ mMax + " ,mCurrent:"+ mCurrent);
				}else{
					finalResult = "no";
					System.out.println("trace no mMax:"+ mMax + " ,mCurrent:"+ mCurrent);
				}
				mMax = mCurrent;
			}
		}
		
		return finalResult;
	}
	/**
	 * 计算总概率P(y)
	 * @param ySize
	 * @param nSize
	 * @return
	 */
	public double culCofV(int ySize,int nSize){
		return DecimalCalculate.div(ySize, nSize);
	}
	/**
	 * 分类
	 * @param list
	 * @return
	 */
	public Map<String, ArrayList<ArrayList<String>>> dataSet(List<ArrayList<String>> list){
		Map<String, ArrayList<ArrayList<String>>> culMap = new HashMap<String, ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> mIsList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> mNoList = new ArrayList<ArrayList<String>>();
		for(int i = 0;i < list.size();i++){
			ArrayList<String> temp = new ArrayList<String>();
			temp = list.get(i);
			String mResult = temp.get(temp.size()-1);//获取最后一项
			if(mResult.equals("yes")){
				mIsList.add(temp);
			}else{
				mNoList.add(temp);
			}
		}
		culMap.put("yes", mIsList);
		culMap.put("no", mNoList);
		return culMap;
	}
	/**
	 * 计算总概率
	 */
	public double culPofV(ArrayList<ArrayList<String>> mList,String mStr,int index){
		int count = 0;
		for(int i = 0;i < mList.size();i++){
			if(mStr.equals(mList.get(i).get(index))){
				count++;
			}
		}
		return DecimalCalculate.div(count, mList.size(), 3);
	}

	public static void main(String[] args) {
		ArrayList<String> li = Lists.newArrayList("2","2","2","2","yes");
		ArrayList<String> li2 = Lists.newArrayList("2","2","2","2","yes");
		ArrayList<String> li3 = Lists.newArrayList("1","1","1","1","yes");
		ArrayList<String> li4 = Lists.newArrayList("1","1","1","1","yes");
		ArrayList<String> li5 = Lists.newArrayList("1","2","1","2","yes");
		ArrayList<String> li6 = Lists.newArrayList("1","2","1","1","yes");
		ArrayList<String> li7 = Lists.newArrayList("1","1","1","1","yes");
		ArrayList<ArrayList<String>> trainList = new ArrayList<>();
//		trainList.add(li);
//		trainList.add(li2);
		trainList.add(li3);
		trainList.add(li4);
//		trainList.add(li5);
//		trainList.add(li6);
//		trainList.add(li7);
//		trainList.add(li7);
//		trainList.add(li7);
//		trainList.add(li7);
//		trainList.add(li7);
//		ArrayList<ArrayList<String>> testList = new ArrayList<>();
//		testList.add(li);
		Bayes bayes = new Bayes();
//		for(int i = 0;i < testList.size();i++){
			ArrayList<String> tmp = new ArrayList<String>();
			tmp = li3;
			String label = tmp.get(tmp.size()-1);
			tmp.remove(tmp.size() - 1);
			String finalStr = bayes.predictClass(trainList, tmp);
			System.out.println("trace finalStr:"+ finalStr);
//		}
	}
}
