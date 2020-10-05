package com.altran.rabbitmq_log_recv.logs_recv_comoponent;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogsConsumer {
 
	@Value("${logs.folder.path}")
	private String logFolderPath;
	
	@Value("${logs.file.name}")
	private String logFileName;
	
    @RabbitListener(queues = {"prod-logs-queue"})
    public void recieveProdLogs(String msg) {
        //System.err.println("Logs receiver recieves a message:" + msg);
    	System.out.println(logFolderPath+logFileName);
        try (FileWriter file = new FileWriter("D:\\New folder (3)\\prod_application_logs.json",true)) {
 
            file.write(msg);
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RabbitListener(queues = {"test-logs-queue"})
    public void recieveTestLogs(String msg) {
        //System.err.println("Logs receiver recieves a message:" + msg);
    	System.out.println(logFolderPath+logFileName);
        try (FileWriter file = new FileWriter("D:\\New folder (3)\\test_application_logs.json",true)) {
 
            file.write(msg);
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
