package com.bignerdranch.android.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.beatbox.databinding.ActivityMainBinding
import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // BeatBox 인스턴스 생성
        beatBox = BeatBox(assets)

        // 바인딩 클래스 인플레이트
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // RecyclerView 구성
        binding.recyclerView.apply {
            
            // 한 행에 세개의 격자를 가짐
            layoutManager = GridLayoutManager(context, 3)
            
            // SoundAdapter 연결
            adapter = SoundAdapter(beatBox.sounds)
        }
    }

    // list_item_sound.xml과 연결되는 SoundHolder 생성
    private inner class SoundHolder(private val binding: ListItemSoundBinding): RecyclerView.ViewHolder(binding.root){

    }

    // SoundHolder와 연결되는 어댑터 생성
    private inner class SoundAdapter(private val sounds: List<Sound>): RecyclerView.Adapter<SoundHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {

        }

        override fun getItemCount() = sounds.size
    }
}