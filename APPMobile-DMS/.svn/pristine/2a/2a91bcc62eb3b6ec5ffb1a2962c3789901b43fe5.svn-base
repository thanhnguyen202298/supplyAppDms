package com.sskey.dms.dialog;

import android.support.v4.app.FragmentActivity;

import com.example.sskey.dms.R;
import com.stepstone.apprating.AppRatingDialog;

import java.util.Arrays;

public class DialogDanhGia {
    //Tiến thêm
    //show dialog Rating bar
    public static void showRatingDialog(FragmentActivity activity) {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Đánh giá")
                .setNegativeButtonText("Huỷ")
                .setNoteDescriptions(Arrays.asList("Rất tệ, Tệ, Bình thường, Tốt, Rất tốt"))
                .setDefaultRating(1)
                .setTitle("Giao hàng hoàn tất")
                .setDescription("Vui lòng đưa ra đánh giá của bạn")
                .setTitleTextColor(R.color.orange)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Vui lòng viết bình luận của bạn")
                .setHintTextColor(R.color.black)
                .setCommentTextColor(R.color.black)
                .setCommentBackgroundColor(R.color.white)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(activity)
                .show();
    }
}
