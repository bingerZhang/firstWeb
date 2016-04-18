package com.binge.configuration;


import com.binge.configurator.*;
import com.binge.exception.DataBackendException;
import com.binge.module.User;
import com.binge.util.Application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        UserConfiguration userConfiguration = new UserConfiguration();

        userConfiguration.setSequence(2);
        userConfiguration.getUsers().add(new User(1, "administrator", "admin"));

        DefaultConfigurationManager manager = new DefaultConfigurationManager();
        manager.setConfiguration(userConfiguration);

//        UserConfiguration configuration = manager.getConfiguration(UserConfiguration.class);
//        System.out.println(configuration);
    }
}
