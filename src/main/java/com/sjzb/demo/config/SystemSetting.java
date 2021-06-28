package com.sjzb.demo.config;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.apache.lucene.store.BufferedIndexInput.BUFFER_SIZE;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-02-23 9:52:41
 * @Description:
 */
public class SystemSetting {
    /**
     * @Author: chenlx
     * @Date: 2021-02-04 9:37:15
     * @Params: null
     * @Return
     * @Description: 获取项目运行的ip地址
     */
    public String getLocalHost() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostAdd = address.getHostAddress();
        return hostAdd;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-02-23 15:43:30
     * @Params: null
     * @Return 运行配置有道词典代理exe
     * @Description:
     */
    public void runYodaoDictProxySetting() throws IOException {
        Runtime rn = Runtime.getRuntime();
        System.out.println("【配置词典】即将打开有道词典代理设置外部程序来链接数据指标词典数据库。");
        Process p = null;
        try {
            String appPath = getSystemPath();
            copyFileFromJar("sjzb_proxy_setting.exe", appPath);
            String sh = "cmd /C start " + appPath + "\\sjzb_proxy_setting.exe" + " \"" + getLocalHost() + "\"";
            sh = sh.replace("file:/", "");
            p = rn.exec(sh);
        } catch (Exception e) {
            System.out.println("Error exec!");
            System.out.println(e);
        }
        System.out.println("【配置词典】程序已尝试调用，具体执行结果请参考新打开的命令窗口。\n【配置词典】启动服务前如果已经打开有道词典，服务会自动关闭并重启。");
    }


    /**
     * @Author: chenlx
     * @Date: 2021-02-23 15:43:47
     * @Params: null
     * @Return
     * @Description: 获取系统路径（目前是只获取AppData）
     */
    public String getSystemPath() {
        String res = "";
        String userhome = System.getProperty("user.home");
        return userhome;

    }

    /**
     * @Author: chenlx
     * @Date: 2021-02-23 15:44:13
     * @Params: null
     * @Return
     * @Description: 获取已经打包进jar的文件到指定的路径
     */
    private void copyFileFromJar(String fileRegex, String strDestFileName) {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + fileRegex;

        try {
            Resource[] resources = resourcePatternResolver.getResources(pattern);

            if (resources != null && resources.length > 0) {
                Resource res = resources[0];
                InputStream fis = res.getInputStream();
                OutputStream fos = new FileOutputStream(strDestFileName + "\\" + fileRegex);
                byte[] bArrBuffer = new byte[BUFFER_SIZE];
                int iCount = 0;

                while ((iCount = fis.read(bArrBuffer, 0, bArrBuffer.length)) != -1) {
                    fos.write(bArrBuffer, 0, iCount);
                }

                fis.close();
                fos.close();
            }
        } catch (IOException e) {
        }
    }

}
