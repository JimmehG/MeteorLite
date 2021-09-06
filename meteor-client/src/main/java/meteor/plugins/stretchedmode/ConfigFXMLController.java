package meteor.plugins.stretchedmode;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import meteor.PluginManager;
import meteor.plugins.Plugin;

public class ConfigFXMLController {

  StretchedModePlugin plugin;
  @FXML
  private JFXToggleButton keepAspectRatioEnabled;
  @FXML
  private JFXToggleButton increasedPerfEnabled;
  @FXML
  private JFXToggleButton integerScalingEnabled;
  @FXML
  private JFXSlider scalingFactorSlider;

  {
    for (Plugin p : PluginManager.plugins) {
      if (p instanceof StretchedModePlugin) {
        plugin = (StretchedModePlugin) p;
      }
    }
  }

  @FXML
  public void initialize() {
  }

}
