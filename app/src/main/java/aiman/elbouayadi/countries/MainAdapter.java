package aiman.elbouayadi.countries;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    public MainAdapter(Activity context, List<MainData> dataList)
    {
        this.context=context;
        this.dataList=dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position) {

        final MainData data=dataList.get(position);

        database=RoomDB.getInstance(context);

        holder.textView.setText(data.getText());
        holder.textView2.setText(data.getCapital());
        holder.textView3.setText(data.getHabitants().toString());


        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainData d=dataList.get(holder.getAdapterPosition());
                final int sID=d.getID();
                String sText=d.getText();
                String cText=d.getCapital();
                Float nText=d.getHabitants();
                final Dialog dialog=new Dialog(context);

                //view
                dialog.setContentView(R.layout.dialog_update);
                int width= WindowManager.LayoutParams.MATCH_PARENT;
                int height=WindowManager.LayoutParams.WRAP_CONTENT;

                //layout
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                final EditText editText=dialog.findViewById(R.id.edit_text);
                final EditText editText2=dialog.findViewById(R.id.edit_text4);
                final EditText editText3 =dialog.findViewById(R.id.edit_text5);
                Button btUpdate=dialog.findViewById(R.id.bt_update);

                editText.setText(sText);
                editText2.setText(cText);
                editText3.setText(nText.toString());

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        String uText=editText.getText().toString().trim();
                        String uCText=editText2.getText().toString().trim();
                        Float uNText=Float.parseFloat(editText3.getText().toString().trim());

                        database.mainDao().update(sID, uText,uCText,uNText);

                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData d=dataList.get(holder.getAdapterPosition());
                database.mainDao().delete(d);

                int position=holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView, textView2, textView3;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view);
            textView2=itemView.findViewById(R.id.text_view_cap);
            textView3=itemView.findViewById(R.id.text_view_hab);


            btEdit=itemView.findViewById(R.id.bt_edit);
            btDelete=itemView.findViewById(R.id.bt_delete);
        }
    }
}
