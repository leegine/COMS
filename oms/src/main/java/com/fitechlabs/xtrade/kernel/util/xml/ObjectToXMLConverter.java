// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectToXMLConverter.java

package com.fitechlabs.xtrade.kernel.util.xml;

import com.fitechlabs.dbind.Enum;
import com.fitechlabs.xtrade.kernel.enum.Enumerated;
import com.fitechlabs.xtrade.kernel.util.ISO8601Format;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.fitechlabs.xtrade.kernel.util.xml:
//            QuotedXML

public final class ObjectToXMLConverter
{
    private static class TestClass
    {

        public static String TAGNAME = "message";
        public static String PTYPE = "test_class";
        public char a;
        public byte b;
        public short c;
        public int d;
        public long e;
        public float f;
        public double g;
        public boolean h;
        public Character A;
        public Byte B;
        public Short C;
        public Integer D;
        public Long E;
        public Float F;
        public Double G;
        public Boolean H;
        public Date I;
        public java.sql.Date J;
        public Timestamp K;
        public String S;
        public char aa[];
        public byte bb[];
        public short cc[];
        public int dd[];
        public long ee[];
        public float ff[];
        public double gg[];
        public boolean hh[];
        public Character AA[];
        public Byte BB[];
        public Short CC[];
        public Integer DD[];
        public Long EE[];
        public Float FF[];
        public Double GG[];
        public Boolean HH[];
        public Date II[];
        public java.sql.Date JJ[];
        public Timestamp KK[];
        public String SS[];
        public List L;
        public Set M;
        public Collection N;
        public Object O;
        public List LL[];
        public Set MM[];
        public Collection NN[];
        public Object OO[];


        TestClass()
        {
        }

        TestClass(int d, float f, Object O, Object OO[], List L, String S, Date I)
        {
            a = (char)d;
            b = (byte)d;
            c = (short)d;
            this.d = d;
            e = d;
            this.f = f;
            g = g;
            h = d != 0;
            this.I = I;
            J = new java.sql.Date(I.getTime());
            K = new Timestamp(I.getTime());
            this.L = L;
            M = new HashSet(L);
            N = L;
            this.O = O;
            this.OO = OO;
            this.S = S;
            aa = (new char[] {
                a, (char)(a + 1)
            });
            bb = (new byte[] {
                b, (byte)(b + 1)
            });
            cc = (new short[] {
                c, (short)(c + 1)
            });
            dd = (new int[] {
                this.d, this.d + 1
            });
            ee = (new long[] {
                e, e + 1L
            });
            ff = (new float[] {
                this.f, this.f + 1.0F
            });
            gg = (new double[] {
                g, g + 1.0D
            });
            hh = (new boolean[] {
                h, !h
            });
            AA = (new Character[] {
                A, A
            });
            BB = (new Byte[] {
                B, B
            });
            CC = (new Short[] {
                C, C
            });
            DD = (new Integer[] {
                D, D
            });
            EE = (new Long[] {
                E, E
            });
            FF = (new Float[] {
                F, F
            });
            GG = (new Double[] {
                G, G
            });
            HH = (new Boolean[] {
                H, H
            });
            II = (new Date[] {
                this.I, this.I
            });
            JJ = (new java.sql.Date[] {
                J, J
            });
            KK = (new Timestamp[] {
                K, K
            });
            LL = (new List[] {
                this.L, this.L
            });
            MM = (new Set[] {
                M, M
            });
            NN = (new Collection[] {
                N, N
            });
            SS = (new String[] {
                this.S, this.S
            });
        }
    }


    private ObjectToXMLConverter()
    {
    }

    public static String toXMLString(Object object)
        throws Exception
    {
        StringBuffer b = new StringBuffer();
        appendOutermostTag(b, object);
        return b.toString();
    }

    private static void appendOutermostTag(StringBuffer b, Object object)
        throws Exception
    {
        Class c = object.getClass();
        String tagname = getStaticStringField(c, "TAGNAME", "object");
        String ptype = getStaticStringField(c, "PTYPE", null);
        b.append("<");
        b.append(tagname);
        b.append(">");
        if(ptype != null)
        {
            b.append(lookupIndent(1));
            b.append("<p_type>");
            b.append(ptype);
            b.append("</p_type>");
        }
        appendContainedFields(b, object, 1);
        b.append("\n</");
        b.append(tagname);
        b.append(">");
    }

    private static String getStaticStringField(Class c, String name, String defaultValue)
    {
        Field f = c.getField(name);
        if(Modifier.isStatic(f.getModifiers()))
            return (String)f.get(c);
        break MISSING_BLOCK_LABEL_29;
        Exception ignored;
        ignored;
        return defaultValue;
    }

    private static void appendContainedFields(StringBuffer b, Object o, int indent)
        throws Exception
    {
        Field fields[] = o.getClass().getFields();
        for(int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            if(Modifier.isStatic(f.getModifiers()))
                continue;
            String tagname = f.getName();
            Class type = f.getType();
            if(type.isArray())
            {
                Object a = f.get(o);
                if(a == null)
                    continue;
                for(int j = 0; j < Array.getLength(a); j++)
                    appendTagNamed(b, Array.get(a, j), tagname, indent);

            } else
            {
                appendTagNamed(b, f.get(o), tagname, indent);
            }
        }

    }

    private static void appendTagNamed(StringBuffer b, Object o, String tagname, int indent)
        throws Exception
    {
        if(o == null)
            return;
        if(o instanceof Collection)
        {
            for(Iterator it = ((Collection)o).iterator(); it.hasNext(); appendTagNamed(b, it.next(), tagname, indent));
            return;
        }
        String indentString = lookupIndent(indent);
        b.append(indentString);
        b.append("<");
        b.append(tagname);
        b.append(">");
        Class c = o.getClass();
        if(basicTypes.contains(c))
        {
            if(charTypes.contains(c))
                b.append(charToString(o));
            else
            if(c == (java.lang.String.class))
                b.append(QuotedXML.quote((String)o));
            else
                b.append(o.toString());
        } else
        if(o instanceof Date)
            b.append(dateToString((Date)o));
        else
        if(o instanceof Enum)
        {
            Enum e = (Enum)o;
            b.append(e.stringValue());
        } else
        if(o instanceof Enumerated)
        {
            b.append(((Enumerated)o).stringValue());
        } else
        {
            String ptype = getStaticStringField(c, "PTYPE", null);
            if(ptype != null)
            {
                b.append(lookupIndent(indent + 1));
                b.append("<p_type>");
                b.append(ptype);
                b.append("</p_type>");
            }
            appendContainedFields(b, o, indent + 1);
            b.append(indentString);
        }
        b.append("</");
        b.append(tagname);
        b.append(">");
    }

    private static String charToString(Object cobj)
    {
        char c = ((Character)cobj).charValue();
        int i = 0xffff & c;
        if(i >= 127 || i < 20)
            return "\\u" + i;
        else
            return String.valueOf(c);
    }

    private static String dateToString(Date date)
    {
        if(!(date instanceof java.sql.Date))
            break MISSING_BLOCK_LABEL_28;
        ISO8601Format iso8601format = DATE_ONLY_FORMAT;
        JVM INSTR monitorenter ;
        return DATE_ONLY_FORMAT.format(date);
        Exception exception;
        exception;
        throw exception;
        iso8601format = DATE_TIME_FORMAT;
        JVM INSTR monitorenter ;
        return DATE_TIME_FORMAT.format(date);
        Exception exception1;
        exception1;
        throw exception1;
    }

    private static String lookupIndent(int indent)
    {
        if(indent >= indents.length)
            indent = indents.length - 1;
        return indents[indent];
    }

    public static void main(String arg[])
        throws Exception
    {
        List someList = new ArrayList();
        someList.add("list-it-1");
        someList.add("list-it-2");
        someList.add("list-it-3");
        someList.add("list-it-4");
        TestClass A = new TestClass(23, 45.6F, new TestClass(), new Object[] {
            "aaa", "bbb", "ccc", "ddd"
        }, someList, "SomeString", new Date());
        System.out.println("----------------------------------------");
        System.out.println(toXMLString(A));
        System.out.println("----------------------------------------");
    }

    static final ISO8601Format DATE_ONLY_FORMAT = new ISO8601Format(3, false);
    static final ISO8601Format DATE_TIME_FORMAT = new ISO8601Format(6, false);
    private static Set basicTypes;
    private static Set charTypes;
    private static String indents[];

    static 
    {
        basicTypes = new HashSet();
        charTypes = new HashSet();
        basicTypes.add(Character.TYPE);
        basicTypes.add(Byte.TYPE);
        basicTypes.add(Short.TYPE);
        basicTypes.add(Integer.TYPE);
        basicTypes.add(Long.TYPE);
        basicTypes.add(Float.TYPE);
        basicTypes.add(Double.TYPE);
        basicTypes.add(Boolean.TYPE);
        basicTypes.add(Void.TYPE);
        basicTypes.add(java.lang.Character.class);
        basicTypes.add(java.lang.Byte.class);
        basicTypes.add(java.lang.Short.class);
        basicTypes.add(java.lang.Integer.class);
        basicTypes.add(java.lang.Long.class);
        basicTypes.add(java.lang.Float.class);
        basicTypes.add(java.lang.Double.class);
        basicTypes.add(java.lang.Boolean.class);
        basicTypes.add(java.lang.String.class);
        basicTypes.add(java.math.BigDecimal.class);
        charTypes.add(Character.TYPE);
        charTypes.add(java.lang.Character.class);
        String longest = "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        indents = new String[longest.length()];
        for(int i = 0; i < indents.length; i++)
            indents[i] = longest.substring(0, i + 1);

    }
}
