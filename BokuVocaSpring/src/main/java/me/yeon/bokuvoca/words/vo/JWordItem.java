package me.yeon.bokuvoca.words.vo;

public class JWordItem {
	private int num;
	private String word;
	private String yomi;
	private String type;
	private String summary;
	private String meaning;
	private int star;
	private String jlpt;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getJlpt() {
		return jlpt;
	}
	public void setJlpt(String jlpt) {
		this.jlpt = jlpt;
	}
	
	public JWordItem() {
		
	}
	
	public JWordItem(int num) {
		this.num = num;
	}
	
	public JWordItem(String yomi, String word) {
		this.yomi = yomi;
		this.word = word;
	}
}