package wilson_frai.snow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_weather.view.*
import wilson_frai.domain.models.WeatherModel
import wilson_frai.snow.R
import kotlin.math.pow

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var weathers = mutableListOf<WeatherModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.itemView.item_weather_hour.text = (3 * position).toString()
        holder.itemView.item_weather_temperature.text = weathers[position].temperature.toString()

        when (weathers[position].weather) {
            "Thunderstorm" -> holder.itemView.item_weather_image.setImageResource(R.drawable.img_thunderstorm)
            "Rain" -> holder.itemView.item_weather_image.setImageResource(R.drawable.img_rain)
            "Drizzle" -> holder.itemView.item_weather_image.setImageResource(R.drawable.img_rain)
            "Clouds" -> holder.itemView.item_weather_image.setImageResource(R.drawable.img_clouds)
            "Clear" -> holder.itemView.item_weather_image.setImageResource(R.drawable.img_sun)
            "Snow" -> holder.itemView.item_weather_image.setImageResource(R.drawable.img_snow)
            else -> holder.itemView.item_weather_image.setImageResource(R.drawable.img_thunderstorm)
        }
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    fun setData(data: List<WeatherModel>) {
        weathers.clear()
        data.forEach() { weather ->
            weathers.add(weather)
        }
    }
}