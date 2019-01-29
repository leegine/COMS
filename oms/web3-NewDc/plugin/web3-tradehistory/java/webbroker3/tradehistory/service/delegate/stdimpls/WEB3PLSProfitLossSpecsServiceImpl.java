head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 損益明細照会サービスImpl(WEB3PLSProfitLossSpecsServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 王敏 (中訊) 新規作成
                 : 2006/10/19  趙林鵬 (中訊) モデル056,058,059,060,061,062,063
                 : 2006/11/07  周捷 (中訊) モデル066
                 : 2006/11/07  周捷 (中訊) モデル067
                 : 2006/12/15  何文敏 (中訊) モデル068
                 : 2006/12/18  何文敏 (中訊) モデル069
*/

package webbroker3.tradehistory.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3CommodityCodeTypeDef;
import webbroker3.common.define.WEB3ProfitLossRecordDivDef;
import webbroker3.common.define.WEB3ReturnDivDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;
import webbroker3.tradehistory.WEB3PLSProfitLossSpecsCsv;
import webbroker3.tradehistory.data.ProfitLossSpecParams;
import webbroker3.tradehistory.data.ProfitLossSpecRow;
import webbroker3.tradehistory.define.WEB3PLSProfitLossSpecsSortKeyItemDef;
import webbroker3.tradehistory.define.WEB3PlsBvsCarryoverBalanceRecDef;
import webbroker3.tradehistory.define.WEB3PlsBvsDetailOrderRecDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductDivDef;
import webbroker3.tradehistory.define.WEB3TradeHistorySortDivDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTransactionDivDef;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsSortKeyUnit;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsUnit;
import webbroker3.tradehistory.service.delegate.WEB3PLSProfitLossSpecsService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (損益明細照会サービスImpl)<BR>
 * 損益明細照会サービス実装クラス<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsServiceImpl extends WEB3ClientRequestService implements WEB3PLSProfitLossSpecsService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsServiceImpl.class);

    /**
     * @@roseuid 418877BB005D
     */
    public WEB3PLSProfitLossSpecsServiceImpl()
    {

    }

    /**
     * 損益明細照会処理を行う。<BR>
     * <BR>
     * １）リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * 　@■ 引数のリクエストデータが、『損益明細照会リクエスト』の場合<BR>
     * 　@　@・this.get損益明細照会()メソッドをコールする。<BR>
     * <BR>
     * 　@■ 引数のリクエストデータが、『損益明細ファ@イルダウンロードリクエスト』の場合<BR>
     * 　@　@・this.get損益明細ファ@イルダウンロード()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416CFD660297
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3PLSProfitLossSpecsRequest)
        {
            l_response = this.getProfitLossSpecs((WEB3PLSProfitLossSpecsRequest)l_request);
        }
        else if (l_request instanceof WEB3PLSProfitLossDownloadRequest)
        {
            l_response = this.getProfitLossDownload((WEB3PLSProfitLossDownloadRequest)l_request);
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
     * (get損益明細照会)<BR>
     * 損益明細照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(損益明細照会サービス)get損益明細照会」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 損益明細照会リクエストオブジェクト<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsResponse
     * @@roseuid 416CFDB50110
     */
    protected WEB3PLSProfitLossSpecsResponse getProfitLossSpecs(
        WEB3PLSProfitLossSpecsRequest l_request) throws WEB3BaseException
    {
       final String STR_METHOD_NAME = "getProfitLossSpecs(WEB3PLSProfitLossSpecsRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();

        //1.2 validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.4 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.5 get損益明細件数(顧客, String)
        //顧客：　@getMainAccount()の戻り値
        //処理区分：　@リクエストデータ.処理区分
        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
        int l_intProfitLossSpecCount =
            l_historyTradeHistoryDataManager.getProfitLossSpecCount(
                l_mainAccount,
                l_request.transactionDiv);

        WEB3PLSProfitLossSpecsResponse l_response =
            (WEB3PLSProfitLossSpecsResponse)l_request.createResponse();
        if (l_intProfitLossSpecCount == 0)
        {
            l_response.totalPages = "1";
            l_response.totalRecords = "0";
            l_response.pageIndex = "1";
            l_response.profitLossUnits = null;
            return l_response;
        }

        //1.6 取得データ格納用ArrayListを生成する。
        List l_lisProfitLossSpecs = new ArrayList();

        //1.7 create残高データ(顧客)

        WEB3PLSProfitLossSpecsUnit l_web3PLSProfitLossSpecsUnit = this.createBalanceData(l_mainAccount);
        //1.8 create残高データ()の戻り値 == nullの場合
        if (l_web3PLSProfitLossSpecsUnit == null)
        {
            //1.8.1 createResponse()

            //1.8.2 プロパティセット
            l_response.totalPages  = "1";
            l_response.totalRecords  = "0";
            l_response.pageIndex  = "1";
            l_response.profitLossUnits  = null;
            return l_response;
        }

        //1.9(*)リクエストデータ.処理区分 == null(2ヶ月表示) の場合
        List l_lisprofitLossSpecList = null;
        if (l_request.transactionDiv == null)
        {
            //1.9.1 create繰越残高データ(顧客, String, 損益明細情報)
            WEB3PLSProfitLossSpecsUnit l_plsProfitLossSpecsUnit =
               this.createCarryoverBalanceData(
                   l_mainAccount,
                   l_request.displayTerm,
                   l_web3PLSProfitLossSpecsUnit);

            //add(Object)
            //ArrayListに繰越残高データを追加する。
            //繰越残高データ：　@create繰越残高データ()の戻り値
            l_lisProfitLossSpecs.add(l_plsProfitLossSpecsUnit);

            //1.9.3 get損益明細一覧(顧客, String, String, Date)
            l_lisprofitLossSpecList =
                l_historyTradeHistoryDataManager.getProfitLossSpecList(
                    l_mainAccount,
                    WEB3PlsBvsDetailOrderRecDef.DETAIL_ORDER_REC,
                    l_request.displayTerm,
                    null);
        }

        //1.10 (*)リクエストデータ.処理区分 == 01(18ヶ月表示) の場合
        if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(l_request.transactionDiv))
        {
            Date l_datListStartDate = WEB3DateUtility.getDate(l_request.listStartDate, "yyyyMMdd");
            Date l_datListEndDate = WEB3DateUtility.getDate(l_request.listEndDate, "yyyyMMdd");
            //1.10.1（*）初期表示時（メニューからの遷移）の場合
            //リクエストデータ.表示期間From == null & リクエストデータ.表示期間To == null
            Date l_datCalcDate = null;
            if (l_request.listStartDate == null && l_request.listEndDate == null)
            {
                //1.10.1.1create検索条件文字列(Date, Date, String, String)
                //表示期間From：　@リクエストデータ.表示期間From
                //表示期間To：　@リクエストデータ.表示期間To
                //銘柄コード：　@リクエストデータ.銘柄コード
                //商品区分：　@リクエストデータ.商品区分
                String l_strQueryString = this.createQueryString(
                    l_datListStartDate,
                    l_datListEndDate,
                    l_request.productCode,
                    l_request.commodityType);

                //1.10.1.2 create検索条件データコンテナ(Date, Date, String, String)
                //表示期間From：　@リクエストデータ.表示期間From
                //表示期間To：　@リクエストデータ.表示期間To
                //銘柄コード：　@リクエストデータ.銘柄コード
                //商品区分：　@リクエストデータ.商品区分
                String[] l_strQueryDataContainers = this.createQueryDataContainer(
                    l_datListStartDate,
                    l_datListEndDate,
                    l_request.productCode,
                    l_request.commodityType);

                //createソート条件(String, 損益明細ソートキー)
                //ソート区分：　@01：初期表示
                //ソートキー：　@リクエストデータ.ソートキー
                String l_strSortCond = this.createSortCond(
                    WEB3TradeHistorySortDivDef.INITIAL_DISPLAY,
                    l_request.sortKeys);

                //1.10.1.4get計算年月日(顧客, String, Object[], String)
                //顧客：　@getMainAccount()の戻り値
                //検索条件文字列：　@create検索受検文字列()の戻り値
                //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値
                //ソート条件：　@createソート条件()の戻り値
                l_datCalcDate = l_historyTradeHistoryDataManager.getCalcDate(
                    l_mainAccount,
                    l_strQueryString,
                    l_strQueryDataContainers,
                    l_strSortCond);
            }
            //1.10.2create検索条件文字列(Date, Date, String, String)
            //表示期間From：　@リクエストデータ.表示期間From (*1)
            //表示期間To：　@リクエストデータ.表示期間To (*1)
            //銘柄コード：　@リクエストデータ.銘柄コード
            //商品区分：　@リクエストデータ.商品区分
            //(*1)
            //表示期間From = null & 表示期間To = null（初期表示）の場合
            //　@・表示期間From：　@get計算年月日()の戻り値の1ヶ月前の日付
            //　@・表示期間To：　@get計算年月日()の戻り値
            //※get計算年月日()の戻り値 = null の場合、表示期間FromとToには nullを設定する.
            if (l_request.listStartDate == null && l_request.listEndDate == null)
            {
                if (l_datCalcDate != null)
                {
                    Calendar l_calendar = new GregorianCalendar();
                    l_calendar.setTime(l_datCalcDate);
                    l_calendar.add(Calendar.MONTH, -1);
                    l_datListStartDate = l_calendar.getTime();
                    l_datListEndDate = l_datCalcDate;
                }
            }
            String l_strQueryString = this.createQueryString(
                    l_datListStartDate,
                    l_datListEndDate,
                    l_request.productCode,
                    l_request.commodityType);

            //1.10.3create検索条件データコンテナ(Date, Date, String, String)
            String[] l_strQueryDataContainers = this.createQueryDataContainer(
                l_datListStartDate,
                l_datListEndDate,
                l_request.productCode,
                l_request.commodityType);

            //createソート条件(String, 損益明細ソートキー)
            //ソート区分：　@02：初期表示以外
            //ソートキー：　@リクエストデータ.ソートキー
            String l_strSortCond = this.createSortCond(
                WEB3TradeHistorySortDivDef.INITIAL_DISPLAY_OTHERS,
                l_request.sortKeys);

            //1.10.5get損益明細一覧(顧客, String, Object[], String)
            //顧客：　@getMainAccount()の戻り値
            //検索条件文字列：　@create検索受検文字列()の戻り値
            //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値
            //ソート条件：　@createソート条件()の戻り値
            l_lisprofitLossSpecList =
                l_historyTradeHistoryDataManager.getProfitLossSpecList(
                    l_mainAccount,
                    l_strQueryString,
                    l_strQueryDataContainers,
                    l_strSortCond);

            for (int i = 0; i < l_request.sortKeys.length; i++)
            {
                //リクエストデータ.ソートキー.キー項目 = "calcDate(計算年月日)"　@かつ、
                //リクエストデータ.ソートキー.昇順/降順 = D(降順) の場合
                //リクエストデータ.ソートキー要素数分上記の処理を行う。
                if (WEB3PLSProfitLossSpecsSortKeyItemDef.CALC_DATE.equals(l_request.sortKeys[i].keyItem)
                    && WEB3AscDescDef.DESC.equals(l_request.sortKeys[i].ascDesc))
                {
                    //add(Object)
                    //ArrayListに残高データを追加する。
                    //残高データ：　@create残高データ()の戻り値
                    l_lisProfitLossSpecs.add(l_web3PLSProfitLossSpecsUnit);
                }
            }
        }

        int l_intSize = 0;
        if (l_lisprofitLossSpecList != null)
        {
            l_intSize = l_lisprofitLossSpecList.size();
        }

        //1.11 取得した明細&入出金レコード(=損益明細Params)数分Loop処理
        for (int i = 0; i < l_intSize; i++)
        {
             //1.11.1 損益明細情報( )
             WEB3PLSProfitLossSpecsUnit l_profitLossSpecsUnit = new WEB3PLSProfitLossSpecsUnit();

             //1.11.2 分岐フロー明細レコードの場合(損益明細Params.レコード区分 == "20：明細レコード")
             ProfitLossSpecParams  l_profitLossSpecParams =  (ProfitLossSpecParams)l_lisprofitLossSpecList.get(i);
             if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecParams.getRecDiv()))
             {
                  //1.11.2.1 プロパティセット
                  if (l_profitLossSpecParams.getProfitLossSpecIdIsSet())
                  {
                      l_profitLossSpecsUnit.profitLossSpecId = "" + l_profitLossSpecParams.getProfitLossSpecId();
                  }
                  else
                  {
                      l_profitLossSpecsUnit.profitLossSpecId = "";
                  }
                  l_profitLossSpecsUnit.prolossRecDiv = l_profitLossSpecParams.getRecDiv();
                  l_profitLossSpecsUnit.calcDate = l_profitLossSpecParams.getCalcDate();
                  l_profitLossSpecsUnit.deliveryDate = l_profitLossSpecParams.getDeliveryDate();
                  l_profitLossSpecsUnit.productName = l_profitLossSpecParams.getStandardName();
                  l_profitLossSpecsUnit.fundType = l_profitLossSpecParams.getCommodityDiv();
                  l_profitLossSpecsUnit.applicationCode = l_profitLossSpecParams.getApplicationCode();
                  l_profitLossSpecsUnit.termDiv = l_profitLossSpecParams.getTermDiv();
                  if (l_profitLossSpecParams.getQuantityIsNull())
                  {
                      l_profitLossSpecsUnit.quantity = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.quantity =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getQuantity()/100000);
                  }

                  l_profitLossSpecsUnit.passDate = l_profitLossSpecParams.getPassDate();

                  if (l_profitLossSpecParams.getPassAmountIsNull())
                  {
                      l_profitLossSpecsUnit.passAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.passAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getPassAmount());
                  }

                  l_profitLossSpecsUnit.getDate = l_profitLossSpecParams.getGetDate();

                  if (l_profitLossSpecParams.getGetAmountIsNull())
                  {
                      l_profitLossSpecsUnit.getAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.getAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getGetAmount());
                  }

                  if (l_profitLossSpecParams.getProlossAmountIsNull())
                  {
                      l_profitLossSpecsUnit.prolossAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.prolossAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getProlossAmount());
                  }

                  if (l_profitLossSpecParams.getTotalProlossAmountIsNull())
                  {
                      l_profitLossSpecsUnit.totalProlossAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.totalProlossAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getTotalProlossAmount());
                  }
             }
             //1.11.3 分岐フロー入出金レコードの場合(損益明細Params.レコード区分 == "21：入出金レコード")
             else if (l_profitLossSpecParams.getRecDiv().equals(WEB3ProfitLossRecordDivDef.ORDER_REC))
             {
                  //1.11.3.1 プロパティセット
                  if (l_profitLossSpecParams.getProfitLossSpecIdIsSet())
                  {
                      l_profitLossSpecsUnit.profitLossSpecId = "" + l_profitLossSpecParams.getProfitLossSpecId();
                  }
                  else
                  {
                      l_profitLossSpecsUnit.profitLossSpecId = null;
                  }
                  l_profitLossSpecsUnit.prolossRecDiv = l_profitLossSpecParams.getRecDiv();
                  l_profitLossSpecsUnit.calcDate = l_profitLossSpecParams.getCalcDate();
                  l_profitLossSpecsUnit.deliveryDate = l_profitLossSpecParams.getDeliveryDate();
                  l_profitLossSpecsUnit.returnDiv = l_profitLossSpecParams.getReturnDiv();
                  l_profitLossSpecsUnit.remark = l_profitLossSpecParams.getRemark();

                  if (!l_profitLossSpecsUnit.returnDiv.equals(WEB3ReturnDivDef.RETURN))
                  {
                      if (l_profitLossSpecParams.getProlossAmountIsNull())
                      {
                          l_profitLossSpecsUnit.prolossAmount = null;
                      }
                      else
                      {
                          l_profitLossSpecsUnit.prolossAmount =
                              WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getProlossAmount());
                      }

                      if (l_profitLossSpecParams.getTaxableAmountIsNull())
                      {
                          l_profitLossSpecsUnit.taxableAmount = null;
                      }
                      else
                      {
                          l_profitLossSpecsUnit.taxableAmount =
                              WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getTaxableAmount());
                      }

                  }
                  if (l_profitLossSpecParams.getCollectTaxAmountIsNull())
                  {
                      l_profitLossSpecsUnit.collectTaxAmount = null;
                  }
                  else
                  {
                      l_profitLossSpecsUnit.collectTaxAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getCollectTaxAmount());
                  }
                  if (l_profitLossSpecParams.getCollectTaxNAmountIsNull())
                  {

                  }
                  else
                  {
                      l_profitLossSpecsUnit.collectTaxNAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getCollectTaxNAmount());
                  }
                  if (l_profitLossSpecParams.getCollectTaxLAmountIsNull())
                  {

                  }
                  else
                  {
                      l_profitLossSpecsUnit.collectTaxLAmount =
                          WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getCollectTaxLAmount());
                  }
             }
             //add(Object)
             //ArrayListに明細または入出金データを追加する。
             //明細or入出金データ：　@プロパティセットした損益明細情報
             l_lisProfitLossSpecs.add(l_profitLossSpecsUnit);
        }

        //リクエストデータ.処理区分 == null(2ヶ月表示) の場合
        if (l_request.transactionDiv == null)
        {
            //add(Object)
            //ArrayListに残高データを追加する。
            //残高データ：　@create残高データ()の戻り値
            l_lisProfitLossSpecs.add(l_web3PLSProfitLossSpecsUnit);
        }

        //リクエストデータ.処理区分 == 01(18ヶ月表示)の場合
        //リクエストデータ.ソートキー.キー項目 = "calcDate(計算年月日)"　@かつ、
        //リクエストデータ.ソートキー.昇順/降順 = A(昇順) の場合
        //ArrayListに残高データを追加する。
        //リクエストデータ.ソートキー要素数分上記の処理を行う。
        if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(l_request.transactionDiv))
        {
            for (int i = 0; i < l_request.sortKeys.length; i++)
            {
                //add(Object)
                if (WEB3PLSProfitLossSpecsSortKeyItemDef.CALC_DATE.equals(l_request.sortKeys[i].keyItem)
                    && WEB3AscDescDef.ASC.equals(l_request.sortKeys[i].ascDesc))
                {
                    l_lisProfitLossSpecs.add(l_web3PLSProfitLossSpecsUnit);
                }
            }
        }

        //1.13 toArray( )
        WEB3PLSProfitLossSpecsUnit[] l_web3PLSProfitLossSpecsUnits =
            new WEB3PLSProfitLossSpecsUnit[l_lisProfitLossSpecs.size()];
        l_lisProfitLossSpecs.toArray(l_web3PLSProfitLossSpecsUnits);
        //1.14 createResponse
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_web3PLSProfitLossSpecsUnits, l_intPageIndex, l_intPageSize);
        WEB3PLSProfitLossSpecsUnit[] l_returnSpecUnit =
            (WEB3PLSProfitLossSpecsUnit[])l_pageIndexInfo.getArrayReturned(WEB3PLSProfitLossSpecsUnit.class);
        //1.15 (*)プロパティセット
        l_response.totalPages  = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        l_response.profitLossUnits = l_returnSpecUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create残高データ)<BR>
     * 残高データを格納した損益明細情報インスタンスを返却する。<BR>
     * <BR>
     * １）取引履歴ﾃﾞｰﾀﾏﾈｰｼﾞｬ.get残高レコードFrom損益明細()メソッドを<BR>
     * 　@コールし、損益明細Paramsを取得する。<BR>
     * <BR>
     * 　@[get残高レコードFrom損益明細()にセットするパラメータ]<BR>
     * 　@　@顧客：　@パラメータ.顧客<BR>
     * <BR>
     * 　@戻り値がnullの場合、nullを返却し終了する。<BR>
     * <BR>
     * ２）損益明細情報インスタンスを生成し以下のプロパティをセットする。<BR>
     * 　@　@ID     ＝　@１）の戻り値.損益明細ID<BR>
     * 　@　@作業年月日  ＝　@１）の戻り値.作業年月日<BR>
     * 　@　@損益明細レコード区分　@＝　@１）の戻り値.レコード区分<BR>
     * 　@　@譲渡金額   ＝　@１）の戻り値.譲渡金額<BR>
     * 　@　@取得費等   ＝　@１）の戻り値.取得費等<BR>
     * 　@　@累計損益   ＝　@１）の戻り値.累計損益<BR>
     * <BR>
     * 　@[①@取引履歴ﾃﾞｰﾀﾏﾈｰｼﾞｬ.is１２月() == trueの場合]<BR>
     * 　@　@徴収税額   ＝　@１）の戻り値.徴収済税額(当月)<BR>
     * 　@　@徴収税額(国税)   ＝　@１）の戻り値.徴収済税額(当月国税)<BR>
     * 　@　@徴収税額(地方税)  ＝　@１）の戻り値.徴収済税額(当月地方税)<BR>
     * <BR>
     * 　@[①@以外の場合]<BR>
     * 　@　@徴収税額   ＝　@１）の戻り値.徴収済税額(翌月)<BR>
     * 　@　@徴収税額(国税)   ＝　@１）の戻り値.徴収済税額(翌月国税)<BR>
     * 　@　@徴収税額(地方税)  ＝　@１）の戻り値.徴収済税額(翌月地方税)<BR>
     * <BR>
     * 　@　@※is１２月()のパラメータには、１）の戻り値.作業年月日をセット。<BR>
     * <BR>
     * ３）プロパティセットした損益明細情報を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsUnit
     * @@roseuid 416E0E640322
     */
    protected WEB3PLSProfitLossSpecsUnit createBalanceData(WEB3GentradeMainAccount l_mainAccount)  throws  WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceData(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);

        //１）取引履歴ﾃﾞｰﾀﾏﾈｰｼﾞｬ.get残高レコードFrom損益明細()メソッドを
        // コールし、損益明細Paramsを取得する。
        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
        ProfitLossSpecParams l_profitLossSpecParams = l_historyTradeHistoryDataManager.getBalanceRecordFromProfitLoss(l_mainAccount);
        if (l_profitLossSpecParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //2 損益明細情報インスタンスを生成し以下のプロパティをセットする。
        WEB3PLSProfitLossSpecsUnit l_plsProfitLossSpecsUnit = new WEB3PLSProfitLossSpecsUnit();
        //ID ＝　@１）の戻り値.損益明細ID
        if (l_profitLossSpecParams.getProfitLossSpecIdIsSet())
        {
            l_plsProfitLossSpecsUnit.profitLossSpecId = "" + l_profitLossSpecParams.getProfitLossSpecId();
        }
        //作業年月日 ＝　@１）の戻り値.作業年月日
        l_plsProfitLossSpecsUnit.workDate = l_profitLossSpecParams.getWorkDate();
        l_plsProfitLossSpecsUnit.prolossRecDiv = l_profitLossSpecParams.getRecDiv();
        if (l_profitLossSpecParams.getPassAmountIsNull())
        {
            l_plsProfitLossSpecsUnit.passAmount = null;
        }
        else
        {
            l_plsProfitLossSpecsUnit.passAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getPassAmount());
        }
         
        if (l_profitLossSpecParams.getGetAmountIsNull())
        {
            l_plsProfitLossSpecsUnit.getAmount = null;
        }
        else
        {
            l_plsProfitLossSpecsUnit.getAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getGetAmount());
        }
        
        if (l_profitLossSpecParams.getTotalProlossAmountIsNull())
        {
            l_plsProfitLossSpecsUnit.totalProlossAmount = null;
        }
        else
        {
            l_plsProfitLossSpecsUnit.totalProlossAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getTotalProlossAmount());
        }
        
        //[①@取引履歴ﾃﾞｰﾀﾏﾈｰｼﾞｬ.is１２月() == trueの場合]
        if (l_historyTradeHistoryDataManager.isDecember(l_profitLossSpecParams.getWorkDate()))
        {
            if (l_profitLossSpecParams.getColltaxAmountCurrIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxAmountCurr());
            }
            
            if (l_profitLossSpecParams.getColltaxNAmountCurrIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxNAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxNAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxNAmountCurr());
            }
            
            if (l_profitLossSpecParams.getColltaxLAmountCurrIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxLAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxLAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxLAmountCurr());    
            }
            
        }
        else
        {
            if (l_profitLossSpecParams.getColltaxAmountNxtIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxAmountNxt());
            }
            if (l_profitLossSpecParams.getColltaxNAmountNxtIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxNAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxNAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxNAmountNxt());
            }
            if (l_profitLossSpecParams.getColltaxLAmountNxtIsNull())
            {
                l_plsProfitLossSpecsUnit.collectTaxLAmount = null;
            }
            else
            {
                l_plsProfitLossSpecsUnit.collectTaxLAmount = WEB3StringTypeUtility.formatNumber(l_profitLossSpecParams.getColltaxLAmountNxt());
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_plsProfitLossSpecsUnit;
    }

    /**
     * (create繰越残高データ)<BR>
     * 繰越残高データを格納する損益明細情報を返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(損益明細照会サービス)create繰越残高データ」参照<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strDisplayTerm - (表示期間)<BR>
     * 表示期間<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     * @@param l_balanceUnit - (残高情報)<BR>
     * 残高情報を格納した損益明細情報<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsUnit
     * @@roseuid 416E201501DA
     */
    protected WEB3PLSProfitLossSpecsUnit createCarryoverBalanceData(WEB3GentradeMainAccount l_mainAccount, String l_strDisplayTerm, WEB3PLSProfitLossSpecsUnit l_balanceUnit) throws WEB3BaseException
    {
         final String STR_METHOD_NAME = "createCarryoverBalanceData(WEB3GentradeMainAccount, String, WEB3PLSProfitLossSpecsUnit)";
         log.entering(STR_METHOD_NAME);

         // 1.1  get累計損益(顧客, String)
         WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
         double  l_dblTotalProlossAmount = l_historyTradeHistoryDataManager.getTotalProlossAmount(l_mainAccount, l_strDisplayTerm);

         //1.2 get損益明細一覧(顧客, String, String, Date)
         List  l_lstProfitLossSpec  = l_historyTradeHistoryDataManager.getProfitLossSpecList(l_mainAccount, WEB3PlsBvsDetailOrderRecDef.PAYMENT_REC, l_strDisplayTerm, l_balanceUnit.workDate);
         int l_listProfitLossSpecSize = 0;
         if (l_lstProfitLossSpec != null)
         {
             l_listProfitLossSpecSize = l_lstProfitLossSpec.size();
         }

         //1.3 取得した入出金レコード(=損益明細Params)数分Loop処理
         double  l_dblTotalCollectTaxAmount = 0;  //徴収税額
         double  l_dblTotalCollectTaxNAmount = 0; //徴収税額(国税)
         double  l_dblTotalCollectTaxLAmount = 0; //徴収税額(地方税)

         for (int i = 0; i < l_listProfitLossSpecSize; i++)
         {
              l_dblTotalCollectTaxAmount += ((ProfitLossSpecParams)l_lstProfitLossSpec.get(i)).getCollectTaxAmount();
              l_dblTotalCollectTaxNAmount += ((ProfitLossSpecParams)l_lstProfitLossSpec.get(i)).getCollectTaxNAmount();
              l_dblTotalCollectTaxLAmount += ((ProfitLossSpecParams)l_lstProfitLossSpec.get(i)).getCollectTaxLAmount();
         }

         //1.4 損益明細情報( )
         WEB3PLSProfitLossSpecsUnit  l_web3PLSProfitLossSpecsUnit = new WEB3PLSProfitLossSpecsUnit();

         //1.5 プロパティセット
         //損益明細レコード区分   ＝　@"00：繰越残高"
         l_web3PLSProfitLossSpecsUnit.prolossRecDiv = WEB3PlsBvsCarryoverBalanceRecDef.CARRYOVER_BALANCE_REC;
         //累計損益         ＝　@get累計損益()の戻り値
         l_web3PLSProfitLossSpecsUnit.totalProlossAmount = WEB3StringTypeUtility.formatNumber(l_dblTotalProlossAmount);
         //徴収税額         ＝　@パラメータ.残高情報.徴収税額 - 入出金レコードの徴収税額の合計
         l_web3PLSProfitLossSpecsUnit.collectTaxAmount = WEB3StringTypeUtility.formatNumber(Double.parseDouble(l_balanceUnit.collectTaxAmount) - l_dblTotalCollectTaxAmount);
         //徴収税額(国税)     ＝　@パラメータ.残高情報.徴収税額(国税) - 入出金レコードの徴収税額(国税)の合計
         l_web3PLSProfitLossSpecsUnit.collectTaxNAmount = WEB3StringTypeUtility.formatNumber(Double.parseDouble(l_balanceUnit.collectTaxNAmount) - l_dblTotalCollectTaxNAmount);
         //徴収税額(地方税)        ＝　@パラメータ.残高情報.徴収税額(地方税) - 入出金レコードの徴収税額(地方税)の合計
         l_web3PLSProfitLossSpecsUnit.collectTaxLAmount = WEB3StringTypeUtility.formatNumber(Double.parseDouble(l_balanceUnit.collectTaxLAmount) - l_dblTotalCollectTaxLAmount);

         log.exiting(STR_METHOD_NAME);
         return l_web3PLSProfitLossSpecsUnit;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）[表示期間]を検索条件文字列にセットする。<BR>
     * ※パラメータ.表示期間From != null & パラメータ.表示期間To != nullの場合に実施する<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and calc_date >= to_date(?, 'YYYYMMDD') " <BR>
     * 　@　@検索条件文字列 += "and calc_date <= to_date(?, 'YYYYMMDD') "<BR>
     * <BR>
     * ２）[銘柄コード]を検索条件文字列にセットする。<BR>
     * ※パラメータ.銘柄コード != nullの場合に実施する <BR>
     * <BR>
     * 　@　@検索条件文字列 += "and product_code = ? "  <BR>
     * <BR>
     * ３）[商品区分]を検索条件文字列にセットする。<BR>
     * ※パラメータ.商品区分 != nullの場合に実施する <BR>
     * <BR>
     * 　@パラメータ.商品区分が、<BR>
     * <BR>
     * 　@[”A:全商品”の場合]<BR>
     * 　@　@検索条件文字列 += "and rec_div in (?, ?) "<BR>
     * <BR>
     * 　@[”B:現物・信用”の場合]<BR>
     * 　@　@検索条件文字列 += "and rec_div = ? "<BR>
     * 　@　@検索条件文字列 += "and commodity_div in (?, ?, ?, ?) "<BR>
     * <BR>
     * 　@[”C:現物”の場合]<BR>
     * 　@　@検索条件文字列 += "and rec_div = ? "<BR>
     * 　@　@検索条件文字列 += "and commodity_div in (?, ?, ?) "<BR>
     * <BR>
     * 　@[”D:信用”または”L:債券”の場合]<BR>
     * 　@　@検索条件文字列 += "and rec_div = ? " <BR>
     * 　@　@検索条件文字列 += "and commodity_div = ? "<BR>
     * <BR>
     * 　@[”H:投信”または”K:外国株式”の場合] <BR>
     * 　@　@検索条件文字列 += "and rec_div = ? " <BR>
     * 　@　@検索条件文字列 += "and commodity_div in (?, ?) "<BR>
     * <BR>
     * 　@[”I:入出金”の場合]<BR>
     * 　@　@検索条件文字列 += "and rec_div = ? "<BR>
     * <BR>
     * ４）作成した検索条件文字列を返却する。<BR>
     * @@param l_datListStartDate - (表示期間From)<BR>
     * 表示期間From<BR>
     * (形式：YYYYMMDD)<BR>
     * @@param l_datListEndDate - (表示期間To)<BR>
     * 表示期間To<BR>
     * (形式：YYYYMMDD)<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strCommodityType - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String createQueryString(
        Date l_datListStartDate, Date l_datListEndDate,
        String l_strProductCode, String l_strCommodityType)
    {
        final String STR_METHOD_NAME = " createQueryString(Date, Date, String, String)";

        log.entering(STR_METHOD_NAME);

        StringBuffer l_strQueryString = new StringBuffer();

        //１）[表示期間]を検索条件文字列にセットする。
        //※パラメータ.表示期間From != null & パラメータ.表示期間To != nullの場合に実施する
        //
        //　@　@検索条件文字列 += "and calc_date >= to_date(?, 'YYYYMMDD') "
        //　@　@検索条件文字列 += "and calc_date <= to_date(?, 'YYYYMMDD') "

        if (l_datListStartDate != null && l_datListEndDate != null)
        {
            l_strQueryString.append("and calc_date >= to_date(?, 'YYYYMMDD') ");
            l_strQueryString.append("and calc_date <= to_date(?, 'YYYYMMDD') ");
        }

        //２）[銘柄コード]を検索条件文字列にセットする.
        //※パラメータ.銘柄コード != nullの場合に実施する
        //
        //　@　@検索条件文字列 += "and product_code = ? "
        if (l_strProductCode != null)
        {
            l_strQueryString.append("and product_code = ? ");
        }

        //３）[商品区分]を検索条件文字列にセットする。
        //※パラメータ.商品区分 != nullの場合に実施する
        if (l_strCommodityType != null)
        {
            //   　@パラメータ.商品区分が、
            //   　@[”A:全商品”の場合]
            //   　@　@検索条件文字列 += "and rec_div in (?, ?) "
            if (WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div in (?, ?) ");
            }

            //   　@[”B:現物・信用”の場合]
            //   　@　@検索条件文字列 += "and rec_div = ? "
            //   　@　@検索条件文字列 += "and commodity_div in (?, ?, ?, ?) "
            if (WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
                l_strQueryString.append("and commodity_div in (?, ?, ?, ?) ");
            }

            //   　@[”C:現物”の場合]
            //   　@　@検索条件文字列 += "and rec_div = ? "
            //   　@　@検索条件文字列 += "and commodity_div in (?, ?, ?) "
            if (WEB3TradeHistoryProductDivDef.EQUITY.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
                l_strQueryString.append("and commodity_div in (?, ?, ?) ");
            }

            //    [”D:信用”または”L:債券”の場合]
            //   　@　@検索条件文字列 += "and rec_div = ? "
            //   　@　@検索条件文字列 += "and commodity_div = ? "
            if (WEB3TradeHistoryProductDivDef.MARGIN.equals(l_strCommodityType)
                || WEB3TradeHistoryProductDivDef.BOND.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
                l_strQueryString.append("and commodity_div = ? ");
            }

            //   　@[”H:投信”または”K:外国株式”の場合]
            //   　@　@検索条件文字列 += "and rec_div = ? "
            //   　@　@検索条件文字列 += "and commodity_div in (?, ?) "
            if (WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(l_strCommodityType)
                || WEB3TradeHistoryProductDivDef.FOREIGN.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
                l_strQueryString.append("and commodity_div in (?, ?) ");
            }

            //   　@[”I:入出金”の場合]
            //   　@　@検索条件文字列 += "and rec_div = ? "
            if (WEB3TradeHistoryProductDivDef.AIO.equals(l_strCommodityType))
            {
                l_strQueryString.append("and rec_div = ? ");
            }
        }

        // ４）作成した検索条件文字列を返却する
        String l_strQueryStringReturn = l_strQueryString.toString();

        log.exiting(STR_METHOD_NAME);
        return l_strQueryStringReturn;
    }

    /**
     * (create検索条件データコンテナ) <BR>
     * 検索条件文字列の"?"部分にセットするパラメータリスト(文字列配列)を作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）[表示期間]をArrayListに追加する。<BR>
     * ※パラメータ.表示期間From != null & パラメータ.表示期間To != nullの場合に実施する <BR>
     * <BR>
     * 　@・パラメータ.表示期間From  <BR>
     * 　@・パラメータ.表示期間To  <BR>
     * <BR>
     * ３）[銘柄コード]をArrayListに追加する。  <BR>
     * ※パラメータ.銘柄コード != nullの場合に実施する <BR>
     * <BR>
     *  ・パラメータ.銘柄コード <BR>
     * <BR>
     * ４）[商品区分]をArrayListに追加する。  <BR>
     * ※パラメータ.商品区分 != nullの場合に実施する <BR>
     * <BR>
     * 　@[”A:全商品”の場合]  <BR>
     * 　@・"20:明細レコード" <BR>
     * 　@・"21:入出金レコード" <BR>
     * <BR>
     * 　@[”B:現物・信用”の場合]  <BR>
     * 　@・"20:明細レコード" <BR>
     * 　@・"10:日株売買"  <BR>
     * 　@・"11:ミニ株売買"  <BR>
     * 　@・"12:ミニ株権利"  <BR>
     * 　@・"15:信用取引"  <BR>
     * <BR>
     * 　@[”C:現物”の場合]  <BR>
     * 　@・"20:明細レコード" <BR>
     * 　@・"10:日株売買"  <BR>
     * 　@・"11:ミニ株売買"  <BR>
     * 　@・"12:ミニ株権利"  <BR>
     * <BR>
     * 　@[”D:信用”の場合]  <BR>
     * 　@・"20:明細レコード" <BR>
     * 　@・"15:信用取引"  <BR>
     * <BR>
     * 　@[”L:債券”の場合]  <BR>
     * 　@・"20:明細レコード" <BR>
     * 　@・"30:日債売買"  <BR>
     * <BR>
     * 　@[”H:投信”の場合]  <BR>
     * 　@・"20:明細レコード" <BR>
     * 　@・"20:投信取引"  <BR>
     * 　@・"21:投信取引"  <BR>
     * <BR>
     * 　@[”K:外国株式”の場合]  <BR>
     * 　@・"20:明細レコード" <BR>
     * 　@・"40:外株売買"  <BR>
     * 　@・"42:外株権利"  <BR>
     * <BR>
     * 　@[”I:入出金”の場合]  <BR>
     * 　@・"21:入出金レコード" <BR>
     * <BR>
     * ５）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_datListStartDate - (表示期間From)<BR>
     * 表示期間From<BR>
     * (形式：YYYYMMDD)<BR>
     * @@param l_datListEndDate - (表示期間To)<BR>
     * 表示期間To<BR>
     * (形式：YYYYMMDD)<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strCommodityType - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * @@return String[]
     * @@roseuid 413C30E102D8
     */
    protected String[] createQueryDataContainer(
        Date l_datListStartDate, Date l_datListEndDate,
        String l_strProductCode, String l_strCommodityType)
        {
            final String STR_METHOD_NAME = " createQueryString(Date, Date, String, String)";

            log.entering(STR_METHOD_NAME);

            //* １）ArrayListを生成する。<BR>
            List l_lisContainers = new ArrayList();

            //２）[表示期間]をArrayListに追加する.
            //※パラメータ.表示期間From != null & パラメータ.表示期間To != nullの場合に実施する
            //　@・パラメータ.表示期間From
            //　@・パラメータ.表示期間To
            if ((l_datListStartDate != null) && (l_datListEndDate != null))
            {
                l_lisContainers.add(WEB3DateUtility.formatDate(l_datListStartDate, "yyyyMMdd"));
                l_lisContainers.add(WEB3DateUtility.formatDate(l_datListEndDate, "yyyyMMdd"));
            }

            //３）[銘柄コード]をArrayListに追加する
            //※パラメータ.銘柄コード != nullの場合に実施する
            //　@・パラメータ.銘柄コード
            if (l_strProductCode != null)
            {
                l_lisContainers.add(l_strProductCode);
            }

            //４）[商品区分]をArrayListに追加する。
            //※パラメータ.商品区分 != nullの場合に実施する
            if (l_strCommodityType != null)
            {
                //[”A:全商品”の場合]
                // ・"20:明細レコード"
                //・"21:入出金レコード"
                if (WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
                }

                // [”B:現物・信用”の場合]
                // ・"20:明細レコード"
                //・"10:日株売買"
                //・"11:ミニ株売買"
                //・"12:ミニ株権利"
                //・"15:信用取引"
                if (WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.JAPANESE_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MINI_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MINI_STOCK_CLAIM);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MARGIN);
                }

                //[”C:現物”の場合]
                //・"20:明細レコード"
                //・"10:日株売買"
                //・"11:ミニ株売買"
                //・"12:ミニ株権利"
                if (WEB3TradeHistoryProductDivDef.EQUITY.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.JAPANESE_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MINI_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MINI_STOCK_CLAIM);
                }

                //[”D:信用”の場合]
                //・"20:明細レコード"
                //・"15:信用取引"
                if (WEB3TradeHistoryProductDivDef.MARGIN.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MARGIN);
                }

                //[”L:債券”の場合]
                // ・"20:明細レコード"
                //・"30:日債売買"
                if (WEB3TradeHistoryProductDivDef.BOND.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.JAPANESE_BOND_TRADE);
                }

                //[”H:投信”の場合]
                //・"20:明細レコード"
                //・"20:投信取引"
                //・"21:投信取引"
                if (WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MUTUAL_FUND_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.MUTUAL_FUND_TRADING);
                }

                //[”K:外国株式”の場合]
                //　@・"20:明細レコード"
                //　@・"40:外株売買"
                //　@・"42:外株権利"
                if (WEB3TradeHistoryProductDivDef.FOREIGN.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.DETAIL_REC);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.FOREIGN_STOCK_TRADE);
                    l_lisContainers.add(WEB3CommodityCodeTypeDef.FOREIGN_STOCK_CLAIM);
                }

                // [”I:入出金”の場合]
                // ・"21:入出金レコード"
                if (WEB3TradeHistoryProductDivDef.AIO.equals(l_strCommodityType))
                {
                    l_lisContainers.add(WEB3ProfitLossRecordDivDef.ORDER_REC);
                }
            }

            //５）生成したArrayList.toArray()の戻り値を返却する。
            String[] l_strQueryDataContainers = new String[l_lisContainers.size()];
            l_lisContainers.toArray(l_strQueryDataContainers);

            log.exiting(STR_METHOD_NAME);
            return l_strQueryDataContainers;
        }

    /**
     * (createソート条件)<BR>
     * ソート条件文字列を作成する。<BR>
     * <BR>
     * ・テーブル列物理名を使用し、以下の通り、ソート条件文字列（order by句）を作成する。<BR>
     * <BR>
     * １） パラメータ.ソート区分 = 01:初期表示 の場合<BR>
     * <BR>
     * 　@１－１） 以下のソート条件を作成する。<BR>
     * 　@　@　@　@・ 計算年月日：　@降順<BR>
     * <BR>
     * 　@　@　@　@ソート条件 = "calc_date desc "<BR>
     * <BR>
     * ２） パラメータ.ソート区分 = 02:初期表示以外 の場合<BR>
     * 　@・引数.ソートキーの要素数分以下の処理を繰り返し、ソート条件文字列を作成する。<BR>
     * <BR>
     * 　@２－１） ソートキーを編集する。<BR>
     * 　@　@２－１－１） キー項目 = 「calcDate：計算年月日」の場合<BR>
     * <BR>
     * 　@　@　@　@ソート条件 = "calc_date "<BR> 
     * <BR>
     * 　@２－２） ソート条件を編集する。<BR>
     * 　@　@２－２－１） 昇順／降順 = 「A：昇順」の場合、<BR>
     * <BR>
     * 　@　@　@　@ソート条件 += "asc "<BR>
     * <BR>
     * 　@　@２－２－２） 昇順／降順 = 「D：降順」の場合、<BR>
     * <BR>
     * 　@　@　@　@ソート条件 += "desc "<BR>
     * <BR>
     * 　@２－３） 「損益明細テーブル.SORT-NO」をソート条件に追加する。<BR>
     * 　@　@２－３－１） 昇順／降順 = 「A：昇順」の場合、<BR>
     * <BR>
     * 　@　@　@　@ソート条件 += ", sort_no asc "<BR>
     * <BR>
     * 　@　@２－２－２） 昇順／降順 = 「D：降順」の場合、<BR>
     * 　@　@　@　@ソート条件 += ", sort_no desc "<BR>
     * <BR>
     * <BR>
     * ３） パラメータ.ソート区分 = 03：ダウンロード の場合<BR>
     * <BR>
     * 　@３－１） 以下のソート条件を作成する。<BR>
     * 　@　@　@　@・ 計算年月日：　@昇順<BR>
     * 　@　@　@　@・ SORT-NO：　@昇順<BR>
     * <BR>
     * 　@　@　@　@ソート条件 = "calc_date asc, sort_no asc "<BR>
     * <BR>
     * <BR>
     * 4） 作成したソート条件文字列を返却する。<BR>
     * @@param l_strSortDiv - (ソート区分)<BR>
     * ソート区分<BR>
     * 　@01：　@初期表示<BR>
     * 　@02：　@初期表示以外<BR>
     * 　@03：　@ダウンロード<BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * 損益明細ソートキーオブジェクト
     * @@return String
     * @@roseuid 413C30E102D8
     */
    protected String createSortCond(String l_strSortDiv,
        WEB3PLSProfitLossSpecsSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            " createSortCond(String l_strSortDiv, WEB3PLSProfitLossSpecsSortKeyUnit[] l_sortKey) ";

        log.entering(STR_METHOD_NAME);

        String l_strSort = "";

        //１） パラメータ.ソート区分 = 01:初期表示 の場合
        if (WEB3TradeHistorySortDivDef.INITIAL_DISPLAY.equals(l_strSortDiv))
        {
            //１－１） 以下のソート条件を作成する.
            //・ 計算年月日：　@降順
            //ソート条件 = "calc_date desc "
            l_strSort = "calc_date desc ";
        }
        //２） パラメータ.ソート区分 = 02:初期表示以外 の場合
        //・引数.ソートキーの要素数分以下の処理を繰り返し、ソート条件文字列を作成する。
        else if (WEB3TradeHistorySortDivDef.INITIAL_DISPLAY_OTHERS.equals(l_strSortDiv))
        {
            if (l_sortKeys != null || l_sortKeys.length !=0)
            {
                for (int i = 0; i < l_sortKeys.length; i++)
                {
                    //２－１） ソートキーを編集する。
                    //２－１－１） キー項目 = 「calcDate：計算年月日」の場合、
                    //ソート条件 = "calc_date "
                    if (WEB3PLSProfitLossSpecsSortKeyItemDef.CALC_DATE.equals(l_sortKeys[i].keyItem))
                    {
                        l_strSort = "calc_date ";
                    }

                    //２－２－１） 昇順／降順 = 「A：昇順」の場合、
                    //ソート条件 += "asc "
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        l_strSort += "asc ";
                    }
                    //２－２－２） 昇順／降順 = 「D：降順」の場合、
                    //ソート条件 += "desc "
                    else
                    {
                        l_strSort += "desc ";
                    }

                    //２－３） 「損益明細テーブル.SORT-NO」をソート条件に追加する。
                    //２－３－１） 昇順／降順 = 「A：昇順」の場合、
                    //ソート条件 += ", sort_no asc "
                    if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                    {
                        l_strSort += ", sort_no asc ";
                    }
                    //２－２－２） 昇順／降順 = 「D：降順」の場合、
                    //ソート条件 += ", sort_no desc "
                    else
                    {
                        l_strSort += ", sort_no desc ";
                    }
                }
            }
        }

        //３） パラメータ.ソート区分 = 03：ダウンロード の場合
        else if (WEB3TradeHistorySortDivDef.DOWNLOAD.equals(l_strSortDiv))
        {
            //３－１） 以下のソート条件を作成する。
            //・ 計算年月日：　@昇順
            //・ SORT-NO：　@昇順
            //ソート条件 = "calc_date asc, sort_no asc "
            l_strSort = "calc_date asc, sort_no asc ";
        }

        //4） 作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strSort;
    }

    /**
     * (get損益明細ファ@イルダウンロード)<BR>
     * 損益明細ファ@イルダウンロード処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(損益明細照会サービス)get損益明細ファ@イルダウンロード」参照<BR>
     * ======================================================= <BR>
     * 　@　@　@　@シーケンス図 :  「(損益明細照会サービス)get損益明細ファ@イルダウンロード」<BR>
     * 　@　@　@　@具体位置 : 1.8 get損益明細件数(顧客, String)<BR>
     * 　@　@　@　@get損益明細件数()メソッドの戻り値 == 0の場合、<BR>
     * 　@　@　@　@検索条件に合致する損益明細データはございません。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag           :  BUSINESS_ERROR_02666       <BR>
     * ======================================================= <BR>
     * <BR>
     * ======================================================= <BR>
     * 　@　@　@　@シーケンス図 :  「(損益明細照会サービス)get損益明細ファ@イルダウンロード」<BR>
     * 　@　@　@　@具体位置 : 1.10 (*)create残高データ()の戻り値 == nullの場合<BR>
     * 　@　@　@　@create残高データ()の戻り値 == nullの場合、<BR>
     * 　@　@　@　@検索条件に合致する損益明細データはございません。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag           :  BUSINESS_ERROR_02666       <BR>
     * ======================================================= <BR>
     * <BR>
     * ======================================================= <BR>
     * 　@　@　@　@シーケンス図 :  「(損益明細照会サービス)get損益明細ファ@イルダウンロード」<BR>
     * 　@　@　@　@具体位置 : 1.14  損益明細行数が"getプリファ@レンス()の戻り値"を超えた場合、<BR>
     * 　@　@　@　@例外（BUSINESS_ERROR_01957）をthrowする。<BR>
     * 　@　@　@　@損益明細行数が"getプリファ@レンス()の戻り値"を超えた場合、<BR>
     * 　@　@　@　@該当する件数がダウンロード件数を超えています。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag           :  BUSINESS_ERROR_01957       <BR>
     * ======================================================= <BR>
     * <BR>
     * ======================================================= <BR>
     * 　@　@　@　@シーケンス図 :  「(損益明細照会サービス)get損益明細ファ@イルダウンロード」<BR>
     * 　@　@　@　@具体位置 : 1.15 損益明細行数が"0件"の場合、例外（BUSINESS_ERROR_02666）をthrowする。<BR>
     * 　@　@　@　@損益明細行数が"0件"の場合、<BR>
     * 　@　@　@　@検索条件に合致する損益明細データはございません。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag           :  BUSINESS_ERROR_02666       <BR>
     * ======================================================= <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 損益明細ファ@イルダウンロードリクエストオブジェクト<BR>
     * @@return WEB3PLSProfitLossDownloadResponse
     * @@roseuid 416CFDB50110
     */
    protected WEB3PLSProfitLossDownloadResponse getProfitLossDownload(
        WEB3PLSProfitLossDownloadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProfitLossDownload(WEB3PLSProfitLossDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();

        //1.2 validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.4 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (
            WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.5 取引カレンダコンテキストの設定
        //  ・取引カレンダコンテキスト.受付時間区分 = "30：ダウンロード"
        //  ※上記以外は既存値。
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
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

        //1.8get損益明細件数(顧客, String)
        //顧客：　@getMainAccount()の戻り値
        //処理区分：　@リクエストデータ.処理区分

        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager =
            new WEB3HistoryTradeHistoryDataManager();

        int l_intProfitLossSpecCount =
            l_historyTradeHistoryDataManager.getProfitLossSpecCount(
                l_mainAccount,
                l_request.transactionDiv);

        if (l_intProfitLossSpecCount == 0)
        {
            log.debug("get損益明細件数 == 0の場合throwする例外");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02666,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "検索条件に合致する損益明細データはございません。");
        }

        //1.9create残高データ(顧客)
        WEB3PLSProfitLossSpecsUnit l_plsProfitLossSpecsUnit = this.createBalanceData(l_mainAccount);

        //1.10(*)create残高データ()の戻り値 == nullの場合
        //throwする例外
        //BUSINESS_ERROR_02666
        //[検索条件に合致する損益明細データはございません。]
        if (l_plsProfitLossSpecsUnit == null)
        {
            log.debug("create残高データ()の戻り値 == nullの場合throwする例外");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02666,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "検索条件に合致する損益明細データはございません。");
        }

        //1.11 (*)リクエストデータ.処理区分 == null(2ヶ月表示) の場合
        WEB3PLSProfitLossSpecsUnit l_createCarryoverBalanceData = null;
        List l_lisProfitLossSpecList = null;
        if (l_request.transactionDiv == null)
        {
            //1.11.1.create繰越残高データ(顧客, String, 損益明細情報)
            //顧客：　@getMainAccount()の戻り値
            //表示期間：　@リクエストデータ.表示期間
            //残高情報：　@create残高データ()の戻り値
            l_createCarryoverBalanceData =
                this.createCarryoverBalanceData(
                    l_mainAccount,
                    l_request.displayTerm,
                    l_plsProfitLossSpecsUnit);

            //1.11.2get損益明細一覧(顧客, String, String, Date)
            //顧客：　@getMainAccount()の戻り値
            //取得対象レコード区分：　@"3：明細&入出金レコード"
            //表示期間：　@リクエストデータ.表示期間
            //作業年月日：　@null
            l_lisProfitLossSpecList = l_historyTradeHistoryDataManager.getProfitLossSpecList(
                l_mainAccount,
                WEB3PlsBvsDetailOrderRecDef.DETAIL_ORDER_REC,
                l_request.displayTerm,
                null);
        }

        //1.12(*)リクエストデータ.処理区分 == 01(18ヶ月表示) の場合
        if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(l_request.transactionDiv))
        {
            //1.12.1create検索条件文字列(Date, Date, String, String)
            //表示期間From：　@リクエストデータ.表示期間From
            //表示期間To：　@リクエストデータ.表示期間To
            //銘柄コード：　@リクエストデータ.銘柄コード
            //商品区分：　@リクエストデータ.商品区分
            Date l_datListStartDate = WEB3DateUtility.getDate(l_request.listStartDate, "yyyyMMdd");
            Date l_datListEndDate = WEB3DateUtility.getDate(l_request.listEndDate, "yyyyMMdd");
            String l_strQueryString = this.createQueryString(
                l_datListStartDate,
                l_datListEndDate,
                l_request.productCode,
                l_request.commodityType);

            //1.12.2create検索条件データコンテナ(Date, Date, String, String)
            //表示期間From：　@リクエストデータ.表示期間From
            //表示期間To：　@リクエストデータ.表示期間To
            //銘柄コード：　@リクエストデータ.銘柄コード
            //商品区分：　@リクエストデータ.商品区分
            String[] l_strQueryDataContainers = this.createQueryDataContainer(
                l_datListStartDate,
                l_datListEndDate,
                l_request.productCode,
                l_request.commodityType);

            //1.12.3.createソート条件(String, 損益明細ソートキー)
            //ソート条件を作成する。
            //ソート区分：　@03：ダウンロード
            //ソートキー：　@null
            String l_strSortCond = this.createSortCond(
                WEB3TradeHistorySortDivDef.DOWNLOAD, null);

            //1.12.4get損益明細一覧(顧客, String, Object[], String)
            //顧客：　@getMainAccount()の戻り値
            //検索条件文字列：　@create検索受検文字列()の戻り値
            //検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値
            //ソート条件：　@createソート条件()の戻り値
            l_lisProfitLossSpecList = l_historyTradeHistoryDataManager.getProfitLossSpecList(
                l_mainAccount,
                l_strQueryString,
                l_strQueryDataContainers,
                l_strSortCond);
        }

        //1.13getプリファ@レンス(String)
        //設定名称：　@固定文言"DL_REC_COUNT_PROFITLOSSLIST"
        String l_strPreferences = this.getPreferences(
            WEB3SystemPreferencesNameDef.DL_REC_COUNT_PROFITLOSSLIST);

        //1.14損益明細行数が"getプリファ@レンス()の戻り値"を超えた場合、
        //例外（BUSINESS_ERROR_01957）をthrowする。
        //    ・throwする例外
        //  　@　@BUSINESS_ERROR_01957
        //  　@　@[該当する件数がダウンロード件数を超えています。]
        int l_intListCount = 0;
        if (l_lisProfitLossSpecList != null)
        {
            l_intListCount = l_lisProfitLossSpecList.size();
        }

        //データ不整合の場合
        if (!WEB3StringTypeUtility.isInteger(l_strPreferences))
        {
            log.debug("データ不整合の場合例外をthrowする");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        if (l_intListCount > Integer.parseInt(l_strPreferences))
        {
            log.debug("損益明細行数がgetプリファ@レンス()の戻り値を超えた場合例外をthrowする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01957,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する件数がダウンロード件数を超えています。");
        }

        //1.15損益明細行数が"0件"の場合、例外（BUSINESS_ERROR_02666）をthrowする。
        // ・throwする例外
        // BUSINESS_ERROR_02666
        // [該当する件数がダウンロード件数を超えています。]
        if (l_intListCount == 0)
        {
            log.debug("損益明細行数が0件の場合、例外（BUSINESS_ERROR_02666）をthrowする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02666,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "検索条件に合致する損益明細データはございません。");
        }

        //1.16損益明細CSV( )
        WEB3PLSProfitLossSpecsCsv l_pLSProfitLossSpecsCsv = new WEB3PLSProfitLossSpecsCsv();

        //1.16.1createキーヘッダ( )
        l_pLSProfitLossSpecsCsv.createKeyHeader();

        //1.16.2createカラムヘッダ( )
        l_pLSProfitLossSpecsCsv.createColumnHeader();

        //1.17(*)リクエストデータ.処理区分 == null(2ヶ月表示) &
        //create繰越残高データ() != nullの場合
        if (l_request.transactionDiv == null
            && l_createCarryoverBalanceData != null)
        {
            //1.17.1add明細行( )
            int l_intRowNumber = l_pLSProfitLossSpecsCsv.addRow();

            //1.17.2set計算日(int, String)
            //行番号：　@add明細行()の戻り値
            //計算日：　@null
            l_pLSProfitLossSpecsCsv.setCalcDate(
                l_intRowNumber,
                " ");

            //1.17.3set受渡日(int, Date)
            //行番号：　@add明細行()の戻り値
            //受渡日：　@null
            l_pLSProfitLossSpecsCsv.setDeliveryDate(
                l_intRowNumber,
                null);

            //1.17.4set（商品）適用(int, String, String, String)
            //行番号：　@add明細行()の戻り値
            //商品：　@null
            //適用コード：　@null
            //会社コード：　@null
            l_pLSProfitLossSpecsCsv.setFundTypeApplication(
                l_intRowNumber,
                null,
                null,
                null);

            //1.17.5set銘柄名(String, String, String, String, String, String)
            //行番号：　@add明細行()の戻り値
            //レコード区分：　@create繰越残高データ()の戻り値.レコード区分
            //返還金区分：　@null
            //備考：　@null
            //銘柄名：　@null
            //会社コード：　@getMainAccount()の戻り値.証券会社コード
            l_pLSProfitLossSpecsCsv.setProductName(
                l_intRowNumber + "",
                l_createCarryoverBalanceData.prolossRecDiv,
                null,
                null,
                null,
                l_mainAccount.getInstitution().getInstitutionCode());

            //1.17.6set長短(int, String, String)
            //行番号：　@add明細行()の戻り値
            //長短等区分：　@null
            //会社コード：　@null
            l_pLSProfitLossSpecsCsv.setTerm(
                l_intRowNumber,
                null,
                null);

            //1.17.7set数量(String, String)
            //行番号：　@add明細行()の戻り値
            //数量：　@null
            l_pLSProfitLossSpecsCsv.setQuantity(
                l_intRowNumber + "",
                null);

            //1.17.8set譲渡日(int, Date)
            //行番号：　@add明細行()の戻り値
            //譲渡日：　@null
            l_pLSProfitLossSpecsCsv.setPassDate(
                l_intRowNumber,
                null);

            //1.17.9set譲渡金額(int, String)
            //行番号：　@add明細行()の戻り値
            //譲渡金額：　@null
            l_pLSProfitLossSpecsCsv.setPassAmount(
                l_intRowNumber,
                null);

            //1.17.10set取得日(int, Date)
            //行番号：　@add明細行()の戻り値
            //取得日：　@null
            l_pLSProfitLossSpecsCsv.setGetDate(
                l_intRowNumber,
                null);

            //1.17.11set取得費等(int, String)
            //行番号：　@add明細行()の戻り値
            //取得費等：　@null
            l_pLSProfitLossSpecsCsv.setGetAmount(
                l_intRowNumber,
                null);

            //1.17.12set損益(int, String)
            //行番号：　@add明細行()の戻り値
            //損益：　@null
            l_pLSProfitLossSpecsCsv.setProlossAmount(
                l_intRowNumber,
                null);

            //1.17.13set累計損益(int, String)
            //行番号：　@add明細行()の戻り値
            //累計損益：　@create繰越残高データ()の戻り値.累計損益
            l_pLSProfitLossSpecsCsv.setTotalProlossAmount(
                l_intRowNumber,
                l_createCarryoverBalanceData.totalProlossAmount);

            //1.17.14set課税対象額(int, String)
            //行番号：　@add明細行()の戻り値
            //課税対象額：　@null
            l_pLSProfitLossSpecsCsv.setTaxableAmount(
                l_intRowNumber,
                null);

            //1.17.15set徴収税額(int, String)
            //行番号：　@add明細行()の戻り値
            //徴収税額：　@create繰越残高データ()の戻り値.徴収税額
            l_pLSProfitLossSpecsCsv.setCollectTaxAmount(
                l_intRowNumber,
                l_createCarryoverBalanceData.collectTaxAmount);

            //1.17.16set徴収税額（国税）(int, String)
            //行番号：　@add明細行()の戻り値
            //徴収税額（国税）：　@create繰越残高データ()の戻り値.徴収税額（国税）
            l_pLSProfitLossSpecsCsv.setCollectTaxNAmount(
                l_intRowNumber,
                l_createCarryoverBalanceData.collectTaxNAmount);

            //1.17.17set徴収税額（地方税）(int, String)
            //行番号：　@add明細行()の戻り値
            //徴収税額（地方税）：　@create繰越残高データ()の戻り値.徴収税額（地方税）
            l_pLSProfitLossSpecsCsv.setCollectTaxLAmount(
                l_intRowNumber,
                l_createCarryoverBalanceData.collectTaxLAmount);
        }

        //1.18(*)取得した『明細・入出金データ』の件数分Loop処理
        for (int i = 0; i < l_intListCount; i++)
        {
            ProfitLossSpecRow l_profitLossSpecRow = (ProfitLossSpecRow)l_lisProfitLossSpecList.get(i);
            if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv())
                || WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
            {
                //1.18.1add明細行( )
                int l_intRowNumber = l_pLSProfitLossSpecsCsv.addRow();

                //1.18.2set計算日(int, String)
                //行番号：　@add明細行()の戻り値
                //計算日：　@get損益明細一覧()の戻り値[index].計算日
                l_pLSProfitLossSpecsCsv.setCalcDate(
                    l_intRowNumber,
                    WEB3DateUtility.formatDate(l_profitLossSpecRow.getCalcDate(), "yyyy/MM/dd"));

                //1.18.3set受渡日(int, Date)
                //行番号：　@add明細行()の戻り値
                //受渡日：　@get損益明細一覧()の戻り値[index].受渡日
                l_pLSProfitLossSpecsCsv.setDeliveryDate(
                    l_intRowNumber,
                    l_profitLossSpecRow.getDeliveryDate());

                //1.18.4set（商品）適用(int, String, String, String)
                //行番号：　@add明細行()の戻り値
                //商品：　@get損益明細一覧()の戻り値[index].商品 (*1)
                //適用コード：　@get損益明細一覧()の戻り値[index].適用コード (*1)
                //会社コード：　@getMainAccount()の戻り値.証券会社コード (*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //    21：入出金レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setFundTypeApplication(
                        l_intRowNumber,
                        null,
                        null,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setFundTypeApplication(
                        l_intRowNumber,
                        l_profitLossSpecRow.getCommodityDiv(),
                        l_profitLossSpecRow.getApplicationCode(),
                        l_mainAccount.getInstitution().getInstitutionCode());
                }

                //1.18.5set銘柄名(String, String, String, String, String, String)
                //行番号：　@add明細行()の戻り値
                //レコード区分：　@get損益明細一覧()の戻り値[index].レコード区分
                //返還金区分：　@get損益明細一覧()の戻り値[index].返還金区分
                //備考：　@get損益明細一覧()の戻り値[index].備考
                //銘柄名：　@get損益明細一覧()の戻り値[index].銘柄名
                //会社コード：　@getMainAccount()の戻り値.証券会社コード
                l_pLSProfitLossSpecsCsv.setProductName(
                    l_intRowNumber + "",
                    l_profitLossSpecRow.getRecDiv(),
                    l_profitLossSpecRow.getReturnDiv(),
                    l_profitLossSpecRow.getRemark(),
                    l_profitLossSpecRow.getStandardName(),
                    l_mainAccount.getInstitution().getInstitutionCode());

                //1.18.6set長短(int, String, String)
                //行番号：　@add明細行()の戻り値
                //長短等区分：　@get損益明細一覧()の戻り値[index].長短等区分(*1)
                //会社コード：　@getMainAccount()の戻り値.証券会社コード (*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分
                //= 21：入出金レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setTerm(
                        l_intRowNumber,
                        null,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setTerm(
                        l_intRowNumber,
                        l_profitLossSpecRow.getTermDiv(),
                        l_mainAccount.getInstitution().getInstitutionCode());
                }

                //1.18.7set数量(String, String)
                //行番号：　@add明細行()の戻り値
                //数量：　@get損益明細一覧()の戻り値[index].枚数 / 100000 (*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //21：入出金レコード]の場合には null を設定する.
                String l_strQuantity =
                    WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getQuantity() / 100000);
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_strQuantity = null;
                }

                l_pLSProfitLossSpecsCsv.setQuantity(
                    l_intRowNumber + "",
                    l_strQuantity);

                //1.18.8set譲渡日(int, Date)
                //行番号：　@add明細行()の戻り値
                //譲渡日：　@get損益明細一覧()の戻り値[index].譲渡日(*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //21：入出金レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setPassDate(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setPassDate(
                        l_intRowNumber,
                        l_profitLossSpecRow.getPassDate());
                }

                //1.18.9set譲渡金額(int, String)
                //行番号：　@add明細行()の戻り値
                //譲渡金額：　@get損益明細一覧()の戻り値[index].譲渡金額 (*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //21：入出金レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setPassAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setPassAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getPassAmount()));
                }

                //1.18.10set取得日(int, Date)
                //行番号：　@add明細行()の戻り値
                //取得日：　@get損益明細一覧()の戻り値[index].取得日 (*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //21：入出金レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setGetDate(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setGetDate(
                        l_intRowNumber,
                        l_profitLossSpecRow.getGetDate());
                }

                //1.18.11set取得費等(int, String)
                //行番号：　@add明細行()の戻り値
                //取得費等：　@get損益明細一覧()の戻り値[index].取得費等 (*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //21：入出金レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setGetAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setGetAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getGetAmount()));
                }

                //1.18.12set損益(int, String)
                //行番号：　@add明細行()の戻り値
                //損益：　@get損益明細一覧()の戻り値[index].損益 (*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 = 21：入出金レコード]　@かつ、
                //[get損益明細一覧()の戻り値[index].返還金区分 = 1：返還金]　@の場合には
                //引数.損益には null を設定する。
                String l_strProlossAmount = WEB3StringTypeUtility.formatNumber(
                    l_profitLossSpecRow.getProlossAmount());

                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv())
                    && WEB3ReturnDivDef.RETURN.equals(l_profitLossSpecRow.getReturnDiv()))
                {
                    l_strProlossAmount = null;
                }
                l_pLSProfitLossSpecsCsv.setProlossAmount(
                    l_intRowNumber,
                    l_strProlossAmount);

                //1.18.13set累計損益(int, String)
                //行番号：　@add明細行()の戻り値
                //累計損益：　@get損益明細一覧()の戻り値[index].累計損益 (*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //21：入出金レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setTotalProlossAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setTotalProlossAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getTotalProlossAmount()));
                }

                //1.18.14set課税対象額(int, String)
                //行番号：　@add明細行()の戻り値
                //課税対象額：　@get損益明細一覧()の戻り値[index].課税対象額 (*1)
                //
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 = 21：入出金レコード]　@かつ
                //[get損益明細一覧()の戻り値[index].返還金区分 = 1：返還金]　@の場合には、
                //引数.課税対象額には null を設定する。
                //(*2)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //20：明細レコード]の場合には null を設定する。
                String l_strTaxableAmount = WEB3StringTypeUtility.formatNumber(
                    l_profitLossSpecRow.getTaxableAmount());

                if ((WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_profitLossSpecRow.getRecDiv())
                    && WEB3ReturnDivDef.RETURN.equals(l_profitLossSpecRow.getReturnDiv()))
                    || WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv()))
                    {
                        l_strTaxableAmount = null;
                    }

                l_pLSProfitLossSpecsCsv.setTaxableAmount(
                    l_intRowNumber,
                    l_strTaxableAmount);

                //1.18.15set徴収税額(int, String)
                //行番号：　@add明細行()の戻り値
                //徴収税額：　@get損益明細一覧()の戻り値[index].徴収税額 (*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //20：明細レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getCollectTaxAmount()));
                }

                //1.18.16set徴収税額（国税）(int, String)
                //行番号：　@add明細行()の戻り値
                //徴収税額（国税）：　@get損益明細一覧()の戻り値[index].徴収税額（国税）(*1)
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //20：明細レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxNAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxNAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getCollectTaxNAmount()));
                }

                //1.18.17set徴収税額（地方税）(int, String)
                //行番号：　@add明細行()の戻り値
                //徴収税額（地方税）：　@get損益明細一覧()の戻り値[index].徴収税額（地方税）
                //(*1)
                //[get損益明細一覧()の戻り値[index].レコード区分 =
                //20：明細レコード]の場合には null を設定する。
                if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_profitLossSpecRow.getRecDiv()))
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxLAmount(
                        l_intRowNumber,
                        null);
                }
                else
                {
                    l_pLSProfitLossSpecsCsv.setCollectTaxLAmount(
                        l_intRowNumber,
                        WEB3StringTypeUtility.formatNumber(l_profitLossSpecRow.getCollectTaxLAmount()));
                }
            }
        }

        //1.19add明細行( )
        int l_intRowNumber = l_pLSProfitLossSpecsCsv.addRow();

        //1.20set計算日(int, String)
        //行番号：　@add明細行()の戻り値
        //計算日：　@null
        l_pLSProfitLossSpecsCsv.setCalcDate(
            l_intRowNumber,
            " ");

        //1.21set受渡日(int, Date)
        //行番号：　@add明細行()の戻り値
        //受渡日：　@null
        l_pLSProfitLossSpecsCsv.setDeliveryDate(
            l_intRowNumber,
            null);

        //1.22set（商品）適用(int, String, String, String)
        //行番号：　@add明細行()の戻り値
        //商品：　@null
        //適用コード：　@null
        //会社コード：　@null
        l_pLSProfitLossSpecsCsv.setFundTypeApplication(
            l_intRowNumber,
            null,
            null,
            null);

        //1.23set銘柄名(String, String, String, String, String, String)
        //行番号：　@add明細行()の戻り値
        //レコード区分：　@create残高データ()の戻り値.レコード区分
        //返還金区分：　@null
        //備考：　@null
        //銘柄名：　@null
        //会社コード：　@getMainAccount()の戻り値.証券会社コード
        l_pLSProfitLossSpecsCsv.setProductName(
            l_intRowNumber + "",
            l_plsProfitLossSpecsUnit.prolossRecDiv,
            null,
            null,
            null,
            l_mainAccount.getInstitution().getInstitutionCode());

        //1.24set長短(int, String, String)
        //行番号：　@add明細行()の戻り値
        //長短等区分：　@null
        //会社コード：　@null
        l_pLSProfitLossSpecsCsv.setTerm(
            l_intRowNumber,
            null,
            null);

        //1.25set数量(String, String)
        //行番号：　@add明細行()の戻り値
        //数量：　@null
        l_pLSProfitLossSpecsCsv.setQuantity(
            l_intRowNumber + "",
            null);

        //1.26set譲渡日(int, Date)
        //行番号：　@add明細行()の戻り値
        //譲渡日：　@null
        l_pLSProfitLossSpecsCsv.setPassDate(
            l_intRowNumber,
            null);

        //1.27set譲渡金額(int, String)
        //行番号：　@add明細行()の戻り値
        //譲渡金額：　@create残高データ()の戻り値.譲渡金額
        l_pLSProfitLossSpecsCsv.setPassAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.passAmount);

        //1.28set取得日(int, Date)
        //行番号：　@add明細行()の戻り値
        //取得日：　@null
        l_pLSProfitLossSpecsCsv.setGetDate(
            l_intRowNumber,
            null);

        //1.29set取得費等(int, String)
        //行番号：　@add明細行()の戻り値
        //取得費等：　@create残高データ()の戻り値.取得費等
        l_pLSProfitLossSpecsCsv.setGetAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.getAmount);

        //1.30set損益(int, String)
        //行番号：　@add明細行()の戻り値
        //損益：　@null
        l_pLSProfitLossSpecsCsv.setProlossAmount(
            l_intRowNumber,
            null);

        //1.31set累計損益(int, String)
        //行番号：　@add明細行()の戻り値
        //累計損益：　@create残高データ()の戻り値.累計損益
        l_pLSProfitLossSpecsCsv.setTotalProlossAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.totalProlossAmount);

        //1.32set課税対象額(int, String)
        //行番号：　@add明細行()の戻り値
        //課税対象額：　@null
        l_pLSProfitLossSpecsCsv.setTaxableAmount(
            l_intRowNumber,
            null);

        //1.33set徴収税額(int, String)
        //行番号：　@add明細行()の戻り値
        //徴収税額：　@create残高データ()の戻り値.徴収税額
        l_pLSProfitLossSpecsCsv.setCollectTaxAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.collectTaxAmount);

        //1.34set徴収税額（国税）(int, String)
        //行番号：　@add明細行()の戻り値
        //徴収税額（国税）：　@create残高データ()の戻り値.徴収税額（国税）
        l_pLSProfitLossSpecsCsv.setCollectTaxNAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.collectTaxNAmount);

        //1.35set徴収税額（地方税）(int, String)
        //行番号：　@add明細行()の戻り値
        //徴収税額（地方税）：　@create残高データ()の戻り値.徴収税額（地方税）
        l_pLSProfitLossSpecsCsv.setCollectTaxLAmount(
            l_intRowNumber,
            l_plsProfitLossSpecsUnit.collectTaxLAmount);

        //1.36 getCSVファ@イル行( )

        String[] l_strCsvFileLines = l_pLSProfitLossSpecsCsv.getCsvFileLines();

        //1.37createResponse( )
        WEB3PLSProfitLossDownloadResponse l_response =
            (WEB3PLSProfitLossDownloadResponse)l_request.createResponse();

        //1.38 (*1)プロパティセット
        //レスポンスデータにプロパティをセットする。
        //ダウンロードファ@イル  ＝　@getCSVファ@イル行()の戻り値
        l_response.downloadFile = l_strCsvFileLines;

        //現在日時        ＝　@TradingSystem.getSystemTimestamp()の戻り値
        l_response.currentDate = WEB3DateUtility.toDay(GtlUtils.getTradingSystem().getSystemTimestamp());

        log.exiting(STR_METHOD_NAME);
        return l_response;
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
     * @@param l_strPreferences - (設定名称)<BR>
     * システムプリファ@レンス名称<BR>
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
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                SystemPreferencesRow.TYPE,
                l_strWhere.toString(),
                null,
                null,
                l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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

        //２）取得したシステムプリファ@レンステーブルのレコードの設定値を返却する。
        String l_strValue = null;
        if (l_lisRecords.size() != 0)
        {
            SystemPreferencesRow l_preferencesRow = (SystemPreferencesRow)l_lisRecords.get(0);

            l_strValue = l_preferencesRow.getValue();

        }

        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }

}
@
