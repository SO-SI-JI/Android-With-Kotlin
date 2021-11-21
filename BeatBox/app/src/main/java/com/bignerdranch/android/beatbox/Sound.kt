package com.bignerdranch.android.beatbox

private const val WAV = ".wav"

class Sound(val assetPath: String){
    
    // 파일 이름을 가져와서 확장자를 제거
    val name = assetPath.split("/").last().removePrefix(WAV)
}