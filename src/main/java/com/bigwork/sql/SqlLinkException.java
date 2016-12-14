package com.bigwork.sql;

/**
 * Created by 15HR-1528SS on 2016/5/5.
 */
public class SqlLinkException extends Exception {
    String errorMessage;

    public SqlLinkException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String toString(){
        return errorMessage;
    }

    public String getMessage(){
        return errorMessage;
    }
}
