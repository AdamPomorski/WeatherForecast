package com.plcoding.weatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.util.Resource
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application

):LocationTracker {
    override suspend fun getCurrentLocation(): Resource<Location> {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasAccessFineLocationPermission) return Resource.Error(message = "No fine location permission granted")
        if(!hasCoarseLocationPermission) return Resource.Error(message = "No coarse location permission granted")
        if(!isGPSEnabled)  return Resource.Error(message = "No GPS location permission granted")

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if(isComplete){
                    if(isSuccessful){
                        cont.resume(Resource.Success(data = result))
                    } else{
                        cont.resume(Resource.Error(message = "Error in retrieving location"))
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(Resource.Success(data = it))
                }
                addOnFailureListener{
                    cont.resume(Resource.Error(message = "Failure in retrieving location"))
                }
                addOnCanceledListener {
                    cont.cancel()
                }

            }


        }



    }
}