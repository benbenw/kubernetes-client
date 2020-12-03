/**
 * Copyright 2018 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.dekorate.crd.decorator;

import io.dekorate.kubernetes.decorator.Decorator;
import io.dekorate.kubernetes.decorator.NamedResourceDecorator;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceSubresourcesFluent;

public class AddLabelSelectorPathDecorator extends NamedResourceDecorator<CustomResourceSubresourcesFluent<?>> {

  private final String path;
 
	public AddLabelSelectorPathDecorator(String path) {
    this(ANY, path);
	}

	public AddLabelSelectorPathDecorator(String name, String path) {
		super(name);
		this.path = path;
	}
 
	@Override
	public void andThenVisit(CustomResourceSubresourcesFluent<?> subresources, ObjectMeta resourceMeta) {
 if (subresources.hasScale())  {
      subresources.editScale().withLabelSelectorPath(path).endScale();
    } else {
      subresources.withNewScale().withLabelSelectorPath(path).endScale();
    }
	}

	@Override
	public Class<? extends Decorator>[] after() {
		return new Class[] { AddSubresourcesDecorator.class };
	}
}
