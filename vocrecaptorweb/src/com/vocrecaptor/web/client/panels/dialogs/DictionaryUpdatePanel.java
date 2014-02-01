package com.vocrecaptor.web.client.panels.dialogs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.vocrecaptor.web.client.utils.ErrorNotifier;

public class DictionaryUpdatePanel extends Composite {
	
	public DictionaryUpdatePanel(final DialogBox dialog, final Long dictId) {
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		final FormPanel form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL() + "upload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		FlexTable table = new FlexTable();
		table.setWidget(0, 0, new Label("Mode"));
		VerticalPanel modes = new VerticalPanel();
		RadioButton merge = new RadioButton("mode", "merge");
		merge.setFormValue("merge");
		merge.setValue(true);
		RadioButton replace = new RadioButton("mode", "replace");
		replace.setFormValue("replace");
		replace.setTitle("All words from the current list will be deleted and new words will be written instead");
		RadioButton append = new RadioButton("mode", "append");
		append.setFormValue("append");
		append.setTitle("All new words will be added to the end of the current word list no matter if such words exist in current list or not");
		merge.setTitle("Program will analize both lists and only new words from new list will be added to the end of the current list");
		modes.add(merge);
		modes.add(replace);
		modes.add(append);
		table.setWidget(0, 1, modes);
		table.setWidget(2, 0, new Label("Dictionary file"));
		HorizontalPanel filePanel = new HorizontalPanel();
		Hidden id = new Hidden();
		id.setName("id");
		id.setValue(String.valueOf(dictId));
		filePanel.add(id);
		final FileUpload file = new FileUpload();
		file.setName("file");
		filePanel.add(file);
		table.setWidget(2, 1, filePanel);		

		form.setWidget(table);
		panel.add(form);
		form.addSubmitHandler(new SubmitHandler() {
			
			@Override
			public void onSubmit(SubmitEvent event) {
				if ("".equals(file.getFilename())) {
					ErrorNotifier.showError(ErrorNotifier.FILE_IS_NOT_SPECIFIED);
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
					//ErrorNotifier.showMessage(ErrorNotifier.FILE_IS_UPDATED + arg0.getResults());
					ErrorNotifier.showMessage(arg0.getResults().substring(
							arg0.getResults().indexOf("~") + 1, arg0.getResults().lastIndexOf("~")));
					dialog.hide();
				}
			}
		});
		
		HorizontalPanel buttons = new HorizontalPanel();
		Button ok = new Button("Upload");
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
	}

}
