package com.fanzhou.service;

import com.fanzhou.domain.User;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author fanzhou
 * @create 2020/8/17 0017
 */
public interface ExportService {
    void exportExcel(List<User> userList) throws FileNotFoundException, Exception;
}
