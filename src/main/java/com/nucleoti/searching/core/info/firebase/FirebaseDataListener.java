package com.nucleoti.searching.core.info.firebase;

import com.google.firebase.database.DataSnapshot;

public interface FirebaseDataListener {

    void onDataValue(DataSnapshot dataSnapshot);
}