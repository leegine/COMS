head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegiServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス登録サービスImpl(WEB3AdminSrvRegiServiceRegiServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SrvUseKeyTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse;
import webbroker3.srvregi.message.WEB3SrvRegiExecKey;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceRegiService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者サービス登録サービスImpl)<BR>
 * サービス利用管理者サービス登録サービス実装クラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceRegiServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceRegiService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceRegiServiceImpl.class);

    /**
     * @@roseuid 416F392B00BB
     */
    public WEB3AdminSrvRegiServiceRegiServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者サービス登録処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate登録( )または、 <BR>
     * submit登録( )メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F510070150
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);
        
        //1.1:<l_request instanceof サービス利用管理者サービス登録確認リクエストの場合>
        if (l_request instanceof WEB3AdminSrvRegiServiceRegistConfirmRequest)
        {
            //1.1.1:validateサービス登録(サービス利用管理者サービス登録確認リクエスト)
            log.debug(" WEB3AdminSrvRegiServiceRegistConfirmRequest ");
            WEB3AdminSrvRegiServiceRegistConfirmResponse l_serviceRegistConfirmResponse = validateServiceRegi(
                (WEB3AdminSrvRegiServiceRegistConfirmRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_serviceRegistConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiServiceRegistCompleteRequest)
        //1.2:<l_request instanceof サービス利用管理者サービス登録完了リクエストの場合>
        {
            //1.2.1:submitサービス登録(サービス利用管理者サービス登録完了リクエスト)
            log.debug(" WEB3AdminSrvRegiServiceRegistCompleteRequest ");
            WEB3AdminSrvRegiServiceRegistCompleteResponse l_serviceRegistCompleteResponse = submitServiceRegi(
                (WEB3AdminSrvRegiServiceRegistCompleteRequest)l_request);
            
            log.exiting(STR_METHOD_NAME);
            return l_serviceRegistCompleteResponse;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }               
    }
    
    /**
     * (validateサービス登録)<BR>
     * サービス利用管理者サービス登録審査処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）サービス登録審査」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス登録審査」): <BR>
     *         1.6. isDIR管理者( )<BR>
     *        管理者オブジェクト.isDIR管理者( )==falseの場合、<BR>
     *        例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00986<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス登録審査」): <BR>
     *         1.7. getSrvMaster(String, String, boolean)<BR>
     *          サービスマスターオブジェクトが取得できた場合、<BR>
     *          例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01989<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F510270140
     */
    protected WEB3AdminSrvRegiServiceRegistConfirmResponse validateServiceRegi(WEB3AdminSrvRegiServiceRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateServiceRegi(WEB3AdminSrvRegiServiceRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1:validate( )
        l_request.validate();
        
        //1.3:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE,true);
        
        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:isDIR管理者( )
        if (!l_admin.isDirAdministrator())
        {
            log.debug("isDIR管理者()=false");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00985, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.7:getサービスマスター(String, String, boolean)
        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        
        WEB3SrvRegiServiceMaster l_srvMasterGetted = null;
        
        try
        {
            l_srvMasterGetted = l_srvInfoManagement.getSrvMaster(
                l_strInstitutionCode, 
                l_request.serviceDiv, 
                false);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug("1.7:getサービスマスター(String, String, boolean) = null");
            
            log.debug(l_ex.getMessage(), l_ex);
        }
        
        if ( l_srvMasterGetted != null)
        {
            log.debug("1.7:getサービスマスター(String, String, boolean) != null");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01989, 
                this.getClass().getName() + STR_METHOD_NAME);                            
        }
        
        //1.8:createNewサービスマスター(証券会社コード : String, サービス区分 : String, 申込区分 : String)
        WEB3SrvRegiServiceMaster l_srvMaster =  
            WEB3SrvRegiServiceMaster.createNewSrvMaster(
                l_strInstitutionCode, l_request.serviceDiv, l_request.applyDiv);
        
        //1.9:get確認メール情報( )
        WEB3GentradeMailInfo l_comfirmMailInfo = l_srvMaster.getConfirmMailInfo();
        
        //1.10:get契約期限メール情報( )
        WEB3GentradeMailInfo l_endMailDiv = l_srvMaster.getEndMaiDivInfo();
        
        //1.11:createレスポンス( )
        WEB3AdminSrvRegiServiceRegistConfirmResponse l_response = 
            (WEB3AdminSrvRegiServiceRegistConfirmResponse)l_request.createResponse();
        
        //1.12:<レスポンス・セット>
        if (l_comfirmMailInfo == null)
        {   
            log.debug("l_comfirmMailInfo == null");
            
            l_response.confirmMailFrom = null;
            l_response.confirmMailSubject = null;
            l_response.confirmMailHeader = null;
            l_response.confirmMailBody = null;
            l_response.confirmMailFooter = null;
        }
        else
        {
            log.debug("l_comfirmMailInfo != null");
            
            l_response.confirmMailFrom = l_comfirmMailInfo.getMailSender();
            l_response.confirmMailSubject = l_comfirmMailInfo.getSubject();
            l_response.confirmMailHeader = l_comfirmMailInfo.getMailHeader();
            l_response.confirmMailBody = l_comfirmMailInfo.getMailText();
            l_response.confirmMailFooter = l_comfirmMailInfo.getMailFooter();
        }
              
        if (l_endMailDiv == null)
        {   
            log.debug("l_endMailDiv == null");
            
            l_response.noticeMailFrom = null;
            l_response.noticeMailSubject = null;
            l_response.noticeMailHeader = null;
            l_response.noticeMailBody = null;
            l_response.noticeMailFooter = null;
        }
        else
        {
            log.debug("l_endMailDiv != null");
            
            l_response.noticeMailFrom = l_endMailDiv.getMailSender();
            l_response.noticeMailSubject = l_endMailDiv.getSubject();
            l_response.noticeMailHeader = l_endMailDiv.getMailHeader();
            l_response.noticeMailBody = l_endMailDiv.getMailText();
            l_response.noticeMailFooter = l_endMailDiv.getMailFooter();
        }        
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_response;
    }
    
    /**
     * (submitサービス登録)<BR>
     * サービス利用管理者サービス登録処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）サービス登録」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス登録」): <BR>
     *         1.7 isDIR管理者( )  <BR>
     *         管理者オブジェクト.isDIR管理者( )==falseの場合、<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00986<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス登録」): <BR>
     *         1.8 getSrvMaster(String, String, boolean)<BR>
     *         サービスマスターオブジェクトが取得できた場合、<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01989<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5108002D7
     */
    protected WEB3AdminSrvRegiServiceRegistCompleteResponse submitServiceRegi(WEB3AdminSrvRegiServiceRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitServiceRegi(WEB3AdminSrvRegiServiceRegistCompleteReques)";
        log.entering(STR_METHOD_NAME);

        //1.1:validate( )
        l_request.validate();
        
        //1.3:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE,true);        
        
        //1.5:validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.6:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7:isDIR管理者( )
        if (!l_admin.isDirAdministrator())
        {
            log.debug("1.7:isDIR管理者()=false");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00985, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.8:getサービスマスター(String, String, boolean)
        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();
       
        WEB3SrvRegiServiceMaster l_srvMasterGetted = null;
        
        try
        {
            l_srvMasterGetted = l_srvInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug("1.8:getサービスマスター(String, String, boolean) = null");
    
            log.debug(l_ex.getMessage(), l_ex);
        }
        
        if (l_srvMasterGetted != null)
        {
            log.debug("1.8:getサービスマスター(String, String, boolean) != null");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01989, 
                this.getClass().getName() + STR_METHOD_NAME);                            
        }
        
        //1.9:createNewサービスマスター(証券会社コード : String, サービス区分 : String, 申込区分 : String)
        WEB3SrvRegiServiceMaster l_srvMaster =  
            WEB3SrvRegiServiceMaster.createNewSrvMaster(
                l_strInstitutionCode, l_request.serviceDiv, l_request.applyDiv);        
        
        //1.10:setサービス名称(サービス名称 : String)
        l_srvMaster.setSrvName(l_request.serviceName);
        
        //1.11:setサービス利用URL(String)
        l_srvMaster.setSrvUrl(l_request.url);
        
        //1.12:saveNewサービスマスター( )
        l_srvMaster.saveNewSrvMaster();
        
        //1.13:<リクエストデータ.摘要 != nullの場合>
        if (l_request.summary != null)
        {
            log.debug("1.13:<リクエストデータ.摘要 != nullの場合>");
            
            //1.13.1:createNew申込要サービス(String, String)
            WEB3SrvRegiApplicationRequiredService l_appliRequiredSrv = 
                WEB3SrvRegiApplicationRequiredService.createNewAppliRequiredSrv(
                    l_strInstitutionCode, l_request.serviceDiv);
        
            //1.13.2:set抽選設定(String)
            l_appliRequiredSrv.setLotDiv(l_request.lotteryDiv);
        
            //1.13.3:set摘要(String)
            l_appliRequiredSrv.setSummary(l_request.summary);
        
            //1.13.4:set確認メール(String)
            l_appliRequiredSrv.setStartMailDiv(l_request.confirmMailDiv);
        
            //1.13.5:set契約期限メール(String)
            l_appliRequiredSrv.setEndMailDiv(l_request.noticeMailDiv);
            
            //1.13.6:set基準手数料合計額(double)
            l_appliRequiredSrv.setMinCommAmt(0);
        
            //1.13.7: saveNew申込要サービス( )
            l_appliRequiredSrv.saveNewAppliRequiredSrv();
        }
        
        //1.14: ＜リクエストデータ.第２URL!=nullの場合＞
        if (l_request.url2 != null)
        {
            //1.14.1:createNewサービス利用キー(String, String, String)
            WEB3SrvRegiServiceUseKey l_srvUseKey = 
                WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                    l_request.serviceDiv, WEB3SrvUseKeyTypeDef.URL2);
    
            //1.14.2:setサービス利用キー(String)
            l_srvUseKey.setSrvUseKey(l_request.url2);
            
            //1.14.3:saveNewサービス利用キー( )
            l_srvUseKey.saveNewSrvUseKey();
        }
        
        //1.15:＜サービス利用キーのうち入力必須項目の登録（当手続きを５回実施する）
        //ハッシュ計算方式区分
        //1.15.1:createNewサービス利用キー(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.HASH_CAL_HOW_TO_DIV);
        //1.15.2:setサービス利用キー(String)
        l_srvUseKey.setSrvUseKey(l_request.hashCalHowToDiv);
        //1.15.3:saveNewサービス利用キー( )
        l_srvUseKey.saveNewSrvUseKey();

        //ハッシュ計算手順区分
        //1.15.1:createNewサービス利用キー(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey2 = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.HASH_CAL_ORDER_DIV);
        //1.15.2:setサービス利用キー(String)
        l_srvUseKey2.setSrvUseKey(l_request.hashCalOrderDiv);
        //1.15.3:saveNewサービス利用キー( )
        l_srvUseKey2.saveNewSrvUseKey();
         
        //送信方法@区分 
        //1.15.1:createNewサービス利用キー(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey3 = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.SEND_HOW_TO_DIV);
        //1.15.2:setサービス利用キー(String)
        l_srvUseKey3.setSrvUseKey(l_request.sendHowToDiv);
        //1.15.3:saveNewサービス利用キー( )
        l_srvUseKey3.saveNewSrvUseKey();
        
        //送信パラメータ区分
        //1.15.1:createNewサービス利用キー(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey4 = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.SEND_PARAM_DIV);
        //1.15.2:setサービス利用キー(String)
        l_srvUseKey4.setSrvUseKey(l_request.sendParamDiv);
        //1.15.3:saveNewサービス利用キー( )
        l_srvUseKey4.saveNewSrvUseKey();
         
        //暗号化顧客コード区分
        //1.15.1:createNewサービス利用キー(String, String, String)
        WEB3SrvRegiServiceUseKey l_srvUseKey5 = 
            WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                l_request.serviceDiv, WEB3SrvUseKeyTypeDef.CRYPT_ACCOUNT_CODE_DIV);
        //1.15.2:setサービス利用キー(String)
        l_srvUseKey5.setSrvUseKey(l_request.cryptAccountCodeDiv);
        //1.15.3:saveNewサービス利用キー( )
        l_srvUseKey5.saveNewSrvUseKey();

        //1.16:＜リクエストデータ.ハッシュ値一覧の件数分繰り返す＞
        if (l_request.hashList != null)
        {
            int l_intCnt = l_request.hashList.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3SrvRegiExecKey l_exceKey = l_request.hashList[i];
                //1.16.1:＜リクエストデータ.ハッシュ値一覧[n].無効区分=有効の場合＞
                if (!l_exceKey.invalidDiv)
                {
                    //1.16.1.1:createNewサービス利用キー(String, String, String)
                    WEB3SrvRegiServiceUseKey l_srvUseKeyNew = 
                        WEB3SrvRegiServiceUseKey.createNewSrvUseKey(
                            l_strInstitutionCode, 
                            l_request.serviceDiv, 
                            WEB3SrvUseKeyTypeDef.HSAH_VALUE);
                            
                    //1.16.1.2:setサービス利用キー(String)
                    l_srvUseKeyNew.setSrvUseKey(l_exceKey.key);
                    
                    //1.16.1.3:saveNewサービス利用キー( )
                    l_srvUseKeyNew.saveNewSrvUseKey();
                }
            }
        }

        
        //1.17:＜リクエストデータ.送信パラメータ一覧の件数分繰り返す＞
        if (l_request.paramList != null)
        {
            int l_intCnt = l_request.paramList.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3SrvRegiExecKey l_exceKey = l_request.paramList[i];
                //1.17.1:＜リクエストデータ.送信パラメータ一覧[n].無効区分=有効の場合＞
                if (!l_exceKey.invalidDiv)
                {
                    //1.17.1.1:createNewサービス利用キー(String, String, String)
                    WEB3SrvRegiServiceUseKey l_srvUseKeyNew = 
                        WEB3SrvRegiServiceUseKey.createNewSrvUseKey(
                            l_strInstitutionCode, 
                            l_request.serviceDiv, 
                            WEB3SrvUseKeyTypeDef.SEND_PARAM);
                            
                    //1.17.1.2:setサービス利用キー(String)
                    l_srvUseKeyNew.setSrvUseKey(l_exceKey.key);
                    
                    //1.17.1.3:saveNewサービス利用キー( )
                    l_srvUseKeyNew.saveNewSrvUseKey();
                }
            }
        }
        
        //1.15:createレスポンス( )
        WEB3AdminSrvRegiServiceRegistCompleteResponse l_response = 
            (WEB3AdminSrvRegiServiceRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME); 
        
        return l_response;
    }
}
@
