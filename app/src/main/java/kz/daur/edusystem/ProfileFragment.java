package kz.daur.edusystem;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    TextView show, tvBio, hide;
    ImageButton btn_profile_back;
    ImageView btnSettings;
    Button btn_edit;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        show = (TextView) view.findViewById(R.id.show);
        tvBio = (TextView) view.findViewById(R.id.tvBio);
        hide = (TextView) view.findViewById(R.id.hide);
        btnSettings = (ImageView) view.findViewById(R.id.btnSettings);
        btn_edit = (Button) view.findViewById(R.id.btn_edit);

        btn_profile_back = (ImageButton) view.findViewById(R.id.btn_profile_back);

        setTextViewColor(show, getResources().getColor(R.color.dodger_blue),
                getResources().getColor(R.color.sky_blue));

        setTextViewColor(hide, getResources().getColor(R.color.dodger_blue),
                getResources().getColor(R.color.sky_blue));

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView tvFullName = (TextView) view.findViewById(R.id.tvFullName);
        final TextView tvAge = (TextView) view.findViewById(R.id.tvAge);
        final TextView tvCity = (TextView) view.findViewById(R.id.tvCity);
        final TextView tvCountry = (TextView) view.findViewById(R.id.tvCountry);
        final TextView tvDateOfBirth = (TextView) view.findViewById(R.id.tvDateOfBirth);
        final TextView tvJob = (TextView) view.findViewById(R.id.tvJob);
        final TextView tvEdu = (TextView) view.findViewById(R.id.tvEdu);
        final TextView tvGender = (TextView) view.findViewById(R.id.tvGender);
        tvBio = (TextView) view.findViewById(R.id.tvBio);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    String fullName = userProfile.fullName;
                    String age = userProfile.age;
                    String city = userProfile.city;
                    String country = userProfile.country;
                    String dateOfBirth = userProfile.dateOfBirth;
                    String job = userProfile.job;
                    String edu = userProfile.edu;
                    String biography = userProfile.biography;
                    Boolean gender = userProfile.gender;

                    tvFullName.setText(fullName);
                    tvAge.setText(age);
                    tvCity.setText(city);
                    tvCountry.setText(country);
                    tvDateOfBirth.setText(dateOfBirth);
                    tvJob.setText(job);
                    tvEdu.setText(edu);
                    tvBio.setText(biography);
                    if(gender == false ) {
                        tvGender.setText("Male");
                    } else if(gender == true) {
                        tvGender.setText("Female");
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfile.class));
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                tvBio.setMaxLines(Integer.MAX_VALUE);
            }
        });


        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(View.VISIBLE);
                hide.setVisibility(View.INVISIBLE);
                tvBio.setMaxLines(3);
            }
        });

        return view;
    }

    private void setTextViewColor(TextView textView, int...color) {
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(textView.getText().toString());

        Shader shader = new LinearGradient(0, 0, width,
                textView.getTextSize(), color, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
        textView.setTextColor(color[0]);
    }
}