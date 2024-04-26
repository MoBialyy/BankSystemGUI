package application;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalDate;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller{
	
	

	
	
	
	//main ctrl
	@FXML
	private AnchorPane home;
	private Stage stage;
	private Scene scene;
	
	public void switchScene1(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
	
	
	//scene1 ctrl
	@FXML
	TextField customerIDTextField;
	@FXML
	TextField passTextField;
	@FXML
	Label wrong;
	private static int CID;
	private static int AID;
	private static int LID;
	private Bank banknbe = Bank.getInstance();
	
	public void switchMain(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchScene3(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("scene3.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void login(ActionEvent event) throws IOException {
		String cidtxt = customerIDTextField.getText();
		if(cidtxt.isBlank()) {
			wrong.setText("Please enter your customer ID!");
		}
		else {
			int CID = Integer.valueOf(customerIDTextField.getText());
		    String pass = passTextField.getText();   
		    if(pass.isBlank()) {
		    	wrong.setText("Please enter your password!");
		    }
		    else {
			    if (banknbe.existAccount(CID)) {
			        Customer customer = banknbe.searchCustomerById(CID);
			        if (pass.equals(customer.getPassword())) {
			            FXMLLoader loader = new FXMLLoader(getClass().getResource("scene6.fxml"));
			            Parent root = loader.load();
			            setCID(CID);
			            
			            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			            Scene scene = new Scene(root);
			            stage.setScene(scene);
			            stage.show();}
			        else {
			            wrong.setText("Wrong Password");
			        }}
			    else {
			        wrong.setText("No account with this name");
			    }
		    }
		}
	}

	public void setCID(int CID) {
        this.CID = CID;
	}
	
	public int getCID() {
		return CID;
	}
	
	public void setAID(int AID) {
        this.AID = AID;
	}
	
	public int getAID() {
		return AID;
	}
	
	public void setLID(int LID) {
        this.LID = LID;
	}
	
	public int getLID() {
		return LID;
	}
	

	
	
	
	
	
	
	//scene 3 ctrl
	@FXML
	TextField nameTextField;
	@FXML
	TextField addressTextField;
	@FXML
	TextField nationalIDTextField;
	@FXML
	TextField emailTextField;
	@FXML
	TextField phoneTextField;
	@FXML
	private RadioButton rbtn1,rbtn2;
	
	public void createacc(ActionEvent e) throws InterruptedException, IOException {
	    String uname = nameTextField.getText();
	    String pass = passTextField.getText();
	    String address = addressTextField.getText();
	    String nationalID = nationalIDTextField.getText();
	    String email = emailTextField.getText();
	    String phone = phoneTextField.getText();
	    if(uname.isBlank() || pass.isBlank() || address.isBlank() || nationalID.isBlank() || email.isBlank() || phone.isBlank()) {
	    	wrong.setText("Please fill in the data");
	    	wrong.setTextFill(Color.RED);
	    }
	    else {
	    	if(!rbtn1.isSelected() && !rbtn2.isSelected()) {
		    	wrong.setText("Please choose an account type");
		    	wrong.setTextFill(Color.RED);
	    	}
	    	else {
	    		if(rbtn1.isSelected()) {
				    banknbe.addCustomer(uname, address, nationalID, phone, email, pass, "Checking");
			    }
				if(rbtn2.isSelected()) {
					 banknbe.addCustomer(uname, address, nationalID, phone, email, pass, "Savings");
				}

			    wrong.setText("Account created succesfully and your customer ID is "+ (banknbe.getCustCID()-1));
			    wrong.setTextFill(Color.GREEN);

			    PauseTransition pause = new PauseTransition(Duration.seconds(5));
			    pause.setOnFinished(event -> {
			        try {
			            Parent root = FXMLLoader.load(getClass().getResource("scene1.fxml"));
			            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			            Scene scene = new Scene(root);
			            stage.setScene(scene);
			            stage.show();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			    });
			    pause.play();
		    }
	    }
	}
	

	
	
	
	
	
	//Scene2 ctrl
	@FXML
	Label nameLabel;
	@FXML
	private Button logoutbtn;
	@FXML
	private AnchorPane menuScene;
	
	public void logout(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout!");
		alert.setContentText("Are you sure?");
		if(alert.showAndWait().get() == ButtonType.OK) {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
		    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		    scene = new Scene(root);
		    stage.setScene(scene);
		    stage.show();
		}
		
	}

	public void switchScene2(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchScene9(ActionEvent e) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("scene9.fxml"));
	    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void switchScene7(ActionEvent e) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("scene7.fxml"));
	    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void switchScene8(ActionEvent e) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("scene8.fxml"));
	    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void switchScene10(ActionEvent e) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("scene10.fxml"));
	    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void switchScene11(ActionEvent e) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("scene11.fxml"));
	    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void switchScene12(ActionEvent e) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("scene12.fxml"));
	    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void switchScene13(ActionEvent e) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("scene13.fxml"));
	    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void switchScene14(ActionEvent e) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("scene14.fxml"));
	    stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}

	
	
	
	
	
	//scene6 ctrl -- account id check
	@FXML
	Label please;
	@FXML
	TextField AIDTextField;

	public void checkID(ActionEvent e) throws IOException,NullPointerException {
		Customer customer;
		String aidtxt = AIDTextField.getText();
		if(aidtxt.isBlank()) {
			please.setVisible(true);
			please.setText("Please enter an account ID!");
			please.setTextFill(Color.RED);
		}
		else {
			int AID1 = Integer.valueOf(AIDTextField.getText());
			customer = banknbe.searchCustomerById(CID);
			account acc;
			acc = customer.searchAccountById(AID1);
			
			if(acc != null) {
				please.setVisible(true);
				please.setText("  Redirecting you now");
				please.setTextFill(Color.GREEN);
				setAID(AID1);
			    PauseTransition pause = new PauseTransition(Duration.seconds(5));
			    pause.setOnFinished(event -> {
			        try {
			            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
			            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			            Scene scene = new Scene(root);
			            stage.setScene(scene);
			            stage.show();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			    });
			    pause.play();
			}
			else {
				please.setVisible(true);
				please.setText("This Account ID doesn't exist");
				please.setTextFill(Color.RED);
			}
		}
	}

		

	
	
	
	//scene7 ctrl -- withdraw
	@FXML
	TextField moneyTextField;
	@FXML
	Label wrong1;
	@FXML
	Label id1;
	@FXML
	Label id2;
	@FXML
	Label id3;
	@FXML
	Label oldamount;
	@FXML
	Label newamount;
	@FXML
	Label withdraw1;
	
	public void withdraw(ActionEvent e) throws IOException,NullPointerException {
		String moneytxt = moneyTextField.getText();
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(moneytxt);
		if(moneytxt.isBlank() || matcher.find()) {
			wrong1.setVisible(true);
			wrong1.setText("Please enter an amount of money");
			wrong1.setTextFill(Color.RED);
		}
		else {
			if(wrong1.isVisible()) {
				wrong1.setVisible(false);
			}
			double money = Double.valueOf(moneyTextField.getText());
			Customer customer = banknbe.searchCustomerById(CID);
			account acc = customer.searchAccountById(AID);
			double totalAmount = acc.getBalance();
			if(totalAmount>=money) {
				
				id1.setVisible(true);
				id2.setVisible(true);
				id3.setVisible(true);
				oldamount.setVisible(true);
				newamount.setVisible(true);
				withdraw1.setVisible(true);
				
				double newAmount;
				acc.withdraw(money);
				newAmount = acc.getBalance();
				
				oldamount.setText(String.valueOf(totalAmount));
				newamount.setText(String.valueOf(newAmount));
				withdraw1.setText(String.valueOf(money));
				
				PauseTransition pause = new PauseTransition(Duration.seconds(20));
			    pause.setOnFinished(event -> {
			        try {
			            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
			            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			            Scene scene = new Scene(root);
			            stage.setScene(scene);
			            stage.show();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			        catch (NullPointerException ex1) {
			        	ex1.printStackTrace();
			        }
			    });
			    pause.play();
			}
			else {
				wrong1.setVisible(true);
				wrong1.setText("Please enter a valid amount of money");
				wrong1.setTextFill(Color.RED);
			}
		}
		
	}
	
	
	
	
	
	
	
	
	//scene8 ctrl -- deposit
	public void deposit(ActionEvent e) throws IOException,NullPointerException {
		String moneytxt = moneyTextField.getText();
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(moneytxt);
		if(moneytxt.isBlank()) {
			wrong1.setVisible(true);
			wrong1.setText("Please enter an amount of money");
			wrong1.setTextFill(Color.RED);
		}
		else {
			if(matcher.find()) {
				wrong1.setVisible(true);
				wrong1.setText("Please enter a valid input");
				wrong1.setTextFill(Color.RED);
			}
			else {
				if(wrong1.isVisible()) {
					wrong1.setVisible(false);
				}
				double money = Double.valueOf(moneyTextField.getText());
				Customer customer = banknbe.searchCustomerById(CID);
				account acc = customer.searchAccountById(AID);
				double totalAmount = acc.getBalance();
				double newAmount;
				acc.deposit(money);
				newAmount = acc.getBalance();
				
				id1.setVisible(true);
				id2.setVisible(true);
				id3.setVisible(true);
				oldamount.setVisible(true);
				newamount.setVisible(true);
				withdraw1.setVisible(true);	
					
				oldamount.setText(String.valueOf(totalAmount));
				newamount.setText(String.valueOf(newAmount));
				withdraw1.setText(String.valueOf(money));
				PauseTransition pause = new PauseTransition(Duration.seconds(20));
			    pause.setOnFinished(event -> {
			        try {
			            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
			            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			            Scene scene = new Scene(root);
			            stage.setScene(scene);
			            stage.show();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			        catch (NullPointerException ex1) {
			        	ex1.printStackTrace();
			        }
			    });
			    pause.play();
			}
		}
	}
	
	
	
	
	
	
	
	
	//scene9 ctrl -- check details
	@FXML
	Label currentBalance;
	@FXML
	Label clientName;
	@FXML
	Label clientAdd;
	@FXML
	Label clientNID;
	@FXML
	Label clientCID;
	@FXML
	Label clientAType;
	@FXML
	Label clientEmail;
	@FXML
	Label clientPNB;
	@FXML
	Label clientPw;
	@FXML
	Label clientAID;
	
	public void checkBalance(ActionEvent e) throws IOException {
		Customer customer = banknbe.searchCustomerById(CID);
		account acc = customer.searchAccountById(AID);
		
		double totalAmount = acc.getBalance();
		String name = customer.getName();
		String add = customer.getAddress();
		String natid = customer.getNationalId();
		String cid = String.valueOf(getCID());
		String emailadd = customer.getEmailAddress();
		String pnb = customer.getPhoneNumber();
		String pw = customer.getPassword();
		String aid = String.valueOf(acc.getAccountId());
		String acctype = acc.getAccountType();
		
		currentBalance.setText(String.valueOf(totalAmount));
		clientName.setText(name);
		clientAdd.setText(add); 
		clientNID.setText(natid);
		clientCID.setText(cid);
		clientAType.setText(acctype);
		clientEmail.setText(emailadd);
		clientPNB.setText(pnb);
		clientPw.setText(pw);
		clientAID.setText(aid);
		
		currentBalance.setVisible(true);
		clientName.setVisible(true);
		clientAdd.setVisible(true);
		clientNID.setVisible(true);
		clientCID.setVisible(true);
		clientAType.setVisible(true);
		clientEmail.setVisible(true);
		clientPNB.setVisible(true);
		clientPw.setVisible(true);
		clientAID.setVisible(true);
		
		PauseTransition pause = new PauseTransition(Duration.seconds(20));
	    pause.setOnFinished(event -> {
	        try {
	            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
	            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	            Scene scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        catch (NullPointerException ex1) {
	        	ex1.printStackTrace();
	        }
	    });
	    pause.play();
		
	}

	
	
	
	
	
	//scene10 ctrl -- make loan
	@FXML
	TextField moneyTextField12;
	@FXML
	TextField monthsTextField1;
	@FXML
	TextField loantypeTextField;
	@FXML
	Label id111;
	@FXML
	Label id112;
	@FXML
	Label id113;
	@FXML
	Label id114;
	@FXML
	Label id115;
	@FXML
	Label oldAmount;
	@FXML
	Label newAmount;
	@FXML
	Label loanAmount;
	@FXML
	Label amountDue;
	@FXML
	Label dueDate;
	@FXML
	Label loanDone;
	@FXML
	Label newAmount12;
	@FXML
	Label wrong5;
	
	public void loan(ActionEvent e) throws IOException, NullPointerException{
		String moneytxt = moneyTextField12.getText();
		String monthstxt = monthsTextField1.getText();
		String type = loantypeTextField.getText();
		if(moneytxt.isBlank() || monthstxt.isBlank() || type.isBlank()) {
			wrong5.setVisible(true);
			wrong5.setText("Please fill in the data");
			wrong5.setTextFill(Color.RED);
		}
		else {
			Pattern pattern = Pattern.compile("[^0-9]");
			Matcher matcher = pattern.matcher(moneytxt);
			Pattern pattern1 = Pattern.compile("[^0-9]");
			Matcher matcher1 = pattern1.matcher(monthstxt);
			if(matcher.find() || matcher1.find()){
				wrong5.setVisible(true);
				wrong5.setText("Please enter a valid input");
				wrong5.setTextFill(Color.RED);
			}
			else {
				if(wrong5.isVisible()) {
					wrong5.setVisible(false);
				}
				double money = Double.valueOf(moneyTextField12.getText());
				int months = Integer.valueOf(monthsTextField1.getText());
				LocalDate locDate = LocalDate.now();
				Customer customer = banknbe.searchCustomerById(CID);
				account acc = customer.searchAccountById(AID);
				double oldAmount1 = acc.getBalance();
				acc.makeLoan(money, months, locDate, type);
				double newAmount1 = acc.getBalance();

				oldAmount.setText(String.valueOf(oldAmount1));	
				newAmount.setText(String.valueOf(newAmount1));	
				loanAmount.setText(String.valueOf(money));	
				amountDue.setText(String.valueOf(Math.ceil(money + money*0.005*months)));
				dueDate.setText(String.valueOf(locDate.plusMonths(months)));
				
				id111.setVisible(true);
				id112.setVisible(true);
				id113.setVisible(true);
				id114.setVisible(true);
				id115.setVisible(true);
				loanDone.setVisible(true);
				oldAmount.setVisible(true);
				newAmount.setVisible(true);
				loanAmount.setVisible(true);	
				amountDue.setVisible(true);	
				dueDate.setVisible(true);
				newAmount12.setVisible(true);
				newAmount12.setText(String.valueOf((acc.getLid()-1)));
				
				
				PauseTransition pause = new PauseTransition(Duration.seconds(20));
			    pause.setOnFinished(event -> {
			        try {
			            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
			            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			            Scene scene = new Scene(root);
			            stage.setScene(scene);
			            stage.show();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        } catch(NullPointerException ex1) {
			        	ex1.printStackTrace();
			        }
			    });
			    pause.play();
			}
		}
	}
	
	
	
	
	
	
	
	//scene11 ctrl -- load id check
	@FXML
	TextField loanIDTextField;
	@FXML
	Label balance12;
	@FXML
	Label balance122;
	@FXML
	Label balance123;
	@FXML
	Button btnokxx;

	public void checkLID(ActionEvent e) throws IOException {
		Customer customer;
		int LID = Integer.valueOf(loanIDTextField.getText());
		customer = banknbe.searchCustomerById(CID);
		account acc = customer.searchAccountById(AID);
		Loan loan = acc.searchLoanByID(LID);
		
		if(loan != null) {
			if(balance123.isVisible()) {
				balance123.setVisible(false);
			}
			double dueAmountx = loan.getOutstandingBalance(); 
			balance12.setVisible(true);
			balance12.setTextFill(Color.GREEN);
			balance122.setText(String.valueOf(dueAmountx));
			balance122.setVisible(true);
			setLID(LID);
			btnokxx.setVisible(true);
		}
		else {
			balance123.setVisible(true);
			balance123.setText("This Loan ID doesn't exist");
			balance123.setTextFill(Color.RED);
		}
	}
	
	public void proceed(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("scene12.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
		
	
	
	
	
	
	
	
	//scene12 ctrl -- pay loan 
	@FXML
	TextField moneyTextField20;
	@FXML
	Label paymentOK;
	@FXML
	Label curBalance;
	@FXML
	Label amDue;
	@FXML
	Label amStatus;
	
	public void payLoan(ActionEvent e) throws IOException, NullPointerException {
		Customer customer;
		customer = banknbe.searchCustomerById(CID);
		account acc = customer.searchAccountById(AID);
		Loan loan = acc.searchLoanByID(LID);
		double amountdue = loan.getOutstandingBalance();
		double money = Double.valueOf(moneyTextField20.getText());
		LocalDate date1 = LocalDate.now();
		double currentmoney = acc.getBalance();
		
		if(money>0 && money<=amountdue && money<=currentmoney) {
			loan.makePayment(money);
			paymentOK.setVisible(true);
			paymentOK.setText("Payment Successfully, redirecting you to main menu");
			paymentOK.setTextFill(Color.GREEN);
			acc.setBalance(acc.getBalance()-money);
			curBalance.setText(String.valueOf(acc.getBalance()));
			amDue.setText(String.valueOf(loan.getOutstandingBalance()));
			amStatus.setText(String.valueOf(loan.checkLoanStatus(date1)));
			
			PauseTransition pause = new PauseTransition(Duration.seconds(20));
		    pause.setOnFinished(event -> {
		        try {
		            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
		            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		            Scene scene = new Scene(root);
		            stage.setScene(scene);
		            stage.show();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    });
		    pause.play();
		}
		else {
			paymentOK.setVisible(true);
			paymentOK.setTextFill(Color.RED);
			paymentOK.setText("Invalid amount of money!");
		}
	}
	
	
	
	
	
	
	
	
	
	//scene13 ctrl -- create account
	@FXML
	Label wrong22;
	
	public void createacc22(ActionEvent e)throws IOException{
		if(rbtn1.isSelected()) {
			Customer customer = banknbe.searchCustomerById(CID);
			int lastAID = customer.getAccID();
			customer.addAccount("Checking");
			wrong22.setText("Account created successfully and the new account id is "+ lastAID );
			wrong22.setTextFill(Color.GREEN);
			PauseTransition pause = new PauseTransition(Duration.seconds(20));
		    pause.setOnFinished(event -> {
		        try {
		            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
		            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		            Scene scene = new Scene(root);
		            stage.setScene(scene);
		            stage.show();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    });
		    pause.play();
	    }
		if(rbtn2.isSelected()) {

			Customer customer = banknbe.searchCustomerById(CID);
			int lastAID = customer.getAccID();
			customer.addAccount("Savings");
			wrong22.setText("Account created successfully and the new account id is "+ lastAID );
			wrong22.setTextFill(Color.GREEN);
			PauseTransition pause = new PauseTransition(Duration.seconds(10));
		    pause.setOnFinished(event -> {
		        try {
		            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
		            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		            Scene scene = new Scene(root);
		            stage.setScene(scene);
		            stage.show();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    });
		    pause.play();
		}
		if( !rbtn1.isSelected() && !rbtn2.isSelected()){
			wrong22.setText("Please choose an account type");
			wrong22.setTextFill(Color.RED);
		}
	}
	
	
	
	
	
	
	
	
	//scene14 ctrl -- transfer money
	@FXML
	TextField cidTextField;
	@FXML
	TextField aidTextField;
	@FXML
	TextField moneyamTextField;
	@FXML
	Label wrong13;
	
	public void send(ActionEvent e)throws IOException,NumberFormatException{
		String cidtxt = cidTextField.getText();
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(cidtxt);
		if(cidtxt.isBlank()) {
			wrong13.setText("Please enter customer ID!");
			wrong13.setTextFill(Color.RED);
		}
		if(matcher.find()) {
			wrong13.setText("Please enter a valid input!");
			wrong13.setTextFill(Color.RED);
		}
		int cid13 = Integer.valueOf(cidTextField.getText());
		if(!banknbe.existAccount(cid13) || cid13==getCID()) {
			wrong13.setText("Invalid customer ID!");
			wrong13.setTextFill(Color.RED);
		}
		else {
			String aidtxt = aidTextField.getText();
			Pattern pattern1 = Pattern.compile("[^0-9]");
			Matcher matcher1 = pattern1.matcher(aidtxt);
			if(aidtxt.isBlank()) {
				wrong13.setText("Please enter account ID!");
				wrong13.setTextFill(Color.RED);
			}
			if(matcher1.find()) {
				wrong13.setText("Please enter a valid input!");
				wrong13.setTextFill(Color.RED);
			}
			int aid13 = Integer.valueOf(aidTextField.getText());
			Customer customer = banknbe.searchCustomerById(cid13);
			if(!customer.existAccountID(aid13)) {
				wrong13.setText("Invalid account ID!");
				wrong13.setTextFill(Color.RED);
			}
			else {//acc dh elreceiver, acc2 dh elsender
				String moneytxt = moneyamTextField.getText();
				Pattern pattern3 = Pattern.compile("[^0-9]");
				Matcher matcher3 = pattern3.matcher(moneytxt);
				if(moneytxt.isBlank()) {
					wrong13.setText("Please enter an amount of money!");
					wrong13.setTextFill(Color.RED);
				}
				if(matcher3.find()) {
					wrong13.setText("Please enter a valid input!");
					wrong13.setTextFill(Color.RED);
				}
				double moneyam13 = Double.valueOf(moneyamTextField.getText());
				Customer customer2 = banknbe.searchCustomerById(getCID());
				account acc2 = customer2.searchAccountById(getAID());
				account acc = customer.searchAccountById(aid13);
				if(acc2.getBalance()<moneyam13 || moneyam13<0) {
					wrong13.setText("insufficient amount of money!");
					wrong13.setTextFill(Color.RED);
				}
				else {
					acc.TransferFunds(acc2, acc, moneyam13);
					wrong13.setText("Funds transferred successfully!");
					wrong13.setTextFill(Color.GREEN);
					
					PauseTransition pause = new PauseTransition(Duration.seconds(10));
				    pause.setOnFinished(event -> {
				        try {
				            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
				            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
				            Scene scene = new Scene(root);
				            stage.setScene(scene);
				            stage.show();
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
				    });
				    pause.play();
				}
			}
		}
	}

	
}