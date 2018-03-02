package me.yeon.bokuvoca.words.vo;

public class JVocaItem {
	private int num;
	private String word;
	private String yomi;
	private String type;
	private String summary;
	private String added;
	private String remembered;
	private String user;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getYomi() {
		return yomi;
	}
	public void setYomi(String yomi) {
		this.yomi = yomi;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getAdded() {
		return added;
	}
	public void setAdded(String added) {
		this.added = added;
	}
	public String getRemembered() {
		return remembered;
	}
	public void setRemembered(String remembered) {
		this.remembered = remembered;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public JVocaItem()
	{
		
	}
	
	public JVocaItem(JWordItem jword, String user) {
		num = jword.getNum(); 
		word = jword.getWord();
		yomi = jword.getYomi();
		type = jword.getType();
		summary = jword.getSummary();
		this.user = user; 
	}
	
}