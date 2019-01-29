head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算結果クラス(WEB3TPCalcResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/17 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * （余力計算結果）
 * 
 * 取引可能額の計算結果を格納するクラス
 */
public class WEB3TPCalcResult
{

    /**
     * （注文種別）<BR>
     * OrderTypeEnumにて定義<BR>
     * <BR>
     * [セットされている取引可能額との対応]<BR>
     * 株式買付可能額・・・OrderTypeEnum.株式現物買注文<BR>
     * 信用新規建可能額・・・OrderTypeEnum.株式信用新規買建注文<BR>
     * 信用現引可能額・・・OrderTypeEnum.株式信用現引注文<BR>
     * 投信買付可能額・・・OrderTypeEnum.投資信託買注文<BR>
     * 出金可能額・・・OrderTypeEnum.出金注文<BR>
     * その他商品買付可能額・・・OrderTypeEnum.その他<BR>
     * <BR>
     * ※保証金預託率の場合はnull。<BR>
     */
    public OrderTypeEnum orderTypeEnum;

    /**
     * （取引可能額）
     * 各注文種別の最小取引可能額
     */
    public double tradingPower;

    /**
     * （適用日）
     * 各注文種別の最小取引可能額の適用日
     */
    public int appliedPoint;

    /**
     * @@roseuid 410DF888010E
     */
    public WEB3TPCalcResult()
    {

    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.append("orderTypeEnum", this.orderTypeEnum);
        l_builder.append("tradingPower", this.tradingPower);
        l_builder.append("appliedPoint", this.appliedPoint);

        return l_builder.toString();
    }

}
@
