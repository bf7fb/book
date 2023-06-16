package com.wangzhen.mapper;

import com.wangzhen.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author wz
 * @ClassName UserMapper
 * @date 2023/6/12 17:19
 * @Description TODO
 */
public interface UserMapper {
    @Insert("insert into tb_user(username,password,name) values(#{username},#{password},#{name})")
    void register(User user);

    @Select("select * from tb_user where username = #{username}")
    User queryUsername(String username);

    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User login(User user);

    @Select("")
    User queryUserInfo();
}
