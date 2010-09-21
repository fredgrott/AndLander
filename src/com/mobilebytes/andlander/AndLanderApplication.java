package com.mobilebytes.andlander;

import java.util.List;

import com.google.inject.Module;

import roboguice.application.GuiceApplication;

public class AndLanderApplication extends GuiceApplication {

	@Override
	protected void addApplicationModules(List<Module> modules) {
        modules.add(new AppModule());
    }
}
