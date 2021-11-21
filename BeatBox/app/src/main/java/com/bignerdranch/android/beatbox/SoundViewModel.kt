package com.bignerdranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

// 뷰모델
class SoundViewModel : BaseObservable(){

    var sound: Sound? = null
        set(sound){
            field = sound

            // Sound의 모든 바인딩 속성값이 변경되었음을 ListItemSoundBinding에 알린다
            notifyChange()
        }

    // 버튼 제목을 갖는 속성
    @get:Bindable
    val title: String?
        get() = sound?.name
}