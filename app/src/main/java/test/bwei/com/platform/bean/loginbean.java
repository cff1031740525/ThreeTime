package test.bwei.com.platform.bean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/20
 * Description:
 */

public class loginbean {


    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-27T09:14:46","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/100.jpg","latitude":null,"longitude":null,"mobile":"13775854299","money":0,"nickname":"陈汉飞","password":"123123","praiseNum":null,"token":"2169FEE6F3AC42D662EFF44250A0BD87","uid":100,"username":"13775854299"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-27T09:14:46
         * email : null
         * fans : null
         * follow : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/100.jpg
         * latitude : null
         * longitude : null
         * mobile : 13775854299
         * money : 0
         * nickname : 陈汉飞
         * password : 123123
         * praiseNum : null
         * token : 2169FEE6F3AC42D662EFF44250A0BD87
         * uid : 100
         * username : 13775854299
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public Object fans;
        public Object follow;
        public int gender;
        public String icon;
        public Object latitude;
        public Object longitude;
        public String mobile;
        public int money;
        public String nickname;
        public String password;
        public Object praiseNum;
        public String token;
        public int uid;
        public String username;
    }
}
