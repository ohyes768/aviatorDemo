package com.dbapp.aviator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: jackson.tang
 * @version: 1.0
 * @Date: Created in 2018/1/24 10:15
 * @Modified:
 */
public class FastjsonReadFile {

    public static void main(String[] args) {
        JSONArray root = readJson();
        for( Object single : root){
            JSONObject jsonObject = (JSONObject) single;
            if("true".equals(jsonObject.get("enable"))){
                String aviaExp = jsonObject.getString("condition");
            }else continue;
        }

    }

    public static JSONArray readJson(){
        JSONArray root = new JSONArray();
        try {
//            System.out.println(FastjsonReadFile.class.getResource("").getPath());
            InputStream is	= FastjsonReadFile.class.getClassLoader().getResourceAsStream("fieldmapping_items.json");
            String text = IOUtils.toString(is,"utf8");
            root = JSON.parseObject(text,JSONArray.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  root;
    }



}
