head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BondTradeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売買区分定数定義インタフェイス(WEB3BondTradeTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 売買区分　@定数定義インタフェイス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3BondTradeTypeDef
{
    /**
     * 1:買付
     */
    public static final String BUY = "1";

    /**
     * 2:売却
     */
    public static final String SELL = "2";

    /**
     * 3:応募
     */
    public static final String RECRUIT = "3";

    /**
     * 4：買付/売却
     */
    public static final String BUY_SELL = "4";
}
@
