package com.qf.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Administrator 2019/7/15 0015 14:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {
    private Integer code;
    private String msg;
    private Object data;
}
