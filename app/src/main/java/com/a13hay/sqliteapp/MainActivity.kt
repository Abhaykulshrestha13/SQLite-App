package com.a13hay.sqliteapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener{
    lateinit var button:Button
    lateinit var spinner:Spinner
    lateinit var edT:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        spinner = findViewById(R.id.spinner)
        edT = findViewById(R.id.edit_text)
        loadSpinnerData()
        button.setOnClickListener {
            var lable = edT.text.toString()
            if(lable.trim().isNotEmpty()){
                var db = DataBaseHandler(applicationContext)
                db.insertLable(lable)
                edT.setText("")
                var inputMethodManager:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(edT.windowToken,0)
                loadSpinnerData()
            }
            else{
                Toast.makeText(this,"Please enter something",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun loadSpinnerData() {
        var db:DataBaseHandler = DataBaseHandler(applicationContext)
        var lables:List<String> = db.getAllLables()
        var arrayAdapter:ArrayAdapter<String> = ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,lables)
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Toast.makeText(this, p0?.getItemAtPosition(p2).toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}