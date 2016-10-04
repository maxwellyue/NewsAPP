package com.yue.maxwell.newsapp.net;

/**
 * 2016/10/3 0003，由 Administrator 创建 .
 * <p>
 * 功能描述：
 * <p>
 * 说明：
 * ---------------------------
 * 修改时间：
 * 修改说明：
 * 修改人：
 */

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Retrofit字符转换器
 * Created by 风雪涟漪 on 2015/11/4.
 */
public class StringConverterFactory extends Converter.Factory {

    private static final String TAG = "StringConverterFactory";

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    public StringConverterFactory() {
        super();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        if(type == String.class){
            return StringConverter.INSTANCE;
        }

        return null;
    }

}