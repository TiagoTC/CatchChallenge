package acatch.tiago.catchchallenge.network

import acatch.tiago.catchchallenge.network.beans.Item
import io.reactivex.Single
import retrofit2.http.GET

/**
 * This is the lowest layer of our network stack. This should be used by INetwork only!
 * (that is why it is package protected). Our general application code should use the INetwork
 * interface instead.
 */
internal interface IWebAPI {

	@get:GET("master/data/data.json")
	val items: Single<List<Item>>
}
