package acatch.tiago.catchchallenge.screens

import acatch.tiago.catchchallenge.R
import acatch.tiago.catchchallenge.network.beans.Item
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity(), LifecycleRegistryOwner, SwipeRefreshLayout.OnRefreshListener {

	private val lifecycleRegistry = LifecycleRegistry(this)

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private var mTwoPane: Boolean = false

	private lateinit var swipeRefresh: SwipeRefreshLayout
	private lateinit var itemsViewModel: ItemsViewModel


	override fun getLifecycle() = lifecycleRegistry

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_item_list)

		swipeRefresh = findViewById(R.id.swipe_refresh)
		swipeRefresh.setOnRefreshListener(this)

		val toolbar: Toolbar = findViewById(R.id.toolbar)
		setSupportActionBar(toolbar)
		toolbar.title = title

		if (findViewById<View>(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-w900dp).
			// If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true
		}

		itemsViewModel = ViewModelProviders.of(this).get(ItemsViewModel::class.java)
		itemsViewModel.observeItems().observe(this, Observer {

			if(it!!.hasError()){
				Toast.makeText(applicationContext, R.string.failed_to_load_items, Toast.LENGTH_SHORT).show()
			}

			if(it.hasItems()){

				val recyclerView: RecyclerView = findViewById(R.id.item_list)
				if(it.items != null){
					recyclerView.adapter = SimpleItemRecyclerViewAdapter(it.items)
				}
			}
		})

		itemsViewModel.observeItems().fetch()
	}

	override fun onRefresh() {

		itemsViewModel.observeItems().fetch()
		swipeRefresh.isRefreshing = false
	}

	inner class SimpleItemRecyclerViewAdapter(private val mValues: List<Item>) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

		override fun onCreateViewHolder(parent: ViewGroup,
										viewType: Int): ViewHolder {

			val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
			return ViewHolder(view)
		}

		override fun onBindViewHolder(holder: ViewHolder,
									  position: Int) {

			holder.mItem = mValues[position]
			holder.mIdView.text = mValues[position].id.toString()
			holder.mContentView.text = mValues[position].title

			holder.mView.setOnClickListener { v ->

				if (mTwoPane) {

					val arguments = Bundle()
					arguments.putSerializable(ItemDetailFragment.ARG_ITEM, holder.mItem)

					val fragment = ItemDetailFragment()
					fragment.arguments = arguments
					supportFragmentManager.beginTransaction().replace(R.id.item_detail_container, fragment).commit()

				} else {

					val context = v.context
					val intent = Intent(context, ItemDetailActivity::class.java)
					intent.putExtra(ItemDetailFragment.ARG_ITEM, holder.mItem)

					context.startActivity(intent)
				}
			}
		}

		override fun getItemCount(): Int {

			return mValues.size
		}

		inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

			val mIdView: TextView = mView.findViewById<TextView>(R.id.id)
			val mContentView: TextView = mView.findViewById<TextView>(R.id.content)
			var mItem: Item? = null
		}
	}
}
