head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSellOrderPossibleQuantityResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 差金決済売付可能数量結果(WEB3TPSellOrderPossibleQuantityResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/06/21 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.updtpower.settlement;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (差金決済売付可能数量結果)
 * 
 * @@author nakazato(DIR-ST)
 * @@version 1.0
 */
public class WEB3TPSellOrderPossibleQuantityResult
{

    /**
     * (差金決済対象銘柄フラグ)<BR>
     * <BR>
     * 注文銘柄が差金決済対象銘柄かどうかの判定フラグ<BR>
     * （true：差金決済銘柄 false：非差金決済銘柄）<BR>
     */
    public boolean dayTradeFundFlg;

    /**
     * (売付可能数量)<BR>
     */
    public double sellPossQuantity;

    /**
     * (預り金不足額)<BR>
     */
    public double lackAmt;

    /**
     * (コンストラクタ)<BR>
     */
    public WEB3TPSellOrderPossibleQuantityResult()
    {
    }

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("dayTradeFundFlg", this.dayTradeFundFlg);
        l_builder.append("sellPossQuantity", this.sellPossQuantity);
        l_builder.append("lackAmt", this.lackAmt);

        return l_builder.toString();
    }

}@
