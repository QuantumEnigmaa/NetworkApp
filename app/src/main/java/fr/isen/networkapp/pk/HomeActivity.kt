package fr.isen.networkapp.pk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.networkapp.pk.databinding.ActivityHomeBinding
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.networkapp.pk.FeedAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create poste button handler
        binding.activityHomeCreatePost.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }

        loadFeed()
    }

    private fun loadFeed() {
        var entries = listOf<String>("salade", "boeuf", "glace")
        val adapter = FeedAdapter(entries)
        binding.activityHomeRecycler.layoutManager = LinearLayoutManager(this)
        binding.activityHomeRecycler.adapter = adapter
    }
}