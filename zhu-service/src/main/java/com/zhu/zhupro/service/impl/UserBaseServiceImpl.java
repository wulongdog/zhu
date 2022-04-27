package com.zhu.zhupro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.zhupro.dto.UserDto;
import com.zhu.zhupro.dto.mapper.UserToMapper;
import com.zhu.zhupro.mapper.UserBaseMapper;
import com.zhu.zhupro.pojo.UserBase;
import com.zhu.zhupro.service.UserBaseService;
import com.zhu.zhupro.utils.encryption.Md5encryption;
import com.zhu.zhupro.utils.fileutils.FileUtils;
import com.zhu.zhupro.utils.pathutils.UserUrlPath;
import com.zhu.zhupro.vo.invo.UserBaseVo;
import com.zhu.zhupro.vo.invo.UserLoginVo;
import com.zhu.zhupro.vo.invo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserBaseServiceImpl implements UserBaseService {

    @Autowired
    private UserBaseMapper userBaseMapper;
    @Autowired
    private UserToMapper userToMapper;

    @Override
    public String Register(UserRegisterVo Register) {
        QueryWrapper<UserBase> userBaseQueryWrapper = new QueryWrapper<>();
        userBaseQueryWrapper.eq("email", Register.getEmail());
        try {
            if(userBaseMapper.selectOne(userBaseQueryWrapper)!=null){
                return "该用户已注册!";
            }
            UserBase userBase = new UserBase();
            userBase.setEmail(Register.getEmail());
            String password = Md5encryption.md5(Register.getPassword(), Register.getEmail());
            userBase.setPassword(password);
            userBase.setGender(Register.getGender());
            userBaseMapper.insert(userBase);
            String s = UserUrlPath.setUserUrl(userBase.getId());
            userBase.setProfileUrl(s);
            userBaseMapper.updateById(userBase);
        }catch (Exception e) {
            return e.getMessage();
        }
        return "注册成功!请登录!";
    }

    @Override
    public Map<String,Object> Login(UserLoginVo userLoginVo) throws Exception {
        QueryWrapper<UserBase> userBaseQueryWrapper = new QueryWrapper<>();
        userBaseQueryWrapper.eq("email", userLoginVo.getEmail());
        Map<String,Object> res = new HashMap<String,Object>();
        UserBase userBase = userBaseMapper.selectOne(userBaseQueryWrapper);
        if ( userBase!= null) {
            if(Md5encryption.verify(userLoginVo.getPassword(), userLoginVo.getEmail(),userBase.getPassword())){
                res.put("msg", "登录成功!");
                res.put("user",userBase);
                return res;
            }
            res.put("msg","密码错误!");
            return res;
        }
        res.put("msg","该用户还没有注册!请先进行注册!");
        return res;
    }

    @Override
    public String modifyInfo(UserBaseVo userBaseVo){
        try {
            UserBase userBase = userBaseVo.getUserBase();
            userBaseMapper.updateById(userBase);
            return "修改成功!";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String getBasePath(){
        //获取jar包所在目录
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
        String dirPath = jarF.getParentFile().toString()+"/upload/";
        return dirPath;
    }

    @Override
    public boolean photoFileReset(MultipartFile file,UserBase userBase,Integer type) throws IOException {
        String basePath = getBasePath();
        String s = FileUtils.setPhotoPath(file, basePath);
        if(type==1) {
            userBase.setPhoto(s);
        }else {
            userBase.setBackground(s);
        }
        userBaseMapper.updateById(userBase);
        return true;
    }

    @Override
    public UserDto getUserHome(Integer id){
        UserBase userBase = userBaseMapper.selectById(id);
        UserDto userDto = userToMapper.toDto(userBase);
        return userDto;
    }

}
