package com.example.tapnbite.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selectedChip = new MutableLiveData<>();

    public void selectChip(String chip) {
        selectedChip.setValue(chip);
    }

    public LiveData<String> getSelectedChip() {
        return selectedChip;
    }
}
