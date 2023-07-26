package nocamelstyle.cuber.timeryou.extensions

fun Int?.toFormattedTime(): String? {
    if (this == null) return null

    val milliseconds = this % 100
    val seconds = (this / 100) % 60
    val minutes = (this / 6000)

    return "$minutes:$seconds.$milliseconds"
}