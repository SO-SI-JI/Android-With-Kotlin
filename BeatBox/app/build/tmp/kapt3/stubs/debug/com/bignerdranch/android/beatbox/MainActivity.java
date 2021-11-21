package com.bignerdranch.android.beatbox;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\t\nB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/bignerdranch/android/beatbox/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "beatBox", "Lcom/bignerdranch/android/beatbox/BeatBox;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "SoundAdapter", "SoundHolder", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.bignerdranch.android.beatbox.BeatBox beatBox;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/bignerdranch/android/beatbox/MainActivity$SoundHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/bignerdranch/android/beatbox/databinding/ListItemSoundBinding;", "(Lcom/bignerdranch/android/beatbox/MainActivity;Lcom/bignerdranch/android/beatbox/databinding/ListItemSoundBinding;)V", "app_debug"})
    final class SoundHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding binding = null;
        
        public SoundHolder(@org.jetbrains.annotations.NotNull()
        com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding binding) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J\u001c\u0010\u000e\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/bignerdranch/android/beatbox/MainActivity$SoundAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/bignerdranch/android/beatbox/MainActivity$SoundHolder;", "Lcom/bignerdranch/android/beatbox/MainActivity;", "sounds", "", "Lcom/bignerdranch/android/beatbox/Sound;", "(Lcom/bignerdranch/android/beatbox/MainActivity;Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "app_debug"})
    final class SoundAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.bignerdranch.android.beatbox.MainActivity.SoundHolder> {
        private final java.util.List<com.bignerdranch.android.beatbox.Sound> sounds = null;
        
        public SoundAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.bignerdranch.android.beatbox.Sound> sounds) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.bignerdranch.android.beatbox.MainActivity.SoundHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.bignerdranch.android.beatbox.MainActivity.SoundHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
    }
}