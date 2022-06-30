package lk.mobios.serappu.Customer.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.Customer.CustomerHomeActivity;
import lk.mobios.serappu.Customer.EditUserActivity;
import lk.mobios.serappu.Customer.User;
import lk.mobios.serappu.R;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    TextView btn_edit_user, p_name, p_email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);

        CustomerHomeActivity activity = (CustomerHomeActivity) getActivity();
        User lr = new User();
        lr = activity.getUserData();


        btn_edit_user = root.findViewById(R.id.btn_edit_user);
        p_name = root.findViewById(R.id.p_name);
        p_email = root.findViewById(R.id.p_email);

        p_email.setText(lr.getEmail());
        p_name.setText(lr.getName());


        btn_edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Edit User Code
                Intent i = new Intent(getActivity(), EditUserActivity.class);
                startActivity(i);
            }
        });
        return root;
    }
}