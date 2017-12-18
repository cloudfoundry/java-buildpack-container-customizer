/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.container.customizer.boot1;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ClassUtils;

import static org.springframework.util.ClassUtils.isPresent;

public final class AllowLinkingApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (areClassesPresent()) {
            applicationContext.getBeanFactory().registerSingleton(AllowLinkingEmbeddedServletContainerCustomizer.class.getCanonicalName(), new AllowLinkingEmbeddedServletContainerCustomizer());
        }
    }

    private boolean areClassesPresent() {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

        return isPresent("org.apache.catalina.Context", classLoader)
            && isPresent("org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory", classLoader);
    }

}
