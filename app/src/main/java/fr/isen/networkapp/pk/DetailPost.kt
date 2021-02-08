package fr.isen.networkapp.pk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.networkapp.pk.adapters.FeedAdapter.Companion.POST_INFO
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

        binding.activityPostDetailPosterName.text = post.posterName
        binding.activityPostDetailDescription.text = post.content
        binding.activityPostDetailTitle.text = post.postTitle
        binding.activityPostDetailLikeCount.text = "${post.likeBy!!.size-1}"

        Picasso.get().load(post.url).into(binding.activityPostDetailImage)

        //TODO better user
        binding.activityPostDetailLike.setOnClickListener {
            like("Jean-Test")
        }
    }

    private fun like(user: String) {
        if(post.likeBy!!.contains(user)) {
            post.likeBy!!.remove(user)
        }
        else {
            post.likeBy!!.add(user)
        }
        binding.activityPostDetailLikeCount.text = "${post.likeBy!!.size-1}"
        return
    }
    override fun onDestroy() {
        super.onDestroy()
        FirebaseUtils.dbRef.child(post.postId!!).setValue(post)
        finish()
    }

}