package com.example.recyclerexample;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> arrayList;
    private int lastPosition=-1;
    private int fontStyle=Typeface.NORMAL;
    public void setFontStyle(int fontStyle)
    {
        this.fontStyle=fontStyle;
        notifyDataSetChanged();
    }
    ContactAdapter(Context context,ArrayList<ContactModel> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.contact,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContactModel contactModel=arrayList.get(position);
        holder.imgContact.setImageResource(contactModel.img);
        holder.tvName.setText(contactModel.name);
        holder.tvNumber.setText(contactModel.number);

        holder.tvName.setTypeface(null, fontStyle);
        holder.tvNumber.setTypeface(null, fontStyle);

        if(position>lastPosition)
        {
            setAnimation(holder.itemView,position);
            lastPosition=position;
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_dialog);
                EditText etName=dialog.findViewById(R.id.etName);
                EditText etNumber=dialog.findViewById(R.id.etNumber);
                TextView textView=dialog.findViewById(R.id.textView);
                Button btnUpdate=dialog.findViewById(R.id.btnAdd);

                btnUpdate.setText("Update");
                textView.setText("Update Contact");
                etName.setText(arrayList.get(position).name);
                etNumber.setText(arrayList.get(position).number);

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name=etName.getText().toString();
                        String number=etNumber.getText().toString();
                        if(name.isEmpty() || number.isEmpty())
                        {
                            Toast.makeText(context, "Please fill all the details!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            arrayList.set(position,new ContactModel(R.drawable.person1,name,number,R.drawable.ic_baseline_call_24));
                            notifyItemChanged(position);
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete?")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrayList.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvNumber;
        ImageView imgContact,imgIconCall;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvNumber=itemView.findViewById(R.id.tvNumber);
            imgContact=itemView.findViewById(R.id.imgContact);
            linearLayout=itemView.findViewById(R.id.llRow);
            imgIconCall=itemView.findViewById(R.id.iconCall);

            imgIconCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phnNumber=arrayList.get(getAdapterPosition()).number;
                    Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phnNumber));
                    context.startActivity(intent);
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String phnNumber=arrayList.get(getAdapterPosition()).number;
//                    Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phnNumber));
//                    context.startActivity(intent);
                }
            });
        }
    }

    private void setAnimation(View viewAnim ,int pos)
    {
        Animation slideIn= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        viewAnim.setAnimation(slideIn);
    }
}
