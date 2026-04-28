/*******************************************************************************
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Eclipse Foundation - initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.core.tests.internal.core.builders;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.pde.internal.core.builders.PDEMarkerFactory;
import org.eclipse.pde.ui.tests.util.ProjectUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link org.eclipse.pde.internal.core.builders.ExtensionsErrorReporter}.
 */
public class ExtensionsErrorReporterTest {

	private IProject project;

	@Before
	public void setup() throws Exception {
		project = ProjectUtils.createPluginProject(getClass().getName(), "1.0.0");
	}

	@After
	public void tearDown() throws Exception {
		if (project != null && project.exists()) {
			project.delete(true, null);
		}
	}

	/**
	 * Tests that a plugin.xml with only the XML declaration, the eclipse
	 * processing instruction, and an empty &lt;plugin&gt; element does not
	 * produce a "useless file" warning. The file is a valid placeholder and
	 * should not be flagged.
	 */
	@Test
	public void testNoUselessFileWarningForEmptyPluginXml() throws Exception {
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" //$NON-NLS-1$
				+ "<?eclipse version=\"3.4\"?>\n" //$NON-NLS-1$
				+ "<plugin/>\n"; //$NON-NLS-1$
		IFile pluginXml = project.getFile("plugin.xml"); //$NON-NLS-1$
		pluginXml.create(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), true, null);

		project.build(IncrementalProjectBuilder.FULL_BUILD, null);

		assertThat(findUselessFileMarkers(pluginXml)).isEmpty();
	}

	/**
	 * Tests that a plugin.xml with child elements but no extensions or
	 * extension points does produce a "useless file" warning.
	 */
	@Test
	public void testUselessFileWarningWhenChildrenPresentButNoExtensions() throws Exception {
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" //$NON-NLS-1$
				+ "<?eclipse version=\"3.4\"?>\n" //$NON-NLS-1$
				+ "<plugin>\n" //$NON-NLS-1$
				+ "  <runtime/>\n" //$NON-NLS-1$
				+ "</plugin>\n"; //$NON-NLS-1$
		IFile pluginXml = project.getFile("plugin.xml"); //$NON-NLS-1$
		pluginXml.create(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), true, null);

		project.build(IncrementalProjectBuilder.FULL_BUILD, null);

		assertThat(findUselessFileMarkers(pluginXml)).isNotEmpty();
	}

	private List<IMarker> findUselessFileMarkers(IFile file) throws Exception {
		return Arrays.stream(file.findMarkers(PDEMarkerFactory.MARKER_ID, false, 0))
				.filter(m -> m.getAttribute(PDEMarkerFactory.PROBLEM_ID, -1) == PDEMarkerFactory.P_USELESS_FILE)
				.toList();
	}

}
