package com.cse.haste.util;

import com.cse.haste.context.HasteException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author WangZhenqi
 */
public class FTPServerUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPServerUtil.class);

    private static String ftpServerIp = PropertiesUtil.ftpServerIp;
    private static String ftpUsername = PropertiesUtil.ftpUsername;
    private static String ftpPassword = PropertiesUtil.ftpPassword;

    private FTPServerUtil(String ip, int port, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPServerUtil ftpServerUtil = new FTPServerUtil(ftpServerIp, 21, ftpUsername, ftpPassword);
        logger.info("FTP server connected...");
        boolean result = ftpServerUtil.uploadFileToFTPServer("aries", fileList);
        logger.info("upload completed...");
        return result;
    }

    public static Workbook getTemplate(String templateName) throws IOException, InvalidFormatException {
        FTPServerUtil ftpServerUtil = new FTPServerUtil(ftpServerIp, 21, ftpUsername, ftpPassword);
        logger.info("FTP server connected...");
        Workbook workbook = ftpServerUtil.getTemplateFromFTPServer("aries", templateName);
        logger.info("upload completed...");
        return workbook;
    }

    private boolean uploadFileToFTPServer(String remotePath, List<File> fileList) throws IOException {
        boolean uploadResult = true;
        FileInputStream fis = null;
        // 链接FTP文件服务器
        if (connectServer(this.ip, this.port, this.username, this.password)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis);
                }
            } catch (IOException e) {
                logger.error("file upload exception: ", e);
                uploadResult = false;
                e.printStackTrace();
            } finally {
                if (null != fis) {
                    fis.close();
                }
                ftpClient.disconnect();
            }
        } else {
            throw new HasteException(StatusCode.FTP_SERVER_CONNECT_FAILED);
        }
        return uploadResult;
    }

    private Workbook getTemplateFromFTPServer(String remotePath, String templateName) throws IOException, InvalidFormatException {
        InputStream in = null;
        // 链接FTP文件服务器
        if (connectServer(this.ip, this.port, this.username, this.password)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                in = ftpClient.retrieveFileStream(templateName);
                return WorkbookFactory.create(in);
            } catch (IOException e) {
                logger.error("file upload exception: ", e);
            } finally {
                if (null != in) {
                    in.close();
                }
                ftpClient.disconnect();
            }
        } else {
            throw new HasteException(StatusCode.FTP_SERVER_CONNECT_FAILED);
        }
        return null;
    }

    private boolean connectServer(String ip, int port, String username, String password) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(username, password);
        } catch (IOException e) {
            logger.error("file upload exception: ", e);
        }
        return isSuccess;
    }

    /**
     * 文件服务器IP
     */
    private String ip;
    /**
     * 文件服务器端口
     */
    private int port;
    /**
     * 文件服务器用户名
     */
    private String username;
    /**
     * 文件服务器密码
     */
    private String password;
    /**
     * 文件服务器客户端
     */
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}