package com.tp7.fragment.clockapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch s;
    Button button;
    ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = (Switch) findViewById(R.id.picker_switch);
        insertFragment();


        button = (Button) findViewById(R.id.toggle_button);

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null){
                    return false;
                }
                else {
                    mActionMode = startActionMode(mActionModeCallback);
                    v.setSelected(true);
                    return true;
                }

            }
        });


    }


    public void togglePicker() {
        insertFragment();
    }

    public void insertFragment(){
        MyFragment fragment = new MyFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean("dateOK", s.isChecked());
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_layout, fragment)
                .commit();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu iheb) {
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.context_menu,iheb);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {

            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            RelativeLayout l = findViewById(R.id.activity_main);
            switch (menuItem.getItemId()){
                case R.id.action_color_dark:
                    //l.setBackgroundColor(getResources().getColor(R.color.darkColor));
                    l.setBackgroundResource(R.color.darkColor);

                 //   s.setTextColor(getResources().getColor(R.color.lightColor));
                    s.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightColor));

                    button.setBackgroundResource(R.color.colorPrimary);
                    button.setTextColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.colorAccent));

                    actionMode.finish();

                    return true;
                case R.id.action_color_light:

                    //l.setBackgroundColor(getResources().getColor(R.color.darkColor));
                    l.setBackgroundResource(R.color.lightColor);
                    //s.setTextColor(getResources().getColor(R.color.darkColor));
                    s.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.darkColor));

                    button.setBackgroundResource(R.color.greyColor);
                    button.setTextColor(ContextCompat.getColor(getApplicationContext(),
                            R.color.darkColor));
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };

    public void show(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //s.toggle();
            togglePicker();
        }

        return super.onOptionsItemSelected(item);
    }
}