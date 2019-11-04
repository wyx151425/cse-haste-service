package com.cse.haste.util;

/**
 * 响应状态码
 *
 * @author WangZhenqi
 */
public class StatusCode {
    public static final int SUCCESS = 200;
    public static final int STATUS_ERROR = 300;
    public static final int SYSTEM_ERROR = 500;
    public static final int PARAM_ERROR = 600;
    public static final int REQUEST_ILLEGAL = 9999;
    public static final int USER_UNREGISTER = 1000;
    public static final int USER_REGISTERED = 1001;
    public static final int USER_DISABLED = 1002;
    public static final int USER_LOGIN_TIMEOUT = 1003;
    public static final int USER_PASSWORD_ERROR = 1004;
    public static final int USER_OLD_PASSWORD_ERROR = 1005;
    public static final int USER_EMAIL_HAS_EXISTED = 1006;
    public static final int USER_UNAUTHORIZED = 1007;
    public static final int FILE_FORMAT_ERROR = 2000;
    public static final int FILE_RESOLVE_ERROR = 2001;
    public static final int PORTRAIT_FILE_UPLOAD_ERROR = 2002;
    public static final int FTP_SERVER_CONNECT_FAILED = 9000;
}
