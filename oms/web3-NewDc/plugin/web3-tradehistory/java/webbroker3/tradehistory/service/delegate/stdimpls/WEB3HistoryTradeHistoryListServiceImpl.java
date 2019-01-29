head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧サービスImpl(WEB3HistoryTradeHistoryListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 範慧琴 (中訊) 新規作成
Revesion History : 2005/10/07 沢村 (SRA) 電子鳩未使用対応
Revesion History : 2005/10/26 水落 (SRA) 内藤証券対応
Revesion History : 2005/11/08 王維（日本中訊）外株対応・障害U02526対応
Revesion History : 2006/01/23 李志強(日本中訊) 仕様変更・モデル043
Revesion History : 2006/01/27 鈴木（SRA) 仕様変更管理No.044
Revesion History : 2006/04/25 凌建平(中訊) 仕様変更・モデル047-051
Revesion History : 2006/05/01 鈴木(SCS) calc口座残高、create取引履歴情報 修正対応
Revesion History : 2006/05/29 相馬(SCS) create取引履歴情報 修正対応
Revesion History : 2006/06/27 鈴木(SCS) calc口座残高 修正対応・モデル055
Revesion History : 2006/10/19 張騰宇 (中訊) モデル 057
Revesion History : 2007/06/14 周墨洋 (中訊) モデル 071
*/
package webbroker3.tradehistory.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3DepositMarginDivDef;
import webbroker3.common.define.WEB3IoDivDef;
import webbroker3.common.define.WEB3ReportDivDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TodayPaymentCheckDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.util.WEB3FeqDateUtility;

import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryListCSV;
import webbroker3.tradehistory.data.TradeHistoryParams;
import webbroker3.tradehistory.data.TradeHistoryRow;
import webbroker3.tradehistory.data.TransactionHistoryDao;
import webbroker3.tradehistory.data.TransactionHistoryParams;
import webbroker3.tradehistory.data.TransactionHistoryRow;
import webbroker3.tradehistory.define.WEB3HistoryKeyItemDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryBondDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryCpitalGainTaxDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryEquityDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryEquityMarginDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryFuturesDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryFuturesOptionDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryMarginDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryMutualFundRuitoDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryOptionDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductDivDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryRemarkCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTradeCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryForeignDef;
import webbroker3.tradehistory.message.WEB3HistoryDailyBalanceUnit;
import webbroker3.tradehistory.message.WEB3HistorySortKeyUnit;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryUnit;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeHistoryListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (取引履歴一覧サービスImpl)<BR>
 * 取引履歴一覧サービス実装クラス<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryListServiceImpl extends WEB3ClientRequestService implements WEB3HistoryTradeHistoryListService 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3HistoryTradeHistoryListServiceImpl.class);   

    /**
     * (wk受渡金額)         
     * wk受渡金額（work変数）<BR> 
     * <BR>
     * このwork変数はis金額補正()とcalc口座残高()の <BR>
     * 計算処理にて使用します。<BR>         
     */
    private double wkNetAmount = 0D;
    
    /**
     * @@roseuid 41789C470261
     */
    public WEB3HistoryTradeHistoryListServiceImpl() 
    {
     
    }
    
    /**
     * 取引履歴一覧画面表示処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * １）引数のリクエストデータが、取引履歴一覧リクエストの場合<BR>
     * 　@　@パラメータのリクエストデータを<BR>
     * 　@　@取引履歴一覧リクエストにキャストして<BR>
     * 　@　@get取引履歴一覧画面()メソッドをコールする。<BR>
     * <BR>
     * ２）引数のリクエストデータが、取引履歴一覧ファ@イルダウンロードリクエストの場合<BR>
     * 　@　@パラメータのリクエストデータを<BR>
     * 　@　@取引履歴一覧ファ@イルダウンロードリクエストにキャストして<BR>
     * 　@　@get取引履歴一覧ファ@イルダウンロード()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 413C2ECB00E5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3HistoryTradeHistoryListRequest)
        {
            l_response = this.getTradeHistoryListScreen((WEB3HistoryTradeHistoryListRequest)l_request);
        }
        else if(l_request instanceof WEB3HistoryTradeHistoryDownloadRequest)
        {
            l_response = this.getTradeHistoryListDownload((WEB3HistoryTradeHistoryDownloadRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get取引履歴一覧画面)<BR>
     * 取引履歴一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(取引履歴一覧サービス)get取引履歴一覧画面」参照<BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  「(取引履歴一覧サービス)get取引履歴一覧画面」        
     * <BR>
     *         具体位置    :  1.8  get取引履歴一覧(String, String[], String)    <BR>
     *         取引履歴Paramsの一覧を取得する。 <BR>
     *         nullが返却された場合は、 <BR>
     *         「条件該当取引未存在エラー」の例外をスローする。  <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :  BUSINESS_ERROR_01070         <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     *         シーケンス図 :  「(取引履歴一覧サービス)get取引履歴一覧画面」        
     * <BR>
     *         具体位置    :  1.9.3  get取引履歴一覧(String, String[], String)    <BR>
     *         取引履歴Paramsの一覧を取得する。<BR>
     *         nullが返却された場合は、<BR>
     *         「条件該当取引未存在エラー」の例外をスローする。 <BR>
     *         class         :  WEB3BusinessLayerException       <BR>
     *         tag            :   BUSINESS_ERROR_01070        <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 取引履歴一覧リクエストオブジェクト<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListResponse
     * @@roseuid 413FAB3403D9
     */
    protected WEB3HistoryTradeHistoryListResponse getTradeHistoryListScreen(WEB3HistoryTradeHistoryListRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradeHistoryListScreen(WEB3HistoryTradeHistoryListRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
       
        //1.4 validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5 create検索条件文字列
        Date l_datListStartDate = WEB3DateUtility.getDate(l_request.listStartDate, "yyyyMMdd");
        Date l_datListEndDate = WEB3DateUtility.getDate(l_request.listEndDate, "yyyyMMdd");
        
        String l_queryString = this.createQueryString(l_datListStartDate, l_datListEndDate, l_request.productCode, l_request.commodityType) ;       
        
        //1.6 create検索条件データコンテナ 
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_mainAccount, l_datListStartDate, l_datListEndDate, l_request.productCode, l_request.commodityType);

        //1.7 createソート条件
        String l_strSortCond = this.createSortCond(l_request.sortKeys); 
        
        
        //1.8 get取引履歴一覧
        List l_lisTradeHistorys = WEB3HistoryTradeHistoryDataManager.getTradeHistoryList(l_queryString, l_strQueryDataContainers, l_strSortCond);
        int  l_intSize = l_lisTradeHistorys.size();
        //1.15 createResponse()
        WEB3HistoryTradeHistoryListResponse l_response = (WEB3HistoryTradeHistoryListResponse)l_request.createResponse();

        if (l_intSize == 0)
        {
            l_response.tradingReportFlag = false;
            l_response.totalPages = "1";
            l_response.pageIndex = "1";
            l_response.totalRecords = "0";
            l_response.dailyBalanceUnits = null;
            return l_response;            
        }
        
        //1.9 (*1)分岐フロー
        if(l_request.listStartDate == null&&l_request.listEndDate == null )
        {
 
            //取引履歴の最新の受渡日を取得する
            Date l_datLatestDeliveryDate = ((TradeHistoryParams)l_lisTradeHistorys.get(0)).getDeliveryDate();
            
            for (int i = 1;i < l_intSize;i ++)
            {
                if (WEB3DateUtility.compareToDay(((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate(), l_datLatestDeliveryDate) > 0)
                {
                    l_datLatestDeliveryDate = ((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate();
                }
            }
            //取引履歴の最新の受渡日の一ヶ月前日付を取得する
            Calendar l_calendar = new GregorianCalendar();
            l_calendar.setTime(l_datLatestDeliveryDate);
            l_calendar.add(Calendar.MONTH, -1);
            
            //1.9.1 create検索条件文字列         
            l_queryString = this.createQueryString(l_calendar.getTime(), l_datLatestDeliveryDate, l_request.productCode, l_request.commodityType) ;            

            //1.9.2 create検索条件データコンテナ 
            l_strQueryDataContainers = this.createQueryDataContainer(l_mainAccount, l_calendar.getTime(), l_datLatestDeliveryDate, l_request.productCode, l_request.commodityType);

            //1.9.3 get取引履歴一覧
            l_lisTradeHistorys = WEB3HistoryTradeHistoryDataManager.getTradeHistoryList(l_queryString, l_strQueryDataContainers, l_strSortCond);
            l_intSize = l_lisTradeHistorys.size();
            if (l_intSize == 0)
            {
                l_response.tradingReportFlag = false;
                l_response.totalPages = "1";
                l_response.pageIndex = "1";
                l_response.totalRecords = "0";
                l_response.dailyBalanceUnits = null;
                return l_response;           
            } 
        }
        
        //1.10 取引履歴情報オブジェクトを格納する為のリストを生成。
        List l_lisTradeHistoryUnits = new ArrayList();
                       
        //1.11 受渡日別残高情報オブジェクトを格納する為のリストを生成。
        List l_lisDailyBalanceUnits = new ArrayList();
        
        //1.12 is当日出金(long)(取引履歴一覧サービスImpl::is当日出金)
		// [引数] 
		//  部店ID：顧客.getBranch().getBranchId()
        boolean l_blnIsTodayPayment =
            this.isTodayPayment(l_mainAccount.getBranch().getBranchId());

        //1.13 (*) get取引履歴一覧()の戻り値のうち、表示対象行(fromIndex ～ toIndex)の間Loop処理を実施する             
        //表示対象行 fromIndex の計算
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
   
        if (l_intRequestPageIndex < 1)
        {
            l_intRequestPageIndex = 1;
        }
        if (l_intRequestPageSize < 1)
        {
            l_intRequestPageSize = l_intSize;
        }
        
        // 総ページ数:
        int l_intTotalPages; 
        ///*-start--------------------------------------------- 
        // 明細の要素数÷ページ内表示行数
        if (l_intSize % l_intRequestPageSize == 0)
        {
            l_intTotalPages = l_intSize / l_intRequestPageSize;
        }
        //※余りが出る場合は、＋１した値を設定
        else
        {
            l_intTotalPages = l_intSize / l_intRequestPageSize + 1;
        }
        //-end-----------------------------------------------         
        
        //要求ページ番号
        int l_intPageIndex =
            (l_intRequestPageIndex > l_intTotalPages)
                ? l_intTotalPages
                : l_intRequestPageIndex;
        l_intPageIndex = (l_intPageIndex < 1) ? 1 : l_intPageIndex;
        
        //表示対象行fromIndex
        int l_intFromIndex = l_intRequestPageSize * (l_intPageIndex - 1);

        //表示対象行 toIndexの計算  
        int l_intToIndex = l_intRequestPageSize * l_intPageIndex;
        if (l_intToIndex > l_intSize)
        {
            l_intToIndex = l_intSize;
        }
               
        for(int i = l_intFromIndex;i <= l_intToIndex - 1; i ++ )
        {
            //1.13.1 create取引履歴情報
            WEB3HistoryTradeHistoryUnit l_web3HistoryTradeHistoryUnit = this.createTradeHistoryUnit((TradeHistoryParams)l_lisTradeHistorys.get(i));

			//1.13.2 is金額補正(取引履歴Params, 顧客)(取引履歴一覧サービスImpl::is金額補正)
			// アイテムの定義
			// 	金額補正のレコード判別し、補正を行う。 
			//  [引数] 
			//  　@顧客：　@getMainAccount()の戻り値 
			//  　@取引履歴Prams：処理対象の取引履歴Params
            
            //is当日出金()の戻り値がtrueの場合のみ実施
            if (l_blnIsTodayPayment)
            {
                this.isPaymentRevision((TradeHistoryParams)l_lisTradeHistorys.get(i), l_mainAccount);    
            }
            
            //1.13.3 add(取引履歴情報オブジェクト)
            l_lisTradeHistoryUnits.add(l_web3HistoryTradeHistoryUnit);    
            
            //1.13.4 (*2)分岐フロー
            //Loop処理最後の要素の場合 または、
            //次回の取引履歴Params.受渡日と今回の取引履歴Params.受渡日が異なる場合
            //以下の処理を実施する。
            //2004.11.24 17:30 範慧琴　@修正
            if((i == l_intToIndex - 1)||
                ((i < l_intToIndex - 1)&&WEB3DateUtility.compareToDay(((TradeHistoryParams)l_lisTradeHistorys.get(i+1)).getDeliveryDate(), ((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate()) != 0 ))
            {
                //1.13.4.1 取引履歴情報の配列を取得する。
                WEB3HistoryTradeHistoryUnit[] l_web3HistoryTradeHistoryUnits = new WEB3HistoryTradeHistoryUnit[l_lisTradeHistoryUnits.size()];
                l_lisTradeHistoryUnits.toArray(l_web3HistoryTradeHistoryUnits); 
                
                //1.13.4.2 create受渡日別残高情報
                WEB3HistoryDailyBalanceUnit l_web3HistoryDailyBalanceUnit= this.createDailyBalanceUnit(l_mainAccount, ((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate(), l_web3HistoryTradeHistoryUnits);  
                
				//1.13.4.3 calc口座残高(受渡日別残高情報)(取引履歴一覧サービスImpl::calc口座残高)
				// [引数] 
				// 　@受渡日別残高情報：　@create受渡日残高情報()の戻り値 
                this.calcAccountBalance(l_web3HistoryDailyBalanceUnit);
                
                //1.13.4.4 toArray()の戻り値をプロパティ：取引履歴情報一覧にセット。
                l_web3HistoryDailyBalanceUnit.tradeHistoryUnits = l_web3HistoryTradeHistoryUnits;
                
                //1.13.4.5(*)検索条件文字列、データコンテナの作成
                //以前に作成した検索条件文字列に受渡日の条件を追加する。

                //検索条件文字列 = create検索条件文字列()の戻り値 + " and delivery_date = ? "
                //検索条件データコンテナ = create検索条件データコンテナ()の戻り値の末尾に、
                //        処理対象の取引履歴Params.受渡日を追加する。
                
                String l_query = l_queryString + " and to_char(delivery_date,'YYYY/MM/DD') = ? ";
                int l_lenth = 0;
                if (l_strQueryDataContainers != null)
                {
                    l_lenth = l_strQueryDataContainers.length;
                }
                
                String[] l_newContainers = new String[l_lenth + 1];
                for (int j = 0; j < l_lenth; j++)
                {
                    l_newContainers[j] = l_strQueryDataContainers[j];
                }
                Date l_dateDeliveryDate = ((TradeHistoryParams)l_lisTradeHistorys.get(i)).getDeliveryDate();
                l_newContainers[l_lenth] = WEB3DateUtility.formatDate(l_dateDeliveryDate,"yyyy/MM/dd");
                
                
                //1.13.4.6get取引履歴一覧(検索条件文字列 : String, 検索条件データコンテナ : String[], ソート条件 : String)
                //指定した受渡日の取引履歴Paramsの一覧を取得する。

                //[引数]
                //検索条件文字列：　@create検索条件文字列()の戻り値
                //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値
                //ソート条件：　@createソート条件()の戻り値
                List l_tradeHistoryList = WEB3HistoryTradeHistoryDataManager.getTradeHistoryList(l_query,l_newContainers,l_strSortCond);
                //1.13.4.7 is損益明細リンク(Date, String, 取引履歴情報[])
                //損益明細リンクフラグを貼るかどうか判別する。

                //[引数]
                //  受渡日：　@処理対象の取引履歴Params.受渡日
                //  口座残高：　@create受渡日別残高情報()の戻り値.口座残高
                //  取引履歴情報一覧：　@get取引履歴一覧()の戻り値　@※受渡日を条件に加えて検索した結果。                              
                if (l_tradeHistoryList != null && l_tradeHistoryList.size() > 0)
                {
                    int l_intTradeHistoryListSize = l_tradeHistoryList.size();
                    WEB3HistoryTradeHistoryUnit[] l_historyTradeHistoryUnits = new WEB3HistoryTradeHistoryUnit[l_intTradeHistoryListSize];
                    
                    for (int j = 0; j < l_intTradeHistoryListSize; j++)
                    {
                        l_historyTradeHistoryUnits[j] =  createTradeHistoryUnit((TradeHistoryParams)l_tradeHistoryList.get(j));
                    }
                    
                    boolean l_isProfitLossLink = this.isProfitLossLink(l_dateDeliveryDate,l_web3HistoryDailyBalanceUnit.accountBalance,l_historyTradeHistoryUnits);
                    //1.14.4.8(*)is損益明細リンク()の戻り値をプロパティ：損益明細リンクフラグにセットする。
                    l_web3HistoryDailyBalanceUnit.profitLossLink = l_isProfitLossLink;
                }

                //1.13.4.9 add(受渡日別残高情報オブジェクト)
                l_lisDailyBalanceUnits.add(l_web3HistoryDailyBalanceUnit);
                
                //1.13.4.10 (次回処理の準備)取引履歴情報を格納するArrayListの要素をクリアする。
                l_lisTradeHistoryUnits.clear();      
            }               
        }
        
        
        //1.14 受渡日別残高情報の配列を取得する。
        WEB3HistoryDailyBalanceUnit[] l_web3HistoryDailyBalanceUnits = new WEB3HistoryDailyBalanceUnit[l_lisDailyBalanceUnits.size()];
        l_lisDailyBalanceUnits.toArray(l_web3HistoryDailyBalanceUnits);
        
//        WEB3GentradeBatoClientService l_batoClientService = 
//            (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);
//        //1.14 validate電子鳩実施(機@能区分 : String)
//        String l_strBato = null;
        boolean l_blnTradingReportFlag;    
//        try
//        {
//            l_strBato = l_batoClientService.validateBato(WEB3GentradeBatoFunctionDivDef.BATO_TRAN_HIST_SERVICE);
//            //validate電子鳩実施()の戻り値 == "0： 顧客未実施"の場合、false。
//            if (WEB3GentradeBatoServiceRegServiceResultDef.NOT_AGREEMENT.equals(l_strBato))
//            {            
//                l_blnTradingReportFlag = false;
//            }
//            //以外、trueをセット。
//            else
//            {
//                l_blnTradingReportFlag = true;
//            }
//        }
//        catch(WEB3BusinessLayerException l_ex)
//        {
//            l_blnTradingReportFlag = true;
//        }
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        // 取引報告書交付区分 == "1：電子交付"の場合、trueをセット。
        if (l_mainAccountRow.getTradingReportDiv().equals(WEB3ReportDivDef.ACCEPT))
        {
            l_blnTradingReportFlag = true;
        }
        // 以外、falseをセット。
        else
        {
            l_blnTradingReportFlag = false;
        }

        //1.16 レスポンスデータに プロパティセット
        l_response.tradingReportFlag = l_blnTradingReportFlag;
        l_response.totalPages = WEB3StringTypeUtility.formatNumber((double)l_intTotalPages);
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber((double)l_intSize);
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber((double)l_intPageIndex);
        l_response.dailyBalanceUnits = l_web3HistoryDailyBalanceUnits;    

        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }

    /**
     * (get取引履歴一覧ファ@イルダウンロード)<BR>
     * 取引履歴一覧ファ@イルダウンロード処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(取引履歴一覧サービス)get取引履歴一覧ファ@イルダウンロード」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 取引履歴一覧ファ@イルダウンロードリクエスト<BR>
     * @@return WEB3HistoryTradeHistoryDownloadResponse
     * @@roseuid 413C2D7A03BF
     */
    protected WEB3HistoryTradeHistoryDownloadResponse getTradeHistoryListDownload(WEB3HistoryTradeHistoryDownloadRequest l_request)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradeHistoryListDownload(WEB3HistoryTradeHistoryDownloadRequest)";
        log.entering(STR_METHOD_NAME); 

        //1.1 validate( )(取引履歴一覧ファ@イルダウンロードリクエスト::validate)
        l_request.validate();
        
        //1.2 get補助口座(補助口座タイプ : SubAccountTypeEnum)
        //  [引数] 
        //  補助口座タイプ：　@SubAccountTypeEnum.株式取引口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

		//証券会社： （get補助口座()の戻り値）.getInstitutuin()の戻り値
		WEB3GentradeInstitution l_institution = 
			(WEB3GentradeInstitution) l_subAccount.getInstitution(); 
			
        //1.3 getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
       
        //1.4 validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 取引カレンダコンテキストの設定
        //  取引カレンダコンテキストを取引履歴ダウンロードの設定にする。
        //  以下の値で取引カレンダコンテキストをリセットする。
        //  ・取引カレンダコンテキスト.受付時間区分 = "30：ダウンロード"
        //  ※上記以外は既存値。
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //受付時間区分を取得
        String l_strTradingTimeType = l_context.getTradingTimeType();

        //受付時間区分をセットする
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DOWNLOAD);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);

        //1.6 validateダウンロード時間帯()
        WEB3GentradeTradingTimeManagement.validateDownloadTimeZone();

        //1.7 取引カレンダコンテキストのリセット
        l_context.setTradingTimeType(l_strTradingTimeType);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        //1.8 create検索条件文字列(Date, Date, String, String)(取引履歴一覧サービスImpl::create検索条件文字列)
        //[引数] 
        //  表示期間From：　@リクエストデータ.表示期間From 
        //  表示期間To：　@リクエストデータ.表示期間To 
        //  銘柄コード：　@リクエストデータ.銘柄コード 
        //  商品区分：　@リクエストデータ.商品区分
        Date l_datListStartDate = WEB3DateUtility.getDate(l_request.listStartDate, "yyyyMMdd");
        Date l_datListEndDate = WEB3DateUtility.getDate(l_request.listEndDate, "yyyyMMdd");
        String l_strQueryString = this.createQueryString(
            l_datListStartDate, 
            l_datListEndDate, 
            l_request.productCode, 
            l_request.commodityType) ;            
        
        //1.9 create検索条件データコンテナ(顧客, Date, Date, String, String)(取引履歴一覧サービスImpl::create検索条件データコンテナ)
        //[引数] 
        //  顧客：　@getMainAccount()の戻り値 
        //  表示期間From：　@リクエストデータ.表示期間From 
        //  表示期間To：　@リクエストデータ.表示期間To 
        //  銘柄コード：　@リクエストデータ.銘柄コード 
        //  商品区分：　@リクエストデータ.商品区分
        String[] l_strQueryDataContainers = this.createQueryDataContainer(
            l_mainAccount,
            l_datListStartDate,
            l_datListEndDate,
            l_request.productCode,
            l_request.commodityType);

        //1.10 createソート条件forダウンロード( )(取引履歴一覧サービスImpl::createソート条件forダウンロード)
        String l_strSortCond = this.createSortCondForDownload(); 

        //1.11 get取引履歴一覧(String, String[], String)(取引履歴データマネージャ::get取引履歴一覧)
        //[引数] 
        //  検索条件文字列：　@create検索条件文字列()の戻り値 
        //  検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値 
        //  ソート条件：　@createソート条件()の戻り値
        List l_lisTradeHistoryList = WEB3HistoryTradeHistoryDataManager.getTradeHistoryList(
            l_strQueryString,
            l_strQueryDataContainers,
            l_strSortCond);

        //1.12 getプリファ@レンス(String)(取引履歴一覧サービスImpl::getプリファ@レンス)
        //[引数] 
        //  設定名称：　@固定文言"DL_REC_COUNT_TRADEHISTORYLIST"
        String l_strPreferences = this.getPreferences(WEB3SystemPreferencesNameDef.DL_REC_COUNT_TRADEHISTORYLIST);

        //1.13 レコード件数がgetプリファ@レンス()の戻り値以上の場合、
        //  「該当する件数がダウンロード件数を超えています。」エラー
        int l_intListCount = 0;
        if (l_lisTradeHistoryList != null)
        {
            l_intListCount = l_lisTradeHistoryList.size();
        }

        if (l_intListCount > Integer.parseInt(l_strPreferences))
        {
            log.debug("「該当する件数がダウンロード件数を超えています。」");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01957,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.14 レコード件数が0件の場合、「条件該当取引未存在エラー」
        if (l_intListCount == 0)
        {
            log.debug("「条件該当取引未存在エラー」");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01070,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.15 取引履歴一覧CSV( )(取引履歴一覧CSV::取引履歴一覧CSV)
        WEB3HistoryTradeHistoryListCSV l_tradeHistoryListCSV = new WEB3HistoryTradeHistoryListCSV();

        //前回の取引履歴Params.受渡日
        Date l_datDeliveryDateBefore = null;

        //今回の取引履歴Params.受渡日
        Date l_datDeliveryDateNow = null;
        
        //口座残高
		String l_strAccountBalance = null;
						
        //1.16 取引履歴Paramsの要素数分、Loop処理を実施する
        for (int i = 0; i < l_intListCount; i++)
        {
            // 前回の取引履歴Params.受渡日を取得
            l_datDeliveryDateBefore = l_datDeliveryDateNow;

            TradeHistoryRow l_tradeHistoryRow = (TradeHistoryRow) l_lisTradeHistoryList.get(i);
            TradeHistoryParams l_tradeHistoryParams = new TradeHistoryParams(l_tradeHistoryRow);

            // 今回の取引履歴Params.受渡日を取得
            l_datDeliveryDateNow = l_tradeHistoryParams.getDeliveryDate();
					
            //1.16.1 add明細行( )
            int l_intRowNumber = l_tradeHistoryListCSV.addRow();
            
            //1.16.2 set受渡日(int, Date)(取引履歴一覧CSV::set受渡日)
            //[set受渡日()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  受渡日：　@処理対象の取引履歴Params.受渡日
            l_tradeHistoryListCSV.setDeliveryDate(
                l_intRowNumber,
                l_datDeliveryDateNow);
            
            //1.16.3 set約定日(int, Date)(取引履歴一覧CSV::set約定日)
            //[set約定日()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  約定日：　@処理対象の取引履歴Params.約定日
            l_tradeHistoryListCSV.setExecutionDate(
                l_intRowNumber,
                l_tradeHistoryParams.getExecDate());
    
            //1.16.4 set商品コード名称(int, String, String, String)(取引履歴一覧CSV::set商品コード名称)
            //[set商品コード名称()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  会社コード：　@処理対象の取引履歴Params.証券会社コード 
            //  商品コード：　@処理対象の取引履歴Params.商品コード 
            //  弁済区分：　@処理対象の取引履歴Params.弁済区分
            l_tradeHistoryListCSV.setCommodityCode(
                l_intRowNumber,
                l_tradeHistoryParams.getInstitutionCode(),
                l_tradeHistoryParams.getCommodityCode(),
                l_tradeHistoryParams.getRepaymentType());
            
            //1.16.5 set銘柄(int, String, String, String)(取引履歴一覧CSV::set銘柄)
            //[set銘柄()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  商品コード：　@処理対象の取引履歴Params.商品コード 
            //  銘柄コード：　@処理対象の取引履歴Params.銘柄コード 
            //  銘柄摘要名：　@処理対象の取引履歴Params.銘柄摘要名
            l_tradeHistoryListCSV.setProduct(
                l_intRowNumber,
                l_tradeHistoryParams.getCommodityCode(),
                l_tradeHistoryParams.getProductCode(),
                l_tradeHistoryParams.getProductName());
            
            //1.16.6 set口座区分名称(int, String, String, String)(取引履歴一覧CSV::set口座区分名称)
            //[set口座区分名称()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  商品コード：　@処理対象の取引履歴Params.商品コード 
            //  口座区分：　@処理対象の取引履歴Params.口座区分 
            //  摘要コード：　@処理対象の取引履歴Params.摘要コード
            //  取引コード：　@処理対象の取引履歴Params.取引コード
            l_tradeHistoryListCSV.setAccountType(
                l_intRowNumber,
                l_tradeHistoryParams.getCommodityCode(),
                l_tradeHistoryParams.getAccountDiv(),
                l_tradeHistoryParams.getRemarkCode(),
                l_tradeHistoryParams.getTradeCode());
    
            //1.16.7 set翻訳摘要名(int, String, String)(取引履歴一覧CSV::set翻訳摘要名)
            //[set翻訳摘要名()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  商品コード：　@処理対象の取引履歴Params.商品コード 
            //  翻訳摘要名：　@処理対象の取引履歴Params.翻訳摘要名
            l_tradeHistoryListCSV.setRemarkName(
                l_intRowNumber,
                l_tradeHistoryParams.getCommodityCode(),
                l_tradeHistoryParams.getRemarkName());
            
            //1.16.8 set数量(int, String)(取引履歴一覧CSV::set数量)
            //[set数量()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  数量：　@処理対象の取引履歴Params.数量
            l_tradeHistoryListCSV.setQuantity(
                l_intRowNumber,
                WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getQuantity()));
            
            //1.16.9 set単価(int, String)(取引履歴一覧CSV::set単価)
            //[set銘単価()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  単価：　@処理対象の取引履歴Params.単価
            l_tradeHistoryListCSV.setPrice(
                l_intRowNumber,
                WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getPrice()));
    
            //1.16.10 set通貨単位名称(int, String, String)(取引履歴一覧CSV::set通貨単位名称)
            //[set通貨単位名称()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  会社コード：　@処理対象の取引履歴Params.証券会社コード 
            //  通貨単位：　@処理対象の取引履歴Params.通貨単位
            l_tradeHistoryListCSV.setMonetaryUnit(
                l_intRowNumber,
                l_tradeHistoryParams.getInstitutionCode(),
                l_tradeHistoryParams.getMonetaryUnit());
            
            //1.16.11 set受渡金額(int, String)(取引履歴一覧CSV::set受渡金額)
            //[set銘柄()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  受渡金額：　@処理対象の取引履歴Params.受渡金額
            l_tradeHistoryListCSV.setNetAmount(
                l_intRowNumber,
                WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getNetAmount()));
    
            //1.16.12 set譲渡損益(int, String)(取引履歴一覧CSV::set譲渡損益)
            //[set譲渡損益()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  譲渡損益：　@処理対象の取引履歴Params.譲渡損益
            l_tradeHistoryListCSV.setCapitalGain(
                l_intRowNumber,
                WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getCapitalGain()));
    
            //1.16.13 (*1)分岐フロー
            //(*1)分岐フロー
            // 前回の取引履歴Params.受渡日と今回の取引履歴Params.受渡日が異なる場合
            //口座残高を定義
            if (WEB3DateUtility.compare(l_datDeliveryDateBefore, l_datDeliveryDateNow) != 0)
            {
                //1.16.13.1 get口座残高(顧客, Date)(取引履歴一覧サービスImpl::get口座残高)
                //[引数]  
                //  顧客：　@getMainAccount()の戻り値  
                //  受渡日：　@処理対象の取引履歴Params.受渡日 
                l_strAccountBalance = this.getAccountBalance(
                    l_mainAccount,
                    l_datDeliveryDateNow);
            }
    
            //1.16.14 set口座残高(int, String)(取引履歴一覧CSV::set口座残高)
            //[set口座残高()に指定する引数] 
            //  行番号：　@add明細行()の戻り値 
            //  口座残高：　@get口座残高()の戻り値
            l_tradeHistoryListCSV.setAccountBalance(
                l_intRowNumber,
                l_strAccountBalance);
        }

        //1.17 getCSVファ@イル行( )
        String[] l_strCsvFileLines = l_tradeHistoryListCSV.getCsvFileLines();
        
        //1.18 createResponse( )
        WEB3HistoryTradeHistoryDownloadResponse l_response =
            (WEB3HistoryTradeHistoryDownloadResponse)l_request.createResponse();

        //1.19 プロパティセット
        //レスポンスデータにプロパティをセットする。
        //ダウンロードファ@イル  ＝　@getCSVファ@イル行()の戻り値
        l_response.downloadFile = l_strCsvFileLines;

        //現在日時        ＝　@TradingSystem.getSystemTimestamp()の戻り値
        l_response.currentDate = WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）以下の顧客情報を検索条件文字列にセットする。<BR>
     * 　@・証券会社コード<BR>
     * 　@・部店コード<BR>
     * 　@・顧客コード<BR>
     * <BR>
     * 　@検索条件文字列 = "institution_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and branch_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and account_code = ? "<BR>
     * <BR>
     * ２）パラメータ.表示期間From != null かつ <BR>
     * 　@　@パラメータ.表示期間To != nullの場合、<BR>
     * 　@　@表示期間Fromと表示期間Toを検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 +=<BR>
     * 　@　@"and delivery_date >= to_date(?, 'YYYYMMDD') "<BR>
     * 　@　@+ "and delivery_date <= to_date(?, 'YYYYMMDD') "<BR>
     * <BR>
     * ３）パラメータ.銘柄コード != nullの場合、<BR>
     * 　@　@銘柄コードを検索条件に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and product_code = ? "<BR>
     * <BR>
     * ４）パラメータ.商品区分 != nullの場合、<BR>
     * 　@　@商品コード、摘要コードを以下に従い検索条件に追加する。<BR>
     * <BR>
     * 　@　@パラメータ.商品区分が、<BR>
     * 　@　@[”A:全商品”の場合]<BR>
     * 　@　@　@商品コード、摘要コードを検索条件に追加しない。<BR>
     * <BR>
     * 　@　@[”B:現物・信用”の場合]<BR>
     * 　@　@　@検索条件文字列 += "and commodity_code in (?, ?) "<BR>
     * <BR>
     * 　@　@[”C:現物”または”D:信用”または”K:外国株式”の場合]<BR>
     * 　@　@　@検索条件文字列 += "and commodity_code = ? "<BR>
     * <BR>
     * 　@　@[”E:先物・オプション”の場合]<BR>
     * 　@　@　@検索条件文字列 += "and commodity_code IN (?, ?, ?, ?, ?, ?, ?, ?) "<BR>
     * <BR>
     * 　@　@[”F:先物”の場合]<BR>
     * 　@　@　@検索条件文字列 += "and commodity_code IN (?, ?, ?) "<BR>
     * <BR>
     * 　@　@[”G:オプション”の場合]<BR>
     * 　@　@　@検索条件文字列 += "and commodity_code IN (?, ?, ?, ?, ?) "<BR>
     * <BR>
     * 　@　@[”H:投信・累投”の場合]<BR>
     * 　@　@　@検索条件文字列 += "and (commodity_code IN (?, ?, ?, ?) "<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@+ "or (commodity_code =? and remark_code in (?, ?) 
     *　@　@　@　@　@　@　@　@　@　@　@　@+ "and trade_code= ?)) " <BR>
     * <BR>
     * 　@　@[”I:入出金”の場合]<BR>
     * 　@　@　@検索条件文字列 += "and commodity_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and remark_code not in (?, ?, ?, ?, ?, ?) "<BR>
     * <BR>
     * 　@　@[”J:譲渡益税”の場合]<BR>
     * 　@　@　@検索条件文字列 += "and commodity_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and remark_code in (?, ?, ?, ?, ?, ?) "<BR>
     * <BR>
     * 　@　@[”L:債券”の場合] <BR>
     *　@　@　@検索条件文字列 += "and commodity_code IN (?, ?) " <BR>
     * <BR>
     * ５）作成した検索条件文字列を返却する。<BR>
     * @@param l_datListStartDate - (表示期間From)<BR>
     * 表示期間From<BR>
     * (YYYYMMDD)<BR>
     * @@param l_datListEndDate - (表示期間To)<BR>
     * 表示期間To<BR>
     * (YYYYMMDD)<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strProductType - (商品区分)<BR>
     * A：　@全商品<BR>
     * B：　@現物・信用<BR>
     * C：　@現物<BR>
     * D：　@信用<BR>
     * E：　@先物・オプション<BR>
     * F：　@先物<BR>
     * G：　@オプション<BR>
     * H：　@投信・累投<BR>
     * I：　@入出金<BR>
     * J：　@譲渡益税<BR>
     * K：　@外国株式<BR>
     * L：　@債券<BR>
     * @@return String
     * @@roseuid 413C30C30143
     */
    protected String createQueryString(Date l_datListStartDate, Date l_datListEndDate, String l_strProductCode, String l_strProductType) 
    {
        final String STR_METHOD_NAME = " createQueryString(Date l_datListStartDate, Date l_datListEndDate, String l_strProductCode, String l_strProductType)"; 
        
        log.entering(STR_METHOD_NAME);  
        
        StringBuffer l_strQueryString = new StringBuffer();
        //
        // １）以下の顧客情報を検索条件文字列にセットする。
        // 　@・証券会社コード
        // 　@・部店コード
        // 　@・顧客コード
        //
        l_strQueryString.append("institution_code = ? and branch_code = ? and account_code = ? ");
            
        //
        // ２）パラメータ.表示期間From != null かつ パラメータ.表示期間To != 
        //     nullの場合、<BR>
        // 　@ 表示期間Fromと表示期間Toを検索条件に追加する。
        //
        if ((l_datListStartDate != null) && (l_datListEndDate != null))
        {
            l_strQueryString.append("and delivery_date >= to_date(?, 'YYYYMMDD') "
                + "and delivery_date <= to_date(?, 'YYYYMMDD') ");
        }

        //
        // ３）パラメータ.銘柄コード != nullの場合、
        // 　@　@銘柄コードを検索条件に追加する。
        //
        if (l_strProductCode != null)
        {
            l_strQueryString.append("and product_code = ? ");  	
        }
          
        //
        // ４）パラメータ.商品区分 != nullの場合、
        // 　@　@商品コード、摘要コードを以下に従い検索条件に追加する。
        //
        if (l_strProductType != null)
        {
            //
            // 　@　@パラメータ.商品区分が、
            //     [”B:現物・信用”の場合]
            //
            if(WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(l_strProductType))
            {
                l_strQueryString.append("and commodity_code in (?, ?) ");    	
            } 
            //     [”C:現物”または”D:信用”または”K:外国株式”の場合]<BR>
            else if((WEB3TradeHistoryProductDivDef.EQUITY.equals(l_strProductType)) || (WEB3TradeHistoryProductDivDef.MARGIN.equals(l_strProductType)) || (WEB3TradeHistoryProductDivDef.FOREIGN.equals(l_strProductType))) 
            {
                 l_strQueryString.append("and commodity_code = ? ");             
            } 
            //     [”E:先物・オプション”の場合]
            else if(WEB3TradeHistoryProductDivDef.FUTURES_OPTION.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code IN (?, ?, ?, ?, ?, ?, ?, ?) ");    	
            }                          
            //     [”F:先物”の場合]
            else if(WEB3TradeHistoryProductDivDef.FUTURES.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code IN (?, ?, ?) ");    	
            }                        
            //     [”G:オプション”の場合]
            else if(WEB3TradeHistoryProductDivDef.OPTION.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code IN (?, ?, ?, ?, ?) ");    	
            }  
            //    [”H:投信・累投”の場合]
            else if(WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(l_strProductType))
            {
                 l_strQueryString.append("and (commodity_code IN (?, ?, ?, ?) ");
                 l_strQueryString.append("or (commodity_code =? and remark_code in (?, ?) and trade_code= ?)) ");
            }                                 
            //     [”I:入出金”の場合]
            else if(WEB3TradeHistoryProductDivDef.AIO.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code = ? "+
                    "and remark_code not in (?, ?, ?, ?, ?, ?) ");    	
            }                                    
            //     [”J:譲渡益税”の場合]
            else if(WEB3TradeHistoryProductDivDef.CPITAL_GAIN_TAX.equals(l_strProductType))
            {
                 l_strQueryString.append("and commodity_code = ? "+
                    "and remark_code in (?, ?, ?, ?, ?, ?) ");    	
            }          
            //     [”L:債券”の場合]
            else if (WEB3TradeHistoryProductDivDef.BOND.equals(l_strProductType))
            {
                l_strQueryString.append("and commodity_code IN (?, ?) " );
            }
        }
        
        log.exiting(STR_METHOD_NAME);  
              
        // ５）作成した検索条件文字列を返却する。
        String l_strQueryStringReturn = l_strQueryString.toString();
        return l_strQueryStringReturn;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件文字列の"?"部分にセットするパラメータリスト(文字列配列)を作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）以下の順番で顧客情報をArrayListに追加する。<BR>
     * 　@　@※以降、ArrayListに追加する際には、String型に変換してから追加すること。<BR>
     * 　@・証券会社コード<BR>
     * 　@　@パラメータ.顧客.証券会社コード<BR>
     * 　@・部店コード<BR>
     * 　@　@パラメータ.顧客.部店コード<BR>
     * 　@・顧客コード<BR>
     * 　@　@パラメータ.顧客.getAccountCode()<BR>
     * 　@　@※先頭6byteのみセットする。<BR>
     * <BR>
     * ３）パラメータ.表示期間From != null かつ パラメータ.表示期間To != nullの場合、<BR>
     * <BR>
     * 　@　@以下の順序で表示期間をArrayListに追加する。<BR>
     * 　@・パラメータ.表示期間From<BR>
     * 　@・パラメータ.表示期間To<BR>
     * <BR>
     * ４）パラメータ.銘柄コード != nullの場合、<BR>
     * 　@　@パラメータ.銘柄コードをArrayListに追加する。<BR>
     * <BR>
     * ５）パラメータ.商品区分 != nullの場合、<BR>
     * 　@　@商品コード、摘要コードを以下に従い、<BR>
     * 　@　@上から順にArrayListに追加する。<BR>
     * <BR>
     * 　@　@[”A:全商品”の場合]<BR>
     * 　@　@　@商品コード、摘要コードをArrayListに追加しない。<BR>
     * <BR>
     * 　@　@[”B:現物・信用”の場合]<BR>
     * 　@　@　@・"10:現物"<BR>
     * 　@　@　@・"11:信用"<BR>
     * <BR>
     * 　@　@[”C:現物”の場合]<BR>
     * 　@　@　@・"10:現物"<BR>
     * <BR>
     * 　@　@[”D:信用”の場合]<BR>
     * 　@　@　@・"11:信用"<BR>
     * <BR>
     * 　@　@[”E:先物・オプション”の場合]<BR>
     * 　@　@　@・"50:株式先物"<BR>
     * 　@　@　@・"51:株式オプション"<BR>
     * 　@　@　@・"52:債券先物"<BR>
     * 　@　@　@・"53:債券先物オプション"<BR>
     * 　@　@　@・"54:店頭オプション"<BR>
     * 　@　@　@・"55:海外先物"<BR>
     * 　@　@　@・"56:海外先物オプション"<BR>
     * 　@　@　@・"57:株券オプション"<BR>
     * <BR>
     * 　@　@[”F:先物”の場合]<BR>
     * 　@　@　@・"50:株式先物"<BR>
     * 　@　@　@・"52:債券先物"<BR>
     * 　@　@　@・"55:海外先物"<BR>
     * <BR>
     * 　@　@[”G:オプション”の場合]<BR>
     * 　@　@　@・"51:株式オプション"<BR>
     * 　@　@　@・"53:債券先物オプション"<BR>
     * 　@　@　@・"54:店頭オプション"<BR>
     * 　@　@　@・"56:海外先物オプション"<BR>
     * 　@　@　@・"57:株券オプション"<BR>
     * <BR>
     * 　@　@[”H:投信・累投”の場合]<BR>
     * 　@　@　@・"20:国内投信"<BR>
     * 　@　@　@・"21:外国投信"<BR>
     * 　@　@　@・"22:GP"<BR>
     * 　@　@　@・"23:MRF"<BR>
     * 　@　@　@・"00:MMF"<BR>
     * 　@　@　@・"D102:売付"<BR>
     * 　@　@　@・"D108:買付"<BR>
     * 　@　@　@・"A3:振替"<BR>
     * <BR>
     * 　@　@[”I:入出金” または ”J:譲渡益税”の場合]<BR>
     * 　@　@　@・"99:金銭"<BR>
     * 　@　@　@・"1079:金銭((特定)譲渡益税還付金)"<BR>
     * 　@　@　@・"1080:金銭((特定)譲渡益税徴収)"<BR>
     * 　@　@　@・"1082:金銭(特定譲渡益税徴収 国税)"<BR>
     * 　@　@　@・"1083:金銭(特定譲渡益税徴収 地方税)"<BR>
     * 　@　@　@・"1084:金銭(特定譲渡益税還付 国税)"<BR>
     * 　@　@　@・"1085:金銭(特定譲渡益税還付 地方税)"<BR>
     * <BR>
     * 　@　@[”K:外国株式”の場合]<BR>
     * 　@　@　@・"40:外国株式"<BR>
     * <BR>
     * 　@　@[”L:債券”の場合] <BR>
     *　@　@　@・"30:国内債券" <BR>
     *　@　@　@・"60:外国債券" <BR>
     * <BR>
     * ６）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_datListStartDate - (表示期間From)<BR>
     * 表示期間From<BR>
     * (YYYYMMDD)<BR>
     * @@param l_datListEndDate - (表示期間To)<BR>
     * 表示期間To<BR>
     * (YYYYMMDD)<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strProductType - (商品区分)<BR>
     * A：　@全商品<BR>
     * B：　@現物・信用<BR>
     * C：　@現物<BR>
     * D：　@信用<BR>
     * E：　@先物・オプション<BR>
     * F：　@先物<BR>
     * G：　@オプション<BR>
     * H：　@投信・累投<BR>
     * I：　@入出金<BR>
     * J：　@譲渡益税<BR>
     * K：　@外国株式<BR>
     * L：　@債券 <BR>
     * @@return java.lang.String[]
     * @@roseuid 413C30D3025B
     */
    protected String[] createQueryDataContainer(WEB3GentradeMainAccount l_mainAccount, Date l_datListStartDate, Date l_datListEndDate, String l_strProductCode, String l_strProductType) 
    {
        final String STR_METHOD_NAME = "createQueryDataContainer (WEB3GentradeMainAccount l_mainAccount, Date l_datListStartDate, Date l_datListEndDate, String l_strProductCode, String l_strProductType) "; 
        
        log.entering(STR_METHOD_NAME);  
       
        //* １）ArrayListを生成する。<BR>
        List l_lisContainers = new ArrayList();     
        
        //
        // ２）以下の順番で顧客情報をArrayListに追加する。<BR>
        // 　@　@※以降、ArrayListに追加する際には、String型に変換してから追加すること。<BR>
        // 　@・証券会社コード<BR>
        // 　@　@パラメータ.顧客.証券会社コード<BR>
        // 　@・部店コード<BR>
        // 　@　@パラメータ.顧客.部店コード<BR>
        // 　@・顧客コード<BR>
        // 　@　@パラメータ.顧客.getAccountCode()<BR>
        // <BR>
        l_lisContainers.add(l_mainAccount.getInstitution().getInstitutionCode()) ;
        l_lisContainers.add(l_mainAccount.getBranch().getBranchCode());
        l_lisContainers.add(l_mainAccount.getAccountCode().substring(0, 6));
               
        //
        // ３）パラメータ.表示期間From != null かつ パラメータ.表示期間To != 
        // nullの場合、<BR>
        // 　@　@以下の順序で表示期間をArrayListに追加する。<BR>
        // 　@・パラメータ.表示期間From<BR>
        // 　@・パラメータ.表示期間To<BR>
        // <BR>
        if ((l_datListStartDate != null) && (l_datListEndDate != null))
        {
            l_lisContainers.add(WEB3DateUtility.formatDate(l_datListStartDate, "yyyyMMdd"));
            l_lisContainers.add(WEB3DateUtility.formatDate(l_datListEndDate, "yyyyMMdd"));
        } 

        // 
        // ４）パラメータ.銘柄コード != nullの場合、<BR>
        // 　@　@パラメータ.銘柄コードをArrayListに追加する。<BR>
        if (l_strProductCode != null)
        {
            l_lisContainers.add(l_strProductCode);    
        }
              
        // 
        // ５）パラメータ.商品区分 != nullの場合、<BR>
        // 　@　@商品コード、摘要コードを以下に従い、<BR>
        // 　@　@上から順にArrayListに追加する。<BR>
        if (l_strProductType != null)
        { 
        
            // <BR>
            // 　@　@[”B:現物・信用”の場合]<BR>
            // 　@　@　@・"10:現物"<BR>
            // 　@　@　@・"11:信用"<BR>
            if(WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryEquityMarginDef.EQUITY);
                l_lisContainers.add(WEB3TradeHistoryEquityMarginDef.MARGIN);                                    
            }              
            // <BR>
            // 　@　@[”C:現物”の場合]<BR>
            // 　@　@　@・"10:現物"<BR>
            else if(WEB3TradeHistoryProductDivDef.EQUITY.equals(l_strProductType)) 
            {
                l_lisContainers.add(WEB3TradeHistoryEquityDef.EQUITY);             
            }              
            // <BR>
            // 　@　@[”D:信用”の場合]<BR>
            // 　@　@　@・"11:信用"<BR>
            else if(WEB3TradeHistoryProductDivDef.MARGIN.equals(l_strProductType)) 
            {
                l_lisContainers.add(WEB3TradeHistoryMarginDef.MARGIN);               
            }       
            // <BR>
            // 　@　@[”E:先物・オプション”の場合]<BR>
            // 　@　@　@・"50:株式先物"<BR>
            // 　@　@　@・"51:株式オプション"<BR>
            // 　@　@　@・"52:債券先物"<BR>
            // 　@　@　@・"53:債券先物オプション"<BR>
            // 　@　@　@・"54:店頭オプション"<BR>
            // 　@　@　@・"55:海外先物"<BR>
            // 　@　@　@・"56:海外先物オプション"<BR>
            // 　@　@　@・"57:株券オプション"<BR>
            else if(WEB3TradeHistoryProductDivDef.FUTURES_OPTION.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.STOCK_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.STOCK_OPTION);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.BOND_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.BOND_FUTURES_OPTION); 
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.STORE_OPTION);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.FOREIGN_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.FOREIGN_FUTURES_OPTION);
                l_lisContainers.add(WEB3TradeHistoryFuturesOptionDef.STOCK_BOND_OPTION);                                                                     
            }                       
            // <BR>
            // 　@　@[”F:先物”の場合]<BR>
            // 　@　@　@・"50:株式先物"<BR>
            // 　@　@　@・"52:債券先物"<BR>
            // 　@　@　@・"55:海外先物"<BR>
            else if(WEB3TradeHistoryProductDivDef.FUTURES.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryFuturesDef.STOCK_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesDef.BOND_FUTURES);
                l_lisContainers.add(WEB3TradeHistoryFuturesDef.FOREIGN_FUTURES);        
            }                     
            // <BR>
            // 　@　@[”G:オプション”の場合]<BR>
            // 　@　@　@・"51:株式オプション"<BR>
            // 　@　@　@・"53:債券先物オプション"<BR>
            // 　@　@　@・"54:店頭オプション"<BR>
            // 　@　@　@・"56:海外先物オプション"<BR>
            // 　@　@　@・"57:株券オプション"<BR>
            else if(WEB3TradeHistoryProductDivDef.OPTION.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryOptionDef.STOCK_OPTION); 
                l_lisContainers.add(WEB3TradeHistoryOptionDef.BOND_FUTURES_OPTION);
                l_lisContainers.add(WEB3TradeHistoryOptionDef.STORE_OPTION);
                l_lisContainers.add(WEB3TradeHistoryOptionDef.FOREIGN_FUTURES_OPTION);
                l_lisContainers.add(WEB3TradeHistoryOptionDef.STOCK_BOND_OPTION);                                                                     
      
            }              
            // <BR>
            // 　@　@[”H:投信・累投”の場合]<BR>
            // 　@　@　@・"20:国内投信"<BR>
            // 　@　@　@・"21:外国投信"<BR>
            // 　@　@　@・"22:GP"<BR>
            // 　@　@　@・"23:MRF"<BR>
            // 　@　@　@・"00:MMF"<BR>
            // 　@　@　@・"D102:売付"<BR>
            // 　@　@　@・"D108:買付"<BR>
            // 　@　@　@・"A3:振替"<BR>
            else if(WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(l_strProductType))
            {
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_DOMESTIC);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_FOREIGN);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.GP);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.MRF);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.MMF);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.SELL);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.BUY);
                l_lisContainers.add(WEB3TradeHistoryMutualFundRuitoDef.TRANSFER);
            }
            // <BR>
            // 　@　@[”I:入出金” または ”J:譲渡益税”の場合]<BR>
            // 　@　@　@・"99:金銭"<BR>
            // 　@　@　@・"1079:金銭((特定)譲渡益税還付金)"<BR>
            // 　@　@　@・"1080:金銭((特定)譲渡益税徴収)"<BR>
            // 　@　@　@・"1082:金銭(特定譲渡益税徴収 国税)"<BR>
            // 　@　@　@・"1083:金銭(特定譲渡益税徴収 地方税)"<BR>
            // 　@　@　@・"1084:金銭(特定譲渡益税還付 国税)"<BR>
            // 　@　@　@・"1085:金銭(特定譲渡益税還付 地方税)"<BR>
            else if((WEB3TradeHistoryProductDivDef.AIO.equals(l_strProductType)) || (WEB3TradeHistoryProductDivDef.CPITAL_GAIN_TAX.equals(l_strProductType)))
            {
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_RETURN);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_COLLECTION);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_COLLECTION_NATION); 
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_COLLECTION_REGION);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_RETURN_NATION);
                l_lisContainers.add(WEB3TradeHistoryCpitalGainTaxDef.CASH_CPITAL_GAIN_TAX_RETURN_REGION);
                 
            }
			// <BR>
			// 　@　@[”K:外国株式”の場合]<BR>
			// 　@　@　@・"40:外国株式"<BR>
			else if(WEB3TradeHistoryProductDivDef.FOREIGN.equals(l_strProductType)) 
			{
				l_lisContainers.add(WEB3TradeHistoryForeignDef.FOREIGN);               
			}
            //　@　@[”L:債券”の場合] 
            //　@・"30:国内債券" 
            //　@・"60:外国債券" 
            else if (WEB3TradeHistoryProductDivDef.BOND.equals((l_strProductType)))
            {
                l_lisContainers.add(WEB3TradeHistoryBondDef.DOMESTIC_BOND);
                l_lisContainers.add(WEB3TradeHistoryBondDef.FOREIGN_BOND);
            }
        } 
        
        //６）生成したArrayList.toArray()の戻り値を返却する。<BR>
        String[] l_strQueryDataContainers = new String[l_lisContainers.size()];
        l_lisContainers.toArray(l_strQueryDataContainers);

        log.exiting(STR_METHOD_NAME);         
        return l_strQueryDataContainers;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件を作成し、返却する。<BR>
     * <BR>
     * １）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@１－１）ソートキー.キー項目とソートキー.昇順／降順の組み合わせ<BR>
     * 　@　@　@　@　@　@をソート条件に追加する。<BR>
     * <BR>
     * 　@　@　@　@　@　@キー項目　@　@　@　@　@　@ソート条件<BR>
     * 　@　@　@　@　@　@---------　@　@　@　@　@----------<BR>
     *             ・"受渡日"　@　@→　@　@"delivery_date"<BR>
     * 　@　@　@　@　@　@・"約定日"　@　@→　@　@"exec_date"<BR>
     * <BR>
     * 　@　@　@　@　@　@昇順／降順　@　@　@　@ソート条件<BR>
     * 　@　@　@　@　@　@---------　@　@　@　@　@----------<BR>
     * 　@　@　@　@　@　@・"A:昇順"　@　@→　@　@"asc"<BR>
     * 　@　@　@　@　@　@・"D:降順"　@　@→　@　@"desc"<BR>
     * <BR>
     * ２）商品コード、銘柄コード、受渡金額をソート条件に追加する。<BR>
     * <BR>
     * 　@ソート条件 += "commodity_code asc "<BR>
     * 　@　@　@　@　@　@　@　@　@+ ", product_code asc "<BR>
     * 　@　@　@　@　@　@　@　@　@+ ", net_amount desc "<BR>
     * <BR>
     * ３）作成したソート条件を返却する。<BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * 取引履歴ソートキーオブジェクト<BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String createSortCond(WEB3HistorySortKeyUnit[] l_sortKey) 
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3HistorySortKeyUnit[] l_sortKey) "; 
        
        log.entering(STR_METHOD_NAME); 
        
        StringBuffer l_strSortCond = new StringBuffer();
        
        //
        // １）パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        // 　@　@１－１）ソートキー.キー項目とソートキー.昇順／降順の組み合わせ
        // 　@　@　@　@　@　@をソート条件に追加する。
        int l_intSortKeyLength = l_sortKey.length; 
        for (int i = 0; i < l_intSortKeyLength; i ++)
        {
            if ( WEB3HistoryKeyItemDef.DELIVERY_DATE.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("delivery_date");
            } 
            else if (WEB3HistoryKeyItemDef.EXEC_DATE.equals(l_sortKey[i].keyItem))
            {
                if (l_strSortCond.length() != 0) 
                {
                    l_strSortCond.append(", ");
                }
                l_strSortCond.append("exec_date");        
            }
            else
            {
                continue;
            }
                       
            if(WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
            {
                 l_strSortCond.append( " asc");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKey[i].ascDesc))
            {
                l_strSortCond.append(" desc");
            }   
        }    
        //
        // ２）商品コード、銘柄コード、受渡金額をソート条件に追加する。
        // <BR>
        // 　@ソート条件 += "commodity_code asc "
        // 　@　@　@　@　@　@　@　@　@+ ", product_code asc "
        // 　@　@　@　@　@　@　@　@　@+ ", net_amount desc "
        if (l_strSortCond.length() == 0)
        {
            l_strSortCond.append("commodity_code asc "
                + ", product_code asc "
                + ", net_amount desc ");
        } 
        else
        {
            l_strSortCond.append(", commodity_code asc "
                + ", product_code asc "
                + ", net_amount desc ");        
        
        }  
        //* ３）作成したソート条件を返却する。 
        log.exiting(STR_METHOD_NAME);  
        return l_strSortCond.toString();
    }
    
    /**
     * (get口座残高)<BR>
     * 引数の条件に該当する口座残高(受渡後残高の総和)を返却する。<BR>
     * <BR>
     * １）取引履歴データマネージャ.get顧客勘定残高履歴一覧()をコールする。<BR>
     * <BR>
     * 　@[get顧客勘定残高履歴一覧()にセットするパラメータ]<BR>
     * 　@証券会社コード：　@パラメータ.顧客.証券会社コード<BR>
     * 　@部店コード：　@パラメータ.顧客.部店コード<BR>
     * 　@口座コード：　@パラメータ.顧客.getAccountCode()<BR>
     * 　@受渡日：　@パラメータ.受渡日<BR>
     * <BR>
     * 　@nullが返却された場合は、nullを返却し、終了する。<BR>
     * <BR>
     * ２）１）のメソッドの戻り値(=List)に格納されている顧客勘定残高履歴Params<BR>
     * 　@の「受残」項目の値を総和する。<BR>
     * <BR>
     * ３）総和した値を文字列変換して返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 413C308E02DA
     */
    protected String getAccountBalance(WEB3GentradeMainAccount l_mainAccount, Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccountBalance(WEB3GentradeMainAccount l_mainAccount, Date l_datDeliveryDate)"; 
        
        log.entering(STR_METHOD_NAME);   
              
        // １）取引履歴データマネージャ.get顧客勘定残高履歴一覧()をコールする。<BR>        
        List l_lisRecords = WEB3HistoryTradeHistoryDataManager.getTransactionHistoryList(l_mainAccount.getInstitution().getInstitutionCode(), 
            l_mainAccount.getBranch().getBranchCode(), 
            l_mainAccount.getAccountCode().substring(0, 6), 
            l_datDeliveryDate);
        
        //nullが返却された場合は、nullを返却し、終了する。
        if (l_lisRecords == null)
        {
            return null;
        }
         
        // ２）１）のメソッドの戻り値(=List)に格納されている顧客勘定残高履歴Params<BR>
        // 　@の「受残」項目の値を総和する。<BR>
        double l_dblTotalConfirmedBalance = 0;
        
        if (l_lisRecords != null)
        {
            int l_intSize = l_lisRecords.size();       
            for (int i = 0;i < l_intSize;i++)
            {
                l_dblTotalConfirmedBalance += ((TransactionHistoryParams)l_lisRecords.get(i)).getConfirmedBalance(); 
            }
        }
        // ３）総和した値を文字列変換して返却する。<BR>
        String l_strTotalConfirmedBalance = WEB3StringTypeUtility.formatNumber(l_dblTotalConfirmedBalance);

        log.exiting(STR_METHOD_NAME); 
        return l_strTotalConfirmedBalance;
    }
    
    /**
     * (create取引履歴情報)<BR>
     * 引数の取引履歴Paramsより取引履歴情報を作成し、返却する。<BR>
     * <BR>
     * １）取引履歴情報インスタンスを生成する。<BR>
     * <BR>
     * ２）投信タイプを保持する為の変数wk投信タイプ（String型　@初期値：null）を作成する。<BR>
     * <BR>
     * ３）１）にて生成したインスタンスに以下のプロパティをセットする。<BR>
     *   取引履歴ID = パラメータ.取引履歴Params.取引履歴ID<BR>
     * 　@約定日 = パラメータ.取引履歴Params.約定日<BR>
     * 　@商品コード = パラメータ.取引履歴Params.商品コード<BR>
     * 　@弁済区分 = パラメータ.取引履歴Params.弁済区分<BR>
     * 　@銘柄コード = パラメータ.取引履歴Params.銘柄コード<BR>
     * <BR>
     * 　@[投信の場合のみ、銘柄名取得先を判断する]<BR>
     * 　@投信（商品コード = "20" または "21")の場合、以下の処理を行う。<BR>
     * 　@ （get部店プリファ@レンス（顧客.getBranch()） != 1)の場合、<BR>
     * 　@　@　@　@銘柄名 = パラメータ.取引履歴Params.銘柄摘要名<BR>
     * 　@　@その他の場合、<BR>
     * 　@　@　@　@get投信銘柄マスター（顧客.getBranch().getInstitutionCode(),パラメータ.取引履歴Params.銘柄コード）を行う。<BR>
     *         銘柄名 = 投信銘柄マスター.投信銘柄名 <BR>
     * 　@　@　@　@wk投信タイプ = 投信銘柄マスター.投信タイプ <BR>　@　@
     * 　@　@　@ （投信銘柄マスター = null）の場合、銘柄名 = パラメータ.取引履歴Params.銘柄・摘要名<BR>
     * 　@投信以外の場合、銘柄名 = パラメータ.取引履歴Params.銘柄・摘要名<BR>
     * <BR>
     * 　@口座区分 = パラメータ.取引履歴Params.口座区分<BR>
     * 　@翻訳摘要名 =wk投信タイプ=0 && 取引履歴Params.出入区分 = 2(入) && 取引履歴Params.取引コード=11の場合、"買取"をセットする。<BR>
     * 　@　@　@　@　@　@　@上記以外の場合、パラメータ.取引履歴Params.翻訳摘要名をセットする。<BR> 　@　@
     * 　@数量 = パラメータ.取引履歴Params.売買数量<BR>
     * 　@数量単位 = パラメータ.取引履歴Params.数量単位<BR>
     * 　@単価 = パラメータ.取引履歴Params.約定単価<BR>
     *   売買区分 = パラメータ.取引履歴Params.売買区分<BR>
     * 　@受渡金額 = パラメータ.取引履歴Params.受渡金額<BR>
     * 　@譲渡損益 = パラメータ.取引履歴Params.譲渡損益<BR>
     *             ※nullの場合、nullをセット。<BR>
     * 　@明細管理番号 = パラメータ.取引履歴Params.明細管理番号<BR>
     * 　@出入区分 = パラメータ.取引履歴Params.出入区分<BR>                                                                                                                        
     * 　@取引コード = パラメータ.取引履歴Params.取引コード<BR>                                                                                                                      
     * 　@摘要コード = パラメータ.取引履歴Params.摘要コード<BR>
     * 　@通貨単位 = パラメータ.取引履歴Params.通貨単位<BR>     
     * 　@決済区分 = パラメータ.取引履歴Params.決済区分<BR>                                                                                                                   
     * <BR>
     * ４）this.is簿価単価明細リンク()をコールし、結果を<BR>
     * 　@生成した取引履歴情報.簿価単価明細リンクフラグにセットする。<BR>
     * <BR>
     * 　@[is簿価単価明細リンク()にセットするパラメータ]<BR>
     * 　@取引履歴Params：　@パラメータ.取引履歴Params<BR>
     * <BR>
     * ５）プロパティセットした取引履歴情報インスタンスを返却する。<BR>
     * @@param l_tradeHistoryParams - (取引履歴Params)<BR>
     * 取引履歴Params<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryTradeHistoryUnit
     * @@throws WEB3BaseException
     * @@roseuid 413D225A0350
     */
    protected WEB3HistoryTradeHistoryUnit createTradeHistoryUnit(TradeHistoryParams l_tradeHistoryParams)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createTradeHistoryUnit(TradeHistoryParams l_tradeHistoryParams)";
        log.entering(STR_METHOD_NAME); 

        // １）取引履歴情報インスタンスを生成する。
        WEB3HistoryTradeHistoryUnit l_web3HistoryTradeHistoryUnit = new WEB3HistoryTradeHistoryUnit();
        
        //２）投信タイプを保持する為の変数wk投信タイプ（String型　@初期値：null）を作成する。
        String l_strWkMutualfundType = null;
        
        // ３）１）にて生成したインスタンスに以下のプロパティをセットする。<BR>
        //   取引履歴ID = パラメータ.取引履歴Params.取引履歴ID
        l_web3HistoryTradeHistoryUnit.tradeHistoryId = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getTradeHistoryId());
        
        // 　@約定日 = パラメータ.取引履歴Params.約定日<BR>
        l_web3HistoryTradeHistoryUnit.execDate = l_tradeHistoryParams.getExecDate();
        
        // 　@商品コード = パラメータ.取引履歴Params.商品コード
        l_web3HistoryTradeHistoryUnit.commodityCode = l_tradeHistoryParams.getCommodityCode();
        
        // 　@弁済区分 = パラメータ.取引履歴Params.弁済区分
        l_web3HistoryTradeHistoryUnit.repaymentDiv = l_tradeHistoryParams.getRepaymentType();
        
        // 　@銘柄コード = パラメータ.取引履歴Params.銘柄コード
        l_web3HistoryTradeHistoryUnit.productCode = l_tradeHistoryParams.getProductCode();
        
		// [投信の場合のみ、銘柄名取得先を判断する]
		//    投信（商品コード = "20" または "21")の場合、以下の処理を行う。
		//      （get部店プリファ@レンス（顧客.getBranch()） != 1)の場合、
		//　@　@       銘柄名 = パラメータ.取引履歴Params.銘柄摘要名
		//       その他の場合、
		//           get投信銘柄マスター（顧客.getBranch().getInstitutionCode(),パラメータ.取引履歴Params.銘柄コード）を行う。 
        //           銘柄名 = 投信銘柄マスター.投信銘柄名 
        //		     wk投信タイプ = 投信銘柄マスター.投信タイプ				
		//　@        （銘柄名 = null）の場合、銘柄名 = パラメータ.取引履歴Params.銘柄・摘要名
		//    投信以外の場合、銘柄名 = パラメータ.取引履歴Params.銘柄・摘要名
		String l_strProductName = null;
		Integer l_preferences = this.getBranchPreferences(this.getMainAccount().getBranch().getBranchId());

		if (WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_DOMESTIC.equals(l_tradeHistoryParams.getCommodityCode())
			|| WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_FOREIGN.equals(l_tradeHistoryParams.getCommodityCode()))
		{
			if (l_preferences != null 
				&& l_preferences.intValue() == 1)
			{
				Object l_objMutualFundProduct = 
					this.getFundProductMaster(
						this.getMainAccount().getBranch().getInstitution().getInstitutionCode(),
						l_tradeHistoryParams.getProductCode());
				
				if (l_objMutualFundProduct != null)
				{
					MutualFundProductRow l_fundProductRow = (MutualFundProductRow)l_objMutualFundProduct;
					MutualFundProductParams l_mutualFundProductParams = new MutualFundProductParams(l_fundProductRow);
					
					//銘柄名 = 投信銘柄マスター.投信銘柄名
					l_strProductName = l_mutualFundProductParams.getStandardName();

					//wk投信タイプ = 投信銘柄マスター.投信タイプ
					l_strWkMutualfundType = l_mutualFundProductParams.getFundType().intValue() + "";
				}
				
				if (l_strProductName == null)
				{
					l_strProductName = l_tradeHistoryParams.getProductName();
				}
			}
			else
			{
				l_strProductName = l_tradeHistoryParams.getProductName();
			}
		}
		else
		{
			l_strProductName = l_tradeHistoryParams.getProductName();
		}
		l_web3HistoryTradeHistoryUnit.productName = l_strProductName;
        
        // 　@口座区分 = パラメータ.取引履歴Params.口座区分
        l_web3HistoryTradeHistoryUnit.taxType = l_tradeHistoryParams.getAccountDiv();
        
        // 　@翻訳摘要名 = wk投信タイプ=0 && 取引履歴Params.取引コード = 11の場合、"買取"をセットする。
        //wk投信タイプ0以外の場合、パラメータ.取引履歴Params.翻訳摘要名をセットする。 

        //2006/05/01 SCS鈴木修正　@START
//        if ("0".equals(l_strWkMutualfundType))
        if ("0".equals(l_strWkMutualfundType) &&
            "2".equals(l_tradeHistoryParams.getIoDiv()) &&
            WEB3TradeHistoryTradeCodeDef.TRADE_CODE_11.equals(l_tradeHistoryParams.getTradeCode()))
        //2006/05/01 SCS鈴木修正　@END

        {
            l_web3HistoryTradeHistoryUnit.remarkName = "買取";
        }
        else
        {
            l_web3HistoryTradeHistoryUnit.remarkName = l_tradeHistoryParams.getRemarkName();
        }
        
        // 　@数量 = パラメータ.取引履歴Params.売買数量
        l_web3HistoryTradeHistoryUnit.quantity = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getQuantity());
        
        // 　@数量単位 = パラメータ.取引履歴Params.数量単位
        l_web3HistoryTradeHistoryUnit.quantityUnit = l_tradeHistoryParams.getQuantityType();
        
        // 　@単価 = パラメータ.取引履歴Params.約定単価
        l_web3HistoryTradeHistoryUnit.price = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getPrice());
        
        //   売買区分 = パラメータ.取引履歴Params.売買区分
        l_web3HistoryTradeHistoryUnit.historyDealingType = l_tradeHistoryParams.getBuySellDiv();
        
        // 　@受渡金額 = パラメータ.取引履歴Params.受渡金額
        l_web3HistoryTradeHistoryUnit.deliveryAmount = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getNetAmount());
        
        // 　@譲渡損益 = パラメータ.取引履歴Params.譲渡損益
        if (l_tradeHistoryParams.getCapitalGainIsNull())
        {
            l_web3HistoryTradeHistoryUnit.capitalProfitLoss = null;
        }
        else
        {
            l_web3HistoryTradeHistoryUnit.capitalProfitLoss = WEB3StringTypeUtility.formatNumber(l_tradeHistoryParams.getCapitalGain());
        }
        
        // 　@明細管理番号 = パラメータ.取引履歴Params.明細管理番号
        if (l_tradeHistoryParams.getDetailsManagementNoIsNull() == true)                                                                                                                        
        {                                                                                                                       
            l_web3HistoryTradeHistoryUnit.detailsManageNo = null;                                                                                                                   
        }                                                                                                                       
        else                                                                                                                        
        {                                                                                                                       
            l_web3HistoryTradeHistoryUnit.detailsManageNo = "" + l_tradeHistoryParams.getDetailsManagementNo();                                                                                                                 
        }
                
        // 　@出入区分 = パラメータ.取引履歴Params.出入区分    
        l_web3HistoryTradeHistoryUnit.ioDiv = l_tradeHistoryParams.getIoDiv();
                                                                                                                            
        //   取引コード = パラメータ.取引履歴Params.取引コード
        l_web3HistoryTradeHistoryUnit.tradeCode = l_tradeHistoryParams.getTradeCode();
                                                                                                                                
        //   摘要コード = パラメータ.取引履歴Params.摘要コード     
        l_web3HistoryTradeHistoryUnit.remarkCode = l_tradeHistoryParams.getRemarkCode();
        
        //  通貨単位 = パラメータ.取引履歴Params.通貨単位
        l_web3HistoryTradeHistoryUnit.monetaryUnit = l_tradeHistoryParams.getMonetaryUnit();
        
        //  決済区分 = パラメータ.取引履歴Params.決済区分 
        l_web3HistoryTradeHistoryUnit.settleDiv = l_tradeHistoryParams.getPaymentDiv();
        
        // ４）this.is簿価単価明細リンク()をコールし、結果を<BR>
        // 　@生成した取引履歴情報.簿価単価明細リンクフラグにセットする。
        l_web3HistoryTradeHistoryUnit.bookValueLink = this.isBookValueLink(l_tradeHistoryParams);
        
        //     ５）プロパティセットした取引履歴情報インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);       
        return l_web3HistoryTradeHistoryUnit;
    }

    /**
     * (create受渡日別残高情報)<BR>
     * 引数の受渡日に該当する受渡日別残高情報を作成し、返却する。<BR>
     * <BR>
     * １）受渡日別残高情報インスタンスを生成する。<BR>
     * <BR>
     * ２）this.get口座残高()をコールする。<BR>
     * <BR>
     * 　@[get口座残高()にセットするパラメータ]<BR>
     * 　@顧客：　@パラメータ.顧客<BR>
     * 　@受渡日：　@パラメータ.受渡日<BR>
     * <BR>
     * ３）受渡日別残高情報インスタンスにプロパティをセットする。<BR>
     *   顧客勘定残高履歴ID = 顧客勘定残高履歴Row(*1).顧客勘定残高履歴ID<BR>
     * 　@受渡日 = パラメータ.受渡日<BR>
     * 　@口座残高 = ２）にて取得した口座残高<BR>
     * 　@損益明細リンクフラグ = false<BR>
     * <BR>
     * 4）プロパティセットした受渡日別残高情報インスタンスを返却する。<BR>
     * <BR>
     * (*1)顧客勘定残高履歴Dao.findRowBy...()メソッドにて取得する。<BR>
     * 　@　@※メソッド名が長い為、省略。<BR>
     *　@  [findRowBy...()にセットするパラメータ]<BR>
     *　@　@    証券会社コード：　@パラメータ.顧客.証券会社コード<BR>
     *　@　@    部店コード：　@パラメータ.顧客.部店コード<BR>
     *　@　@    顧客コード：　@パラメータ.顧客.口座コード<BR>
     *　@　@    受渡日：　@パラメータ.受渡日<BR>
     *　@　@    預り区分：　@"預り金"<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_tradeHistoryUnits - (取引履歴情報一覧)<BR>
     * 取引履歴情報オブジェクトの配列<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryDailyBalanceUnit
     * @@throws WEB3BaseException
     * @@roseuid 413D362300B2
     */
    protected WEB3HistoryDailyBalanceUnit createDailyBalanceUnit(WEB3GentradeMainAccount l_mainAccount, Date l_datDeliveryDate, WEB3HistoryTradeHistoryUnit[] l_tradeHistoryUnits) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createDailyBalanceUnit(WEB3GentradeMainAccount l_mainAccount, Date l_deliveryDate, WEB3HistoryTradeHistoryUnit[] l_tradeHistoryUnits)"; 
        log.entering(STR_METHOD_NAME); 
        
        // １）受渡日別残高情報インスタンスを生成する。
        WEB3HistoryDailyBalanceUnit l_web3HistoryDailyBalanceUnit = new WEB3HistoryDailyBalanceUnit();
        
        //(*1)顧客勘定残高履歴Dao.findRowBy...()メソッドにて取得する。
        //  ※メソッド名が長い為、省略。
        //  [findRowBy...()にセットするパラメータ]
        //    証券会社コード：　@パラメータ.顧客.証券会社コード
        //    部店コード：　@パラメータ.顧客.部店コード
        //    顧客コード：　@パラメータ.顧客.口座コード
        //    受渡日：　@パラメータ.受渡日
        //    預り区分：　@"預り金"
        TransactionHistoryRow l_transactionHistoryRow = null;
        try
        {
            l_transactionHistoryRow = 
                TransactionHistoryDao.findRowByInstitutionCodeBranchCodeAccountCodeDeliveryDateDepositMarginDiv(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_mainAccount.getAccountCode().substring(0, 6),
                    new Timestamp(l_datDeliveryDate.getTime()),
                    WEB3DepositMarginDivDef.FROM_DEPOSIT);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // ２）this.get口座残高()をコールする。
        String l_strAccountBalance = this.getAccountBalance(l_mainAccount, l_datDeliveryDate);
                
        // ４）受渡日別残高情報インスタンスにプロパティをセットする。
        //   顧客勘定残高履歴ID = 顧客勘定残高履歴Row(*1).顧客勘定残高履歴ID
        // 　@受渡日 = パラメータ.受渡日
        // 　@口座残高 = ２）にて取得した口座残高
        // 　@損益明細リンクフラグ = false
        if (l_transactionHistoryRow != null)
        {
            l_web3HistoryDailyBalanceUnit.transactionHistoryId = 
                WEB3StringTypeUtility.formatNumber(
                    l_transactionHistoryRow.getTransactionHistoryId());
        }
        else
        {
            l_web3HistoryDailyBalanceUnit.transactionHistoryId = null;
        }

        l_web3HistoryDailyBalanceUnit.deliveryDate = WEB3DateUtility.toDay(l_datDeliveryDate);
        l_web3HistoryDailyBalanceUnit.accountBalance = l_strAccountBalance;
        l_web3HistoryDailyBalanceUnit.profitLossLink = false;
        
        // ５）プロパティセットした受渡日別残高情報インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);   
        return l_web3HistoryDailyBalanceUnit;
    }
    
    /**
     * (is簿価単価明細リンク)<BR>
     * 簿価単価明細リンクを行うか判別する。<BR>
     * <BR>
     * １）以下の条件(①@～⑤)を全て満たす場合はtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@①@前月初営業日 <= パラメータ.取引履歴Params.受渡日<BR>
     * 　@②パラメータ.取引履歴Params.約定日 <=　@前日営業日<BR>
     * 　@③パラメータ.取引履歴Params.口座区分 == ”1：特定”<BR>
     * 　@④パラメータ.取引履歴Params.商品コードが以下のいづれかに該当。<BR>
     * 　@　@・”10：国内株式”<BR>
     * 　@　@・”40：外国株式”<BR>
     * 　@　@・”11：信用” かつ 取引コードが”53：現引・現渡”<BR>
     * 　@　@・”20：国内投信”<BR>
     * 　@　@・”21：外国投信”<BR>
     * 　@⑤パラメータ.取引履歴Params.出入区分 == ”1：出”<BR>
     * @@param l_tradeHistoryParams - (取引履歴Params)<BR>
     * 取引履歴Params<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 413D628B0007
     */
    protected boolean isBookValueLink(TradeHistoryParams l_tradeHistoryParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isBookValueLink(TradeHistoryParams l_tradeHistoryParams)"; 
        log.entering(STR_METHOD_NAME);  
        
        boolean l_blBookValueLink;
        
        //前月初営業日 l_gentradeBizDate.roll(0)
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(GtlUtils.getSystemTimestamp());
        
        l_calendar.add(Calendar.MONTH, -1);
        
        l_calendar.set(Calendar.DATE,l_calendar.getActualMinimum(Calendar.DATE));
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObj = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

       SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
       //証券会社： （get補助口座()の戻り値）.getInstitutuin()の戻り値
       WEB3GentradeInstitution l_institution = 
	       (WEB3GentradeInstitution) l_subAccount.getInstitution(); 
       String l_strInstitutionCode = l_institution.getInstitutionCode();

       Market l_market = null;
		
       Date l_preBizDate = null;
			   
       //外国株式の場合
       if( WEB3TradeHistoryForeignDef.FOREIGN.equals(l_tradeHistoryParams.getCommodityCode()) )
       {
       	    //銘柄コードNullの場合、falseを返却
       	    if ( l_tradeHistoryParams.getProductCode() == null )
       	    {
				l_blBookValueLink = false;
				return l_blBookValueLink;       	    	
       	    }else {
				//市場コード
				String l_strMarketCode = null;
				
				//外株銘柄            
				WEB3FeqProduct l_feqProduct = null;
				
				WEB3FeqOrderManager l_feqOrderManager = 
					(WEB3FeqOrderManager)l_finApp.getTradingModule(
						ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        		
        		try{
					//validate外株銘柄(証券会社, String)
					l_feqProduct =
						(WEB3FeqProduct) l_feqOrderManager.validateFeqProduct(
							l_institution, 
							l_tradeHistoryParams.getProductCode());
				        
					//get市場( )
					WEB3GentradeMarket l_feq_market = l_feqProduct.getMarket();
					l_strMarketCode = l_feq_market.getMarketCode();
					log.debug("市場コード = " + l_strMarketCode);
                        
					//validate市場(市場)
					l_feqOrderManager.validateMarket(l_feq_market);
				
					WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(
				    	new Timestamp(l_calendar.getTime().getTime()));
				
					//前月初営業日の取得
					while (!WEB3FeqDateUtility.isFeqBizDate(new Timestamp(l_calendar.getTime().getTime())))
					{
						l_calendar.add(Calendar.DATE, 1);
					}
					
					//前日営業日の取得
					l_preBizDate = l_gentradeBizDate.calcFeqBizDate(
						l_strInstitutionCode, l_strMarketCode, GtlUtils.getSystemTimestamp(), -1);
				}
				catch (WEB3BaseException l_ex)
				{
					l_blBookValueLink = false;
					return l_blBookValueLink;
				}
       	    }
        }else
        //国内株の場合
        {
			//前月初営業日の取得
            while (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
                WEB3GentradeTradingTimeManagement.getBizDateType(
                    new Timestamp(l_calendar.getTime().getTime()))))
            {
                l_calendar.add(Calendar.DATE, 1);
            }
            
  	        //前日営業日の取得
	        l_preBizDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(-1);
        }

		WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(
			new Timestamp(l_calendar.getTime().getTime()));
                                                
        if (WEB3DateUtility.compareToDay(l_gentradeBizDate.roll(0), l_tradeHistoryParams.getDeliveryDate()) <= 0&&
            WEB3DateUtility.compareToDay(l_tradeHistoryParams.getExecDate(), l_preBizDate) <= 0&&
            WEB3AccountDivDef.SPECIAL.equals(l_tradeHistoryParams.getAccountDiv()) &&
            (WEB3TradeHistoryProductCodeDef.DOMESTIC_STOCK.equals(l_tradeHistoryParams.getCommodityCode()) ||
            WEB3TradeHistoryProductCodeDef.FOREIGN_STOCK.equals(l_tradeHistoryParams.getCommodityCode()) || 
            (WEB3TradeHistoryProductCodeDef.MARGIN.equals(l_tradeHistoryParams.getCommodityCode()) && 
            WEB3TradeHistoryTradeCodeDef.SWAP.equals(l_tradeHistoryParams.getTradeCode())) ||
            WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_DOMESTIC.equals(l_tradeHistoryParams.getCommodityCode()) ||
            WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_FOREIGN.equals(l_tradeHistoryParams.getCommodityCode())) &&
            WEB3IoDivDef.OUTPUT.equals(l_tradeHistoryParams.getIoDiv()))
        {
            l_blBookValueLink = true;
        }
        else
        {
            l_blBookValueLink = false;        
        }
         
        log.exiting(STR_METHOD_NAME);           	
        return l_blBookValueLink;
    }
    
    /**
     * (is損益明細リンク)<BR>
     * 損益明細リンクを行うか判別する。<BR>
     * <BR>
     * １）以下の条件(①@～③)を全て満たす場合はtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@①@パラメータ.口座残高 != null<BR>
     * 　@②前月初日営業日 <= パラメータ.受渡日<BR>
     * 　@③パラメータ.取引履歴情報一覧の要素数分以下のチェックを行い、<BR>
     * 　@　@全ての条件を満たす取引履歴情報が一つでも存在すること。<BR>
     * 　@　@　@・明細データ.約定日 <= 前日営業日<BR>
     * 　@　@　@・明細データ.口座区分 == "1：特定"<BR>
     * 　@　@　@・(明細データ.商品コード == "11：信用" かつ<BR>
     * 　@　@　@　@ 明細データ.摘要コード == ("A201" or "A210" or "A214"))<BR>
     * 　@　@　@　@または、<BR>
     * 　@　@　@　@(明細データ.商品コード == ("10：現物" or "30：国内債券" or 
     * "40：外国株式") かつ<BR>
     * 　@　@　@　@　@明細データ.出入区分 == "2：入")<BR>
     * 　@　@　@　@または、
     * 　@　@　@　@(明細データ.商品コード == ("20：国内投信" or "21：外国投信") かつ
     *  　@　@　@　@明細データ.取引コード == "92：買取" かつ
     *          明細データ.出入区分 == "2：入") 
     * 　@　@　@　@または、<BR>
     * 　@　@　@　@(明細データ.出入区分 == "2：入" かつ <BR>
     * 　@　@　@　@　@明細データ.取引コード == "A2" かつ<BR>
     * 　@　@　@　@　@明細データ.摘要コード == "1079")<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_strAccountBalance - (口座残高)<BR>
     * 受渡後の口座残高<BR>
     * @@param l_tradeHistoryUnits - (取引履歴情報一覧)<BR>
     * 取引履歴情報オブジェクトの配列<BR>
     * @@return Boolean
     * @@roseuid 413D488A001C
     */
    protected boolean isProfitLossLink(Date l_datDeliveryDate, String l_strAccountBalance, WEB3HistoryTradeHistoryUnit[] l_tradeHistoryUnits) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " isProfitLossLink(Date l_datDeliveryDate, String l_strAccountBalance, WEB3HistoryTradeHistoryUnit[] l_tradeHistoryUnits) "; 
        log.entering(STR_METHOD_NAME);  
        
        boolean l_blProfitLossLink;
    	  
        //前月初営業日 l_gentradeBizDate.roll(0)
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(GtlUtils.getSystemTimestamp());
        l_calendar.add(Calendar.MONTH, -1);
        l_calendar.set(Calendar.DATE,l_calendar.getActualMinimum(Calendar.DATE));

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObj = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
		//証券会社： （get補助口座()の戻り値）.getInstitutuin()の戻り値
		WEB3GentradeInstitution l_institution = 
			(WEB3GentradeInstitution) l_subAccount.getInstitution(); 
		String l_strInstitutionCode = l_institution.getInstitutionCode();              

        //①@パラメータ.口座残高 == null
        if (l_strAccountBalance == null )
        {
	        l_blProfitLossLink = false;
	        return l_blProfitLossLink;
        }        
        	               
        // 　@③パラメータ.取引履歴情報一覧の要素数分以下のチェックを行い、
        // 　@　@全ての条件を満たす取引履歴情報が一つでも存在すること。
        l_blProfitLossLink = false;
        int l_intSize = l_tradeHistoryUnits.length;
        for (int i = 0; i < l_intSize; i ++ )
        {
			Market l_market = null;
		
			Date l_preBizDate = null;
					
			//外国株式の場合
			if( WEB3TradeHistoryForeignDef.FOREIGN.equals(l_tradeHistoryUnits[i].commodityCode) )
        	{
				//銘柄コードNullの場合、falseを返却
				if ( l_tradeHistoryUnits[i].productCode == null )
				{
					l_blProfitLossLink = false;
					return l_blProfitLossLink;
					
				}else{
					//市場コード
					String l_strMarketCode = null;
				
					//外株銘柄            
					WEB3FeqProduct l_feqProduct = null;
				
					WEB3FeqOrderManager l_feqOrderManager = 
						(WEB3FeqOrderManager)l_finApp.getTradingModule(
							ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        		
        			try {
						//validate外株銘柄(証券会社, String)
						l_feqProduct =
							(WEB3FeqProduct) l_feqOrderManager.validateFeqProduct(
								l_institution, 
								l_tradeHistoryUnits[i].productCode);
				        
						//get市場( )
						WEB3GentradeMarket l_feq_market = l_feqProduct.getMarket();
						l_strMarketCode = l_feq_market.getMarketCode();
						log.debug("市場コード = " + l_strMarketCode);
                        
						//validate市場(市場)
						l_feqOrderManager.validateMarket(l_feq_market);
					
						WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(
							new Timestamp(l_calendar.getTime().getTime()));
					
						//前月初営業日の取得
						while (!WEB3FeqDateUtility.isFeqBizDate(new Timestamp(l_calendar.getTime().getTime())))
						{
							l_calendar.add(Calendar.DATE, 1);
						}
					
						//前日営業日の取得
						l_preBizDate = l_gentradeBizDate.calcFeqBizDate(
								l_strInstitutionCode, l_strMarketCode, GtlUtils.getSystemTimestamp(), -1);
					}
					catch (WEB3BaseException l_ex)
					{
						l_blProfitLossLink = false;
						return l_blProfitLossLink;
					}
				}
        	}else
        	//国内株の場合
        	{
				//前月初営業日の取得
                while (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        new Timestamp(l_calendar.getTime().getTime()))))
                {
                    l_calendar.add(Calendar.DATE, 1);
                }				

                //前日営業日の取得
				l_preBizDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp()).roll(-1);
        	}
        	
			WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(
				new Timestamp(l_calendar.getTime().getTime()));
        	
			//②前月初日営業日 > パラメータ.受渡日
			if (WEB3DateUtility.compareToDay(l_gentradeBizDate.roll(0), l_datDeliveryDate) > 0)
			{
				l_blProfitLossLink = false;
				return l_blProfitLossLink;
			}       	
        	
            if(//明細データ.約定日 <= 前日営業日
                (WEB3DateUtility.compareToDay(l_tradeHistoryUnits[i].execDate, l_preBizDate) <= 0)&&
                //明細データ.口座区分 == "1：特定"  
                (WEB3AccountDivDef.SPECIAL.equals(l_tradeHistoryUnits[i].taxType))&&
                // 　@　@　@・(明細データ.商品コード == "11：信用" かつ
                // 　@　@　@　@ 明細データ.摘要コード == ("A201" or "A210" or "A214"))         または、
                // 　@　@　@　@(明細データ.商品コード == ("10：現物" or "30：国内債券" or 
                //         "40：外国株式") かつ
                // 　@　@　@　@　@明細データ.出入区分 == "2：入")                                    　@　@　@　@または、
                //         (明細データ.商品コード == ("20：国内投信" or "21：外国投信") かつ 
                //          明細データ.取引コード == "92：買取" かつ 
                //          明細データ.出入区分 == "2：入") 
                //          または、 
                // 　@　@　@　@(明細データ.出入区分 == "2：入" かつ 
                // 　@　@　@　@　@明細データ.取引コード == "A2" かつ
                // 　@　@　@　@　@明細データ.摘要コード == "1079")             
                ((WEB3TradeHistoryProductCodeDef.MARGIN.equals(l_tradeHistoryUnits[i].commodityCode)&&
                  (WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A201.equals(l_tradeHistoryUnits[i].remarkCode)||
                   WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A210.equals(l_tradeHistoryUnits[i].remarkCode)||
                   WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A214.equals(l_tradeHistoryUnits[i].remarkCode)))||               
                ((WEB3TradeHistoryProductCodeDef.EQUITY.equals(l_tradeHistoryUnits[i].commodityCode)||
                   WEB3TradeHistoryProductCodeDef.DOMESTIC_BOND.equals(l_tradeHistoryUnits[i].commodityCode)||
                   WEB3TradeHistoryProductCodeDef.FOREIGN_STOCK.equals(l_tradeHistoryUnits[i].commodityCode))&&
                   WEB3IoDivDef.INPUT.equals(l_tradeHistoryUnits[i].ioDiv))||
                ((WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_DOMESTIC.equals(l_tradeHistoryUnits[i].commodityCode)||
                   WEB3TradeHistoryMutualFundRuitoDef.MUTUAL_FUND_FOREIGN.equals(l_tradeHistoryUnits[i].commodityCode))&&
                   WEB3IoDivDef.INPUT.equals(l_tradeHistoryUnits[i].ioDiv))||                
                (WEB3IoDivDef.INPUT.equals(l_tradeHistoryUnits[i].ioDiv)&&
                  WEB3TradeHistoryTradeCodeDef.TRADE_CODE_A2.equals(l_tradeHistoryUnits[i].tradeCode)&&
                  WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_1079.equals(l_tradeHistoryUnits[i].remarkCode)))              
            )
            {
                l_blProfitLossLink = true;
                break;        
            }
            else
            {
                continue;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_blProfitLossLink;    	   
    }

    /**
     * (createソート条件forダウンロード)<BR>
     * ソート条件を作成し、返却する。<BR>
     * <BR>
     * １）受渡日（昇順）、約定日（昇順）、商品コード（昇順）、<BR>
     *  銘柄コード（昇順）、受渡金額（昇順）をソート条件として編集する。<BR>
     * <BR>
     * 　@ソート条件  = ""delivery_date asc"<BR>
     * 　@　@　@　@　@　@　@+ ", exec_date asc"<BR>
     * 　@　@　@　@　@　@　@+ ", commodity_code asc"<BR>
     * 　@　@　@　@　@　@　@+ ", product_code asc"<BR>
     * 　@　@　@　@　@　@　@+ ", net_amount asc"<BR>
     * <BR>
     * ２）作成したソート条件を返却する。<BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * 取引履歴ソートキーオブジェクト<BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String createSortCondForDownload() 
    {
        final String STR_METHOD_NAME = " createSortCondForDownload() "; 
        log.entering(STR_METHOD_NAME); 
        
        // １）受渡日（昇順）、約定日（昇順）、商品コード（昇順）、
        // 銘柄コード（昇順）、受渡金額（昇順）をソート条件として編集する。
        String l_strSortCond = " delivery_date asc, exec_date asc, commodity_code asc,"
            + " product_code asc, net_amount asc ";

        log.exiting(STR_METHOD_NAME);  
        //２）作成したソート条件を返却する。
        return l_strSortCond;
    }

    /**
     * (getプリファ@レンス)<BR>
     * パラメータに指定された設定名称の設定値をシステムプリファ@レンステーブルから取得する。<BR>
     * <BR>
     * １）システムプリファ@レンステーブルから以下の条件でレコードを取得する。<BR>
     * <BR>
     * 　@[取得条件]<BR>
     * 　@名称（環境変数名） = パラメータ.設定名称<BR>
     * <BR>
     * ２）取得したシステムプリファ@レンステーブルのレコードの設定値を返却する。<BR>
     * @@param l_strPreferences - (名称（環境変数名）)<BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String getPreferences(String l_strPreferences) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPreferences(String) "; 
        log.entering(STR_METHOD_NAME); 
        
        //１）システムプリファ@レンステーブルから以下の条件でレコードを取得する。
        // 　@[取得条件]
        // 　@名称（環境変数名） = パラメータ.設定名称
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" name = ? ");

        Object[] l_objWhere = {l_strPreferences};

        List l_lisRecords = new ArrayList();
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                SystemPreferencesRow.TYPE,
                l_strWhere.toString(),
                null,
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //２）取得したシステムプリファ@レンステーブルのレコードの設定値を返却する。
        String l_strValue = null;
        if (l_lisRecords.size() != 0)
        {
            SystemPreferencesRow l_preferencesRow = (SystemPreferencesRow)l_lisRecords.get(0);

            l_strValue = l_preferencesRow.getValue();

            //データ不整合の場合
            if (!WEB3StringTypeUtility.isInteger(l_strValue))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                        + "データ不整合エラー。");
            }
        }

        log.exiting(STR_METHOD_NAME);  
        return l_strValue;
    }
    
	/**
	 * (get部店プリファ@レンス)<BR>
	 * パラメータに指定された該当部店システムプリファ@レンステーブルのプリファ@レンスの値を返却する。<BR>
	 * <BR>
	 * １）部店システムプリファ@レンステーブルから以下の条件でレコードを取得する。<BR>
	 * <BR>
	 * 　@[取得条件]<BR>
	 * 　@部店ID = パラメータ.部店ID<BR>
	 * 　@プリファ@レンス名 = プリファ@レンス名.銘柄名取得先区分<BR>
	 * 　@プリファ@レンス名の連番 = 1<BR>
	 * <BR>
	 * 　@検索結果が取得できなかった場合、 nullを返却する。<BR>
	 * <BR>
	 * ２）取得した部店システムプリファ@レンステーブルのプリファ@レンスの値を返却する。<BR>
	 * @@param l_lngBranchId - (部店ID)<BR>
	 * @@return Integer
	 * @@roseuid 413C30E102D8
	 */
	protected Integer getBranchPreferences(long l_lngBranchId) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getBranchPreferences(long) "; 
		log.entering(STR_METHOD_NAME); 
        
		//１）部店システムプリファ@レンステーブルから以下の条件でレコードを取得する。
		//　@[取得条件]
		//　@部店ID = パラメータ.部店ID
		//　@プリファ@レンス名 = プリファ@レンス名.銘柄名取得先区分
		//　@プリファ@レンス名の連番 = 1
		StringBuffer l_strWhere = new StringBuffer();
		l_strWhere.append(" branch_id = ? ");
		l_strWhere.append(" and name = ? ");
		l_strWhere.append(" and name_serial_no = ? ");

		Object[] l_objWhere = {
			new Long(l_lngBranchId),
			WEB3BranchPreferencesNameDef.GETPRODUCTNAME_DIV,
			"1"};

		List l_lisRecords = null;
		try
		{
			QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
			l_lisRecords = l_QueryProcessor.doFindAllQuery(
				BranchPreferencesRow.TYPE,
				l_strWhere.toString(),
				null,
				null,
				l_objWhere);
		}
		catch (DataFindException l_dfe)
		{
			log.error(STR_METHOD_NAME, l_dfe);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dfe.getMessage(),
				l_dfe);
		}
		catch (DataQueryException l_dqe)
		{
			log.error(STR_METHOD_NAME, l_dqe);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dqe.getMessage(),
				l_dqe);
		}
		catch (DataNetworkException l_dne)
		{
			log.error(STR_METHOD_NAME, l_dne);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dne.getMessage(),
				l_dne);
		}

		//検索結果が取得できなかった場合、 nullを返却する。
		//２）取得した部店システムプリファ@レンステーブルのプリファ@レンスの値を返却する。
		if (l_lisRecords == null || l_lisRecords.size() == 0)
		{
			log.exiting(STR_METHOD_NAME);  
			return null;
		}
		else
		{
			BranchPreferencesRow l_preferencesRow = (BranchPreferencesRow)l_lisRecords.get(0);

			String l_strValue = l_preferencesRow.getValue();
			if (!WEB3StringTypeUtility.isInteger(l_strValue))
			{
				log.exiting(STR_METHOD_NAME);
				return null;
			}

			log.exiting(STR_METHOD_NAME);
			return new Integer(l_strValue);
		}
	}
    
	/**
	 * (get投信銘柄マスター)<BR>
	 * 指定されたパラメータに該当投信銘柄マスタデータのオブジェクトを返却する。<BR>
	 * <BR>
	 * １）投信銘柄マスタから以下の条件でレコードを取得する。<BR>
	 * <BR>　@
	 * 　@[取得条件]<BR>
	 * 　@証券会社コード = パラメータ.証券会社コード<BR>
	 * 　@銘柄コード = パラメータ.銘柄コード<BR>
	 * 　@回号コード = 0<BR>
	 * <BR>
	 * 　@検索結果が取得できなかった場合、 nullを返却する。<BR> 
	 * <BR>
	 * ２）取得した投信銘柄マスタデータのオブジェクトを返却する。<BR>
	 * @@param l_strInstitutionCode - (証券会社コード)<BR>
	 * @@param l_strProductCode - (銘柄コード)<BR>
	 * @@return Object
	 * @@roseuid 413C30E102D8
	 */
	protected Object getFundProductMaster(
		String l_strInstitutionCode, 
		String l_strProductCode) 
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " getFundProductMaster(String, String) "; 
		log.entering(STR_METHOD_NAME); 
        
		//１）投信銘柄マスタから以下の条件でレコードを取得する。
		//  [取得条件]
		//  証券会社コード = パラメータ.証券会社コード
		//  銘柄コード = パラメータ.銘柄コード
		//  回号コード = 0
		StringBuffer l_strWhere = new StringBuffer();
		l_strWhere.append(" institution_code = ? ");
		l_strWhere.append(" and product_code = ? ");
		l_strWhere.append(" and product_issue_code = ? ");

		Object[] l_objWhere = {
			l_strInstitutionCode,
			l_strProductCode,
			"0"};

		List l_lisRecords = null;
		try
		{
			QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
			l_lisRecords = l_QueryProcessor.doFindAllQuery(
				MutualFundProductRow.TYPE,
				l_strWhere.toString(),
				null,
				null,
				l_objWhere);
		}
		catch (DataFindException l_dfe)
		{
			log.error(STR_METHOD_NAME, l_dfe);
			return null;
		}
		catch (DataQueryException l_dqe)
		{
			log.error(STR_METHOD_NAME, l_dqe);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dqe.getMessage(),
				l_dqe);
		}
		catch (DataNetworkException l_dne)
		{
			log.error(STR_METHOD_NAME, l_dne);

			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_dne.getMessage(),
				l_dne);
		}

		//　@検索結果が取得できなかった場合、 nullを返却する。 
		//２）取得した投信銘柄マスタデータのオブジェクトを返却する。
		MutualFundProductRow l_fundProductRow = null;
		if (!l_lisRecords.isEmpty())
		{
			l_fundProductRow = (MutualFundProductRow)l_lisRecords.get(0);
		}

		log.exiting(STR_METHOD_NAME);  
		return l_fundProductRow;
	}    

	/**
	 * (is当日出金)<BR>
	 * パラメータに指定された該当部店システムプリファ@レンステーブルに<BR> 
	 * プリファ@レンスの値が合った場合はtrue、なかった場合はfalseを返却する。<BR> 
	 * <BR>
	 * １）部店システムプリファ@レンステーブルから以下の条件でレコードを取得する。<BR> 
	 * <BR>
	 * 　@[取得条件] <BR>
	 * 　@部店ID = パラメータ.部店ID <BR>
	 * 　@プリファ@レンス名 = プリファ@レンス名.当日出金チェック <BR>
	 * 　@プリファ@レンス名の連番 = 1 <BR>
	 * <BR>
	 * 　@検索結果が取得できなかった場合、 falseを返却する。 <BR>
	 * <BR>
	 * ２）取得した部店システムプリファ@レンステーブルのプリファ@レンスの値が <BR>
	 * 　@　@1（当日出金可能）の場合、tureを返却する。 <BR>
	 * 　@　@それ以外の場合はfalseを返却する。<BR>
	 * @@param l_lngBranchId - (部店ID)<BR>
	 * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 413C30E102D8
	 */
	protected boolean isTodayPayment(long l_lngBranchId)
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " isTodayPayment(long)"; 
		log.entering(STR_METHOD_NAME);
		
        //プリファ@レンス名の連番を定義
        final int NAME_SERIAL_NO = 1;

        //１）部店システムプリファ@レンステーブルから以下の条件でレコードを取得する。 
		// [取得条件] 
		//  　@部店ID = パラメータ.部店ID 
		//  　@プリファ@レンス名 = プリファ@レンス名.当日出金チェック 
		//  　@プリファ@レンス名の連番 = 1 
        BranchPreferencesRow l_branchPreferencesRow = null;
        try
        {
        	l_branchPreferencesRow = BranchPreferencesDao.findRowByPk(
            	l_lngBranchId,
                WEB3BranchPreferencesNameDef.TODAY_PAYMENT_CHECK,
                NAME_SERIAL_NO);
        }
        catch (DataFindException l_exp)
        {
            //検索結果が取得できなかった場合、 falseを返却する。 
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException l_exp)
        {
            log.error(l_exp.getMessage());
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        catch (DataNetworkException l_exp)
        {
            log.error(l_exp.getMessage());
            log.exiting(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }

        //２）取得した部店システムプリファ@レンステーブルのプリファ@レンスの値が 
		//  1（当日出金可能）の場合、tureを返却する。 
        String l_strValue = l_branchPreferencesRow.getValue();
        if (WEB3TodayPaymentCheckDef.CHECK.equals(l_strValue))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

		//  それ以外の場合はfalseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
	}
	
	/**
	 * (is金額補正)<BR>
	 * 受渡金額の補正を行う。<BR> 
	 * <BR>
	 * １）以下の条件にて金額を補正するレコードかを判別する。<BR> 
	 * <BR>
	 * 　@・顧客マスタ.MRF口座開設区分=1（口座開設）<BR> 
	 * 　@・取引履歴.商品コード = 99　@&& <BR> 
	 * 　@　@　@取引履歴.取引コード = A1　@&& <BR>
	 * 　@　@　@取引履歴.摘要コード = 0070　@&& <BR>　@ 
	 * 　@　@　@取引履歴.出入区分 = 1 <BR>
	 * <BR>
	 * 　@上記条件に当てはまるレコードだった場合、<BR> 
	 * 　@　@『　@wk受渡金額 = wk受渡金額 + 取引履歴.受渡金額　@』<BR> 
	 * 　@上記条件に当てはまらない場合、処理を行わない。<BR>
	 * @@param l_tradeHistoryParams - (取引履歴Params)<BR>
	 * @@param l_mainAccount - (顧客)<BR>
     * @@roseuid 413C30E102D8
	 */
	protected void isPaymentRevision(
		TradeHistoryParams l_tradeHistoryParams,
		WEB3GentradeMainAccount l_mainAccount)
	{
		final String STR_METHOD_NAME = " isPaymentRevision(TradeHistoryParams, WEB3GentradeMainAccount)"; 
		log.entering(STR_METHOD_NAME);
		
		//１）以下の条件にて金額を補正するレコードかを判別する。 
		//	・顧客マスタ.MRF口座開設区分=1（口座開設） 
		//	・取引履歴.商品コード = 99　@&&　@ 
		//	　@取引履歴.取引コード = A1　@&& 
		//	　@取引履歴.摘要コード = 0070　@&& 　@ 
		//	　@取引履歴.出入区分 = 1 
		MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
		MainAccountParams l_mainAccountParams = new MainAccountParams(l_mainAccountRow);
		
		if (WEB3AccountOpenDef.OPEN.equals(l_mainAccountParams.getMrfAccOpenDiv())
			&& WEB3TradeHistoryProductCodeDef.MONEY.equals(l_tradeHistoryParams.getCommodityCode())
			&& WEB3TradeHistoryTradeCodeDef.TRADE_CODE_A1.equals(l_tradeHistoryParams.getTradeCode())
			&& WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_0070.equals(l_tradeHistoryParams.getRemarkCode())
			&& WEB3IoDivDef.OUTPUT.equals(l_tradeHistoryParams.getIoDiv()))
		{
			//	上記条件に当てはまるレコードだった場合、 
			//	　@『　@wk受渡金額 = wk受渡金額 + 取引履歴.受渡金額　@』
		    
            BigDecimal l_bdNetAmount = new BigDecimal(l_tradeHistoryParams.getNetAmount());
            BigDecimal l_bdwkNetAmount = new BigDecimal(this.wkNetAmount);
            this.wkNetAmount = l_bdNetAmount.add(l_bdwkNetAmount).doubleValue();
		}
		
		//上記条件に当てはまらない場合、処理を行わない。
        log.exiting(STR_METHOD_NAME);
	}
	
	/**
	 * (calc口座残高)<BR>
	 * 以下の条件の場合、口座残高の計算を行う。<BR> 
	 * <BR>
	 * wk受渡金額 != 0 && 受渡日別残高情報 != null && 受渡日別残高情報.口座残高 != null の場合、<BR> 
   * 以下の計算処理を行う。<BR>
	 * <BR>
	 * １）受渡日別残高情報.口座残高= <BR>
	 * 　@　@　@　@　@　@　@　@受渡日別残高情報.口座残高 + wk受渡金額 <BR>
	 * <BR>
	 * ２）　@wk受渡金額を初期化する。（wk受渡金額 = 0）<BR>
	 * <BR>
	 * @@param l_dailyBalanceUnit - (受渡日別残高情報)<BR>
     * @@roseuid 413C30E102D8
	 */
	protected void calcAccountBalance(WEB3HistoryDailyBalanceUnit l_dailyBalanceUnit)
	{
		final String STR_METHOD_NAME = " calcAccountBalance(WEB3HistoryDailyBalanceUnit)"; 
		log.entering(STR_METHOD_NAME);
		
		//wk受渡金額 != 0 && 受渡日別残高情報 != null && 受渡日別残高情報.口座残高 != null
    //の場合、以下の計算処理を行う。
		if ((this.wkNetAmount != 0) && (l_dailyBalanceUnit != null) && (l_dailyBalanceUnit.accountBalance != null))
		{
			//１）受渡日別残高情報.口座残高= 
			//  受渡日別残高情報.口座残高 - wk受渡金額 
            BigDecimal l_bdAccountBalance = new BigDecimal(l_dailyBalanceUnit.accountBalance);
            BigDecimal l_bdwkNetAmount = new BigDecimal(this.wkNetAmount);

            //2006/05/01 SCS鈴木修正START
//            double l_dblAccountBalance = l_bdAccountBalance.subtract(l_bdwkNetAmount).doubleValue();
            double l_dblAccountBalance = l_bdAccountBalance.add(l_bdwkNetAmount).doubleValue();
            //2006/05/01 SCS鈴木修正END

			l_dailyBalanceUnit.accountBalance = 
			    WEB3StringTypeUtility.formatNumber(l_dblAccountBalance);
			
			//２）　@wk受渡金額を初期化する。（wk受渡金額 = 0）
			this.wkNetAmount = 0;
		}
		
		log.exiting(STR_METHOD_NAME);
	}
}
@
