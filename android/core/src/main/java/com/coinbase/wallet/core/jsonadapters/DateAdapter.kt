import android.annotation.SuppressLint
import com.squareup.moshi.*
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter : JsonAdapter<Date>() {
    companion object {
        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }

    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        return if (reader.peek() == JsonReader.Token.NULL) reader.nextNull()
        else dateFormat.parse(reader.nextString())
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        if (value == null) writer.value(null as String?)
        else writer.value(dateFormat.format(value))
    }
}
