package com.allergokiller.android.fragments.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import com.allergokiller.android.R
import com.allergokiller.android.data.entity.Point
import kotlinx.android.synthetic.main.fragment_map.*
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import org.osmdroid.views.overlay.simplefastpoint.LabelledGeoPoint
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlayOptions
import org.osmdroid.views.overlay.simplefastpoint.SimplePointTheme


class MapFragment : Fragment(), MapEventsReceiver {

    private val vm by activityViewModels<MapFragmentViewModel>()

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1

    private var mLocationOverlay: MyLocationNewOverlay? = null

    private var sfpo: SimpleFastPointOverlay? = null

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

        requestPermissionsIfNecessary(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        );

        vm.hotbedsList.observe(this.viewLifecycleOwner, {
            val oldSfpo = sfpo

            val pt = SimplePointTheme(it.map {
                LabelledGeoPoint(it.position.lat, it.position.lng, "Point #" + it.id)
            }, true)

            val textStyle = Paint()
            textStyle.style = Paint.Style.FILL
            textStyle.color = Color.parseColor("#0000ff")
            textStyle.textAlign = Paint.Align.CENTER
            textStyle.textSize = 24f
            val opt = SimpleFastPointOverlayOptions.getDefaultStyle()
                .setAlgorithm(SimpleFastPointOverlayOptions.RenderingAlgorithm.MAXIMUM_OPTIMIZATION)
                .setRadius(7f).setIsClickable(true).setCellSize(15).setTextStyle(textStyle)

            sfpo = SimpleFastPointOverlay(pt, opt)

            sfpo!!.setOnClickListener { points, point ->
                Toast.makeText(
                    context,
                    "You clicked " + (points[point] as LabelledGeoPoint).label,
                    Toast.LENGTH_SHORT
                ).show()
            }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        val permissionsToRequest: ArrayList<String?> = ArrayList()
        for (i in grantResults.indices) {
            permissionsToRequest.add(permissions[i])
        }
        if (permissionsToRequest.size > 0) {
            requestPermissions(
                permissionsToRequest.toArray(arrayOfNulls(0)),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    private fun requestPermissionsIfNecessary(permissions: Array<String>) {
        val permissionsToRequest: ArrayList<String> = ArrayList()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this.requireContext(), permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted
                permissionsToRequest.add(permission)
            }
        }
        if (permissionsToRequest.size > 0) {
            requestPermissions(
                permissionsToRequest.toArray(arrayOfNulls(0)),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {

        return false
    }

    override fun longPressHelper(p: GeoPoint?): Boolean {
        val fragment = AddHotbedDialog.init(requireActivity(), Point(lat = p!!.latitude, lng = p!!.longitude))
        fragment.show(childFragmentManager, "add_hotbed_dialog")
        fragment.setFragmentResultListener(AddHotbedDialog.RESULT_REQUEST) { s, b ->
            val title = b.getString("title")!!
            val body = b.getString("body")!!
            vm.addHotbed(title, body, lat = p.latitude, lng = p.longitude)
        }
        return true
    }
}