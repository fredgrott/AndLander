package com.mobilebytes.andlander;

import roboguice.config.AbstractAndroidModule;
import roboguice.inject.SharedPreferencesName;

public class AppModule extends AbstractAndroidModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub



		// BUG need a better way to set default preferences context
        bindConstant().annotatedWith(SharedPreferencesName.class).to("andlander");

	}

}
