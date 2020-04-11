package com.app.manager.utils;
 
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

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

    /**
     * 提供（相对）精确的除法运算当发生除不尽的情况时
     * 定精度，以后的数字四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示精确到小数点以后几位
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private static double calTotalProb(List<List<String>> classData, String value, int index){
		int sum = 0;
		int num = classData.size();
		for(int i=0;i<num;i++)
		{
			List<String> data = classData.get(i);
			if(data.get(index).equals(value)){
				sum++;
			}
		}
		return (double)sum/num;
	}


	public static Map<String, Object> calculationProbability(List<List<String>> dataSet, List<String> testSet){
		Map<String, Object> result = new HashMap<>(16);
    	if (CollectionUtils.isEmpty(dataSet) || CollectionUtils.isEmpty(testSet)){
			result.put("yes", 0);
			result.put("no", 0);
			result.put("result", "unknow");
			return result;
		}
		Map<String, List<List<String>>> doc = classify(dataSet);
		List<String> keyList = new ArrayList<>(doc.keySet());
		double maxValue=0.0;
		for(int i=0; i<doc.size(); i++)
		{
			String c = keyList.get(i);
			List<List<String>> y = doc.get(c);
			double prob = (double)y.size()/dataSet.size();
			System.out.println(c+" 占总量的比例: "+prob);
			double loopProb = 0.0;
			for(int j=0;j<testSet.size();j++)
			{
				double colProb = calTotalProb(y,testSet.get(j),j);
				if (0 == colProb) {
					prob = prob * colProb;
				}
				loopProb += prob;
			}
			loopProb = div(loopProb, testSet.size(), 3);
			System.out.printf("P(%s | testcase) * P(testcase) = %f ,loopProb = %f\n",c,prob, loopProb);
			result.put(c, loopProb);
			if(prob>=maxValue)
			{
				maxValue=prob;
			}
		}
		String resultKey = "";
		double resultProb=0.0;
		for (Map.Entry<String, Object> map : result.entrySet()){
			if ((Double)map.getValue() >= resultProb){
				resultProb = (Double)map.getValue();
				resultKey = map.getKey();
			}
		}
		if (null == result.get("yes")){
			result.put("yes", 0);
		}
		if(null == result.get("no")){
			result.put("no", 0);
		}
		result.put("result", resultKey);
		return result;
	}
}