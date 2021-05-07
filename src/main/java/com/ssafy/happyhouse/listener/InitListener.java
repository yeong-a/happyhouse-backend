package com.ssafy.happyhouse.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	sce.getServletContext().setAttribute("root", sce.getServletContext().getContextPath());
    }
	
}
