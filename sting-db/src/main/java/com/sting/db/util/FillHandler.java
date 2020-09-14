package com.sting.db.util;

import java.util.HashMap;

public interface FillHandler {
    void insertFill(HashMap<String, Object> fill);

    void updateFill(HashMap<String, Object> fill);

}
