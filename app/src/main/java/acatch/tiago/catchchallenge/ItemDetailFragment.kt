package acatch.tiago.catchchallenge

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ItemDetailFragment : Fragment() {

	private var mItem: Item? = null

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		if (arguments.containsKey(ARG_ITEM)) {

			mItem = arguments.getSerializable(ARG_ITEM) as Item

			val activity = this.activity
			val appBarLayout = activity.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
			appBarLayout?.title = mItem!!.title
		}
	}

	override fun onCreateView(inflater: LayoutInflater?,
							  container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {

		val rootView = inflater!!.inflate(R.layout.item_detail, container, false)

		val viewSubtitle: TextView = rootView.findViewById(R.id.subtitle)
		viewSubtitle.text = mItem?.subtitle

		val viewContent: TextView = rootView.findViewById(R.id.content)
		viewContent.text = mItem?.content

		return rootView
	}

	companion object {

		val ARG_ITEM = "arg_item"
	}
}
