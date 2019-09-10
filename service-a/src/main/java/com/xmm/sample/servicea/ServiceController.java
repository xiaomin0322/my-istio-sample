package com.xmm.sample.servicea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by simon (simon.meng@fox.mal.com) on 17/04/2018.
 */
@RestController
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.b.url}")
    private String url;

    @Value("${app.version}")
    private String version;
    
	// 全局的trace对象
	@Autowired
	Tracer tracer;

    @GetMapping("/info")
    public String info() {
    	System.out.println("tracer:"+tracer);
    	addSpan();
        String rsp = "A Service version = " + this.version + "  ===> " + notify(url);
        log.info(rsp);
        return rsp;
    }
    
    public void addSpan() {
    	Span currentSpan = tracer.getCurrentSpan();
		if (currentSpan == null) {
			return;
		}
		currentSpan.tag("http.method", "zzm");
		currentSpan.tag("pod.name", "asdasd");
    }

    public String notify(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            return e.getMessage();
        }


    }
}
