package com.vocrecaptor.swing;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.vocrecaptor.swing.controller.MainMenu;
import com.vocrecaptor.swing.controller.listeners.ExitListener;
import com.vocrecaptor.swing.controller.listeners.RecapWindowListener;
import com.vocrecaptor.swing.controller.listeners.RunEditorListener;
import com.vocrecaptor.swing.controller.listeners.RunManagerListener;
import com.vocrecaptor.swing.controller.listeners.RunRecapListener;
import com.vocrecaptor.swing.model.Model;
import com.vocrecaptor.swing.resource.PropertyGetter;
import com.vocrecaptor.swing.view.EditorPanel;
import com.vocrecaptor.swing.view.ProgramManagerPanel;
import com.vocrecaptor.swing.view.RecapPanel;

/**
 * Application starting point. Initializes model, views (panels and frames),
 * application system tray menu and starts application.
 * 
 * @author Alexey Peskov
 */
public class Main {

	private static final String VERSION_NUMBER = "1.2.2";
	static TrayIcon trayIcon = null;

	private Model model;
	private EditorPanel editorPanel;
	private RecapPanel recapPanel;
	private ProgramManagerPanel dictManagerPanel;
	private JFrame recapFrame;
	private JFrame editorFrame;
	private JFrame dictManagerFrame;

	/**
	 * Application entry point.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new Main();
	}

	/**
	 * Creates a new instance of application main class.
	 */
	private Main() {

		// Model
		model = new Model();

		// Views
		editorPanel = new EditorPanel(model);
		recapPanel = new RecapPanel(model);
		dictManagerPanel = new ProgramManagerPanel(model);

		model.addObserver(recapPanel);
		model.addObserver(dictManagerPanel);
		model.addObserver(editorPanel);

		initFrames();
		model.init();

		initApplicationMenu();
	}

	/**
	 * Initializes frames.
	 */
	private void initFrames() {
		recapFrame = new JFrame(PropertyGetter
				.getNamedProperty("Recap_frame_title"));
		recapFrame.getContentPane().add(recapPanel, BorderLayout.CENTER);
		recapFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		recapFrame.addWindowListener(new RecapWindowListener(model));
		recapFrame.setAlwaysOnTop(true);
		recapFrame.setLocationRelativeTo(null);
		recapFrame.pack();

		editorFrame = new JFrame(PropertyGetter
				.getNamedProperty("Editor_frame_title"));
		editorFrame.getContentPane().add(editorPanel, BorderLayout.CENTER);
		editorFrame.pack();

		dictManagerFrame = new JFrame(PropertyGetter
				.getNamedProperty("Program_manager_frame_title"));
		dictManagerFrame.getContentPane().add(dictManagerPanel,
				BorderLayout.CENTER);
		dictManagerFrame.pack();

		editorPanel.setParentFrame(editorFrame);
		recapPanel.setParentFrame(recapFrame);
		dictManagerPanel.setParentFrame(dictManagerFrame);
	}

	/**
	 * Initializes application main menu.
	 */
	private void initApplicationMenu() {
		MainMenu menu = new MainMenu();
		menu.setExitListener(new ExitListener());
		menu.setRecapListener(new RunRecapListener(recapFrame, model));
		menu.setEditorListener(new RunEditorListener(editorFrame, model));
		menu.setDictManagerListener(new RunManagerListener(
				dictManagerFrame, model));

		if (SystemTray.isSupported()) {
			SystemTray tray = SystemTray.getSystemTray();
			URL imgURL = ClassLoader
					.getSystemResource("com/vocrecaptor/swing/resource/tray.gif");
			Image image = Toolkit.getDefaultToolkit().getImage(imgURL);
			trayIcon = new TrayIcon(image, PropertyGetter
					.getNamedProperty("Application_hint")
					+ VERSION_NUMBER, menu);
			trayIcon.setImageAutoSize(true);

			try {
				tray.add(trayIcon);
				trayIcon.addActionListener(new RunManagerListener(
						dictManagerFrame, model));
				model.setTrayIcon(trayIcon);
			} catch (AWTException e) {
				System.err
						.println(PropertyGetter
								.getNamedProperty("Tray_icon_could_not_be_added_error"));
				System.exit(0);
			}
		} else {
			System.err.println(PropertyGetter
					.getNamedProperty("Tray_is_not_supported_error"));
			System.exit(0);
		}
	}
}
