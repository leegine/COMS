head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文受付取消認証サービスImpl(WEB3AdminFeqOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 郭英 (中訊) 新規作成
Revesion History : 2005/08/03 黄建(中訊) レビュー 
Revesion History : 2006/09/18 徐大方(中訊) 仕様変更・モデル244     
Revesion History : 2006/12/19 徐宏偉(中訊) モデル　@No.315対応
Revesion History : 2006/12/19 徐宏偉(中訊) モデル　@ＤＢ更新仕様 079
Revesion History : 2006/12/19 徐宏偉(中訊) モデル　@ＤＢ更新仕様 080
Revesion History : 2007/01/16 徐大方(中訊) モデル　@No.334対応
Revesion History : 2007/02/25 齊珂  (中訊) モデル　@No.345対応
Revesion History : 2009/08/03 車進(中訊)   モデル　@No.510  No.511対応
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqAcceptDivDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqExecuteGroup;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptService;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.util.WEB3FeqLogUtility;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式注文受付取消認証サービスImpl)<BR>
 * 管理者外国株式注文受付取消認証サービス実装クラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptServiceImpl implements WEB3AdminFeqOrderAcceptService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F2006D
     */
    public WEB3AdminFeqOrderAcceptServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式注文（注文取消）受付入力処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －get検索画面()<BR>
     * －get入力画面()<BR>
     * －submit受付()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 429FEB91032D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest)
        {
            //get検索画面()
            l_response = 
                this.getSearchScreen((WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptCancelInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminFeqOrderAcceptCancelInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptCancelCompleteRequest)
        {
            //submit受付()
            l_response = 
                this.submitAccept((WEB3AdminFeqOrderAcceptCancelCompleteRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get検索画面)<BR>
     * 検索画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図「（(管)受付認証）get検索画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptSearchCondInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FEC2600FA
     */
    protected WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse getSearchScreen(
        WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSearchScreen(WEB3AdminFeqOrderAcceptSearchCondInputRequest) ";
        log.entering(STR_METHOD_NAME);

        //1.1:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.2:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.3:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get取扱可能市場(証券会社コード : String, 銘柄タイプ : ProductTypeEnum)
        //取扱可能な市場コードの一覧を取得する。
        //[引数]
        //証券会社：　@get証券会社コード()の戻り値
        //銘柄タイプ：　@ProductTypeEnum.外国株式
        String[] l_strMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY);
        
        int l_intCnt = 0;

        if (l_strMarkets != null && l_strMarkets.length > 0)
        {
            l_intCnt = l_strMarkets.length;
        }

        log.debug("HandlingPossibleMarket length ==== " + l_intCnt);
        
        //空のリストを生成する
        //（非市場直結用）
        List l_lisMarketCode1 = new ArrayList();

        //空のリストを生成する
        //（市場直結用）
        List l_lisMarketCode2 = new ArrayList();

        //get取扱可能市場()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_intCnt; i++)
        {
            String l_strMarketCode = l_strMarkets[i];
            MarketRow l_marketRow = null;
            try
            {
                l_marketRow =
                    MarketDao.findRowByInstitutionCodeMarketCode(l_strInstitutionCode, l_strMarketCode);

                if (l_marketRow == null)
                {
                    log.debug("market not found with InstitutionCode, MarketCode:"
                        + l_strInstitutionCode + ", " + l_strMarketCode);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "条件に該当する市場がみつかりませんでした。");
                }
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。",
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。",
                    l_ex);
            }

            //市場用プリファ@レンステーブルより該当するレコードを取得する。
            //市場ID：　@get取扱可能市場()の戻り値の該当要素（=市場コード）に該当する市場.市場ID
            //プリファ@レンス項目名：　@"feq.sle.broker"
            //項目名連番：　@1
            try
            {
                MarketPreferencesDao.findRowByPk(
                    l_marketRow.getMarketId(),
                    WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER,
                    1);

                //分岐フロー：市場用プレファ@レンステーブルに該当レコードが存在する場合
                //リスト（市場直結用）に市場コードを追加する。
                if (!l_lisMarketCode2.contains(l_strMarketCode))
                {
                    l_lisMarketCode2.add(l_strMarketCode);
                }
                log.debug("市場用プレファ@レンステーブルに該当レコードが存在する場合、"
                + "リスト（市場直結用）に市場コードを追加する");
            }
            catch(DataFindException l_ex)
            {
                //分岐フロー：市場用プリファ@レンステーブルに該当レコードが存在しない場合
                //リスト（非市場直結用）に市場コードを追加する。
                if (!l_lisMarketCode1.contains(l_strMarketCode))
                {
                    l_lisMarketCode1.add(l_strMarketCode);
                }

                log.debug("市場用プリファ@レンステーブルに該当レコードが存在しない場合、"
                    + "リスト（非市場直結用）に市場コードを追加する。");
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。",
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。",
                    l_ex);
            }
        }

        //1.5:createResponse( )
        WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse l_response = 
           (WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse)l_request.createResponse();

        //1.6:(*) プロパティセット
        //発注日：　@当日（TradingSystem.getSystemTimestamp()の日付部分）
        l_response.orderBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());

        //市場コード一覧[]：　@ArrayListの非市場直結用、市場直結用の順で設定
        List l_lisMarketCodes = new ArrayList();
        for (int i = 0; i < l_lisMarketCode1.size(); i++)
        {
            l_lisMarketCodes.add(l_lisMarketCode1.get(i));
        }

        for (int j = 0; j < l_lisMarketCode2.size(); j++)
        {
            l_lisMarketCodes.add(l_lisMarketCode2.get(j));
        }

        String[] l_strMarketCodes = new String[l_lisMarketCodes.size()];
        l_lisMarketCodes.toArray(l_strMarketCodes);

        l_response.marketList = l_strMarketCodes;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * シーケンス図「（(管)受付認証）get入力画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91034C
     */
    protected WEB3AdminFeqOrderAcceptCancelInputResponse getInputScreen(WEB3AdminFeqOrderAcceptCancelInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderAcceptInputRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1: validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.3:validate権限(機@能カテゴリコード : String, is更新 : boolean)(
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:get証券会社( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();

        //get運用コード
        //７桁の「運用コード」文字列を取得する。
        //[get運用コード()に指定する引数]
        //証券会社コード：証券会社.get証券会社コード( )の戻り値
        //運用コード：リクエストデータ.運用コード
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService) Services.getService(
            WEB3FeqOrderEmpCodeGettingService.class);

        String l_strManagementCode = l_feqOrderEmpCodeGettingService.getEmpCode(
            l_institution.getInstitutionCode(), l_request.managementCode);
 
        //1.5:get注文単位(証券会社, 管理者外国株式注文受付取消認証入力リクエスト)
        WEB3FeqOrderUnit[] l_orderUnits = this.getOrderUnits(l_institution, l_request, l_strManagementCode);

        //1.6:WEB3PageIndexInfo(l_objs（=get注文単位()の戻り値） : 
        //Object[], l_intRequestPageIndex : int, l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageInfo = new WEB3PageIndexInfo(
            l_orderUnits, 
            Integer.parseInt(l_request.pageIndex), 
            Integer.parseInt(l_request.pageSize));
        
        //1.7:getArrayReturned( )
        Object[] l_objArrayReturns = l_pageInfo.getArrayReturned();

        //1.8:(*) 表示対象ページに該当するオブジェクト要素分LOOP処理
        WEB3AdminFeqExecuteGroup[] l_executeGroups = null;
        
        if (l_objArrayReturns != null && l_objArrayReturns.length > 0)
        {
            int l_intCnt = l_objArrayReturns.length;
            l_executeGroups = new WEB3AdminFeqExecuteGroup[l_intCnt];
            
            for (int i = 0; i < l_intCnt; i++)
            {
                //1.8.1: 外国株式注文明細（管理者）( )
                l_executeGroups[i] = new WEB3AdminFeqExecuteGroup();
                
                //1.8.2:create外国株式注文明細（管理者）(外国株式注文明細（管理者）, 外国株式注文単位)
                WEB3FeqCommonMessageCreatedService l_comMessCreatedService = 
                    new WEB3FeqCommonMessageCreatedServiceImpl();
                
                l_comMessCreatedService.createAdminFeqExecuteGroup(l_executeGroups[i], (WEB3FeqOrderUnit)l_objArrayReturns[i]);
            }
        }
        
        //1.9:getTotalPages( )
        int l_intTotalPage = l_pageInfo.getTotalPages();
        
        //1.10:getTotalRecords( )
        int l_intTotalRecord = l_pageInfo.getTotalRecords();
        
        //1.11:getPageIndex( )
        int l_intPageIndex = l_pageInfo.getPageIndex();
        
        //1.12:createResponse( )
        WEB3AdminFeqOrderAcceptCancelInputResponse l_response = 
           (WEB3AdminFeqOrderAcceptCancelInputResponse)l_request.createResponse();
           
        //1.13:(*) プロパティセット
        //(*) プロパティセット
        //レスポンスデータプロパティに以下の通り値をセットする。
        //注文一覧[]：　@生成した外国株式注文明細（管理者）[]]
        l_response.executeGroups = l_executeGroups;
        
        //表示ページ番号：　@getPageIndex()
        l_response.pageIndex = Integer.toString(l_intPageIndex);
        
        //総ページ数：　@getTotalPages()
        l_response.totalPages = Integer.toString(l_intTotalPage);
        
        //総レコード数：　@getTotalRecords()
        l_response.totalRecords = Integer.toString(l_intTotalRecord);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit受付)<BR>
     * 注文（注文取消）受付入力完了処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)受付認証）submit受付」 参照。<BR>
     * ========================================================<BR>
     * 　@　@:  1.7 入力行にエラーがある場合（エラー運用コードList.size() != 0）、<BR>
     * 　@　@例外をスローする<BR> 
     * 　@　@入力行にエラーがある場合（エラー運用コードList.size() != 0）、<BR>
     * 　@　@例外をスローする<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:  BUSINESS_ERROR_02169<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91036B
     */
    protected WEB3AdminFeqOrderAcceptCancelCompleteResponse submitAccept(
        WEB3AdminFeqOrderAcceptCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitAccept(WEB3AdminFeqOrderAcceptCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
       
        //1.1: validate( )
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.3:validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:ArrayList()
        List l_lisOrderEmpCodes = new ArrayList();

        //1.5:(*) リクエストデータ.注文受付取消一覧[]各要素毎のLOOP処理
        if (l_request.orderAcceptCancelList != null && l_request.orderAcceptCancelList.length > 0)
        {
            int l_intCnt = l_request.orderAcceptCancelList.length;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqOrderAcceptCancelUnit l_orderAcceptCancelUnit = l_request.orderAcceptCancelList[i];
                
                //1.5.1:(*) （注文受付取消一覧[index].変更後受付区分 == null）の場合、continue;
                if (l_orderAcceptCancelUnit.aftChangeAcceptDiv == null)
                {
                    continue;
                }
                
                //1.5.2:get注文単位ByOrderId(long)
                WEB3FeqOrderUnit l_orderUnit = 
                    (WEB3FeqOrderUnit)l_orderMgr.getOrderUnitByOrderId(Long.parseLong(l_orderAcceptCancelUnit.orderId));
                
                //1.5.3:is出来終了( )
                boolean l_blnIsExecEnd = l_orderUnit.isExecEnd();
                
                //1.5.4:(*) 出来終了後（is出来終了() == true）
                if (l_blnIsExecEnd)
                {
                    //1.5.4.1:get運用コード( )
                    String l_strOrderEmpCode = l_orderUnit.getOrderEmpCode();
                    
                    //1.5.4.2:add(arg0（=運用コード）
                    l_lisOrderEmpCodes.add(l_strOrderEmpCode);                    
                }
                
                //1.5.5:is注文受付可能(String)
                boolean l_blnIsOrderAcceptPoss = 
                    l_orderUnit.isOrderAcceptPoss(l_orderAcceptCancelUnit.aftChangeAcceptDiv);
                    
                //1.5.6:(*) 受付不可（is注文受付可能() == false）の場合
                if (!l_blnIsOrderAcceptPoss)
                {
                    //1.5.6.1:get運用コード( )
                    String l_strOrderEmpCode = l_orderUnit.getOrderEmpCode();
                    
                    //1.5.6.2:add(arg0（=運用コード）
                    l_lisOrderEmpCodes.add(l_strOrderEmpCode);    
                }
            }
        }
        
        //1.6:size()
        int l_intListSize = l_lisOrderEmpCodes.size();
            
        //1.7:(*) 入力行にエラーがある場合（エラー運用コードList.size() != 0）、例外をスローする
        if (l_intListSize != 0)
        {
            //※例外の追加文字列
            //（WEB3BaseException.errorMessage）
            // に運用コードListの内容をセットする。
            // ”運用コード，運用コード，，，”
            StringBuffer l_sbErrorMessage = new StringBuffer();
            
            for (int i = 0; i < l_intListSize; i++)
            {
                l_sbErrorMessage.append(l_lisOrderEmpCodes.get(i) + ", "); 
            }
            
            String l_strErrorMessage = l_sbErrorMessage.toString();
            l_strErrorMessage = l_strErrorMessage.substring(0, l_strErrorMessage.length() - 2);
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);                        
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02169,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strErrorMessage);
        }
        
        //1.8:(*) リクエストデータ.注文受付取消一覧[]各要素毎のLOOP処理
        if (l_request.orderAcceptCancelList != null && l_request.orderAcceptCancelList.length > 0)
        {
            int l_intCnt = l_request.orderAcceptCancelList.length;
            log.debug("リクエストデータ.注文受付取消一覧[]の要素数 = " + l_intCnt);
                         
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqOrderAcceptCancelUnit l_orderAcceptCancelUnit = l_request.orderAcceptCancelList[i];
                    
                //1.8.1:(*) （注文受付取消一覧[index].変更後受付区分 == null）の場合、continue;
                if (l_orderAcceptCancelUnit.aftChangeAcceptDiv == null)
                {
                    continue;
                }
                
                log.debug("注文受付取消一覧[ " + i + "].変更後受付区分 = " + l_orderAcceptCancelUnit.aftChangeAcceptDiv);

                //get市場レスポンスメッセージ
                //市場コールバックサービスの引数を生成する。
                //[get市場レスポンスメッセージ()に指定する引数]
                //　@注文ID：
                //　@　@リクエストデータ.注文受付取消一覧[index].注文ID
                //　@変更後受付区分：
                //　@　@リクエストデータ.注文受付取消一覧[index].変更後受付区分
                WEB3FeqAcceptUpdateService l_feqAcceptUpdateService =
                    (WEB3FeqAcceptUpdateService)Services.getService(
                        WEB3FeqAcceptUpdateService.class);
                MarketResponseMessage l_marketResponseMessage =
                    l_feqAcceptUpdateService.getMarketResponseMessage(
                        Long.parseLong(l_orderAcceptCancelUnit.orderId),
                        l_orderAcceptCancelUnit.aftChangeAcceptDiv);

                //update受付(MarketResponseMessage)
                //受付更新処理を行う。
                //[update受付()に指定する引数]
                //　@arg0：
                //　@　@get市場レスポンスメッセージ()の戻り値
                l_feqAcceptUpdateService.updateAccept(l_marketResponseMessage);
            }
        }

        //1.9:createResponse( )
        WEB3AdminFeqOrderAcceptCancelCompleteResponse l_response = 
           (WEB3AdminFeqOrderAcceptCancelCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get注文単位)<BR>
     * 条件に当てはまる注文単位を指定の順番に取得する。<BR>
     * <BR>
     * リクエストデータで指定された条件にて外国株式注文単位テーブルを検索する。<BR>
     * 取得行オブジェクトにて外国株式注文単位を生成し、配列で返却する。<BR>
     * 該当データがない場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02165<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@外国株式注文単位.証券会社コード = 証券会社.getInstitutionCode() And<BR>
     * 　@外国株式注文単位.部店ＩＤ = 部店ＩＤ(*1) And ※指定がある場合のみ<BR>
     * 　@外国株式注文単位.口座ＩＤ = 口座ＩＤ(*2) And ※指定がある場合のみ<BR>
     * 　@外国株式注文単位.発注日  = リクエストデータ.発注日 And ※指定がある場合のみ<BR>
     * 　@外国株式注文単位.運用コード = 引数.運用コード And <BR>
     * ※指定がある場合のみ<BR>
     * 　@外国株式注文単位.市場ＩＤ = 市場ＩＤ(*3) And ※指定がある場合のみ<BR>
     * 　@外国株式注文単位.銘柄ＩＤ = 銘柄ＩＤ(*4) And ※指定がある場合のみ<BR>
     * 　@外株注文単位.出来終了処理日時 = null And<BR>
     * 　@外国株式注文単位.注文状態 in (*5)<BR>
     *   外国株式注文単位.市場から確認済の数量 = null もしくは　@not null
     *   （受付区分 == 0：注文受付中の場合 null、受付区分 == 2：注文受付済の場合 not null）
     * <BR>
     * 　@(*1)　@部店ＩＤ<BR>
     * 　@　@　@アカウントマネージャ.getBranch().getBranchId()にて取得する。<BR>
     * <BR>
     * 　@　@　@[getBranch()に指定する引数]<BR>
     * 　@　@　@institution：　@証券会社<BR>
     * 　@　@　@branchCode：　@リクエストデータ.部店コード<BR>
     * <BR>
     * 　@(*2)　@口座ＩＤ<BR>
     * 　@　@アカウントマネージャ.get顧客().getAccountId()にて取得する。<BR>
     * <BR>
     * 　@　@　@[get顧客()に指定する引数]<BR>
     * 　@　@　@証券会社コード：　@証券会社.getInstitutionCode()<BR>
     * 　@　@　@部店コード：　@リクエストデータ.部店コード<BR>
     * 　@　@　@口座コード：　@リクエストデータ.顧客コード<BR>
     * <BR>
     * 　@(*3)　@市場ＩＤ<BR>
     * 　@　@　@金融オブジェクトマネージャ.getMarket().getMarketId()にて取得する。<BR>
     * <BR>
     * 　@　@　@[getMarket()に指定する引数]<BR>
     * 　@　@　@institution：　@証券会社<BR>
     * 　@　@　@marketCode：　@リクエストデータ.市場コード<BR>
     * <BR>
     * 　@(*4)　@銘柄ＩＤ<BR>
     * 　@　@　@外国株式プロダクトマネージャ.get外国株式銘柄().getProductId()にて取得する。<BR>
     * <BR>
     * 　@　@　@[get外国株式銘柄()に指定する引数]<BR>
     * 　@　@　@証券会社：　@証券会社<BR>
     * 　@　@　@銘柄コード：　@リクエストデータ.銘柄コード<BR>
     * <BR>
     * 　@(*5)　@リクエストデータ.受付区分にて指定されたコード値に対応する以下の値<BR>
     * 　@　@（受付区分 == 0：注文受付中）の場合、<BR>
     * 　@　@　@　@OrderStatusEnum.2:発注中（新規注文），10:発注済（変更注文）<BR>
     * 　@　@（受付区分 == 1：訂正取消中）の場合、<BR>
     * 　@　@　@　@OrderStatusEnum.8:発注中（変更注文），13:発注中（取消注文）<BR>
     * 　@　@（受付区分 == 2：注文受付済）の場合、<BR>
     * 　@　@　@　@OrderStatusEnum.3:発注済（新規注文），10:発注済（変更注文），<BR>
     *                        14:発注済（取消注文）<BR>
     * 　@　@受付区分の指定がない（受付区分 == null）場合、以下のすべて。<BR>
     * 　@　@　@OrderStatusEnum.2:発注中（新規注文），3:発注済（新規注文），<BR>
     * 　@　@　@8:発注中（変更注文），10:発注済（変更注文），11:発注失敗（変更注文），<BR>
     * 　@　@　@13:発注中（取消注文），14:発注済（取消注文），15:発注失敗（取消注文）<BR>
     * <BR>
     * 　@[取得順]<BR>
     * 　@（リクエストデータ.ソートキーの指定の通り）<BR>
     * <BR>
     * 　@－（ソートキー.キー項目 == 運用コード）の場合、外国株式注文単位.運用コード<BR>
     * 　@－（ソートキー.キー項目 == 注文番号）の場合、外国株式注文単位.注文ＩＤ<BR>
     * 　@－（ソートキー.キー項目 == 部店コード）の場合、外国株式注文単位.部店ＩＤの<BR>
     * 3～5byte目<BR>
     * 　@－（ソートキー.キー項目 == 顧客コード）の場合、外国株式注文単位.口座ＩＤの<BR>
     * 9～14byte目<BR>
     * 　@－（ソートキー.キー項目 == 特定口座区分）の場合、外国株式注文単位.税区分<BR>
     * 　@－（ソートキー.キー項目 == 注文時間）の場合、外国株式注文単位.受注日時<BR>
     * 　@－（ソートキー.キー項目 == 決済区分）の場合、外国株式注文単位.決済区分<BR>
     * 　@－（ソートキー.キー項目 == 市場コード）の場合、外国株式注文単位.市場ＩＤ<BR>
     * 　@－（ソートキー.キー項目 == 銘柄コード）の場合、外国株式注文単位.銘柄ＩＤ<BR>
     * 　@－（ソートキー.キー項目 == 売買区分）の場合、外国株式注文単位.注文種別<BR>
     *   －（ソートキー.キー項目 == 発注日）の場合、外国株式注文単位.発注日<BR>
     * <BR>
     * 　@※ 部店ＩＤ -> 証券会社＋部店<BR>
     * 　@※ 市場ＩＤ -> 証券会社＋市場<BR>
     * 　@※ 銘柄ＩＤ -> 証券会社＋商品＋銘柄コード<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_strManagementCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A53C86037F
     */
    private WEB3FeqOrderUnit[] getOrderUnits(
        WEB3GentradeInstitution l_institution, 
        WEB3AdminFeqOrderAcceptCancelInputRequest l_request,
        String l_strManagementCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getOrderUnits(WEB3GentradeInstitution,"
            + " WEB3AdminFeqOrderAcceptInputRequest, String) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "証券会社が未指定(null)です。");
        }
        

        //リクエストデータで指定された条件にて外国株式注文単位テーブルを検索する。            
        //外国株式注文単位.証券会社コード = 証券会社.getInstitutionCode() And 
        String l_strWhere = " institution_code = ? and ";
        
        List l_lisObjs = new ArrayList();
        l_lisObjs.add(l_institution.getInstitutionCode());
        
        //外国株式注文単位.部店ＩＤ = 部店ＩＤ(*1) And ※指定がある場合のみ
        //(*1)　@部店ＩＤ 
        //アカウントマネージャ.getBranch().getBranchId()にて取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        log.debug("リクエストデータ.部店コード = " + l_request.branchCode);
        log.debug("リクエストデータ.顧客コード = " + l_request.accountCode);
        log.debug("リクエストデータ.市場コード = " + l_request.marketCode);
        log.debug("リクエストデータ.銘柄コード = " + l_request.productCode);
        log.debug("リクエストデータ.受付区分 = " + l_request.acceptDiv);
        log.debug("リクエストデータ.発注日 = " + l_request.orderBizDate);
        
        if (!WEB3StringTypeUtility.isEmpty(l_request.branchCode))
        {
            try
            {
                Branch l_branch = l_accountMgr.getBranch(l_institution, l_request.branchCode);//NotFoundException

                long l_lngBranchId = l_branch.getBranchId();

                l_strWhere += "branch_id = ? and ";

                l_lisObjs.add(new Long(l_lngBranchId));
            }
            catch (NotFoundException l_ex)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "該当部店データなし");
            }                
        }
        
        //(*2)　@口座ＩＤ 
        //アカウントマネージャ.get顧客().getAccountId()にて取得する。
        if (!(WEB3StringTypeUtility.isEmpty(l_request.branchCode) || 
            WEB3StringTypeUtility.isEmpty(l_request.accountCode)))
        {
            WEB3GentradeMainAccount l_mainAccount = l_accountMgr.getMainAccount(
                l_institution.getInstitutionCode(),
                l_request.branchCode,
                l_request.accountCode);
                
            long l_lngAccountId = l_mainAccount.getAccountId();
            
            l_strWhere += "account_id = ? and ";
            
            l_lisObjs.add(new Long(l_lngAccountId));
        }
                
        //外国株式注文単位.発注日  = リクエストデータ.発注日 And ※指定がある場合のみ 
        if (l_request.orderBizDate != null)
        {
            l_strWhere += "biz_date = ? and ";
            
            l_lisObjs.add(WEB3DateUtility.formatDate(l_request.orderBizDate, "yyyyMMdd"));
        }
        
        //外国株式注文単位.運用コード =  引数.運用コード And ※指定がある場合のみ 
        if (!WEB3StringTypeUtility.isEmpty(l_strManagementCode))
        {
            l_strWhere += "order_emp_code = ? and ";
            
            l_lisObjs.add(l_strManagementCode);
        }
        
        //外国株式注文単位.市場ＩＤ = 市場ＩＤ(*3) And ※指定がある場合のみ 
        //(*3)　@市場ＩＤ 
        //金融オブジェクトマネージャ.getMarket().getMarketId()にて取得する。 
        if (!WEB3StringTypeUtility.isEmpty(l_request.marketCode))
        {
            WEB3GentradeFinObjectManager l_finObjMgr = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
            try
            {
                Market l_market = l_finObjMgr.getMarket(
                    l_institution.getInstitutionCode(),
                    l_request.marketCode);//NotFoundException                    
                                
                long l_lngMarketId = l_market.getMarketId();
            
                l_strWhere += "market_id = ? and ";
            
                l_lisObjs.add(new Long(l_lngMarketId));
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }                                  
        }

        //外国株式注文単位.銘柄ＩＤ = 銘柄ＩＤ(*4) And ※指定がある場合のみ 
        //(*4)　@銘柄ＩＤ 
        //外国株式プロダクトマネージャ.get外国株式銘柄().getProductId()にて取得する。 
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        if (!WEB3StringTypeUtility.isEmpty(l_request.productCode))
        {                
            WEB3FeqProductManager l_productMgr = (WEB3FeqProductManager)l_tradingModule.getProductManager();
            try
            {
                FeqProduct l_product = 
                    l_productMgr.getFeqProduct(l_institution, l_request.productCode);//NotFoundException
            
                long l_lngProductId = l_product.getProductId();
            
                l_strWhere += "product_id = ? and ";
            
                l_lisObjs.add(new Long(l_lngProductId));
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }                   
        }
        
        //外株注文単位.出来終了処理日時 = null And 
        l_strWhere += "exec_end_timestamp is null and ";
            
        //外国株式注文単位.注文状態 in (*5) 
        //WEB3-XBFEQ-A-CD-0062
        //(*5)　@リクエストデータ.受付区分にて指定されたコード値に対応する以下の値
        if (l_request.acceptDiv != null && l_request.acceptDiv.length > 0)
        {
            String l_strWhereOrderStatus = "";
            
            int l_intAcceptDivCnt = l_request.acceptDiv.length;
            
            List l_lisAcceptDivs = new ArrayList();
            
            for (int i = 0; i < l_intAcceptDivCnt; i++)
            {
                String l_strAcceptDiv = l_request.acceptDiv[i];
                
                if (!l_lisAcceptDivs.contains(l_strAcceptDiv))
                {
                    l_lisAcceptDivs.add(l_strAcceptDiv);
                    
                    if (l_strWhere.trim().endsWith("?"))
                    {
                        l_strWhere += ",";
                    }
                    
                    //（受付区分 == 0：注文受付中）の場合、 
                    //OrderStatusEnum.2:発注中（新規注文），10:発注済（変更注文、閉局中）
                    if (WEB3FeqAcceptDivDef.ORDER_ACCEPTING.equals(l_strAcceptDiv))
                    {
			            l_strWhereOrderStatus += " (order_status in (";
                        l_strWhereOrderStatus += "?, ? ";
                        l_strWhereOrderStatus += ") and confirmed_quantity is null)";
                        l_lisObjs.add(OrderStatusEnum.ORDERING);
                        l_lisObjs.add(OrderStatusEnum.MODIFIED);
                    }
                    //（受付区分 == 1：訂正取消中）の場合、 
                    // OrderStatusEnum.8:発注中（変更注文），13:発注中（取消注文）
                    else if (WEB3FeqAcceptDivDef.CHANGING_CANCELING.equals(l_strAcceptDiv))
                    {
                        if (l_strWhereOrderStatus.equals(""))
                        {
				            l_strWhereOrderStatus += " (order_status in (";
	                        l_strWhereOrderStatus += "?, ?))";
                        }
                        else
                        {
				            l_strWhereOrderStatus += "or (order_status in (";
	                        l_strWhereOrderStatus += "?, ?))";
                        }
                        l_lisObjs.add(OrderStatusEnum.MODIFYING);
                        l_lisObjs.add(OrderStatusEnum.CANCELLING);
                    }            
                    //（受付区分 == 2：注文受付済）の場合、 
                    // OrderStatusEnum.3:発注済（新規注文），10:発注済（変更注文），14:発注済（取消注文） 
                    else if (WEB3FeqAcceptDivDef.ORDER_ACCEPTED.equals(l_strAcceptDiv))
                    {
                        if (l_strWhereOrderStatus.equals(""))
                        {
				            l_strWhereOrderStatus += " (order_status in (";
	                        l_strWhereOrderStatus += "?, ?, ?";
	                        l_strWhereOrderStatus += ") and confirmed_quantity is not null)";
                        }
                        else
                        {
				            l_strWhereOrderStatus += "or (order_status in (";
	                        l_strWhereOrderStatus += "?, ?, ?";
	                        l_strWhereOrderStatus += ") and confirmed_quantity is not null)";
                        }
                        l_lisObjs.add(OrderStatusEnum.ORDERED);
                        l_lisObjs.add(OrderStatusEnum.MODIFIED);
                        l_lisObjs.add(OrderStatusEnum.CANCELLED);
                    }
                    else
                    {
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "受付区分の値が不正のです。");
                    }
                }                    
            }
            
            l_strWhere += "(" + l_strWhereOrderStatus + ")";
        }
        // 受付区分の指定がない（受付区分 == null）場合、以下のすべて。 
        //OrderStatusEnum.2:発注中（新規注文），3:発注済（新規注文）， 
        //8:発注中（変更注文），10:発注済（変更注文），11:発注失敗（変更注文）， 
        //13:発注中（取消注文），14:発注済（取消注文），15:発注失敗（取消注文） 
        else
        {
            l_strWhere += "order_status in (?, ?, ?, ?, ?, ?, ?, ?) ";
            l_lisObjs.add(OrderStatusEnum.ORDERING);
            l_lisObjs.add(OrderStatusEnum.ORDERED);
            l_lisObjs.add(OrderStatusEnum.MODIFYING);
            l_lisObjs.add(OrderStatusEnum.MODIFIED);
            l_lisObjs.add(OrderStatusEnum.NOT_MODIFIED);
            l_lisObjs.add(OrderStatusEnum.CANCELLING);
            l_lisObjs.add(OrderStatusEnum.CANCELLED);
            l_lisObjs.add(OrderStatusEnum.NOT_CANCELLED);
        }
        
        Object[] l_objs = new Object[l_lisObjs.size()];
        l_lisObjs.toArray(l_objs);

        //[取得順] 
        //（リクエストデータ.ソートキーの指定の通り） 
        //－（ソートキー.キー項目 == 運用コード）の場合、外国株式注文単位.運用コード 
        //－（ソートキー.キー項目 == 注文番号）の場合、外国株式注文単位.注文ＩＤ 
        //－（ソートキー.キー項目 == 部店コード）の場合、外国株式注文単位.部店ＩＤの3～5byte目 
        //－（ソートキー.キー項目 == 顧客コード）の場合、外国株式注文単位.口座ＩＤの9～14byte目 
        //－（ソートキー.キー項目 == 特定口座区分）の場合、外国株式注文単位.税区分 
        //－（ソートキー.キー項目 == 注文時間）の場合、外国株式注文単位.受注日時 
        //－（ソートキー.キー項目 == 決済区分）の場合、外国株式注文単位.決済区分 
        //－（ソートキー.キー項目 == 市場コード）の場合、外国株式注文単位.市場ＩＤ 
        //－（ソートキー.キー項目 == 銘柄コード）の場合、外国株式注文単位.銘柄ＩＤ 
        //－（ソートキー.キー項目 == 売買区分）の場合、外国株式注文単位.注文種別 
        //－（ソートキー.キー項目 == 発注日）の場合、外国株式注文単位.発注日
        StringBuffer l_sbOrderBy = new StringBuffer();            
        
        if (l_request.sortKeys != null && l_request.sortKeys.length > 0)
        {
            int l_intCnt = l_request.sortKeys.length;
            
            List l_lisKeyItems = new ArrayList();
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3ForeignSortKey l_sortKey = l_request.sortKeys[i];
                if (l_sortKey != null)
                {
                    String l_strKeyItem = l_sortKey.keyItem;
                    
                    log.debug("項目 @@@@ i = " + i + "@@@@");
                    log.debug("ソートキー.キー項目 = " + l_strKeyItem);
                    
                    if (!l_lisKeyItems.contains(l_strKeyItem))
                    {
                        l_lisKeyItems.add(l_strKeyItem);
                        
                        if (WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_strKeyItem))
                        {                            
                            l_sbOrderBy.append(" order_emp_code ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.ORDER_NO.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" order_id ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" SubStr(branch_id, 3, 3) ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" SubStr(account_id, 9, 6) ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" tax_type ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" received_date_time ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" settle_div ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" market_id ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" product_id ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" order_type ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" biz_date ");
                        }
                        
                        if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                        {
                            l_sbOrderBy.append(" ASC ");
                        }
                        else if (WEB3AscDescDef.DESC.equals(l_sortKey.ascDesc))
                        {
                            l_sbOrderBy.append(" DESC ");
                        }
                        
                        l_sbOrderBy.append(" , ");
                    }                        
                }                                                            
            }
        }
        
        String l_strOrderBy = null;
        if (l_sbOrderBy.length() > 0)
        {
            l_strOrderBy = l_sbOrderBy.toString();
            l_strOrderBy = l_strOrderBy.substring(0, l_strOrderBy.length() - 2) + " ";
        }
        
        log.debug("SQL文: " + WEB3FeqLogUtility.getQueryString(l_strWhere, l_strOrderBy, null, l_objs));            
            
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException 
            List l_lisOrderUnitRows = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE, 
                l_strWhere,
                l_strOrderBy,
                null,
                l_objs);//DataNetworkException, DataQueryException                            
            
            //取得行オブジェクトにて外国株式注文単位を生成し、配列で返却する。
            WEB3FeqOrderUnit[] l_orderUnits = null;
            
            if (l_lisOrderUnitRows != null && !l_lisOrderUnitRows.isEmpty())
            {
                int l_intRowsSize = l_lisOrderUnitRows.size();
                l_orderUnits = new WEB3FeqOrderUnit[l_intRowsSize];
                
                WEB3FeqOrderManager l_orderMgr = 
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
                
                for (int i = 0; i < l_intRowsSize; i++)
                {
                    FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisOrderUnitRows.get(i);
                    l_orderUnits[i] = (WEB3FeqOrderUnit)l_orderMgr.toOrderUnit(l_orderUnitRow);
                    
                }
                
                log.exiting(STR_METHOD_NAME);
                return l_orderUnits;
            }
            //該当データがない場合、例外をスローする。
            else
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);                        
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "外国株式銘柄が取得できません。");
            }            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
}
@
