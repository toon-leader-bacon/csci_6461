module org.nocab.nocabmachine {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.nocab.nocabmachine to javafx.fxml;
    exports org.nocab.nocabmachine;
    exports nocab;
    opens nocab to javafx.fxml;
    exports nocab.FieldProcessors;
    opens nocab.FieldProcessors to javafx.fxml;
    exports nocab.DataStructures;
    opens nocab.DataStructures to javafx.fxml;
}