package com.yqx.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>利用poi读取excel</p>
 *
 * 1.先用InputStream获取excel文件的io流
 * 2.然后创建一个内存中的excel文件HSSFWorkbook类型对象
 * 3.对这个excel文件的每页做循环处理
 * 4.对每页中每行做循环处理
 * 5.对每行中的每个单元格做处理，获取这个单元格的值
 * 6.把这行添加到一个List数组中
 * 7.把每行添加到最后的总结果中
 * 8.解析完以后就获取了一个List<List<String>>类型的对象了
 *
 * <p>xls和xlsx步骤相同，只是提供的类不同</p>
 *
 * @author YangChingyu-k
 * @date 2019/9/5 15:52
 */
public class DemoReadExcelText {

    public static void main(String[] args) {

        String path = "D:/work_to_space/textFile/msg.xls";
        try {
            List<List<String>> result = new DemoReadExcelText().readXls(path);
            System.out.println("---- size ----" + result.size());
            for (int i=0;i<result.size();i++) {
                List<String> list = result.get(i);
                System.out.println("------ list: ------" + list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取数据xls类型
     *
     * @param path
     * @return
     * @throws Exception
     */
    private List<List<String>> readXls(String path) throws Exception{

        InputStream is = new FileInputStream(path);
        // xls类型
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        int size = workbook.getNumberOfSheets();
        // 循环每一页，并处理当前页
        for (int i=0; i<size; i++) {
            // 标识某一页
            HSSFSheet sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            // 遍历，表头不要
            for (int rowNum=1; rowNum<=sheet.getLastRowNum();rowNum++) {
                // HSSFRow 表示行
                HSSFRow hssfRow = sheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                int minCol = hssfRow.getFirstCellNum();
                int maxCol = hssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                //遍历该行，获取处理每个cell元素
                for (int colIx = minCol; colIx<maxCol; colIx++) {
                    // HSSFCell 表示单元格
                    HSSFCell cell = hssfRow.getCell(colIx);
                    if (cell == null) {
                        continue;
                    }
                    rowList.add(getStringVal(cell));
                }
                result.add(rowList);
            }

        }
        return result;

    }

    /**
     * 读取数据xlsx类型
     *
     * @param path
     * @return
     * @throws Exception
     */
    private List<List<String>> readXlsx(String path) throws Exception{
        InputStream is = new FileInputStream(path);
        // xlsx
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        // 循环每一页
        for (int i=0;i<workbook.getNumberOfSheets();i++) {
            XSSFSheet xssfSheet = workbook.getSheetAt(i);
            if (xssfSheet == null) {
                continue;
            }
            // 遍历，表头不要
            for (int rowNum=1;rowNum<=xssfSheet.getLastRowNum();rowNum++) {
                // XSSFRow 表示行
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                int minCol = xssfRow.getFirstCellNum();
                int maxCol = xssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                // 遍历该行，获取每个cell
                for (int j=minCol;j<maxCol;j++) {
                    // 单元格
                    XSSFCell cell = xssfRow.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    rowList.add(cell.toString());
                }
                result.add(rowList);
            }
        }
        return result;
    }


    /**
     * 类型修改
     *
     * @param cell
     * @return
     */
    private String getStringVal(HSSFCell cell) {

        String value = "";

        // 旧版
        // switch (cell.getCellType()) {
        //     case Cell.CELL_TYPE_BOOLEAN:
        //         return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
        //     case Cell.CELL_TYPE_FORMULA:
        //         return cell.getCellFormula();
        //     case Cell.CELL_TYPE_NUMERIC:
        //         cell.setCellType(Cell.CELL_TYPE_STRING);
        //         return cell.getStringCellValue();
        //     case Cell.CELL_TYPE_STRING:
        //         return cell.getStringCellValue();
        //     default:
        //         return "";
        // }

        // 新版
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                value =  cell.getBooleanCellValue() ? "TRUE" : "FALSE";
                break;
            case FORMULA:
                value = "" + cell.getCellFormula();
                break;
            case NUMERIC:
                value = "" + cell.getNumericCellValue();
                break;
            case STRING:
                value = "" + cell.getStringCellValue();
                break;
            case ERROR:
                value = "" + cell.getErrorCellValue();
                break;
            default:
                break;
        }
        return value;

    }

}
