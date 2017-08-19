package acatch.tiago.catchchallenge

import acatch.tiago.catchchallenge.network.INetwork
import acatch.tiago.catchchallenge.network.Network
import android.app.Application

/**
 * Created by tiago on 19/08/17.
 */
class ThisApplication: Application() {

	lateinit var network: INetwork

	override fun onCreate() {

		super.onCreate()

		network = Network()
	}
}