head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DesignateMethodDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指定方法@　@定数定義インタフェイス(WEB3DesignateMethodDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/02 周勇(sinocom) 新規作成
Revesion History : 2004/08/25 孟東(sinocom) SELECTを追加
Revesion History : 2004/08/25 孟東(sinocom) ALLを追加
Revesion History : 2004/09/23 孟東(sinocom) ALLの定義を修正
*/
package webbroker3.common.define;

/**
 * 指定方法@　@定数定義インタフェイス
 *
 * @@author 周勇(sinocom)
 * @@version 1.0
 */
public interface WEB3DesignateMethodDef
{
    /**
     * 選択指定
     */
    public final static String SELECT = "0";
    
    /**
     * 全部指定
     */
    public final static String ALL = "2";
    
    /**
     * 金額指定
     */
    public static final String AMOUNT = "3";

    /**
     * 口数指定
     */
    public static final String NUMBER = "4";

}
@
