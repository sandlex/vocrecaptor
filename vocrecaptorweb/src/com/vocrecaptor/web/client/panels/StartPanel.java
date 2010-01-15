package com.vocrecaptor.web.client.panels;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.panels.dialogs.DictionaryViewPanel;
import com.vocrecaptor.web.client.remote.DictionaryService;
import com.vocrecaptor.web.client.remote.DictionaryServiceAsync;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;
import com.vocrecaptor.web.client.utils.ProgressNotifier;

public class StartPanel extends FixedWidthPanel {

	private final DictionaryServiceAsync dictionaryService = GWT
			.create(DictionaryService.class);

	private List<DictionaryTransferObject> dicts = new ArrayList<DictionaryTransferObject>();
	private List<Image> downloads = new ArrayList<Image>();
	private FlexTable table;
	
	public StartPanel(ApplicationModel model) {
		super(model);

		HorizontalPanel panel = new HorizontalPanel();
		//panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		VerticalPanel components = new VerticalPanel();
		//components.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		table = new FlexTable();
		components.add(new Label("Public dictionaries:"));
		components.add(table);

		panel.add(components);
		initWidget(panel);
		setWidth("650px");
		setHeight("300px");

		updateDictionaries();
	}
	
	public void updateDictionaries() {
		ProgressNotifier.show(ProgressNotifier.LOADING);
		dictionaryService.getPublic(new AsyncCallback<List<DictionaryTransferObject>>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(List<DictionaryTransferObject> result) {
						table.clear();
						downloads.clear();
						dicts.clear();

						table.setStylePrimaryName("voc-gwt-Table");
						table.getRowFormatter().setStylePrimaryName(0,
								"voc-gwt-Table-header");

						table.setWidget(0, 0, new HTML(
								"&nbsp;Description&nbsp;"));
						table
								.setWidget(0, 1, new HTML(
										"&nbsp;Languages&nbsp;"));
						table.setWidget(0, 2, new HTML("&nbsp;Action&nbsp;"));

						for (final DictionaryTransferObject dict : result) {
							dicts.add(dict);
							int row = result.indexOf(dict) + 1;

							Hyperlink description = new Hyperlink(dict
									.getDescription(), "null");
							description.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									showDictionaryContent(dict);
								}
							});
							table.setWidget(row, 0, description);

							HorizontalPanel flags = new HorizontalPanel();
							Image through = new Image("images/flags/"
									+ dict.getThroughLanguage() + ".gif");
							through.setTitle(dict.getThroughLanguage());
							flags.add(through);
							flags.add(new Image("images/arrow_right.gif"));
							Image learn = new Image("images/flags/"
									+ dict.getLearningLanguage() + ".gif");
							learn.setTitle(dict.getLearningLanguage());
							flags.add(learn);

							table.setWidget(row, 1, flags);

							HorizontalPanel actions = new HorizontalPanel();
							Image download = new Image(
									"images/icon_download.gif");
							download.setStylePrimaryName("handy");
							download.setTitle("Download dictionary");
							download.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									downloadDictionary(downloads.indexOf(event
											.getSource()));
								}
							});
							actions.add(download);
							downloads.add(download);
							table.setWidget(row, 2, actions);
						}
						ProgressNotifier.hide();
					}
				});
	}

	public ApplicationModel getModel() {
		return model;
	}
	
	private void showDictionaryContent(DictionaryTransferObject dictionary) {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setStylePrimaryName("voc-gwt-DialogBox");
		dialogBox.setModal(true);
		dialogBox.setWidth("600px");
		dialogBox.setHeight("400px");
		dialogBox.setText("Dictionary: " + dictionary.getDescription());
		dialogBox.setAnimationEnabled(true);
		dialogBox.setWidget(new DictionaryViewPanel(dialogBox,
				dictionary));
		dialogBox.center();
	}

	private void downloadDictionary(final int index) {
		DictionaryTransferObject dictionary = dicts.get(index);
		
		Window.Location.assign(  
                GWT.getModuleBaseURL() + "download?id=" + dictionary.getId()); 
	}	

}
