package com.example.dmrcconnect;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<HelpSearchResult> {

    private Context context;
    private int resourceId;
    private List<HelpSearchResult> items;
    private List<HelpSearchResult> tempItems;
    private List<HelpSearchResult> suggestions;

    public SearchAdapter(@NonNull Context context, int resourceId, ArrayList<HelpSearchResult> items){
        super(context, resourceId, items);
        this.items = items;
        this.context = context;
        this.resourceId = resourceId;
        tempItems = new ArrayList<>(items);
        suggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(resourceId, parent, false);
                HelpSearchResult helpSearchResult = getItem(position);
                TextView name = (TextView) view.findViewById(R.id.help_result_name);
                ImageView imageView = (ImageView) view.findViewById(R.id.category_imageview);
                imageView.setImageResource(helpSearchResult.getImage());
                name.setText(helpSearchResult.getName());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    @Nullable
    @Override
    public HelpSearchResult getItem(int position){
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return helpResultFilter;
    }

    private Filter helpResultFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            HelpSearchResult fruit = (HelpSearchResult) resultValue;
            return fruit.getName();
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (HelpSearchResult fruit: tempItems) {
                    if (fruit.getName().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(fruit);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<HelpSearchResult> tempValues = (ArrayList<HelpSearchResult>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (HelpSearchResult fruitObj : tempValues) {
                    add(fruitObj);
                    notifyDataSetChanged();
                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };


    }
