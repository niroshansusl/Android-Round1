package com.hotelquickly.niroshan.hotelquickly.adapters;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hotelquickly.niroshan.hotelquickly.WebviewActivity;
import com.hotelquickly.niroshan.hotelquickly.beans.BeanObjectList;
import com.hotelquickly.niroshan.hotelquickly.R;
import com.hotelquickly.niroshan.hotelquickly.utils.UiUtils;

/**
 * com.hotelquickly.niroshan.hotelquickly
 * <p/>
 * Created by Niroshan Rathnayake.
 */

public class CardViewDataAdapter extends
		RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

	private List<BeanObjectList> objectList;
	private Activity context;

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final String ARG_PARAM3 = "param3";

	public CardViewDataAdapter(List<BeanObjectList> objectList, Activity context) {
		this.objectList = objectList;
		this.context = context;

	}

	// Create new views
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.cardview_row, null);

		// create ViewHolder

		ViewHolder viewHolder = new ViewHolder(itemLayoutView, context);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {

		UiUtils.setTextViewFontSizeBasedOnScreenDensity(context, viewHolder.tvPageTitle, UiUtils.FONT_MEDIUM_DENSITY_SIZE);
		viewHolder.tvPageTitle.setText(objectList.get(position).getPageTitle());
		viewHolder.objectItem = objectList.get(position);

	}

	// Return the size arraylist
	@Override
	public int getItemCount() {
		return objectList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView tvPageTitle;
		public BeanObjectList objectItem;
		Activity context;

		public ViewHolder(View itemLayoutView, final Activity context) {
			super(itemLayoutView);

			this.context = context;

			tvPageTitle = (TextView) itemLayoutView.findViewById(R.id.tvPageTitle);

			itemLayoutView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					context.startActivity(new Intent(context, WebviewActivity.class)
							.putExtra(ARG_PARAM1, objectItem.getUrl())
							.putExtra(ARG_PARAM2, objectItem.getCache())
							.putExtra(ARG_PARAM3, objectItem.getPageTitle()));

				}
			});

		}

	}

}
