package com.example.ucare;

import android.content.Context;
import androidx.annotation.NonNull;


import com.example.ucare.data.source.MedicineRepository;
import com.example.ucare.data.source.local.MedicinesLocalDataSource;



public class Injection {

    public static MedicineRepository provideMedicineRepository(@NonNull Context context) {
        return MedicineRepository.getInstance(MedicinesLocalDataSource.getInstance(context));
    }
}