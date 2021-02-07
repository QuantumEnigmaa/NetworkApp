package fr.isen.networkapp.pk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.networkapp.pk.databinding.ActivityHomeBinding
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import fr.isen.networkapp.pk.FeedAdapter
import fr.isen.networkapp.pk.model.Post
import fr.isen.networkapp.pk.utils.FirebaseUtils

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var postLst: MutableList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create post button handler
        //TODO le button passe en dessous (j'imagine) du recyclerview avec
        // + le truc du refresh a pas oublier
        //le parametre android:layout_height="match_parent"
        //et devient useless
        binding.activityHomeCreatePost.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }

        postLst = mutableListOf()
        FirebaseUtils.dbRef.addListenerForSingleValueEvent(
                 object : ValueEventListener{
                     override fun onCancelled(error: DatabaseError) {
                         TODO("Not yet implemented")
                     }

                     override fun onDataChange(snapshot: DataSnapshot) {
                         if(snapshot!!.exists()){
                             for(h in snapshot.children){
                                 //TODO reecrire en plus opti
                                 // +moyen d'eviter "E/RecyclerView: No adapter attached; skipping layout"?
                                 Log.wtf("dataFlag", h.toString())
                                 val post = h.getValue(Post::class.java)
                                 postLst.add(post!!)

                             }
                             //TODO de tout rentrer en une ligne?
                             //TODO reverse lst pr avoir les post dans le bon ordre
                             val adapter = FeedAdapter(postLst)
                             binding.activityHomeRecycler.layoutManager = LinearLayoutManager(applicationContext)
                             binding.activityHomeRecycler.adapter = adapter
                         }
                     }
                 })
    }
}