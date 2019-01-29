head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerCalcEquity.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 資産余力情報<現物顧客>(WEB3TPTradingPowerCalcEquity.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 nakazato(ACT) 新規作成
                   2006/09/11 徐宏偉 (中訊) モデルNo.010 
                   2006/09/11 徐宏偉 (中訊) 計算式書  No.002-004
Revesion History : 2007/09/24 孟亞南 (中訊) モデルNo.172 計算式書 No.008 No.009
                   2007/11/08 inomata (SCS) モデルNo.229 計算式書 No.015
Revesion History : 2008/04/01 崔遠鵬 (中訊) モデルNo.265
Revesion History : 2008/09/10 張少傑 (中訊) 計算式書No.016-017
Revesion History : 2008/09/10 劉剣 (中訊) モデルNo.299
*/
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.data.TpCalcResultEquityDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.define.WEB3TPOrixSecuredLoanLockDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.util.WEB3LogUtility;

/**
 * （資産余力情報<現物顧客>）<BR>
 * <BR>
 * 余力更新結果<現物顧客>をより、各種取引可能額を計算するクラス<BR>
 * <BR>
 * @@author  nakazato(ACT)
 * @@version 1.0
 */
public class WEB3TPTradingPowerCalcEquity
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcEquity.class);

    /**
     * （余力計算結果Params<現物顧客>）
     */
    protected TpCalcResultEquityParams calcResultEquity;

    /**
     * （余力計算結果詳細Params<現物顧客>）
     */
    protected TpCalcResultEquityDetailParams calcResultDetailEquity;

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
	 * （今回注文出金額）
	 * 
	 * ※現注文内容.概算代金
	 */
	private double thisTimOrderCont = 0.0;

    /**
     * @@roseuid 410DF85D037F
     */
    public WEB3TPTradingPowerCalcEquity()
    {

    }

    /**
     * (コンストラクタ)<BR>
     * 引数を属性にセットする。<BR>
     * <BR>
     * １）引数.余力計算結果<現物顧客>より、余力計算結果Params<現物顧客>オブジェクトを取得し、<BR>
     * 　@this.余力計算結果Params<現物顧客>にセットする。<BR>
     * <BR>
     * ２）引数.余力計算結果<現物顧客>より、余力計算結果詳細Params<現物顧客>オブジェクトを取得し、<BR>
     * 　@this.余力計算結果詳細Params<現物顧客>にセットする。<BR>
     * <BR>
     * ３）引数.余力計算条件を、this.余力計算条件にセットする。<BR>
     * <BR>
     * @@param l_calcResult - （余力計算結果）
     * @@param l_calcCondition - （余力計算条件）
     */
    public WEB3TPTradingPowerCalcEquity(List l_calcResult, WEB3TPCalcCondition l_calcCondition)
    {
        /*
         * Listより余力計算結果<現物顧客>オブジェクト、余力計算結果詳細<現物顧客>オブジェクト
         * を取得しプロパティにセットする。
         */
        for (Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object)l_iter.next();

            if (l_element instanceof TpCalcResultEquityParams)
            {
                TpCalcResultEquityParams l_params = (TpCalcResultEquityParams)l_element;
                this.setCalcResultEquity(l_params);
            }
            else if (l_element instanceof TpCalcResultEquityDetailParams)
            {
                TpCalcResultEquityDetailParams l_params = (TpCalcResultEquityDetailParams)l_element;
                this.setCalcResultDetailEquity(l_params);
            }
        }

        //パラメータ.余力計算条件をプロパティにセットする。
        this.calcCondition = l_calcCondition;
    }

    /**
     * (コンストラクタ)<BR>
     * 引数を属性にセットする。<BR>
     * <BR>
     * １）引数.余力計算結果<現物顧客>より、余力計算結果Params<現物顧客>オブジェクトを取得し、<BR>
     * 　@this.余力計算結果Params<現物顧客>にセットする。<BR>
     * <BR>
     * ２）引数.余力計算結果<現物顧客>より、余力計算結果詳細Params<現物顧客>オブジェクトを取得し、<BR>
     * 　@this.余力計算結果詳細Params<現物顧客>にセットする。<BR>
     * <BR>
     * ３）引数.使用可能現金情報を、this.使用可能現金情報にセットする<BR>
     * <BR>
     * ４）引数.余力計算条件を、this.余力計算条件にセットする。<BR>
     * <BR>
     * @@param l_calcResultEquity - （余力計算結果）
     * @@param l_actualBalanceInfo - （使用可能現金情報）
     * @@param l_calcCondition - （余力計算条件）
     */
    public WEB3TPTradingPowerCalcEquity(
        List l_calcResult,
        WEB3TPActualAccountBalanceInfo l_actualBalanceInfo,
        WEB3TPCalcCondition l_calcCondition)
    {
        /*
         * Listより余力計算結果<現物顧客>オブジェクト、余力計算結果詳細<現物顧客>オブジェクト
         * を取得しプロパティにセットする。
         */
        for (Iterator l_iter = l_calcResult.iterator(); l_iter.hasNext();)
        {
            Object l_element = (Object)l_iter.next();

            if (l_element instanceof TpCalcResultEquityParams)
            {
                TpCalcResultEquityParams l_params = (TpCalcResultEquityParams)l_element;
                this.setCalcResultEquity(l_params);
            }
            else if (l_element instanceof TpCalcResultEquityDetailParams)
            {
                TpCalcResultEquityDetailParams l_params = (TpCalcResultEquityDetailParams)l_element;
                this.setCalcResultDetailEquity(l_params);
            }
        }

        //パラメータ.余力計算条件をプロパティにセットする。
        this.calcCondition = l_calcCondition;

        //パラメータ.使用可能現金情報をプロパティにセットする。
        this.actualBalanceInfo = l_actualBalanceInfo;

    }
	/**
	 * (コンストラクタ)<BR>
	 * 引数を属性にセットする。<BR>
	 * <BR>
	 * １）this(List, 余力計算条件)をコールする。<BR>
	 * [引数]<BR>
	 * List： 引数.余力計算結果<現物顧客><BR>
	 * 余力計算条件： 引数.余力計算条件<BR>
	 * <BR>
	 * ２）引数.今回注文出金額を、this.今回注文出金額にセットする。<BR>
	 * <BR>
	 * @@param l_calcResult - （余力計算結果）
	 * @@param l_calcCondition - （余力計算条件）
	 * @@param l_thisTimOrderCont - （今回注文出金額）
	 */
	public WEB3TPTradingPowerCalcEquity(
			List l_calcResult, 
			WEB3TPCalcCondition l_calcCondition, 
			double l_thisTimOrderCont)
	{
		this(l_calcResult, l_calcCondition);
		this.thisTimOrderCont = Math.abs(l_thisTimOrderCont);
	}

    /**
     * （get余力計算結果Params<現物顧客>）<BR>
     * 
     * this.余力計算結果Params<現物顧客>を返却する。<BR>
     * @@return webbroker3.tradingpower.data.TpCalcResultEquityParams
     * @@roseuid 40FCBE970098
     */
    public TpCalcResultEquityParams getCalcResultEquity()
    {
        //this.余力計算結果Paramsを返却する。
        return this.calcResultEquity;
    }

    /**
     * （set余力計算結果Params<現物顧客>）<BR>
     * 
     * パラメータ.余力計算結果Params<現物顧客>をthis.余力計算結果Params<現物顧客>にセッ
     * トする。<BR>
     * @@param l_calcResultEquity - （余力計算結果Params<現物顧客>）
     * @@roseuid 40FCBEAC022F
     */
    public void setCalcResultEquity(TpCalcResultEquityParams l_calcResultEquity)
    {
        //パラメータ.余力計算結果Paramsをプロパティにセットする。
        this.calcResultEquity = l_calcResultEquity;
    }

    /**
     * （get余力計算結果詳細Params<現物顧客>）<BR>
     * 
     * this.余力計算結果詳細Params<現物顧客>を返却する。<BR>
     * @@return webbroker3.tradingpower.data.TpCalcResultEquityDetailParams
     */
    public TpCalcResultEquityDetailParams getCalcResultDetailEquity()
    {
        return calcResultDetailEquity;
    }

    /**
     * （set余力計算結果詳細Params<現物顧客>）<BR>
     * 
     * パラメータ.余力計算結果詳細Params<現物顧客>をthis.余力計算結果詳細Params<現物顧客>にセットする。<BR>
     * @@param l_calcResultDetailEquity - （余力計算結果詳細Params<現物顧客>）
     */
    public void setCalcResultDetailEquity(TpCalcResultEquityDetailParams l_calcResultDetailEquity)
    {
        this.calcResultDetailEquity = l_calcResultDetailEquity;
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
     * （get余力計算条件）<BR>
     * 
     * this.余力計算条件を返却する。<BR>
     * @@return webbroker3.tradingpower.WEB3TPCalcCondition
     * @@roseuid 40FCBEC100E6
     */
    public WEB3TPCalcCondition getCalcCondition()
    {
        //this.余力計算条件を返却する。
        return this.calcCondition;
    }

    /**
     * （set余力計算条件）<BR>
     * 
     * パラメータ.余力計算条件をthis.余力計算条件にセットする。<BR>
     * @@param l_calcCondition - （余力計算条件）
     * @@roseuid 40FCBEC703C5
     */
    public void setCalcCondition(WEB3TPCalcCondition l_calcCondition)
    {
        //パラメータ.余力計算条件をプロパティにセットする
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
     * 引数が0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@引数で指定された指定日(=n)の「預り金残高」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<現物顧客>.get預り金残高（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4722D03B1
     */
    public double getAccountBalance(int l_intSpecifiedPoint)
    {
        //預り金残高
        double l_dblAccountBalance;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //預り金残高( T + 0 )を取得する。
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //預り金残高( T + 1 )を取得する。
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //預り金残高( T + 2 )を取得する。
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //預り金残高( T + 3 )を取得する。
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //預り金残高( T + 4 )を取得する。
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //預り金残高( T + 5 )を取得する。
                l_dblAccountBalance = this.calcResultEquity.getAccountBalance5();
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
     * （get当日約定済代金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「当日約定済代金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 引数が0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@引数で指定された指定日(=n)の「当日約定済代金」を返却する。<BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<現物顧客>.get当日約定済代金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B472360305
     */
    public double getTodayExecutedAmount(int l_intSpecifiedPoint)
    {
        //当日約定済代金
        double l_dblTodayExecutedAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //当日約定済代金( T + 0 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //当日約定済代金( T + 1 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //当日約定済代金( T + 2 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //当日約定済代金( T + 3 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //当日約定済代金( T + 4 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //当日約定済代金( T + 5 )を取得する。
                l_dblTodayExecutedAmount = this.calcResultEquity.getTodayExecutedAmount5();
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
     * 
     * 引数で指定された指定日(=n)の、「当日未約定代金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 引数が0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@引数で指定された指定日(=n)の「当日未約定代金」を返却する。<BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<現物顧客>.get当日未約定代金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4723E03B1
     */
    public double getTodayUnexecutedAmount(int l_intSpecifiedPoint)
    {
        //当日未約定代金
        double l_dblTodayUnexecutedAmount;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //当日約定済代金( T + 0 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //当日約定済代金( T + 1 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //当日約定済代金( T + 2 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //当日約定済代金( T + 3 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //当日約定済代金( T + 4 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //当日約定済代金( T + 5 )を取得する。
                l_dblTodayUnexecutedAmount = this.calcResultEquity.getTodayUnexecutedAmount5();
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
     * 引数が0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@引数で指定された指定日(=n)の「日計り拘束金」を返却する。<BR>
     *  [返却値]<BR>
     * this.余力計算結果Params<現物顧客>.get日計り拘束金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4724502F5
     */
    public double getDayTradeRestraint(int l_intSpecifiedPoint)
    {
        //日計り拘束金
        double l_dblDayTradeRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //日計り拘束金( T + 0 )を取得する。
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //日計り拘束金( T + 1 )を取得する。
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //日計り拘束金( T + 2 )を取得する。
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //日計り拘束金( T + 3 )を取得する。
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //日計り拘束金( T + 4 )を取得する。
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //日計り拘束金( T + 5 )を取得する。
                l_dblDayTradeRestraint = this.calcResultEquity.getDayTradeRestraint5();
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
     * 引数が0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@引数で指定された指定日(=n)の「その他拘束金」を返却する。<BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<現物顧客>.getその他拘束金（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B472500305
     */
    public double getOtherRestraint(int l_intSpecifiedPoint)
    {
        //その他拘束金
        double l_dblOtherRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //その他拘束金( T + 0 )を取得する。
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //その他拘束金( T + 1 )を取得する。
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //その他拘束金( T + 2 )を取得する。
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //その他拘束金( T + 3 )を取得する。
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //その他拘束金( T + 4 )を取得する。
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //その他拘束金( T + 5 )を取得する。
                l_dblOtherRestraint = this.calcResultEquity.getOtherRestraint5();
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
     * （get預り証券評価額）<BR>
     * 
     * 引数で指定された指定日(=n)の、「預り証券評価額」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 引数が0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@引数で指定された指定日(=n)の「預り証券評価額」を返却する。<BR>
     * ［返却値］<BR>
     * this.余力計算結果Params<現物顧客>.ge預り証券評価額（T+n）<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4725602D6
     */
    public double getTrustSecurityAsset(int l_intSpecifiedPoint)
    {
        //預り証券評価額
        double l_dblTrustSecurityAsset;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //預り証券評価額( T + 0 )を取得する。
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //預り証券評価額( T + 1 )を取得する。
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //預り証券評価額( T + 2 )を取得する。
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //預り証券評価額( T + 3 )を取得する。
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //預り証券評価額( T + 4 )を取得する。
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //預り証券評価額( T + 5 )を取得する。
                l_dblTrustSecurityAsset = this.calcResultEquity.getTrustSecurityAsset5();
                break;

            default :
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getTrustSecurityAsset(int)");
        }

        //取得した預り証券評価額を返却する。
        return l_dblTrustSecurityAsset;
    }
    
    /**
     * (get預り金担保出金拘束金)<BR> 
     * <BR>
     * 引数で指定された指定日(=n)の、「預り金担保出金拘束金」を返却する。<BR> 
     * <BR>
     * １）　@引数チェックを行う。 <BR>
     * 引数が0以上5以下でない時、0を返却する。 <BR>
     * <BR>
     * ２）　@引数で指定された指定日(=n)の「預り金担保出金拘束金」を返却する。<BR>
     * <BR>
     * ［返却値］ <BR>
     * this.余力計算結果Params<現物顧客>.ge預り金担保出金拘束金（T+n）<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     */
    public double getCashDepositRestraint(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getCashDepositRestraint(int l_intSpecifiedPoint)";
        log.entering(STR_METHOD_NAME);
        
        //預り金担保出金拘束金
        double l_dblCashDepositRestraint;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //預り金担保出金拘束金( T + 0 )を取得する。
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //預り金担保出金拘束金( T + 1 )を取得する。
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //預り金担保出金拘束金( T + 2 )を取得する。
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //預り金担保出金拘束金( T + 3 )を取得する。
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //預り金担保出金拘束金( T + 4 )を取得する。
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //預り金担保出金拘束金( T + 5 )を取得する。
                l_dblCashDepositRestraint = this.calcResultEquity.getCashDepositRestraint5();
                break;

            default :
                //エラーをスロー
                log.error("パラメータ値不正。");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + ".getCashDepositRestraint(int)");
        }

        //取得した預り金担保出金拘束金を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblCashDepositRestraint;
    }

    /**
     * （calc使用可能現金）<BR>
     * 
     * 引数で指定された指定日(=n)の、「使用可能現金」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B5A85D01BE
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

        //使用可能現金（= 預り金残高 - 当日約定済代金 - 当日未約定代金 - その他拘束金）を計算する。
        double l_dblActualAccountBalance =
            this.getAccountBalance(l_intSpecifiedPoint)
                - this.getTodayExecutedAmount(l_intSpecifiedPoint)
                - this.getTodayUnexecutedAmount(l_intSpecifiedPoint)
                - this.getOtherRestraint(l_intSpecifiedPoint);

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
     * @@roseuid 40B5A86A0325
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
        double l_dblDayTradeRestraint;

        //パラメータ.指定日がT+0の時
        if (l_intSpecifiedPoint == 0)
        {
            //日計り拘束金に、日計り拘束金(0)と特別立替金実績の大きい方を代入
            l_dblDayTradeRestraint =
                Math.max(
                    this.getDayTradeRestraint(l_intSpecifiedPoint),
                    this.calcCondition.getSpecialDebitAmount());
        }
        //それ以外
        else
        {
            //日計り拘束金に、日計り拘束金(T+0)を代入
            l_dblDayTradeRestraint = this.getDayTradeRestraint(l_intSpecifiedPoint);
        }

        //引出可能現金（= 使用可能現金 - 日計り拘束金）を計算する。
        double l_dblActualPaymentBalance =
            this.calcActualAccountBalance(l_intSpecifiedPoint) - l_dblDayTradeRestraint;

        //計算した引出可能現金を返却する。
        return l_dblActualPaymentBalance;
    }

    /**
     * （calc株式買付可能額）<BR>
     * 
     * 引数で指定された指定日(=n)の、「株式買付可能額」を返却する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B47344021C
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

        //株式買付可能額（= 使用可能現金 + 預り証券評価額）を計算する。
        double l_dblEquityTradingPower =
            this.calcActualAccountBalance(l_intSpecifiedPoint)
                + this.getTrustSecurityAsset(l_intSpecifiedPoint);

        //計算した株式買付可能額を返却する。
        return l_dblEquityTradingPower;
    }

    /**
     * （calc株式買付可能額<日計り拘束金考慮>）<BR>
     * 
     * 引数で指定された指定日(=n)の、「株式買付可能額<日計り拘束金考慮>」を返却する。<B
     * R>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     * @@roseuid 40B4735B00A5
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

        //株式買付可能額<日計り拘束金>（= 引出可能現金 + 預り証券評価額）を計算する。
        double l_dblEquityTradingPower =
            this.calcActualPaymentBalance(l_intSpecifiedPoint)
                + this.getTrustSecurityAsset(l_intSpecifiedPoint);

        //計算した株式買付可能額を返却する。
        return l_dblEquityTradingPower;
    }

    /**
     * （calcその他商品買付可能額)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の「その他商品買付可能額」を計算する。<BR>
     * this.calc使用可能現金(int)を返却する
     * <BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double calcOtherTradingPower(int l_intSpecifiedPoint)
    {
            //使用可能現金を返却する。
            return calcActualAccountBalance( l_intSpecifiedPoint );
    }

    /**
     * （calcその他商品買付可能額<日計拘束金考慮>)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の「その他商品買付可能額」を計算する。<BR>
     * this.calc引出可能現金(int)を返却する
     * <BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double calcOtherTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
            //引出可能現金を返却する。
            return calcActualPaymentBalance( l_intSpecifiedPoint );
    }
    
    /**
     * (calc出金可能額)<BR> 
     * <BR>
     * 引数で指定された指定日(=n)の「出金可能額」を計算する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     */
    public double calcPaymentTradingPower(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcPaymentTradingPower(int)";
        log.entering(STR_METHOD_NAME);
        
        // ・    使用可能現金(n)  資産余力情報<現物顧客>.calc使用可能現金(n)
        double l_dblActualAccountBalance = 
            this.calcActualAccountBalance(l_intSpecifiedPoint);
        
        // ・    預り金担保出金拘束金(n) 資産余力情報<現物顧客>.get預り金担保出金拘束金(n)
        double l_dblCashDepositRestraint = 
            this.getCashDepositRestraint(l_intSpecifiedPoint);
        
        // ①@　@出金可能額(指定日)を求める。
        // 出金可能額(n) =　@使用可能現金(n)　@-　@預り金担保出金拘束金(n)
        // ※    nは、引数の指定日。
        double l_dblPaymentTradingPower = 
            l_dblActualAccountBalance - l_dblCashDepositRestraint;
       
        // ②　@計算した出金可能額(n)を返却する
        log.exiting(STR_METHOD_NAME);
        return l_dblPaymentTradingPower;
    }
    
    /**
     * (calc出金可能額<日計り拘束金考慮>) <BR>
     * <BR>
     * 引数で指定された指定日(=n)の「出金可能額」を計算する。 <BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。 <BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (指定日)
     * @@return double
     */
    public double calcPaymentTradingPowerIncDayTrade(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcPaymentTradingPowerIncDayTrade(int)";
        log.entering(STR_METHOD_NAME);
        
        //※   計算式に使用する各値の取得方法@
        //・   引出可能現金(n)       ・・・資産余力情報<現物顧客>.calc引出可能現金(n)
        double l_dblActualPaymentBalance = 
            this.calcActualPaymentBalance(l_intSpecifiedPoint);
        
        //・   預り金担保出金拘束金(n)   ・・・資産余力情報<現物顧客>.get預り金担保出金拘束金(n)
        double l_dblCashDepositRestraint = 
            this.getCashDepositRestraint(l_intSpecifiedPoint);
        
        //①@　@出金可能額<日計り拘束金考慮>(指定日)を求める。
        //出金可能額<日計り拘束金考慮>(n) =　@引出可能現金(n) - 預り金担保出金拘束金(n)
        double l_dblPaymentTradingPowerIncDayTrade = 
            l_dblActualPaymentBalance - l_dblCashDepositRestraint;
        
        //②　@計算した出金可能額<日計り拘束金考慮>(n)を返却する
        log.exiting(STR_METHOD_NAME);
        return l_dblPaymentTradingPowerIncDayTrade;
    }

    /**
     * （calc出金可能額）<BR>
     * 
     * 最小の「出金可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFEB840029
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
     * <BR>
     * 最小の「投信買付可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40DFEB900394
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
     * @@roseuid 40DFEB9E00F4
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

        //入金請求額(n) = Abs(Min(引出可能現金(n), 0))
        double calcDemandamount = Math.min(this.calcActualPaymentBalance(l_intSpecifiedPoint), 0);
        calcDemandamount = Math.abs(calcDemandamount);        

        return calcDemandamount;
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

        //立替金(n) = Abs(Min(使用可能現金(n), 0))
        double l_dblDebitAmount = Math.min(this.calcActualAccountBalance(l_intSpecifiedPoint), 0);
        l_dblDebitAmount = Math.abs(l_dblDebitAmount);        

        return l_dblDebitAmount;
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

        //立替金(n) = Abs(Min(引出可能現金(n), 0))
        double l_dblSpecialDebitAmount = Math.min(this.calcActualPaymentBalance(l_intSpecifiedPoint), 0);
        l_dblSpecialDebitAmount = Math.abs(l_dblSpecialDebitAmount);        

        return l_dblSpecialDebitAmount;
    }

    /**
     * （calc適用株式買付可能額）<BR>
     * 
     * 最小の「株式買付可能額」を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@return WEB3TPCalcResult
     * @@roseuid 40BFFD9D00EA
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
                //預り証券評価制の時
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //株式買付可能額を計算する。
                    l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
                }
                //預り証券評価制でない時
                else
                {
                    //使用可能現金を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualAccountBalance(index));
                }
            }
            //それ以外
            else
            {
                //預り証券評価制の時
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //株式買付可能額<日計り拘束金考慮>を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
                }
                //預り証券評価制でない時
                else
                {
                    //引出可能現金を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualPaymentBalance(index));
                }
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
    
        /*
         * 引出可能現金を取得する。
         */
        //引出可能現金（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //引出可能現金(BigDecimal型)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(l_dblActualPaymentBalance);
        //適用日<引出可能現金>
        int l_intActPayAppliedPoint = 0;
    
        //始点(指定日の範囲) = T+0
        int l_intStart = WEB3TPSpecifiedPointDef.T_0;
        //終点(指定日の範囲)
        int l_intEnd;

        //預り証券評価制顧客の場合
        if(this.calcCondition.isAssetEvalDiv() == true)
        {
            //終点(指定日の範囲) = 発注日<株式／信用>
            l_intEnd = this.calcCondition.getEquityBizDate();
        }
        //預り証券非評価制顧客の場合
        else
        {
            //終点(指定日の範囲) = 余力計算基準日<株式買付／信用現引>-1
            l_intEnd = l_intBasePoint - 1;
        }

        //指定日の範囲（始点～終点）でループ
        for(int index = l_intStart; index <= l_intEnd; index++)
        {
            //引出可能現金(項番)(BigDecimal型)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcActualPaymentBalance(index));

            //引出可能現金(項番)が引出可能現金より小さい時
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //引出可能現金に引出可能現金(項番)を代入する。
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //適用日に項番を代入する。
                l_intActPayAppliedPoint = index;
            }
        }
    
        /*
         * 取得した、株式買付可能額、引出可能現金より 最終的な株式買付可能額を計算する。
         */
        //［a. 株式買付可能額 < 0の場合］
        if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            //株式買付可能額 = -1
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }

        //［a. 引出可能額 < 0の場合］
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.株式買付可能額 >= 0 の場合]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<引出可能現金>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.株式買付可能額 < 0 かつ 適用日 > 適用日<引出可能現金> の場合]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<引出可能現金>
                l_intAppliedPoint = l_intActPayAppliedPoint;
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
     * @@return WEB3TPCalcResult
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
                //預り証券評価制の時
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //株式買付可能額を計算する。
                    l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
                }
                //預り証券評価制でない時
                else
                {
                    //使用可能現金を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualAccountBalance(index));
                }
            }
            //それ以外
            else
            {
                //預り証券評価制の時
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //株式買付可能額<日計り拘束金考慮>を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
                }
                //預り証券評価制でない時
                else
                {
                    //引出可能現金を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualPaymentBalance(index));
                }
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
                //預り証券評価制の時
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //株式買付可能額を計算する。
                    l_bdCurEquityTradingPower = new BigDecimal(this.calcEquityTradingPower(index));
                }
                //預り証券評価制でない時
                else
                {
                    //使用可能現金を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualAccountBalance(index));
                }
            }
            //それ以外
            else
            {
                //預り証券評価制の時
                if (this.calcCondition.isAssetEvalDiv() == true)
                {
                    //株式買付可能額<日計り拘束金考慮>を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcEquityTradingPowerIncDayTrade(index));
                }
                //預り証券評価制でない時
                else
                {
                    //引出可能現金を計算する。
                    l_bdCurEquityTradingPower =
                        new BigDecimal(this.calcActualPaymentBalance(index));
                }
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
    
        /*
         * 引出可能現金を取得する。
         */
        //引出可能現金（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //引出可能現金(BigDecimal型)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(
                l_dblActualPaymentBalance);
        //適用日<引出可能現金>
        int l_intActPayAppliedPoint = 0;

        //始点(指定日の範囲) = T+0
        int l_intStart = WEB3TPSpecifiedPointDef.T_0;
        //終点(指定日の範囲)
        int l_intEnd;

        //預り証券評価制顧客の場合
        if(this.calcCondition.isAssetEvalDiv() == true)
        {
            //終点(指定日の範囲) = 発注日<株式／信用>
            l_intEnd = this.calcCondition.getEquityBizDate();
        }
        //預り証券非評価制顧客の場合
        else
        {
            //終点(指定日の範囲) = 余力計算基準日<株式買付／信用現引>-1
            l_intEnd = l_intBasePoint - 1;
        }

        //指定日の範囲（始点～終点）でループ
        for(int index = l_intStart; index <= l_intEnd; index++)
        {
            //引出可能現金(項番)(BigDecimal型)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcActualPaymentBalance(index));

            //引出可能現金(項番)が引出可能現金より小さい時
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //引出可能現金に引出可能現金(項番)を代入する。
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //適用日に項番を代入する。
                l_intActPayAppliedPoint = index;
            }
        }

        /*
         * 取得した、株式買付可能額、引出可能現金より 最終的な株式買付可能額を計算する。
         */
        //［a. 株式買付可能額 < 0の場合］
        if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            //株式買付可能額 = -1
            l_bdEquityTradingPower = new BigDecimal(-1.0);
        }

        //［a. 引出可能額 < 0の場合］
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.株式買付可能額 >= 0 の場合]
            if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<引出可能現金>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.株式買付可能額 < 0 かつ 適用日 > 適用日<引出可能現金> の場合]
            else if(l_bdEquityTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //株式買付可能額 = -1
                l_bdEquityTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<引出可能現金>
                l_intAppliedPoint = l_intActPayAppliedPoint;
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
     * （calc適用株式買付可能額)<BR>
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
     * －this.余力計算条件.set余力計算基準日<現物株式/信用現引>()をコール<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@int：１）の戻り値<BR>
     *<BR>
     *  ５）３）の戻り値を返却する。<BR>
     * <BR>
     * @@param l_intBasePoint
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
     * （calc適用その他商品買付可能額）<BR>
     * 
     * 引数の注文種別に該当する最小の「その他商品買付可能額」を計算する。<BR>
     * 引数.注文種別が指定されななかった時（注文種別=null）、注文種別=OrderTypeEnum.その他とする。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * @@param l_orderTypeEnum - （注文種別）
     * OrderTypeEnumにて定義
     * @@return WEB3TPCalcResult
     * @@roseuid 40DBAEA100B2
     */
    public WEB3TPCalcResult calcAppliedOtherTradingPower(OrderTypeEnum l_orderTypeEnum)
    {
        /*
         * 余力計算基準日を設定する。
         */
    
        //余力計算基準日
        int l_intBasePoint;
    
        //パラメータ.注文種別がnull、証拠金振替、為替保証金振替、中国株式振替 または 大証金への振替
        //または オリックスクレジットへの振替 または　@CFD振替注文（預り金からCFD口座の時
        if (l_orderTypeEnum == null 
                || l_orderTypeEnum.equals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.TRANSFER_TO_FEQ) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK) == true
                || l_orderTypeEnum.equals(OrderTypeEnum.TO_ORIX_CREDIT) == true
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
            //［a. 引数.注文種別 =　@出金注文　@または　@証拠金への振替　@
            //または　@為替保証金への振替　@または　@外国株式への振替　@または オリックスクレジットへの振替 
            //または　@CFD振替注文（預り金からCFD口座)の時］
            if(l_orderTypeEnum != null
                    && (l_orderTypeEnum.equals(OrderTypeEnum.CASH_OUT) == true
                            || l_orderTypeEnum.equals(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN) == true
                            || l_orderTypeEnum.equals(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT) == true 
                            || l_orderTypeEnum.equals(OrderTypeEnum.TRANSFER_TO_FEQ) == true
                            || l_orderTypeEnum.equals(OrderTypeEnum.TO_ORIX_CREDIT) == true
                            || l_orderTypeEnum.equals(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT) == true))
            {
                if (index == l_intBasePoint)
                {
                    //その他商品買付可能額(n)を取得する
                    l_bdCurOtherTradingPower = new BigDecimal(this.calcPaymentTradingPower(index));
                }
                //それ以外
                else
                {
                    //その他商品買付可能額<日計り拘束金考慮>(n)を取得する
                    l_bdCurOtherTradingPower =
                        new BigDecimal(this.calcPaymentTradingPowerIncDayTrade(index));
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
            else
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
        }
    
        /*
         * 引出可能現金を取得する。
         */
        //引出可能現金（最小値を求めたいのでdouble型の最大値を初期値にセット）
        double l_dblActualPaymentBalance = Double.MAX_VALUE;
        //引出可能現金(BigDecimal型)
        BigDecimal l_bdActualPaymentBalance = new BigDecimal(
                l_dblActualPaymentBalance);
        //適用日<引出可能現金>
        int l_intActPayAppliedPoint = 0;

        //指定日の範囲（0～余力計算基準日<株式買付／信用現引>-1）でループ
        for (int index = WEB3TPSpecifiedPointDef.T_0; index < l_intBasePoint; index++)
        {
            //引出可能現金(項番)(BigDecimal型)
            BigDecimal l_bdCurActualPaymentBalance = new BigDecimal(this
                .calcActualPaymentBalance(index));

            //引出可能現金(項番)が引出可能現金より小さい時
            if(l_bdCurActualPaymentBalance.compareTo(l_bdActualPaymentBalance) < 0)
            {
                //引出可能現金に引出可能現金(項番)を代入する。
                l_bdActualPaymentBalance = l_bdCurActualPaymentBalance;
                //適用日に項番を代入する。
                l_intActPayAppliedPoint = index;
            }
        }
        
        /*
         * 取得した、その他商品買付可能額、引出可能現金より
         * 最終的なその他商品買付可能額を計算する。
         */
        //［a. その他商品買付可能額 < 0の場合］
        if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0)
        {
            //その他商品買付可能額 = -1
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }

        //［a. 引出可能額 < 0の場合］
        if(l_bdActualPaymentBalance.compareTo(new BigDecimal(0.0)) < 0)
        {
            //[b.その他商品買付可能額 >= 0 の場合]
            if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) >= 0)
            {
                //その他商品買付可能額 = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<引出可能現金>
                l_intAppliedPoint = l_intActPayAppliedPoint;
            }
            //[b.その他商品買付可能額 < 0 かつ 適用日 > 適用日<引出可能現金> の場合]
            else if(l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) < 0
                    && l_intAppliedPoint > l_intActPayAppliedPoint)
            {
                //その他商品買付可能額 = -1
                l_bdOtherTradingPower = new BigDecimal(-1.0);
                //適用日 = 適用日<引出可能現金>
                l_intAppliedPoint = l_intActPayAppliedPoint;
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
        //その他商品買付余力停止区分 == true かつ 引数.注文種別 not in (
        //出金、証拠金振替、為替保証金振替、中国株式振替、大証金への振替、リックスクレジットへ、
        //CFD振替注文（預り金からCFD口座))の場合
        if (this.calcCondition.isOtherTradingStop() == true
            && !(OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(l_orderTypeEnum)
                || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }
        //出金余力停止中 かつ 引数.注文種別in (出金、証拠金振替、為替保証金振替、
        //中国株式振替、大証金への振替オ、リックスクレジットへの振替、
        //CFD振替注文（預り金からCFD口座))の場合
        if (this.calcCondition.isPaymentStop() == true
            && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(l_orderTypeEnum)
                || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))          
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }
        
        // [預り金担保出金停止区分 == true 　@かつ
        //引数.注文種別 in （出金、証拠金への振替、為替保証金への振替、中国株式への振替、リックスクレジットへ、
        //CFD振替注文（預り金からCFD口座））の場合]
        //その他商品買付可能額 = -1をセットする。
        if (this.calcCondition.isCashDepositStopDiv() == true
            && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
                || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
                || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)))             
        {
            l_bdOtherTradingPower = new BigDecimal(-1.0);
        }

        //その他商品買付可能額<証券担保ローン考慮>を求める。
        //[a.その他商品買付可能額 >= 0 かつ
        //引数.注文種別 in （出金、証拠金への振替、為替保証金への振替、中国株式への振替、大証金への振替、
        //CFD振替注文（預り金からCFD口座）) かつ
        //証券担保ローン口座開設済顧客（担保ローン出金拘束金レコード有）==true の場合]
        if (l_bdOtherTradingPower.compareTo(new BigDecimal(0.0)) >= 0
           && (OrderTypeEnum.CASH_OUT.equals(l_orderTypeEnum)
               || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
               || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
               || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
               || OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(l_orderTypeEnum)
               || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum))
           && this.calcCondition.isOrixSecuredLoanAccOpenDiv())
        {
            //[b.金額ロックフラグ == 1 の場合]
            if (WEB3TPOrixSecuredLoanLockDef.ORIX_SECURED_LOAN.equals(
                this.calcCondition.getOrixSecuredLoanLockFlag()))
            {
                //その他商品買付可能額 = -1 をセットする。
                l_bdOtherTradingPower = new BigDecimal(-1.0);
            }

            //オリックスクレジット基準出金可能額
            BigDecimal l_dbOrixCredit = new BigDecimal("0");
            try
            {
                l_dbOrixCredit = new BigDecimal("" + this.calcOrixCreditPaymentPower());
            }
            catch (WEB3SystemLayerException l_ex)
            {
                //エラーをスロー
                log.error("illegal Argument");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + ".calcAppliedOtherTradingPower(OrderTypeEnum)");
            }
            //[b.オリックスクレジット基準出金可能額 >= 0 の場合]
            if (l_dbOrixCredit.compareTo(new BigDecimal("0")) >= 0)
            {
                l_bdOtherTradingPower = l_bdOtherTradingPower.min(l_dbOrixCredit);
            }
            else
            {
                //その他商品買付可能額 = -1 をセットする。
                l_bdOtherTradingPower = new BigDecimal(-1.0);
            }
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
     * 　@　@　@－this.余力計算条件.get余力計算基準日<投信買付>()をコール<BR> 
     * <BR>
     * 　@　@[a.引数.注文種別 == 1001：出金 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<出金>()をコール<BR> 
     * <BR>
     * 　@　@[a.引数.注文種別 == 605：OP新規買建 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<オプション新規買建>()をコール<BR> 
     * <BR>
     * 　@　@[a.引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,<BR>
     * 　@　@　@　@1013:外国株式への振替,1020:オリックスクレジットへの振替,<BR>
     * 　@　@　@　@1021:CFD振替注文（預り金からCFD口座）]<BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<その他買付>()をコール <BR>
     * <BR>
     * 　@　@[a.以外の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<その他買付>()をコール<BR> 
     * <BR>
     * ２）余力計算基準日に、引数.基準日をセットする。 <BR>
     * 　@　@[a.引数.注文種別 == 201：投信買付、203：投信募集、204：投信乗換 の場合] <BR>
     * 　@　@　@－this.余力計算条件.set余力計算基準日<投信買付>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 1001：出金 の場合] <BR>
     * 　@　@　@?this.余力計算条件.set余力計算基準日<出金>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 605：OP新規買建 の場合] <BR>
     * 　@　@　@?this.余力計算条件.set余力計算基準日<オプション新規買建>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,<BR>
     * 　@　@　@　@1013:外国株式への振替,1020:オリックスクレジットへの振替,<BR>
     * 　@　@　@　@1021:CFD振替注文（預り金からCFD口座）] <BR>
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
     * 　@　@　@注文種別：引数.注文種別 但し、a.以外の場合についてはnull <BR>
     * <BR>
     * ４）退避していた、基準日を戻す <BR>
     * 　@　@[a.引数.注文種別 == 201：投信買付、203：投信募集、204：投信乗換 の場合] <BR>
     * 　@　@　@－this.余力計算条件.get余力計算基準日<投信買付>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 1001：出金 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<出金>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 605：OP新規買建 の場合] <BR>
     * 　@　@　@?this.余力計算条件.get余力計算基準日<オプション新規買建>()をコール <BR>
     * <BR>
     * 　@　@[a.引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,<BR>
     * 　@　@　@　@1013:外国株式への振替,1020:オリックスクレジットへの振替,<BR>
     * 　@　@　@　@1021:CFD振替注文（預り金からCFD口座）] <BR>
     * 　@　@　@-this.余力計算条件.set余力計算基準日<その他買付>()をコール <BR>
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
        //引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,1013:外国株式への振替,
        //1020:オリックスクレジットへの振替, 1021:CFD振替注文（預り金からCFD口座）
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderTypeEnum)
                || OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderTypeEnum)
                || OrderTypeEnum.TRANSFER_TO_FEQ.equals(l_orderTypeEnum)
                || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_orderTypeEnum)
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
     * (calc株式買付可能額～連続注文～) <BR>
     * <BR>
     * 最小の「株式買付可能額～連続注文～」を計算する。 <BR>
     * <BR>
     * 1)引数を基準日とした場合の適用株式買付可能額を取得する。 <BR>
     * <BR>
     * 　@-this.calc適用株式買付可能額(int)をコールする。 <BR>
     * <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@int：引数.基準日<BR> 
     * <BR>
     *   -株式買付可能額を取得する。 <BR>
     * <BR>
     * 　@　@株式買付可能額 = 余力計算結果.取引可能額 <BR>
     * <BR>
     * 2)現物買付予約注文の(現買)概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@[this.当日株式予約注文単位一覧 == null  <BR>
     * 　@　@または this.当日株式予約注文単位一覧.size() == 0 場合] <BR>
     * <BR>
     * 　@　@(現買)概算受渡代金合計 = 0 <BR>
     * <BR>
     * 　@[以外の場合] <BR>
     * <BR>
     * 　@　@-this.当日株式予約注文単位一覧を以下の条件でフィルタする。 <BR>
     * <BR>
     * 　@　@　@[検索条件]<BR> 
     * 　@　@　@　@注文種別 = '1：現物買注文'<BR> 
     * 　@　@　@　@注文有効状態 = ’1：オープン’<BR> 
     * <BR>
     * 　@　@-フィルタ結果より概算受渡代金を集計する。 <BR>
     * <BR>
     * 　@　@　@(現買)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金) <BR>
     * <BR>
     * 3)株式買付可能額?連続注文?を返却する <BR>
     * <BR>
     * 　@返却値 = 株式買付可能額 - (現買)概算受渡代金合計 <BR>
     * <BR>
     * @@param l_intBasePoint - (基準日)
     * @@return double
     */
    public double calcSuccEquityTradingPower(int l_intBasePoint)
    {
        /*
         * 引数.指定日を基準日とした場合の適用株式買付可能額を取得する。
         */
        WEB3TPCalcResult l_result = this.calcAppliedEquityTradingPower(l_intBasePoint);

        //適用株式買付可能額
        double l_dblEqTradingPower = l_result.tradingPower;

        /*
         * 現物買付予約注文の(現買)概算受渡代金を集計する。
         */
        //(現買)概算受渡代金合計
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

                    //注文種別 == 1:現物買注文 の場合
                    if(OrderTypeEnum.EQUITY_BUY.equals(l_orderType) == true)
                    {
                        //(現買)概算受渡代金合計 = sum(株式予約注文単位Row.概算受渡代金)
                        l_dblRevEqTradingPower = l_dblRevEqTradingPower
                                + Math.abs(l_params.getEstimatedPrice());
                    }
                }
            }
        }

        /*
         * 株式買付可能額～連続注文～を返却する
         * 
         * 返却値 = 株式買付可能額 - (現買)概算受渡代金合計 
         */
        return l_dblEqTradingPower - l_dblRevEqTradingPower;
    }

    /**
     * (staticメソッド)(find余力計算結果<現物顧客>?口座ＩＤ指定?) <BR>
     * <BR>
     * １）顧客オブジェクトを生成する。 <BR>
     * <BR>
     * 　@?コンストラクタ、WEB3GentradeMainAccount()コール <BR>
     * 　@　@［引数］ <BR>
     * 　@　@　@口座ID：引数.口座ID <BR>
     * <BR>
     * ２）"余力再計算基準時間"を取得する。 <BR>
     * <BR>
     * 　@?プロセス管理テーブルを以下の条件で検索する。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@プロセスID："0006:余力再計算基準時間" <BR>
     * 　@　@　@証券会社コード:顧客オブジェクト.getDataSourceObject().get証券会社コード()<BR> 
     * 　@　@　@部店コード：顧客オブジェクト.getDataSourceObject().get部店コード() <BR>
     * <BR>
     * 　@?"余力再計算基準時間"を取得する。<BR> 
     * 　@　@[a.検索結果 != null の場合] <BR>
     * 　@　@　@"余力再計算基準時間" = プロセス管理Params.最終更新時刻<BR> 
     * <BR>
     * 　@　@[a.検索結果 == null の場合]<BR> 
     * 　@　@　@"余力再計算基準時間" = null <BR>
     * <BR>
     * ３）最新の余力計算結果Paramsオブジェクトを取得する。 <BR>
     * <BR>
     * 　@?余力計算結果テーブル(現物顧客)を以下の条件で検索する。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@顧客ID：引数.口座ID<BR> 
     * 　@　@　@作成日付：“最新の日付”<BR> 
     * 　@　@　@（余力計算結果（現物顧客）IDの降順）<BR>
     * <BR>
     * 　@※検索結果 == null または 検索結果.size() == 0 場合、<BR>
     * 　@　@余力再計算フローへ <BR>
     * <BR>
     * ４）最新の余力計算結果詳細Paramsオブジェクトを取得する。 <BR>
     * <BR>
     * 　@[a."余力再計算基準時間" == null または、 <BR>
     * 　@　@ "余力再計算基準時間" <  余力計算結果Paramsオブジェクト.作成日付] <BR>
     * <BR>
     * 　@　@?余力計算結果詳細テーブル(現物顧客)を以下の条件で検索する。 <BR>
     * 　@　@　@［検索条件］ <BR>
     * 　@　@　@　@余力計算結果（現物顧客）ID：余力計算結果Paramsオブジェクト.余力計算結果（現物顧客）ID <BR>
     * <BR>
     * 　@　@※余力計算結果詳細Paramsが取得できなかった場合、例外をスローする。 <BR>
     * <BR>
     * 　@[a.以外の場合] <BR>
     * 　@　@※余力再計算フローへ<BR> 
     * <BR>
     * ５）返却値(List型)を設定し、呼び出し元へ返却する。 <BR>
     * <BR>
     * 　@[返却値]<BR> 
     * 　@　@Listの、0番目の要素：３）で取得した、余力計算結果Params<BR> 
     * 　@　@Listの、1番目の要素：４）で取得した、余力計算結果詳細Params<BR> 
     * <BR>
     * ：余力再計算フロー <BR>
     * <BR>
     * E-1)信用口座開設区分を取得する。<BR> 
     * <BR>
     * ?顧客オブジェクト.is信用口座開設()をコール<BR> 
     * 　@[引数] <BR>
     * 　@　@弁済区分＝”指定なし<BR> 
     * <BR>
     * 　@※信用顧客の場合(is信用口座開設==true)、例外をスローする。 <BR>
     * <BR>
     * E-2)補助口座オブジェクトを取得する。<BR> 
     * <BR>
     * 　@?顧客オブジェクト.getSubAccount()<BR> 
     * 　@　@[引数] <BR>
     * 　@　@　@補助口座タイプ："株式取引口座(預り金)" <BR>
     * <BR>
     * E-3）余力計算条件オブジェクトを生成する。<BR> 
     * <BR>
     * 　@?余力計算条件.create余力計算条件<標準>()をコール <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@補助口座：補助口座オブジェクト<BR> 
     * <BR>
     * E-4）余力更新オブジェクトを生成する。<BR> 
     * <BR>
     * 　@?コンストラクタ、余力更新()をコール<BR> 
     * 　@　@[引数] <BR>
     * 　@　@　@口座ID：引数.口座ID<BR> 
     * 　@　@　@信用顧客フラグ：is信用口座開設()の戻り値<BR> 
     * 　@　@　@余力計算条件：create余力計算条件()の戻り値 <BR>
     * 　@　@　@現注文内容：null <BR>
     * <BR>
     * E-5）余力更新内容(List)を取得しDBに格納する。<BR> 
     * <BR>
     * 　@?余力更新オブジェクト.calc余力更新内容<現物顧客>()をコール<BR> 
     * <BR>
     * 　@?余力更新オブジェクト.save余力更新内容<現物顧客>()をコール <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@List：余力更新オブジェクト.calc余力更新内容<現物顧客>()の戻り値 <BR>
     * <BR>
     * E-7）余力更新オブジェクト.calc余力更新内容<現物顧客>()の戻り値を返却する。 <BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)
     * @@return List
     */
    public static List findCalcResultEquityParams(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(long)";
        log.entering(STR_METHOD_NAME);

        //余力計算結果テーブル（現物顧客）の検索条件
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" account_id = ? ");
        l_strWhere.append(" and created_timestamp = ");
        l_strWhere.append(" ( ");
        l_strWhere.append("  select max(created_timestamp) ");
        l_strWhere.append("  from tp_calc_result_equity ");
        l_strWhere.append("  where account_id = ? ");
        l_strWhere.append(" ) ");
        
        Object[] l_bindVars = { new Long(l_lngAccountId),
                               new Long(l_lngAccountId) };       

        log.debug(
            "Finding TpCalcResultEquityParams where="
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
            //余力計算結果テーブル<現物顧客>を検索
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_lisRow =
                l_qp.doFindAllQuery(
                    TpCalcResultEquityParams.TYPE,
                    l_strWhere.toString(),
                    "calc_result_equity_id desc",
                    null,
                    l_bindVars,
                    new RowType[] {TpCalcResultEquityParams.TYPE});
            
            //余力計算結果が検索された場合
            if(l_lisRow != null && l_lisRow.size() != 0)
            {
                //余力計算結果Paramsを取得
                TpCalcResultEquityParams l_resultParams = (TpCalcResultEquityParams)l_lisRow.get(0);

                /*
                 * 最新の余力計算結果詳細Paramsオブジェクトを取得する。 
                 */
                //[a."余力再計算基準時間" == null または、 
                //   "余力再計算基準時間" <  余力計算結果Paramsオブジェクト.作成日付] 
                if(l_updateTimeStamp == null
                        || l_updateTimeStamp.before(l_resultParams.getCreatedTimestamp()) == true)
                {
                    //余力計算結果詳細<現物顧客>Paramsを取得
                    TpCalcResultEquityDetailParams l_resultDetailParams = (TpCalcResultEquityDetailParams)TpCalcResultEquityDetailDao.findRowByPk(l_resultParams.getCalcResultEquityId());

                    l_updResults = new ArrayList();
                    l_updResults.add(l_resultParams);
                    l_updResults.add(l_resultDetailParams);

                    //取得した余力更新内容(List)を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return l_updResults;
                }
            }
            
            log.error("TpCalcResultEquityParams:data is not found");

            //信用口座開設区分を取得
            boolean l_blnMargin =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            //信用顧客の場合
            if (l_blnMargin == true)
            {
                //エラーをスロー
                log.error("illegal Argument");
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

            //余力更新内容(List)を取得
            l_updResults = l_tpUpd.calcTradingpowerUpdResultEquity(); 
            //余力更新内容をテーブルにInsert
            l_tpUpd.saveTradingpowerUpdResultEquity(l_updResults);

            //取得した余力更新内容(List)を返却する
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
     * （find余力計算結果<現物顧客>Params）<BR>
     * (staticメソッド)<BR>
     * <BR>
     * １）DBより該当顧客の最新の余力計算結果Params<現物顧客>を１行取得する。<BR>
     * 　@[対象テーブル]<BR>
     * 　@　@余力計算結果テーブル（現物顧客）<BR>
     * <BR>
     * 　@［検索条件］<BR>
     * 　@　@余力計算結果(現物顧客)ＩＤ：引数.余力計算結果ＩＤ<BR>
     * <BR>
     * 　@　@※余力計算結果Paramsが取得できなかった場合、エラーをスローする。<BR>
     * <BR>
     * ２）DBより該当顧客の最新の余力計算結果詳細Params<現物顧客>を１行取得する。<BR>
     * <BR>
     * 　@[対象テーブル]<BR>
     * 　@　@余力計算結果詳細テーブル（現物顧客）<BR>
     * <BR>
     * 　@［検索条件］<BR>
     * 　@　@余力計算結果(現物顧客)ID：引数.余力計算結果ＩＤ<BR>
     * <BR>
     * 　@　@※余力計算結果詳細Paramsが取得できなかった場合、エラーをスローする。<BR>
     * <BR>
     * ３）Listに、１）で取得した余力計算結果Params<現物顧客>と、<BR>
     *  ２）で取得した、余力計算結果詳細Params<現物顧客>を、格納して返却する。<BR>
     * 　@Listの、0番目の要素：１）で取得した、余力計算結果<現物顧客>Params<BR>
     * 　@Listの、1番目の要素：２）で取得した、余力計算結果詳細<現物顧客>Params<BR>
     * <BR>
     * @@param l_lngCalcResultId - (余力計算結果ID)<BR>
     * @@return List
     */
    public static List findCalcResultEquityParamsSpecifiedCalcResultId(long l_lngCalcResultId)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerCalcEquity.findCalcResultEquityParamsSpecifiedCalcResultId(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //余力計算結果テーブル<現物顧客>を検索
            TpCalcResultEquityParams l_resultParams =
                (TpCalcResultEquityParams)TpCalcResultEquityDao.findRowByPk(l_lngCalcResultId);

            //余力計算結果詳細<現物顧客>Paramsを取得
            TpCalcResultEquityDetailParams l_resultDetailParams =
                (TpCalcResultEquityDetailParams)TpCalcResultEquityDetailDao.findRowByPk(
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
     * (calcオリックスクレジット基準出金可能額)<BR>
     * <BR>
     * オリックスクレジット専用当日使用可能現金を計算する。<BR>
     * <BR>
     * (*) 計算の詳細は、「基本設計計算式書（余力）.doc」参照。<BR>
     * <BR>
     * @@return double
     * @@throws WEB3SystemLayerException
     */
    private double calcOrixCreditPaymentPower() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "calcOrixCreditPaymentPower()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //余力計算結果Params.get口座ID()
        long l_lngAccountId = this.calcResultEquity.getAccountId();

        long l_lngSubAccountId = 0;

        try
        {
            //拡張アカウントマネージャ.get補助口座(顧客口座ID,1:株式取引口座(預り金)).getSubAccountId()
            l_lngSubAccountId = l_gentradeAccountManager.getSubAccount(
                l_lngAccountId,
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getSubAccountId();
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません。", l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //営業日（T+0）
        Date l_datBizDate = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //入出金注文単位テーブルより入金レコードを以下条件で検索し、 当日入金レコードを取得する。
        //[条件]
        //口座ID = 顧客口座ID
        //補助口座ID =  顧客補助口座ID
        //注文種別 in (入金注文,証拠金からの振替,FXからの振替,中国株式からの振替,その他から振替、
        //CFD振替注文（CFD口座から預り金))
        //注文状態 not in (新規注文発注失敗, 取消注文発注済)
        //発注日 >= 営業日（T+0）
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and sub_account_id = ? ");
        l_sbWhere.append(" and order_type in ( ?, ?, ?, ?, ?, ? ) ");
        l_sbWhere.append(" and order_status not in ( ?, ? ) ");
        l_sbWhere.append(" and to_date(biz_date, 'YYYYMMDD') >= ? ");

        Object[] l_bindVars = {
            new Long(l_lngAccountId),
            new Long(l_lngSubAccountId),
            OrderTypeEnum.CASH_IN,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE,
            OrderTypeEnum.TRANSFER_FROM_FEQ,
            OrderTypeEnum.FROM_OTHER_TRANSFER,
            OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD,
            OrderStatusEnum.NOT_ORDERED,
            OrderStatusEnum.CANCELLED,
            l_datBizDate};

        QueryProcessor l_qp;
        List l_lisInRow = null;
        try
        {
            l_qp = Processors.getDefaultProcessor();
            l_lisInRow = l_qp.doFindAllQuery(
                AioOrderUnitParams.TYPE,
                l_sbWhere.toString(),
                l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //入出金注文単位テーブルより出金レコードを以下条件で検索し、 当日出金レコードを取得する。
        //[条件]
        //口座ID = 顧客口座ID
        //補助口座ID =  顧客補助口座ID
        //注文種別 in (出金注文,証拠金への振替,FXへの振替,中国株式への振替,その他への振替,
        //大証金への振替,オリックスクレジットへの振替、CFD振替注文（預り金からCFD口座))
        //注文状態 not in (新規注文発注失敗, 取消注文発注済)
        //発注日 >= 営業日（T+0）
        StringBuffer l_sbWhere1 = new StringBuffer();
        l_sbWhere1.append(" account_id = ? ");
        l_sbWhere1.append(" and sub_account_id = ? ");
        l_sbWhere1.append(" and order_type in ( ?, ?, ?, ?, ?, ?, ?, ? ) ");
        l_sbWhere1.append(" and order_status not in ( ?, ? ) ");
        l_sbWhere1.append(" and to_date(biz_date, 'YYYYMMDD') >= ? ");

        Object[] l_bindVars1 = {
            new Long(l_lngAccountId),
            new Long(l_lngSubAccountId),
            OrderTypeEnum.CASH_OUT,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
            OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT,
            OrderTypeEnum.TRANSFER_TO_FEQ,
            OrderTypeEnum.TO_OTHER_TRANSFER,
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK,
            OrderTypeEnum.TO_ORIX_CREDIT,
            OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT,
            OrderStatusEnum.NOT_ORDERED,
            OrderStatusEnum.CANCELLED,
            l_datBizDate};

        List l_lisOutRow = null;
        try
        {
            l_qp = Processors.getDefaultProcessor();
            l_lisOutRow = l_qp.doFindAllQuery(
                AioOrderUnitParams.TYPE,
                l_sbWhere1.toString(),
                l_bindVars1);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        BigDecimal l_bdInQuantity = new BigDecimal("0");
        BigDecimal l_bdOutQuantity = new BigDecimal("0");
        BigDecimal l_bdTrading = new BigDecimal("0");
        BigDecimal l_bdTotal = new BigDecimal("0");

        if (!l_lisInRow.isEmpty())
        {
            //当日入金額合計
            int l_intInSize = l_lisInRow.size();
            for (int i = 0; i < l_intInSize; i++)
            {
                AioOrderUnitRow l_inAioOrderUnitRow = (AioOrderUnitRow)l_lisInRow.get(i);
                BigDecimal l_bdInQuantity1 = new BigDecimal("" + l_inAioOrderUnitRow.getQuantity());
                l_bdInQuantity = l_bdInQuantity.add(l_bdInQuantity1);
            }
        }

        if (!l_lisOutRow.isEmpty())
        {
            //当日出金額合計
            int l_intOutSize = l_lisOutRow.size();
            for (int i = 0; i < l_intOutSize; i++)
            {
                AioOrderUnitRow l_outAioOrderUnitRow = (AioOrderUnitRow)l_lisOutRow.get(i);
                BigDecimal l_bdOutQuantity1 = new BigDecimal("" + l_outAioOrderUnitRow.getQuantity());
                l_bdOutQuantity = l_bdOutQuantity.add(l_bdOutQuantity1);
            }
        }

        //・	オリックスクレジット担保ローン出金可能額	・・・余力計算条件.get担保ローン出金可能額
        String l_strTradingPower = this.calcCondition.getOrixSecuredLoanPaymentTradingPower();

        // オリックスクレジット専用当日使用可能現金
        // = オリックスクレジット担保ローン出金可能額 + 当日入金額合計 - 当日出金額合計 - 今回注文出金額
        if (l_strTradingPower != null)
        {
            l_bdTrading =  new BigDecimal(l_strTradingPower);
        }

        BigDecimal l_thisTimOrderCont =  new BigDecimal(String.valueOf(this.thisTimOrderCont));
        l_bdTotal = l_bdTrading.add(l_bdInQuantity).subtract(l_bdOutQuantity).subtract(l_thisTimOrderCont);

        log.exiting(STR_METHOD_NAME);
        return l_bdTotal.doubleValue();
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
