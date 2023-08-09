package nocamelstyle.cuber.timeryou.ui.screens.statistics

import nocamelstyle.cuber.timeryou.dataset.Record

interface StatisticsContract {

    sealed class Event {
        class SelectType(val type: String): Event()
        class SelectCategory(val type: String): Event()
        object Resume: Event()
        class Remove(val item: Record): Event()
    }

    sealed class Effect {

    }

}