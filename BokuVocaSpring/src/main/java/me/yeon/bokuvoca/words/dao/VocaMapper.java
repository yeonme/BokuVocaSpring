package me.yeon.bokuvoca.words.dao;

import me.yeon.bokuvoca.words.vo.JWordItem;

public interface VocaMapper {
	public JWordItem randomWord();
	public JWordItem selectDetailWord(int num);
	public int countWord();
}
