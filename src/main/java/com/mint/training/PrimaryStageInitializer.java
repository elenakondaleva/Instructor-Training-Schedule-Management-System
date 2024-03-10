package com.mint.training;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
final class PrimaryStageInitializer implements ApplicationListener<JavaFxApplication.StageReadyEvent> {
        private final static String APP_TITLE = "Instructor Training Schedule Management System";
        private final static String INDEX_VIEW = "/pages/primaryPage";
        private final ApplicationContext applicationContext;
        private Scene scene;

        @Override
        @SneakyThrows
        public void onApplicationEvent (JavaFxApplication.StageReadyEvent event){
        val parent = loadFXML(INDEX_VIEW);
        scene = new Scene(parent, 1300, 1000);
        scene.getStylesheets().add(getClass().getResource("/style/label.css").toExternalForm());
        val stage = event.getStage();
        stage.setTitle(APP_TITLE);
        stage.setScene(scene);
        stage.show();
    }

        private Parent loadFXML (String fxml) throws IOException {
        var resource = PrimaryStageInitializer.class.getResource(fxml + ".fxml");

        if (resource == null) {
            throw new IOException("Failed to find FXML resource " + fxml);
        }

        val fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader.load();
    }
}

