package cc.stan.example;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseRowModel {

    @ExcelProperty(value = {"用户名"}, index = 0)
    protected String id;

    @ExcelProperty(value = {"年龄"}, index = 1)
    private Integer age;

    @ExcelProperty(value = {"邮箱"}, index = 2)
    private String email;

}
