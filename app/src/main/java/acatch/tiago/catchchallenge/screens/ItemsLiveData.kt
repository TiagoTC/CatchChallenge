package acatch.tiago.catchchallenge.screens

import acatch.tiago.catchchallenge.network.INetwork
import acatch.tiago.catchchallenge.network.beans.Item
import android.arch.lifecycle.LiveData
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by tiago on 19/08/17.
 */
class ItemsLiveData(private val network: INetwork,
					private val disposables: MutableList<Disposable> = ArrayList<Disposable>()) : LiveData<ItemsObservableData>() {

	fun fetch() {

		network.getItems()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(object : SingleObserver<List<Item>> {

					override fun onSubscribe(d: Disposable) {

						disposables.add(d)
					}

					override fun onSuccess(items: List<Item>) {

						value = ItemsObservableData.buildItems(items)
					}

					override fun onError(e: Throwable) {

						// Unfortunately LiveData does not allow us to return an error.
						value = ItemsObservableData.buildError()
					}
				})
	}

	override fun onActive() {

		fetch()
	}

	override fun onInactive() {

		for (disposable in disposables) {
			disposable.dispose()
		}
	}
}