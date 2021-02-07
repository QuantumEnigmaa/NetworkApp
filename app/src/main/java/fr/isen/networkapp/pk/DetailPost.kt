package fr.isen.networkapp.pk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.networkapp.pk.FeedAdapter.Companion.POST_INFO
import fr.isen.networkapp.pk.R
import fr.isen.networkapp.pk.databinding.ActivityDetailPostBinding
import fr.isen.networkapp.pk.model.Post

class DetailPost : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getSerializableExtra(POST_INFO) as Post

        binding.activityDetailPostNomPosteur.text = post.posterName
        binding.activityDetailPostDescriptionPost.text = post.content
        binding.activityDetailPostTitle.text = post.postTitle
    }

}