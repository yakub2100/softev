import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Button;


public class Main extends JFrame{
	private JTextField titleText;
	private JTextArea tfText;
	public Main() {
		//setting up main frame
		super("GIFT Editor 2000");
		this.setSize(1000,500);
		this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.setVisible( true );
		
		//Making GUI components
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//tabbedPane.addTab("Trye-False", new JTab());
		getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		Panel trueFalse = new Panel();
		tabbedPane.addTab("True-False", null, trueFalse, null);
		trueFalse.setLayout(new MigLayout("", "[][grow][]", "[][186.00][][][][][][][][][]"));
		
		JLabel lblQuestionTitle = new JLabel("Question Title (Optional)");
		trueFalse.add(lblQuestionTitle, "cell 0 0,alignx trailing");
		
		titleText = new JTextField();
		trueFalse.add(titleText, "cell 1 0,growx,aligny top,span 3");
		titleText.setColumns(10);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setHorizontalAlignment(SwingConstants.RIGHT);
		trueFalse.add(lblQuestion, "cell 0 1,alignx right,aligny top");
		
		tfText =new JTextArea (5,20);
		
		JScrollPane scrollPane = new JScrollPane(tfText);
		trueFalse.add(scrollPane, "cell 1 1,span 3,grow");
		
		JLabel lblCorrectAnswer = new JLabel("  Correct Answer");
		trueFalse.add(lblCorrectAnswer, "flowx,cell 1 2");
		
		JButton btnCancelclearQuestionText = new JButton("Cancel/Clear Question Text");
		trueFalse.add(btnCancelclearQuestionText, "cell 2 2");
		
		ButtonGroup rb1 = new ButtonGroup();		
		JRadioButton rdbtnTrue = new JRadioButton("True");	
		JRadioButton rdbtnFalse = new JRadioButton("False");
		rdbtnFalse.setBackground(trueFalse.getBackground());
		rdbtnTrue.setBackground(trueFalse.getBackground());
		rb1.add(rdbtnFalse);
		rb1.add(rdbtnTrue);
		trueFalse.add(rdbtnTrue, "flowx,cell 1 3");
		trueFalse.add(rdbtnFalse, "cell 1 3");
		
		JButton btnSave = new JButton("Save");
		trueFalse.add(btnSave, "cell 0 4,alignx center,aligny center");
		
		JButton btnAddAnotherTruefalse = new JButton("Add Another True/False Question");
		trueFalse.add(btnAddAnotherTruefalse, "cell 1 10,alignx center");
		//clear text action
		btnCancelclearQuestionText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				tfText.setText("");		
			}
		});
		
		Panel matching = new Panel();
		tabbedPane.addTab("Matching", null, matching, null);
		matching.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel multipleChoice = new JPanel();
		tabbedPane.addTab("Multiple Choice", null, multipleChoice, null);
		multipleChoice.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel shortAns = new JPanel();
		tabbedPane.addTab("Short Answer", null, shortAns, null);
		shortAns.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel numer = new JPanel();
		tabbedPane.addTab("Numerical", null, numer, null);
		numer.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel calc = new JPanel();
		tabbedPane.addTab("Calculated", null, calc, null);
		calc.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel essay = new JPanel();
		tabbedPane.addTab("Essey", null, essay, null);
		essay.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel desc = new JPanel();
		tabbedPane.addTab("Description", null, desc, null);
		desc.setLayout(new MigLayout("", "[]", "[]"));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Main();

	}

}
