// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMLToObjectConverter.java

package com.fitechlabs.xtrade.kernel.util.xml;

import com.fitechlabs.xtrade.kernel.boot.MessageClassRegistry;
import com.fitechlabs.xtrade.kernel.enum.EnumeratedManager;
import com.fitechlabs.xtrade.kernel.util.ObjectPrettyPrinter;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

// Referenced classes of package com.fitechlabs.xtrade.kernel.util.xml:
//            XmlConversionException, XMLToMapOfListsConverter, TagnameResolver, ObjectToXMLConverter

public final class XMLToObjectConverter
{

    public XMLToObjectConverter(TagnameResolver resolver)
    {
        this.resolver = resolver;
    }

    public static Object toObject(String xmlString)
        throws Exception
    {
        return defaultConverter.xmlToObject(xmlString);
    }

    public static Object toObject(File file)
        throws Exception
    {
        return defaultConverter.xmlToObject(file);
    }

    public static Object toObject(InputStream stream)
        throws Exception
    {
        return defaultConverter.xmlToObject(stream);
    }

    public Object xmlToObject(String xmlString)
        throws Exception
    {
        Map mapOfLists = XMLToMapOfListsConverter.toMapOfLists(xmlString);
        return mapOfListsToObject(mapOfLists);
    }

    public Object xmlToObject(File file)
        throws Exception
    {
        Map mapOfLists = XMLToMapOfListsConverter.toMapOfLists(file);
        return mapOfListsToObject(mapOfLists);
    }

    public Object xmlToObject(InputStream stream)
        throws Exception
    {
        Map mapOfLists = XMLToMapOfListsConverter.toMapOfLists(stream);
        return mapOfListsToObject(mapOfLists);
    }

    public static boolean compareXml(String xmlA, String xmlB)
        throws Exception
    {
        Map mapA = XMLToMapOfListsConverter.toMapOfLists(xmlA);
        Map mapB = XMLToMapOfListsConverter.toMapOfLists(xmlB);
        return mapA == null && mapB == null || mapA.equals(mapB);
    }

    public static Document toDocument(String xml)
        throws Exception
    {
        return getBuilder().parse(new InputSource(new StringReader(xml)));
    }

    public static Document toDocument(File xmlFile)
        throws Exception
    {
        return getBuilder().parse(xmlFile);
    }

    public static Document toDocument(InputStream stream)
        throws Exception
    {
        return getBuilder().parse(stream);
    }

    static DocumentBuilderFactory getFactory()
    {
        if(theFactory == null)
            synchronized(com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class)
            {
                if(theFactory == null)
                    theFactory = DocumentBuilderFactory.newInstance();
            }
        return theFactory;
    }

    static DocumentBuilder getBuilder()
        throws ParserConfigurationException
    {
        return getFactory().newDocumentBuilder();
    }

    public Object toObject(Document document)
        throws Exception
    {
        Map map = toMapOfLists(document);
        return mapOfListsToObject(map);
    }

    private Object mapOfListsToObject(Map mapOfLists)
        throws Exception
    {
        java.util.Map.Entry entry = (java.util.Map.Entry)mapOfLists.entrySet().iterator().next();
        String tagname = (String)entry.getKey();
        List list = (List)entry.getValue();
        Class messageBaseClass = resolver.getClass(tagname, null);
        if(messageBaseClass == null)
            throw new XmlConversionException("No base class registered for top-level message tagname='" + tagname + "'");
        else
            return mapOrStringToObject(tagname, list.get(0), messageBaseClass);
    }

    private Object mapOrStringToObject(String tagname, Object mapOrString, Class expectedClass)
        throws Exception
    {
        if(mapOrString instanceof Map)
            return mapToObject(tagname, (Map)mapOrString, expectedClass);
        else
            return convertStringToScalar(tagname, (String)mapOrString, expectedClass);
    }

    private Object mapToObject(String tagname, Map map, Class expectedClass)
        throws Exception
    {
        List pTypeList = (List)map.get("p_type");
        Object instance = createInstanceFromPType(tagname, pTypeList, expectedClass);
        Field fields[] = getCachedFields(instance.getClass());
        for(int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            if(Modifier.isStatic(f.getModifiers()))
                continue;
            String fname = f.getName();
            List valueList = (List)map.get(fname);
            if(valueList != null)
                f.set(instance, listToObject(fname, valueList, f.getType()));
        }

        return instance;
    }

    private static Field[] getCachedFields(Class c)
        throws Exception
    {
        Field f[] = (Field[])cachedFields.get(c);
        if(f == null)
            cachedFields.put(c, f = c.getFields());
        return f;
    }

    private Object listToObject(String fieldName, List stringsOrMaps, Class expectedType)
        throws Exception
    {
        if(expectedType.isArray())
            return listToObjectArray(fieldName, stringsOrMaps, expectedType.getComponentType());
        if((java.util.Collection.class).isAssignableFrom(expectedType))
            return arrayToCollection(listToObjectArray(fieldName, stringsOrMaps, java.lang.String.class), expectedType);
        Object item;
        switch(stringsOrMaps.size())
        {
        default:
            throw new XmlConversionException("More than one tag value found for non-array field named " + fieldName);

        case 0: // '\0'
            throw new XmlConversionException("Internal filling error - empty list found while filling field " + fieldName);

        case 1: // '\001'
            item = stringsOrMaps.get(0);
            break;
        }
        return mapOrStringToObject(fieldName, item, expectedType);
    }

    private static Collection arrayToCollection(Object array, Class collectionType)
        throws Exception
    {
        int n = Array.getLength(array);
        Collection c;
        if((java.util.Set.class).isAssignableFrom(collectionType))
            c = new HashSet(n * 2);
        else
            c = new ArrayList(n);
        for(int i = 0; i < n; i++)
            c.add(Array.get(array, i));

        return c;
    }

    private Object listToObjectArray(String fieldName, List stringsOrMaps, Class componentType)
        throws Exception
    {
        int n = stringsOrMaps.size();
        if(n == 0)
            return null;
        Object array = Array.newInstance(componentType, n);
        for(int i = 0; i < n; i++)
            Array.set(array, i, mapOrStringToObject(fieldName, stringsOrMaps.get(i), componentType));

        return array;
    }

    private Object createInstanceFromPType(String fieldname, List p_type_list, Class expectedClass)
        throws Exception
    {
        if(p_type_list == null)
            return expectedClass.newInstance();
        Object p_type_value;
        switch(p_type_list.size())
        {
        case 0: // '\0'
            return expectedClass.newInstance();

        default:
            throw new XmlConversionException("field " + fieldname + " - p_type must be at most one sub-element or attribute, " + p_type_list.size() + " were found.");

        case 1: // '\001'
            p_type_value = p_type_list.get(0);
            break;
        }
        if(!(p_type_value instanceof String))
            throw new XmlConversionException("Field " + fieldname + " - p_type must be at most one String sub-element or attribute, a tag containing sub-elements was found.");
        String p_type = (String)p_type_value;
        Class actualClass;
        try
        {
            String tagname = (String)expectedClass.getField("TAGNAME").get(null);
            actualClass = resolver.getClass(tagname, p_type);
            if(actualClass == null)
                throw new XmlConversionException("No registered class found for fieldname=" + fieldname + ", tagname=" + tagname + ", p_type=" + p_type);
            if(!expectedClass.isAssignableFrom(actualClass))
                throw new XmlConversionException("Registered " + actualClass + " for fieldname=" + fieldname + ", tagname=" + tagname + ", p_type=" + p_type + " not compatible with expected " + expectedClass);
        }
        catch(NoSuchFieldException ignore)
        {
            actualClass = expectedClass;
        }
        catch(ClassCastException cce)
        {
            throw new XmlConversionException("Field " + fieldname + " - base type " + expectedClass + "'s TAGNAME variable must be a static String field");
        }
        return actualClass.newInstance();
    }

    private static Object convertStringToScalar(String tagname, String value, Class scalarType)
        throws Exception
    {
        Method m;
        if(value == null)
            return valueOnEmptyTag(scalarType);
        m = (Method)conversionMethods.get(scalarType);
        if(m != null)
            break MISSING_BLOCK_LABEL_229;
        if((com.fitechlabs.xtrade.kernel.enum.Enumerated.class).isAssignableFrom(scalarType))
            return EnumeratedManager.getInstance().valueFromString(scalarType, value);
        if(!(com.fitechlabs.dbind.Enum.class).isAssignableFrom(scalarType))
            break MISSING_BLOCK_LABEL_193;
        Method em = scalarType.getMethod("valueOf", new Class[] {
            java.lang.String.class
        });
        return em.invoke(scalarType, new Object[] {
            value
        });
        InvocationTargetException ite;
        ite;
        throw new XmlConversionException("Tag " + tagname + ", String to " + scalarType + " conversion failed: " + ite.getTargetException());
        throw new XmlConversionException("Tag " + tagname + ", no conversion found to convert String to " + scalarType);
        return m.invoke(com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class, new String[] {
            value
        });
        ite;
        Throwable t = ite.getTargetException();
        if(t instanceof Exception)
            throw (Exception)t;
        else
            throw new XmlConversionException("Tag " + tagname + ", unknown Throwable caught during conversion - " + t);
    }

    private static Object valueOnEmptyTag(Class scalarType)
        throws Exception
    {
        Object obj = emptyTagIndicators.get(scalarType);
        if(obj != null)
            return obj;
        else
            return scalarType.newInstance();
    }

    public static Character characterValueOf(String value)
        throws Exception
    {
        if(value.startsWith("\\u"))
            return new Character((char)(int)Long.valueOf(value.substring(2)).longValue());
        if(value.length() != 1)
        {
            Character c = (Character)characterSequences.get(value);
            throw new ParseException("Cannot convert String to Character: '" + value + "'", 0);
        } else
        {
            return new Character(value.charAt(0));
        }
    }

    public static String stringValueOf(String value)
        throws ParseException
    {
        return value;
    }

    public static Timestamp timestampValueOf(String value)
        throws ParseException
    {
        return new Timestamp(stringToDate(value, false).getTime());
    }

    public static Time timeValueOf(String value)
        throws ParseException
    {
        return new Time(stringToDate(value, false).getTime());
    }

    public static Date dateValueOf(String value)
        throws ParseException
    {
        return new Date(stringToDate(value, true).getTime());
    }

    public static java.util.Date utilDateValueOf(String value)
        throws ParseException
    {
        return stringToDate(value, true);
    }

    public static BigDecimal bigDecimalValueOf(String value)
        throws ParseException
    {
        return new BigDecimal(value);
    }

    private static java.util.Date stringToDate(String string, boolean date_only)
        throws ParseException
    {
        java.util.Date date;
        if(date_only)
            synchronized(ObjectToXMLConverter.DATE_ONLY_FORMAT)
            {
                date = ObjectToXMLConverter.DATE_ONLY_FORMAT.parse(string);
            }
        else
            synchronized(ObjectToXMLConverter.DATE_TIME_FORMAT)
            {
                date = ObjectToXMLConverter.DATE_TIME_FORMAT.parse(string);
            }
        if(date == null)
            throw new ParseException("bad date format: " + string, 0);
        else
            return date;
    }

    public static Map toMapOfLists(Node docOrElement)
        throws Exception
    {
        Object o = contentsAsMapOrString(docOrElement);
        if(!(o instanceof Map))
            throw new RuntimeException("XMLToObjectConverter.toMapOfLists(): document contents were not converted to a Map as expected.");
        else
            return (Map)o;
    }

    private static Object contentsAsMapOrString(Node element)
        throws Exception
    {
        NamedNodeMap attr = element.getAttributes();
        int attrCount = attr == null ? 0 : attr.getLength();
        boolean hasChildren = element.hasChildNodes();
        if(attrCount <= 0 && !hasChildren)
            return null;
        NodeList childNodes = null;
        int childCount = 0;
        if(hasChildren)
        {
            childNodes = element.getChildNodes();
            childCount = childNodes.getLength();
            if(attrCount <= 0 && childCount == 1)
            {
                Node onlyChild = childNodes.item(0);
                if(onlyChild.getNodeType() == 3)
                {
                    String s = onlyChild.getNodeValue().trim();
                    return "".equals(s) ? null : s;
                }
            }
        }
        Map map = new HashMap();
        for(int i = 0; i < attrCount; i++)
        {
            Node a = attr.item(i);
            String attrName = a.getNodeName();
            String attrValue = a.getNodeValue();
            includeInMap(map, attrName, attrValue);
        }

        for(int i = 0; i < childCount; i++)
        {
            Node child = childNodes.item(i);
            switch(child.getNodeType())
            {
            case 3: // '\003'
                String value = child.getNodeValue().trim();
                if(value != null && value.length() > 0)
                    throw new XmlConversionException("Node " + element.getNodeName() + " contains both text and non-text sub-nodes");
                break;

            case 1: // '\001'
                String childName = child.getNodeName();
                Object contents = contentsAsMapOrString(child);
                includeInMap(map, childName, contents);
                break;
            }
        }

        return map;
    }

    private static void includeInMap(Map map, String name, Object stringOrMap)
    {
        List list = (List)map.get(name);
        if(list == null)
            map.put(name, list = new ArrayList());
        list.add(stringOrMap);
    }

    public static void printNode(Node node)
        throws Exception
    {
        printNode(node, "\n", "", "\n");
    }

    private static void printNode(Node node, String indent, String prefix, String postfix)
        throws Exception
    {
        System.out.print(indent + prefix);
        System.out.print("type=" + typeName[node.getNodeType()] + "(" + node.getNodeType() + "), name=" + node.getNodeName() + ", value='" + ObjectPrettyPrinter.toEscapedString(node.getNodeValue()) + "'");
        indent = indent + "    ";
        NamedNodeMap attr = node.getAttributes();
        if(attr != null && attr.getLength() > 0)
        {
            System.out.print(indent + "{attr: ");
            for(int i = 0; i < attr.getLength(); i++)
                printNode(attr.item(i), indent + "    ", "[" + i + "]", ",");

            System.out.print("}");
        }
        if(node.hasChildNodes())
        {
            System.out.print(indent + "(children: ");
            NodeList children = node.getChildNodes();
            for(int i = 0; i < children.getLength(); i++)
                printNode(children.item(i), indent + "    ", "[" + i + "]", ",");

            System.out.print(indent + ")");
        }
        System.out.print(postfix);
    }

    public static String mapOfListsToString(Map map)
    {
        return XMLToMapOfListsConverter.mapOfListsToString(map);
    }

    TagnameResolver resolver;
    private static XMLToObjectConverter defaultConverter = new XMLToObjectConverter(new TagnameResolver() {

        public Class getClass(String contextTag, String tagName)
        {
            return MessageClassRegistry.getClass(contextTag, tagName);
        }

    }
);
    private static DocumentBuilderFactory theFactory = null;
    private static final String typeName[] = {
        "none", "Element", "Attr", "Text", "CDATA", "EntityRef", "Entity", "ProcInstr", "Comment", "Document", 
        "DocType", "DocFragment", "Notation"
    };
    private static final short NODE_TYPE_ELEMENT = 1;
    private static final short NODE_TYPE_ATTR = 2;
    private static final short NODE_TYPE_TEXT = 3;
    private static Map cachedFields = Collections.synchronizedMap(new HashMap());
    private static Map emptyTagIndicators;
    private static Map conversionMethods;
    private static Map characterSequences;

    static 
    {
        emptyTagIndicators = new HashMap(20);
        conversionMethods = new HashMap(20);
        characterSequences = new HashMap(10);
        try
        {
            Byte emptyByte = new Byte((byte)1);
            Short emptyShort = new Short((short)1);
            Integer emptyInteger = new Integer(1);
            Long emptyLong = new Long(1L);
            Float emptyFloat = new Float(1.0F);
            Double emptyDouble = new Double(1.0D);
            Character emptyCharacter = new Character('\001');
            java.util.Date emptyUtilDate = new java.util.Date(0L);
            Date emptyDate = new Date(0L);
            Time emptyTime = new Time(0L);
            Timestamp emptyTimestamp = new Timestamp(0L);
            emptyTagIndicators.put(Byte.TYPE, emptyByte);
            emptyTagIndicators.put(java.lang.Byte.class, emptyByte);
            emptyTagIndicators.put(Boolean.TYPE, Boolean.TRUE);
            emptyTagIndicators.put(java.lang.Boolean.class, Boolean.TRUE);
            emptyTagIndicators.put(Short.TYPE, emptyShort);
            emptyTagIndicators.put(java.lang.Short.class, emptyShort);
            emptyTagIndicators.put(Integer.TYPE, emptyInteger);
            emptyTagIndicators.put(java.lang.Integer.class, emptyInteger);
            emptyTagIndicators.put(Long.TYPE, emptyLong);
            emptyTagIndicators.put(java.lang.Long.class, emptyLong);
            emptyTagIndicators.put(Float.TYPE, emptyFloat);
            emptyTagIndicators.put(java.lang.Float.class, emptyFloat);
            emptyTagIndicators.put(Double.TYPE, emptyDouble);
            emptyTagIndicators.put(java.lang.Double.class, emptyDouble);
            emptyTagIndicators.put(Character.TYPE, emptyCharacter);
            emptyTagIndicators.put(java.lang.Character.class, emptyCharacter);
            emptyTagIndicators.put(java.util.Date.class, emptyUtilDate);
            emptyTagIndicators.put(java.sql.Timestamp.class, emptyTimestamp);
            emptyTagIndicators.put(java.sql.Time.class, emptyTime);
            emptyTagIndicators.put(java.sql.Date.class, emptyDate);
            emptyTagIndicators.put(java.lang.String.class, "");
            emptyTagIndicators.put(java.math.BigDecimal.class, new BigDecimal(0.0D));
            Class args[] = {
                java.lang.String.class
            };
            conversionMethods.put(Byte.TYPE, (java.lang.Byte.class).getMethod("valueOf", args));
            conversionMethods.put(java.lang.Byte.class, (java.lang.Byte.class).getMethod("valueOf", args));
            conversionMethods.put(Boolean.TYPE, (java.lang.Boolean.class).getMethod("valueOf", args));
            conversionMethods.put(java.lang.Boolean.class, (java.lang.Boolean.class).getMethod("valueOf", args));
            conversionMethods.put(Short.TYPE, (java.lang.Short.class).getMethod("valueOf", args));
            conversionMethods.put(java.lang.Short.class, (java.lang.Short.class).getMethod("valueOf", args));
            conversionMethods.put(Integer.TYPE, (java.lang.Integer.class).getMethod("valueOf", args));
            conversionMethods.put(java.lang.Integer.class, (java.lang.Integer.class).getMethod("valueOf", args));
            conversionMethods.put(Long.TYPE, (java.lang.Long.class).getMethod("valueOf", args));
            conversionMethods.put(java.lang.Long.class, (java.lang.Long.class).getMethod("valueOf", args));
            conversionMethods.put(Float.TYPE, (java.lang.Float.class).getMethod("valueOf", args));
            conversionMethods.put(java.lang.Float.class, (java.lang.Float.class).getMethod("valueOf", args));
            conversionMethods.put(Double.TYPE, (java.lang.Double.class).getMethod("valueOf", args));
            conversionMethods.put(java.lang.Double.class, (java.lang.Double.class).getMethod("valueOf", args));
            conversionMethods.put(Character.TYPE, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("characterValueOf", args));
            conversionMethods.put(java.lang.Character.class, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("characterValueOf", args));
            conversionMethods.put(java.util.Date.class, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("utilDateValueOf", args));
            conversionMethods.put(java.sql.Timestamp.class, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("timestampValueOf", args));
            conversionMethods.put(java.sql.Time.class, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("timeValueOf", args));
            conversionMethods.put(java.sql.Date.class, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("dateValueOf", args));
            conversionMethods.put(java.lang.String.class, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("stringValueOf", args));
            conversionMethods.put(java.lang.Object.class, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("stringValueOf", args));
            conversionMethods.put(java.math.BigDecimal.class, (com.fitechlabs.xtrade.kernel.util.xml.XMLToObjectConverter.class).getMethod("bigDecimalValueOf", args));
            characterSequences.put("\\r", new Character('\r'));
            characterSequences.put("\\n", new Character('\n'));
            characterSequences.put("\\0", new Character('\0'));
        }
        catch(Exception e)
        {
            throw new RuntimeException("Class loading failed - couldn't find conversion method " + e);
        }
    }
}
