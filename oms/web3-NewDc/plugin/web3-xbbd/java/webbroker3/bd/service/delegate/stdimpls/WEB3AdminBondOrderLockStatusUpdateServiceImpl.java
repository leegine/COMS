head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockStatusUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文ロック区分更新サービスImpl(WEB3AdminBondOrderLockStatusUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 周捷(中訊) 新規作成         
*/

package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockRequest;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderLockStatusUpdateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券管理者注文ロック区分更新サービスImpl)<BR>
 * 債券管理者注文ロック区分更新サービス実装クラス
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminBondOrderLockStatusUpdateServiceImpl 
    implements WEB3AdminBondOrderLockStatusUpdateService 
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondOrderLockStatusUpdateServiceImpl.class);
    
    /**
     * @@roseuid 44E3362D02AF
     */
    public WEB3AdminBondOrderLockStatusUpdateServiceImpl() 
    {
     
    }
    
    /**
     * 債券管理者注文ロック区分更新処理を行う<BR>
     * <BR>
     * シーケンス図「（債券）管理者注文ロック区分更新」参照
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C42DA703D7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 リクエストを管理者債券注文ロック区分更新リクエストにキャストする
        WEB3AdminBondOrderLockUnlockRequest l_bondOrderLockUnlockRequest = 
            (WEB3AdminBondOrderLockUnlockRequest)l_request;
        
        //1.2 validate( )
        l_bondOrderLockUnlockRequest.validate();
        
        //1.3 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate権限(String, boolean)
        //[引数]  
        // 機@能カテゴリコード： 機@能カテゴリコード.債券（約定変更、約定取消）
        // is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_EXECUTE_MODIFY_CANCEL,
            true);
        
        //1.5 get債券注文単位By注文ID(long)
        //[引数] 
        // 注文ID： リクエストの注文IDをlongに変換したもの
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_orderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        WEB3BondOrderUnit l_bondOrderUnit = 
            l_orderManager.getBondOrderUnitByOrderId(
                Long.parseLong(l_bondOrderLockUnlockRequest.id));

        //1.6 validate部店権限(String)
        AccountManager l_accountManager = l_finApp.getAccountManager();

        try
        {
            Branch l_branch = 
                l_accountManager.getBranch(l_bondOrderUnit.getBranchId());
            String l_strBranchCode = l_branch.getBranchCode();
            l_administrator.validateBranchPermission(l_strBranchCode);
            
            //1.7 validate注文ロック区分更新可能状態(拡張債券注文単位)
            l_orderManager.validateOrderLockDivUpdatePossibleStatus(l_bondOrderUnit);
            
            //1.8 update債券注文ロック区分(注文ロック区分 : String, 拡張債券注文単位 : 拡張債券注文単位)
            //[引数] 
            // ロック区分： リクエストの注文ロック区分 
            // 拡張債券注文単位： 取得した拡張債券注文単位
            l_orderManager.updateBondOrderLockStatus(
                l_bondOrderLockUnlockRequest.orderLockDiv, 
                l_bondOrderUnit);
            
            //1.9 get債券注文単位(long)
            //[引数] 
            // 注文単位ID： 取得した拡張債券注文単位.注文単位ID
            OrderUnit l_orderUnit = 
                l_orderManager.getOrderUnit(l_bondOrderUnit.getOrderUnitId());
            l_bondOrderUnit = (WEB3BondOrderUnit)l_orderUnit;
            
            //1.10 get注文ロック解除ボタン区分(拡張債券注文単位)
            //[引数] 
            // 拡張債券注文単位： 取得した拡張債券注文単位
            WEB3AdminBondHelperService l_bondHelperService = 
                (WEB3AdminBondHelperService)Services.getService(WEB3AdminBondHelperService.class);
            String l_strOrderLockCancelButtonDiv = 
                l_bondHelperService.getOrderLockButtonDiv(l_bondOrderUnit);
            
            //1.11 get約定変更ボタン区分(拡張債券注文単位)
            //[引数] 
            // 拡張債券注文単位： 取得した拡張債券注文単位
            String l_strExecuteChangButtonDiv = 
                l_bondHelperService.getExecuteChangButtonDiv(l_bondOrderUnit);
            
            //1.12 get取消ボタン区分(拡張債券注文単位)
            //[引数] 
            // 拡張債券注文単位： 取得した拡張債券注文単位
            String l_strCancelButtonDiv = 
                l_bondHelperService.getCancelButtonDiv(l_bondOrderUnit);
            
            //1.13 createレスポンス( )
            WEB3AdminBondOrderLockUnlockResponse l_response = 
                (WEB3AdminBondOrderLockUnlockResponse)l_request.createResponse();
            
            //1.14  プロパティセット
            //get債券注文単位()から取得した注文単位により、レスポンス属性値を設定する。
            //○レスポンス.ロック解除ボタン区分＝　@get注文ロック解除ボタン区分()の戻り値
            l_response.lockDiv = l_strOrderLockCancelButtonDiv;
            
            //○レスポンス.約定変更ボタン区分　@＝　@get約定変更ボタン区分()の戻り値
            l_response.execChgDiv = l_strExecuteChangButtonDiv;
            
            //○レスポンス.取消ボタン区分　@　@　@＝　@get取消ボタン区分()の戻り値
            l_response.cancelDiv = l_strCancelButtonDiv;
            
            //○レスポンス.更新時間　@　@　@　@　@　@＝　@現在日時
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("error in l_accountManager.getBranch", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
