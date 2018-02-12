package com.ramadan_apps.mvvm_demo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ramadan_apps.mvvm_demo.model.People;

/**
 * Created by Mahmoud Ramadan on 2/11/18.
 */

public class ItemPeopleViewModel extends BaseObservable {
    private People people;
    private Context context;

    public ItemPeopleViewModel(People people, Context context) {
        this.people = people;
        this.context = context;
    }

    public String getFullName() {
        people.fullName =
                people.name.title + "." + people.name.firts + " " + people.name.last;
        return people.fullName;
    }

    public String getCell() {
        return people.cell;
    }

    public String getMail() {
        return people.mail;
    }

    public String getPictureProfile() {
        return people.picture.medium;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
       Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
       // context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), people));
    }

    public void setPeople(People people) {
        this.people = people;
        notifyChange();
    }
}
