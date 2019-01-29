head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerSettlementService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 差金決済取引余力サービス(WEB3TPTradingPowerSettlementService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/22 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (差金決済取引余力サービス)
 * 差金決済取引余力サービスインターフェース
 */
public interface WEB3TPTradingPowerSettlementService extends Service
{

    /**
     * （get差金決済売付可能数量）<BR>
     * <BR>
     * @@param l_subAccount - （補助口座）
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@param l_lngMarketId - （市場ID）
     * @@param l_dblLimitPrice - （指値）
     * @@param l_dblLotSize - （売買単位）
     * @@return double
     */
    public double getSellOrderPossibleQuantity(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        long l_lngMarketId,
        double l_dblLimitPrice,
        double l_dblLotSize) throws WEB3SystemLayerException;

    /**
     * （get差金決済買付可能額）<BR>
     * <BR>
     * @@param l_subAccount - （補助口座）
     * @@param l_datSpecifiedDate - （指定日）
     * @@param l_lngOrderFundId - （注文銘柄ID）
     * @@param l_blnTodayRepFund - （即日入金対象銘柄フラグ）
     * 　@・指定銘柄が即日入金対象銘柄の場合⇒true
     * 　@・指定銘柄が即日入金対象銘柄でない場合⇒false
     * @@return double
     */
    public double getBuyOrderPossibleAmount(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datSpecifiedDate,
        long l_lngOrderFundId,
        boolean l_blnTodayRepFund)
        throws WEB3SystemLayerException;
}@
