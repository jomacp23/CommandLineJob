package com.commandline.jobsconfiguration;

import com.commandline.jobs.JobDemo;
import com.commandline.util.PropertiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jose.m.cruz.perez on 24/02/2017.
 */
public class JobConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(JobConfiguration.class);

    public static JobDemo configJob() {

        System.out.println("Configuracion del job iniciada");
        LOGGER.info("Configuracion del job iniciada");

        JobDemo jobDemo = new JobDemo();

        jobDemo.setUsername(PropertiesConfig.USER_NAME);
        jobDemo.setPassword(PropertiesConfig.USER_PASSWORD);
        jobDemo.setJob(PropertiesConfig.JOB_NAME);
        jobDemo.setServer(PropertiesConfig.SERVER_URL);
        jobDemo.setPort(PropertiesConfig.SERVER_PORT);

        return jobDemo;
    }

}
