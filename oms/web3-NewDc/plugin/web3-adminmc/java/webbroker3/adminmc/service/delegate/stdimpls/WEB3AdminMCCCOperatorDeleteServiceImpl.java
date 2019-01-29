head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCオペレータ削除サービスImpl(WEB3AdminMCCCOperatorDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23 範慧琴 (中訊) 新規作成
*/


package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderPK;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorDeleteService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteResponse;

/**
 * (管理者メニュー制御CCオペレータ削除サービスImpl)<BR>
 * 管理者メニュー制御CCオペレータ削除サービス実装クラス<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 * <BR>
 */
public class WEB3AdminMCCCOperatorDeleteServiceImpl implements WEB3AdminMCCCOperatorDeleteService 
{
    
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorDeleteServiceImpl.class);   

    /**
     * @@roseuid 4198640C0399
     */
    public WEB3AdminMCCCOperatorDeleteServiceImpl() 
    {
     
    }
    
    /**
     * CCオペレータ削除処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除確認ﾘｸｴｽﾄの場合 <BR>
     * 　@－validate扱者()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除完了ﾘｸｴｽﾄの場合 <BR>
     * 　@－submit扱者()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F789F027F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCCCOperatorDeleteConfirmRequest)
        {
            
            l_response = this.validateTrader((WEB3AdminMCCCOperatorDeleteConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminMCCCOperatorDeleteCompleteRequest)
        {
            l_response = this.submitTrader((WEB3AdminMCCCOperatorDeleteCompleteRequest)l_request);           
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate扱者)<BR>
     * CCオペレータ削除確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CC削除）validate扱者」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 :「管理者メニュー制御（CC削除）validate扱者」<BR>
     * 具体位置    :1.7 (*1) 扱者が存在しない場合（オブジェクトが取得できない場合）、例外をスローする。<BR>
     * ※ 既存データチェック<BR>
     * 入力された扱者コード（オペレータコード）にて、扱者オブジェクトを生成する。<BR>
     * 生成できない場合、削除対象オペレータがないと判断し、例外をスローする。<BR>
     * class         :  WEB3BusinessLayerException<BR>
     *  tag            :  BUSINESS_ERROR_01191         <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除確認ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmResponse
     * @@roseuid 417F789F0281
     */
    protected WEB3AdminMCCCOperatorDeleteConfirmResponse validateTrader(WEB3AdminMCCCOperatorDeleteConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateTrader(WEB3AdminMCCCOperatorDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=true） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,true);
        
        //1.4 get証券会社( )
        Institution l_institution = l_web3Administrator.getInstitution();
                
        //1.5 validate部店権限(String)
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        //1.6 getTrader()
        //1.7 (*1) 扱者が存在しない場合（オブジェクトが取得できない場合）、例外をスローする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        try
        {        
            l_gentradeFinObjectManager.getTrader(l_institution, l_request.operatorCode, l_request.branchCode);        
        }
        catch (NotFoundException l_ex)
        {
            log.error("扱者が存在しない場合のエラー。", l_ex);
            log.exiting(STR_METHOD_NAME);            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);              
        }
                
        //1.8 createResponse()
        WEB3AdminMCCCOperatorDeleteConfirmResponse l_response = (WEB3AdminMCCCOperatorDeleteConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit扱者)<BR>
     * CCオペレータ削除完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CC削除）submit扱者」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 :「管理者メニュー制御（CC削除）submit扱者」<BR>
     * 具体位置    :1.8 (*1) 扱者が存在しない場合（オブジェクトが取得できない場合）、例外をスローする。<BR>
     * ※ 既存データチェック<BR>
     * 入力された扱者コード（オペレータコード）にて、扱者オブジェクトを生成する。<BR>
     * 生成できない場合、削除対象オペレータがないと判断し、例外をスローする。<BR>
     * class         :  WEB3BusinessLayerException<BR>
     *  tag            :  BUSINESS_ERROR_01191         <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除完了ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteResponse
     * @@roseuid 417F789F0283
     */
    protected WEB3AdminMCCCOperatorDeleteCompleteResponse submitTrader(WEB3AdminMCCCOperatorDeleteCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitTrader(WEB3AdminMCCCOperatorDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=true） : boolean)
        l_web3Administrator.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR,true);
        
        //1.4 get証券会社( )
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.5 validate部店権限(String)
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        //1.6 validate取引パスワード(String)
        l_web3Administrator.validateTradingPassword(l_request.password);

        //1.7 getTrader()
        //1.8 (*1) 扱者が存在しない場合（オブジェクトが取得できない場合）、例外をスローする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        Trader l_trader = null;
        try
        {        
            l_trader = l_gentradeFinObjectManager.getTrader(l_institution, l_request.operatorCode, l_request.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("扱者が存在しない場合のエラー。", l_ex);
            log.exiting(STR_METHOD_NAME);            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);              
        } 
        
        //1.9 getLoginId()
        long l_lngLoginId = l_trader.getLoginId();    
        
        OpLoginAdminService l_opLoginAdminService =
                    (OpLoginAdminService)Services.getService(OpLoginAdminService.class); 
        
        //1.10 removeLogin(long)           
        l_opLoginAdminService.removeLogin(l_lngLoginId); 
        
        //1.11 setLoginAttributes(long, Map)
        l_opLoginAdminService.setLoginAttributes(l_lngLoginId, null);     
        
        //1.12 getTraderId( )
        long l_traderId = l_trader.getTraderId();  
        
        //1.13 doDeleteQuery
        try
        {        
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteQuery(new TraderPK(l_traderId));
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.17 createResponse( )
        WEB3AdminMCCCOperatorDeleteCompleteResponse l_reponse = (WEB3AdminMCCCOperatorDeleteCompleteResponse)l_request.createResponse();            
 
        log.exiting(STR_METHOD_NAME);
        return l_reponse;
    }
}
@
