package com.casic.accessControl.xls;
import com.casic.accessControl.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/1.
 */
public class ReadColumnFromExcel {



    public static Map<String,Object> CheckColumnExcel(String path) throws  IOException{
        Map<String,Object> map = new HashMap<String, Object>();
        if (path == null || Common.EMPTY.equals(path)) {
            map.put("success",false);
            map.put("message","文件路径不能为空！");
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    map = CheckXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    map = CheckXlsx(path);
                }
            } else {
                map.put("success",false);
                map.put("message",path + Common.NOT_EXCEL_FILE);
            }
        }
        return map;
    }

    public static Map<String,Object> CheckXlsx(String path) {
        Map<String,Object> map = new HashMap<String, Object>();
        return map;
    }

    public static Map<String, Object> CheckXls(String path) {
        Map<String,Object> map = new HashMap<String, Object>();
        return map;
    }


}
