package br.com.bhr.hammerboard.core;

/**
 * Created by ben on 28/11/2017.
 */

public interface ActionResult<TError, TSuccess> {
    void onSuccess(TSuccess result);
    void onError(TError error);
}
