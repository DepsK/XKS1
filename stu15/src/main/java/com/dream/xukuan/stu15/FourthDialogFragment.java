package com.dream.xukuan.stu15;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @author XK
 * @date 2018/3/9.
 */
public class FourthDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        Dialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("请确认")
                .setMessage("你选中了"+position)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "你确定了！", Toast.LENGTH_SHORT).show();
                    }
                }).create();

        return dialog;
    }
}