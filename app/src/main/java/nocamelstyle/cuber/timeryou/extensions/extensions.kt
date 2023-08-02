package nocamelstyle.cuber.timeryou.extensions

fun Int?.toFormattedTime(): String? {
    if (this == null) return null

    val time = this / 10

    val milliseconds = time % 100
    val seconds = (time / 100) % 60
    val minutes = (time / 6000)

    return "$minutes:$seconds.$milliseconds"
}