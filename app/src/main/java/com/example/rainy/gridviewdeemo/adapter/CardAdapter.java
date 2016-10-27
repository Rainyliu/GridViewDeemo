package com.example.rainy.gridviewdeemo.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rainy.gridviewdeemo.R;
import com.example.rainy.gridviewdeemo.beans.Card;
import com.example.rainy.gridviewdeemo.holder.CommonHolder;

/**
 * Author: Rainy <br>
 * Description: GridViewDeemo <br>
 * Since: 2016/10/24 0024 上午 10:37 <br>
 */

public class CardAdapter extends BaseRecyclerAdapter<Card> {
    @Override
    public CommonHolder<Card> setViewHolder(ViewGroup parent) {
        return new CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<Card> {

//        @Bind(R.id.tv_title)
//        TextView tv_title;
//
//        @Bind(R.id.tv_subtitle)
//        TextView tv_subtitle;
//
//        @Bind(R.id.iv_cover)
//        ImageView iv_cover;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.list_item);
        }

        @Override
        public void bindData(Card card) {
//            tv_title.setText(card.title);
//            tv_subtitle.setText(card.info);
//            iv_cover.setImageResource(card.imageSrc);
        }
    }
}

