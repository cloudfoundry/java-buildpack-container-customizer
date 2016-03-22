/*
 * Copyright 2016 the original author or authors.
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
import org.apache.catalina.WebResourceRoot;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public final class AllowLinkingTomcatContextCustomizerTest {

    private final TomcatEmbeddedServletContainerFactory container = new TomcatEmbeddedServletContainerFactory();

    private final Context context = mock(Context.class);

    private final AllowLinkingEmbeddedServletContainerCustomizer servletContainerCustomizer = new AllowLinkingEmbeddedServletContainerCustomizer();

    private final WebResourceRoot webResourceRoot = mock(WebResourceRoot.class);

    @Test
    public void customize() {
        this.servletContainerCustomizer.customize(this.container);

        Collection<LifecycleListener> lifecycleListeners = this.container.getContextLifecycleListeners();
        assertEquals(1, lifecycleListeners.size());
        LifecycleListener lifecycleListener = lifecycleListeners.iterator().next();

        LifecycleEvent lifecycleEvent = new LifecycleEvent(this.context, Lifecycle.CONFIGURE_START_EVENT, null);
        lifecycleListener.lifecycleEvent(lifecycleEvent);

        verify(this.webResourceRoot).setAllowLinking(true);
    }

    @Test
    public void customizeIgnoredEvent() {
        this.servletContainerCustomizer.customize(this.container);

        Collection<LifecycleListener> lifecycleListeners = this.container.getContextLifecycleListeners();
        assertEquals(1, lifecycleListeners.size());
        LifecycleListener lifecycleListener = lifecycleListeners.iterator().next();

        LifecycleEvent lifecycleEvent = new LifecycleEvent(this.context, Lifecycle.AFTER_DESTROY_EVENT, null);
        lifecycleListener.lifecycleEvent(lifecycleEvent);

        verifyZeroInteractions(this.context);
    }

    @Test
    public void customizeNonTomcat() {
        ConfigurableEmbeddedServletContainer container = mock(ConfigurableEmbeddedServletContainer.class);

        this.servletContainerCustomizer.customize(container);

        verifyZeroInteractions(container);
    }

    @Before
    public void setUp() throws Exception {
        when(this.context.getResources()).thenReturn(this.webResourceRoot);
    }

}
