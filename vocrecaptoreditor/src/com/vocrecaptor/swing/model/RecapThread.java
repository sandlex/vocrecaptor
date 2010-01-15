package com.vocrecaptor.swing.model;

import java.util.Random;

import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.resource.PropertyGetter;
import com.vocrecaptor.swing.view.updatetypes.ClearRecapPanelType;
import com.vocrecaptor.swing.view.updatetypes.ShowDefinitionType;
import com.vocrecaptor.swing.view.updatetypes.ShowExampleType;
import com.vocrecaptor.swing.view.updatetypes.ShowTranslationType;

/**
 * Class represents a thread working in the background of recap process.
 * The thread is responsible for displaying word parts in recap panel with
 * specified parameters set by user (such as reverse order, random order and all
 * kinds of delays). 
 * 
 * @author Alexey Peskov
 */
public class RecapThread extends Thread {

	//A reference to model.
	private Model model;
	
	//Generator for generating a next random number of word to display.
	private Random generator;
	
	//Flag of thread activity. 
    private boolean stopped;
    
    //Currently displayed word.
    private int currentIndex;

    /*
     * A group of user parameters set in ProgramManager panel defining 
     * a recap process. 
     */
    private int defShowTime;
    private int transShowTime;
    private int exampleShowTime;
    private boolean isReverseOrder;
    private boolean isRandomOrder;

    /**
     * Constructor. Creates a new instance of RecapThread.
     * 
     * @param model a reference to model
     * @param defShowTime definition displaying length
     * @param transShowTime translation displaying length
     * @param exampleShowTime example displaying length
     * @param isReverseOrder reverse order flag
     * @param isRandomOrder random order flag
     */
    public RecapThread(Model model, int defShowTime, int transShowTime,
            int exampleShowTime, boolean isReverseOrder, boolean isRandomOrder) {
        generator = new Random();
        stopped = false;

    	this.model = model;
        this.defShowTime = defShowTime;
        this.transShowTime = transShowTime;
        this.exampleShowTime = exampleShowTime;
        this.isReverseOrder = isReverseOrder;
        this.isRandomOrder = isRandomOrder;
    }

    /**
     * Main thread body.
     */
    public void run() {
        currentIndex = 0;
        while (!stopped) {
            try {
                if (model.recapWordsList.isEmpty()) {
                	model.notify(new ShowTranslationType(PropertyGetter.getNamedProperty("Recap_list_is_empty._You_should_specify_another_session_or_group_for_recap!")));
                    sleep((int) (1000 * transShowTime));
                } else {
                    WordBean randomWord = model.recapWordsList.get(currentIndex);
                    if (!isReverseOrder) {
                    	model.notify(new ShowDefinitionType(randomWord.getDefinition()));
                        sleep((int) (1000 * defShowTime));
                        model.notify(new ShowTranslationType(randomWord.getTranslation()));
                        sleep((int) (1000 * transShowTime));
                    } else {
                    	model.notify(new ShowTranslationType(randomWord.getTranslation()));
                        sleep((int) (1000 * transShowTime));
                        model.notify(new ShowDefinitionType(randomWord.getDefinition()));
                        sleep((int) (1000 * defShowTime));
                    }
                    if (!randomWord.getExample().isEmpty()) {
                    	model.notify(new ShowExampleType(randomWord.getExample()));
                        sleep((int) (1000 * exampleShowTime));
                    }
                    model.notify(new ClearRecapPanelType());
                    if (!isRandomOrder) {
                        currentIndex++;
                        if (currentIndex >= model.recapWordsList.size()) {
                            currentIndex = 0;
                        }
                    } else {
                        currentIndex = generator.nextInt(model.recapWordsList.size());
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(PropertyGetter.getNamedProperty("Reader_thread_sleep_error_during_read_operation."));
            }
        }
    }

    /**
     * Stops thread execution.
     */
    public void stopThread() {
        stopped = true;
    }

    /**
     * Sets a new value of definition displaying length.
     * @param defShowTime definition displaying length
     */
    public void setDefinitionTime(int defShowTime) {
        this.defShowTime = defShowTime;
    }

    /**
     * Sets a new value of translation displaying length.
     * @param transShowTime translation displaying length
     */
    public void setTranslationTime(int transShowTime) {
        this.transShowTime = transShowTime;
    }

    /**
     * Sets a new value of example displaying length.
     * @param exampleShowTime example displaying length
     */
    public void setExampleTime(int exampleShowTime) {
        this.exampleShowTime = exampleShowTime;
    }

    /**
     * Sets a new value of reverse order flag.
     * @param isReverseOrder
     */
    public void setReverseOrder(boolean isReverseOrder) {
        this.isReverseOrder = isReverseOrder;
    }

    /**
     * Sets a new value of random order flag.
     * @param isRandomOrder
     */
    public void setRandomOrder(boolean isRandomOrder) {
        this.isRandomOrder = isRandomOrder;
    }

    /**
     * Returns a currently displayed WordBean object.
     * @return a WordBean object
     */
    protected WordBean getCurrentWord() {
        return model.recapWordsList.get(currentIndex);
    }

}
