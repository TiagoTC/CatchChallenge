package acatch.tiago.catchchallenge.network.beans

import java.io.Serializable

/**
 * Created by tiago on 19/08/17.
 */
data class Item(val id: Int,
				val title: String,
				val subtitle: String,
				val content: String) : Serializable