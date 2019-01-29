head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiSrvListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス一覧サービスImpl(WEB3AdminSrvRegiSrvListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 李頴淵 新規作成
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.define.WEB3SrvRegiOfferingDivAppendDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiLotteryGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiNoApplyGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiNoLotteryGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceResponse;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiSrvListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者サービス一覧サービスImpl)<BR>
 * サービス利用管理者サービス一覧サービス実装クラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiSrvListServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiSrvListService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiSrvListServiceImpl.class);
    
    /**
     * @@roseuid 416F392A033C
     */
    public WEB3AdminSrvRegiSrvListServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者サービス一覧照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）サービス一覧」参照<BR>
     * ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス一覧」): <BR>
     *   1.5.: ＜分岐処理＞権限チェック結果の判定<BR>
     *   機@能カテゴリコードが「サービス」の場合も、「顧客」の場合もチェックエラーと<BR> 
     *   なった場合は、例外をスローする。「権限エラー」<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01057<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F4FED40056
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3AdminSrvRegiServiceReferenceRequest l_adminSrvRegiServiceReferenceRequest = 
            (WEB3AdminSrvRegiServiceReferenceRequest)l_request;
        
        //1.2 getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限
        int l_intValidateCnt = 0;
        try
        { 
            log.debug("validate権限1");
            l_administrator.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, false); //WEB3BaseException
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("validate権限 error 1");
            log.debug(STR_METHOD_NAME,l_ex);
            l_intValidateCnt = l_intValidateCnt + 1;
        }
        
        //1.4 validate権限
        try
        {
            log.debug("validate権限2");
            l_administrator.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, false); //WEB3BaseException
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("validate権限 error 2");   
            log.debug(STR_METHOD_NAME,l_ex);
            l_intValidateCnt = l_intValidateCnt + 1; 
        }
        
        //1.5 ＜分岐処理＞権限チェック結果の判定
        if (l_intValidateCnt == 2)
        {
            log.debug("権限チェック結果の判定");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01057,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 get証券会社コード
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.7 isDIR管理者
        boolean l_blnDirAdministrator = l_administrator.isDirAdministrator();
        
        //1.8 getサービスマスター一覧
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster[] l_srvRegiServiceMasters = null;
        if (l_blnDirAdministrator)
        {
            log.debug("l_blnDirAdministrator");
            l_srvRegiServiceMasters = 
                l_srvRegiServiceInfoManagement.getSrvMasterList(l_strInstitutionCode, WEB3SrvRegiOfferingDivAppendDef.EVERYTHING);
        }
        else
        {
            log.debug("!l_blnDirAdministrator");
            l_srvRegiServiceMasters = 
                l_srvRegiServiceInfoManagement.getSrvMasterList(l_strInstitutionCode, WEB3SrvRegiOfferingDivAppendDef.REQUIRE);
        }
        
        //1.9 <現在日時の取得>
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //1.10  <繰り返し処理 *1>       
        List l_lisAdminSrvRegiNoLotteryGroup = new ArrayList();
        List l_lisAdminSrvRegiLotteryGroup = new ArrayList();
        List l_lisAdminSrvRegiNoApplyGroup = new ArrayList();
        
        int l_intLength = l_srvRegiServiceMasters.length;
        for (int i = 0; i < l_intLength; i++)
        { 
            //1.10.2 <分岐処理 *1>  
            if (l_srvRegiServiceMasters[i].isAppliRequired())
            {
                log.debug("<分岐処理 *1>");
                //1.10.2.1 get申込要サービス
                WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
                    l_srvRegiServiceMasters[i].getAppliRequiredSrv(false);
                if (l_srvRegiApplicationRequiredService == null)
                {
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + STR_METHOD_NAME);    
                }    
                
                //1.10.2.1.1get抽選設定 
                String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();
                
                //1.10.2.2 <分岐処理 *1>
                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
                {                   
                    log.debug("1.10.2.2 <分岐処理 *1>");
                    //1.10.2.2.1サービス利用管理者抽選無サービス明細情報一覧行
                    WEB3AdminSrvRegiNoLotteryGroup l_adminSrvRegiNoLotteryGroup = new WEB3AdminSrvRegiNoLotteryGroup();
                    
                    //1.10.2.2.2 getサービス利用期間料金一覧( )
                    WEB3SrvRegiServiceUsePeriodAmt[] l_srvRegiServiceUsePeriodAmts = 
                        l_srvRegiServiceMasters[i].getSrvUseTermAmtList();
                    
                    //10.2.2.3  <繰り返し処理 *2>
                    //10.2.2.3.1 サービス利用期間料金情報
                    int l_intLen = l_srvRegiServiceUsePeriodAmts.length;
                    WEB3SrvRegiChargeInfo[] l_srvRegiChargeInfos = new WEB3SrvRegiChargeInfo[l_intLen];
                    
                    for (int j = 0; j < l_intLen; j++)
                    {
                        log.debug("<プロパティ・セット *1>");
                        l_srvRegiChargeInfos[j] = new WEB3SrvRegiChargeInfo();
                        //10.2.2.3.2 <プロパティ・セット *1>
                        //○利用期間ID=取得したサービス利用期間料金オブジェクト.get通番( )
                        l_srvRegiChargeInfos[j].chargeId = 
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmts[j].getConsecutiveNumbers()); 
                        //○利用期間単位区分=取得したサービス利用期間料金オブジェクト.get利用期間区分( )
                        l_srvRegiChargeInfos[j].chargeDiv = l_srvRegiServiceUsePeriodAmts[j].getSrvUsePeriodDiv(); 
                        //○利用期間=取得したサービス利用期間料金オブジェクト.get利用期間( )
                        l_srvRegiChargeInfos[j].chargePeriod = 
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmts[j].getSrvUsePeriod()); 
                        //○利用料金=取得したサービス利用期間料金オブジェクト.get利用料金( )
                        l_srvRegiChargeInfos[j].chargeAmt = 
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmts[j].getUseAmt()); 
                        //○無効区分="有効"
                        l_srvRegiChargeInfos[j].invalidDiv = false;
                    }
                    
                    //1.10.2.2.4<プロパティ・セット *2>
                    //○ID=サービスマスターオブジェクト.getサービス区分( )
                    l_adminSrvRegiNoLotteryGroup.serviceDiv = l_srvRegiServiceMasters[i].getSrvDiv(); 
                    //○サービス名称=サービスマスターオブジェクト.getサービス名称( )
                    l_adminSrvRegiNoLotteryGroup.serviceName = l_srvRegiServiceMasters[i].getSrvName(); 
                    //○ステータス=サービスマスターオブジェクト.getステータス( )
                    l_adminSrvRegiNoLotteryGroup.serviceStatus = l_srvRegiServiceMasters[i].getStatus(); 
                    //○申込可能期間設定=(*1) 
                    //(*1-1) 申込要サービスオブジェクト.get申込可能期間（自）( )、get申込可能期間（至）( ) 
                    //の戻り値が両方ともnullだった場合、"無"をセットする。
                    if (l_srvRegiApplicationRequiredService.getAppliDateFrom() == null &&
                        l_srvRegiApplicationRequiredService.getAppliDateTo() == null)
                    {
                        log.debug("両方ともnullだった場合、無をセットする");
                        l_adminSrvRegiNoLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT; 
                    }
                    //(*1-2) 申込要サービスオブジェクト.get申込可能期間（自）( )、get申込可能期間（至）( ) 
                    //の戻り値が両方ともnull以外だった場合、"有"をセットする。
                    if (l_srvRegiApplicationRequiredService.getAppliDateFrom() != null &&
                        l_srvRegiApplicationRequiredService.getAppliDateTo() != null)
                    {
                        log.debug("戻り値が両方ともnull以外だった場合、有をセットする");
                        l_adminSrvRegiNoLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE; 
                    } 
                    //○利用期間料金情報=<繰り返し処理 *2>で作成した配列
                    l_adminSrvRegiNoLotteryGroup.chargeInfo = l_srvRegiChargeInfos;                     
                    //○試用期間単位区分=申込要サービスオブジェクト.get試用期間区分( )
                    l_adminSrvRegiNoLotteryGroup.trialDiv = l_srvRegiApplicationRequiredService.getTrialPeriodDiv();                    
                    //○試用期間=申込要サービスオブジェクト.get試用期間( )
                    if (l_srvRegiApplicationRequiredService.getTrialPeriod() == null)
                    {
                        l_adminSrvRegiNoLotteryGroup.trialPeriod = null; 
                        log.debug("get試用期間 == null");
                    }
                    else
                    {
                        log.debug("get試用期間 != null");
                        l_adminSrvRegiNoLotteryGroup.trialPeriod = 
                            l_srvRegiApplicationRequiredService.getTrialPeriod().toString(); 
                    }
   
                        
                    l_lisAdminSrvRegiNoLotteryGroup.add(l_adminSrvRegiNoLotteryGroup);                         
                }
                //1.10.2.3 <分岐処理 *3>   
                else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
                {
                    log.debug("1.10.2.3 <分岐処理 *3> ");
                    //1.10.2.3.1 getサービス抽選情報
                    WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                        l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvRegiServiceMasters[i].getSrvDiv(), l_tsSystemTimestamp, 0);
                        
                    //1.10.2.3.2  <分岐処理 *4>  
                    WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo2 = null;
                    if (l_srvRegiServiceLotInfo == null)
                    {
                        //1.10.2.3.2.1 getサービス抽選情報
                        log.debug("1.10.2.3.2.1 getサービス抽選情報");  
                        l_srvRegiServiceLotInfo2 =
                            l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvRegiServiceMasters[i].getSrvDiv(), l_tsSystemTimestamp, 1);
                    }
                    
                    //1.10.2.3.3 <分岐処理 *5>
                    WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo3 = null;
                    if (l_srvRegiServiceLotInfo == null && l_srvRegiServiceLotInfo2 == null)
                    {
                        //1.10.2.3.3.1 getサービス抽選情報
                        log.debug("<分岐処理 *5>");
                        l_srvRegiServiceLotInfo3 = 
                            l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvRegiServiceMasters[i].getSrvDiv(), l_tsSystemTimestamp, -1);
                    }
                    
                    //1.10.2.3.4 サービス利用管理者抽選有サービス明細情報一覧行
                    WEB3AdminSrvRegiLotteryGroup l_adminSrvRegiLotteryGroup = new WEB3AdminSrvRegiLotteryGroup();
                    
                    //1.10.2.3.6  <プロパティ・セット *3>
                    //○ID=サービスマスターオブジェクト.getサービス区分( )
                    l_adminSrvRegiLotteryGroup.serviceDiv = l_srvRegiServiceMasters[i].getSrvDiv(); 
                    //○サービス名称=サービスマスターオブジェクト.getサービス名称( )
                    l_adminSrvRegiLotteryGroup.serviceName = l_srvRegiServiceMasters[i].getSrvName(); 
                    //○ステータス=サービスマスターオブジェクト.getステータス( ) 
                    l_adminSrvRegiLotteryGroup.serviceStatus = l_srvRegiServiceMasters[i].getStatus();
                    //○申込可能期間設定=true 
                    l_adminSrvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE;
                    
                    //サービス抽選情報オブジェクトが取得できた場合
                    if (l_srvRegiServiceLotInfo != null)
                    {
                        log.debug("サービス抽選情報オブジェクトが取得できた場合");
                        //○運用区分=取得したサービス抽選情報オブジェクト.get運用区分( )
                        l_adminSrvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo.getInvestDiv(); 
                        //○申込期間（自）=取得したサービス抽選情報オブジェクト.get申込期間（自）( )
                        l_adminSrvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo.getAppliDateFrom(); 
                        //○申込期間（至）=取得したサービス抽選情報オブジェクト.get申込期間（至）( ) 
                        l_adminSrvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo.getAppliDateTo();
                        //○抽選日=取得したサービス抽選情報オブジェクト.get抽選日( ) 
                        l_adminSrvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo.getLotDate();
                        //○適用開始日=取得したサービス抽選情報オブジェクト.get適用開始日( ) 
                        l_adminSrvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo.getAppliStartDate();
                        //○適用終了日=取得したサービス抽選情報オブジェクト.get適用終了日( ) 
                        l_adminSrvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo.getAppliEndDate();
                        //○利用料金=取得したサービス抽選情報オブジェクト.get利用料金( ) 
                        l_adminSrvRegiLotteryGroup.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo.getUseAmt());
                        //○入札単位=取得したサービス抽選情報オブジェクト.get入札単位( )  
                        if (l_srvRegiServiceLotInfo.getBiddingPrice() == null)
                        {
                            l_adminSrvRegiLotteryGroup.biddingPriceUnit = null;
                        }
                        else
                        {
                            l_adminSrvRegiLotteryGroup.biddingPriceUnit = l_srvRegiServiceLotInfo.getBiddingPrice().toString();
                        }
                        
                    }
                    else if (l_srvRegiServiceLotInfo2 != null)
                    {
                        log.debug("l_srvRegiServiceLotInfo2 != null");
                        //○運用区分=取得したサービス抽選情報オブジェクト.get運用区分( )
                        l_adminSrvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo2.getInvestDiv(); 
                        //○申込期間（自）=取得したサービス抽選情報オブジェクト.get申込期間（自）( )
                        l_adminSrvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo2.getAppliDateFrom(); 
                        //○申込期間（至）=取得したサービス抽選情報オブジェクト.get申込期間（至）( ) 
                        l_adminSrvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo2.getAppliDateTo();
                        //○抽選日=取得したサービス抽選情報オブジェクト.get抽選日( ) 
                        l_adminSrvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo2.getLotDate();
                        //○適用開始日=取得したサービス抽選情報オブジェクト.get適用開始日( ) 
                        l_adminSrvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo2.getAppliStartDate();
                        //○適用終了日=取得したサービス抽選情報オブジェクト.get適用終了日( ) 
                        l_adminSrvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo2.getAppliEndDate();
                        //○利用料金=取得したサービス抽選情報オブジェクト.get利用料金( ) 
                        l_adminSrvRegiLotteryGroup.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo2.getUseAmt());
                        //○入札単位=取得したサービス抽選情報オブジェクト.get入札単位( )  
                        if (l_srvRegiServiceLotInfo2.getBiddingPrice() == null)
                        {
                            l_adminSrvRegiLotteryGroup.biddingPriceUnit = null;
                        }
                        else
                        {
                            l_adminSrvRegiLotteryGroup.biddingPriceUnit = l_srvRegiServiceLotInfo2.getBiddingPrice().toString();
                        }
                    }
                    else if (l_srvRegiServiceLotInfo3 != null)
                    {
                        log.debug("l_srvRegiServiceLotInfo3 != null");
                        //○運用区分=取得したサービス抽選情報オブジェクト.get運用区分( )
                        l_adminSrvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo3.getInvestDiv(); 
                        //○申込期間（自）=取得したサービス抽選情報オブジェクト.get申込期間（自）( )
                        l_adminSrvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo3.getAppliDateFrom(); 
                        //○申込期間（至）=取得したサービス抽選情報オブジェクト.get申込期間（至）( ) 
                        l_adminSrvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo3.getAppliDateTo();
                        //○抽選日=取得したサービス抽選情報オブジェクト.get抽選日( ) 
                        l_adminSrvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo3.getLotDate();
                        //○適用開始日=取得したサービス抽選情報オブジェクト.get適用開始日( ) 
                        l_adminSrvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo3.getAppliStartDate();
                        //○適用終了日=取得したサービス抽選情報オブジェクト.get適用終了日( ) 
                        l_adminSrvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo3.getAppliEndDate();
                        //○利用料金=取得したサービス抽選情報オブジェクト.get利用料金( ) 
                        l_adminSrvRegiLotteryGroup.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo3.getUseAmt());
                        //○入札単位=取得したサービス抽選情報オブジェクト.get入札単位( )  
                        if (l_srvRegiServiceLotInfo3.getBiddingPrice() == null)
                        {
                            l_adminSrvRegiLotteryGroup.biddingPriceUnit = null;
                        }
                        else
                        {
                            l_adminSrvRegiLotteryGroup.biddingPriceUnit = l_srvRegiServiceLotInfo3.getBiddingPrice().toString();
                        }
                        
                    }
                    else
                    {
                        // サービス抽選情報オブジェクトが取得できなかった場合 
                        log.debug("サービス抽選情報オブジェクトが取得できなかった場合");
                        //○運用区分=null 
                        l_adminSrvRegiLotteryGroup.useDiv = null;
                        //○申込期間（自）=null
                        l_adminSrvRegiLotteryGroup.applyStartDate = null; 
                        //○申込期間（至）=null
                        l_adminSrvRegiLotteryGroup.applyEndDate = null; 
                        //○抽選日=null
                        l_adminSrvRegiLotteryGroup.lotteryDate = null;
                        //○適用開始日=null
                        l_adminSrvRegiLotteryGroup.trialStartDate = null; 
                        //○適用終了日=null
                        l_adminSrvRegiLotteryGroup.trialEndDate = null; 
                        //○利用料金=null
                        l_adminSrvRegiLotteryGroup.chargeAmt = null; 
                        //○入札単位=null
                        l_adminSrvRegiLotteryGroup.biddingPriceUnit = null;
                    }
                    l_lisAdminSrvRegiLotteryGroup.add(l_adminSrvRegiLotteryGroup);
                }
            }
            //1.10.2.4 <分岐処理 *6>
            else if(!l_srvRegiServiceMasters[i].isAppliRequired())
            {   
                log.debug("<分岐処理 *6>");
                //1.10.2.4.1 サービス利用管理者申込不要サービス明細情報一覧行
                WEB3AdminSrvRegiNoApplyGroup l_adminSrvRegiNoApplyGroup = new WEB3AdminSrvRegiNoApplyGroup();
                //1.10.2.4.2 <プロパティ・セット *4>
                //○ID=サービスマスターオブジェクト.getサービス区分( )
                l_adminSrvRegiNoApplyGroup.serviceDiv = l_srvRegiServiceMasters[i].getSrvDiv(); 
                //○サービス名称=サービスマスターオブジェクト.getサービス名称( ) 
                l_adminSrvRegiNoApplyGroup.serviceName = l_srvRegiServiceMasters[i].getSrvName();
                //○ステータス=サービスマスターオブジェクト.getステータス( ) 
                l_adminSrvRegiNoApplyGroup.serviceStatus = l_srvRegiServiceMasters[i].getStatus();
                //○申込可能期間設定=false
                l_adminSrvRegiNoApplyGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT;
                l_lisAdminSrvRegiNoApplyGroup.add(l_adminSrvRegiNoApplyGroup);
            }
        }
        //createレスポンス
        log.debug("createレスポンス");
        WEB3AdminSrvRegiServiceReferenceResponse l_adminSrvRegiServiceReferenceResponse 
            = (WEB3AdminSrvRegiServiceReferenceResponse)l_adminSrvRegiServiceReferenceRequest.createResponse();
        
        WEB3AdminSrvRegiNoLotteryGroup[] l_adminSrvRegiNoLotteryGroups = 
            new WEB3AdminSrvRegiNoLotteryGroup[l_lisAdminSrvRegiNoLotteryGroup.size()];
        WEB3AdminSrvRegiLotteryGroup[] l_adminSrvRegiLotteryGroups = 
            new WEB3AdminSrvRegiLotteryGroup[l_lisAdminSrvRegiLotteryGroup.size()];
        WEB3AdminSrvRegiNoApplyGroup[] l_adminSrvRegiNoApplyGroups = 
            new WEB3AdminSrvRegiNoApplyGroup[l_lisAdminSrvRegiNoApplyGroup.size()];
            
        l_lisAdminSrvRegiNoLotteryGroup.toArray(l_adminSrvRegiNoLotteryGroups);
        l_lisAdminSrvRegiLotteryGroup.toArray(l_adminSrvRegiLotteryGroups); 
        l_lisAdminSrvRegiNoApplyGroup.toArray(l_adminSrvRegiNoApplyGroups);   
        
        l_adminSrvRegiServiceReferenceResponse.noLotList = l_adminSrvRegiNoLotteryGroups;
        l_adminSrvRegiServiceReferenceResponse.lotList = l_adminSrvRegiLotteryGroups;
        l_adminSrvRegiServiceReferenceResponse.noApplyList = l_adminSrvRegiNoApplyGroups;
        
        log.exiting(STR_METHOD_NAME);
        return l_adminSrvRegiServiceReferenceResponse;
    }
}
@
