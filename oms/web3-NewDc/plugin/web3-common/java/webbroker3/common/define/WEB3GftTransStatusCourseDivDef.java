head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GftTransStatusCourseDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3GftTransStatusCourseDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 屈陽 (中訊) 新規作成
Revesion History : 2008/9/24 陸文静 (中訊) 【入出金】仕様変更管理台帳 ＤＢレイアウトNo.148
*/

package webbroker3.common.define;

/**
 * GFT振替状況テーブルのコース区分　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */
public interface WEB3GftTransStatusCourseDivDef
{
    /**
     * 0：DEFAULT　@
     */
    public static final String DEFAULT = "0";
    
    /**
     * 1：1万通貨コース
     */
    public static final String ONE_COSE = "1";
    
    /**
     * 2：10万通貨コース
     */
    public static final String TEN_COSE = "2";

    /**
     * 3：CFDコース
     */
    public static final String CFD_COURSE = "3";
}
@
