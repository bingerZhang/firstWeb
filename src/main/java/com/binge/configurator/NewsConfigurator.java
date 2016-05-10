package com.binge.configurator;

import com.binge.configuration.NewsConfiguration;
import com.binge.module.News;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zlb on 2016/4/18.
 */
public class NewsConfigurator extends JsonConfigurator<NewsConfiguration> {
    public NewsConfigurator(File file) {
        super(file);
    }

    @Override
    protected NewsConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        NewsConfiguration NewsConfiguration = new NewsConfiguration();
        if(properties!=null){
            NewsConfiguration.setCompanyNewsList(ObjectMapper.getList(properties, "companyNewsList", new NewsObjectMapper()));
            NewsConfiguration.setIndustryNewsList(ObjectMapper.getList(properties,"industryNewsLists",new NewsObjectMapper()));
            NewsConfiguration.setCompanyVideoList(ObjectMapper.getList(properties,"companyVideosList",new NewsObjectMapper()));
        } else {
            NewsConfiguration.setCompanyNewsList(new ArrayList<News>());
            NewsConfiguration.setIndustryNewsList(new ArrayList<News>());
            NewsConfiguration.setCompanyVideoList(new ArrayList<News>());
        }

        return NewsConfiguration;

    }

    @Override
    protected Map<String, Object> getProperties(NewsConfiguration configuration) throws IOException {
        Map<String, Object> properties = new LinkedHashMap<String, Object>();
        properties.put("companyNewsList", ObjectMapper.serialize(configuration.getCompanyNewsList(), new NewsObjectMapper()));
        properties.put("industryNewsLists", ObjectMapper.serialize(configuration.getIndustryNewsList(), new NewsObjectMapper()));
        properties.put("companyVideosList", ObjectMapper.serialize(configuration.getCompanyVideoList(), new NewsObjectMapper()));
        return properties;
    }

    protected class NewsObjectMapper extends ObjectMapper<News> {

        @Override
        public Map<String, Object> serialize(News value) {
            Map<String, Object> properteis = new HashMap<String, Object>();
            properteis.put("id", value.getId());
            properteis.put("title",value.getTitle());
            properteis.put("description", value.getDescription());
            properteis.put("imagepath",value.getImagepath());
            properteis.put("detail",value.getDetail());
            properteis.put("updatetime",value.getUpdatetime());
            properteis.put("videourl",value.getVideourl());
            return properteis;
        }

        @Override
        public News deserialize(Map<String, Object> map) {
            News News = new News();
            News.setId(getInt(map, "id", 0));
            News.setTitle(getString(map, "title"));
            News.setDescription(getString(map,"description"));
            News.setImagepath(getString(map,"imagepath"));
            News.setDetail(getString(map,"detail"));
            News.setUpdatetime(getString(map,"updatetime"));
            News.setVideourl(getString(map,"videourl"));
            return News;
        }
    }
}
