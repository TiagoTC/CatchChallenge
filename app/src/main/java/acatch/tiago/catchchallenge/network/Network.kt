package acatch.tiago.catchchallenge.network

import acatch.tiago.catchchallenge.network.beans.Item
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Network : INetwork {

	private val weatherAPI: IWebAPI

	init {

		val retrofit = Retrofit.Builder()
				.baseUrl("https://raw.githubusercontent.com/catchnz/android-test/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()

		weatherAPI = retrofit.create(IWebAPI::class.java)
	}

	override fun getItems(): Single<List<Item>> {

		return weatherAPI.items
	}
}
