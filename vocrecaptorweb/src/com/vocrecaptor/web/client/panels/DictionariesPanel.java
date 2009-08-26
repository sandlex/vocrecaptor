package com.vocrecaptor.web.client.panels;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.remote.DictionaryService;
import com.vocrecaptor.web.client.remote.DictionaryServiceAsync;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;

public class DictionariesPanel extends Composite {

	private final DictionaryServiceAsync dictionaryService = GWT.create(DictionaryService.class);
	
	private List<DictionaryTransferObject> dicts = new ArrayList<DictionaryTransferObject>();
	
	private FlexTable table;
	
	private ApplicationModel model;
	
	public DictionariesPanel(ApplicationModel model) {
		this.model = model;
		
		VerticalPanel panel = new VerticalPanel();
		
		table = new FlexTable();
		panel.add(table);
		
		Button addDictionary = new Button("Add dictionary");
		addDictionary.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final DialogBox dialogBox = new DialogBox();
				dialogBox.setStylePrimaryName("voc-gwt-DialogBox");
				dialogBox.setModal(true);
				dialogBox.setText("New dictionary");
				dialogBox.setAnimationEnabled(true);
				dialogBox.setWidget(new DictionaryPanel(dialogBox, DictionariesPanel.this));
				dialogBox.center();
			}
		});
		panel.add(addDictionary);
		
		initWidget(panel);
		
		updateDictionaries();
	}
	
	public void updateDictionaries() {
		
		dictionaryService.getByUser(model.getUser().getId(), new AsyncCallback<List<DictionaryTransferObject>>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(List<DictionaryTransferObject> result) {
				table.clear();
				
				table.setWidget(0, 0, new HTML("id"));
				table.setWidget(0, 1, new HTML("description"));
				table.setWidget(0, 2, new HTML("languages"));
				table.setWidget(0, 3, new HTML("public"));
				table.setWidget(0, 4, new HTML("download"));
				
				for (DictionaryTransferObject dict : result) {
					int row = result.indexOf(dict) + 1;
					table.setWidget(row, 0, new HTML(dict.getId().toString()));
					table.setWidget(row, 1, new HTML(dict.getDescription()));
					table.setWidget(row, 2, new HTML(dict.getLearningLanguage() + " through" + dict.getThroughLanguage()));
					table.setWidget(row, 3, new HTML(String.valueOf(dict.getIsPublic())));
					table.setWidget(row, 4, new HTML());
				}
			}
		});
		
	}
	
	public ApplicationModel getModel() {
		return model;
	}
	
}
