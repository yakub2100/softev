import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;

import java.awt.Color;
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;


public class Main extends JFrame{
	private JTextField titleText;
	private JTextField titleTextMC;
	private JTextArea tfText;
	private JTextArea mcText;
	private int row =4,matchRow=2, matchRowlbl=1;
	private JPanel multipleChoice;
	private JPanel matching;
	private ArrayList<JTextField> multiFields1;
	private ArrayList<JTextField> multiFields2;
	private ArrayList<JTextField> matchFields1;
	private ArrayList<JTextField> matchFields2;
	private JTextField matchTitle;
	
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
		Color bgr = trueFalse.getBackground();
		
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
		rdbtnFalse.setBackground(bgr);
		rdbtnTrue.setBackground(bgr);
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
		
		matching = new JPanel();
		matching.setBackground(bgr);
		tabbedPane.addTab("Matching", null, matching, null);
		matching.setLayout(new MigLayout("", "[][20.00][grow][20.00][grow][]", "[][][][][][][][][][][][]"));
		
		JLabel lblQuestionTitleoptional = new JLabel("Question Title (Optional)");
		matching.add(lblQuestionTitleoptional, "cell 0 0,alignx trailing");
		
		matchTitle = new JTextField();
		matching.add(matchTitle, "cell 1 0,growx,spanx 6");
		matchTitle.setColumns(10);
		
		JButton btnAddQa = new JButton("Add Q&A");
		matching.add(btnAddQa, "cell 0 2,alignx center");
		
		JButton btnSave_2 = new JButton("Save");
		matching.add(btnSave_2, "cell 0 "+matchRow+2+",alignx center");
		
		JButton btnAddAnotherMatching = new JButton("Add Another Matching Question");
		matching.add(btnAddAnotherMatching, "cell 2 "+matchRow+5+",alignx center");
		//add questions action
		btnAddQa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				matchFields1 = new ArrayList<JTextField>();
				matchFields2 = new ArrayList<JTextField>();
				
				JLabel label = new JLabel(matchRowlbl+"");
				matching.add(label, "cell 1 "+matchRow+",alignx trailing");
				
				JTextField field1 = new JTextField();
				matching.add(field1, "cell 2 "+matchRow+",growx");
				field1.setColumns(10);
				int t = matchRowlbl+1;
				JLabel label_1 = new JLabel(t+"");
				matching.add(label_1, "cell 3 "+matchRow+",alignx trailing");
				
				JTextField field2 = new JTextField();
				matching.add(field2, "cell 4 "+matchRow+",growx");
				field2.setColumns(10);
						
				repaint();
				matchRow++;
				matchRowlbl+=2;
				matchFields1.add(field1);
				matchFields2.add(field2);
			}
		});
				
		multipleChoice = new JPanel();
		tabbedPane.addTab("Multiple Choice", null, multipleChoice, null);
		multipleChoice.setLayout(new MigLayout("", "[][805.00,grow][grow]", "[][186.00][][][][][][][][][]"));
		multipleChoice.setBackground(bgr);
		JLabel lblQuestionTitleMC = new JLabel("Question Title (Optional)");
		multipleChoice.add(lblQuestionTitleMC, "cell 0 0,alignx trailing");
		titleTextMC = new JTextField();
		multipleChoice.add(titleTextMC, "cell 1 0,growx,aligny top,span 3");
		titleTextMC.setColumns(10);
		JLabel lblQuestionMC = new JLabel("Question");
		lblQuestionMC.setHorizontalAlignment(SwingConstants.RIGHT);
		multipleChoice.add(lblQuestionMC, "cell 0 1,alignx right,aligny top");
		
		mcText =new JTextArea (5,20);
		
		JScrollPane scrollPaneMC = new JScrollPane(mcText);
		multipleChoice.add(scrollPaneMC, "cell 1 1,span 3,grow");
		
		JButton btnChancelclearQuestionText = new JButton("Chancel/Clear Question Text");
		multipleChoice.add(btnChancelclearQuestionText, "cell 1 2 1 2,alignx right,spanx 2");
		
		JButton btnAddAnswer = new JButton("Add Answer");
		multipleChoice.add(btnAddAnswer, "cell 0 4,alignx center");
		
		
		
		JButton btnSave_1 = new JButton("Save");
		multipleChoice.add(btnSave_1, "cell 0 "+row+2+",alignx center");
		
		JButton btnAddAnotherMultiple = new JButton("Add Another Multiple Choice Question");
		multipleChoice.add(btnAddAnotherMultiple, "cell 1 "+row+5+",alignx center");
		//clear text action
		btnChancelclearQuestionText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				mcText.setText("");		
			}
		});
		
		btnAddAnswer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				multiFields1 = new ArrayList<JTextField>();
				multiFields2 = new ArrayList<JTextField>();
				JTextField field1 = new JTextField();
				multipleChoice.add(field1, "cell 1 "+row+",grow");
				field1.setColumns(10);
				
				JTextField field2 = new JTextField();
				multipleChoice.add(field2, "cell 2 "+row+",grow");
				field2.setColumns(10);
				repaint();
				row++;
				multiFields1.add(field1);
				multiFields2.add(field2);
			}
		});
		
		
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
