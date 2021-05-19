package com.shiliang.icor.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.shiliang.icor.hander.CreateTemplateWriteHandler;
import com.shiliang.icor.hander.TemplateCellWriteHandler;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName ExcelUtils.java
 * @Description TODO
 * @createTime 2021年05月09日 10:04:00
 */
public class ExcelUtils {

    /**
     * 生成excel模板
     *
     * @param response
     * @param fileName    下载的文件名，
     * @param sheetName   sheet名
     * @param data        导出的数据
     * @param model       导出的头
     * @param heardHeight 头行高
     */
    public static void createTemplate(HttpServletResponse response, String fileName,
                                      String sheetName, List<? extends Object> data,
                                      Class<?> model, int heardHeight) throws IOException {

        HorizontalCellStyleStrategy horizontalCellStyleStrategy = setMyCellStyle();
        EasyExcel.write(getOutputStream(fileName, response, ExcelTypeEnum.XLSX), model).
                excelType(ExcelTypeEnum.XLSX).sheet(sheetName)
                .registerWriteHandler(new TemplateCellWriteHandler(heardHeight))
                .registerWriteHandler(horizontalCellStyleStrategy)
                .doWrite(data);
    }

    /**
     * 创建我的cell  策略
     *
     * @return
     */
    public static HorizontalCellStyleStrategy setMyCellStyle() {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 颜色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 10);
        // 字体
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setWrapped(true);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 设置内容靠中对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭

        return horizontalCellStyleStrategy;
    }

    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response, ExcelTypeEnum excelTypeEnum) throws IOException {
        //创建本地文件
        String filePath = fileName + excelTypeEnum.getValue();
        try {
//            fileName = new String(filePath.getBytes(), StandardCharsets.ISO_8859_1);
            fileName=URLEncoder.encode(filePath,"UTF-8");
//            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType("application/vnd.ms-excel");
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.addHeader("Content-Disposition", "filename=" +fileName );
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
//            response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName );
            return response.getOutputStream();
        } catch (IOException e) {
            throw new IOException("导出文件异常");
        }

    }
}
