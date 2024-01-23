package com.ahmad.healthbodyandmind.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.ahmad.healthbodyandmind.R;
import com.ahmad.healthbodyandmind.databinding.ActivitySettingsBinding;
import com.ahmad.healthbodyandmind.signup.signupCustomModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class settings extends AppCompatActivity {
FirebaseAuth auth;
    ActivitySettingsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        // Inflate the binding layout
      binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        settingsModel viewModel = new ViewModelProvider(this).get(settingsModel.class);
        viewModel.fetchDataFromFirebase(auth.getUid());
            viewModel.getData().observe(this, signupCustomModel -> {
                binding.NameSettingEt.setText(signupCustomModel.getName());
                binding.emailSettingEt.setText(signupCustomModel.getEmail());
                binding.phoneSettingEt.setText(signupCustomModel.getPhone());
                binding.passwordSettingEt.setText(signupCustomModel.getPassword());
        });
        binding=ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        com.ahmad.healthbodyandmind.databinding.ActivitySettingsBinding finalBinding = binding;
        binding.btnsave.setOnClickListener(v->{
            String name= finalBinding.NameSettingEt.getText().toString();
            String Email= finalBinding.emailSettingEt.getText().toString();
            String password= finalBinding.passwordSettingEt.getText().toString();
            String phone= finalBinding.phoneSettingEt.getText().toString();
            Map<String, Object> updateData = new HashMap<>();
            updateData.put("name", name);
            updateData.put("email", Email);
            updateData.put("password", password);
            updateData.put("phone", phone);

            auth.getCurrentUser().updateEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    auth.getCurrentUser().updatePassword(password).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                         viewModel.updateData(auth.getUid(),updateData);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(settings.this, "failed update re-sign and try ", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}