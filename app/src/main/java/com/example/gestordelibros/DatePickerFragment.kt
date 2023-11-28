package com.example.gestordelibros

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.Calendar


class DatePickerFragment(var fechaIntro : EditText) : DialogFragment(), DatePickerDialog.OnDateSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val año = c.get(Calendar.YEAR)
        val mes = c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireActivity(), this, año, mes, dia)
    }



    override fun onDateSet(view: DatePicker, año: Int, mes: Int, dia: Int) {
        val mesSumado = mes + 1
        Toast.makeText(requireContext(), "$dia/$mesSumado/$año", Toast.LENGTH_SHORT).show()
        var fechaSeleccionada = "$dia/$mesSumado/$año"
        fechaIntro.setText(fechaSeleccionada)
    }


}