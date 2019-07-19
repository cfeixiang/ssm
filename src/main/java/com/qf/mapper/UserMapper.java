package com.qf.mapper;

import com.qf.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * Administrator 2019/7/15 0015 10:56
 */

public interface UserMapper {
    Integer findCountByUsername(@Param("username")String username);

    Integer save(User user);

    User findByUsername(@Param("username")String username);
}
