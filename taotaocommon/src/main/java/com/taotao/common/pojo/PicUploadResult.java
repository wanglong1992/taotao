package com.taotao.common.pojo;

public class PicUploadResult {
    private Integer error;
    private String url;
    private  String message;

    public Integer getError() {
        return error;
    }

    public PicUploadResult setError(Integer error) {
        this.error = error;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PicUploadResult setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PicUploadResult setMessage(String message) {
        this.message = message;
        return this;
    }
}
