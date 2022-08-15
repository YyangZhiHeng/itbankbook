package com.swjd.controller;

import com.mysql.jdbc.StringUtils;
import com.swjd.pojo.Customer;
import com.swjd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan("com.swjd.mapper")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //用户登录
    @PostMapping("/toLogin")
    public Integer toLogin(@RequestBody Customer customer){
        System.out.println(customer);
        String username = customer.getUsername();
        String password = customer.getPassword();
        //账号密码不能为空
        if (StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(password)){
            System.out.println("0");
                return 0;
        }
        //账号不存在
        Customer customer1 = customerService.query().eq("username", username).one();
        //账号错误，用户不存在
        if (customer1==null){
            System.out.println("1");
                return 1;
        }
        //密码错误
        if (!customer1.getPassword().equals(password)){
            System.out.println("2");
            return 2;
        }
        //登录成功
        if (customer1.getUsername().equals(username)&&customer1.getPassword().equals(password)) {
            System.out.println("3");
                return 3;
        }
        System.out.println("4");
        return 4;
    }

    //管理员页面添加用户
    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        System.out.println(customer);
    }
    //查询所有用户信息
    @RequestMapping("/findCustomerAll")
    public List<Customer> findUserAll(){
        return customerService.list();
    }

    //注册用户
    @PostMapping ("/toRegister")
    public void registerCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        String username=customer.getUsername();
        String password=customer.getPassword();

        //账号密码不为空注册成功
        if (StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(password)){
            throw new RuntimeException("账号或密码为空");
        }
        customerService.save(customer);
    }

    //管理员根据Id删除用户信息
    @DeleteMapping("/deleteCustomerById/{id}")
    public void deleteCustomerById(@PathVariable("id") Integer id){
        customerService.removeById(id);
    }

    //管理员页面修改用户信息
    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestBody Customer customer){
        customerService.updateById(customer);
    }
}
