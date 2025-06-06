/*******************************************************************************
 *  Copyright (c) 2007, 2013 IBM Corporation and others.
 *
 *  This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *     IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.internal.build.site;

import java.util.Objects;

import org.eclipse.equinox.p2.publisher.eclipse.FeatureEntry;
import org.eclipse.pde.internal.build.Utils;
import org.osgi.framework.Version;
import org.osgi.framework.VersionRange;

/**
 * ReachablePlugin's are sorted first by id, then by the width of the version range.
 * With equal range width, {@code R1 < R2} if {@code R1.range.getMinimum() < R2.range.getMaximum()}
 */
public class ReachablePlugin implements Comparable<Object> {
	public static final VersionRange WIDEST_RANGE = Utils.EMPTY_RANGE;
	public static final VersionRange NARROWEST_RANGE = new VersionRange(VersionRange.LEFT_CLOSED, Version.emptyVersion, Version.emptyVersion, VersionRange.RIGHT_OPEN);

	private final String id;
	private final VersionRange range;

	public ReachablePlugin(String id, VersionRange range) {
		this.id = id;
		this.range = range;
	}

	public ReachablePlugin(FeatureEntry entry) {
		id = entry.getId();
		range = Utils.createVersionRange(entry);
	}

	public String getId() {
		return id;
	}

	public VersionRange getRange() {
		return range;
	}

	private static final Version VERSION_MAX = new Version(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

	@Override
	public int compareTo(Object o) {
		if (o instanceof ReachablePlugin toCompare) {
			int result = id.compareTo(toCompare.id);
			if (result != 0) {
				return result;
			}
			//We want the object with the widest version range to sort first
			Version right = range.getRight() == null ? VERSION_MAX : range.getRight();
			Version toCompareRight = toCompare.range.getRight() == null ? VERSION_MAX : toCompare.range.getRight();
			result = substract(toCompareRight, toCompare.range.getLeft()).compareTo(substract(right, range.getLeft()));
			if (result != 0) {
				return result;
			}
			result = -Boolean.compare(range.getRightType() == VersionRange.RIGHT_CLOSED, toCompare.range.getRightType() == VersionRange.RIGHT_CLOSED);
			if (result != 0) {
				return result;
			}
			if (this.equals(o)) {
				return 0;
			}
			result = range.getLeft().compareTo(toCompareRight);
			if (result != 0) {
				return result;
			}
			//Give up
			return -1;
		}
		return -1;
	}

	private Version substract(Version v1, Version v2) { //v1 - v2 where v1 is always greater or equals to v2
		int major, minor, micro = 0;
		int carry = 0;
		if (v1.getMicro() < v2.getMicro()) {
			micro = Integer.MAX_VALUE - v2.getMicro() + v1.getMicro();
			carry = 1;
		} else {
			micro = v1.getMicro() - v2.getMicro();
			carry = 0;
		}
		if (v1.getMinor() < v2.getMinor() + carry) {
			minor = Integer.MAX_VALUE - (v2.getMinor() + carry) + v1.getMinor();
			carry = 1;
		} else {
			minor = v1.getMinor() - (v2.getMinor() + carry);
			carry = 0;
		}
		if (v1.getMajor() < v2.getMajor() + carry) {
			major = Integer.MAX_VALUE - (v2.getMajor() + carry) + v1.getMajor();
			carry = 1;
		} else {
			major = v1.getMajor() - (v2.getMajor() + carry);
			carry = 0;
		}
		return new Version(major, minor, micro);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof ReachablePlugin toCompare //
				&& Objects.equals(id, toCompare.id) //
				&& range.getLeftType() == toCompare.range.getLeftType() //
				&& range.getRightType() == toCompare.range.getRightType() //
				&& Objects.equals(range.getLeft(), toCompare.range.getLeft())//
				&& Objects.equals(range.getRight(), toCompare.range.getRight());
	}

	@Override
	public int hashCode() {
		return id.hashCode() + range.hashCode() * 17;
	}

	@Override
	public String toString() {
		return id + ' ' + range.toString();
	}
}
