package com.kevincodes.teststuff

import android.content.Context
import android.widget.Toast

object StuffUtil {
    fun createToast(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    fun createDummyList(): List<Dummy> {
        val mDummyList = arrayListOf<Dummy>()
        mDummyList.add(
            Dummy(
                "Whole Heart",
                "Hillsong United",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Fhillsong_united_00.jpg?alt=media&token=1efea7b9-4210-4328-a652-c83cb61ff8e4"
            )
        )
        mDummyList.add(
            Dummy(
                "Less like me",
                "Zach Williams",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Fzach-with-guitar-1024x683.jpg?alt=media&token=fec73d4c-9134-4057-9c22-e1c23e2a01a7"
            )
        )
        mDummyList.add(
            Dummy(
                "Different",
                "Micah Tyler",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Fmicah_tyler_00.jpg?alt=media&token=4fa269f8-fba9-4f06-baa9-bfc2c08cf917"
            )
        )
        mDummyList.add(
            Dummy(
                "Flawless",
                "MercyMe",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Fmercyme_img_00_2021.jpg?alt=media&token=1edcf8ce-07ef-4c95-83eb-ed49bbfcc8cc"
            )
        )
        mDummyList.add(
            Dummy(
                "Awake my soul (Acoustic)",
                "Hillsong Worship",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Fhillsong_worship_image_00.jpg?alt=media&token=b318527e-70b0-44d5-bae4-6bd53d68331d"
            )
        )
        mDummyList.add(
            Dummy(
                "It's gonna be okay",
                "Tasha Layton",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Ftashalayton_00.jpg?alt=media&token=9a909d9c-55c0-488b-9a7c-74baa7e9bc00"
            )
        )
        mDummyList.add(
            Dummy(
                "Your nature",
                "Kari Jobe",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Fkarijobe_00.jpg?alt=media&token=fafc212e-c786-44f8-80e6-b4cd90c4c206"
            )
        )
        mDummyList.add(
            Dummy(
                "Take heart (again)",
                "Hillsong Worship",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Fhillsong_worship_take_heart_cover.jpg?alt=media&token=8f612852-7c01-413c-83f6-5de4d487d62d"
            )
        )
        mDummyList.add(
            Dummy(
                "Rescue Story",
                "Zach Williams",
                "https://firebasestorage.googleapis.com/v0/b/audiofy-dabd3.appspot.com/o/artist_images%2Fzach_williams_rescue_story_00.jpg?alt=media&token=af81a6e0-6047-47ee-84e0-8d590ef2b1b3"
            )
        )
        return mDummyList
    }
}