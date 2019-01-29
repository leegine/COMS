head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradeStopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 営業日区分FLAG定数定義クラス(WEB3TradeStopDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 今井　@高史(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 現物停止フラグ定義クラス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3TradeStopDef
{

    /**
     * 0：DEFAULT
     */
    public final static String ACTIVE = "0";

    /**
     * 1：停止中（取引所規制
     */
    public final static String STOP_MARKET_DEREG = "1";

    /**
     * 2：停止中（当社規制）
     */
    public final static String STOP_COMPANY_DEREG = "2";
}
@
