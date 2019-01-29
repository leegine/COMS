head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryCpitalGainTaxDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 譲渡益税 (WEB3TradeHistoryCpitalGainTaxDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27  温 顕 法@(中訊) 新規作成
*/
package webbroker3.tradehistory.define;

/**
 * 譲渡益税 定数定義インタフェイス
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
public interface WEB3TradeHistoryCpitalGainTaxDef
{

    /**
     *99：　@金銭
     */
    public final static String CASH = "99";
    
    /**
     *1079：　@金銭((特定)譲渡益税還付金)
     */
    public final static String CASH_CPITAL_GAIN_TAX_RETURN = "1079";

    /**
     *1080：　@金銭((特定)譲渡益税徴収)
     */
    public final static String CASH_CPITAL_GAIN_TAX_COLLECTION = "1080";

    /**
     *1082：　@金銭(特定譲渡益税徴収 国税)
     */
    public final static String CASH_CPITAL_GAIN_TAX_COLLECTION_NATION = "1082";

    /**
     *1083：　@金銭(特定譲渡益税徴収 地方税)
     */
    public final static String CASH_CPITAL_GAIN_TAX_COLLECTION_REGION = "1083";

    /**
     *1084：　@金銭(特定譲渡益税還付 国税)
     */
    public final static String CASH_CPITAL_GAIN_TAX_RETURN_NATION = "1084";

    /**
     *1085：　@金銭(特定譲渡益税還付 地方税)
     */
    public final static String CASH_CPITAL_GAIN_TAX_RETURN_REGION = "1085";

}
@
