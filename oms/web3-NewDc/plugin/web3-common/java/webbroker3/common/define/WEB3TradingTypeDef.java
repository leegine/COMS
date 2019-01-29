head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引区分定数定義インタフェイス(WEB3TradingTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 岡村和明(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 取引区分 定数定義インタフェイス
 * @@author 岡村和明(SRA)
 * @@version 1.0
 */
public interface WEB3TradingTypeDef
{

    /**
     * 1：現物買注文
     */
    public static final String BUY_ORDER = "1";
    
    /**
     * 2：現物売注文
     */
    public static final String SELL_ORDER = "2";
    
    /**
     * 25：単純入出庫
     */
    public static final String SIO = "25";
    
    /**
     * 99：立会外分売
     */
    public static final String PRESENCE_ORDER = "99";
}
@
