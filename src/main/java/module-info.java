module com.example.chatserverclient {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.chatserverclient to javafx.fxml;
    exports com.example.chatserverclient;
}