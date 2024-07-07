package com.dearwang.store01.controller;


import com.dearwang.store01.entity.User;
import com.dearwang.store01.service.IUserService;
import com.dearwang.store01.service.ex.InsertException;
import com.dearwang.store01.service.ex.UsernameDuplicatedException;
import com.dearwang.store01.service.ex.avatar.*;
import com.dearwang.store01.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//接收数据方式：请求处理方法设置为pojo类型来接受前端的数据，springboot会将前端的url地址将pojo类中的属性进行比较
//2.请求数据类型参数列表设置为非pojo类型，如string类型，springboot会直接将请求的参数名和方法的参数名进行比较
//@Controller与@requestbody混合为下面这个注解
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;
    /*定义一个常量用来设置上传文件的最大值10MB*/
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /*定义一个常量来限制上传文件的类型*/
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    /*静态数组*/
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/tif");
        AVATAR_TYPE.add("image/webp");
    }


//此处请求可获取数据太少，复用性太低
    /*@RequestMapping("getUid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK, data);
    }*/

    //    该请求保存了用户的部分重要数据，后续可以通过添加user获取的对象来增加所需数据
    @RequestMapping("queryUser")
    public JsonResult<User> queryUser(HttpSession session) {
        Integer uid = getUidFromSession(session);
        User data = userService.getByUid(uid);
        //        定义一个新的数据对象来存放所需要的数据
        User user = new User();
        user.setUid(data.getUid());
        user.setUsername(data.getUsername());
        user.setGender(data.getGender());
        user.setPhone(data.getPhone());
        user.setEmail(data.getEmail());
        user.setAvatar(data.getAvatar());
        return new JsonResult<>(OK, user);
    }

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
//        创建响应结果对象
        userService.reg(user);
        return new JsonResult<Void>(OK);

    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        /*向session对象完成全局的数据绑定*/
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        /*获取绑定的数据*/
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK, data);
    }

    //这里使用根据UID来修改对应的pwd，我开始用的post请求，后更改为request请求，应该这里使用get请求也是可以的
    @RequestMapping("update_pwd")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword,
                                           HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("update_user")
    public JsonResult<Void> changeInfo(HttpSession session, User user) {

        /*实际编程中需注意实体类对象是否与前端对象相同*/
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }


    /**
     * 此处使用请求时，前后端使用的请求若是get，提交数据时最大为2kb，因此建议使用post请求
     *
     * @param session 已经封装的uid和username数据
     * @param file    springMVC封装的接口MultipartFile用于获取文件类型的数据接口，
     *                用于存放访问某个文件的数据包，文件类型为(".value").
     *                而SpringBoot则是会自动识别该接口，并将文件数据数值给该接口定义的参数中
     * @return 返回状态码和用户头像路径
     * //@RequestParam("value") 该注解用于解决前端字段名与后端对象名不相同时进行强行绑定
     */
    @RequestMapping("upload_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
//        判断文件是否为空
        if (file.isEmpty()) {//获取文件是否为空
            throw new FileEmptyException("文件为空");
        }
//        判断文件是否超出大小
        if (file.getSize() > AVATAR_MAX_SIZE) {//获取文件大小
            throw new FileSizeException("文件大小超出限制");
        }
//        判断文件类型是否符合规则
        String contentType = file.getContentType();//获取文件类型
        if (!AVATAR_TYPE.contains(contentType)) {//contains表示当前对象中是否包含对应文件类型
            throw new FileTypeException("文件类型不符合规则");
        }
//        将上传的文件保存至一个规定的文件夹下.../upload/value.value
//        getRealPath根据上下文查找文件目录
//        getServletContext用于管理SpringMVC应用管理以及操作
        String parent = session.getServletContext().getRealPath("upload");
//        IO流     File对象指向parent路径，并确定File路径是否存在
        File dir = new File(parent);
        if (!dir.exists()) {//exists判断是否存在
            dir.mkdirs();//mkdirs创建多级目录
        }
//        获取上传文件名称，使用UUID工具生成新的文件名，防止文件名重复被覆盖
//        getOriginalFilename返回文件名.文件类型
        String oldFileName = file.getOriginalFilename();
        int index = oldFileName.lastIndexOf(".");//查找最后一个"."存在的位置,并记录保存
        String suffix = oldFileName.substring(index);//从index位置来截取文件类型，index之前的字段全部截断
//        String newFileName = UUID.randomUUID().toString().toUpperCase() + suffix;
        String newFileName = UUID.randomUUID().toString() + suffix;
        File createFile = new File(dir, newFileName);//表示在dir目录下创建名为newFileName的空文件
//        写入空文件
        try {
            file.transferTo(createFile);//transferTo将file文件中的数据写入到createFile的文件中
        } catch (FileStateException e) {
            throw new FileStateException("文件状态出现错误");
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写时出现错误");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
//        返回头像的路径/upload/value.value
        String avatar =dir+"/"+ newFileName;
//        这里有个错误，之前传送过去的是parent这会导致后台只有路径名没有图片名
        userService.changeAvatar(uid, avatar, username);
//        返回用户头像路径
        return new JsonResult<>(OK, avatar);
    }
}