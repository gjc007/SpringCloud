package com.gjc.springcloud.Service.impl;

import com.gjc.springcloud.Service.DeptService;
import com.gjc.springcloud.dao.DeptDao;
import com.gjc.springcloud.entities.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    @Override
    public boolean add(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept get(Long id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> list() {
        return deptDao.findAll();
    }
}
