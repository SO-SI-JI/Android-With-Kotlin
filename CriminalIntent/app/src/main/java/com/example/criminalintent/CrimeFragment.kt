package com.example.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class CrimeFragment : Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        titleField = view.findViewById(R.id.crime_title) as EditText
        return view
    }

    override fun onStart(){
        super.onStart()

        val titleWatcher = object : TextWatcher{
            override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {
                // 여기서는 이 함수의 실행 코드를 구현할 필요가 없어서 비워 둔다.
            }

            override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
                // 여기서는 이 함수의 실행 코드를 구현할 필요가 없어서 비워 둔다.
            }
        }
        titleField.addTextChangedListener(titleWatcher)
    }
}