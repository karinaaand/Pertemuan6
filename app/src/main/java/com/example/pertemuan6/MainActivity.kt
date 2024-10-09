package com.example.pertemuan6

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan6.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            // Get Array
            val monthList = resources.getStringArray(R.array.month)

            // Initiate Time and Date
            var selectedTime = "${timePicker.hour}:${timePicker.minute}"
            val _tempCalendar: Calendar = Calendar.getInstance()
            _tempCalendar.timeInMillis = System.currentTimeMillis()
            var selectedDate = "${_tempCalendar.get(Calendar.DAY_OF_MONTH)} ${monthList[_tempCalendar.get(Calendar.MONTH)]} ${_tempCalendar.get(Calendar.YEAR)}"

            // Kehadiran Dropdown List
            val kehadiranList = arrayOf("Hadir Tepat Waktu", "Terlambat", "Izin")
            val adapterKehadiran = ArrayAdapter(
                this@MainActivity,  // Use context of MainActivity
                android.R.layout.simple_spinner_item,
                kehadiranList
            )
            adapterKehadiran.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kehadiranSpinner.adapter = adapterKehadiran

            // Selected Kehadiran
            kehadiranSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        // Menampilkan pesan pilihan kehadiran
                        val selectedKehadiran = kehadiranList[position]
                        Toast.makeText(
                            this@MainActivity,
                            "Kehadiran: $selectedKehadiran",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Tidak ada aksi jika tidak ada pilihan yang dipilih
                    }
                }

            // Submit Button Action
            submitButton.setOnClickListener {
                val keterangan = keteranganEdittext.text.toString()
                Toast.makeText(
                    this@MainActivity,
                    "Time: $selectedTime\nDate: $selectedDate\nKehadiran: ${kehadiranSpinner.selectedItem}\nKeterangan: $keterangan",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
