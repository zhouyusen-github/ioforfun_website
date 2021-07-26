package com.quickstart.domain;

import lombok.Data;

/**
 * return stats about request
 */
@Data
public class ResultInfo {
    private boolean flag; // true mean success, false mean fail
    private Object data; // result data
    private String errorMsg;
}
