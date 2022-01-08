package com.example.newmed.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.example.newmed.R
import com.example.newmed.database.MedDatabase
import com.example.newmed.reposotiry.PatientRepository
import kotlinx.coroutines.*

/**
 * Implementation of App Widget functionality.
 */
class PatientWidget : AppWidgetProvider() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    lateinit var db: MedDatabase

    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        db = MedDatabase.getDatabase(context, applicationScope)
        val repository = PatientRepository(db.patientDao())

        uiScope.launch {
            val patient = repository.getPatientById(id = 5)
            if (patient != null) {
                for (appWidgetId in appWidgetIds) {
                    val views = RemoteViews(context.packageName, R.layout.patient_widget)
                    views.setTextViewText(R.id.nameCall, patient.nameCall)
                    views.setTextViewText(R.id.numberCall, patient.numberCall)
                    views.setTextViewText(R.id.address, patient.addressPatient)

                    appWidgetManager.updateAppWidget(appWidgetId, views)
                }
            }
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}