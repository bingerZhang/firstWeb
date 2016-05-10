package com.binge.configuration;


import com.binge.configurator.*;
import com.binge.exception.DataBackendException;
import com.binge.module.*;
import com.binge.util.Application;

import java.io.File;
import java.util.*;

/**
 * Created by user on 2015/6/4.
 */
public class DefaultConfigurationManager implements ConfigurationManager, Runnable {
    private static DefaultConfigurationManager INSTANCE;
    private final Map<Class<? extends Configuration>, Configurator<? extends Configuration>> configurators = new HashMap<Class<? extends Configuration>, Configurator<? extends Configuration>>();

    public static DefaultConfigurationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultConfigurationManager();
        }
        return INSTANCE;
    }

    private DefaultConfigurationManager() {
        configurators.put(UserConfiguration.class, wrap(new UserConfigurator(getConfFile(UserConfiguration.TYPE))));
        configurators.put(DistributeConfiguration.class, wrap(new DistributeConfigurator(getConfFile(DistributeConfiguration.TYPE))));
        configurators.put(ProjectConfiguration.class,wrap(new ProjectConfigurator(getConfFile(ProjectConfiguration.TYPE))));
        configurators.put(HotImageConfiguration.class,wrap(new HotImageConfigurator(getConfFile(HotImageConfiguration.TYPE))));
        configurators.put(MapConfiguration.class,wrap(new MapConfigurator(getConfFile(MapConfiguration.TYPE))));
        configurators.put(BrandConfiguration.class,wrap(new BrandConfigurator(getConfFile(MapConfiguration.TYPE))));
        configurators.put(ProductConfiguration.class,wrap(new ProductConfigurator(getConfFile(ProductConfiguration.TYPE))));
        configurators.put(NewsConfiguration.class,wrap(new NewsConfigurator(getConfFile(NewsConfiguration.TYPE))));
        configurators.put(ContactConfiguration.class,wrap(new ContactConfigurator(getConfFile(ContactConfiguration.TYPE))));
    }


    public List<Configurator> getConfigurators() {
        return new ArrayList<Configurator>(configurators.values());
    }

    public <T extends Configuration> Configurator<T> getConfigurator(Class<T> clazz) {
        Configurator configurator = configurators.get(clazz);
        if (configurator == null) {
            throw new IllegalArgumentException("No such configurator: " + clazz);
        }
        return configurator;
    }

    public <T extends Configuration> T getConfiguration(Class<T> clazz) throws DataBackendException {
        Configurator<T> configurator = getConfigurator(clazz);
        return configurator.getConfiguration();
    }


    public <T extends Configuration> void setConfiguration(T configuration) throws DataBackendException {
        Configurator<T> configurator = (Configurator<T>) getConfigurator(configuration.getClass());
        configurator.setConfiguration(configuration);
    }


    public void run() {
        for (Configurator configurator : getConfigurators()) {
            if (configurator instanceof DefaultConfigurator) {
                try {
                    reloadConfiguration((DefaultConfigurator) configurator);
                } catch (DataBackendException e) {
//                    logger.warn(e);
                }
            }
        }
    }

    public void reloadConfiguration(DefaultConfigurator<Configuration> configurator) throws DataBackendException {
        if (!isReloadNeeded(configurator.getConfigurator())) {
            return;
        }
        Configuration configuration = configurator.getConfiguration(true);
//        logger.info("Reload configuration: " + configuration.getType());
    }

    private boolean isReloadNeeded(Configurator<Configuration> configurator) {
        return configurator instanceof FileConfigurator && ((FileConfigurator) configurator).isChanged();
    }

    private <T extends Configuration> DefaultConfigurator<T> wrap(Configurator<T> configurator) {
        return new DefaultConfigurator<T>(configurator);
    }

    private static File getConfFile(String type) {
        return new File(Application.CONF_PATH, type + ".conf");
    }

    private static File getPropertiesFile(String type) {
        return new File(Application.CONF_PATH, type + ".properties");
    }




    public static void main(String[] args) throws Exception {
//        testUserConfiguration();
        testProjectConfiguration();
//        testHotImageConfiguration();
//        testBrandConfiguration();
//        testProductConfiguration();
//        testNewsConfiguration();
//        testContactConfiguration();
    }

    public static void testContactConfiguration() throws DataBackendException {
        ContactConfiguration contactConfiguration = new ContactConfiguration();
        Contact contact = new Contact(1,"联系人：user1","company","地址：beijing","联系电话：18813190000","传真：010-1111111");
        List<Contact> list = new ArrayList<Contact>();
        list.add(contact);
        contactConfiguration.setContact(list);
        DefaultConfigurationManager manager = new DefaultConfigurationManager();
        manager.setConfiguration(contactConfiguration);
    }

    public static void testNewsConfiguration() throws DataBackendException {
        NewsConfiguration newsConfiguration = new NewsConfiguration();
        List<News> newsList = new ArrayList<News>();
        String content = "<p><img src=\"/images/3.jpg\" border=\"0\"></p>\n" +
                "\t\t\t<p>受大环境影响，石材企业转型是必然之路，对于单纯的石材工程等企业欲往家装工程方面发展方向，利用加盟家装品牌而获取的无形资产价值及商机，实现真正意义的品牌资源共享是最好的选择。</p>\n" +
                "\t\t\t<p>作为石材家装一线品牌，近日，溪石装饰全国诚邀加盟商正式启动，溪石家装集工装产品与家装产品相配套、材料销售与工程服务相促进、建筑与装饰装修相结合；优秀合作伙伴包括（中铁、中建、万科、龙湖、万达、中海、泛海、融创、绿城、星河湾、中梁、招商、华润、远洋、融侨等国内龙头地产），将助您战略转型。<br>目前溪石装饰已陆续在各大交通主干道投放户外广告。无论是在国道、省道、高速公路、码头、还是在酒店皆可看到别墅豪宅专家·助您战略转型，溪石装饰诚邀品牌加盟商的广告。<br>▼高速公路广告牌</p>\n" +
                "\t\t\t<p><img src=\"/images/1.jpg\" border=\"0\"><br></p>\n" +
                "\t\t\t<p><strong><span style=\"color: rgb(153, 51, 0);\">溪石优势<br></span></strong>※“中国驰名商标”品牌，石材与装饰一体化服务商<br>※“建筑装饰工程设计与施工一级”、“建筑幕墙工程设计与施工一级”双一级资质<br>※中国房地产开发企业500强首选供应品牌<br>※与万科、龙湖、万达、绿城、星河湾等龙头地产达成长期合作关系。<br>我们期待您的加入，助您战略转型，共享豪宅资源。<br>加盟热线：188 \n" +
                "\t7623 7777<br></p>";
        News news1 = new News(1,"title1","description1","/images/3.jpg","detail1",new Date().toString(),"http://xishigroup.com/cn/v_view.php?id=1");
        News news2 = new News(2,"title2","description2","/images/1.jpg","detail2",new Date().toString(),"http://xishigroup.com/cn/v_view.php?id=1");
        News news3 = new News(3,"title3","description3","/images/2.jpg","detail3",new Date().toString(),"http://xishigroup.com/cn/v_view.php?id=1");
        News news4 = new News(4,"title4","description4","/images/3.jpg","detail4",new Date().toString(),"http://xishigroup.com/cn/v_view.php?id=1");
        News news5 = new News(5,"title5","description5","/images/1.jpg","detail5",new Date().toString(),"http://xishigroup.com/cn/v_view.php?id=1");
        news1.setDetail(content);
        news2.setDetail(content);
        news3.setDetail(content);
        news4.setDetail(content);
        news5.setDetail(content);

        newsList.add(news1);
        newsList.add(news2);
        newsList.add(news3);
        newsList.add(news4);
        newsList.add(news5);
        newsConfiguration.setCompanyNewsList(newsList);
        newsConfiguration.setIndustryNewsList(newsList);
        newsConfiguration.setCompanyVideoList(newsList);
        DefaultConfigurationManager manager = new DefaultConfigurationManager();
        manager.setConfiguration(newsConfiguration);
    }

    public static void testProductConfiguration() throws DataBackendException {
        ProductConfiguration productConfiguration = new ProductConfiguration();
        List<String> imageList= new ArrayList<String>();
        imageList.add("/images/2.jpg");
        imageList.add("/images/1.jpg");
        imageList.add("/images/3.jpg");

        Product product1 = new Product(1,"test",1,"test-desc1", imageList,new Date());
        Product product2 = new Product(2,"test",1,"test-desc2", imageList,new Date());
        Product product3 = new Product(3,"test",1,"test-desc3", imageList,new Date());
        Product product4 = new Product(4,"test",1,"test-desc4", imageList,new Date());
        Product product5 = new Product(5,"test",1,"test-desc5", imageList,new Date());
        Product product6 = new Product(6,"test",1,"test-desc6", imageList,new Date());
        productConfiguration.addProduct(product1);
        productConfiguration.addProduct(product2);
        productConfiguration.addProduct(product3);
        productConfiguration.addProduct(product4);
        productConfiguration.addProduct(product5);
        productConfiguration.addProduct(product6);
        DefaultConfigurationManager manager = new DefaultConfigurationManager();
        manager.setConfiguration(productConfiguration);
    }

    public static void testBrandConfiguration() throws DataBackendException {
        BrandConfiguration brandConfiguration = new BrandConfiguration();
        Brand brand1 = new Brand(1,1,"意大利自动抛光机1","/images/2.jpg");
        Brand brand2 = new Brand(2,1,"意大利自动抛光机2","/images/1.jpg");
        Brand brand3 = new Brand(3,1,"意大利自动抛光机3","/images/2.jpg");
        Brand brand4 = new Brand(4,1,"意大利自动抛光机4","/images/1.jpg");
        Brand brand5 = new Brand(5,1,"意大利自动抛光机5","/images/2.jpg");
        Brand brand6 = new Brand(6,1,"意大利自动抛光机6","/images/1.jpg");
        Brand brand7 = new Brand(7,1,"意大利自动抛光机7","/images/1.jpg");
        Brand brand8 = new Brand(8,1,"意大利自动抛光机8","/images/1.jpg");
        Brand brand9 = new Brand(9,1,"意大利自动抛光机9","/images/2.jpg");
        brandConfiguration.addBrand("productionEquipment",brand1);
        brandConfiguration.addBrand("productionEquipment",brand2);
        brandConfiguration.addBrand("productionEquipment",brand3);
        brandConfiguration.addBrand("productionEquipment",brand4);
        brandConfiguration.addBrand("productionEquipment",brand5);
        brandConfiguration.addBrand("productionEquipment",brand6);
        brandConfiguration.addBrand("productionEquipment",brand7);
        brandConfiguration.addBrand("productionEquipment",brand8);
        brandConfiguration.addBrand("productionEquipment",brand9);
        DefaultConfigurationManager manager = new DefaultConfigurationManager();
        manager.setConfiguration(brandConfiguration);
    }


    public static void testHotImageConfiguration() throws DataBackendException {
        HotImageConfiguration hotImageConfiguration = new HotImageConfiguration();
        HotImage hotImage1 = new HotImage(1,"first","/images/1.jpg","/index.html",true);
        HotImage hotImage2 = new HotImage(2,"first","/images/2.jpg","/index.html",true);
        HotImage hotImage3 = new HotImage(3,"first","/images/3.jpg","/index.html",true);
        HotImage hotImage4 = new HotImage(2,"first","/images/2.jpg","/index.html",true);
        hotImageConfiguration.addHotImage(hotImage1);
        hotImageConfiguration.addHotImage(hotImage2);
        hotImageConfiguration.addHotImage(hotImage3);
        hotImageConfiguration.addHotImage(hotImage4);
        DefaultConfigurationManager manager = new DefaultConfigurationManager();
        manager.setConfiguration(hotImageConfiguration);
    }

    public static void testUserConfiguration() throws DataBackendException {
        UserConfiguration userConfiguration = new UserConfiguration();

        userConfiguration.setSequence(2);
        userConfiguration.getUsers().add(new User(1, "administrator", "testadmin"));

        DefaultConfigurationManager manager = new DefaultConfigurationManager();
        manager.setConfiguration(userConfiguration);
//        UserConfiguration configuration = manager.getConfiguration(UserConfiguration.class);
//        System.out.println(configuration);
    }

    public static FamousProject getNewFamousProject(int id, String name,String desc,List<String>imagepath,String iconpath){
        FamousProject famousProject = new FamousProject();
        famousProject.setId(id);
        famousProject.setName(name);
        famousProject.setDescription(desc);

        famousProject.setImagePath(imagepath);
        famousProject.setIconPath(iconpath);
        return famousProject;

    }
    public static void testProjectConfiguration() throws DataBackendException {
        List<String> imageList = new ArrayList<String>();
        imageList.add("/images/a1.jpg");
        imageList.add("/images/a2.jpg");
        imageList.add("/images/a2.jpg");
        imageList.add("/images/a2.jpg");
        imageList.add("/images/a2.jpg");
        imageList.add("/images/a1.jpg");
        imageList.add("/images/a1.jpg");

        String desc = "<br>使用品种：G664、英国棕、黑金花、古典米黄\n" +
                "<br>安装部位：室内、室外、墙面、地面  <br>项目支持：石材供货、技术指导";

        FamousProject famousProject = getNewFamousProject(2,"test_2",desc,imageList,"");
        FamousProject famousProject3 = getNewFamousProject(3,"test_3",desc,imageList,"");
        FamousProject famousProject4 = getNewFamousProject(4,"test_4",desc,imageList,"");
        FamousProject famousProject5 = getNewFamousProject(5,"test_5",desc,imageList,"");
        FamousProject famousProject6 = getNewFamousProject(6,"test_6",desc,imageList,"");
//        famousProject.setDescription("test_desc");
//        famousProject.setImagePath("/images/1.jpg");
//        famousProject.setIconPath("");
//        famousProject.setId(1);
//        famousProject.setName("NOVATEK天然气公司楼楼楼楼楼");


        ProjectConfiguration projectConfiguration = new ProjectConfiguration();
        List<FamousProject> famousProjectList = projectConfiguration.getFamousProjectList();
        famousProjectList.add(famousProject);
        famousProjectList.add(famousProject3);
        famousProjectList.add(famousProject4);
        famousProjectList.add(famousProject5);
        famousProjectList.add(famousProject6);

        projectConfiguration.setFamousProjectList(famousProjectList);

        DefaultConfigurationManager manager = new DefaultConfigurationManager();
        manager.setConfiguration(projectConfiguration);

    }

}
