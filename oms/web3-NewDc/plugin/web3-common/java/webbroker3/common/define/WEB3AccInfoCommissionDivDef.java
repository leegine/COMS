head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccInfoCommissionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料区分定数定義インタフェイス(WEB3AccInfoCommissionDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/18 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 手数料区分定数定義インタフェイス<BR>
 * （委託手数料コースマスタの手数料区分の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoCommissionDivDef
{
    /**
     * null：DEFAULT（リテラ以外）
     */
    public final static String DEFAULT = null;

    /**
     * 0:現物取引手数料
     */
    public final static String EQUITY_TRADE_COMMISSION = "0";

    /**
     * 1:信用取引手数料
     */
    public final static String MARGIN_TRADE_COMMISSION = "1";
}@
