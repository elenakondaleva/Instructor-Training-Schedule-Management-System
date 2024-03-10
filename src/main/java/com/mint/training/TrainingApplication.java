package com.mint.training;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class TrainingApplication {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}

}
