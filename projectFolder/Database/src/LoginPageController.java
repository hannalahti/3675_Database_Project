/*
 * Author: Hanna Lahti
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginPageController extends Controller{

    @FXML
    private Button loginButton;

    @FXML
    private Text loginErrorMessage;

    @FXML
    private Text loginText;

    @FXML
    private Text newUserText;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Hyperlink signupHyperlink;

    @FXML
    private TextField usernameTextField;

    Boolean loginPage = true;
    String usernameTextFieldStyle;
    String passwordTextFieldStyle;

    @FXML
    void loginButtonPressed(ActionEvent event) {

        if(!checkFormat())
            return;

        if(loginPage)
            login(event);
        else
            signup(event);
    }

    @FXML
    void signupLinkPressed(ActionEvent event) {

        loginPage = !loginPage;

        if(loginPage)
            showLoginPage();
        else
            showSignupPage();
    }

    void login(ActionEvent e) {

        if (super.db.findUser(usernameTextField.getText(), passwordTextField.getText()) == -1) {
            showErrors();
            return;
        }
        super.switchWindow(e, "myShows.fxml", loginButton);
        System.out.println("login successful");
    }

    void signup(ActionEvent e) {

		if(super.db.registerUser(usernameTextField.getText(), passwordTextField.getText())==-1){
			showErrors();
			return;
		}
        super.switchWindow(e, "myShows.fxml", loginButton);
        System.out.println("signup successful");
    }

    void showSignupPage() {
        loginText.setText("SIGN UP");
        newUserText.setText("Existing user?");
        signupHyperlink.setText("Login.");
        loginButton.setText("SIGNUP");
        clear();
    }

    void showLoginPage() {
        loginText.setText("LOGIN");
        newUserText.setText("New user?");
        signupHyperlink.setText("Signup.");
        loginButton.setText("LOGIN");
        clear();
    }

    /*
     * clears text from all text fields and removes error indicators
     */
    void clear() {
        usernameTextField.clear();
        passwordTextField.clear();
        removeError();
    }

    //check valid string format
    public boolean generalFormat(String s, Node t) {
        if(s.length()>22 || "".equals(s) || s.contains(" ")) {
            t.setStyle("-fx-border-color: red");
            return false;
        }
        return true;
    }

    public boolean checkFormat() {

        if(!generalFormat(usernameTextField.getText(),usernameTextField)||!generalFormat(passwordTextField.getText(),passwordTextField)) {
            showErrors();
            return false;
        }
        return true;
    }

    /*
     * indicates errors
     */
    void showErrors() {
        if(loginPage) {
            usernameTextField.setStyle("-fx-border-color: red");
            passwordTextField.setStyle("-fx-border-color: red");
        }
        loginErrorMessage.setVisible(true);
    }

    public void initialize() {
        /*
         * when usernameTextField is selected, error messages are removed
         */
        usernameTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            removeError();
        });

        /*
         * when passwordTextField is selected, error messages are removed
         */
        passwordTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            removeError();
        });

    }

    /*
     * removes error indicators
     */
    void removeError() {
        usernameTextField.setStyle(usernameTextFieldStyle); // Remove the red error border
        passwordTextField.setStyle(passwordTextFieldStyle); // Remove the red error border
        loginErrorMessage.setVisible(false);
    }

}
