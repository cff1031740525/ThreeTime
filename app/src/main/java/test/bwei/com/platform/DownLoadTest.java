package test.bwei.com.platform;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/22
 * Description:
 */

public class DownLoadTest {
    public static void main(String[] args) throws Exception {
        //请求服务器的路径
        String path = "http://120.27.23.105/version/baidu.apk";
        int threadcount=3;
        URL url = new URL(path);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (200 == responseCode) {
            //获取请求数据的长度
            int contentLength = httpURLConnection.getContentLength();
            //创建一个和请求数据长度一样的临时文件
            RandomAccessFile raf=new RandomAccessFile("baidu.apk","rwd");
            //设置这个文件的大小
            raf.setLength(contentLength);
            raf.close();
            //设置每个子线程下载文件的大小
            int sonsize=contentLength/threadcount;



        }
    }
}
