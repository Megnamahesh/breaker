package com.larvalabs.redditchat;

import controllers.Application;
import org.apache.commons.codec.binary.Base64;

import java.util.concurrent.TimeUnit;

public class Constants {

    public static final String REDDIT_BASE_URL = "https://www.reddit.com";
    public static final String BREAKER_BOT_USERNAME = "breakerbot";
    public static final String SYSTEM_USERNAME = "breakerbotsystem";
    public static final String USERNAME_REACTNATIVE_TESTUSER = "breakertestuser";
    public static final String USERNAME_GUEST = "guest";

    public static final int MAX_MSG_LENGTH = 500;

    public static final String CHATROOM_DEFAULT = "breakerapp";
    public static final String DEFAULT_PROFILE_URL = "/public/img/user-anon.png";
    public static final long MAX_PROFILE_IMAGE_SIZE_BYTES = 300 * 1000;
    public static final int NUM_PEOPLE_TO_OPEN_ROOM = 5;
    public static final int DEFAULT_MIN_KARMA_REQUIRED_TO_POST = 2;
    public static final long STREAM_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(2);

    public static final int ACTIVE_ROOMS_MAX = 5;

    public enum Flair {
        DEV_SAME_ROOM(0x1F451),
        TOP_STARS_ROOM(0x1F31F),
        TOP_STARS_GLOBAL(0x1F3C6);

        int emojiCodePoint;

        private Flair(int emojiCodePoint) {
            this.emojiCodePoint = emojiCodePoint;
        }

        public String getAsString() {
            return new String(Character.toChars(emojiCodePoint));
        }
    }

    public static final String HEADER_X_REQUEST_ID = "x-request-id";
    public static final String HEADER_X_REQUEST_START = "x-request-start";

    public static final int NUMBER_DAYS_INACTIVE_STOP_NOTIFICATIONS = 21;

    public static final int CODE_LENGTH = 6;

    public static final int THRESHOLD_MESSAGE_FLAG = 5; // Number of flags before a message is hidden
    public static final int THRESHOLD_USER_FLAG = 15;   // Number of flags for a user before they're blocked
    public static final int THRESHOLD_ROOM_USERS_FOR_TOP_LIST = 14;   // Number of users required in a room for it to appear on top list

    public static final String URL_CLOUDFRONT_THUMB_PREFIX = "http://d1j13ers05ggmx.cloudfront.net/thumb?url=";
    public static final String URL_CLOUDINARY_FULLSIZE_PREFIX = "http://res.cloudinary.com/appchat/image/fetch/";
    public static final String URL_S3_BUCKET_SCREENSHOT_FULLSIZE = "http://breaker-screenshots.s3.amazonaws.com/";
    public static final String S3BUCKET_PROFILEPICS = "breaker-userprofile";
    public static final String URL_S3_BUCKET_PROFILE_FULLSIZE = "https://" + S3BUCKET_PROFILEPICS + ".s3.amazonaws.com/";
    public static final String URL_S3_BUCKET_WALLPAPER_FULLSIZE = "http://breaker-wallpaper.s3.amazonaws.com/";
    public static final String URL_WALLPAPER_THUMB = URL_CLOUDFRONT_THUMB_PREFIX;

    public static final long TIMEOUT_TRANSLATE_SECONDS = 1;

    public static final String APPCHAT_PACKAGE_NAME = "com.larvalabs.myapps";

    public static final int PING_FREQUENCY_SEC = 30;
    public static final int PRESENCE_TIMEOUT_SEC = PING_FREQUENCY_SEC;
    public static final int RESEND_MEMBERLIST_SEC = PING_FREQUENCY_SEC * 3;

    public static final int USER_FLAG_THRESHOLD = 15;

    public static final int DEFAULT_MESSAGE_LIMIT = 20;

    public static final String REDDIT_AUTH_STR = Base64.encodeBase64String((Application.REDDIT_CLIENTID + ":" + Application.REDDIT_SECRET).getBytes());
}
