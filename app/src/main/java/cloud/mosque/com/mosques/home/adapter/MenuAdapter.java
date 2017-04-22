package cloud.mosque.com.mosques.home.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cloud.mosque.com.mosques.BR;
import cloud.mosque.com.mosques.R;
import cloud.mosque.com.mosques.databinding.RowMenuBinding;
import cloud.mosque.com.mosques.home.model.Data;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Bindingholder> {

    private List<Data> dataList;

    public MenuAdapter(){
        this.dataList = new ArrayList<>();
    }

    @Override
    public Bindingholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu, parent, false);
        return new Bindingholder(v);
    }

    @Override
    public void onBindViewHolder(Bindingholder holder, int position) {
        final Data datas = dataList.get(holder.getAdapterPosition());
        holder.getBinding().setVariable(BR.data, datas);
        holder.getBinding().setData(datas);
        Log.e("xxx",""+ getItemCount());
        Log.e("DATA ", datas.picture);
    }

    public void setMainMenu(List<Data> dataList) {
        this.dataList = dataList;
        Log.e("xxx",""+ dataList.get(1).name);
        notifyDataSetChanged();
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Glide.with(imageView.getContext())
                .load(v)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class Bindingholder extends RecyclerView.ViewHolder {
        RowMenuBinding binding;

        Bindingholder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        RowMenuBinding getBinding(){
            return binding;
        }
    }
}
