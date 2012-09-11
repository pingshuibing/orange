package com.qut.spc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class PanelView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_for_panels);
       /// getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // here we should get list of panels from XML (see MainActivity.java comments to know how)
        // then deploythem to the list
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_panel_view, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// do somwthing with selected item (panel)
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
