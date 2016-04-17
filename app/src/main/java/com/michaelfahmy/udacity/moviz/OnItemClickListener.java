package com.michaelfahmy.udacity.moviz;

import android.view.View;

/**
 * Created by michael on 4/14/16.
 */

/** Implementation of onItemClickListener Interface */
public interface OnItemClickListener {
    /**
     * @param view     the view clicked
     * @param position the position of item clicked
     */
    void onItemClick(View view, int position);
}
