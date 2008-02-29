/*
 * Copyright 2004-2008 the original author or authors.
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
package org.springframework.webflow.engine.support;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.webflow.engine.VariableValueFactory;
import org.springframework.webflow.execution.RequestContext;

public class BeanFactoryVariableValueFactory implements VariableValueFactory {

	private Class type;

	private AutowireCapableBeanFactory beanFactory;

	public BeanFactoryVariableValueFactory(Class type, AutowireCapableBeanFactory beanFactory) {
		this.type = type;
		this.beanFactory = beanFactory;
	}

	public Object createVariableValue(RequestContext context) {
		return beanFactory.createBean(type);
	}

	public Object restoreReferences(Object value, RequestContext context) {
		beanFactory.autowireBeanProperties(value, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
		return value;
	}

}