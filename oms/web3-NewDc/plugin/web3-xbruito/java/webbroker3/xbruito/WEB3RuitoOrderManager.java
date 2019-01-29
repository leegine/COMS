head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張累投注文マネージャクラス(WEB3RuitoOrderManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 韋念瓊 (中訊) 新規作成
                   2004/12/02 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoOrderManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3TradedDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張累投注文マネージャクラス<BR>
 */
public class WEB3RuitoOrderManager extends RuitoOrderManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3RuitoOrderManager.class);

    /**
     * （validateNewOrderのオーバーロード）<BR>
     * <BR>
     * 累投買付・解約注文の発注審査を行う。<BR>
     * <BR>
     * １）　@引数.is買付 == trueの場合<BR>
     * 　@－validate新規注文（買付）()をコールする。<BR>
     * 　@　@［validate新規注文（買付）に渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@拡張累投銘柄： 引数.拡張累投銘柄<BR>
     * 　@　@　@注文数量： 引数.注文数量<BR>
     * 　@　@　@指定方法@： 引数.指定方法@<BR>
     * 　@－validate新規注文（買付）()が正常終了した場合は、<BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。<BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］<BR>
     * 　@　@　@　@発注審査結果： ProcessingResult.SUCCESS_RESULT<BR>
     * 　@－validate新規注文（買付）()が例外をスローした場合は、<BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。<BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］<BR>
     * 　@　@　@　@発注審査結果： <BR>
     *               例外.getValidationResult().getProcessingResult()<BR>
     * <BR>
     * ２）　@引数.is買付 == falseの場合<BR>
     * 　@－validate新規注文（解約）()をコールする。<BR>
     * 　@　@［validate新規注文（解約）に渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@拡張累投銘柄： 引数.拡張累投銘柄<BR>
     * 　@　@　@注文数量： 引数.注文数量<BR>
     * 　@　@　@受渡方法@： 引数.受渡方法@<BR>
     * 　@　@　@指定方法@： 引数.指定方法@<BR>
     * 　@－validate新規注文（解約）()が正常終了した場合は、<BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。<BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］<BR>
     * 　@　@　@　@発注審査結果： ProcessingResult.SUCCESS_RESULT<BR>
     * 　@－validate新規注文（解約）()が例外をスローした場合は、<BR>
     * 　@　@　@NewOrderValidationResultオブジェクトを生成して返す。<BR>
     * 　@　@　@［NewOrderValidationResultのコンストラクタに渡すパラメタ］<BR>
     * 　@　@　@　@発注審査結果： <BR>
     *          例外.getValidationResult().getProcessingResult()<BR>
     * @@param l_subAccount - 補助口座L<BR>
     * @@param l_expansionRuitoProduct - 拡張累投銘柄<BR>
     * @@param l_orderQuantity - 注文数量<BR>
     * @@param l_isBuy - 買付の場合はtrue、解約の場合はfalseを指定する。<BR>
     * @@param l_strPaymentMethod - 受渡方法@<BR>
     * <BR>
     * 解約の場合のみ使用。<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * <BR>
     * 解約の場合のみ使用。<BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationRes
     * ult
     * @@roseuid 406B70F3011D
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct,
        double l_dbOrderQuantity,
        boolean isBuy,
        String l_strPaymentMethod,
        String l_strDesignateMethod)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateNewOrder(" +
            "SubAccount l_subAccount,WEB3RuitoProduct " +
            "l_ruitoProduct,double l_dbOrderQuantity,boolean isBuy," +
            "String l_strPaymentMethod,String l_strDesignateMethod)";
            
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_ruitoProduct == null)
        {
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //１）　@引数.is買付 == trueの場合
            if (isBuy)
            {          
                //－validate新規注文（買付）()をコールする。
                this.validateNewOrderBuy(
                    l_subAccount,
                    l_ruitoProduct,
                    l_dbOrderQuantity,
                    l_strDesignateMethod);
                //－validate新規注文（買付）()が正常終了した場合は、
                NewOrderValidationResult l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                return l_newOrderValidationResult;
            }
            // ２）　@引数.is買付 == falseの場合
            else
            {
                log.debug("引数.is買付 == falseの場合");
                //－validate新規注文（解約）()をコールする。
                this.validateNewOrderSell(
                    l_subAccount,
                    l_ruitoProduct,
                    l_dbOrderQuantity,
                    l_strPaymentMethod,
                    l_strDesignateMethod);
                //－validate新規注文（解約）()が正常終了した場合は、
                NewOrderValidationResult l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("l_newOrderValidationResult = " + l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
                return l_newOrderValidationResult;
            }
        }
        catch (OrderValidationException l_ex)
        {
            //－validate新規注文()が例外をスローした場合は、
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(
                    l_ex.getValidationResult().getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            return l_newOrderValidationResult;
        }
    }

    /**
     * （validateCancelOrderの実装） <BR>
     * <BR>
     * シーケンス図「(累投)注文・約定エンティティ／(累投)発注審査(取消)」 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座ID<BR>
     * @@param l_cancelOrderSpec - 累投取消注文内容<BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 406B70F3016B
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec validateCancelOrder)
    {
        String STR_METHOD_NAME =
            "validateCancelOrder(SubAccount l_subAccount, " + "CancelOrderSpec l_cancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        NewOrderValidationResult l_newOrderValidationResult = null;
        try
        {
            if (l_subAccount == null || validateCancelOrder == null)
            {
                 throw new WEB3BaseException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + "." + STR_METHOD_NAME);
            }            
            
            //2. 累投注文オブジェクトを取得する。 
            OrderUnit[] l_orderUnits = 
                this.getOrderUnits(validateCancelOrder.getOrderId());
                
            RuitoOrderUnit l_ruitoOrderUnit = (RuitoOrderUnit)l_orderUnits[0];

            log.debug("l_ruitoOrderUnit.getOrderUnitId() = " + l_ruitoOrderUnit.getOrderUnitId());
            
            //3. 指定された注文が取消可能かチェックする。
            WEB3RuitoOrderManagerReusableValidationsCheck l_orderManagerReusableValidationsCheck =
                new WEB3RuitoOrderManagerReusableValidationsCheck();

            l_orderManagerReusableValidationsCheck.validateCancelPossible(l_ruitoOrderUnit);

            // NewOrderValidationResultオブジェクトを生成して返す。           
            l_newOrderValidationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
        }

        catch (OrderValidationException l_ex)
        {
            //NewOrderValidationResultオブジェクトを生成して返す。
            l_newOrderValidationResult =
                new NewOrderValidationResult(
                    l_ex.getValidationResult().getProcessingResult());
            return l_newOrderValidationResult;
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in validateCancelOrder", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * validate新規注文（買付）<BR>
     * <BR>
     * シーケンス図「(累投)注文・約定エンティティ/(累投)発注審査(買付)」参照 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_expansionRuitoProduct - 拡張累投銘柄<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 407E4A4C008C
     */
    public void validateNewOrderBuy(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct,
        double l_dblOrderQuantity,
        String l_strDesignateMethod)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateNewOrderBuy(" +
            "SubAccount l_subAccount,WEB3RuitoProduct l_ruitoProduct," +
            "double l_dblOrderQuantity,String l_strDesignateMethod)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ruitoProduct == null)
        {
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1.1　@指定方法@チェック         
        WEB3RuitoOrderManagerReusableValidationsCheck l_validCheck =
            new WEB3RuitoOrderManagerReusableValidationsCheck();
        l_validCheck.validateDesignateMethod(l_ruitoProduct, l_strDesignateMethod);

        // 1.2　@取引可能銘柄チェック
        l_validCheck.validateTradedProduct(l_ruitoProduct, true);

        // 1.3　@累積投資口座チェック        
        l_validCheck.validateRuitoAccount(l_subAccount, l_ruitoProduct);

        // 1.4　@同一発注日売買チェック
        l_validCheck.validateSameOrderDateTrade(l_subAccount, 
            WEB3BuySellTypeDef.BUY, l_ruitoProduct);

        //1.5 拡張累投銘柄マネージャ( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        WEB3RuitoProductManager l_ruitoProductMgr = 
            (WEB3RuitoProductManager) l_tm.getProductManager();
        
        // 1.6　@受渡日取得
        Date l_datDelivertDate = 
            l_ruitoProductMgr.getDeliveryDate(
                    l_ruitoProduct.getInstitution(),
                    l_ruitoProduct.getProductCode(),
                    true);

        // 1.7　@取引金額チェック
        l_validCheck.validateTradedPrice(l_dblOrderQuantity, l_ruitoProduct, true);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate新規注文（解約）<BR>
     * <BR>
     * シーケンス図「(累投)注文・約定エンティティ/(累投)発注審査(解約)」参照 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_expansionRuitoProduct - 拡張累投銘柄<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@param l_strPaymentMethod - 受渡方法@<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException
     * @@roseuid 407E645903A9
     */
    public void validateNewOrderSell(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct,
        double l_dblOrderQuantity,
        String l_strPaymentMethod,
        String l_strDesignateMethod)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateNewOrderSell(" +
            "SubAccount l_subAccount,WEB3RuitoProduct l_ruitoProduct," +
            "double l_dblOrderQuantity,String l_strPaymentMethod," +
            "String l_strDesignateMethod)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_ruitoProduct == null)
        {
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3RuitoOrderManagerReusableValidationsCheck l_validCheck =
            new WEB3RuitoOrderManagerReusableValidationsCheck();
        
        //1.1　@取引可能銘柄チェック
        l_validCheck.validateTradedProduct(l_ruitoProduct, false);

        //1.2　@引数.受渡方法@の値が”1：銀行振込”の場合、
        if (l_strPaymentMethod.equals(WEB3PaymentMethodDef.BANK_TRANSFER))
        {
            //1.2.1 銀行振込先口座登録チェックを行う。
            l_validCheck.validateBankTransferAccountInsert(l_subAccount);
        }

        //1.3　@引数.指定方法@が”全部”の場合、
        if (l_strDesignateMethod.equals(WEB3DesignateMethodDef.ALL))
        {
            log.debug("指定方法@が”全部”の場合");
            //1.3.1 既に解約注文がだされていないかチェックする。
            l_validCheck.validateAllSell(l_subAccount, l_ruitoProduct);
        }
        //1.4　@引数.指定方法@が”全部”でない場合
        else if ((l_strDesignateMethod.equals(WEB3SellDivDef.MONEY_DESIGNATE)) || 
                (l_strDesignateMethod.equals(WEB3SellDivDef.COUNT_DESIGNATE)))
        {
			log.debug("指定方法@が”全部”でない場合");
			//1.4.1 同一日解約チェックを行う。
			l_validCheck.validateSameOrderDateSell(
				l_subAccount,
				l_ruitoProduct,
				l_strDesignateMethod);
				
            //1.4.2 解約可能残高チェックを行う。
            l_validCheck.validateSellPossibleBalance(
                l_subAccount,
                l_ruitoProduct,
                l_dblOrderQuantity);

            //1.4.3　@取引金額チェック<BR>
            l_validCheck.validateTradedPrice(l_dblOrderQuantity, l_ruitoProduct, false);
        }
        
		//1.5 累投発注審査個別チェック（同一発注日売買チェック）
		String l_strTradedDiv = null;
		if (l_strDesignateMethod.equals(WEB3DesignateMethodDef.ALL))
		{
			l_strTradedDiv = WEB3TradedDivDef.ALL_SELL;
		}
		else if ((l_strDesignateMethod.equals(WEB3SellDivDef.MONEY_DESIGNATE)) || 
				(l_strDesignateMethod.equals(WEB3SellDivDef.COUNT_DESIGNATE)))
		{
			l_strTradedDiv = WEB3TradedDivDef.PARTIALLY_SELL;
		}
		l_validCheck.validateSameOrderDateTrade
				(l_subAccount, l_strTradedDiv, l_ruitoProduct);
				
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 累投注文単位オブジェクトを返す。<BR>
     * <BR>
     * １）　@証券会社オブジェクトを取得する。<BR>
     * 　@アカウントマネージャ.getInstitution()をコールし、<BR>
     *      証券会社オブジェクトを取得する。<BR>
     * 　@［getInstitutionに渡すパラメタ］<BR>
     * 　@　@証券会社コード： 引数.証券会社コード<BR>
     * <BR>
     * ２）　@部店オブジェクトを取得する。<BR>
     * 　@アカウントマネージャ.getBranch()をコールして、<BR>
     *      部店オブジェクトを取得する。<BR>
     * 　@［getBranchに渡すパラメタ］<BR>
     * 　@　@証券会社： 取得した証券会社オブジェクト<BR>
     * 　@　@部店コード： 引数.部店コード<BR>
     * <BR>
     * ３）　@累投注文単位オブジェクトを取得する。<BR>
     * 　@－以下の条件で累投注文単位オブジェクトを検索し、<BR>
     *      累投注文単位Paramsオブジェクトを取得する。<BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@部店ID = 取得した部店オブジェクト.getBranchId()の戻り値 AND<BR>
     * 　@　@　@識別コード = 引数.識別コード<BR>
     * <BR>
     * 　@－this.getOrderUnit()をコールし、<BR>
     *        累投注文単位オブジェクトを取得する。<BR>
     * 　@　@[getOrderUnitに渡すパラメタ]<BR>
     * 　@　@　@注文単位ID： 累投注文単位Params.getOrderUnitId()の戻り値<BR>
     * <BR>
     * ４）　@取得した累投注文単位オブジェクトを返す。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_strRequestNumber - 識別コード<BR>
     * @@return RuitoOrderUnit
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 40851B8A0157
     */
    public RuitoOrderUnit getRuitoOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber)
        throws NotFoundException,WEB3BaseException
    {
        String STR_METHOD_NAME = "getRuitoOrderUnit(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        // 1)証券会社オブジェクトを取得する。
        FinApp l_finApp;        
        l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        Institution l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
        log.debug("証券会社オブジェクトを取得する。");
        log.debug("InstitutionCode =  " + l_institution.getInstitutionCode());
        
        // 2)部店オブジェクトを取得する。
        Branch l_branch = 
            l_accMgr.getBranch(l_institution, l_strBranchCode);
        log.debug("部店オブジェクトを取得する。");
        log.debug("BranchCode = " + l_branch.getBranchCode());
        
        RuitoOrderUnit l_ruitoOrderUnit = null;
        try
        {
            // 3)累投注文単位オブジェクトを取得する。
            String l_whereClause = 
                "branch_id = ? and order_request_number = ?";
            
            log.debug("branch_id = " + l_branch.getBranchId());
            log.debug("order_request_number = " + l_strRequestNumber);
            
            List l_lisRows = null;
            Object l_bindVars[] =
                { new Long(l_branch.getBranchId()), new String(l_strRequestNumber)};

            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);

			int l_intCount = l_lisRows.size();
			log.debug("l_intCount = " + l_intCount);
            if (l_intCount == 1)
            {
                RuitoOrderUnitParams[] l_ruitoOrderUnitParams = new RuitoOrderUnitParams[l_intCount];
                l_lisRows.toArray(l_ruitoOrderUnitParams);
                
                log.debug("l_lisRows.size = " + l_lisRows.size());            
                log.debug("l_ruitoOrderUnitParams[0].getOrderUnitId() = " + 
                    l_ruitoOrderUnitParams[0].getOrderUnitId());
    
                l_ruitoOrderUnit = (RuitoOrderUnit) this.getOrderUnit(
                        l_ruitoOrderUnitParams[0].getOrderUnitId());
            }
			else if (l_lisRows == null || l_intCount == 0)
			{
				log.debug("__テーブルに該当するデータがありません__");
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			else if (l_intCount > 1)
			{
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80004,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"テーブルに重複する該当データが存在します。");
			}
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);

        }
        log.exiting(STR_METHOD_NAME);
        // 4)取得した累投注文単位オブジェクトを返す。
        return l_ruitoOrderUnit;
    }

    /**
     * 累投注文単位オブジェクトを返す。<BR>
     * <BR>
     * １）　@累投注文単位オブジェクトを取得する。<BR>
     * 　@－以下の条件で累投注文単位オブジェクトを検索し、<BR>
     *        累投注文単位Paramsオブジェクトを取得する。<BR>
     * 　@　@[検索条件]<BR>
     * 　@　@　@口座ID = 引数.口座ID AND<BR>
     * 　@　@　@補助口座ID = 引数.補助口座ID AND<BR>
     * 　@　@　@識別コード = 引数.識別コード<BR>
     * <BR>
     * 　@－this.getOrderUnit()をコールし、<BR>
     *          累投注文単位オブジェクトを取得する。<BR>
     * 　@　@[getOrderUnitに渡すパラメタ]<BR>
     * 　@　@　@注文単位ID： 累投注文単位Params.getOrderUnitId()の戻り値<BR>
     * <BR>
     * ２）　@取得した累投注文単位オブジェクトを返す。<BR>
     * @@param l_lngAccountID - 口座ID<BR>
     * @@param l_lngSubAccountID - 補助口座ID<BR>
     * @@param l_strRequestNumber - 識別コード<BR>
     * @@return RuitoOrderUnit
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 4085D54D018C
     */
    public RuitoOrderUnit getRuitoOrderUnit(
        long l_lngAccountID,
        long l_lngSubAccountID,
        String l_strRequestNumber)
        throws NotFoundException,WEB3BaseException
    {
        String STR_METHOD_NAME = "getRuitoOrderUnit(" +
            "long l_lngAccountID, long l_lngSubAccountID, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        RuitoOrderUnit l_ruitoOrderUnit = null;
        try
        {
            // 1)累投注文単位オブジェクトを取得する。 
            String l_whereClause =
                "account_id = ? and sub_account_id = ? " + 
                "and order_request_number = ?";
            Object l_bindVars[] =
                {
                    new Long(l_lngAccountID),
                    new Long(l_lngSubAccountID),
                    new String(l_strRequestNumber)};
                    
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);
                    
            int l_intCount = l_lisRows.size();
            log.debug("l_intCount = " + l_intCount);
            if (l_intCount == 1)
            {
                RuitoOrderUnitParams[] l_orderUnitParams = new RuitoOrderUnitParams[l_intCount];
                l_lisRows.toArray(l_orderUnitParams);
                l_ruitoOrderUnit =
                    (RuitoOrderUnit) this.getOrderUnit
                    (l_orderUnitParams[0].getOrderUnitId());
            }
			else if (l_lisRows == null || l_intCount == 0)
			{
				log.debug("__テーブルに該当するデータがありません__");
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			else if (l_intCount > 1)
			{
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80004,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"テーブルに重複する該当データが存在します。");
			}
        }        
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);         
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);   
        }
        // 2)取得した累投注文単位オブジェクトを返す。           
        log.exiting(STR_METHOD_NAME);
        return l_ruitoOrderUnit;
    }

    /**
     * (get累投注文単位一覧)<BR>
     * <BR>
     * 指定条件に一致する注文の注文単位オブジェクトの一覧を返却する。<BR>
     * (getOrderUnitsのオーバーロード)<BR>
     * <BR>
     * １)　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２)　@検索条件を生成する。<BR>
     * 　@２－１)　@"account_id = ?　@<BR>
     *               and　@sub_account_id = ?<BR>
     *               and product_type = ? <BR>
     *               and biz_date >= ? and<BR>
     * 　@　@　@　@　@　@  (order_type = ? or order_type = ?)<BR>
     *                and (ruito_type = ?<BR>
     *                 or ruito_type = ?)"<BR>
     * 　@　@　@　@で検索条件文字列を作成する。<BR>
     * <BR>
     * 　@２－２)　@以下のパラメータで検索条件データコンテナを作成する。<BR>
     * 　@　@　@　@1.口座ID(補助口座オブジェクト.getAccountId( )で取得可)<BR>
     * 　@　@　@　@2.補助口座ID(補助口座オブジェクト.getSubAccountId( )で取得可)<BR>
     * 　@　@　@　@3.ProductTypeEnum.RUITO(累積投資)<BR>
     * 　@　@　@　@4.当日（業務日付）を指定(*1)<BR>
     * 　@　@　@　@5.OrderTypeEnum.RUITO_BUY(累投買注文)<BR>
     * 　@　@　@　@6.OrderTypeEnum.RUITO_SELL(累投売注文)<BR>
     * 　@　@　@　@7.RuitoTypeEnum.中期国債ファ@ンド<BR>
     * 　@　@　@　@8.RuitoTypeEnum.MMF<BR>
     * <BR>
     * ３)　@QueryProcessor.doFindAllQuery( )により、<BR>
     *        注文単位オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@doFindAllQuery( 累投注文単位Row.TYPE<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@２－１)の検索条件文字列,<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@引数のソート条件,<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@null,<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@２－２)の検索条件データコンテナ)<BR>
     * <BR>
     * ４)　@検索結果を返却する。<BR>
     * <BR>
     * (*1)　@業務日付の取得<BR>
     * 　@TradingSystem.getBizDate()にて取得した業務(バッチ)日付の戻り値<BR>
     * @@param l_subAccount - 補助口座オブジェクト<BR>
     * @@param l_strSortCond - 累投ソートキーオブジェクト<BR>
     * @@return List
     * @@roseuid 40865F090048
     */
    public List getOrderUnits(SubAccount l_subAccount, String l_strSortCond)
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getOrderUnits(" +
            "SubAccount l_subAccount, String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //検索条件を生成する。
        List l_lisRows = null;
        List l_lisReturnOrderUnit = null;
        String l_strWhereClause;
        l_strWhereClause =
            "account_id = ? AND sub_account_id = ? AND "
                + "product_type = ? AND biz_date >= ? AND "
                + "(order_type = ? OR order_type = ?) AND "
                + "(ruito_type = ? OR ruito_type = ?)";
        try
        {
			//(*1)　@業務日付の取得   
			//TradingSystem.getBizDate()にて取得した業務(バッチ)日付の戻り値
			Date l_dateBizDate =
				GtlUtils.getTradingSystem().getBizDate();

            String l_strBizDate = 
                WEB3DateUtility.formatDate(l_dateBizDate, "yyyyMMdd");
            
            log.debug("業務日付: l_strBizDate = " + l_strBizDate);
            
            long l_lngAccount = l_subAccount.getAccountId();
            long l_lngSubAccount = l_subAccount.getSubAccountId();
            
            Object[] l_bindValues = new Object[8];
            l_bindValues[0] = new Long(l_lngAccount);
            l_bindValues[1] = new Long(l_lngSubAccount);
            l_bindValues[2] = ProductTypeEnum.RUITO;
            l_bindValues[3] = l_strBizDate;
            l_bindValues[4] = OrderTypeEnum.RUITO_BUY;
            l_bindValues[5] = OrderTypeEnum.RUITO_SELL;
            l_bindValues[6] = RuitoTypeEnum.CHUUKOKU_FUND;
            l_bindValues[7] = RuitoTypeEnum.MMF;

            // パラメータで検索条件データコンテナを作成する。              
            log.debug("account_id = " + l_lngAccount + 
                " AND sub_account_id = " + l_lngSubAccount + 
                " AND product_type = " + ProductTypeEnum.RUITO.intValue() +
                " AND biz_date >= " + l_strBizDate +
                " AND (order_type = " + OrderTypeEnum.RUITO_BUY +
                " OR order_type = " + OrderTypeEnum.RUITO_SELL + ") AND " +
                "(ruito_type = " + RuitoTypeEnum.CHUUKOKU_FUND + 
                " OR ruito_type = " + RuitoTypeEnum.MMF + ")");
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_strSortCond,
                    null,
                    l_bindValues);
            
            //注文単位オブジェクト
            int l_intCount = l_lisRows.size();
            RuitoOrderUnitParams[] l_ruitoOrderUnitParams = 
                    new RuitoOrderUnitParams[l_intCount];
            l_lisRows.toArray(l_ruitoOrderUnitParams);  
             
            l_lisReturnOrderUnit = new ArrayList();
            log.debug("order unit count = " + l_intCount);
            
            for (int i = 0; i < l_intCount; i++)
            {
                log.debug("OrderUnitId = " + 
                    l_ruitoOrderUnitParams[i].getOrderUnitId());
               
                l_lisReturnOrderUnit.add(
                    this.getOrderUnit(l_ruitoOrderUnitParams[i].getOrderUnitId()));
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnOrderUnit;
    }
}
@
