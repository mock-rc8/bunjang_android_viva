package com.example.bunjang_clone.src.home.detail.buy.address

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.Button
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.DialogDeleteAddressBinding
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressResponse
import com.example.bunjang_clone.src.home.detail.buy.address.models.DeleteAddressData
import com.example.bunjang_clone.src.home.detail.buy.address.models.DeleteResponse
import com.example.bunjang_clone.src.home.detail.buy.address.models.GetAddressData

class DeleteAddressActivity(context: Context, shippingIdx: String) :
    BaseActivity<DialogDeleteAddressBinding>(DialogDeleteAddressBinding::inflate),
    AddressActivityInterface {

    private val dialog = Dialog(context)
    private val mContext = context
    private var shippingIdx = Integer.parseInt(shippingIdx)

    fun showDialog() {
        dialog.setContentView(R.layout.dialog_delete_address)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        var lp = WindowManager.LayoutParams()
        lp.width = 900
        lp.height = 570

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(lp.width, lp.height)

        val yes = dialog.findViewById<Button>(R.id.btn_yes)
        val no = dialog.findViewById<Button>(R.id.btn_no)

        yes.setOnClickListener {
            val patchRequest = DeleteAddressData(shippingIdx = shippingIdx)
            AddressService(this).patchDeleteData(patchRequest)
        }
        no.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onPostAddressSuccess(response: AddressResponse) {
    }

    override fun onPostAddressFail(message: String) {
    }

    override fun onGetAddressSuccess(response: GetAddressData) {
    }

    override fun onGetAddressFail(message: String) {
    }

    override fun onPatchDeleteAddressSuccess(response: DeleteResponse) {
        if (response.code == 1000){
            Intent(mContext, AddAddressActivity::class.java).apply {
            }.run{
                mContext.startActivity(this)
            }
            dialog.dismiss()
        }
    }

    override fun onPatchDeleteAddressFail(message: String) {
    }
}