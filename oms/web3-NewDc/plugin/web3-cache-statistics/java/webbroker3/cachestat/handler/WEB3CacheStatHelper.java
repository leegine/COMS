head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatHelperクラス(WEB3CacheStatHelper.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.handler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * キャッシュ統計プラグインのヘルパークラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3CacheStatHelper
{
    
    /**
     * スレッドセーフな<code>NumberFormat</code>のリポジトリ
     */
    private final ThreadLocal threadLocalNumberFormats = new ThreadLocal()
    {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };
    
    /**
     * <code>NumberFormat</code>を使って数値を文字列にフォーマットする。
     * 
     * @@param l_lngValue フォーマットする数値
     * @@param l_strPattern パターン
     * @@return 指定した数値をフォーマットした文字列
     */
    String format(long l_lngValue, String l_strPattern)
    {
        return getNumberFormat(l_strPattern).format(l_lngValue);
    }

    /**
     * <code>NumberFormat</code>を使って数値を文字列にフォーマットする。
     * 
     * @@param l_lngValue フォーマットする数値
     * @@param l_strPattern パターン
     * @@return 指定した数値をフォーマットした文字列
     */
    String format(double l_dblValue, String l_strPattern)
    {
        return getNumberFormat(l_strPattern).format(l_dblValue);
    }
    
    /**
     * スレッドセーフな<code>NumberFormat</code>を取得する。
     * 
     * @@param l_strPattern パターン
     * @@return スレッドセーフな<code>NumberFormat</code> 
     */
    NumberFormat getNumberFormat(String l_strPattern)
    {
        Map l_numberFormats = (Map) threadLocalNumberFormats.get();
        NumberFormat l_nf = (NumberFormat) l_numberFormats.get(l_strPattern);
        if (l_nf == null)
        {
            l_nf = new DecimalFormat(l_strPattern);
            l_numberFormats.put(l_strPattern, l_nf);
        }
        return l_nf;
    }

}
@
