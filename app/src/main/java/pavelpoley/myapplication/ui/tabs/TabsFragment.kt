package pavelpoley.myapplication.ui.tabs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import pavelpoley.myapplication.MainActivity
import pavelpoley.myapplication.R

class TabsFragment : Fragment(R.layout.fragment_tab) {

    private data class FragmentsWithBottomMenu(val id: Int, val menu: Int)

    private val FRAGMENTS_WITH_BOTTOM_BAR = listOf(
        FragmentsWithBottomMenu(R.id.fragmentHome, R.menu.bottom_menu_1),
        FragmentsWithBottomMenu(R.id.fragmentTab2, R.menu.bottom_menu_2)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =  childFragmentManager.findFragmentById(R.id.nav_host_tabs) as NavHostFragment
        val navController = navHostFragment.navController

        addOnDestinationChangedListener(navController)
        addOnTabSelectedListener(navController)
    }

    private fun addOnDestinationChangedListener (navController: NavController) {
        navController.addOnDestinationChangedListener {controller, destination, args ->
            Log.d("Args:", destination.toString())

            val result = FRAGMENTS_WITH_BOTTOM_BAR.find { it.id == destination.id }

             if (result != null) {
                 showBottomMenu(result.menu)
             } else {
                 hideBottomMenu()
             }
        }
    }

    private fun addOnTabSelectedListener (navController: NavController) {
        view?.findViewById<TabLayout>(R.id.tabLayout)?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener  {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        navController.navigate(R.id.fragmentHome)
                    }

                    1 -> {
                        navController.navigate(R.id.fragmentTab1)
                    }

                    2 -> {
                        navController.navigate(R.id.fragmentTab2)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun showBottomMenu (menuRes: Int) {
        activity?.let {
            (it as MainActivity).showBottomMenu(menuRes)
        }
    }

    private fun hideBottomMenu () {
        activity?.let {
            (it as MainActivity).hideBottomMenu()
        }
    }
}