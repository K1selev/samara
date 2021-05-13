package ru.techpark.districtfood.MainScreen.CardsPreview;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.techpark.districtfood.R;

public class CardsViewHolder extends RecyclerView.ViewHolder {
    protected ImageView img;
    protected ImageView click_for_transition;
    protected boolean mIsLike;
    protected ImageButton mLikeBtn;
    protected ImageButton mMapBtn;
    protected TextView mMiddleReceipt;
    protected TextView mScore;
    protected TextView mName;

    public CardsViewHolder(@NonNull View itemView) {
        super(itemView);

        img = itemView.findViewById(R.id.photo_restaurant);
        click_for_transition = itemView.findViewById(R.id.click_for_transition);
        mName = itemView.findViewById(R.id.named_restaurant);
        mLikeBtn = itemView.findViewById(R.id.like);
        mMiddleReceipt = itemView.findViewById(R.id.description);
        mScore = itemView.findViewById(R.id.score_preview);
        mMapBtn = itemView.findViewById(R.id.route);
    }

    public void bind(){
        img.setImageResource(R.drawable.food);
        if (mIsLike){
            mLikeBtn.setImageResource(R.drawable.like_true);
        }else {
            mLikeBtn.setImageResource(R.drawable.like);
        }

    }

    public void IsLike(boolean mIsLike){
        this.mIsLike = mIsLike;
    }

}
