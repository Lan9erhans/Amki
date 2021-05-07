package UI;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DAO.RuntimeQuizDao;
import DAO.SuperDao;
import DTO.runtimeQuiz;

public class QuizRevealView extends SuperView {
	JFrame nextMenuFrame;
	RuntimeQuizDao runtimeQuizDao;
	JLabel quizLabel;
	JLabel answerLabel;
	JButton wrongButton;
	String wrongBtnCmd;

	public QuizRevealView(JFrame frame, SuperDao superDao) {
		super(frame, superDao);
//		runtimeQuizDao = (RuntimeQuizDao)superDao.getDao();
	}
	@Override
	protected QuizRevealView setDao(SuperDao dao) {
		runtimeQuizDao = (RuntimeQuizDao)dao;
		getNextLabel();
		validate();
		repaint();
		return this;
	}
	
	@Override
	protected void placeFields() {
		quizLabel = new JLabel();
		answerLabel = new JLabel();

		placeFactory.placeNext(keyLabel);
		placeFactory.placeBelow(quizLabel);
		placeFactory.placeNext(valueLabel);
		placeFactory.placeBelow(answerLabel);
		placeSubFields();
	}
	@Override
	protected void placeButtons() {
		placeFactory.placeNext(postButton);
		wrongButton = new JButton();
		placeFactory.placeBelow(wrongButton);
		wrongButton.addActionListener(this);
	}
	@Override
	protected void placeSubFields() {
	}
	@Override
	protected void placeSubButtons() {
	}
	private void getNextLabel() {
		quizLabel.setText(runtimeQuizDao.getQuiz());
		answerLabel.setText(runtimeQuizDao.getAnswer());
	}
	@Override
	protected void setLabelButtonConfig() {
		titleBar.setText("Quiz");
		keyLabel.setText("Q:");
		valueLabel.setText("A:");
		wrongButton.setText("WRONG");
		postButton.setText("CORRECT");
	}
	@Override
	protected void setKeyValueFieldConfig() {
	}
	@Override
	protected void setBtnCmd() {
		postBtnCmd = "CORRECT";
		wrongBtnCmd = "WRONG";
	}
	@Override
	protected void doPosting(HashMap<String, String> crawlInfo) {
		for(runtimeQuiz rq :  runtimeQuizDao.runtimeQuizList) {
			System.out.println(rq.getQuiz());
		}
		runtimeQuizDao.Correct();
	}
	@Override
	protected boolean isPostDone() {
		return true;
	}
	@Override
	protected void updateResponseBar() {
	}
	@Override
	protected void doSubService(String btnCmd) {
		if(btnCmd.equals(wrongBtnCmd)) {
			doWrongPost();
		}
	}
	private void doWrongPost() {
		runtimeQuizDao.inCorrect();
		doPostGet();
	}
	@Override
	protected void doPostGet() {
		doGet(nextPanel.setDao(runtimeQuizDao));
	}
}
