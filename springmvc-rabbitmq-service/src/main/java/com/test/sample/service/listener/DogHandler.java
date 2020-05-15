/**
 * FileName: PigHandler
 * Author:   huang.yj
 * Date:     2020/5/14 13:41
 * Description: 消费者1
 */
package com.test.sample.service.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 〈消费者3〉
 *
 * @author huang.yj
 * @create 2020/5/14
 * @since 1.0.0
 */
public class DogHandler { //也可不实现ChannelAwareMessageListener监听接口或者MessageListener监听接口，但是<rabbit:listener ref>标签必须加上method属性指定监听的方法。

    private static final ObjectMapper mapper = new ObjectMapper();

    public void listen(byte[] messageBody) {
        try {
            JsonNode jsonData = mapper.readTree(messageBody);
            System.out.println("我是消费者3,我的名字是" + jsonData.get("name").asText());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}