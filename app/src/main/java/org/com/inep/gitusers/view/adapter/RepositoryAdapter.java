package org.com.inep.gitusers.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.com.inep.gitusers.R;
import org.com.inep.gitusers.model.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shiki on 28/12/2017.
 */

public class RepositoryAdapter extends BaseAdapter {

    private Context context;

    private List<Repository> repositories;

    public RepositoryAdapter(Context context, List<Repository> repositories) {
        this.context = context;
        this.repositories = repositories;
    }

    /** *********************** HOLDER *********************** */
    static class MyViewHolder {
        @BindView(R.id.elementItemRepositoryImage) ImageView imageViewRepository;
        @BindView(R.id.elementItemRepositoryImageLock) ImageView imageViewLock;
        @BindView(R.id.elementItemRepositoryImageUnlock) ImageView imageViewUnlock;
        @BindView(R.id.elementItemRepositoryTextViewName) TextView textViewName;
        @BindView(R.id.elementItemRepositoryTextViewLanguage) TextView textViewLanguage;
        @BindView(R.id.elementItemRepositorytextViewDescription) TextView textViewDescription;

        MyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /** ********************* OVERRRIDE ********************* */
    @Override
    public int getCount() {
        return repositories.size();
    }

    @Override
    public Object getItem(int position) {
        return repositories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        RepositoryAdapter.MyViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listview_item_repository, parent, false);

            holder = new RepositoryAdapter.MyViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (RepositoryAdapter.MyViewHolder) view.getTag();
        }

        Repository repository = repositories.get(position);

        holder.textViewName.setText(repository.getName());
        holder.textViewLanguage.setText( (!repository.getLanguage().isEmpty() ? repository.getLanguage() : context.getString(R.string.ui_label_no_value) ) );
        holder.textViewDescription.setText( (!repository.getDescription().isEmpty() ? repository.getDescription() : context.getString(R.string.ui_label_no_value) ) );

        if (repository.isPrivate()) {
            holder.imageViewLock.setVisibility(View.VISIBLE);
            holder.imageViewUnlock.setVisibility(View.GONE);
        } else {
            holder.imageViewLock.setVisibility(View.GONE);
            holder.imageViewUnlock.setVisibility(View.VISIBLE);
        }

        Picasso.with(context).load(R.drawable.repository).fit()
                .into(holder.imageViewRepository);

        return view;
    }

}
