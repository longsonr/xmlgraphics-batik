/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.dom.svg;

import org.apache.batik.dom.AbstractDocument;

import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGAnimatedEnumeration;
import org.w3c.dom.svg.SVGAnimatedLength;
import org.w3c.dom.svg.SVGMaskElement;

/**
 * This class implements {@link org.w3c.dom.svg.SVGMaskElement}.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class SVGOMMaskElement
    extends    SVGGraphicsElement
    implements SVGMaskElement {

    /**
     * Creates a new SVGOMMaskElement object.
     */
    protected SVGOMMaskElement() {
    }

    /**
     * Creates a new SVGOMMaskElement object.
     * @param prefix The namespace prefix.
     * @param owner The owner document.
     */
    public SVGOMMaskElement(String prefix, AbstractDocument owner) {
        super(prefix, owner);
    }

    /**
     * <b>DOM</b>: Implements {@link org.w3c.dom.Node#getLocalName()}.
     */
    public String getLocalName() {
        return SVG_MASK_TAG;
    }

    /**
     * <b>DOM</b>: Implements {@link SVGMaskElement#getMaskUnits()}.
     */
    public SVGAnimatedEnumeration getMaskUnits() {
        throw new RuntimeException(" !!! TODO: getMaskUnits");
    }

    /**
     * <b>DOM</b>: Implements {@link
     * org.w3c.dom.svg.SVGPatternElement#getMaskContentUnits()}.
     */
    public SVGAnimatedEnumeration getMaskContentUnits() {
        throw new RuntimeException(" !!! TODO: getMaskContentUnits()");
    }

    /**
     * <b>DOM</b>: Implements {@link SVGMaskElement#getX()}.
     */
    public SVGAnimatedLength getX() {
        throw new RuntimeException(" !!! TODO: getX()");
    }

    /**
     * <b>DOM</b>: Implements {@link SVGMaskElement#getY()}.
     */
    public SVGAnimatedLength getY() {
        throw new RuntimeException(" !!! TODO: getY()");
    }

    /**
     * <b>DOM</b>: Implements {@link SVGMaskElement#getWidth()}.
     */
    public SVGAnimatedLength getWidth() {
        throw new RuntimeException(" !!! TODO: getWidth()");
    }

    /**
     * <b>DOM</b>: Implements {@link SVGMaskElement#getHeight()}.
     */
    public SVGAnimatedLength getHeight() {
        throw new RuntimeException(" !!! TODO: getHeight()");
    }

    /**
     * Returns a new uninitialized instance of this object's class.
     */
    protected Node newNode() {
        return new SVGOMMaskElement();
    }
}
