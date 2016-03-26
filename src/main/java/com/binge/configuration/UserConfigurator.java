package com.binge.configuration;


import com.binge.exception.DataBackendException;
import com.binge.module.User;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by user on 2015/6/4.
 */
public class UserConfigurator extends JsonConfigurator<UserConfiguration> {

    public UserConfigurator(File file) {
        super(file);
    }

    @Override
    protected UserConfiguration getConfiguration(Map<String, Object> properties) throws IOException {
        UserConfiguration configuration = new UserConfiguration();
        if (properties != null) {
            configuration.setSequence(ObjectMapper.getInt(properties, "sequence", 2));
            configuration.setUsers(ObjectMapper.getList(properties, "users", new UserMapper(this)));
        }
        return configuration;
    }

    @Override
    protected Map<String, Object> getProperties(UserConfiguration configuration) throws IOException {
        Map<String, Object> properties = new LinkedHashMap<String, Object>();
        properties.put("sequence", configuration.getSequence());
        properties.put("users", ObjectMapper.serialize(configuration.getUsers(), new UserMapper(this)));
        return properties;
    }

    private class UserMapper extends ObjectMapper<User> {

        private UserConfigurator configurator;

        public UserMapper(UserConfigurator configurator) {
            this.configurator = configurator;
        }

        @Override
        public Map<String, Object> serialize(User user) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            if (user.getId() == null) {
                UserConfiguration configuration = null;
                try {
                    configuration = configurator.getConfiguration();
                } catch (DataBackendException e) {
                    e.printStackTrace();
                }
                user.setId(UserConfiguration.INDEX.getAndSet(configuration.getSequence()));
            }
            map.put("id", user.getId());
            map.put("username", user.getUsername());
            map.put("password", user.getPassword());
            return map;
        }

        @Override
        public User deserialize(Map<String, Object> map) {
            User user = new User();
            user.setId(Integer.parseInt(map.get("id").toString()));
            user.setUsername(map.get("username").toString());
            user.setPassword(map.get("password").toString());
            return user;
        }
    }
}
