package com.vocrecaptor.web.client.panels.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.remote.DictionaryService;
import com.vocrecaptor.web.client.remote.DictionaryServiceAsync;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;
import com.vocrecaptor.web.client.remote.transferobjects.WordBean;
import com.vocrecaptor.web.client.remote.transferobjects.WordsPage;
import com.vocrecaptor.web.client.utils.ProgressNotifier;

public class DictionaryViewPanel extends Composite {

	private final DictionaryServiceAsync dictionaryService = GWT
			.create(DictionaryService.class);

	private int currentPage;
	private int maxPage = 0;
	private DictionaryTransferObject dictionary;
	private FlexTable table;
	private ListBox pages;
	private Hyperlink first;
	private Hyperlink prev;
	private Hyperlink next;
	private Hyperlink last;
	private Label legend;
	
	
	public DictionaryViewPanel(final DialogBox dialog, DictionaryTransferObject dictionary) {

		this.dictionary = dictionary;

		VerticalPanel panel = new VerticalPanel();
		ScrollPanel words = new ScrollPanel();
		words.setHeight("400px");
		words.setWidth("600px");
		table = new FlexTable();
		//table.setHeight("400px");
		table.setWidth("580px");
		table.setStylePrimaryName("voc-gwt-Table");
		table.getRowFormatter().setStylePrimaryName(0, "voc-gwt-Table-header");

		table.setWidget(0, 0, new HTML("&nbsp;#&nbsp;"));
		table.setWidget(0, 1, new HTML("&nbsp;Definition&nbsp;"));
		table.setWidget(0, 2, new HTML("&nbsp;Translation&nbsp;"));
		table.setWidget(0, 3, new HTML("&nbsp;Example&nbsp;"));
		words.add(table);
		panel.add(words);
		HorizontalPanel paging = new HorizontalPanel();
		paging.setSpacing(5);
		first = new Hyperlink("|<", null);
		first.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (currentPage != 0) {
					setCurrentPage(0);
				}
			}
		});
		paging.add(first);
		prev = new Hyperlink("<", null);
		prev.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (currentPage > 0) {
					setCurrentPage(currentPage - 1);
				}
			}
		});
		paging.add(prev);
		pages = new ListBox();
		pages.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				setCurrentPage(new Integer(
						pages.getValue(pages.getSelectedIndex())));
			}
		});
		paging.add(pages);
		next = new Hyperlink(">", null);
		next.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (currentPage < maxPage) {
					setCurrentPage(currentPage + 1);
				}
			}
		});
		paging.add(next);
		last = new Hyperlink(">|", null);
		paging.add(last);
		last.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (currentPage != maxPage) {
					setCurrentPage(maxPage);
				}
			}
		});
		legend = new Label();
		paging.add(legend);
		panel.add(paging);
		HorizontalPanel buttons = new HorizontalPanel();
		Button close = new Button("Close");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialog.hide();
			}
		});
		buttons.add(close);
		panel.add(buttons);
		initWidget(panel);
		
		setCurrentPage(0);
	}

	private void setCurrentPage(int value) {
		ProgressNotifier.show(ProgressNotifier.LOADING);
		currentPage = value;
		pages.setSelectedIndex(currentPage);
		loadWords();
//		if (currentPage == 0) {
//			first.setStylePrimaryName("disabled");
//			prev.setStylePrimaryName("disabled");
//		} else {
//			first.setStylePrimaryName("enabled");
//			prev.setStylePrimaryName("enabled");
//		}
//		if (currentPage == maxPage) {
//			next.setStylePrimaryName("disabled");
//			last.setStylePrimaryName("disabled");
//		} else {
//			next.setStylePrimaryName("enabled");
//			last.setStylePrimaryName("enabled");
//		}
	}
	
	private void loadWords() {
		dictionaryService.getWordsPage(dictionary.getId(), currentPage,
				new AsyncCallback<WordsPage>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(WordsPage result) {
						int i = 1;
						int empty = 0;
						for (WordBean word : result.getPage()) {
							table.setWidget(i, 0, new HTML(
									"".equals(word.getDefinition()) ? "":
									String.valueOf(i + currentPage * 15)));
							table.setWidget(i, 1,
									new HTML(word.getDefinition()));
							table.setWidget(i, 2, new HTML(word
									.getTranslation()));
							table.setWidget(i, 3, new HTML(word.getExample()));
							i++;
							if ("".equals(word.getDefinition())) empty++;
						}

						if (maxPage == 0) {
							maxPage = result.getPages();
							for (int page = 1; page <= result.getPages() + 1; page++) {
								pages.addItem(String.valueOf(page), String.valueOf(page - 1));
							}
						}
						
						legend.setText((currentPage * 15 + 1) + " - " + (i + currentPage * 15 - 1 - empty) + " from " + result.getWords());
						
						ProgressNotifier.hide();
					}
				});
	}

}
