package view;


import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;
import viewmodel.TemperatureViewModel;

public class MainViewController
{
  @FXML private Label mOne;
  @FXML private Label mTwo;
  @FXML private Label mThree;
  @FXML private Label state;
  @FXML private Label errorLabel;
  @FXML private TextField critcalHighSetting;
  @FXML private TextField critcalLowSetting;

  private TemperatureViewModel viewModel;
  private ViewHandler handler;
  private Region root;

  public MainViewController()
  {

  }

  public void init(ViewHandler handler, TemperatureViewModel viewModel, Region root)
  {
    this.viewModel = viewModel;
    this.handler = handler;
    this.root = root;
    Bindings.bindBidirectional(mOne.textProperty(),viewModel.getT0(), new NumberStringConverter());
    Bindings.bindBidirectional(mTwo.textProperty(),viewModel.getT1(), new NumberStringConverter());
    Bindings.bindBidirectional(mThree.textProperty(),viewModel.getT2(), new NumberStringConverter());
    Bindings.bindBidirectional(state.textProperty(),viewModel.getPower(), new NumberStringConverter());
    Bindings.bindBidirectional(critcalLowSetting.textProperty(),viewModel.getTempLow(), new NumberStringConverter());
    Bindings.bindBidirectional(critcalHighSetting.textProperty(),viewModel.getTempHigh(), new NumberStringConverter());
    Bindings.bindBidirectional(errorLabel.textProperty(),viewModel.getError());
  }

  public void reset()
  {
    this.mOne.setText("");
    this.mTwo.setText("");
    this.mThree.setText("");
    this.state.setText("");
    this.errorLabel.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void onSetHighLow()
  {
    viewModel.setTempHigh();
    viewModel.setTempLow();
  }

  @FXML private void onDownButton()
  {
    viewModel.turnDown();
  }

  @FXML private void onUpButton()
  {
    viewModel.turnUp();
  }

}
