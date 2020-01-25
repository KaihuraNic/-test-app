package dataprod;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import dataprod.models.Survey;
import dataprod.models.UserProfile;
import dataprod.viewModels.UserProfileViewModel;

import com.dataprod.a_heel.R;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileAcivity extends AppCompatActivity {

    private UserProfileViewModel userProfileViewModel;
    private Survey contextSurvey;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap contextBitmap;

    @BindView(R.id.image)
    ImageView image_prof;
    @BindView(R.id.et_f_name)
    EditText fName;
    @BindView(R.id.et_s_name)
    EditText sName;
    @BindView(R.id.id_no)
    EditText IdNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_acivity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        contextSurvey = new Gson().fromJson(intent.getStringExtra("content"), Survey.class);

        userProfileViewModel = ViewModelProviders.of(UserProfileAcivity.this).get(UserProfileViewModel.class);

        findViewById(R.id.save_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(fName.getText().toString()) && !TextUtils.isEmpty(sName.getText().toString()) && !TextUtils.isEmpty(IdNo.getText().toString())) {
                    userProfileViewModel.saveUserProfile(contextSurvey.getId(), new UserProfile( 1,
                            fName.getText().toString(), sName.getText().toString(), IdNo.getText().toString(), getPhoto(), contextSurvey.getId()
                    ));

                    Intent intent = new Intent(UserProfileAcivity.this, QuestionBaseActivity.class);
                    intent.putExtra("content", new Gson().toJson(contextSurvey));
                    intent.putExtra("nationalNumber",IdNo.getText().toString());
                    startActivity(intent);
                }
            }
        });
        findViewById(R.id.image_slect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

    }

    private String getPhoto() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        contextBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap convert(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(base64Str.substring(base64Str.indexOf(",") + 1), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                image_prof.setImageBitmap(bitmap);
                contextBitmap = bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
