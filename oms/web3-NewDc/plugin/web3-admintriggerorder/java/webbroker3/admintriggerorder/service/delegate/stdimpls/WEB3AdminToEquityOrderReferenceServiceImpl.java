head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityOrderReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・株式注文照会サービスImpl(WEB3AdminToEquityOrderReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03　@呉艶飛(中訊) 新規作成
Revesion History : 2006/7/5 徐宏偉 (中訊) 仕様変更モデル076
Revesion History : 2006/7/5 肖志偉 (中訊) 仕様変更モデル077
Revesion History : 2006/10/18  唐性峰(中訊) 仕様変更モデルNo.096
Revesion History : 2006/10/23  唐性峰(中訊) 仕様変更モデルNo.085
Revesion History : 2006/10/30  柴双紅(中訊) 仕様変更モデルNo.100
Revesion History : 2006/11/20  黄建(中訊) 仕様変更モデルNo.101、102、104、105、107、110、111、118
Revision History : 2006/11/20  齊珂(中訊) 仕様変更モデルNo.119
Revision History : 2006/12/12  齊珂(中訊) 仕様変更モデルNo.123
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.data.AdmintoEqtypeOrderUnitRow;
import webbroker3.admintriggerorder.define.WEB3AdminToEquityKeyItemDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTickMatchDivDef;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefSortKey;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToEquityOrderReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsOrderMissParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・株式注文照会サービスImpl)<BR>
 * トリガー注文管理者・株式注文照会サービスImpl<BR>
 * 
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3AdminToEquityOrderReferenceServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3AdminToEquityOrderReferenceService 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToEquityOrderReferenceServiceImpl.class);
    
    /**
     * コンストラクタ<BR>
     */
    public WEB3AdminToEquityOrderReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 株式注文照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○トリガー注文管理者・株式注文照会入力リクエストの場合<BR>
     * 　@　@this.get入力画面()をコールする。<BR>
     * <BR>
     * ○トリガー注文管理者・株式注文照会リクエストの場合<BR>
     * 　@　@this.get照会画面()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminToEquityOrderRefInpRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToEquityOrderRefInpRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToEquityOrderRefRefRequest)
        {   
            l_response = this.getReferenceScreen((WEB3AdminToEquityOrderRefRefRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 株式注文照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・株式注文照会サービス）get入力画面」<BR>
     * 参照。<BR>
     *  ========================================================<BR>
     * シーケンス図(「（トリガー注文管理者・株式注文照会サービス）get入力画面 」<BR>
     * 1.7 (*)分岐フロー：(*1)で作成した市場配列が0件の場合、業務エラーをスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02692<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・株式注文照会入力リクエスト <BR>
     * @@return WEB3AdminToEquityOrderRefInpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminToEquityOrderRefInpResponse getInputScreen(
        WEB3AdminToEquityOrderRefInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToEquityOrderRefInpRequest)";
        log.entering(STR_METHOD_NAME);

        l_request.validate();
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.国内株式(トリガー注文照会) 
        //is更新：　@false(更新なし) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_TRIGGER_ORDER_INQUIRY, false);

        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.5 ArrayList( )市場コード
        List l_lisMarkets = new ArrayList();
        //発注日
        List l_lisDates = new ArrayList();


        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //1.8 リクエスト.部店コードの要素数分Loop処理

        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            //1.8.1get部店(証券会社コード : String, 部店コード : String)
            WEB3GentradeBranch l_branch  = null;
            try
            {
                l_branch = 
                    l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_request.branchCode[i]);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_nfe.getMessage(), l_nfe);
            }

            //1.6 get取扱可能市場(部店 : 部店, 弁済区分 : String, 弁済期限値 : double)
            String[] l_strPossibleMarkets = null;

            String[] l_strMarkets =
                WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                    l_branch, ProductTypeEnum.EQUITY);

            //1.7(*1)市場がArrayListに含まれていない場合、追加する。
            if (l_strMarkets != null && l_strMarkets.length != 0)
            {
                for (int y = 0; y < l_strMarkets.length; y++)
                {
                    if (!l_lisMarkets.contains(l_strMarkets[y]))
                    {
                        l_lisMarkets.add(l_strMarkets[y]);
                    }
                }
            }

            //1.8.2get取扱可能市場(部店 : 部店, 弁済区分 : String, 弁済期限値 : double)
            l_strPossibleMarkets =
                WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
                    l_branch, WEB3GentradeRepaymentDivDef.DEFAULT, 0);
            
            //1.8.3(*1)市場がArrayListに含まれていない場合、追加する。
            if (l_strPossibleMarkets != null && l_strPossibleMarkets.length != 0)
            {
                for (int k = 0; k < l_strPossibleMarkets.length; k++)
                {
                    if (!l_lisMarkets.contains(l_strPossibleMarkets[k]))
                    {
                        l_lisMarkets.add(l_strPossibleMarkets[k]);
                    }
                }
            }
        }

        //(*)分岐フロー：(*1)で作成した市場配列が0件の場合、業務エラーをスローする。
        if (l_lisMarkets == null || l_lisMarkets.isEmpty())         
        {           
            log.debug("取扱可能市場が存在しない。");         
            log.exiting(STR_METHOD_NAME);           
            throw new WEB3BusinessLayerException(           
                WEB3ErrorCatalog.BUSINESS_ERROR_00643,          
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱可能市場が存在しない。");           
        }
        
        //1.9市場の配列を生成する。
        String[] l_strHandlingPossibleMarket = new String[l_lisMarkets.size()];
        l_lisMarkets.toArray(l_strHandlingPossibleMarket);

        //1.10(*)業務日付の前営業日、業務日付を追加する。
        //      業務日付： GtlUtils.getTradingSystem().getBizDate()
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDateCalcUtil = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-1);
        l_lisDates.add(WEB3DateUtility.toDay(l_tsDevidendRightDate));
        l_lisDates.add(WEB3DateUtility.toDay(l_datBizDate));

        //1.11(*)市場配列の要素数分Loop処理
        if (l_strHandlingPossibleMarket != null)
        {
            for (int i = 0; i < l_strHandlingPossibleMarket.length; i++)
            {
                //1.11.1reset市場コード(市場コード : String)
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strHandlingPossibleMarket[i]);
                //1.11.2get発注日( )
                l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                //1.11.3(*2)取得した発注日がArrayListに含まれていない場合、追加する。
                if (l_datBizDate != null)
                {

                    if (!l_lisDates.contains(l_datBizDate))
                    {
                        l_lisDates.add(WEB3DateUtility.toDay(l_datBizDate));
                    }
                }
                
            }                        
        }
        
        Date[] l_datBizDates = new Date[l_lisDates.size()]; 
        l_lisDates.toArray(l_datBizDates);
        
        //1.12get取扱可能条件注文種別一覧（PR層）(String, String[], String)
        String[] l_strOrderTypeList = 
            this.getValidTriggerOrderTypeListByPr(l_strInstitutionCode, l_strHandlingPossibleMarket, ProductTypeEnum.EQUITY);
        
        WEB3AdminToEquityOrderRefInpResponse l_response = (WEB3AdminToEquityOrderRefInpResponse)l_request.createResponse();
        
        //1.13プロパティセット
        //市場一覧      ＝　@(*1)で生成した配列
        l_response.marketList = l_strHandlingPossibleMarket;
        //発注日一覧 ＝　@(*2)で生成した配列
        l_response.orderBizDateList = l_datBizDates;
        //条件発注種別一覧  ＝　@get取扱可能条件注文種別一覧（PR層の戻り値
        l_response.triggerOrderTypeList = l_strOrderTypeList;
        return l_response;
    }
    
    /**
     * (get照会画面)<BR>
     * 株式注文照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・株式注文照会サービス）get照会画面」<BR>
     * 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・株式注文照会リクエスト<BR>
     * @@return WEB3AdminToEquityOrderRefRefResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminToEquityOrderRefRefResponse getReferenceScreen(
        WEB3AdminToEquityOrderRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminToEquityOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード：　@機@能カテゴリコード.国内株式(トリガー注文照会)
        //is更新：　@false(更新なし)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_TRIGGER_ORDER_INQUIRY, false);

        //1.4 validate部店権限(部店コード : String[])
        l_admin.validateBranchPermission(l_request.branchCode);

        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.6 create共通検索条件文字列(証券会社コード : String,
        //    リクエストデータ : トリガー注文管理者・注文照会共通リクエスト)
        String l_strCommonQueryString = WEB3AdminToDataManager.createCommonQueryString(
            l_strInstitutionCode, l_request);

        //1.7 get銘柄ＩＤ(String, String)
        //[引数]
        //証券会社コード：　@get証券会社コード()の戻り値
        //銘柄コード：　@リクエストデータ.銘柄コード
        String l_strProductId = this.getProductId(l_strInstitutionCode, l_request.productCode);

        //1.8 create検索文字列(String)
        String l_strQueryString = this.createQueryString(l_strProductId);

        //1.9 create共通検索条件データコンテナ(証券会社コード : String,
        //リクエストデータ : トリガー注文管理者・注文照会共通リクエスト, 銘柄タイプ : ProductTypeEnum)
        String[] l_strCommonDataContainers =
            WEB3AdminToDataManager.createCommonQueryDataContainer(
                l_strInstitutionCode,
                l_request,
                ProductTypeEnum.EQUITY);

        //1.10 create検索条件データコンテナ(String)
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_strProductId);

        //1.11 createソート条件(注文照会ソートキー[])
        //[引数]
        //ソートキー：　@リクエストデータ.ソートキー
        String l_strSortCondition = this.createSortCond(l_request.sortKeys);

        //1.12 get注文単位一覧(String, String[], String)
        //[get注文単位一覧()の引数設定]
        //検索条件文字列：
        //　@create共通検索条件文字列()の戻り値＋create検索条件文字列()の戻り値
        //検索条件データコンテナ：
        //  create共通検索条件データコンテナ()の戻り値＋create検索条件データコンテナ()の戻り値
        //ソート条件：
        //　@createソート条件()の戻り値
        int l_intDcLength = l_strCommonDataContainers.length;
        int l_intQdcLength = l_strQueryDataContainers.length;
        String[] l_strContainers = new String[l_intDcLength + l_intQdcLength];
        List l_lisContainers = new ArrayList();
        for (int i = 0; i < l_intDcLength; i++)
        {
            l_lisContainers.add(l_strCommonDataContainers[i]);
        }

        for (int i = 0; i < l_intQdcLength; i++)
        {
            l_lisContainers.add(l_strQueryDataContainers[i]);
        }

        l_lisContainers.toArray(l_strContainers);

        EqtypeOrderUnitParams[] l_orderUnitParamses = this.getOrderUnitList(
            l_strCommonQueryString + l_strQueryString,
            l_strContainers,
            l_strSortCondition);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManagerImpl =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        EqTypeOrderUnit[] l_carryOverOrderUnit = null;
        //1.13メッセージ 分岐（フロー：get注文単位一覧の戻り値≠nullの場合）
        if (l_orderUnitParamses != null)
        {
            int l_intLength = l_orderUnitParamses.length;
            EqTypeOrderUnit[] l_orderUnits = new EqTypeOrderUnit[l_intLength];
            for (int i = 0; i < l_intLength; i++)
            {
                l_orderUnits[i] = (EqTypeOrderUnit)l_orderManagerImpl.toOrderUnit(l_orderUnitParamses[i]);
            }
            //1.13.1 remove繰越元注文単位(注文単位Params一覧 : EqTypeOrderUnitParams[])
            //[引数]
            //注文単位Params一覧：　@get注文単位一覧()の戻り値
            l_carryOverOrderUnit = l_orderManagerImpl.removeCarryOverOriginalOrderUnit(l_orderUnits);
        }

        WEB3AdminToEquityOrderRefRefResponse l_response =
            (WEB3AdminToEquityOrderRefRefResponse)l_request.createResponse();
        //1.14 （分岐フロー： remove繰越元注文単位()の戻り値＝null　@または　@get注文単位一覧の戻り値 == nullの場合）
        if (l_carryOverOrderUnit == null || l_orderUnitParamses == null)
        {
            //1.14.2(*)レスポンスデータにプロパティをセットする。
            //総ページ数 ＝0
            l_response.totalPages = "0";
            //総レコード数 ＝0
            l_response.totalRecords = "0";
            //表示ページ番号  ＝0
            l_response.pageIndex = "0";
            //株式注文照会Unit一覧   ＝null
            l_response.equityOrderList = null;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.15 WEB3PageIndexInfo(l_objs : 論理ビュー::java::lang::Object[],
        //l_intRequestPageIndex : l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_carryOverOrderUnit,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

        //1.16 getArrayReturned(l_classType : Class)
        EqTypeOrderUnit[] l_eqTypeOrderUnits =
            (EqTypeOrderUnit[])l_pageIndexInfo.getArrayReturned(EqTypeOrderUnit.class);

        //1.17create株式注文照会Unit一覧(注文単位[],  株式注文照会リクエスト)
        WEB3AdminToEquityOrderRefUnit[] l_refUnits =
            this.createEquityOrderRefUnitList(l_eqTypeOrderUnits, l_request);

        //1.21 (*)プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        //総ページ数       ＝ WEB3PageIndexInfo.getTotalPages()
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());

        //総レコード数      ＝ WEB3PageIndexInfo.getTotalRecords()
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

        //表示ページ番号     ＝ WEB3PageIndexInfo.getPageIndex()
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());

        //株式注文照会Unit一覧   ＝create株式注文照会Unit一覧()の戻り値
        l_response.equityOrderList = l_refUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get銘柄ID)<BR>
     * 引数の条件に該当する株式銘柄.銘柄IDを返却する。<BR> 
     * <BR>
     * １）パラメータ.銘柄コードがnullの場合は、nullを返却する。<BR>
     * <BR>
     * ２） DB検索 <BR>
     * 　@以下の条件で株式銘柄テーブルを検索する。 <BR>
     * <BR>
     * 　@証券会社コード = パラメータ.証券会社コード かつ <BR>
     * 　@銘柄コード = パラメータ.銘柄コード <BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、「該当データなし」の <BR>
     * 　@例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * <BR>
     * ３） 検索結果を返却する。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getProductId(String l_strInstitutionCode, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductId(String, String)";
        log.entering(STR_METHOD_NAME);

        //１）パラメータ.銘柄コードがnullの場合は、nullを返却する。
        if (l_strProductCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //２） DB検索以下の条件で株式銘柄テーブルを検索する。
        //証券会社コード = パラメータ.証券会社コード かつ 
       //銘柄コード = パラメータ.銘柄コード 
        String l_strWhere = " institution_code = ? ";
        l_strWhere += " and product_code = ? ";
        
        ArrayList l_lisConditions = new ArrayList();
        l_lisConditions.add(l_strInstitutionCode);
        l_lisConditions.add(l_strProductCode);
        
        
        String[] l_strConditions = new String[l_lisConditions.size()];
        l_lisConditions.toArray(l_strConditions);
        List l_lisReturns = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns = l_queryProcessor.doFindAllQuery(
                EqtypeProductRow.TYPE,
                l_strWhere,
                l_strConditions);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

        //検索結果が取得できなかった場合、「該当データなし」の例外をスローする。
        if ( l_lisReturns == null || l_lisReturns.size() == 0)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //３） 検索結果を返却する。
        EqtypeProductRow[] l_rows = new EqtypeProductRow[l_lisReturns.size()];
        l_lisReturns.toArray(l_rows);
        
        log.exiting(STR_METHOD_NAME);
        return String.valueOf(l_rows[0].getProductId());
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。  <BR>
     * <BR>
     * １）　@検索条件文字列インスタンス(：String)を生成する。<BR>  
     * <BR>
     * ２）　@パラメータ.銘柄ID≠nullの場合、  <BR>
     * 　@銘柄IDを検索条件文字列に追加する。<BR> 
     * <BR>
     * 　@検索条件文字列 += "and product_id = ? "<BR> 
     * <BR>
     * ３）　@作成した検索条件文字列を返却する。<BR>  
     * <BR>
     * @@param l_strProductId - (銘柄ID)<BR>
     * @@return String
     */
    protected String createQueryString(String l_strProductId) 
    {
        final String STR_METHOD_NAME = " createQueryString(String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strQueryString = new String();
        if (null != l_strProductId)
        {
            l_strQueryString += "and product_id = ? ";
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを作成する。<BR>  
     * <BR>
     * １）　@ArrayListを生成する。  <BR>
     * <BR>
     * ２）　@パラメータ.銘柄ID≠nullの場合、 <BR> 
     * 　@銘柄IDを生成したArrayListに追加する。 <BR> 
     * <BR>
     * ３）　@生成したArrayList.toArray()の戻り値を返却する。<BR>  
     * @@param l_strProductId - (銘柄ID)<BR>
     * @@return String[]
     */
    protected String[] createQueryDataContainer(String l_strProductId) 
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListを生成する。
        ArrayList l_lisQueryDataContainers = new ArrayList();
        //２）　@パラメータ.銘柄ID≠nullの場合、銘柄IDを生成したArrayListに追加する。
        if (null != l_strProductId)
        {
            l_lisQueryDataContainers.add(l_strProductId);
        }
        
        //３）　@生成したArrayList.toArray()の戻り値を返却する
        String[] l_strContainers = new String[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_strContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strContainers;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件を作成する。  <BR>
     * <BR>
     * １）　@パラメータ.ソートキー＝nullの場合、 <BR> 
     * 　@　@"銘柄ID 昇順"のソート条件を返却する。<BR>  
     * <BR>
     * ２）　@ソート条件文字列(：String)を作成する。  <BR>
     * <BR>
     * ３）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>  
     * 　@３－１）　@ソートキー.キー項目を対応する列物理名に変換し、<BR>  
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「部店コード」　@→　@注文単位.部店ID<BR>
     * 　@　@・「顧客コード」　@→　@substr(注文単位.口座ID, 9, 6)<BR> 
     * 　@　@・「銘柄コード」　@→　@注文単位.銘柄ID <BR>
     * 　@　@・「市場コード」　@→　@注文単位.市場ID <BR> 
     * 　@　@・「口座区分」　@→　@注文単位.税区分<BR>
     * 　@　@・「取引区分」　@→　@注文単位.注文種別 <BR> 
     * 　@　@・「弁済」　@→　@注文単位.弁済区分<BR>
     * 　@　@・「値段条件」　@→　@注文単位.値段条件<BR>
     * 　@　@・「執行条件」　@→　@注文単位.執行条件<BR>  
     * 　@　@・「注文有効期限」　@→　@注文単位.注文失効日付<BR>  
     * 　@　@・「注文時間」　@→　@注文単位.受注日時<BR> 
     * 　@　@・「発注日」　@→　@注文単位.発注日 <BR>
     * 　@　@・「受渡日」　@→　@注文単位.受渡日<BR>  
     * <BR>
     * 　@３－２）　@ソートキー.昇順／降順に対応する取得順序 <BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>  
     * <BR>
     * ４）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加 <BR> 
     * <BR>
     * ５）　@作成したソート条件文字列を返却する。<BR> 
     * @@param l_sortKeys - (注文照会ソートキー[])<BR>
     * @@return String
     */
    protected String createSortCond(WEB3AdminToOrderRefSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = " createSortCondition(WEB3AdminToOrderRefSortKey[])";
        log.entering(STR_METHOD_NAME);

        //１）　@パラメータ.ソートキー＝nullの場合、"銘柄ID 昇順"のソート条件を返却する。
        if (null == l_sortKeys)
        {
            log.exiting(STR_METHOD_NAME);
            return " product_id asc ";
        }

        //２）　@ソート条件文字列(：String)を作成する。
        StringBuffer l_strBuf = new StringBuffer();
        
        //３）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //３－１）　@ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する。
            //・「部店コード」　@→　@注文単位.部店ID
            if (WEB3AdminToEquityKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("branch_id");
            }
            //・「顧客コード」　@→　@substr(注文単位.口座ID, 9, 6)
            else if (WEB3AdminToEquityKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("substr(account_id, 9, 6)");
            }
            //・「銘柄コード」　@→　@注文単位.銘柄ID
            else if (WEB3AdminToEquityKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("product_id");
            }
            //・「市場コード」　@→　@注文単位.市場ID
            else if (WEB3AdminToEquityKeyItemDef.MARKET_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("market_id");
            }
            //・「口座区分」　@→　@注文単位.税区分
            else if (WEB3AdminToEquityKeyItemDef.TAX_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("tax_type");
            }
            //・「取引区分」　@→　@注文単位.注文種別
            else if (WEB3AdminToEquityKeyItemDef.TRADING_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("order_type");
            }
            //・「弁済」　@→　@注文単位.弁済区分
            else if (WEB3AdminToEquityKeyItemDef.REPAYMENT_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("repayment_type");
            }
            //・「値段条件」　@→　@注文単位.値段条件
            else if (WEB3AdminToEquityKeyItemDef.PRICE_CONDITION_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("price_condition_type");
            }            
            //・「執行条件」　@→　@注文単位.執行条件
            else if (WEB3AdminToEquityKeyItemDef.EXEC_COND_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("execution_condition_type");
            }
            //・「注文有効期限」　@→　@注文単位.注文失効日付
            else if (WEB3AdminToEquityKeyItemDef.EXPIRATION_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("expiration_date");
            }
            //・「注文時間」　@→　@注文単位.受注日時
            else if (WEB3AdminToEquityKeyItemDef.ORDER_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("received_date_time");
            }
            //・「発注日」　@→　@注文単位.発注日
            else if (WEB3AdminToEquityKeyItemDef.ORDER_BIZ_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("biz_date");
            }
            //・「受渡日」　@→　@注文単位.受渡日
            else if (WEB3AdminToEquityKeyItemDef.DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("delivery_date");
            }
            else
            {
                continue;
            }
         
            //３－２）　@ソートキー.昇順／降順に対応する取得順序(asc or desc)をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strBuf.append(" asc,");
            }
            else
            {
                l_strBuf.append(" desc,");
            }
        }     

        //４）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加
        l_strBuf.append(" last_updated_timestamp");
        l_strBuf.append(" asc");
        
        //５）　@作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strBuf.toString();
    }
    
    /**
     * (get注文単位一覧)<BR>
     * 引数の条件に該当する注文単位の一覧を返却する。<BR>  
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>  
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>  
     * 　@　@arg0：　@注文単位ViewRow(AdmintoEqtypeOrderUnitRow).TYPE<BR> 
     * 　@　@arg1：　@パラメータ.検索条件文字列<BR>  
     * 　@　@arg2：　@パラメータ.ソート条件<BR>  
     * 　@　@arg3：　@null<BR>  
     * 　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>  
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。<BR>  
     * <BR>
     * ２）検索結果のListからEqtypeOrderUnitParams[]を生成して返却する。<BR>  
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * @@param l_strQueryDataContainer - (検索条件データコンテナ)<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * @@return EqtypeOrderUnitParams[]
     * @@throws WEB3BaseException
     */
    protected EqtypeOrderUnitParams[] getOrderUnitList(
        String l_strQueryString, 
        String[] l_strQueryDataContainer,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnitList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        //  　@[doFindAllQuery()にセットするパラメータ]
        //  　@arg0：　@注文単位ViewRow(AdmintoEqtypeOrderUnitRow).TYPE
        //　@　@arg1：　@パラメータ.検索条件文字列
        //　@　@arg2：　@パラメータ.ソート条件
        //　@　@arg3：　@null
        //　@　@arg4：　@パラメータ.検索条件データコンテナ
        List l_lisReturns = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns = l_queryProcessor.doFindAllQuery(
                AdmintoEqtypeOrderUnitRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        //２）検索結果が取得できなかった場合、nullを返却する。
        if (null == l_lisReturns || l_lisReturns.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //３） 検索結果のListからEqtypeOrderUnitParams[]を生成して返却する。
        EqtypeOrderUnitParams[] l_orderUnitParams = new EqtypeOrderUnitParams[l_lisReturns.size()];
        for (int i = 0; i < l_lisReturns.size(); i++)
        {
            l_orderUnitParams[i] = toEqtypeOrderUnitParams((AdmintoEqtypeOrderUnitRow)l_lisReturns.get(i));
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (create株式注文照会Unit一覧)<BR>
     * 引数の注文単位一覧より、株式注文約定照会Unitの一覧を <BR> 
     * 作成し、返却する。<BR>
     * <BR>
     * シーケンス図<BR>  
     * 「(トリガー注文管理者・株式注文照会サービス)create株式注文照会Unit一覧」<BR>  
     * 参照。<BR> 
     * @@param l_orderUnitList - (注文単位一覧)<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・株式注文照会リクエスト
     * @@return WEB3AdminToEquityOrderRefUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3AdminToEquityOrderRefUnit[] createEquityOrderRefUnitList(
        EqTypeOrderUnit[] l_orderUnitList, 
        WEB3AdminToEquityOrderRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createEquityOrderRefUnitList(EqTypeOrderUnit[], WEB3AdminToEquityOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitList == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        //1.1 ArrayList( )
        List l_lisOrderUnits = new ArrayList();
        
        //1.2 (*)パラメータ.注文単位一覧要素数分Loop処理
        int l_intLength = l_orderUnitList.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //1.2.1 getOrderId( )
            long l_lngOrderId = l_orderUnitList[i].getOrderId();
            
            //1.2.2 getBranchId( )
            long l_lngBranchId = l_orderUnitList[i].getBranchId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            WEB3EquityTradingModule l_tradingModule
                = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager
                = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnitList[i].getDataSourceObject();

            //1.2.4 getAccountId( )
            long l_lngAccountId = l_orderUnitList[i].getAccountId();

            WEB3GentradeBranch l_branch = null;
            WEB3GentradeMainAccount l_mainAccount = null;
            WEB3GentradeMarket l_market = null;
            try
            {
                //1.2.3 getBranch(arg0 : long)
                l_branch = (WEB3GentradeBranch) l_accountManager.getBranch(l_lngBranchId);
                //1.2.5 getMainAccount(arg0 : long)
                l_mainAccount = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_lngAccountId);
                l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());

            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }            
            
            //1.2.6 get表示顧客コード( )
            String l_strDisplayAccountCode = l_mainAccount.getDisplayAccountCode();
            
            //1.2.7 getProduct( )
            EqTypeProduct l_product = (EqTypeProduct) l_orderUnitList[i].getProduct();
            
            //reset市場コード(市場コード : String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            
            //is手動発注可能(注文単位 : EqTypeOrderUnit)
            boolean l_blnIsManualOrderPossible = 
                l_orderManager.isManualOrderPossible(l_orderUnitList[i]);
   
            //1.2.8 get商品区分(EquityOrderUnit)
            String l_strProductDiv = WEB3EquityDataAdapter.getProductType(l_orderUnitList[i]);
            
            //1.2.9 取引区分を取得する。 (注文種別 : OrderTypeEnum)
            String l_strTradingType = WEB3EquityDataAdapter.getTradingType(
                l_orderUnitList[i].getOrderType());
            
            //1.2.10 注文状態区分を取得する。  (注文単位 : OrderUnit)
            String l_strOrderState = WEB3EquityDataAdapter.getOrderState(l_orderUnitList[i]);
            
            //1.2.11 get口座区分(税区分 : TaxTypeEnum)
            //口座区分を取得する。
            //[引数]
            //税区分：　@注文単位.税区分
            String l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_orderUnitList[i].getTaxType());

            String l_strExecType = WEB3EquityDataAdapter.getExecType(l_orderUnitList[i]);
            
            //1.2.12 get執行条件（SONAR）(執行条件 : EqTypeExecutionConditionType)
            String l_strExecCond = 
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getExecutionConditionType());
            
            //1.2.13is出来るまで注文単位
            boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnitList[i]);
            //1.2.14 isMarketOrder( )
            boolean l_blnIsMarketOrder = l_orderUnitList[i].isMarketOrder();
            
            //get発注状況区分(注文単位 : EqTypeOrderUnit, 条件注文種別 : String)
            String l_strOrderStatusType = 
                WEB3EquityDataAdapter.getOrderStatusType(l_orderUnitList[i], l_request.triggerOrderType);
            
            //1.2.16 getルールエンジンからの通知(注文単位 : OrderUnit, 条件注文種別 : String,
            //       銘柄タイプ : ProductTypeEnum)
            //  注文単位：　@注文単位
            //  条件注文種別：　@パラメータ.条件注文種別
            //  銘柄タイプ：　@ProductTypeEnum.株式 
            RlsConOrderHitNotifyParams l_notifyParams = 
                WEB3AdminToDataManager.getRlsConOrderHitNotify(
                    l_orderUnitList[i], l_request.triggerOrderType, ProductTypeEnum.EQUITY); 
            
            //get発注失敗注文(OrderUnit, String, ProductTypeEnum, String)
            //[引数]  
            // 注文単位：　@注文単位  
            // 条件注文種別：　@パラメータ.リクエストデータ.条件注文種別  
            // 銘柄タイプ：　@ProductTypeEnum.株式  
            // 失敗区分：　@パラメータ.リクエストデータ.歩み値照合区分(*)  
            // (*)歩み値照合区分≠null、かつ、  
            // 　@歩み値照合区分≠"全てエラー"の場合のみ設定
            String l_strMissType = null;
            String l_strTickMatchDiv = l_request.tickMatchDiv;
            if (l_strTickMatchDiv != null
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(l_strTickMatchDiv))
            {
                l_strMissType = l_strTickMatchDiv;
            }
            RlsOrderMissParams l_rlsOrderMissParams = 
                WEB3AdminToDataManager.getRlsOrderMiss(
                    l_orderUnitList[i], 
                    l_request.triggerOrderType, 
                    ProductTypeEnum.EQUITY, 
                    l_strMissType);
            
            //1.2.17トリガー注文管理者・株式注文照会Unit( )
            WEB3AdminToEquityOrderRefUnit l_orderRefUnit = new WEB3AdminToEquityOrderRefUnit();
            
            //1.2.18プロパティセット
            //ID        ＝ 注文単位.注文ID
            l_orderRefUnit.id = "" + l_orderUnitList[i].getOrderId();
            
            //条件注文種別    ＝ パラメータ.条件注文種別
            l_orderRefUnit.triggerOrderType = l_request.triggerOrderType;
            
            //発注条件演算子   ＝ 株式データアダプタ.get発注条件演算子(注文単位)の戻り値
            l_orderRefUnit.condOperator = WEB3EquityDataAdapter.getOrderCondOperator(
        		l_orderUnitList[i]);
            
            //発注条件単価    ＝ 株式データアダプタ.get逆指値基準値(注文単位)の戻り値
            l_orderRefUnit.orderCondPrice = WEB3EquityDataAdapter.getStopOrderPrice(
        		l_orderUnitList[i]);
            
            //Ｗ指値用注文単価区分 = 株式データアダプタ.getＷ指値用注文単価区分(注文単位)の戻り値
            String l_strWLimitOrderPriceDiv = WEB3EquityDataAdapter.getWLimitOrderPriceDiv(l_orderUnitList[i]);
            l_orderRefUnit.wLimitOrderPriceDiv = l_strWLimitOrderPriceDiv;
            
            //Ｗ指値用注文単価 = (*1)株式データアダプタ.getＷ指値用注文単価(注文単位)の戻り値
            //(*1)W指値用注文単価区分が"指値"の場合のみセット
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strWLimitOrderPriceDiv))
            {
                l_orderRefUnit.wLimitPrice = 
                    WEB3EquityDataAdapter.getWLimitOrderPrice(l_orderUnitList[i]);
            }
            
            //Ｗ指値用執行条件 = 株式データアダプタ.getＷ指値用執行条件(注文単位)の戻り値
            l_orderRefUnit.wlimitExecCondType = 
                WEB3EquityDataAdapter.getWLimitExecCondType(l_orderUnitList[i]);
            
            //Ｗ指値用有効状態区分 = 株式データアダプタ.getＷ指値用有効状態区分(注文単位)の戻り値
            l_orderRefUnit.wlimitEnableStatusDiv = 
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnitList[i]);
            
            //W指値用切替前注文単価 = = 株式データアダプタ.getW指値用切替前注文単価(注文単位)の戻り値
            l_orderRefUnit.wlimitBefChgLimitPrice = 
                WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnitList[i]);
            
            //W指値用切替前執行条件 = 株式データアダプタ.getW指値用切替前執行条件(注文単位)の戻り値
            l_orderRefUnit.wlimitBefChgExecCondType = 
                WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnitList[i]);

            //部店コード       ＝ getBranch()の戻り値.部店コード
            l_orderRefUnit.branchCode = l_branch.getBranchCode();
            
            //顧客コード       ＝ getMainAccount()の戻り値.get表示顧客コード()
            l_orderRefUnit.accountCode = l_strDisplayAccountCode;
            
            //市場コード       ＝ 注文単位.市場IDに該当する市場.市場コード
            l_orderRefUnit.marketCode = l_market.getMarketCode();
            
            //銘柄名     ＝ 銘柄名     ＝ null
            l_orderRefUnit.productName = null;
            
            //商品区分        ＝ get商品区分()の戻り値
            l_orderRefUnit.productDiv = l_strProductDiv;
            
            //取引区分＝get取引区分（）の戻り値
            l_orderRefUnit.tradingType = l_strTradingType;
            
            //執行条件      ＝ get執行条件（SONAR）
            l_orderRefUnit.execCondType = l_strExecCond;
            
            //注文有効期限    ＝ is出来るまで注文単()の戻り値がtrueの場合、注文単位.注文失効日付をセット
            if (l_blnIsCarriedOrderUnit)
            {
                l_orderRefUnit.expirationDate = l_orderUnitRow.getExpirationDate();
            }
            
            //注文数量      ＝ 注文単位.注文数量
            l_orderRefUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getQuantity());
            
            //注文単価区分    ＝ isMarketOrder()の戻り値がtrueの場合、"成行"をセット。falseの場合、"指値"をセット。
            if (l_blnIsMarketOrder)
            {
                l_orderRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_orderRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                //注文単価        ＝ 注文単価区分が"指値"の場合、注文単位.指値をセット。
                l_orderRefUnit.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getLimitPrice());
            }
            
            //注文状態区分    ＝ get注文状態区分()の戻り値
            l_orderRefUnit.orderState = l_strOrderState;
            
            //約定状態区分    ＝ get約定状態区分()の戻り値
            l_orderRefUnit.execType = l_strExecType;
            
            //訂正取消区分  ＝ 注文単位.注文訂正・取消区分
            l_orderRefUnit.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
            
            //注文時間        ＝ 注文単位.受注日時
            l_orderRefUnit.orderDate = l_orderUnitRow.getReceivedDateTime();
                        
            //発注日     ＝ 注文単位.発注日
            l_orderRefUnit.orderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
            
            //受渡日     ＝ 注文単位.受渡日
            l_orderRefUnit.deliveryDate = WEB3DateUtility.toDay(l_orderUnitRow.getDeliveryDate());
            
            //発注状況区分    ＝ get発注状況区分（注文単位，条件注文種別）の戻り値
            l_orderRefUnit.triggerOrderState = l_strOrderStatusType;
            
            //(*2)getルールエンジンからの通知()の戻り値≠nullの場合セット
            if (null != l_notifyParams)
            {
                //時価情報受信時間    ＝ (*2)ルールエンジンからの通知Params.tickヒットタイムスタンプ
                l_orderRefUnit.currentPriceInfoAcceptTime = l_notifyParams.getHitTickTimestamp();
                //トリガー起動時間    ＝ (*2)ルールエンジンからの通知Params.ルールエンジンファ@イアタイムスタンプ
                l_orderRefUnit.triggerStartTime = l_notifyParams.getRlsHitTimestamp();
                //発注完了時間  ＝ (*2)ルールエンジンからの通知Params.発注完了タイムスタンプ
                l_orderRefUnit.orderCompleteTime = l_notifyParams.getOrderSubmitTimestamp();
            }
            
            if (null != l_rlsOrderMissParams)
            {
                //歩み値予想時間 = (*2)発注失敗注文Params.tickヒットタイムスタンプ
                l_orderRefUnit.tickExpectTime = l_rlsOrderMissParams.getHitTickTimestamp();
                //歩み値照合区分 = get発注失敗注文の戻り値＝nullの場合"正常"。以外、発注失敗注文Params.失敗区分
                l_orderRefUnit.tickMatchDiv = l_rlsOrderMissParams.getMissType();
            }
            else
            {
                l_orderRefUnit.tickMatchDiv = WEB3AdminToTickMatchDivDef.NORMAL;    
            }
            
            //手動発注可能フラグ   ＝ 拡張株式注文マネージャ.is手動発注可能(注文単位)の戻り値。
            l_orderRefUnit.manualFlag = l_blnIsManualOrderPossible;
            
            //---------- 株式特化プロパティ ----------------------------
            //口座区分        ＝ get口座区分()の戻り値
            l_orderRefUnit.taxType = l_strTaxType;
            
            //銘柄コード       ＝ getProduct()の戻り値.銘柄コード
            l_orderRefUnit.productCode = l_product.getProductCode();
            
            //弁済区分        ＝ 注文単位.弁済区分
            l_orderRefUnit.repaymentDiv = l_orderUnitRow.getRepaymentType();
            
            //値段条件        ＝ 注文単位.値段条件
            l_orderRefUnit.priceCondType = l_orderUnitRow.getPriceConditionType();
            
            //1.2.19 add(arg0 : Object)
            l_lisOrderUnits.add(l_orderRefUnit);
        }
        
        //1.3 toArray( )
        WEB3AdminToEquityOrderRefUnit[] l_refUnits = new WEB3AdminToEquityOrderRefUnit[l_lisOrderUnits.size()];
        l_lisOrderUnits.toArray(l_refUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_refUnits;
    }
    
    /**
     * (get取扱可能条件注文種別一覧（PR層))<BR>
     * 該当会社・市場コード・銘柄タイプで取扱可能な条件注文を取得し、<BR> 
     * PR層で使用する条件注文種別の一覧を返却する。<BR>
     * 取扱可能な条件注文が存在しない場合はnullを返却する。<BR>   
     * <BR>
     * １）　@ArrayListを作成する。<BR>
     * <BR>
     * ２）パラメータ.市場コードの要素数分以下の処理を繰り返す。<BR>
     * <BR>
     * 　@２－１）　@取扱可能注文条件取得 <BR>   
     * <BR>
     * 　@　@該当商品の取扱可能注文条件オブジェクトを取得する。<BR>     
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@銘柄タイプ：　@ProductTypeEnum.株式<BR>
     * 　@　@　@先物／オプション区分：　@"DEFAULT"<BR>
     * 　@　@　@信用取引区分：　@"DEFUALT"<BR>
     * 　@　@　@市場コード：　@該当のパラメータ.市場コード<BR>
     * <BR>
     * 　@２－２）　@取扱可能注文条件Rowの取得<BR>
     * <BR>
     * 　@２－３）　@取扱注文条件Row.（発注条件）逆指値＝"取扱可能"　@かつ <BR>
     * 　@　@　@　@　@　@ArrayListに"逆指値"がない場合、<BR>
     * 　@　@　@　@　@　@"逆指値"をArrayListに追加する。<BR>
     * <BR>
     * 　@２－４）　@取扱注文条件Row.（発注条件）W指値＝"取扱可能"　@かつ <BR>
     * 　@　@　@　@　@　@ArrayListに"W指値"がない場合、<BR> 
     * 　@　@　@　@　@　@"W指値"をArrayListに追加する。<BR> 
     * <BR>
     * 　@２－５）　@取扱注文条件Row.連続注文＝"取扱可能"　@かつ <BR>
     * 　@　@　@　@　@　@ArrayListに"連続注文"がない場合、<BR> 
     * 　@　@　@　@　@　@"連続注文"をArrayListに追加する。<BR> 
     * <BR>
     * ３）　@生成したArrayList.toArray()の戻り値を返却する。<BR> 
     * 　@　@　@※toArray()の戻り値.length＝0の場合、nullを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strMarketCodes - (市場コード配列)<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    protected String[] getValidTriggerOrderTypeListByPr(
        String l_strInstitutionCode, 
        String[] l_strMarketCodes, 
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getValidTriggerOrderTypeListByPr(String, String[], ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strMarketCodes == null)
        {
            log.debug("市場コード配列 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "市場コード配列 = null。");
        }
        
        //１）　@ArrayListを作成する。
        List l_lisValidTriggerOrderTypes = new ArrayList();
        
        //２）パラメータ.市場コードの要素数分以下の処理を繰り返す。
        for (int i = 0; i < l_strMarketCodes.length; i++)
        {
            //２－１）　@取扱可能注文条件取得 <BR>   
            //該当商品の取扱可能注文条件オブジェクトを取得する。
            // 　@　@[引数] 
            // 　@　@　@証券会社コード：　@パラメータ.証券会社コード
            // 　@　@　@銘柄タイプ：　@ProductTypeEnum.株式
            // 　@　@　@先物／オプション区分：　@"DEFAULT"
            // 　@　@　@信用取引区分：　@"DEFUALT"
            // 　@　@　@市場コード：　@該当のパラメータ.市場コード
            WEB3GentradeHandlingOrderCond l_orderCond = new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode, 
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCodes[i]);
            
            //２－２）　@取扱可能注文条件Rowの取得
            //２－１）で取得した取扱注文条件.getDataSourceObject()をコールする。
            EnableOrderConditionRow l_conditionRow = (EnableOrderConditionRow)l_orderCond.getDataSourceObject();
            //２－３）　@取扱注文条件Row.（発注条件）逆指値＝"取扱可能"　@かつ 
            //ArrayListに"逆指値"がない場合、
            //"逆指値"をArrayListに追加する。
            if (WEB3DealtDef.CAN_DEALT.equals(l_conditionRow.getStopOrder())
                && !l_lisValidTriggerOrderTypes.contains(WEB3TriggerOrderTypeDef.STOP))
            {
                l_lisValidTriggerOrderTypes.add(WEB3TriggerOrderTypeDef.STOP);
            }
            //２－４）　@取扱注文条件Row.（発注条件）W指値＝"取扱可能"　@かつ 
            //ArrayListに"W指値"がない場合、
            //"W指値"をArrayListに追加する。
            if (WEB3DealtDef.CAN_DEALT.equals(l_conditionRow.getWLimitOrder())
                && !l_lisValidTriggerOrderTypes.contains(WEB3TriggerOrderTypeDef.W_LlIMIT))
            {
                l_lisValidTriggerOrderTypes.add(WEB3TriggerOrderTypeDef.W_LlIMIT);
            }
            
            //２－５）　@取扱注文条件Row.連続注文＝"取扱可能"　@かつ 
            //ArrayListに"連続注文"がない場合、
            //"連続注文"をArrayListに追加する。
            if (WEB3DealtDef.CAN_DEALT.equals(l_conditionRow.getChainOrder())
                && !l_lisValidTriggerOrderTypes.contains(WEB3TriggerOrderTypeDef.SUCC))
            {
                l_lisValidTriggerOrderTypes.add(WEB3TriggerOrderTypeDef.SUCC);
            }                        
            
        }
        
        //３）　@生成したArrayList.toArray()の戻り値を返却する。
        //※toArray()の戻り値.length＝0の場合、nullを返却する。
        String[] l_strValidTriggerOrderTypes = new String[l_lisValidTriggerOrderTypes.size()];
        l_lisValidTriggerOrderTypes.toArray(l_strValidTriggerOrderTypes);
        
        if (l_strValidTriggerOrderTypes.length ==0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strValidTriggerOrderTypes;
        }

    }
    

    /**
     * (AdmintoEqtypeOrderUnitRow を　@eqtypeOrderUnitParams に転換する)<BR>
     * AdmintoEqtypeOrderUnitRow を  eqtypeOrderUnitParams に転換する<BR>   
     * <BR>
     * @@param p_row - (AdmintoEqtypeOrderUnitRow)<BR>
     * @@return EqtypeOrderUnitParams
     */
    
    private EqtypeOrderUnitParams toEqtypeOrderUnitParams(AdmintoEqtypeOrderUnitRow p_row)
    {
        EqtypeOrderUnitParams l_params = new EqtypeOrderUnitParams();
        l_params.setOrderUnitId(p_row.getOrderUnitId());
        l_params.setAccountId(p_row.getAccountId());
        l_params.setSubAccountId(p_row.getSubAccountId());
        l_params.setBranchId(p_row.getBranchId());
        if (!p_row.getTraderIdIsNull())
        {
            l_params.setTraderId(p_row.getTraderId());
        }
        l_params.setOrderId(p_row.getOrderId());
        l_params.setOrderType(p_row.getOrderType());
        l_params.setOrderCateg(p_row.getOrderCateg());
        l_params.setLastOrderActionSerialNo(p_row.getLastOrderActionSerialNo());
        l_params.setLastExecutionSerialNo(p_row.getLastExecutionSerialNo());
        l_params.setProductType(p_row.getProductType());
        if (!p_row.getMarketIdIsNull())
        {
            l_params.setMarketId(p_row.getMarketId());
        }
        l_params.setQuantity(p_row.getQuantity());
        
        if (!p_row.getLimitPriceIsNull())
        {
            l_params.setLimitPrice(new Double(p_row.getLimitPrice()));
        }
        l_params.setExecutionConditionType(p_row.getExecutionConditionType());
        l_params.setPriceConditionType(p_row.getPriceConditionType());
        l_params.setOrderConditionType(p_row.getOrderConditionType());
        l_params.setOrderCondOperator(p_row.getOrderCondOperator());
        if (!p_row.getStopOrderPriceIsNull())
        {
            l_params.setStopOrderPrice(new Double(p_row.getStopOrderPrice()));
        }
        if (!p_row.getWLimitPriceIsNull())
        {
            l_params.setWLimitPrice(new Double(p_row.getWLimitPrice()));
        }
        l_params.setDeliveryDate(p_row.getDeliveryDate());
        l_params.setExpirationDate(p_row.getExpirationDate());
        if (!p_row.getConfirmedQuantityIsNull())
        {
            l_params.setConfirmedQuantity(new Double(p_row.getConfirmedQuantity()));
        }
        if (!p_row.getConfirmedPriceIsNull())
        {
            l_params.setConfirmedPrice(new Double(p_row.getConfirmedPrice()));
        }
        if (!p_row.getExecutedQuantityIsNull())
        {
            l_params.setExecutedQuantity(new Double(p_row.getExecutedQuantity()));
        }
        if (!p_row.getExecutedAmountIsNull())
        {
            l_params.setExecutedAmount(new Double(p_row.getExecutedAmount()));
        }
        l_params.setOrderStatus(p_row.getOrderStatus());
        l_params.setOrderOpenStatus(p_row.getOrderOpenStatus());
        l_params.setExpirationStatus(p_row.getExpirationStatus());
        l_params.setTaxType(p_row.getTaxType());
        l_params.setSwapTaxType(p_row.getSwapTaxType());
        l_params.setRepaymentType(p_row.getRepaymentType());
        if (!p_row.getRepaymentNumIsNull())
        {
            l_params.setRepaymentNum(new Integer(p_row.getRepaymentNum()));
        }
        l_params.setSonarRepaymentType(p_row.getSonarRepaymentType());
        l_params.setBizDate(p_row.getBizDate());
        l_params.setProductId(p_row.getProductId());
        l_params.setQuantityType(p_row.getQuantityType());
        l_params.setOrderChanel(p_row.getOrderChanel());
        l_params.setReceivedDateTime(p_row.getReceivedDateTime());
        l_params.setVoucherNo(p_row.getVoucherNo());
        l_params.setCommTblNo(p_row.getCommTblNo());
        l_params.setCommTblSubNo(p_row.getCommTblSubNo());
        l_params.setSonarTraderCode(p_row.getSonarTraderCode());
        if (!p_row.getPriceIsNull())
        {
            l_params.setPrice(new Double(p_row.getPrice()));
        }
        l_params.setOrderRequestNumber(p_row.getOrderRequestNumber());
        if (!p_row.getEstimatedPriceIsNull())
        {
            l_params.setEstimatedPrice(new Double(p_row.getEstimatedPrice()));
        }
        if (!p_row.getCapitalGainIsNull())
        {
            l_params.setCapitalGain(new Double(p_row.getCapitalGain()));
        }
        if (!p_row.getCapitalGainTaxIsNull())
        {
            l_params.setCapitalGainTax(new Double(p_row.getCapitalGainTax()));
        }
        l_params.setSonarTradedCode(p_row.getSonarTradedCode());
        l_params.setSonarMarketCode(p_row.getSonarMarketCode());
        l_params.setCommProductCode(p_row.getCommProductCode());
        l_params.setShortSellOrderFlag(p_row.getShortSellOrderFlag());
        l_params.setModifyCancelType(p_row.getModifyCancelType());
        l_params.setOrderRootDiv(p_row.getOrderRootDiv());
        l_params.setSubmitOrderRouteDiv(p_row.getSubmitOrderRouteDiv());
        if (!p_row.getConfirmedOrderPriceIsNull())
        {
            l_params.setConfirmedOrderPrice(new Double(p_row.getConfirmedOrderPrice()));
        }
        if (!p_row.getConfirmedEstimatedPriceIsNull())
        {
            l_params.setConfirmedEstimatedPrice(new Double(p_row.getConfirmedEstimatedPrice()));
        }
        l_params.setConfirmedExecConditionType(p_row.getConfirmedExecConditionType());
        l_params.setConfirmedPriceConditionType(p_row.getConfirmedPriceConditionType());
        l_params.setClosingOrderType(p_row.getClosingOrderType());
        l_params.setErrorReasonCode(p_row.getErrorReasonCode());
        l_params.setRequestType(p_row.getRequestType());
        if (!p_row.getFirstOrderUnitIdIsNull())
        {
            l_params.setFirstOrderUnitId(new Long(p_row.getFirstOrderUnitId()));
        }
        l_params.setCreatedTimestamp(p_row.getCreatedTimestamp());
        l_params.setLastUpdatedTimestamp(p_row.getLastUpdatedTimestamp());
        l_params.setConfirmedOrderRev(p_row.getConfirmedOrderRev());
        l_params.setOrderRev(p_row.getOrderRev());
        l_params.setReserveOrderExistFlag(p_row.getReserveOrderExistFlag());
        if (!p_row.getOriginalQuantityIsNull())
        {
            l_params.setOriginalQuantity(new Double(p_row.getOriginalQuantity()));
        }
        l_params.setStopOrderOrderedTimestamp(p_row.getStopOrderOrderedTimestamp());
        l_params.setOrgOrderConditionType(p_row.getOrgOrderConditionType());
        l_params.setOrgOrderCondOperator(p_row.getOrgOrderCondOperator());
        if (!p_row.getOrgStopOrderPriceIsNull())
        {
            l_params.setOrgStopOrderPrice(new Double(p_row.getOrgStopOrderPrice()));
        }
        if (!p_row.getOrgWLimitPriceIsNull())
        {
            l_params.setOrgWLimitPrice(new Double(p_row.getOrgWLimitPrice()));
        }  
        l_params.setOrgWLimitExecCondType(p_row.getOrgWLimitExecCondType());
        l_params.setWLimitExecCondType(p_row.getWLimitExecCondType());
        if (!p_row.getWLimitBeforeLimitPriceIsNull())
        {
            l_params.setWLimitBeforeLimitPrice(new Double(p_row.getWLimitBeforeLimitPrice()));
        }
        l_params.setWLimitBeforeExecCondType(p_row.getWLimitBeforeExecCondType());
        l_params.setSubmitOrderDelayFlag(p_row.getSubmitOrderDelayFlag());
        return l_params;
    }
    


}
@
