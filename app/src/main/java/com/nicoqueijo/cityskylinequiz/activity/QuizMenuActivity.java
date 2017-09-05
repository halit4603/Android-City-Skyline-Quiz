package com.nicoqueijo.cityskylinequiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.nicoqueijo.cityskylinequiz.adapter.ExpandableListAdapter;
import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.model.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizMenuActivity extends AppCompatActivity {

    public static final int TIMED_MODE = 0;
    public static final int UNTIMED_MODE = 1;
    public static final int EVERY_CITY_MODE = 2;
    public static final int SECONDS_30 = 0;
    public static final int SECONDS_60 = 1;
    public static final int SECONDS_120 = 2;
    public static final int QUESTIONS_10 = 0;
    public static final int QUESTIONS_20 = 1;
    public static final int QUESTIONS_50 = 2;
    public static final int NO_FAULTS = 0;
    public static final int FAULTS_ALLOWED = 1;

    private ActionBar mActionBar;
    private SharedPreferences mSharedPreferences;
    private ArrayList<City> mCities;
    private ExpandableListView mExpandableList;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<Integer> mParentGameModes;
    private Map<Integer, List<Integer>> mChildGameModes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setTheme(mSharedPreferences.getInt("theme", R.style.AppThemeLight));
        setContentView(R.layout.activity_menu_quiz);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setIcon(R.drawable.ic_light_game);
        mActionBar.setTitle(R.string.actionbar_play_game);

        Intent intentPlayGame = getIntent();
        mCities = (ArrayList<City>) intentPlayGame.getSerializableExtra("cityList");

        mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list_view);
        fillExpandableListData();
        mExpandableListAdapter = new ExpandableListAdapter(this, mParentGameModes, mChildGameModes);
        mExpandableList.setAdapter(mExpandableListAdapter);

        mExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                switch (groupPosition) {
                    case (TIMED_MODE):
                        switch (childPosition) {
                            case (SECONDS_30):
                                Toast.makeText(QuizMenuActivity.this, "Timed : 30 seconds", Toast.LENGTH_SHORT).show();
                                // Start quiz game intent appropriate for unlimited questions within 30 seconds.
                                // Keep dequeueing questions while there is time remaining.
                                // Don't forget to pass the city list!
                                break;
                            case (SECONDS_60):
                                Toast.makeText(QuizMenuActivity.this, "Timed : 60 seconds", Toast.LENGTH_SHORT).show();
                                // Start quiz game intent appropriate for unlimited questions within 60 seconds.
                                // Keep dequeueing questions while there is time remaining.
                                // Don't forget to pass the city list!
                                break;
                            case (SECONDS_120):
                                Toast.makeText(QuizMenuActivity.this, "Timed : 120 seconds", Toast.LENGTH_SHORT).show();
                                // Start quiz game intent appropriate for unlimited questions within 120 seconds.
                                // Keep dequeueing questions while there is time remaining.
                                // Don't forget to pass the city list!
                                break;
                        }
                        break;
                    case (UNTIMED_MODE):
                        switch (childPosition) {
                            case (QUESTIONS_10):
                                Toast.makeText(QuizMenuActivity.this, "Untimed : 10 questions", Toast.LENGTH_SHORT).show();
                                // Start quiz game intent appropriate for 10 questions.
                                // Keep dequeueing questions while the queue is not empty.
                                // Don't forget to pass the city list!
                                break;
                            case (QUESTIONS_20):
                                Toast.makeText(QuizMenuActivity.this, "Untimed : 20 questions", Toast.LENGTH_SHORT).show();
                                // Start quiz game intent appropriate for 20 questions.
                                // Keep dequeueing questions while the queue is not empty.
                                // Don't forget to pass the city list!
                                break;
                            case (QUESTIONS_50):
                                Toast.makeText(QuizMenuActivity.this, "Untimed : 50 questions", Toast.LENGTH_SHORT).show();
                                // Start quiz game intent appropriate for 50 questions.
                                // Keep dequeueing questions while the queue is not empty.
                                // Don't forget to pass the city list!
                                break;
                        }
                        break;
                    case (EVERY_CITY_MODE):
                        switch (childPosition) {
                            case (NO_FAULTS):
                                Toast.makeText(QuizMenuActivity.this, "Every city : No faults", Toast.LENGTH_SHORT).show();
                                // Start quiz game intent appropriate for unlimited questions without faults.
                                // Keep dequeueing questions while the queue is not empty.
                                // End the game immediately upon a wrong answer.
                                // Don't forget to pass the city list!
                                break;
                            case (FAULTS_ALLOWED):
                                Toast.makeText(QuizMenuActivity.this, "Every city : Faults allowed", Toast.LENGTH_SHORT).show();
                                // Start quiz game intent appropriate for unlimited questions with faults allowed.
                                // Keep dequeueing questions while the queue is not empty.
                                // Don't forget to pass the city list!
                                break;
                        }
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Populates the parent and child items of the expandable list view with their appropriate strings.
     */
    private void fillExpandableListData() {
        mParentGameModes = new ArrayList<>();
        mChildGameModes = new HashMap<>();

        mParentGameModes.add(R.string.timed);
        mParentGameModes.add(R.string.untimed);
        mParentGameModes.add(R.string.every_city);

        List<Integer> timedModeChildren = new ArrayList<>();
        List<Integer> untimedModeChildren = new ArrayList<>();
        List<Integer> everyCityModeChildren = new ArrayList<>();

        timedModeChildren.add(R.string.seconds_30);
        timedModeChildren.add(R.string.seconds_60);
        timedModeChildren.add(R.string.seconds_120);

        untimedModeChildren.add(R.string.questions_10);
        untimedModeChildren.add(R.string.questions_20);
        untimedModeChildren.add(R.string.questions_50);

        everyCityModeChildren.add(R.string.no_faults);
        everyCityModeChildren.add(R.string.faults_allowed);

        mChildGameModes.put(mParentGameModes.get(TIMED_MODE), timedModeChildren);
        mChildGameModes.put(mParentGameModes.get(UNTIMED_MODE), untimedModeChildren);
        mChildGameModes.put(mParentGameModes.get(EVERY_CITY_MODE), everyCityModeChildren);
    }
}
