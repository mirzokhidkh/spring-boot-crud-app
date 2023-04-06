package uz.mk.springbootcrudapp.util.testForValiTest;

import fido.valitest.RequestData;
import fido.valitest.TestMethod;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ValiTest {
    public static void checkValiTest(HttpServletRequest request, JSONObject requestBody, String requestId) {
        StringBuffer requestURL = request.getRequestURL();
//        System.out.println("request.getScheme()==>" + request.getScheme());
//        System.out.println("request.getServerName()==>" + request.getServerName());
//        System.out.println("request.getServerPort()==>" + request.getServerPort());
//        System.out.println("request.getContextPath()==>" + request.getContextPath());
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        String swaggerJsonAddress = baseUrl + "/v3/api-docs";
//        String baseUrl = request.getHeader("Origin");
        String endPointUrl = request.getRequestURI();
        String methodType = request.getMethod();
        String bearerToken = request.getHeader("Authorization");

        //        String requestId = request.getHeader("requestId");
//        System.out.println("getRequestURL " + request.getRequestURL());
//        System.out.println("getPathInfo " + request.getPathInfo());
//        System.out.println("Authorization  ===> " + bearerToken);
//        System.out.println("requestId  ===> " + request.getHeader("requestId"));
//        Map<String, String> headers = getAllHeaders(request);
//        System.out.println(headers);

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("requestId", requestId);
        headersMap.put("Authorization", bearerToken);


        System.out.println("swaggerJsonAddress  ===> " + swaggerJsonAddress);
        System.out.println("endPointUrl  ===> " + endPointUrl);
        System.out.println("correctBodyJson ===> " + requestBody.toString());


        System.out.println("url  ===> " + requestURL);
        System.out.println("method  ===> " + methodType);
        System.out.println("headersMap  ===> " + headersMap);

        RequestData requestData = new RequestData(requestURL.toString(), methodType, headersMap, new HashMap<>());

        TestMethod testMethod = new TestMethod(swaggerJsonAddress, endPointUrl, requestBody.toString(), requestData);

        boolean b = testMethod.bodyCheck();
        System.out.println(b);

        System.out.println(testMethod.doTest(new ParserClassExample()));
    }

    public Map<String, String> getAllHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();
        Collections.list(request.getHeaderNames()).forEach(headerName -> headersMap.put(headerName, request.getHeader(headerName)));
        return headersMap;
    }

}
