head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityTradeStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引停止区分 定数定義インタフェイス(WEB3AdminEquityTradeStopDivDef.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.define;

/**
 * 取引停止区分 定数定義インタフェイス
 *
 * @@author Umadevi
 * @@version 1.0
 */
public interface WEB3AdminEquityTradeStopDivDef
{

    /**
     *取引可能
     */
    public final static String NORMAL = "0";

    /**
     *取引停止
     */
    public final static String SUSPENTION = "1";

}@
