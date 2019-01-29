head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinanceAmountRepayUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 融資額返済UnitServiceImpl(WEB3AioFinanceAmountRepayUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 唐性峰 (中訊) 新規作成       
                   2006/10/23 徐大方 (中訊) 仕様変更モデル670
                   2006/11/06 徐大方 (中訊) 仕様変更モデル683
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSecurityLoanUpdateInterceptor;
import webbroker3.aio.data.PayRequiredAmountParams;
import webbroker3.aio.define.WEB3AioUpdaterCodeDivDef;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PayRequiredAmountStatusDef;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SecurityShortageAccountPK;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * (融資額返済UnitServiceImpl)<BR>
 * 融資額返済UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 * TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * 
 * @@author 唐性峰(中訊)
 * @@version 1.0
 */
public class WEB3AioFinanceAmountRepayUnitServiceImpl implements WEB3AioFinanceAmountRepayUnitService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioFinanceAmountRepayUnitServiceImpl.class);
    
    /**
     * @@roseuid 4510F52E0148
     */
    public WEB3AioFinanceAmountRepayUnitServiceImpl() 
    {
     
    }
    
    /**
     * 返済必要額データ更新処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（融資額返済）返済必要額データ更新」参照。<BR>
     * @@param l_payRequiredAmountParams - (返済必要額データParams)<BR>
     * 返済必要額データの行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 4506A0E70047
     */
    public void execute(PayRequiredAmountParams l_payRequiredAmountParams) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(PayRequiredAmountParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_payRequiredAmountParams == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //1.1　@口座をロックする。 
            //　@－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 
            //※引数は返済必要額データParamsから取得。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //証券会社コードを取得する
            String l_strInstitutionCode =
                l_payRequiredAmountParams.getInstitutionCode();

            //部店コードを取得する
            String l_strBranchCode = l_payRequiredAmountParams.getBranchCode();

            //口座コードを取得する
            String l_strAccountCode = l_payRequiredAmountParams.getAccountCode();

            l_accountManager.lockAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);

            //1.2 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            //顧客オブジェクトを取得する。 
            //［引数］ 
            //証券会社コード：　@返済必要額データParams.証券会社コード 
            //部店コード：　@返済必要額データParams.部店コード 
            //顧客コード：　@返済必要額データParams.顧客コード
            WEB3GentradeMainAccount l_gentradeMainAccount = l_accountManager.getMainAccount(
                l_payRequiredAmountParams.getInstitutionCode(),
                l_payRequiredAmountParams.getBranchCode(),
                l_payRequiredAmountParams.getAccountCode());
            
            //1.3 is信用口座開設(弁済区分 : String)
            //信用口座開設区分を取得する。 
            //[引数] 
            //弁済区分：”指定なし”
            boolean l_blnIsMarginAccountEstablished = 
                l_gentradeMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
            
            //1.4 is先物OP口座開設(先物／オプション区分 : String)
            //先物口座開設区分を取得する。 
            //[引数] 
            //先物/オプション区分：”1”(先物) 
            //[戻り値] 
            //先物口座開設区分
            boolean l_blnFuturesAccountOpenDiv = 
                l_gentradeMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES);
            
            //1.5 is先物OP口座開設(先物／オプション区分 : String)
            //オプション口座開設区分を取得する。 
            //[引数] 
            //先物/オプション区分：”2”(オプション) 
            //[戻り値] 
            //オプション口座開設区分
            boolean l_blnOptionAccountOpenDiv = 
                l_gentradeMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);
            
            //1.6  get顧客行( )
            //顧客Paramsを取得する。
            MainAccountParams l_mainAccountParams = 
                (MainAccountParams)l_gentradeMainAccount.getMainAccountRow();
            
            if (!l_payRequiredAmountParams.getPayRequiredAmountIsNull())
            {
                boolean l_blnFlag = true;
                //1.6 is信用口座開設() = false(信用客でない) 且つ 先物口座開設区分 = false 
                //且つ オプション口座開設区分 = false 且つ 顧客Params.証券担保ローン区分　@= 1(開設)の場合
                if (!(l_blnIsMarginAccountEstablished 
                    || l_blnFuturesAccountOpenDiv 
                    || l_blnOptionAccountOpenDiv)
                    && l_mainAccountParams.getSecuredLoanSecAccOpenDiv().equals(WEB3SecuredLoanSecAccOpenDivDef.OPEN))
                {
                    //1.7.1 get補助口座(口座ID : , 補助口座タイプ : )
                    //補助口座オブジェクトを取得する。 
                    //[引数] 
                    //口座ID：　@返済必要額データParams.口座ID 
                    //補助口座タイプ： 1（株式取引口座（預り金））
                    WEB3GentradeSubAccount l_subAccount = 
                        (WEB3GentradeSubAccount) l_accountManager.getSubAccount( 
                            l_payRequiredAmountParams.getAccountId(), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
     
                    //1.7.2 get大証金への振替可能額(補助口座 : 補助口座, 受渡日 : Date)
                    //大証金への振替可能額を取得する。 
                    //[引数の設定] 
                    //補助口座：　@get補助口座()の戻り値 
                    //受渡日：　@返済必要額データParams.処理日の翌々営業日
                    WEB3TPTradingPowerService l_tradingPower = 
                        (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
                    Timestamp l_tsBaseTime = 
                        new Timestamp(
                            WEB3DateUtility.getDate(l_payRequiredAmountParams.getProcDate(), "yyyyMMdd").getTime());
                    WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBaseTime);
                    Timestamp l_tsNextDate = l_genBizDate.roll(2);

                    double l_dblOsakaTransferableTradingPower = 
                        l_tradingPower.getOsakaTransferableTradingPower(
                            l_subAccount,
                            l_tsNextDate);

                    
                    //1.7.3  (*)プロパティセット
                    //(*)以下のとおりにプロパティをセットする。
                    //余力：　@get大証金への振替可能額()の戻り値
                    //返済必要額：　@返済必要額データParams.返済必要額
                    double l_dblDecisionPayAmount = 0D;
                    
                    if (l_dblOsakaTransferableTradingPower <= l_payRequiredAmountParams.getPayRequiredAmount())
                    {
                        //・(余力　@<= 返済必要額)の場合
                        //   確定返済額 = 余力
                        log.debug("余力　@<= 返済必要額の場合");
                        l_dblDecisionPayAmount = l_dblOsakaTransferableTradingPower;

                    }
                    else if (l_dblOsakaTransferableTradingPower > l_payRequiredAmountParams.getPayRequiredAmount())
                    {
                        //・(余力　@> 返済必要額)の場合
                        //　@確定返済額 = 返済必要額
                        log.debug("(余力　@> 返済必要額)の場合");
                        l_dblDecisionPayAmount = l_payRequiredAmountParams.getPayRequiredAmount();
                    }
                    //3）確定返済額の100円単位まで切り捨て
                    BigDecimal l_bdDecisionPayAmount= new BigDecimal(String.valueOf(l_dblDecisionPayAmount));
                    BigDecimal l_bdThousand = new BigDecimal("1000");
                    l_bdDecisionPayAmount = 
                        l_bdDecisionPayAmount.divide(
                            l_bdThousand, 0, BigDecimal.ROUND_DOWN).multiply(l_bdThousand);
                    l_dblDecisionPayAmount = l_bdDecisionPayAmount.doubleValue();
                    l_payRequiredAmountParams.setDecisionPayAmount(l_dblDecisionPayAmount);

                    //1.7.4 get商品ID(証券会社 : Institution)
                    //商品ID（銘柄ID）を取得する。 
                    //[引数] 
                    //証券会社： 補助口座.get取引店().getInstitution()の戻り値
                    TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
                    WEB3AioOrderManager l_orderManager = 
                        (WEB3AioOrderManager) l_tradingModule.getOrderManager();

                    long l_lngProductId = 
                        l_orderManager.getProductId(l_subAccount.getWeb3GenBranch().getInstitution());

                    //1.7.5 入出金注文内容(代理入力者 : Trader, 注文種別 : OrderTypeEnum, 
                    //振替タイプ : AssetTransferTypeEnum, 商品ID : long, 金額 : double, 
                    //記述 : String, 振替予定日 : Date, 決済機@関ID : String, 注文ID : Long)
                    //
                    //入出金注文内容を生成する。 
                    //[引数の設定] 
                    //代理入力者：　@null 
                    //注文種別： 1019（振替注文(預り金から大証金)） 
                    //振替タイプ：　@2（出金） 
                    //商品ID：　@get商品ID()の戻り値 
                    //金額：　@確定返済額 
                    //記述：　@null 
                    //振替予定日：　@返済必要額データParams.処理日の翌々営業日 
                    //決済機@関ID：　@null 
                    //注文ID：　@null
                    WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
                        null,
                        OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK,
                        AssetTransferTypeEnum.CASH_OUT,
                        l_lngProductId,
                        l_dblDecisionPayAmount,
                        null,
                        l_tsNextDate,
                        null,
                        null); 
                    
                    //1.7.6 証券担保ローン更新インタセプタ(入出金注文内容 : 入出金注文内容)
                    //コンストラクタ 
                    //インスタンスを生成し、引数の入出金注文内容をプロパティにセットする。
                    WEB3AioSecurityLoanUpdateInterceptor l_securityLoanUpdateInterceptor =
                        new WEB3AioSecurityLoanUpdateInterceptor(l_aioNewOrderSpec);
                    
                    //1.7.7 (*)プロパティセット
                    //(*)以下のようにプロパティ値をセットする。
                    //発注日 = 返済必要額データParams.処理日の翌営業日
                    //受渡日 = 返済必要額データParams.処理日の翌々営業日
                    //注文経路 = "E"(振替注文(預り金から大証金))
                    l_securityLoanUpdateInterceptor.setOrderBizDate(l_genBizDate.roll(1));
                    l_securityLoanUpdateInterceptor.setDeliveryDate(l_tsNextDate);
                    l_securityLoanUpdateInterceptor.setOrderRootDiv(WEB3OrderRootDivDef.FROM_DEPOSIT_AMOUNT_DSK);
                    
                    //1.7.8 createNewOrderId( )
                    //新規注文IDを採番する。
                    long l_lngNewOrderId = l_orderManager.createNewOrderId();
                    
                    //1.7.9 setThreadLocalPersistenceEventInterceptor(arg0 : AioOrderManagerPersistenceEventInterceptor)
                    //インタセプタをセットする。  
                    //[引数]  
                    //入出金注文更新インタセプタ：　@生成した入出金注文更新インタセプタ
                    l_orderManager.setThreadLocalPersistenceEventInterceptor(l_securityLoanUpdateInterceptor);
                    
                    //1.7.10 submitNewOrder(arg0 : SubAccount, arg1 : ProductTypeEnum, arg2 : NewOrderSpec,
                    //arg3 : long, arg4 : String, arg5 : boolean)
                    //注文登録処理を行う。 
                    //[引数] 
                    //補助口座：　@補助口座 
                    //商品タイプ：　@5（現金） 
                    //入出金注文内容：　@入出金注文内容()の戻り値 
                    //注文ＩＤ：　@createNewOrderId()の戻り値 
                    //パスワード：　@　@顧客.getTradingPassword()の戻り値をWEB3Crypt.decrvpt()で復号したもの 
                    //isSkip発注審査：　@true 
                    WEB3Crypt l_web3Crypt = new WEB3Crypt();
                    OrderSubmissionResult l_submissionResult =
                        l_orderManager.submitNewOrder(
                            l_subAccount,
                            ProductTypeEnum.CASH,
                            l_aioNewOrderSpec,
                            l_lngNewOrderId,
                            l_web3Crypt.decrypt(l_gentradeMainAccount.getTradingPassword()),
                            true);
                    if (l_submissionResult.getProcessingResult().isFailedResult())
                    {
                        l_blnFlag = false;
                    }
                    
                    if (l_blnFlag)
                    {
                        //1.7.11 doFindByPrimaryKeyQuery(arg0 : PrimaryKey)
                        //担保不足顧客データテーブルから、以下の条件のレコードを取得する。 
                        //[引数] 
                        //口座ID = 返済必要額データParams.口座ID 
                        //[戻り値] 
                        //担保不足顧客データRow
                        boolean l_blnFindRow = true;
                        SecurityShortageAccountRow l_securityShortageAccountRow = null;
                        QueryProcessor l_queryProcessor = null;
                        try
                        {         
                            l_queryProcessor = Processors.getDefaultProcessor();
                            PrimaryKey l_primaryKey = new SecurityShortageAccountPK(
                                l_payRequiredAmountParams.getAccountId());

                            l_securityShortageAccountRow =
                                (SecurityShortageAccountRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_primaryKey);
                        }

                        catch (DataQueryException l_ex)
                        {
                            l_blnFindRow = false;
                            log.debug("DBへのアクセスに失敗しました", l_ex);
                        }
                        catch (DataNetworkException l_ex)
                        {
                            l_blnFindRow = false;
                            log.debug("DBサーバとの通信に失敗した", l_ex);
                        }

                        if (l_blnFindRow)
                        {
                            SecurityShortageAccountParams l_securityShortageAccountParams =
                                new SecurityShortageAccountParams(l_securityShortageAccountRow);

                            //1.7.12  担保不足顧客データParams.処理日 = 返済必要額データParams.処理日　@尚且つ　@
                            //担保不足顧客データParams.出金停止区分 = ' 2 ' の場合
                            if (l_securityShortageAccountParams.getProcDate().equals(
                                l_payRequiredAmountParams.getProcDate()) &&
                                WEB3PaymentStopDivDef.PART.equals(l_securityShortageAccountParams.getPaymentStopDiv()))
                            {
                                //1.7.12.1 
                                String l_strPaymentStopAmount = "0";
                                if (l_securityShortageAccountParams.getPaymentStopAmount() != null)
                                {
                                    l_strPaymentStopAmount = 
                                        l_securityShortageAccountParams.getPaymentStopAmount();
                                }
                                if (Double.parseDouble(l_strPaymentStopAmount) > 
                                    l_payRequiredAmountParams.getDecisionPayAmount())
                                {
                                    BigDecimal l_bdResult = new BigDecimal(
                                        l_strPaymentStopAmount).subtract(
                                            new BigDecimal(WEB3StringTypeUtility.formatNumber(
                                                l_payRequiredAmountParams.getDecisionPayAmount())));
                                    l_securityShortageAccountParams.setPaymentStopAmount(
                                        WEB3StringTypeUtility.formatNumber(l_bdResult.doubleValue()));
                                }
                                else
                                {
                                    l_securityShortageAccountParams.setPaymentStopAmount("0");
                                }
                                
                                //1.7.12.2 プロパティセット
                                l_securityShortageAccountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
                                l_securityShortageAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                                //1.7.12.3 doUpdateQuery(arg0 : Row)
                                //担保不足顧客データテーブルのレコードを更新する。 
                                //［引数］ 
                                //担保不足顧客データParams
                                try
                                {
                                    l_queryProcessor.doUpdateQuery(l_securityShortageAccountParams);
                                }
                                catch (DataQueryException l_ex)
                                {
                                    log.debug("Error in DataQueryException... ", l_ex);
                                }
                                catch (DataNetworkException l_ex)
                                {
                                    log.debug("DBへのアクセスに失敗しました: ", l_ex);
                                }
                            }
                        }
                        
                        //1.7.13 余力再計算(補助口座 : 補助口座)
                        //余力更新を行う。 
                        //[引数の設定] 
                        //補助口座：　@補助口座
                        WEB3TPTradingPowerReCalcService l_tradingPowerReCalcService =
                            (WEB3TPTradingPowerReCalcService)Services.getService(
                                WEB3TPTradingPowerReCalcService.class);

                        l_tradingPowerReCalcService.reCalcTradingPower(l_subAccount);

                        //1.7.14  (*)プロパティセット
                        //(*)返済必要額データParamsの以下の値をセットする。
                        //上記処理が正常終了の場合：
                        //　@処理区分 = 1(正常終了)
                        l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.NORMAL_END);
                    }  
                }     
                if (l_blnFlag)
                {
                    //1.8 (*)is信用口座開設() = true(信用客) 若しくは 先物口座開設区分 = true 若しくは オプション口座開設区分 = true の場合
                    if (l_blnIsMarginAccountEstablished
                        || l_blnFuturesAccountOpenDiv
                        || l_blnOptionAccountOpenDiv)
                    {
                        //1.8.1 (*)プロパティセット
                        //(*)返済必要額データParamsの以下の値を更新する。
                        //処理区分 = 2(現物顧客以外エラー)
                        //確定返済額 = null
                        l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.EQTYPE_COSTOMER_EXCEPT);
                        l_payRequiredAmountParams.setDecisionPayAmount(null);
                        
                    }
                    
                    //1.9  (*)上記条件を満たさない場合　@且つ　@顧客Params.証券担保ローン区分 = 0(未開設)の場合
                    else if (l_mainAccountParams.getSecuredLoanSecAccOpenDiv().equals(
                        WEB3SecuredLoanSecAccOpenDivDef.NOT_OPEN))
                    {
                        //1.9.1 (*)プロパティセット
                        //(*)返済必要額データParamsの以下の値を更新する。
                        //処理区分 = 3(証券担保ローン未開設)
                        //確定返済額 = null
                        l_payRequiredAmountParams.setStatus(
                            WEB3PayRequiredAmountStatusDef.SECURITIES_WARRANTY_LOAN_NOT_OPEN);
                        l_payRequiredAmountParams.setDecisionPayAmount(null);
                        
                    } 
                    
                    //1.10 (*)プロパティセット
                    //(*)返済必要額データParamsの以下の値を更新する。
                    //更新者コード = "aio_loan"
                    //更新日時 = 処理日
                    l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
                    l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
                }                      
                else
                {
                    //1.11  (*)プロパティセット
                    //上記処理でエラーが発生した場合以下のプロパティ値をセットする。
                    //処理区分 = 9(エラー)
                    //確定返済額 = null
                    //更新者コード = "aio_loan"
                    //更新日時 = 処理日
                    log.debug("Error in submitNewOrder");
                    l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.ERROR);
                    l_payRequiredAmountParams.setDecisionPayAmount(null);
                    l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
                    l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
                }
            }
            else 
            {
                //1.12  (*)プロパティセット
                //上記処理でエラーが発生した場合以下のプロパティ値をセットする。
                //処理区分 = 9(エラー)
                //確定返済額 = null
                //更新者コード = "aio_loan"
                //更新日時 = 処理日
                log.debug("返済必要額データParams.返済必要額の戻り値==nullの場合");
                l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.ERROR);
                l_payRequiredAmountParams.setDecisionPayAmount(null);
                l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
                l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
            }

        }
        catch (NotFoundException l_ex)
        {
            //1.10  (*)プロパティセット
            //上記処理でエラーが発生した場合以下のプロパティ値をセットする。
            //処理区分 = 9(エラー)
            //確定返済額 = null
            //更新者コード = "aio_loan"
            //更新日時 = 処理日
            log.debug("テーブルに該当するデータがありません。" + l_ex);
            l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.ERROR);
            l_payRequiredAmountParams.setDecisionPayAmount(null);
            l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
            l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
        }
        catch (WEB3BaseException l_ex)
        {
            //1.10  (*)プロパティセット
            //上記処理でエラーが発生した場合以下のプロパティ値をセットする。
            //処理区分 = 9(エラー)
            //確定返済額 = null
            //更新者コード = "aio_loan"
            //更新日時 = 処理日
            log.debug("エラー in 'get顧客' 若しくは 'get商品ID'" + l_ex);
            l_payRequiredAmountParams.setStatus(WEB3PayRequiredAmountStatusDef.ERROR);
            l_payRequiredAmountParams.setDecisionPayAmount(null);
            l_payRequiredAmountParams.setLastUpdater(WEB3AioUpdaterCodeDivDef.AIO_LOAN);
            l_payRequiredAmountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp()); 
        }
        
        //1.11 doUpdateQuery(arg0 : Row)
        //返済必要額データテーブルのレコードを更新する。 
        //［引数］ 
        //返済必要額データParams
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_payRequiredAmountParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        log.exiting(STR_METHOD_NAME);
    }
}
@
