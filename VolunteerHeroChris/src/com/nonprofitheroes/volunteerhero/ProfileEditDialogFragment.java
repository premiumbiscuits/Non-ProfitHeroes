package com.nonprofitheroes.volunteerhero;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ProfileEditDialogFragment extends DialogFragment {
    
    public interface ProfileEditDialogListener {
        public void onDialogPositiveClick(ProfileEditDialogFragment dialog);
        public void onDialogNegativeClick(ProfileEditDialogFragment dialog);
    }
    
    ProfileEditDialogListener listener;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (ProfileEditDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement ProfiledEditDialogListener");
        }
    }
    
    private String value = null;
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View inflatedView = inflater.inflate(R.layout.edit_dialog, null);
        
        //Create dialog and bind on click listeners
        builder.setTitle(R.string.profile_edit)
               .setView(inflatedView)
               .setPositiveButton(R.string.change, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       okayClick(inflatedView);
                       listener.onDialogPositiveClick(ProfileEditDialogFragment.this);
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       value = null;
                       listener.onDialogNegativeClick(ProfileEditDialogFragment.this);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    
    private void okayClick(View view){
        EditText editText = (EditText)view.findViewById(R.id.edit_field);
        this.value = editText.getText().toString();
    }
    
    public String getValue(){
        return this.value;
    }
}
