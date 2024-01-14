package com.example.tasbeeh;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class view_pager_adapter extends FragmentStateAdapter {
    public view_pager_adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new zikr();
            case 1:
                return new SaveZikr();
            default:
                return new zikr();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
