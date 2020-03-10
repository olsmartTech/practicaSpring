package com.nucleoti.searching.core.info.firebase;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.firebase.database.*;
import com.nucleoti.searching.core.info.firebase.core.FirebaseModel;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FirebaseService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    //private final DatabaseReference databaseReference;

    //private final GoogleCredential scoped;

    @Autowired
    DatabaseReference databaseReference;

    @Autowired
    GoogleCredential scoped;
    /*
    public FirebaseService(DatabaseReference databaseReference, GoogleCredential scoped) {
        this.databaseReference = databaseReference;
        this.scoped = scoped;
    }*/

    public void save(String path, FirebaseModel model) {
        logger.info(">> New object: {} in path: {}", model.getId(), path);
        DatabaseReference childReference = databaseReference.child(path);
        childReference.child(model.getId()).setValue(model.getValue());
    }

    public void save(String path, List<FirebaseModel> models) {
        logger.info(">> New objects: {} in path: {}", models.stream().map(FirebaseModel::getId).collect(Collectors.toList()), path);
        DatabaseReference childReference = databaseReference.child(path);
        models.forEach(model -> {
            logger.info(model.toString());
            childReference.child(model.getId()).setValue(model.getValue());
        });
    }

    public void update(String path, FirebaseModel... models) {
        logger.info(">> Path: {} updated with: {}", path, Arrays.asList(models));
        DatabaseReference childReference = databaseReference.child(path);
        Map<String, Object> objectToUpdated = new HashMap<>();
        for (FirebaseModel model : models) {
            objectToUpdated.put(model.getId(), model.getValue());
        }
        childReference.updateChildren(objectToUpdated);
    }

    public void delete(String path, String id) {
        logger.info(">> Deleted id: {} from path: {}", id, path);
        DatabaseReference childReference = databaseReference.child(path);
        Map<String, Object> objectToUpdated = new HashMap<>();
        objectToUpdated.put(id, null);
        childReference.updateChildren(objectToUpdated);
    }

    public void getByKey(String path, String key, FirebaseDataListener listener) throws Exception {
        logger.info(">> Find key {} in path: {}", key, path);
        DatabaseReference childReference = databaseReference.child(path);
        childReference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.onDataValue(dataSnapshot);
                } else {
                    throw new DatabaseException(">> No results found for the search");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    public String getToken() throws IOException {

        scoped.refreshToken();

        return scoped.getAccessToken();

    }
}