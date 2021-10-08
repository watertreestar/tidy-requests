package com.github.watertreestar.requests;

import org.junit.Test;

public class RequestsTest {

    @Test
    public void test() {
        ParsedResponse response = Requests.get("https://baidu.com").send().toTextResponse();
        System.out.println(response.statusCode());
        System.out.println(response.content());
    }
}
