head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : その他件数照会サービスImpl(WEB3AdminAioOtherCountReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/7/7 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.data.OtherOrderExecutedCountRow;
import webbroker3.aio.define.WEB3AioOtherOrderProductDivDef;
import webbroker3.aio.define.WEB3AioOtherOrderRecordDivDef;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceInputRequest;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceInputResponse;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceRequest;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceResponse;
import webbroker3.aio.message.WEB3AioDailyOrderCountUnit;
import webbroker3.aio.message.WEB3AioMonthlyOrderCountUnit;
import webbroker3.aio.message.WEB3AioOtherCountInfomationUnit;
import webbroker3.aio.message.WEB3AioOtherCountReferenceUnit;
import webbroker3.aio.message.WEB3AioSettleInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioOtherCountReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (その他件数照会サービスImpl)<BR>
 * その他件数照会サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminAioOtherCountReferenceService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioOtherCountReferenceServiceImpl.class);  
    
    /**
     * @@roseuid 423562E500BB
     */
    public WEB3AdminAioOtherCountReferenceServiceImpl() 
    {
     
    }
    
    /**
     * その他件数照会処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *   get入力画面() <BR>
     *   get件数照会画面()<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E37DBE03B4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //リクエストデータの型により、以下の処理をコールする。
        //get入力画面()
        //get件数照会画面()

        if (l_request instanceof WEB3AdminAioOtherCountReferenceInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminAioOtherCountReferenceInputRequest)l_request);   
        }
        else if (l_request instanceof WEB3AdminAioOtherCountReferenceRequest)
        {
            l_response =
                getCountReferenceScreen((WEB3AdminAioOtherCountReferenceRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }    

    /**
     * (get入力画面)<BR>
     * 入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(その他件数照会サービス)get入力画面」参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminAioOtherCountReferenceInputResponse
     * @@roseuid 421ADE7F03B2
     */
    protected WEB3AdminAioOtherCountReferenceInputResponse getInputScreen(
        WEB3AdminAioOtherCountReferenceInputRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = "getInputScreen(" +
                "WEB3AdminAioOtherCountReferenceInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(String, boolean) 管理者権限のチェックを行う。 
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.その他件数照会 
        //is更新：　@false(更新なし) 
        //※ 機@能カテゴリコードは、
        //DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.OTHER_ORDER_COUNT_MANAGEMENT, 
            false);
        
        //1.3 管理者.証券会社コードを取得する。        
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.4 get提携決済機@関明細(String) 
        //提携決済機@関明細の配列を取得する。 
        //［引数］ 
        //証券会社コード： get証券会社コード()の戻り値
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                WEB3AioMultiBankSettleControlService.class);     
        
        WEB3AioSettleInstitutionUnit[] l_aioSelectSettleInstitutionUnits = 
            l_aioMultiBankSettleControlService.getAffiliatedPaySchemeDetails(
                l_strInstitutionCode);
        
        //1.5 証券会社毎の商品区分を取得し返却する。 
        //[引数] 
        //証券会社コード：　@get証券会社コード()の戻り値
        String[] l_strCommodityDivs = this.getCommodityDiv(l_strInstitutionCode);
        
        //1.6 createResponse()
        WEB3AdminAioOtherCountReferenceInputResponse l_response =
            (WEB3AdminAioOtherCountReferenceInputResponse)l_request.createResponse();
        
        //1.7  プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。        
        //レスポンス.商品一覧 = get商品区分()の戻り値
        l_response.commodityDivList = l_strCommodityDivs;
        
        //レスポンス.決済機@関情報 = get提携決済機@関明細()の戻り値        
        l_response.settleInstitutionUnits = l_aioSelectSettleInstitutionUnits;       
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get件数照会画面)<BR>
     * 件数照会画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(その他件数照会サービス)get件数照会画面」参照
     * <BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminAioOtherCountReferenceResponse
     * @@roseuid 41E38B5E02AA
     */
    protected WEB3AdminAioOtherCountReferenceResponse getCountReferenceScreen(
        WEB3AdminAioOtherCountReferenceRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = "getCountReferenceScreen(" +
                "WEB3AdminAioOtherCountReferenceRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 validate()
        //リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( ) 
        //ログイン情報から管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(String, boolean) 管理者権限のチェックを行う。 
        //[引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.その他件数管理 
        //is更新：　@false(更新なし) 
        //※ 機@能カテゴリコードは、DBレイアウト
        // 「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.OTHER_ORDER_COUNT_MANAGEMENT,
            false);
        
        //1.4 validate部店権限(String[]) 部店権限のチェックを行う。 
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        l_web3Administrator.validateBranchPermission(l_request.branchCodeList);
        
        //1.5 get証券会社コード( ) 管理者.証券会社コードを取得する。 
        String l_strInstitutionCode = 
            l_web3Administrator.getInstitution().getInstitutionCode();
        
        //1.6 検索条件文字列を作成する。 
        //[引数] 
        //リクエストデータ：　@その他件数照会リクエスト 
        String l_strWhereClause = this.createQueryString(l_request);
        
        //1.7 検索条件データコンテナを作成する。 
        //[引数] 
        //証券会社コード：get証券会社コード()の戻り値 
        //リクエストデータ：その他件数照会リクエスト
        Object l_bindVars[] = this.createQueryContainer(
            l_strInstitutionCode, 
            l_request);
        
        //1.8 ソート条件を作成する。
        String l_strSortCond = this.createSortCond();
        
        //1.9 その他用注文件数データを取得する。 
        //[引数] 
        //検索条件文字列：　@create検索条件文字列()の戻り値 
        //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
        //ソート条件：　@createソート条件()の戻り値
        List l_lisOtherOrderCount = 
            this.getOtherCountReferenceUnit(
                l_strWhereClause, 
                l_bindVars, 
                l_strSortCond);
        
        // その他用注文件数データが存在しない場合、nullを返却する
        if (l_lisOtherOrderCount == null)
        {
			// レスポンスデータを生成する。
			WEB3AdminAioOtherCountReferenceResponse l_response = 
				(WEB3AdminAioOtherCountReferenceResponse)l_request.createResponse();        
			//レスポンスデータ.その他件数照会情報　@＝　@null
			l_response.otherCountReferenceUnits = null;

			return l_response;
        }
        
        //1.10 その他用注文件数データを格納するArrayListを生成する。(日次明細用)
        List l_lisDailyDetail = new ArrayList();
        
        //1.11 その他用注文件数データを格納するArrayListを生成する。(月次合計用)
        List l_lisMonthlyCount = new ArrayList();
        
        //1.12 日次明細集計用エリアを生成し初期化する        
        //・発注日 = その他用注文件数照会Params.発注日(YYYYMMDD)
        //・件数１ = 0
        //・件数２ = 0
        //・件数３ = 0
        OtherOrderExecutedCountRow l_initOtherOrderCountRow = 
            (OtherOrderExecutedCountRow) l_lisOtherOrderCount.get(0);
        Date l_datBizDate = WEB3DateUtility.getDate(
            l_initOtherOrderCountRow.getBizDate(), "yyyyMMdd");
        long l_lngDailyBuyOrderCount = 0;
        long l_lngDailySellOrderCount = 0;
        long l_lngDailyExecuteCount = 0;
        
        //1.13 月次合計集計用エリアを生成し初期化する
        //・年月 = その他用注文件数照会Params.発注日(YYYYMM)
        //・件数１ = 0
        //・件数２ = 0
        //・件数３ = 0
        Date l_datInitRowBizDate = WEB3DateUtility.getDate(
            l_initOtherOrderCountRow.getBizDate(), "yyyyMMdd");
        
        String l_strYearMonth = WEB3DateUtility.formatDate(
                l_datInitRowBizDate, "yyyyMM");
        
        long l_lngMonthlyBuyOrderCount = 0;
        long l_lngMonthlySellOrderCount = 0;
        long l_lngMonthlyExecuteCount = 0;
        
        OtherOrderExecutedCountRow l_otherOrderCountRow = null;
            
        //1.14 取得したレコード数分Loop処理を実施する
        for (int i = 0; i < l_lisOtherOrderCount.size(); i++)
        {
            l_otherOrderCountRow = 
                (OtherOrderExecutedCountRow) l_lisOtherOrderCount.get(i);
            
            Date l_datOtherCountBizDate = WEB3DateUtility.getDate(
                    l_otherOrderCountRow.getBizDate(), "yyyyMMdd");
            
            String l_strYyyyMmBizdate = WEB3DateUtility.formatDate(
                    l_datOtherCountBizDate, "yyyyMM");
            
            //1.14.1  if 日次明細集計エリア.発注日 == その他件数照会Params.発注日の場合
            if (WEB3DateUtility.compareToDay( 
                    l_datBizDate, l_datOtherCountBizDate) == 0)
            {
                //1.14.1.1 日次明細の件数集計を行う
                //・日次明細集計エリア.発注日(変更無し)
                //・日次明細集計エリア.件数１ = 日次明細集計エリア.件数１ + 
                //     Long.parseLong(その他用注文件数照会Params.件数１)
                l_lngDailyBuyOrderCount += 
                        l_otherOrderCountRow.getBuyOrderCount();
                
                //・日次明細集計エリア.件数２ = 日次明細集計エリア.件数２ + 
                //     Long.parseLong(その他用注文件数照会Params.件数２)                
                l_lngDailySellOrderCount += 
                        l_otherOrderCountRow.getSellOrderCount();
                
                //・日次明細集計エリア.件数３ = 日次明細集計エリア.件数３ + 
                //     Long.parseLong(その他用注文件数照会Params.件数２)
                l_lngDailyExecuteCount += 
                        l_otherOrderCountRow.getExecuteCount();  
            }
            //1.14.2 if 日次明細集計エリア.発注日 != その他件数照会Params.発注日の場合
            else
            {
                //1.14.2.1 その他件数情報インスタンスを生成する。(日次明細用)
                WEB3AioOtherCountInfomationUnit l_dailyOtherCountInfoUnit = 
                    new WEB3AioOtherCountInfomationUnit();
                
                //1.14.2.2 (*)プロパティセット
                //(*)以下の通りに、プロパティにセットする。
                //その他件数情報.発注日 = 日次明細集計エリア.発注日(YYYYMMDD)
                //その他件数情報.件数１ = 日次明細集計エリア.件数１
                //その他件数情報.件数２ = 日次明細集計エリア.件数２
                //その他件数情報.件数３ = 日次明細集計エリア.件数３
                l_dailyOtherCountInfoUnit.orderBizDate = 
                    WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
                l_dailyOtherCountInfoUnit.buyOrderCount = l_lngDailyBuyOrderCount + "";
                if (!WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(l_request.commodityDiv)){
                    l_dailyOtherCountInfoUnit.sellOrderCount = l_lngDailySellOrderCount + "";
                    l_dailyOtherCountInfoUnit.executeCount = l_lngDailyExecuteCount + "";
                }
                //1.14.2.3 「(1)日次明細用」リストに、その他件数情報を追加する。 
                //[引数] 
                //その他件数情報オブジェクト 
                l_lisDailyDetail.add(l_dailyOtherCountInfoUnit);
                
                //1.14.2.4 日次明細集計エリアをその他用注文件数照会Paramsの値で初期化する
                //日次明細集計エリアの初期化
                //・日次明細集計エリア.発注日 = その他用注文件数照会Params.発注日(YYYYMMDD)
                //・日次明細集計エリア.件数１ = Long.parseLong(その他用注文件数照会Params.件数１)
                //・日次明細集計エリア.件数２ = Long.parseLong(その他用注文件数照会Params.件数２)
                //・日次明細集計エリア.件数３ = Long.parseLong(その他用注文件数照会Params.件数３)
                l_datBizDate = l_datOtherCountBizDate;
                l_lngDailyBuyOrderCount = 
                        l_otherOrderCountRow.getBuyOrderCount();
                l_lngDailySellOrderCount = 
                        l_otherOrderCountRow.getSellOrderCount();
                l_lngDailyExecuteCount = 
                        l_otherOrderCountRow.getExecuteCount();                
            }
            
            //1.14.3 if 月次合計集計エリア.年月 == その他用注文件数照会Params.発注月(YYYYMM)の場合
            if (l_strYearMonth.equals(l_strYyyyMmBizdate))
            {
                //1.14.3.1 月次合計の件数集計を行う
                //月次合計集計
                //・月次合計集計エリア.年月(変更無し)
                //・月次合計集計エリア.件数１ = 月次合計集計エリア.件数１ + 
                //     Long.parseLong(その他用注文件数照会Params.件数１)
                //・月次合計集計エリア.件数２ = 月次合計集計エリア.件数２ + 
                //     Long.parseLong(その他用注文件数照会Params.件数２)
                //・月次合計集計エリア.件数３ = 月次合計集計エリア.件数３ + 
                //     Long.parseLong(その他用注文件数照会Params.件数３)
                l_lngMonthlyBuyOrderCount += 
                        l_otherOrderCountRow.getBuyOrderCount();
                
                l_lngMonthlySellOrderCount += 
                        l_otherOrderCountRow.getSellOrderCount();
                
                l_lngMonthlyExecuteCount += 
                        l_otherOrderCountRow.getExecuteCount();
            }
            //1.14.4 if 月次合計集計エリア.年月 != その他用注文件数照会Params.発注月(YYYYMM)の場合
            else
            {
                //1.14.4.1 その他件数情報インスタンスを生成する。(月次合計用)
                WEB3AioOtherCountInfomationUnit l_monthlyOtherCountInfoUnit = 
                    new WEB3AioOtherCountInfomationUnit();
                
                //1.14.4.2 (*)プロパティセット
                //(*)以下の通りに、プロパティにセットする。
                //その他件数情報.発注日 = 集計エリア.年月(YYYYMM)
                //その他件数情報.件数１ = 集計エリア.件数１.toString()
                //その他件数情報.件数２ = 集計エリア.件数２.toString()
                //その他件数情報.件数３ = 集計エリア.件数３.toString()
                l_monthlyOtherCountInfoUnit.orderBizDate = l_strYearMonth;
                l_monthlyOtherCountInfoUnit.buyOrderCount = l_lngMonthlyBuyOrderCount + "";
				if (!WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(l_request.commodityDiv)){
                    l_monthlyOtherCountInfoUnit.sellOrderCount = l_lngMonthlySellOrderCount + "";
                    l_monthlyOtherCountInfoUnit.executeCount = l_lngMonthlyExecuteCount + "";
				}
                //1.14.4.3 「(2)月次合計用」リストに、その他件数情報を追加する。 
                //[引数] 
                //その他件数情報オブジェクト 
                l_lisMonthlyCount.add(l_monthlyOtherCountInfoUnit);
                
                //1.14.4.4 月次合計集計エリアをその他用注文件数照会Paramsの値で初期化する
                //月次合計集計エリアの初期化
                //・年月 = その他用注文件数照会Params.発注日(YYYYMM)
                //・件数１ = Long.parseLong(その他用注文件数照会Params.件数１)
                //・件数２ = Long.parseLong(その他用注文件数照会Params.件数２)
                //・件数３ = Long.parseLong(その他用注文件数照会Params.件数３)
                l_strYearMonth = l_strYyyyMmBizdate;
                l_lngMonthlyBuyOrderCount = 
                        l_otherOrderCountRow.getBuyOrderCount();
                l_lngMonthlySellOrderCount = 
                        l_otherOrderCountRow.getSellOrderCount();
                l_lngMonthlyExecuteCount = 
                        l_otherOrderCountRow.getExecuteCount();
            }
        }
        
        //集計対象最終発注日分処理
        //1.15 その他件数情報インスタンスを生成する。(集計対象最終発注日分の日次明細用)
        WEB3AioOtherCountInfomationUnit l_lastDailyOtherCountInfoUnit = 
            new WEB3AioOtherCountInfomationUnit();
        
        //1.16 (*)プロパティセット
        //(*)以下の通りに、プロパティにセットする。
        //その他件数情報.発注日 = 日次明細集計エリア.発注日(YYYYMMDD)
        //その他件数情報.件数１ = 日次明細集計エリア.件数１
        //その他件数情報.件数２ = 日次明細集計エリア.件数２
        //その他件数情報.件数３ = 日次明細集計エリア.件数３
        l_lastDailyOtherCountInfoUnit.orderBizDate = 
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        l_lastDailyOtherCountInfoUnit.buyOrderCount = l_lngDailyBuyOrderCount + "";
		if (!WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(l_request.commodityDiv)){
            l_lastDailyOtherCountInfoUnit.sellOrderCount = l_lngDailySellOrderCount + "";
            l_lastDailyOtherCountInfoUnit.executeCount = l_lngDailyExecuteCount + "";
		}
        //1.17 「(1)日次明細用」リストに、その他件数情報を追加する。 
        //[引数] 
        //その他件数情報オブジェクト
        l_lisDailyDetail.add(l_lastDailyOtherCountInfoUnit);
        
        //集計対象最終月分処理
        //1.18 その他件数情報インスタンスを生成する。(集計対象最終月分の月次合計用)
        WEB3AioOtherCountInfomationUnit l_lastMonthlyOtherCountInfoUnit = 
            new WEB3AioOtherCountInfomationUnit();
        
        //1.19 (*)プロパティセット
        //(*)以下の通りに、プロパティにセットする。
        //その他件数情報.発注日 = 集計エリア.年月(YYYYMM)
        //その他件数情報.件数１ = 集計エリア.件数１.toString()
        //その他件数情報.件数２ = 集計エリア.件数２.toString()
        //その他件数情報.件数３ = 集計エリア.件数３.toString()
        l_lastMonthlyOtherCountInfoUnit.orderBizDate = l_strYearMonth;
        l_lastMonthlyOtherCountInfoUnit.buyOrderCount = l_lngMonthlyBuyOrderCount + "";
		if (!WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(l_request.commodityDiv)){
            l_lastMonthlyOtherCountInfoUnit.sellOrderCount = l_lngMonthlySellOrderCount + "";
            l_lastMonthlyOtherCountInfoUnit.executeCount = l_lngMonthlyExecuteCount + "";
		}
        //1.20 「(2)月次合計用」リストに、その他件数情報を追加する。 
        //[引数] 
        //その他件数情報オブジェクト 
        l_lisMonthlyCount.add(l_lastMonthlyOtherCountInfoUnit);
        
        //1.21 リストから配列を取得する。
        WEB3AioOtherCountInfomationUnit[] l_dailyOtherCountInfomationUnits = 
            new WEB3AioOtherCountInfomationUnit[l_lisDailyDetail.size()];
        l_lisDailyDetail.toArray(l_dailyOtherCountInfomationUnits);
        
        //1.22 リストから配列を取得する。
        WEB3AioOtherCountInfomationUnit[] l_monthlyOtherCountInfomationUnits = 
            new WEB3AioOtherCountInfomationUnit[l_lisMonthlyCount.size()];
        l_lisMonthlyCount.toArray(l_monthlyOtherCountInfomationUnits);
        
        //1.23 発注日別件数情報のインスタンスを生成する。 
        WEB3AioDailyOrderCountUnit l_dailyOrderCountUnit = 
            new WEB3AioDailyOrderCountUnit();
        
        //1.24 (*)プロパティセット
        //(*)以下の通りに、プロパティにセットする。
        //発注日別件数情報.その他件数情報　@＝　@toArray()の戻り値((1)日次明細用)
        l_dailyOrderCountUnit.otherCountInfomationUnits = 
            l_dailyOtherCountInfomationUnits;
        
        //1.25 発注月別件数情報インスタンスを生成する。
        WEB3AioMonthlyOrderCountUnit l_monthlyOrderCountUnit = 
            new WEB3AioMonthlyOrderCountUnit();
        
        //1.26 (*)プロパティセット
        //(*)以下の通りに、プロパティにセットする。
        //発注月別件数情報.その他件数情報　@＝　@toArray()の戻り値((2)月次合計用)
        l_monthlyOrderCountUnit.otherCountInfomationUnits = 
            l_monthlyOtherCountInfomationUnits;
        
        //1.27 その他件数照会情報のインスタンスを生成する。
        WEB3AioOtherCountReferenceUnit l_otherCountReferenceUnit = 
            new WEB3AioOtherCountReferenceUnit();
        
        //1.28 (*)プロパティセット
        //(*)以下の通りに、プロパティをセットする。
        //その他件数照会.発注月別件数情報　@＝　@発注月別件数情報
        l_otherCountReferenceUnit.monthlyOrderCountUnits = l_monthlyOrderCountUnit;
        
        //その他件数照会.発注日別件数情報　@＝　@発注日別件数情報
        l_otherCountReferenceUnit.dailyOrderCountUnits = l_dailyOrderCountUnit;
                
        //1.29 レスポンスデータを生成する。
        WEB3AdminAioOtherCountReferenceResponse l_response = 
            (WEB3AdminAioOtherCountReferenceResponse)l_request.createResponse();
        
        //1.30 (*)プロパティセット
        //(*)レスポンスデータに、プロパティをセットする。
        //レスポンスデータ.その他件数照会情報　@＝　@その他件数照会情報
        l_response.otherCountReferenceUnits = l_otherCountReferenceUnit;
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * その他用注文件数テーブルからデータを取得する際の条件を生成する。 <BR>
     * <BR>
     *１）空の文字列を生成する <BR>
     *<BR>
     *２）証券会社コード <BR>
     *<BR>
     *" institution_code = ?"を１）の文字列に追加する。 <BR>
     *<BR>
     *３）部店コード <BR>
     *<BR>
     *３－１）引数.リクエストデータ.部店コード.length() == 1 の場合 <BR>
     *<BR>
     *" and branch_code = ?"を１）の文字列に追加する。 <BR>
     *<BR>
     *３－２）引数.リクエストデータ.部店コード.length() > 1 の場合 <BR>
     *<BR>
     *" and (branch_code= ? or branch_code= ? or ... or branch_code= ?)"<BR>
     *  を１）の文字列に追加する。 <BR>
     *<BR>
     *※" branch_code = ?"の数は、部店コードの要素数と同じ <BR>
     *<BR>
     *４）レコード区分 <BR>
     *<BR>
     *" and record_div = ?"を１）の文字列に追加する。 <BR>
     *<BR>
     *５）商品区分 <BR>
     *<BR>
     *" and product_div = ?"を１）の文字列に追加する。 <BR>
     *<BR>
     *６）決済機@関ID <BR>
     *<BR>
     *引数.リクエストデータ.決済機@関ID != null　@の場合 <BR>
     *" and pay_scheme_id = ?"を１）の文字列に追加する。 <BR>
     *<BR>
     *７）発注日 <BR>
     *<BR>
     *" and biz_date >= ?"を１）の文字列に追加する。 <BR>
     *<BR>
     *８）文字列を返却する<BR>
     *
     * @@param l_request - リクエストデータ
     * @@return String
     * @@roseuid 41E38B5E02BA
     */
    protected String createQueryString(
            WEB3AdminAioOtherCountReferenceRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = "createQueryString(" +
                "WEB3AdminAioOtherCountReferenceRequest l_request)";
        log.entering(l_strMethodName);
        
        //１）空の文字列を生成する 
        StringBuffer l_strBuffer = new StringBuffer();
        
        //２）証券会社コード 
        //" institution_code = ?"を１）の文字列に追加する。 
        l_strBuffer.append(" institution_code = ? ");
        
        //３）部店コード 
        //３－１）引数.リクエストデータ.部店コード.length() == 1 の場合 
        //" and branch_code = ?"を１）の文字列に追加する。 
        StringBuffer l_strBufferBranch = new StringBuffer();
        l_strBufferBranch.append(" and branch_code in ( ? ");    
        
        //３－２）引数.リクエストデータ.部店コード.length() > 1 の場合 
        //" and (branch_code= ? or branch_code= ? or ... 
        //  or branch_code= ?)"を１）の文字列に追加する。 
        //※" branch_code = ?"の数は、部店コードの要素数と同じ 
        
        for (int i = 1; i < l_request.branchCodeList.length; i++)
        {
            l_strBufferBranch.append(", ? ");
        }        
        l_strBufferBranch.append(")");
        
        l_strBuffer.append(l_strBufferBranch);
        
        //４）レコード区分 
        //" and record_div = ?"を１）の文字列に追加する。
        l_strBuffer.append(" and record_div = ? ");

        //５）商品区分 
        //" and product_div = ?"を１）の文字列に追加する。
        l_strBuffer.append(" and product_div = ? ");
        
        //６）決済機@関ID 
        //引数.リクエストデータ.決済機@関ID != null　@の場合 
        //" and pay_scheme_id = ?"を１）の文字列に追加する。 
        if(!WEB3StringTypeUtility.isEmpty(l_request.paySchemeId))
        {
            l_strBuffer.append(" and pay_scheme_id = ? ");
        }
        
        //７）発注日 
        //" and biz_date >= ?"を１）の文字列に追加する。 
        l_strBuffer.append(" and biz_date >= ? ");
        
        //８）文字列を返却する        
        log.exiting(l_strMethodName);
        return l_strBuffer.toString();
    }
    
    /**
     * (create検索条件データコンテナ )<BR>
     * その他用注文件数テーブルからデータを取得する際の条件のデータコンテナを生成する。<BR> 
     * <BR>
     * １）空のArrayListを生成する。 <BR>
     * <BR>
     * ２）証券会社コード <BR>
     * <BR>
     * 引数.証券会社コードを１）のListに追加する。 <BR>
     * <BR>
     * ３）部店コード <BR>
     * <BR>
     * 引数.リクエストデータ.部店コードの各要素を１）のListに追加する。 <BR>
     * <BR>
     * ４）レコード区分 <BR>
     * <BR>
     * ”0”を１）のListに追加する。 <BR>
     * <BR>
     * ５）商品区分 <BR>
     * <BR>
     * 引数.リクエストデータ.商品区分を１）のListに追加する。 <BR>
     * <BR>
     * ６）決済機@関ID <BR>
     * <BR>
     * 引数.リクエストデータ.決済機@関ID != null　@の場合 <BR>
     * 引数.リクエストデータ.決済機@関IDを１）のListに追加する。 <BR>
     * <BR>
     * ７）発注日 <BR>
     * <BR>
     * 発注日(*1)を１）のListに追加する。 <BR>
     * <BR>
     * ８）Listから配列を取得して、返却する。 <BR>
     * <BR>
     * (*1)発注日 <BR>
     * TradingSystem.getBizDate()にて業務日付を取得 <BR>
     * 発注日 = (業務日付 - 2ヶ月)の年月(YYYYMM) + ”01” <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_request - リクエストデータ
     * @@param l_strStatusDiv - ステータス区分
     * 
     * @@return String[]
     * @@roseuid 41E5E3FB01F0
     */
    protected String[] createQueryContainer(
            String l_strInstitutionCode, 
            WEB3AdminAioOtherCountReferenceRequest l_request) 
    {
        String l_strMethodName = 
            "createQueryContainer(String l_strInstitutionCode, " +
            "WEB3AdminAioOtherCountReferenceRequest l_request";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //１）空のArrayListを生成する。
        List l_lisValue = new ArrayList();
        
        //２）証券会社コード 
        //引数.証券会社コードを１）のListに追加する。 
        l_lisValue.add(l_strInstitutionCode);
        
        //３）部店コード 
        //引数.リクエストデータ.部店コードの各要素を１）のListに追加する。
        for (int i = 0; i < l_request.branchCodeList.length; i++)
        {
            l_lisValue.add(l_request.branchCodeList[i]);
        }
        
        //４）レコード区分 
        //”0”を１）のListに追加する。       
        l_lisValue.add(WEB3AioOtherOrderRecordDivDef.DAILY_DETAIL);       
        
        //５）商品区分 
        //引数.リクエストデータ.商品区分を１）のListに追加する。       
        l_lisValue.add(l_request.commodityDiv);        
        
        //６）決済機@関ID 
        //引数.リクエストデータ.決済機@関ID != null　@の場合 
        //引数.リクエストデータ.決済機@関IDを１）のListに追加する。 
        if (!WEB3StringTypeUtility.isEmpty(l_request.paySchemeId))
        {
            l_lisValue.add(l_request.paySchemeId);
        }
        
        //７）発注日 
        //発注日(*1)を１）のListに追加する。 
        //(*1)発注日 
        //TradingSystem.getBizDate()にて業務日付を取得 
        //発注日 = (業務日付 - 2ヶ月)の年月(YYYYMM) + ”01” 
        String l_strBusinessDate = 
            WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMM");
        
        int l_intBizDate = 
            Integer.parseInt(l_strBusinessDate.substring(0, 4)) * 12 + 
            Integer.parseInt(l_strBusinessDate.substring(4, 6)) - 2;
        
        String l_strBizDate = 
            WEB3StringTypeUtility.formatNumber((int)(l_intBizDate / 12)) + 
            WEB3StringTypeUtility.formatNumber(l_intBizDate % 12, 2) + "01";
             
        l_lisValue.add(l_strBizDate);
        
        //８）Listから配列を取得して、返却する。
        String[] l_strValue = new String[l_lisValue.size()];
        l_lisValue.toArray(l_strValue);
        
        log.exiting(l_strMethodName);
        
        return l_strValue;
    }
    
    /**
     * (getその他用注文件数一覧)<BR>
     * 入力画面取得処理を行う。<BR><BR>
     * <BR>
     * 引数の条件に該当するその他用注文件数Paramsの一覧を返却する。<BR> 
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR> 
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ] <BR>
     * 　@　@arg0：　@その他用注文件数Row.TYPE <BR>
     * 　@　@arg1：　@引数.検索条件文字列 <BR>
     * 　@　@arg2：　@引数.ソート条件 <BR>
     * 　@　@arg3：　@null <BR>
     * 　@　@arg4：　@引数.検索条件データコンテナ <BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。 <BR>
     * <BR>
     * ３）２）の検索結果を返却する。 <BR>
     * 
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * 
     * @@return List
     * @@roseuid 421ADE7F03B2
     */
    protected List getOtherCountReferenceUnit(
            String l_strQueryString, 
            Object[] l_queryContainer, 
            String l_strSortCond) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getOtherCountReferenceUnit(String l_strQueryString," +  
            "Object[] l_queryContainer, String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRows  = null;
        try
        {   
            //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
            //[doFindAllQuery()にセットするパラメータ] 
            //arg0：　@その他用注文件数Row.TYPE 
            //arg1：　@引数.検索条件文字列 
            //arg2：　@引数.ソート条件 
            //arg3：　@null 
            //arg4：　@引数.検索条件データコンテナ 
            l_lisRows  = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrderExecutedCountRow.TYPE, 
                    l_strQueryString, 
                    l_strSortCond, 
                    null, 
                    l_queryContainer); 
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //検索結果が取得できなかった場合、nullを返却する。
        if (l_lisRows.size() == 0)
        {
            return null;
        }
        
        //２）１）の検索結果を返却する。        
        log.exiting(STR_METHOD_NAME);        
        return l_lisRows;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件を作成する。 <BR>
     * <BR>
     * １）テーブル列物理名より、以下のソート条件を表すソート条件文字列を作成する。 <BR>
     * <BR>
     * その他用注文件数テーブル.発注日　@昇順 <BR>
     * <BR>
     * ２）作成したソート条件文字列を返却する。 <BR>
     * 
     * @@return String
     * @@roseuid 421ADE7F03B2
     */
    protected String createSortCond() 
    {
        String l_strMethodName = "createSortCond()";
        log.entering(l_strMethodName);        
        
        String l_strSort = new String();
        
        //１）テーブル列物理名より、以下のソート条件を表すソート条件文字列を作成する。 
        //その他用注文件数テーブル.発注日　@昇順 
        l_strSort = " biz_date ";
        
        //２）作成したソート条件文字列を返却する。
        log.exiting(l_strMethodName);
        
        return l_strSort;
    }
    
    /**
     * (get商品区分)<BR>
     * 証券会社毎の商品区分を取得し返却する。 <BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ] <BR>
     * 　@　@arg0：　@その他用注文件数Row.TYPE <BR>
     * 　@　@arg1：　@” institution_code = ?” <BR>
     * 　@　@arg2：　@” product_div” <BR>
     * 　@　@arg3：　@null <BR>
     * 　@　@arg4：　@Object[]{引数.証券会社コード} <BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。 <BR>
     * 
     * ２）商品区分を、「4:信用保証金HULFT送信」、<BR>
     *    及び重複レコードを取除いて配列にセットする。 <BR>
     * <BR>
     * ２－１）ArrayListを生成する。 <BR>
     * <BR>
     * ２－２）1レコード目の行オブジェクト.get商品区分の値を格納するエリアを生成し、<BR>
     * 値を退避する。 <BR>
     * 　@　@　@　@(1レコード目の行オブジェクト.get商品区分 = ”4”の場合は2レコード目以降<BR>
     * の行オブジェクト.get商品区分 != ”4”の値を退避する) <BR>
     * <BR>
     * ２－３）退避した商品区分をListに追加する。 <BR>
     * <BR>
     * ２－４）２－２）以降のレコード数分Loop処理を実施し以下の処理を行う。 <BR>
     * <BR>
     * 　@　@　@退避エリアの商品区分 != 行オブジェクト.get商品区分、<BR>
     *          且つ行オブジェクト.get商品区分 != ”4”の場合、 <BR>
     * 　@　@　@商品区分をListに追加し、<BR>
     *          退避エリアに行オブジェクト.get商品区分の値を代入する。<BR> 
     * <BR>
     * ２－５）Listから配列を取得する。 <BR>
     * <BR>　@　@　@　@ 
     * ３）取得した配列を返却する。<BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     *
     * @@return String[]
     * @@roseuid 421ADE7F03B2
     */
    protected String[] getCommodityDiv(String l_strInstitutionCode) 
    throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getCommodityDiv(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRows  = null;
        try
        {   
            //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
            //[doFindAllQuery()にセットするパラメータ] 
            //arg0：　@その他用注文件数Row.TYPE 
            //arg1：　@” institution_code = ?” 
            //arg2：　@” product_div” 
            //arg3：　@null 
            //arg4：　@Object[]{引数.証券会社コード} 

            Object[] l_bindVars = {l_strInstitutionCode};
            l_lisRows  = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrderExecutedCountRow.TYPE, 
                    " institution_code = ?", 
                    " product_div", 
                    null, 
                    l_bindVars ); 
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //検索結果が取得できなかった場合、nullを返却する。
        if (l_lisRows == null || l_lisRows.size() == 0)
        {
            return null;
        }

        //２）商品区分を、「4:信用保証金HULFT送信」、及び重複レコードを取除いて配列にセットする。 
        //２－１）ArrayListを生成する。 
        List l_lisProductDiv = new ArrayList();
        
        //２－２）1レコード目の行オブジェクト.get商品区分の値を格納するエリアを生成し、値を退避する。 
        //　@(1レコード目の行オブジェクト.get商品区分 = ”4”の場合は
        //  2レコード目以降の行オブジェクト.get商品区分 != ”4”の値を退避する) 

        OtherOrderExecutedCountRow l_otherOrderCountRow = 
            (OtherOrderExecutedCountRow) l_lisRows.get(0);
        String l_strProductDiv = l_otherOrderCountRow.getProductDiv();
        
        //２－３）退避した商品区分をListに追加する。
        if (!WEB3AioOtherOrderProductDivDef.MARGIN_GUARANTEE_HULFT.equals(
                l_strProductDiv))
        {
            l_lisProductDiv.add(l_strProductDiv);
        }
            
        for (int i = 1; i < l_lisRows.size(); i++)
        {
            l_otherOrderCountRow = (OtherOrderExecutedCountRow) l_lisRows.get(i);
            //退避エリアの商品区分 != 行オブジェクト.get商品区分、
            //且つ行オブジェクト.get商品区分 != ”4”の場合、
            if (!l_strProductDiv.equals(l_otherOrderCountRow.getProductDiv()) && 
                    !WEB3AioOtherOrderProductDivDef.MARGIN_GUARANTEE_HULFT.equals(
                            l_otherOrderCountRow.getProductDiv()))
            {
                //商品区分をListに追加し、退避エリアに行オブジェクト.get商品区分の値を代入する。 
                l_lisProductDiv.add(l_otherOrderCountRow.getProductDiv());
                l_strProductDiv = l_otherOrderCountRow.getProductDiv();
            }
        }
        //２－５）Listから配列を取得する。
        String[] l_strCommodityDivs =  new String[l_lisProductDiv.size()];
        l_lisProductDiv.toArray(l_strCommodityDivs);
        
        //３）取得した配列を返却する。
        log.exiting(STR_METHOD_NAME);        
        return l_strCommodityDivs;
    }
}
@
