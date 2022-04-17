package com.cqupt.domin.queryvo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaperuserVO {
    //接收多个id  这里用作paperuser和loginfo的批量删除
    private Integer[] ids;
}
