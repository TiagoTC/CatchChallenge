package acatch.tiago.catchchallenge.screens

import acatch.tiago.catchchallenge.ThisApplication
import android.app.Application
import android.arch.lifecycle.AndroidViewModel

/**
 * Created by tiago on 19/08/17.
 */
class ItemsViewModel(application: Application) : AndroidViewModel(application) {

	private val itemsLiveData: ItemsLiveData

	init {

		val thisApplication = application as ThisApplication
		itemsLiveData = ItemsLiveData(thisApplication.network)
	}

	fun observeItems() = itemsLiveData
}
