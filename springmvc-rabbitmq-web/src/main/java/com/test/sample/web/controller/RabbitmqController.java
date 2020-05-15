/**
 * FileName: RabbitmqController
 * Author:   huang.yj
 * Date:     2020/5/14 17:50
 * Description:
 */
package com.test.sample.web.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author huang.yj
 * @create 2020/5/14
 * @since 1.0.0
 */

@Controller
@RequestMapping("/rabbit")
public class RabbitmqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *@描述 路由模式测试
     */
    @RequestMapping(value="routing", method= RequestMethod.GET)
    public void testRouting(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        map.put("name", "pig");
        //根据key发送到对应的队列
        rabbitTemplate.convertAndSend("que_pig_key", map);

        map.put("id", "2");
        map.put("name", "cat");
        //根据key发送到对应的队列
        rabbitTemplate.convertAndSend("que_cat_key", map);
    }

    /**
     *@描述 主题模式测试
     */
    @RequestMapping(value="topic", method= RequestMethod.GET)
    public void testTopic(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        map.put("name", "pigAdd");
        //匹配pig.#的key 都会发送到 que_pig队列
        rabbitTemplate.convertAndSend("pig.add", map);
        map.put("name", "pigUpdate");
        rabbitTemplate.convertAndSend("pig.update", map);
        map.put("name", "pigDelete");
        rabbitTemplate.convertAndSend("pig.delete", map);


        map.put("id", "2");
        map.put("name", "catAdd");
        //匹配cat.#的key 都会发送到 que_cat队列
        rabbitTemplate.convertAndSend("cat.add", map);
        map.put("name", "catUpdate");
        rabbitTemplate.convertAndSend("cat.update", map);
        map.put("name", "catDelete");
        rabbitTemplate.convertAndSend("cat.delete", map);
    }

    /**
     * @描述 订阅模式测试
     */
    @RequestMapping(value="publish", method = RequestMethod.GET)
    public void testPublish() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "小红");
        rabbitTemplate.convertAndSend(map);
    }
}