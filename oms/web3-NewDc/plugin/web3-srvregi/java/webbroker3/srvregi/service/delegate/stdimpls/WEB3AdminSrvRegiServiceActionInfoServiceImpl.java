head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceActionInfoServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス履歴情報サービスImpl(WEB3AdminSrvRegiServiceActionInfoServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
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
import webbroker3.srvregi.message.WEB3AdminSrvRegiHistoryGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceActionInfoService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者サービス履歴情報サービスImpl)<BR>
 * サービス利用管理者サービス履歴情報サービス実装クラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceActionInfoServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceActionInfoService 
{
    
     /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceActionInfoServiceImpl.class);

    /**
     * @@roseuid 416F392B037A
     */
    public WEB3AdminSrvRegiServiceActionInfoServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者サービス履歴情報検索処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）サービス履歴情報照会」参照<BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス履歴情報照会」): <BR>
     *         1.9:<分岐処理>  <BR>
     *        <分岐処理><BR>
     *        getサービス抽選情報履歴( )の戻り値=nullの場合、<BR>
     *        例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00991<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F51B5E0298
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +  STR_METHOD_NAME);
        }

        //1.1:validate( )
        WEB3AdminSrvRegiServiceHistoryRequest l_srvHistoryRequest = (WEB3AdminSrvRegiServiceHistoryRequest)l_request;
        l_srvHistoryRequest.validate();
        
        //1.3:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, false);
        
        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:getサービスマスター(String, String, boolean)
        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvMaster = 
            l_srvInfoManagement.getSrvMaster(l_strInstitutionCode, l_srvHistoryRequest.serviceDiv, false);
        
        //1.7:getサービス名称( )
        String l_strSrvName = l_srvMaster.getSrvName();

        //1.8:get履歴サービス抽選情報一覧(String, String)
        WEB3SrvRegiServiceLotInfo[] l_srvLotInfos = 
            l_srvInfoManagement.getActionSrvLotInfoList(l_strInstitutionCode, l_srvHistoryRequest.serviceDiv);
        
        //1.9:<分岐処理>
        if (l_srvLotInfos == null || l_srvLotInfos.length == 0)
        {
            log.debug("1.9:<分岐処理>");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00991, 
                this.getClass().getName() + STR_METHOD_NAME);    
        }
        
        //1.10:<繰り返し処理>
        int l_intSrvLotInfoCnt = l_srvLotInfos.length;
        log.debug("l_intSrvLotInfoCnt:" + l_intSrvLotInfoCnt);
        WEB3AdminSrvRegiHistoryGroup[] l_adminHistoryGroups = new WEB3AdminSrvRegiHistoryGroup[l_intSrvLotInfoCnt];
        
        for (int i = 0; i < l_intSrvLotInfoCnt; i++)
        {
            log.debug("loop count:" + i);
            
            //1.10.1サービス利用管理者サービス履歴情報一覧行( )
            l_adminHistoryGroups[i] = new WEB3AdminSrvRegiHistoryGroup();
            //1.10.2:<レスポンス・セット>
            l_adminHistoryGroups[i].lotteryId = String.valueOf(l_srvLotInfos[i].getConsecutiveNumbers());
            l_adminHistoryGroups[i].useDiv = l_srvLotInfos[i].getInvestDiv();
            l_adminHistoryGroups[i].applyStartDate = l_srvLotInfos[i].getAppliDateFrom();
            l_adminHistoryGroups[i].applyEndDate = l_srvLotInfos[i].getAppliDateTo();
            l_adminHistoryGroups[i].lotteryDate = l_srvLotInfos[i].getLotDate();
            l_adminHistoryGroups[i].trialStartDate = l_srvLotInfos[i].getAppliStartDate();
            l_adminHistoryGroups[i].trialEndDate = l_srvLotInfos[i].getAppliEndDate();
            l_adminHistoryGroups[i].chargeAmt =  String.valueOf(l_srvLotInfos[i].getUseAmt());
            
            if (l_srvLotInfos[i].getBiddingPrice() == null)
            {
                l_adminHistoryGroups[i].biddingPriceUnit = null;
            }
            else
            {
                l_adminHistoryGroups[i].biddingPriceUnit = l_srvLotInfos[i].getBiddingPrice().toString();
            }
            l_adminHistoryGroups[i].paymentDate= l_srvLotInfos[i].getPaymentDate();
            
            if (l_srvLotInfos[i].getPublicOfferingQty() == null)
            {
                l_adminHistoryGroups[i].applyMax = null;
            }
            else
            {
                l_adminHistoryGroups[i].applyMax = l_srvLotInfos[i].getPublicOfferingQty().toString();
            }
            
            if (l_srvLotInfos[i].getHighSuccessBid() == null)
            {
                l_adminHistoryGroups[i].maxSuccBidding= null;
            }
            else
            {
                l_adminHistoryGroups[i].maxSuccBidding =  l_srvLotInfos[i].getHighSuccessBid().toString();
            }
            
            if (l_srvLotInfos[i].getLowSuccessBid() == null)
            {
                l_adminHistoryGroups[i].minSuccBidding = null;
            }
            else
            {
                l_adminHistoryGroups[i].minSuccBidding =  l_srvLotInfos[i].getLowSuccessBid().toString();
            }
            
            if (l_srvLotInfos[i].getAverageSuccessBid() == null)
            {
                l_adminHistoryGroups[i].weightedAverage = null;
            }
            else
            {
                l_adminHistoryGroups[i].weightedAverage = l_srvLotInfos[i].getAverageSuccessBid().toString();
            } 
        }
        
        //1.11:createレスポンス( )
        WEB3AdminSrvRegiServiceHistoryResponse l_srvHistoryResponse = 
            (WEB3AdminSrvRegiServiceHistoryResponse)l_srvHistoryRequest.createResponse();
        l_srvHistoryResponse.serviceDiv = l_srvHistoryRequest.serviceDiv;
        l_srvHistoryResponse.serviceName = l_strSrvName;
        l_srvHistoryResponse.histories = l_adminHistoryGroups;
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_srvHistoryResponse;
    }
}
@
