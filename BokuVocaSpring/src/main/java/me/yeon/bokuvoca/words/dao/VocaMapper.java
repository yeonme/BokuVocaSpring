package me.yeon.bokuvoca.words.dao;

import java.util.ArrayList;
import java.util.HashMap;

import me.yeon.bokuvoca.words.vo.JVocaItem;
import me.yeon.bokuvoca.words.vo.JWordItem;

public interface VocaMapper {
	public JWordItem randomWord();
	public JWordItem selectDetailWord(int num);
	public int countWord();
	public ArrayList<JWordItem> selectListWord(String keyword);
	public ArrayList<JWordItem> selectQWordAll();
	public ArrayList<JWordItem> selectQWordJLPT(String jlpt);
	public ArrayList<JWordItem> selectQWordStar(int star);
	public ArrayList<JWordItem> selectQWordVoca(String user);
	public ArrayList<JWordItem> listYomiByLength(HashMap<String,Object> hmap);
	public ArrayList<JWordItem> listKanjiByLength(HashMap<String,Object> hmap);
	public int newVoca(JVocaItem voca);
	public int deleteVoca(JVocaItem voca);
	public JVocaItem hasVoca(JVocaItem voca);
}
