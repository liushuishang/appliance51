//package appliance51.rest.controller;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//public class IdentificationFilter implements Filter
//{
//    public static final String TICKET_NAME = "com_jme_security_ticket";
//    public static final String KEY_NAME = "com_jme_security_key";
//
//    private final static Logger logger = LoggerFactory.getLogger(IdentificationFilter.class);
//
//    IdentificationManager identificationManager;
//
//    /**
//     * @see Filter#doFilter(ServletRequest,
//     *      ServletResponse, FilterChain)
//     */
//    @Override
//    public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain chain)
//            throws IOException, ServletException
//    {
//        doFilter1(request1, response1, chain);
//    }
//
//    /**
//     * 没有登录的时这个方法 会让JSON数据请求通过 doFilter 的实现代码
//     * @param request1
//     * @param response1
//     * @param chain
//     * @throws IOException
//     * @throws ServletException
//     */
//    private void doFilter1(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException
//    {
//        req.setCharacterEncoding("UTF-8");
//        res.setCharacterEncoding("UTF-8");
//        HttpServletRequest request = (HttpServletRequest) req;
//
//        HttpServletResponse response = (HttpServletResponse) res;
//        //HttpSession session = request.getSession();
//
//        String path = request.getRequestURI();
//
//        if (path.contains("/identification") || path.contains("/login") || path.contains("/systemInfo"))
//        {
//            logger.debug("not path:{}", new Object[]
//            { path });
//            chain.doFilter(request, response);
//        }
//        else
//        {
//            //从 header 取得ticket
//            String ticket = request.getParameter(TICKET_NAME);
//            logger.debug("ticket:{}", new Object[]
//            { ticket });
//
//            //看一下参数里
//            if (ticket == null)
//            {
//            	ticket = request.getHeader(TICKET_NAME);
//            }
//
//            //如果没有则试图从cookie里取
//            if (ticket == null)
//            {
//                Cookie[] cookies = request.getCookies();
//                if (cookies != null) {
//                    for(Cookie cookie : cookies) {
//                        if (TICKET_NAME.equals(cookie.getName())) {
//                            ticket = cookie.getValue();
//                        }
//                    }
//                }
//            }
//
//            if (ticket == null || ticket.length() == 0)
//            {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            }
//            else
//            {
//                //验证没有超时
//                long time = identificationManager.getTicketTime(ticket);
//                logger.debug("time:{}", new Object[]
//                { time });
//                if (time <= 0)
//                {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                }
//                else
//                {
//                    //通过的将key 存放到request
//                    String key = identificationManager.getKey(ticket);
//                    request.setAttribute(KEY_NAME, key);
//                    chain.doFilter(request, response);
//                }
//            }
//
//        }
//    }
//
//    /**
//     *
//     * @see Filter#destroy()
//     */
//    @Override
//    public void destroy()
//    {
//    }
//
//    /**
//     *
//     * @see Filter#init(FilterConfig)
//     */
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException
//    {
//        logger.info("IdentificationFilter.init start" + filterConfig.getServletContext().getServerInfo());
//
//        ServletContext context = filterConfig.getServletContext();
//        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
//        identificationManager = (IdentificationManager) ctx.getBean("identificationManager");
//
//        logger.info("IdentificationFilter.init end identificationManager:" + identificationManager);
//    }
//
//}
