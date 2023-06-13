package glory.video.player.models;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import glory.video.player.R;

import java.util.Objects;

public class VolumeDialog extends AppCompatDialogFragment {

    private ImageView Corees;
    private TextView volumeNo;
    private SeekBar seekBar12;
    AudioManager audioManager;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.vol_dialog_item, null);
        builder.setView(view);
        requireActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Corees = view.findViewById(R.id.vol_close);
        volumeNo = view.findViewById(R.id.vol_number);
        seekBar12 = view.findViewById(R.id.vol_seekbar);

        audioManager = (AudioManager) requireContext().getSystemService(Context.AUDIO_SERVICE);
        seekBar12.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        seekBar12.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        seekBar12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                int mediaVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                double volPer = Math.ceil((((double) mediaVolume / (double) maxVol) * (double) 100));
                volumeNo.setText("" + volPer);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Corees.setOnClickListener(v -> dismiss());
        return builder.create();
    }
}
