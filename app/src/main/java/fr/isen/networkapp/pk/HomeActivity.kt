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
        //TODO le button passe en dessous du recyclerview avec
        // le parametre android:layout_height="match_parent"
        // et devient useless
        binding.activityHomeCreatePost.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }

        //loadFeed()

        postLst = mutableListOf()
        FirebaseUtils.dbRef.addListenerForSingleValueEvent(
                 object : ValueEventListener{
                     override fun onCancelled(error: DatabaseError) {
                         TODO("Not yet implemented")
                     }

                     override fun onDataChange(snapshot: DataSnapshot) {
                         if(snapshot!!.exists()){
                             for(h in snapshot.children){
                                 //TODO cool print mais a virer
                                 Log.wtf("dataFlag", h.toString())
                                 val post = h.getValue(Post::class.java)
                                 postLst.add(post!!)

                             }
                             val adapter = FeedAdapter(postLst)
                             binding.activityHomeRecycler.layoutManager = LinearLayoutManager(applicationContext)
                             binding.activityHomeRecycler.adapter = adapter
                         }
                     }
                 })
    }
/*
    private fun loadFeed() {
        var entries = listOf<String>("salade", "boeuf", "glace")
        val adapter = FeedAdapter(entries)
        binding.activityHomeRecycler.layoutManager = LinearLayoutManager(this)
        binding.activityHomeRecycler.adapter = adapter
    }*/
}