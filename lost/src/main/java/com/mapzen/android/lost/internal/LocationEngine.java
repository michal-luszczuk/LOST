package com.mapzen.android.lost.internal;

import com.mapzen.android.lost.api.LocationRequest;

import android.content.Context;
import android.location.Location;

/**
 * Base class for {@link com.mapzen.android.lost.internal.FusionEngine} and
 * {@link com.mapzen.android.lost.internal.MockEngine} classes.
 */
public abstract class LocationEngine {
    private final Context context;
    private final Callback callback;
    private LocationRequest request;

    public LocationEngine(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
    }

    /**
     * Return most best recent location available.
     */
    public abstract Location getLastLocation();

    /**
     * Enables the engine on receiving a valid location request. Disables the engine on receiving a
     * {@code null} request.
     *
     * @param request Valid location request to enable or {@code null} to disable.
     */
    public void setRequest(LocationRequest request) {
        this.request = request;
        if (request != null) {
            enable();
        } else {
            disable();
        }
    }

    /**
     * Subclass should perform all operations required to enable the engine. (ex. Register for
     * location updates.)
     */
    protected abstract void enable();

    /**
     * Subclass should perform all operations required to disable the engine. (ex. Remove location
     * updates.)
     */
    protected abstract void disable();

    protected Context getContext() {
        return context;
    }

    protected Callback getCallback() {
        return callback;
    }

    protected LocationRequest getRequest() {
        return request;
    }

    public interface Callback {
        public void reportLocation(Location location);
    }
}
