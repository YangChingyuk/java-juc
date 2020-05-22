package com.yqx.demo;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * jxl辅助测试
 *
 * @author YangChingyu-k
 * @date 2019/9/4 17:03
 */
public class DemoSqlTest {

    public static void main(String[] args) {

        // 读取Excel文件
        File readfile = new File("D:/work_to_space/textFile/msg.xls");
        try {
            //得到所有数据
            List<List<String>> allData = readExcel(readfile);
            //处理数据
            List<List<String>> result = dealData(allData);
            // 写入
            makeExcel(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据
     * @param file
     * @return
     * @throws Exception
     */
    private static List<List<String>> readExcel (File file) throws Exception {

        // 创建输入流，读取Excel
        InputStream is = new FileInputStream(file.getAbsolutePath());
        // jxl提供的Workbook类
        Workbook wb = Workbook.getWorkbook(is);

        Sheet sheet = wb.getSheet(0);
        int rows = sheet.getRows();
        List<List<String>> allData = new ArrayList<List<String>>();
        // 前面4行不要
        for (int j = 4; j < rows; j++) {

            List<String> oneData = new ArrayList<String>();
            // 得到每一行的单元格的数据
            Cell[] cells = sheet.getRow(j);
            for (int k = 0; k < cells.length; k++) {

                oneData.add(cells[k].getContents().trim());
            }
            allData.add(oneData);

        }
        return allData;

    }


    /**
     * 处理数据
     */
    public static List<List<String>> dealData (List < List < String >> allData) {

        List<List<String>> result = new ArrayList<List<String>>();
        List<String> lmsgList = new ArrayList<String>();
        List<String> operList = new ArrayList<String>();

        for (int i = 0; i < allData.size(); i++) {

            List<String> oneDatai = allData.get(i);

            // LM_ID
            String id = getUUID();

            // 登记时间
            String createTime = oneDatai.get(1);
            // 姓名
            String username = oneDatai.get(2);
            if (username.contains("'")) {
                StringBuffer sb = new StringBuffer(username);
                sb.insert(username.indexOf("'"), "'");
                username = sb.toString();
            }
            // 联系电话
            String mobile = oneDatai.get(3);
            if (mobile.contains("'")) {
                StringBuffer sb = new StringBuffer(mobile);
                sb.insert(mobile.indexOf("'"), "'");
                mobile = sb.toString();
            }
            if (mobile.contains("\\")) {
                StringBuffer sb = new StringBuffer(mobile);
                sb.insert(mobile.indexOf("\\"), "\\");
                mobile = sb.toString();
            }
            // 内容
            String content = oneDatai.get(4);

            // 留言表插入SQL
            String leaveMsgSql = "INSERT INTO LM_LEAVE_MSG (ID, LM_SERIAL_NUM, LM_STATUS, LM_PRIORITY,\n" +
                    "        WORKGROUP, AGENT, CUSTOMER_ID, IP, FROM_PAGE, CHANNEL_NO, MOBILE, EMAIL, CONTENT, CREATE_TIME,\n" +
                    "        CLOSE_TIME, UPDATE_TIME, LAST_AGENT_REPLY_TIME, LAST_AGENT_REPLY, STRATEGY, USER_NAME, QQ, WECHAT, ENTERPRISE_ID, AREA, ENGINE, LM_FROM, SUBORG_ID,LM_TYPE)\n" +
                    "        VALUES(\n" +
                    "            '"+id+"', NEXTVAL('LM_ID'), '3', null, null, null, null, null, null\n" +
                    "            , null, '"+mobile+"', null, '"+content+"', '"+createTime+"', null, '"+createTime+"', null, null\n" +
                    "            , '1', '"+username+"', null, null, '{orgName}', null, null, null, '{suborgId}', '2' );";
            lmsgList.add(leaveMsgSql);

            // 操作记录ID
            String operId = getUUID();
            // 备注添加到操作记录表中
            String operContent = oneDatai.get(7);
            // 操作记录表插入SQL
            String operSql = "INSERT INTO LM_OPERATION(ID, LM_ID, OPERATOR, WORKGROUP_NAME, OPER_TYPE, OPER_CONTENT, OPER_TIME, HAS_FILES) \n" +
                    "VALUES( '"+operId+"', '"+id+"', '{operator}', '{workgroupName}', null, '<p>"+operContent+"</p>', null, '0' );";
            operList.add(operSql);
        }
        result.add(lmsgList);
        result.add(operList);

        return result;

    }


    /**
     * 将数据写入到excel中
     */
    public static void makeExcel (List < List < String >> result) {
        String msgFile = "C:/Users/YangChingyu-k/Desktop/留言SQL/20181001_1231_msg.sql";
        String operFile = "C:/Users/YangChingyu-k/Desktop/留言SQL/20181001_1231_oper.sql";
        String msgSql = "";
        String operSql = "";

        for (String s : result.get(0)) {
            msgSql += s + "\r\n";
        }

        for (String str : result.get(1)) {
            operSql += str + "\r\n";
        }

        try {
            // 写入文件
            File writeMsg = new File(msgFile);
            writeMsg.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writeMsg));
            out.write(msgSql);
            out.flush();
            out.close();
            System.out.print("--- 生成msgSql文件成功 ---\n");

            System.out.println("-----------------------\n");

            File writeOper = new File(operFile);
            writeOper.createNewFile(); // 创建新文件
            BufferedWriter outOper = new BufferedWriter(new FileWriter(writeOper));
            outOper.write(operSql);
            outOper.flush();
            outOper.close();
            System.out.print("--- 生成operSql文件成功 ---\n");

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 生成ID
     * @return
     */
    public static String getUUID() {
        return "" + UUID.randomUUID();
    }

}
