package com.fanzhou.dao;

import com.fanzhou.domain.User;

import java.util.List;

/**
 * @author fengdeguisu0808
 * @date 2020/7/9 22:46
 */
public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();
}
