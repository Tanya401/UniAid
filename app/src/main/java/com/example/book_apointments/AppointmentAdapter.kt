
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book_apointments.Appointments
import com.example.book_apointments.DataBaseHelper
import com.example.book_apointments.R
import org.w3c.dom.Text


class AppointmentAdapter(appointments: List<Appointments>) :
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    private val mAppointments: List<Appointments>

    class ViewHolder(var mCardView: CardView) : RecyclerView.ViewHolder(
        mCardView
    )

    init {
        mAppointments = appointments
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the appointment_card_view layout
        val v = LayoutInflater.from(parent.context).inflate(R.layout.req_cardview,parent,false) as CardView
        return ViewHolder(v)
    }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // Bind data to the CardView
            val appointment: Appointments = mAppointments[position]

            // Get appointments for the current user email from the database
//            val email = "prachi.bagga.20cse@bmu.edu.in" // Replace with the current user email
//            val dbHelper = DataBaseHelper(holder.itemView.context)
//            val appointments = dbHelper.getAppointmentsForEmail(email)
//
//            // Find the appointment in the list with the same reason and date as the current appointment
//            val appointmentFromDb = appointments.find { it.email == email }
//
//
//            // Set the text for the appointment reason and date
//            val reasonView = holder.mCardView.findViewById<TextView>(R.id.appointment_reason)
//            reasonView.text = appointmentFromDb?.reason ?: ""
//            val dateView = holder.mCardView.findViewById<TextView>(R.id.appointment_date)
//            dateView.text = appointmentFromDb?.date ?: ""
//            // Set the text for the appointment time from the database
//            val timeView = holder.mCardView.findViewById<TextView>(R.id.appointment_time)
//            timeView.text = appointmentFromDb?.time ?: ""
//
//            dbHelper.close()
        }


    override fun getItemCount(): Int {
        return mAppointments.size
    }

}

