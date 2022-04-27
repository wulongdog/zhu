package com.zhu.zhupro.controller;

import com.zhu.zhupro.service.IssueBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueBaseService issueBaseService;


}
