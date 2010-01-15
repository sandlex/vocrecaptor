package com.vocrecaptor.web.client.panels;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.panels.dialogs.DictionaryEditPanel;
import com.vocrecaptor.web.client.panels.dialogs.DictionaryUpdatePanel;
import com.vocrecaptor.web.client.panels.dialogs.DictionaryViewPanel;
import com.vocrecaptor.web.client.remote.DictionaryService;
import com.vocrecaptor.web.client.remote.DictionaryServiceAsync;
import com.vocrecaptor.web.client.remote.transferobjects.DictionaryTransferObject;
import com.vocrecaptor.web.client.utils.ErrorNotifier;
import com.vocrecaptor.web.client.utils.ProgressNotifier;

public class DictionariesPanel extends FixedWidthPanel {

	private final DictionaryServiceAsync dictionaryService = GWT
			.create(DictionaryService.class);

	private List<DictionaryTransferObject> dicts = new ArrayList<DictionaryTransferObject>();
	private List<Image> downloads = new ArrayList<Image>();
	private List<Image> updates= new ArrayList<Image>();
	private List<Image> deletes = new ArrayList<Image>();

	private FlexTable table;

	public DictionariesPanel(ApplicationModel model) {
		super(model);

		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		VerticalPanel components = new VerticalPanel();
		components.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		table = new FlexTable();
		components.add(table);

		Button addDictionary = new Button("Add dictionary");
		addDictionary.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final DialogBox dialogBox = new DialogBox();
				dialogBox.setStylePrimaryName("voc-gwt-DialogBox");
				dialogBox.setModal(true);
				dialogBox.setText("New dictionary");
				dialogBox.setAnimationEnabled(true);
				dialogBox.setWidget(new DictionaryEditPanel(dialogBox,
						DictionariesPanel.this));
				dialogBox.center();
			}
		});
		components.add(addDictionary);

		panel.add(components);
		initWidget(panel);
		updateDictionaries();
		setWidth("650px");
		setHeight("300px");
	}

	public void updateDictionaries() {
		ProgressNotifier.show(ProgressNotifier.LOADING);
		dictionaryService.getByUser(model.getUser().getId(),
				new AsyncCallback<List<DictionaryTransferObject>>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(List<DictionaryTransferObject> result) {
						table.clear();
						downloads.clear();
						updates.clear();
						deletes.clear();
						dicts.clear();

						table.setStylePrimaryName("voc-gwt-Table");
						table.getRowFormatter().setStylePrimaryName(0,
								"voc-gwt-Table-header");

						table.setWidget(0, 0, new HTML("&nbsp;#&nbsp;"));
						table.setWidget(0, 1, new HTML(
								"&nbsp;Description&nbsp;"));
						table
								.setWidget(0, 2, new HTML(
										"&nbsp;Languages&nbsp;"));
						table.setWidget(0, 3, new HTML("&nbsp;Public&nbsp;"));
						table.setWidget(0, 4, new HTML("&nbsp;Action&nbsp;"));

						for (final DictionaryTransferObject dict : result) {
							dicts.add(dict);
							int row = result.indexOf(dict) + 1;
							CheckBox isPublic = new CheckBox();
							isPublic.setEnabled(false);
							isPublic.setValue(dict.getIsPublic());
							table.setWidget(row, 0, new HTML(dict.getId()
									.toString()));

							Hyperlink description = new Hyperlink(dict
									.getDescription(), "null");
							description.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									showDictionaryContent(dict);
								}
							});
							table.setWidget(row, 1, description);

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

							table.setWidget(row, 2, flags);
							table.setWidget(row, 3, isPublic);

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
							Image update = new Image(
									"images/action_refresh.gif");
							update.setStylePrimaryName("handy");
							update.setTitle("Update dictionary");
							update.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									updateDictionary(updates.indexOf(event
											.getSource()));
								}
							});
							
							actions.add(update);
							updates.add(update);
							Image delete = new Image("images/page_delete.gif");
							delete.setStylePrimaryName("handy");
							delete.setTitle("Delete dictionary");
							delete.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									deleteDictionary(deletes.indexOf(event
											.getSource()));
								}
							});
							actions.add(delete);
							deletes.add(delete);
							table.setWidget(row, 4, actions);
						}
						ProgressNotifier.hide();
					}
				});
	}

	public ApplicationModel getModel() {
		return model;
	}

	private void deleteDictionary(final int index) {

		final DialogBox dialogBox = new DialogBox();
		dialogBox.setStylePrimaryName("voc-gwt-DialogBox");
		dialogBox.setModal(true);
		dialogBox.setText("Delete confirmation");
		dialogBox.setAnimationEnabled(true);
		VerticalPanel panel = new VerticalPanel();
		panel.add(new HTML("Dictionary will be deleted. Are you sure?"));
		HorizontalPanel buttons = new HorizontalPanel();
		Button cancel = new Button("Cancel");
		cancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		Button delete = new Button("Delete");
		delete.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DictionaryTransferObject dictionary = dicts.get(index);
				dictionaryService.delete(dictionary, new AsyncCallback<Long>() {

					@Override
					public void onFailure(Throwable caught) {
						ErrorNotifier.showError("Something went wrong");
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(Long result) {
						dialogBox.hide();
						ErrorNotifier.showMessage("Dictionary deleted");
						updateDictionaries();
					}

				});
			}
		});
		buttons.add(delete);
		buttons.add(cancel);
		panel.add(buttons);
		dialogBox.setWidget(panel);
		dialogBox.center();
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

	private void updateDictionary(final int index) {

		final DialogBox dialogBox = new DialogBox();
		dialogBox.setStylePrimaryName("voc-gwt-DialogBox");
		dialogBox.setModal(true);
		dialogBox.setText("Updating dictionary: " + dicts.get(index).getDescription());
		dialogBox.setAnimationEnabled(true);
		dialogBox.setWidget(new DictionaryUpdatePanel(dialogBox,
				dicts.get(index).getId()));
		dialogBox.center();
	}

}
