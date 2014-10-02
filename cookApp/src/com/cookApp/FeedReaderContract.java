package com.example.moletracker;

import android.app.Activity;
import android.os.Bundle;
import android.database.*;
import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Moles";
        public static final String COLUMN_SKIN_AREA = "Skin Area";
        public static final String COLUMN_MOLE_LOCATION = "Mole Location";
    }
}
