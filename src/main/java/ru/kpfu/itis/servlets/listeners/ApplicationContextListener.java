package ru.kpfu.itis.servlets.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.kpfu.itis.repositories.AdvertRepository;
import ru.kpfu.itis.repositories.FilesRepository;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.repositories.impl.AdvertRepositoryImpl;
import ru.kpfu.itis.repositories.impl.FilesRepositoryImpl;
import ru.kpfu.itis.repositories.impl.UserRepositoryImpl;
import ru.kpfu.itis.security.AuthenticateManagerImpl;
import ru.kpfu.itis.security.AuthenticationManager;
import ru.kpfu.itis.services.AdvertService;
import ru.kpfu.itis.services.FilesService;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.services.impl.AdvertServiceImpl;
import ru.kpfu.itis.services.impl.FilesServiceImpl;
import ru.kpfu.itis.services.impl.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    private HikariDataSource hikariDataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();

        try {
            properties.load(ApplicationContextListener.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driverClassName"));
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.maxPoolSize")));

        hikariDataSource = new HikariDataSource(hikariConfig);


        UserRepository userRepository = new UserRepositoryImpl(hikariDataSource);
        AdvertRepository advertRepository = new AdvertRepositoryImpl(hikariDataSource);
        FilesRepository filesRepository = new FilesRepositoryImpl(hikariDataSource);


        UserService userService = new UserServiceImpl(userRepository);
        AdvertService advertService = new AdvertServiceImpl(advertRepository);
        FilesService filesService = new FilesServiceImpl(filesRepository);

        AuthenticationManager authenticationManager = new AuthenticateManagerImpl(userRepository);

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("advertService", advertService);
        sce.getServletContext().setAttribute("filesService", filesService);;
        sce.getServletContext().setAttribute("authenticateManager", authenticationManager);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        hikariDataSource.close();
    }
}
