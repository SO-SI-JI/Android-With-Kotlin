package com.bignerdranch.android.beatbox

import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.lang.Exception

private const val TAG = "BeatBox"
private const val SOUND_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5

// AssetManager 인스턴스 참조
class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>

    // SoundPool 객체 생성
    private val soundPool = SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build()

    init{
        sounds = loadSounds()
    }

    // Asset의 파일 내역 얻기
    private fun loadSounds(): List<Sound>{

        val soundNames: Array<String>

        try{
            // !! : null이 될 수 없음을 단언 (null이면 NullPointerException)
            soundNames = assets.list(SOUND_FOLDER)!!
        } catch (e: Exception){
            Log.e(TAG, "Could not list assets", e)
            return emptyList()
        }

        // mutableListOf : 수정 가능한 List
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUND_FOLDER/$filename"
            val sound = Sound(assetPath)
            sounds.add(sound)
        }

        return sounds
    }
}