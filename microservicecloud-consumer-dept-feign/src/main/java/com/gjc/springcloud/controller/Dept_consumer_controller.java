package com.gjc.springcloud.controller;

import com.gjc.springcloud.Service.DeptClientService;
import com.gjc.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class Dept_consumer_controller {
    @Autowired
    private DeptClientService deptClientService;

    @GetMapping("/consumer/dept/add")
    public Boolean add(Dept dept){
        return this.deptClientService.add(dept);
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){

        return this.deptClientService.get(id);
    }
    @RequestMapping("consumer/dept/list")
    public List<Dept> list(){
        return this.deptClientService.list();
    }

}
