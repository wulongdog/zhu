package com.zhu.zhupro.service;

import com.zhu.zhupro.dto.UserDto;
import com.zhu.zhupro.pojo.UserBase;
import com.zhu.zhupro.vo.invo.UserBaseVo;
import com.zhu.zhupro.vo.invo.UserLoginVo;
import com.zhu.zhupro.vo.invo.UserRegisterVo;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

//@Transactional(readOnly = true)
public interface UserBaseService {

    String Register(UserRegisterVo userRegisterVo) throws Exception;

    Map<String,Object> Login(UserLoginVo userLoginVo) throws Exception;

    String modifyInfo(UserBaseVo userBaseVo);

    boolean photoFileReset(MultipartFile file, UserBase userBase,Integer type) throws IOException;

    UserDto getUserHome(Integer id);
}
