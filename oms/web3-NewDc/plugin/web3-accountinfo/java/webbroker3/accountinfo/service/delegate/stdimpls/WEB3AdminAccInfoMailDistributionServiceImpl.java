head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客まさ情報案内メール配信指示サービスImpl(WEB3AdminAccInfoMailDistributionServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 劉江涛 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoMailDistribution;
import webbroker3.accountinfo.data.InformationMailRequestPK;
import webbroker3.accountinfo.data.InformationMailRequestRow;
import webbroker3.accountinfo.message.WEB3AccInfoMailDistributionInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailDistributionService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客まさ情報案内メール配信指示サービスImpl)<BR>
 * 管理者お客まさ情報案内メール配信指示サービス実装クラス<BR>
 * @@author 劉江涛
 * @@version 1.0 
 */

public class WEB3AdminAccInfoMailDistributionServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMailDistributionService
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailDistributionServiceImpl.class);
        
    public WEB3AdminAccInfoMailDistributionServiceImpl()
    {
        
    }
    /**
     * 案内メール配信指示処理を実施する。<BR>
     *<BR>
     *１）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     *<BR>
     *○ 引数のリクエストデータが、管理者お客様情報案内メール配信指示リクエストの場合<BR> 
　@   * －get配信指示画面()をコールする。 <BR>
     *<BR>
     *○ 引数のリクエストデータが、管理者お客様情報案内メール配信指示確認リクエストの場合<BR>
　@   * －validate配信指示()をコールする。<BR> 
     *<BR>
     *○ 引数のリクエストデータが、管理者お客様情報案内メール配信指示完了リクエストの場合<BR>
　@   * －submit配信指示()をコールする。<BR>
     *<BR>
     *○ 引数のリクエストデータが、管理者お客様情報案内メール配信指示取消確認リクエストの場合<BR>
　@   *  －validate配信指示()をコールする。<BR>
     *<BR>
     *○ 引数のリクエストデータが、管理者お客様情報案内メール配信指示取消完了リクエストの場合<BR>
　@   *  －submit配信指示()をコールする。<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //引数のリクエストデータが、管理者お客様情報案内メール配信指示リクエストの場合
        if (l_request instanceof WEB3AdminAccInfoMailDistributionRequest)
        {
            
            l_response = this.getMailDistributionScreen((WEB3AdminAccInfoMailDistributionRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報案内メール配信指示確認リクエストの場合
        else if (l_request instanceof WEB3AdminAccInfoMailDistributionConfirmRequest)
        {
            
            l_response = this.validateMailDistribution((WEB3AdminAccInfoMailDistributionConfirmRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報案内メール配信指示完了リクエストの場合
        else if (l_request instanceof WEB3AdminAccInfoMailDistributionCompleteRequest)
        {
            
            l_response = this.submintMailDistribution((WEB3AdminAccInfoMailDistributionCompleteRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報案内メール配信指示取消確認リクエストの場合
        else if (l_request instanceof WEB3AdminAccInfoMailDistributionCancelConfirmRequest)
        {
            
            l_response = this.validateMailDistributionCancle((WEB3AdminAccInfoMailDistributionCancelConfirmRequest) l_request);
        }
        
        //数のリクエストデータが、管理者お客様情報案内メール配信指示取消完了リクエストの場合
        else if (l_request instanceof WEB3AdminAccInfoMailDistributionCancelCompleteRequest)
        {
            
            l_response = this.submitMailDistributionCancle((WEB3AdminAccInfoMailDistributionCancelCompleteRequest) l_request);
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (get配信指示画面)<BR>
     * 案内メール配信指示画面表示処理を行う。<BR> 
     *<BR>
     *シーケンス図 <BR>
     *「お客様情報（案内メール配信）get配信指示画面」参照。<BR>
     * @@param l_request - 管理者お客様情報案内メール配信指示リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA301D8
     */
    protected WEB3AdminAccInfoMailDistributionResponse getMailDistributionScreen(WEB3AdminAccInfoMailDistributionRequest l_request)throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getMailDistributionScreen(WEB3AccInfoMailDistributionRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2) validate権限(機@能カテゴリコード（=メール情報） : String, is更新（=true） : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.3) is全部店許可( )
        boolean l_blnIsAllBranchsPermission = l_administrator.isAllBranchsPermission();
        
        //1.4) 全部店許可の管理者でない場合（is全部店許可() == false）、例外をスローする
        if(!l_blnIsAllBranchsPermission)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 全部店許可の管理者でない場合は、操作不可");
        }
        
        //1.5) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.6) get最新履歴(String)
        WEB3AccInfoMailDistribution l_AccInfoMailDistribution = WEB3AccInfoMailDistribution.getLastAction(l_strInstitutionCode);
        
        //1.8)管理者お客様情報案内メール配信指示レスポンス(WEB3GenRequest)
        WEB3AdminAccInfoMailDistributionResponse l_response =
            (WEB3AdminAccInfoMailDistributionResponse) l_request.createResponse();
            
        //1.7)  案内メール配信指示の最新履歴が取得できた場合（get最新履歴() != null）
        if(l_AccInfoMailDistribution != null)
        {
            //1.7.1) 案内メール配信指示情報( )
            WEB3AccInfoMailDistributionInfo l_AccInfoMailDistributionInfo = new WEB3AccInfoMailDistributionInfo();
            
            //1.7.2)get案内メール配信指示ＩＤ( )
            long l_lngInformationMailRequestId = l_AccInfoMailDistribution.getInformationMailRequestId();
            
            //1.7.3)get配信指示日時( )
            Date l_datRequestTimestamp = l_AccInfoMailDistribution.getRequestTimestamp();
            
            //1.7.4)get配信顧客数( )
            long l_lngAccountCount = l_AccInfoMailDistribution.getAccountCount();
            
            //1.7.5)is全顧客( )
            boolean l_blnisAllAccountFlag = l_AccInfoMailDistribution.isAllAccount();
            
            //1.7.6)get案内メール件名( )
            String l_strMailSubject = l_AccInfoMailDistribution.getMailSubject();
            
            //1.7.7)get更新者コード( )
            String l_strLastUpdater = l_AccInfoMailDistribution.getLastUpdater();
            
            //1.7.8)is配信済( )
            boolean l_blnisMailRequestStatus = l_AccInfoMailDistribution.isMailRequestStatus();
            
            //1.7.9)プロパティセット
            //　@ＩＤ：　@get案内メール配信指示ＩＤ()
            l_AccInfoMailDistributionInfo.id = "" + l_lngInformationMailRequestId;
            
            //配信指示日時：　@get配信指示日時()
            l_AccInfoMailDistributionInfo.distributionRequestDate = l_datRequestTimestamp;
            
            //配信顧客数：　@get配信顧客数()
            l_AccInfoMailDistributionInfo.distributionAccountNumber = "" + l_lngAccountCount;
            
            //全顧客フラグ：　@is全顧客()
            l_AccInfoMailDistributionInfo.allAccountFlag = l_blnisAllAccountFlag;
            
            //件名：　@get案内メール件名()
            l_AccInfoMailDistributionInfo.mailSubject = l_strMailSubject;
            
            //更新者コード：　@get更新者コード()
            l_AccInfoMailDistributionInfo.updaterCode = l_strLastUpdater;
            
            //　@配信指示フラグ：最新履歴が取得出来た場合、is配信済()の戻り値
            l_response.requestFlag = l_blnisMailRequestStatus;
            
            //　@案内メール配信指示情報：最新履歴が取得出来た場合、生成した案内メール配信指示情報オブジェクト
            l_response.mailDistributionInfo = l_AccInfoMailDistributionInfo;
        }
        else
        {
            //プロパティセット 
            //以外の場合、true
            l_response.requestFlag = true;
            //以外の場合、null
            l_response.mailDistributionInfo = null;
        }   
                    
        //　@送信メール区分：　@案内メール配信指示.案内メール識別ＩＤ
        l_response.sendMailDiv = WEB3AccInfoMailDistribution.SENDMAILDIV;
        //識別ＩＤ：　@案内メール配信指示.送信メール区分
        l_response.discernId = WEB3AccInfoMailDistribution.DISCERNMENTID;
        
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    /**
     * (validate配信指示)<BR>
     * 案内メール配信指示確認処理を行う。<BR>
     *<BR>
     *シーケンス図 <BR>
     *「お客様情報（案内メール配信）validate配信指示」参照。<BR>
     * @@param l_request - :管理者お客様情報案内メール配信指示確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA302D8 
     */
    protected WEB3AdminAccInfoMailDistributionConfirmResponse validateMailDistribution(WEB3AdminAccInfoMailDistributionConfirmRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMailDistribution(WEB3AccInfoMailDistributionConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //validate( )
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate権限(機@能カテゴリコード（=メール情報） : String, is更新（=true） : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.3) is全部店許可( )
        boolean l_blnAllBranchsPermission = l_administrator.isAllBranchsPermission();
        if (!l_blnAllBranchsPermission)
        {
         
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 全部店許可の管理者でない場合は、操作不可");     
        }
        
        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //get最新履歴(証券会社コード : String)
        WEB3AccInfoMailDistribution l_strLastAction = WEB3AccInfoMailDistribution.getLastAction(l_strInstitutionCode);
        if (l_strLastAction != null)
        {
            //is配信済( )
            boolean l_blnMailRequestStatus = l_strLastAction.isMailRequestStatus();
            if (!l_blnMailRequestStatus)
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01381, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 最新履歴が配信未済の場合は、配信指示不可");                 
            }             
        }
        //calc配信顧客数(is全顧客 : boolean)
        long l_lngCalcAccountCount = WEB3AccInfoMailDistribution.calcAccountCount(l_strInstitutionCode, l_request.allAccountFlag);
        
        //1.8createResponse()
        WEB3AdminAccInfoMailDistributionConfirmResponse l_response = 
            (WEB3AdminAccInfoMailDistributionConfirmResponse) l_request.createResponse(); 
            
        // 配信顧客数：　@get配信顧客数() 
        l_response.distributionAccountNumber = "" + l_lngCalcAccountCount;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (submit配信指示)<BR>
     * 案内メール配信指示完了処理を行う。<BR>
     *<BR>
     *シーケンス図<BR> 
     *「お客様情報（案内メール配信）submit配信指示」参照。<BR>
     * @@param l_request - 管理者お客様情報案内メール配信指示完了リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA303D8 
     */
    protected WEB3AdminAccInfoMailDistributionCompleteResponse submintMailDistribution(WEB3AdminAccInfoMailDistributionCompleteRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submintMailDistribution(WEB3AccInfoMailDistributionCompleteRequest)";
        log.entering(STR_METHOD_NAME );
        
        //1.1)validate()
        l_request.validate();
        
        //1.2getInstanceFromログイン情報( )
        
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
       
        //1.3validate権限(String, boolean)        
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4is全部店許可( )        
        boolean l_blnAllBranchsPermission = l_administrator.isAllBranchsPermission();
        
        //1.5(*) 全部店許可の管理者でない場合（is全部店許可() == false）、例外をスローする。
        if (!l_blnAllBranchsPermission)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 全部店許可の管理者でない場合は、操作不可");   
        }
        
        //1.6get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.7validate取引パスワード(パスワード : String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.8get最新履歴(証券会社コード : String)
        WEB3AccInfoMailDistribution l_mailDistribution = WEB3AccInfoMailDistribution.getLastAction(l_strInstitutionCode);
        
        //1.9(*) 案内メール配信指示の最新履歴が取得できた場合（get最新履歴() != null）
        if (l_mailDistribution != null)
        {
            if (!l_mailDistribution.isMailRequestStatus())
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01381, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 最新履歴が配信未済の場合は、配信指示不可");  
            }
        }
        
        //1.10get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
        //1.11createNew案内メール配信指示(証券会社コード : String, is全顧客 : boolean, 更新者コード : String)
        WEB3AccInfoMailDistribution l_accInfoMailDistribution = 
            WEB3AccInfoMailDistribution.createNewMailDistribution(l_strInstitutionCode, l_request.allAccountFlag, l_strAdministratorCode);
        
        //1.12getDataSourceObject( )
        InformationMailRequestRow l_informationMailRequestRow = 
            (InformationMailRequestRow)l_accInfoMailDistribution.getDataSourceObject();
            
        //1.13doInsertQuery(arg0（=案内メール配信指示行） : Row)
        try
        {
                
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_informationMailRequestRow);
        }
        catch (DataFindException l_ex)
        {
                
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました! ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
                
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        
        //1.14createResponse( )
        WEB3AdminAccInfoMailDistributionCompleteResponse l_response = 
            (WEB3AdminAccInfoMailDistributionCompleteResponse)l_request.createResponse();
        
        return l_response;
    }
    /**
     * (validate配信取消)<BR>
     * 案内メール配信指示取消確認処理を行う。<BR> 
     *<BR>
     *シーケンス図<BR> 
     *「お客様情報（案内メール配信）validate配信取消」参照。<BR> 
     * @@param l_request - 管理者お客様情報案内メール配信指示取消確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA304D8
     */
    protected WEB3AdminAccInfoMailDistributionCancelConfirmResponse validateMailDistributionCancle(WEB3AdminAccInfoMailDistributionCancelConfirmRequest l_request)throws WEB3BaseException
    { 
        final String STR_METHOD_NAME = " validateMailDistributionCancle(WEB3AccInfoMailDistributionCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate権限(機@能カテゴリコード（=メール情報） : String, is更新（=true） : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4) is全部店許可( )
        boolean l_blnAllBranchsPermission = l_administrator.isAllBranchsPermission();
        if (!l_blnAllBranchsPermission)
        {
         
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 全部店許可の管理者でない場合は、操作不可");   
        }
        //get証券会社コード( )
        l_administrator.getInstitutionCode();
        
        //案内メール配信指示(案内メール配信指示ＩＤ : long)
        WEB3AccInfoMailDistribution l_accInfoMailDistribution = null;
        try
        {
            l_accInfoMailDistribution =
                new WEB3AccInfoMailDistribution(Long.parseLong(l_request.id));
        }
        catch(NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);  
        }

        //is配信済( )
        if (l_accInfoMailDistribution.isMailRequestStatus())
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01382, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 最新履歴が配信済の場合は、配信取消不可");
        }
        
        WEB3AdminAccInfoMailDistributionCancelConfirmResponse l_response = 
            (WEB3AdminAccInfoMailDistributionCancelConfirmResponse) l_request.createResponse();  
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * (submit配信取消)<BR>
     * 案内メール配信指示取消完了処理を行う。<BR> 
     *<BR>
     *シーケンス図 <BR>
     *「お客様情報（案内メール配信）submit配信取消」参照。<BR> 
     * @@param l_request - 管理者お客様情報案内メール配信指示取消完了リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA305D8
     */
    protected WEB3AdminAccInfoMailDistributionCancelCompleteResponse submitMailDistributionCancle(WEB3AdminAccInfoMailDistributionCancelCompleteRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitMailDistributionCancle(WEB3AccInfoMailDistributionCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate権限(機@能カテゴリコード（=メール情報） : String, is更新（=true） : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.MAILINFO, true);
        
        //1.4) is全部店許可( )
        boolean l_blnAllBranchsPermission = l_administrator.isAllBranchsPermission();
        if(!l_blnAllBranchsPermission)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 全部店許可の管理者でない場合は、操作不可"); 
        }
        
        //1.6)get証券会社コード( )
        l_administrator.getInstitutionCode();
        
        //1.7)validate取引パスワード(パスワード : String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //1.8)案内メール配信指示(long)
        WEB3AccInfoMailDistribution l_accInfoMailDistribution = null;
        try
        {
            l_accInfoMailDistribution =
                new WEB3AccInfoMailDistribution(Long.parseLong(l_request.id));
        }
        catch(NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);  
        }

            
       //1.9)is配信済( )
       boolean l_blnisMailRequestStatus = l_accInfoMailDistribution.isMailRequestStatus();
       //1.10)
        if(l_blnisMailRequestStatus)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01382, 
                this.getClass().getName() + STR_METHOD_NAME,
                " 最新履歴が配信済の場合は、配信取消不可");
        }
        //1.11)get案内メール配信指示ＩＤ( )
        long l_lngRequestId = l_accInfoMailDistribution.getInformationMailRequestId();
        
        //1.12)
        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            InformationMailRequestPK l_informationMailRequesPK = new InformationMailRequestPK(l_lngRequestId);
            l_queryProcessor.doDeleteQuery(l_informationMailRequesPK);
        }
        catch (DataFindException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.13)
        WEB3AdminAccInfoMailDistributionCancelCompleteResponse l_response = 
        (WEB3AdminAccInfoMailDistributionCancelCompleteResponse) l_request.createResponse();  
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}@
