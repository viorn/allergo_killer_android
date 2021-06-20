package com.allergokiller.android.fragments.map

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.allergokiller.android.App
import com.allergokiller.android.R
import com.allergokiller.android.core.actions.MessageAction
import com.allergokiller.android.factories.map.HotbedPoint
import com.allergokiller.android.factories.map.PointClickListener
import com.allergokiller.android.core.AFragment
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_map.*
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


class MapFragment : AFragment(), MapEventsReceiver {

    private val vm by activityViewModels<MapFragmentViewModel>()

    private var mLocationOverlay: MyLocationNewOverlay? = null

    private var sfpo: Overlay? = null

    private val hotbedOverlayFactory get() = App.appComponent.hotbedOverlayFactory()

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

        childFragmentManager.setFragmentResultListener(AddHotbedDialog.RESULT_REQUEST, viewLifecycleOwner) { s, b ->
            val result = b.getParcelable<AddHotbedDialog.Result>("result")!!
            vm.addHotbed(result.title, result.description, lat = result.lat, lng = result.lng)
        }

        map.setTileSource(TileSourceFactory.MAPNIK);
        this.mLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), map)
        this.mLocationOverlay?.enableMyLocation()
        map.overlays.add(this.mLocationOverlay)

        map.controller.setZoom(18.0)
        mLocationOverlay?.runOnFirstFix {
            view.post {
                vm.findByCenter(
                    mLocationOverlay?.myLocation?.latitude!!,
                    mLocationOverlay?.myLocation?.longitude!!
                )
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

        vm.actionsFlowable.subscribe { event ->
            if (event is MessageAction) {
                Toast.makeText(this@MapFragment.activity, event.message, Toast.LENGTH_SHORT).show()
            }
        }.addTo(createViewCompositeDisposable)


        vm.stateFlowable.map { it.loading }.distinctUntilChanged().subscribe { loading ->
            fl_loading.visibility = if (loading) View.VISIBLE else View.GONE
        }.addTo(createViewCompositeDisposable)

        vm.stateFlowable.map { it.hotbedList }.distinctUntilChanged().subscribe { hotbedList ->
            val oldSfpo = sfpo

            sfpo = hotbedOverlayFactory.buildOverlay(hotbedList, object : PointClickListener {
                override fun onClickListener(point: HotbedPoint) {
                    Toast.makeText(
                        context,
                        "You clicked " + point.hotbed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            if (oldSfpo != null) {
                map.overlays.remove(oldSfpo)
            }

            map.overlays.add(sfpo!!)
        }.addTo(createViewCompositeDisposable)
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
            AddHotbedDialog.init(AddHotbedDialog.Params(lat = p!!.latitude, lng = p!!.longitude))
        fragment.show(childFragmentManager, "add_hotbed_dialog")
        return true
    }
}