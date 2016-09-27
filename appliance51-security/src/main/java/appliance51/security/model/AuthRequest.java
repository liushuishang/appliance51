package appliance51.security.model;

import appliance51.common.utils.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.javatuples.KeyValue;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-09-17 22:05.
 */
public class AuthRequest {

    public static final String MULTIPART = "multipart/";
    //    public static final String FROM_HEADER = "X-Engine-From"; //用于判断内网外网（Nginx配置添加Header）
//    public static final String SSL_HEADER = "X-Engine-SSL";
    public static final String TOKEN_NAME = "X-Client-Token";
    public static final String CLIENT_TYPE = "X-Client-Type";
    public static final String CLIENT_PLATFORM = "X-Client-Paltform";
    public static final String CLIENT_VERSION = "X-Client-Version";


    private HttpServletRequest request;
    private List<KeyValue<String, String>> cacheCookies;

    public AuthRequest(HttpServletRequest request) {
        if (request == null) {
            throw new NullPointerException("request");
        }
        this.request = request;
    }

    public String getClientType(){
        return request.getHeader(CLIENT_TYPE);
    }
    public String getClientPlatform(){
        return request.getHeader(CLIENT_PLATFORM);
    }
    public String getClientVersion(){
        return request.getHeader(CLIENT_VERSION);
    }
    public String getClientToken(){
        return request.getHeader(TOKEN_NAME);
    }


    public String getHeader(String name) {
        return request.getHeader(name);
    }

    public List<String> getHeaders(String name) {
        Enumeration<String> values = request.getHeaders(name);
        List<String> list = new ArrayList<String>();
        while (values.hasMoreElements()) {
            list.add(values.nextElement());
        }
        return list;
    }

    public Iterable<String> getHeaderNames() {
        final Enumeration<String> e = request.getHeaderNames();
        return () -> new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return e.hasMoreElements();
            }

            @Override
            public String next() {
                return e.nextElement();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    public List<KeyValue<String, String>> getCookies() {
        if (cacheCookies != null) {
            return cacheCookies;
        }
        List<KeyValue<String, String>> ret = new ArrayList<>();
        Cookie[] cookieArr = request.getCookies();
        if (cookieArr != null) {
            for (Cookie acookie : cookieArr) {
                ret.add(new KeyValue<>(acookie.getName(),
                        acookie.getValue()));
            }
        }
        cacheCookies = ret;
        return cacheCookies;
    }

    public String getCookie(String name) {
        if (name == null) {
            return null;
        }
        List<KeyValue<String, String>> cookies = getCookies();
        for (KeyValue<String, String> pair : cookies) {
            if (name.equals(pair.getKey()))
                return pair.getValue();
        }
        return null;
    }

    public String getMethod() {
        return request.getMethod();
    }

    public String getCharacterEncoding() {
        return request.getCharacterEncoding();
    }

    public boolean isMultiPart() {
        String contentType = request.getContentType();
        return "POST".equals(request.getMethod()) && contentType != null
                && contentType.toLowerCase().startsWith(MULTIPART);
    }


    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    public void setAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }

    public String getScheme() {
        return request.getScheme();
    }

    public String getRequestURI() {
        return request.getRequestURI();
    }

    public String getRemoteIp() {
        return IPUtils.getRealIpAddr(this.request);
    }

    public String getApiPath() {
        String path = this.getRequestURI();
        if (path.length() <= 1) {
            return path;
        }
        // trim version
        int index = path.indexOf('/', 1);
        if (index > 0) {
            String version = path.substring(1, index);
            if (NumberUtils.isDigits(version)) {
                path = path.substring(index);
            }
        }
        // trim suffix
        index = path.lastIndexOf('.');
        if (index >= 0) {
            path = path.substring(0, index);
        } else {
            index = path.indexOf('?');
            if (index >= 0) {
                path = path.substring(0, index);
            }
        }

        return path;
    }

    public boolean isHttps() {
        return StringUtils.equalsIgnoreCase("HTTPS", this.getScheme())
                || StringUtils.equalsIgnoreCase("SSL", this.getHeader("X-Proto"));

    }

    public String getParameter(String name) {
        return this.request.getParameter(name);
    }


}