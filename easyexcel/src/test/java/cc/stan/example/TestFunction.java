package cc.stan.example;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFunction {

    @Test
    public void writeExcel1() throws IOException {
        OutputStream out = new FileOutputStream("e-2007.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);

        Sheet sheet1 = new Sheet(1, 1);
        sheet1.setSheetName("测试");

        sheet1.setHead(Arrays.asList(
                Arrays.asList("name"),
                Arrays.asList("age"),
                Arrays.asList("email")));

        List<List<Object>> data = new ArrayList<>();
        List<Object> o = new ArrayList<>();
        o.add("小李");
        o.add("12");
        o.add("aaa@aa.com");
        data.add(o);

        o = new ArrayList<>();
        o.add("小小");
        o.add("13");
        o.add("bb@aa.com");
        data.add(o);

        writer.write1(data, sheet1);
        writer.finish();
        out.close();

    }

    @Test
    public void writeExcel2() throws IOException {
        OutputStream outputStream = new FileOutputStream("e2.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);

        Sheet sheet1 = new Sheet(1, 1, User.class, "使用model", null);

        List<User> data = Arrays.asList(
                User.builder().id("li").age(10).email("a@a.com").build(),
                User.builder().id("xiao").age(12).email("b@a.com").build()
        );

        writer.write(data, sheet1);
        writer.finish();
        outputStream.close();
    }

}
