head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TradeTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(中訊)　@新規作成
Revesion History : 2004/07/01 孟 東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 売買区分　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3TradeTypeDef
{
    /**
     * 1 : 買付
     */
    public static final String BUY = "1";

    /**
     * 2 : 売付
     */
    public static final String SELL = "2";

    /**
     * 3 : 募集
     */
    public static final String RECRUIT = "3";

    /**
     * 1 : 売建
     */
    public static final String OPEN_SHORT_MARGIN = "1";

    /**
     * 2 : 買建
     */
    public static final String OPEN_LONG_MARGIN = "2";

}
@
