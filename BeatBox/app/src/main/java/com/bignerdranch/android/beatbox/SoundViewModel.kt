package com.bignerdranch.android.beatbox

// 뷰모델
class SoundViewModel {

    var sound: Sound? = null
        set(sound){
            field = sound
        }

    // 버튼 제목을 갖는 속성
    val title: String?
        get() = sound?.name
}