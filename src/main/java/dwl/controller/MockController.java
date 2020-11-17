package dwl.controller;

import dwl.plugins.BeanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenlong.ding
 * @date 2020/11/17 18:56
 */
@RestController
@RequestMapping("/mock")
public class MockController extends BeanRepository {

    /**
     * 触发任务
     */
    @GetMapping("/trigger/task/xiaohua")
    public Object triggerXHTask(){
        xhTask.autoGetXiaoHua();
        return "success";
    }

}
