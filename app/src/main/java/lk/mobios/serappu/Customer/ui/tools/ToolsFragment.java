package lk.mobios.serappu.Customer.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.Customer.CustomerHomeActivity;
import lk.mobios.serappu.R;

public class ToolsFragment extends Fragment {
    private DBHelper mydb;
    private ToolsViewModel toolsViewModel;
    TextView btn_sendFeedback;
    EditText et_name,et_feedback;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mydb = new DBHelper(getActivity());
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        et_name= root.findViewById(R.id.et_name);
        et_feedback= root.findViewById(R.id.et_feedback);
        btn_sendFeedback=root.findViewById(R.id.btn_sendFeedback);


        btn_sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _name = et_name.getText().toString().trim();
                String _feedback = et_feedback.getText().toString().trim();
                mydb.insertNewFeedback(_name,_feedback);

                Toast.makeText(getActivity(), "Your feedback is sent successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), CustomerHomeActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return root;
    }
}