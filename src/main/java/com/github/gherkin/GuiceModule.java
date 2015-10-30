package com.github.gherkin;

import com.github.gherkin.service.ContentResource;
import com.google.inject.Binder;
import com.google.inject.Module;

public class GuiceModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(ContentResource.class);
    }
}
