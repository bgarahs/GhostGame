/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package com.example.first;

public final class R {
    public static final class attr {
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int buttonBarButtonStyle=0x7f010001;
        /** <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
         */
        public static final int buttonBarStyle=0x7f010000;
    }
    public static final class color {
        public static final int background_color=0x7f050000;
        public static final int black_overlay=0x7f050002;
        public static final int green=0x7f050001;
    }
    public static final class dimen {
        /**  Default screen margins, per the Android Design guidelines. 

         Customize dimensions originally defined in res/values/dimens.xml (such as
         screen margins) for sw720dp devices (e.g. 10" tablets) in landscape here.
    
         */
        public static final int activity_horizontal_margin=0x7f060000;
        public static final int activity_vertical_margin=0x7f060001;
    }
    public static final class drawable {
        public static final int backgorund_image=0x7f020000;
        public static final int background_image=0x7f020001;
        public static final int backgroundxml=0x7f020002;
        public static final int big_button=0x7f020003;
        public static final int ic_launcher=0x7f020004;
        public static final int ic_launcher_old=0x7f020005;
        public static final int menu_button=0x7f020006;
        public static final int menu_button1=0x7f020007;
        public static final int menu_button2=0x7f020008;
        public static final int menu_button_clicked=0x7f020009;
        public static final int small_button=0x7f02000a;
        public static final int small_button_clicked=0x7f02000b;
        public static final int small_button_clicked_old=0x7f02000c;
        public static final int small_button_old=0x7f02000d;
        public static final int the_word_background=0x7f02000e;
    }
    public static final class id {
        public static final int about_view=0x7f0a0001;
        public static final int action_settings=0x7f0a000d;
        public static final int exit_btn=0x7f0a0005;
        public static final int gra_layout=0x7f0a000a;
        public static final int last_symbol=0x7f0a0008;
        public static final int layout_label=0x7f0a0002;
        public static final int new_game_btn=0x7f0a0003;
        public static final int pressKey=0x7f0a0009;
        public static final int rules_btn=0x7f0a0004;
        public static final int rules_view=0x7f0a0000;
        public static final int someview=0x7f0a000c;
        public static final int text_message=0x7f0a000b;
        public static final int theWord=0x7f0a0007;
        public static final int winner_view=0x7f0a0006;
    }
    public static final class layout {
        public static final int activity_display_message=0x7f030000;
        public static final int activity_main=0x7f030001;
        public static final int activity_readin_file=0x7f030002;
    }
    public static final class menu {
        public static final int display_message=0x7f090000;
        public static final int main=0x7f090001;
        public static final int readin_file=0x7f090002;
        public static final int restart_or_exit_game=0x7f090003;
    }
    public static final class raw {
        public static final int game_lost_sound=0x7f040000;
        public static final int game_won_sound=0x7f040001;
    }
    public static final class string {
        public static final int action_settings=0x7f070008;
        public static final int app_name=0x7f070000;
        public static final int exit_game=0x7f070002;
        public static final int game_over=0x7f070004;
        public static final int game_rules=0x7f070003;
        public static final int hello_world=0x7f07000a;
        public static final int new_game=0x7f070001;
        public static final int pressKey=0x7f070005;
        public static final int title_activity_display_message=0x7f070007;
        public static final int title_activity_main=0x7f070006;
        public static final int title_activity_readin_file=0x7f070009;
    }
    public static final class style {
        /** 
        Base application theme, dependent on API level. This theme is replaced
        by AppBaseTheme from res/values-vXX/styles.xml on newer devices.

    

            Theme customizations available in newer API levels can go in
            res/values-vXX/styles.xml, while customizations related to
            backward-compatibility can go here.

        

        Base application theme for API 11+. This theme completely replaces
        AppBaseTheme from res/values/styles.xml on API 11+ devices.

    
 API 11 theme customizations can go here. 

        Base application theme for API 14+. This theme completely replaces
        AppBaseTheme from BOTH res/values/styles.xml and
        res/values-v11/styles.xml on API 14+ devices.
    
 API 14 theme customizations can go here. 
         */
        public static final int AppBaseTheme=0x7f080000;
        /**  Application theme. 
 All customizations that are NOT specific to a particular API-level can go here. 
         */
        public static final int AppTheme=0x7f080001;
        public static final int ButtonBar=0x7f080003;
        public static final int ButtonBarButton=0x7f080004;
        public static final int FullscreenActionBarStyle=0x7f080005;
        public static final int FullscreenTheme=0x7f080002;
    }
    public static final class styleable {
        /** 
         Declare custom theme attributes that allow changing which styles are
         used for button bars depending on the API level.
         ?android:attr/buttonBarStyle is new as of API 11 so this is
         necessary to support previous API levels.
    
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #ButtonBarContainerTheme_buttonBarButtonStyle com.example.first:buttonBarButtonStyle}</code></td><td></td></tr>
           <tr><td><code>{@link #ButtonBarContainerTheme_buttonBarStyle com.example.first:buttonBarStyle}</code></td><td></td></tr>
           </table>
           @see #ButtonBarContainerTheme_buttonBarButtonStyle
           @see #ButtonBarContainerTheme_buttonBarStyle
         */
        public static final int[] ButtonBarContainerTheme = {
            0x7f010000, 0x7f010001
        };
        /**
          <p>This symbol is the offset where the {@link com.example.first.R.attr#buttonBarButtonStyle}
          attribute's value can be found in the {@link #ButtonBarContainerTheme} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name android:buttonBarButtonStyle
        */
        public static final int ButtonBarContainerTheme_buttonBarButtonStyle = 1;
        /**
          <p>This symbol is the offset where the {@link com.example.first.R.attr#buttonBarStyle}
          attribute's value can be found in the {@link #ButtonBarContainerTheme} array.


          <p>Must be a reference to another resource, in the form "<code>@[+][<i>package</i>:]<i>type</i>:<i>name</i></code>"
or to a theme attribute in the form "<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>".
          @attr name android:buttonBarStyle
        */
        public static final int ButtonBarContainerTheme_buttonBarStyle = 0;
    };
}
