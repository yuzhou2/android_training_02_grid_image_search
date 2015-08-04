package com.yuzhou.viewer.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.yuzhou.viewer.R;
import com.yuzhou.viewer.service.GoogleImageSearchTaskParams;

import java.util.List;

public class AdvancedSearchActivity extends AppCompatActivity
{
    private GoogleImageSearchTaskParams searchPrefs;
    private EditText etSize;
    private EditText etColor;
    private EditText etType;
    private EditText etSite;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        List<String> sizes = Lists.newArrayList(new String[]{"small", "medium", "large", "extra-large"});
        etSize = (EditText) findViewById(R.id.etAdvSize);
        etSize.setOnClickListener(new OnClickListPopupTextViewListener(etSize, sizes));

        List<String> colors = Lists.newArrayList(new String[]{"black", "blue", "brown", "gray", "green"});
        etColor = (EditText) findViewById(R.id.etAdvColor);
        etColor.setOnClickListener(new OnClickListPopupTextViewListener(etColor, colors));

        List<String> types = Lists.newArrayList(new String[]{"face", "photo", "clip art", "line art"});
        etType = (EditText) findViewById(R.id.etAdvType);
        etType.setOnClickListener(new OnClickListPopupTextViewListener(etType, types));

        etSite = (EditText) findViewById(R.id.etAdvSite);


        searchPrefs = (GoogleImageSearchTaskParams) getIntent().getSerializableExtra("prefs");
        etSize.setText(searchPrefs.getImgSize());
        etColor.setText(searchPrefs.getImgColor());
        etType.setText(searchPrefs.getImgType());
        etSite.setText(searchPrefs.getSiteFilter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_advanced_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSave(View view)
    {
        searchPrefs.setImgSize(etSize.getText().toString());
        searchPrefs.setImgColor(etColor.getText().toString());
        searchPrefs.setImgType(etType.getText().toString());
        searchPrefs.setSiteFilter(etSite.getText().toString());

        Intent intent = getIntent();
        intent.putExtra("new_prefs", searchPrefs);

        setResult(RESULT_OK, intent);
        finish();
    }
}