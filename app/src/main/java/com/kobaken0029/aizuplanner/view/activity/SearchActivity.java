package com.kobaken0029.aizuplanner.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.kobaken0029.aizuplanner.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity {
    public static final String TAG = SearchActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.edit_search)
    EditText mEditText;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(
                activity, R.anim.abc_fade_in, R.anim.abc_fade_out);
        ActivityCompat.startActivity(activity, intent, compat.toBundle());
    }

    private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && keyCode == KeyEvent.KEYCODE_ENTER) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(mEditText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
                EventActivity.start(SearchActivity.this);
                SearchActivity.this.finish();
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        boolean higherThanMarshmallow = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
        final Drawable upArrow = higherThanMarshmallow
                ? ContextCompat.getDrawable(getApplicationContext(), R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                : getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        if (upArrow != null) {
            if (higherThanMarshmallow) {
                upArrow.setColorFilter(ContextCompat.getColor(
                        getApplicationContext(), R.color.gray600), PorterDuff.Mode.SRC_ATOP);
            } else {
                upArrow.setColorFilter(getResources().getColor(R.color.gray600), PorterDuff.Mode.SRC_ATOP);
            }
        }
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        mEditText.setOnKeyListener(mKeyListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                this.finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}
