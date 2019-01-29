head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccAdditionalContentsSelectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追加内容選択サービスImpl(WEB3ToSuccAdditionalContentsSelectServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccClientRequestService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccAdditionalContentSelectRequest;
import webbroker3.triggerorder.message.WEB3SuccAdditionalContentSelectResponse;
import webbroker3.triggerorder.message.WEB3SuccTradingInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccAdditionalContentsSelectService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (追加内容選択サービスImpl)<BR>
 * 追加内容選択サービス実装クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToSuccAdditionalContentsSelectServiceImpl extends WEB3ToSuccClientRequestService implements WEB3ToSuccAdditionalContentsSelectService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccAdditionalContentsSelectServiceImpl.class);
    
    /**
     * @@roseuid 4348E3640128
     */
    public WEB3ToSuccAdditionalContentsSelectServiceImpl() 
    {
     
    }
    
    /**
     * 追加内容選択サービス処理を行う。<BR>
     * <BR>
     * this.get選択画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D17640356
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

        if (l_request instanceof WEB3SuccAdditionalContentSelectRequest)
        {
            l_response =
                this.getSelectScreen( (WEB3SuccAdditionalContentSelectRequest) l_request);
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
     * (get選択画面)<BR>
     * 追加内容選択画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（追加内容選択）get選択画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 追加内容選択リクエストオブジェクト<BR>
     * @@return WEB3SuccAdditionalContentSelectResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D17CD0039
     */
    protected WEB3SuccAdditionalContentSelectResponse getSelectScreen(WEB3SuccAdditionalContentSelectRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1リクエストデータの整合性をチェックする。
        l_request.validate();
        //1.2システムが緊急停止中、バッチ中であるかチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3顧客を取得する。
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        //1.4連続注文が取扱可能であるかチェックする。 
        WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        //証券会社コード：　@get口座()の戻り値.証券会社コード 
        String l_strInstitution = l_mainAccount.getInstitution().getInstitutionCode();
        
        String l_strCommodityType = l_request.commodityType;
        //銘柄タイプ： 
        //　@[商品区分 == ("現物株式" or "信用取引")の場合] 
        //　@　@ProductTypeEnum.株式 
        //　@[上記以外の場合] 
        //　@ProductTypeEnum.先物オプション         
        ProductTypeEnum l_productType = null;
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType) 
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
        {
            l_productType = ProductTypeEnum.EQUITY;
        }
        else
        {
            l_productType = ProductTypeEnum.IFO;
        }
        
        //先物／オプション区分： 
        // 　@[商品区分 == "先物"の場合] 
        // 　@　@"先物" 
        //　@[商品区分 == "オプション"の場合] 
        // 　@　@"オプション" 
        //　@[上記以外の場合] 
        // 　@　@"DEFAULT"
        String l_strfuturesOptionDiv = null;
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType))
        {
            l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
        }
        else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
        {
            l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.OPTION;
        }
        else
        {
            l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
        }

        l_orderManager.validateSuccOrderHandling(l_strInstitution, l_productType, l_strfuturesOptionDiv);
        
        //1.5注文単位を取得する。 
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(WEB3ToSuccDataGettingService.class);
        OrderUnit l_orderUnit = 
            l_dataGettingService.getOrderUnit(Long.parseLong(l_request.parentOrderId), l_strCommodityType);
        
        //1.6トリガー注文が設定可能な注文かどうかチェックする。 
        l_orderManager.validateTriggerOrderSettingToParentOrder(l_orderUnit);
        // トリガー注文が追加設定可能かどうかチェックする。
        l_orderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        //1.7連続注文取引情報の一覧を作成する。 
        WEB3SuccTradingInfo[] l_tradingInfos = 
            this.createSuccOrderTradedInfoList(l_mainAccount, l_orderUnit.getOrderType(), l_strCommodityType);
        
        //1.8レスポンスデータを生成する。
        WEB3SuccAdditionalContentSelectResponse l_reponse = 
            (WEB3SuccAdditionalContentSelectResponse)l_request.createResponse();
        
        //1.9メッセージ (*)プロパティセット
        l_reponse.parentOrderId = l_request.parentOrderId;
        l_reponse.succTradingList = l_tradingInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_reponse;
    }
    
    /**
     * (create連続注文取引情報一覧)<BR>
     * 顧客が選択可能な取引情報の一覧を作成する。<BR>
     * <BR>
     * １）　@リクエストデータ.商品区分により<BR>
     * 　@処理対象の商品区分一覧を決定する。<BR>
     * <BR>
     * 　@リクエストデータ.商品区分が、<BR>
     * 　@["現物株式" or "信用取引"の場合]<BR>
     * 　@　@処理対象の商品区分一覧 = "現物株式", "信用取引"<BR>
     * <BR>
     * 　@["先物" or "オプション"の場合]<BR>
     * 　@　@処理対象の商品区分一覧 = "先物", "オプション"<BR>
     * <BR>
     * ２）　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@１）にて決定した処理対象の商品区分一覧の<BR>
     * 　@要素数分、以下の処理を行う。<BR>
     * 　@３－１）　@連続注文取引情報インスタンスを生成する。<BR>
     * 　@３－２）　@生成した連続注文取引情報にプロパティをセットする。<BR>
     * <BR>
     * 　@商品区分 = 処理対象の商品区分<BR>
     * 　@連続注文取引区分一覧 = <BR>
     * 　@　@以下の両メソッドの戻り値を要素とする配列<BR>
     * 　@　@①@this.get反対売買取引()<BR>
     * 　@　@②連続注文マネージャImpl.get連続注文取引一覧()<BR>
     * 　@　@※①@、②の戻り値ともnullの場合、次の要素へ処理を移行する。<BR>
     * 　@　@　@(continue)<BR>
     * <BR>
     * 　@　@[get反対売買取引()に指定する引数]<BR>
     * 　@　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@　@注文種別：　@パラメータ.注文種別<BR>
     * 　@　@　@商品区分：　@処理対象の商品区分<BR>
     * <BR>
     * 　@　@[get連続注文取引一覧()に指定する引数]<BR>
     * 　@　@　@顧客：　@パラメータ.顧客<BR>
     * 　@　@　@注文種別：　@パラメータ.注文種別<BR>
     * 　@　@　@商品区分一覧：　@処理対象の商品区分のみを<BR>
     * 　@　@　@　@要素とする配列<BR>
     * <BR>
     * 　@３－３）　@生成したArrayListにプロパティセットした<BR>
     * 　@　@インスタンスを追加する。<BR>
     * <BR>
     * ４）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@param l_strCommodityDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * 
     * @@return WEB3SuccTradingInfo[]
     * @@throws WEB3BaseException
     * @@roseuid 432658740097
     */
    protected WEB3SuccTradingInfo[] createSuccOrderTradedInfoList(WEB3GentradeMainAccount l_mainAccount, OrderTypeEnum l_orderType, String l_strCommodityDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSuccOrderTradedInfoList(WEB3GentradeMainAccount l_mainAccount, OrderTypeEnum l_orderType, String l_strCommodityDiv)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3ToSuccAdditionalContentsSelectServiceImpl.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        //１）　@リクエストデータ.商品区分により処理対象の商品区分一覧を決定する。
        //リクエストデータ.商品区分が、
        //* 　@["現物株式" or "信用取引"の場合]
        //* 　@　@処理対象の商品区分一覧 = "現物株式", "信用取引"
        String[] l_strPorductDivList = new String[2];
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv)
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            l_strPorductDivList[0] = WEB3CommodityDivDef.EQUITY;
            l_strPorductDivList[1] = WEB3CommodityDivDef.MARGIN;
        }

        ///["先物" or "オプション"の場合]
        //処理対象の商品区分一覧 = "先物", "オプション"
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv)
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            l_strPorductDivList[0] = WEB3CommodityDivDef.FUTURE;
            l_strPorductDivList[1] = WEB3CommodityDivDef.OPTION;
        }
        
        //２）　@ArrayListを生成する。
        List l_lisTradingInfo = new ArrayList(); 
        
        if (l_strPorductDivList != null)
        {
            //３）　@１）にて決定した処理対象の商品区分一覧の要素数分、以下の処理を行う。
            for (int i = 0; i <l_strPorductDivList.length; i ++)
            {
                //３－１）　@連続注文取引情報インスタンスを生成する。
                WEB3SuccTradingInfo l_tradingInfo = new WEB3SuccTradingInfo();
                
                //３－２）　@生成した連続注文取引情報にプロパティをセットする。
                /*
                 * 商品区分 = 処理対象の商品区分
                 * 　@連続注文取引区分一覧 = 
                 * 　@　@以下の両メソッドの戻り値を要素とする配列
                 * 　@　@①@this.get反対売買取引()
                 * 　@　@②連続注文マネージャImpl.get連続注文取引一覧()
                 */
                String[] l_strTrades = 
                    this.getReversingTrade(l_mainAccount, l_orderType, l_strPorductDivList[i]);
                
                WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                String[] l_strTargetProductDiv = {l_strPorductDivList[i]};
                String [] l_strTradeLists = 
                    l_orderManager.getSuccOrderTradeList(l_mainAccount, l_orderType, l_strTargetProductDiv);
                
                //※①@、②の戻り値ともnullの場合、次の要素へ処理を移行する。
                //(continue)
                if (l_strTrades == null && l_strTradeLists == null)
                {
                    continue;
                }
                
                l_tradingInfo.commodityType = l_strPorductDivList[i];
                //３－３）　@生成したArrayListにプロパティセットしたインスタンスを追加する。
                Object[] l_objSuccTradingType =  WEB3Toolkit.merge(l_strTrades,l_strTradeLists);
                
                if (l_objSuccTradingType != null)
                {
                    String[] l_strSuccTradingType = new String[l_objSuccTradingType.length];
                    for (int j = 0; j < l_objSuccTradingType.length; j++)
                    {
                        l_strSuccTradingType[j] = l_objSuccTradingType[j].toString();
                    }
                    
                    l_tradingInfo.succTradingTypeList = l_strSuccTradingType;
                    l_lisTradingInfo.add(l_tradingInfo);
                }

                                
            }
        }
        //４）　@ArrayList.toArray()の戻り値を返却する。
        WEB3SuccTradingInfo[] l_tradingInfos = new WEB3SuccTradingInfo[l_lisTradingInfo.size()];
        l_lisTradingInfo.toArray(l_tradingInfos);
        
        log.exiting(STR_METHOD_NAME);
        return l_tradingInfos;       

    }
    
    /**
     * (get反対売買取引)<BR>
     * 引数の注文種別、商品区分に該当する反対売買取引を <BR>
     * 返却する。 <BR>
     * <BR>
     * １）　@反対売買取引区分一覧の取得 <BR>
     * 　@連続注文マネージャImpl.get反対売買取引()をコールする。 <BR>
     * <BR>
     * 　@[get反対売買取引()に指定する引数] <BR>
     * 　@　@　@顧客：　@パラメータ.顧客 <BR>
     * 　@　@　@注文種別：　@パラメータ.注文種別 <BR>
     * <BR>
     * 　@戻り値がnullの場合、nullを返却し、終了する。 <BR>
     * <BR>
     * <BR>
     * ２）　@ArrayListを生成する。 <BR>
     * <BR>
     * ３）　@１）の戻り値の要素数分、以下の処理を繰り返す。 <BR>
     * 　@３?１）　@パラメータ.商品区分 == <BR>
     * 　@　@連続注文データ取得サービスImpl.get商品区分()の場合、 <BR>
     * 　@　@生成したArrayListに処理対象の要素を追加する。 <BR>
     * <BR>
     * 　@　@[get商品区分()に指定する引数] <BR>
     * 　@　@　@連続注文取引区分：　@処理対象の要素 <BR>
     * <BR>
     * ４）　@生成したArrayList.toArray()の戻り値を返却する。 <BR>
     * 　@　@　@※生成したArrayList.size() == 0の場合、nullを返却する。 <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別
     * @@param l_lisReturnTrades - (連続注文取引区分)<BR>
     * 連続注文取引区分<BR>
     * 
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 4327D5650261
     */
    protected String[] getReversingTrade(WEB3GentradeMainAccount l_mainAccount, OrderTypeEnum l_orderType, String l_strCommodityDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getReversingTrade(WEB3GentradeMainAccount l_mainAccount, OrderTypeEnum l_orderType, String l_strCommodityDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1）　@反対売買取引区分一覧の取得
        WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        //①@連続注文マネージャImpl.get反対売買取引()をコールする。
        String[] l_strTrades = l_orderManager.getReversingTrades(l_mainAccount, l_orderType);
        
        //戻り値がnullの場合、nullを返却し、終了する。
        if(l_strTrades == null)
        {
            return null;        	
        }

		//２）　@ArrayListを生成する。
		List l_lisReturnTrades = new ArrayList();

        //３）　@１）の戻り値の要素数分、以下の処理を繰り返す。
		for(int i = 0; i < l_strTrades.length; i++)
		{

            //３?１）　@パラメータ.商品区分 == 連続注文データ取得サービスImpl.get商品区分()の場合、
            //生成したArrayListに処理対象の要素を追加する。 <BR>
			WEB3ToSuccDataGettingServiceImpl toSuccDataGettingService = new WEB3ToSuccDataGettingServiceImpl();
			if(l_strCommodityDiv == toSuccDataGettingService.getCommodityDiv(l_strTrades[i]))
			{
				l_lisReturnTrades.add(l_strTrades[i]);
			}
		}

		String[] l_strReturnTrades = new String[l_lisReturnTrades.size()];
		l_lisReturnTrades.toArray(l_strReturnTrades);

        //生成したArrayList.size() == 0の場合、nullを返却する。
		if(l_lisReturnTrades.size() == 0)
		{
			return null;
		}
		
		//４）　@生成したArrayList.toArray()の戻り値を返却する。
		return l_strReturnTrades;

    }
}
@
