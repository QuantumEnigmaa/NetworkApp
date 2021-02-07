package fr.isen.networkapp.pk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.networkapp.pk.FeedAdapter.Companion.POST_INFO
import fr.isen.networkapp.pk.R
import fr.isen.networkapp.pk.databinding.ActivityDetailPostBinding
import fr.isen.networkapp.pk.model.Post
import fr.isen.networkapp.pk.utils.FirebaseUtils

class DetailPost : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding
    private lateinit var post: Post


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        post = intent.getSerializableExtra(POST_INFO) as Post

        val tempLike = 1
        //val tempLike = if(post.likeBy!!.isNullOrEmpty()) 0 else post.likeBy!!.size

        Log.wtf("dataFlag", post.likeBy.toString())
        post.likeBy!!.add("Robert")

        binding.activityDetailPostNomPosteur.text = post.posterName
        binding.activityDetailPostDescriptionPost.text = post.content
        binding.activityDetailPostTitle.text = post.postTitle

        binding.activityDetailPostLikeCount.text = tempLike.toString()

        //binding.activityDetailPostLikeCount.text = post.likeNumber().toString()
        //binding.activityDetailPostLikeCount.text = post.likeBy!!.size.toString()
        //Log.wtf("dataFlag", "flag")
        //Log.wtf("dataFlag", post.likeBy?.size.toString())
/*
        binding.activityDetailPostLike.setOnClickListener {
            post.like("Jean-Test")
            FirebaseUtils.dbRef.child(post.postId!!).setValue(post)
        }*/
    }

    private fun likeNumber(): Int {
        //return if(post.likeBy!!.isNullOrEmpty()) 0 else post.likeBy!!.size
        return 0
    }

    private fun like(user: String): Boolean {
        if(post.likeBy!!.contains(user)) {
            post.likeBy!!.remove(user)
        }
        else {
            post.likeBy!!.add(user)
        }
        return true
    }

}