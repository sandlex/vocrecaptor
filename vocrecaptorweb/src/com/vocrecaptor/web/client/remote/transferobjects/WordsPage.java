package com.vocrecaptor.web.client.remote.transferobjects;

import java.io.Serializable;
import java.util.List;

public class WordsPage implements Serializable {
	
	private static final long serialVersionUID = -4111931730062788890L;
	
	private Integer pages;
	private Integer words;
	private List<WordBean> page;
	
	public WordsPage() {
	}

	public WordsPage(Integer pages, Integer words, List<WordBean> page) {
		this.pages = pages;
		this.words = words;
		this.page = page;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public List<WordBean> getPage() {
		return page;
	}

	public void setPage(List<WordBean> page) {
		this.page = page;
	}

	public Integer getWords() {
		return words;
	}

	public void setWords(Integer words) {
		this.words = words;
	}
	             

}
