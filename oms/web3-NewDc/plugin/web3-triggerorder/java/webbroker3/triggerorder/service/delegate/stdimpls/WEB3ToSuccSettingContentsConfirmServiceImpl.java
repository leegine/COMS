head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingContentsConfirmServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 設定内容確認サービスImpl(WEB3ToSuccSettingContentsConfirmServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/1７ 李　@俊(中訊) 新規作成
Revesion History : 2008/04/14 趙林鵬 (中訊) モデルNo.295
Revesion History : 2008/05/08 趙林鵬 (中訊) モデルNo.346
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccClientRequestService;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.triggerorder.message.WEB3SuccSettingContentConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccSettingContentConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingContentsConfirmService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (設定内容確認サービスImpl)<BR>
 * 設定内容確認サービス実装クラス<BR>
 * @@author 李俊 <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccSettingContentsConfirmServiceImpl extends WEB3ToSuccClientRequestService implements WEB3ToSuccSettingContentsConfirmService 
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingContentsConfirmServiceImpl.class);   
    /**
     * @@roseuid 4348E2FC00EA
     */
    public WEB3ToSuccSettingContentsConfirmServiceImpl() 
    {
     
    }
    
    /**
     * 設定内容確認サービス処理を行う。<BR>
     * <BR>
     * this.get確認画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D18D00153
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException  
    {

         final String STR_METHOD_NAME =
             "execute(WEB3GenRequest l_request)";
         log.entering(STR_METHOD_NAME);

         if (l_request == null)
         {
             log.error("パラメータ.リクエストデータがnullです。");
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 getClass().getName() + "." + STR_METHOD_NAME);
         }

         WEB3GenResponse l_response = null;

         if (l_request instanceof WEB3SuccSettingContentConfirmRequest)
         {
             l_response =
                this.getConfirmScreen((WEB3SuccSettingContentConfirmRequest)l_request);
         }
         else
         {
             log.error("パラメータ.リクエストデータの型が不正です。");
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                 getClass().getName() + "." + STR_METHOD_NAME);
         }

         log.exiting(STR_METHOD_NAME);
         return l_response;
     
    }
    
    /**
     * (get確認画面)<BR>
     * 設定内容確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（設定内容確認）get確認画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 設定内容確認リクエストオブジェクト<BR>
     * @@return WEB3SuccSettingContentConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D191E02AA
     */
    protected WEB3SuccSettingContentConfirmResponse getConfirmScreen(WEB3SuccSettingContentConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getConfirmScreen(WEB3SuccSettingContentConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate
        l_request.validate();
        
        //1.2.validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get補助口座
        SubAccount l_subAccount = this.getSubAccount(l_request.commodityType);
        
        //1.4 validate連続注文取扱可能
        WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();        
        //証券会社コード
        String l_strInstitution = l_subAccount.getInstitution().getInstitutionCode();
        //銘柄タイプ： 
        //　@[商品区分 == ("現物株式" or "信用取引")の場合] 
        //　@　@ProductTypeEnum.株式 
        //　@[上記以外の場合] 
        //　@ProductTypeEnum.先物オプション 
        ProductTypeEnum  l_productType = null;
        //商品区分
        String l_strCommodityType = l_request.commodityType;
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType) 
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
        {
            l_productType = ProductTypeEnum.EQUITY;
        }
        else
        {
            l_productType = ProductTypeEnum.IFO;
        }
        // 先物／オプション区分： 
        // [商品区分 == "先物"の場合] 
        // "先物" 
        // [商品区分 == "オプション"の場合] 
        // "オプション" 
        // [上記以外の場合] 
        //　@"DEFAULT"
        String l_strFuturesOptionDiv = null;
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType))
        {
        	l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
        }
        else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
        {
        	l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.OPTION;
        }
        else
        {
        	l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
        }
        
        l_orderManager.validateSuccOrderHandling(l_strInstitution, l_productType, l_strFuturesOptionDiv);

        //1.5 注文単位を取得する。 
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(WEB3ToSuccDataGettingService.class);
        OrderUnit l_orderUnit = 
            l_dataGettingService.getOrderUnit(Long.parseLong(l_request.parentOrderId), l_strCommodityType);

        long l_lngOrderId = l_orderUnit.getOrderId();
        //リクエストデータ.商品区分 == ("現物株式" or "信用取引")の場合
        EqTypeOrderUnit[] l_succEqTypeOrderUnits = null;
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
        {
            //get有効株式子注文単位一覧
            try 
            {
                l_succEqTypeOrderUnits = l_orderManager.getOpenReserveEqtypeOrderUnits(l_lngOrderId);
            } 
            catch(NotFoundException l_nfe)
            {
                log.error("有効株式子注文単位一覧が取得できません。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文ID:[" + l_lngOrderId + "]に該当する有効株式子注文単位一覧が取得できません。",
                    l_nfe);
            } 
        }
        //リクエストデータ.商品区分 == ("先物" or "オプション")
        WEB3ToSuccIfoOrderUnitImpl[] l_toSuccIfoOrderUnits = null;
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
        {
            // get有効先物OP子注文単位一覧(long)
            l_toSuccIfoOrderUnits = l_orderManager.getOpenReserveIfoOrderUnits(l_lngOrderId); 
        } 

		//1.7連続注文明細インスタンスを生成する。
        WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
        
        //1.8 create連続注文明細
        l_dataGettingService.createSuccOrderUnit(l_succOrderUnit, l_orderUnit, false);
        
        //1.9 create予約注文明細
        //get有効株式子注文単位一覧(親注文の注文ID)の戻り値がnull以外　@かつ、
        //get有効株式子注文単位一覧(親注文の注文ID)の要素数が0以外の場合
        if (l_succEqTypeOrderUnits != null && l_succEqTypeOrderUnits.length != 0)
        {
            RsvEqOrderUnitRow[] l_rsvEqOrderUnitRows = new RsvEqOrderUnitRow[l_succEqTypeOrderUnits.length];
            for (int i = 0; i < l_succEqTypeOrderUnits.length; i++)
            {
                l_rsvEqOrderUnitRows[i] = (RsvEqOrderUnitRow)l_succEqTypeOrderUnits[i].getDataSourceObject();
            }
            l_dataGettingService.createRsvOrderUnit(l_succOrderUnit, l_rsvEqOrderUnitRows, false);
        }

        //get有効先物OP子注文単位一覧(親注文の注文ID)の戻り値がnull以外　@かつ、
        //get有効先物OP子注文単位一覧(親注文の注文ID)の要素数が0以外の場合
        else if (l_toSuccIfoOrderUnits != null && l_toSuccIfoOrderUnits.length != 0)
        {
            RsvIfoOrderUnitRow[] l_rsvIfoOrderUnitRows = new RsvIfoOrderUnitRow[l_toSuccIfoOrderUnits.length];
            for (int i = 0; i < l_toSuccIfoOrderUnits.length; i++)
            {
                l_rsvIfoOrderUnitRows[i] = (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnits[i].getDataSourceObject();
            }
            l_dataGettingService.createRsvOrderUnit(l_succOrderUnit, l_rsvIfoOrderUnitRows, false);
        }

        WEB3SuccSettingContentConfirmResponse l_response = (WEB3SuccSettingContentConfirmResponse)l_request.createResponse();
        l_response.orderUnit = l_succOrderUnit;
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
        
    }
}
@
