package acatch.tiago.catchchallenge.screens

import acatch.tiago.catchchallenge.R
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_item_detail)

		val toolbar: Toolbar = findViewById(R.id.detail_toolbar)
		setSupportActionBar(toolbar)

		val actionBar = supportActionBar
		actionBar?.setDisplayHomeAsUpEnabled(true)

		if (savedInstanceState == null) {

			val arguments = Bundle()
			arguments.putSerializable(ItemDetailFragment.ARG_ITEM, intent.getSerializableExtra(ItemDetailFragment.ARG_ITEM))

			val fragment = ItemDetailFragment()
			fragment.arguments = arguments
			supportFragmentManager.beginTransaction().add(R.id.item_detail_container, fragment).commit()
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {

		val id = item.itemId
		if (id == android.R.id.home) {

			navigateUpTo(Intent(this, ItemListActivity::class.java))
			return true
		}
		return super.onOptionsItemSelected(item)
	}
}
