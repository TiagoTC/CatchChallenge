package acatch.tiago.catchchallenge.screens

import acatch.tiago.catchchallenge.R
import acatch.tiago.catchchallenge.network.beans.Item
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ItemDetailFragment : Fragment() {

	companion object {
		val ARG_ITEM = "ARG_ITEM"
	}

	private val item by lazy {
		arguments.getSerializable(ARG_ITEM) as Item
	}

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		val activity = this.activity
		val appBarLayout = activity.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
		appBarLayout?.title = item.title
	}

	override fun onCreateView(inflater: LayoutInflater,
							  container: ViewGroup?,
							  savedInstanceState: Bundle?): View {

		val rootView = inflater.inflate(R.layout.item_detail, container, false)

		val viewId: TextView = rootView.findViewById(R.id.id)
		viewId.text = rootView.context.getString(R.string.id, item.id)

		val viewSubtitle: TextView = rootView.findViewById(R.id.subtitle)
		viewSubtitle.text = rootView.context.getString(R.string.subtitle, item.subtitle)

		val viewContent: TextView = rootView.findViewById(R.id.content)
		viewContent.text = item.content

		return rootView
	}
}
