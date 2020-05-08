package com.gjc.springcloud.Controller;


import com.gjc.springcloud.Service.DeptService;
import com.gjc.springcloud.entities.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Qualifier("discoveryClient")
    @Autowired
    private DiscoveryClient client;

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }
    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrix_get")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = this.deptService.get(id);
        if (null == dept) {
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }
        return deptService.get(id);
    }
    @GetMapping("/dept/list")
    public List<Dept> list(){
        return deptService.list();
    }
    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery()
    {
        List<String> list = client.getServices();
        System.out.println("**********" + list);

        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }
    public Dept hystrix_get(@PathVariable("id") Long id)
    {
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

}
