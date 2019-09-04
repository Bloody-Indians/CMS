package com.bawag.cms.auth.controller;


import com.bawag.cms.auth.model.User;
import com.bawag.cms.auth.service.ComplaintsServiceImpl;
import com.bawag.cms.auth.service.UserServiceImpl;
import com.bawag.cms.jwt.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
class LoginController {
    private static final String USERNAME_FIELD = "username";
    @Autowired
    ComplaintsServiceImpl complaintsService;
    @Autowired
    private JwtUserDetailsService loginService;
    @Autowired
    UserServiceImpl userService;

    @Autowired
    private DiscoveryClient discoveryClient;

    //login for ref
    @ResponseBody
    @RequestMapping(value = "/admin/login", produces = {"application/json"}, consumes = {"application/x-www-form-urlencoded"},
            method = {RequestMethod.POST})
    public ResponseEntity doLogin(@RequestParam(value = USERNAME_FIELD, defaultValue = "", required = false) String username,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestHeader HttpHeaders headers) {
        System.out.println("username  :" + username);
        UserDetails userDetails = loginService.loadUserByUsername(username);
        System.out.println("userDetails  :" + userDetails);
        return ResponseEntity.ok().body(userDetails);

    }

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/user/info")
    public ResponseEntity userInfo(@RequestBody User createUser) {

        if (null != createUser.getUserEmail()) {
            if (!createUser.getUserEmail().isEmpty()) {
                User userInfo = userService.userInfo(createUser);
                if (null != userInfo)
                    return ResponseEntity.ok().body(userInfo);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }


//service instanace testing purpose starts

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/serviceInstances/")
    public List<String> serviceInstances() {
        return this.discoveryClient.getServices();
    }

    //service instanace testing purpose ends
}