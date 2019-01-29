head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文発注サービスImpl(WEB3ToStopOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 呉艶飛(中訊) 新規作成
                   2006/01/23 呉艶飛(北京中訊) 仕様変更・モデル066
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.ifo.WEB3IfoTradingModule;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopOrderService;
import webbroker3.util.WEB3LogUtility;


/**
 * (逆指値注文発注サービスImpl)<BR>
 * 逆指値注文発注サービス実装クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToStopOrderServiceImpl implements WEB3ToStopOrderService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopOrderServiceImpl.class);
    
    /**
     * @@roseuid 436ACF70035B
     */
    public WEB3ToStopOrderServiceImpl() 
    {
     
    }
    
    /**
     * (execute逆指値注文発注)<BR>
     * 逆指値注文発注処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（逆指値注文発注サービス）execute逆指値注文発注」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （発注対象の注文の注文ID）<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C794900F1
     */
    public void executeStopOrder(SubAccount l_subAccount, long l_lngOrderId, ProductTypeEnum l_productType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "executeStopOrder(SubAccount l_subAccount, long l_lngOrderId, ProductTypeEnum l_productType)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        OrderUnit[] l_orderUnits = null;
        //1.1注文単位オブジェクトを取得する

        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            //1.1.1株式の、注文単位オブジェクトを取得する。
            WEB3EquityTradingModule l_tradingModule
                = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_eqOrderManager
                = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            
            l_orderUnits = l_eqOrderManager.getOrderUnits(l_lngOrderId);
        }
        else if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //1.1.2先物OPの、注文単位オブジェクトを取得する。
            WEB3IfoTradingModule l_tradingModule
                = (WEB3IfoTradingModule) l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_opOrderManager 
                = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_orderUnits = l_opOrderManager.getOrderUnits(l_lngOrderId);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.2（分岐フロー：　@注文単位が取得できなかった場合）
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            //1.2.1log.error()にて、注文単位が取得できなかったメッセージと、
            //口座ID、補助口座ID、注文ID、銘柄タイプを出力する。
            log.error("口座ID = "+ l_subAccount.getAccountId());
            log.error("補助口座ID = "+ l_subAccount.getSubAccountId());
            log.error("注文ID = "+ l_lngOrderId);
            log.error("銘柄タイプ = "+ l_productType);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        OrderUnit l_orderUnit = l_orderUnits[0];

        //1.3（分岐フロー：　@引数の銘柄タイプ=="株式"の場合）
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            WEB3ToStopEquityOrderUnitService l_orderUnitService = 
                (WEB3ToStopEquityOrderUnitService)Services.getService(WEB3ToStopEquityOrderUnitService.class);
            //1.3.1submitEquityStopOrder(注文単位)
            //現物株式逆指値注文を発注する。
            //引数は以下の通りにセットする。
            //株式注文単位：　@取得した注文単位オブジェクト
            if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
            {
                l_orderUnitService.submitEquityStopOrder((EqTypeOrderUnit)l_orderUnit);
            }
            //1.3.2submitMarginOpenContractStopOrder(注文単位)
            //信用新規建逆指値注文を発注する。
            //引数は以下の通りにセットする。
            //株式注文単位：　@取得した注文単位オブジェクト
            else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                l_orderUnitService.submitMarginOpenContractStopOrder((EqTypeOrderUnit)l_orderUnit);
            }
            //1.3.3submitMarginSettleContractStopOrder(注文単位)
            //信用返済逆指値注文を発注する。
            //引数は以下の通りにセットする。
            //株式注文単位：　@取得した注文単位オブジェクト
            else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                l_orderUnitService.submitMarginSettleContractStopOrder((EqTypeOrderUnit)l_orderUnit);
            }
        }
        //1.4（分岐フロー：　@引数の銘柄タイプ=="先物オプション"の場合）
        else
        {
            WEB3ToStopIfoOrderUnitService l_ifoOrderUnitService = 
                (WEB3ToStopIfoOrderUnitService)Services.getService(WEB3ToStopIfoOrderUnitService.class);
            //1.4.1先物新規建逆指値注文を発注する。
            //引数は以下の通りにセットする。
            //先物OP注文単位：　@取得した注文単位オブジェクト
            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg()))
            {
                l_ifoOrderUnitService.submitFuturesOpenContractStopOrder((IfoOrderUnit)l_orderUnit);
            }
            //1.4.2先物返済逆指値注文を発注する。
            //引数は以下の通りにセットする。
            //先物OP注文単位：　@取得した注文単位オブジェクト
            else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
            {
                l_ifoOrderUnitService.submitFuturesSettleContractStopOrder((IfoOrderUnit)l_orderUnit);
            }
            //1.4.3オプション新規建逆指値注文を発注する。
            //引数は以下の通りにセットする。
            //先物OP注文単位：　@取得した注文単位オブジェクト
            else if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
            {
                l_ifoOrderUnitService.submitOptionOpenContractStopOrder((IfoOrderUnit)l_orderUnit);
            }
            //1.4.4オプション返済逆指値注文を発注する。
            //引数は以下の通りにセットする。
            //先物OP注文単位：　@取得した注文単位オブジェクト
            else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()))
            {
                l_ifoOrderUnitService.submitOptionSettleContractStopOrder((IfoOrderUnit)l_orderUnit);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return;
    }
}
@
