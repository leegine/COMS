head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TodayPaymentCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 当日出金チェック定数定義インタフェイス(WEB3TodayPaymentCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/25 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 当日出金チェック 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3TodayPaymentCheckDef
{

    /**
     * 0：当日出金不可
     */
    public final static String DEFAULT = "0";

    /**
     * 1：当日出金可能
     */
    public final static String CHECK = "1";  
} @
