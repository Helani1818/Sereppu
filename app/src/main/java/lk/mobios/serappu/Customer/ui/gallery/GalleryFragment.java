package lk.mobios.serappu.Customer.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import lk.mobios.serappu.Common.DBHelper;
import lk.mobios.serappu.Manager.DisplayPromo;
import lk.mobios.serappu.Manager.DisplayPromoUserActivity;
import lk.mobios.serappu.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private DBHelper mydb;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ListView obj;
        mydb = new DBHelper(getActivity());


        ArrayList array_list = mydb.getAllPromos();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, array_list);

        obj = (ListView) root.findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                Intent intent = new Intent(getActivity(), DisplayPromoUserActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
        return root;
    }
}