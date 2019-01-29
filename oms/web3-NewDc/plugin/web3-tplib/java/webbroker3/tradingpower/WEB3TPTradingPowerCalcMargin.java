head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerCalcMargin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 資産余力情報<信用顧客>(WEB3TPTradingPowerCalcMargin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 nakazato(ACT) 新規作成
                 : 2007/01/22 謝　@旋 (中訊) 仕様変更 計算式書（No.006 - 007）
                   2007/01/22 李木子 (中訊) 仕様変更 計算式書（No.005）
                   2007/01/22 李木子 (中訊) 指摘対応
Revesion History : 2008/04/01 崔遠鵬 (中訊) モデルNo.266
Revesion History : 2008/09/10 張少傑 (中訊) 計算式書No.018
Revesion History : 2008/09/10 劉剣 (中訊) モデルNo.300
Revesion History : 2008/10/21 孟亞南 (中訊) モデルNo.328 計算式書No.019,020
Revesion History : 2008/11/26 劉剣 (中訊) モデルNo.375
*/
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.data.TpCalcResultMarginDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPActPayCheckDef;
import webbroker3.tradingpower.define.WEB3TPActualReceiptMargincallPowerCheckDef;
import webbroker3.tradingpower.define.WEB3TPAdddepositCheckDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.util.WEB3LogUtility;

/**
 * （資産余力情報<信用顧客>）
 * 
 * 余力更新結果<信用顧客>をより、各種取引可能額を計算するクラス
 */
public class WEB3TPTradingPowerCalcMargin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcMargin.class);

    /**
     * （余力計算結果Params<信用顧客>）
     */
    protected TpCalcResultMarginParams calcResultMargin;

    /**
     * （余力計算結果詳細Params<信用顧客>）
     */
    protected TpCalcResultMarginDetailParams calcResultDetailMargin;

    /**
     * （使用可能現金情報）
     */
    protected WEB3TPActualAccountBalanceInfo actualBalanceInfo = null;

    /**
     * （余力計算条件）
     */
    protected WEB3TPCalcCondition calcCondition;

    /**
     * （当日株式予約注文単位一覧） 
     * 
     * ※当日(T+0)以降の株式予約注文単位テーブルRowオブジェクトのリスト 
     */
    protected List todaysRsvEqOrderUnits = null;

    /**
     * @@roseuid 410DF85F0091
     */
    public WEB3TPTradingPowerCalcMargin()
    {

    }

    /**
     * （コンストラクタ）<BR>
     * <BR>
     * 引数を属性にセットする。<BR>
     * <BR>
     * １）引数.余力計算結果<信用顧客>より、余力計算結果Params<信用顧客>オブジェクトを取得し、<BR>
     * 　@this.余力計算結果Params<信用顧客>にセットする。<BR>
     * <BR>
     * ２）引数.余力計算結果<信用顧客>より、余力計算結果詳細Params<信用顧客>オブジェクトを取得し、<BR>
     * 　@this.余力計算結果詳細Params<信用顧客>にセットする。<BR>
     * <BR>
     * ３）引数.余力計算条件を、this.余力計算条件にセットする。<BR>
     * <BR>
     * @@param l_calcResult - （余力計算結果）
     * @@param l_calcCondition - （余力計算条件）
     */
    public WEB3TPTradingPowerCalcMargin(List l_calcResult, WEB3TPCalcCondition l_calcCondition)
    {
        /*
         * Listより余力計算結果<信用顧客>オブジェクト、余力計算結果詳細<信用顧客>オブジェクト
         * を取得しプロパティにセットする。
         */
        for (Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object)l_iter.next();

            if (l_element instanceof TpCalcResultMarginParams)
            {
                TpCalcResultMarginParams l_params = (TpCalcResultMarginParams)l_element;
                this.setCalcResultMargin(l_params);
            }
            else if (l_element instanceof TpCalcResultMarginDetailParams)
            {
                TpCalcResultMarginDetailParams l_params = (TpCalcResultMarginDetailParams)l_element;
                this.setCalcResultDetailMargin(l_params);
            }
        }

        //パラメータ.余力計算条件をプロパティにセットする。
        this.calcCondition = l_calcCondition;
    }

    /**
     * (コンストラクタ)<BR>
     * <BR>
     * 引数を属性にセットする。<BR>
     * <BR>
     * １）引数.余力計算結果<信用顧客>より、余力計算結果Params<信用顧客>オブジェクトを取得し、<BR>
     * 　@this.余力計算結果Params<信用顧客>にセットする。<BR>
     * <BR>
     * ２）引数.余力計算結果<信用顧客>より、余力計算結果詳細Params<信用顧客>オブジェクトを取得し、<BR>
     * 　@this.余力計算結果詳細Params<信用顧客>にセットする。<BR>
     * <BR>
     * ３）引数.使用可能現金情報を、this.使用可能現金情報にセットする。<BR>
     * <BR>
     * ４）引数.余力計算条件を、this.余力計算条件にセットする。<BR>
     * <BR>
     * @@param l_calcResult - （余力計算結果）
     * @@param l_actualBalanceInfo - （使用可能現金情報）
     * @@param l_calcCondition - （余力計算条件）
     */
    public WEB3TPTradingPowerCalcMargin(
        List l_calcResult,
        WEB3TPActualAccountBalanceInfo l_actualBalanceInfo,
        WEB3TPCalcCondition l_calcCondition)
    {
        /*
         * Listより余力計算結果<信用顧客>オブジェクト、余力計算結果詳細<信用顧客>オブジェクト
         * を取得しプロパティにセットする。
         */
        for (Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object)l_iter.next();

            if (l_element instanceof TpCalcResultMarginParams)
            {
                TpCalcResultMarginParams l_params = (TpCalcResultMarginParams)l_element;
                this.setCalcResultMargin(l_params);
            }
            else if (l_element instanceof TpCalcResultMarginDetailParams)
            {
                TpCalcResultMarginDetailParams l_params = (TpCalcResultMarginDetailParams)l_element;
                this.setCalcResultDetailMargin(l_params);
            }
        }

        //パラメータ.使用可能現金情報をプロパティにセットする。
        this.actualBalanceInfo = l_actualBalanceInfo;

        //パラメータ.余力計算条件をプロパティにセットする。
        this.calcCondition = l_calcCondition;
    }

    /**
     * （get余力計算結果Params<信用顧客><BR>
     * 
     * this.余力計算結果Params<信用顧客>を返却する。<BR>
     * @@return webbroker3.tradingpower.data.TpCalcResultMarginParams
     * @@roseuid 40FCBFDA029C
     */
    public TpCalcResultMarginParams getCalcResultMargin()
    {
        return this.calcResultMargin;
    }

    /**
     * （set余力計算結果Params<信用顧客><BR>
     * 
     * パラメータ.余力計算結果Params<信用顧客>をthis.余力計算結果Params<信用顧客>にセッ
     * トする。<BR>
     * @@param l_calcResultMargin - （余力計算結果Params<信用顧客>）
     * @@roseuid 40FCBFDA02CB
     */
    public void setCalcResultMargin(TpCalcResultMarginParams l_calcResultMargin)
    {
        this.calcResultMargin = l_calcResultMargin;
    }

    /**
     * （get余力計算結果詳細Params<信用顧客><BR>
     * 
     * this.余力計算結果詳細Params<信用顧客>を返却する。<BR>
     * @@return webbroker3.tradingpower.data.TpCalcResultMarginDetailParams
     */
    public TpCalcResultMarginDetailParams getCalcResultDetailMargin()
    {
        return this.calcResultDetailMargin;
    }

    /**
     * （set余力計算結果詳細Params<信用顧客><BR>
     * 
     * パラメータ.余力計算結果詳細Params<信用顧客>をthis.余力計算結果詳細Params<信用顧客>にセッ
     * トする。<BR>
     * @@param l_calcResultDetailMargin - （余力計算結果詳細Params<信用顧客>）
     */
    public void setCalcResultDetailMargin(TpCalcResultMarginDetailParams l_calcResultDetailMargin)
    {
        this.calcResultDetailMargin = l_calcResultDetailMargin;
    }

    /**
     * （get使用可能現金情報）<BR>
     * 
     * this.使用可能現金情報を返却する。<BR>
     * @@return webbroker3.tradingpower.ActualAccountBalanceInfo
     */
    public WEB3TPActualAccountBalanceInfo getActualBalanceInfo()
    {
        return this.actualBalanceInfo;
    }

    /**
     * （set使用可能現金情報）<BR>
     * 
     * パラメータ.使用可能現金情報をthis.使用可能現金情報にセットする。<BR>
     * @@param l_actualBalanceInfo - （使用可能現金情報）
     */
    public void setActualBalanceInfo(WEB3TPActualAccountBalanceInfo l_actualBalanceInfo)
    {
        this.actualBalanceInfo = l_actualBalanceInfo;
    }

    /**
     * （get余力計算条件<BR>
     * 
     * this.余力計算条件を返却する。<BR>
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     * @@roseuid 40FCBFDA02FA
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        return this.calcCondition;
    }

    /**
     * （set余力計算条件）<BR>
     * 
     * パラメータ.余力計算条件をthis.余力計算条件にセットする。<BR>
     * @@param l_calcCondition - （余力計算条件）
     * @@roseuid 40FCBFDA0329
     */
    public void setCalcCondition(WEB3TPCalcCondition l_calcCondition)
    {
        this.calcCondition = l_calcCondition;
    }

    /**
     * （get当日株式予約注文単位一覧）<BR>
     * <BR>
     * this.当日株式予約注文単位一覧を返却する。<BR>
     * <BR>
     * @@return List
     */
    public List getTodaysRsvEqOrderUnits()
    {
        return this.todaysRsvEqOrderUnits;
    }

    /**
     * （set当日株式予約注文単位一覧）<BR>
     * <BR>
     * 引数.当日株式予約注文単位一覧をthis.当日株式予約注文単位一覧にセットする。<BR>
     * <BR>
     * @@param l_todaysRsvEqOrderUnits - (当日株式予約注文単位一覧)
     */
    public void setTodaysRsvEqOrderUnits(List l_todaysRsvEqOrderUnits)
    {
        this.todaysRsvEqOrderUnits = l_todaysRsvEqOrderUnits;
    }

    /**
     * （get預り金残高）<BR>
     * 
     * 引数で指定された指定日(=n)の、「預り金残高」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「預り金残高」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get預り金残高（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B409080125
     */
    public double getAccountBalance(int l_intSpecifiedPoint)
    {
        //預り金残高
        double l_dblAccountBalance;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //預り金残高( T + 0 )を取得する。
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //預り金残高( T + 1 )を取得する。
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //預り金残高( T + 2 )を取得する。
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //預り金残高( T + 3 )を取得する。
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //預り金残高( T + 4 )を取得する。
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //預り金残高( T + 5 )を取得する。
                l_dblAccountBalance = this.calcResultMargin.getAccountBalance5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getAccountBalance(int)");
        }

        //取得した預り金残高を返却する。
        return l_dblAccountBalance;
    }

    /**
     * （get当日約定済代金<BR>
     * 
     * 引数で指定された指定日(=n)の、「当日約定済代金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「当日約定済代金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get当日約定済代金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B409630382
     */
    public double getTodayExecutedAmount(int l_intSpecifiedPoint)
    {
        //当日約定済代金
        double l_dblTodayExecutedAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //当日約定済代金( T + 0 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //当日約定済代金( T + 1 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //当日約定済代金( T + 2 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //当日約定済代金( T + 3 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //当日約定済代金( T + 4 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //当日約定済代金( T + 5 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultMargin.getTodayExecutedAmount5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getTodayExecutedAmount(int)");
        }

        //取得した当日約定済代金を返却する。
        return l_dblTodayExecutedAmount;
    }

    /**
     * （get当日未約定代金）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「当日未約定代金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「当日未約定代金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get当日未約定代金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B409B70294
     */
    public double getTodayUnexecutedAmount(int l_intSpecifiedPoint)
    {
        //当日未約定代金
        double l_dblTodayUnexecutedAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //当日約定済代金( T + 0 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //当日約定済代金( T + 1 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //当日約定済代金( T + 2 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //当日約定済代金( T + 3 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //当日約定済代金( T + 4 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //当日約定済代金( T + 5 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultMargin.getTodayUnexecutedAmount5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getTodayUnexecutedAmount(int)");
        }

        //取得した当日約定済代金を返却する。
        return l_dblTodayUnexecutedAmount;
    }

    /**
     * （get日計り拘束金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「日計り拘束金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「日計り拘束金」を計算する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get日計り拘束金（T+n）<BR>
     * 
     * ３）　@日計り拘束金(n)を返却する。
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B42B930141
     */
    public double getDayTradeRestraint(int l_intSpecifiedPoint)
    {
        //日計り拘束金
        double l_dblDayTradeRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //日計り拘束金( T + 0 )を取得する。
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //日計り拘束金( T + 1 )を取得する。
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //日計り拘束金( T + 2 )を取得する。
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //日計り拘束金( T + 3 )を取得する。
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //日計り拘束金( T + 4 )を取得する。
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //日計り拘束金( T + 5 )を取得する。
                l_dblDayTradeRestraint = this.calcResultMargin.getDayTradeRestraint5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getDayTradeRestraint(int)");
        }

        //取得した日計り拘束金を返却する。
        return l_dblDayTradeRestraint;
    }

    /**
     * （getその他拘束金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「その他拘束金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「その他拘束金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.getその他拘束金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4093A02C8
     */
    public double getOtherRestraint(int l_intSpecifiedPoint)
    {
        //その他拘束金
        double l_dblOtherRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //その他拘束金( T + 0 )を取得する。
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //その他拘束金( T + 1 )を取得する。
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //その他拘束金( T + 2 )を取得する。
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //その他拘束金( T + 3 )を取得する。
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //その他拘束金( T + 4 )を取得する。
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //その他拘束金( T + 5 )を取得する。
                l_dblOtherRestraint = this.calcResultMargin.getOtherRestraint5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getOtherRestraint(int)");
        }

        //取得したその他拘束金を返却する。
        return l_dblOtherRestraint;
    }

    /**
     * （get代用証券評価額）<BR>
     * 
     * 引数で指定された指定日(=n)の、「代用証券評価額」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「代用証券評価額」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get代用証券評価額（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4299A0092
     */
    public double getSubstituteSecurityAsset(int l_intSpecifiedPoint)
    {
        //代用証券評価額
        double l_dblSubstituteSecurityAsset;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //代用証券評価額( T + 0 )を取得する。
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //代用証券評価額( T + 1 )を取得する。
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //代用証券評価額( T + 2 )を取得する。
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //代用証券評価額( T + 3 )を取得する。
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //代用証券評価額( T + 4 )を取得する。
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //代用証券評価額( T + 5 )を取得する。
                l_dblSubstituteSecurityAsset = this.calcResultMargin.getSubstituteSecurityAsset5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getSubstituteSecurityAsset(int)");
        }

        //取得した代用証券評価額を返却する。
        return l_dblSubstituteSecurityAsset;
    }

    /**
     * （get未決済建玉代金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「未決済建玉代金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「未決済建玉代金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get未決済建玉代金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429BC013E
     */
    public double getContractAmount(int l_intSpecifiedPoint)
    {
        //未決済建玉代金
        double l_dblContractAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //未決済建玉代金( T + 0 )を取得する。
                l_dblContractAmount = this.calcResultMargin.getContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //未決済建玉代金( T + 1 )を取得する。
                l_dblContractAmount = this.calcResultMargin.getContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //未決済建玉代金( T + 2 )を取得する。
                l_dblContractAmount = this.calcResultMargin.getContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //未決済建玉代金( T + 3 )を取得する。
                l_dblContractAmount = this.calcResultMargin.getContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //未決済建玉代金( T + 4 )を取得する。
                l_dblContractAmount = this.calcResultMargin.getContractAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //未決済建玉代金( T + 5 )を取得する。
                l_dblContractAmount = this.calcResultMargin.getContractAmount5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getContractAmount(int)");
        }

        //取得した未決済建玉代金を返却する。
        return l_dblContractAmount;
    }

    /**
     * （get日計り返済・現引現渡建玉代金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「日計り返済・現引現渡建玉代金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「日計り返済・現引現渡建玉代金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429BC013E
     */
    public double getDayRepayContractAmount(int l_intSpecifiedPoint)
    {
        //日計り返済・現引現渡建玉代金
        double l_dblDayRepayContractAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //日計り返済・現引現渡建玉代金( T + 0 )を取得する。
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //日計り返済・現引現渡建玉代金( T + 1 )を取得する。
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //日計り返済・現引現渡建玉代金( T + 2 )を取得する。
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //日計り返済・現引現渡建玉代金( T + 3 )を取得する。
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //日計り返済・現引現渡建玉代金( T + 4 )を取得する。
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //日計り返済・現引現渡建玉代金( T + 5 )を取得する。
                l_dblDayRepayContractAmount = this.calcResultMargin.getDayRepayContractAmount5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getDayRepayContractAmount(int)");
        }

        //取得した日計り返済・現引現渡建玉代金を返却する。
        return l_dblDayRepayContractAmount;
    }

    /**
     * （get未決済建玉代金<日計り返済・現引現渡建玉代金考慮>）<BR>
     * 
     * 引数で指定された指定日(=n)の、「未決済建玉代金<日計り返済・現引現渡建玉代金考慮>」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「未決済建玉代金<日計り返済・現引現渡建玉代金考慮>」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get未決済建玉代金（T+n）<BR>
     *  + this.余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429BC013E
     */
    public double getContractAmountDayRepay(int l_intSpecifiedPoint)
    {
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>
        double l_dblContractAmountDayPepay;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //未決済建玉代金( T + 0 ) + 日計り返済・現引現渡建玉代金( T + 0 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount0()
                        + this.calcResultMargin.getDayRepayContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //未決済建玉代金( T + 1 ) + 日計り返済・現引現渡建玉代金( T + 1 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount1()
                        + this.calcResultMargin.getDayRepayContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //未決済建玉代金( T + 2 ) + 日計り返済・現引現渡建玉代金( T + 2 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount2()
                        + this.calcResultMargin.getDayRepayContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //未決済建玉代金( T + 3 ) + 日計り返済・現引現渡建玉代金( T + 3 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount3()
                        + this.calcResultMargin.getDayRepayContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //未決済建玉代金( T + 4 ) + 日計り返済・現引現渡建玉代金( T + 4 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount4()
                        + this.calcResultMargin.getDayRepayContractAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //未決済建玉代金( T + 5 ) + 日計り返済・現引現渡建玉代金( T + 5 )
                l_dblContractAmountDayPepay =
                    this.calcResultMargin.getContractAmount5()
                        + this.calcResultMargin.getDayRepayContractAmount5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getContractAmountDayRepay(int)");
        }

        //取得した未決済建玉代金<日計り返済・現引現渡建玉代金考慮>を返却する。
        return l_dblContractAmountDayPepay;
    }

    /**
     * （get必要保証金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「必要保証金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「必要保証金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get必要保証金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429EB010F
     */
    public double getMarginDeposit(int l_intSpecifiedPoint)
    {
        //必要保証金
        double l_dblMarginDeposit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //必要保証金( T + 0 )を取得する。
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //必要保証金( T + 1 )を取得する。
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //必要保証金( T + 2 )を取得する。
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //必要保証金( T + 3 )を取得する。
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //必要保証金( T + 4 )を取得する。
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //必要保証金( T + 5 )を取得する。
                l_dblMarginDeposit = this.calcResultMargin.getMarginDeposit5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getMarginDeposit(int)");
        }

        //取得した必要保証金を返却する。
        return l_dblMarginDeposit;
    }

    /**
     * （get現金必要保証金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「現金必要保証金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「現金必要保証金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get現金必要保証金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B42A050267
     */
    public double getCashMarginDeposit(int l_intSpecifiedPoint)
    {
        //現金必要保証金
        double l_dblCashMarginDeposit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //現金必要保証金( T + 0 )を取得する。
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //現金必要保証金( T + 1 )を取得する。
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //現金必要保証金( T + 2 )を取得する。
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //現金必要保証金( T + 3 )を取得する。
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //現金必要保証金( T + 4 )を取得する。
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //現金必要保証金( T + 5 )を取得する。
                l_dblCashMarginDeposit = this.calcResultMargin.getCashMarginDeposit5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getCashMarginDeposit(int)");
        }

        //取得した現金必要保証金を返却する。
        return l_dblCashMarginDeposit;
    }

    /**
     * （get未決済建玉評価損益）<BR>
     * 
     * 引数で指定された指定日(=n)の、「未決済建玉評価損益」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「未決済建玉評価損益」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get未決済建玉評価損益（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double getContractAssetProfitLoss(int l_intSpecifiedPoint)
    {
        //未決済建玉評価損益
        double l_dblContractAssetProfitLoss;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //未決済建玉評価損益( T + 0 )を取得する。
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //未決済建玉評価損益( T + 1 )を取得する。
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //未決済建玉評価損益( T + 2 )を取得する。
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //未決済建玉評価損益( T + 3 )を取得する。
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //未決済建玉評価損益( T + 4 )を取得する。
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //未決済建玉評価損益( T + 5 )を取得する。
                l_dblContractAssetProfitLoss = this.calcResultMargin.getContractAssetProfitLoss5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getCashMarginDeposit(int)");
        }

        //取得した未決済建玉評価損益を返却する。
        return l_dblContractAssetProfitLoss;
    }
    
    /**
     * （get未受渡建玉代金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「未受渡建玉代金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上3以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「未受渡建玉代金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get未受渡建玉代金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429CD0229
     */
    public double getUndeliContractAmount(int l_intSpecifiedPoint)
    {
        //未受渡建玉代金
        double l_dblUndeliContractAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //未受渡建玉代金( T + 0 )を取得する。
                l_dblUndeliContractAmount = this.calcResultMargin.getUndeliContractAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //未受渡建玉代金( T + 1 )を取得する。
                l_dblUndeliContractAmount = this.calcResultMargin.getUndeliContractAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //未受渡建玉代金( T + 2 )を取得する。
                l_dblUndeliContractAmount = this.calcResultMargin.getUndeliContractAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //未受渡建玉代金( T + 3 )を取得する。
                l_dblUndeliContractAmount = this.calcResultMargin.getUndeliContractAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //未受渡建玉代金に0.0を代入する
                l_dblUndeliContractAmount = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //未受渡建玉代金に0.0を代入する
                l_dblUndeliContractAmount = 0.0;
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliContractAmount(int)");
        }

        //取得した未受渡建玉代金を返却する。
        return l_dblUndeliContractAmount;
    }

    /**
     * （get未受渡建玉必要保証金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「未受渡建玉必要保証金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上3以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「未受渡建玉必要保証金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get未受渡建玉必要保証金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B429F401EA
     */
    public double getUndeliMarginDeposit(int l_intSpecifiedPoint)
    {
        //未受渡建玉必要保証金
        double l_dblUndeliMarginDeposit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //未受渡建玉必要保証金( T + 0 )を取得する。
                l_dblUndeliMarginDeposit = this.calcResultMargin.getUndeliMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //未受渡建玉必要保証金( T + 1 )を取得する。
                l_dblUndeliMarginDeposit = this.calcResultMargin.getUndeliMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //未受渡建玉必要保証金( T + 2 )を取得する。
                l_dblUndeliMarginDeposit = this.calcResultMargin.getUndeliMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //未受渡建玉必要保証金( T + 3 )を取得する。
                l_dblUndeliMarginDeposit = this.calcResultMargin.getUndeliMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //未受渡建玉必要保証金に0.0を代入する
                l_dblUndeliMarginDeposit = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //未受渡建玉必要保証金に0.0を代入する
                l_dblUndeliMarginDeposit = 0.0;
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliMarginDeposit(int)");
        }

        //取得した未受渡建玉必要保証金を返却する。
        return l_dblUndeliMarginDeposit;
    }

    /**
     * （get未受渡建玉現金必要保証金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「未受渡建玉現金必要保証金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上3以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「未受渡建玉現金必要保証金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get未受渡建玉現金必要保証金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B42A150054
     */
    public double getUndeliCashMarginDeposit(int l_intSpecifiedPoint)
    {
        //未受渡建玉現金必要保証金
        double l_dblUndeliCashMarginDeposit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //未受渡建玉現金必要保証金( T + 0 )を取得する。
                l_dblUndeliCashMarginDeposit = this.calcResultMargin.getUndeliCashMarginDeposit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //未受渡建玉現金必要保証金( T + 1 )を取得する。
                l_dblUndeliCashMarginDeposit = this.calcResultMargin.getUndeliCashMarginDeposit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //未受渡建玉現金必要保証金( T + 2 )を取得する。
                l_dblUndeliCashMarginDeposit = this.calcResultMargin.getUndeliCashMarginDeposit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //未受渡建玉現金必要保証金( T + 3 )を取得する。
                l_dblUndeliCashMarginDeposit = this.calcResultMargin.getUndeliCashMarginDeposit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //未受渡建玉現金必要保証金に0.0を代入する
                l_dblUndeliCashMarginDeposit = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //未受渡建玉現金必要保証金に0.0を代入する
                l_dblUndeliCashMarginDeposit = 0.0;
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliCashMarginDeposit(int)");
        }

        //取得した未受渡建玉現金必要保証金を返却する。
        return l_dblUndeliCashMarginDeposit;
    }

    /**
     * （get未受渡建玉決済損）<BR>
     * 
     * 引数で指定された指定日(=n)の、「未受渡建玉決済損」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上3以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「未受渡建玉決済損」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get未受渡建玉決済損（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B42A3200B2
     */
    public double getUndeliContractLoss(int l_intSpecifiedPoint)
    {
        //未受渡建玉決済損
        double l_dblUndeliContractLoss;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //未受渡建玉決済損( T + 0 )を取得する。
                l_dblUndeliContractLoss = this.calcResultMargin.getUndeliContractLoss0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //未受渡建玉決済損( T + 1 )を取得する。
                l_dblUndeliContractLoss = this.calcResultMargin.getUndeliContractLoss1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //未受渡建玉決済損( T + 2 )を取得する。
                l_dblUndeliContractLoss = this.calcResultMargin.getUndeliContractLoss2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //未受渡建玉決済損( T + 3 )を取得する。
                l_dblUndeliContractLoss = this.calcResultMargin.getUndeliContractLoss3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //未受渡建玉決済損に0.0を代入する
                l_dblUndeliContractLoss = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //未受渡建玉決済損に0.0を代入する
                l_dblUndeliContractLoss = 0.0;
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliContractLoss(int)");
        }

        //取得した未受渡建玉決済損を返却する。
        return l_dblUndeliContractLoss;
    }

    /**
     * （get未受渡建玉決済益）<BR>
     * 
     * 引数で指定された指定日(=n)の、「未受渡建玉決済益」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上3以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「未受渡建玉決済益」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.get未受渡建玉決済益（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double getUndeliContractProfit(int l_intSpecifiedPoint)
    {
        //未受渡建玉決済益
        double l_dblUndeliContractProfit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //未受渡建玉決済益( T + 0 )を取得する。
                l_dblUndeliContractProfit = this.calcResultMargin.getUndeliContractProfit0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //未受渡建玉決済益( T + 1 )を取得する。
                l_dblUndeliContractProfit = this.calcResultMargin.getUndeliContractProfit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //未受渡建玉決済益( T + 2 )を取得する。
                l_dblUndeliContractProfit = this.calcResultMargin.getUndeliContractProfit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //未受渡建玉決済益( T + 3 )を取得する。
                l_dblUndeliContractProfit = this.calcResultMargin.getUndeliContractProfit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //未受渡建玉決済益に0.0を代入する
                l_dblUndeliContractProfit = 0.0;
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //未受渡建玉決済益に0.0を代入する
                l_dblUndeliContractProfit = 0.0;
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getUndeliContractProfit(int)");
        }

        //取得した未受渡建玉決済益を返却する。
        return l_dblUndeliContractProfit;
    }


    /**
     * (get建玉諸経費)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「建玉諸経費」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * <BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「建玉諸経費」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<信用顧客>.建玉諸経費(T+n)<BR>
     * @@return double
     * @@roseuid 40B42A4403CF
     */
    public double getContractTotalCost(int l_intSpecifiedPoint)
    {
        //建玉諸経費
        double l_dblContractTotalCost = 0;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //建玉諸経費（T+0）を取得する。
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //建玉諸経費（T+1）を取得する。
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //建玉諸経費（T+2）を取得する。
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //建玉諸経費（T+3）を取得する。
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //建玉諸経費（T+4）を取得する。
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //建玉諸経費（T+5）を取得する。
                l_dblContractTotalCost = this.calcResultMargin.getContractTotalCost5();
                break;

            default :
                //nが0以上5以下でない時、0を返却する。
                l_dblContractTotalCost = 0;
        }

        //取得した建玉諸経費を返却する。
        return l_dblContractTotalCost;
    }

    /**
     * （calc現金保証金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「現金保証金」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B473DA0181
     */
    public double calcMarginAccountBalance(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginAccountBalance(int)");
        }

        //現金保証金（= 預り金残高 - 当日約定済代金 - 当日未約定代金 - その他拘束金）を計算する。
        double l_dblMarginAccountBalance =
            this.getAccountBalance(l_intSpecifiedPoint)
                - this.getTodayExecutedAmount(l_intSpecifiedPoint)
                - this.getTodayUnexecutedAmount(l_intSpecifiedPoint)
                - this.getOtherRestraint(l_intSpecifiedPoint);

        //計算した現金保証金を返却する。
        return l_dblMarginAccountBalance;
    }

    /**
     * （calc使用可能現金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「使用可能現金」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B70C5B00BA
     */
    public double calcActualAccountBalance(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualAccountBalance(int)");
        }

        //使用可能現金（= 現金保証金 - 現金必要保証金 - 未受渡建玉現金必要保証金）を計算する。
        double l_dblActualAccountBalance =
            this.calcMarginAccountBalance(l_intSpecifiedPoint)
                - this.getCashMarginDeposit(l_intSpecifiedPoint)
                - this.getUndeliCashMarginDeposit(l_intSpecifiedPoint);

        //this.使用可能現金情報 ≠ null かつ、引数.指定日 == this.使用可能現金情報.指定 の場合
        if (this.actualBalanceInfo != null
            && this.actualBalanceInfo.specifiedPoint == l_intSpecifiedPoint)
        {
            /*
             * 使用可能現金(n)　@=MIN(使用可能現金情報.差金決済買付可能額, 使用可能現金(n))
             */
            l_dblActualAccountBalance =
                Math.min(this.actualBalanceInfo.settlementBuyAmount, l_dblActualAccountBalance);
        }

        //計算した使用可能現金を返却する。
        return l_dblActualAccountBalance;
    }

    /**
     * （calc引出可能現金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「引出可能現金」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A65401D3
     */
    public double calcActualPaymentBalance(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualPaymentBalance(int)");
        }

        //日計り拘束金
        double l_dblDayTradeRestraint = this.getDayTradeRestraint(l_intSpecifiedPoint);

        //指定日がT+0の時
        if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
        {
            //日計り拘束金に日計り拘束金(T+0)と特別立替金実績の大きい方を代入
            l_dblDayTradeRestraint =
                Math.max(
                    this.getDayTradeRestraint(l_intSpecifiedPoint),
                    this.calcCondition.getSpecialDebitAmount());
        }
        //それ以外
        else
        {
            //日計り拘束金に日計り拘束金(指定日)を代入
            l_dblDayTradeRestraint = this.getDayTradeRestraint(l_intSpecifiedPoint);
        }

        //引出可能現金（= 使用可能現金 - 日計り拘束金）を計算する。
        double l_dblActualPaymentBalance =
            this.calcActualAccountBalance(l_intSpecifiedPoint) - l_dblDayTradeRestraint;

        //計算した引出可能現金を返却する。
        return l_dblActualPaymentBalance;
    }

    /**
     * （calc預り金請求余力）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「預り金請求余力」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double calcAccountBalanceDemandPower(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualPaymentBalance(int)");
        }

        //預り金請求余力（= 引出可能現金 + 未受渡建玉現金必要保証金）を計算する。
        double l_dblAccountBalanceDemandPower = this.calcActualPaymentBalance(l_intSpecifiedPoint)
            + this.getUndeliCashMarginDeposit(l_intSpecifiedPoint);

        //計算した預り金請求余力を返却する。
        return l_dblAccountBalanceDemandPower;
    }
    
    /**
     * （calc差入保証金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「差入保証金」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A57202ED
     */
    public double calcPaidMarginDeposit(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcPaidMarginDeposit(int)");
        }

        //差入保証金（= 現金保証金 + 代用証券評価額）を計算する。
        double l_dblPaidMarginDeposit =
            this.calcMarginAccountBalance(l_intSpecifiedPoint)
                + this.getSubstituteSecurityAsset(l_intSpecifiedPoint);

        //計算した差入保証金を返却する。
        return l_dblPaidMarginDeposit;
    }

    /**
     * （calc受入保証金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「受入保証金」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5810156
     */
    public double calcReceiptMarginDeposit(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcReceiptMarginDeposit(int)");
        }

        //受入保証金(n)
        //（= 差入保証金(n)　@-　@ Abs(Min(未決済建玉評価損益(n), 0)) - 建玉諸経費(n) - 未受渡建玉決済損(n)）を計算する。
        double l_dblReceiptMarginDeposit =
            this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                - Math.abs(Math.min(this.getContractAssetProfitLoss(l_intSpecifiedPoint), 0))
                - getContractTotalCost(l_intSpecifiedPoint)
                - getUndeliContractLoss(l_intSpecifiedPoint);

        //計算した受入保証金を返却する。
        return l_dblReceiptMarginDeposit;
    }

    /**
     * （calc保証金預託率）<BR>
     * 
     * 引数で指定された指定日(=n)の「保証金預託率」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A587003D
     */
    public double calcMarginDepositRate(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginDepositRate(int)");
        }
    
        //保証金預託率
        double l_dblMarginDepositRate;
    
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(BigDecimal型)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));
    
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>が0より大きい時
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //受入保証金(BigDecimal型)
            BigDecimal l_bdReceiptMarginDeposit =
                new BigDecimal(this.calcReceiptMarginDeposit(l_intSpecifiedPoint));
    
            //保証金預託率（= 受入保証金(n) / 未決済建玉代金(n) × 100）を計算する（小数点以下5桁目で切り捨て）
            BigDecimal l_bdMarginDepositRate =
                l_bdReceiptMarginDeposit.multiply(new BigDecimal(100.0)).divide(
                    l_bdContractAmount,
                    4,
                    BigDecimal.ROUND_FLOOR);
    
            //保証金預託率に計算結果を代入する。
            l_dblMarginDepositRate = l_bdMarginDepositRate.doubleValue();
    
        }
        else
            //それ以外
            {
            //保証金預託率に正の無限大を代入する。
            l_dblMarginDepositRate = Double.POSITIVE_INFINITY;
        }
    
        //計算した保証金預託率を返却する。
        return l_dblMarginDepositRate;
    }

    /**
     * （calc保証金余力）<BR>
     * 
     * 引数で指定された指定日(=n)の「保証金余力」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A59502AE
     */
    public double calcMarginPower(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginPower(int)");
        }
    
        //保証金余力
        double l_dblMarginPower;
    
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(BigDecimal型)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));
    
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>が0より大きい時
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //差入保証金
            double l_dblPaidMarginDeposit = this.calcPaidMarginDeposit(l_intSpecifiedPoint);
            BigDecimal l_bdPaidMarginDeposit = new BigDecimal(l_dblPaidMarginDeposit);
            //最低必要保証金
            double l_dblMinMarginDeposit = this.calcCondition.getMinMarginDeposit();
            BigDecimal l_bdMinMarginDeposit = new BigDecimal(l_dblMinMarginDeposit);
            //受入保証金
            double l_dblReceiptMarginDeposit = this.calcReceiptMarginDeposit(l_intSpecifiedPoint);
            BigDecimal l_bdReceiptMarginDeposit = new BigDecimal(l_dblReceiptMarginDeposit);
            //法@定最低必要保証金
            double l_dblLegalMinMarginDeposit = this.calcCondition.getLegalMinMarginDeposit();
            BigDecimal l_bdLegalMinMarginDeposit = new BigDecimal(l_dblLegalMinMarginDeposit);
    
            //差入保証金が最低必要保証金以上　@かつ　@受入保証金が法@定最低必要保証金以上の時
            if (l_bdPaidMarginDeposit.compareTo(l_bdMinMarginDeposit) >= 0
                && l_bdReceiptMarginDeposit.compareTo(l_bdLegalMinMarginDeposit) >= 0)
            {
                //保証金余力（=受入保証金 - 必要保証金）を計算する
                l_dblMarginPower =
                    l_dblReceiptMarginDeposit - this.getMarginDeposit(l_intSpecifiedPoint);
            }
            //それ以外
            else
            {
                //保証金余力①@（=差入保証金 - 最低必要保証金）を計算する
                double l_dblMarginPower1 = l_dblPaidMarginDeposit - l_dblMinMarginDeposit;
    
                //保証金余力②（=受入保証金(n) - Max(必要保証金(n), 法@定最低必要保証金)）を計算する。
                double l_dblMarginPower2 =
                    l_dblReceiptMarginDeposit
                        - Math.max(
                            this.getMarginDeposit(l_intSpecifiedPoint),
                            l_dblLegalMinMarginDeposit);
    
                //保証金余力に保証金余力①@と保証金余力②の小さい方を代入
                l_dblMarginPower = Math.min(l_dblMarginPower1, l_dblMarginPower2);
            }
        }
        //それ以外
        else
        {
            l_dblMarginPower = this.calcReceiptMarginDeposit(l_intSpecifiedPoint);
        }
    
        //計算した保証金余力を返却する。
        return l_dblMarginPower;
    }

    /**
    * （calc追証余力）<BR>
    * 
    * 引数で指定された指定日(=n)の「追証余力」を計算する。<BR>
    * <BR>
    * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
    * @@param l_intSpecifiedPoint
    * @@return double
    * @@roseuid 40B6A5A50231
    */
    public double calcMarginCallPower(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginCallPower(int)");
        }
    
        //追証余力
        double l_dblMarginCallPower;
    
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(BigDecimal型)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));
    
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>が0より大きい時
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            /*
             * 追証余力(n) = Min(差入保証金(n) - 最低必要保証金, 差入保証金(n) - 追証必要保証金(n))
             */
    
            //追証余力①@（=差入保証金(n) - 最低必要保証金）を計算する
            double l_dblMarginCallPower1 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getMinMarginDeposit();
    
            //追証余力②（=差入保証金(n) - 追証必要保証金(n)）を計算する
            double l_dblMarginCallPower2 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcMarginCallDeposit(l_intSpecifiedPoint);
    
            //追証余力に追証余力①@と追証余力②の小さい方を代入する
            l_dblMarginCallPower = Math.min(l_dblMarginCallPower1, l_dblMarginCallPower2);
        }
        //それ以外
        else
        {
            //差入保証金(n) - 追証必要保証金(n)
            l_dblMarginCallPower =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcMarginCallDeposit(l_intSpecifiedPoint);
        }
    
        //計算した追証余力を返却する。
        return l_dblMarginCallPower;
    }

    /**
    * （calc保証金引出余力）<BR>
    * 
    * 引数で指定された指定日(=n)の「信用現引余力」を計算する。<BR>
    * <BR>
    * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
    * @@param l_intSpecifiedPoint
    * @@return double
    * @@roseuid 40B6A5AC0398
    */
    public double calcMarginDrawPower(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginDrawPower(int)");
        }
    
        //保証金引出余力(n)
        double l_dblMarginDrawPower = 0;
    
        //建玉代金（= 未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(n) + 未受渡建玉代金(n)）を計算する
        double l_dblContractAmount =
            this.getContractAmountDayRepay(l_intSpecifiedPoint)
                + this.getUndeliContractAmount(l_intSpecifiedPoint);

        //建玉代金(BigDecimal型)
        BigDecimal l_bdContractAmount = new BigDecimal(l_dblContractAmount);

        //建玉代金が0より大きい時
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            /* 
             * 保証金引出余力 = Min(差入保証金(n) - 最低必要保証金, 差入保証金(n) - 保証金引出拘束金(n)) 
             */
            //保証金引出余力①@（=差入保証金(n) - 最低必要保証金）を計算する。
            double l_dblMarginDrawPower1 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint) - this.calcCondition.getMinMarginDeposit();
            
            //保証金引出余力②（=差入保証金(n) - 保証金引出拘束金(n)）を計算する。
            double l_dblMarginDrawPower2 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcMarginDrawDeposit(l_intSpecifiedPoint);
                    
            //保証金引出余力に保証金引出余力①@と保証金引出余力②の小さい方を代入する
            l_dblMarginDrawPower = Math.min(l_dblMarginDrawPower1, l_dblMarginDrawPower2);
        }
        //建玉代金が存在しない時
        else
        {
            //保証金引出余力(n) = 受入保証金(n)
            l_dblMarginDrawPower =  this.calcReceiptMarginDeposit(l_intSpecifiedPoint);
        }
    
        //計算した保証金引出余力を返却する
        return l_dblMarginDrawPower;
    }

    /**
     * （calc追証必要保証金）<BR>
     * 引数で指定された指定日(=n)の「追証必要保証金」を計算する。
     * 
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。 
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcMarginCallDeposit(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginCallDeposit(int)");
        }
    
        //追証必要保証金(n)
        double l_dblMarginCallDeposit = 0.0;
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(n)
        double l_dblContAmt = this.getContractAmountDayRepay(l_intSpecifiedPoint);
    
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(n) > 0の場合        
        if (l_dblContAmt > 0)
        {
            /*
             *追証必要保証金(n) = 
             *  信用拘束金(n) + 
             *           Max(法@定最低必要保証金, 未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(n) ×　@保証金維持率)
             */
            l_dblMarginCallDeposit =
                this.calcMarginRestraint(l_intSpecifiedPoint)
                    + Math.max(
                        this.calcCondition.getLegalMinMarginDeposit(),
                        l_dblContAmt * this.calcCondition.getMarginMentenanceRate() / 100);
    
            BigDecimal l_bdMarginCallDeposit = new BigDecimal(Double.toString(l_dblMarginCallDeposit));
    
            l_dblMarginCallDeposit =
                l_bdMarginCallDeposit.setScale(0, BigDecimal.ROUND_DOWN).doubleValue();
    
        }
        //以外(未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(n) = 0)の場合
        else
        {
            //追証必要保証金(n) = 信用拘束金(n)
            l_dblMarginCallDeposit = this.calcMarginRestraint(l_intSpecifiedPoint);
        }
    
        return l_dblMarginCallDeposit;
    }

    /**
     * （calc保証金引出拘束金）<BR>
     * 引数で指定された指定日(=n)の「保証金引出拘束金」を計算する。
     * 
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。 
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcMarginDrawDeposit(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginDrawDeposit(int)");
        }
    
        //保証金引出拘束金(n)
        double l_dblMarginDeposit = 0;
    
        //建玉代金（= 未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(n) + 未受渡建玉代金(n)）を計算する
        double l_dblContractAmount =
            this.getContractAmountDayRepay(l_intSpecifiedPoint)
                + this.getUndeliContractAmount(l_intSpecifiedPoint);

        //建玉代金(BigDecimal型)
        BigDecimal l_bdContractAmount = new BigDecimal(l_dblContractAmount);

        //建玉代金が0より大きい時
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //保証金引出拘束金 = 信用拘束金(n) + Max(必要保証金(n) + 未受渡建玉必要保証金(n), 法@定最低必要保証金) 
            l_dblMarginDeposit =
                this.calcMarginRestraint(l_intSpecifiedPoint)
                    + Math.max(
                        this.getMarginDeposit(l_intSpecifiedPoint)
                            + this.getUndeliMarginDeposit(l_intSpecifiedPoint),
                        this.calcCondition.getLegalMinMarginDeposit());
        }
        //建玉代金が存在しない時
        else
        {
            //保証金引出拘束金(n) = 信用拘束金(n)
            l_dblMarginDeposit =  this.calcMarginRestraint(l_intSpecifiedPoint);
        }
    
        //計算した保証金引出余力を返却する
        return l_dblMarginDeposit;
    }

    /**
     * （calc信用拘束金）<BR>
     * 引数で指定された指定日(=n)の「信用拘束金」を計算する
     * 
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。  
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcMarginRestraint(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginRestraint(int)");
        }
    
        //信用拘束金(n) = 
        //    建玉諸経費(n) + Abs(Min(未決済建玉評価損益(n), 0)) + 未受渡建玉決済損(n)
        double l_dblContLoss = Math.abs(Math.min(this.getContractAssetProfitLoss(l_intSpecifiedPoint), 0));

        double l_dblMarginRest =
            this.getContractTotalCost(l_intSpecifiedPoint)
                + l_dblContLoss
                + this.getUndeliContractLoss(l_intSpecifiedPoint);
    
        return l_dblMarginRest;
    }

    /**
     * （calc株式買付可能額）<BR>
     * 
     * 引数で指定された指定日(=n)の、「株式買付可能額」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5B602DD
     */
    public double calcEquityTradingPower(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcEquityTradingPower(int)");
        }
    
        //株式買付可能額
        double l_dblEquityTradingPower;
    
        //建玉代金（= 未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(n) + 未受渡建玉代金(n)）を計算する
        double l_dblContractAmount =
            this.getContractAmountDayRepay(l_intSpecifiedPoint)
                + this.getUndeliContractAmount(l_intSpecifiedPoint);
    
        //建玉代金(BigDecimal型)
        BigDecimal l_bdContractAmount = new BigDecimal(l_dblContractAmount);
    
        //建玉代金が0より大きい時
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //保証金引出余力を計算する。
            double l_dblMarginDrawPower = this.calcMarginDrawPower(l_intSpecifiedPoint);
            //保証金引出余力(BigDecimal型)
            BigDecimal l_bdMarginDrawPower = new BigDecimal(l_dblMarginDrawPower);
    
            //保証金引出余力が、0以上　@かつ　@パラメータ.指定日が、余力計算基準日<株式買付/信用現引>以上の時
            if (l_bdMarginDrawPower.compareTo(new BigDecimal(0.0)) >= 0
                && l_intSpecifiedPoint >= this.calcCondition.getEquityBasePoint())
            {
    
                //余力計算代用掛目(BigDecimal型)
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());
    
                //保証金引出余力 / (1 - (余力計算代用掛目 / 100) を計算する
                l_bdMarginDrawPower =
                    l_bdMarginDrawPower.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                BigDecimal.valueOf(100),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);
    
                //株式買付可能額に、保証金引出余力と使用可能現金(n)の小さい方を代入
                l_dblEquityTradingPower =
                    Math.min(
                        l_bdMarginDrawPower.doubleValue(),
                        this.calcActualAccountBalance(l_intSpecifiedPoint));
    
                //株式可能額に計算結果を代入（少数点以下切捨て）
                l_dblEquityTradingPower = Math.floor(l_dblEquityTradingPower);
            }
            //それ以外
            else
            {
                //株式買付可能額に、保証金引出余力と使用可能現金(n)の小さい方を代入
                l_dblEquityTradingPower =
                    Math.min(
                        l_dblMarginDrawPower,
                        this.calcActualAccountBalance(l_intSpecifiedPoint));
            }
        }
        //それ以外
        else
        {
            //株式買付可能額に、使用可能現金(n)を代入
            l_dblEquityTradingPower = this.calcActualAccountBalance(l_intSpecifiedPoint);
        }
    
        //計算した株式買付可能額を返却する。 
        return l_dblEquityTradingPower;
    }

    /**
     * （calc株式買付可能額<日計り拘束金考慮><BR>
     * 
     * 引数で指定された指定日(=n)の、「株式買付可能額<日計り拘束金考慮>」を返却する。<B
     * R>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5C0009B
     */
    public double calcEquityTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcEquityTradingPowerIncDayTrade(int)");
        }

        //株式買付可能額
        double l_dblEquityTradingPower;

        //建玉代金（= 未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(n) + 未受渡建玉代金(n)）を計算する
        double l_dblContractAmount =
            this.getContractAmountDayRepay(l_intSpecifiedPoint)
                + this.getUndeliContractAmount(l_intSpecifiedPoint);

        //建玉代金(BigDecimal型)
        BigDecimal l_bdContractAmount = new BigDecimal(l_dblContractAmount);

        //建玉代金が0より大きい時
        if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
            //保証金引出余力を計算する。
            double l_dblMarginDrawPower = this.calcMarginDrawPower(l_intSpecifiedPoint);
            //保証金引出余力(BigDecimal型)
            BigDecimal l_bdMarginDrawPower = new BigDecimal(l_dblMarginDrawPower);

            //保証金引出余力が、0以上　@かつ　@パラメータ.指定日が、余力計算基準日<株式買付/信用現引>以上の時
            if (l_bdMarginDrawPower.compareTo(new BigDecimal(0.0)) >= 0
                && l_intSpecifiedPoint >= this.calcCondition.getEquityBasePoint())
            {

                //余力計算代用掛目(BigDecimal型)
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //保証金引出余力 / (1 - (余力計算代用掛目 / 100) ) を計算する
                l_bdMarginDrawPower =
                    l_bdMarginDrawPower.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                BigDecimal.valueOf(100),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //株式買付可能額に、保証金引出余力と引出可能現金(n)の小さい方を代入
                l_dblEquityTradingPower =
                    Math.min(
                        l_bdMarginDrawPower.doubleValue(),
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));

                //株式可能額に計算結果を代入（少数点以下切捨て）
                l_dblEquityTradingPower = Math.floor(l_dblEquityTradingPower);
            }
            //それ以外
            else
            {
                //株式買付可能額に、保証金引出余力と引出可能現金(n)の小さい方を代入
                l_dblEquityTradingPower =
                    Math.min(
                        l_dblMarginDrawPower,
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));
            }
        }
        //それ以外
        else
        {
            //株式買付可能額に、引出可能現金(n)を代入
            l_dblEquityTradingPower = this.calcActualPaymentBalance(l_intSpecifiedPoint);
        }

        //計算した株式買付可能額を返却する。
        return l_dblEquityTradingPower;
    }

    /**
     * （calc信用新規建可能額）<BR>
     * 
     * 引数で指定された指定日(=n)の「信用新規建可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A59E0108
     */
    public double calcMarginTradingPower(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginTradingPower(int)");
        }

        //信用新規建可能額
        double l_dblMarginTradingPower;

        //差入保証金
        BigDecimal l_bdPaidMarginDeposit =
            new BigDecimal(this.calcPaidMarginDeposit(l_intSpecifiedPoint));
        //最低必要保証金
        BigDecimal l_bdMinMarginDeposit = new BigDecimal(this.calcCondition.getMinMarginDeposit());
        //受入保証金
        BigDecimal l_bdReceiptMarginDeposit =
            new BigDecimal(this.calcReceiptMarginDeposit(l_intSpecifiedPoint));
        //法@定最低必要保証金
        BigDecimal l_bdLegalMinMarginDeposit =
            new BigDecimal(this.calcCondition.getLegalMinMarginDeposit());

        //保証金余力
        double l_dblMarginPower = this.calcMarginPower(l_intSpecifiedPoint);
        BigDecimal l_bdMarginPower = new BigDecimal(l_dblMarginPower);

        //差入保証金が最低必要保証金以上　@かつ　@受入保証金が法@定最低必要保証金以上の時
        if (l_bdPaidMarginDeposit.compareTo(l_bdMinMarginDeposit) >= 0
            && l_bdReceiptMarginDeposit.compareTo(l_bdLegalMinMarginDeposit) >= 0)
        {
            //保証金余力が0より大きい時
            if (l_bdMarginPower.compareTo(new BigDecimal(0.0)) > 0)
            {
                //信用新規建可能額(BigDecimal型)
                BigDecimal l_bdMarginTradingPower;
                //保証金率
                BigDecimal l_bdMarginDepositRate =
                    new BigDecimal(this.calcCondition.getMarginDepositRate());

                //現金保証金率が0より大きい時
                if (this.calcCondition.getCashMarginDepositRate() > 0)
                {
                    //信用新規建可能額①@（ = 保証金余力 / 保証金率 / 100）を計算する。
                    BigDecimal l_bdMarginTradingPower1 =
                        l_bdMarginPower.divide(
                            l_bdMarginDepositRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN),
                            10,
                            BigDecimal.ROUND_HALF_EVEN);

                    //預り金請求余力
                    BigDecimal l_bdActualPaymentBalance =
                        new BigDecimal(
                            this.calcAccountBalanceDemandPower(l_intSpecifiedPoint));

                    //現金保証金率
                    BigDecimal l_bdCashMarginDepositRate =
                        new BigDecimal(this.calcCondition.getCashMarginDepositRate());

                    //信用新規建可能額②（ = 預り金請求余力 / 現金保証金率 / 100）
                    BigDecimal l_bdMarginTradingPower2 =
                        l_bdActualPaymentBalance.divide(
                            l_bdCashMarginDepositRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN),
                            10,
                            BigDecimal.ROUND_HALF_EVEN);

                    //信用新規建可能額に信用新規建可能額①@と信用新規建可能額②の小さい方を代入
                    l_bdMarginTradingPower = l_bdMarginTradingPower1.min(l_bdMarginTradingPower2);

                    //信用新規建可能額に代入する（少数点以下切捨て）
                    l_dblMarginTradingPower = Math.floor(l_bdMarginTradingPower.doubleValue());
                }
                //それ以外
                else
                {
                    //信用新規建可能額（ = 保証金余力 / 保証金率 / 100）を計算する。
                    l_bdMarginTradingPower =
                        l_bdMarginPower.divide(
                            l_bdMarginDepositRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN),
                            10,
                            BigDecimal.ROUND_HALF_EVEN);

                    //信用新規建可能額に代入する（少数点以下切捨て）
                    l_dblMarginTradingPower = Math.floor(l_bdMarginTradingPower.doubleValue());
                }
            }
            //それ以外
            else
            {
                //信用新規建可能額に保証金余力を代入
                l_dblMarginTradingPower = l_dblMarginPower;
            }
        }
        //それ以外
        else
        {
            //信用新規建可能額に保証金余力と0の小さい方を代入
            l_dblMarginTradingPower = Math.min(l_dblMarginPower, 0.0);
        }

        //計算した信用新規建可能額を返却する
        return l_dblMarginTradingPower;
    }

    /**
     * （calc信用現引可能額）<BR>
     * 
     * 引数で指定された指定日(=n)の「信用現引余力」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5EF00BA
     */
    public double calcActualReceiptTradingPower(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualReceiptTradingPower(int)");
        }

        // “信用現引余力の追証余力チェック”を取得する
        String l_strInstBranCalcCondition = 
            this.calcCondition.getInstBranCalcCondition(
                WEB3TPCalcCondition.ACTUALRECEIPT_MARGINCALLPOWER_CHECK);

        // 追証余力(n)を計算する
        double l_dblMarginCallPower = this.calcMarginCallPower(l_intSpecifiedPoint);

        //信用現引可能額
        double l_dblActualReceiptTradingPower;

        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(BigDecimal型)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));

        // 追証余力 (n) < 0場合 かつ 信用現引余力の追証余力チェック = 1
        if (l_dblMarginCallPower < 0 && 
            WEB3TPActualReceiptMargincallPowerCheckDef.EXECUTE.equals(l_strInstBranCalcCondition))
        {
            l_dblActualReceiptTradingPower = 0;
        }
        else if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>が0より大きい時

            /*
             * 信用現引可能額①@を計算する。
             * ⇒現引後、保証金割れ、代用証券への振り替えを考慮しない
             * 
             * （計算式）
             * 　@信用現引可能額①@ = Min((差入保証金(n) - 最低必要保証金), (受入保証金(n) - 法@定最低必要保証金))
             */

            //計算①@（=差入保証金(n) - 最低必要保証金）
            double l_dblTmp1 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getMinMarginDeposit();
            //計算②（=受入保証金(n) - 法@定最低必要保証金）
            double l_dblTmp2 =
                this.calcReceiptMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getLegalMinMarginDeposit();

            //信用現引可能額①@に計算①@と計算②の小さい方を代入
            double l_dblActualReceiptTradingPower1 = Math.min(l_dblTmp1, l_dblTmp2);
            BigDecimal l_bdActualReceiptTradingPower1 =
                new BigDecimal(l_dblActualReceiptTradingPower1);

            /*
             * （余力計算代用掛目+保証金維持率）を計算する。
             */
            int l_intTotalRate =
                this.calcCondition.getSubstituteRate()
                    + this.calcCondition.getMarginMentenanceRate();

            //信用現引可能額①@が0より小さい時
            if (l_bdActualReceiptTradingPower1.compareTo(new BigDecimal(0.0)) < 0)
            {
                /*
                 * 信用現引可能額を計算する。
                 * ⇒代用証券への振り替りを考慮しない現引可能額の計算を行う
                 * 
                 * （計算式）
                 * 　@信用現引可能額 = Min(信用現引可能額①@, 受入保証金(n) - 未決済建玉代金(n) * 保証金維持率, 使用可能現金(n))
                 */

                /*
                 * 信用現引可能額②（= 受入保証金(n) - 未決済建玉代金(n) * (保証金維持率 / 100) ）を計算する。
                 */

                //保証金維持率
                BigDecimal l_bdMarginMentenanceRate =
                    new BigDecimal(this.calcCondition.getMarginMentenanceRate());

                //計算①@（= 未決済建玉代金(n) * 保証金維持率 / 100）
                BigDecimal l_bdTmp1 =
                    l_bdContractAmount.multiply(l_bdMarginMentenanceRate).divide(
                        new BigDecimal(100.0),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //信用現引可能額②（= 受入保証金(n) - 計算①@）
                double l_dblActualReceiptTradingPower2 =
                    this.calcReceiptMarginDeposit(l_intSpecifiedPoint) - l_bdTmp1.doubleValue();

                /*
                 * 信用現引可能額に、信用現引可能額①@、計算②の結果、使用可能現金(n)の内、最小の値を代入する。 
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(l_dblActualReceiptTradingPower1, l_dblActualReceiptTradingPower2);

                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower,
                        this.calcActualAccountBalance(l_intSpecifiedPoint));

                //少数点以下を切り捨てる。
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
            //(信用現引可能額①@が0以上　@かつ　@（余力計算代用掛目+保証金維持率）が100（％）より小さい時
            else if (
                l_bdActualReceiptTradingPower1.compareTo(new BigDecimal(0.0)) >= 0
                    && l_intTotalRate < 100)
            {
                /*
                 * 信用現引可能額を計算する。
                 * ⇒維持率割れ、代用証券への振り替りを考慮した現引可能額の計算を行う
                 * 
                 * （計算式）
                 * 　@信用現引可能額(n) = Min(信用現引可能額①@ / (1 - (余力計算代用掛目 / 100)),
                 *              (受入保証金(n)  - 必要保証金(n)) / (1 - ( (余力計算代用掛目 + 保証金維持率) / 100) ),使用可能現金(n))
                 */

                /*
                 * 信用現引可能額②（= 信用現引可能額①@ / (1 - (余力計算代用掛目 / 100))）を計算する。
                 */

                //余力計算代用掛目
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //計算①@（ = 信用現引可能額①@ / (1 - 余力計算代用掛目) / 100）             
                BigDecimal l_bdTmp1 =
                    l_bdActualReceiptTradingPower1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //信用現引可能額②
                double l_dblActualReceiptTradingPower2 = l_bdTmp1.doubleValue();

                /*
                 * 信用現引可能額③（= (受入保証金(n)  - 必要保証金(n)) / (1 - ( (余力計算代用掛目 + 保証金維持率) / 100) )）を計算する。
                 */

                //計算①@（= 受入保証金(n) - 必要保証金(n)）
                l_dblTmp1 =
                    this.calcReceiptMarginDeposit(l_intSpecifiedPoint)
                        - this.getMarginDeposit(l_intSpecifiedPoint);
                l_bdTmp1 = new BigDecimal(l_dblTmp1);

                //(余力計算代用掛目 + 保証金維持率)
                BigDecimal l_bdTotalRate = new BigDecimal(l_intTotalRate);

                //計算②（= 計算①@ / 1 - (余力計算代用掛目 + 保証金維持率) / 100）
                BigDecimal l_bdTmp2 =
                    l_bdTmp1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdTotalRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //信用現引可能額③
                double l_dblActualReceiptTradingPower3 = l_bdTmp2.doubleValue();

                /*
                 * 信用現引可能額に、信用現引可能額②、信用現引可能額③、使用可能現金(n)の内、最小の値を代入する。
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(l_dblActualReceiptTradingPower2, l_dblActualReceiptTradingPower3);

                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower,
                        this.calcActualAccountBalance(l_intSpecifiedPoint));

                //少数点以下を切り捨てる。
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
            //それ以外
            else
            {
                /*
                 * 信用現引可能額を計算する。
                 * ⇒維持率割れを考慮せず、代用証券への振り替わりを考慮した現引可能額の計算を行う
                 * 
                 * （計算式）
                 * 　@信用現引可能額 = Min(信用現引可能額①@ / (1 - (余力計算代用掛目 / 100) ), 使用可能現金(n))
                 */

                /*
                 * 信用現引可能額②（= 信用現引可能額①@ / (1 - (余力計算代用掛目 / 100))）を計算する。
                 */

                //余力計算代用掛目
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //計算①@（= 信用現引可能額①@ / ( 1 - (余力計算代用掛目 / 100))              
                BigDecimal l_bdTmp1 =
                    l_bdActualReceiptTradingPower1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //信用現引可能額②
                double l_dblActualReceiptTradingPower2 = l_bdTmp1.doubleValue();

                /*
                 * 信用現引可能額に、信用現引可能額②、使用可能現金(n)の内、最小の値を代入する。
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower2,
                        this.calcActualAccountBalance(l_intSpecifiedPoint));

                //少数点以下を切り捨てる。
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
        }
        //それ以外
        else
        {
            //信用現引可能額(n) = 使用可能現金(n)
            l_dblActualReceiptTradingPower = this.calcActualAccountBalance(l_intSpecifiedPoint);
        }

        //計算した信用現引可能額を返却する。
        return l_dblActualReceiptTradingPower;
    }

    /**
     * （calc信用現引可能額<日計り拘束金考慮>）<BR>
     * 
     * 引数で指定された指定日(=n)の「信用現引余力<日計り拘束金考慮>」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A5F9035A
     */
    public double calcActualReceiptTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcActualReceiptTradingPowerIncDayTrade(int)");
        }

        // “信用現引余力の追証余力チェック”を取得する
        String l_strInstBranCalcCondition = 
            this.calcCondition.getInstBranCalcCondition(
                WEB3TPCalcCondition.ACTUALRECEIPT_MARGINCALLPOWER_CHECK);

        // 追証余力(n)を計算する
        double l_dblMarginCallPower = this.calcMarginCallPower(l_intSpecifiedPoint);

        //信用現引可能額
        double l_dblActualReceiptTradingPower;

        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>(BigDecimal型)
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.getContractAmountDayRepay(l_intSpecifiedPoint));

        // 追証余力 (n) < 0場合 かつ 信用現引余力の追証余力チェック = 1
        if (l_dblMarginCallPower < 0 &&
            WEB3TPActualReceiptMargincallPowerCheckDef.EXECUTE.equals(l_strInstBranCalcCondition))
        {
            l_dblActualReceiptTradingPower = 0;
        }
        else if (l_bdContractAmount.compareTo(new BigDecimal(0.0)) > 0)
        {
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>が0より大きい時

            /*
             * 信用現引可能額①@を計算する。
             * ⇒現引後、保証金割れ、代用証券への振り替えを考慮しない
             * 
             * （計算式）
             * 　@信用現引可能額①@ = Min((差入保証金(n) - 最低必要保証金), (受入保証金(n) - 法@定最低必要保証金))
             */

            //計算①@（=差入保証金(n) - 最低必要保証金）
            double l_dblTmp1 =
                this.calcPaidMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getMinMarginDeposit();
            //計算②（=受入保証金(n) - 法@定最低必要保証金）
            double l_dblTmp2 =
                this.calcReceiptMarginDeposit(l_intSpecifiedPoint)
                    - this.calcCondition.getLegalMinMarginDeposit();

            //信用現引可能額①@に計算①@と計算②の小さい方を代入
            double l_dblActualReceiptTradingPower1 = Math.min(l_dblTmp1, l_dblTmp2);
            BigDecimal l_bdActualReceiptTradingPower1 =
                new BigDecimal(l_dblActualReceiptTradingPower1);

            /*
             * （余力計算代用掛目+保証金維持率）を計算する。
             */
            int l_intTotalRate =
                this.calcCondition.getSubstituteRate()
                    + this.calcCondition.getMarginMentenanceRate();

            //信用現引可能額①@が0より小さい時
            if (l_bdActualReceiptTradingPower1.compareTo(new BigDecimal(0.0)) < 0)
            {
                /*
                 * 信用現引可能額を計算する。
                 * ⇒代用証券への振り替りを考慮しない現引可能額の計算を行う
                 * 
                 * （計算式）
                 * 　@信用現引可能額 = Min(信用現引可能額①@, 受入保証金(n) - 未決済建玉代金(n) * 保証金維持率, 引出可能現金(n))
                 */

                /*
                 * 信用現引可能額②（= 受入保証金(n) - 未決済建玉代金(n) * (保証金維持率 / 100) ）を計算する。
                 */

                //保証金維持率
                BigDecimal l_bdMarginMentenanceRate =
                    new BigDecimal(this.calcCondition.getMarginMentenanceRate());

                //計算①@（= 未決済建玉代金(n) * 保証金維持率 / 100）
                BigDecimal l_bdTmp1 =
                    l_bdContractAmount.multiply(
                        l_bdMarginMentenanceRate.divide(
                            new BigDecimal(100.0),
                            10,
                            BigDecimal.ROUND_HALF_EVEN));

                //信用現引可能額②（= 受入保証金(n) - 計算①@）
                double l_dblActualReceiptTradingPower2 =
                    this.calcReceiptMarginDeposit(l_intSpecifiedPoint) - l_bdTmp1.doubleValue();

                /*
                 * 信用現引可能額に、信用現引可能額①@、計算②の結果、引出可能現金(n)の内、最小の値を代入する。 
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(l_dblActualReceiptTradingPower1, l_dblActualReceiptTradingPower2);

                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower,
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));

                //少数点以下を切り捨てる。
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
            //(信用現引可能額①@が0以上　@かつ　@（余力計算代用掛目+保証金維持率）が100（％）より小さい時
            else if (l_intTotalRate < 100)
            {
                /*
                 * 信用現引可能額を計算する。
                 * ⇒維持率割れ、代用証券への振り替りを考慮した現引可能額の計算を行う
                 * 
                 * （計算式）
                 * 　@信用現引可能額(n) = Min(信用現引可能額①@ / (1 - (余力計算代用掛目 / 100)),
                 *              (受入保証金(n)  - 必要保証金(n)) / (1 - ( (余力計算代用掛目 + 保証金維持率) / 100) ),
                 *                                                                                                          引出可能現金(n))
                 */

                /*
                 * 信用現引可能額②（= 信用現引可能額①@ / (1 - (余力計算代用掛目 / 100))）を計算する。
                 */

                //余力計算代用掛目
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //計算①@（= 信用現引可能額①@ / (1 - (余力計算代用掛目 / 100)）              
                BigDecimal l_bdTmp1 =
                    l_bdActualReceiptTradingPower1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //信用現引可能額②
                double l_dblActualReceiptTradingPower2 = l_bdTmp1.doubleValue();

                /*
                 * 信用現引可能額③（ = (受入保証金(n) - 必要保証金(n)) / (1 - ( (余力計算代用掛目 + 保証金維持率) / 100) )）を計算する。
                 */

                //計算①@（= 受入保証金(n) - 必要保証金(n)）
                l_dblTmp1 =
                    this.calcReceiptMarginDeposit(l_intSpecifiedPoint)
                        - this.getMarginDeposit(l_intSpecifiedPoint);
                l_bdTmp1 = new BigDecimal(l_dblTmp1);

                //(余力計算代用掛目 + 保証金維持率)
                BigDecimal l_bdTotalRate = new BigDecimal(l_intTotalRate);

                //計算②（= 計算①@ / 1 - ((余力計算代用掛目 + 保証金維持率) / 100))
                BigDecimal l_bdTmp2 =
                    l_bdTmp1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdTotalRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //信用現引可能額③
                double l_dblActualReceiptTradingPower3 = l_bdTmp2.doubleValue();

                /*
                 * 信用現引可能額に、信用現引可能額②、信用現引可能額③、引出可能現金(n)の内、最小の値を代入する。
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(l_dblActualReceiptTradingPower2, l_dblActualReceiptTradingPower3);

                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower,
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));

                //少数点以下を切り捨てる。
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
            //それ以外
            else
            {
                /*
                 * 信用現引可能額を計算する。
                 * ⇒維持率割れを考慮せず、代用証券への振り替わりを考慮した現引可能額の計算を行う
                 * 
                 * （計算式）
                 * 　@信用現引可能額 = Min(信用現引可能額①@ / (1 - (余力計算代用掛目 / 100) ), 引出可能現金(n))
                 */

                /*
                 * 信用現引可能額②（= 信用現引可能額①@ / (1 - (余力計算代用掛目 / 100))）を計算する。
                 */

                //余力計算代用掛目
                BigDecimal l_bdSubstituteRate =
                    new BigDecimal(this.calcCondition.getSubstituteRate());

                //計算①@（= 信用現引可能額①@ / 1 - (余力計算代用掛目 / 100))              
                BigDecimal l_bdTmp1 =
                    l_bdActualReceiptTradingPower1.divide(
                        BigDecimal.valueOf(1).subtract(
                            l_bdSubstituteRate.divide(
                                new BigDecimal(100.0),
                                10,
                                BigDecimal.ROUND_HALF_EVEN)),
                        10,
                        BigDecimal.ROUND_HALF_EVEN);

                //信用現引可能額②
                double l_dblActualReceiptTradingPower2 = l_bdTmp1.doubleValue();

                /*
                 * 信用現引可能額に、信用現引可能額②、引出可能現金(n)の内、最小の値を代入する。
                 */
                l_dblActualReceiptTradingPower =
                    Math.min(
                        l_dblActualReceiptTradingPower2,
                        this.calcActualPaymentBalance(l_intSpecifiedPoint));

                //少数点以下を切り捨てる。
                l_dblActualReceiptTradingPower = Math.floor(l_dblActualReceiptTradingPower);
            }
        }
        //それ以外
        else
        {
            //信用現引可能額(n) = 引出可能現金(n)
            l_dblActualReceiptTradingPower = this.calcActualPaymentBalance(l_intSpecifiedPoint);
        }

        //計算した信用現引可能額を返却する。
        return l_dblActualReceiptTradingPower;
    }

    /**
     * （calcその他商品買付可能額）<BR>
     * 
     * 引数で指定された指定日(=n)の「その他商品買付可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B6A608001E
     */
    public double calcOtherTradingPower(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcOtherTradingPower(int)");
        }

        //その他商品買付可能額(n)　@=　@Min(保証金引出余力(n), 使用可能現金(n))
        double l_dblOtherTradingPower =
            Math.min(this.calcMarginDrawPower(l_intSpecifiedPoint), this.calcActualAccountBalance(l_intSpecifiedPoint));

        //計算したその他商品買付可能額を返却する。
        return l_dblOtherTradingPower;
    }

    /**
     * （calcその他商品買付可能額<日計り拘束金考慮>）<BR>
     * 
     * 引数で指定された指定日(=n)の「その他商品買付可能額<日計り拘束金考慮>」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double calcOtherTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcOtherTradingPowerIncDayTrade(int)");
        }
    
        //その他商品買付可能額(n) =　@Min(保証金引出余力(n), 引出可能現金(n))
        double l_dblOtherTradingPower =
            Math.min(
                this.calcMarginDrawPower(l_intSpecifiedPoint),
                this.calcActualPaymentBalance(l_intSpecifiedPoint));

        //計算したその他商品買付可能額を返却する。
        return l_dblOtherTradingPower;
    }

    /**
     * （calc出金可能額）<BR>
     * 
     * 最小の「出金可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFE9D30029
     */
    public WEB3TPCalcResult calcPaymentTradingPower()
    {
        //出金可能額を取得する
        WEB3TPCalcResult l_calcResult = this.calcAppliedOtherTradingPower(OrderTypeEnum.CASH_OUT);

        //出金可能額を返却する。
        return l_calcResult;
    }

    /**
     * （calc投信買付可能額）<BR>
     * 
     * 最小の「投信買付可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFEAB302F8
     */
    public WEB3TPCalcResult calcFundTradingPower()
    {
        //投信買付可能額を取得する
        WEB3TPCalcResult l_calcResult = this.calcAppliedOtherTradingPower(OrderTypeEnum.MF_BUY);

        //投信買付可能額を返却する。
        return l_calcResult;
    }

    /**
     * （calcオプション新規買建可能額）<BR>
     * 
     * 最小の「オプション新規買建可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFEAC203C3
     */
    public WEB3TPCalcResult calcOptionTradingPower()
    {
        //オプション新規建可能額を取得する
        WEB3TPCalcResult l_calcResult =
            this.calcAppliedOtherTradingPower(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);

        //オプション新規建可能額を返却する。
        return l_calcResult;
    }

    /**
     * （calc入金請求額）<BR>
     * 引数で指定された指定日(=n)の「入金請求額」を計算する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcDemandamount(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcDemandamount(int)");
        }
    
        //入金請求額(n) = Abs(Min(預り金請求余力(n),追証余力(n),0))
        double l_dblDemandAmt =
            Math.min(
                this.calcAccountBalanceDemandPower(l_intSpecifiedPoint),
                this.calcMarginCallPower(l_intSpecifiedPoint));
        l_dblDemandAmt = Math.abs(Math.min(l_dblDemandAmt, 0));
    
        return l_dblDemandAmt;
    }

    /**
     * （calc立替金）<BR>
     * 引数で指定された指定日(=n)の「立替金」を計算する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcDebitAmount(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcDemandamount(int)");
        }
    
        //立替金(n) = Abs(Min((使用可能現金(n) + 未受渡建玉現金必要保証金(n)),0))
        double l_dblDebitAmt = this.calcActualAccountBalance(l_intSpecifiedPoint)
                + this.getUndeliCashMarginDeposit(l_intSpecifiedPoint);
        l_dblDebitAmt = Math.abs(Math.min(l_dblDebitAmt, 0));

        return l_dblDebitAmt;
    }

    /**
     * （calc特別立替金）<BR>
     * 引数で指定された指定日(=n)の「特別立替金」を計算する。<BR>
     * 
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照
     * 
     * @@param l_intSpecifiedPoint<BR>
     * @@return double
     */
    public double calcSpecialDebitAmount(int l_intSpecifiedPoint)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcDemandamount(int)");
        }
    
        //立替金(n) = Abs(Min(預り金請求余力(n),0))
        double l_dblSpecialDebitAmt = Math.abs(Math.min(
                this.calcAccountBalanceDemandPower(l_intSpecifiedPoint),
                0));

        return l_dblSpecialDebitAmt;
    }

    /**
     * （calc保証金維持余力）<BR>
     * <BR>
     * 引数で指定された指定日(=n)、保証金率の「保証金維持余力」を返却する。<BR>
     * <BR>
     * @@param l_intSpecifiedPoint
     * @@param l_marginDepositRate
     * @@return double
     */
    public double calcMarginMaintenancePower(int l_intSpecifiedPoint, int l_marginDepositRate)
    {
        //パラメータ.指定日がT+0より小さい　@または　@T+5より大きい時
        if (l_intSpecifiedPoint < WEB3TPSpecifiedPointDef.T_0
            || l_intSpecifiedPoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcMarginMaintenancePower(int, int)");
        }
    
        //保証金維持余力
        double l_dblMarginMaintenancePower;
    
        //受入保証金
        double l_dblReceiptMarginDeposit = calcReceiptMarginDeposit(l_intSpecifiedPoint);
        
        //未決済建玉代金<日計り返済・現引現渡建玉代金考慮>
        double l_dblContractAmountDayPepay = getContractAmountDayRepay(l_intSpecifiedPoint);
    
        //保証金維持余力 =  受入保証金 - ( 未決済建玉代金<日計り返済・現引現渡建玉代金考慮> * 保証金率 / 100 )
        l_dblMarginMaintenancePower =
            l_dblReceiptMarginDeposit - (l_dblContractAmountDayPepay * l_marginDepositRate / 100);
    
        return l_dblMarginMaintenancePower;
    }

    /**
     * （calc適用保証金預託率）<BR>
     * 
     * 最小の「保証金預託率」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DA90AC006C
     */
    public WEB3TPCalcResult calcAppliedMarginDepositRate()
    {
        /*
         * 保証金預託率を取得する。
         */
    
        //保証金預託率（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblMarginDepositRate = Double.MAX_VALUE;
        //保証金預託率(BigDecimal型)
        BigDecimal l_bdMarginDepositRate = new BigDecimal(l_dblMarginDepositRate);
        
        //適用新規建可能額を求める(新規建可能額の適用日と同期をとるため)
        WEB3TPCalcResult l_appliedMarginTpResult = this.calcAppliedMarginTradingPower();
        
        //適用日を取得する
        int l_intAppliedPoint = l_appliedMarginTpResult.appliedPoint;
        
        //calc保証金預託率(適用日の預託率を求める)
        double l_dblTradingPower = this.calcMarginDepositRate(l_intAppliedPoint);
        
        /*
         * 余力計算結果オブジェクトに計算結果をセットし返却する。
         */
    
        //余力計算結果に計算結果を代入する。
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_dblTradingPower;
        l_calcResult.orderTypeEnum = null;
    
        //余力計算結果を返却する。
        return l_calcResult;
    }

    /**
     * （calc適用保証金預託率）<BR>
     * <BR>
     * 引数.基準日以降最小の「保証金預託率」を計算する。<BR>
     * <BR>
     * １）余力計算基準日<信用新規建>を退避する。<BR>
     * 　@－this.余力計算条件.get余力計算基準日<信用新規建>()をコール<BR>
     * <BR>
     * ２）余力計算基準日<信用新規建>に、引数.基準日をセットする。<BR>
     * 　@－this.余力計算条件.set余力計算基準日<信用新規建>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@int：引数.基準日<BR>
     * <BR>
     * ３）引数.基準日以降、最小の保証金預託率を計算する<BR>
     * 　@－this.calc適用保証金預託率()をコール<BR>
     * <BR>
     * ４）退避していた、基準日を戻す<BR>
     * 　@－this.余力計算条件.set余力計算基準日<信用新規建>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@int：１）の戻り値<BR>
     * <BR>
     * ５）３）の戻り値を返却する。<BR>
     * <BR>
     * @@param l_intBasePoint<BR>
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedMarginDepositRate(int l_intBasePoint)
    {
        //引数.基準日がT+0より小さい時
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedMarginDepositRate(int)");
        }
        
        //余力計算基準日<信用新規建>を退避する。
        int l_intOriginalBasePoint = this.calcCondition.getMarginBasePoint();

        /*
         * 余力計算基準日<信用新規建>に、引数.基準日をセットする
         */
        //引数.基準日がT+5より大きい場合
        if(l_intBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5をセット
            this.calcCondition.setMarginBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //以外の場合
        else
        {
            //引数.基準日をセット
            this.calcCondition.setMarginBasePoint(l_intBasePoint);
        }

        //引数.基準日以降、最小の保証金預託率を計算する
        WEB3TPCalcResult l_result = this.calcAppliedMarginDepositRate();

        //退避していた、基準日を戻す。
        this.calcCondition.setMarginBasePoint(l_intOriginalBasePoint);

        //計算結果を返却
        return l_result;
    }

    /**
     * （calc適用株式買付可能額）<BR>
     * 
     * 最小の「株式買付可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40BFFEA201B5
     */
    public WEB3TPCalcResult calcAppliedEquityTradingPower()
    {
        /*
         * 余力計算基準日<株式/信用現引>以降で
         * 最小の株式買付可能額と適用日を取得する。
         */
        //株式買付可能額（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblEquityTradingPower = Double.MAX_VALUE;
        //株式買付可能額(BigDecimal型)
        BigDecimal l_bdEquityTradingPower = new BigDecimal(l_dblEquityTradingPower);
        //適用日
        int l_intAppliedPoint = 0;
    
        //余力計算基準日<株式買付／信用現引>
        int l_intBasePoint = this.calcCondition.getEquityBasePoint();
    
        //指定日の範囲（余力計算基準日<株式買付／信用現引>～T+5）でループ
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //株式買付可能額(項番)(BigDecimal型)
            BigDecimal l_bdCurEquityTradingPower;
    
            //項番が余力計算基準日<株式買付／信用現引>と等しい時            
            if (index == l_intBasePoint)
            {
                //株式買付可能額を計算する。
                l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
            }
            //それ以外
            else
            {
                //株式買付可能額<日計り拘束金考慮>を計算する。
                l_bdCurEquityTradingPower =
                    new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
            }
    
            //株式買付可能額(項番)が株式買付可能額より小さい時
            if (l_bdCurEquityTradingPower.compareTo(l_bdEquityTradingPower) < 0)
            {
                //株式買付可能額に株式買付可能額(項番)を代入する。
                l_bdEquityTradingPower = l_bdCurEquityTradingPower;
                //適用日に項番を代入する。
                l_intAppliedPoint = index;
            }
        }

        //［a. 株式買付可能額　@< 0の場合］
        if (l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }
        

        /*
         * 預り金請求余力チェック
         */
        //預り金請求余力（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //預り金請求余力(BigDecimal型)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //適用日<預り金請求余力>
        int l_intActPayAppliedPoint = 0;

        //指定日の範囲（T+0～余力計算基準日<株式買付／信用現引>-1）でループ
        for(int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //預り金請求余力(項番)(BigDecimal型)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcAccountBalanceDemandPower(index));

            //預り金請求余力(項番)が預り金請求余力より小さい時
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //預り金請求余力に預り金請求余力(項番)を代入する。
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //適用日に項番を代入する。
                l_intActPayAppliedPoint = index;
            }
        }

        //［a. 預り金請求余力 < 0の場合］
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.株式買付可能額 >= 0 の場合]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.株式買付可能額 < 0 かつ 適用日 > 適用日<預り金請求余力> の場合]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
        }
        
        /*
         * 追証余力チェック
         */
        //発注日<株式／信用>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //追証余力<適用可能額チェック用>を取得
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                OrderTypeEnum.EQUITY_BUY, l_intBizDate, l_intBasePoint);

        //［a. 追証余力 < 0の場合］
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.株式買付可能額 >= 0 の場合]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.株式買付可能額 < 0 かつ 適用日 > 適用日<追証余力> の場合]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }
    
        /*
         * 取引停止中チェック
         */
        //取引停止区分 == true または、その他商品買付余力区分 == true の場合
        if(this.calcCondition.isTradingStop() == true
                || this.calcCondition.isOtherTradingStop() == true)
        {
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }

        /*
         * 余力計算結果オブジェクトに計算結果をセットし返却する。
         */
        //余力計算結果に計算結果を代入する。
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdEquityTradingPower.doubleValue();
        l_calcResult.orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
    
        //余力計算結果を返却する。
        return l_calcResult;
    }

    /**
     * （calc適用株式買付可能額<即日入金対象銘柄>）<BR>
     * <BR>
     * 最小の「株式買付可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     */
    public WEB3TPCalcResult calcAppliedEquityTradingPowerTodayDepFund()
    {
        /*
         * 余力計算基準日<株式/信用現引>以降で
         * 最小の株式買付可能額と適用日を取得する。
         */
        //株式買付可能額（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblEquityTradingPower = Double.MAX_VALUE;
        //株式買付可能額(BigDecimal型)
        BigDecimal l_bdEquityTradingPower = new BigDecimal(l_dblEquityTradingPower);
        //適用日
        int l_intAppliedPoint = 0;
    
        //余力計算基準日<株式買付／信用現引>
        int l_intBasePoint = this.calcCondition.getEquityBasePoint();
    
        //指定日の範囲（T+0～余力計算基準日<株式買付／信用現引>-1）でループ
        for (int index = WEB3TPSpecifiedPointDef.T_0; index <= l_intBasePoint-1; index++)
        {
            //株式買付可能額(項番)(BigDecimal型)
            BigDecimal l_bdCurEquityTradingPower;
    
            //項番がT+0と等しい時            
            if (index == WEB3TPSpecifiedPointDef.T_0)
            {
                //株式買付可能額を計算する。
                l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
            }
            //それ以外
            else
            {
                //株式買付可能額<日計り拘束金考慮>を計算する。
                l_bdCurEquityTradingPower =
                    new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
            }
    
            //株式買付可能額(項番)が株式買付可能額より小さい時
            if (l_bdCurEquityTradingPower.compareTo(l_bdEquityTradingPower) < 0)
            {
                //株式買付可能額に株式買付可能額(項番)を代入する。
                l_bdEquityTradingPower = l_bdCurEquityTradingPower;
                //適用日に項番を代入する。
                l_intAppliedPoint = index;
            }
        }
    
        //指定日の範囲（余力計算基準日<株式買付／信用現引>～T+5）でループ
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //株式買付可能額(項番)(BigDecimal型)
            BigDecimal l_bdCurEquityTradingPower;
    
            //項番が余力計算基準日<株式買付／信用現引>と等しい時            
            if (index == l_intBasePoint)
            {
                //株式買付可能額を計算する。
                l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
            }
            //それ以外
            else
            {
                //株式買付可能額<日計り拘束金考慮>を計算する。
                l_bdCurEquityTradingPower =
                    new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
            }
    
            //株式買付可能額(項番)が株式買付可能額より小さい時
            if (l_bdCurEquityTradingPower.compareTo(l_bdEquityTradingPower) < 0)
            {
                //株式買付可能額に株式買付可能額(項番)を代入する。
                l_bdEquityTradingPower = l_bdCurEquityTradingPower;
                //適用日に項番を代入する。
                l_intAppliedPoint = index;
            }
        }
    
        //［a. 株式買付可能額　@< 0の場合］
        if (l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }
        

        /*
         * 預り金請求余力チェック
         */
        //預り金請求余力（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //預り金請求余力(BigDecimal型)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //適用日<預り金請求余力>
        int l_intActPayAppliedPoint = 0;

        //指定日の範囲（T+0～余力計算基準日<株式買付／信用現引>-1）でループ
        for(int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //預り金請求余力(項番)(BigDecimal型)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcAccountBalanceDemandPower(index));

            //預り金請求余力(項番)が預り金請求余力より小さい時
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //預り金請求余力に預り金請求余力(項番)を代入する。
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //適用日に項番を代入する。
                l_intActPayAppliedPoint = index;
            }
        }

        //［a. 預り金請求余力 < 0の場合］
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.株式買付可能額 >= 0 の場合]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.株式買付可能額 < 0 かつ 適用日 > 適用日<預り金請求余力> の場合]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
        }
        
        /*
         * 追証余力チェック
         */
        //発注日<株式／信用>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //追証余力<適用可能額チェック用>を取得
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                OrderTypeEnum.EQUITY_BUY, l_intBizDate, l_intBasePoint);

        //［a. 追証余力 < 0の場合］
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.株式買付可能額 >= 0 の場合]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.株式買付可能額 < 0 かつ 適用日 > 適用日<追証余力> の場合]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }
    
        /*
         * 取引停止中チェック
         */
        //取引停止区分 == true または、その他商品買付余力区分 == true の場合
        if(this.calcCondition.isTradingStop() == true
                || this.calcCondition.isOtherTradingStop() == true)
        {
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }

        /*
         * 余力計算結果オブジェクトに計算結果をセットし返却する。
         */
        //余力計算結果に計算結果を代入する。
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdEquityTradingPower.doubleValue();
        l_calcResult.orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
    
        //余力計算結果を返却する。
        return l_calcResult;
    }

    /**
     * （calc適用株式買付可能額）<BR>
     * <BR>
     * 引数.基準日以降最小の「株式買付可能額」を計算する。<BR>
     * <BR>
     * １）余力計算基準日<現物株式/信用現引>を退避する。<BR>
     * 　@－this.余力計算条件.get余力計算基準日<現物株式/信用現引>()をコール<BR>
     * <BR>
     * ２）余力計算基準日<現物株式/信用現引>に、引数.基準日をセットする。<BR>
     * 　@－this.余力計算条件.set余力計算基準日<現物株式/信用現引>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@int：引数.基準日<BR>
     * <BR>
     * ３）引数.基準日以降、最小の株式買付可能額を計算する<BR>
     * 　@－this.calc適用株式買付可能額()をコール<BR>
     * <BR>
     * ４）退避していた、基準日を戻す<BR>
     * 　@－this.余力計算条件.set余力計算基準日<現物株式/信用現引>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@int：１）の戻り値<BR>
     * <BR>
     * ５）３）の戻り値を返却する。<BR>
     * <BR>
     * @@param l_intBasePoint<BR>
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedEquityTradingPower(int l_intBasePoint)
    {
        //引数.基準日がT+0より小さい時
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedEquityTradingPower(int)");
        }
        
        //余力計算基準日<現物株式/信用現引>を退避する。
        int l_intOriginalBasePoint = this.calcCondition.getEquityBasePoint();

        /*
         * 余力計算基準日<現物株式/信用現引>に、引数.基準日をセットする
         */
        //引数.基準日がT+5より大きい場合
        if(l_intBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5をセット
            this.calcCondition.setEquityBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //以外の場合
        else
        {
            //引数.基準日をセット
            this.calcCondition.setEquityBasePoint(l_intBasePoint);
        }

        //引数.基準日以降、最小の株式買付可能額を計算する
        WEB3TPCalcResult l_result = this.calcAppliedEquityTradingPower();

        //退避していた、基準日を戻す。
        this.calcCondition.setEquityBasePoint(l_intOriginalBasePoint);

        //計算結果を返却
        return l_result;
    }

    /**
     * （calc適用信用新規建可能額）<BR>
     * 
     * 最小の「信用新規建可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40BFFA8B03D2
     */
    public WEB3TPCalcResult calcAppliedMarginTradingPower()
    {
        /*
         * 信用新規建可能額を取得する。
         */
        //信用新規建可能額（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblMarginTradingPower = Double.MAX_VALUE;
        //信用新規建可能額(BigDecimal型)
        BigDecimal l_bdMarginTradingPower = new BigDecimal(l_dblMarginTradingPower);
        //適用日
        int l_intAppliedPoint = 0;

        //基準日 = 余力計算基準日<信用新規建>
        int l_intBasePoint = this.calcCondition.getMarginBasePoint();

        //指定日の範囲（余力計算基準日<信用新規建>～T+5）でループ
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //信用新規建可能額(項番)(BigDecimal型)
            BigDecimal l_bdCurMarginTradingPower =
                new BigDecimal(this.calcMarginTradingPower(index));
    
            //信用新規建可能額(項番)が信用新規建可能額より小さい時
            if (l_bdCurMarginTradingPower.compareTo(l_bdMarginTradingPower) < 0)
            {
                //信用新規建可能額に信用新規建可能額(項番)を代入
                l_bdMarginTradingPower = l_bdCurMarginTradingPower;
                //適用日に項番を代入
                l_intAppliedPoint = index;
            }
        }
    
        /*
         * 信用新規建可能額を総建玉代金上限値の範囲内に調整する
         */
        //総建玉上限値（=総建玉代金上限値 - 未決済建玉代金(適用日)）を計算する。
        BigDecimal l_bdMaxContPrice =
            new BigDecimal(
                this.calcCondition.getMaxContPrice()
                    - this.getContractAmount(l_intAppliedPoint));
    
        //信用新規建可能額に信用新規建可能額と総建玉上限値の小さい方を代入
        l_bdMarginTradingPower = l_bdMarginTradingPower.min(l_bdMaxContPrice);
    
        /*
         * 差入保証金、受入保証金がそれぞれ基準値を満たしているかどうかの判別を行う
         */
        //余力計算基準日<信用新規建> >= T+1 場合
        if (this.calcCondition.getMarginBasePoint() >= WEB3TPSpecifiedPointDef.T_1)
        {

            //差入保証金(余力計算基準日<発注日>)
            BigDecimal l_bdPaidMarginDeposit =
                new BigDecimal(
                    this.calcPaidMarginDeposit(this.calcCondition.getEquityBizDate()));

            //最低必要保証金
            BigDecimal l_bdMinMarginDeposit =
                new BigDecimal(this.calcCondition.getMinMarginDeposit());

            //受入保証金(余力計算基準日<発注日>)
            BigDecimal l_bdReceiptMarginDeposit =
                new BigDecimal(
                    this.calcReceiptMarginDeposit(this.calcCondition.getEquityBizDate()));

            //法@定最低必要保証金
            BigDecimal l_bdLegalMinMarginDeposit =
                new BigDecimal(this.calcCondition.getLegalMinMarginDeposit());

            //差入保証金が最低必要保証金より小さい　@または　@受入保証金が法@定最低必要保証金より小さい時
            if (l_bdPaidMarginDeposit.compareTo(l_bdMinMarginDeposit) < 0
                || l_bdReceiptMarginDeposit.compareTo(l_bdLegalMinMarginDeposit) < 0)
            {
                //信用新規建可能額に-1を代入
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<信用新規建>
                l_intAppliedPoint = this.calcCondition.getEquityBizDate();
            }
        }
    
        //信用新規建可能額が0より小さい時
        if (l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            //信用新規建可能額に-1を代入
            l_bdMarginTradingPower = new BigDecimal(-1.0);
        }

        /*
         * 預り金請求余力チェック
         */
        //発注日<株式／信用>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //預り金請求余力<適用可能額チェック用>を取得
        WEB3TPCalcResult l_marginActPay = this
            .calcAccountBalanceDemandPowerForCheck(
                    OrderTypeEnum.MARGIN_LONG, l_intBizDate, l_intBasePoint);

        //［a.預り金請求余力 < 0の場合］
        if(l_marginActPay != null && l_marginActPay.tradingPower < 0)
        {
            //[b.信用新規建可能額 >= 0 の場合]
            if(l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //信用新規建可能額 = -1
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_marginActPay.appliedPoint;
            }
            //[b.信用新規建可能額 < 0 かつ 適用日 > 適用日<預り金請求余力> の場合]
            else if(l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginActPay.appliedPoint)
            {
                //信用新規建可能額 = -1
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_marginActPay.appliedPoint;
            }
        }

        /*
         * 追証余力チェック
         */
        //追証余力<適用可能額チェック用>を取得
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                OrderTypeEnum.MARGIN_LONG, l_intBizDate, l_intBasePoint);

        //［a. 追証余力 < 0の場合］
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.信用新規建可能額 >= 0 の場合]
            if(l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //信用新規建可能額 = -1
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.信用新規建可能額 < 0 かつ 適用日 > 適用日<追証余力> の場合]
            else if(l_bdMarginTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //信用新規建可能額 = -1
                l_bdMarginTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }
        
        /*
         * 取引停止中チェック
         */
        //取引停止区分 == true または、信用新規建余力区分 == true　@の場合
        if(this.calcCondition.isTradingStop() == true
                || this.calcCondition.isMarginOpenPositionStop() == true)
        {
            //信用新規建可能額に-1を代入
            l_bdMarginTradingPower = new BigDecimal(-1.0);
        }
        
        /*
         * 余力計算結果オブジェクトに計算結果をセットし返却する。
         */
        //余力計算結果に計算結果を代入する。
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdMarginTradingPower.doubleValue();
        l_calcResult.orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
    
        //余力計算結果を返却する。
        return l_calcResult;
    }

    /**
     * （calc適用信用新規建可能額）<BR>
     * <BR>
     * 引数.基準日以降最小の「信用新規建可能額」を計算する。<BR>
     * <BR>
     * １）余力計算基準日<信用新規建>を退避する。<BR>
     * 　@－this.余力計算条件.get余力計算基準日<信用新規建>()をコール<BR>
     * <BR>
     * ２）余力計算基準日<信用新規建>に、引数.基準日をセットする。<BR>
     * 　@－this.余力計算条件.set余力計算基準日<信用新規建>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@int：引数.基準日<BR>
     * <BR>
     * ３）引数.基準日以降、最小の信用新規建可能額を計算する<BR>
     * 　@－this.calc適用信用新規建可能額()をコール<BR>
     * <BR>
     * ４）退避していた、基準日を戻す<BR>
     * 　@－this.余力計算条件.set余力計算基準日<信用新規建>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@int：１）の戻り値<BR>
     * <BR>
     * ５）３）の戻り値を返却する。<BR>
     * <BR>
     * @@param l_intBasePoint<BR>
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedMarginTradingPower(int l_intBasePoint)
    {
        //引数.基準日がT+0より小さい時
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedMarginTradingPower(int)");
        }
        
        //余力計算基準日<信用新規建>を退避する。
        int l_intOriginalBasePoint = this.calcCondition.getMarginBasePoint();

        /*
         * 余力計算基準日<信用新規建>に、引数.基準日をセットする
         */
        //引数.基準日がT+5より大きい場合
        if(l_intBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5をセット
            this.calcCondition.setMarginBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //以外の場合
        else
        {
            //引数.基準日をセット
            this.calcCondition.setMarginBasePoint(l_intBasePoint);
        }

        //引数.基準日以降、最小の信用新規建買付可能額を計算する
        WEB3TPCalcResult l_result = this.calcAppliedMarginTradingPower();

        //退避していた、基準日を戻す。
        this.calcCondition.setMarginBasePoint(l_intOriginalBasePoint);

        //計算結果を返却
        return l_result;
    }

    /**
     * (calc適用信用新規建可能額<増担保規制銘柄>)<BR>
     * 引数.基準日以降最小の「(増担保)規制信用新規建可能額」を計算する。<BR>
     * <BR>
     * １）(部店)保証金率/(部店)現金保証金率を退避する。<BR>
     * 　@－this.余力計算条件.get保証金率()をコール<BR>
     * 　@－this.余力計算条件.get現金保証金率()をコール<BR>
     * <BR>
     * ２）指定された保証金率/現金保証金率を余力計算条件にセットする。<BR>
     * 　@－this.余力計算条件.set保証金率(引数.保証金率:int)をコール<BR>
     * 　@－this.余力計算条件.set現金保証金率(引数.現金保証金率:int)をコール<BR>
     * <BR>
     * ３）最小の信用新規建可能額を計算する<BR>
     * 　@－this.calc適用信用新規建可能額()をコール<BR>
     * <BR>
     * ４）退避していた、(部店)保証金率/(部店)現金保証金率を戻す<BR>
     * 　@－this.余力計算条件.set保証金率(１）の(部店)保証金率:int)をコール<BR>
     * 　@－this.余力計算条件.set現金保証金率(１）の(部店)現金保証金率:int)をコール<BR>
     * <BR>
     * ５）３）の戻り値を返却する。<BR>
     * <BR>
     * @@param l_intMarginDepRate
     * @@param l_intCashMarginDepRate
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedMarginTradingPowerIncDeposit(
        int l_intMarginDepRate,
        int l_intCashMarginDepRate)
    {
        /*
         * (部店)保証金率/(部店)現金保証金率を退避する
         */
        //(部店)保証金率
        int l_intOriginalMarginDepRate = this.calcCondition.getMarginDepositRate();
        //(部店)現金保証金率
        int l_intOriginalCashMarginDepRate = this.calcCondition.getCashMarginDepositRate();

        /*
         * 指定された保証金率/現金保証金率を余力計算条件にセットする。
         */
        this.calcCondition.setMarginDepositRate(l_intMarginDepRate);
        this.calcCondition.setCashMarginDepositRate(l_intCashMarginDepRate);

        /*
         * 最小の信用新規建可能額を計算する
         */
        WEB3TPCalcResult l_result = this.calcAppliedMarginTradingPower();

        /*
         * 退避していた、(部店)保証金率/(部店)現金保証金率を戻す
         */
        this.calcCondition.setMarginDepositRate(l_intOriginalMarginDepRate);
        this.calcCondition.setCashMarginDepositRate(l_intOriginalCashMarginDepRate);

        //計算結果を返却
        return l_result;
    }
    
    /**
     * （calc適用信用現引可能額）<BR>
     * 
     * 最小の「信用現引可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40C4365C031C
     * 
     */
    public WEB3TPCalcResult calcAppliedActualReceiptTradingPower()
    {
        /*
         * 信用現引可能額を取得する。
         */
        //信用現引可能額（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualReceiptTradingPower = Double.MAX_VALUE;
        //信用現引可能額(BigDecimal型)
        BigDecimal l_bdActualReceiptTradingPower = new BigDecimal(l_dblActualReceiptTradingPower);
        //適用日
        int l_intAppliedPoint = 0;
    
        //余力計算基準日<株式買付/信用現引>
        int l_intBasePoint = this.calcCondition.getEquityBasePoint();
    
        //指定日の範囲（余力計算基準日<株式買付／信用現引>～T+5）でループ
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //信用現引可能額(項番)(BigDecimal型)
            BigDecimal l_bdCurActualReceiptTradingPower;
    
            //項番が余力計算基準日<株式買付／信用現引>と等しい時            
            if (index == l_intBasePoint)
            {
                //信用現引可能額を取得
                l_bdCurActualReceiptTradingPower =
                    new BigDecimal(this.calcActualReceiptTradingPower(index));
            }
            //それ以外
            else
            {
                //信用現引可能額<日計り拘束金考慮>を取得
                l_bdCurActualReceiptTradingPower =
                    new BigDecimal(this.calcActualReceiptTradingPowerIncDayTrade(index));
            }
    
            //信用現引可能額(項番)が信用現引可能額より小さい時
            if (l_bdCurActualReceiptTradingPower.compareTo(l_bdActualReceiptTradingPower) < 0)
            {
                //信用現引可能額に信用現引可能額(項番)を代入
                l_bdActualReceiptTradingPower = l_bdCurActualReceiptTradingPower;
                //適用日に項番を代入
                l_intAppliedPoint = index;
            }
        }
    
        //［a. 信用現引可能額　@< 0の場合］
        if (l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
        }

        /*
         * 預り金請求余力チェック
         */
        //預り金請求余力（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //預り金請求余力(BigDecimal型)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(
                l_dblActualPaymentBalance);
        //適用日<預り金請求余力>
        int l_intActPayAppliedPoint = 0;

        //指定日の範囲（T+0～余力計算基準日<株式買付／信用現引>-1）でループ
        for(int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //預り金請求余力(項番)(BigDecimal型)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcAccountBalanceDemandPower(index));

            //預り金請求余力(項番)が預り金請求余力より小さい時
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //預り金請求余力に預り金請求余力(項番)を代入する。
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //適用日に項番を代入する。
                l_intActPayAppliedPoint = index;
            }
        }

        //［a. 預り金請求余力 < 0の場合］
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.信用現引可能額 >= 0 の場合]
            if(l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //信用現引可能額 = -1
                l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.信用現引可能額 < 0 かつ 適用日 > 適用日<預り金請求余力> の場合]
            else if(l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //信用現引可能額 = -1
                l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
        }
        
        /*
         * 追証余力チェック
         */
        //発注日<株式／信用>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //追証余力<適用可能額チェック用>を取得
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                OrderTypeEnum.SWAP_MARGIN_LONG, l_intBizDate, l_intBasePoint);

        //［a. 追証余力 < 0の場合］
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.信用現引可能額 >= 0 の場合]
            if(l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //信用現引可能額 = -1
                l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.信用現引可能額 < 0 かつ 適用日 > 適用日<追証余力> の場合]
            else if(l_bdActualReceiptTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //信用現引可能額 = -1
                l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }
    
        /*
         * 取引停止中チェック
         */
        //取引停止区分 == true または、その他商品買付余力区分 == true　@の場合
        if(this.calcCondition.isTradingStop() == true
                || this.calcCondition.isOtherTradingStop() == true)
        {
            //信用現引可能額に-1を代入
            l_bdActualReceiptTradingPower = new BigDecimal(-1.0);
        }

        /*
         * 余力計算結果オブジェクトに計算結果をセットし返却する。
         */
        //余力計算結果に計算結果を代入する。
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdActualReceiptTradingPower.doubleValue();
        l_calcResult.orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
    
        //余力計算結果を返却する。
        return l_calcResult;
    }

    /**
     * （calc適用信用現引可能額）<BR>
     * <BR>
     * 引数.基準日以降最小の「信用現引可能額」を計算する。<BR>
     * <BR>
     * １）余力計算基準日<現物株式/信用現引>を退避する。<BR>
     * 　@－this.余力計算条件.get余力計算基準日<現物株式/信用現引>()をコール<BR>
     * <BR>
     * ２）余力計算基準日<現物株式/信用現引>に、引数.基準日をセットする。<BR>
     * 　@－this.余力計算条件.set余力計算基準日<現物株式/信用現引>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@int：引数.基準日<BR>
     * <BR>
     * ３）引数.基準日以降、最小の信用現引可能額を計算する<BR>
     * 　@－this.calc適用信用現引可能額()をコール<BR>
     * <BR>
     * ４）退避していた、基準日を戻す<BR>
     * 　@－this.余力計算条件.set余力計算基準日<現物株式/信用現引>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@　@int：１）の戻り値<BR>
     * <BR>
     * ５）３）の戻り値を返却する。<BR>
     * <BR> 
     * @@param l_intBasePoint<BR>
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedActualReceiptTradingPower(int l_intBasePoint)
    {
        //引数.基準日がT+0より小さい時
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedActualReceiptTradingPower(int)");
        }
        
        //余力計算基準日<現物株式/信用現引>を退避する。
        int l_intOriginalBasePoint = this.calcCondition.getEquityBasePoint();

        /*
         * 余力計算基準日<現物株式/信用現引>に、引数.基準日をセットする
         */
        //引数.基準日がT+5より大きい場合
        if(l_intBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5をセット
            this.calcCondition.setEquityBasePoint(WEB3TPSpecifiedPointDef.T_5);
        }
        //以外の場合
        else
        {
            //引数.基準日をセット
            this.calcCondition.setEquityBasePoint(l_intBasePoint);
        }

        //引数.基準日以降、最小の信用現引買付可能額を計算する
        WEB3TPCalcResult l_result = this.calcAppliedActualReceiptTradingPower();

        //退避していた、基準日を戻す。
        this.calcCondition.setEquityBasePoint(l_intOriginalBasePoint);

        //計算結果を返却
        return l_result;
    }

    /**
     * （calc適用その他商品買付可能額）<BR>
     * 
     * 注文種別で指定された最小の「その他商品買付可能額」を計算する。<BR>
     * 注文種別が指定されなかった時（注文種別=null）、注文種別=OrderTypeEnum.その他とす
     * る。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_orderTypeEnum - （注文種別）
     * OrderTypeEnumにて定義
     * @@return WEB3TPCalcResult
     * @@roseuid 40CD5AC9001E
     */
    public WEB3TPCalcResult calcAppliedOtherTradingPower(OrderTypeEnum l_orderTypeEnum)
    {
        /*
         * 余力計算基準日を設定する。
         */
        //余力計算基準日
        int l_intBasePoint;
    
        //パラメータ.注文種別がnull、証拠金への振替、為替保証金への振替、
        //外国株式への振替、または　@CFD振替注文（預り金からCFD口座) の時
        if (l_orderTypeEnum == null
                || l_orderTypeEnum.equals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.TRANSFER_TO_FEQ) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT) == true)
        {
            //余力計算基準日に余力計算基準日<その他買付>を代入
            l_intBasePoint = this.calcCondition.getOtherBasePoint();
        }
        //パラメータ.注文種別が投信注文の時        
        else if (l_orderTypeEnum.equals(OrderTypeEnum.MF_BUY) == true
                  || l_orderTypeEnum.equals(OrderTypeEnum.MF_RECRUIT) == true
                  || l_orderTypeEnum.equals(OrderTypeEnum.MF_SWITCHING) == true)
        {
            //余力計算基準日に余力計算基準日<投信>を代入
            l_intBasePoint = this.calcCondition.getFundBasePoint();
        }
        //パラメータ.注文種別が出金注文の時
        else if (l_orderTypeEnum.equals(OrderTypeEnum.CASH_OUT) == true)
        {
            //余力計算基準日に余力計算基準日<出金>を代入
            l_intBasePoint = this.calcCondition.getPaymentBasePoint();
        }
        //パラメータ.注文種別がオプション新規買建の時
        else if (l_orderTypeEnum.equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN) == true)
        {
            //余力計算基準日に余力計算基準日<オプション新規買建>を代入
            l_intBasePoint = this.calcCondition.getOptionBasePoint();
        }
        //それ以外
        else
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcOtherTradingPower(OrderTypeEnum)");
        }
    
        /*
         * その他商品買付可能額とその適用日を取得する。
         */
        //その他商品買付可能額（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblOtherTradingPower = Double.MAX_VALUE;
        //その他商品買付可能額(BigDecimal型)
        BigDecimal l_bdOtherTradingPower = new BigDecimal(l_dblOtherTradingPower);
        //適用日
        int l_intAppliedPoint = 0;
    
        //その他商品買付可能額(項番)(BigDecimal型)
        BigDecimal l_bdCurOtherTradingPower;
    
        //指定日の範囲（余力計算基準日～5）でループ
        for (int index = l_intBasePoint; index <= WEB3TPSpecifiedPointDef.T_5; index++)
        {
            //項番が余力計算基準日と等しい時            
            if (index == l_intBasePoint)
            {
                //その他商品買付可能額(n)を取得する
                l_bdCurOtherTradingPower = new BigDecimal(this.calcOtherTradingPower(index));
            }
            //それ以外
            else
            {
                //その他商品買付可能額<日計り拘束金考慮>(n)を取得する
                l_bdCurOtherTradingPower =
                    new BigDecimal(this.calcOtherTradingPowerIncDayTrade(index));
            }
    
            //その他商品買付可能額(項番)がその他商品買付可能額より小さい時
            if (l_bdCurOtherTradingPower.compareTo(l_bdOtherTradingPower) < 0)
            {
                //その他商品買付可能額にその他商品買付可能額(項番)を代入する。
                l_bdOtherTradingPower = l_bdCurOtherTradingPower;
                //適用日に項番を代入する。
                l_intAppliedPoint = index;
            }
        }
    
        //［a. その他商品買付可能額　@< 0の場合］
        if (l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }

        /*
         * 預り金請求余力チェック
         */
        //預り金請求余力（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //預り金請求余力(BigDecimal型)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //適用日<預り金請求余力>
        int l_intActPayAppliedPoint = 0;

        //指定日の範囲（T+0～余力計算基準日-1）でループ
        for(int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //預り金請求余力(項番)(BigDecimal型)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcAccountBalanceDemandPower(index));

            //預り金請求余力(項番)が預り金請求余力より小さい時
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //預り金請求余力に預り金請求余力(項番)を代入する。
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //適用日に項番を代入する。
                l_intActPayAppliedPoint = index;
            }
        }

        //［a.預かり金請求余力 < 0の場合］
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.その他商品買付可能額 >= 0 の場合]
            if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //その他商品買付可能額 = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.その他商品買付可能額 < 0 かつ 適用日 > 適用日<預り金請求余力> の場合]
            else if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //その他商品買付可能額 = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<預り金請求余力>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
        }
        
        /*
         * 追証余力チェック
         */
        //発注日<株式／信用>
        int l_intBizDate = this.calcCondition.getEquityBizDate();

        //追証余力<適用可能額チェック用>を取得
        WEB3TPCalcResult l_marginCallPower = this.calcMarginCallPowerForCheck(
                l_orderTypeEnum, l_intBizDate, l_intBasePoint);

        //［a. 追証余力 < 0の場合］
        if(l_marginCallPower != null && l_marginCallPower.tradingPower < 0)
        {
            //[b.その他商品買付可能額 >= 0 の場合]
            if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //その他商品買付可能額 = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
            //[b.その他商品買付可能額 < 0 かつ 適用日 > 適用日<追証余力> の場合]
            else if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_marginCallPower.appliedPoint)
            {
                //その他商品買付可能額 = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<追証余力>
                l_intAppliedPoint = l_marginCallPower.appliedPoint;
            }
        }

        /*
         * 取引停止中チェック
         */
        //取引停止区分 == true の場合
        if(this.calcCondition.isTradingStop() == true)
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }
        //その他商品買付余力停止区分 == true かつ 引数.注文種別 not in (出金、証拠金振替、為替保証金振替、
        //中国株式振替、CFD振替注文（預り金からCFD口座))の場合
        if (this.calcCondition.isOtherTradingStop() == true
                && !(OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                        || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                        || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                        || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                        || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }
        //出金余力停止中 かつ 引数.注文種別in (出金、証拠金振替、為替保証金振替、
        //中国株式振替、CFD振替注文（預り金からCFD口座))の場合
        if (this.calcCondition.isPaymentStop() == true
            && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
            || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
            || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
            || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
            || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }

        /*
         * 余力計算結果オブジェクトに計算結果をセットし返却する。
         */
        //余力計算結果に計算結果を代入する。
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdOtherTradingPower.doubleValue();
    
        //パラメータ.注文種別がnullの時は
        if (l_orderTypeEnum == null)
        {
            //OrderTypeEnum.UNDEFINEDを代入する。
            l_calcResult.orderTypeEnum = OrderTypeEnum.UNDEFINED;
        }
        //それ以外
        else
        {
            //パラメータ.注文種別を代入する。
            l_calcResult.orderTypeEnum = l_orderTypeEnum;
        }
    
        //余力計算結果を返却する。
        return l_calcResult;
    }

    /**
     * (calc適用その他商品買付可能額) 
     * <BR>
     * 引数.基準日以降最小の「その他商品買付可能額」を計算する。 <BR>
     * <BR>
     * １）余力計算基準日を退避する。 <BR>
     * 　@　@[a.引数.注文種別 == 201：投信買付、203：投信募集、204：投信乗換 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<投信>()をコール<BR> 
     * <BR>
     * 　@　@[a.引数.注文種別 == 1001：出金 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<出金>()をコール<BR> 
     * <BR>
     * 　@　@[a.引数.注文種別 == 605：OP新規買建 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<オプション新規買建>()をコール<BR> 
     * <BR>
     * 　@　@[a.引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,<BR>
     * 　@　@　@　@1013:外国株式への振替, 1021:CFD振替注文（預り金からCFD口座）] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<その他買付>()をコール <BR>
     * <BR>
     * 　@　@[a.以外の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<その他買付>()をコール<BR> 
     * <BR>
     * ２）余力計算基準日に、引数.基準日をセットする。 <BR>
     * 　@　@[a.引数.注文種別 == 201：投信買付、203：投信募集、204：投信乗換 の場合] <BR>
     * 　@　@　@?this.余力計算条件.set余力計算基準日<投信>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 1001：出金 の場合] <BR>
     * 　@　@　@?this.余力計算条件.set余力計算基準日<出金>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 605：OP新規買建 の場合] <BR>
     * 　@　@　@?this.余力計算条件.set余力計算基準日<オプション新規買建>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,<BR>
     * 　@　@　@　@1013:外国株式への振替, 1021:CFD振替注文（預り金からCFD口座）] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<その他買付>()をコール <BR>
     * <BR>
     * 　@　@[a.以外の場合] <BR>
     * 　@　@　@?this.余力計算条件.set余力計算基準日<その他買付>()をコール <BR>
     * <BR>
     * 　@　@（※） <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@int：引数.基準日 <BR>
     * <BR>
     * ３）引数.基準日以降、最小のその他を計算する <BR>
     * 　@?this.calc適用その他商品買付可能額()をコール <BR>
     * <BR>
     * 　@　@［引数］ <BR>
     * 　@　@　@注文種別：引数.注文種別 <BR>
     * <BR>
     * ４）退避していた、基準日を戻す <BR>
     * 　@　@[a.引数.注文種別 == 201：投信買付、203：投信募集、204：投信乗換 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<投信>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 1001：出金 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<出金>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 605：OP新規買建 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<オプション新規買建>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,<BR>
     * 　@　@　@　@1013:外国株式への振替, 1021：CFD振替注文（預かり金からCFD口座)] <BR>
     * 　@　@　@-this.余力計算条件.get余力計算基準日<その他買付>()をコール <BR>
     * <BR>
     * 　@　@[a.以外の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<その他買付>()をコール <BR>
     * <BR>
     * 　@　@（※） <BR>
     * 　@　@［引数］ <BR>
     * 　@　@　@int：１）の戻り値 <BR>
     * <BR>
     * ５）３）の戻り値を返却する。<BR>
     * <BR>
     * 
     * @@param l_orderTypeEnum - （注文種別）
     * OrderTypeEnumにて定義
     * @@param l_intBasePoint
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAppliedOtherTradingPower(
            OrderTypeEnum l_orderTypeEnum, int l_intBasePoint)
    {
        //引数.基準日がT+0より小さい時
        if (l_intBasePoint < WEB3TPSpecifiedPointDef.T_0)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + ".calcAppliedOtherTradingPower(int)");
        }
        
        //基準日
        int l_intNewBasePoint = l_intBasePoint;
        //基準日がT+5より大きい場合
        if(l_intNewBasePoint > WEB3TPSpecifiedPointDef.T_5)
        {
            //T+5をセット
            l_intNewBasePoint = WEB3TPSpecifiedPointDef.T_5;
        }
        
        //オリジナル基準日
        int l_intOriginalBasePoint;        
        //余力計算結果
        WEB3TPCalcResult l_result = null;
        
        //引数.注文種別 == 201：投信買付、203：投信募集、204：投信乗換 の場合
        if(OrderTypeEnum.MF_BUY.equals(l_orderTypeEnum)
           || OrderTypeEnum.MF_RECRUIT.equals(l_orderTypeEnum)
           || OrderTypeEnum.MF_SWITCHING.equals(l_orderTypeEnum))
        {
            //余力計算基準日を退避
            l_intOriginalBasePoint = this.calcCondition.getFundBasePoint();
            //基準日をセット
            this.calcCondition.setFundBasePoint(l_intNewBasePoint);
            //引数.基準日以降、最小のその他商品買付可能額を計算
            l_result = this.calcAppliedOtherTradingPower(l_orderTypeEnum);
            //退避していた、基準日を戻す。
            this.calcCondition.setFundBasePoint(l_intOriginalBasePoint);
        }
        //引数.注文種別 == 1001：出金 の場合
        else if(OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum))
        {
            //余力計算基準日を退避
            l_intOriginalBasePoint = this.calcCondition.getPaymentBasePoint();
            //基準日をセット
            this.calcCondition.setPaymentBasePoint(l_intNewBasePoint);
            //引数.基準日以降、最小のその他商品買付可能額を計算
            l_result = this.calcAppliedOtherTradingPower(l_orderTypeEnum);
            //退避していた、基準日を戻す。
            this.calcCondition.setPaymentBasePoint(l_intOriginalBasePoint);
        }
        //引数.注文種別 == 605：OP新規買建 
        else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderTypeEnum))
        {
            //余力計算基準日を退避
            l_intOriginalBasePoint = this.calcCondition.getOptionBasePoint();
            //基準日をセット
            this.calcCondition.setOptionBasePoint(l_intNewBasePoint);
            //引数.基準日以降、最小のその他商品買付可能額を計算
            l_result = this.calcAppliedOtherTradingPower(l_orderTypeEnum);
            //退避していた、基準日を戻す。
            this.calcCondition.setOptionBasePoint(l_intOriginalBasePoint);
        }
        //引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,
        //1013:外国株式への振替, 1021:CFD振替注文（預り金からCFD口座）
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum))
        {
            //余力計算基準日を退避
            l_intOriginalBasePoint = this.calcCondition.getOtherBasePoint();
            //基準日をセット
            this.calcCondition.setOtherBasePoint(l_intNewBasePoint);
            //引数.基準日以降、最小のその他商品買付可能額を計算
            l_result = this.calcAppliedOtherTradingPower(l_orderTypeEnum);
            //退避していた、基準日を戻す。
            this.calcCondition.setOtherBasePoint(l_intOriginalBasePoint);
        }
        //以外の場合
        else
        {
            //余力計算基準日を退避
            l_intOriginalBasePoint = this.calcCondition.getOtherBasePoint();
            //基準日をセット
            this.calcCondition.setOtherBasePoint(l_intNewBasePoint);
            //引数.基準日以降、最小のその他商品買付可能額を計算
            l_result = this.calcAppliedOtherTradingPower(null);
            //退避していた、基準日を戻す。
            this.calcCondition.setOtherBasePoint(l_intOriginalBasePoint);
        }

        //計算結果を返却
        return l_result;
    }

    /**
     * (calc預り金請求余力<適用可能額チェック用>)
     * 
     * 適用可能額計算のチェック時に用いる預り金請求余力を計算する。
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。
     * 
     * @@param l_orderTypeEnum - （注文種別）
     * @@param l_intBizDate - （発注日）
     * @@param l_intDeliDate - （受渡日）
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcAccountBalanceDemandPowerForCheck(
            OrderTypeEnum l_orderTypeEnum, int l_intBizDate, int l_intDeliDate)
    {
        /*
         * “預り金請求余力チェック方法@”を取得する。
         */
        //預り金請求余力チェック方法@
        String l_strInstBranCalcCond = null;

        //[a.引数.注文種別 == 3:新規買建注文 または 4:新規売建注文 の場合]
        if(OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum) == true
                || OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum) == true)
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINOPEN_ACTPAY_CHECK);
        }
        //[a.以外の場合]
        else
        {
            //nullを返却する。
            return null;
        }
        
        /*
         * “預り金請求余力チェック方法@”別に、適用可能額チェック用の預り金請求余力と適用日を計算する。
         */
        //始点(預り金請求余力チェック範囲)
        int l_intStart = 0;
        //終点(預り金請求余力チェック範囲)
        int l_intEnd = 0;
        
        //[a.”預り金請求余力チェック方法@” == 1:FROM_T0_UNTIL_BIZ_DATE の場合]
        if(WEB3TPActPayCheckDef.FROM_T0_UNTIL_BIZ_DATE
            .equals(l_strInstBranCalcCond) == true)
        {
            //始点 = T+0
            l_intStart = WEB3TPSpecifiedPointDef.T_0;
            //終点 = 発注日
            l_intEnd = l_intBizDate;
        }
        //[a.”預り金請求余力チェック方法@” == 2:FROM_T0_UNTIL_PRE_BIZ_DATE の場合]
        else if(WEB3TPActPayCheckDef.FROM_T0_UNTIL_PRE_BIZ_DATE
            .equals(l_strInstBranCalcCond) == true)
        {
            //始点 = T+0
            l_intStart = WEB3TPSpecifiedPointDef.T_0;
            //終点 = 発注日-1
            l_intEnd = l_intBizDate-1;
        }
        //[a.以外 の場合]
        else
        {
            //nullを返却する。
            return null;
        }

        //始点 > 終点の場合
        if(l_intStart > l_intEnd)
        {
            //nullを返却する。
            return null;
        }
        
        //預り金請求余力（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //預り金請求余力(BigDecimal型)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //適用日
        int l_intAppliedPoint = 0;
    
        //指定日の範囲（始点～終点）でループ
        for (int index = l_intStart; index <= l_intEnd; index++)
        {
            //預り金請求余力(項番)(BigDecimal型)
            BigDecimal l_bdCurActualPaymentBalance =
                new BigDecimal(this.calcAccountBalanceDemandPower(index));
    
            //預り金請求余力(項番)が預り金請求余力より小さい時
            if (l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //預り金請求余力に預り金請求余力(項番)を代入
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //適用日に項番を代入
                l_intAppliedPoint = index;
            }
        }

        /*
         * 余力計算結果オブジェクトに計算結果をセットし返却する。
         */
        //余力計算結果に計算結果を代入する。
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdActualPaymentBalance.doubleValue();
        l_calcResult.orderTypeEnum = l_orderTypeEnum;
        
        return l_calcResult;
    }
    
    /**
     * (calc追証余力<適用可能額チェック用>)
     * 
     * 適用可能額計算のチェック時に用いる追証余力を計算する。
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。
     * 
     * @@param l_orderTypeEnum - （注文種別）
     * @@param l_intBizDate - （発注日）
     * @@param l_intDeliDate - （受渡日）
     * @@return WEB3TPCalcResult
     */
    public WEB3TPCalcResult calcMarginCallPowerForCheck(
            OrderTypeEnum l_orderTypeEnum, int l_intBizDate, int l_intDeliDate)
    {
        /*
         * “追証余力チェック方法@”を取得する。
         */
        //追証余力チェック方法@
        String l_strInstBranCalcCond = null;

        //[a.引数.注文種別 == 1:現物買注文の場合]
        if(OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum) == true)
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.EQUITYBUY_ADDDEPOSIT_CHECK);
        }
        //[a.引数.注文種別 == 3:新規買建注文 または 4:新規売建注文 の場合]
        else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum) == true
                || OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum) == true)
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINOPEN_ADDDEPOSIT_CHECK);
        }
        //[a.引数.注文種別 == 7:現引注文の場合]
        else if(OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderTypeEnum) == true)
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.MARGINSWAPLONG_ADDDEPOSIT_CHECK);
        }
        //[a.以外の場合]
        else
        {
            l_strInstBranCalcCond = this.calcCondition
                .getInstBranCalcCondition(WEB3TPCalcCondition.OTHERBUY_ADDDEPOSIT_CHECK);
        }
        
        /*
         * “追証余力チェック方法@”別に、適用可能額チェック用の追証余力と適用日を計算する。
         */
        //始点(追証余力チェック範囲)
        int l_intStart = 0;
        //終点(追証余力チェック範囲)
        int l_intEnd = 0;
        
        //[a.”追証余力チェック方法@” == 1:FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE の場合]
        if(WEB3TPAdddepositCheckDef.FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE
            .equals(l_strInstBranCalcCond) == true)
        {
            //始点 = 発注日
            l_intStart = l_intBizDate;
            //終点 = 受渡日-1
            l_intEnd = l_intDeliDate - 1;
        }
        //[a.”追証余力チェック方法@” == 2:ON_BIZ_DATE の場合]
        else if(WEB3TPAdddepositCheckDef.ON_BIZ_DATE
            .equals(l_strInstBranCalcCond) == true)
        {
            //始点 = 発注日
            l_intStart = l_intBizDate;
            //終点 = 発注日
            l_intEnd = l_intBizDate;
        }
        //[a.以外 の場合]
        else
        {
            //nullを返却する。
            return null;
        }

        //始点 > 終点の場合
        if(l_intStart > l_intEnd)
        {
            //nullを返却する。
            return null;
        }
        
        //追証余力（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblMarginCallPower = Double.MAX_VALUE;
        //追証余力(BigDecimal型)
        BigDecimal l_bdMarginCallPower = new BigDecimal(l_dblMarginCallPower);
        //適用日
        int l_intAppliedPoint = 0;
    
        //指定日の範囲（始点～終点）でループ
        for (int index = l_intStart; index <= l_intEnd; index++)
        {
            //追証余力(項番)(BigDecimal型)
            BigDecimal l_bdCurMarginCallPower =
                new BigDecimal(this.calcMarginCallPower(index));
    
            //追証余力(項番)が追証余力より小さい時
            if (l_bdCurMarginCallPower.compareTo(l_bdMarginCallPower) < 0)
            {
                //追証余力に追証余力(項番)を代入
                l_bdMarginCallPower = l_bdCurMarginCallPower;
                //適用日に項番を代入
                l_intAppliedPoint = index;
            }
        }

        /*
         * 余力計算結果オブジェクトに計算結果をセットし返却する。
         */
        //余力計算結果に計算結果を代入する。
        WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
        l_calcResult.appliedPoint = l_intAppliedPoint;
        l_calcResult.tradingPower = l_bdMarginCallPower.doubleValue();
        l_calcResult.orderTypeEnum = l_orderTypeEnum;
        
        return l_calcResult;
    }

    /**
     * (calc株式買付可能額～連続注文～) <BR>
     * <BR>
     * 最小の「株式買付可能額～連続注文～」を計算する。 <BR>
     * <BR>
     * 1)引数を基準日とした場合の適用株式買付可能額を取得する。<BR>
     * <BR>
     * 　@-this.calc適用株式買付可能額(int)をコールする。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@int：引数.基準日 <BR>
     * <BR>
     *   -株式買付可能額を取得する。 <BR>
     * <BR>
     * 　@　@株式買付可能額 = 余力計算結果.取引可能額 <BR>
     * <BR>
     * 2)現物買付・信用現引予約注文の(現買・現引)概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@[this.当日株式予約注文単位一覧 == null  <BR>
     * 　@　@または this.当日株式予約注文単位一覧.size() == 0 場合] <BR>
     * <BR>
     * 　@　@(現買・現引)概算受渡代金合計 = 0 <BR>
     * <BR>
     * 　@[以外の場合] <BR>
     * <BR>
     * 　@　@-this.当日株式予約注文単位一覧を以下の条件でフィルタする。<BR> 
     * <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@注文種別 IN ('1：現物買注文', '7：現引注文') <BR>
     * 　@　@　@　@注文有効状態 = ’1：オープン’ <BR>
     * <BR>
     * 　@　@-フィルタ結果より概算受渡代金を集計する。<BR> 
     * <BR>
     * 　@　@　@(現買・現引)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金) <BR>
     * <BR>
     * 3)株式買付可能額～連続注文～を返却する <BR>
     * <BR>
     * 　@返却値 = 株式買付可能額 - (現買・現引)概算受渡代金合計 <BR>
     * <BR>
     * @@param l_intBasePoint - (基準日)
     * @@return double
     */
    public double calcSuccEquityTradingPower(int l_intBasePoint)
    {
        /*
         * 引数を基準日とした場合の適用株式買付可能額を取得する。
         */
        WEB3TPCalcResult l_result = this.calcAppliedEquityTradingPower(l_intBasePoint);

        //適用株式買付可能額
        double l_dblEqTradingPower = l_result.tradingPower;

        /*
         * 現物買付・信用現引予約注文の(現買・現引)概算受渡代金を集計する。 
         */
        //(現買・現引)概算受渡代金合計
        double l_dblRevEqTradingPower = 0;
        //当日株式予約注文単位一覧
        List l_lisRsvEqOrders = this.getTodaysRsvEqOrderUnits();

        //当日株式予約注文単位一覧が存在する場合
        if(l_lisRsvEqOrders != null && l_lisRsvEqOrders.isEmpty() == false)
        {
            for(Iterator iter = l_lisRsvEqOrders.iterator(); iter.hasNext();)
            {
                RsvEqOrderUnitParams l_params = (RsvEqOrderUnitParams)iter.next();

                //注文有効状態 == 1:オープン の場合
                if(OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()) == true)
                {
                    //注文種別
                    OrderTypeEnum l_orderType = l_params.getOrderType();

                    //注文種別 == 1:現物買注文 または、7：現引注文 の場合
                    if(OrderTypeEnum.EQUITY_BUY.equals(l_orderType) == true
                            || OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType) == true)
                    {
                        //(現買・現引)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金)
                        l_dblRevEqTradingPower = l_dblRevEqTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                }
            }
        }

        /*
         * 株式買付可能額～連続注文～を返却する
         * 
         * 返却値 = 株式買付可能額 - (現買・現引)概算受渡代金合計
         */
        return l_dblEqTradingPower - l_dblRevEqTradingPower;
    }

    /**
     * (calc信用新規建可能額～連続注文～) <BR>
     * <BR>
     * 最小の「信用新規建可能額～連続注文～」を計算する。 <BR>
     * <BR>
     * 1)適用信用新規建可能額を取得する。 <BR>
     * <BR>
     * 　@-this.calc適用信用新規建可能額()をコールする。 <BR>
     * <BR>
     * 　@-信用新規建可能額を取得する。 <BR>
     * <BR>
     * 　@　@信用新規建可能額 = 余力計算結果.取引可能額 <BR>
     * <BR>
     * 2)現物買付・信用現引予約注文の(現買・現引)概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@[this.当日株式予約注文単位一覧 == null  <BR>
     * 　@　@または this.当日株式予約注文単位一覧.size() == 0 場合] <BR>
     * <BR>
     * 　@　@(現買・現引)概算受渡代金合計 = 0 <BR>
     * <BR>
     * 　@[以外の場合] <BR>
     * 　@　@-this.当日株式予約注文単位一覧を以下の条件でフィルタする。 <BR>
     * <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@注文種別 IN ('1：現物買注文', '7：現引注文') <BR>
     * 　@　@　@　@注文有効状態 = ’1：オープン’ <BR>
     * <BR>
     * 　@　@-フィルタ結果より概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@　@　@(現買・現引)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金) <BR>
     * <BR>
     * 3)信用新規建予約注文の(新規建)概算受渡代金を集計する。<BR> 
     * <BR>
     * 　@[this.当日株式予約注文単位一覧 == null  <BR>
     * 　@　@または this.当日株式予約注文単位一覧.size() == 0 場合] <BR>
     * <BR>
     * 　@　@(新規建)概算受渡代金概算受渡代金合計 = 0 <BR>
     * <BR>
     * 　@[以外の場合] <BR>
     * 　@　@-this.当日株式予約注文単位一覧を以下の条件でフィルタする。 <BR>
     * <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@注文種別 IN ('3：新規買建注文', '4：新規売建注文') <BR>
     * 　@　@　@　@注文有効状態 = ’1：オープン’ <BR>
     * <BR>
     * 　@　@-フィルタ結果より概算受渡代金を集計する。<BR> 
     * <BR>
     * 　@　@　@(新規建)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金) <BR>
     * <BR>
     * 4)信用新規建可能額～連続注文～を返却する <BR>
     * <BR>
     * 　@返却値 = 信用新規建可能額  <BR>
     * 　@　@　@　@　@ - (新規建)概算受渡代金合計 <BR> 
     * 　@　@　@　@　@ - ( (現買・現引)概算受渡代金合計 * ( 1 - (代用掛目 / 100) ) ) / (保証金率 / 100) <BR>
     * <BR>
     * 　@※代用掛目 = this.余力計算条件.get余力計算代用掛目() <BR>
     * 　@※保証金率 = this.余力計算条件.get保証金率() <BR>
     * <BR>
     * @@return double
     */
    public double calcSuccMarginTradingPower()
    {
        /*
         * 適用信用新規建可能額を取得する。
         */
        WEB3TPCalcResult l_result = this.calcAppliedMarginTradingPower();

        //適用信用新規建可能額
        double l_dblMarginTradingPower = l_result.tradingPower;

        /*
         * 現物買付・信用現引予約注文の(現買・現引)概算受渡代金を集計する。 
         * 信用新規建予約注文の(新規建)概算受渡代金を集計する。 
         */
        //(現買・現引)概算受渡代金合計
        double l_dblRevEqTradingPower = 0;
        //(新規建)概算受渡代金合計
        double l_dblRevMarginTradingPower = 0;
        //当日株式予約注文単位一覧
        List l_lisRsvEqOrders = this.getTodaysRsvEqOrderUnits();

        //当日株式予約注文単位一覧が存在する場合
        if(l_lisRsvEqOrders != null && l_lisRsvEqOrders.isEmpty() == false)
        {
            for(Iterator iter = l_lisRsvEqOrders.iterator(); iter.hasNext();)
            {
                RsvEqOrderUnitParams l_params = (RsvEqOrderUnitParams)iter.next();

                //注文有効状態 == 1:オープン の場合
                if(OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()) == true)
                {
                    //注文種別
                    OrderTypeEnum l_orderType = l_params.getOrderType();

                    //注文種別 == 1:現物買注文 または、7：現引注文 の場合
                    if(OrderTypeEnum.EQUITY_BUY.equals(l_orderType) == true
                            || OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType) == true)
                    {
                        //(現買・現引)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金)
                        l_dblRevEqTradingPower = l_dblRevEqTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                    //注文種別 == 3：新規買建注文　@または、4：新規売建注文 の場合
                    else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType) == true
                            || OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) == true)
                    {
                        //(新規建)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金)
                        l_dblRevMarginTradingPower = l_dblRevMarginTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                }
            }
        }

        /*
         * 信用新規建可能額～連続注文～を返却する 
         * 
         * 返却値 = 信用新規建可能額  
         * 　@　@　@　@ - (新規建)概算受渡代金合計  
         * 　@　@　@　@ - ( (現買・現引)概算受渡代金合計 * ( 1 - (代用掛目 / 100) ) ) / (保証金率 / 100) 
         */
        //代用掛目
        int l_intSubRate = this.calcCondition.getSubstituteRate();
        //保証金率
        int l_intMarginDepRate = this.calcCondition.getMarginDepositRate();

        //信用新規建可能額
        BigDecimal l_bdMarginTradingPower = new BigDecimal(Double.toString(l_dblMarginTradingPower));
        //(新規建)概算受渡代金合計
        BigDecimal l_bdRevMarginTradingPower = new BigDecimal(
                Double.toString(l_dblRevMarginTradingPower));
        //(現買・現引)概算受渡代金合計
        BigDecimal l_bdRevEqTradingPower = new BigDecimal(Double.toString(l_dblRevEqTradingPower));

        //返却値
        BigDecimal l_bdReturn = null;
        l_bdReturn = l_bdRevEqTradingPower.multiply(new BigDecimal(
                Integer.toString(100 - l_intSubRate)));
        l_bdReturn = l_bdReturn.divide(
                new BigDecimal(Integer.toString(l_intMarginDepRate)),
                10,
                BigDecimal.ROUND_HALF_EVEN);
        l_bdReturn = l_bdMarginTradingPower.subtract(l_bdRevMarginTradingPower).subtract(l_bdReturn);

        //少数点以下切捨て
        return Math.floor(l_bdReturn.doubleValue());
    }

    /**
     * (calc信用現引可能額～連続注文～) <BR>
     * <BR>
     * 最小の「信用現引可能額～連続注文～」を計算する。 <BR>
     * <BR>
     * 1)引数を基準日とした場合の適用信用現引可能額を取得する。 <BR>
     * <BR>
     * 　@-this.calc適用信用現引可能額(int)をコールする。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@int：引数.基準日 <BR>
     * <BR>
     *   -信用現引可能額を取得する。 <BR>
     * <BR>
     * 　@　@信用現引可能額 = 余力計算結果.取引可能額 <BR>
     * <BR>
     * 2)現物買付・信用現引予約注文の(現買・現引)概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@[this.当日株式予約注文単位一覧 == null  <BR>
     * 　@　@または this.当日株式予約注文単位一覧.size() == 0 場合] <BR>
     * <BR>
     * 　@　@(現買・現引)概算受渡代金合計 = 0 <BR>
     * <BR>
     * 　@[以外の場合] <BR>
     * 　@　@-this.当日株式予約注文単位一覧を以下の条件でフィルタする。 <BR>
     * <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@注文種別 IN ('1：現物買注文', '7：現引注文') <BR>
     * 　@　@　@　@注文有効状態 = ’1：オープン’ 
     * <BR>
     * 　@　@-フィルタ結果より概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@　@　@(現買・現引)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金) <BR>
     * <BR>
     * 3)信用新規建予約注文の(新規建)概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@[this.当日株式予約注文単位一覧 == null  <BR>
     * 　@　@または this.当日株式予約注文単位一覧.size() == 0 場合] <BR>
     * <BR>
     * 　@　@(新建)概算受渡代金概算受渡代金合計 = 0 <BR>
     * <BR>
     * 　@[以外の場合] <BR>
     * 　@　@-this.当日株式予約注文単位一覧を以下の条件でフィルタする。 <BR>
     * <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@注文種別 IN ('3：新規買建注文', '4：新規売建注文') <BR>
     * 　@　@　@　@注文有効状態 = ’1：オープン’ 
     * <BR>
     * 　@　@-フィルタ結果より概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@　@　@(新規建)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金) <BR>
     * <BR>
     * <BR>
     * 4)信用現引可能額～連続注文～を返却する <BR>
     * <BR>
     * 　@返却値 = 信用新規建可能額   <BR>
     * 　@　@　@　@　@ - ( (新規建)概算受渡代金合計 * (保証金率 / 100))  <BR>
     * 　@　@　@　@　@ - (現買・現引)概算受渡代金合計 <BR>
     * <BR>
     * 　@※保証金率 = this.余力計算条件.get保証金率<BR>
     *  <BR>
     * @@param l_intBasePoint - (基準日)
     * @@return double
     */
    public double calcSuccActualReceiptTradingPower(int l_intBasePoint)
    {
        /*
         * 引数を基準日とした場合の適用信用現引可能額を取得する。
         */
        WEB3TPCalcResult l_result = this.calcAppliedActualReceiptTradingPower(l_intBasePoint);

        //適用信用現引可能額
        double l_dblActualReceiptTradingPower = l_result.tradingPower;

        /*
         * 現物買付・信用現引予約注文の(現買・現引)概算受渡代金を集計する。 
         * 信用新規建予約注文の(新規建)概算受渡代金を集計する。 
         */
        //(現買・現引)概算受渡代金合計
        double l_dblRevEqTradingPower = 0;
        //(新規建)概算受渡代金合計
        double l_dblRevMarginTradingPower = 0;
        //当日株式予約注文単位一覧
        List l_lisRsvEqOrders = this.getTodaysRsvEqOrderUnits();

        //当日株式予約注文単位一覧が存在する場合
        if(l_lisRsvEqOrders != null && l_lisRsvEqOrders.isEmpty() == false)
        {
            for(Iterator iter = l_lisRsvEqOrders.iterator(); iter.hasNext();)
            {
                RsvEqOrderUnitParams l_params = (RsvEqOrderUnitParams)iter.next();

                //注文有効状態 == 1:オープン の場合
                if(OrderOpenStatusEnum.OPEN.equals(l_params.getOrderOpenStatus()) == true)
                {
                    //注文種別
                    OrderTypeEnum l_orderType = l_params.getOrderType();

                    //注文種別 == 1:現物買注文 または、7：現引注文 の場合
                    if(OrderTypeEnum.EQUITY_BUY.equals(l_orderType) == true
                            || OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType) == true)
                    {
                        //(現買・現引)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金)
                        l_dblRevEqTradingPower = l_dblRevEqTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                    //注文種別 == 3：新規買建注文　@または、4：新規売建注文 の場合
                    else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType) == true
                            || OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) == true)
                    {
                        //(新規建)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金)
                        l_dblRevMarginTradingPower = l_dblRevMarginTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                }
            }
        }

        /*
         * 信用現引可能額～連続注文～を返却する 
         * 
         * 返却値 = 信用現引可能額   
         * 　@　@　@　@ - ( (新規建)概算受渡代金合計 * (保証金率 / 100))   
         * 　@　@　@　@ - (現買・現引)概算受渡代金合計 
         */
        //保証金率
        int l_intMarginDepRate = this.calcCondition.getMarginDepositRate();

        //信用現引可能額
        BigDecimal l_bdActualReceiptTradingPower = new BigDecimal(
                Double.toString(l_dblActualReceiptTradingPower));
        //(新規建)概算受渡代金合計
        BigDecimal l_bdRevMarginTradingPower = new BigDecimal(
                Double.toString(l_dblRevMarginTradingPower));
        //(現買・現引)概算受渡代金合計
        BigDecimal l_bdRevEqTradingPower = new BigDecimal(Double.toString(l_dblRevEqTradingPower));

        //返却値
        BigDecimal l_bdReturn = null;
        l_bdReturn = l_bdRevMarginTradingPower.multiply(new BigDecimal(
                Integer.toString(l_intMarginDepRate)));
        l_bdReturn = l_bdReturn.divide(new BigDecimal("100"), 10, BigDecimal.ROUND_HALF_EVEN);
        l_bdReturn = l_bdActualReceiptTradingPower.subtract(l_bdReturn).subtract(
                l_bdRevEqTradingPower);

        //少数点以下切捨て
        return Math.floor(l_bdReturn.doubleValue());
    }
    
    /**
     * (staticメソッド)(find余力計算結果<信用顧客>～口座ＩＤ指定～)<BR>
     * <BR>
     * １）顧客オブジェクトを生成する。<BR>
     * <BR>
     * 　@－コンストラクタ、WEB3GentradeMainAccount()コール<BR>
     * 　@　@［引数］<BR>
     * 　@　@　@口座ID：引数.口座ID<BR>
     * <BR>
     * ２）"余力再計算基準時間"を取得する。<BR>
     * <BR>
     * 　@－プロセス管理テーブルを以下の条件で検索する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@プロセスID："0006:余力再計算基準時間"<BR>
     * 　@　@　@証券会社コード:顧客オブジェクト.getDataSourceObject().get証券会社コード()<BR>
     * 　@　@　@部店コード：顧客オブジェクト.getDataSourceObject().get部店コード()<BR>
     * <BR>
     * 　@－"余力再計算基準時間"を取得する。<BR>
     * 　@　@[a.検索結果 != null の場合]<BR>
     * 　@　@　@"余力再計算基準時間" = プロセス管理Params.最終更新時刻<BR>
     * <BR>
     * 　@　@[a.検索結果 == null の場合]<BR>
     * 　@　@　@"余力再計算基準時間" = null<BR>
     * <BR>
     * ３）最新の余力計算結果Paramsオブジェクトを取得する。<BR>
     * <BR>
     * 　@－余力計算結果テーブル(信用顧客)を以下の条件で検索する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@顧客ID：引数.口座ID<BR>
     * 　@　@　@作成日付：“最新の日付”<BR>
     * 　@　@　@値洗い区分：0:通常<BR>
     * 　@　@　@（余力計算結果（信用顧客）IDの降順）<BR>
     * <BR>
     * 　@※検索結果 == null または 検索結果.size() == 0 場合、<BR>
     * 　@　@余力再計算フローへ<BR>
     * <BR>
     * ４）最新の余力計算結果詳細Paramsオブジェクトを取得する。<BR>
     * <BR>
     * 　@[a."余力再計算基準時間" == null または、<BR>
     * 　@　@ "余力再計算基準時間" <  余力計算結果Paramsオブジェクト.作成日付]<BR>
     * <BR>
     * 　@　@－余力計算結果詳細テーブル(信用顧客)を以下の条件で検索する。<BR>
     * 　@　@　@［検索条件］<BR>
     * 　@　@　@　@余力計算結果（信用顧客）ID：余力計算結果Paramsオブジェクト.余力計算結果（信用顧客）ID<BR>
     * <BR>
     * 　@　@※余力計算結果詳細Paramsが取得できなかった場合、例外をスローする。<BR>
     * <BR>
     * 　@[a.以外の場合]<BR>
     * <BR>
     * 　@　@※余力再計算フローへ<BR>
     * <BR>
     * ５）返却値(List型)を設定し、呼び出し元へ返却する。<BR>
     * <BR>
     * 　@[返却値]<BR>
     * 　@　@Listの、0番目の要素：３）で取得した、余力計算結果Params<BR>
     * 　@　@Listの、1番目の要素：４）で取得した、余力計算結果詳細Params<BR>
     * <BR>
     * <BR>
     * ：余力再計算フロー<BR>
     * <BR>
     * E-1)信用口座開設区分を取得する。<BR>
     * <BR>
     * －顧客オブジェクト.is信用口座開設()をコール<BR>
     * 　@[引数]<BR>
     * 　@　@弁済区分＝”指定なし<BR>
     * <BR>
     * 　@※信用顧客の場合(is信用口座開設==false)、例外をスローする。<BR>
     * <BR>
     * E-2)補助口座オブジェクトを取得する。<BR>
     * <BR>
     * 　@－顧客オブジェクト.getSubAccount()<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@補助口座タイプ："株式取引口座(預り金)"<BR>
     * <BR>
     * E-3）余力計算条件オブジェクトを生成する。<BR>
     * <BR>
     * 　@－余力計算条件.create余力計算条件<標準>()をコール<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@補助口座：補助口座オブジェクト<BR>
     * <BR>
     * E-4）余力更新オブジェクトを生成する。<BR>
     * <BR>
     * 　@－コンストラクタ、余力更新()をコール<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@口座ID：引数.口座ID<BR>
     * 　@　@　@信用顧客フラグ：is信用口座開設()の戻り値<BR>
     * 　@　@　@余力計算条件：create余力計算条件()の戻り値<BR>
     * 　@　@　@現注文内容：null<BR>
     * <BR>
     * E-5）余力更新内容(List)を取得しDBに格納する。<BR>
     * <BR>
     * 　@－余力更新オブジェクト.calc余力更新内容<信用顧客>()をコール<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@値洗い区分："0:通常"<BR>
     * <BR>
     * 　@－余力更新オブジェクト.save余力更新内容<信用顧客>()をコール<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@List：余力更新オブジェクト.calc余力更新内容<信用顧客>()の戻り値<BR>
     * <BR>
     * E-7）余力更新オブジェクト.calc余力更新内容<信用顧客>()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)
     * @@return List
     */
    public static List findCalcResultMarginParams(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams()";
        log.entering(STR_METHOD_NAME);

        //余力計算結果テーブル（信用顧客）の検索条件
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" account_id = ? ");
        l_strWhere.append(" and created_timestamp = ");
        l_strWhere.append(" ( ");
        l_strWhere.append("  select max(created_timestamp) ");
        l_strWhere.append("  from tp_calc_result_margin ");
        l_strWhere.append("  where account_id = ? ");
        l_strWhere.append("  and mark_to_market_div = ? ");
        l_strWhere.append(" ) ");

        Object[] l_bindVars = { new Long(l_lngAccountId),
                               new Long(l_lngAccountId),
                               WEB3TPMarkToMarketDivDef.NORMAL };       

        log.debug(
                "Finding TpCalcResultMarginParams where="
                    + l_strWhere.toString()
                    + ", bindVars="
                    + objectsToString(l_bindVars));

        //余力更新内容(List)
        List l_updResults = null;

        try
        {
            /*
             * 顧客オブジェクトを生成する。 
             */
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngAccountId);

            /*
             * "余力再計算基準時間"を取得する。 
             */
            //"余力再計算基準時間"
            Timestamp l_updateTimeStamp = null;

            //会社コード、部店コードを取得
            MainAccountRow l_accountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            String l_strInstCode = l_accountRow.getInstitutionCode();
            String l_strBranCode = l_accountRow.getBranchCode();

            //プロセスID(0006:余力再計算基準時間)
            String l_strProcessId = "0006";

            //プロセス管理テーブルを検索
            ProcessManagementRow l_processRow = ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    l_strProcessId,
                    l_strInstCode,
                    l_strBranCode);

            if(l_processRow != null)
            {
                l_updateTimeStamp = l_processRow.getLastUpdatedTimestamp();
            }
            
            /*
             * 最新の余力計算結果Paramsオブジェクトを取得する。 
             */
            //余力計算結果テーブル<信用顧客>を検索
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRow =
                l_qp.doFindAllQuery(
                    TpCalcResultMarginParams.TYPE,
                    l_strWhere.toString(),
                    "calc_result_margin_id desc",
                    null,
                    l_bindVars,
                    new RowType[] {TpCalcResultMarginParams.TYPE});

            //余力計算結果が検索された場合
            if(l_lisRow != null && l_lisRow.size() != 0)
            {
                //余力計算結果Paramsを取得
                TpCalcResultMarginParams l_resultParams = (TpCalcResultMarginParams)l_lisRow.get(0);

                /*
                 * 最新の余力計算結果詳細Paramsオブジェクトを取得する。 
                 */
                //[a."余力再計算基準時間" == null または、 
                //   "余力再計算基準時間" <  余力計算結果Paramsオブジェクト.作成日付] 
                if(l_updateTimeStamp == null
                        || l_updateTimeStamp.before(l_resultParams.getCreatedTimestamp()) == true)
                {
                    //余力計算結果詳細Paramsオブジェクトを取得
                    TpCalcResultMarginDetailParams l_resultDetailParams =
                        (TpCalcResultMarginDetailParams)TpCalcResultMarginDetailDao.findRowByPk(
                            l_resultParams.getCalcResultMarginId());

                    l_updResults = new ArrayList();
                    l_updResults.add(l_resultParams);
                    l_updResults.add(l_resultDetailParams);

                    //取得した余力更新内容(List)を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return l_updResults;
                }
            }
                
            log.error("TpCalcResultMarginParams:data is not found");

            //信用口座開設区分を取得
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //現物顧客の場合
            if (l_blnMargin == false)
            {
                //エラーをスローする
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    STR_METHOD_NAME);
            }

            //補助口座<株式取引口座(預り金)>オブジェクトを取得する。
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //余力計算条件オブジェクトを生成する。
            WEB3TPCalcCondition l_calcCond =
                WEB3TPCalcCondition.createCalcConditionStandard(
                    new WEB3GentradeSubAccount(
                        (SubAccountRow)l_subAccount.getDataSourceObject()));

            //余力更新オブジェクトを生成する。
            WEB3TPTradingPowerUpd l_tpUpd =
                new WEB3TPTradingPowerUpd(l_lngAccountId, l_blnMargin, l_calcCond, null);

            /*
             * 余力計算結果Paramsオブジェクトを取得
             */
            l_updResults = l_tpUpd.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL); 
            //余力更新内容をテーブルにInsert
            l_tpUpd.saveTradingpowerUpdResultMargin(l_updResults);

            //取得した余力計算結果Params<信用顧客>オブジェクトを返却する
            log.exiting(STR_METHOD_NAME);
            return l_updResults;
        }
        catch (NotFoundException nfe)
        {
            log.error(nfe.getMessage(), nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * （find余力計算結果<信用顧客>Params）<BR>
     * (staticメソッド)<BR>
     * <BR>
     * １）DBより該当顧客の最新の余力計算結果Params<信用顧客>を１行取得する。<BR>
     * <BR>
     * 　@[対象テーブル]<BR>
     * 　@　@余力計算結果テーブル（信用顧客）<BR>
     * <BR>
     * 　@［検索条件］<BR>
     * 　@　@余力計算結果(信用顧客)ＩＤ：引数.余力計算結果ＩＤ<BR>
     * <BR>
     * 　@　@※余力計算結果Paramsが取得できなかった場合、エラーをスローする。<BR>
     * <BR>
     * ２）DBより該当顧客の最新の余力計算結果詳細Params<信用顧客>を１行取得する。<BR>
     * <BR>
     * 　@[対象テーブル]<BR>
     * 　@　@余力計算結果詳細テーブル（信用顧客）<BR>
     * <BR>
     * 　@［検索条件］<BR>
     * 　@　@余力計算結果(信用顧客)ID：引数.余力計算結果ＩＤ<BR>
     * <BR>
     * 　@※余力計算結果詳細Paramsが取得できなかった場合、エラーをスローする。<BR>
     * <BR>
     * ３）Listに、１）で取得した余力計算結果Params<信用顧客>と、<BR>
     *  ２）で取得した、余力計算結果詳細Params<信用顧客>を、格納して返却する。<BR>
     * <BR>
     * 　@　@Listの、0番目の要素：１）で取得した、余力計算結果<信用顧客>Params<BR>
     * 　@　@Listの、1番目の要素：２）で取得した、余力計算結果詳細<信用顧客>Params<BR>
     * <BR>
     * @@param l_lngCalcResultId - (余力計算結果ID)
     * @@return List
     */
    public static List findCalcResultMarginParamsSpecifiedCalcResultId(long l_lngCalcResultId)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerCalcMargin.findCalcResultMarginParamsSpecifiedCalcResultId(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //余力計算結果テーブル<信用顧客>を検索
            TpCalcResultMarginParams l_resultParams =
                (TpCalcResultMarginParams)TpCalcResultMarginDao.findRowByPk(l_lngCalcResultId);

            //余力計算結果詳細<信用顧客>Paramsを取得
            TpCalcResultMarginDetailParams l_resultDetailParams =
                (TpCalcResultMarginDetailParams)TpCalcResultMarginDetailDao.findRowByPk(
            l_lngCalcResultId);

            List l_updResults = new ArrayList();
            l_updResults.add(l_resultParams);
            l_updResults.add(l_resultDetailParams);

            //取得した余力更新内容(List)を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_updResults;
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(dqe.getMessage(), dqe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(dne.getMessage(), dne);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }
    }

    /**
     * （get建手数料）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「建手数料」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「建手数料」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果詳細Params<信用顧客>.建手数料(T+n)<BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     */
    public double getSetupFee(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getSetupFee(int)";
        log.entering(STR_METHOD_NAME);

        //建手数料
        double l_dblSetupFee;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //建手数料( T + 0 )を取得する。
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //建手数料( T + 1 )を取得する。
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //建手数料( T + 2 )を取得する。
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //建手数料( T + 3 )を取得する。
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //建手数料( T + 4 )を取得する。
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //建手数料( T + 5 )を取得する。
                l_dblSetupFee = this.calcResultDetailMargin.getSetupFee5();
                break;

            default :
                //nが0以上5以下でない時、0を返却する。
                l_dblSetupFee = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //取得した建手数料を返却する。
        return l_dblSetupFee;
    }

    /**
     * （get日歩・逆日歩損）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「日歩・逆日歩損」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「日歩・逆日歩損」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果詳細Params<信用顧客>.日歩・逆日歩損(T+n)<BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     */
    public double getContractInterestLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getContractInterestLoss(int)";
        log.entering(STR_METHOD_NAME);

        //日歩・逆日歩損
        double l_dblContractInterestLoss;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //日歩・逆日歩損( T + 0 )を取得する。
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //日歩・逆日歩損( T + 1 )を取得する。
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //日歩・逆日歩損( T + 2 )を取得する。
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //日歩・逆日歩損( T + 3 )を取得する。
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //日歩・逆日歩損( T + 4 )を取得する。
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //日歩・逆日歩損( T + 5 )を取得する。
                l_dblContractInterestLoss = this.calcResultDetailMargin.getContractInterestLoss5();
                break;

            default :
                //nが0以上5以下でない時、0を返却する。
                l_dblContractInterestLoss = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //取得した日歩・逆日歩損を返却する。
        return l_dblContractInterestLoss;
    }

    /**
     * （get日歩・逆日歩益）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「日歩・逆日歩益」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「日歩・逆日歩益」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果詳細Params<信用顧客>.日歩・逆日歩益(T+n)<BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     */
    public double getContractInterestProfit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getContractInterestProfit(int)";
        log.entering(STR_METHOD_NAME);

        //日歩・逆日歩益
        double l_dblContractInterestProfit;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //日歩・逆日歩益( T + 0 )を取得する。
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //日歩・逆日歩益( T + 1 )を取得する。
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //日歩・逆日歩益( T + 2 )を取得する。
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //日歩・逆日歩益( T + 3 )を取得する。
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //日歩・逆日歩益( T + 4 )を取得する。
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //日歩・逆日歩益( T + 5 )を取得する。
                l_dblContractInterestProfit = this.calcResultDetailMargin.getContractInterestProfit5();
                break;

            default :
                //nが0以上5以下でない時、0を返却する。
                l_dblContractInterestProfit = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //取得した日歩・逆日歩益を返却する。
        return l_dblContractInterestProfit;
    }

    /**
     * （getその他建玉諸経費）<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「その他建玉諸経費」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「その他建玉諸経費」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果詳細Params<信用顧客>.その他建玉諸経費(T+n)<BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     */
    public double getContractOtherCost(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getContractOtherCost(int)";
        log.entering(STR_METHOD_NAME);

        //その他建玉諸経費
        double l_dblContractOtherCost;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //その他建玉諸経費( T + 0 )を取得する。
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //その他建玉諸経費( T + 1 )を取得する。
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //その他建玉諸経費( T + 2 )を取得する。
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //その他建玉諸経費( T + 3 )を取得する。
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //その他建玉諸経費( T + 4 )を取得する。
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //その他建玉諸経費( T + 5 )を取得する。
                l_dblContractOtherCost = this.calcResultDetailMargin.getContractOtherCost5();
                break;

            default :
                //nが0以上5以下でない時、0を返却する。
                l_dblContractOtherCost = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //取得したその他建玉諸経費を返却する。
        return l_dblContractOtherCost;
    }

    private static String objectsToString(Object[] l_objBindVars)
    {

        StringBuffer l_sb = new StringBuffer();
        if (l_objBindVars != null)
        {
            for (int i = 0; i < l_objBindVars.length; i++)
            {
                if (i > 0)
                {
                    l_sb.append(",");
                }
                l_sb.append("[").append(i).append("]=");
                l_sb.append(String.valueOf(l_objBindVars[i]));
            }
        }
        return l_sb.toString();
    }
}
@
