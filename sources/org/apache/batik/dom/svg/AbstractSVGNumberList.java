/*

 ============================================================================
                   The Apache Software License, Version 1.1
 ============================================================================

 Copyright (C) 1999-2003 The Apache Software Foundation. All rights reserved.

 Redistribution and use in source and binary forms, with or without modifica-
 tion, are permitted provided that the following conditions are met:

 1. Redistributions of  source code must  retain the above copyright  notice,
    this list of conditions and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 3. The end-user documentation included with the redistribution, if any, must
    include  the following  acknowledgment:  "This product includes  software
    developed  by the  Apache Software Foundation  (http://www.apache.org/)."
    Alternately, this  acknowledgment may  appear in the software itself,  if
    and wherever such third-party acknowledgments normally appear.

 4. The names "Batik" and  "Apache Software Foundation" must  not  be
    used to  endorse or promote  products derived from  this software without
    prior written permission. For written permission, please contact
    apache@apache.org.

 5. Products  derived from this software may not  be called "Apache", nor may
    "Apache" appear  in their name,  without prior written permission  of the
    Apache Software Foundation.

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 FITNESS  FOR A PARTICULAR  PURPOSE ARE  DISCLAIMED.  IN NO  EVENT SHALL  THE
 APACHE SOFTWARE  FOUNDATION  OR ITS CONTRIBUTORS  BE LIABLE FOR  ANY DIRECT,
 INDIRECT, INCIDENTAL, SPECIAL,  EXEMPLARY, OR CONSEQUENTIAL  DAMAGES (INCLU-
 DING, BUT NOT LIMITED TO, PROCUREMENT  OF SUBSTITUTE GOODS OR SERVICES; LOSS
 OF USE, DATA, OR  PROFITS; OR BUSINESS  INTERRUPTION)  HOWEVER CAUSED AND ON
 ANY  THEORY OF LIABILITY,  WHETHER  IN CONTRACT,  STRICT LIABILITY,  OR TORT
 (INCLUDING  NEGLIGENCE OR  OTHERWISE) ARISING IN  ANY WAY OUT OF THE  USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 This software  consists of voluntary contributions made  by many individuals
 on  behalf of the Apache Software  Foundation. For more  information on the
 Apache Software Foundation, please see <http://www.apache.org/>.

*/

package org.apache.batik.dom.svg;

import org.apache.batik.parser.NumberListHandler;
import org.apache.batik.parser.NumberListParser;
import org.apache.batik.parser.ParseException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGException;
import org.w3c.dom.svg.SVGNumber;
import org.w3c.dom.svg.SVGNumberList;


/**
 * This class is the implementation of
 * <code>SVGNumberList</code>.
 *
 * @author  tonny@kiyut.com
 */
public abstract class AbstractSVGNumberList extends AbstractSVGList implements SVGNumberList {
    
    /**
     * Separator for a length list.
     */
    public final static String SVG_NUMBER_LIST_SEPARATOR
        =" ";

    /**
     * Return the separator between values in the list.
     */
    protected String getItemSeparator(){
        return SVG_NUMBER_LIST_SEPARATOR;
    }
    
    /**
     * Create an SVGException when the checkItemType fails.
     *
     * @return SVGException
     */
    protected abstract SVGException createSVGException(short type,
                                                       String key,
                                                       Object[] args);

    /**
     * return the element owning this SVGNumberList.
     */
    protected abstract Element getElement();
    
    /**
     * Creates a new SVGNumberList.
     */
    protected AbstractSVGNumberList() {
        super();
    }

    /**
     */
    public SVGNumber initialize ( SVGNumber newItem )
        throws DOMException, SVGException {

        return (SVGNumber)initializeImpl(newItem);
    }

    /**
     */
    public SVGNumber getItem ( int index )
        throws DOMException {

        return (SVGNumber)getItemImpl(index);
    }
    
    /**
     */
    public SVGNumber insertItemBefore ( SVGNumber newItem, int index )
        throws DOMException, SVGException {

        return (SVGNumber)insertItemBeforeImpl(newItem,index);
    }

    /**
     */
    public SVGNumber replaceItem ( SVGNumber newItem, int index )
        throws DOMException, SVGException {

        return (SVGNumber)replaceItemImpl(newItem,index);
    }

    /**
     */
    public SVGNumber removeItem ( int index )
        throws DOMException {

        return (SVGNumber)removeItemImpl(index);
    }

    /**
     */
    public SVGNumber appendItem ( SVGNumber newItem )
        throws DOMException, SVGException {

        return (SVGNumber) appendItemImpl(newItem);
    }

    /**
     */
    protected SVGItem createSVGItem(Object newItem){
        
        SVGNumber l = (SVGNumber)newItem;

        return new SVGNumberItem(l.getValue());
    }
    
    /**
     * Parse the attribute associated with this SVGNumberList.
     *
     * @param value attribute value
     * @param handler list handler
     */
    protected void doParse(String value, ListHandler handler)
        throws ParseException{

        NumberListParser NumberListParser = new NumberListParser();
        
        NumberListBuilder builder = new NumberListBuilder(handler);
        
        NumberListParser.setNumberListHandler(builder);
        NumberListParser.parse(value);
        
    }
    
    /**
     * Check if the item is an SVGNumber
     */
    protected void checkItemType(Object newItem)
        throws SVGException {
        if ( !( newItem instanceof SVGNumber ) ){
            createSVGException(SVGException.SVG_WRONG_TYPE_ERR,
                               "expected SVGNumber",
                               null);
        }
    }
    
    /**
     * Representation of the item SVGNumber.
     */
    protected class SVGNumberItem 
        extends AbstractSVGNumber 
        implements SVGItem {

        /**
         * Default Constructor.
         */
        public SVGNumberItem(float value){
            super();
            this.value = value;
        }
        
        public String getValueAsString(){
            return Float.toString(value);
        }

        /**
         * SVGNumberList this item belongs to.
         */
        protected AbstractSVGList parentList;

        /**
         * Associates an item to an SVGXXXList
         *
         * @param list list the item belongs to.
         */
        public void setParent(AbstractSVGList list){
            parentList = list;
        }

        /**
         * Return the list the item belongs to.
         *
         * @return list the item belongs to. This
         *   could be if the item belongs to no list.
         */
        public AbstractSVGList getParent(){
            return parentList;
        }

        /**
         * When the SVGLength changes, notify
         * its parent.
         */
        protected void reset(){
            if ( parentList != null ){
                parentList.itemChanged();
            }
        }
        
    }
    
    /**
     * Helper class to interface the <code>NumberListParser</code>
     * and the <code>NumberHandler</code>
     */
    protected class NumberListBuilder
        implements NumberListHandler {

        /**
         * list handler.
         */
        protected ListHandler listHandler;

        //current value being parsed
        protected float currentValue;
                
        /**
         */
        public NumberListBuilder(ListHandler listHandler){
            this.listHandler = listHandler;
        }

        /**
         */
        public void startNumberList() 
            throws ParseException{

            listHandler.startList();
        }
        /**
         * Implements {@link org.apache.batik.parser.NumberHandler#startNumber()}.
         */
        public void startNumber() throws ParseException {
            currentValue = 0.0f;
        }

        /**
         * Implements {@link org.apache.batik.parser.NumberHandler#numberValue(float)}.
         */
        public void numberValue(float v) throws ParseException {
            currentValue = v;
        }
        
        /**
         * Implements {@link org.apache.batik.parser.NumberHandler#endNumber()}.
         */
        public void endNumber() throws ParseException {
            listHandler.item(new SVGNumberItem(currentValue));
        }
        
        /**
         */
        public void endNumberList() 
            throws ParseException {
            listHandler.endList();
        }
    }
}
