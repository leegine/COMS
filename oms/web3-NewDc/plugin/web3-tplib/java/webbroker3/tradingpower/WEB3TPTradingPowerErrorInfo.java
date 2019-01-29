head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerErrorInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引余力エラー情報(WEB3TPTradingPowerErrorInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (取引余力エラー情報)
 */
public class WEB3TPTradingPowerErrorInfo
{
    /**
     * (取引余力エラー区分)<BR>
     */
    public String tradinPowerErrorDiv;

    /**
     * (預り金不足額)<BR>
     * <BR>
     * [預り金チェック：NGの場合]<BR>
     * 　@不足額をセット<BR>
     * <BR>
     * [預り金チェック：OKの場合]<BR>
     * 　@0をセット<BR>
     * <BR>
     */
    public double lackAccountBalance;

    /**
     * (差金決済買付可能額)　@※現物買付、信用現引において使用、以外の取引の場合は0をセット<BR>
     * <BR>
     * [預り金チェック：NGの場合]<BR>
     * 　@差金決済買付可能額をセット<BR>
     * <BR>
     * [預り金チェック：OKの場合]<BR>
     * 　@0をセット<BR>
     * <BR>
     */
    public double buyOrderPossibleAmount;

    /**
     * (差金決済売付可能数量)　@※現物売付、信用現渡において使用、以外の取引の場合は0をセット<BR>
     * <BR>
     * [預り金チェック：NGの場合]<BR>
     * 　@差金決済売付可能数量をセット<BR>
     * <BR>
     * [預り金チェック：OKの場合]<BR>
     * 　@0をセット<BR>
     * <BR>
     */
    public double sellOrderPossibleQuantity;

    /**
     * (二階建銘柄情報一覧)<BR>
     * <BR>
     * [二階建チェック：NGの場合]<BR>
     * 　@二階建銘柄情報オブジェクトの配列をセット<BR>
     * <BR>
     * [二階建チェック：OKの場合]<BR>
     * 　@nullをセット<BR>
     * <BR>
     */
    public WEB3TPMarginSecurityInfo[] marginSecInfo;

    /**
     * (増担保規制銘柄新規建可能額)<BR>
     * ※信用新規建において使用、以外の取引の場合は0をセット <BR>
     * <BR>
     * [注文銘柄が増担保銘柄　@かつ　@余力チェック：NGの場合] <BR>
     * 　@注文銘柄の信用新規建可能額をセット <BR>
     * <BR>
     * [以外の場合] <BR>
     * 　@0をセット
     * <BR>   
     */
    public double marginTradingPowerIncDeposit;

    /**
     * (コンストラクタ)
     */
    public WEB3TPTradingPowerErrorInfo()
    {
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("tradinPowerErrorDiv", this.tradinPowerErrorDiv);
        l_builder.append("lackAccountBalance", this.lackAccountBalance);
        l_builder.append("buyOrderPossibleAmount", this.buyOrderPossibleAmount);
        l_builder.append("sellOrderPossibleQuantity", this.sellOrderPossibleQuantity);
        l_builder.append("marginTradingPowerIncDeposit", this.marginTradingPowerIncDeposit);

        if (this.marginSecInfo != null)
        {
            for (int index = 0; index < this.marginSecInfo.length; index++)
            {
                l_builder.append(
                    "marginSecInfo[" + index + "]",
                    this.marginSecInfo[index].toString());
            }
        }
        else
        {
            l_builder.append("marginSecInfo", this.marginSecInfo);
        }

        return l_builder.toString();
    }
}
@
