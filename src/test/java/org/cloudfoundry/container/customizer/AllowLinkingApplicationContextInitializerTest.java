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

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class AllowLinkingApplicationContextInitializerTest {

    private final ConfigurableApplicationContext applicationContext = mock(ConfigurableApplicationContext.class);

    private final AllowLinkingApplicationContextInitializer applicationContextInitializer = new AllowLinkingApplicationContextInitializer();

    private final ConfigurableListableBeanFactory beanFactory = mock(ConfigurableListableBeanFactory.class);

    @Test
    public void initialize() {
        this.applicationContextInitializer.initialize(this.applicationContext);

        verify(this.beanFactory).registerSingleton(eq(AllowLinkingEmbeddedServletContainerCustomizer.class.getCanonicalName()), any(AllowLinkingEmbeddedServletContainerCustomizer.class));
    }

    @Before
    public void setUp() throws Exception {
        when(this.applicationContext.getBeanFactory()).thenReturn(this.beanFactory);
    }

}
