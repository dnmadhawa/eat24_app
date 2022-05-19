package com.company.eat24;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.company.eat24.FoodModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodAdapter extends FirebaseRecyclerAdapter <FoodModel,FoodAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FoodAdapter(@NonNull FirebaseRecyclerOptions<FoodModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull FoodModel model) {
        holder.name.setText(model.getName());
        holder.size.setText(model.getSize());
        holder.prize.setText(model.getPrize());

        Glide.with(holder.img.getContext())
                .load(model.getFurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

                View view1 =dialogPlus.getHolderView();
                EditText name = view1.findViewById(R.id.textNameUpdate);
                EditText size = view1.findViewById(R.id.textSizeUpdate);
                EditText prize = view1.findViewById(R.id.textPrizeUpdate);
                EditText url = view1.findViewById(R.id.textImgUrlUpdate);

                Button btnUpdate = view1.findViewById(R.id.btnupdate);

                name.setText(model.getName());
                size.setText(model.getSize());
                prize.setText(model.getPrize());
                url.setText(model.getFurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String , Object> map = new HashMap<>();
                        map.put("name" , name.getText().toString());
                        map.put("size" , size.getText().toString());
                        map.put("prize" , prize.getText().toString());
                        map.put("furl" , url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("food")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })

                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error Occurred while update", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });

                    }
                });

            }
        });


    //code to performe delete

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can not be undone");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("food")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name, size, prize;

        Button btnEdit, btnDelete;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            size = (TextView) itemView.findViewById(R.id.sizetext);
            prize = (TextView) itemView.findViewById(R.id.prizetext);

            btnEdit = (Button)itemView.findViewById(R.id.btnFoodEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnFoodRemove);
        }
    }
}



