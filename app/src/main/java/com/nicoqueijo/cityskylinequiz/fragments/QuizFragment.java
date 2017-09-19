package com.nicoqueijo.cityskylinequiz.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nicoqueijo.cityskylinequiz.R;
import com.nicoqueijo.cityskylinequiz.activities.QuizGameActivity;
import com.nicoqueijo.cityskylinequiz.helpers.ResourceByNameRetriever;
import com.nicoqueijo.cityskylinequiz.models.City;
import com.nicoqueijo.cityskylinequiz.models.Question;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.Queue;

public class QuizFragment extends Fragment implements View.OnClickListener {

    public static final String QUIZ_FRAGMENT = "QUIZ";

    private Queue<Question> mQuestions = new LinkedList<>();
    private int mGroupPosition;
    private int mChildPosition;

    private ImageView mCityImage;

    private LinearLayout mContainerChoice1;
    private LinearLayout mContainerChoice2;
    private LinearLayout mContainerChoice3;
    private LinearLayout mContainerChoice4;

    private ImageView mFlagChoice1;
    private ImageView mFlagChoice2;
    private ImageView mFlagChoice3;
    private ImageView mFlagChoice4;

    private TextView mCityNameChoice1;
    private TextView mCityNameChoice2;
    private TextView mCityNameChoice3;
    private TextView mCityNameChoice4;

    private ImageView mCorrectMarkChoice1;
    private ImageView mCorrectMarkChoice2;
    private ImageView mCorrectMarkChoice3;
    private ImageView mCorrectMarkChoice4;

    private ImageView mIncorrectMarkChoice1;
    private ImageView mIncorrectMarkChoice2;
    private ImageView mIncorrectMarkChoice3;
    private ImageView mIncorrectMarkChoice4;

    private OnFragmentInteractionListener mListener;

    /**
     * Required empty public constructor
     */
    public QuizFragment() {
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment QuizFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static QuizFragment newInstance(String param1, String param2) {
//        QuizFragment fragment = new QuizFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        Log.v("fragment", "onCreateView called");

//        mQuestions = (Queue<Question>) this.getArguments().getSerializable("questions");
//        mGroupPosition = this.getArguments().getInt("group");
//        mChildPosition = this.getArguments().getInt("child");

        mCityImage = (ImageView) view.findViewById(R.id.city_image);

        mContainerChoice1 = (LinearLayout) view.findViewById(R.id.answer_choice_one);
        mContainerChoice2 = (LinearLayout) view.findViewById(R.id.answer_choice_two);
        mContainerChoice3 = (LinearLayout) view.findViewById(R.id.answer_choice_three);
        mContainerChoice4 = (LinearLayout) view.findViewById(R.id.answer_choice_four);

        mFlagChoice1 = (ImageView) view.findViewById(R.id.flag_choice_one);
        mFlagChoice2 = (ImageView) view.findViewById(R.id.flag_choice_two);
        mFlagChoice3 = (ImageView) view.findViewById(R.id.flag_choice_three);
        mFlagChoice4 = (ImageView) view.findViewById(R.id.flag_choice_four);

        mCityNameChoice1 = (TextView) view.findViewById(R.id.city_name_choice_one);
        mCityNameChoice2 = (TextView) view.findViewById(R.id.city_name_choice_two);
        mCityNameChoice3 = (TextView) view.findViewById(R.id.city_name_choice_three);
        mCityNameChoice4 = (TextView) view.findViewById(R.id.city_name_choice_four);

        mCorrectMarkChoice1 = (ImageView) view.findViewById(R.id.correct_mark_choice_one);
        mCorrectMarkChoice2 = (ImageView) view.findViewById(R.id.correct_mark_choice_two);
        mCorrectMarkChoice3 = (ImageView) view.findViewById(R.id.correct_mark_choice_three);
        mCorrectMarkChoice4 = (ImageView) view.findViewById(R.id.correct_mark_choice_four);

        mIncorrectMarkChoice1 = (ImageView) view.findViewById(R.id.incorrect_mark_choice_one);
        mIncorrectMarkChoice2 = (ImageView) view.findViewById(R.id.incorrect_mark_choice_two);
        mIncorrectMarkChoice3 = (ImageView) view.findViewById(R.id.incorrect_mark_choice_three);
        mIncorrectMarkChoice4 = (ImageView) view.findViewById(R.id.incorrect_mark_choice_four);

        mContainerChoice1.setOnClickListener(this);
        mContainerChoice2.setOnClickListener(this);
        mContainerChoice3.setOnClickListener(this);
        mContainerChoice4.setOnClickListener(this);

        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        // TESTING REMOVE LATER
        Picasso.with(getActivity()).load(QuizGameActivity.mQuestions.peek().getCorrectChoice().getImageUrl()).into(mCityImage);

        mCityNameChoice1.setText(ResourceByNameRetriever.getStringResourceByName(QuizGameActivity.mQuestions.peek().getChoice1().getCityName(), getActivity()));
        mCityNameChoice2.setText(ResourceByNameRetriever.getStringResourceByName(QuizGameActivity.mQuestions.peek().getChoice2().getCityName(), getActivity()));
        mCityNameChoice3.setText(ResourceByNameRetriever.getStringResourceByName(QuizGameActivity.mQuestions.peek().getChoice3().getCityName(), getActivity()));
        mCityNameChoice4.setText(ResourceByNameRetriever.getStringResourceByName(QuizGameActivity.mQuestions.peek().getChoice4().getCityName(), getActivity()));

        mFlagChoice1.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(QuizGameActivity.mQuestions.peek().getChoice1().getCountryName(), getActivity()));
        mFlagChoice2.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(QuizGameActivity.mQuestions.peek().getChoice2().getCountryName(), getActivity()));
        mFlagChoice3.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(QuizGameActivity.mQuestions.peek().getChoice3().getCountryName(), getActivity()));
        mFlagChoice4.setImageResource(ResourceByNameRetriever.getDrawableResourceByName(QuizGameActivity.mQuestions.peek().getChoice4().getCountryName(), getActivity()));
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        // TESTING, REMOVE LATER
        LinearLayout choicePress = (LinearLayout) v;
        Question question = QuizGameActivity.mQuestions.peek();
        City guess = null;
        int choiceIndex = 0;
        if (choicePress == mContainerChoice1) {
            guess = question.getChoice1();
        } else if (choicePress == mContainerChoice2) {
            guess = question.getChoice2();
        } else if (choicePress == mContainerChoice3) {
            guess = question.getChoice3();
        } else if (choicePress == mContainerChoice4) {
            guess = question.getChoice4();
        }

        if (guess.getCityName().equals(question.getCorrectChoice().getCityName())) {
            Log.v(QUIZ_FRAGMENT, "Correct!");
        } else {
            Log.v(QUIZ_FRAGMENT, "Incorrect...");
            choicePress.setEnabled(false);
            choicePress.setAlpha(0.5f);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void passQuestionsAndGameMode(Queue<Question> questions, int group, int child) {
        Log.v("fragment", "passQuestions called");
        for (Question question : questions) {
            mQuestions.add(question);
        }
        mGroupPosition = group;
        mChildPosition = child;
    }
}