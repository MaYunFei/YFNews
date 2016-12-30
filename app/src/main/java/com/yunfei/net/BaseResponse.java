package com.yunfei.net;

/**
 * Created by yunfei on 2016/12/6.
 * email mayunfei6@gmail.com
 */

public class BaseResponse<T> {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[]}
     * error_code : 0
     */

    private String reason;
    private ResultEntity<T> result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultEntity<K> {
        private String stat;
        private K data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public K getData() {
            return data;
        }

        public void setData(K data) {
            this.data = data;
        }
    }
}
