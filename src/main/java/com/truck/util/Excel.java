package com.truck.util;

import com.truck.pojo.EntryDetail;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Excel {

    private static  final Logger logger = LoggerFactory.getLogger(Excel.class);

    public static List<EntryDetail> loadExportsLists(Integer entryId,String xlsPath) throws IOException {

        List<EntryDetail> temp = new ArrayList();
        logger.info("xlsPath路径：{}",xlsPath);
        FileInputStream fileIn = new FileInputStream(xlsPath);
//根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fileIn);
//获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
//对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<2){
                continue;
            }
/*
            if(r.getRowNum()<1){
                continue;
            }
*/
//创建实体类
            EntryDetail entryDetail =new EntryDetail();
            entryDetail.setEntryId(entryId);
            entryDetail.setInspectStatus(0);
//取出当前行第1个单元格数据，并封装在info实体stuName属性上
            entryDetail.setCustomsClearance(String.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(0).getNumericCellValue()))));
            entryDetail.setDestination(r.getCell(1).getStringCellValue());
            entryDetail.setPackageNo(r.getCell(2).getStringCellValue());
//            entryDetail.setSerialNo(String.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(3).getNumericCellValue()))));

         if(r.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING){
             entryDetail.setPartsNo(r.getCell(3).getStringCellValue());
         }else{
             entryDetail.setPartsNo(String.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(3).getNumericCellValue()))));
//             exportsLists.setSerialNo(String.valueOf(r.getCell(4).getNumericCellValue()));、
         }

            entryDetail.setPartsName(r.getCell(4).getStringCellValue());
            entryDetail.setPartsEnName(r.getCell(5).getStringCellValue());
            entryDetail.setUnit(r.getCell(6).getStringCellValue());
            entryDetail.setPurchaseNum(Integer.parseInt(new java.text.DecimalFormat("0").format(r.getCell(7).getNumericCellValue())));
            entryDetail.setPurchasePrice(new BigDecimal(0));
      /*      if(String.valueOf(r.getCell(9).getNume、ricCellValue())!=null){
                entryDetail.setPurchasePrice(new BigDecimal(r.getCell(9).getNumericCellValue()));
            }else{
                entryDetail.setPurchasePrice(new BigDecimal(0));
            }*/
            if(String.valueOf(r.getCell(8).getNumericCellValue())!=null){
                entryDetail.setSalesPrice(new BigDecimal(r.getCell(8).getNumericCellValue()));
            }else{
                entryDetail.setSalesPrice(new BigDecimal(0));
            }
            entryDetail.setDeviceType(r.getCell(9).getStringCellValue());


//            info.setLscore(r.getCell(3).getNumericCellValue());
            if(StringUtils.isBlank(entryDetail.getCustomsClearance()) || entryDetail.getCustomsClearance().equals("0")){
                continue;
            }else{
                temp.add(entryDetail);
            }

        }
        fileIn.close();
        //mybatis 批量插入
//        if (temp != null) {
//            productMapper.batchInsert(temp);
//        }
        return temp;
    }

    /*public static void main(String[] args) {
        String path =  "/Users/jianhe/Desktop/出口产品清单 4.xls";
        List<ExportsLists> list = null;
        try {
            list = Excel.loadExportsLists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ExportsLists exportsLists : list) {
            System.out.println(exportsLists.getCartType());
            System.out.println(exportsLists.getPartsName());
        }
    }*/

}
