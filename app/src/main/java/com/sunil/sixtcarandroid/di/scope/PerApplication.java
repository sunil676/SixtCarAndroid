package com.sunil.sixtcarandroid.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by sunil on 31-08-2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}