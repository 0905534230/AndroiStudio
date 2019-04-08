package com.example.contact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<Contact> mContacts;
    private Context mContext;
    private LayoutInflater mInFlater;

    public ContactAdapter(ArrayList<Contact> mContacts , Context mContext){
        this.mContacts = mContacts;
        this.mContext  = mContext;
        this.mInFlater = LayoutInflater.from(mContext);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = mInFlater.inflate(R.layout.item_contact , viewGroup , false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( ContactAdapter.ContactViewHolder contactViewHolder, int i) {
        Contact contact = mContacts.get(i);
//        contactViewHolder.tvTitle.setText(contact.getTitle());
        contactViewHolder.tvFullname.setText(contact.getFullname());
//        contactViewHolder.tvCompany.setText(contact.getCompany());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvFullname;
        TextView tvCompany;


        public ContactViewHolder (View itemView){
            super(itemView);
            itemView.setClickable(true);
//            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvFullname = (TextView)itemView.findViewById(R.id.tv_fullname);
//            tvCompany = (TextView)itemView.findViewById(R.id.tv_company);

        }
    }
}
