package com.imooc.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.StringEntity;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by Administrator on 2017/12/20.
 * 这里写假数据处理逻辑
 * 这里写假数据处理逻辑
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        configureFor(8082);//配置WireMock服务器端口
        removeAllMappings();//清空以前的配置

        mock("order/1", "01");


    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        //读取文件（假数据）内容作为返回的内容
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));

    }
}
