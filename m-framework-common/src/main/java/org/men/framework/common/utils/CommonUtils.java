package org.men.framework.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 字符串处理工具类
 * @author wuwentan
 * @date 2017/5/26
 */
public class CommonUtils {

    /**
     * 获取UUID
     * @return
     */
    public static String uuid(){
        String s = UUID.randomUUID().toString();
        return s.replace("-","");
    }

    /**
     * 日期转换成字符串
     * @param date
     * @param formatStr
     * @return
     */
    public static String DateToString(Date date, String formatStr){
        DateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 日期转换成yyyy-MM-dd HH:mm:ss字符串
     * @param date
     * @return
     */
    public static String getStringDate(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 字符串转换成日期
     * @param date
     * @param formatStr
     * @return
     */
    public static Date StringToDate(String date, String formatStr) {
        DateFormat df = new SimpleDateFormat(formatStr);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            //日期格式转换错误
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 时间转换为时/分/秒
     * @param longTime
     * @return
     */
    public static String TimeToString(long longTime){
        StringBuilder time = new StringBuilder();
        long hours = longTime / 3600;
        long minutes = (longTime % 3600) / 60;
        long seconds = longTime % 60;
        if (hours > 0)
            time.append(hours).append("小时");
        if (minutes > 0)
            time.append(minutes).append("分");
        if (seconds > 0)
            time.append(seconds).append("秒");
        if (hours == 0 && minutes == 0 && seconds == 0)
            time.append("0秒");
        return time.toString();
    }

    /**
     * 字符串日期加减
     * @param dateStr   操作日期
     * @param count     加的数量，负数表示减去
     * @param field     单位：Calendar.DATE, Calendar.MONTH, Calendar.YEAR
     *                  1.年 2.月 3.周 5.日 11.小时 12.分钟 13.秒 14.毫秒
     * @return          相加后的日期字符串
     */
    public static String DateAdd(String dateStr, int count, int field){
        Calendar cd = Calendar.getInstance();
        if(StringUtils.isNotBlank(dateStr)){
            cd.setTime(StringToDate(dateStr, "yyyy-MM-dd"));
            cd.add(field, count);
            dateStr = DateToString(cd.getTime(), "yyyy-MM-dd");
        }
        return dateStr;
    }

    /**
     * 日期加减返回指定日期格式
     * @param date      操作日期
     * @param formatStr 日期格式
     * @param count     加的数量，负数表示减去
     * @param field     单位：Calendar.DATE, Calendar.MONTH, Calendar.YEAR
     *                  1.年 2.月 3.周 5.日 11.小时 12.分钟 13.秒 14.毫秒
     * @return          相加后的日期字符串
     */
    public static String DateAdd(Date date, String formatStr, int count, int field){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(field, count);
        return DateToString(cd.getTime(), formatStr);
    }

    /**
     * 日期相加
     * @param date      操作日期
     * @param count     加的数量，负数表示减去
     * @param field     单位：Calendar.DATE, Calendar.MONTH, Calendar.YEAR
     * @return          相加后的日期
     */
    public static Date DateAdd(Date date, int count, int field){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, count);
        return calendar.getTime();
    }

    /**
     * 日期相减获取时间差(毫秒数)
     * @param startTime
     * @param endTime
     * @param formatStr
     * @return
     */
    public static long DateDiff(String startTime,String endTime,String formatStr){
        long timeDiff = 0;
        if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
            long start_time = StringToDate(startTime,formatStr).getTime();
            long end_time = StringToDate(endTime,formatStr).getTime();
            timeDiff = Math.abs(start_time - end_time);
        }
        return timeDiff;
    }

    /**
     * 获取今天之前的days天日期列表
     * @param days
     * @return
     */
    public static List<String> getDateList(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List dateList = new ArrayList();
        for(int i = 0;i<days;i++){
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 0-(i+1));
            String dateStr = DateToString(calendar.getTime(),"yyyy-MM-dd");
            dateList.add(dateStr);
        }
        Collections.reverse(dateList);
        return dateList;
    }

    /**
     * 获取今天之前的days天日期列表
     * @param days
     * @return
     */
    public static String[] getDateArray(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String[] dateArray = new String[days];
        for(int i=days;i>0;i--){
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 0-i);
            String dateStr = DateToString(calendar.getTime(),"yyyy-MM-dd");
            dateArray[days-i] = dateStr;
        }
        return dateArray;
    }

    /**
     * 字符串日期人性化显示
     * String formatStr
     * @param date
     * @param formatStr
     * @return
     */
    public static String friendlyTime(String date, String formatStr){
        Date nowdate = StringToDate(DateToString(new Date(),formatStr),formatStr);
        Date ondate = StringToDate(date,formatStr);
        Long diffTime = (nowdate.getTime() - ondate.getTime()) / (86400000);
        String dateStr = "";
        switch (diffTime.intValue()){
            case 0:
                dateStr = "今天";
                break;
            case 1:
                dateStr = "昨天";
                break;
            case 2:
                dateStr = "两天前";
                break;
            case 3:
                dateStr = "三天前";
                break;
            case 4:
                dateStr = "四天前";
                break;
            case 5:
                dateStr = "五天前";
                break;
            case 6:
                dateStr = "六天前";
                break;
            case 7:
                dateStr = "一周前";
                break;
            default:
                DateFormat df = new SimpleDateFormat(formatStr);
                dateStr = df.format(date);
        }
        return dateStr;
    }

    /**
     * 获取日期所在的周
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static String weekOfMonth(String dateStr, String formatStr){
        Date date = StringToDate(dateStr,formatStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        return String.valueOf(weekOfMonth);
    }

    /**
     * 将字符集合按“,”分割的字符串输出
     * @param strList
     * @return
     */
    public static String ListToString(List<String> strList){
        StringBuilder str = new StringBuilder("");
        if(strList != null){
            for(int i = 0;i < strList.size();i++){
                str.append(strList.get(i)+",");
            }
        }
        String str1 = str.length() > 0 ? str.substring(0,str.length()-1) : "";
        return str1;
    }

    /**
     * 带分隔符的字符串转换成list
     * @param str
     * @param split
     * @return
     */
    public static List<String> StringToList(String str,String split){
        if(split.equals(".")){
            split = "\\.";
        }
        if(split.equals("|")){
            split = "\\|";
        }
        List<String> stringList = new ArrayList();
        String[] stringArray = str.split(split);
        if(stringArray.length > 0){
            stringList = Arrays.asList(stringArray);
        }
        return stringList;
    }

    /**
     * 将字符串数组按“,”分割的字符串输出
     * @param strArray
     * @return
     */
    public static String ArrayToString(String[] strArray){
        StringBuilder str = new StringBuilder("");
        for(int i = 0;i < strArray.length;i++){
            str.append(strArray[i]+",");
        }
        return str.substring(0,str.length()-1);
    }

    /**
     * 逗号隔开的字符串是否包含某一项
     * @param strArray
     * @param str
     * @return
     */
    public boolean ArrayContains(String strArray,String str){
        boolean result = false;
        List<String> strList = StringToList(strArray,",");
        for(String a : strList){
            if(a.equals(str)){
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 将","隔开的字符串转换成sql语句in条件
     * @param str
     * @return
     */
    public static String StringToSqlIn(String str){
        if(StringUtils.isNotBlank(str)){
            String[] strArray = str.split(",");
            StringBuilder sb = new StringBuilder("");
            for(int i = 0;i < strArray.length;i++){
                sb.append("'"+strArray[i]+"',");
            }
            str = sb.length() > 0 ? sb.substring(0,sb.length()-1) : "";
        }
        return str;
    }

    /**
     * 小数四舍五入保留两位小数
     * @param num
     * @return
     */
    public static String doubleRound(double num){
        return String.format("%.2f",num);
    }

    /**
     * 小数四舍五入
     * @param num 数值
     * @param decimal 保留小数位
     * @param halfType 向上或向下进位：up,down
     * @return
     */
    public static double doubleRound(double num,int decimal,String halfType){
        BigDecimal b = new BigDecimal(num);
        if("down".equals(halfType)){
            return b.setScale(decimal, BigDecimal.ROUND_FLOOR).doubleValue();
        }else{
            return b.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    /**
     * 随机生成字母和数字组合的字符串
     * @param length 生成字符串长度
     * @return
     */
    public static String getRandString(int length) {
        Random random = new Random();
        char ch[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1'};
        if (length > 0) {
            char[] result = new char[length];
            int loops = length / 5, index = 0, rand = random.nextInt();
            for (int i = 0; i < length % 5; i++) {
                result[index++] = ch[(byte) rand & 63];
                rand >>= 6;
            }
            for (int i = length / 5; i > 0; i--) {
                rand = random.nextInt();
                for (int j = 0; j < 5; j++) {
                    result[index++] = ch[(byte) rand & 63];
                    rand >>= 6;
                }
            }
            return new String(result, 0, length);
        } else if (length == 0) {
            return "";
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 生成文件名称规则
     * @param suffix 后缀名
     * @return
     */
    public static String getNewFileName(String suffix){
        String newFileName = DateToString(new Date(),"yyyyMMddHHmmssSSS") + getRandString(5);
        if(StringUtils.isNotBlank(suffix)){
            newFileName = newFileName.concat("." + suffix);
        }
        return newFileName;
    }

    /**
     * 生产序列号
     * @return
     */
    public static String getSerialNo(){
        return DateToString(new Date(),"yyyyMMddHHmmssSSS") + "-" +getRandString(5);
    }

    /**
     * 获取6位随机数字验证码
     * @return
     */
    public static String getMsgCode(){
        int random=(int)((Math.random()*9+1)*100000);//6位随机数
        return random+"";
    }

    /**
     * 转义字符串中的Html标签
     * @param content
     * @return
     */
    public static String transHtmlTags(String content){
        if(!"".equals(content)){
            content = content.replaceAll("<","&lt;");
            content = content.replaceAll(">", "&gt;");
        }
        return content;
    }

    /**
     * 字符串编码转换 ISO-8859-1 转 UTF-8
     * @param str
     * @return
     */
    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 将字符串进行MD5加密
     * @param s
     * @return
     */
    public final static String MD5Encode(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput;
            btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     * @param str 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int strLength(String str) {
        int k = 0x80;
        if (str == null)
            return 0;
        char[] c = str.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (c[i] / k != 0) {//汉字长度+1
                len++;
            }
        }
        return len;
    }

    /**
     * 字符串是否为空
     * @param str
     * @return
     */
    public static String stringIsNull(String str){
        return str==null?"":str;
    }

    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证身份证号
     * @param idCardStr
     * @return
     */
    public static boolean isIDCardNO(String idCardStr){
        Pattern p = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
        Matcher m = p.matcher(idCardStr);
        return m.matches();
    }

    /**
     * 验证用户名
     * @param loginName
     * @return
     */
    public static boolean isLoginName(String loginName){
        Pattern p = Pattern.compile("^\\w{1,60}$");
        Matcher m = p.matcher(loginName);
        return m.matches();
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        Pattern p = Pattern.compile("^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 读取远程服务器文件流
     * @param path
     * @return
     */
    public static InputStream remoteGetFile(String path){
        try {
            URI uri = new URI(path);
            URL url = uri.toURL();
            URLConnection openConnection = url.openConnection();
            return openConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据文件路径获取文件的BASE64位编码
     * @param s
     * @return
     */
    public static String getBASE64(String s) {
        try {
            File file = new File(s);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = inputStream.toString().getBytes();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将BASE64编码的字符串进行解码
     * @param s
     * @return
     */
    public static byte[] getFromBASE64(String s) {
        if (s == null) return null;
        try {
            byte[] bytes = Base64.getDecoder().decode(s);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            return bytes;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断文件夹是否包含某文件
     * @param path,fileName
     * @return
     */
    public static boolean isHasFile(String path, final String fileName) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles(new FileFilter() {
                public boolean accept(File pathName) {
                    return pathName.isFile() && pathName.getName().equals(fileName);
                }
            });
            return files.length > 0;
        }
        return false;
    }

    /**
     * 获取完整带参数的请求url
     * @param request
     * @return

    public static String getFullRequestURL(HttpServletRequest request){
        StringBuffer url = request.getRequestURL();
        String qString = request.getQueryString();
        if (qString != null) {
            url.append("?");
            url.append(qString);
        }
        return url.toString();
    }*/

    /**
     * 根据入职日期计算工作年限
     * @param entryDate
     * @param formatStr
     * @return
     */
    public static String getWorkYear(String entryDate,String formatStr){
        String year = "";
        if(entryDate.length() >= 10){
            Date date = StringToDate(entryDate,formatStr);
            long y = DateDiff(DateToString(date,"yyyy-MM-dd"),DateToString(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
            double dd = y/1000/60/60/24/365;
            year = String.valueOf(dd);
            year = year.substring(0,year.indexOf("."));
        }
        return year;
    }

    /**
     * 根据身份证号计算年龄
     * @param idCard
     * @return
     */
    public static String getAge(String idCard){
        String age = "";
        if(idCard.length() > 14){
            String birthDay = idCard.substring(6,14);
            Date date1 = StringToDate(birthDay,"yyyyMMdd");
            long x = DateDiff(DateToString(date1,"yyyy-MM-dd"),DateToString(new Date(),"yyyy-MM-dd"),"yyyy-MM-dd");
            double d = x/1000/60/60/24/365;
            age = String.valueOf(d);
            age = age.substring(0,age.indexOf("."));
        }
        return age;
    }

    /**
     * 获取当前时间的前月的第一天
     * @param
     */
    public static String getFirstDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }

    /**
     * 获取当前时间的前月的最后一天
     * @param
     */
    public static String getLastDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        String lastDay = format.format(cale.getTime());
        return lastDay;
    }

    private static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(StringUtils.isNotBlank(str)){
            Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
            Matcher isNum = pattern.matcher(str);
            if(isNum.matches()){
                return true;
            }
        }
        return false;
    }

    /**
     * 从map中判断并取出double类型数值
     * @param str
     * @param map
     * @return
     */
    public static double getDoubleValue(String str,Map<String,Object> map){
        if(StringUtils.isNotBlank(str) && map.containsKey(str) && isNumeric(map.get(str).toString())){
            return Double.parseDouble(map.get(str).toString());
        }
        return 0;
    }

    /**
     * 将javaBean转换为xml
     * @param obj java对象
     * @param c javaBean的类型
     * @return
     * @throws Exception
     */
    public static String convertToXml(Object obj,Class c) throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(c);
        Marshaller marshaller = jaxbContext.createMarshaller();
        //格式化输出，即按标签自动换行，否则就是一行输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //设置编码（默认编码就是utf-8）
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        //是否省略xml头信息，默认不省略（false）
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        //marshaller.marshal(obj, file);
        //控制台输出
        //marshaller.marshal(obj,System.out);
        Writer sw = new StringWriter();
        marshaller.marshal(obj,sw);
        String xml=sw.toString();
        return xml;
    }

    /**
     *xml转换为java对象
     * @param xml
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T xmlToJavaBean(String xml,T obj) throws Exception{
        JAXBContext jc = JAXBContext.newInstance(obj.getClass());
        Unmarshaller unmar = jc.createUnmarshaller();
        T model = (T) unmar.unmarshal(new StringReader(xml));
        return model;
    }

    /**
     * 获取当前系统时间戳(秒数)
     * @return
     */
    public static Integer secondsTimestamp(){
        Long time=System.currentTimeMillis();
        String timestamp = String.valueOf(time/1000);
        return Integer.valueOf(timestamp);
    }

    /**
     * 将对象属性的名和值转为map
     * @param obj
     * @param excludeKeys
     * @return
     */
    public static Map<String,Object> objAttrToMap(Object obj,List<String> excludeKeys) {
        final BeanWrapper bw = new BeanWrapperImpl(obj);
        PropertyDescriptor[] pds = bw.getPropertyDescriptors();
        Map<String,Object> map=new HashMap<>();
        Stream.of(pds).forEach(pd -> {
            Object scrValue = bw.getPropertyValue(pd.getName());
            if (!"class".equals(pd.getName()) && !excludeKeys.contains(pd.getName()) && scrValue != null) {
                map.put(pd.getName(),scrValue);
            }
        });
        return map;
    }

    public static void main(String[] args){

    }
}
