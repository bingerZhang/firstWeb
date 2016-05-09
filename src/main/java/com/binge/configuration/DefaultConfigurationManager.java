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
//        testProjectConfiguration();
//        testHotImageConfiguration();
//        testBrandConfiguration();
        testProductConfiguration();
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

    public static FamousProject getNewFamousProject(int id, String name,String desc,String imagepath,String iconpath){
        FamousProject famousProject = new FamousProject();
        famousProject.setId(id);
        famousProject.setName(name);
        famousProject.setDescription(desc);
        famousProject.setImagePath(imagepath);
        famousProject.setIconPath(iconpath);
        return famousProject;

    }
    public static void testProjectConfiguration() throws DataBackendException {
        FamousProject famousProject = getNewFamousProject(2,"test_2","test_2_desc","/images/1.jpg","");
        FamousProject famousProject3 = getNewFamousProject(3,"test_3","test_3_desc","/images/2.jpg","");
        FamousProject famousProject4 = getNewFamousProject(4,"test_4","test_4_desc","/images/1.jpg","");
        FamousProject famousProject5 = getNewFamousProject(5,"test_5","test_5_desc","/images/1.jpg","");
        FamousProject famousProject6 = getNewFamousProject(6,"test_6","test_6_desc","/images/1.jpg","");
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
