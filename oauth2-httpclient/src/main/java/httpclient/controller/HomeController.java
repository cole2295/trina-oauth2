package httpclient.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by zhm on 16-9-12.
 */
@RestController
public class HomeController {
    @RequestMapping("/login")
    public String home(String code, String state, HttpServletRequest request) throws IOException {
        String ce = "trina_app:5788dc4f70f00b2586a19c95";
        HttpResponse response = HttpRequest
                .post("http://localhost:8090/oauthserver/oauth/token")
                .form("code",code)
                .form("grant_type","authorization_code")
                .form("redirect_uri","http://localhost:9099/login")
                .header("Authorization","Basic "+ Base64Utils.encodeToString(ce.getBytes()))
                .send();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(response.body());
        System.out.println(json.get("access_token").toString());
        HttpResponse response2 = HttpRequest
                .post("http://localhost:8090/oauthserver/me")
                .form("access_token",json.get("access_token").toString().replaceAll("\"",""))
                .send();
        return response2.bodyText();
    }
}
