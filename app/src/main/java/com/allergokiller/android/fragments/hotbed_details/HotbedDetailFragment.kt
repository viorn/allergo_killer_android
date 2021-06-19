package com.allergokiller.android.fragments.hotbed_details

import android.os.Bundle
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

class HotbedDetailFragment: Fragment() {
    private val vm by activityViewModels<HotbedDetailViewModel>()

    companion object {
        fun init(activity: FragmentActivity, hotbedId: Long): HotbedDetailFragment {
            val vm by activity.viewModels<HotbedDetailViewModel>()
            vm.init(hotbedId)
            return HotbedDetailFragment()
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