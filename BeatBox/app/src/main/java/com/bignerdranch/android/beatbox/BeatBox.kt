package com.bignerdranch.android.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException
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

    fun play(sound: Sound){
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    // 음원 재생이 끝나면 SoundPool을 리소스 해제함
    fun release(){
        soundPool.release()
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
            try{
                load(sound)
                sounds.add(sound)
            } catch (ioe: IOException){
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }

        return sounds
    }
    
    private fun load(sound: Sound){
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
        
        // 나중에 재생할 음원 파일을 SoundPool에 로드
        // 음원을 유지하고 다시 재생하기 위해 정수 ID를 반환
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }
}