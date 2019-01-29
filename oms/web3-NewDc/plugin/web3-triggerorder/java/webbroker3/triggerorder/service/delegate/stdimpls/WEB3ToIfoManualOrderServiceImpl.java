head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToIfoManualOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP手動発注サービスImpl(WEB3ToIfoManualOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 譚漢江(中訊) 新規作成
                 : 2006/10/30 唐性峰(中訊)　@モデルNo.159
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.rlsgateway.define.WEB3RlsNotifyTypeDef;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualConfirmRequest;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP手動発注サービスImpl)<BR>
 * 先物OP手動発注サービス実装クラス<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToIfoManualOrderServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3ToIfoManualOrderService 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToIfoManualOrderServiceImpl.class);

    /**
     * @@roseuid 43F4933F007D
     */
    public WEB3ToIfoManualOrderServiceImpl() 
    {
     
    }
    
    /**
     * 先物OP手動発注サービス処理を実施する。<BR>
     * リクエストデータの型により、validate手動発注()または、<BR>
     * submit手動発注()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E9AFEB014B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3FuturesOptionsManualConfirmRequest)
        {
            l_response = this.validateManualOrder((WEB3FuturesOptionsManualConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FuturesOptionsManualCompleteRequest)
        {
            l_response = this.submitManualOrder((WEB3FuturesOptionsManualCompleteRequest) l_request);
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
     * (validate手動発注)<BR>
     * シーケンス図<BR>
     * 「(先物OP手動発注サービス)validate手動発注」<BR>
     * 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 先物OP手動発注確認リクエスト<BR>
     * @@return WEB3FuturesOptionsManualConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 43EAD39A00A5
     */
    public WEB3FuturesOptionsManualConfirmResponse validateManualOrder(
        WEB3FuturesOptionsManualConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualOrder(WEB3FuturesOptionsManualConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 （分岐フロー：管理者の場合）
        //※管理者の場合(OpLoginSecurityService.getAccountIdの
        //戻り値 == 0)の場合
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        if (l_opLoginSec.getAccountId() == 0)
        {
            //1.2.1 getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //1.2.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
            //[引数]  
            //機@能カテゴリコード：　@機@能カテゴリコード.先物OP(トリガー注文手動発注)  
            //is更新：　@true(更新あり)  
            l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_HANDLER_SEND, true);
            
            //1.2.3 validate部店権限(部店コード : String[])
            //[引数] 
            //部店コード：　@リクエストデータ.部店コード
            l_admin.validateBranchPermission(l_request.branchCode);
            
        }

        //1.3 ArrayList( )
        List l_lisManuals = new ArrayList();
        
        //1.4 (*)リクエスト.注文ID[]の要素数分loop処理
        int l_intCount = 0;
        int l_intOrderIdLength = l_request.orderId.length;
        for (int i = 0; i < l_intOrderIdLength; i++)
        {
            //1.4.1 exec手動発注(String, String, String, boolean)
            //[引数]
            //銘柄タイプ：　@リクエストデータ.銘柄タイプ
            //条件注文種別：　@リクエストデータ.条件注文種別
            //注文ＩＤ：　@注文ＩＤ[n番目の要素]
            //is更新：　@false
            //発注者ログインID：　@null
            //通知経路：　@null
            WEB3ToIfoManualOrderUnitService l_unitService =
                (WEB3ToIfoManualOrderUnitService) Services.getService(WEB3ToIfoManualOrderUnitService.class);
            
            WEB3FuturesOptionsManualUnit l_manualUnit = l_unitService.execManualOrder(
                l_request.productType,
                l_request.triggerOrderType,
                l_request.orderId[i],
                false,
                null,
                null);
            //exec手動発注の戻り値.手動発注エラーコードが"正常"の戻り値
            if (WEB3ToManualOrderErrorCodeDef.NORMAL.equals(l_manualUnit.manualOrderErrorCode))
            {
                l_intCount++;
            }
            
            //1.4.2 add(arg0 : Object)
            l_lisManuals.add(l_manualUnit);
        }
        
        //exec手動発注の戻り値.手動発注エラーコードが
        //"正常"の戻り値が1件も無かった場合、
        //「手動発注対象注文なし」の例外をthrowする。        
        if (l_intCount < 1)
        {
            log.debug("手動発注対象注文なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02393, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "手動発注対象注文なし。");
        }

        //1.5 toArray( )
        //先物OP手動発注Unitの配列を生成する。
        WEB3FuturesOptionsManualUnit[] l_manualUnits = new WEB3FuturesOptionsManualUnit[l_lisManuals.size()]; 
        l_lisManuals.toArray(l_manualUnits);
        
        //1.6 createResponse( )
        WEB3FuturesOptionsManualConfirmResponse l_response =
            (WEB3FuturesOptionsManualConfirmResponse) l_request.createResponse();
        
        //1.7 (*)プロパティセット
        //先物OP手動発注Unit＝先物OP手動発注Unitの配列
        l_response.manualUnits = l_manualUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit手動発注)<BR>
     * シーケンス図<BR>
     * 「(先物OP手動発注サービス)submit手動発注」<BR>
     * 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3FuturesOptionsManualCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 43EC92C20284
     */
    public WEB3FuturesOptionsManualCompleteResponse submitManualOrder(WEB3FuturesOptionsManualCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualOrder(WEB3FuturesOptionsManualCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //通知経路
        String l_strSubmitnotifyType = "";
        
        //1.2 （分岐フロー： 管理者の場合）
        //※管理者の場合(OpLoginSecurityService.getAccountIdの戻り値 == 0)の場合
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        if (l_opLoginSec.getAccountId() == 0)
        {
            //1.2.1 getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            //1.2.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
            //[引数]  
            //機@能カテゴリコード：　@機@能カテゴリコード.先物OP(トリガー注文手動発注)  
            //is更新：　@true(更新あり)  
            l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_HANDLER_SEND, true);
            
            //1.2.3 validate部店権限(部店コード : String[])
            //[引数] 
            //部店コード：　@リクエストデータ.部店コード
            l_admin.validateBranchPermission(l_request.branchCode);
            
            //1.2.4 validate取引パスワード(パスワード : String)
            l_admin.validateTradingPassword(l_request.password);
            //通知経路：　@管理者
            l_strSubmitnotifyType = WEB3RlsNotifyTypeDef.MANUAL_ADMIN;
            
        }
        
        //1.3 get代理入力者( )
        Trader l_trader = this.getTrader();
        //代理入力の場合
        if (l_trader != null)
        {
            //通知経路：　@オペレータ
            l_strSubmitnotifyType = WEB3RlsNotifyTypeDef.MANUAL_OPERATOR;
        }
        
        //1.4 （分岐フロー： 顧客の場合）
        //（分岐フロー： 顧客の場合（管理者でない 
        //&& 代理入力者オブジェクトも取得できない場合））
        if (l_opLoginSec.getAccountId() != 0 && l_trader == null)
        {
            //1.4.1 get口座()
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

            //1.4.2 validate取引パスワード(顧客 : 顧客, パスワード : String)
            //[引数]
            //顧客：　@getMainAccount()の戻り値
            //パスワード：　@リクエスト.暗証番号
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(l_mainAccount, l_request.password);
            
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客ログイン：取引パスワードチェックエラー");
            }
            //通知経路：　@顧客
            l_strSubmitnotifyType = WEB3RlsNotifyTypeDef.MANUAL_USER;
        }
        
        //1.5 ArrayList( )
        List l_lisManuals = new ArrayList();
        
        //1.6 (*)リクエスト.注文ID[]の要素数分loop処理
        //※リクエスト.注文ID[]の要素数分loopし、
        //　@exec手動発注の戻り値.手動発注エラーコードが
        //　@"正常"の戻り値が1件も無かった場合、
        //　@「手動発注対象注文なし」の例外をthrowする。
        int l_intCount = 0;
        int l_intOrderIdLength = l_request.orderId.length;

        //発注者ログインIDを取得する。
        Long l_lngSubmitterLoginId = new Long(l_opLoginSec.getLoginId());
        
        for (int i = 0; i < l_intOrderIdLength; i++)
        {
            //1.6.1 exec手動発注(String, String, String, boolean)
            //[引数]
            //銘柄タイプ：　@リクエストデータ.銘柄タイプ
            //条件注文種別：　@リクエストデータ.条件注文種別
            //注文ＩＤ：　@注文ＩＤ[n番目の要素]
            //is更新：　@true
            //発注者ログインID：　@OploginSecurityService.getLoginId()の戻り値
            //通知経路：　@管理者 or オペレータ or 顧客
            WEB3ToIfoManualOrderUnitService l_unitService =
                (WEB3ToIfoManualOrderUnitService) Services.getService(WEB3ToIfoManualOrderUnitService.class);
            
            WEB3FuturesOptionsManualUnit l_manualUnit = l_unitService.execManualOrder(
                l_request.productType,
                l_request.triggerOrderType,
                l_request.orderId[i],
                true,
                l_lngSubmitterLoginId,
                l_strSubmitnotifyType);
            
            //exec手動発注の戻り値.手動発注エラーコードが"正常"の戻り値
            if (WEB3ToManualOrderErrorCodeDef.NORMAL.equals(l_manualUnit.manualOrderErrorCode))
            {
                l_intCount++;
            }
            
            //1.6.2 add(arg0 : Object)
            l_lisManuals.add(l_manualUnit);
            
        }
        
        //exec手動発注の戻り値.手動発注エラーコードが
        //"正常"の戻り値が1件も無かった場合、
        //「手動発注対象注文なし」の例外をthrowする。        
        if (l_intCount < 1)
        {
            log.debug("手動発注対象注文なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02393, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "手動発注対象注文なし。");
        }

        //1.7 toArray( )
        //先物OP手動発注Unitの配列を生成する。
        WEB3FuturesOptionsManualUnit[] l_manualUnits = new WEB3FuturesOptionsManualUnit[l_lisManuals.size()]; 
        l_lisManuals.toArray(l_manualUnits);
        
        //1.8 createResponse( )
        WEB3FuturesOptionsManualCompleteResponse l_response =
            (WEB3FuturesOptionsManualCompleteResponse) l_request.createResponse();
        
        //1.9 (*)プロパティセット
        //更新時間      　@ ＝ GtlUtils.getSystemTimestamp()
        //先物OP手動発注Unit＝先物OP手動発注Unitの配列
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        l_response.manualUnits = l_manualUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
