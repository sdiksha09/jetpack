package com.example.shipra.jetpack


import android.os.Bundle
import android.text.TextUtils.isEmpty
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_first2.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first2, container, false)
    }

    private  var dbInstance:PersonDatabase?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        async(UI){
            val instance=bg{PersonDatabase.getInstance(this@FirstFragment.context!!)}
            dbInstance= instance.await()

        }

        btn_clear.setOnClickListener {
          personList.adapter=null
        }
        btn_add.setOnClickListener {
            val name=edt_name.text.toString().trim()
            val age =edt_age.text.toString().trim().toInt()
            val p=Person(null,name,age)


            async(UI){

                val insert=bg{dbInstance?.personDao()?.insertData(p)}
                insert.await()
                insertMessage()

            }

        }

        btn_get.setOnClickListener {

            async(UI) {

                val fetch = bg { dbInstance?.personDao()?.getAll() }

                populateList(fetch.await())

            }
        }
    }

    private fun populateList(data: List<Person>?){
        if (data==null){
            Toast.makeText(this.context,"there is no data",Toast.LENGTH_SHORT).show()
            return
        }


        val adapter =object: ArrayAdapter<Person>(this.context,android.R.layout.simple_list_item_2,android.R.id.text1,data){

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

               val v = super.getView(position, convertView, parent)
              val p = data[position]

                v.findViewById<TextView>(android.R.id.text1).text=p.name
                v.findViewById<TextView>(android.R.id.text2).text=p.age.toString()
                return v


            }
        }
        personList.adapter = adapter





    }



    private fun insertMessage(){

        Toast.makeText(this.context,"Data Inserted",Toast.LENGTH_SHORT).show()
    }


}
