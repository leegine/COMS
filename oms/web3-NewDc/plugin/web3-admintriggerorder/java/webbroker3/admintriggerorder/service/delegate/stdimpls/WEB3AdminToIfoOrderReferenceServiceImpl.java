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
filename	WEB3AdminToIfoOrderReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・先物OP注文照会サービスImpl(WEB3AdminToIfoOrderReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 譚漢江(中訊) 新規作成
Revesion History : 2006/7/5 徐宏偉 (中訊) 仕様変更モデル073
Revesion History : 2006/7/10 肖志偉 (中訊) 仕様変更モデル077
                 : 2006/8/25 肖志偉 (中訊) 仕様変更モデル071,075,082,088
                 : 2006/11/10  柴双紅(中訊) 仕様変更モデルNo.106
                 : 2006/12/04  唐性峰(中訊)　@モデルNo.069
Revesion History : 2007/06/30  孟亜南(中訊) 仕様変更モデルNo.129
Revesion History : 2007/07/13  肖志偉(中訊) 仕様変更DBLayoutNo.004
Revesion History : 2009/03/04 劉剣 (中訊) モデルNo.130,131
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitRow;
import webbroker3.admintriggerorder.define.WEB3AdminToIfoKeyItemDef;
import webbroker3.admintriggerorder.define.WEB3AdminToIfoTaxTypeDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTickMatchDivDef;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefSortKey;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToIfoOrderReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsOrderMissParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・先物OP注文照会サービスImpl)<BR>
 * トリガー注文管理者・先物OP注文照会サービス実装クラス<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderReferenceServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3AdminToIfoOrderReferenceService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToIfoOrderReferenceServiceImpl.class);

    /**
     * @@roseuid 43F1BBDD003E
     */
    public WEB3AdminToIfoOrderReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 先物OP注文照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR> 
     * 以下のメソッドを呼び分ける。<BR> 
     * <BR>
     * ○トリガー注文管理者・先物OP注文照会入力リクエストの場合<BR> 
     * 　@this.get入力画面()をコールする。<BR> 
     * <BR>
     * ○トリガー注文管理者・先物OP注文照会リクエストの場合<BR> 
     * 　@this.get照会画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E3558700E3
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
        
        if (l_request instanceof WEB3AdminToIfoOrderRefInpRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToIfoOrderRefInpRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToIfoOrderRefRefRequest)
        {   
            l_response = this.getReferenceScreen((WEB3AdminToIfoOrderRefRefRequest) l_request);
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
     * 先物OP注文照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(トリガー注文管理者・先物OP注文照会サービス)get入力画面」<BR>
     * 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・先物OP注文照会入力リクエストオブジェクト<BR>
     * @@return WEB3AdminToIfoOrderRefInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E6BB5E020D
     */
    protected WEB3AdminToIfoOrderRefInpResponse getInputScreen(
        WEB3AdminToIfoOrderRefInpRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToIfoOrderRefInpRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.先物OP(トリガー注文照会) 
        //is更新：　@false(更新なし) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_INQUIRY, false);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 ArrayList( )
        List l_lisDates = new ArrayList();

        //指数種別一覧作成用
        Map l_targetProductCodeMap = new TreeMap();

        //1.6 (*)業務日付の前営業日、業務日付を追加する。
        //      業務日付： GtlUtils.getTradingSystem().getBizDate()
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDateCalcUtil = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-1);
        l_lisDates.add(WEB3DateUtility.toDay(l_tsDevidendRightDate));
        l_lisDates.add(WEB3DateUtility.toDay(l_datBizDate));
        
        //1.7 get（部店指数別）取扱条件(証券会社コード : String)
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtConds = 
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(l_strInstitutionCode);
        
        //1.8 (*)get（部店指数別）取扱条件()の戻り値の要素数分Loop処理
        int l_intCondsLength = l_branchIndexDealtConds.length;
        int l_intLisDatesLength = l_lisDates.size();
        boolean l_blnFlag = false;
        for (int i = 0; i < l_intCondsLength; i++ )
        {
            //1.8.1 reset銘柄コード(銘柄コード : String)
            WEB3GentradeTradingTimeManagement.resetProductCode(l_branchIndexDealtConds[i].getUnderlyingProductCode());
         
            //1.8.2 get発注日( )
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //1.8.3 (*)取得した発注日がArrayListに含まれていない場合は、追加する
            //         市場閉局後の指数が存在する場合、翌日注文の発注日を含める。
            l_blnFlag = false;
            for (int j = 0; j < l_intLisDatesLength; j++)
            {
                if (WEB3DateUtility.compareToDay((Date) (l_lisDates.get(j)), l_datOrderBizDate) == 0)
                {
                    l_blnFlag = true;
                    break;
                }
            }
            
            if (!l_blnFlag)
            {
                l_lisDates.add(WEB3DateUtility.toDay(l_datOrderBizDate));
                l_intLisDatesLength++;
            }

            String l_targetProductCode = l_branchIndexDealtConds[i].getUnderlyingProductCode();

            //指数種別一覧作成
            l_targetProductCodeMap.put(l_targetProductCode,l_targetProductCode);
        }
        
        //1.9 toArray( )
        Date[] l_datBizDates = new Date[l_lisDates.size()];
        l_lisDates.toArray(l_datBizDates);
        
        //1.10 is商品実施(証券会社コード : String, 部店コード : String[], 商品区分 : String)
        boolean l_blnIsProductExecOption =
            WEB3AdminToDataManager.isProductExec(
                l_strInstitutionCode,
                l_request.branchCode,
                WEB3CommodityDivDef.OPTION);
        
        String[] l_strOrderCondTypeList = null;
        
        //1.11 （分岐フロー： オプション実施会社（is商品実施()＝true）の場合）
        if (l_blnIsProductExecOption)
        {
            //1.11.1 get取扱可能条件注文種別一覧（PR層）(証券会社コード : String, 商品区分 : String)
            l_strOrderCondTypeList = 
                this.getValidTriggerOrderTypeListByPr(
                    l_strInstitutionCode, WEB3CommodityDivDef.OPTION);
        }
        
        //1.12 is商品実施(証券会社コード : String, 部店コード : String[], 商品区分 : String)
        boolean l_blnIsProductExecFuture =
            WEB3AdminToDataManager.isProductExec(
                l_strInstitutionCode,
                l_request.branchCode,
                WEB3CommodityDivDef.FUTURE);

        String[] l_strOrderCondTypeListByPrs = null;
        
        //1.13 （分岐フロー： 先物実施会社（is商品実施()＝true）の場合）
        if (l_blnIsProductExecFuture)
        {
            //1.13.1 get取扱可能条件注文種別一覧（PR層）(証券会社コード : String, 商品区分 : String)
            l_strOrderCondTypeListByPrs = 
                this.getValidTriggerOrderTypeListByPr(
                    l_strInstitutionCode, WEB3CommodityDivDef.FUTURE);
        }
        
        //1.14 createResponse( )
        WEB3AdminToIfoOrderRefInpResponse l_response = 
            (WEB3AdminToIfoOrderRefInpResponse) l_request.createResponse();
        
        //1.15 (*)プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        //発注日一覧   ＝　@生成したArrayList.toArray()
        l_response.orderBizDateList = l_datBizDates;
        
        //条件注文種別一覧    ＝ 　@(*1)get取扱可能条件注文種別一覧(PR層)()の戻り値
        //(*1)先物・オプション両方の条件注文種別一覧を取得している場合は、
        //取得した一覧のうち、uniqueな値のみの配列をセットする。
        //商品未実施、または、取扱可能条件注文なしの場合は、nullをセットする。
        List l_lisOrderConds = new ArrayList();
        int l_intFlag = 1;
        if (l_strOrderCondTypeListByPrs != null)
        {
            if (l_strOrderCondTypeList == null)
            {
                l_strOrderCondTypeList = l_strOrderCondTypeListByPrs;
            }
            else
            {
                l_intFlag = 2;
                int l_intLength = l_strOrderCondTypeList.length;
                int l_intLengthByPr = l_strOrderCondTypeListByPrs.length;
                for (int j = 0; j < l_intLength; j++)
                {
                    l_lisOrderConds.add(l_strOrderCondTypeList[j]);
                }
                
                for (int i = 0; i < l_intLengthByPr; i++)
                {
                    boolean l_blnListFlag = false;
                    for (int j = 0; j < l_intLength; j++)
                    {
                        
                        if (l_strOrderCondTypeListByPrs[i].equals(l_strOrderCondTypeList[j]))
                        {
                            l_blnListFlag = true;
                            break;
                        }
                    }
                    
                    if (!l_blnListFlag)
                    {
                        l_lisOrderConds.add(l_strOrderCondTypeListByPrs[i]);
                    }
                }
            }
        }
        
        if (l_intFlag == 1)
        {
            l_response.triggerOrderTypeList = l_strOrderCondTypeList;
        }
        else
        {
            String[] l_strCondTypeList = new String[l_lisOrderConds.size()];
            l_lisOrderConds.toArray(l_strCondTypeList);
            l_response.triggerOrderTypeList = l_strCondTypeList;
        }

        //get（部店指数別）取扱条件のうち、
        //uniqueな指数（原資産銘柄コード）のみの配列をセットする｡
        if(!l_targetProductCodeMap.isEmpty())
        {
            String l_strTargetProductCodeList[] = new String[l_targetProductCodeMap.size()];

            l_targetProductCodeMap.keySet().toArray(l_strTargetProductCodeList);

            l_response.targetProductList = l_strTargetProductCodeList;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get照会画面)<BR>
     * 先物OP注文照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(トリガー注文管理者・先物OP注文照会サービス)get照会画面」<BR>
     * 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・先物OP注文照会リクエストオブジェクト<BR>
     * @@return WEB3AdminToIfoOrderRefRefResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E6BB5E022C
     */
    protected WEB3AdminToIfoOrderRefRefResponse getReferenceScreen(
        WEB3AdminToIfoOrderRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminToIfoOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.先物OP(トリガー注文照会) 
        //is更新：　@false(更新なし) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_INQUIRY, false);

        //1.4 validate部店権限(部店コード : String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 create共通検索条件文字列(証券会社コード : String,
        //    リクエストデータ : トリガー注文管理者・注文照会共通リクエスト)
        String l_strCommonQueryString = WEB3AdminToDataManager.createCommonQueryString(
            l_strInstitutionCode, l_request);
        
        // 1.8.1 / 1.14.1 /1.16.1 /1.20  createResponse( )
        WEB3AdminToIfoOrderRefRefResponse l_response =
            (WEB3AdminToIfoOrderRefRefResponse) l_request.createResponse();
        
        // 1.8.2 / 1.14.2 /1.16.2  プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        //総ページ数              ＝ 0
        l_response.totalPages = "0";
        //総レコード数            ＝ 0
        l_response.totalRecords = "0";
        //表示ページ番号          ＝ 0
        l_response.pageIndex = "0";
        //先物OP注文照会Unit一覧  ＝ 0
        l_response.ifoOrderList = null;
        
        //1.7 get銘柄ID(String, String, String, String, String)
        //[引数] 
        //証券会社コード：　@get証券会社コード()の戻り値 
        //指数種別：　@リクエストデータ.指数種別 
        //限月：　@リクエストデータ.限月 
        //行使価格：　@リクエストデータ.行使価格 
        //オプション商品区分：　@リクエストデータ.オプション商品区分
        String[] l_strProductIds = null;
        try 
        {
            l_strProductIds =
                this.getProductIdList(
                    l_strInstitutionCode,
                    l_request.targetProductCode,
                    l_request.delivaryMonth,
                    l_request.strikePrice,
                    l_request.opProductType);
        }
        //1.8 分岐フロー： get銘柄ID一覧()が例外をthrowした場合）
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.9 create検索条件文字列(トリガー注文管理者・先物OP注文照会リクエスト, String[])
        String l_strQueryString = this.createQueryString(l_request, l_strProductIds);
        
        //1.10 create共通検索条件データコンテナ(証券会社コード : String,
        //リクエストデータ : トリガー注文管理者・注文照会共通リクエスト, 銘柄タイプ : ProductTypeEnum)
        String[] l_strDataContainers = 
            WEB3AdminToDataManager.createCommonQueryDataContainer(
                l_strInstitutionCode, 
                l_request,
                ProductTypeEnum.IFO);
        
        //1.11 create検索条件データコンテナ(トリガー注文管理者・先物OP注文照会リクエスト, String[])
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_request, l_strProductIds);
        
        //1.12 createソート条件(注文照会ソートキー[])
        //[引数] 
        //ソートキー：　@リクエストデータ.ソートキー
        String l_strSortCondition = this.createSortCond(l_request.sortKeys);
        
        //1.13 get注文単位一覧(String, String[], String)
        //[get注文単位一覧()の引数設定]
        //検索条件文字列：
        //　@create共通検索条件文字列()の戻り値＋create検索条件文字列()の戻り値
        //検索条件データコンテナ：
        //  create共通検索条件データコンテナ()の戻り値＋create検索条件データコンテナ()の戻り値
        //ソート条件：
        //　@createソート条件()の戻り値
        int l_intDcLength = l_strDataContainers.length;
        int l_intQdcLength = l_strQueryDataContainers.length;
        String[] l_strContainers = new String[l_intDcLength + l_intQdcLength];
        List l_lisContainers = new ArrayList();
        for (int i = 0; i < l_intDcLength; i++)
        {
            l_lisContainers.add(l_strDataContainers[i]);
        }
        
        for (int i = 0; i < l_intQdcLength; i++)
        {
            l_lisContainers.add(l_strQueryDataContainers[i]);
        }
        
        l_lisContainers.toArray(l_strContainers);
        
        IfoOrderUnitParams[] l_orderUnitList = this.getOrderUnitList(
            l_strCommonQueryString + l_strQueryString,
            l_strContainers,
            l_strSortCondition);

        //1.14 （分岐フロー： get注文単位一覧()の戻り値＝nullの場合）
        if (null == l_orderUnitList)
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.15 remove繰越元注文単位(注文単位Params一覧 : IfoOrderUnitParams[])
        //[引数] 
        //注文単位Params一覧：　@get注文単位一覧()の戻り値
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManagerImpl =
            (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();

        IfoOrderUnitParams[] l_orderUnitParams = l_orderManagerImpl.removeCarryOverOriginalOrderUnit(l_orderUnitList);
        int l_intLength = l_orderUnitParams.length;
        IfoOrderUnit[] l_orderUnits = new IfoOrderUnit[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            l_orderUnits[i] = (IfoOrderUnit) l_orderManagerImpl.toOrderUnit(l_orderUnitParams[i]);
        }
        
        //1.16 （分岐フロー： remove繰越元注文単位()の戻り値＝nullの場合）
        if (null == l_orderUnitParams)
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.17 WEB3PageIndexInfo(l_objs : 論理ビュー::java::lang::Object[], 
        //l_intRequestPageIndex : l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_orderUnits,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));
        
        //1.18 getArrayReturned(l_classType : Class)
        IfoOrderUnit[] l_ifoOrderUnits = (IfoOrderUnit[]) l_pageIndexInfo.getArrayReturned(IfoOrderUnit.class);
        
        //1.19 create先物OP注文照会Unit一覧(IfoOrderUnit[], String)
        WEB3AdminToIfoOrderRefUnit[] l_refUnits =
            this.createIfoOrderRefUnitList(l_ifoOrderUnits, l_request);
        
        //1.21 (*)プロパティセット
        //(*)レスポンスデータにプロパティをセットする。
        //総ページ数       ＝ WEB3PageIndexInfo.getTotalPages()
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        
        //総レコード数      ＝ WEB3PageIndexInfo.getTotalRecords()
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

        //表示ページ番号     ＝ WEB3PageIndexInfo.getPageIndex()
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        
        //先物OP注文照会Unit一覧  ＝ create先物OP注文照会Unit一覧()の戻り値
        l_response.ifoOrderList = l_refUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get銘柄ID一覧)<BR>
     * 引数の条件に該当する先物OP銘柄.銘柄IDの一覧返却する。<BR>
     * <BR>
     * １） 以下のパラメータが全てnullの場合は、nullを返却する。<BR>
     * 　@・限月<BR>
     * 　@・行使価格<BR>
     * 　@・オプション商品区分<BR>
     * <BR>
     * ２） DB検索<BR>
     * 　@以下の条件で先物OP銘柄テーブルを検索する。<BR>
     * <BR>
     * 　@証券会社コード = パラメータ.証券会社コード かつ<BR>
     * 　@原資産銘柄コード(*1) = パラメータ.指数種別 かつ<BR>
     * 　@限月(*1) = パラメータ.限月 かつ<BR>
     * 　@行使価格(*1) = パラメータ.行使価格 かつ<BR>
     * 　@先物オプション商品(*1) = パラメータ.オプション商品区分<BR>
     * <BR>
     * 　@(*1)対応するパラメータがnullの場合は、検索条件に加えない。<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、「該当データなし」の<BR>
     * 　@例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01037<BR>
     * <BR>
     * ３） 検索結果の全要素から銘柄IDを取得し、<BR>
     * 　@銘柄IDの配列を生成して返却する。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strTargetProductCode - (指数種別)<BR>
     * 指数種別<BR>
     * @@param l_strDelivaryMonth - (限月)<BR>
     * 限月<BR>
     * @@param l_strStrikePrice - (行使価格)<BR>
     * 行使価格<BR>
     * @@param l_strOpProductType - (オプション商品区分)<BR>
     * オプション商品区分<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43E87A150154
     */
    protected String[] getProductIdList(
        String l_strInstitutionCode,
        String l_strTargetProductCode,
        String l_strDelivaryMonth,
        String l_strStrikePrice,
        String l_strOpProductType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductId(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 以下のパラメータが全てnullの場合は、nullを返却する。
        //     ・限月
        //     ・行使価格
        //     ・オプション商品区分
        if (null == l_strDelivaryMonth
            && null == l_strStrikePrice
            && null == l_strOpProductType)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //２） DB検索
        //   以下の条件で先物OP銘柄テーブルを検索する。
        //   　@証券会社コード = パラメータ.証券会社コード かつ
        //   　@原資産銘柄コード(*1) = パラメータ.指数種別 かつ
        //   　@限月(*1) = パラメータ.限月 かつ
        //   　@行使価格(*1) = パラメータ.行使価格 かつ
        //   　@先物オプション商品(*1) = パラメータ.オプション商品区分
        //  　@(*1)対応するパラメータがnullの場合は、検索条件に加えない。
        String l_strWhere = " institution_code = ? ";

        ArrayList l_lisConditions = new ArrayList();
        l_lisConditions.add(l_strInstitutionCode);

        if (null != l_strTargetProductCode)
        {
            l_strWhere += " and underlying_product_code = ? ";
            l_lisConditions.add(l_strTargetProductCode);
        }

        if (null != l_strDelivaryMonth)
        {
            l_strWhere += " and month_of_delivery = ? ";
            l_lisConditions.add(l_strDelivaryMonth);
        }
        
        if (null != l_strStrikePrice)
        {
            l_strWhere += " and strike_price = ? ";
            l_lisConditions.add(l_strStrikePrice);
        }

        if (null != l_strOpProductType)
        {
            l_strWhere += " and derivative_type = ?";
            //パラメータ.オプション商品区分＝"プットオプション"の場合、IfoDelivativeTypeEnum.プットオプション
            if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_strOpProductType))
            {
                l_lisConditions.add(String.valueOf(IfoDerivativeTypeEnum.PUT_OPTIONS.intValue()));
            }
            //パラメータ.オプション商品区分＝"コールオプション"の場合、IfoDelivativeTypeEnum.コールオプション
            else if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_strOpProductType))
            {
                l_lisConditions.add(String.valueOf(IfoDerivativeTypeEnum.CALL_OPTIONS.intValue()));
            }
        }
        
        String[] l_strConditions = new String[l_lisConditions.size()];
        l_lisConditions.toArray(l_strConditions);
        List l_lisReturns = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns = l_queryProcessor.doFindAllQuery(
                IfoProductRow.TYPE,
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
        if (null == l_lisReturns || l_lisReturns.size() == 0)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //３） 検索結果の全要素から銘柄IDを取得し、
        // 銘柄IDの配列を生成して返却する。
        IfoProductRow[] l_rows = new IfoProductRow[l_lisReturns.size()];
        l_lisReturns.toArray(l_rows);
        
        int l_intSize = l_lisReturns.size();
        String[] l_strs = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strs[i] = String.valueOf(l_rows[i].getProductId());
        }
        log.exiting(STR_METHOD_NAME);
        return l_strs;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）　@検索条件文字列インスタンス(：String)を生成する。<BR>
     * <BR>
     * ２）　@パラメータ.リクエストデータ.商品区分≠nullの場合、<BR>
     * 　@商品区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and future_option_div = ? "<BR>
     * <BR>
     * ３）　@パラメータ.銘柄ID一覧≠nullの場合、<BR>
     * 　@銘柄IDを検索条件文字列に追加する。<BR>
     * 　@パラメータ.銘柄ID一覧の要素数分"?"を追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and product_id in (?,?,,,)" <BR>
     * <BR>
     * 　@パラメータ.銘柄ID一覧＝null　@かつ　@パラメータ.リクエストデータ.指数種別≠null　@の場合、<BR>
     * 　@指数種別を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and product_id like '?' "<BR>
     * <BR>
     * ４）　@作成した検索条件文字列を返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_strProductIds - (銘柄ID一覧)<BR>
     * 銘柄ID<BR>
     * @@return String
     * @@roseuid 43E87DDF03B5
     */
    protected String createQueryString(WEB3AdminToIfoOrderRefRefRequest l_request, String[] l_strProductIds) 
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminToIfoOrderRefRefRequest, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@検索条件文字列インスタンス(：String)を生成する。
        String l_strQueryString = new String();
        
        //２）　@パラメータ.リクエストデータ.商品区分≠nullの場合、商品区分を検索条件文字列に追加する。
        //検索条件文字列 += "and future_option_div = ? "
        if (null != l_request.productDiv)
        {
            l_strQueryString += "and future_option_div = ? ";
        }
        
        //３）　@パラメータ.銘柄ID一覧≠nullの場合、 銘柄IDを検索条件文字列に追加する。
        //パラメータ.銘柄ID一覧の要素数分"?"を追加する。
        //検索条件文字列 += "and product_id in (?,?,,,)"
        if (null != l_strProductIds)
        {
            l_strQueryString += "and product_id in (? ";
            int l_intSize = l_strProductIds.length;
            for (int i = 0; i < l_intSize; i++)
            {
                if (i != 0)
                {
                    l_strQueryString += ",?";
                }
            }
            l_strQueryString += ")";
        }

        //パラメータ.銘柄ID一覧＝null　@かつ　@パラメータ.リクエストデータ.指数種別≠null　@の場合、
        //指数種別を検索条件文字列に追加する。
        //検索条件文字列 += "and product_id like '?' "
        if (l_strProductIds == null && l_request.targetProductCode != null)
        {
            l_strQueryString += "and product_id like ? ";
        }

        //４）　@作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.リクエストデータ.商品区分≠nullの場合、<BR>
     * 　@商品を判別する条件を生成したArrayListに追加する。<BR>
     * <BR>
     *  　@[パラメータ.商品区分＝"先物"の場合]<BR>
     * 　@　@・"先物"（先物／オプション区分）<BR>
     * 　@[パラメータ.商品区分＝"オプション"の場合]<BR>
     * 　@　@・"オプション"（先物／オプション区分）<BR>
     * <BR>
     * ３）　@パラメータ.銘柄ID一覧≠nullの場合、<BR>
     * 　@銘柄ID一覧の全要素を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@パラメータ.銘柄ID一覧＝null　@かつ　@パラメータ.リクエストデータ.指数種別≠nullの場合、<BR>
     * 　@'%' + パラメータ.リクエストデータ.指数種別の下２桁を生成したArrayListに追加する。<BR>
     * <BR>
     * ４）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_strProductIds - (銘柄ID一覧)<BR>
     * 銘柄ID<BR>
     * @@return String[]
     * @@roseuid 43E8859E00FE
     */
    protected String[] createQueryDataContainer(WEB3AdminToIfoOrderRefRefRequest l_request, String[] l_strProductIds) 
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(WEB3AdminToIfoOrderRefRefRequest, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@ArrayListを生成する。
        ArrayList l_lisQueryDataContainers = new ArrayList();
        
        //２）　@パラメータ.リクエストデータ.商品区分≠nullの場合、商品を判別する条件を生成したArrayListに追加する。
        //[パラメータ.商品区分＝"先物"の場合]・"先物"（先物／オプション区分）
        //[パラメータ.商品区分＝"オプション"の場合]・"オプション"（先物／オプション区分）
        if (null != l_request.productDiv)
        {    
            if (WEB3CommodityDivDef.FUTURE.equals(l_request.productDiv))
            {
                l_lisQueryDataContainers.add(WEB3FuturesOptionDivDef.FUTURES);
            }
            else if (WEB3CommodityDivDef.OPTION.equals(l_request.productDiv))
            {
                l_lisQueryDataContainers.add(WEB3FuturesOptionDivDef.OPTION);
            }
        }
        
        //３）　@パラメータ.銘柄ID一覧≠nullの場合、銘柄ID一覧の全要素を生成したArrayListに追加する。
        if (null != l_strProductIds)
        {
            int l_intSize = l_strProductIds.length;
            for (int i = 0; i < l_intSize; i++)
            {
                l_lisQueryDataContainers.add(l_strProductIds[i]);
            }
        }

        //パラメータ.銘柄ID一覧＝null　@かつ　@パラメータ.リクエストデータ.指数種別≠nullの場合、
        //'%' + パラメータ.リクエストデータ.指数種別の下２桁を生成したArrayListに追加する。
        if (l_strProductIds == null && l_request.targetProductCode != null)
        {
            String l_strValue = l_request.targetProductCode.substring(2, 4);
            l_lisQueryDataContainers.add("%" + l_strValue);
        }

        //４）　@生成したArrayList.toArray()の戻り値を返却する。
        String[] l_strContainers = new String[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_strContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strContainers;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件を作成する。<BR>
     * <BR>
     * １）　@パラメータ.ソートキー＝nullの場合、<BR>
     * 　@　@"銘柄ID 昇順"のソート条件を返却する。<BR>
     * <BR>
     * ２）　@ソート条件文字列(：String)を作成する。<BR>
     * <BR>
     * ３）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@３－１）　@ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「部店コード」　@→　@先物OP注文単位.部店ID<BR>
     * 　@　@・「顧客コード」　@→　@substr(先物OP注文単位.口座ID, 9, 6)<BR>
     * 　@　@・「銘柄コード」　@→　@先物OP注文単位.銘柄ID<BR>
     * 　@　@・「市場コード」　@→　@先物OP注文単位.市場ID<BR>
     * 　@　@・「口座区分」　@→　@substr(先物OP注文単位.補助口座ID, 13, 2)<BR>
     * 　@　@・「商品区分」　@→　@先物OP注文単位.先物／オプション区分<BR>
     * 　@　@・「取引区分」　@→　@先物OP注文単位.注文種別<BR>
     * 　@　@・「執行条件」　@→　@先物OP注文単位.執行条件<BR>
     * 　@　@・「注文有効期限」　@→　@先物OP注文単位.注文失効日付<BR>
     * 　@　@・「注文時間」　@→　@先物OP注文単位.受注日時<BR>
     * 　@　@・「発注日」　@→　@先物OP注文単位.発注日<BR>
     * 　@　@・「受渡日」　@→　@先物OP注文単位.受渡日<BR>
     * <BR>
     * 　@３－２）　@ソートキー.昇順／降順に対応する取得順序<BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ４）　@ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加<BR>
     * <BR>
     * ５）　@作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキーの配列<BR>
     * @@return String
     * @@roseuid 43E886C70246
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
            //・「部店コード」　@→　@先物OP注文単位.部店ID
            if (WEB3AdminToIfoKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("branch_id");
            }
            //・「顧客コード」　@→　@substr(先物OP注文単位.口座ID, 9, 6)
            else if (WEB3AdminToIfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("substr(account_id, 9, 6)");
            }
            //・「銘柄コード」　@→　@先物OP注文単位.銘柄ID
            else if (WEB3AdminToIfoKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("product_id");
            }
            //・「市場コード」　@→　@先物OP注文単位.市場ID
            else if (WEB3AdminToIfoKeyItemDef.MARKET_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("market_id");
            }
            //・「口座区分」　@→　@substr(先物OP注文単位.補助口座ID, 13, 2)
            else if (WEB3AdminToIfoKeyItemDef.TAX_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("substr(sub_account_id, 13, 2)");
            }
            //・「商品区分」　@→　@先物OP注文単位.先物／オプション区分
            else if (WEB3AdminToIfoKeyItemDef.PRODUCT_DIV.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("future_option_div");
            }
            //・「取引区分」　@→　@先物OP注文単位.注文種別
            else if (WEB3AdminToIfoKeyItemDef.TRADING_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("order_type");
            }
            //・「執行条件」　@→　@先物OP注文単位.執行条件
            else if (WEB3AdminToIfoKeyItemDef.EXEC_COND_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("execution_condition_type");
            }
            //・「注文有効期限」　@→　@先物OP注文単位.注文失効日付
            else if (WEB3AdminToIfoKeyItemDef.EXPIRATION_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("expiration_date");
            }
            //・「注文時間」　@→　@先物OP注文単位.受注日時
            else if (WEB3AdminToIfoKeyItemDef.ORDER_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("received_date_time");
            }
            //・「発注日」　@→　@先物OP注文単位.発注日
            else if (WEB3AdminToIfoKeyItemDef.ORDER_BIZ_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("biz_date");
            }
            //・「受渡日」　@→　@先物OP注文単位.受渡日
            else if (WEB3AdminToIfoKeyItemDef.DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
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
     * 　@　@arg0：　@先物OP注文単位ViewRow(AdmintoIfoOrderUnitRow).TYPE<BR>
     * 　@　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * ２）検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ３）検索結果のListからIfoOrderUnitParams[]を生成して返却する。<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_strQueryDataContainer - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return IfoOrderUnitParams[]
     * @@throws WEB3BaseException
     * @@roseuid 43E95FBE03E3
     */
    protected IfoOrderUnitParams[] getOrderUnitList(
        String l_strQueryString, 
        String[] l_strQueryDataContainer,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnitList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        //  　@[doFindAllQuery()にセットするパラメータ]
        //  　@arg0：　@先物OP注文単位ViewRow(AdmintoIfoOrderUnitRow).TYPE 
        //　@　@arg1：　@パラメータ.検索条件文字列
        //　@　@arg2：　@パラメータ.ソート条件
        //　@　@arg3：　@null
        //　@　@arg4：　@パラメータ.検索条件データコンテナ
        List l_lisReturns = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns = l_queryProcessor.doFindAllQuery(
                AdmintoIfoOrderUnitRow.TYPE,
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
        
        //３）検索結果のListからIfoOrderUnitParams[]を生成して返却する。
        IfoOrderUnitParams[] l_orderUnitParams = new IfoOrderUnitParams[l_lisReturns.size()];
        for (int i = 0; i < l_lisReturns.size(); i++)
            l_orderUnitParams[i] = toIfoOrderUnitParams((AdmintoIfoOrderUnitRow)l_lisReturns.get(i));
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (create先物OP注文照会Unit一覧)<BR>
     * 引数の注文単位一覧より、先物OP注文約定照会Unitの一覧を<BR>
     * 作成し、返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(トリガー注文管理者・先物OP注文照会サービス)create先物OP注文照会Unit一覧」<BR>
     * 参照。<BR>
     * @@param l_orderUnitList - (注文単位一覧)<BR>
     * 先物OP注文単位の配列<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・先物OP注文照会リクエストオブジェクト<BR>
     * @@return WEB3AdminToIfoOrderRefUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 43E971E7020E
     */
    protected WEB3AdminToIfoOrderRefUnit[] createIfoOrderRefUnitList(
        IfoOrderUnit[] l_orderUnitList,
        WEB3AdminToIfoOrderRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createIfoOrderRefUnitList(IfoOrderUnit[], WEB3AdminToIfoOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitList == null || l_request == null)
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

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow) l_orderUnitList[i].getDataSourceObject();

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
            
            //1.2.6 getOP取引口座タイプ( )
            SubAccountTypeEnum l_subAccountTypeEnum = l_mainAccount.getOpSubAccountType();
            
            //1.2.7 get表示顧客コード( )
            String l_strDisplayAccountCode = l_mainAccount.getDisplayAccountCode();
            
            //1.2.8 getProduct( )
            IfoProduct l_product = (IfoProduct) l_orderUnitList[i].getProduct();

            //1.2.9 reset銘柄コード()
            WEB3GentradeTradingTimeManagement.resetProductCode(l_product.getUnderlyingProductCode());

            //1.2.10 is手動発注可能(IfoOrderUnit)
            TradingModule l_tradeModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_tradeModule.getOrderManager();
            boolean l_blnisManualOrderPossible =
                l_optionOrderManagerImpl.isManualOrderPossible(l_orderUnitList[i]);

            //1.2.11 get商品区分(IfoOrderUnit)
            String l_strProductDiv = WEB3IfoDataAdapter.getCommodityDiv(l_orderUnitList[i]);
            
            //1.2.12 get取引区分(注文種別 : OrderTypeEnum)
            String l_strTradingType = WEB3IfoDataAdapter.getTradingType(
                l_orderUnitList[i].getOrderType());
            
            //1.2.13 get注文状態区分(注文単位 : OrderUnit)
            String l_strOrderState = WEB3IfoDataAdapter.getOrderStatusType(l_orderUnitList[i]);
            
            //1.2.14 get執行条件(PR層)(執行条件 : IfoOrderExecutionConditionType)
            String l_strExecCond = WEB3IfoDataAdapter.getExecutionCondByPr(
                l_orderUnitList[i].getExecutionConditionType());
            
            //1.2.15 get約定状態区分(注文単位 : OrderUnit)
            String l_strExecType = WEB3IfoDataAdapter.getExecStatusType(l_orderUnitList[i]);
            
            //1.2.16 isMarketOrder( )
            boolean l_blnIsMarketOrder = l_orderUnitList[i].isMarketOrder();

            //1.2.17 get発注状況区分(注文単位 : OrderUnit, 条件注文種別 : String)
            String l_strTriggerOrderState = WEB3IfoDataAdapter.getTriggerOrderStatusType(
                    l_orderUnitList[i], l_request.triggerOrderType);

            //1.2.18 getＷ指値用有効状態区分(注文単位 : IfoOrderUnit)
            String l_strWLimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnitList[i]);

            //1.2.19 getＷ指値用切替前注文単価(注文単位 : IfoOrderUnit)
            String l_strWLimitBefSwitchPrice =
                WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_orderUnitList[i]);

            //1.2.20 getＷ指値用切替前執行条件(注文単位 : IfoOrderUnit)
            String l_strWLimitBefSwitchExecCondType =
                WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnitList[i]);

            //1.2.21 getルールエンジンからの通知(注文単位 : OrderUnit, 条件注文種別 : String,
            //       銘柄タイプ : ProductTypeEnum)
            //  注文単位：　@注文単位
            //  条件注文種別：　@パラメータ.リクエストデータ.条件注文種別
            //  銘柄タイプ：　@ProductTypeEnum.先物オプション
            RlsConOrderHitNotifyParams l_notifyParams =
                WEB3AdminToDataManager.getRlsConOrderHitNotify(
                    l_orderUnitList[i], l_request.triggerOrderType, ProductTypeEnum.IFO);

            //1.2.22 get発注失敗注文()
            //[引数]
            //注文単位：　@注文単位
            //条件注文種別：　@パラメータ.リクエストデータ.条件注文種別
            //銘柄タイプ：　@ProductTypeEnum.先物オプション
            //失敗区分：　@パラメータ.リクエストデータ.歩み値照合区分(*)
            //(*)歩み値照合区分≠null、かつ、
            //　@歩み値照合区分≠"全てのエラー"の場合のみ設定。
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
                    ProductTypeEnum.IFO, 
                    l_strMissType);
            
            //1.2.23 トリガー注文管理者・先物OP注文照会Unit( )
            //トリガー注文管理者・先物OP注文照会Unitインスタンスを生成する。
            WEB3AdminToIfoOrderRefUnit l_execRefUnit = new WEB3AdminToIfoOrderRefUnit();
            
            //1.2.24 (*)プロパティセット
            //(*)トリガー注文管理者・先物OP注文照会Unitインスタンスに以下のプロパティをセットする。
            //---------- 商品共通プロパティ --------------------
            //ID      ＝ 注文単位.注文ID
            l_execRefUnit.id = String.valueOf(l_lngOrderId);
            
            //条件注文種別  ＝ パラメータ.条件注文種別
            l_execRefUnit.triggerOrderType = l_request.triggerOrderType;

            //発注条件演算子 ＝ 先物OPデータアダプタ.get発注条件演算子（注文単位）の戻り値
            l_execRefUnit.condOperator = WEB3IfoDataAdapter.getOrderCondOperator(
        		l_orderUnitList[i]);
            
            //発注条件単価  ＝  先物OPデータアダプタ.get逆指値基準値（注文単位）の戻り値
            l_execRefUnit.orderCondPrice = WEB3IfoDataAdapter.getStopOrderPrice(
        		l_orderUnitList[i]);
            
            //発注条件単価区分 ＝ 先物OPデータアダプタ.get発注条件基準値タイプ（注文単位）の戻り値    
            l_execRefUnit.orderCondPriceDiv = WEB3IfoDataAdapter.getStopPriceType(
        		l_orderUnitList[i]);

            //W指値用注文単価区分　@   ＝ 先物OPデータアダプタ.getW指値用注文単価区分（注文単位）の戻り値
            l_execRefUnit.wLimitOrderPriceDiv = WEB3IfoDataAdapter.getWLimitOrderPriceDiv(
                l_orderUnitList[i]);

            //W指値用注文単価      ＝ (*1)先物OPデータアダプタ.getW指値用注文単価（注文単位）の戻り値
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_execRefUnit.wLimitOrderPriceDiv))
            {
                l_execRefUnit.wLimitPrice = WEB3IfoDataAdapter.getWLimitOrderPrice(
                    l_orderUnitList[i]);
            }

            //W指値用執行条件      ＝ 先物OPデータアダプタ.getW指値用執行条件(注文単位)の戻り値
            l_execRefUnit.wlimitExecCondType = WEB3IfoDataAdapter.getWLimitExecCondType(
                l_orderUnitList[i]);

            //W指値用注文有効状態区分  ＝ 先物OPデータアダプタ.getW指値用注文有効状態区分()の戻り値
            l_execRefUnit.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

            //W指値用切替前注文単価   ＝ 先物OPデータアダプタ.getW指値用切替前注文単価()の戻り値
            l_execRefUnit.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

            //W指値用切替前執行条件   ＝ 先物OPデータアダプタ.getW指値用切替前執行条件()の戻り値
            l_execRefUnit.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

            //部店コード       ＝ getBranch()の戻り値.部店コード
            l_execRefUnit.branchCode = l_branch.getBranchCode();
            
            //顧客コード       ＝ getMainAccount()の戻り値.get表示顧客コード()
            l_execRefUnit.accountCode = l_strDisplayAccountCode;
            
            //市場コード       ＝ 注文単位.市場IDに該当する市場.市場コード
            l_execRefUnit.marketCode = l_market.getMarketCode();
            
            //銘柄名     ＝ getProduct()の戻り値.銘柄名
            IfoProductRow l_productRow = (IfoProductRow) l_product.getDataSourceObject();
            l_execRefUnit.productName = l_productRow.getStandardName();
            
            //商品区分        ＝ get商品区分()の戻り値
            l_execRefUnit.productDiv = l_strProductDiv;
            
            //取引区分        ＝ get取引区分()の戻り値
            l_execRefUnit.tradingType = l_strTradingType;
            
            //執行条件        ＝ get執行条件(PR層)()の戻り値
            l_execRefUnit.execCondType = l_strExecCond;
            
            //注文有効期限  ＝ 　@　@先物OPデータアダプタ.get注文期限区分(注文単位）
            //が"出来るまで注文"の場合のみ、注文単位.注文失効日付をセット。以外、nullをセット。
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnitList[i]);
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_execRefUnit.expirationDate = l_orderUnitRow.getExpirationDate();
            }
            else
            {
                l_execRefUnit.expirationDate = null;
            }
            
            //注文数量        ＝ 注文単位.注文数量
            l_execRefUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitList[i].getQuantity());
            
            //注文単価区分  ＝ isMarketOrder()の戻り値がtrueの場合、"成行"をセット。falseの場合、"指値"をセット。
            if (l_blnIsMarketOrder)
            {
                l_execRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_execRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                //注文単価        ＝ 注文単価区分が"指値"の場合、注文単位.指値をセット。
                l_execRefUnit.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitList[i].getLimitPrice());
            }
            
            //注文状態区分  ＝ get注文状態区分()の戻り値
            l_execRefUnit.orderState = l_strOrderState;
            
            //約定状態区分  ＝ get約定状態区分()の戻り値
            l_execRefUnit.execType = l_strExecType;
            
            //訂正取消区分  ＝ 注文単位.注文訂正・取消区分
            l_execRefUnit.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
            
            //注文時間        ＝ 注文単位.受注日時
            l_execRefUnit.orderDate = l_orderUnitRow.getReceivedDateTime();
            
            //発注日     ＝ 注文単位.発注日
            l_execRefUnit.orderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
            
            //受渡日     ＝ 注文単位.受渡日
            l_execRefUnit.deliveryDate = l_orderUnitRow.getDeliveryDate();
            
            //発注状況区分  ＝ get発注状況区分()の戻り値
            l_execRefUnit.triggerOrderState = l_strTriggerOrderState;
            
            //(*2)getルールエンジンからの通知()の戻り値≠nullの場合セット
            if (null != l_notifyParams)
            {
                //時価情報受信時間    ＝ (*2)ルールエンジンからの通知Params.tickヒットタイムスタンプ
                l_execRefUnit.currentPriceInfoAcceptTime = l_notifyParams.getHitTickTimestamp();
                //トリガー起動時間    ＝ (*2)ルールエンジンからの通知Params.ルールエンジンファ@イアタイムスタンプ
                l_execRefUnit.triggerStartTime = l_notifyParams.getRlsHitTimestamp();
                //発注完了時間  ＝ (*2)ルールエンジンからの通知Params.発注完了タイムスタンプ
                l_execRefUnit.orderCompleteTime = l_notifyParams.getOrderSubmitTimestamp();
            }

            //歩み値照合区分       ＝ get発注失敗注文()の戻り値＝nullの場合、"正常"。以外、発注失敗注文Params.失敗区分
            if (l_rlsOrderMissParams == null)
            {
                l_execRefUnit.tickMatchDiv = WEB3AdminToTickMatchDivDef.NORMAL;
            }
            else
            {
                l_execRefUnit.tickMatchDiv = l_rlsOrderMissParams.getMissType();

                //歩み値予想時間       ＝ (*4)発注失敗注文Params.tickヒットタイムスタンプ
                l_execRefUnit.tickExpectTime = l_rlsOrderMissParams.getHitTickTimestamp();
            }

            //手動発注可能フラグ   ＝ OP注文マネージャ.is手動発注可能()の戻り値
            l_execRefUnit.manualFlag = l_blnisManualOrderPossible;

            //夕場前注文繰越対象フラグ ＝ 先物OPデータアダプタ.get夕場前繰越対象フラグ（PR層）(注文単位)
            l_execRefUnit.eveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnitList[i]);

            //立会区分 ＝ 注文単位.立会区分
            l_execRefUnit.sessionType = l_orderUnitRow.getSessionType();

            //---------- 先物オプション特化プロパティ --------------------
            //口座区分     ＝ getOP取引口座タイプ()の戻り値が
            //　@　@         SubAccountTypeEnum.株式取引口座の場合："オプション買建口座"をセット
            //　@　@         SubAccountTypeEnum.株式オプション取引口座(先物証拠金)の場合："先物オプション口座"をセット
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_execRefUnit.taxType = WEB3AdminToIfoTaxTypeDef.OPTION_BUY_TAX;
            }
            else if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_execRefUnit.taxType = WEB3AdminToIfoTaxTypeDef.FUTURE_OPTION_TAX;
            }
            
            //指数種別     ＝ getProduct()の戻り値.原資産銘柄コード
            l_execRefUnit.targetProductCode = l_productRow.getUnderlyingProductCode();

            //限月       ＝ getProduct()の戻り値.限月
            l_execRefUnit.delivaryMonth = l_productRow.getMonthOfDelivery();
            
            //(*3)注文単位.先物／オプション区分＝"オプション"の場合セット
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitRow.getFutureOptionDiv()))
            {
                //行使価格     ＝ (*3)getProduct()の戻り値.行使価格       
                l_execRefUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_productRow.getStrikePrice());

                //オプション商品区分    ＝ (*3)getProduct()の戻り値.先物オプション商品区分＝
                //"コールオプション"の場合："コールオプション"、
                //"プットオプション"の場合："プットオプション"
                if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_productRow.getDerivativeType()))
                {
                    l_execRefUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
                }
                else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_productRow.getDerivativeType()))
                {
                    l_execRefUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
                }
            }
            
            //1.2.19 add(arg0 : Object)
            l_lisOrderUnits.add(l_execRefUnit);
        }
        
        //1.3 toArray( )
        WEB3AdminToIfoOrderRefUnit[] l_refUnits = new WEB3AdminToIfoOrderRefUnit[l_lisOrderUnits.size()];
        l_lisOrderUnits.toArray(l_refUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_refUnits;
    }
    
    /**
     * (get取扱可能条件注文種別一覧（PR層）)<BR>
     * 該当会社・部店・商品で取扱可能な条件注文を取得し、<BR>
     * PR層で使用する条件注文種別の一覧を返却する。<BR>
     * 取扱可能な条件注文が存在しない場合はnullを返却する。<BR>
     * <BR>
     * １）　@取扱可能注文条件取得<BR>
     * <BR>
     * 　@該当商品の取扱可能注文条件オブジェクトを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@先物／オプション区分：　@<BR>
     * 　@　@　@- パラメータ.商品区分＝"オプション"の場合、"オプション"<BR>
     * 　@　@　@- パラメータ.商品区分＝"先物"の場合、"先物"<BR>
     * 　@　@銘柄タイプ：　@ProductTypeEnum.先物オプション<BR>
     * 　@　@信用取引区分：　@"DEFUALT"<BR>
     * <BR>
     * ２）　@取扱可能注文条件Rowの取得<BR>
     * <BR>
     * 　@１）で取得した取扱注文条件.getDataSourceObject()をコールする。<BR>
     * <BR>
     * ３）　@ArrayListを作成する。<BR>
     * <BR>
     * ４）　@取扱注文条件Row.（発注条件）逆指値＝"取扱可能"の場合、<BR>
     * 　@　@　@"逆指値"をArrayListに追加する。<BR>
     * <BR>
     * ５）　@取扱注文条件Row.（発注条件）W指値＝"取扱可能"の場合、<BR>
     * 　@　@　@"W指値"をArrayListに追加する。<BR>
     * <BR>
     * ６）　@取扱注文条件Row.連続注文＝"取扱可能"の場合、<BR>
     * 　@　@　@"連続注文"をArrayListに追加する。<BR>
     * <BR>
     * ７）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * 　@　@　@※toArray()の戻り値.length＝0の場合、nullを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strProductType - (商品区分)<BR>
     * 商品区分<BR> 
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 43E7FC1A00DF
     */
    protected String[] getValidTriggerOrderTypeListByPr(
        String l_strInstitutionCode,
        String l_strProductType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getValidTriggerOrderTypeListByPr(String, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@取扱可能注文条件取得
        String l_strFutureOptionDiv = null;
        ProductTypeEnum l_productType = null;
        
        //パラメータ.商品区分＝"オプション"の場合、"オプション"
        //パラメータ.商品区分＝"先物"の場合、"先物"
        if (WEB3CommodityDivDef.OPTION.equals(l_strProductType))
        {
            l_strFutureOptionDiv = WEB3FuturesOptionDivDef.OPTION;
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strProductType))
        {
            l_strFutureOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
        }

        //銘柄タイプ：　@ProductTypeEnum.先物オプション
        l_productType = ProductTypeEnum.IFO;

        //該当商品の取扱可能注文条件オブジェクトを取得する。
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                l_productType,
                l_strFutureOptionDiv,
                WEB3MarginTradingDivDef.DEFAULT);
        
        //２）　@取扱可能注文条件Rowの取得
        //  １）で取得した取扱注文条件.getDataSourceObject()をコールする。
        EnableOrderConditionRow l_condRow = (EnableOrderConditionRow) l_handlingOrderCond.getDataSourceObject();
        
        //３）　@ArrayListを作成する。
        List l_lisOrderConds = new ArrayList();
        
        //４）　@取扱注文条件Row.（発注条件）逆指値＝"取扱可能"の場合、"逆指値"をArrayListに追加する。
        if (WEB3DealtDef.CAN_DEALT.equals(l_condRow.getStopOrder()))
        {
            l_lisOrderConds.add(WEB3TriggerOrderTypeDef.STOP);
        }
        
        //５）　@取扱注文条件Row.（発注条件）W指値＝"取扱可能"の場合、"W指値"をArrayListに追加する。
        if (WEB3DealtDef.CAN_DEALT.equals(l_condRow.getWLimitOrder()))
        {
            l_lisOrderConds.add(WEB3TriggerOrderTypeDef.W_LlIMIT);
        }
        
        //６）　@取扱注文条件Row.連続注文＝"取扱可能"の場合、"連続注文"をArrayListに追加する。
        if (WEB3DealtDef.CAN_DEALT.equals(l_condRow.getChainOrder()))
        {
            l_lisOrderConds.add(WEB3TriggerOrderTypeDef.SUCC);
        }
        
        //７）　@生成したArrayList.toArray()の戻り値を返却する。
        String[] l_strOrderConds = new String[l_lisOrderConds.size()];
        l_lisOrderConds.toArray(l_strOrderConds);
        
        //※toArray()の戻り値.length＝0の場合、nullを返却する。
        if (0 == l_strOrderConds.length)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strOrderConds;
    }

    
    /**
     * (AdmintoIfoOrderUnitRow を　@IfoOrderUnitParams に転換する)<BR>
     * AdmintoIfoOrderUnitRow を  IfoOrderUnitParams に転換する<BR>   
     * <BR>
     * @@param p_row - (AdmintoIfoOrderUnitRow)<BR>
     * @@return IfoOrderUnitParams
     */    
    private IfoOrderUnitParams toIfoOrderUnitParams( AdmintoIfoOrderUnitRow p_row )
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(p_row.getOrderUnitId());
        l_params.setAccountId(p_row.getAccountId());
        l_params.setSubAccountId(p_row.getSubAccountId());
        l_params.setBranchId(p_row.getBranchId());
        if ( !p_row.getTraderIdIsNull() )
            l_params.setTraderId(new Long( p_row.getTraderId() ));    
        l_params.setOrderId(p_row.getOrderId());
        l_params.setOrderType(p_row.getOrderType());
        l_params.setOrderCateg(p_row.getOrderCateg());
        l_params.setLastOrderActionSerialNo(p_row.getLastOrderActionSerialNo());
        l_params.setLastExecutionSerialNo(p_row.getLastExecutionSerialNo());
        l_params.setProductType(p_row.getProductType());
        l_params.setFutureOptionDiv(p_row.getFutureOptionDiv());
        if ( !p_row.getMarketIdIsNull() )
            l_params.setMarketId(new Long( p_row.getMarketId() ));
        l_params.setQuantity(p_row.getQuantity());
        if ( !p_row.getLimitPriceIsNull() )
            l_params.setLimitPrice(new Double( p_row.getLimitPrice() ));
        l_params.setExecutionConditionType(p_row.getExecutionConditionType());
        l_params.setOrderConditionType(p_row.getOrderConditionType());
        l_params.setOrderCondOperator(p_row.getOrderCondOperator());
        l_params.setStopPriceType(p_row.getStopPriceType());
        if ( !p_row.getStopOrderPriceIsNull() )
            l_params.setStopOrderPrice(new Double( p_row.getStopOrderPrice() ));
        if ( !p_row.getWLimitPriceIsNull() )
        	l_params.setWLimitPrice(new Double( p_row.getWLimitPrice() ));
        l_params.setDeliveryDate(p_row.getDeliveryDate());
        l_params.setExpirationDate(p_row.getExpirationDate());
        if ( !p_row.getConfirmedQuantityIsNull() )
        	l_params.setConfirmedQuantity(new Double( p_row.getConfirmedQuantity() ));
        if ( !p_row.getConfirmedPriceIsNull() )
        	l_params.setConfirmedPrice(new Double( p_row.getConfirmedPrice() ));
        if ( !p_row.getExecutedQuantityIsNull() )
        	l_params.setExecutedQuantity(new Double( p_row.getExecutedQuantity() ));
        if ( !p_row.getExecutedAmountIsNull() )
        	l_params.setExecutedAmount(new Double( p_row.getExecutedAmount() ));
        l_params.setOrderStatus(p_row.getOrderStatus());
        l_params.setOrderOpenStatus(p_row.getOrderOpenStatus());
        l_params.setExpirationStatus(p_row.getExpirationStatus());
        l_params.setTaxType(p_row.getTaxType());
        l_params.setBizDate(p_row.getBizDate());
        l_params.setProductId(p_row.getProductId());
        l_params.setOrderChanel(p_row.getOrderChanel());
        l_params.setReceivedDateTime(p_row.getReceivedDateTime());
        l_params.setVoucherNo(p_row.getVoucherNo());
        l_params.setCommTblNo(p_row.getCommTblNo());
        l_params.setCommTblSubNo(p_row.getCommTblSubNo());
        l_params.setSonarTradedCode(p_row.getSonarTraderCode());
        if ( !p_row.getPriceIsNull() )
        	l_params.setPrice(new Double( p_row.getPrice() ));
        l_params.setOrderRequestNumber(p_row.getOrderRequestNumber());
        if ( !p_row.getEstimatedPriceIsNull() )
            l_params.setEstimatedPrice(new Double( p_row.getEstimatedPrice() ));
        l_params.setSonarTradedCode(p_row.getSonarTradedCode());
        l_params.setSonarMarketCode(p_row.getSonarMarketCode());
        l_params.setCommProductCode(p_row.getCommProductCode());
        l_params.setModifyCancelType(p_row.getModifyCancelType());
        l_params.setOrderRootDiv(p_row.getOrderRootDiv());
        if ( !p_row.getConfirmedOrderPriceIsNull() )
        	l_params.setConfirmedOrderPrice(new Double( p_row.getConfirmedOrderPrice() ));
        if ( !p_row.getConfirmedEstimatedPriceIsNull() )
        	l_params.setConfirmedEstimatedPrice(new Double( p_row.getConfirmedEstimatedPrice() ));
        l_params.setConfirmedExecConditionType(p_row.getConfirmedExecConditionType());
        l_params.setClosingOrder( p_row.getClosingOrder());
        l_params.setErrorReasonCode(p_row.getErrorReasonCode());
        l_params.setRequestType(p_row.getRequestType());
        if ( !p_row.getFirstOrderUnitIdIsNull() )
            l_params.setFirstOrderUnitId(new Long( p_row.getFirstOrderUnitId() ));
        l_params.setCreatedTimestamp(p_row.getCreatedTimestamp());
        l_params.setLastUpdatedTimestamp(p_row.getLastUpdatedTimestamp());
        l_params.setOrgOrderConditionType(p_row.getOrgOrderConditionType());
        l_params.setOrgOrderCondOperator(p_row.getOrgOrderCondOperator());
        l_params.setOrgStopPriceType(p_row.getOrgStopPriceType());
        if ( !p_row.getOrgStopOrderPriceIsNull() )
        	  l_params.setOrgStopOrderPrice(new Double( p_row.getOrgStopOrderPrice() ));
        if ( !p_row.getOrgWLimitPriceIsNull() )
            l_params.setOrgWLimitPrice(new Double( p_row.getOrgWLimitPrice() ));
        l_params.setOrgWLimitExecCondType( p_row.getOrgWLimitExecCondType());
        	  l_params.setWLimitExecCondType( p_row.getWLimitExecCondType());
        if ( !p_row.getWLimitBeforeLimitPriceIsNull() )
         	  l_params.setWLimitBeforeLimitPrice(new Double( p_row.getWLimitBeforeLimitPrice() ));
        l_params.setWLimitBeforeExecCondType( p_row.getWLimitBeforeExecCondType());
        l_params.setSubmitOrderDelayFlag(p_row.getSubmitOrderDelayFlag());
        l_params.setDayTradeType(p_row.getDayTradeType());
        l_params.setEveningSessionCarryoverFlag(p_row.getEveningSessionCarryoverFlag());
        l_params.setSessionType(p_row.getSessionType());
        return l_params;
      
      
    }

}
@
