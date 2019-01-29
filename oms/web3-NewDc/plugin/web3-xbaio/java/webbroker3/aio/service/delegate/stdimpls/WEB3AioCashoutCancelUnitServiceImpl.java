head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消UnitServiceImpl(WEB3AioCashoutCancelUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 周勇 (中訊) 新規作成                                      
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.aio.WEB3AioCashoutTradingPowerCheckUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

import java.util.Map;

/**
 * (出金取消UnitServiceImpl)<BR>
 * 出金取消UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクション<BR>
 *     TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW)を指定する。
 */
public class WEB3AioCashoutCancelUnitServiceImpl implements WEB3AioCashoutCancelUnitService 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCancelUnitServiceImpl.class);
    
    /**
     * @@roseuid 41B03AF200DA
     */
    public WEB3AioCashoutCancelUnitServiceImpl() 
    {
     
    }
    
    /**
     * 出金取消処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込問合せ）出金取消」 参照
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param String - パスワード
     * @@throws WEB3BaseException
     * @@roseuid 4191DA5202E7
     */
    public void execute(AioOrderUnit l_orderUnit,String l_strAdminPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) get補助口座(, )
        //アイテムの定義
        //補助口座を取得する。 
        //[引数] 
        //口座ID： 注文単位.口座ID 
        //補助口座ID： 注文単位.補助口座ID 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccMana = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        long l_lngAccountId = l_orderUnit.getAccountId();
        long l_lngSubAccountId = l_orderUnit.getSubAccountId();
        
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_gentradeAccMana.getSubAccount(l_lngAccountId, l_lngSubAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2) lock口座(String, String, String)
        //アイテムの定義
        //口座をロックする。
        //[引数] 
        //証券会社コード： 補助口座から取得した証券会社コード 
        //部店コード： 補助口座から取得した部店コード 
        //口座コード： 補助口座から取得した口座コード 
        String l_institutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_branchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_accountCode = l_subAccount.getMainAccount().getAccountCode();
        l_gentradeAccMana.lockAccount(l_institutionCode, l_branchCode, l_accountCode);
        
        //1.3) 取消注文内容インスタンスを生成する。
        //[引数]
        //注文ID： 注文単位.注文ID
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_orderUnit.getOrderId());

        //1.4) 出金余力チェック更新インタセプタ( )
        //アイテムの定義
        //インタセプタを生成する。
        WEB3AioCashoutTradingPowerCheckUpdateInterceptor 
            l_aioCashoutTradingPowerCheckUpdateInterceptor =
                new WEB3AioCashoutTradingPowerCheckUpdateInterceptor();
        
        //1.5) setThreadLocalPersistenceEventInterceptor
        //アイテムの定義
        //インタセプタをセットする。
        //[引数] 
        //出金余力チェック更新インタセプタ：　@生成した出金余力チェック更新インタセプタ 
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashoutTradingPowerCheckUpdateInterceptor);
            
            
        //1.6ログインタイプ属性から、取引パスワード設定を属性値を取得する
		OpLoginSecurityService l_securityService = (OpLoginSecurityService)
			Services.getService(OpLoginSecurityService.class);
		OpLoginAdminService l_admService = (OpLoginAdminService) Services.getService(
			OpLoginAdminService.class);
        
		//LoginInfo->LoginType->LoginTypeAttribute 
		LoginInfo l_loginInfo = l_securityService.getLoginInfo();
		Map l_mapAttributes = l_admService.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());
		
		MainAccount l_mainAccount = l_subAccount.getMainAccount();
		
		//取引パスワード設定を取得する
		String l_strAttribute =
			(String) l_mapAttributes.get(
				WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV);

		String l_strPassword = null;
		// ログインパスワードの場合
		if(WEB3TradingPwdEnvDef.DEFAULT.equals(l_strAttribute))
		{
			l_strPassword = l_strAdminPassword;
		}
		// 取引パスワードの場合
		else if(WEB3TradingPwdEnvDef.USE_TRADING_PWD.equals(l_strAttribute))
		{
			WEB3Crypt l_web3Crypt = new WEB3Crypt();
			l_strPassword = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());
		}
		log.debug("取引パスワード設定 = " + l_strAttribute);
	               
        //1.7) submitCancelOrder(SubAccount, CancelOrderSpec, 
        //論理ビュー::java::lang::String, boolean)(AIO注文マネージャ::submitCancelOrder)
        //アイテムの定義
        //取消を実行する。 
        //[引数] 
        //補助口座：　@get補助口座（）の戻り値 
        //取消注文内容：　@取消注文内容オブジェクト 
        //取引パスワード設定 == ”DEFAULT” の場合、引数.パスワード 
		//取引パスワード設定 == ”取引パスワード使用” の場合、顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの 
        //isSkip発注審査：　@true
                       
        OrderSubmissionResult l_submitCancelOrderResult = 
            l_aioOrderManager.submitCancelOrder(
                l_subAccount, 
                l_cancelOrderSpec,
                l_strPassword,
                true);
        
        if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("submitCancelOrder Error" +
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }  
        
        //1.8)  validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 
        //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
        //アイテムの定義
        //余力の更新をする。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //注文内容インタセプタ： null 
        //注文内容： null 
        //注文種別： 1001（出金注文） 
        //余力更新フラグ： true 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

        l_service.reCalcTradingPower(l_gentradeSubAccount);

        log.exiting(STR_METHOD_NAME);
    }
}@
