package com.soft1851.user.mapper;

import com.soft1851.pojo.AppUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AppUserMapper extends Mapper<AppUser> {
}