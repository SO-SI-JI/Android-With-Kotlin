package com.bignerdranch.android.beatbox

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class SoundViewModelTest {

    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        sound = Sound("assetPath")
        
        // 테스트 대상이 되는 객체를 Subject로 칭함
        subject = SoundViewModel()
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle(){
        
        // 테스트 대상의 title 속성값이 Sound의 name 속성값과 같아야 함
        MatcherAssert.assertThat(subject.title, `is`(sound.name))
    }
}