package com.cse.haste.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author WangZhenqi
 */
@Component
public class PropertiesUtil {
    /**
     * FTP文件服务器IP
     */
    public static String ftpServerIp;
    /**
     * FTP文件服务器用户名
     */
    public static String ftpUsername;
    /**
     * FTP文件服务器密码
     */
    public static String ftpPassword;

    @Value(value = "${ftp.server.ip}")
    public void setFtpServerIp(String ftpServerIp) {
        PropertiesUtil.ftpServerIp = ftpServerIp;
    }

    @Value(value = "${ftp.server.username}")
    public void setFtpUsername(String ftpUsername) {
        PropertiesUtil.ftpUsername = ftpUsername;
    }

    @Value(value = "${ftp.server.password}")
    public void setFtpPassword(String ftpPassword) {
        PropertiesUtil.ftpPassword = ftpPassword;
    }
}
