head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	ToStringStyle.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ToStringのスタイルを保持するクラス(WEB3TPToStringStyle.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/06 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.tradingpower.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * ToStringのスタイルを保持するクラス<br>
 *
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class ToStringStyle
{

    /**
     * 内容表示の開始時に出力される文字列
     */
    private String contentStart = "[";
    /**
     * 内容表示の終了時に出力される文字列
     */
    private String contentEnd = "]\n";
    /**
     * フィールドの名前と要素の区切りに表示される文字列
     */
    private String fieldNameValueSeparator = "=";
    /**
     * フィールドの区切りに表示される文字列
     */
    private String fieldSeparator = ",";
    /**
     * 配列表示の開始時に出力される文字列
     */
    private String arrayContentsStart = "{";
    /**
     * 配列の要素の区切りに表示される文字列
     */
    private String arrayContentsSeparator = ",";
    /**
     * 配列表示の終了時に出力される文字列
     */
    private String arrayContentsEnd = "}";
    /**
     * <code>null</code>の場合に出力される文字列
     */
    private String nullText = "<null>";

    /**
     * コンストラクタ
     */
    public ToStringStyle()
    {
    }

    /**
     * StringBufferにbooleanの値を追加する
     *
     * @@param l_sbBuffer 出力先のStringBuffer
     * @@param l_strFieldName フィールド名
     * @@param l_blnValue 出力する値
     */
    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        boolean l_blnValue)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        l_sbBuffer.append(l_blnValue);
        appendFieldEnd(l_sbBuffer);
    }

    /**
     * StringBufferにbooleanの配列を追加する
     *
     * @@param l_sbBuffer 出力先のStringBuffer
     * @@param l_strFieldName フィールド名
     * @@param l_array 出力する値
     */
    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        boolean[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        byte l_bytValue)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        l_sbBuffer.append(l_bytValue);
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        byte[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        char l_chValue)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        l_sbBuffer.append(l_chValue);
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        char[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        double l_dblValue)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        l_sbBuffer.append(l_dblValue);
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        double[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        float l_fltValue)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        l_sbBuffer.append(l_fltValue);
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        float[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        int l_intValue)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        l_sbBuffer.append(l_intValue);
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        int[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        long l_lngValue)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        l_sbBuffer.append(l_lngValue);
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        long[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        Object l_object)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_object == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_object);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        Object[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        short l_shtValue)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        l_sbBuffer.append(l_shtValue);
        appendFieldEnd(l_sbBuffer);
    }

    public void append(
        StringBuffer l_sbBuffer,
        String l_strFieldName,
        short[] l_array)
    {
        appendFieldStart(l_sbBuffer, l_strFieldName);
        if (l_array == null)
        {
            l_sbBuffer.append(getNullText());
        } else
        {
            appendDetail(l_sbBuffer, l_array);
        }
        appendFieldEnd(l_sbBuffer);
    }

    public void appendStart(StringBuffer l_sbBuffer, Object l_object)
    {
        if (l_object != null)
        {
            l_sbBuffer.append(getShortClassName(l_object));
        }
        l_sbBuffer.append(getContentStart());
    }

    public void appendEnd(StringBuffer l_sbBuffer)
    {
        if (getFieldSeparator() != null)
        {
            removeLastFieldSeparator(l_sbBuffer);
        }
        l_sbBuffer.append(getContentEnd());
    }

    public void appendSuper(StringBuffer l_sbBuffer, String l_strSuperToString)
    {
        if (l_strSuperToString != null)
        {
            int l_intPos1 =
                l_strSuperToString.indexOf(contentStart)
                    + contentStart.length();
            int l_intPos2 = l_strSuperToString.lastIndexOf(contentEnd);
            if (l_intPos1 != l_intPos2 && l_intPos1 >= 0 && l_intPos2 >= 0)
            {
                String l_strData =
                    l_strSuperToString.substring(l_intPos1, l_intPos2);
                l_sbBuffer.append(l_strData);
                appendFieldEnd(l_sbBuffer);
            }
        }
    }

    protected final String getContentStart()
    {
        return contentStart;
    }

    protected final void setContentStart(String l_strContentStart)
    {
        contentStart = l_strContentStart;
    }

    protected final String getContentEnd()
    {
        return contentEnd;
    }

    protected final void setContentEnd(String l_strContentEnd)
    {
        contentEnd = l_strContentEnd;
    }

    protected final String getFieldNameValueSeparator()
    {
        return fieldNameValueSeparator;
    }

    protected final void setFieldNameValueSeparator(String l_strFieldNameValueSeparator)
    {
        fieldNameValueSeparator = l_strFieldNameValueSeparator;
    }

    protected final String getFieldSeparator()
    {
        return fieldSeparator;
    }

    protected final void setFieldSeparator(String l_strFieldNameSeparator)
    {
        fieldSeparator = l_strFieldNameSeparator;
    }

    protected final String getArrayContentsStart()
    {
        return arrayContentsStart;
    }

    protected final void setArrayContentsStart(String l_strArrayContentsSeparator)
    {
        arrayContentsStart = l_strArrayContentsSeparator;
    }

    protected final String getArrayContentsSeparator()
    {
        return arrayContentsSeparator;
    }

    protected final void setArrayContentsSeparator(String l_strArrayContentsSeparator)
    {
        arrayContentsSeparator = l_strArrayContentsSeparator;
    }

    protected final String getArrayContentsEnd()
    {
        return arrayContentsEnd;
    }

    protected final void setArrayContentsEnd(String l_strArrayContentsEnd)
    {
        arrayContentsEnd = l_strArrayContentsEnd;
    }

    protected final String getNullText()
    {
        return nullText;
    }

    protected final void setNullText(String l_strNullString)
    {
        nullText = l_strNullString;
    }

    private void appendFieldStart(
        StringBuffer l_sbBuffer,
        String l_strFieldName)
    {
        if (l_strFieldName != null)
        {
            l_sbBuffer.append(l_strFieldName);
            l_sbBuffer.append(getFieldNameValueSeparator());
        }
    }

    private void appendFieldEnd(StringBuffer l_sbBuffer)
    {
        l_sbBuffer.append(getFieldSeparator());
    }

    private void appendDetail(StringBuffer l_sbBuffer, boolean[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            l_sbBuffer.append(l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, byte[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            l_sbBuffer.append(l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, char[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            l_sbBuffer.append(l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, double[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            l_sbBuffer.append(l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, float[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            l_sbBuffer.append(l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, int[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            l_sbBuffer.append(l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, Map l_map)
    {
        l_sbBuffer.append(getArrayContentsStart());
        int l_intCnt = 0;
        for (Iterator l_it = l_map.keySet().iterator(); l_it.hasNext();)
        {
            Object l_key = l_it.next();
            Object l_value = l_map.get(l_key);
            if (l_intCnt > 0)
                l_sbBuffer.append(getFieldSeparator());
            appendDetail(l_sbBuffer, l_key);
            l_sbBuffer.append(getFieldNameValueSeparator());
            appendDetail(l_sbBuffer, l_value);
            l_intCnt++;
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, Collection l_col)
    {
        l_sbBuffer.append(getArrayContentsStart());
        int l_intCnt = 0;
        for (Iterator l_it = l_col.iterator(); l_it.hasNext();)
        {
            Object l_entry = l_it.next();
            if (l_intCnt > 0)
                l_sbBuffer.append(getFieldSeparator());
            appendDetail(l_sbBuffer, l_entry);
            l_intCnt++;
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, long[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            l_sbBuffer.append(l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, Object l_object)
    {
        if (l_object instanceof Map)
        {
            appendDetail(l_sbBuffer, (Map) l_object);
        } else if (l_object instanceof Collection)
        {
            appendDetail(l_sbBuffer, (Collection) l_object);
        } else if (l_object instanceof long[])
        {
            appendDetail(l_sbBuffer, (long[]) l_object);
        } else if (l_object instanceof int[])
        {
            appendDetail(l_sbBuffer, (int[]) l_object);
        } else if (l_object instanceof double[])
        {
            appendDetail(l_sbBuffer, (double[]) l_object);
        } else if (l_object instanceof boolean[])
        {
            appendDetail(l_sbBuffer, (boolean[]) l_object);
        } else if (l_object instanceof char[])
        {
            appendDetail(l_sbBuffer, (char[]) l_object);
        } else if (l_object instanceof float[])
        {
            appendDetail(l_sbBuffer, (float[]) l_object);
        } else if (l_object instanceof byte[])
        {
            appendDetail(l_sbBuffer, (byte[]) l_object);
        } else if (l_object instanceof short[])
        {
            appendDetail(l_sbBuffer, (short[]) l_object);
        } else if (l_object instanceof Object[])
        {
            appendDetail(l_sbBuffer, (Object[]) l_object);
        } else
        {
            l_sbBuffer.append(l_object);
        }
    }

    private void appendDetail(StringBuffer l_sbBuffer, Object[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            appendDetail(l_sbBuffer, l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private void appendDetail(StringBuffer l_sbBuffer, short[] l_array)
    {
        l_sbBuffer.append(getArrayContentsStart());
        for (int i = 0; i < l_array.length; i++)
        {
            if (i > 0)
            {
                l_sbBuffer.append(getArrayContentsSeparator());
            }
            l_sbBuffer.append(l_array[i]);
        }
        l_sbBuffer.append(getArrayContentsEnd());
    }

    private String getShortClassName(Object l_object)
    {
        StringBuffer l_sbClassName =
            new StringBuffer(l_object.getClass().getName());
        int l_intIndex = l_sbClassName.lastIndexOf(".");
        if (l_intIndex > 0)
        {
            return l_sbClassName.substring(l_intIndex + 1);
        }
        return l_sbClassName.toString();
    }

    private void removeLastFieldSeparator(StringBuffer l_sbBuffer)
    {
        String l_strSep = getFieldSeparator();
        int l_intLen = l_sbBuffer.length();
        int l_intSepLen = l_strSep.length();
        if (l_intLen > 0 && l_intSepLen > 0 && l_intLen >= l_intSepLen)
        {
            boolean l_match = true;
            for (int i = 0; i < l_intSepLen; i++)
            {
                if (l_sbBuffer.charAt(l_intLen - 1 - i)
                    != l_strSep.charAt(l_intSepLen - 1 - i))
                {
                    l_match = false;
                    break;
                }
            }
            if (l_match)
            {
                l_sbBuffer.setLength(l_intLen - l_intSepLen);
            }
        }

    }

}
@
