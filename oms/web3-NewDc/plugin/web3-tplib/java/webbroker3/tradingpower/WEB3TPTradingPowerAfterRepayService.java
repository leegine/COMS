head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerAfterRepayService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 返済後余力サービス(WEB3TPTradingPowerAfterRepayService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) 新規作成
*/

package webbroker3.tradingpower;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * (返済後余力サービス)
 */
public interface WEB3TPTradingPowerAfterRepayService extends Service
{

    /**
     * (create返済後資産余力情報<信用顧客>)<BR>
     * <BR>
     * 返済後余力オブジェクトを作成する。<BR>
     * <BR>
     * シーケンス図「(返済後余力サービスImpl)create返済後資産余力情報」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_orderSpecIntercepter - (注文内容インタセプタ)
     * @@param l_orderSpec - (注文内容)
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerCalcMarginAfterRepay
     * @@roseuid 41BD4B7502FA
     */
    public WEB3TPTradingPowerCalcMarginAfterRepay createWEB3TPTradingPowerCalcAfterRepay(
        WEB3GentradeSubAccount l_subAccount,
        Object l_orderSpecIntercepter,
        Object l_orderSpec)
        throws WEB3SystemLayerException;

    /**
     * (create返済時注意文言)<BR>
     * <BR>
     * 返済時注意文言オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図「(返済後余力サービスImpl)create返済時注意文言」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     * @@param l_orderSpecIntercepter - (注文内容インタセプタ)
     * @@param l_orderSpec - (注文内容)
     * @@return webbroker3.tradingpower.WEB3TPAttentionObjection
     * @@roseuid 41BD4B75030A
     */
    public WEB3TPAttentionObjection createWEB3TPAttentionObjection(
        WEB3GentradeSubAccount l_subAccount,
        Object l_orderSpecIntercepter,
        Object l_orderSpec)
        throws WEB3SystemLayerException;
}
@
