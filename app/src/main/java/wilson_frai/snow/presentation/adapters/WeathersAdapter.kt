package wilson_frai.snow.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_future_forecast.view.*
import kotlinx.android.synthetic.main.item_list_weather.view.*
import kotlinx.android.synthetic.main.item_weather.view.*
import wilson_frai.domain.models.WeatherModel
import wilson_frai.snow.R


class WeathersAdapter(private val context: Context, private val days: List<String>) : RecyclerView.Adapter<WeathersAdapter.WeathersViewHolder>() {
    class WeathersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setData(weathers: List<WeatherModel>, position: Int, context: Context, days: List<String>) {
            val adapter = WeatherAdapter()
            adapter.weathers = weathers.subList(position, position+7)
            itemView.ilw_text.text = days[position]
            itemView.ilw_recycler.adapter = adapter
        }
    }

    // class WeatherAdapter
    var weathers: List<WeatherModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeathersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_weather, parent, false)
        return WeathersViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeathersViewHolder, position: Int) {
        holder.setData(weathers, position, context, days)
    }

    override fun getItemCount(): Int = (weathers.size / 8)

}