head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushObjectToXMLConverter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュメッセージXMLコンバーター(WEB3RichPushObjectToXMLConverter.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.lang.reflect.*;
import java.util.*;

import webbroker3.util.*;

/**
 * リッチクライアントプッシュメッセージXMLコンバーター
 * @@author  : 劉(FLJ)
 * @@version : 1.0
 */
public final class WEB3RichPushObjectToXMLConverter
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushObjectToXMLConverter.class);

    /** XML HEAD */
    protected static final String XML_HEAD =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";

    /** XML MAIN TAG NAME */
    protected static final String XML_MAIN_TAG_NAME_S =
        "<response>\n";

    /** XML MAIN TAG NAME */
    protected static final String XML_MAIN_TAG_NAME_E =
        "</response>\n";

    /**
     * オブジェクトをXMLフォーマット変換
     *
     * @@param l_obj Object
     * @@return String
     */
    public static String toXML(Object l_obj)
    {
        final String STR_METHOD_NAME = "toXML()";
        log.entering(STR_METHOD_NAME);

        if (l_obj != null)
        {
            String l_strClassName = l_obj.getClass().getName();
            ArrayList arraylist = new ArrayList();
            for (StringTokenizer stringtokenizer = new StringTokenizer(l_strClassName,
                ".");
                 stringtokenizer.hasMoreTokens(); arraylist.add(stringtokenizer.nextToken()))
            {
                ;
            }
            if (arraylist.size() > 0)
            {
                l_strClassName = (String) arraylist.get(arraylist.size() - 1);
            }

            StringBuffer l_strbuff = new StringBuffer();
            String indentString = new String("  ");
            l_strbuff.append(indentString);

            l_strbuff.append("<" + l_strClassName + ">");
            l_strbuff.append("\n");

            appendContainedFields(l_strbuff, l_obj, indentString + indentString);

            l_strbuff.append(indentString);
            l_strbuff.append("</" + l_strClassName + ">");
            l_strbuff.append("\n");

            log.exiting(STR_METHOD_NAME);
            return l_strbuff.toString();

        }

        log.debug("toXMLString(NULL)");
        log.exiting(STR_METHOD_NAME);
        return new String("");

    }

    /**
     * オブジェクトのFieldからXMLtagへ変換
     *
     * @@param b StringBuffer
     * @@param o Object
     * @@param indentString String
     */
    private static void appendContainedFields(StringBuffer b, Object o,
                                              String indentString)
    {
        Field fields[] = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            if (Modifier.isStatic(f.getModifiers()))
            {
                continue;
            }
            f.setAccessible(true);
            String tagname = f.getName();
            Object tagValue = null;
            try
            {
                tagValue = f.get(o);
            }
            catch (Exception ex)
            {
                log.error(ex.getMessage(), ex);
            }

            if (tagValue == null)
            {
                continue;
            }

            b.append(indentString);
            b.append("<");
            b.append(tagname);
            b.append(">");
            b.append(tagValue);
            b.append("</");
            b.append(tagname);
            b.append(">");
            b.append("\n");
        }

    }

}
@
