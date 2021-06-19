package com.allergokiller.android.fragments.map

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import com.allergokiller.android.App
import com.allergokiller.android.R
import com.allergokiller.android.data.entity.Point
import com.allergokiller.android.events.MessageEvent
import com.allergokiller.android.factories.map.IHotbedOSMOverlayFactory
import kotlinx.android.synthetic.main.fragment_map.*
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


class MapFragment : Fragment(), MapEventsReceiver {

    private val vm by activityViewModels<MapFragmentViewModel>()

    private var mLocationOverlay: MyLocationNewOverlay? = null

    private var sfpo: Overlay? = null

    private val hotbedOverlayFactory get() = App.appComponent!!.hotbedOverlayFactory()

    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        map.setTileSource(TileSourceFactory.MAPNIK);
        this.mLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), map)
        this.mLocationOverlay?.enableMyLocation()
        map.overlays.add(this.mLocationOverlay)

        map.controller.setZoom(18.0)
        mLocationOverlay?.runOnFirstFix {
            vm.findByCenter(
                mLocationOverlay?.myLocation?.latitude!!,
                mLocationOverlay?.myLocation?.longitude!!
            )
            view.post {
                map.controller.animateTo(mLocationOverlay?.myLocation)
            }
        }

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        val overlayEvents = MapEventsOverlay(context, this);
        map.overlays.add(overlayEvents)


        permissionResult.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )

        vm.events.observe(this.viewLifecycleOwner, { event ->
            if (event is MessageEvent) {
                Toast.makeText(this@MapFragment.activity, event.message, Toast.LENGTH_SHORT).show()
            }
        })

        vm.state.observe(this.viewLifecycleOwner, { state ->
            val oldSfpo = sfpo

            sfpo = hotbedOverlayFactory.buildOverlay(state.hotbedList, object : IHotbedOSMOverlayFactory.PointClickListener {
                override fun onClickListener(point: IHotbedOSMOverlayFactory.DataGeoPoint) {
                    Toast.makeText(
                        context,
                        "You clicked " + point.data,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            if (oldSfpo != null) {
                map.overlays.remove(oldSfpo)
            }

            map.overlays.add(sfpo!!)
        })
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        map.onPause()
        super.onPause()
    }

    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {

        return false
    }

    override fun longPressHelper(p: GeoPoint?): Boolean {
        val fragment =
            AddHotbedDialog.init(requireActivity(), Point(lat = p!!.latitude, lng = p!!.longitude))
        fragment.show(childFragmentManager, "add_hotbed_dialog")
        fragment.setFragmentResultListener(AddHotbedDialog.RESULT_REQUEST) { s, b ->
            val title = b.getString("title")!!
            val body = b.getString("body")!!
            vm.addHotbed(title, body, lat = p.latitude, lng = p.longitude)
        }
        return true
    }
}