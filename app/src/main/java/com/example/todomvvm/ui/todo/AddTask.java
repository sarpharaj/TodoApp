package com.example.todomvvm.ui.todo;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.todomvvm.R;
import com.example.todomvvm.TodoActivity;
import com.example.todomvvm.database.Todo;
import com.example.todomvvm.database.TodoRepository;

import java.util.Calendar;
import java.util.List;


public class AddTask extends Fragment {
    private static final String TAG = TodoActivity.class.getSimpleName();

    EditText mEditText,mDescription;
    Button btn;
    TextView mDate;
    RadioGroup radioGroup;
    ImageView img;
    TodoRepository repository;
    DatePickerDialog picker;

    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;



    public AddTask() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {

        return new AddTask();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //CHALENA BHANEY FINAL HATAUNEY

        final View view;
        view = inflater.inflate(R.layout.fragment_add_task, container, false);

        mEditText = view.findViewById(R.id.editTitle);
        mDescription =view.findViewById(R.id.editTextTaskDescription);
        btn = (Button) view.findViewById(R.id.saveButton);
        img = view.findViewById(R.id.cal);
        mDate =view.findViewById(R.id.date_edit);
        /*radioGroup = view.findViewById(R.id.radioGroup);*/



        repository = new TodoRepository(getActivity().getApplication());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String title = mEditText.getText().toString();
               String detail = mDescription.getText().toString();
               String date = mDate.getText().toString();
               /*int pr = getPriorityFromViews();*/
                Todo todo = new Todo(title,detail,date);
                repository.insert(todo);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,TodoFragment.newInstance()).commitNow();
            }

           /* private int getPriorityFromViews() {
                int priority = 1;
                int checkedId = ((RadioGroup) view.findViewById(R.id.radioGroup)).getCheckedRadioButtonId(); //Made view final

                switch (checkedId) {
                    case R.id.radButton1:
                        priority = PRIORITY_HIGH;
                        break;
                    case R.id.radButton2:
                        priority = PRIORITY_MEDIUM;
                        break;
                    case R.id.radButton3:
                        priority = PRIORITY_LOW;
                        break;
                }


                return priority;
            }*/
            /*private void setPriorityInViews(int priority) {
                switch (priority) {
                    case PRIORITY_HIGH:
                        ((RadioGroup) view.findViewById(R.id.radioGroup)).check(R.id.radButton1);
                        break;
                    case PRIORITY_MEDIUM:
                        ((RadioGroup) view.findViewById(R.id.radioGroup)).check(R.id.radButton2);
                        break;
                    case PRIORITY_LOW:
                        ((RadioGroup) view.findViewById(R.id.radioGroup)).check(R.id.radButton3);
                }

            }*/
        });




//DATE
       img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                picker = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }

                }, year, month, day);
                picker.show();
            }
        });
        return view;
    }


}