package com.hl.spring.mvcframework.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class HLDispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private List classNames = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6.等待请求
        req.getRequestURI();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1.加载配置文件
//        System.out.println("init");
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描所有相关类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.舒适化所有相关类, 并保存到ioc容器
        doInstance();

        //4.执行依赖注入(把@Autowired注解的字段赋值)
        doAutowired();

        //5.构造HandlerMapping, 将url和Method 关联
        initHandlerMapping();


        System.out.println("初始化完毕");
    }

    private void doAutowired() {
    }

    private void initHandlerMapping() {
    }

    private void doInstance() {
    }

    private void doScanner(String scanPackage) {

        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        assert url != null;
        File dir = new File(url.getFile());
        for (File file: Objects.requireNonNull(dir.listFiles())) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else{
                String className = scanPackage + "." + file.getName().replace(".class","");
                classNames.add(className);
                System.out.println(className);
            }
        }
    }

    private void doLoadConfig(String locationConfig) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locationConfig);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
