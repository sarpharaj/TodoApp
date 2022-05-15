package com.example.todomvvm.ui.todo;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todomvvm.R;
import com.example.todomvvm.database.Todo;
import com.example.todomvvm.database.TodoRepository;

import java.util.Calendar;


public class UpdateFragment extends Fragment {

    EditText mUpdateTitle,mUpdateDetails;
    TextView mUpdateDate;
    ImageView img;
    Button updateButton;
    TodoRepository repo;
    DatePickerDialog picker;
    TodoViewModel todoViewModel;

    Todo todo;


    public static Fragment newInstance() {

        return new UpdateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_update, container, false);
        todoViewModel = new TodoViewModel(getActivity().getApplication());
        //Retrieve the value
        Bundle bundle = getArguments();
        String title = bundle.getString("task_title");
        String description = bundle.getString("task_description");
        String date = bundle.getString("crated_at");
        final int taskId = bundle.getInt("task_id");

        mUpdateTitle = view.findViewById(R.id.updateTitle);
        mUpdateDetails = view.findViewById(R.id.updateTextTaskDescription);
        mUpdateDate =view.findViewById(R.id.updateDate);
        img = view.findViewById(R.id.calen);

        mUpdateTitle.setText(title);
        mUpdateDetails.setText(description);

        updateButton = view.findViewById(R.id.updateButton);
        repo = new TodoRepository(getActivity().getApplication());

        //calender
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
                        mUpdateDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }

                }, year, month, day);
                picker.show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title,details,date;

                title = mUpdateTitle.getText().toString();
                details = mUpdateDetails.getText().toString();
                date = mUpdateDate.getText().toString();

                repo.updateTask(taskId, title,details,date);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,TodoFragment.newInstance()).commit();

            }
        });

        return view;
    }
}