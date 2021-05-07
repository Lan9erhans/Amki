package UI;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;

import DAO.RuntimeQuizDao;
import DAO.SuperDao;

public class QuizHideView extends SuperView {
	RuntimeQuizDao runtimeQuizDao;
	JFrame nextMenuFrame;
	
	public QuizHideView(JFrame frame, SuperDao dao) {
		super(frame, dao);
//		runtimeQuizDao = (RuntimeQuizDao)dao.getDao();
	}
	/*Smelly 3 Methods Below for getMenuFrame*/
	protected void setNextMenuFrame(JFrame nextMenuFrame) {
		this.nextMenuFrame = nextMenuFrame;
	}
	@Override
	protected boolean isNextMenuFrameExist() {
		nextMenuFrame.setVisible(false);
		return (nextMenuFrame != null ) ? true : false;
	}
	@Override
	protected void nextMenuFrameOn() {
		nextMenuFrame.setVisible(true);
	}
	
	@Override
	public QuizHideView setDao(SuperDao dao) {
		this.runtimeQuizDao = (RuntimeQuizDao)dao;
		valueLabel.setText(runtimeQuizDao.getQuiz());
		validate();
		repaint();
		return this;
	}
	@Override
	protected void placeFields() {
		placeFactory.placeNext(keyLabel);
		placeFactory.placeBelow(valueLabel);
	}
	@Override
	protected void placeButtons() {
		placeFactory.placeNext(getButton);
		placeFactory.placeBelow(postButton);
	}
	@Override
	protected void placeSubFields() {
	}
	@Override
	protected void placeSubButtons() {
	}
//	 runtimeQuizDao의 호출시점은 Constructor가 아닌가? => override된 method라 super에서 열람함.
	@Override
	protected void setLabelButtonConfig() {
		keyLabel.setText("Q:");
		titleBar.setText("Quiz");
		postButton.setText("ANSWER");
		getButton.setText("EXIT");
	}
	@Override
	protected void setKeyValueFieldConfig() {
	}
	@Override
	protected void setBtnCmd() {
		postBtnCmd = "ANSWER";
		getBtnCmd = "EXIT";
	}
	@Override
	protected void doPosting(HashMap<String, String> crawlInfo) {
	}
	@Override
	protected boolean isPostDone() {
		return true;
	}
	@Override
	protected void updateResponseBar() {
	}
	
	@Override
//	doGet의 nullPointer조건을 우회하기 위함.
//	if 내부를 함수로 만들경우, Override해서 우회할 수도 있을 것.
	protected void doPostGet() {
		frame.remove(this);
		frame.getContentPane().add(BorderLayout.CENTER, nextPanel.setDao(runtimeQuizDao));
		frame.validate();
		frame.repaint();
	}
	@Override
	protected void doGet(SuperView nextPanel) {
		System.out.println(runtimeQuizDao.DisplayQuiz());
		super.doGet(nextPanel.setDao(runtimeQuizDao));
	}
	@Override
	protected void doSubService(String btnCmd) {
	}
}
