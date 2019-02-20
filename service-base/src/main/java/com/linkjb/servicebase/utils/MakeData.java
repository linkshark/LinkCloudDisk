package com.linkjb.servicebase.utils;

/**
 * @author sharkshen
 * @data 2019/2/19 10:17
 * @Useage
 */

import com.linkjb.servicebase.pojo.NameData;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 * 随机生成汉字姓名(10万数据量) + 身份证号
 *
 * @class_name MakeData.java
 * @package_name com.yushen.data
 */
public class MakeData {

    public static void main(String[] args) {

        int dataLength = 1000000;
        List<NameData> dataList = makeData(dataLength);
        for(int i = 0 ; i< dataList.size();i++){
            System.out.println("姓名：" + dataList.get(i).getName() +",身份证号：" + dataList.get(i).getIdCardNumber());
        }

    }

    /**
     * 随机生成名字+身份证号
     *
     * @param dataLength
     * @return
     */
    public static List<NameData> makeData(int dataLength) {
        // 写一个百家姓的数组
        String[] Surname = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈",
                "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶",
                "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费",
                "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于",
                "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧",
                "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴",
                "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席",
                "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐",
                "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢",
                "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪",
                "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀",
                "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段",
                "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班",
                "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘",
                "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲",
                "邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能",
                "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦",
                "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "浦", "尚", "农", "温",
                "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼", "容", "向", "古",
                "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文",
                "寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂",
                "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养",
                "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖",
                "益", "桓", "公", "仉", "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "归", "海", "晋", "楚",
                "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟", "佘", "佴", "伯", "赏", "墨", "哈", "谯", "篁", "年",
                "爱", "阳", "佟", "言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁", "檀", "祭",
                "密", "敬", "揭", "舜", "楼", "疏", "冒", "浑", "挚", "胶", "随", "高", "皋", "原", "种", "练", "弥",
                "仓", "眭", "蹇", "覃", "阿", "门", "恽", "来", "綦", "召", "仪", "风", "介", "巨", "木", "京", "狐",
                "郇", "虎", "枚", "抗", "达", "杞", "苌", "折", "麦", "庆", "过", "竹", "端", "鲜", "皇", "亓", "老",
                "是", "秘", "畅", "邝", "还", "宾", "闾", "辜", "纵", "侴", "万俟", "司马", "上官", "欧阳", "夏侯",
                "诸葛", "闻人", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正", "濮阳", "淳于", "单于",
                "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空",
                "兀官", "司寇", "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋",
                "夹谷", "宰父", "谷梁", "段干", "百里", "东郭", "微生", "梁丘", "左丘", "东门", "西门", "南宫", "第五", "公仪",
                "公乘", "太史", "仲长", "叔孙", "屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门", "毋丘", "贺兰",
                "綦毋", "屋庐", "独孤", "南郭", "北宫", "王孙" };
        Random random = new Random(System.currentTimeMillis());

        // List集合存储姓名+身份证号实体类
        List<NameData> nameDataList = new ArrayList<>();
        // 循环dataLength 我要生成这么多数据量的数据
        for (int i = 0; i < dataLength; i++) {
            // 随机取得一个姓氏
            int index = random.nextInt(Surname.length - 1);
            String nameSur = Surname[index];
            String name = nameSur;
            NameData nameData = new NameData();
            // nextBoolean() 方法用于从该随机数生成器的序列得到下一个伪均匀分布的布尔值。
            // 根据随机布尔值，确定名字是一个字还是两个字
            if (random.nextBoolean()) {
                name += getName() + getName();
            } else {
                name += getName();
            }
            nameData.setName(name);
            // 再给每个生成的名字加个随机身份证号
            String idCardNumber = makeidCardNumber();
            nameData.setIdCardNumber(idCardNumber);
            nameDataList.add(nameData);
            //System.out.println(name + ":" + idCardNumber);
        }
        return nameDataList;
    }

    /**
     * 随机获取一个汉字来组成名字
     *
     * @return
     */
    public static String getName() {
        String nameStr = "";
        int highCode, lowCode;
        Random random = new Random();
        // 区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        highCode = (176 + Math.abs(random.nextInt(71)));
        random = new Random();
        // 位码，0xA0打头，范围第1~94列
        lowCode = 161 + Math.abs(random.nextInt(94));

        byte[] codeArr = new byte[2];
        codeArr[0] = (new Integer(highCode)).byteValue();
        codeArr[1] = (new Integer(lowCode)).byteValue();
        try {
            // 区位码组合成汉字
            nameStr = new String(codeArr, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return nameStr;
    }

    /**
     * 随机生成身份证号
     *
     * @return
     */
    private static String makeidCardNumber() {
        // 身份证号是啥？户籍所在地（第1到第6位） + 出生日期（第7到第14位） + 落户派出所代码（第15、16位） + 性别代码（第17位） + 验证码（第18位）
        // 户籍所在地(以北京为例)
        Map<String, Integer> registerLocation = new HashMap<>();
        registerLocation.put("北京市", 110000);
        registerLocation.put("市辖区", 110100);
        registerLocation.put("东城区", 110101);
        registerLocation.put("西城区", 110102);
        registerLocation.put("崇文区", 110103);
        registerLocation.put("宣武区", 110104);
        registerLocation.put("朝阳区", 110105);
        registerLocation.put("丰台区", 110106);
        registerLocation.put("石景山区", 110107);
        registerLocation.put("海淀区", 110108);
        registerLocation.put("门头沟区", 110109);
        registerLocation.put("房山区", 110111);
        registerLocation.put("通州区", 110112);
        registerLocation.put("顺义区", 110113);
        registerLocation.put("昌平区", 110114);
        registerLocation.put("大兴区", 110115);
        registerLocation.put("怀柔区", 110116);
        registerLocation.put("平谷区", 110117);
        registerLocation.put("县", 110200);
        registerLocation.put("密云县", 110228);
        registerLocation.put("延庆县", 110229);
        registerLocation.put("天津市", 120000);
        registerLocation.put("市辖区", 120100);
        registerLocation.put("和平区", 120101);
        registerLocation.put("河东区", 120102);
        registerLocation.put("河西区", 120103);
        registerLocation.put("南开区", 120104);
        registerLocation.put("河北区", 120105);
        registerLocation.put("红桥区", 120106);
        registerLocation.put("东丽区", 120110);
        registerLocation.put("西青区", 120111);
        registerLocation.put("津南区", 120112);
        registerLocation.put("北辰区", 120113);
        registerLocation.put("武清区", 120114);
        registerLocation.put("宝坻区", 120115);
        registerLocation.put("县", 120200);
        registerLocation.put("宁河县", 120221);
        registerLocation.put("静海县", 120223);
        registerLocation.put("蓟　县", 120225);

        StringBuffer strBuffer = new StringBuffer();
        // 区号
        strBuffer.append(randomLocationCode(registerLocation));
        // 身份证号
        strBuffer.append(randomBirthday());
        // 15、16、17位
        strBuffer.append(randomCode());
        // 利用前十七位获取第十八位
        String eighteenth = verificationCode(strBuffer.toString());
        strBuffer.append(eighteenth);
        return strBuffer.toString();
    }

    /**
     * 随机获取区号
     * @param registerLocation
     * @return
     */
    public static String randomLocationCode(Map<String, Integer> registerLocation) {
        int index = (int) (Math.random() * registerLocation.size());
        Collection<Integer> values = registerLocation.values();
        Iterator<Integer> it = values.iterator();
        int i = 0;
        int locationCode = 0;
        while (i <= index && it.hasNext()) {
            i++;
            if (i == index) {
                locationCode = it.next();
            }
        }
        return String.valueOf(locationCode);
    }

    /**
     * 随机生成出生日期
     *
     * @return
     */
    public static String randomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 60) + 1950);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }
    /**
     * 随机获取落户派出所代码（第15、16位） + 性别代码（第17位）
     * 直接生成三位数
     * @return
     */
    public static String randomCode() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }
    /**
     * 生成第18位身份证号
     * @param str17
     * @return
     * 身份证校验码的计算方法
     * 将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2。
     * 将这17位数字和系数相乘的结果相加。
     * 用加出来和除以11，看余数是多少？
     * 余数只可能有0－1－2－3－4－5－6－7－8－9－10这11个数字。
     * 其分别对应的最后一位身份证的号码为1－0－X －9－8－7－6－5－4－3－2。
     */
    public static String verificationCode(String str17) {
        char[] chars = str17.toCharArray();
        if (chars.length < 17) {
            return " ";
        }
        // 前十七位分别对应的系数
        int[] coefficient = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        // 最后应该取得的第十八位的验证码
        char[] resultChar = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        int[] numberArr = new int[17];
        int result = 0;
        for (int i = 0; i < numberArr.length; i++) {
            numberArr[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < numberArr.length; i++) {
            result += coefficient[i] * numberArr[i];
        }
        return String.valueOf(resultChar[result % 11]);
    }
}
