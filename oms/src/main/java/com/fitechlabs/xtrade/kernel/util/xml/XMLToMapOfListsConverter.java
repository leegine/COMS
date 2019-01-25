// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMLToMapOfListsConverter.java

package com.fitechlabs.xtrade.kernel.util.xml;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class XMLToMapOfListsConverter
{
    private static class State
    {

        private String name;
        private Object text;
        private Map submap;
        private Object value;
        private State prev;








        private State(State prev)
        {
            this.prev = prev;
        }

        private State()
        {
        }


    }


    public XMLToMapOfListsConverter()
    {
    }

    public static Map toMapOfLists(String xmlString)
        throws Exception
    {
        java.io.Reader reader = new StringReader(xmlString);
        InputSource source = new InputSource(reader);
        return toMapOfLists(source);
    }

    public static Map toMapOfLists(File file)
        throws Exception
    {
        FileInputStream stream = new FileInputStream(file);
        InputSource source = new InputSource(stream);
        return toMapOfLists(source);
    }

    public static Map toMapOfLists(InputStream stream)
        throws Exception
    {
        InputSource source = new InputSource(stream);
        return toMapOfLists(source);
    }

    private static SAXParser newSAXParser()
        throws SAXException, ParserConfigurationException
    {
        if(theFactory != null)
            break MISSING_BLOCK_LABEL_184;
        Class class1 = com.fitechlabs.xtrade.kernel.util.xml.XMLToMapOfListsConverter.class;
        JVM INSTR monitorenter ;
        int i;
        if(theFactory != null)
            break MISSING_BLOCK_LABEL_172;
        i = 0;
_L1:
        if(i >= parserNames.length)
            break MISSING_BLOCK_LABEL_123;
        SAXParser p;
        Class c = Class.forName(parserNames[i]);
        SAXParserFactory f = (SAXParserFactory)c.newInstance();
        p = f.newSAXParser();
        theFactory = f;
        return p;
        Throwable e;
        e;
        System.err.println("XMLToMapOfListsConverter: " + parserNames[i] + " failed to load, " + e);
        i++;
          goto _L1
        theFactory = SAXParserFactory.newInstance();
        if(theFactory == null)
            throw new RuntimeException("No parser factory.");
        System.out.println("XMLToMapOfListsConverter using SAXParserFactory class " + theFactory);
        class1;
        JVM INSTR monitorexit ;
        break MISSING_BLOCK_LABEL_184;
        Exception exception;
        exception;
        throw exception;
        return theFactory.newSAXParser();
    }

    public static Map toMapOfLists(InputSource xml)
        throws SAXException, ParserConfigurationException, IOException
    {
        final State outermostTag = new State();
        DefaultHandler handler = new DefaultHandler() {

            public void startElement(String namespaceURI, String sName, String qName, Attributes attrs)
                throws SAXException
            {
                state = new State(state);
                String eName = sName;
                if("".equals(eName))
                    eName = qName;
                state.name = eName;
                if(attrs != null)
                {
                    int n = attrs.getLength();
                    if(n > 0)
                    {
                        state.submap = new HashMap();
                        for(int i = 0; i < n; i++)
                        {
                            String aName = attrs.getLocalName(i);
                            if("".equals(aName))
                                aName = attrs.getQName(i);
                            String aValue = attrs.getValue(i);
                            List list = new ArrayList();
                            list.add(aValue);
                            state.submap.put(aName, list);
                        }

                    }
                }
            }

            public void endElement(String namespaceURI, String sName, String qName)
                throws SAXException
            {
                State prior = state;
                state = state.prev;
                String string_value = null;
                if(prior.text != null)
                {
                    string_value = prior.text.toString().trim();
                    if(string_value.length() <= 0)
                        string_value = null;
                }
                if(prior.submap != null && string_value != null)
                    throw new SAXException("element '" + prior.name + "' has both attributes and subelements.");
                Object value;
                if(prior.submap != null)
                    value = prior.submap;
                else
                if(string_value != null)
                    value = string_value;
                else
                    value = null;
                if(state.submap == null)
                    state.submap = new HashMap();
                Object saved = state.submap.get(prior.name);
                if(saved == null)
                {
                    List list = new ArrayList();
                    list.add(value);
                    state.submap.put(prior.name, list);
                } else
                {
                    List list = (List)saved;
                    list.add(value);
                }
            }

            public void characters(char buf[], int offset, int len)
                throws SAXException
            {
                if(len > 0)
                {
                    String s = new String(buf, offset, len);
                    if(state.text == null)
                    {
                        if(s.trim().length() > 0)
                            state.text = s;
                    } else
                    {
                        if(state.text instanceof String)
                            state.text = new StringBuffer((String)state.text);
                        ((StringBuffer)state.text).append(s);
                    }
                }
            }

            State state;

            
                throws SAXException
            {
                state = this.outermostTag;
            }
        }
;
        SAXParser saxParser = newSAXParser();
        saxParser.parse(xml, handler);
        return outermostTag.submap;
    }

    public static String mapOfListsToString(Map map)
    {
        StringBuffer b = new StringBuffer("M{");
        Iterator it = map.entrySet().iterator();
        do
        {
            if(!it.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
            b.append("'" + entry.getKey() + "':");
            Object item = entry.getValue();
            if(item instanceof List)
                b.append(listOfMapsToString((List)entry.getValue()));
            else
                b.append(String.valueOf(item));
            if(it.hasNext())
                b.append(";");
        } while(true);
        b.append("}");
        return b.toString();
    }

    private static String listOfMapsToString(List list)
    {
        StringBuffer b = new StringBuffer("L(");
        Iterator it = list.iterator();
        do
        {
            if(!it.hasNext())
                break;
            Object item = it.next();
            if(item == null)
                b.append("null");
            else
            if(item instanceof Map)
                b.append(mapOfListsToString((Map)item));
            else
                b.append("'" + item + "'");
            if(it.hasNext())
                b.append(",");
        } while(true);
        b.append(")");
        return b.toString();
    }

    private static SAXParserFactory theFactory;
    private static final String parserNames[] = {
        "org.apache.crimson.jaxp.SAXParserFactoryImpl", "org.apache.xerces.jaxp.SAXParserFactoryImpl"
    };
    private static final List EMPTY_LIST = new ArrayList(0);

}
