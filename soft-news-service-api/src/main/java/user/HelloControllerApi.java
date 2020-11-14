package user;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * HelloControllerApi接口
 * @author ysx
 */

public interface HelloControllerApi {

    /**
     * hello接口
     *
     * @return Object
     */
    @GetMapping("/hello")
    Object hello();
}
