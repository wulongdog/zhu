package com.zhu.zhupro.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zhu.zhupro.constant.Code;
import com.zhu.zhupro.dto.UserDto;
import com.zhu.zhupro.pojo.UserBase;
import com.zhu.zhupro.service.UserBaseService;
import com.zhu.zhupro.utils.captcha.CreateCaptcha;
import com.zhu.zhupro.utils.tokencreate.CreateToken;
import com.zhu.zhupro.vo.invo.UserBaseVo;
import com.zhu.zhupro.vo.invo.UserLoginVo;
import com.zhu.zhupro.vo.invo.UserRegisterVo;
import com.zhu.zhupro.vo.outvo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBaseService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    private CreateCaptcha createCaptcha = new CreateCaptcha();

    @RequestMapping("/captcha")
    public void captcha(@RequestParam(value = "email") String email, HttpServletRequest request, HttpServletResponse response){
        try {
            DefaultKaptcha dk = createCaptcha.getCaptcha();
            String text = dk.createText();
            BufferedImage img = dk.createImage(text);
            redisTemplate.opsForValue().set(email+"code",text,2, TimeUnit.MINUTES);
            response.setContentType("image/jpeg");
            ImageIO.write(img, "jpg", response.getOutputStream());
        }catch (Exception e){
        }
    }

    @RequestMapping("/register")
    public ResultVo register(@RequestBody UserRegisterVo userLoginVo, HttpServletRequest request){
        Object user = redisTemplate.opsForValue().get(userLoginVo.getEmail() + "code");
        if(user!=null){
            String code = user.toString();
            if(!userLoginVo.getCaptcha().equals(code)){
                return new ResultVo(false,Code.SAVE_ERROR,null,"验证码错误");
            }
            try {
                String msg = userService.Register(userLoginVo);
                return new ResultVo(true, Code.SAVE_OK,null,msg);
            }catch (Exception e){
                return new ResultVo(false,Code.SAVE_ERROR,null,e.getMessage());
            }
        }else {
            return new ResultVo(false,Code.SAVE_ERROR,null,"请先获取验证码!");
        }

    }

    @RequestMapping("/login")
    public ResultVo login(@RequestBody UserLoginVo userLoginVo, HttpServletRequest request,HttpServletResponse  response){
        String token = CreateToken.buildJWT(userLoginVo.getEmail());

        response.setHeader("token",token);

        try {
            Map<String, Object> login = userService.Login(userLoginVo);
            String msg = (String) login.get("msg");
            if(msg.equals("登录成功!")){
                UserBase user = (UserBase) login.get("user");
                redisTemplate.opsForValue().set(token,user,1,TimeUnit.HOURS);
                return new ResultVo(true, Code.QUERY_OK,user,msg);
            }
            return new ResultVo(true, Code.QUERY_OK,null,msg);
        }catch (Exception e){
            return new ResultVo(false,Code.SAVE_ERROR,null,e.getMessage());
        }
    }

    @RequestMapping("/modifyinfo")
    public ResultVo modifyInfo(@RequestBody UserBaseVo userBaseVo){
        try {
            String msg = userService.modifyInfo(userBaseVo);
            return new ResultVo(true, Code.UPDATE_OK,null,msg);
        }catch (Exception e){
            return new ResultVo(false,Code.UPDATE_ERROR,null,e.getMessage());
        }
    }

    @RequestMapping("/photo")
    public ResultVo changePhoto(@RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(value = "type", required = false) Integer type,HttpServletRequest request){
        String token = request.getHeader("token");
        Object o = redisTemplate.opsForValue().get(token);
        UserBase user = (UserBase) o;
        try {
            if(userService.photoFileReset(file,user,type)){
                redisTemplate.opsForValue().set(token,user);
                return new ResultVo(true,Code.SAVE_OK,user,"修改成功!");
            }else {
                return new ResultVo(true,Code.SAVE_OK,user,"出现了未知错误!");
            }
        }catch (Exception e){
            return new ResultVo(false,Code.SAVE_ERROR,null,e.getMessage());
        }
    }

    //个人页面
    @RequestMapping("/user{id}-home")
    public ResultVo personHome(@PathVariable Integer id){
        try {
            UserDto userHome = userService.getUserHome(id);
            return new ResultVo(true,Code.SAVE_OK,userHome,"获取成功");
        }catch (Exception e){
            return new ResultVo(false,Code.QUERT_ERROR,null,e.getMessage());
        }
    }

}
