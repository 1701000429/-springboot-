package com.cqupt.domin.queryvo;

import com.cqupt.domin.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PaperTypeNumberVo {
    //对应的类型名 和  数量
    List<Type> typeList;
    List<Integer> paperNumber;
    List<String> typeNameList;
}
