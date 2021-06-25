package com.allergokiller.hotbedmap.fragments.hotbed_details

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.allergokiller.core.RxFragment
import com.allergokiller.hotbedmap.R
import kotlinx.android.parcel.Parcelize

class HotbedDetailFragment : RxFragment() {
    private val vm by viewModels<HotbedDetailViewModel>()

    @Parcelize
    data class Params(
        val hotbedId: Long
    ) : Parcelable

    companion object {
        fun init(params: Params): HotbedDetailFragment {
            return HotbedDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("params", params)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hotbed_detail, container, false)
    }
}