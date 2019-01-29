head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続設定一覧サービスImpl(WEB3ToSuccSettingListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 呉艶飛(中訊) 新規作成
Revesion History : 2006/11/24 李俊(中訊)　@ モデル No.185 No.203
Revesion History : 2007/01/17 徐宏偉(中訊)　@モデル No.223
Revesion History : 2007/12/17 趙林鵬(中訊) 仕様変更モデルNo.247
Revesion History : 2008/03/20 趙林鵬(中訊) 仕様変更モデルNo.258, 288
Revesion History : 2008/04/07 趙林鵬(中訊) 仕様変更モデルNo.313
Revesion History : 2008/04/08 トウ鋒鋼(中訊) QA UT-0011
Revesion History : 2008/04/18 趙林鵬(中訊) 仕様変更モデル327, QA FT-0021, 329
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ReserveOrderExistFlagDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.triggerorder.WEB3ToSuccClientRequestService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccKeyItemDef;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.triggerorder.message.WEB3SuccProductInfo;
import webbroker3.triggerorder.message.WEB3SuccSettingListRequest;
import webbroker3.triggerorder.message.WEB3SuccSettingListResponse;
import webbroker3.triggerorder.message.WEB3SuccSortKey;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingListService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.triggerorder.util.WEB3TriggerOrderUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (連続設定一覧サービスImpl)<BR>
 * 連続設定一覧サービス実装クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToSuccSettingListServiceImpl extends WEB3ToSuccClientRequestService implements WEB3ToSuccSettingListService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingListServiceImpl.class);
    
    /**
     * @@roseuid 4348E3D0002E
     */
    public WEB3ToSuccSettingListServiceImpl() 
    {
     
    }
    
    /**
     * 連続設定一覧サービス処理を行う。<BR>
     * <BR>
     * this.get一覧画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431CFFA10032
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ.リクエストデータがnullです。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccSettingListRequest)
        {
            l_response =
                this.getListScreen( (WEB3SuccSettingListRequest) l_request);
        }
        else
        {
            log.debug("パラメータ.リクエストデータの型が不正です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get一覧画面)<BR>
     * 連続設定一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連続設定一覧）get一覧画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 連続設定一覧リクエストオブジェクト<BR>
     * @@return WEB3SuccSettingListResponse
     * @@roseuid 431CFF9E038D
     */
    protected WEB3SuccSettingListResponse getListScreen(WEB3SuccSettingListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1リクエストデータの整合性をチェックする。
        l_request.validate();
        //1.2システムが緊急停止中、バッチ中であるかチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3メッセージ (*)リクエストデータ.商品区分一覧をソートする。
        String[] l_strTypeLists = l_request.commodityTypeList;
        
        String l_strAscDesc = l_request.sortKeys[0].ascDesc;
        
        String[] l_strCommodityTypeLists = new String[l_strTypeLists.length];
        if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
        {
            Arrays.sort(l_strTypeLists); 
            l_strCommodityTypeLists = l_strTypeLists;
        }
        else
        {
            Arrays.sort(l_strTypeLists); 
            for (int i = 0; i < l_strTypeLists.length; i++)
            {
                l_strCommodityTypeLists[i] = l_strTypeLists[l_strTypeLists.length -1 - i];
            }
        }
        
        List l_lisOptionProductInfo = new ArrayList();
        List l_lisFutureProductInfo = new ArrayList();
        //1.4注文データを格納するArrayListを生成する。
        List l_lisOrderData = new ArrayList();
        WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(WEB3ToSuccDataGettingService.class);
        //1.5メッセージ (*)ソートした商品区分の要素数分Loop処理
        WEB3GentradeSubAccount l_subAccount = null;
        for (int i = 0; i < l_strCommodityTypeLists.length; i++)
        {
            String l_strCommodityType = l_strCommodityTypeLists[i];
            String l_strfuturesOptionDiv = null;
            ProductTypeEnum l_productType = null;
            try
            {
                //1.5.1補助口座を取得する。
                l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount(l_strCommodityTypeLists[i]);
                //証券会社コード：　@get口座()の戻り値.証券会社コード 
                String l_strInstitution = l_subAccount.getInstitution().getInstitutionCode();
                
 
                //銘柄タイプ： 
                //　@[商品区分 == ("現物株式" or "信用取引")の場合] 
                //　@　@ProductTypeEnum.株式 
                //　@[上記以外の場合] 
                //　@ProductTypeEnum.先物オプション         

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
                //1.5.2連続注文が取扱可能であるかチェックする。
                l_orderManager.validateSuccOrderHandling(l_strInstitution, l_productType, l_strfuturesOptionDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getErrorMessage());
                continue;
            }
            
            //1.5.3ソート条件を作成する。
            String l_strSortCond = this.createSortCond(l_strCommodityType, l_request.sortKeys);
            
            List l_lisOrderUnit = new ArrayList();

            //1.5.4(*)商品区分 == ("現物株式" or "信用取引")の場合
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
            {
                //1.5.4.1検索条件文字列を作成する。
                //(銘柄コードプルダウンを作成する為、検索条件は最小限とする)
                String l_strQueryString = this.createQueryString(l_strCommodityType, l_request.productCode, l_request.orderBizDate, l_subAccount);
                //1.5.4.2検索条件データコンテナを作成する。
                String[] l_strQueryContainer = 
                    this.createQueryContainer(
                        (WEB3GentradeInstitution)l_subAccount.getInstitution(), 
                        l_subAccount, 
                        l_strCommodityType, 
                        l_request.productCode, 
                        l_request.orderBizDate);
                //1.5.4.3Eqtypeの注文単位一覧を取得する。
                l_lisOrderUnit = 
                    l_dataGettingService.getOrderUnitList(l_subAccount, ProductTypeEnum.EQUITY, l_strQueryString, l_strQueryContainer, l_strSortCond);
                if (l_lisOrderUnit == null || l_lisOrderUnit.isEmpty())
                {
                    continue;
                }
            }
            //1.5.5(*)商品区分 == ("先物" or "オプション")の場合
            else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType) 
                || WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
            {
                //1.5.5.1検索条件文字列を作成する。
                //(銘柄コードプルダウンを作成する為、検索条件は最小限とする)
                String l_strQueryString = this.createQueryString(l_strCommodityType, null,null, l_subAccount);
                //1.5.5.2検索条件データコンテナを作成する。(銘柄コードプルダウンを作成する為、検索条件は最小限とする)

                String[] l_strQueryContainer = 
                    this.createQueryContainer(
                        (WEB3GentradeInstitution)l_subAccount.getInstitution(), 
                        l_subAccount, 
                        l_strCommodityType, 
                        null, 
                        null);
                
                //1.5.5.3Ifoの注文単位一覧を取得する。
                List l_lisOrderUnitList = 
                    l_dataGettingService.getOrderUnitList(l_subAccount, ProductTypeEnum.IFO, l_strQueryString, l_strQueryContainer, l_strSortCond);
                
                if (l_lisOrderUnitList == null || l_lisOrderUnitList.isEmpty())
                {
                    continue;
                }
                
                //1.5.5.4先物 or オプションの銘柄情報を作成する。※先物とオプションとで別の変数を定義し、結果を別々に保持すること。
                WEB3SuccProductInfo[] l_productInfo = this.createIfoProductInfo(l_lisOrderUnitList);
                
                if (l_productInfo != null)
                {
                    if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
                    {
                        for (int j = 0; j < l_productInfo.length; j++)
                        {
                            l_lisOptionProductInfo.add(l_productInfo[j]);
                        }
                    }
                    else
                    {
                        for (int j = 0; j < l_productInfo.length; j++)
                        {
                            l_lisFutureProductInfo.add(l_productInfo[j]);
                        }
                    }
                }
                String l_datBizdate = null;
                
                if (l_request.orderBizDate != null)
                {
                    l_datBizdate = WEB3DateUtility.formatDate(l_request.orderBizDate, "yyyyMMdd");
                }
               
                //1.5.5.5入力された検索条件に該当するIfo注文単位の一覧を取得する。
                l_lisOrderUnit = 
                    this.getDisplayObjIfoOrderUnit(l_lisOrderUnitList, (WEB3GentradeInstitution)l_subAccount.getInstitution(), l_request.productCode, l_datBizdate);
                
                if (l_lisOrderUnit == null || l_lisOrderUnit.isEmpty())
                {
                    continue;
                }
            }

            l_lisOrderData.addAll(l_lisOrderUnit);
        }
         //業務日付を、レスポンス.発注日一覧にセットする
        Date l_datOrderBizdate = GtlUtils.getTradingSystem().getBizDate();

        Collection l_lstorderBizDate = new ArrayList();
        l_lstorderBizDate.add(l_datOrderBizdate);
        
        boolean l_blnIsEqtype = false;
        for (int i = 0; i < l_strCommodityTypeLists.length; i++)
        {	
            //リクエストデータ.商品区分一覧に"現物株式" or "信用取引"が含まれる場合
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityTypeLists[i]) 
                || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityTypeLists[i]))
            {
            	l_blnIsEqtype = true;
            }
            //商品区分 == ("先物" or "オプション")の場合
            else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityTypeLists[i])
                || WEB3CommodityDivDef.OPTION.equals(l_strCommodityTypeLists[i]))
            {
                String l_strFuturesOptionDiv = null;
                //先物／オプション区分: 商品区分の要素
                if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityTypeLists[i]))
                {
                    l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
                }
                else
                {
                    l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.OPTION;
                }

                // get（部店指数別）取扱条件
                //証券会社コード： this.get口座()の戻り値.getBranch().getInstitution().証券会社コード
                //部店コード：　@this.get口座()の戻り値.getBranch().部店コード
                //先物／オプション区分：　@商品区分の要素
                WEB3GentradeBranchIndexDealtCond[] l_branchIndexes =
                    WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                        this.getMainAccount().getBranch().getInstitution().getInstitutionCode(),
                        this.getMainAccount().getBranch().getBranchCode(),
                        l_strFuturesOptionDiv);

                // reset市場コード(市場コード : String)
                //市場コード：　@"DEFAULT"
                WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

                // reset受付時間区分(受付時間区分 : String)
                //受付時間区分：　@"株価指数先物OP"
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

                //（部店指数別）取扱条件要素ごとのLoop処理
                for (int j = 0; j < l_branchIndexes.length; j++)
                {
                    // reset銘柄コード(銘柄コード : String)
                    //銘柄コード：　@（部店指数別）取扱条件.原資産銘柄コード
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                        l_branchIndexes[j].getUnderlyingProductCode());

                    //get発注日( )
                    Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

                    //取得した取扱条件要素毎の発注日に、レスポンス.発注日一覧に含まれていない日付がある場合は
                    //レスポンス.発注日一覧に追加する。
                    if (!l_lstorderBizDate.contains(l_datBizDate))
                    {
                        l_lstorderBizDate.add(l_datBizDate);
                    }
                }
            }
        }
        if(l_blnIsEqtype)
        {
            //get取扱可能市場(部店 : 部店, 銘柄タイプ : ProductTypeEnum) 
            //[引数]
            // 部店：　@this.get口座()の戻り値.getBranch()
            // 銘柄タイプ：　@"株式"
            String[] l_strMarketList = 
            	WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                	(WEB3GentradeBranch)this.getMainAccount().getBranch(),
					ProductTypeEnum.EQUITY);
            //reset受付時間区分
            //[引数] 
			// 受付時間区分：　@"株式・信用" 
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            //(*)get取扱可能市場()の戻り値の要素数分Loop処理
            for (int i = 0; i < l_strMarketList.length; i++ )
            {
            	//reset市場コード(市場コード : String)
            	//[引数] 
            	//市場コード：　@市場コード一覧.市場コード
            	WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketList[i]);
            	
            	//get発注日( )
            	Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            	
            	//取得した市場毎の発注日に、レスポンス.発注日一覧に含まれていない日付がある場合は、
            	//レスポンス.発注日一覧に追加する。
                if (!l_lstorderBizDate.contains(l_datBizDate))
                {
                    l_lstorderBizDate.add(l_datBizDate);
                }
            }
        }
        int l_intListSize = l_lstorderBizDate.size();
        Date[] l_orderBizDates= new Date[l_intListSize];
        l_lstorderBizDate.toArray(l_orderBizDates);
        
        //1.6(*)生成したArrayList.size() == 0の場合
        if (l_lisOrderData.size() == 0)
        {
            //1.6.1レスポンスデータを生成する。
            WEB3SuccSettingListResponse l_response = (WEB3SuccSettingListResponse)l_request.createResponse();
            
            l_response.orderBizDateList = l_orderBizDates;
            
            return l_response;
        }
        
        //1.7予約注文の一覧を取得する。
        //口座ID：　@get補助口座()の戻り値.口座ID。
        //商品区分一覧：　@リクエストデータ.商品区分一覧
        Hashtable l_hasOrderUnit =
            l_dataGettingService.getRsvOrderUnitList(
                l_subAccount.getAccountId(),
                l_request.commodityTypeList);
        //1.8WEB3PageIndexInfoインスタンスを生成する。
        WEB3PageIndexInfo l_info = new WEB3PageIndexInfo(l_lisOrderData, Integer.parseInt(l_request.pageIndex), Integer.parseInt(l_request.pageSize));
        //1.9画面表示対象のOrderUnitの配列を生成する。
        Object[] l_objReturned = l_info.getArrayReturned(OrderUnit.class);
        //1.10連続注文明細を格納するArrayListを生成する。
        List l_lisOrderUnit = new ArrayList();
        //1.11メッセージ (*)getArrayReturned()の戻り値の要素数分Loop処理
        
        if (l_objReturned != null)
        {
            for (int i = 0; i < l_objReturned.length; i++)
            {
                //1.11.1連続注文明細インスタンスを生成する。
                WEB3SuccOrderUnit l_orderUnit = new WEB3SuccOrderUnit();
                
                OrderUnit l_unit = (OrderUnit)l_objReturned[i];
                //1.11.2連続注文明細にプロパティをセットする。 
                l_dataGettingService.createSuccOrderUnit(l_orderUnit, l_unit, true);
                //1.11.3メッセージ (*)前提注文に設定されている予約注文の一覧を取得する。
                ProductTypeEnum l_productType = l_unit.getProduct().getProductType(); 
                
                String l_strKey = WEB3TriggerOrderUtility.createKey(l_unit.getOrderId(), l_productType);
                List l_lisrsvOrderUnit = (ArrayList)l_hasOrderUnit.get(l_strKey); 
				if (l_lisrsvOrderUnit != null)
				{
					//1.11.4メッセージ (*)予約注文(=get()の戻り値)が取得できた場合
					//予約注文明細を作成する。
                    //前提注文明細：　@生成した連続注文明細
                    //予約注文Row一覧：　@取得した予約注文の一覧
                    //is可能フラグ設定：　@true（設定する）
                    if (l_lisrsvOrderUnit.get(0) instanceof RsvEqOrderUnitRow)
                    {
                        RsvEqOrderUnitRow[] l_preOrderUnit = new RsvEqOrderUnitRow[l_lisrsvOrderUnit.size()];
                        l_lisrsvOrderUnit.toArray(l_preOrderUnit);
                        l_dataGettingService.createRsvOrderUnit(l_orderUnit, l_preOrderUnit, true);
                    }
                    else if (l_lisrsvOrderUnit.get(0) instanceof RsvIfoOrderUnitRow)
                    {
                        RsvIfoOrderUnitRow[] l_preOrderUnit = new RsvIfoOrderUnitRow[l_lisrsvOrderUnit.size()];
                        l_lisrsvOrderUnit.toArray(l_preOrderUnit);
                        l_dataGettingService.createRsvOrderUnit(l_orderUnit, l_preOrderUnit, true);
                    }
				}
					//1.11.5ArrayListに生成した連続注文明細を追加する。 
				l_lisOrderUnit.add(l_orderUnit);
            }                      
        }
        
        WEB3SuccOrderUnit[] l_orderUnits = new WEB3SuccOrderUnit[l_lisOrderUnit.size()];
        //1.12連続注文明細の配列を生成する。
        l_lisOrderUnit.toArray(l_orderUnits);
        //1.13レスポンスデータを生成する。
        WEB3SuccSettingListResponse l_response = (WEB3SuccSettingListResponse)l_request.createResponse();
        //1.14メッセージ (*)プロパティセット
        //発注日一覧　@    ＝　@当日営業日(*1)、 翌日営業日を要素とする配列
        l_response.orderBizDateList = l_orderBizDates;

        l_response.orderUnitsList = l_orderUnits;
        //表示ページ番号   ＝　@WEB3PageIndexInfo.getPageIndex()
        l_response.pageIndex = "" + l_info.getPageIndex();
        //総ページ数 ＝　@WEB3PageIndexInfo.getTotalPages()
        l_response.totalPages = "" + l_info.getTotalPages();
        //総レコード数    ＝　@WEB3PageIndexInfo.getTotalRecords()
        l_response.totalRecords = "" + l_info.getTotalRecords();
        //先物銘柄情報    ＝　@先物のcreate先物OP銘柄情報()の   
        if (!l_lisFutureProductInfo.isEmpty())
        {
            WEB3SuccProductInfo[] l_succProductFuturesInfo = new WEB3SuccProductInfo[l_lisFutureProductInfo.size()];
            l_lisFutureProductInfo.toArray(l_succProductFuturesInfo);        
            l_response.futuresProductInfo = l_succProductFuturesInfo;
        }
        else
        {
            l_response.futuresProductInfo = null;
        }        
        //オプション銘柄情報 ＝　@オプションのcreate先物OP銘柄情報()の戻り値
        if (!l_lisOptionProductInfo.isEmpty())
        {
            WEB3SuccProductInfo[] l_succProductOptionInfo = new WEB3SuccProductInfo[l_lisOptionProductInfo.size()];
            l_lisOptionProductInfo.toArray(l_succProductOptionInfo);        
            l_response.optionsProductInfo = l_succProductOptionInfo;
        }
        else
        {
            l_response.optionsProductInfo = null;
        }        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 各注文単位テーブルを検索する為の、<BR>
     * 検索条件を作成する。<BR>
     * <BR>
     * ※ 先物オプションはパラメータ.発注日及びパラメータ.銘柄コードに<BR>
     * ※ （引数設定で）常にnullが設定される。<BR>
     * <BR>
     * １）　@発注日条件を作成する。<BR>
     * 　@[パラメータ.発注日 != nullの場合]<BR>
     * 　@　@検索条件文字列 = "biz_date = ? "<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@検索条件文字列 = "biz_date >= ? "<BR>
     * <BR>
     * 　@※ パラメータ.商品区分が、"先物"or"オプション"の場合、<BR>
     * 　@※ 検索条件文字列の先頭に"and "を設定する。<BR>
     * <BR>
     * ２）　@パラメータ.銘柄コード != nullの場合、<BR>
     * 　@銘柄条件を追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and product_id = ? "<BR>
     * <BR>
     * ３）　@各商品ごとの条件を追加する。<BR>
     * 　@パラメータ.商品区分が、<BR>
     * 　@["現物株式"の場合]<BR>
     * 　@　@検索条件文字列 += "and order_type in (?, ?) "<BR>
     * <BR>
     * 　@["信用取引の場合]<BR>
     * 　@　@検索条件文字列 += "and order_categ in (?, ?, ?) "<BR>
     * <BR>
     * 　@["先物" or "オプション"の場合]<BR>
     * 　@　@検索条件文字列 += "and future_option_div = ? "<BR>
     * <BR>
     * ４）　@表示対象となる注文の条件を追加する。<BR>
     * 　@[条件]<BR>
     * 　@　@(注文有効状態 = "オープン" or<BR>
     * 　@　@ (注文有効状態 = "クローズ" and <BR>
     * 　@　@　@予約注文設定フラグ = "設定の可能性あり"))<BR>
     * 　@　@and 連続注文の発行により作成された注文でない。<BR>
     * <BR>
     * 　@検索条件文字列 += "and (order_open_status = ? or "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(order_open_status = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "reserve_order_exist_flag = ?)) "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and order_id not in " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@[パラメータ.商品区分が"現物株式" or "信用取引"の場合] <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rsv_eq_order_unit " <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@[上記以外の場合] <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rsv_ifo_order_unit " <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "where account_id =?)" <BR>
     * <BR>
     * ５）　@市場条件を追加する。 <BR>
     * 　@（検索注文対象外とする市場をセット） <BR>
     * <BR>
     * 　@検索条件文字列 += "and market_id not in (?, ?, ・・・・・・) "（*1） <BR>
     * <BR>
     * （*1）”?”の数は、（部店市場別・PTS）取扱条件.get取扱可能市場()で取得した <BR>
     * 　@配列の要素数分設定する。  <BR>
     * <BR>
     * 　@　@[get取扱可能市場()の引数設定]  <BR>
     * 　@　@部店：　@パラメータ.補助口座.get取引店()の戻り値  <BR>
     * 　@　@銘柄タイプ：　@"株式" <BR>
     * <BR>
     * ６）　@作成した検索条件文字列を返却する。<BR>
     * @@param l_strCommodityDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * 
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return String
     * @@roseuid 43263A0B0366
     */
    protected String createQueryString(
            String l_strCommodityDiv,
            String l_strProductCode,
            Date l_datBizDate,
            WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "createQueryString(String l_strCommodityDiv, String l_strProductCode, Date l_datBizDate, WEB3GentradeSubAccount l_subAccount) ";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("パラメータ.リクエストデータがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        StringBuffer l_sbQueryString = new StringBuffer();

        //　@※ パラメータ.商品区分が、"先物"or"オプション"の場合、
        //  ※ 検索条件文字列の先頭に"and "を設定する。
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv)
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            // １）　@発注日条件を作成する。
            // 　@[パラメータ.発注日 != nullの場合]
            // 　@　@検索条件文字列 = "biz_date = ? "
            if (l_datBizDate != null)
            {
                l_sbQueryString.append(" and biz_date = ? ");
            }
            //[上記以外の場合]
            //検索条件文字列 = "biz_date >= ? "
            else
            {
                l_sbQueryString.append(" and biz_date >= ? ");
            }
        }
        else
        {
            // １）　@発注日条件を作成する。
            // 　@[パラメータ.発注日 != nullの場合]
            // 　@　@検索条件文字列 = "biz_date = ? "
            if (l_datBizDate != null)
            {
                l_sbQueryString.append("biz_date = ? ");
            }
            //[上記以外の場合]
            //検索条件文字列 = "biz_date >= ? "
            else
            {
                l_sbQueryString.append("biz_date >= ? ");
            }
        }

        /*
         * ２）　@パラメータ.銘柄コード != nullの場合、
         * 　@銘柄条件を追加する。
         * 　@検索条件文字列 += "and product_id = ? "
         */
        if (l_strProductCode != null)
        {
            l_sbQueryString.append("and product_id = ? ");
        }
        /*
         * ３）　@各商品ごとの条件を追加する。
         * 　@パラメータ.商品区分が、
         * 　@["現物株式"の場合]
         * 　@　@検索条件文字列 += "and order_type in (?, ?) "
         */
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("and order_type in (?, ?) ");
        }
        
        // 　@["信用取引の場合]
        // 　@　@検索条件文字列 += "and order_categ in (?, ?, ?) "
        if (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("and order_categ in (?, ?, ?) ");
        }
        
        // 　@["先物" or "オプション"の場合]
        //検索条件文字列 += "and future_option_div = ? "
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv) 
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("and future_option_div = ? ");
        }
        
        //４）　@表示対象となる注文の条件を追加する。
        l_sbQueryString.append("and (order_open_status = ? or " 
                + "(order_open_status = ? and " 
                + "reserve_order_exist_flag = ?))");

        //+ "and order_id not in "
        l_sbQueryString.append("and order_id not in");

        //[パラメータ.商品区分が"現物株式" or "信用取引"の場合] + "(select order_id from rsv_eq_order_unit "
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) 
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("(select order_id from rsv_eq_order_unit ");
        }
        //[上記以外の場合]+ "(select order_id from rsv_ifo_order_unit "
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv) 
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("(select order_id from rsv_ifo_order_unit ");
        }
        //+ "where account_id =?)"
        l_sbQueryString.append("where account_id =?)");

        //５）　@市場条件を追加する。
        String[] l_strMarketCodes = 
            WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY);

        int l_intMarketCodesCnt = l_strMarketCodes.length;

        if (l_intMarketCodesCnt > 0)
        {
            StringBuffer l_sbQueryMarketCodes = new StringBuffer();
 
            for (int i = 0; i < l_intMarketCodesCnt; i++)
            {
                if (l_sbQueryMarketCodes.length() != 0)
                {
                    l_sbQueryMarketCodes.append(", ");
                }
                l_sbQueryMarketCodes.append("?");

            }
            l_sbQueryString.append(" and market_id not in (" + l_sbQueryMarketCodes.toString() + ")");
        }

        //６）　@作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 各注文単位テーブルを検索する為の<BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@発注日条件を追加する。<BR>
     * 　@[パラメータ.発注日 != nullの場合]<BR>
     * 　@　@パラメータ.発注日をArrayListに追加する。<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@業務日付をArrayListに追加する。<BR>
     * <BR>
     * ３）　@パラメータ.銘柄コード != nullの場合、<BR>
     * 　@銘柄コードに該当する銘柄IDをArrayListに追加する。<BR>
     * <BR>
     * 　@※銘柄は、連続注文データ取得サービスImpl.get銘柄()にて取得する。<BR>
     * <BR>
     * 　@　@[get銘柄()に指定する引数]<BR>
     * 　@　@　@証券会社：　@パラメータ.証券会社<BR>
     * 　@　@　@銘柄コード：　@パラメータ.銘柄コード<BR>
     * 　@　@　@商品区分：　@パラメータ.商品区分<BR>
     * <BR>
     * ４）　@各商品ごとの条件をArrayListに追加する。<BR>
     * 　@パラメータ.商品区分が、<BR>
     * 　@["現物株式"の場合]<BR>
     * 　@　@・OrderTypeEnum.現物買注文<BR>
     * 　@　@・OrderTypeEnum.現物売注文<BR>
     * <BR>
     * 　@["信用取引の場合]<BR>
     * 　@　@・OrderCategEnum.新規建注文<BR>
     * 　@　@・OrderCategEnum.返済注文<BR>
     * 　@　@・OrderCategEnum.現引現渡注文<BR>
     * <BR>
     * 　@["先物"の場合]<BR>
     * 　@　@・"先物"（先物オプション区分）<BR>
     * <BR>
     * 　@["オプション"の場合]<BR>
     * 　@　@・"オプション"（先物オプション区分）<BR>
     * <BR>
     * ５）　@表示対象となる注文の条件をArrayListに追加する。<BR>
     * 　@①@"オープン"(注文有効状態)<BR>
     * 　@②"クローズ"(注文有効状態)<BR>
     * 　@③"設定の可能性あり"(予約注文設定フラグ)<BR>
     * 　@④パラメータ.補助口座.口座ID<BR>
     * <BR>
     * ６）　@市場条件を追加する。 <BR>
     * 　@　@（検索注文対象外とする市場を取得する。） <BR>
     * <BR>
     * 　@　@６－１）　@（部店市場別・PTS）取扱条件..get取扱可能市場()をコールする。 <BR>
     * <BR>
     * 　@　@　@[get取扱可能市場()の引数設定] <BR>
     * 　@　@　@部店：　@パラメータ.補助口座.get取引店()の戻り値 <BR>
     * 　@　@　@銘柄タイプ：　@"株式" <BR>
     * <BR>
     * 　@　@６－２）　@６－１）で取得できた市場コードの配列要素数分、以下を繰り返す。 <BR>
     * <BR>
     * 　@拡張金融オブジェクトマネージャ.get市場().getMarketId()の戻り値をArrayListに追加する。 <BR>
     * <BR>
     * 　@　@　@[get市場()の引数設定] <BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社.getInstitutionCode()の戻り値 <BR>
     * 　@　@　@市場コード：　@６－１）の戻り値（市場コード配列）の要素 <BR>
     * <BR>
     * ７）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strCommodityDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * 
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@return String[]
     * @@roseuid 43263A0B0375
     */
    protected String[] createQueryContainer(
        WEB3GentradeInstitution l_institution, 
        WEB3GentradeSubAccount l_subAccount, 
        String l_strCommodityDiv, 
        String l_strProductCode, 
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createQueryString(String l_strCommodityDiv, String l_strProductCode, Date l_datBizDate) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@ArrayListを生成する。
        List l_lisQueryContainer = new ArrayList();
        
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(WEB3ToSuccDataGettingService.class);
        
        /*
         * ２）　@発注日条件を追加する。
         * 　@[パラメータ.発注日 != nullの場合]
         * 　@　@パラメータ.発注日をArrayListに追加する。
         */
        if (l_datBizDate != null)
        {
            l_lisQueryContainer.add(WEB3DateUtility.formatDate((l_datBizDate), "yyyyMMdd"));
        }
        //[上記以外の場合]業務日付(*1)をArrayListに追加する。
        else
        {
            l_lisQueryContainer.add(WEB3DateUtility.formatDate((GtlUtils.getTradingSystem().getBizDate()), "yyyyMMdd"));
        }
        //３）　@パラメータ.銘柄コード != nullの場合、銘柄コードに該当する銘柄IDをArrayListに追加する。
        if (l_strProductCode != null)
        {
            Product l_product = l_dataGettingService.getProduct(l_institution, l_strProductCode, l_strCommodityDiv);
            l_lisQueryContainer.add(new Long(l_product.getProductId()).toString());
        }
        
        /*
         * ４）　@各商品ごとの条件をArrayListに追加する。
         * 　@パラメータ.商品区分が、<BR>
         * 　@["現物株式"の場合]<BR>
         * 　@　@・OrderTypeEnum.現物買注文<BR>
         * 　@　@・OrderTypeEnum.現物売注文<BR>
         */
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv))
        {
            l_lisQueryContainer.add(Integer.toString(OrderTypeEnum.EQUITY_BUY.intValue()));
            l_lisQueryContainer.add(Integer.toString(OrderTypeEnum.EQUITY_SELL.intValue()));
        }
        
        /*
         * 　@["信用取引の場合]<BR>
         * 　@　@・OrderCategEnum.新規建注文<BR>
         * 　@　@・OrderCategEnum.返済注文<BR>
         * 　@　@・OrderCategEnum.現引現渡注文<BR>
         */
        if (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            l_lisQueryContainer.add(Integer.toString(OrderCategEnum.OPEN_MARGIN.intValue()));
            l_lisQueryContainer.add(Integer.toString(OrderCategEnum.CLOSE_MARGIN.intValue()));
            l_lisQueryContainer.add(Integer.toString(OrderCategEnum.SWAP_MARGIN.intValue()));
        }
        
        // 　@["先物"の場合]・"先物"（先物オプション区分）
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv))
        {
            l_lisQueryContainer.add(WEB3FuturesOptionDivDef.FUTURES);
        }
        //["オプション"の場合]<BR>・"オプション"（先物オプション区分）
        if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            l_lisQueryContainer.add(WEB3FuturesOptionDivDef.OPTION);
        }
        
        /*
         * ５）　@表示対象となる注文の条件をArrayListに追加する。
         * 　@①@"オープン"(注文有効状態)
         * 　@②"クローズ"(注文有効状態)
         * 　@③"設定の可能性あり"(予約注文設定フラグ)
         * 　@④パラメータ.補助口座.口座ID
         */
        l_lisQueryContainer.add(Integer.toString(OrderOpenStatusEnum.OPEN.intValue()));
        l_lisQueryContainer.add(Integer.toString(OrderOpenStatusEnum.CLOSED.intValue()));        
        l_lisQueryContainer.add(WEB3ReserveOrderExistFlagDef.SET_POSSIBLE);        
        l_lisQueryContainer.add(new Long(l_subAccount.getAccountId()).toString());
        
        // ６）　@市場条件を追加する。 （検索注文対象外とする市場を取得する。）
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //証券会社コード
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        //市場コード
        String[] l_strMarketCodes =
            WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY);

        for(int i = 0; i < l_strMarketCodes.length; i++)
        {
            try
            {
                long l_lngMarketId =
                    l_finObjManager.getMarket(l_strInstitutionCode, l_strMarketCodes[i]).getMarketId();
                l_lisQueryContainer.add("" + l_lngMarketId);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }

        //７）　@生成したArrayList.toArray()の戻り値を返却する。
        String[] l_strReturn = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }
    
    /**
     * (createソート条件)<BR>
     * 各注文単位テーブルの検索結果のソート条件を作成する。<BR>
     * <BR>
     * １）　@パラメータ.ソートキー.要素数 == 1の場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * ２）　@パラメータ.ソートキーの要素数分、対応するテーブルの列物理名、<BR>
     * 　@　@昇順／降順指定を付加しセットする。<BR>
     * 　@　@※開始index = 1とする。<BR>
     * 　@　@　@(0番目には"商品区分"が格納されている為)<BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り<BR>
     * 　@　@　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照<BR>
     * <BR>
     * 　@　@　@　@変換前　@　@　@　@　@　@　@　@　@変換後<BR>
     * -------------　@　@　@　@-----------------------------<BR>
     * 　@　@　@　@・銘柄コード 　@　@　@　@　@ ：注文単位テーブル．銘柄ID<BR>
     * 　@　@　@　@・市場コード 　@　@　@　@　@ ：注文単位テーブル．市場ID<BR>
     * 　@　@　@　@・口座区分(*2)　@ 　@　@ ：注文単位テーブル．税区分<BR>
     * 　@　@　@　@・取引区分　@　@　@　@　@　@ ：注文単位テーブル．注文種別<BR>
     * 　@　@　@　@・弁済区分(*2)　@　@　@  ：注文単位テーブル．弁済区分<BR>
     * 　@　@　@　@・値段条件(*2)　@　@ 　@ ：注文単位テーブル．値段条件<BR>
     * 　@　@　@　@・執行条件(*2) 　@　@　@ ：注文単位テーブル．執行条件<BR>
     * 　@　@　@　@・発注条件区分(*2)  ：(*1)<BR>
     * 　@　@　@　@・注文時間 　@　@　@　@　@  ：(*3)<BR>
     * 　@　@　@　@・発注日 　@　@　@　@　@　@　@ ：注文単位テーブル．発注日<BR>
     * 　@　@　@　@・注文有効期限 　@　@  ：注文単位テーブル．注文失効日付<BR>
     * <BR>
     * 　@(*1) nvl（注文単位テーブル.元発注条件, 注文単位テーブル.発注条件）を設定する。<BR>
     * 　@　@　@※注文単位テーブル.元発注条件に値が設定されている場合は、<BR>
     * 　@　@　@　@　@　@元発注条件をもとにソートする<BR>
     * 　@　@　@　@元発注条件に値が設定されていない場合は、<BR>
     * 　@　@　@　@　@　@注文単位テーブル.発注条件をもとにソートする<BR>
     * <BR>
     * 　@(*2) パラメータ.商品区分 == "現物株式" or "信用取引"の場合のみ作成<BR>
     * <BR>
     * 　@(*3) パラメータ.商品区分 == "現物株式" or "信用取引"の場合、<BR>
     * 　@　@　@　@　@注文単位テーブル．作成日付を設定。<BR>
     * 　@      パラメータ.商品区分 == "先物" or "オプション"の場合、<BR>
     * 　@　@　@　@　@注文単位テーブル．受注日時を設定。<BR>
     * <BR>
     * 　@・昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定<BR>
     * <BR>
     * ３）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加する。<BR>
     * <BR>
     * ４）　@作成したソート条件文字列を返却する。<BR>
     * @@param l_strCommodityDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     * 
     * @@param l_sortKey - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43263A0B0385
     */
    protected String createSortCond(String l_strCommodityDiv, WEB3SuccSortKey[] l_sortKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSortCond(String l_strCommodityDiv, WEB3SuccSortKey l_sortKey)  ";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKey == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@パラメータ.ソートキー.要素数 == 1の場合、nullを返却する。
        if (l_sortKey.length == 1)
        {
            return null;
        }
        StringBuffer l_strReturn = new StringBuffer();
        String l_strEqualsReturn = null;

        //返還の値の設定        
        for (int i = 1; i < l_sortKey.length; i++)
        {
            log.debug(" キー項目" + i + " = "+ l_sortKey[i].keyItem);
            log.debug(" 昇順／降順" + i + " = "+ l_sortKey[i].keyItem);
            //銘柄コード 　@　@　@　@　@：注文単位テーブル．銘柄ID
            if(WEB3ToSuccKeyItemDef.PRODUCT_CODE.equals(l_sortKey[i].keyItem))
            {
                l_strReturn.append("product_id");
            }
            //・市場コード 　@　@　@　@　@：注文単位テーブル．市場ID
            else if(WEB3ToSuccKeyItemDef.MARKET_CODE.equals(l_sortKey[i].keyItem))
            {
                l_strReturn.append("market_id");
            }
            //口座区分(*)　@ 　@　@　@：注文単位テーブル．税区分
            else if(WEB3ToSuccKeyItemDef.TAX_TYPE.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("tax_type");
                }
            }
            //取引区分　@　@　@　@　@　@：注文単位テーブル．注文種別
            else if(WEB3ToSuccKeyItemDef.TRADING_TYPE.equals(l_sortKey[i].keyItem))
            {
                l_strReturn.append("order_type");
            }
            //弁済区分(*)　@　@　@　@ ：注文単位テーブル．弁済区分
            else if(WEB3ToSuccKeyItemDef.REPAYMENT_DIV.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("repayment_type");
                }
            }
            //・値段条件(*)　@　@　@　@：注文単位テーブル．値段条件
            else if(WEB3ToSuccKeyItemDef.PRICE_COND_TYPE.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("price_condition_type");
                }
            }
            //・執行条件 　@　@　@　@　@　@：注文単位テーブル．執行条件
            else if(WEB3ToSuccKeyItemDef.EXEC_COND_TYPE.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv)
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("execution_condition_type");
                }
            }
            //・発注条件区分　@　@　@：
            //　@　@　@(*1) nvl（注文単位テーブル.元発注条件, 注文単位テーブル.発注条件）を設定する。
            else if(WEB3ToSuccKeyItemDef.ORDER_COND_TYPE.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv)
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("nvl(org_order_condition_type, order_condition_type)");
                }
            }
            //・注文時間 　@　@　@　@　@　@：注文単位テーブル．作成日付
            else if(WEB3ToSuccKeyItemDef.ORDER_DATE.equals(l_sortKey[i].keyItem))
            {
                //パラメータ.商品区分 == "現物株式" or "信用取引"の場合、
                //注文単位テーブル．作成日付を設定
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv)
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("created_timestamp");
                }
                //パラメータ.商品区分 == "先物" or "オプション"の場合
                //注文単位テーブル．受注日時を設定。
                else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv)
                    || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("received_date_time");
                }
            }
            //・発注日 　@　@　@　@　@　@　@：注文単位テーブル．発注日
            else if (WEB3ToSuccKeyItemDef.ORDER_BIZ_DATE.equals(l_sortKey[i].keyItem)) 
            {
                l_strReturn.append("biz_date");
            }
            //・注文有効期限 　@　@　@：注文単位テーブル．注文失効日付
            else if(WEB3ToSuccKeyItemDef.EXPIRATION_DATE.equals(l_sortKey[i].keyItem))
            {
                l_strReturn.append("expiration_date");
            }
            else
            {
                continue;
            }

            if (l_strReturn.length() != 0)
            {
                if (!l_strReturn.toString().equals(l_strEqualsReturn))
                {
                    l_strReturn.append(" ");
                    if(WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
                    {
                        l_strReturn.append("ASC");
                    }
                    else
                    {
                        l_strReturn.append("DESC");
                    }

                    l_strReturn.append(" , ");
                }
                l_strEqualsReturn = l_strReturn.toString();
            }
        }
        
        //３）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加する。
        l_strReturn.append("last_updated_timestamp");
        l_strReturn.append(" ");
        l_strReturn.append("ASC");
        
        //４）　@作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
    }
    
    /**
     * (get表示対象Ifo注文単位)<BR>
     * 引数の条件に該当するIfo注文単位のListを<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@パラメータ.銘柄コード == null And<BR>
     * 　@パラメータ.発注日 == nullの場合、<BR>
     * 　@パラメータ.注文単位一覧を返却し、終了する。<BR>
     * <BR>
     * ２）　@パラメータ.銘柄コード != nullの場合、<BR>
     * 　@銘柄IDを取得する。　@<BR>
     * 　@※先物OPプロダクトマネージャ.get銘柄()にて先物OP銘柄を取得する。<BR>
     * <BR>
     * 　@　@[get銘柄()に指定する引数]<BR>
     * 　@　@　@証券会社：　@パラメータ.証券会社<BR>
     * 　@　@　@銘柄コード：　@パラメータ.銘柄コード<BR>
     * <BR>
     * ３）　@ArrayListを生成する。<BR>
     * <BR>
     * ４）　@パラメータ.注文単位一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@４－１）　@パラメータ.銘柄コード != nullの場合、<BR>
     * 　@　@処理対象の要素(=IfoOrderUnit).銘柄ID != 取得した銘柄IDの場合、<BR>
     * 　@　@次の要素へ処理を移行する。(continue;)<BR>
     * <BR>
     * 　@４－２）　@パラメータ.発注日 != nullの場合、<BR>
     * 　@　@処理対象の要素(=IfoOrderUnit).発注日 != パラメータ.発注日の場合、<BR>
     * 　@　@次の要素へ処理を移行する。(continue;)<BR>
     * 　@　@※パラメータ.発注日は、YYYYMMDD形式にフォーマットすること。<BR>
     * <BR>
     * 　@４－３）　@生成したArrayListに処理対象の要素を追加する。<BR>
     * <BR>
     * ５）　@生成したArrayList.size() == 0の場合、nullを返却する。<BR>
     * 　@　@　@以外、生成したArrayListを返却する。<BR>
     * @@param l_lisOrderUnitList - (注文単位一覧)<BR>
     * 注文単位一覧)<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@return List
     * @@roseuid 431EC66202DD
     */
    protected List getDisplayObjIfoOrderUnit(
        List l_lisOrderUnitList, 
        WEB3GentradeInstitution l_institution, 
        String l_strProductCode, 
        String l_strBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDisplayObjIfoOrderUnit(List l_lisOrderUnitList, WEB3GentradeInstitution l_institution, String l_strProductCode, String l_datBizDate) ";
        log.entering(STR_METHOD_NAME);
        /*
         * １）　@パラメータ.銘柄コード == null And
         * 　@パラメータ.発注日 == nullの場合、
         * 　@パラメータ.注文単位一覧を返却し、終了する。
         */
        if (l_strProductCode == null && l_strBizDate == null)
        {
            return l_lisOrderUnitList;
        }
        
        //２）　@パラメータ.銘柄コード != nullの場合、銘柄IDを取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        long l_lngProductId = 0;
        if (l_strProductCode != null)
        {
            try
            {
                //先物OPプロダクトマネージャ.get銘柄()にて先物OP銘柄を取得する。
                WEB3IfoProductImpl l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_strProductCode);
                l_lngProductId = l_ifoProductImpl.getProductId(); 
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }            
        }
        //３）　@ArrayListを生成する。
        List l_lisReturn = new ArrayList();
        //４）　@パラメータ.注文単位一覧の要素数分、以下の処理を繰り返す。
        for (int i = 0; i < l_lisOrderUnitList.size(); i++)
        {
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_lisOrderUnitList.get(i);
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            /*
             * ４－１）　@パラメータ.銘柄コード != nullの場合、
             * 　@　@処理対象の要素(=IfoOrderUnit).銘柄ID != 取得した銘柄IDの場合、
             * 　@　@次の要素へ処理を移行する。(continue;)
             */
            if (l_strProductCode != null)
            {
                if (l_lngProductId != l_row.getProductId())
                {
                    continue;
                }
            }
            
            /*
             * ４－２）　@パラメータ.発注日 != nullの場合、
             * 　@　@処理対象の要素(=IfoOrderUnit).発注日 != パラメータ.発注日の場合、
             * 　@　@次の要素へ処理を移行する。(continue;)
             * 　@　@※パラメータ.発注日は、YYYYMMDD形式にフォーマットすること
             */
            if (l_strBizDate != null)
            {
                if (!l_row.getBizDate().equals(l_strBizDate))
                {
                    continue;
                }
            }
            
            //４－３）　@生成したArrayListに処理対象の要素を追加する。
            l_lisReturn.add(l_orderUnit);
        }

        
        //５）　@生成したArrayList.size() == 0の場合、nullを返却する。
        //以外、生成したArrayListを返却する。
        if (l_lisReturn.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturn;
    }
    
    /**
     * (create先物OP銘柄情報)<BR>
     * 引数の注文単位一覧より、<BR>
     * 先物OP銘柄情報を作成し、返却する。<BR>
     * <BR>
     * １）　@TreeMapを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.注文単位一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@２－１）　@処理対象の要素(=IfoOrderUnit)より銘柄IDを取得する。<BR>
     * <BR>
     * 　@２－２）　@TreeMap.get(銘柄ID) != nullの場合、<BR>
     * 　@　@次の要素へ処理を移行する。(continue)<BR>
     * <BR>
     * 　@２－３）　@先物OP銘柄を取得する。<BR>
     * 　@　@先物OPプロダクトマネージャ.getProduct(銘柄ID)をコールする。<BR>
     * <BR>
     * 　@２－４）　@連続注文銘柄情報インスタンスを生成し、<BR>
     * 　@　@以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@生成したインスタンス.銘柄コード = 先物OP銘柄.銘柄コード<BR>
     * 　@　@生成したインスタンス.銘柄名 = 先物OP銘柄.銘柄名<BR>
     * <BR>
     * 　@２－５）　@プロパティセットしたインスタンスをTreeMapに追加する。<BR>
     * 　@　@TreeMap.put()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[put()に指定する引数]<BR>
     * 　@　@　@key：　@銘柄ID<BR>
     * 　@　@　@value：　@プロパティセットしたインスタンス<BR>
     * <BR>
     * ３）　@生成したTreeMap.values().toArray()の戻り値を返却する。<BR>
     * @@param l_lisOrderUnitList - (注文単位一覧)<BR>
     * 注文単位一覧<BR>
     * @@return WEB3SuccProductInfo[]
     * @@roseuid 431ECD8A029E
     */
    protected WEB3SuccProductInfo[] createIfoProductInfo(List l_lisOrderUnitList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createIfoProductInfo(List l_lisOrderUnitList) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisOrderUnitList == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3ToSuccSettingListServiceImpl.class.getName() + STR_METHOD_NAME,
                "[l_lisOrderUnitList] = " + l_lisOrderUnitList
                );
        }
        //１）　@TreeMapを生成する。
        Map l_mapReturn = new TreeMap();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        //）　@パラメータ.注文単位一覧の要素数分、以下の処理を繰り返す。
        for (int i = 0; i <l_lisOrderUnitList.size(); i++)
        {
            WEB3SuccProductInfo l_productInfo = new WEB3SuccProductInfo();
            //２－１）　@処理対象の要素(=IfoOrderUnit)より銘柄IDを取得する。
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_lisOrderUnitList.get(i);
            long l_lngProductId = l_orderUnit.getProduct().getProductId();
            
            //２－２）　@TreeMap.get(銘柄ID) != nullの場合、次の要素へ処理を移行する。(continue)
            if (l_mapReturn.get(new Long(l_lngProductId)) != null)
            {
                continue;
            }
            //２－３）　@先物OP銘柄を取得する。先物OPプロダクトマネージャ.getProduct(銘柄ID)をコールする
            try
            {
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_ifoProductManagerImpl.getProduct(l_lngProductId);
                //２－４）　@連続注文銘柄情報インスタンスを生成し、以下のプロパティをセットする。
                //生成したインスタンス.銘柄コード = 先物OP銘柄.銘柄コード
                l_productInfo.productCode = l_ifoProductImpl.getProductCode();
                //生成したインスタンス.銘柄名 = 先物OP銘柄.銘柄名
                l_productInfo.productName = l_ifoProductImpl.getStandardName();
                
                //２－５）　@プロパティセットしたインスタンスをTreeMapに追加する。
                l_mapReturn.put(new Long(l_lngProductId), l_productInfo);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }
        //３）　@生成したTreeMap.values().toArray()の戻り値を返却する。
        WEB3SuccProductInfo[] l_productInfo = new WEB3SuccProductInfo[l_mapReturn.size()];
        l_mapReturn.values().toArray(l_productInfo);        
        return l_productInfo;
    }
}
@
