package com.quickstart.controller;

import com.quickstart.domain.ResultInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController {
    @RequestMapping(value = "/error/{code}")
    public ResultInfo error(@PathVariable String code) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(false);
        resultInfo.setErrorMsg(code);
        return resultInfo;
    }
}
