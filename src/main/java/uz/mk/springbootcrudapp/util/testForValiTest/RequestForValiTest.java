package uz.mk.springbootcrudapp.util.testForValiTest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//@AllArgsConstructor
public class RequestForValiTest {
    private static Map<String, String> headers ;
    private static String methodType;
    private static Map<String, String> params;
    private static String url;

    public RequestForValiTest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        StringBuffer requestURL = request.getRequestURL();
        String baseUrl = request.getHeader("Origin");
        String endPointUrl = request.getRequestURI();
        String methodType = request.getMethod();
        String authorization = request.getHeader("Authorization");
//        String requestId = request.getHeader("requestId");
//        System.out.println("getRequestURL " + request.getRequestURL());
        System.out.println("FULL URL " + requestURL);
        System.out.println("Origin " + baseUrl);
        System.out.println("endPointUrl " + endPointUrl);
//        System.out.println("getPathInfo " + request.getPathInfo());
        System.out.println("methodType " + methodType);
        System.out.println("Authorization " + authorization);
        System.out.println("requestId " + request.getHeader("requestId"));
        Map<String, String> headers = getAllHeaders(request);
        System.out.println(headers);
//        RequestForValiTest requestForValiTest = new RequestForValiTest(
//        getAllHeaders(request),
//        methodType,
//        new HashMap<>(),
//        requestURL.toString());

//
        this.headers = getAllHeaders(request);
        this.methodType = methodType;
        this.params = new HashMap<>();
        this.url = requestURL.toString();
    }


    public Map<String, String> getHeaders() {
        return headers;
    }


    public String getMethod() {
        return methodType;
    }


    public Map<String, String> getParams() {
        return params;
    }


    public String getUrl() {
        return url;
    }


    public Map<String, String> getAllHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();
        Collections.list(request.getHeaderNames()).forEach(headerName -> headersMap.put(headerName, request.getHeader(headerName)));
        return headersMap;
    }

}
