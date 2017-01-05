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

package org.cloudfoundry.container.customizer;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE)
final class AllowLinkingEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer {

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (!TomcatEmbeddedServletContainerFactory.class.isAssignableFrom(container.getClass())) {
            this.logger.warn("This is not a Tomcat container. Symbolically linked files will not be enabled for this application.");
            return;
        }

        ((TomcatEmbeddedServletContainerFactory) container).addContextLifecycleListeners(new AllowLinkingLifecycleListener());
    }

    private static final class AllowLinkingLifecycleListener implements LifecycleListener {

        @Override
        public void lifecycleEvent(LifecycleEvent event) {
            if (Lifecycle.CONFIGURE_START_EVENT.equals(event.getType())) {
                ((Context) event.getLifecycle()).getResources().setAllowLinking(true);
            }
        }

    }

}
