package com.soft1851.user.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.user.UserControllerApi;
import com.soft1851.pojo.AppUser;
import com.soft1851.pojo.bo.UpdateUserInfoBO;
import com.soft1851.pojo.vo.AppUserVO;
import com.soft1851.pojo.vo.UserAccountInfoVo;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.user.service.UserService;
import com.soft1851.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController extends BaseController implements UserControllerApi {

    private final AppUserMapper appUserMapper;
    private final UserService userService;

    @Override
    public GraceResult getAllUsers() {
        return GraceResult.ok(appUserMapper.selectAll());
    }


    @Override
    public GraceResult getUserInfo(String userId) {
//        Example userExample = new Example(AppUser.class);
//        Example.Criteria criteria = userExample.createCriteria();
//        criteria.andEqualTo("id",userId);
//        AppUser user = appUserMapper.selectOneByExample(userExample);
//        return GraceResult.ok(user);

        // 判断不能为空
        if (StringUtils.isBlank(userId)) {
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
        // 1 根据userId查询用户，调用内部封装方法(复用，扩展方便)
        AppUser user = getUser(userId);
        // 2 设置VO--需要展示的信息
        UserAccountInfoVo accountVo = new UserAccountInfoVo();
        // 3 拷贝属性
        BeanUtils.copyProperties(user, accountVo);
        return GraceResult.ok(accountVo);
    }

    @Override
    public GraceResult updateUserInfo(@Valid UpdateUserInfoBO updateUserInfoBO, BindingResult result) {
        // 判断BindingResult是否保存错误的验证信息，如果有 直接return
        if (result.hasErrors()) {
            Map<String, String> errorMap = getErrors(result);
            return GraceResult.errorMap(errorMap);
        }
        // 执行更新用户信息操作
        userService.updateUserInfo(updateUserInfoBO);
        return GraceResult.ok();
    }

    @Override
    public GraceResult getUserBasicInfo(String userId) {
        //1.判断不能为空
        if (StringUtils.isBlank(userId)){
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
        //2.查询userId
        AppUser user = getUser(userId);
        //3.信息脱敏，设置不显示
        AppUserVO userVO = new AppUserVO();
        BeanUtils.copyProperties(user,userVO);
        return GraceResult.ok(userVO);
    }

    private AppUser getUser(String userId) {
        // 1 查询redis中是否包含用户信息，如果包含侧查询redis返回，如果不包含侧查询数据库
        String userJson = redis.get(REDIS_USER_INFO + ":" + userId);
        AppUser user;
        if (StringUtils.isNotBlank(userJson)) {
            user = JsonUtil.jsonToPojo(userJson,AppUser.class);
        }else {
            user = userService.getUser(userId);
            // 2 由于用户信息不怎么会变动，这类信息数据不会去查询数据库，完全可以把用户信息存入到redis中
            // 哪怕修改休息，也不会立马体现，这也是弱一致性，在这里有过期事件，到时间用户信息会更新到页面显示
            // 基本信息是属于数据一致性优先级比较低的，用户眼里看的主要以内容为主，至于文章是谁发的，一般来说不会过多的关注
            redis.set(REDIS_USER_INFO + ":" + userId, JsonUtil.objectToJson(user),1);
        }
        return user;
    }
}