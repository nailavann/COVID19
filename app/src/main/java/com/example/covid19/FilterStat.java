package com.example.covid19;

import java.util.ArrayList;


public class FilterStat extends android.widget.Filter {
    private UlkeAdapter ulkeAdapter;
    private ArrayList<Ulke> filterList;

    public FilterStat(UlkeAdapter ulkeAdapter, ArrayList<Ulke> filterList) {
        this.ulkeAdapter = ulkeAdapter;
        this.filterList = filterList;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint!= null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<Ulke> filteredList = new ArrayList<>();
            for(int i =0; i<filterList.size(); i++){
                if (filterList.get(i).getUlkeAdi().toUpperCase().contains(constraint)){
                    filteredList.add(filterList.get(i));
                }

            }
            results.count = filteredList.size();
            results.values = filteredList;
        }
        else{
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        ulkeAdapter.ulkeArrayList = (ArrayList<Ulke>) results.values;
        ulkeAdapter.notifyDataSetChanged();

    }
}
