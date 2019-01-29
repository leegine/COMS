head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferManagementUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替管理UnitService実装クラス(WEB3AdminFXTransferManagementUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 于美麗 (中訊) 新規作成
                 : 2006/08/17 鈴木 (SCS) モデルNo.610対応
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FxTransStatusOperationDivDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX振替管理UnitServiceImpl) <BR>
 * FX振替管理UnitService実装クラス <BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor( <BR>
 * TransactionalInterceptor.TX_CREATE_NEW)を指定する。
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXTransferManagementUnitServiceImpl implements
    WEB3AdminFXTransferManagementUnitService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferManagementUnitServiceImpl.class);

    /**
     * (submit取消) <BR>
     * DBに対して振替注文の取消処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX振替管理）submit取消（Unit）」参照
     * 
     * @@param l_gftTransferStatusParams - GFT振替状況Paramsオブジェクト
     * @@param l_administrator - 管理者オブジェクト
     * @@param l_strPassword - パスワード
     * @@throws WEB3BaseException
     * @@roseuid 41C68F630238
     */
    public void submitCancel(GftTransferStatusParams l_gftTransferStatusParams,
        WEB3Administrator l_administrator, String l_strPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancel()";
        log.entering(STR_METHOD_NAME);
        
        if (l_gftTransferStatusParams == null || l_administrator == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // FXデータ制御サービスを取得
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
    
        //1.1 GFT振替状況テーブルを更新する。 
        // [引数] 
        // GFT振替状況Params： GFT振替状況行オブジェクト 
        // 更新後振替状況区分： 3（取消） 
        // 更新者コード： 管理者.管理者コード 
        l_fxDataControlService.updateGFTTransferStatus(
            l_gftTransferStatusParams,
            WEB3TransferStatusDivDef.CANCEL,
            l_administrator.getAdministratorCode());
        
        //1.2 GFT振替状況Params.処理区分が01：証券口座から為替保証金へ(FX出金)の場合、
        //　@　@または　@03：証券口座から株先証拠金へ(先OP出金)の場合、実施
        if(WEB3FxTransStatusOperationDivDef.TO_FX.equals(l_gftTransferStatusParams.getOperationDiv())
            || WEB3FxTransStatusOperationDivDef.TO_FUOP.equals(l_gftTransferStatusParams.getOperationDiv()))
        {
            // AIO注文マネージャを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            //拡張アカウントマネージャ取得する    
            WEB3GentradeAccountManager l_genAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //1.2.1 注文の取消を行う。 
            // [引数] 
            // 証券会社コード： GFT振替状況Params.証券会社コード 
            // 部店コード： GFT振替状況Params.部店コード 
            // 顧客コード： GFT振替状況Params.顧客コード 
            // 識別コード： GFT振替状況Params.識別コード 
            // 信用振替用識別コード： GFT振替状況Params.信用振替用識別コード 
            // パスワード： 
            l_web3AioOrderMgr.transferOrderCancel(
                l_gftTransferStatusParams.getInstitutionCode(),
                l_gftTransferStatusParams.getBranchCode(),
                l_gftTransferStatusParams.getAccountCode(),
                l_gftTransferStatusParams.getOrderRequestNumber(),
                l_gftTransferStatusParams.getMrgTrnOrderRequestNumber(),
				l_strPassword
                );
            
            //1.2.2 顧客のインスタンスを取得する。 
            // [引数] 
            // 証券会社コード： GFT振替状況Params.証券会社コード 
            // 部店コード： GFT振替状況Params.部店コード 
            // 口座コード： GFT振替状況Params.顧客コード 
            WEB3GentradeMainAccount l_genMainAccount = 
                l_genAccountManager.getMainAccount(
                    l_gftTransferStatusParams.getInstitutionCode(),
                    l_gftTransferStatusParams.getBranchCode(),
                    l_gftTransferStatusParams.getAccountCode()
                    );
            
            //1.2.3 補助口座を取得する。 
            // [引数] 
            // 補助口座タイプ： 1（預り金口座）
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount) l_genMainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            catch(NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.2.4 余力の再計算を行う。 
            // [引数] 
            // 補助口座： 補助口座オブジェクト
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        }       
        log.exiting(STR_METHOD_NAME);
    }
}@
