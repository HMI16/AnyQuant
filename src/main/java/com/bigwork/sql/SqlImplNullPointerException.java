package com.bigwork.sql;

/**
 * Created by 15HR-1528SS on 2016/5/5.
 */
public class SqlImplNullPointerException extends Exception {
    String errorMessage;

    public SqlImplNullPointerException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String toString(){
        return errorMessage;
    }

    public String getMessage(){
        return errorMessage;
    }
}
