import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class GuessingGame implements ActionListener {

	private JFrame frmGuessingGame;
	public JTextField textField;
	public JTextArea textArea;
	public int stage=0;
	public long numFrom;
	public long numTo;
	public long attempts;
	public long attempts_used;
	public long randNum;
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
					GuessingGame window = new GuessingGame();
					window.frmGuessingGame.setVisible(true);
					window.greetUser();
				
			}
		});
	}

	
	public GuessingGame() {
		initialize();
		
	}

	private void initialize() {
		 frmGuessingGame =new JFrame();
		 frmGuessingGame.setForeground(new Color(0, 0, 0));
		 frmGuessingGame.setTitle("Guessing Game :)");
		 frmGuessingGame.getContentPane().setBackground(new Color(72, 61, 139));
		 frmGuessingGame.setBackground(Color.RED);
		    frmGuessingGame.setAlwaysOnTop(true);
			frmGuessingGame.setBounds(100, 100, 446, 300);
			frmGuessingGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmGuessingGame.getContentPane().setLayout(null);
			
			
			JButton btnSend = new JButton("Send");
			btnSend.setBounds(335, 216, 89, 34);
			frmGuessingGame.getContentPane().add(btnSend);
			btnSend.addActionListener(this);
			
			textField = new JTextField();
			textField.setBackground(SystemColor.controlHighlight);
			textField.setForeground(Color.BLACK);
			textField.setBounds(10, 216, 315, 32);
			frmGuessingGame.getContentPane().add(textField);
			textField.setColumns(10);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 387, 184);
			frmGuessingGame.getContentPane().add(scrollPane);
			
			textArea = new JTextArea();
			textArea.setBackground(Color.ORANGE);
			scrollPane.setViewportView(textArea);
			textArea.setEditable(false);
			}
	
	
	
	public long getRandomNumberBetween(long from,long to) {
		return from + (long) (Math.random()*(to-(from-1)));
		
		
	}
	
	public void greetUser() {
		textArea.setText("Hello there, may I have your name?");
		
	};
	public void actionPerformed (ActionEvent buttonClick) {
		switch(stage) { 
		
		
		case 0:if(textField.getText().matches("[a-zA-Z]+")) {
			textArea.setText(textArea.getText()+"\nHello "+textField.getText()+"\n\nDo I need to explain the rules to you?(Y/N)");
			stage=1;
			textField.setText(null);
			break;
			}
		else {
		textArea.setText(textArea.getText()+"\nThat names does not seem right to me.\nTry only letters.");
		textField.setText(null);
		break;}
		
		
		
		
	case 1:   if(textField.getText().matches("N|n")) {
			textArea.setText(textArea.getText()+"\nOkay then!\nNumber from:");
			stage=2;
			textField.setText(null);
			break;}
		if(textField.getText().matches("Y|y")) {
			textArea.setText(textArea.getText()+"\nIn Short, you have to give me a range of numbers(positive integers),\n"
			+ "In which you will have to guess a random generated number,\nalso how many attempts you would like to have!"
					+"\nOkay then!\nNumber from: ");
			stage=2;
			textField.setText(null);
			break;}
		else {textArea.setText(textArea.getText()+"\nNot a valid input.");
		textField.setText(null);
		break;}
		
		
		
		
		
		
	case 2:    if(textField.getText().matches("\\d+")) {
			textArea.setText(textArea.getText()+textField.getText()+"\nTo Number: ");
			numFrom=Long.parseLong(textField.getText());
			stage=3;
			textField.setText(null);
			break;}
			else {textArea.setText(textArea.getText()+"\nNot a valid input.\nNumber from: ");
			textField.setText(null);
			break;}
		
		
		
		
		
	case 3:    if(textField.getText().matches("\\d+")&&Long.parseLong(textField.getText())-1>numFrom) {
			textArea.setText(textArea.getText()+textField.getText()+"\nNumber of attempts: ");
			numTo=Long.parseLong(textField.getText());
			textField.setText(null);
			stage=4;
			break;}
		       else {try{if(Long.parseLong(textField.getText())<=(numFrom+1))
			               {textArea.setText(textArea.getText()+"\nNumber must be greater!\nTo Number: ");
		                   textField.setText(null);}}
		catch(Exception isNotNumber) {
		textArea.setText(textArea.getText()+"\nNot a valid input.\nTo Number: ");
		textField.setText(null);
		 break;}
		}break;
		
		
		
	case 4:    {if(textField.getText().matches("\\d+")&&Long.parseLong(textField.getText())<=(numTo-numFrom)&&Long.parseLong(textField.getText())!=0) {
			textArea.setText(textArea.getText()+textField.getText()+"\n Your Guess: ");
			attempts=Long.parseLong(textField.getText());
			stage=5;
			randNum = getRandomNumberBetween(numFrom,numTo);
			textField.setText(null);
			break;}
		else {try{if(Long.parseLong(textField.getText())>(numTo-numFrom)) {
			        textArea.setText(textArea.getText()+"\nYou attempts are equal to or more than\nthe possible numbers in this range!"
				    + "\nNumber of attempts: ");
			        break;}}
		catch(Exception isNotNumber1) {
		          textArea.setText(textArea.getText()+"\nNot a valid input.\nNumber of attempts: ");
			      textField.setText(null);
			      break;}}}
	
	
	
	case 5: 
		attempts_used++;
	          try{  if (Long.parseLong(textField.getText())==randNum) {
			         
		             textArea.setText(textArea.getText()+textField.getText()+"\nCongratulations!!!\nThe number was: "+Long.toString(randNum)
		             +"\nIt took you "+Long.toString(attempts_used)+" attempts!\nWould you like another game?(Y/N)");
		             textField.setText(null);
		             stage=6;
		             break;}
	   if(attempts!=1&&attempts_used!=attempts)
	         {if(Long.parseLong(textField.getText())<randNum&&Long.parseLong(textField.getText())<=numTo&&Long.parseLong(textField.getText())>=numFrom){
	        	 
		         textArea.setText(textArea.getText()+textField.getText()+"\nThe Random Number is bigger!\nYour Guess: ");
		         textField.setText(null);
		         }
	          else {if(Long.parseLong(textField.getText())>randNum&&Long.parseLong(textField.getText())<=numTo&&Long.parseLong(textField.getText())>=numFrom) {
	    	       textArea.setText(textArea.getText()+textField.getText()+"\nThe Random Number is smaller!\nYour Guess: ");
	                 textField.setText(null);
	                  }
	                else {textArea.setText(textArea.getText()+textField.getText()+"\nYour number is not in the range!\nYour Guess: ");
	                      textField.setText(null);
	                       }}}}
	catch(Exception isNotNumber2) { 
	          if(attempts_used==attempts||attempts==1){textArea.setText(textArea.getText()+textField.getText()+"\nLuck wasn't on your side this time.."
	 			     +"\nThe number was: "+Long.toString(randNum)+"\nWould you like another game?(Y/N)");
	 		         textField.setText(null);
	 		         stage=6;
	          break;}
	              textArea.setText(textArea.getText()+"\nNot a valid input.\nYour Guess: ");
	              
	              textField.setText(null);}
	          if(attempts_used==attempts||attempts==1){textArea.setText(textArea.getText()+textField.getText()+"\nLuck wasn't on your side this time.."
	 			     +"\nThe number was: "+Long.toString(randNum)+"\nWould you like another game?(Y/N)");
	 		         textField.setText(null);
	 		         stage=6;}
	          break;
	          
	
	
	 case 6:{if(textField.getText().matches("N|n")) {
			System.exit(0);}
	 if(textField.getText().matches("Y|y"))
		 textArea.setText(textArea.getText()+"\nOkay then!\nNumber from:");
	      textField.setText(null);
	      attempts_used=0;
		 stage=2;}
	}
  }
}
  



	
			
		
		
		
	


