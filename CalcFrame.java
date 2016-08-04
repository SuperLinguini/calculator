//Milind Lingineni Period 4 APCS Calculator Project
//import the necessary classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcFrame extends JFrame
{
	private double first;
	private double second;
	private boolean entered;

	private String operator;

	private String[] names = {"7", "8", "9", "X", "4", "5", "6", "/", "1", "2", "3", "-", ".", "0", "=", "+"};
	private JButton[] buttons;

	private JTextField number;

	private Container contain;

	public static void main(String[] args)
	{
		CalcFrame frame = new CalcFrame();
		frame.setVisible(true);
	}

	public CalcFrame()
	{
		super("Calculator");

		//creates the keyboard and button listener
		CalcListener listener = new CalcListener();

		//initializes the instance fields
		first = 0;
		second = 0;
		operator = "";
		entered = false;

		//establishes the properties of the text field
		number = new JTextField("0");
		number.setBounds(20, 20, 260, 45);
		number.setEditable(false);
		number.setFont(new Font("Arial", Font.BOLD, 20));
		number.setHorizontalAlignment(JTextField.RIGHT);
		number.setBackground(new Color(0, 223, 252));
		add(number, BorderLayout.NORTH);

		//set the frame properties
		setSize(300,430);
		setResizable(false);
		setTitle("Calculator");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setLayout(null);
		setFocusable(true);

		//gets the container and adds the key listener
		contain = getContentPane();
		contain.setBackground(new Color(0, 95, 107));
		addKeyListener(listener);

		Color borderColor = new Color(0, 140, 158);
		Color buttonColor = new Color(0, 180, 204);

		//creates a button JPanel for the digits and opertators and sets its properties
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
		buttonPanel.setBounds(20,70,260,250);
		buttons = new JButton[names.length];
		//creates and adds the buttons, also adds the ActionListener
		for(int i = 0; i < names.length; i++)
		{
			buttons[i] = new JButton(names[i]);
			buttons[i].setBackground(buttonColor);
			buttons[i].addActionListener(listener);
			buttonPanel.add(buttons[i]);
		}
		buttonPanel.setBackground(borderColor);
		buttonPanel.setBorder(BorderFactory.createMatteBorder(5,5,5,3, borderColor));
		add(buttonPanel);

		//Creates the button JPanel for the C and CE buttons and set its properties
		JPanel clearButtonPanel = new JPanel();
		clearButtonPanel.setLayout(new GridLayout(1, 2, 5, 5));
		clearButtonPanel.setBounds(20, 315, 260, 60);
		JButton cButton = new JButton("C");
		JButton cEButton = new JButton("CE");
		cButton.setBackground(buttonColor);
		cEButton.setBackground(buttonColor);
		cButton.addActionListener(listener);
		cEButton.addActionListener(listener);
		clearButtonPanel.add(cButton);
		clearButtonPanel.add(cEButton);
		clearButtonPanel.setBackground(borderColor);
		clearButtonPanel.setBorder(BorderFactory.createMatteBorder(5,5,5,3, borderColor));
		add(clearButtonPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public double getNumber()
	{
		return Double.parseDouble(number.getText());
	}

	private class CalcListener implements ActionListener, KeyListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() instanceof JButton)
			{
				JButton clickedButton = (JButton) event.getSource();

				String buttonText = clickedButton.getText();

				if(entered)
				{
					first = getNumber();
					number.setText("0");
					entered = false;

					if(buttonText.equals("X"))
					{
						operator = "multiply";
						number.setText("0");
					}
					else if(buttonText.equals("/"))
					{
						operator = "divide";
						number.setText("0");
					}
					else if(buttonText.equals("-"))
					{
						operator = "subtract";
						number.setText("0");
					}
					else if(buttonText.equals("+"))
					{
						operator = "add";
						number.setText("0");
					}
					else if(buttonText.equals("="))
					{
						second = getNumber();
						number.setText("" + Calc.calculate(first, second, operator));
						entered = true;
					}
				}
				else
				{
					if(buttonText.equals("X"))
					{
						first = getNumber();
						operator = "multiply";
						number.setText("0");
					}
					else if(buttonText.equals("/"))
					{
						first = getNumber();
						operator = "divide";
						number.setText("0");
					}
					else if(buttonText.equals("-"))
					{
						first = getNumber();
						operator = "subtract";
						number.setText("0");
					}
					else if(buttonText.equals("+"))
					{
						first = getNumber();
						operator = "add";
						number.setText("0");
					}
				}


				for(int i = 0; i < 10; i++)
				{
					if(buttonText.equals("" + i))
						if(number.getText().equals("0"))
							number.setText("");
				}

				if(buttonText.equals("."))
					if(number.getText().equals("0"))
						number.setText("");

				for(int i = 0; i < 10; i++)
				{
					if(buttonText.equals("" + i))
						number.setText(number.getText() + buttonText);
				}

				if(buttonText.equals("."))
				{
					int count = 0;
					String num = number.getText();
					for(int i = 0; i < num.length(); i++)
					{
						if(num.substring(i, i+1).equals("."))
							count++;
					}
					if(count == 0)
						number.setText(number.getText() + ".");
				}

				else if(buttonText.equals("C"))
				{
					first = 0;
					second = 0;
					number.setText("0");
				}
				else if(buttonText.equals("CE"))
				{
					second = 0;
					number.setText("0");
				}

				else if(buttonText.equals("="))
				{
					second = getNumber();
					number.setText("" + Calc.calculate(first, second, operator));
					entered = true;
				}
			}
			requestFocusInWindow();
		}

		public void keyPressed(KeyEvent event)
		{
			char typed = event.getKeyChar();

			if(entered)
			{
				if(event.getKeyCode() != KeyEvent.VK_SHIFT)
				{
					first = getNumber();
					number.setText("0");
					entered = false;
				}

				if(typed == 'X' || typed == 'x' || typed == '*')
				{
					operator = "multiply";
					number.setText("0");
				}
				else if(typed == '/')
				{
					operator = "divide";
					number.setText("0");
				}
				else if(typed == '-')
				{
					operator = "subtract";
					number.setText("0");
				}
				else if(typed == '+')
				{
					operator = "add";
					number.setText("0");
				}
				else if(typed == '=')
				{
					second = getNumber();
					number.setText("" + Calc.calculate(first, second, operator));
					entered = true;
				}
			}
			else
			{
				if(typed == 'X' || typed == 'x' || typed == '*')
				{
					first = getNumber();
					operator = "multiply";
					number.setText("0");
				}
				else if(typed == '/')
				{
					first = getNumber();
					operator = "divide";
					number.setText("0");
				}
				else if(typed == '-')
				{
					first = getNumber();
					operator = "subtract";
					number.setText("0");
				}
				else if(typed == '+')
				{
					first = getNumber();
					operator = "add";
					number.setText("0");
				}
			}

			if(typed == '1' || typed == '2' || typed == '3' || typed == '4' || typed == '5' || typed == '6' || typed == '7' || typed == '8' || typed == '9' || typed == '0' || typed == '.')
				if(number.getText().equals("0"))
					number.setText("");

			if(typed == '1')
				number.setText(number.getText() + 1);
			else if(typed == '2')
				number.setText(number.getText() + 2);
			else if(typed == '3')
				number.setText(number.getText() + 3);
			else if(typed == '4')
				number.setText(number.getText() + 4);
			else if(typed == '5')
				number.setText(number.getText() + 5);
			else if(typed == '6')
				number.setText(number.getText() + 6);
			else if(typed == '7')
				number.setText(number.getText() + 7);
			else if(typed == '8')
				number.setText(number.getText() + 8);
			else if(typed == '9')
				number.setText(number.getText() + 9);
			else if(typed == '0')
				number.setText(number.getText() + 0);
			else if(typed == '.')
			{
				int count = 0;
				String num = number.getText();
				for(int i = 0; i < num.length(); i++)
				{
					if(num.substring(i, i+1).equals("."))
						count++;
				}
				if(count == 0)
					number.setText(number.getText() + ".");
			}

			else if(typed == '=')
			{
				second = getNumber();
				number.setText("" + Calc.calculate(first, second, operator));
				entered = true;
			}
			else if(typed == 'c' || typed == 'C')
			{
				first = 0;
				second = 0;
				number.setText("0");
			}

			else if (event.getKeyCode() == KeyEvent.VK_ENTER)
	        {
				second = getNumber();
				number.setText("" + Calc.calculate(first, second, operator));
				entered = true;
	        }
        }

        public void keyTyped(KeyEvent event) {}

		public void keyReleased(KeyEvent event) {}
	}
}
