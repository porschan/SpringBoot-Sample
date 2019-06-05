package com.chanchifeng.data.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO implements Serializable {

    private Boolean state;
    private String result;

}
