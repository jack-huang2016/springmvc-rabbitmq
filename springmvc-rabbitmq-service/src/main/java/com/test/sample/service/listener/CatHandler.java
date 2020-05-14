/**
 * FileName: PigHandler
 * Author:   huang.yj
 * Date:     2020/5/14 13:41
 * Description: 消费者1
 */
package com.test.sample.service.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;

/**
 * 〈消费者2〉
 *
 * @author huang.yj
 * @create 2020/5/14
 * @since 1.0.0
 */
public class CatHandler implements MessageListener { //手动回复实现ChannelAwareMessageListener监听接口 。自动回复，可实现MessageListener监听接口

    private static final ObjectMapper mapper = new ObjectMapper();

    public void onMessage(Message message) {
        try {
            //使用jackson解析，message就是rabbitmq传来的消息
            JsonNode jsonData = mapper.readTree(message.getBody());
            System.out.println("我是消费者2,我的id是" + jsonData.get("id").asText()
                    + ",我的名字是" + jsonData.get("name").asText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}