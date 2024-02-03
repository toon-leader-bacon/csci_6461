module org.nocab.nocabmachine {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.nocab.nocabmachine to javafx.fxml;
    exports org.nocab.nocabmachine;
    exports nocab.nocab;
    opens nocab.nocab to javafx.fxml;
    exports nocab.nocab.FieldProcessors;
    opens nocab.nocab.FieldProcessors to javafx.fxml;
    exports nocab.nocab.DataStructures;
    opens nocab.nocab.DataStructures to javafx.fxml;
}