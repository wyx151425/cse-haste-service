package com.cse.haste.util;

/**
 * @author WangZhenqi
 */
public class Constant {

    public static final String TOKEN = "token";
    public static final String CURRENT_USER = "current_user";

    public static class Request {
        public static class Header {
            public static final String REFERER = "referer";
        }
    }

    public static final class DocType {
        public static final String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        public static final String XLSX_UTF8 = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8";
    }

    public static class Status {
        public static final int DISABLED = 0;
        public static final int ENABLED = 1;
    }

    public static class Roles {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    public static class EvaluationPlan {
        public static class Types {
            public static final int LEADERSHIP_EVALUATION_PLAN = 100;
            public static final int LEADER_CADRE_EVALUATION_PLAN = 200;
            public static final int PROFESSIONAL_EVALUATION_PLAN = 300;
        }

        public static class Stages {
            public static final int INITIALIZE = 0;
            public static final int STARTED = 1;
            public static final int COMPLETED = 2;
        }
    }
}