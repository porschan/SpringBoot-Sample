package com.chanchifeng.data.common.vo;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T>  implements Serializable {

    private int currentPage;
    private int totalPage;
    private List<T> data;
    private long totalElements;

}
