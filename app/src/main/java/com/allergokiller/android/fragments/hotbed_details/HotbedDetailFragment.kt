package com.allergokiller.android.fragments.hotbed_details

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.allergokiller.android.R
import com.allergokiller.android.fragments.map.AddHotbedDialogViewModel
import com.allergokiller.android.fragments.map.MapFragmentViewModel
import kotlinx.android.parcel.Parcelize

class HotbedDetailFragment : Fragment() {
    private val vm by activityViewModels<HotbedDetailViewModel>()

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