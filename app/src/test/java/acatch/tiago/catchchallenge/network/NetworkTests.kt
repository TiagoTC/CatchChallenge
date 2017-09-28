package acatch.tiago.catchchallenge.network

import junit.framework.Assert.assertNotNull
import org.junit.Test

/**
 * Created by tiago on 19/08/17.
 */
class NetworkTests{

	private val network: INetwork

	init {
		network = Network()
	}

	@Test
	@Throws(Exception::class)
	fun `test_get_items_call`() {

		val callResponse = network.getItems()
		val items = callResponse.blockingGet()

		assertNotNull(items)
		assert(items.isNotEmpty())
	}
}