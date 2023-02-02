package com.shira.testforpango

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shira.testforpango.Constants.MINEABLE
import com.shira.testforpango.Constants.PRICE
import com.shira.testforpango.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog:BottomSheetDialog

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            showBottomSheet()
        }
    }

    @SuppressLint("ResourceType")
    private fun showBottomSheet(){
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet,null)
        dialog = BottomSheetDialog(this,R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        val editText: EditText? = dialog.findViewById(R.id.etNum)
        val button:Button? = dialog.findViewById(R.id.btnFinish)
        val checkBox:CheckBox? = dialog.findViewById(R.id.checkBox)
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = FirstFragment()
        button?.setOnClickListener {
            val price = editText?.text.toString()

            val mBundle = Bundle()
            if (price != "")
              mBundle.putDouble(PRICE,price.toDouble())
            checkBox?.isChecked?.let { it1 -> mBundle.putBoolean(MINEABLE, it1) }
            mFragment.arguments = mBundle
            mFragmentTransaction.add(R.id.mFragment,mFragment).commit()
            dialog.dismiss()
        }
        dialog.show()
    }

}
