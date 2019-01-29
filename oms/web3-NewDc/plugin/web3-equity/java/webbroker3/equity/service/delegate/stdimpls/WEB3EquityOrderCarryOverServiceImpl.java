head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文繰越サービスImpl(WEB3EquityOrderCarryOverServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/02 中尾寿彦(SRA) 新規作成
Revesion History : 2008/01/23 トウ鋒鋼(中訊) 仕様変更　@モデルNo.1247、1290
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.ArrayListPage;
import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DateRangeQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.equity.WEB3EquityCarryOverCloseInterceptor;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.message.WEB3EquityOrderCarryOverRequest;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;

/**
 * （注文繰越サービスImpl）。<BR>
 * <BR>
 * 注文繰越サービス実装クラス
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverServiceImpl
    implements WEB3EquityOrderCarryOverService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderCarryOverServiceImpl.class);

    /**
     * @@roseuid 40B2A2870009
     */
    public WEB3EquityOrderCarryOverServiceImpl()
    {

    }

    /**
     * 注文繰越サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文繰越）注文繰越」参照。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CF8F011B
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        WEB3EquityOrderCarryOverRequest l_carryOverRequest;
        if (l_request instanceof WEB3EquityOrderCarryOverRequest)
        {
            l_carryOverRequest = (WEB3EquityOrderCarryOverRequest)l_request;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.debug("リクエストデータ.証券会社コード = " + l_carryOverRequest.institutionCode);
        
        // validate()
        l_carryOverRequest.validate();
        
        // オンライン実行結果.set処理中()
		WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			OnlineTransactionCallback l_onTransactionCallback =
				new OnlineTransactionCallback(
					l_carryOverRequest.institutionCode,
					l_carryOverRequest.rangeFrom,
					l_carryOverRequest.rangeTo);
			l_onlineRunStatus =
				(WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
				QueryProcessor.TX_CREATE_NEW,
				l_onTransactionCallback);
		}
		catch (DataCallbackException l_dce)
		{
			WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
			throw l_wbe;
		}
		catch (DataException l_de)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_de.getMessage(),
				l_de);
		}

        //getInstitution()
        Institution l_institution = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_carryOverRequest.institutionCode);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
                    
        //get処理対象顧客一覧()
        WEB3GentradeMainAccount[] l_mainAccounts =
            getMainAccounts(
                l_institution,
                l_carryOverRequest.rangeFrom,
                l_carryOverRequest.rangeTo);
        
        //取得した顧客オブジェクト数分、Loop。顧客毎にcommit／rollbackする。
        boolean isCarryOverAllAccountsSuccess = true;
        WEB3EquityOrderCarryOverService l_orderCarryOverService =
            (WEB3EquityOrderCarryOverService)Services.getService(WEB3EquityOrderCarryOverService.class);
        int l_mainAccountsLen = 0;
        if (l_mainAccounts != null)
        {
            l_mainAccountsLen = l_mainAccounts.length;
        }
        log.debug("l_mainAccountsLen = " + l_mainAccountsLen);
        for (int i = 0;i < l_mainAccountsLen;i++)
        {
            try
            {
                //顧客単位繰越実行()
                log.debug("顧客単位繰越実行");
                l_orderCarryOverService.execCarryOverForAccount(l_mainAccounts[i]);
            }
            catch (Exception l_exp)
            {
                log.error("顧客単位繰越エラー発生 :口座ID[" + l_mainAccounts[i].getAccountId() + "]");
                log.error(STR_METHOD_NAME, l_exp);
                isCarryOverAllAccountsSuccess = false;
            }
        }
        log.debug("isCarryOverAllAccountsSuccess = " + isCarryOverAllAccountsSuccess);
        
        //オンライン実行結果テーブルupdate
		String l_strStatus;
        if (isCarryOverAllAccountsSuccess == true)
        {
			l_strStatus = WEB3RunStatusDivDef.DEALED;
        }
        else
        {
			l_strStatus = WEB3RunStatusDivDef.ERROR;
        }
		l_onlineRunStatus.updateRunStatusDiv(l_strStatus);
        
        log.exiting(STR_METHOD_NAME);
        return l_carryOverRequest.createResponse();
    }

    /**
     * (顧客単位繰越実行)<BR>
     * 注文繰越処理を行う。<BR>
     * <BR>
     * 処理のシーケンスは、<BR>
     * シーケンス図「（注文繰越）顧客単位繰越実行」を参照。<BR>
     * @@param l_mainAccount - 顧客<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CF8F0248
     */
    public void execCarryOverForAccount(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execCarryOverForAccount(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        // 1.1. lock口座()
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_accountManager.lockAccount(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode());
        }
        catch (WEB3BaseException l_wbe)
        {
            if (l_wbe.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
            {
                log.error(
                    "口座ロックに失敗しました。： " +
                    "証券会社コード=[" + l_mainAccount.getInstitution().getInstitutionCode() + "], " +
                    "部店コード=[" + l_mainAccount.getBranch().getBranchCode() + "], " +
                    "顧客コード=[" + l_mainAccount.getAccountCode() + "]");
            }
            throw l_wbe;
        }
        
        //1.2. get有効注文単位()
        ListPage l_lstOrderUnits = getCarryOverOrderUnit(l_mainAccount);        
        int l_intSize = 0;
        if (l_lstOrderUnits != null)
        {
            l_intSize = l_lstOrderUnits.size();
        }
        log.debug("有効注文単位(顧客)数：[" + l_intSize + "]");
        
        EqTypeOrderManager l_orderMgr =
            (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
        //1.3. get有効注文単位( )で取得したオブジェクト数分、Loop。
        for (int i = 0;i < l_intSize;i++)
        {
            log.debug("get有効注文単位( )で取得したオブジェクト数分、Loop  Start");
            //1.3.1. 株式注文繰越失効インタセプタ()
            WEB3EquityCarryOverCloseInterceptor l_carrayOverCloseInterceptor =
                new WEB3EquityCarryOverCloseInterceptor(
                    WEB3ErrorReasonCodeDef.NORMAL);
            
            //1.3.2. setThreadLocalPersistenceEventInterceptor()
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(
                l_carrayOverCloseInterceptor);
            
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_lstOrderUnits.get(i);
            
            //1.3.3. expireOrder()
            ProcessingResult l_processingResult = l_orderMgr.expireOrder(l_orderUnit.getOrderId());
            if (l_processingResult.isFailedResult())
            {
                ErrorInfo l_errorInfo = l_processingResult.getErrorInfo();
                WEB3BaseException l_baseException =
                    new WEB3BaseException(
                        l_errorInfo,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_errorInfo.getErrorMessage());
                throw l_baseException;
            }
            log.debug("get有効注文単位( )で取得したオブジェクト数分、Loop  End");
        }
        
        //1.4. get有効注文単位( )で取得したオブジェクト数分、Loop。
        WEB3EquityOrderCarryOverUnitService l_orderCarrayOverUnitService =
            (WEB3EquityOrderCarryOverUnitService)Services.getService(
                WEB3EquityOrderCarryOverUnitService.class);
        Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        for (int i = 0;i < l_intSize;i++)
        {
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_lstOrderUnits.get(i);
            if (l_orderUnit.getExpirationTimestamp().compareTo(l_tsBizDate) <= 0)
            {
                continue;
            }
            //1.4.1. insert繰越注文()
            log.debug("insert繰越注文(注文単位) orderUnitId = " + l_orderUnit.getOrderUnitId());
            log.debug("insert繰越注文(注文単位)実行");
            l_orderCarrayOverUnitService.insertCarryOverOrder(l_orderUnit);
        }
        
        //1.5. getSubAccount()
		WEB3GentradeSubAccount l_subAccount;
        SubAccountTypeEnum l_subAccountType;
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
        {
			l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
        }
        else
        {
			l_subAccountType = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
        }
        
        try 
        {
			l_subAccount = 
				(WEB3GentradeSubAccount)l_accountManager.getSubAccount(
					l_mainAccount.getAccountId(), 
					l_subAccountType);
		} 
		catch (NotFoundException e) 
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				getClass().getName() + "." + STR_METHOD_NAME,
				e.getMessage(),
				e);
		}
		
		//1.6. 余力再計算()
		WEB3TPTradingPowerService l_tpTradingPowerService = 
			(WEB3TPTradingPowerService)Services.getService(
				WEB3TPTradingPowerService.class);
		l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        		
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get有効注文単位)<BR>
     * 引数で指定した顧客の、有効な注文単位オブジェクトを取得する。<BR>
     * <BR>
     * １）　@当該顧客の、株・信用の有効注文の注文単位オブジェクトを全て取得する。<BR>
     * <BR>
     * １－１）　@当該顧客の、"株式取引口座"の有効注文の注文単位オブジェクトを全て取得する。<BR>
     * 　@　@　@　@　@（拡張株式注文マネージャ.getOpenOrderUnits( )をコール）<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜getOpenOrderUnits( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@補助口座：　@引数の顧客.getSubAccount(SubAccountTypeEnum."株式取引口座"<BR>
     * 　@　@　@　@　@　@（EQUITY_SUB_ACCOUNT）)の戻り値オブジェクト　@<BR>
     * 　@　@　@銘柄タイプ：　@ProductTypeEnum."株式"（EQUITY）　@<BR>
     * 　@　@　@日付範囲：　@全て（ALL_DATE_RANGES）<BR>
     * 　@　@　@ページ範囲：　@全ページ（ALL_PAGES）<BR>
     * 　@　@　@ソートキー：　@注文単位.受注日時<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * １－２）　@当該顧客が信用客であるかどうかをチェックする。<BR>
     * <BR>
     * 　@　@　@引数の顧客.is信用口座開設("0：DEFAULT")をコールする。<BR>
     * <BR>
     * １－３）　@顧客.is信用口座開設("0：DEFAULT")==trueの場合のみ、<BR>
     * 　@　@　@　@　@当該顧客の、"信用取引口座"の有効注文の注文単位オブジェクトを全て取得する。<BR>
     * 　@　@　@　@　@（拡張株式注文マネージャ.getOpenOrderUnits( )をコール）<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜getOpenOrderUnits( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@補助口座：　@引数の顧客.getSubAccount(SubAccountTypeEnum."信用取引口座"<BR>
     * 　@　@　@　@　@　@（EQUITY_MARGIN_SUB_ACCOUNT）)の戻り値オブジェクト　@<BR>
     * 　@　@　@銘柄タイプ：　@ProductTypeEnum."株式"（EQUITY）　@<BR>
     * 　@　@　@日付範囲：　@全て（ALL_DATE_RANGES）<BR>
     * 　@　@　@ページ範囲：　@全ページ（ALL_PAGES）<BR>
     * 　@　@　@ソートキー：　@注文単位.受注日時<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * １－１）及び１－３）の取得結果ListPageを両方とも使用する。<BR>
     * <BR>
     * ２）　@繰越対象となる市場IDのリストを作成する。<BR>
     * <BR>
     * ２－１）（部店市場別）取扱条件.get（部店市場別）取扱条件()をコールして、 <BR>
     * 　@　@　@取扱条件オブジェクトの配列を取得する。<BR>
     * <BR>
     * 　@　@　@＜get（部店市場別）取扱条件()にセットする引数＞<BR>
     * 　@　@　@　@部店:　@引数の顧客.getBranch()<BR>
     * <BR>
     * ２－２）取得した取扱条件オブジェクトの全要素について以下の処理を行う。<BR>
     * <BR>
     * 　@　@　@（部店市場別）取扱条件.get市場コード()により市場コードを取得する。<BR>
     * 　@　@　@拡張金融オブジェクトマネージャ.get市場()をコールし、<BR>
     * 　@　@　@取得した市場オブジェクトの市場IDをリストに追加する。<BR>
     * <BR>
     * 　@　@　@＜get市場()にセットする引数＞ <BR>
     * 　@　@　@　@証券会社コード:　@引数の顧客.getInstitution().getInstitutionCode()<BR>
     * 　@　@　@　@市場コード:　@（部店市場別）取扱条件.get市場コード()<BR>
     * <BR>
     * ３）　@繰越対象とならない注文単位オブジェクトを除外する。<BR>
     * <BR>
     * ３－１）　@１）で取得した注文単位オブジェクトに対し、<BR>
     * 　@　@　@市場閉局後に登録した新規注文でないかどうかのチェックを行う。<BR>
     * 　@　@　@市場閉局後に登録した新規注文と判定した場合は、<BR>
     * 　@　@　@１）の取得結果から当該注文単位オブジェクトを削除する。<BR>
     * <BR>
     * 　@　@　@＜市場閉局後に登録した新規注文のチェック＞<BR>
     * 　@　@　@注文単位.発注日 > 業務日付(*1) の場合は、<BR>
     * 　@　@　@市場閉局後に登録した新規注文であると判定する。<BR>
     * 　@　@　@上記以外の場合は、市場閉局後に登録した新規注文でないと判定する。<BR>
     * <BR>
     * ３－２）　@１）で取得した注文単位オブジェクトに対し、<BR>
     * 　@　@　@ミニ株注文でないかどうかのチェックを行う。<BR>
     * 　@　@　@ミニ株注文と判定した場合は、繰越対象とはならないため、<BR>
     * 　@　@　@１）の取得結果から当該注文単位オブジェクトを削除する。<BR>
     * <BR>
     * 　@　@　@＜ミニ株注文のチェック＞<BR>
     * 　@　@　@注文単位.注文種別 == （"株式ミニ株買注文" or "株式ミニ株売注文"）の場合は、<BR>
     * 　@　@　@ミニ株注文であると判定する。<BR>
     * 　@　@　@上記以外の場合は、ミニ株注文でないと判定する。<BR>
     * <BR>
     * ３－３）　@１）で取得した注文単位オブジェクトに対し、<BR>
     * 　@　@　@繰越対象市場かどうかのチェックを行う。 <BR>
     * 　@　@　@注文単位.市場IDが　@２）で作成した繰越対象となる市場IDリストに含まれない場合は、<BR>
     * 　@　@　@１）の取得結果から当該注文単位オブジェクトを削除する。<BR>
     * <BR>
     * ４）　@作成したListPageを返す。該当データなしの場合はnullを返す。<BR>
     * <BR>
     * (*1)業務日付は、GtlUtils.getTradingSystem( ).getBizDate( ) で取得する。<BR>
     * <BR>
     * @@param l_mainAccount - 顧客<BR>
     * @@return ListPage
     * @@roseuid 4137CF8F02AC
     */
    public ListPage getCarryOverOrderUnit(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getCarryOverOrderUnit(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        List l_lisOrderUnit = new ArrayList();
        try
        {
            // １）　@当該顧客の、株・信用の有効注文の注文単位オブジェクトを全て取得する。
            // １－１）当該顧客の、"株式取引口座"の有効注文の注文単位オブジェクトを全て取得する。
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            DefaultSortKeySpec l_sortKeySpec = new DefaultSortKeySpec("received_date_time");
            ListPage l_lstEquityOrderUnit =
                l_orderMgr.getOpenOrderUnits(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    DateRangeQueryParamsSpec.ALL_DATE_RANGES,
                    PaginationQueryParamsSpec.ALL_PAGES,
                    l_sortKeySpec);
            log.debug("l_lstEquityOrderUnit.size() = " + l_lstEquityOrderUnit.size());
            l_lisOrderUnit.addAll(l_lstEquityOrderUnit);
            
            // １－２）　@当該顧客が信用客であるかどうかをチェックする。
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                // １－３）　@顧客.is信用口座開設("0：DEFAULT")==trueの場合の
                // 　@　@　@　@　@当該顧客の、"信用取引口座"の有効注文の注文単位オブジェクトを全て取得する。
                l_subAccount =
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                ListPage l_lstMarginOrderUnit =
                    l_orderMgr.getOpenOrderUnits(
                        l_subAccount,
                        ProductTypeEnum.EQUITY,
                        DateRangeQueryParamsSpec.ALL_DATE_RANGES,
                        PaginationQueryParamsSpec.ALL_PAGES,
                        l_sortKeySpec);
                log.debug("l_lstMarginOrderUnit.size() = " + l_lstMarginOrderUnit.size());
                // １－１）及び１－３）の取得結果ListPageを両方とも使用する。
                l_lisOrderUnit.addAll(l_lstMarginOrderUnit);
            }

            //２）　@繰越対象となる市場IDのリストを作成する。
            //２－１）（部店市場別）取扱条件.get（部店市場別）取扱条件()をコールして、
            //取扱条件オブジェクトの配列を取得する。
            WEB3GentradeBranchMarketDealtCond[] l_branchMarketDealtConds =
                WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(
                    (WEB3GentradeBranch)l_mainAccount.getBranch());

            //拡張金融オブジェクトマネージャを取得する。
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            int l_intLength = l_branchMarketDealtConds.length;
            String l_strMarketCode = null;
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            Market l_market = null;
            List l_lisMarketIds = new ArrayList();
            //２－２）取得した取扱条件オブジェクトの全要素について以下の処理を行う。
            for (int i = 0; i < l_intLength; i++)
            {
                //（部店市場別）取扱条件.get市場コード()により市場コードを取得する。
                l_strMarketCode = l_branchMarketDealtConds[i].getMarketCode();

                //拡張金融オブジェクトマネージャ.get市場()をコールし
                l_market = l_objectManager.getMarket(l_strInstitutionCode, l_strMarketCode);

                //取得した市場オブジェクトの市場IDをリストに追加する。
                l_lisMarketIds.add(new Long(l_market.getMarketId()));
            }

            // ３－１）　@１）で取得した注文単位オブジェクトに対し、市場閉局後に登録した新規注文でないかどうかの
            // 　@　@　@チェックを行う。
            // 　@　@　@市場閉局後に登録した新規注文と判定した場合は、繰越対象とはならないため、
            // 　@　@　@１）の取得結果から当該注文単位オブジェクトを削除する。
            log.debug("削除前:l_lisOrderUnit.size() = " + l_lisOrderUnit.size());
            Iterator l_orderUnitIterator = l_lisOrderUnit.listIterator();
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            String l_strBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBizDate);
            while (l_orderUnitIterator.hasNext())
            {
                EqTypeOrderUnit l_orderUnit =
                    (EqTypeOrderUnit)l_orderUnitIterator.next();
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                // 市場閉局後に登録した新規注文のチェック
                if (l_orderUnitRow.getBizDate().compareTo(l_strBizDate) > 0)
                {
                    l_orderUnitIterator.remove();
                }
                // ミニ株注文のチェック
                else if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderUnitRow.getOrderType()) ||
                          OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderUnitRow.getOrderType()))
                {
                    l_orderUnitIterator.remove();
                }
                //注文単位.市場IDが　@２）で作成した繰越対象となる市場IDリストに含まれない場合
                else if (!l_lisMarketIds.contains(new Long(l_orderUnitRow.getMarketId())))
                {
                    l_orderUnitIterator.remove();
                }
            }
            log.debug("削除後:l_lisOrderUnit.size() = " + l_lisOrderUnit.size());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
        // ４）　@作成したListPageを返す。該当データなしの場合はnullを返す
        if (l_lisOrderUnit.isEmpty())
        {
            return null;
        }
        else
        {
            return new ArrayListPage(
                l_lisOrderUnit,
                PaginationQueryParamsSpec.ALL_PAGES.getPageSize(),
                PaginationQueryParamsSpec.ALL_PAGES.getPageNumber());
        }
    }

    /**
     * (get処理対象顧客一覧)<BR>
     * <BR>
     * 有効注文を持つ顧客の一覧を作成し返す。<BR>
     * <BR>
     * １）　@市場ID一覧を作成する。  <BR>
     * <BR>
     * 　@　@１－１）（部店市場別）取扱条件.get（部店市場別）取扱条件( )をコールして、<BR>
     * 　@　@　@　@　@取扱条件オブジェクトの配列を取得する。  <BR>
     * <BR>
     * 　@　@　@　@　@＜get（部店市場別）取扱条件( )にセットする引数＞  <BR>
     * 　@　@　@　@　@　@証券会社コード:　@プロパティの証券会社コード  <BR>
     * <BR>
     * 　@　@１－２）取得した取扱条件オブジェクトの全要素について以下の処理を行う。<BR>
     * <BR>
     * 　@　@　@　@　@（部店市場別）取扱条件.get市場コード( )により市場コードを取得する。<BR>
     * 　@　@　@　@　@拡張金融オブジェクトマネージャ.get市場( )をコールし、  <BR>
     * 　@　@　@　@　@取得した市場オブジェクトの市場IDをリストに追加する。  <BR>
     * 　@　@　@　@　@※ただし、既に市場IDがリストに存在する場合は追加しない。 <BR>
     * <BR>
     * 　@　@　@　@　@[get市場( )の引数設定] <BR>
     * 　@　@　@　@　@　@証券会社コード:　@プロパティの証券会社コード   <BR>
     * 　@　@　@　@　@　@市場コード:　@（部店市場別）取扱条件.get市場コード( )<BR>
     * <BR>
     * ２）　@株・信用の有効注文の注文単位オブジェクトを全て取得する。<BR>
     * <BR>
     * 　@　@　@クエリプロセッサを使用し、以下の条件に合致する注文単位オブジェクトを全て取得する。<BR>
     * 　@　@　@（口座ID昇順指定）<BR>
     * 　@　@　@※当日発注分の、有効な現物・信用注文を取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜抽出条件＞<BR>
     * <BR>
     * 　@　@　@　@　@　@部店ID in 引数の証券会社.getBranches( )の戻り値のいずれか<BR>
     * 　@　@　@かつ　@銘柄タイプ == "株式"<BR>
     * 　@　@　@かつ　@注文種別 != （"株式ミニ株買注文"、"株式ミニ株売注文"）<BR>
     * 　@　@　@かつ　@注文有効状態 == "オープン"<BR>
     * 　@　@　@かつ　@引数のFrom口座ID <= 口座ID<BR>
     * 　@　@　@かつ　@口座ID <= 引数のTo口座ID<BR>
     * 　@　@　@かつ　@発注日 == 業務日付(*1)<BR>
     * 　@　@　@かつ　@市場ID in １）で取得した市場ID一覧<BR>
     * <BR>
     * 　@　@　@(*1)業務日付は、GtlUtils.getTradingSystem( ).getBizDate( ) で取得する。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ３）　@顧客オブジェクトの一覧を作成する。<BR>
     * <BR>
     * 　@　@　@まず、２）で取得した注文単位オブジェクト.口座IDの一意な一覧を作成する。<BR>
     * 　@　@　@各口座ID毎に、該当する顧客オブジェクトを取得し、<BR>
     * 　@　@　@返却用の顧客オブジェクトの一覧に追加していく。<BR>
     * <BR>
     * ４）　@作成した顧客オブジェクトの一覧を返却する。<BR>
     * 　@　@　@※該当データなし時はnullを返却する。<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト。
     * @@param l_lngRangeFrom - (From口座ID)<BR>
     * From口座ID。
     * @@param l_lngRangeTo - (To口座ID)<BR>
     * To口座ID。
     * @@return WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException
     */
    public WEB3GentradeMainAccount[] getMainAccounts(
        Institution l_institution,
        long l_lngRangeFrom,
        long l_lngRangeTo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccounts(Institution, long, long)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //取扱条件オブジェクトの配列を取得する。
        WEB3GentradeBranchMarketDealtCond[] l_branchMarketDealtConds =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(
                l_institution.getInstitutionCode());

        //拡張金融オブジェクトマネージャを取得する。
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        int l_intLength = l_branchMarketDealtConds.length;
        String l_strMarketCode = null;
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        Market l_market = null;
        List l_lisMarketIds = new ArrayList();
        //取得した取扱条件オブジェクトの全要素について以下の処理を行う。
        for (int i = 0; i < l_intLength; i++)
        {
            //（部店市場別）取扱条件.get市場コード()により市場コードを取得する。
            l_strMarketCode = l_branchMarketDealtConds[i].getMarketCode();

            //拡張金融オブジェクトマネージャ.get市場()をコールし
            try
            {
                l_market = l_objectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
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

            //取得した市場オブジェクトの市場IDをリストに追加する。
            //※ただし、既に市場IDがリストに存在する場合は追加しない。
            if (!l_lisMarketIds.contains(new Long(l_market.getMarketId())))
            {
                l_lisMarketIds.add(new Long(l_market.getMarketId()));
            }
        }

        WEB3GentradeMainAccount[] l_accounts = null;
        
        StringBuffer l_sbWhere = new StringBuffer();
        Branch[] l_branchs = l_institution.getBranches();
        if (l_branchs != null && l_branchs.length > 0)
        {
            l_sbWhere.append("branch_id in (" + l_branchs[0].getBranchId());
            for (int i = 1;i < l_branchs.length;i++)
            {
                l_sbWhere.append("," + l_branchs[i].getBranchId());
            }
            l_sbWhere.append(") and ");
        }
        l_sbWhere.append("product_type = ?");
        l_sbWhere.append(" and order_type not in (?,?)");
        l_sbWhere.append(" and order_open_status = ?");
        l_sbWhere.append(" and ? <= account_id");
        l_sbWhere.append(" and account_id <= ?");
        l_sbWhere.append(" and biz_date = ?");
        if (!l_lisMarketIds.isEmpty())
        {
            l_sbWhere.append(" and market_id in (");
            l_sbWhere.append(((Long)l_lisMarketIds.get(0)).longValue());
            for (int i = 1; i < l_lisMarketIds.size(); i++)
            {
                l_sbWhere.append("," + ((Long)l_lisMarketIds.get(i)).longValue());
            }
            l_sbWhere.append(")");
        }
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        String l_strBizDate =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBizDate);
        Object[] l_objWhere =
        {
            ProductTypeEnum.EQUITY,
            OrderTypeEnum.MINI_STOCK_BUY,
            OrderTypeEnum.MINI_STOCK_SELL,
            OrderOpenStatusEnum.OPEN,
            new Long(l_lngRangeFrom),
            new Long(l_lngRangeTo),
            l_strBizDate
        };
        
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisRecord =
                l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    "account_id asc",
                    null,
                    l_objWhere);
            if (l_lisRecord != null && !l_lisRecord.isEmpty())
            {
                long l_accountId = 0L;
                int l_intSize = l_lisRecord.size();
                List l_lisAccount = new ArrayList();
                for (int i = 0;i < l_intSize;i++)
                {
                    EqtypeOrderUnitRow l_orderUnitRow =
                        (EqtypeOrderUnitRow)l_lisRecord.get(i);
                    if (l_accountId != l_orderUnitRow.getAccountId())
                    {
                        l_accountId = l_orderUnitRow.getAccountId();
                        WEB3GentradeMainAccount l_account =
                            new WEB3GentradeMainAccount(l_accountId);
                        l_lisAccount.add(l_account);
                    }
                }
                l_accounts = new WEB3GentradeMainAccount[l_lisAccount.size()];
                l_lisAccount.toArray(l_accounts);
            }
        }
        catch (DataException l_dex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_accounts;
    }
    
    /**
     * (オンライン実行結果TransactionCallback)<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス。<BR>
     * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
     */
    private class OnlineTransactionCallback
        implements TransactionCallback
    {
		/**
		 * (証券会社コード)。<BR>
		 * 繰越対象の証券会社コード。<BR>
		 */
		private String institutionCode;
    
		/**
		 * (From口座ID)。<BR>
		 * From口座ID。<BR>
		 */
		private long rangeFrom;
    
		/**
		 * (To口座ID)。<BR>
		 * To口座ID。<BR>
		 */
		private long rangeTo;
		
		/**
		 * (オンライン実行結果TransactionCallback)。<BR>
		 * コンストラクタ。<BR>
		 * @@param l_strInstitutionCode - (証券会社コード)<BR>
		 * 証券会社コード。<BR>
		 * @@param l_lngRangeFrom - (From口座ID)<BR>
		 * From口座ID。<BR>
		 * @@param l_lngRangeTo - (To口座ID)<BR>
		 * To口座ID。<BR>
		 */
		public OnlineTransactionCallback(
			String l_strInstitutionCode,
			long l_lngRangeFrom,
			long l_lngRangeTo)
		{
			institutionCode = l_strInstitutionCode;
			rangeFrom = l_lngRangeFrom;
			rangeTo = l_lngRangeTo;
		}
        
        /**
         * (process)<BR>
         * <BR>
         * トランザクション処理を実施する。<BR>
         * <BR>
         * オンライン実行結果テーブルへ"処理中"設定を行う。<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         */
		public Object process()
			throws DataNetworkException, DataQueryException, DataCallbackException
		{
			final String STR_METHOD_NAME = "OnlineTransactionCallback.process()";
			log.entering(STR_METHOD_NAME);

			WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
			try
			{
				l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
					this.institutionCode, ProductTypeEnum.EQUITY,
					WEB3FuturesOptionDivDef.DEFAULT, WEB3OnlineServiceDiv.ORDER_CARRY_OVER,
					this.rangeFrom, this.rangeTo);
			}
			catch (WEB3BaseException l_be)
			{
				log.exiting(STR_METHOD_NAME);
				throw new DataCallbackException(l_be.getMessage(), l_be);
			}
			log.exiting(STR_METHOD_NAME);
			return l_onlineRunStatus;
		}
    }
}
@
