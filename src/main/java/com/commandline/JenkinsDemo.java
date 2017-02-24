package com.commandline;

import com.commandline.jobsconfiguration.JobConfiguration;
import com.commandline.jobsposter.JenkinsPoster;

import com.commandline.jobs.JobDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class JenkinsDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(JenkinsDemo.class);

    public static void main(String[] args) throws Exception {
        System.out.println("Inicia la ejecucion del Job");
        LOGGER.info("Dar de alta un libro");
        JobDemo jobDemo = JobConfiguration.configJob();

        // Se inicia el job
        JenkinsPoster jenkinsPoster = new JenkinsPoster(jobDemo);
        System.out.println("Iniciando la ejecucion de postJenkinsJob");
        LOGGER.info("Iniciando la ejecucion de postJenkinsJob");
        jenkinsPoster.postJenkinsJob();
        System.out.println("Ejecucion finalizada de postJenkinsJob");
        LOGGER.info("Ejecucion finalizada de postJenkinsJob");
    }

}
