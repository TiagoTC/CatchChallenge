package acatch.tiago.catchchallenge.network

import acatch.tiago.catchchallenge.network.beans.Item
import io.reactivex.Single

interface INetwork {

	fun getItems(): Single<List<Item>>
}
