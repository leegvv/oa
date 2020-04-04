package net.arver.oa.controller;

import net.arver.oa.vo.ServiceResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("hello")
    public Object hello() {
        return new ServiceResult<>("Hello OA !");
    }
}
