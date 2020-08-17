package com.fanzhou.service.impl;

import com.fanzhou.domain.User;
import com.fanzhou.service.ExportService;
import com.fanzhou.utils.ExcelFormatUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * @author fanzhou
 * @create 2020/8/17 0017
 */
public class ExportServiceImpl implements ExportService {
    @Override
    public void exportExcel(List<User> userList) throws Exception {

        InputStream is = export(userList);
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        OutputStreamWriter osW = new OutputStreamWriter(new FileOutputStream("aaa.xls"));

    }

    private InputStream export(List<User> list) {
        ByteArrayOutputStream output = null;
        InputStream inputStream1 = null;
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);// 保留1000条数据在内存中
        SXSSFSheet sheet = wb.createSheet();
// 设置报表头样式
        CellStyle header = ExcelFormatUtil.headSytle(wb);// cell样式
        CellStyle content = ExcelFormatUtil.contentStyle(wb);// 报表体样式

// 每一列字段名
        String[] strs = new String[]{"序号", "用户名", "生日", "性别", "地址"};

// 字段名所在表格的宽度
        int[] ints = new int[]{5000, 5000, 5000, 5000, 5000};

// 设置表头样式
        ExcelFormatUtil.initTitleEX(sheet, header, strs, ints);
        //logger.info(">>>>>>>>>>>>>>>>>>>>表头样式设置完成>>>>>>>>>>");

        if (list != null && list.size() > 0) {
            //logger.info(">>>>>>>>>>>>>>>>>>>>开始遍历数据组装单元格内容>>>>>>>>>>");
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                SXSSFRow row = sheet.createRow(i + 1);
                int j = 0;
                SXSSFCell cell = row.createCell(j++);

                cell = row.createCell(j++);
                cell.setCellValue(user.getId()); //序号
                cell.setCellStyle(content);


                cell.setCellValue(user.getUsername());  // 用户名
                cell.setCellStyle(content);

                cell = row.createCell(j++);
                cell.setCellValue(user.getBirthday()); // 生日
                cell.setCellStyle(content);

                cell = row.createCell(j++);
                cell.setCellValue(user.getSex()); // 性别
                cell.setCellStyle(content);


                cell = row.createCell(j++);
                cell.setCellValue(user.getAddress()); // 地址
                cell.setCellStyle(content);

            }
            //logger.info(">>>>>>>>>>>>>>>>>>>>结束遍历数据组装单元格内容>>>>>>>>>>");
        }
        try {
            output = new ByteArrayOutputStream();
            wb.write(output);
            inputStream1 = new ByteArrayInputStream(output.toByteArray());
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                    if (inputStream1 != null)
                        inputStream1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inputStream1;
    }
}
