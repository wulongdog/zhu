package com.zhu.zhupro.service.impl;

import com.zhu.zhupro.mapper.IssueBaseMapper;
import com.zhu.zhupro.mapper.IssueSubscriptionMapper;
import com.zhu.zhupro.service.IssueBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueBaseServiceImpl implements IssueBaseService {

    @Autowired
    private IssueBaseMapper issueBaseMapper;
    @Autowired
    private IssueSubscriptionMapper issueSubscriptionMapper;



}
