head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	ToStringBuilder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ToStringで出力される文字列を生成するクラス(WEB3ToStringBuilder.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/06 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.tradingpower.util;

/**
 * ToStringで出力される文字列を生成するクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class ToStringBuilder
{
    
    private final Object object;
    
    private final ToStringStyle style;
    
    private final StringBuffer buffer;
    
    protected ToStringBuilder(Object l_object, ToStringStyle l_style, StringBuffer l_buffer)
    {
        this.object = l_object;
        this.style = l_style;
        this.buffer = l_buffer;
        getStyle().appendStart(getStringBuffer(), getObject());
    }
    
    public ToStringBuilder append(String l_strFieldName, boolean l_blnValue)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_blnValue);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, boolean[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, byte l_bytValue)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_bytValue);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, byte[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, char l_chrValue)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_chrValue);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, char[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, double l_dblValue)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_dblValue);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, double[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, float l_fltValue)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_fltValue);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, float[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, int l_intValue)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_intValue);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, int[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, long l_lngValue)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_lngValue);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, long[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, Object l_object)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_object);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, Object[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, short l_shtValue)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_shtValue);
        return this;
    }

    public ToStringBuilder append(String l_strFieldName, short[] l_array)
    {
        getStyle().append(getStringBuffer(), l_strFieldName, l_array);
        return this;
    }

    public ToStringBuilder appendSuper(String l_strSuper)
    {
        getStyle().appendSuper(getStringBuffer(), l_strSuper);
        return this;
    }

    public String toString()
    {
        getStyle().appendEnd(getStringBuffer());
        return buffer.toString();
    }

    protected final Object getObject()
    {
        return object;
    }

    protected final StringBuffer getStringBuffer()
    {
        return buffer;
    }

    protected final ToStringStyle getStyle()
    {
        return style;
    }

}
@
