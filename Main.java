import java.awt.event.*;
import javax.swing.*;
import java.time.*;
public class Main{
	public static void main(String[] args){
		JFrame frame=new JFrame("Sommer- & Winterzeit Rechner");
			frame.setSize(168,204);
			frame.setResizable(false);
			frame.setVisible(true);
			
		JLabel header=new JLabel("Sommer- & Winterzeit");
			frame.add(header);
			header.setHorizontalAlignment(JTextField.CENTER);
			header.setBounds(6,12,156,24);
			
		JLabel subheader=new JLabel("Rechner");
			frame.add(subheader);
			subheader.setHorizontalAlignment(JTextField.CENTER);
			subheader.setBounds(6,30,156,24);
			
		JLabel times=new JLabel("Clk   Std      Min   Lck");
			frame.add(times);
			times.setHorizontalAlignment(JTextField.CENTER);
			times.setBounds(12,66,144,24);

    	JCheckBox clock = new JCheckBox("");
			frame.add(clock);
			clock.setBounds(12,84,24,24);

		JTextField hour = new JTextField("");
			frame.add(hour);
			hour.setHorizontalAlignment(JTextField.CENTER);
			hour.setBounds(48,84,24,24);

		JLabel colon = new JLabel(":");
			colon.setHorizontalAlignment(JTextField.CENTER);
			colon.setBounds(84,102,24,24);
			frame.add(colon);
			
		JTextField minute=new JTextField("");
			frame.add(minute);
			minute.setHorizontalAlignment(JTextField.CENTER);
			minute.setBounds(96,84,24,24);
			
		JCheckBox lock = new JCheckBox("");
			frame.add(lock);
			lock.setBounds(132,84,24,24);
			
		JButton winterzeitButton=new JButton("Winterzeit");
			frame.add(winterzeitButton);
			winterzeitButton.setBounds(12,130,144,24);
			winterzeitButton.setEnabled(false);
			
		JButton sommerzeitButton=new JButton("Sommerzeit");
			frame.add(sommerzeitButton);
			sommerzeitButton.setBounds(12,154,144,24);
			sommerzeitButton.setEnabled(false);
		
		clock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(clock.isSelected()){
					hour.setText(Integer.toString(LocalDateTime.now().getHour()));
					minute.setText(Integer.toString(LocalDateTime.now().getMinute()));
					minute.setEditable(false);
					hour.setEditable(false);
				}else if(!clock.isSelected()){
					hour.setText("");
					minute.setText("");
					minute.setEditable(true);
					hour.setEditable(true);			 
				};
			};
		});

		KeyAdapter hourField = new KeyAdapter() {
			public void keyTyped(KeyEvent keypress) {
				char character = keypress.getKeyChar();
				if(((character<'0')||(character>'9'))&&(character!=KeyEvent.VK_BACK_SPACE)){
					keypress.consume();
				};
				if(hour.getText().length()==0){
					if(character>'2'){
						keypress.consume();
					};
				};
				if(hour.getText().length()==1){
				if(Integer.parseInt(hour.getText())==2){
					if(character>'4'){
						keypress.consume();
					};
			       };
				};
				if(hour.getText().length()>=2){
					keypress.consume();
				};
			};
		};
    	hour.addKeyListener(hourField);
    
		KeyAdapter minuteField = new KeyAdapter() {
			public void keyTyped(KeyEvent keypress) {
				char character = keypress.getKeyChar();
				if(((character<'0')||(character>'9'))&&(character!=KeyEvent.VK_BACK_SPACE)){
					keypress.consume();
				};
				if(minute.getText().length()==0){
					if(character>'5'){
						keypress.consume();
					};
				};
				if(minute.getText().length()>=2){
					keypress.consume();
				};
			};
		};
		minute.addKeyListener(minuteField);
				
		lock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(lock.isSelected()){
					hour.setEditable(false);
					minute.setEditable(false);
					clock.setEnabled(false);
					sommerzeitButton.setEnabled(true);
					winterzeitButton.setEnabled(true);
					while(hour.getText().length()<2){
						hour.setText("0" + hour.getText());
					};
					while(minute.getText().length()<2){
						minute.setText("0" + minute.getText());
					};
				};
				if(!lock.isSelected()){
					if(!clock.isSelected()){
						hour.setEditable(true);
						minute.setEditable(true);
						hour.setText("");
						minute.setText("");
					}else{
						hour.setText(Integer.toString(LocalDateTime.now().getHour()));
						minute.setText(Integer.toString(LocalDateTime.now().getMinute()));
					};
					clock.setEnabled(true);
					sommerzeitButton.setEnabled(false);
					winterzeitButton.setEnabled(false);
				};
			};
		});

		winterzeitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				while(hour.getText().length()<2){
					hour.setText("0" + hour.getText());
				};
				if(Integer.parseInt(hour.getText())==0){
					hour.setText("24");
				};
				hour.setText(Integer.toString(Integer.parseInt(hour.getText()) - 1));
				winterzeitButton.setEnabled(false);
				sommerzeitButton.setEnabled(true);
			};
		});
		
		sommerzeitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				while(hour.getText().length()<2){
					hour.setText("0" + hour.getText());
				};
				if(Integer.parseInt(hour.getText())==24){
					hour.setText("0");
				};
				hour.setText(Integer.toString(Integer.parseInt(hour.getText()) + 1));
				winterzeitButton.setEnabled(true);
				sommerzeitButton.setEnabled(false);
			};
		});
	};
};