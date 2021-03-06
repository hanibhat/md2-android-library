package de.wwu.md2.android.md2library.controller.eventhandler.implementation;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import de.wwu.md2.android.md2library.controller.action.interfaces.Md2Action;

/**
 * Event handler for onClick events.
 * Related to ElementEventType onClick in MD2-DSL.
 * Implements the interface View.OnClickListener and View.OnTouchListener
 * <p/>
 * Created on 11/08/2015
 *
 * @author Fabian Wrede
 * @version 1.0
 * @since 1.0
 */
public class Md2OnClickHandler extends AbstractMd2WidgetEventHandler implements View.OnClickListener, View.OnTouchListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    /**
     * Instantiates a new Md 2 on click handler.
     */
    public Md2OnClickHandler() {
        super();
    }

    @Override
    public void onClick(View v) {
        System.out.println("clicked");
        this.execute();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                this.execute();
                return true;
        }
        return false;
    }

    @Override
    public void execute(){
        super.execute();
        System.out.println("execute");
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("MD2OnClickHandler: #Actions = " + getActions().size() + "; ");
        for (Md2Action action : getActions()) {
            result.append(action.getActionSignature() + "; ");
        }
        return result.toString();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.execute();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        return; // Do nothing
    }
}
