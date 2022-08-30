package wilson_frai.snow.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_weather.view.*
import wilson_frai.domain.models.WeatherModel
import wilson_frai.snow.R


class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setData(weathers: List<WeatherModel>, position: Int) {
            val hour = "${(3 * position)}:00"
            val temperature = "${weathers[position].temperature}°"
            itemView.item_weather_hour.text = hour
            itemView.item_weather_temperature.text = temperature

            when (weathers[position].weather) {
                "Thunderstorm" -> itemView.item_weather_image.setImageResource(R.drawable.img_thunderstorm)
                "Rain" -> itemView.item_weather_image.setImageResource(R.drawable.img_rain)
                "Drizzle" -> itemView.item_weather_image.setImageResource(R.drawable.img_rain)
                "Clouds" -> itemView.item_weather_image.setImageResource(R.drawable.img_clouds)
                "Clear" -> itemView.item_weather_image.setImageResource(R.drawable.img_sun)
                "Snow" -> itemView.item_weather_image.setImageResource(R.drawable.img_snow)
                else -> itemView.item_weather_image.setImageResource(R.drawable.img_thunderstorm)
            }
        }
    }

    // class WeatherAdapter
    var weathers: List<WeatherModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.setData(weathers, position = position)
    }

    override fun getItemCount(): Int = weathers.size
}