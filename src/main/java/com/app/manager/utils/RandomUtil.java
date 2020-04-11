package com.app.manager.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * 随机生成中文姓名，性别，Email，手机号，住址
 *
 * @author
 */
public class RandomUtil {


    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    //姓
    private static String firstName = "王李张刘陈杨黄赵吴周徐孙马朱胡郭何高林罗郑梁谢宋唐许韩冯邓曹彭曾肖田董袁潘于蒋蔡余杜叶程苏魏吕丁任沈姚卢姜崔钟谭陆汪范金石廖贾夏韦付方白邹孟熊秦邱江尹薛闫段雷侯龙史陶黎贺顾毛郝龚邵万钱严覃武戴莫孔向汤";
    //女孩名
    private static String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    //男孩名
    private static String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    //路
    private static String[] road = "厦禾路,思明东路,思明西路,思明南路,思明北路,中山路,湖滨东路,湖滨西路,湖滨南路,公园东路,公园西路,公园南路,公园北路,鹭江道,莲前东路,莲前西路,前埔东路,前埔西路,前埔南路,前埔北路,环岛南路,环岛北路,环岛凤屿路,莲花南路,莲花北路,东浦路,市府大道,白鹭洲路,建新路,西堤路,后埭西路,同安路,大学路,小学路,仙岳路,镇海路,新华路,鼓新路,鼓声路,兆和路,龙头路,泉州路,福州路,三明路,禾祥东路,禾祥西路,金榜路,漳州路,港后路,溪岸路,将军祠路,万寿路,角尾路,体育路,屿后路,岩路,晃岩路,环岛高速,嘉禾路,吕岭路,台湾街,斗西路,幸福路,成功大道,东渡路,开禾路,金尚路,云顶南路,云顶北路,会展南路,东坪山路,文园路,文屏路,文曾路,演武路,民族路,西村路,电台山路,气象台路,滨榔路,升平路,湖光路,湖明路,莲岳路,谊爱路".split(",");
    //手机号开头
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    //邮箱
    private static final String[] email_suffix = "@foxmail.com,@qq.com,@163.com,@googlemail.com,@126.com,@sina.com,@sohu.com".split(",");

    /**
     * 返回Email
     *
     * @param lMin 最小长度
     * @param lMax 最大长度
     * @return
     */
    public static String getEmail(int lMin, int lMax) {
        int length = getNum(lMin, lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }

    /**
     * 返回手机号码
     */

    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    /**
     * 生成三字中文名字
     */
    public static String getName() {
        Random random = new Random();
        int index = random.nextInt(firstName.length() - 1);
        String name = firstName.substring(index, index + 1);
        //获得一个随机的姓氏
        //可以根据这个数设置产生的男女比例
        int i = random.nextInt(3);
        if (i == 2) {
            int j = random.nextInt(girl.length() - 2);
            name = "2," + name + girl.substring(j, j + 2);
        } else {
            int j = random.nextInt(girl.length() - 2);
            //三字名
            name = "1," + name + boy.substring(j, j + 2);
        }
        return name;
    }


    /**
     * @param N 随机数范围
     * @return 代码
     */
    public static Integer getIntNum(int N) {
        Random random = new Random();
        int dm = random.nextInt(N) + 1;
        return dm;
    }

    public static Double getDoubleNum(double min, double max, int scl) {
        // 用于提取指定小数位
        int pow = (int) Math.pow(10, scl);
        return Math.floor((Math.random() * (max - min) + min) * pow) / pow;
    }

    /**
     * 返回生日和身份证号
     */
    public static String getIdentityAndBirth(String beginnum, String begindate, String enddate) {
        //String bm = "3502";//开头数字
        //随机数范围
        int N = 9;
        Random random = new Random();
        String bmTmp = beginnum;
        //身份证开头6位
        for (int j = 0; j < 6 - beginnum.length(); j++) {
            bmTmp = bmTmp + random.nextInt(N);
        }
        Date date = randomDate(begindate, enddate);
        String sr = FormatTime(date, "yyyyMMdd");
        String sr1 = FormatTime(date, "yyyy-MM-dd");
        bmTmp = bmTmp + sr;
        //身份证后4位
        for (int j = 0; j < 4; j++) {
            bmTmp = bmTmp + random.nextInt(N);
        }
        String sfz = sr1 + "," + bmTmp;
        return sfz;
    }

    /**
     * 返回地址
     *
     * @return
     */
    public static String getRoad() {
        int index = getNum(0, road.length - 1);
        String qu = "海淀区";
        String ss = "北京市";
        String first = road[index];
        String second = String.valueOf(getNum(11, 150)) + "号";
        //String third="-"+getNum(1,20)+"-"+getNum(1,10);
        String third = getNum(1, 9) + "" + getNum(10, 20) + "室";
        return ss + qu + first + second + third;

    }

    /**
     * 格式化时间
     *
     * @param date
     * @param format
     * @return
     */
    public static String FormatTime(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 生成指定日期范围的随机时间
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = getLongNum(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成指定范围随机数
     *
     * @param begin
     * @param end
     * @return long类型随机数
     */
    public static long getLongNum(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return getLongNum(begin, end);
        }
        return rtn;
    }

    /**
     * 产生服从正态分布的随机数
     * @param standardDeviation 标准差σ
     * @param mathematicalExpectation  数学期望 μ
     * @return
     */
    public static Double getDistributed(int standardDeviation, Double mathematicalExpectation, int scale){
        Random r = new Random();
        double randomNum = r.nextGaussian() * Math.sqrt(standardDeviation) + mathematicalExpectation;
        while (randomNum < 0){
            randomNum = r.nextGaussian() * Math.sqrt(standardDeviation) + mathematicalExpectation;
        }
        BigDecimal bg = new BigDecimal(randomNum);
        randomNum = bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return randomNum;
    }

    /**
     * 生成指定范围随机数
     *
     * @param start
     * @param end
     * @return int类型随机数
     */
    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static void main(String[] args) {


        Random r = new Random();
        for (int i = 0; i < 1; i++) {
            Double gau = r.nextGaussian() * Math.sqrt(5) + 10;
            System.out.println(gau);
        }

//        for (int i = 0; i < 10; i++) {
//            String[] xmTmp = getName().split(",");
//            String xm = xmTmp[1];//姓名
//            String xb = xmTmp[0];//性别 1男、2女
//            String[] sfzTmp = getIdentityAndBirth("4806", "1940-1-1", "2018-12-31").split(",");
//            String birth = sfzTmp[0]; //出生日期
//            String identity = sfzTmp[1]; //身份证号
//            String tel = getTel(); //手机号
//            String email = getEmail(6, 18); //邮箱
//            Double doubleNum = getDoubleNum(8, 10, 2);
//            Integer educationalLevel = getIntNum(4); //文化程度 01-4
//
//            System.out.println(xm);
//            System.out.println(xb);
//            System.out.println(birth);
//            System.out.println(tel);
//            System.out.println(identity);
//            System.out.println(doubleNum);
//        }
    }



}
