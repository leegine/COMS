head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文発注サービスImpl(WEB3ToSuccOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 凌建平(中訊) 新規作成
Revesion History : 2008/05/05 劉剣(中訊) 仕様変更　@モデルNo.312、314
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (連続注文発注サービスImpl)<BR>
 * 連続注文発注サービス実装クラス。<BR>
 * <BR>
 * @@author 凌建平 <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccOrderServiceImpl implements WEB3ToSuccOrderService 
{

    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    	WEB3ToSuccOrderServiceImpl.class);

    /**
     * @@roseuid 4348E5B000FA
     */
    public WEB3ToSuccOrderServiceImpl() 
    {
     
    }
    
    /**
     * (execute連続注文発注)<BR>
     * 連続注文発注処理を行う。<BR>
     * シーケンス図「（連続注文発注サービス）execute連続注文発注」を参照。<BR>
     *  ======================================================== <BR>
     *  シーケンス図「（連続注文発注サービス）execute連続注文発注」 <BR> 
     *  1.5.1「親注文≠全部約定」の例外をthrowしエラー終了<BR> 
     *   class: WEB3BusinessLayerException <BR>
     *   tag: BUSINESS_ERROR_02242<BR>
     *  ========================================================== <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_lngSubOrderId - (予約注文の注文ID)<BR>
     * 注文ID。<BR>
     * （発注対象の予約注文の注文ID）<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43215FFA02C5
     */
    public void executeSuccOrder(SubAccount l_subAccount, long l_lngSubOrderId, ProductTypeEnum l_productType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "executeSuccOrder(SubAccount, long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        try
		{
            //1.1 getOrderUnit(ProductTypeEnum, long)
            //発注対象の子注文の注文単位オブジェクトを取得する。 
            //引数は以下の通りにセットする。 
            //  銘柄タイプ：　@引数の銘柄タイプ 
            //  注文ID：　@引数の注文ID
            WEB3ToSuccOrderManagerImpl l_orderManager = 
            	(WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_productType, l_lngSubOrderId);

            //1.2 取得した子注文.注文有効状態≠OPEN
            if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
            {
                log.exiting(STR_METHOD_NAME);

            	//1.2.1 return
            	return;
            }
            
            //1.3 親注文の注文単位オブジェクトを取得する
            //1.3.1 株式の、親注文の注文単位オブジェクトを取得する。
            //引数は以下の通りにセットする。 
            //  親注文の注文単位ID：　@取得した予約注文単位.親注文の注文単位ID
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            EqTypeOrderUnit l_eqTypeOrderUnit = null;
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                WEB3EquityOrderManager l_equityOrderManager =
                    (WEB3EquityOrderManager)l_finApp.getTradingModule(l_productType).getOrderManager();
                RsvEqOrderUnitRow l_orderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_eqTypeOrderUnit = (EqTypeOrderUnit)l_equityOrderManager.getOrderUnit(l_orderUnitRow.getParentOrderUnitId());
            }

            //1.3.2 先物OPの、親注文の注文単位オブジェクトを取得する。
            //引数は以下の通りにセットする。 
            //  親注文の注文単位ID：　@取得した予約注文単位.親注文の注文単位ID
            IfoOrderUnit l_ifoOrderUnit = null;
            if (ProductTypeEnum.IFO.equals(l_productType))
            {
                IfoOrderManager l_ifoOrderManager =
                    (IfoOrderManager)l_finApp.getTradingModule(l_productType).getOrderManager();
                RsvIfoOrderUnitRow l_orderUnitRow = (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_ifoOrderUnit = (IfoOrderUnit)l_ifoOrderManager.getOrderUnit(l_orderUnitRow.getParentOrderUnitId());
            }

            //1.4  isFullyExecuted( )
            boolean l_blnIsFullyExecuted = false;
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                l_blnIsFullyExecuted = l_eqTypeOrderUnit.isFullyExecuted();
            }
            else if (ProductTypeEnum.IFO.equals(l_productType))
            {
                l_blnIsFullyExecuted = l_ifoOrderUnit.isFullyExecuted();
            }

            //1.5 分岐フロー：　@親注文 ≠ 全部約定の場合
            //1.5.1「親注文≠全部約定」の例外をthrowしエラー終了
		    //   class: WEB3BusinessLayerException
		    //   tag: BUSINESS_ERROR_02242
            if (!l_blnIsFullyExecuted)
            {
                log.debug("親注文 ≠ 全部約定の場合。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02242,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.6 分岐フロー：　@引数の銘柄タイプ=="株式"の場合
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                WEB3ToSuccEquityOrderUnitService l_orderUnitService =
                    (WEB3ToSuccEquityOrderUnitService)Services.getService(
                        WEB3ToSuccEquityOrderUnitService.class);

                if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.6.1 submit現物株式注文(株式予約注文単位)
                    // 引数は以下の通りにセットする
                    // 株式予約注文単位：　@取得した子注文オブジェクト
                    l_orderUnitService.submitEquityOrder((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.6.2 submit信用新規建注文(株式予約注文単位)
                    l_orderUnitService.submitMarginOpenContractOrder((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.6.3 submit信用返済注文(株式予約注文単位)
                    l_orderUnitService.submitMarginSettleContractOrder((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.6.4 submit信用現引現渡注文(株式予約注文単位)
                    l_orderUnitService.submitMarginSwapContractOrder((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
                }
            }

            //分岐フロー：　@引数の銘柄タイプ=="先物OP"の場合
            if (ProductTypeEnum.IFO.equals(l_productType))
            {
                WEB3ToSuccIfoOrderUnitService l_toSuccIfoOrderUnit =
                    (WEB3ToSuccIfoOrderUnitService)Services.getService(
                            WEB3ToSuccIfoOrderUnitService.class);

                if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg()))
                {
                    //submit先物新規建注文(先物OP予約注文単位Impl)
                    l_toSuccIfoOrderUnit.submitFuturesOpenContractOrder((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
                {
                    //submit先物返済注文(先物OP予約注文単位Impl)
                    l_toSuccIfoOrderUnit.submitFuturesSettleContractOrder((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
                {
                    //submitOP新規建注文(先物OP予約注文単位Impl)
                    l_toSuccIfoOrderUnit.submitOptionsOpenContractOrder((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()))
                {
                    //submitOP返済注文(先物OP予約注文単位Impl)
                    l_toSuccIfoOrderUnit.submitOptionsSettleContractOrder((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
                }
            }
		}

        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
