module org.nocab.nocabmachine {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.nocab.nocabmachine to javafx.fxml;
    exports org.nocab.nocabmachine;
    exports org.nocab.nocabmachine.nocab;
    opens org.nocab.nocabmachine.nocab to javafx.fxml;
    exports org.nocab.nocabmachine.nocab.FieldProcessors;
    opens org.nocab.nocabmachine.nocab.FieldProcessors to javafx.fxml;
}