head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込サービスImpl(WEB3AioCashoutServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 黄建 (中訊) 新規作成
Revesion History : 2004/10/25 周勇(中訊) レビュー
Revesion History : 2004/12/09 周勇 (中訊) 残対応
Revesion History : 2007/03/16 何文敏 (中訊) モデルNo.716
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import webbroker3.aio.WEB3AioCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashoutCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3BankCodeDef;
import webbroker3.common.define.WEB3FinTransferDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTransferedFinInstitution;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金申込サービスImpl)<BR>
 * 出金申込サービス実装クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashoutService 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutServiceImpl.class);  
    
    /**
     * 出金申込サービス処理を実施する。 <BR>
     * <BR>
     * リクエストデータの型によりvalidate注文()、<BR>
     * またはsubmit注文()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBEF0F0148
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AioCashoutConfirmRequest)
        {
            //validate注文()メソッド
            l_response =
                this.validateOrder(
                    (WEB3AioCashoutConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AioCashoutCompleteRequest)
        {
            //submit注文()メソッド
            l_response =
                this.submitOrder(
                    (WEB3AioCashoutCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "リクエストデータが"
                    + " WEB3AioCashoutConfirmRequest "
                    + " と WEB3AioCashoutCompleteRequest以外である, but is " + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 出金申込の発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（出金申込）validate注文」参照。 <BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / 出金申込 」(出金申込）validate注文 )<BR>
     * 　@　@:  1.2.get発注日<BR>   
     *     発注日 > リクエストデータ.振込予定日 の場合、例外をスローする。<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00773<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「 (入出金サービスモデル) / 出金申込」 (出金申込）validate注文)<BR>
     * 　@　@:  1.5.get振込先登録区分( )<BR>   
     *     戻り値 = 0（未登録）の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00774<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioCashoutConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBEF5D0010
     */
    protected WEB3AioCashoutConfirmResponse validateOrder(
        WEB3AioCashoutConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3AioCashoutConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1）validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2）get発注日( )
        //発注日を取得する。
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate(); 

        //1.3）get補助口座(SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);            
        
		//==================FinApp==============================
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
		//入出金注文マネージャクラスを取得する。
		WEB3AioOrderManager l_aioOrderManager = 
			(WEB3AioOrderManager) l_finApp.getTradingModule(
				ProductTypeEnum.AIO).getOrderManager();

		//1.4）get直近振込日(SubAccount, Date)
		//[引数]
		//補助口座： get補助口座()の戻り値
		//発注日： get発注日()の戻り値
		//直近振込日 > リクエストデータ.振込予定日 の場合、例外をスローする。
		Date l_datClosestTransfer = 
			l_aioOrderManager.getClosestTransferDate(
				l_subAccount, l_datOrderBizDate);
		log.debug("直近の振込日を取得する = " + l_datClosestTransfer);        

		if (WEB3DateUtility.compareToDay(l_datClosestTransfer, l_request.transScheduledDate) > 0)
		{
			log.debug("直近振込日 > リクエストデータ.振込予定日エラー。");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00773,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"直近振込日[" + l_datClosestTransfer + "] > リクエストデータ.振込予定日[" 
				+ l_request.transScheduledDate + "]");        
		}            

        //1.5）getMainAccount( )
        //顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_gentradeMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();          
        
        //is円貨振込先（銀行口座）登録( )
        //当該顧客が、振込先（銀行口座）に円貨登録しているかどうかを判定する。
        //戻り値 = false(未登録)の場合､例外をスローする。
        boolean l_blnIsJapaneseCurrencyBankAccountRegi =
            l_gentradeMainAccount.isJapaneseCurrencyBankAccountRegi();

        if (!l_blnIsJapaneseCurrencyBankAccountRegi)
        {
            log.debug("出金口座未開設エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00774,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "振込先登録区分= 0（未登録）");            
        }           
        
        //1.7）代理入力者オブジェクトを取得する。
        //=================WEB3SystemLayerException=======================
        Trader l_trader =  this.getTrader();
       
        //1.8）入出金用の商品IDを取得する。 
        //[引数] 
        // 証券会社： 補助口座.getInstitution()の戻り値 
        
        //入出金用の商品IDを取得する
        long l_lngProductId = 
            l_aioOrderManager.getProductId(l_subAccount.getInstitution());
        log.debug("入出金用の商品IDを取得する:" + l_lngProductId);        
        
        //1.9）入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
        //long, double, String, Date, String, long)(入出金注文内
        //出金注文内容インスタンスを生成する。 
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //注文種別： 1001（出金注文） 
        //振替タイプ： 2（出金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.出金金額 
        //記述： null 
        //振替予定日： リクエストデータ.振替予定日 
        //決済機@関ID： null 
        //注文ID： null 
        
        //入出金注文内容インスタンスを生成する
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader, 
                OrderTypeEnum.CASH_OUT,
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.cashoutAmt), 
                null,
                l_request.transScheduledDate,
                null,
                null);   
                        
        //1.10）validate新規注文(SubAccount, ProductTypeEnum, 入出金注文内容)
        //注文内容のチェックを行う。 
        //- 同一受渡日の注文チェック 
        //- 出金金額範囲チェック 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //商品タイプ： 5（現金） 
        //入出金注文内容： 入出金注文内容オブジェクト 
        NewOrderValidationResult l_newOrderValidationResult =
            l_aioOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec);
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("注文内容のチェックを行う Error " +
                l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        //新規注文注文ＩＤ        
        long l_lngNewOrderId = l_newOrderValidationResult.getNewOrderId(); 
        log.debug("新規注文注文ＩＤ" + l_lngNewOrderId); 
            
        //1.11 入出金注文更新インタセプタのインスタンスを生成する。 
        //[引数] 
        //入出金注文内容： 入出金注文内容 
        WEB3AioCashTransOrderUpdateInterceptor l_orderUpdateInterceptor =
            new WEB3AioCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        //1.12 (*1)プロパティセット 
        Date l_datOrderBizTomDate = new WEB3GentradeBizDate(
            new Timestamp(l_datOrderBizDate.getTime())).roll(1);
        
        if (WEB3DateUtility.compareToDay(
                l_datOrderBizDate, l_aioNewOrderSpec.getEstimatedTransferDate()) == 0 
            || WEB3DateUtility.compareToDay(
                l_datOrderBizTomDate, l_aioNewOrderSpec.getEstimatedTransferDate()) == 0 )
        {
            l_orderUpdateInterceptor.setBizDate(l_datOrderBizDate);
        }
        else
        {
            Date l_datTransferTomDate = new WEB3GentradeBizDate(
                new Timestamp(l_aioNewOrderSpec.getEstimatedTransferDate().getTime())).roll(1);
            l_orderUpdateInterceptor.setBizDate(l_datTransferTomDate);
        }
        l_orderUpdateInterceptor.setDeliveryDate(l_aioNewOrderSpec.getEstimatedTransferDate());
        
        WEB3AioCashTransOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_orderUpdateInterceptor};
    
        //注文内容の配列
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioNewOrderSpec}; 
        
        //1.13 余力のチェックを行う。 
        //[引数] 
        //補助口座：　@補助口座オブジェクト 
        //注文内容インタセプタ： 入出金注文更新インタセプタの配列 
        //注文内容： 入出金注文内容の配列 
        //注文種別： 1001（出金注文） 
        //余力更新フラグ： false 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tPTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                OrderTypeEnum.CASH_OUT, 
                false);

        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす
        if (l_powerResult.isResultFlg() == false)
        {
            log.debug("取引余力チェックエラー。"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー。");
        }
        
        //1.14）get振込先金融機@関( )
        //顧客の振込先金融機@関オブジェクトを取得する。 
        WEB3GentradeTransferedFinInstitution 
            l_gentradeTransferedFinInstitution =
                l_gentradeMainAccount.getTransferedFinInstitution();
        
        //1.15）get銀行コード( )
        //銀行コードを取得する。
        String l_strFinInstitutionCode = 
            l_gentradeTransferedFinInstitution.getFinInstitutionCode();
            
        //1.16）get銀行名( )
        //銀行名を取得する。 
        String l_strFinInstitutionName =
            l_gentradeTransferedFinInstitution.getFinInstitutionName();
        
        //1.17) get銀行名() == nullの場合
		if ( l_strFinInstitutionName == null)
		{
			TransferedFinInstitutionRow l_transferedFinInstitutionRow = null;
			try
			{
				//振込先金融機@関テーブルより以下の条件で検索する
				//[取得条件] 
				// 証券会社コード
				// 部店コード
				// 顧客コード
				// 指定区分
				l_transferedFinInstitutionRow = TransferedFinInstitutionDao.findRowByPk(
					l_subAccount.getInstitution().getInstitutionCode(),
					l_gentradeMainAccount.getBranch().getBranchCode(),
					l_gentradeMainAccount.getAccountCode(),
					"A" );
			}
			catch (DataFindException l_ex)
			{
				//例外をスロー
				log.debug("該当する振込先金融機@関がありません。");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			catch (DataException l_ex)
			{
				//例外をスロー
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			
			if (WEB3FinTransferDivDef.BANK_TRANSFER.equals(l_transferedFinInstitutionRow.getTransferDiv()))
			{
                //振込先金融機@関.振替区分="1(銀行）"の場合、例外をスローする。
				log.debug("振替区分=1(銀行)の場合、銀行名に値がありません。");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);   
				
			}
		}
		        
        //==========remain zhou-yong NO.1 begin ===========
        
        //1.18) get支店コード( )(振込先金融機@関::get支店コード)
        //アイテムの定義
        //支店コードを取得する。 
        String l_strFinBranchCode =
            l_gentradeTransferedFinInstitution.getFinBranchCode();   

        //==========remain zhou-yong NO.1 end ===========
        
        //1.19）get支店名( )
        //支店名を取得する。 
        String l_strFinBranchName =
            l_gentradeTransferedFinInstitution.getFinBranchName();        
 
        //1.20） get預金区分( )
        //預金区分を取得する。   
        String l_strFinSaveDiv =
            l_gentradeTransferedFinInstitution.getFinSaveDiv();     
 
        //1.21）get口座番号( )
        //口座番号を取得する。  
        String l_strFinAccountNo =
            l_gentradeTransferedFinInstitution.getFinAccountNo();
        
        //1.22） createResponse( ) レスポンスデータを生成する。 
        WEB3AioCashoutConfirmResponse l_aioCashoutConfirmResponse = 
            (WEB3AioCashoutConfirmResponse) l_request.createResponse();            
        
        //1.23）(*) 以下のとおりに、プロパティをセットする。

        //レスポンス.振込先登録区分 = "1"（登録済）
        l_aioCashoutConfirmResponse.transRegistDiv = WEB3BankAccountRegiDef.ALREADY_REGISTER;
        
        //レスポンス.銀行コード = 振込先金融機@関.get銀行コード()の戻り値
        l_aioCashoutConfirmResponse.bankCode = l_strFinInstitutionCode;
        
        //レスポンス.銀行名 = 振込先金融機@関.get銀行名()の戻り値
        l_aioCashoutConfirmResponse.bankName = l_strFinInstitutionName;   
        
        //レスポンス.支店名 = 振込先金融機@関.get支店名()の戻り値
        l_aioCashoutConfirmResponse.branchBankName = l_strFinBranchName;
        
        //レスポンス.預金区分 = 振込先金融機@関.get預金区分()の戻り値
        l_aioCashoutConfirmResponse.depositDiv = l_strFinSaveDiv;
        
        //==========remain zhou-yong NO.2 begin ===========
        
        //レスポンス.口座番号 = （以下のとおり）
        //１）・銀行コード="9900"（郵貯）の場合、振込先金融機@関.get支店コード()の戻り値 + "-" + get口座番号( )の戻り値  をセット
        //    ・銀行コード!="9900"（郵貯）の場合、get口座番号( )の戻り値  をセット
		if(WEB3BankCodeDef.POSTAL_SAVINGS.equals(l_strFinInstitutionCode))
		{
			l_aioCashoutConfirmResponse.accountID = l_strFinBranchCode + "-" + l_strFinAccountNo;
		}
		else 
		{
			l_aioCashoutConfirmResponse.accountID = l_strFinAccountNo;
		}
        
        //==========remain zhou-yong NO.2 end ===========
        
        //レスポンス.出金金額 = リクエストデータ.出金金額
        l_aioCashoutConfirmResponse.cashoutAmt = l_request.cashoutAmt;
        
        //レスポンス.振込予定日 = リクエストデータ.振込予定日
        l_aioCashoutConfirmResponse.transScheduledDate = 
            l_request.transScheduledDate;
        
        //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_aioCashoutConfirmResponse.checkDate = l_datOrderBizDate;
        
        //レスポンス.注文ID = （validate新規注文の戻り値）.getNewOrderId()の戻り値 
        l_aioCashoutConfirmResponse.checkOrderID = l_lngNewOrderId;
        
        
        log.exiting(STR_METHOD_NAME); 
        return l_aioCashoutConfirmResponse;
    }
    
    /**
     * (submit注文)<BR>
     * 出金申込の登録を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（出金申込）submit注文」参照。 <BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(入出金サービスモデル) / 出金申込 」<BR>
     * （出金申込）submit注文 )<BR>
     * 　@　@:  1.5.get振込先登録区分( )<BR>   
     *     戻り値 = 0（未登録）の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00774<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioCashoutCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBEF6B0177
     */
    protected WEB3AioCashoutCompleteResponse submitOrder(
        WEB3AioCashoutCompleteRequest l_request)  throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3AioCashoutCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1）validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2）get発注日(Date)
        //発注日を取得する。 
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate(); 
        log.debug("発注日の取得 " + l_datOrderBizDate);    
        
        //1.3）get補助口座(SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);       

		//==================FinApp==============================
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
		//入出金注文マネージャクラスを取得する。
		WEB3AioOrderManager l_aioOrderManager = 
			(WEB3AioOrderManager) l_finApp.getTradingModule(
				ProductTypeEnum.AIO).getOrderManager();

		//1.4）get直近振込日(SubAccount, Date)
		//[引数]
		//補助口座： get補助口座()の戻り値
		//発注日： get発注日()の戻り値
		//直近振込日 > リクエストデータ.振込予定日 の場合、例外をスローする。
		Date l_datClosestTransfer = 
			l_aioOrderManager.getClosestTransferDate(
				l_subAccount, l_datOrderBizDate);
		log.debug("直近の振込日を取得する = " + l_datClosestTransfer);        

		if (WEB3DateUtility.compareToDay(l_datClosestTransfer, l_request.transScheduledDate) > 0)
		{
			log.debug("直近振込日 > リクエストデータ.振込予定日エラー。");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00773,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"直近振込日[" + l_datClosestTransfer + "] > リクエストデータ.振込予定日[" 
				+ l_request.transScheduledDate + "]");        
		}            

        //1.5）getMainAccount( )
        //顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_gentradeMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();  

        //is円貨振込先（銀行口座）登録( )
        //当該顧客が、振込先（銀行口座）に円貨登録しているかどうかを判定する。
        boolean l_blnIsJapaneseCurrencyBankAccountRegi =
            l_gentradeMainAccount.isJapaneseCurrencyBankAccountRegi();
       
        if (!l_blnIsJapaneseCurrencyBankAccountRegi)
        {
            log.debug("出金口座未開設エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00774,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "振込先登録区分= 0（未登録）");            
        }           

        //1.7）代理入力者オブジェクトを取得する。
        //=================WEB3SystemLayerException=======================
        Trader l_trader =  this.getTrader();
       
        //1.8）入出金用の商品IDを取得する。 
        //[引数] 
        // 証券会社： 補助口座.getInstitution()の戻り値 
        long l_lngProductId = 
            l_aioOrderManager.getProductId(l_subAccount.getInstitution());
        log.debug("入出金用の商品IDを取得する" + l_lngProductId);
        
        //1.9）入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
        //long, double, String, Date, String, long)(入出金注文内容::入出金注文内容)
        //出金注文内容インスタンスを生成する。 
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //注文種別： 1001（出金注文） 
        //振替タイプ： 2（出金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.出金金額 
        //記述： null 
        //振替予定日： リクエストデータ.振替予定日 
        //決済機@関ID： null 
        //注文ID： リクエストデータ.確認時注文ID 
        //入出金注文内容インスタンスを生成する
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader, 
                OrderTypeEnum.CASH_OUT,
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.cashoutAmt), 
                null,
                l_request.transScheduledDate,
                null,
                new Long(l_request.checkOrderID));
        
        //1.10）validate新規注文(SubAccount, ProductTypeEnum, 入出金注文内容)
        //注文内容のチェックを行う。 
        //- 同一受渡日の注文チェック 
        //- 出金金額範囲チェック 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //商品タイプ： 5（現金） 
        //入出金注文内容： 入出金注文内容オブジェクト 
        NewOrderValidationResult l_newOrderValidationResult =
            l_aioOrderManager.validateNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec);
                
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("注文内容のチェックを行う Error " + 
                l_newOrderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        //新規注文注文ＩＤ        
        long l_lngNewOrderId = l_newOrderValidationResult.getNewOrderId(); 
        log.debug("新規注文注文ＩＤ" + l_lngNewOrderId);      

        //1.11）get新規識別コード
        // 新規の識別コードを取得する。
        WEB3HostReqOrderNumberManageService l_numberService =
            (WEB3HostReqOrderNumberManageService) Services.getService(
            WEB3HostReqOrderNumberManageService.class);
        //[引数]
        //証券会社コード： 補助口座から取得した証券会社コード
        //部店コード： 補助口座から取得した部店コード
        //銘柄タイプ： 5（現金）

      String l_strOrderRequestNumber = 
          l_numberService.getNewNumber(
              l_subAccount.getInstitution().getInstitutionCode(),
              l_subAccount.getMainAccount().getBranch().getBranchCode(),
              ProductTypeEnum.CASH);
          
      log.debug("注文識別番号:" + l_strOrderRequestNumber);

        //1.12）メッセージ 入出金注文更新インタセプタ(入出金注文内容)
        //入出金注文更新インタセプタのインスタンスを生成する。 
        WEB3AioCashTransOrderUpdateInterceptor 
            l_aioCashTransOrderUpdateInterceptor = 
                new WEB3AioCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        //1.13）(*) 以下のとおりに、インタセプタのプロパティをセットする。
        //インタセプタ.発注日 = （以下のとおり）
        //get発注日()の戻り値 = 入出金注文内容.振替予定日 or
        //get発注日()の戻り値の翌営業日 = 入出金注文内容.振替予定日 の場合、get発注日()の戻り値
        Date l_datEstimatedTransferDate = 
            l_aioNewOrderSpec.getEstimatedTransferDate();  //振替予定日
        
        Date l_datNextOrderBizDate = 
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime())).roll(1); // get発注日()の戻り値の翌営業日
        
        if ((WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datEstimatedTransferDate) == 0)
            ||(WEB3DateUtility.compareToDay(l_datNextOrderBizDate, l_datEstimatedTransferDate) == 0))
        {
            l_aioCashTransOrderUpdateInterceptor.setBizDate(l_datOrderBizDate);
        }
        //それ以外の場合、入出金注文内容.振替予定日の前営業日
        else
        {
            Date l_datretBizDate = 
                new WEB3GentradeBizDate(
                    new Timestamp(
                        l_datEstimatedTransferDate.getTime())).roll(-1);
            
            l_aioCashTransOrderUpdateInterceptor.setBizDate(l_datretBizDate);
        }
        
        //インタセプタ.受渡日 = 入出金注文内容.振替予定日
        l_aioCashTransOrderUpdateInterceptor.setDeliveryDate(
            l_datEstimatedTransferDate);
        
        //インタセプタ.識別コード = get新規識別コード()の戻り値
        l_aioCashTransOrderUpdateInterceptor.setOrderRequestNumber(l_strOrderRequestNumber);
        
        //===========remain zhou-yong NO.3 begin========
        //1.14) validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],
        //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
        //アイテムの定義
        //余力の更新をする。
        //[引数] 
        //補助口座：　@補助口座オブジェクト 
        //注文内容インタセプタ： 入出金注文更新インタセプタの配列 
        //注文内容： 入出金注文内容の配列 
        //注文種別： 1001（出金注文） 
        //余力更新フラグ： true
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

        Object[] l_objCashoutCancelUpdate= {l_aioCashTransOrderUpdateInterceptor};
        Object[] l_objNewOrderSpec= {l_aioNewOrderSpec};
        
        WEB3TPTradingPowerResult l_tPTradingPowerResult = 
            l_service.validateTradingPower(l_gentradeSubAccount, l_objCashoutCancelUpdate, 
                l_objNewOrderSpec, OrderTypeEnum.CASH_OUT, true);
        
        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。
        if(!l_tPTradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力結果.判定フラグ == false");
            
        }
        
        //===========remain zhou-yong NO.3 end========
        
        //1.15）setThreadLocalPersistenceEventInterceptor(
        //入出金注文更新インタセプタ : AioOrderManagerPersistenceEventInterceptor)
        //インタセプタをセットする。 
        //[引数] 
        //入出金注文更新インタセプタ：　@生成した入出金注文更新インタセプタ 
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashTransOrderUpdateInterceptor);
         
        //1.16）注文登録処理を行う。 
        //[引数] 
        //補助口座：　@補助口座オブジェクト 
        //商品タイプ：　@5（現金） 
        //入出金注文内容：　@入出金注文内容オブジェクト 
        //注文ＩＤ：　@（validate新規注文の戻り値）.getNewOrderId()の戻り値 
        //パスワード：　@リクエストデータ.暗証番号 
        //isSkip発注審査：　@true 
        OrderSubmissionResult l_submitNewOrderResult =
            l_aioOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec,
                l_lngNewOrderId,
                l_request.password,
                true);
                
        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("注文登録処理を行う Error" +  
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }        
        
        //1.17）getOrderUnits(注文ID : long)
        //注文単位オブジェクトを取得する。 
        //※リストの1番目の要素のものを取得する。 
        //[引数] 
        //注文ＩＤ：　@（validate新規注文の戻り値）.getNewOrderId()の戻り値 
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(l_lngNewOrderId);
        if (l_orderUnits.length <= 0)
        {
          log.debug("Error in 注文単位オブジェクトを取得する");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnits[0];
        AioOrderUnitRow l_aioOrderUnitRow = 
            (AioOrderUnitRow) l_aioOrderUnit.getDataSourceObject();
                
        //1.18）get振込先金融機@関( )
        //顧客の振込先金融機@関オブジェクトを取得する。 
        WEB3GentradeTransferedFinInstitution 
            l_gentradeTransferedFinInstitution =
                l_gentradeMainAccount.getTransferedFinInstitution();      
                   
        //1.19）get銀行コード( )
        //銀行コードを取得する。 
        String l_strFinInstitutionCode = 
            l_gentradeTransferedFinInstitution.getFinInstitutionCode();        
         
        //1.20）get銀行名( )
        //銀行名を取得する。 
        String l_strFinInstitutionName =
            l_gentradeTransferedFinInstitution.getFinInstitutionName();
            
        //1.21) get銀行名() == nullの場合
		if ( l_strFinInstitutionName == null)
		{
			TransferedFinInstitutionRow l_transferedFinInstitutionRow = null;
			try
			{
				//振込先金融機@関テーブルより以下の条件で検索する
				//[取得条件] 
				// 証券会社コード
				// 部店コード
				// 顧客コード
				// 指定区分
				l_transferedFinInstitutionRow = TransferedFinInstitutionDao.findRowByPk(
					l_subAccount.getInstitution().getInstitutionCode(),
					l_gentradeMainAccount.getBranch().getBranchCode(),
					l_gentradeMainAccount.getAccountCode(),
					"A" );
			}
			catch (DataFindException l_ex)
			{
				//例外をスロー
				log.debug("該当する振込先金融機@関がありません。");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			catch (DataException l_ex)
			{
				//例外をスロー
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			
			if (WEB3FinTransferDivDef.BANK_TRANSFER.equals(l_transferedFinInstitutionRow.getTransferDiv()))
			{
				//振込先金融機@関.振替区分="1(銀行）"の場合、例外をスローする。
				log.debug("振替区分=1(銀行)の場合、銀行名に値がありません。");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01937,
					this.getClass().getName() + "." + STR_METHOD_NAME);   
			}
		}    
        
        //=========remain zhou-yong NO.4 begin =============
        
        //1.22）get支店コード( )(振込先金融機@関::get支店コード)
        //アイテムの定義
        //支店コードを取得する。
        String l_strFinBranchCode =
            l_gentradeTransferedFinInstitution.getFinBranchCode();         
        
        //=========remain zhou-yong NO.4 end =============
        
        //1.23）get支店名( )
        //支店名を取得する。 
        String l_strFinBranchName =
            l_gentradeTransferedFinInstitution.getFinBranchName();  
        
        //1.24） get預金区分( )
        //預金区分を取得する。   
        String l_strFinSaveDiv =
            l_gentradeTransferedFinInstitution.getFinSaveDiv();            
        
        //1.25）get口座番号( )
        //口座番号を取得する。    
        String l_strFinAccountNo =
            l_gentradeTransferedFinInstitution.getFinAccountNo();
        
        //1.26）レスポンスデータを生成する。
        WEB3AioCashoutCompleteResponse l_aioCashoutCompleteResponse =
            (WEB3AioCashoutCompleteResponse) l_request.createResponse();
         
        //1.27）(*) 以下のとおりに、プロパティをセットする。
         
        //レスポンス.振込先登録区分 = "1"（登録済）
        l_aioCashoutCompleteResponse.transRegistDiv = WEB3BankAccountRegiDef.ALREADY_REGISTER;

        //レスポンス.銀行コード = 振込先金融機@関.get銀行コード()の戻り値
        l_aioCashoutCompleteResponse.bankCode = l_strFinInstitutionCode;
        
        //レスポンス.銀行名 = 振込先金融機@関.get銀行名()の戻り値
        l_aioCashoutCompleteResponse.bankName = l_strFinInstitutionName;   
        
        //レスポンス.支店名 = 振込先金融機@関.get支店名()の戻り値
        l_aioCashoutCompleteResponse.branchBankName = l_strFinBranchName;
        
        //レスポンス.預金区分 = 振込先金融機@関.get預金区分()の戻り値
        l_aioCashoutCompleteResponse.depositDiv = l_strFinSaveDiv;
        
        //==========remain zhou-yong NO.5 begin ===========
        
        //レスポンス.口座番号 = （以下のとおり）
        //１）・銀行コード="9900"（郵貯）の場合、振込先金融機@関.get支店コード()の戻り値 + "-" + get口座番号( )の戻り値  をセット
        //    ・銀行コード!="9900"（郵貯）の場合、get口座番号( )の戻り値 をセット
		if(WEB3BankCodeDef.POSTAL_SAVINGS.equals(l_strFinInstitutionCode))
		{
			l_aioCashoutCompleteResponse.accountID = l_strFinBranchCode + "-" + l_strFinAccountNo;
		}
		else 
		{
			l_aioCashoutCompleteResponse.accountID = l_strFinAccountNo;
		}
        
        //==========remain zhou-yong NO.5 end ===========
        
        //レスポンス.出金金額 = リクエストデータ.出金金額
        l_aioCashoutCompleteResponse.cashoutAmt = l_request.cashoutAmt;
        
        //レスポンス.振込予定日 = リクエストデータ.振込予定日
        l_aioCashoutCompleteResponse.transScheduledDate = 
            l_request.transScheduledDate;
        
        //レスポンス.更新時間 = 注文単位.更新日付
        l_aioCashoutCompleteResponse.lastUpdatedTimestamp = 
            l_aioOrderUnitRow.getLastUpdatedTimestamp();
        
        //レスポンス.注文ID = （validate新規注文の戻り値）.getNewOrderId()の戻り値 
        //l_aioCashoutCompleteResponse.orderId = String.valueOf(l_lngNewOrderId);
        l_aioCashoutCompleteResponse.orderId =l_lngNewOrderId + "";
                
        log.exiting(STR_METHOD_NAME);
        return l_aioCashoutCompleteResponse;

    }
}
@
