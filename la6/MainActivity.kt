<androidx.drawerlayout.widget.DrawerLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:id="@+id/drawer_layout"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity">

<LinearLayout
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical">

<include layout="@layout/toolbar"/>

<!-- Contenedor de fragmentos -->
<FrameLayout
android:id="@+id/fragment_container"
android:layout_width="match_parent"
android:layout_height="0dp"
android:layout_weight="1"/>

</LinearLayout>

<!-- Menú del drawer -->
<com.google.android.material.navigation.NavigationView
android:id="@+id/nav_view"
android:layout_width="wrap_content"
android:layout_height="match_parent"
android:layout_gravity="start"
app:menu="@menu/drawer_menu"/>

</androidx.drawerlayout.widget.DrawerLayout>
class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        // Toolbar setup
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Navigation Listener
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_popular_recipes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_saved_recipes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SavedRecipesFragment())
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_shopping_list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ShoppingListFragment())
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }

        // Load the HomeFragment by default
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
class SavedRecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_recipes, container, false)
    }
}
class ShoppingListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }
}
class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val recipeTitle = intent.getStringExtra("RECIPE_TITLE")
        val recipeDescription = intent.getStringExtra("RECIPE_DESC")

        val titleTextView: TextView = findViewById(R.id.recipe_title)
        val descTextView: TextView = findViewById(R.id.recipe_description)

        titleTextView.text = recipeTitle
        descTextView.text = recipeDescription
    }
}
<LinearLayout
xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"
android:padding="16dp">

<TextView
android:id="@+id/recipe_title"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="24sp"
android:textStyle="bold"/>

<TextView
android:id="@+id/recipe_description"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="16sp"/>

</LinearLayout>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
<item
android:id="@+id/nav_popular_recipes"
android:title="Popular Recipes" />
<item
android:id="@+id/nav_saved_recipes"
android:title="Saved Recipes" />
<item
android:id="@+id/nav_shopping_list"
android:title="Shopping List" />
</menu>
<resources>
<string name="prime_rib_roast">Prime Rib Roast</string>
<string name="prime_rib_desc">A classic and tender cut of meat perfect for family gatherings...</string>
<!-- Agregar más recetas -->
</resources>
