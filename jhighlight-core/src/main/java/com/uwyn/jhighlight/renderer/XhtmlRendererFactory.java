/*
 * Copyright 2004-2006 Geert Bevin <gbevin[remove] at uwyn dot com>
 * Distributed under the terms of either:
 * - the common development and distribution license (CDDL), v1.0; or
 * - the GNU Lesser General Public License, v2.1 or later
 * $Id: XhtmlRendererFactory.java 3108 2006-03-13 18:03:00Z gbevin $
 */
package com.uwyn.jhighlight.renderer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.uwyn.jhighlight.renderer.impl.CppXhtmlRenderer;
import com.uwyn.jhighlight.renderer.impl.GroovyXhtmlRenderer;
import com.uwyn.jhighlight.renderer.impl.JavaXhtmlRenderer;
import com.uwyn.jhighlight.renderer.impl.NapileXhtmlRender;
import com.uwyn.jhighlight.renderer.impl.XmlXhtmlRenderer;

/**
 * Provides a single point of entry to instantiate Xhtml renderers.
 *
 * @author Geert Bevin (gbevin[remove] at uwyn dot com)
 * @version $Revision: 3108 $
 * @since 1.0
 */
public abstract class XhtmlRendererFactory
{
	public final static String GROOVY = "groovy";
	public final static String JAVA = "java";
	public final static String NS = "ns";
	public final static String NAPILE = "napile";
	public final static String BEANSHELL = "beanshell";
	public final static String BSH = "bsh";
	public final static String XML = "xml";
	public final static String XHTML = "xhtml";
	public final static String LZX = "lzx";
	public final static String HTML = "html";
	public final static String CPP = "cpp";
	public final static String CXX = "cxx";
	public final static String CPLUSPLUS = "c++";

	private final static Map<String, Class<? extends Renderer>> RENDERERS_CLASSNAMES = new HashMap<String, Class<? extends Renderer>>()
	{{
			put(GROOVY, GroovyXhtmlRenderer.class);
			put(JAVA, JavaXhtmlRenderer.class);
			put(NS, NapileXhtmlRender.class);
			put(NAPILE, NapileXhtmlRender.class);
			put(BEANSHELL, JavaXhtmlRenderer.class);
			put(BSH, JavaXhtmlRenderer.class);
			put(XML, XmlXhtmlRenderer.class);
			put(XHTML, XmlXhtmlRenderer.class);
			put(LZX, XmlXhtmlRenderer.class);
			put(HTML, XmlXhtmlRenderer.class);
			put(CPP, CppXhtmlRenderer.class);
			put(CXX, CppXhtmlRenderer.class);
			put(CPLUSPLUS, CppXhtmlRenderer.class);
		}};

	/**
	 * Instantiates an instance of a known <code>XhtmlRenderer</code> according to
	 * the type that's provided.
	 *
	 * @param type The type of renderer, look at the static variables of this
	 *             class to see which ones are supported.
	 * @return an instance of the <code>XhtmlRenderer</code> that corresponds to the type; or
	 *         <p><code>null</code> if the type wasn't known
	 * @since 1.0
	 */
	public static Renderer getRenderer(String type)
	{
		Class<? extends Renderer> clazz = RENDERERS_CLASSNAMES.get(type.toLowerCase());
		if(clazz == null)
			return null;
		try
		{
			return clazz.newInstance();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returned a set with all the supported XHTML renderer types.
	 *
	 * @return a <code>Set</code> with the supported XHTML renderer types as strings.
	 * @since 1.0
	 */
	public static Set<String> getSupportedTypes()
	{
		return Collections.unmodifiableSet(RENDERERS_CLASSNAMES.keySet());
	}
}
