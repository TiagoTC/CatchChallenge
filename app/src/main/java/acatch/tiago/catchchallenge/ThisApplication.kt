package acatch.tiago.catchchallenge

import acatch.tiago.catchchallenge.network.INetwork
import acatch.tiago.catchchallenge.network.Network
import android.app.Application

/**
 * Created by tiago on 19/08/17.
 */
class ThisApplication: Application() {

	/**
	 * In the real world I would use a dependency injection framework instead
	 */
	val network: INetwork by lazy{
		Network()
	}
}
