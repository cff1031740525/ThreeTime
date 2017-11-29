package test.bwei.com.platform.jsonbean;

import java.util.List;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/28
 * Description:
 */

public class JokeBean {
    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        public Object commentNum;
        public String content;
        public String createTime;
        public int jid;
        public Object praiseNum;
        public Object shareNum;
        public int uid;
        public UserBean user;

        public static class UserBean {
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
}
