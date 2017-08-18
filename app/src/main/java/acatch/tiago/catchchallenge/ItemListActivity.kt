package acatch.tiago.catchchallenge


import acatch.tiago.catchchallenge.dummy.DummyContent
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private var mTwoPane: Boolean = false

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_item_list)

		val toolbar: Toolbar = findViewById(R.id.toolbar)
		setSupportActionBar(toolbar)
		toolbar.title = title

		val recyclerView: RecyclerView = findViewById(R.id.item_list)!!
		setupRecyclerView(recyclerView)

		if (findViewById<View>(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-w900dp).
			// If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true
		}
	}

	private fun setupRecyclerView(recyclerView: RecyclerView) {

		recyclerView.adapter = SimpleItemRecyclerViewAdapter(DummyContent.ITEMS)
	}

	inner class SimpleItemRecyclerViewAdapter(private val mValues: List<DummyContent.DummyItem>) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

		override fun onCreateViewHolder(parent: ViewGroup,
										viewType: Int): ViewHolder {

			val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
			return ViewHolder(view)
		}

		override fun onBindViewHolder(holder: ViewHolder,
									  position: Int) {

			holder.mItem = mValues[position]
			holder.mIdView.text = mValues[position].id
			holder.mContentView.text = mValues[position].content

			holder.mView.setOnClickListener { v ->
				if (mTwoPane) {
					val arguments = Bundle()
					arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem!!.id)
					val fragment = ItemDetailFragment()
					fragment.arguments = arguments
					supportFragmentManager.beginTransaction().replace(R.id.item_detail_container, fragment).commit()
				} else {
					val context = v.context
					val intent = Intent(context, ItemDetailActivity::class.java)
					intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem!!.id)

					context.startActivity(intent)
				}
			}
		}

		override fun getItemCount(): Int {

			return mValues.size
		}

		inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

			val mIdView: TextView
			val mContentView: TextView
			var mItem: DummyContent.DummyItem? = null

			init {
				mIdView = mView.findViewById<TextView>(R.id.id)
				mContentView = mView.findViewById<TextView>(R.id.content)
			}

			override fun toString(): String {

				return super.toString() + " '" + mContentView.text + "'"
			}
		}
	}
}
