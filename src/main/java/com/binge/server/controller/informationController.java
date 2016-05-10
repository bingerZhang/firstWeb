package com.binge.server.controller;

import com.binge.configuration.NewsConfiguration;
import com.binge.module.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zlb
 * Date: 16-3-9
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping( "/information" )
public class InformationController extends DefaultController {
    @RequestMapping( "/news" )
    public String getNews() {
        NewsConfiguration newsConfiguration = getConfiguration(NewsConfiguration.class);
        int type = 1;
        String newType = request.getParameter("type");
        if(newType!=null&newType.length()>0)type = Integer.valueOf(newType);
        request.setAttribute("type",type);
        request.setAttribute("newslist",newsConfiguration.getNewsListByType(type));
        return "/information/newsList";
    }

    @RequestMapping( "/companyNews" )
    public String companyNews() {
        NewsConfiguration newsConfiguration = getConfiguration(NewsConfiguration.class);
        request.setAttribute("type",1);
        request.setAttribute("newslist",newsConfiguration.getCompanyNewsList());
        return "/information/newsList";
    }

    @RequestMapping( "/industryNews" )
    public String industryNews() {
        NewsConfiguration newsConfiguration = getConfiguration(NewsConfiguration.class);
        request.setAttribute("type",2);
        request.setAttribute("newslist",newsConfiguration.getIndustryNewsList());
        return "/information/newsList";
    }

    @RequestMapping( "/companyVideos" )
    public String companyVideos() {
        NewsConfiguration newsConfiguration = getConfiguration(NewsConfiguration.class);
        request.setAttribute("type",3);
        request.setAttribute("videolist",newsConfiguration.getCompanyVideoList());
        return "/information/companyVideos";
    }
    @RequestMapping( "/detail" )
    public String detail() {
        NewsConfiguration newsConfiguration = getConfiguration(NewsConfiguration.class);
        int id = Integer.valueOf(request.getParameter("id"));
        int type = Integer.valueOf(request.getParameter("type"));
        News[] newsList = newsConfiguration.getNewsByTypeId(type,id);
        if(newsList!=null){
            request.setAttribute("prenews",newsList[0]);
            request.setAttribute("news",newsList[1]);
            request.setAttribute("nextnews",newsList[2]);
        }
        request.setAttribute("type",type);
        return "/information/detail";
    }
}
