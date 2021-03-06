package com.vocrecaptor.web.client.panels.dialogs;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.vocrecaptor.web.client.panels.DictionariesPanel;
import com.vocrecaptor.web.client.remote.LanguageService;
import com.vocrecaptor.web.client.remote.LanguageServiceAsync;
import com.vocrecaptor.web.client.remote.transferobjects.LanguageTransferObject;
import com.vocrecaptor.web.client.utils.ErrorNotifier;

public class DictionaryEditPanel extends Composite {

	private final LanguageServiceAsync languageService = GWT.create(LanguageService.class);
	
	private List<LanguageTransferObject> languages = new ArrayList<LanguageTransferObject>();
	private ListBox learnLang;
	private ListBox throughLang;
	
	public DictionaryEditPanel(final DialogBox dialog, final DictionariesPanel parent) {
		
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		final FormPanel form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL() + "upload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		FlexTable table = new FlexTable();
		
		table.setWidget(0, 0, new Label("You learn"));

		HorizontalPanel languagesPanel = new HorizontalPanel();
		learnLang = new ListBox();
		learnLang.setName("learningLanguage");
		languagesPanel.add(learnLang);
		languagesPanel.add(new Label("through"));
		throughLang = new ListBox();
		throughLang.setName("throughLanguage");
		languagesPanel.add(throughLang);
		table.setWidget(0, 1, languagesPanel);
		
		table.setWidget(1, 0, new Label("Description"));
		final TextBox description = new TextBox();
		description.setName("description");
		table.setWidget(1, 1, description);
		
		table.setWidget(2, 0, new Label("Is public"));
		final CheckBox isPublic = new CheckBox();
		isPublic.setName("isPublic");
		table.setWidget(2, 1, isPublic);
		
		table.setWidget(3, 0, new Label("Dictionary file"));
		HorizontalPanel filePanel = new HorizontalPanel();
		Hidden user = new Hidden();
		user.setName("user");
		user.setValue(String.valueOf(parent.getModel().getUser().getId()));
		filePanel.add(user);
		final FileUpload file = new FileUpload();
		file.setName("file");
		filePanel.add(file);
		table.setWidget(3, 1, filePanel);		

		form.setWidget(table);
		panel.add(form);
		form.addSubmitHandler(new SubmitHandler() {
			
			@Override
			public void onSubmit(SubmitEvent event) {
				if ("".equals(file.getFilename())) {
					ErrorNotifier.showError(ErrorNotifier.FILE_IS_NOT_SPECIFIED);
					event.cancel();
				}

				if ("".equals(description.getValue())) {
					ErrorNotifier.showError(ErrorNotifier.DICTIONARY_DESCRIPTION_IS_NOT_SPECIFIED);
					event.cancel();
				}
			
			}
		});
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent arg0) {
				if (arg0.getResults().contains("ERROR")) {
					ErrorNotifier.showError(ErrorNotifier.SOMETHING_WENT_WRONG);
				} else if(arg0.getResults().contains("BADFORMAT")) {
					ErrorNotifier.showError(ErrorNotifier.WRONG_FILE_FORMAT);
				} else if(arg0.getResults().contains("TOLARGE")) {
					ErrorNotifier.showError(ErrorNotifier.DICTIONARY_FILE_IS_TOO_LARGE);
				} else {
					dialog.hide();
					parent.updateDictionaries();
				}
			}
		});
		
		HorizontalPanel buttons = new HorizontalPanel();
		Button ok = new Button("Create");
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});
		buttons.add(ok);
		Button cancel = new Button("Cancel");
		cancel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				dialog.hide();
			}
		});
		buttons.add(cancel);
		panel.add(buttons);
		
		initWidget(panel);
		
		initLanguages();
	}
	
	private void initLanguages() {
		languageService.getAll(new AsyncCallback<List<LanguageTransferObject>>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(List<LanguageTransferObject> result) {
				for (LanguageTransferObject lang : result) {
					learnLang.addItem(lang.getName(), lang.getShortName());
					throughLang.addItem(lang.getName(), lang.getShortName());
					languages.add(lang);
				}
			}
		});
	}

}
