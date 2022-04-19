package kg.junesqo.postapp.ui.posts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.junesqo.postapp.OnItemClickListener;
import kg.junesqo.postapp.R;
import kg.junesqo.postapp.data.models.Post;
import kg.junesqo.postapp.databinding.ItemPostBinding;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private OnItemClickListener onItemClickListener;

    private List<Post> posts = new ArrayList<>();

    public PostAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(posts.get(position));
        Bundle bundle = new Bundle();
        bundle.putString("postId", String.valueOf(posts.get(position).getId()));

        holder.itemView.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_postsFragment_to_formFragment, bundle)
        );

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemClickListener.onItemLongClick(String.valueOf(posts.get(position).getId()));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    protected class PostViewHolder extends RecyclerView.ViewHolder {
        private ItemPostBinding binding;

        public PostViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Post post) {
            binding.tvUserId.setText(String.valueOf(post.getUserId()));
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());
        }
    }

}
