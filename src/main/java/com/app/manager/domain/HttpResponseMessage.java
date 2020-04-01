package com.app.manager.domain;

import java.util.Objects;

/**
 * @author cxf
 */
public class HttpResponseMessage {

    /**
     * http响应状态码
     */
    private String httpCode;

    /**
     * http响应消息
     */
    private String message;

    /**
     * http响应内容
     */
    private String content;

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        HttpResponseMessage that = (HttpResponseMessage) o;
        return Objects.equals(httpCode, that.httpCode) &&
                Objects.equals(message, that.message) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpCode, message, content);
    }

    @Override
    public String toString() {
        return "HttpResponseMessage{" +
                "httpCode='" + httpCode + '\'' +
                ", message='" + message + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
