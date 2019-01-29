head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス落札額更新入力サービスImpl(WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateInputService;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者サービス落札額更新入力サービスImpl)<BR>
 * サービス利用管理者サービス落札額更新入力サービス実装クラス<BR>                                                               
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceBidPriceUpdateInputService 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl.class);
        
    /**
     * @@roseuid 416F3927031C
     */
    public WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者サービス落札額更新入力照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）顧客落札額更新入力」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）顧客落札額更新入力」): <BR>
     *         1.8.<分岐処理> <BR>      
     *          getサービス抽選情報( )の戻り値=nullの場合<BR>
     *          例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01018<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EC2C01B6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiSuccBidInputRequest l_adminSrvRegiSuccBidInputRequest;
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
       
        l_adminSrvRegiSuccBidInputRequest = (WEB3AdminSrvRegiSuccBidInputRequest)l_request;        
        
        //1.1.validate( )
        l_adminSrvRegiSuccBidInputRequest.validate();
        
        //1.2.setBusinessTimestamp( )
        WEB3SrvRegiTradingTimeManagement.setTimestamp();
        
        //1.3.getInstanceFromログイン情報()
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4.validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, true);
        
        //1.5.get証券会社コード()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        
        //1.6.getサービスマスター(String, String, boolean)
        WEB3SrvRegiServiceMaster l_serviceMaster = l_serviceInfoManagement.getSrvMaster(
            l_strInstitutionCode, l_adminSrvRegiSuccBidInputRequest.serviceDiv, false);
        
        //1.7.getサービス抽選情報(通番 : long, is行ロック : boolean)
        WEB3SrvRegiServiceLotInfo l_serviceLotInfo =
            l_serviceMaster.getSrvLotInfo(Long.parseLong(l_adminSrvRegiSuccBidInputRequest.lotteryId), false);
        
        //1.8.getサービス抽選情報( )の戻り値=nullの場合例外をスローする。
        if(l_serviceLotInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.9.createレスポンス()
        WEB3AdminSrvRegiSuccBidInputResponse l_response =
            (WEB3AdminSrvRegiSuccBidInputResponse)l_adminSrvRegiSuccBidInputRequest.createResponse();
        
        //1.10 <プロパティ・セット>    
        //サービス区分
        l_response.serviceDiv = l_adminSrvRegiSuccBidInputRequest.serviceDiv;
        
        //サービス名称
        l_response.serviceName = l_serviceMaster.getSrvName();
        
        //申込期間（自）
        l_response.applyStartDate = l_serviceLotInfo.getAppliDateFrom();
        
        //申込期間（至）
        l_response.applyEndDate = l_serviceLotInfo.getAppliDateTo();
        
        //抽選日
        l_response.lotteryDate = l_serviceLotInfo.getLotDate();
        
        //適用開始日
        l_response.trialStartDate = l_serviceLotInfo.getAppliStartDate();
        
        //適用終了日
        l_response.trialEndDate = l_serviceLotInfo.getAppliEndDate();
        
        //利用料金
        l_response.chargeAmt = String.valueOf(l_serviceLotInfo.getUseAmt());
        
        //入札単位
        if(l_serviceLotInfo.getBiddingPrice() != null)
        {
            l_response.biddingPriceUnit = l_serviceLotInfo.getBiddingPrice().toString(); 
        }
                  
        //出金日
        l_response.paymentDate = l_serviceLotInfo.getPaymentDate();
        
        //募集枠        
        if(l_serviceLotInfo.getPublicOfferingQty() != null)
        {
            l_response.applyMax = l_serviceLotInfo.getPublicOfferingQty().toString(); 
        }
        
        //最高落札額
        if(l_serviceLotInfo.getHighSuccessBid() != null)
        {
            l_response.maxSuccBidding = l_serviceLotInfo.getHighSuccessBid().toString(); 
        }
        
        //最低落札額
        if(l_serviceLotInfo.getLowSuccessBid() != null)
        {
            l_response.minSuccBidding = l_serviceLotInfo.getLowSuccessBid().toString(); 
        }
        
        //加重平均額
        if(l_serviceLotInfo.getAverageSuccessBid() != null)
        {
            l_response.weightedAverage = l_serviceLotInfo.getAverageSuccessBid().toString();
        }

        //運用区分＝取得したサービス抽選情報オブジェクト.get運用区分()
        l_response.useDiv = l_serviceLotInfo.getInvestDiv();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
