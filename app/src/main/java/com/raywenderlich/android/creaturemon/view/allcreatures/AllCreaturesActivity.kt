package com.raywenderlich.android.creaturemon.view.allcreatures

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.raywenderlich.android.creaturemon.R
import com.raywenderlich.android.creaturemon.view.creature.CreatureActivity
import com.raywenderlich.android.creaturemon.viewmodels.AllCreaturesViewmodel
import kotlinx.android.synthetic.main.activity_all_creatures.*
import kotlinx.android.synthetic.main.content_all_creatures.*

class AllCreaturesActivity : AppCompatActivity() {

  private val adapter = CreatureAdapter(mutableListOf())
  private lateinit var viewmodel: AllCreaturesViewmodel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_all_creatures)
    setSupportActionBar(toolbar)

    viewmodel = ViewModelProviders.of(this).get(AllCreaturesViewmodel::class.java)

    creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
    creaturesRecyclerView.adapter = adapter
    viewmodel.getLiveDataCreatures().observe(this, Observer {
      it?.let { creatureList ->
        adapter.updateCreatures(creatureList)
      }
    })
    fab.setOnClickListener {
      startActivity(Intent(this, CreatureActivity::class.java))
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_clear_all -> {
        viewmodel.clearAllCreatures()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}
