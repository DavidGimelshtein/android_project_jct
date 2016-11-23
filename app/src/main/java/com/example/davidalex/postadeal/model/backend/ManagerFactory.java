package com.example.davidalex.postadeal.model.backend;

import com.example.davidalex.postadeal.model.datasource.ListDsManager;
import com.example.davidalex.postadeal.model.datasource.RemoteDsManager;

/**
 * Created by david on 22.11.2016.
 */

public class ManagerFactory {

    public static IDSManager getDsManager(String name) {

        IDSManager dsManager;
        switch (name) {
            case "ListDsManager":
                dsManager = new ListDsManager();
                break;
            case "RemoteDsManager":
                dsManager = new RemoteDsManager();
                break;
            default:
                dsManager = null;
        }
        return dsManager;
    }
}
