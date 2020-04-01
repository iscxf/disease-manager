package com.app.manager.utils;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BayesUtil {

	private static Map<String,List<List<String>>> classify(List<List<String>> dataSet){
		Map<String,List<List<String>>> map = new HashMap<>(16);
		int num=dataSet.size();
		for(int i=0;i<num;i++)
		{
			List<String> Y = dataSet.get(i);
			String type = Y.get(Y.size()-1);

			if(map.containsKey(type)){
				map.get(type).add(Y);
			}else{
				List<List<String>> nlist = new ArrayList<>();
				nlist.add(Y);
				map.put(type,nlist);
			}
		}
		return map;
	}


	private static double CalPro_yj_c(List<List<String>> classdata, String value, int index){
		int sum = 0;
		int num = classdata.size();
		for(int i=0;i<num;i++)
		{
			List<String> Y = classdata.get(i);
			if(Y.get(index).equals(value)){
				sum++;
			}
		}
		return (double)sum/num;

	}


	public static Map<String, Object> calculationProbability(List<List<String>> dataSet, List<String> testSet){
		Map<String, List<List<String>>> doc = classify(dataSet);

		Object classes[] = doc.keySet().toArray();
		double maxValue=0.0;
		int maxClass=-1;
		Map<String, Object> result = new HashMap<>(16);
		for(int i=0; i<doc.size(); i++)
		{
			String c = classes[i].toString();
			List<List<String>> y = doc.get(c);
			double prob = (double)y.size()/dataSet.size();

			System.out.println(c+" : "+prob);
			result.put(c, prob);

			for(int j=0;j<testSet.size();j++)
			{
				double P_yj_c = CalPro_yj_c(y,testSet.get(j),j);

				prob = prob*P_yj_c;
			}

			System.out.printf("P(%s | testcase) * P(testcase) = %f\n",c,prob);

			if(prob>=maxValue)
			{
				maxValue=prob;
				maxClass=i;
			}
		}
		if (maxClass == -1){
			maxClass = 0;
		}
		result.put("result", classes[maxClass]);
		return result;
//		return classes[maxClass].toString();
	}
}