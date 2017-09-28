package acatch.tiago.catchchallenge.screens

import acatch.tiago.catchchallenge.network.beans.Item

/**
 * Created by tiago on 19/08/17.
 */
class ItemsObservableData private constructor(val items: List<Item>,
											  private val error: Boolean) {

	fun hasItems(): Boolean {
		return items.isNotEmpty()
	}

	fun hasError(): Boolean {
		return error
	}

	companion object {

		fun buildItems(nForecast: List<Item>): ItemsObservableData {
			return ItemsObservableData(nForecast, false)
		}

		fun buildError(): ItemsObservableData {
			return ItemsObservableData(ArrayList(), true)
		}
	}
}
