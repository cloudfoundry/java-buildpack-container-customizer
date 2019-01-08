/*
 * Copyright 2016-2019 the original author or authors.
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

import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.core.Ordered;

import java.util.logging.Logger;

import static org.apache.catalina.Lifecycle.CONFIGURE_START_EVENT;

final class AllowLinkingEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer, Ordered {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (!TomcatEmbeddedServletContainerFactory.class.isAssignableFrom(container.getClass())) {
            this.logger.warning("This is not a Tomcat container. Symbolically linked files will not be enabled for this application.");
            return;
        }

        ((TomcatEmbeddedServletContainerFactory) container).addContextLifecycleListeners(event -> {
            if (CONFIGURE_START_EVENT.equals(event.getType())) {
                ((Context) event.getLifecycle()).getResources().setAllowLinking(true);
            }
        });
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}
