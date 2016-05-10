package com.binge.configuration;

import com.binge.module.FamousProject;
import com.binge.module.News;
import com.binge.module.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zlb on 2016/4/18.
 */
public class NewsConfiguration implements Configuration {
    public static final String TYPE = "news";
    private List<News> companyNewsList;
    private List<News> industryNewsList;
    private List<News> companyVideoList;

    public NewsConfiguration() {
        companyNewsList = new ArrayList<News>();
        industryNewsList = new ArrayList<News>();
        companyVideoList = new ArrayList<News>();
    }

    public List<News> getCompanyNewsList() {
        return companyNewsList;
    }

    public void setCompanyNewsList(List<News> companyNewsList) {
        this.companyNewsList = companyNewsList;
    }

    public List<News> getCompanyVideoList() {
        return companyVideoList;
    }

    public void setCompanyVideoList(List<News> companyVideoList) {
        this.companyVideoList = companyVideoList;
    }

    public List<News> getIndustryNewsList() {
        return industryNewsList;
    }

    public void setIndustryNewsList(List<News> industryNewsList) {
        this.industryNewsList = industryNewsList;
    }

    public News[] getNewsByTypeId(int type, int id){
        News[] newsList = new News[3];
        List<News> list = null;
        News nextNews = null;
        boolean found = false;
        list = getNewsListByType(type);
        for(News news:list){
            if(found){
                newsList[2]=news;
                break;
            }
            if(id==news.getId()){
                found = true;
                newsList[1] = news;
            }else{
                newsList[0] = news;
            }
        }
        return newsList;
    }

    public List<News> getNewsListByType(int type){
        if(type == 1){
            return companyNewsList;
        }else if(type == 2){
            return industryNewsList;
        }else if(type == 3){
            return companyVideoList;
        }
        return companyNewsList;
    }

    public void addNews(int type,News addnews){
        List<News> list = getNewsListByType(type);
        for(News news:list){
            if(news.equals(addnews)){
                list.remove(news);
            }
        }
        list.add(addnews);
    }

    @Override
    public String getType() {
        return TYPE;
    }
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
