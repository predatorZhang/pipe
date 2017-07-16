package com.casic.accessControl.xls;

import com.casic.accessControl.org.dto.UserInfoDTO;
import com.casic.accessControl.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
 * Created by test203 on 2015/12/24.
 */
public class ReadUserInfoFromExcel {

    public static List<UserInfoDTO> readExcel(String path) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return new ArrayList<UserInfoDTO>();
    }

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


    /**
     * Read the Excel 2010
     *
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public static List<UserInfoDTO> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        if (xssfSheet == null ) {
            return new ArrayList<UserInfoDTO>();
        }

        XSSFRow xssfRow = xssfSheet.getRow(0);
        List<UserInfoDTO> list = new ArrayList<UserInfoDTO>();
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            xssfRow = xssfSheet.getRow(rowNum);
            if (xssfRow != null) {
                if(xssfRow.getCell(0)!=null){
                    xssfRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    if(!StringUtils.isNotBlank(xssfRow.getCell(0).getStringCellValue())){
                        break;
                    }
                    UserInfoDTO dto = new UserInfoDTO();
                    dto.setUsername(xssfRow.getCell(0).getStringCellValue());
                    dto.setTel(xssfRow.getCell(1).getStringCellValue());
                    dto.setAddress(xssfRow.getCell(2).getStringCellValue());
                    dto.setDescn(xssfRow.getCell(3).getStringCellValue());
                    dto.setPassed(true);
                    dto.setPassword(xssfRow.getCell(0).getStringCellValue()+"123");
                    list.add(dto);
                }

            }
        }
        return list;
    }

    /**
     * Read the Excel 2003-2007
     *
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public static List<UserInfoDTO> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

        // Read the Sheet
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        if (hssfSheet == null ) {
            return new ArrayList<UserInfoDTO>();
        }

        HSSFRow hssfRow = hssfSheet.getRow(0);
        List<UserInfoDTO> list = new ArrayList<UserInfoDTO>();
        for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
            hssfRow = hssfSheet.getRow(rowNum);
            if (hssfRow != null) {
                if(!StringUtils.isNotBlank(hssfRow.getCell(0).getStringCellValue())){
                    break;
                }
                UserInfoDTO dto = new UserInfoDTO();
                dto.setUsername(hssfRow.getCell(0).getStringCellValue());
                dto.setPassword(hssfRow.getCell(0).getStringCellValue()+"123");
                dto.setPassed(false);
                dto.setTel(hssfRow.getCell(1).getStringCellValue());
                dto.setAddress(hssfRow.getCell(2).getStringCellValue());
                dto.setDescn(hssfRow.getCell(3).getStringCellValue());
                list.add(dto);
            }
        }
        return list;
    }
}
