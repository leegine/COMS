head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountChangeInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更入力サービスImpl(WEB3AdminSrvRegiAccountChangeInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerSearchCondition;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeInputService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客変更入力サービスImpl)<BR>
 * サービス利用管理者顧客変更入力サービス実装クラス<BR>                                                               
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountChangeInputServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountChangeInputService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountChangeInputServiceImpl.class);

    /**
     * @@roseuid 416F3928009C
     */
    public WEB3AdminSrvRegiAccountChangeInputServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者顧客変更入力サービス処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）顧客変更入力」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E5D5006E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);        
                              
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3AdminSrvRegiCustomerChangeInputRequest l_srvRegiCustomerChangeInputRequest = 
            (WEB3AdminSrvRegiCustomerChangeInputRequest)l_request;
        
        //1.1  validate( )
        l_srvRegiCustomerChangeInputRequest.validate();
        
        //1.3 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
                
        //1.5 証券会社コード
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //サービス情報管理
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();

        //1.6 getサービスマスター
        //サービス区分
        String l_strServiceDiv = l_srvRegiCustomerChangeInputRequest.serviceDiv;
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = 
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);
            
        //1.7 getサービス名称( )
        String l_strSrvName = l_srvRegiServiceMaster.getSrvName();
        
        //1.8 <*1>繰り返し処理
        WEB3AdminSrvRegiCustomerSearchCondition[] searchConditions = l_srvRegiCustomerChangeInputRequest.searchConditions;
        int l_intArrayLengh = searchConditions.length;
        WEB3AdminSrvRegiCustomerChangeGroup[] l_srvRegiCustomerChangeGroup = new WEB3AdminSrvRegiCustomerChangeGroup[l_intArrayLengh];
        
    
        for (int i = 0; i < l_intArrayLengh; i++)
        {
            //1.8.1 validate部店権限(String[])           
            l_admin.validateBranchPermission(l_srvRegiCustomerChangeInputRequest.searchConditions[i].branchCode);
            
            //部店コード
            String l_strBranchCode = l_srvRegiCustomerChangeInputRequest.searchConditions[i].branchCode;
            
            //顧客コード
            String l_strAccountCode = l_srvRegiCustomerChangeInputRequest.searchConditions[i].accountCode;
                       
            //拡張アカウントマネージャ
            //６桁のリクエストデータ.顧客コードから７桁の顧客コードを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
            //1.8.2  get顧客(String, String, String)  
            WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
                
            //1.8.3 getAccountCode()   
            String l_strDbAccountCode = l_mainAccount.getAccountCode();         
            
            //サービス利用申込登録サービス
            WEB3SrvRegiRegistService l_srvRegiRegistService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

            //1.8.4 getサービス申込登録(String, String, String, String, long, )
            //申込登録ID
            long l_lngApplyRegId = Long.parseLong(l_srvRegiCustomerChangeInputRequest.searchConditions[i].applyRegId);
            //サービス申込登録
            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = 
                l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode, 
                l_strServiceDiv, l_strDbAccountCode, l_lngApplyRegId, false);
            
            //1.8.5 サービス利用管理者顧客変更一覧行()
            l_srvRegiCustomerChangeGroup[i] = new WEB3AdminSrvRegiCustomerChangeGroup();
            
            //1.8.6 <プロパティ・セット>

            //サービス申込登録行
            SrvRegiApplicationParams l_srvRegiApplicationParams = 
                (SrvRegiApplicationParams)l_gentradeSrvRegiApplication.getDataSourceObject();
                
            //申込登録ID=サービス申込登録行オブジェクト.get申込登録ID( )の戻り値    
            l_srvRegiCustomerChangeGroup[i].applyRegId = String.valueOf(l_srvRegiApplicationParams.getRegistId());
            
            //抽選区分=取得したサービスマスターオブジェクト.get申込要サービス.get抽選設定( )の戻り値
            WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService = l_srvRegiServiceMaster.getAppliRequiredSrv(true);
            
            if(l_srvRegiApplicationRequiredService == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);                   
                throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     getClass().getName() + STR_METHOD_NAME);

            }
            
            l_srvRegiCustomerChangeGroup[i].lotteryDiv = l_srvRegiApplicationRequiredService.getLotDiv();
            
            //部店コード=リクエストデータ.検索条件.部店コード 
            l_srvRegiCustomerChangeGroup[i].branchCode = l_strBranchCode;
            
            //顧客コード=顧客オブエジェクト.get表示顧客コード( )の戻り値 
            l_srvRegiCustomerChangeGroup[i].accountCode = l_mainAccount.getDisplayAccountCode();
            
            //申込抽選区分=サービス申込登録行オブジェクト.get申込抽選区分( )の戻り値
            l_srvRegiCustomerChangeGroup[i].applyLotteryDiv = l_srvRegiApplicationParams.getAppliLotDiv();
            
            //申込日=サービス申込登録行オブジェクト.get申込日( )の戻り値をセットする。
            l_srvRegiCustomerChangeGroup[i].applyDate = l_srvRegiApplicationParams.getAppliDate();
            
            //適用開始日=サービス申込登録行オブジェクト.get適用開始日( )の戻り値
            l_srvRegiCustomerChangeGroup[i].trialStartDate = l_srvRegiApplicationParams.getAppliStartDate();
            
            //適用終了日=サービス申込登録行オブジェクト.get適用終了日( )の戻り値 
            l_srvRegiCustomerChangeGroup[i].trialEndDate = l_srvRegiApplicationParams.getAppliEndDate();
            
            //登録区分=サービス申込登録行オブジェクト.get登録区分( )の戻り値 
            l_srvRegiCustomerChangeGroup[i].registDiv = l_srvRegiApplicationParams.getPaymentDiv();
            
            //利用料金=サービス申込登録行オブジェクト.get利用料金( )の戻り値
            String l_strChargeAmt = null;
            if(!l_srvRegiApplicationParams.getUseAmtIsNull())
            {
                l_strChargeAmt = String.valueOf(l_srvRegiApplicationParams.getUseAmt());
            }
            l_srvRegiCustomerChangeGroup[i].chargeAmt = l_strChargeAmt;
            
        }
        
        //1.9 createレスポンス( )
        WEB3AdminSrvRegiCustomerChangeInputResponse l_srvRegiCustomerChangeInputResponse = 
            (WEB3AdminSrvRegiCustomerChangeInputResponse)l_srvRegiCustomerChangeInputRequest.createResponse();
        
        l_srvRegiCustomerChangeInputResponse.serviceName = l_strSrvName;
        l_srvRegiCustomerChangeInputResponse.chgCustomerList = l_srvRegiCustomerChangeGroup;
                        
        return l_srvRegiCustomerChangeInputResponse;
    }
}
@
