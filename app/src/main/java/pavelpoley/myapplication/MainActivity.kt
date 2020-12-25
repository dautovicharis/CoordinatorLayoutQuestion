package pavelpoley.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var bottomMenuView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomMenuView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    fun hideBottomMenu () {
        bottomMenuView?.visibility = View.GONE
        removeNavigationItemSelectedListener(bottomMenuView)
    }

    fun showBottomMenu (menuRes: Int) {
        bottomMenuView?.menu?.clear()
        bottomMenuView?.visibility = View.VISIBLE
        bottomMenuView?.inflateMenu(menuRes)
        setOnNavigationItemSelectedListener(bottomMenuView)
    }

    private fun removeNavigationItemSelectedListener (bottomNavigationView: BottomNavigationView?) {
        bottomNavigationView?.setOnNavigationItemSelectedListener(null)
    }

    private fun setOnNavigationItemSelectedListener (bottomNavigationView: BottomNavigationView?) {
        bottomNavigationView?.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {

            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                 when (item.itemId) {

                     R.id.bottomMenu1 -> {
                         Toast.makeText(applicationContext, "Hello from menu 1", Toast.LENGTH_SHORT).show()
                     }

                     R.id.bottomMenu2 -> {
                         Toast.makeText(applicationContext, "Hello from menu 2", Toast.LENGTH_SHORT).show()
                     }
                 }
                return true
            }
        })
    }
}