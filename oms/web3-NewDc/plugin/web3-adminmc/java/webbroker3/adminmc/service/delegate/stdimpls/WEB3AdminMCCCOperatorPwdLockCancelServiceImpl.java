head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.26.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorPwdLockCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除ｻｰﾋﾞｽImpl(WEB3AdminMCCCOperatorPwdLockCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 Tongwei(中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorPwdLockCancelService;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除ｻｰﾋﾞｽImpl)<BR>
 * 管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除ｻｰﾋﾞｽ実装クラス<BR>
 * 
 * @@author Tongwei
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorPwdLockCancelServiceImpl implements WEB3AdminMCCCOperatorPwdLockCancelService 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorPwdLockCancelServiceImpl.class);      

    
    /**
     * @@roseuid 4198640B0399
     */
    public WEB3AdminMCCCOperatorPwdLockCancelServiceImpl() 
    {
     
    }
    
    /**
     * CCオペレータパスワードロック解除処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸﾞ解除確認ﾘｸｴｽﾄの場合<BR> 
     * 　@－validate解除()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄの場合 <BR>
     * 　@－submit解除()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F2396003A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null; 
        
        //１）　@リクエストデータの型により、以下の通りメソッドをコールする。
        if(l_request instanceof WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest)
        {
           //○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸﾞ解除確認ﾘｸｴｽﾄの場合
           //　@－validate解除()をコールする。 
            l_response = this.validateCancel((WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest)
        {
            //○ 引数のリクエストデータが、管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄの場合 
            // 　@－submit解除()をコールする。
            l_response = this.submitCancel((WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest)l_request);
        }    
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate解除)<BR>
     * CCオペレータパスワードロック解除確認処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CCパスワードロック解除）validate解除」参照。 <BR>    
     * <BR>
     * =============================================== <BR>
     * シーケンス図 :「管理者メニュー制御（CCパスワードロック解除）validate解除」 <BR>
     * 具体位置    : 1.7 (*1) 扱者が存在しない場合（オブジェクトが取得できない場合）、例外をスローする。<BR>
     *  既存データチェック<BR>
     * 入力された扱者コード（オペレータコード）にて、扱者オブジェクトを生成する。<BR>
     * 生成できない場合、対象オペレータがないと判断し、例外をスローする。<BR>
     *  class         :  WEB3BusinessLayerException<BR>
     *  tag            :   BUSINESS_ERROR_01191        <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse
     * @@roseuid 417F2396024D
     */
    protected WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse validateCancel(WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateCancel(WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME); 
       
        //1.1 validate()
        l_request.validate();
        
        //1.2getInstanceFromログイン情報
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=true） : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR, true);  
        
        //1.4 get証券会社
        Institution l_institution = l_administartor.getInstitution();
        
        //1.5 validate部店権限(String[])
        l_administartor.validateBranchPermission(l_request.branchCode);
        
        //1.6 getTrader(long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
        try
        {
            l_finObjectManager.getTrader(l_institution, l_request.operatorCode, l_request.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            // 1.7 (*1) 扱者が存在しない場合（オブジェクトが取得できない場合）、例外をスローする。           
            log.error("扱者が存在しない場合のエラー" ,  l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException( WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);
        } 

        //1.8 createResponse( )    
        WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse l_response = (WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse)l_request.createResponse();    
        
        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (submit解除)<BR>
     * CCオペレータパスワードロック解除完了処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（CCパスワードロック解除）submit解除」参照。 <BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 :「管理者メニュー制御（CCパスワードロック解除）submit解除」<BR>
     * 具体位置    : 1.8(*1) 扱者が存在しない場合（オブジェクトが取得できない場合）、例外をスローする<BR>
     * ※ 既存データチェック<BR>
     * 入力された扱者コード（オペレータコード）にて、扱者オブジェクトを生成する。<BR>
     * 生成できない場合、対象オペレータがないと判断し、例外をスローする。<BR>
     *  class         :  WEB3BusinessLayerException<BR>
     *  tag            :   BUSINESS_ERROR_01191        <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse
     * @@roseuid 417F239700B7
     */
    protected WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse submitCancel(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitCancel(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest l_request)";
                log.entering(STR_METHOD_NAME); 
       
        //1.1 validate()
        l_request.validate();
        
        //1.2getInstanceFromログイン情報
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード（=オペレータ管理） : String, is更新（=true） : boolean)
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.ADMINMC_OPERATOR, true);  
        
        //1.4 get証券会社
        Institution l_institution = l_administartor.getInstitution();
        
        String[] l_strBranchCodes = new String[1];
        l_strBranchCodes[0] = l_request.branchCode;
        
        //1.5 validate部店権限(String[])
        l_administartor.validateBranchPermission(l_strBranchCodes);
        
        //1.6 validate取引パスワード(String)
        l_administartor.validateTradingPassword(l_request.password);
        
        //1.7getTrader(long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
        Trader l_trader = null;
        try
        {
            l_trader = l_finObjectManager.getTrader(l_institution, l_request.operatorCode, l_request.branchCode);
        }
        catch (NotFoundException l_ex)
        {
            // 1.8 (*1) 扱者が存在しない場合（オブジェクトが取得できない場合）、例外をスローする。           
            log.error("扱者が存在しない場合のエラー" ,  l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException( WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                this.getClass().getName() + STR_METHOD_NAME);
        } 
        
        // 1.9 getLoginId( )
        long l_lngLoginid = l_trader.getLoginId();
        
        // 1.10 clearBadPassowrdHistory(long)
        OpLoginAdminService l_opLoginAdminService =
            (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
        l_opLoginAdminService.clearBadPassowrdHistory(l_lngLoginid);
        
        // 1.11 enableLogin(long)
        l_opLoginAdminService.enableLogin(l_lngLoginid);
        
        //1.12 createResponse( )    
        WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse l_response = (WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse)l_request.createResponse();    
        
        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
}
@
