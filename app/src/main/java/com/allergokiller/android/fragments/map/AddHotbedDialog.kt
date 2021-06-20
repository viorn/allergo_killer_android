package com.allergokiller.android.fragments.map

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.*
import com.allergokiller.android.R
import com.allergokiller.android.data.entity.Point
import com.allergokiller.android.fragments.ADialogFragment
import io.reactivex.rxkotlin.addTo
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.diaglo_add_hotbed.*

class AddHotbedDialog() : ADialogFragment() {

    private val vm by activityViewModels<AddHotbedDialogViewModel>()

    @Parcelize
    data class Params(
        val lat: Double, val lng: Double
    ) : Parcelable

    @Parcelize
    data class Result(
        val title: String,
        val description: String
    ) : Parcelable

    companion object {
        val RESULT_REQUEST = "add_hotbed_dialog"

        fun init(params: Params): AddHotbedDialog {
            return AddHotbedDialog().apply {
                arguments = Bundle().apply {
                    putParcelable("params", params)
                }
            }
        }
    }

    private val params get() = requireArguments().getParcelable<Params>("params")!!

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

        vm.init(Point(lat = params.lat, lng = params.lng))

        vm.stateFlowable.subscribe { state ->
            tv_coords.text = "${state.point!!.lat}\n${state.point!!.lng}"
        }.addTo(viewCompositeDisposable)

        iv_close.setOnClickListener {
            dismiss()
        }

        et_name.setText(vm.title)
        et_description.setText(vm.description)

        et_name.addTextChangedListener {
            vm.setTitle(it?.toString() ?: "")
        }

        et_description.addTextChangedListener {
            vm.setDescription(it?.toString() ?: "")
        }

        btn_add.setOnClickListener {
            setFragmentResult(RESULT_REQUEST, Bundle().apply {
                putParcelable("result", Result(
                    title = vm.title,
                    description = vm.description
                ))
            })
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vm.reset()
    }

    override fun dismiss() {
        super.dismiss()
    }
}