import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;


public class Main extends JFrame{
	private JTextField titleText;
	private JTextField titleTextMC;
	private JTextArea tfText;
	private JTextArea mcText;
	private JTextArea shText;
	private JTextArea eText;
	private JTextArea nText;
	private JTextArea dText;
	private int row =6,matchRow=2, matchRowlbl=1, rowSh=4;
	private JPanel multipleChoice;
	private JPanel matching;
	private JPanel shortAns;
	private ArrayList<JTextField> multiFields1= new ArrayList<JTextField>();;
	private ArrayList<JTextField> multiFields2= new ArrayList<JTextField>();;
	private ArrayList<JTextField> matchFields1= new ArrayList<JTextField>();;
	private ArrayList<JTextField> matchFields2= new ArrayList<JTextField>();;
	private ArrayList<JTextField> shFields1 = new ArrayList<JTextField>();
	private JTextField matchTitle;
	private JTextField answerSh;
	private JTextField answerN;
	private JTextField shTitle;
	private JTextField nTitle;
	private JTextField nError;
	private JTextField dTitle;
	private JTextField eTitle;
	private JRadioButton rdbtnTrue;
	private JRadioButton rdbtnFalse;
	private JTextField multiCorrect;
	private JTextField corCom;
	private JLabel wrongLabel;
	
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
		rdbtnTrue = new JRadioButton("True");	
		rdbtnFalse = new JRadioButton("False");
		rdbtnFalse.setBackground(bgr);
		rdbtnTrue.setBackground(bgr);
		rb1.add(rdbtnFalse);
		rb1.add(rdbtnTrue);
		trueFalse.add(rdbtnTrue, "flowx,cell 1 3");
		trueFalse.add(rdbtnFalse, "cell 1 3");
		
		JButton btnSave = new JButton("Save");
		trueFalse.add(btnSave, "cell 0 4,alignx center,aligny center");
		
		//clear text action
		btnCancelclearQuestionText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				tfText.setText("");		
			}
		});
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				if(!tfText.getText().equals("")){
					if(rdbtnTrue.isSelected()||rdbtnFalse.isSelected()){
						if(rdbtnTrue.isSelected()){
							createTf(titleText.getText(),tfText.getText(),true);
						}
						else{
							createTf(titleText.getText(),tfText.getText(),false);
						}
						JOptionPane.showMessageDialog(null, "Question saved");
						tfText.setText("");
						titleText.setText("");
					}
					else{
						JOptionPane.showMessageDialog(null, "Select one of the answer buttons!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Question text must not be empty!");
				}
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
		
		//multiple choice
		multipleChoice = new JPanel();
		tabbedPane.addTab("Multiple Choice", null, multipleChoice, null);
		multipleChoice.setLayout(new MigLayout("", "[][grow][grow]", "[][186.00][][][][][][][][][][][][][][][][][]"));
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
		
		JButton btnAddAnswer = new JButton("Add Wrong Answer");
		multipleChoice.add(btnAddAnswer, "cell 0 4,alignx center");
		
		btnAddAnswer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	

				wrongLabel = new JLabel("Wrong Answer");
				multipleChoice.add(wrongLabel, "cell 0 "+row+",alignx trailing");
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
		
		JLabel lblComment = new JLabel("Comment(Optional)");
		multipleChoice.add(lblComment, "cell 2 4");
		
		JLabel lblCorrectanswer = new JLabel("Correct Answer");
		multipleChoice.add(lblCorrectanswer, "cell 0 5,alignx trailing");
		
		multiCorrect = new JTextField();
		multipleChoice.add(multiCorrect, "cell 1 5,growx");
		multiCorrect.setColumns(10);
		
		corCom = new JTextField();
		multipleChoice.add(corCom, "cell 2 5,growx");
		corCom.setColumns(10);
	
		JButton btnSave_1 = new JButton("Save");
		multipleChoice.add(btnSave_1, "cell 0 "+row+5+",alignx center");
		btnSave_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				if(!mcText.getText().equals("")&&!multiCorrect.getText().equals("")){
					createMulti(titleTextMC.getText(),mcText.getText(),multiCorrect.getText(),corCom.getText());
					JOptionPane.showMessageDialog(null, "Question saved");
					mcText.setText("");
					titleTextMC.setText("");
					multiCorrect.setText("");
					corCom.setText("");
					for(int i =0;i<multiFields1.size();i++){
						JTextField f = multiFields1.get(i);
						multipleChoice.remove(multiFields1.get(i));		
						multipleChoice.remove(multiFields2.get(i));	
					}
					multiFields1=new ArrayList<JTextField>();
					multiFields2=new ArrayList<JTextField>();
					row=6;
					multipleChoice.remove(wrongLabel);
					repaint();
				}
				else{
					JOptionPane.showMessageDialog(null, "Question and answer text must not be empty!");
				}
			}
		});
		
		//clear text action
		btnChancelclearQuestionText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				mcText.setText("");		
			}
		});
		lblQuestion.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		//Numerical tab
		JPanel numer = new JPanel();
		tabbedPane.addTab("Numerical", null, numer, null);
		numer.setLayout(new MigLayout("", "[][grow][grow]", "[][186.00][][][][][][][][][][][][]"));
		
		numer.setBackground(bgr);
		
		JLabel lblQuestionN = new JLabel("Question");
		
		JLabel lblQuestionTitleoptionalN = new JLabel("Question Title(Optional)");
		numer.add(lblQuestionTitleoptionalN, "cell 0 0,alignx trailing");
		
		nTitle = new JTextField();
		numer.add(nTitle, "cell 1 0,growx,spanx 3");
		nTitle.setColumns(10);
		numer.add(lblQuestionN, "cell 0 1,alignx right,aligny top");
		
		nText =new JTextArea (5,20);
		
		JScrollPane scrollPaneN = new JScrollPane(nText);
		numer.add(scrollPaneN, "cell 1 1,span 3,grow");
		
		JButton btnDeleteclearQuestionTextN = new JButton("Cancel/Clear Question Text");
		numer.add(btnDeleteclearQuestionTextN, "cell 2 2,alignx right");
		
		//clear text action
		btnDeleteclearQuestionTextN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				nText.setText("");		
			}
		});
		
		JLabel lblCorrectAnswerN = new JLabel("  Correct Answer");
		numer.add(lblCorrectAnswerN, "flowx,cell 0 3,alignx trailing");
		
		answerN= new JTextField();
		numer.add(answerN, "cell 1 3 2 1,growx");
		answerN.setColumns(10);
		
		JLabel lblErrorMarginoptional = new JLabel("Error Margin(optional)");
		numer.add(lblErrorMarginoptional, "cell 0 4,alignx trailing");
		
		nError = new JTextField();
		numer.add(nError, "cell 1 4");
		nError.setColumns(10);
		
		
		JButton btnSaveN= new JButton("Save");
		numer.add(btnSaveN, "cell 0 5,alignx center");
		
		btnSaveN.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				if(!nText.getText().equals("")){
					try{
						Double.parseDouble(answerN.getText());
						if(!nError.getText().equals("")){
							Double.parseDouble(nError.getText());
						}
						createNum(nTitle.getText(),nText.getText(),answerN.getText(),nError.getText());
						JOptionPane.showMessageDialog(null, "Question saved");
						nText.setText("");
						nTitle.setText("");
						nError.setText("");
						answerN.setText("");
					}
					catch(Exception e59){
						JOptionPane.showMessageDialog(null, "Answer and error must be numbers!");
					}				
				}
				else{
					JOptionPane.showMessageDialog(null, "Question text must not be empty!");
				}
			}
		});
		
		
		//essay tab
		JPanel essay = new JPanel();
		tabbedPane.addTab("Essay", null, essay, null);
		essay.setLayout(new MigLayout("", "[][grow][grow]", "[][186.00][][][][][][][][]"));
		essay.setBackground(bgr);
		
		JLabel lblQuestionE = new JLabel("Question");
		
		JLabel lblQuestionTitleoptionaE = new JLabel("Question Title(Optional)");
		essay.add(lblQuestionTitleoptionaE, "cell 0 0,alignx trailing");
		
		eTitle = new JTextField();
		essay.add(eTitle, "cell 1 0,growx,spanx 3");
		eTitle.setColumns(10);
		essay.add(lblQuestionE, "cell 0 1,alignx right,aligny top");
		
		eText =new JTextArea (5,20);
		
		JScrollPane scrollPaneE = new JScrollPane(eText);
		essay.add(scrollPaneE, "cell 1 1,span 3,grow");
		
		JButton btnDeleteclearQuestionTextE = new JButton("Cancel/Clear Question Text");
		essay.add(btnDeleteclearQuestionTextE, "cell 2 2,alignx right");
		
		btnDeleteclearQuestionTextE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				eText.setText("");
			}
		});
		
		JButton btnSaveE = new JButton("Save");
		essay.add(btnSaveE, "cell 0 4,alignx center,aligny center");
		
		btnSaveE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				if(!eText.getText().equals("")){
					createEssay(eTitle.getText(),eText.getText());
					JOptionPane.showMessageDialog(null, "Question saved");
					eText.setText("");
					eTitle.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "Question text must not be empty!");
				}
			}
		});
		
	
		//description tab
		JPanel desc = new JPanel();
		tabbedPane.addTab("Description", null, desc, null);
		desc.setLayout(new MigLayout("", "[][grow][grow]", "[][186.00][][][][][][][][]"));
		desc.setBackground(bgr);
		
		JLabel lblQuestionD = new JLabel("Question");
		
		JLabel lblQuestionTitleoptionaD = new JLabel("Question Title(Optional)");
		desc.add(lblQuestionTitleoptionaD, "cell 0 0,alignx trailing");
		
		dTitle = new JTextField();
		desc.add(dTitle, "cell 1 0,growx,spanx 3");
		dTitle.setColumns(10);
		desc.add(lblQuestionD, "cell 0 1,alignx right,aligny top");
		
		dText =new JTextArea (5,20);
		
		JScrollPane scrollPaneD = new JScrollPane(dText);
		desc.add(scrollPaneD, "cell 1 1,span 3,grow");
		
		JButton btnDeleteclearQuestionTextDes = new JButton("Cancel/Clear Question Text");
		desc.add(btnDeleteclearQuestionTextDes, "cell 2 2,alignx right");
		
		btnDeleteclearQuestionTextDes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				dText.setText("");
			}
		});
		
		JButton btnSaveD = new JButton("Save");
		desc.add(btnSaveD, "cell 0 4,alignx center,aligny center");

		btnSaveD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				if(!dText.getText().equals("")){
					createDesc(dTitle.getText(),dText.getText());
					JOptionPane.showMessageDialog(null, "Question saved");
					dText.setText("");
					dTitle.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "Question text must not be empty!");
				}
			}
		});
		
		//short answer tab
		shortAns = new JPanel();
		tabbedPane.addTab("Short Answer", null, shortAns, null);
		shortAns.setLayout(new MigLayout("", "[][grow][grow]", "[][186.00][][][][][][][][][][][][]"));
		shortAns.setBackground(bgr);
		
		JLabel lblQuestionSh = new JLabel("Question");
		
		JLabel lblQuestionTitleoptional_1 = new JLabel("Question Title(Optional)");
		shortAns.add(lblQuestionTitleoptional_1, "cell 0 0,alignx trailing");
		
		shTitle = new JTextField();
		shortAns.add(shTitle, "cell 1 0,growx,spanx 3");
		shTitle.setColumns(10);
		shortAns.add(lblQuestionSh, "cell 0 1,alignx right,aligny top");
		
		shText =new JTextArea (5,20);
		
		JScrollPane scrollPaneSh = new JScrollPane(shText);
		shortAns.add(scrollPaneSh, "cell 1 1,span 3,grow");
		
		JButton btnDeleteclearQuestionText = new JButton("Cancel/Clear Question Text");
		shortAns.add(btnDeleteclearQuestionText, "cell 2 2,alignx right");
		
		JLabel lblCorrectAnswerSh = new JLabel("  Correct Answer");
		shortAns.add(lblCorrectAnswerSh, "flowx,cell 0 3,alignx trailing");
		
		answerSh = new JTextField();
		shortAns.add(answerSh, "cell 1 3 2 1,growx");
		answerSh.setColumns(10);
		
		JButton btnAddAnswerSh = new JButton("Add Answer");
		shortAns.add(btnAddAnswerSh, "cell 0 4");
		
		JButton btnSaveSh= new JButton("Save");
		shortAns.add(btnSaveSh, "cell 0 "+rowSh+2+",alignx center");
		
		btnSaveSh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				if(!shText.getText().equals("")&&!answerSh.getText().equals("")){
					createSA(shTitle.getText(),shText.getText());
					JOptionPane.showMessageDialog(null, "Question saved");
					shText.setText("");
					shTitle.setText("");
					answerSh.setText("");
					for(JTextField f : shFields1){
						shortAns.remove(f);				
					}
					shFields1=new ArrayList<JTextField>();
					rowSh=4;
					repaint();
				}
				else{
					JOptionPane.showMessageDialog(null, "Question and answer text must not be empty!");
				}
			}
		});
		
		btnAddAnswerSh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	

				JTextField field1 = new JTextField();
				shortAns.add(field1, "cell 1 "+rowSh+",grow,spanx 2");
				field1.setColumns(10);
				shFields1.add(field1);
				rowSh++;
				repaint();
			}
		});
		btnDeleteclearQuestionText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				shText.setText("");
			}
		});
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new Main();

	}
	
	public void createTf(String title, String question, boolean ans){
		
		String q;
		if(!title.equals("")){
			if(ans){
				q ="::"+title+"::"+question+" {T}";
			}
			else{
				q ="::"+title+"::"+question+" {F}";
			}
		}
		else{
			if(ans){
				q =question+" {T}";
			}
			else{
				q =question+" {F}";
			}
		}
		appendToFile(q+"\n");	
	}
	public void createNum(String title, String question, String ans, String error){
		
		String q;
		if(!title.equals("")){
			if(error.equals("")){
				q ="::"+title+"::"+question+" {#"+ans+"}";
			}
			else{
				q ="::"+title+"::"+question+" {#"+ans+":"+error+"}";
			}
		}
		else{
			if(error.equals("")){
				q =question+" {#"+ans+"}";
			}
			else{
				q =question+" {#"+ans+":"+error+"}";
			}
		}
		appendToFile(q+"\n");	
	}
	
	public void createDesc(String title, String question){
		
		String q;
		if(!title.equals("")){		
			q ="::"+title+"::"+question;
		}
		else{
			q =question;
		}
		appendToFile(q+"\n");
	}
	public void createEssay(String title, String question){
		
		String q;
		if(!title.equals("")){		
			q ="::"+title+"::"+question+"{}";
		}
		else{
			q =question+"{}";
		}
		appendToFile(q+"\n");
	}
	public void createSA(String title, String question){
		
		StringBuffer q= new StringBuffer("");
		if(!title.equals("")){		
			q.append("::"+title+"::"+question+"{="+answerSh.getText()+" ");
		}
		else{
			q.append(question+"{="+answerSh.getText()+" ");
		}
		
		for(JTextField f:shFields1){
			if(!f.getText().equals("")){
				q.append("="+f.getText()+" ");	
			}
			else{
				JOptionPane.showMessageDialog(null, "Empty answer field,this empty answer will not be saved");
			}
		}
		q.append("}");
		appendToFile(q+"\n");	
	}
	public void createMulti(String title, String question, String ans, String com){
		
		StringBuffer q= new StringBuffer("");
		if(!title.equals("")){		
			q.append("::"+title+"::"+question+"{\n="+ans+"\n");		
		}
		else{
			q.append(question+"{\n="+ans+"\n");
		}
		//append correct comment
		if(!com.equals(""))q.append("#"+com+"\n");
		
		for(int i=0;i<multiFields1.size();i++){
			JTextField qu = multiFields1.get(i);
			JTextField c = multiFields2.get(i);
			if(!qu.getText().equals("")){
				q.append("~"+qu.getText()+"\n");
				if(!c.getText().equals("")){
					q.append("#"+c.getText()+"\n");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Empty answer field,this empty answer will not be saved");
			}
		}
		q.append("}");
		appendToFile(q+"\n");	
	}
	
	public void appendToFile(String s){
		File out = new File ("output.txt");
		StringBuffer contents = new StringBuffer("");
		//if file exists - parse and save contnents
		if(out.exists()){
			try {
				FileInputStream fs = new FileInputStream(out);
				InputStreamReader isr = new InputStreamReader(fs);
				BufferedReader br = new BufferedReader(isr);
				
				String str;
		        while((str = br.readLine()) != null){
		        	contents.append(str+"\n");				         
			    }
		        br.close();
		        isr.close();
		        fs.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		//if file does not exist, create new file
		else{
			try {
				out.createNewFile();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			//write to output file
			PrintWriter pw = new PrintWriter(out);
			contents.append(s);
			pw.print(contents);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
