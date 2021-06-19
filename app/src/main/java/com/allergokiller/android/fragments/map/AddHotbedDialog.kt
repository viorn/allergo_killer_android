package com.allergokiller.android.fragments.map

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.*
import androidx.lifecycle.LiveData
import com.allergokiller.android.R
import com.allergokiller.android.tools.observer
import com.allergokiller.android.data.entity.Point
import kotlinx.android.synthetic.main.diaglo_add_hotbed.*

class AddHotbedDialog: DialogFragment() {

    private val vm by activityViewModels<AddHotbedDialogViewModel>()

    companion object {
        val RESULT_REQUEST = "add_hotbed_dialog"

        fun init(activity: FragmentActivity,point: Point):AddHotbedDialog {
            val vm by activity.viewModels<AddHotbedDialogViewModel>()
            vm.init(point)
            return AddHotbedDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MyDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setCancelable(false);

        return inflater.inflate(R.layout.diaglo_add_hotbed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.state.observe(this, { state ->
            tv_coords.text = "${state.point!!.lat}\n${state.point!!.lng}"
        })

        iv_close.setOnClickListener {
            dismiss()
        }

        et_name.setText( vm.state.value?.title ?: "")
        et_description.setText( vm.state.value?.description ?: "")

        et_name.addTextChangedListener {
            vm.setTitle(it?.toString() ?: "")
        }

        et_description.addTextChangedListener {
            vm.setDescription(it?.toString()?:"")
        }

        btn_add.setOnClickListener {
            setFragmentResult(RESULT_REQUEST, Bundle().apply {
                putString("title", vm.state.value!!.title)
                putString("body", vm.state.value!!.description)
            })
            dismiss()
        }
    }


    override fun dismiss() {
        super.dismiss()
    }
}