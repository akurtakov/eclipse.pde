/*******************************************************************************
 * Copyright (c) 2000, 2022 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     EclipseSource Corporation - ongoing enhancements
 *     Lars Vogel <Lars.Vogel@vogella.com> - Bug 433714
 *******************************************************************************/
package org.eclipse.pde.internal.ui;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Bundle of all images used by the PDE plugin.
 */
public class PDEPluginImages {

	private static final String NAME_PREFIX = PDEPlugin.getPluginId() + "."; //$NON-NLS-1$

	private static ImageRegistry PLUGIN_REGISTRY;

	public final static String ICONS_PATH = "icons/"; //$NON-NLS-1$

	/**
	 * Set of predefined Image Descriptors.
	 */

	private static final String PATH_OBJ = ICONS_PATH + "obj16/"; //$NON-NLS-1$
	private static final String PATH_VIEW = ICONS_PATH + "view16/"; //$NON-NLS-1$
	private static final String PATH_DVIEW = ICONS_PATH + "dview16/"; //$NON-NLS-1$
	private static final String PATH_LCL = ICONS_PATH + "elcl16/"; //$NON-NLS-1$
	private static final String PATH_TOOL = ICONS_PATH + "etool16/"; //$NON-NLS-1$
	private static final String PATH_OVR = ICONS_PATH + "ovr16/"; //$NON-NLS-1$
	private static final String PATH_WIZBAN = ICONS_PATH + "wizban/"; //$NON-NLS-1$

	/**
	 * Frequently used images
	 */
	public static final String IMG_FORM_WIZ = NAME_PREFIX + "FORM_WIZ"; //$NON-NLS-1$
	public static final String IMG_FORM_BANNER = NAME_PREFIX + "FORM_BANNER"; //$NON-NLS-1$
	public static final String IMG_ATTRIBUTE_OBJ = NAME_PREFIX + "IMG_ATTRIBUTE_OBJ"; //$NON-NLS-1$
	public static final String IMG_ATT_CLASS_OBJ = NAME_PREFIX + "IMG_ATT_CLASS_OBJ"; //$NON-NLS-1$
	public static final String IMG_ATT_FILE_OBJ = NAME_PREFIX + "IMG_ATT_FILE_OBJ"; //$NON-NLS-1$
	public static final String IMG_ATT_IMPL_OBJ = NAME_PREFIX + "IMG_ATT_IMPL_OBJ"; //$NON-NLS-1$
	public static final String IMG_ATT_REQ_OBJ = NAME_PREFIX + "IMG_ATT_REQ_OBJ"; //$NON-NLS-1$
	public static final String IMG_ATT_ID_OBJ = NAME_PREFIX + "IMG_ATT_ID_OBJ"; //$NON-NLS-1$
	public static final String IMG_ATT_STRING_OBJ = NAME_PREFIX + "IMG_ATT_STRING_OBJ"; //$NON-NLS-1$
	public static final String IMG_ATT_BOOLEAN_OBJ = NAME_PREFIX + "IMG_ATT_BOOLEAN_OBJ"; //$NON-NLS-1$
	public static final String IMG_GENERIC_XML_OBJ = NAME_PREFIX + "IMG_GENERIC_XML_OBJ"; //$NON-NLS-1$
	public static final String OBJ_DESC_GENERATE_CLASS = NAME_PREFIX + "OBJ_DESC_GENERATE_CLASS"; //$NON-NLS-1$
	public static final String OBJ_DESC_GENERATE_INTERFACE = NAME_PREFIX + "OBJ_DESC_GENERATE_INTERFACE"; //$NON-NLS-1$
	public static final String OBJ_DESC_PACKAGE = NAME_PREFIX + "OBJ_DESC_PACKAGE"; //$NON-NLS-1$
	public static final String OBJ_DESC_BUNDLE = NAME_PREFIX + "OBJ_DESC_BUNDLE"; //$NON-NLS-1$

	/**
	 * OBJ16
	 */
	public static final ImageDescriptor DESC_MAIN_TAB = create(PATH_OBJ, "main_tab.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ALL_SC_OBJ = create(PATH_OBJ, "all_sc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATTRIBUTE_OBJ = create(PATH_OBJ, "attribute_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATT_CLASS_OBJ = create(PATH_OBJ, "att_class_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATT_FILE_OBJ = create(PATH_OBJ, "att_file_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATT_IMPL_OBJ = create(PATH_OBJ, "att_impl_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATT_REQ_OBJ = create(PATH_OBJ, "att_req_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATT_URI_OBJ = create(PATH_OBJ, "att_URI_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATT_ID_OBJ = create(PATH_OBJ, "att_id_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATT_STRING_OBJ = create(PATH_OBJ, "att_string_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ATT_BOOLEAN_OBJ = create(PATH_OBJ, "att_boolean_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_BUNDLE_OBJ = create(PATH_OBJ, "bundle_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CHOICE_SC_OBJ = create(PATH_OBJ, "choice_sc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FEATURE_JAR_OBJ = create(PATH_OBJ, "ftr_jar_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FEATURE_MF_OBJ = create(PATH_OBJ, "ftr_mf_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FEATURE_OBJ = create(PATH_OBJ, "feature_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_LICENSE_OBJ = create(PATH_OBJ, "license_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NOREF_FEATURE_OBJ = create(PATH_OBJ, "noref_feature_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ELREF_SC_OBJ = create(PATH_OBJ, "elref_sc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXTENSIONS_OBJ = create(PATH_OBJ, "extensions_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXTENSION_OBJ = create(PATH_OBJ, "extension_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXT_PLUGIN_OBJ = create(PATH_OBJ, "ext_plugin_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXT_FRAGMENT_OBJ = create(PATH_OBJ, "external_frgmt_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXT_POINTS_OBJ = create(PATH_OBJ, "ext_points_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXT_POINT_OBJ = create(PATH_OBJ, "ext_point_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_GEL_SC_OBJ = create(PATH_OBJ, "gel_sc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_GENERIC_XML_OBJ = create(PATH_OBJ, "generic_xml_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_GROUP_SC_OBJ = create(PATH_OBJ, "group_sc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_JAR_OBJ = create(PATH_OBJ, "jar_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_JAR_LIB_OBJ = create(PATH_OBJ, "jar_l_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_JAVA_LIB_OBJ = create(PATH_OBJ, "java_lib_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_OVERVIEW_OBJ = create(PATH_OBJ, "overview_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PAGE_OBJ = create(PATH_OBJ, "page_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PLUGIN_MF_OBJ = create(PATH_OBJ, "plugin_mf_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FRAGMENT_MF_OBJ = create(PATH_OBJ, "frgmt_mf_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_BUILD_VAR_OBJ = create(PATH_OBJ, "build_var_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_LOOP_OBJ = create(PATH_OBJ, "loop_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_LOOP_NODE_OBJ = create(PATH_OBJ, "loop_node_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PROCESSING_INST_OBJ = create(PATH_OBJ, "processinginst.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_XML_ELEMENT_OBJ = create(PATH_OBJ, "element.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_XML_ELEMENT_REF_OBJ = create(PATH_OBJ, "elref_sc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FOLDER_OBJ = create(PATH_OBJ, "fldr_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_DEFAULT_OBJ = create(PATH_OBJ, "build_var_obj.svg"); //$NON-NLS-1$

	public static final ImageDescriptor DESC_SIMPLECS_OBJ = create(PATH_OBJ, "cheatsheet_simple_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_COMPCS_OBJ = create(PATH_OBJ, "cheatsheet_composite_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CSTASKGROUP_OBJ = create(PATH_OBJ, "cheatsheet_taskgroup_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CSITEM_OBJ = create(PATH_OBJ, "cheatsheet_item_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CSSUBITEM_OBJ = create(PATH_OBJ, "cheatsheet_subitem_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CSINTRO_OBJ = create(PATH_OBJ, "cheatsheet_intro_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CSCONCLUSION_OBJ = create(PATH_OBJ, "cheatsheet_conclusion_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CSUNSUPPORTED_OBJ = create(PATH_OBJ, "cheatsheet_unsupported_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CHEATSHEET_OBJ = create(PATH_OBJ, "cheatsheet_obj.svg"); //$NON-NLS-1$

	public static final ImageDescriptor DESC_TOC_OBJ = create(PATH_OBJ, "toc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TOC_TOPIC_OBJ = create(PATH_OBJ, "toc_topic_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TOC_LEAFTOPIC_OBJ = create(PATH_OBJ, "toc_leaftopic_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TOC_LINK_OBJ = create(PATH_OBJ, "toc_link_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TOC_ANCHOR_OBJ = create(PATH_OBJ, "toc_anchor_obj.svg"); //$NON-NLS-1$

	public static final ImageDescriptor DESC_CTXHELP_CONTEXT_OBJ = create(PATH_OBJ, "ctxhelp_context_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CTXHELP_DESC_OBJ = create(PATH_OBJ, "ctxhelp_desc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CTXHELP_COMMAND_OBJ = create(PATH_OBJ, "ctxhelp_command_obj.svg"); //$NON-NLS-1$

	public static final ImageDescriptor DESC_COMGROUP_OBJ = create(PATH_OBJ, "keygroups_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_GENCOM_OBJ = create(PATH_OBJ, "command_obj.svg"); //$NON-NLS-1$

	public static final ImageDescriptor DESC_REPOSITORY_OBJ = create(PATH_OBJ, "metadata_repo_obj.svg"); //$NON-NLS-1$

	public static final ImageDescriptor DESC_PLUGIN_OBJ = create(PATH_OBJ, "plugin_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PLUGIN_DIS_OBJ = create(PATH_OBJ, "plugin_dis_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_OPERATING_SYSTEM_OBJ = create(PATH_OBJ, "operating_system_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_SETTINGS_OBJ = create(PATH_OBJ, "settings.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FRAGMENT_OBJ = create(PATH_OBJ, "frgmt_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FRAGMENT_DIS_OBJ = create(PATH_OBJ, "frgmt_dis_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_REQ_PLUGINS_OBJ = create(PATH_OBJ, "req_plugins_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FRAGMENTS_OBJ = create(PATH_OBJ, "frgmts_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_REQ_PLUGIN_OBJ = create(PATH_OBJ, "req_plugin_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_RUNTIME_OBJ = create(PATH_OBJ, "runtime_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_SCHEMA_OBJ = create(PATH_OBJ, "schema_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_SCOMP_JAR_OBJ = create(PATH_OBJ, "scomp_jar_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_SEQ_SC_OBJ = create(PATH_OBJ, "seq_sc_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_DOC_SECTION_OBJ = create(PATH_OBJ, "doc_section_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ALERT_OBJ = create(PATH_OBJ, "alert_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TSK_ALERT_OBJ = create(PATH_OBJ, "tsk_alert_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_LINK_OBJ = create(PATH_OBJ, "link_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_LINKS_OBJ = create(PATH_OBJ, "links_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ERROR_ST_OBJ = create(PATH_OBJ, "error_st_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_WARNING_ST_OBJ = create(PATH_OBJ, "warning_st_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_INFO_ST_OBJ = create(PATH_OBJ, "info_st_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CATEGORY_OBJ = create(PATH_OBJ, "category_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PSEARCH_OBJ = create(PATH_OBJ, "psearch_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_SITE_OBJ = create(PATH_OBJ, "site_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_JUNIT_MAIN_TAB = create(PATH_OBJ, "test.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_OUTPUT_FOLDER_OBJ = create(PATH_OBJ, "output_folder_attrib.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_SOURCE_ATTACHMENT_OBJ = create(PATH_OBJ, "source_attach_attrib.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FTR_XML_OBJ = create(PATH_OBJ, "ftr_xml_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_OK_TRANSLATE_OBJ = create(PATH_OBJ, "ok_st_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NO_TRANSLATE_OBJ = create(PATH_OBJ, "incomplete_tsk.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_DISCOVERY = create(PATH_OBJ, "discovery.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_XML_TEXT_NODE = create(PATH_OBJ, "xml_text_node.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CLIPBOARD = create(PATH_OBJ, "copyviewtoclipboard_tsk.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_BUILD_EXEC = create(PATH_OBJ, "build_exec.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TARGET_DEFINITION = create(PATH_OBJ, "target_profile_xml_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TARGET_ENVIRONMENT = create(PATH_OBJ, "environment.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PRODUCT_DEFINITION = create(PATH_OBJ, "product_xml_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PRODUCT_LAUNCHING = create(PATH_OBJ, "start_application.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PRODUCT_BRANDING = create(PATH_OBJ, "eclipse.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PLUGINS_FRAGMENTS = create(PATH_OBJ, "plugins_and_fragments.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_SITE_XML_OBJ = create(PATH_OBJ, "site_xml_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_IMAGE_APPLICATION = create(PATH_OBJ, "image_application.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FOCUS_ON = create(PATH_OBJ, "focus.svg"); //$NON-NLS-1$

	/**
	 * OVR16
	 */
	public static final ImageDescriptor DESC_DOC_CO = create(PATH_OVR, "doc_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_WARNING_CO = create(PATH_OVR, "warning_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ERROR_CO = create(PATH_OVR, "error_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXPORT_CO = create(PATH_OVR, "export_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXTERNAL_CO = create(PATH_OVR, "external_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_BINARY_CO = create(PATH_OVR, "binary_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_JAVA_CO = create(PATH_OVR, "java_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_JAR_CO = create(PATH_OVR, "jar_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PROJECT_CO = create(PATH_OVR, "project_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_OPTIONAL_CO = create(PATH_OVR, "optional_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_INTERNAL_CO = create(PATH_OVR, "internal_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FRIEND_CO = create(PATH_OVR, "friend_co.svg"); //$NON-NLS-1$

	/**
	 * TOOL16
	 */
	public static final ImageDescriptor DESC_NEWEXPRJ_TOOL = create(PATH_TOOL, "newexprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWEXP_TOOL = create(PATH_TOOL, "newexp_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWEX_TOOL = create(PATH_TOOL, "newex_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWFTRPRJ_TOOL = create(PATH_TOOL, "newftrprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWPPRJ_TOOL = create(PATH_TOOL, "newpprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWFRAGPRJ_TOOL = create(PATH_TOOL, "newfprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWEXP_WIZ_TOOL = create(PATH_TOOL, "newexp_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_VALIDATE_TOOL = create(PATH_TOOL, "validate.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXPORT_PLUGIN_TOOL = create(PATH_TOOL, "exp_deployplug.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXPORT_PRODUCT_TOOL = create(PATH_TOOL, "exp_product.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXPORT_FEATURE_TOOL = create(PATH_TOOL, "exp_deployfeat.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXPORT_TARGET_TOOL = create(PATH_TOOL, "export_target.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_BUILD_TOOL = create(PATH_TOOL, "build_exec.svg"); //$NON-NLS-1$

	/**
	 * LCL
	 */
	public static final ImageDescriptor DESC_ADD_ATT = create(PATH_LCL, "add_att.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ALPHAB_SORT_CO = create(PATH_LCL, "alphab_sort_co.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ALPHAB_SORT_CO_MINI = create(PATH_LCL, "alphab_sort_co_mini.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CLONE_ATT = create(PATH_LCL, "clone_att.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CLONE_EL = create(PATH_LCL, "clone_el.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_GENERATE_CLASS = create(PATH_LCL, "generate_class.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_GENERATE_INTERFACE = create(PATH_LCL, "generate_interface.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PACKAGE_OBJ = create(PATH_LCL, "package_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_GOTOOBJ = create(PATH_LCL, "goto_obj.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PROPERTIES = create(PATH_LCL, "properties.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_REFRESH = create(PATH_LCL, "refresh.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_DELETE = create(PATH_LCL, "delete_edit.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_MAXIMIZE = create(PATH_LCL, "maximize.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_RESTORE = create(PATH_LCL, "restore.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FULL_HIERARCHY = create(PATH_LCL, "full_hierarchy.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_HORIZONTAL = create(PATH_LCL, "th_horizontal.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_VERTICAL = create(PATH_LCL, "th_vertical.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_COLLAPSE_ALL = create(PATH_LCL, "collapseall.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_COLLAPSE_ALL_MINI = create(PATH_LCL, "collapse_all_mini.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TOGGLE_EXPAND_STATE = create(PATH_LCL, "toggle_expand_state.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_HELP = create(PATH_LCL, "help.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_LINK_WITH_EDITOR = create(PATH_LCL, "synced.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CALLEES = create(PATH_LCL, "ch_callees.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CALLERS = create(PATH_LCL, "ch_callers.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_DEP_LOOP = create(PATH_LCL, "dep_loop.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FLAT_LAYOUT = create(PATH_LCL, "flatLayout.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_HIERARCHICAL_LAYOUT = create(PATH_LCL, "hierarchicalLayout.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_HISTORY_LIST = create(PATH_LCL, "history_list.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CLEAR = create(PATH_LCL, "clear.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FILTER = create(PATH_LCL, "filter_ps.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FILTER_RELATED = create(PATH_LCL, "filter_related.svg"); //$NON-NLS-1$

	public static final ImageDescriptor DESC_RUN_EXC = create(PATH_OBJ, "run_exc.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_DEBUG_EXC = create(PATH_OBJ, "debug_exc.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PROFILE_EXC = create(PATH_OBJ, "profile_exc.svg"); //$NON-NLS-1$

	public static final ImageDescriptor DESC_CON_SEV = create(PATH_LCL, "configure_problem_severity.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ORGANIZE_MANIFESTS_ACTION = create(PATH_LCL,
			"cleanmanifest.svg"); //$NON-NLS-1$
	/**
	 * WIZ
	 */
	public static final ImageDescriptor DESC_NEWPPRJ_WIZ = create(PATH_WIZBAN, "newpprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWFRAGPRJ_WIZ = create(PATH_WIZBAN, "newfprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_DEFCON_WIZ = create(PATH_WIZBAN, "defcon_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TARGET_WIZ = create(PATH_WIZBAN, "target_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWEX_WIZ = create(PATH_WIZBAN, "newex_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWEXP_WIZ = create(PATH_WIZBAN, "newexp_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWEXPRJ_WIZ = create(PATH_WIZBAN, "newexprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWFTRPRJ_WIZ = create(PATH_WIZBAN, "newftrprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWFTRPTCH_WIZ = create(PATH_WIZBAN, "newefix_wizban.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_NEWSITEPRJ_WIZ = create(PATH_WIZBAN, "newsiteprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FILESYSTEM_WIZARD = create(PATH_WIZBAN, "newfolder_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_CONVJPPRJ_WIZ = create(PATH_WIZBAN, "convjpprj_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXT_POINT_SCHEMA_WIZ = create(PATH_WIZBAN, "schema_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PLUGIN_IMPORT_WIZ = create(PATH_WIZBAN, "imp_extplug_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PLUGIN_EXPORT_WIZ = create(PATH_WIZBAN, "exp_deployplug_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FEATURE_IMPORT_WIZ = create(PATH_WIZBAN, "imp_extfeat_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_FEATURE_EXPORT_WIZ = create(PATH_WIZBAN, "exp_deployfeat_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_MIGRATE_30_WIZ = create(PATH_WIZBAN, "migrate_30_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PRODUCT_WIZ = create(PATH_WIZBAN, "product_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_PRODUCT_EXPORT_WIZ = create(PATH_WIZBAN, "exp_product.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_JAR_TO_PLUGIN_WIZ = create(PATH_WIZBAN, "jarToPlugin_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_EXTSTR_WIZ = create(PATH_WIZBAN, "extstr_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_XHTML_CONVERT_WIZ = create(PATH_WIZBAN, "xhtml_wiz.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_ORGANIZE_MANIFESTS = create(PATH_WIZBAN, "cleanmanifest_wiz.svg"); //$NON-NLS-1$
	/**
	 * View
	 */
	public static final ImageDescriptor DESC_ARGUMENT_TAB = create(PATH_VIEW, "variable_tab.svg"); //$NON-NLS-1$
	public static final ImageDescriptor DESC_TARGET_REPO = create(PATH_DVIEW, "memory_view.png"); //$NON-NLS-1$

	private static ImageDescriptor create(String prefix, String name) {
		return ImageDescriptor.createFromURL(makeImageURL(prefix, name));
	}

	public static Image get(String key) {
		if (PLUGIN_REGISTRY == null) {
			Display display = Display.getCurrent();
			if (display == null && PlatformUI.isWorkbenchRunning()) {
				display = PlatformUI.getWorkbench().getDisplay();
			}
			if (display == null) {
				return null;
			}

			initialize(display);
		}
		return PLUGIN_REGISTRY.get(key);
	}

	/* package */
	private static final void initialize(Display display) {
		PLUGIN_REGISTRY = new ImageRegistry(display);
		manage(display, IMG_ATT_CLASS_OBJ, DESC_ATT_CLASS_OBJ);
		manage(display, IMG_ATT_FILE_OBJ, DESC_ATT_FILE_OBJ);
		manage(display, IMG_ATT_IMPL_OBJ, DESC_ATT_IMPL_OBJ);
		manage(display, IMG_ATT_REQ_OBJ, DESC_ATT_REQ_OBJ);
		manage(display, IMG_ATT_ID_OBJ, DESC_ATT_ID_OBJ);
		manage(display, IMG_ATT_STRING_OBJ, DESC_ATT_STRING_OBJ);
		manage(display, IMG_ATT_BOOLEAN_OBJ, DESC_ATT_BOOLEAN_OBJ);
		manage(display, IMG_ATTRIBUTE_OBJ, DESC_ATTRIBUTE_OBJ);
		manage(display, IMG_GENERIC_XML_OBJ, DESC_GENERIC_XML_OBJ);
		manage(display, OBJ_DESC_GENERATE_CLASS, DESC_GENERATE_CLASS);
		manage(display, OBJ_DESC_GENERATE_INTERFACE, DESC_GENERATE_INTERFACE);
		manage(display, OBJ_DESC_PACKAGE, DESC_PACKAGE_OBJ);
		manage(display, OBJ_DESC_BUNDLE, DESC_BUNDLE_OBJ);
	}

	private static URL makeImageURL(String prefix, String name) {
		String path = "$nl$/" + prefix + name; //$NON-NLS-1$
		return FileLocator.find(PDEPlugin.getDefault().getBundle(), IPath.fromOSString(path), null);
	}

	public static Image manage(Display display, String key, ImageDescriptor desc) {
		Image image = desc.createImage(display);
		PLUGIN_REGISTRY.put(key, image);
		return image;
	}
}
