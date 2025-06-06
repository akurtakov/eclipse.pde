/*******************************************************************************
 * Copyright (c) 2013, 2023 bndtools project and others.
 *
* This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Neil Bartlett <njbartlett@gmail.com> - initial API and implementation
 *     Ferry Huberts <ferry.huberts@pelagic.nl> - ongoing enhancements
 *     Peter Kriens <peter.kriens@aqute.biz> - ongoing enhancements
 *     Sean Bright <sean@malleable.com> - ongoing enhancements
 *     Carter Smithhart <carter.smithhart@gmail.com> - ongoing enhancements
 *     BJ Hargrave <bj@hargrave.dev> - ongoing enhancements
*******************************************************************************/
package org.eclipse.pde.bnd.ui.model.resource;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.pde.bnd.ui.BoldStyler;
import org.eclipse.pde.bnd.ui.internal.ItalicStyler;
import org.osgi.framework.Version;
import org.osgi.framework.namespace.AbstractWiringNamespace;
import org.osgi.framework.namespace.BundleNamespace;
import org.osgi.framework.namespace.ExecutionEnvironmentNamespace;
import org.osgi.framework.namespace.HostNamespace;
import org.osgi.framework.namespace.IdentityNamespace;
import org.osgi.framework.namespace.PackageNamespace;
import org.osgi.namespace.contract.ContractNamespace;
import org.osgi.namespace.extender.ExtenderNamespace;
import org.osgi.namespace.service.ServiceNamespace;
import org.osgi.resource.Capability;
import org.osgi.resource.Namespace;
import org.osgi.resource.Requirement;
import org.osgi.resource.Resource;
import org.osgi.service.repository.ContentNamespace;

import aQute.bnd.osgi.resource.CapReqBuilder;
import aQute.bnd.osgi.resource.FilterParser;
import aQute.bnd.osgi.resource.FilterParser.Expression;
import aQute.bnd.osgi.resource.FilterParser.Op;
import aQute.bnd.osgi.resource.FilterParser.RangeExpression;
import aQute.bnd.osgi.resource.FilterParser.SimpleExpression;
import aQute.bnd.osgi.resource.FilterParser.WithRangeExpression;
import aQute.bnd.osgi.resource.ResourceUtils;
import aQute.bnd.service.resource.SupportingResource;
import aQute.bnd.unmodifiable.Maps;

public class R5LabelFormatter {

	private final static Pattern				EE_PATTERN		= Pattern.compile("osgi.ee=([^)]*).*version=([^)]*)");

	private static final Map<String, Pattern>	FILTER_PATTERNS	= Maps.of(
		ExecutionEnvironmentNamespace.EXECUTION_ENVIRONMENT_NAMESPACE, EE_PATTERN, PackageNamespace.PACKAGE_NAMESPACE,
		Pattern.compile("osgi\\.wiring\\.package=([^)]*)"));

	public static final String										PLUGIN_ID			= "bndtools.core";

	public static String getVersionAttributeName(String ns) {
		String r;

		if (ns == null) {
			r = null;
		} else if (ns.equals(IdentityNamespace.IDENTITY_NAMESPACE)) {
			r = IdentityNamespace.CAPABILITY_VERSION_ATTRIBUTE;
		} else if (ns.equals(ContentNamespace.CONTENT_NAMESPACE)) {
			r = null;
		} else if (ns.equals(BundleNamespace.BUNDLE_NAMESPACE)) {
			r = AbstractWiringNamespace.CAPABILITY_BUNDLE_VERSION_ATTRIBUTE;
		} else if (ns.equals(HostNamespace.HOST_NAMESPACE)) {
			r = AbstractWiringNamespace.CAPABILITY_BUNDLE_VERSION_ATTRIBUTE;
		} else if (ns.equals(ExecutionEnvironmentNamespace.EXECUTION_ENVIRONMENT_NAMESPACE)) {
			r = ExecutionEnvironmentNamespace.CAPABILITY_VERSION_ATTRIBUTE;
		} else if (ns.equals(PackageNamespace.PACKAGE_NAMESPACE)) {
			r = PackageNamespace.CAPABILITY_VERSION_ATTRIBUTE;
		} else if (ns.equals(ExtenderNamespace.EXTENDER_NAMESPACE)) {
			r = ExtenderNamespace.CAPABILITY_VERSION_ATTRIBUTE;
		} else if (ns.equals(ContractNamespace.CONTRACT_NAMESPACE)) {
			r = ContractNamespace.CAPABILITY_VERSION_ATTRIBUTE;
		} else if (ns.equals(ServiceNamespace.SERVICE_NAMESPACE)) {
			r = null;
		} else {
			r = null;
		}

		return r;
	}

	public static Pattern getFilterPattern(String ns) {
		return FILTER_PATTERNS.get(ns);
	}

	/*
	 * Most namespaces have a "main" attribute that is the same as the
	 * namespace. For example, namespace osgi.wiring.package has an attribute
	 * "osgi.wiring.package" that specifies the package name. The main exception
	 * to this rule is the osgi.service namespace, which uses "objectClass".
	 */
	public static String getMainAttributeName(String ns) {
		if (ServiceNamespace.SERVICE_NAMESPACE.equals(ns)) {
			return ServiceNamespace.CAPABILITY_OBJECTCLASS_ATTRIBUTE;
		}

		return ns;
	}

	public static String getNamespaceImagePath(String ns) {
		String r = "brkpi_obj.svg"; // generic green dot

		if (BundleNamespace.BUNDLE_NAMESPACE.equals(ns) || HostNamespace.HOST_NAMESPACE.equals(ns)) {
			r = "bundle.png";
		} else if (ExecutionEnvironmentNamespace.EXECUTION_ENVIRONMENT_NAMESPACE.equals(ns)) {
			r = "jcu_obj.svg";
		} else if (PackageNamespace.PACKAGE_NAMESPACE.equals(ns)) {
			r = "package_obj.svg";
		} else if (ServiceNamespace.SERVICE_NAMESPACE.equals(ns)) {
			r = "service.png";
		} else if (ExtenderNamespace.EXTENDER_NAMESPACE.equals(ns)) {
			r = "tricks.svg";
		} else if (ContractNamespace.CONTRACT_NAMESPACE.equals(ns)) {
			r = "signed_yes_tbl.svg";
		} else if ("osgi.whiteboard".equals(ns)) {
			r = "whiteboard.png";
		} else if ("bnd.multirelease".equals(ns)) {
			r = "jar_obj.svg";
		} else if ("osgi.unresolvable".equalsIgnoreCase(ns) || "osgi.missing".equalsIgnoreCase(ns)
			|| "donotresolve".equalsIgnoreCase(ns) || "compile-only".equalsIgnoreCase(ns)) {
			r = "excludeMode_filter.svg";
		}

		return r;
	}

	public static void appendNamespaceWithValue(StyledString label, String ns, String value, boolean shorten) {
		String prefix = ns;
		if (shorten) {
			if (IdentityNamespace.IDENTITY_NAMESPACE.equals(ns)) {
				prefix = "id";
			} else if (BundleNamespace.BUNDLE_NAMESPACE.equals(ns)) {
				prefix = "";
			} else if (HostNamespace.HOST_NAMESPACE.equals(ns)) {
				prefix = "host";
			} else if (ExecutionEnvironmentNamespace.EXECUTION_ENVIRONMENT_NAMESPACE.equals(ns)) {
				prefix = "";
			} else if (PackageNamespace.PACKAGE_NAMESPACE.equals(ns)) {
				prefix = "";
			} else if (ServiceNamespace.SERVICE_NAMESPACE.equals(ns)) {
				prefix = "";
			} else if (ContractNamespace.CONTRACT_NAMESPACE.equals(ns)) {
				prefix = "";
			} else if ("osgi.whiteboard".equals(ns)) {
				prefix = "";
			}
		}

		if (prefix.length() > 0) {
			label.append(prefix + "=", StyledString.QUALIFIER_STYLER);
		}
		label.append(value, BoldStyler.INSTANCE_DEFAULT);
	}

	public static void appendCapability(StyledString label, Capability cap, boolean shorten) {
		String ns = cap.getNamespace();

		Resource r = cap.getResource();
		if (r instanceof SupportingResource sr) {
			int index = sr.getSupportingIndex();
			if (index >= 0) {
				label.append("[" + index + "] ");
			}
		}

		Object nsValue = cap.getAttributes()
			.get(getMainAttributeName(ns));
		String versionAttributeName = getVersionAttributeName(ns);
		if (nsValue != null) {
			appendNamespaceWithValue(label, ns, nsValue.toString(), shorten);

			if (versionAttributeName != null) {
				Object version = cap.getAttributes()
					.get(versionAttributeName);
				if (version != null) {
					label.append(", " + versionAttributeName, StyledString.QUALIFIER_STYLER);
					label.append(" " + version.toString(), BoldStyler.INSTANCE_COUNTER);
				}
			}
		} else {
			label.append(ns, BoldStyler.INSTANCE_DEFAULT);
		}
		label.append(" ", StyledString.QUALIFIER_STYLER);

		if (!cap.getAttributes()
			.isEmpty()) {
			boolean first = true;
			for (Entry<String, Object> entry : cap.getAttributes()
				.entrySet()) {
				String key = entry.getKey();
				if (!key.equals(ns) && !key.equals(versionAttributeName)) {
					if (first) {
						label.append("[", StyledString.QUALIFIER_STYLER);
					} else {
						label.append(", ", StyledString.QUALIFIER_STYLER);
					}

					first = false;
					label.append(key + "=", StyledString.QUALIFIER_STYLER);
					label.append(entry.getValue() != null ? entry.getValue()
						.toString() : "<null>", StyledString.QUALIFIER_STYLER);
				}
			}
			if (!first) {
				label.append("]", StyledString.QUALIFIER_STYLER);
			}
		}

		if (!cap.getDirectives()
			.isEmpty()) {
			label.append(" ");
			boolean first = true;
			for (Entry<String, String> directive : cap.getDirectives()
				.entrySet()) {
				label.append(directive.getKey() + ":=" + directive.getValue(), StyledString.QUALIFIER_STYLER);
				if (!first) {
					label.append(", ", StyledString.QUALIFIER_STYLER);
				}
			}
		}

	}

	public static void appendResourceLabel(StyledString label, Resource resource) {
		Capability identity = ResourceUtils.getIdentityCapability(resource);
		String name = ResourceUtils.getIdentity(identity);
		if (name == null) {
			if (resource != null) {
				name = resource.toString();
			} else {
				name = "<unknown>";
			}
		}
		label.append(name, BoldStyler.INSTANCE_DEFAULT);

		Version version = org.eclipse.pde.bnd.ui.ResourceUtils.getVersion(identity);
		if (version != null) {
			label.append(" " + version, StyledString.COUNTER_STYLER);
		}
	}

	public static void appendRequirementLabel(StyledString label, Requirement requirement, boolean shorten) {
		try {
			requirement = CapReqBuilder.unalias(requirement);
		} catch (Exception e) {
			ILog.get()
				.log(new Status(IStatus.WARNING, PLUGIN_ID, 0,
					"Error parsing aliased requirement, using original", e));
		}

		String namespace = requirement.getNamespace();
		String filter = requirement.getDirectives()
			.get(Namespace.REQUIREMENT_FILTER_DIRECTIVE);

		boolean optional = Namespace.RESOLUTION_OPTIONAL.equals(requirement.getDirectives()
			.get(Namespace.REQUIREMENT_RESOLUTION_DIRECTIVE));

		Resource r = requirement.getResource();
		if (r instanceof SupportingResource sr) {
			int index = sr.getSupportingIndex();
			if (index >= 0) {
				label.append("[" + index + "] ");
			}
		}

		FilterParser fp = new FilterParser();
		if (filter == null) {
			if (namespace.contains("$")) {
				Pattern pattern = Pattern.compile("\\{(.*?)\\}");
				Matcher matcher = pattern.matcher(namespace);
				label.append(namespace);
				while (matcher.find()) {
					int begin = matcher.start(1);
					int end = matcher.end(1);
					label.setStyle(begin, end - begin, BoldStyler.INSTANCE_DEFAULT);
				}
			} else {
				label.append(namespace + ": <no filter>", ItalicStyler.INSTANCE_ERROR);
			}
		} else {
			try {
				Expression exp = fp.parse(filter);
				if (exp instanceof WithRangeExpression) {
					appendNamespaceWithValue(label, namespace, ((WithRangeExpression) exp).printExcludingRange(),
						shorten);
					RangeExpression range = ((WithRangeExpression) exp).getRangeExpression();
					if (range != null) {
						label.append(" ")
							.append(formatRangeString(range), StyledString.COUNTER_STYLER);
					}
				} else if (ExecutionEnvironmentNamespace.EXECUTION_ENVIRONMENT_NAMESPACE.equals(namespace)) {
					Matcher matcher = EE_PATTERN.matcher(filter);
					if (matcher.find()) {
						String eename = matcher.group(1);
						String version = matcher.group(2);
						appendNamespaceWithValue(label, namespace, eename, true);
						label.append(" ")
							.append(version, StyledString.COUNTER_STYLER);
					} else {
						appendNamespaceWithValue(label, namespace, filter, true);
					}
				} else {
					appendNamespaceWithValue(label, namespace, filter, true);
				}
			} catch (Exception e) {
				label.append(namespace + ": ", StyledString.QUALIFIER_STYLER);
				label.append("<parse error>", ItalicStyler.INSTANCE_ERROR);
			}
		}

		boolean first = true;
		for (Entry<String, String> directive : requirement.getDirectives()
			.entrySet()) {
			if (Namespace.REQUIREMENT_RESOLUTION_DIRECTIVE.equals(directive.getKey())
				|| Namespace.REQUIREMENT_FILTER_DIRECTIVE.equals(directive.getKey())) {
				continue; // deal with the filter: and resolution: directives
			}
							// separately
			StringBuilder buf = new StringBuilder();
			buf.append(first ? " " : ", ");
			buf.append(directive.getKey())
				.append(":=")
				.append(directive.getValue());
			label.append(buf.toString(), StyledString.QUALIFIER_STYLER);
			first = false;
		}

		if (optional) {
			label.setStyle(0, label.length(), StyledString.QUALIFIER_STYLER);
			label.append(" <optional>", ItalicStyler.INSTANCE_DEFAULT);
		}
	}

	public static String formatRangeString(RangeExpression range) {
		StringBuilder sb = new StringBuilder();

		SimpleExpression low = range.getLow();
		if (low == null) {
			sb.append("[0");
		} else {
			if (low.getOp() == Op.GREATER) {
				sb.append("(");
			} else {
				sb.append("[");
			}
			sb.append(low.getValue());
		}

		sb.append(", ");

		SimpleExpression high = range.getHigh();
		if (high == null) {
			sb.append("\u221e]"); // INFINITY Unicode: U+221E, UTF-8: E2 88 9E
		} else {
			sb.append(high.getValue());
			if (high.getOp() == Op.LESS) {
				sb.append(")");
			} else {
				sb.append("]");
			}
		}
		return sb.toString();
	}

}
