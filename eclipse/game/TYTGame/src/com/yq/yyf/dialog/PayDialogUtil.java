package com.yq.yyf.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.game.jump.R;

public class PayDialogUtil
{
  private static volatile PayDialogUtil mInstance;
  private Button btnCancel;
  private Button btnSure;
  private Context context;
  private AlertDialog dlg;
  private DialogButtonListener listener;

  private void createDialog()
  {
    View localView = LayoutInflater.from(this.context).inflate(R.layout.customdialog, null);
    this.dlg = new AlertDialog.Builder(this.context).create();
    this.dlg.requestWindowFeature(1);
    this.dlg.show();
    this.dlg.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 84);
        return true;
      }
    });
    Window localWindow = this.dlg.getWindow();
    localWindow.setFlags(32, 32);
    localWindow.setBackgroundDrawableResource(17170445);
    localWindow.getDecorView().setPadding(0, 0, 0, 0);
    localWindow.setGravity(17);
    localWindow.setContentView(localView);
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.width = -1;
    localLayoutParams.height = -1;
    localLayoutParams.alpha = 0.8F;
    localLayoutParams.dimAmount = 0.8F;
    localWindow.setAttributes(localLayoutParams);
    this.btnSure = ((Button)localView.findViewById(R.id.confirm_btn));
    this.btnCancel = ((Button)localView.findViewById(R.id.cancel_btn));
  }

  public static PayDialogUtil getInstance()
  {
    if (mInstance == null);
    try
    {
      if (mInstance == null)
        mInstance = new PayDialogUtil();
      return mInstance;
    }
    finally
    {
    }
  }

  private void setValue()
  {
    this.btnCancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PayDialogUtil.this.listener.cancel();
        PayDialogUtil.this.dlg.dismiss();
      }
    });
    this.btnSure.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PayDialogUtil.this.listener.sure();
        PayDialogUtil.this.dlg.dismiss();
      }
    });
  }

  public void showPayDialog(Context paramContext, DialogButtonListener paramDialogButtonListener)
  {
    this.context = paramContext;
    this.listener = paramDialogButtonListener;
    createDialog();
    setValue();
  }
}