head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス詳細サービスImpl(WEB3AdminSrvRegiServiceDetailServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
Revesion History : 2007/06/06 張騰宇 (中訊) モデル255 259
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
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
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiCommCondMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCommissionCondition;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.message.WEB3SrvRegiExecKey;
import webbroker3.srvregi.message.WEB3SrvRegiLotteryInfo;
import webbroker3.srvregi.message.WEB3SrvRegiSetAbleCommissionCondition;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceDetailService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者サービス詳細サービスImpl)<BR>
 * サービス利用管理者サービス詳細サービス実装クラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceDetailServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceDetailService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceDetailServiceImpl.class);

    /**
     * @@roseuid 416F392A03D8
     */
    public WEB3AdminSrvRegiServiceDetailServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者サービス詳細検索処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）サービス詳細」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス詳細」): <BR>
     *         1.7 isDIR管理者( )  <BR>
     *         1.8.is申込要( )<BR>
     *         管理者オブジェクト.isDIR管理者( )==false、かつ<BR>
     *         is申込要( )=falseの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00985<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F50AF1025A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("l_request == null");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.1:validate( )
        WEB3AdminSrvRegiServiceDetailsRequest l_srvDetailsRequest = 
            (WEB3AdminSrvRegiServiceDetailsRequest)l_request;
        l_srvDetailsRequest.validate();
        
        //1.3:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE,false);
        
        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:getサービスマスター(String, String, boolean)
        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = 
            new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvMaster = 
            l_srvInfoManagement.getSrvMaster(l_strInstitutionCode,l_srvDetailsRequest.serviceDiv, false);
        
        
        //1.7:isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        
        //1.8:is申込要( )
        boolean l_blnIsAppliRequired = l_srvMaster.isAppliRequired();
        
        if (!l_blnIsAppliRequired && !l_blnIsDirAdmin)
        {
            log.debug("isDIR管理者()=false and is申込要()=false");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00985, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3SrvRegiApplicationRequiredService l_appliRequiredSrv = null;
        WEB3SrvRegiChargeInfo[] l_chargeInfos = null;
        WEB3SrvRegiLotteryInfo l_lotteryInfo = null;    
        WEB3SrvRegiSetAbleCommissionCondition[] l_ableCommissionConditions = null;
        WEB3SrvRegiApplyCommissionCondition[] l_applyCommissionConditions = null;    
        String l_strFreeTargetPeriod = null;

        //<is申込要( )＝trueの場合>
        if (l_blnIsAppliRequired)
        {
            log.debug("1.9:<is申込要( )＝trueの場合>");
            
            //1.9.1:get申込要サービス( )
            l_appliRequiredSrv = l_srvMaster.getAppliRequiredSrv(false);

            if (l_appliRequiredSrv == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            //1.9.1.1:get抽選設定( )
            String l_strLotDiv = l_appliRequiredSrv.getLotDiv();
        
            //1.9.2:<分岐処理 *1>
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                log.debug("1.9.2:<分岐処理 *1>");
                
                //1.9.2.1:getサービス利用期間料金一覧( )
                WEB3SrvRegiServiceUsePeriodAmt[] l_srvUsePeriodAmts = l_srvMaster.getSrvUseTermAmtList();

                //get無料対象期間( )
                l_strFreeTargetPeriod = l_appliRequiredSrv.getFreeTargetPeriod();

                //1.9.2.2:<繰り返し処理 *1>
                int l_intSrvUsePeriodAmtCnt = l_srvUsePeriodAmts.length;
                
                log.debug("l_intSrvUsePeriodAmtCnt:" + l_intSrvUsePeriodAmtCnt);
                
                if(l_intSrvUsePeriodAmtCnt > 0)
                {
                    log.debug("entering 1.9.2.2:<繰り返し処理 *1>");
                    l_chargeInfos = new WEB3SrvRegiChargeInfo[l_intSrvUsePeriodAmtCnt]; 
                
                    for (int i = 0; i < l_intSrvUsePeriodAmtCnt; i++)
                    {   
                        log.debug("loop connt:" + i);
                        
                        //1.9.2.2.1: サービス利用期間料金情報( )
                        l_chargeInfos[i] = new WEB3SrvRegiChargeInfo();
                    
                        //1.9.2.2.2:<プロパティ・セット *1>
                        l_chargeInfos[i].chargeId = String.valueOf(l_srvUsePeriodAmts[i].getConsecutiveNumbers());
                        l_chargeInfos[i].chargeDiv = l_srvUsePeriodAmts[i].getSrvUsePeriodDiv();
                        l_chargeInfos[i].chargePeriod = String.valueOf(l_srvUsePeriodAmts[i].getSrvUsePeriod());
                        l_chargeInfos[i].chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmts[i].getUseAmt());
                        l_chargeInfos[i].invalidDiv = false;
                    }
                    log.debug("exiting 1.9.2.2:<繰り返し処理 *1>");
                } 
                
                //1.9.2.3:get手数料条件一覧(String)
                List l_lisCommCondMasterList = l_srvInfoManagement.getCommCondList(l_strInstitutionCode);
                int l_intCommCondMasterCnt = l_lisCommCondMasterList.size();

                log.debug("l_intCommCondMasterCnt:" + l_intCommCondMasterCnt);   
        
                if (l_intCommCondMasterCnt > 0)
                {            
                    l_ableCommissionConditions = new WEB3SrvRegiSetAbleCommissionCondition[l_intCommCondMasterCnt];
                    //1.9.2.4: <繰り返し処理 *2>
                    for (int i = 0; i < l_intCommCondMasterCnt; i++)
                    {
                        log.debug("loop count:" + i);
                
                        WEB3SrvRegiCommCondMaster l_commCondMaster= (WEB3SrvRegiCommCondMaster)l_lisCommCondMasterList.get(i);
                        //1.9.2.4.1:サービス利用設定可能手数料条件( )
                        l_ableCommissionConditions[i] = new WEB3SrvRegiSetAbleCommissionCondition();
                        //1.9.2.4.2:<プロパティ・セット>
                        l_ableCommissionConditions[i].productKindDiv = l_commCondMaster.getOrderAccProduct();
                        l_ableCommissionConditions[i].productName = l_commCondMaster.getCommProdTypeName();
                    }            
                }

                //1.9.2.5:get手数料条件一覧( )
                List l_lisCommCondRowList = l_srvMaster.getCommCondList(); 

                if (l_lisCommCondRowList != null && l_lisCommCondRowList.size() > 0)
                {
                    int l_intCommCondRowCnt = l_lisCommCondRowList.size();
            
                    l_applyCommissionConditions = new WEB3SrvRegiApplyCommissionCondition[l_intCommCondRowCnt];
                    //1.9.2.6: <繰り返し処理 *3>
                    for (int i = 0; i < l_intCommCondRowCnt; i++)
                    {
                        log.debug("loop count:" + i);
                
                        SrvRegiCommCondRow l_srvRegiCommCondRow= (SrvRegiCommCondRow)l_lisCommCondRowList.get(i);
                        //1.9.2.6.1:サービス利用適用手数料条件( )
                        l_applyCommissionConditions[i] = new WEB3SrvRegiApplyCommissionCondition();
            
                        //1.9.2.6.2:<プロパティ・セット>
                        l_applyCommissionConditions[i].productKindDiv = l_srvRegiCommCondRow.getOrderAccProduct();
                        l_applyCommissionConditions[i].invalidDiv = false;
                    }
                } 
            }            
                                
            //1.9.3:<現在日時の取得>
            Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
            //1.9.4:<分岐処理 *2>
            if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
            {
                log.debug("1.9.4:<分岐処理 *2>");
                
                //1.9.4.1:getサービス抽選情報(String, String, Timestamp, int)
                WEB3SrvRegiServiceLotInfo l_srvLotInfo = 
                    l_srvInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvDetailsRequest.serviceDiv, l_tsNowDate, 0);
            
                //1.9.4.2:<分岐処理 *3>
                if (l_srvLotInfo == null)
                {
                    log.debug("1.9.4.2:<分岐処理 *3>");
                    
                    //1.9.4.2.1:getサービス抽選情報(String, String, Timestamp, int)
                    l_srvLotInfo = 
                        l_srvInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvDetailsRequest.serviceDiv, l_tsNowDate, 1);
                    //1.9.4.2.2:<分岐処理 *4>
                    if (l_srvLotInfo == null)
                    {
                        log.debug("1.9.4.2.2:<分岐処理 *4>");
                        
                        //1.9.4.2.2.1:getサービス抽選情報(String, String, Timestamp, int)
                        l_srvLotInfo = 
                            l_srvInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvDetailsRequest.serviceDiv, l_tsNowDate, -1);
                    }
                }
                
                //1.9.4.3:<分岐処理 *5>
                if (l_srvLotInfo != null)
                {
                    log.debug("1.9.4.3:<分岐処理 *5>");
                    
                    //1.9.4.3.1:サービス利用抽選情報( )
                    l_lotteryInfo = new WEB3SrvRegiLotteryInfo();
                    
                    //1.9.4.3.2:<プロパティ・セット *2>
                    l_lotteryInfo.lotteryId = String.valueOf(l_srvLotInfo.getConsecutiveNumbers());
                    l_lotteryInfo.useDiv = l_srvLotInfo.getInvestDiv();
                    l_lotteryInfo.applyStartDate = l_srvLotInfo.getAppliDateFrom();
                    l_lotteryInfo.applyEndDate = l_srvLotInfo.getAppliDateTo();
                    l_lotteryInfo.lotteryDate = l_srvLotInfo.getLotDate();
                    l_lotteryInfo.trialStartDate = l_srvLotInfo.getAppliStartDate();
                    l_lotteryInfo.trialEndDate = l_srvLotInfo.getAppliEndDate();
                    l_lotteryInfo.chargeAmt = String.valueOf(l_srvLotInfo.getUseAmt());
                    
                    if (l_srvLotInfo.getBiddingPrice() == null)
                    {
                        l_lotteryInfo.biddingPriceUnit = null;
                    }
                    else
                    {
                        l_lotteryInfo.biddingPriceUnit =l_srvLotInfo.getBiddingPrice().toString();
                    }
                    
                    l_lotteryInfo.paymentDate = l_srvLotInfo.getPaymentDate();
                    
                    if (l_srvLotInfo.getPublicOfferingQty() == null)
                    {
                        l_lotteryInfo.applyMax = null;
                    }
                    else
                    {
                        l_lotteryInfo.applyMax = l_srvLotInfo.getPublicOfferingQty().toString();
                    }
                    
                    l_lotteryInfo.invalidDiv = false;                         
                }
            }            
        }
        
        //1.10:＜isDIR管理者()==trueの場合＞
        WEB3SrvRegiExecKey[] l_hashExecKeys = null;
        WEB3SrvRegiExecKey[] l_paramExecKeys = null;
        if (l_blnIsDirAdmin)
        {
            //1.10.1: getハッシュ値一覧( )
            WEB3SrvRegiServiceUseKey[] l_useKeys1 = l_srvMaster.getHashList(); 
            int l_intSrvUseKeyCnt = l_useKeys1.length;
        
            if(l_intSrvUseKeyCnt > 0)
            {
                l_hashExecKeys = new WEB3SrvRegiExecKey[l_intSrvUseKeyCnt];
                //1.10.2:<繰り返し処理 *4>
                for (int i = 0; i < l_intSrvUseKeyCnt; i++)
                {
                    //1.10.2.1:サービス利用起動キー( )
                    l_hashExecKeys[i] = new WEB3SrvRegiExecKey();

                    //1.10.2.2:<プロパティ・セット *3>
                    l_hashExecKeys[i].keyKindDiv = l_useKeys1[i].getSrvUseKeyType();
                    l_hashExecKeys[i].keyId = String.valueOf(l_useKeys1[i].getSrvUseKeyId());
                    l_hashExecKeys[i].key = l_useKeys1[i].getSrvUseKey();
                    l_hashExecKeys[i].invalidDiv = false;
                }
            }
            
            //1.10.3: 
            WEB3SrvRegiServiceUseKey[] l_useKeys2 = l_srvMaster.getParamList(); 
            l_intSrvUseKeyCnt = l_useKeys2.length;
        
            if(l_intSrvUseKeyCnt > 0)
            {
                l_paramExecKeys = new WEB3SrvRegiExecKey[l_intSrvUseKeyCnt];

                for (int i = 0; i < l_intSrvUseKeyCnt; i++)
                {
                    //1.10.3.1:サービス利用起動キー( )
                    l_paramExecKeys[i] = new WEB3SrvRegiExecKey();

                    //1.10.3.2:<プロパティ・セット *3>
                    l_paramExecKeys[i].keyKindDiv = l_useKeys2[i].getSrvUseKeyType();
                    l_paramExecKeys[i].keyId = String.valueOf(l_useKeys2[i].getSrvUseKeyId());
                    l_paramExecKeys[i].key = l_useKeys2[i].getSrvUseKey();
                    l_paramExecKeys[i].invalidDiv = false;
                }
            }
        }        

        //1.16:createレスポンス( )
        WEB3AdminSrvRegiServiceDetailsResponse l_srvDetailsResponse = 
            (WEB3AdminSrvRegiServiceDetailsResponse)l_srvDetailsRequest.createResponse();
        
        
        log.debug("1.17:<レスポンス・セット>");
        //1.17:<レスポンス・セット>            
        l_srvDetailsResponse.serviceDiv = l_srvDetailsRequest.serviceDiv;
        l_srvDetailsResponse.serviceName = l_srvMaster.getSrvName();
        SrvRegiMasterRow l_masterRow = (SrvRegiMasterRow)l_srvMaster.getDataSourceObject();
        l_srvDetailsResponse.applyDiv = l_masterRow.getOfferingDiv();
        l_srvDetailsResponse.serviceStatus = l_srvMaster.getStatus();
        l_srvDetailsResponse.consentSentence = l_srvMaster.getConsDoc(false).getCons();
        
        WEB3GentradeMailInfo l_comfirmMailInfo = l_srvMaster.getConfirmMailInfo();
        if (l_comfirmMailInfo == null)
        {
            log.debug("l_comfirmMailInfo == null");
            
            l_srvDetailsResponse.confirmMailFrom = null;
            l_srvDetailsResponse.confirmMailSubject = null;
            l_srvDetailsResponse.confirmMailHeader = null;
            l_srvDetailsResponse.confirmMailBody = null;
            l_srvDetailsResponse.confirmMailFooter = null;
        }
        else
        {
            log.debug("l_comfirmMailInfo != null");
            
            l_srvDetailsResponse.confirmMailFrom = l_comfirmMailInfo.getMailSender();
            l_srvDetailsResponse.confirmMailSubject = l_comfirmMailInfo.getSubject();
            l_srvDetailsResponse.confirmMailHeader = l_comfirmMailInfo.getMailHeader();
            l_srvDetailsResponse.confirmMailBody = l_comfirmMailInfo.getMailText();
            l_srvDetailsResponse.confirmMailFooter = l_comfirmMailInfo.getMailFooter();
        }
        
        WEB3GentradeMailInfo l_endMailInfo = l_srvMaster.getEndMaiDivInfo();
        if (l_endMailInfo == null)
        {   
            log.debug("l_endMailInfo == null");
            
            l_srvDetailsResponse.noticeMailFrom = null;
            l_srvDetailsResponse.noticeMailSubject = null;
            l_srvDetailsResponse.noticeMailHeader = null;
            l_srvDetailsResponse.noticeMailBody = null;
            l_srvDetailsResponse.noticeMailFooter = null;
        }
        else
        {
            log.debug("l_endMailInfo != null");
            
            l_srvDetailsResponse.noticeMailFrom = l_endMailInfo.getMailSender();
            l_srvDetailsResponse.noticeMailSubject = l_endMailInfo.getSubject();
            l_srvDetailsResponse.noticeMailHeader = l_endMailInfo.getMailHeader();
            l_srvDetailsResponse.noticeMailBody = l_endMailInfo.getMailText();
            l_srvDetailsResponse.noticeMailFooter = l_endMailInfo.getMailFooter();
        }        
        
        //is申込要( )＝"要"だった場合
        if (l_blnIsAppliRequired)
        {
            log.debug("is申込要( )＝要");
            
            l_srvDetailsResponse.summary = l_appliRequiredSrv.getSummary();
            l_srvDetailsResponse.elePigeonDiv = l_appliRequiredSrv.isElectricIssue();
        
            l_srvDetailsResponse.chargeInfo = l_chargeInfos;  
            l_srvDetailsResponse.trialDiv = l_appliRequiredSrv.getTrialPeriodDiv();
            
            if (l_appliRequiredSrv.getTrialPeriod() == null)
            {
                l_srvDetailsResponse.trialPeriod = null;
            }
            else
            {
                l_srvDetailsResponse.trialPeriod = l_appliRequiredSrv.getTrialPeriod().toString();
            }
            
            
            if (l_appliRequiredSrv.getAppliDateFrom() == null)
            {
                l_srvDetailsResponse.applyAbleStartDate = null;
            }
            else
            {
                l_srvDetailsResponse.applyAbleStartDate = l_appliRequiredSrv.getAppliDateFrom().toString();
            }
            
            if (l_appliRequiredSrv.getAppliDateTo() == null)
            {
                l_srvDetailsResponse.applyAbleEndDate = null;
            }
            else
            {
                l_srvDetailsResponse.applyAbleEndDate = l_appliRequiredSrv.getAppliDateTo().toString();
            }
            
            l_srvDetailsResponse.serviceContent = l_appliRequiredSrv.getSrvContents();
            l_srvDetailsResponse.explainURL = l_appliRequiredSrv.getSrvExplanUrl();
            l_srvDetailsResponse.confirmMailDiv = l_appliRequiredSrv.getStartMailDiv();
        
            l_srvDetailsResponse.noticeMailDiv = l_appliRequiredSrv.getEndMailDiv();
            
            if (l_appliRequiredSrv.getSendMailInterval() == null)
            {
                l_srvDetailsResponse.noticeMailDate = null;
            }
            else
            {
                l_srvDetailsResponse.noticeMailDate = l_appliRequiredSrv.getSendMailInterval().toString();
            }
            
            l_srvDetailsResponse.lotteryDiv = l_appliRequiredSrv.getLotDiv();
            
            WEB3SrvRegiLotteryInfo[] l_lotteryInfos = new WEB3SrvRegiLotteryInfo[1];
            
            if (l_lotteryInfo == null)
            {
                l_lotteryInfos = null; 
            }
            else
            {
                l_lotteryInfos[0] = l_lotteryInfo;
            }
            
            l_srvDetailsResponse.applyInfo = l_lotteryInfos;

            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_appliRequiredSrv.getLotDiv()))
            {
                l_srvDetailsResponse.commissionAttainTotal = l_appliRequiredSrv.getMinCommAmt();
                l_srvDetailsResponse.offerType = l_appliRequiredSrv.getSupplyDiv();
                l_srvDetailsResponse.setAbleCommissionConditions = l_ableCommissionConditions;
                l_srvDetailsResponse.applyCommissionConditions = l_applyCommissionConditions;
                l_srvDetailsResponse.freeTargetPeriod = l_strFreeTargetPeriod;
            }
            else
            {
                l_srvDetailsResponse.commissionAttainTotal = null;
                l_srvDetailsResponse.offerType = null;
                l_srvDetailsResponse.setAbleCommissionConditions = null;
                l_srvDetailsResponse.applyCommissionConditions = null;
                l_srvDetailsResponse.freeTargetPeriod = null;
            }


        }
        else
        {
            log.debug("is申込要( )!＝要");
            
            l_srvDetailsResponse.summary = null;
            l_srvDetailsResponse.elePigeonDiv = false;
        
            l_srvDetailsResponse.chargeInfo = null;  
            l_srvDetailsResponse.trialDiv = null;
            l_srvDetailsResponse.trialPeriod = null;
            l_srvDetailsResponse.applyAbleStartDate = null;
            l_srvDetailsResponse.applyAbleEndDate = null;
         
            l_srvDetailsResponse.serviceContent = null;
            l_srvDetailsResponse.explainURL = null;
            l_srvDetailsResponse.confirmMailDiv = null;
        
            l_srvDetailsResponse.noticeMailDiv = null;
            l_srvDetailsResponse.noticeMailDate = null;
        
            l_srvDetailsResponse.lotteryDiv = null;
            
            l_srvDetailsResponse.applyInfo = null;
            
            l_srvDetailsResponse.commissionAttainTotal = null;
            l_srvDetailsResponse.offerType = null;
            l_srvDetailsResponse.setAbleCommissionConditions = null;
            l_srvDetailsResponse.applyCommissionConditions = null;            
        }
        
        if (l_blnIsDirAdmin)
        {
            l_srvDetailsResponse.url = l_srvMaster.getSrvUrl();
            //第２URL=サービスマスターオブジェクト.get第２URL( )(*6) 
            l_srvDetailsResponse.url2 = l_srvMaster.getUrl2();
            //ハッシュ計算方式区分=サービスマスターオブジェクト.getハッシュ計算方式区分( )(*6) 
            l_srvDetailsResponse.hashCalHowToDiv = l_srvMaster.getHashCalHowToDiv();
            //ハッシュ計算手順区分=サービスマスターオブジェクト.getハッシュ計算手順区分( )(*6) 
            l_srvDetailsResponse.hashCalOrderDiv = l_srvMaster.getHashCalOrderDiv(); 
            //送信方法@区分=サービスマスターオブジェクト.get送信方法@区分( )(*6) 
            l_srvDetailsResponse.sendHowToDiv = l_srvMaster.getSendHowToDiv();
            //送信パラメータ区分=サービスマスターオブジェクト.get送信パラメータ区分( )(*6) 
            l_srvDetailsResponse.sendParamDiv = l_srvMaster.getSendParamDiv(); 
            //暗号化顧客コード区分=サービスマスターオブジェクト.get暗号化顧客コード区分( )(*6) 
            l_srvDetailsResponse.cryptAccountCodeDiv = l_srvMaster.getCryptAccountCodeDiv(); 
            //ハッシュ値一覧=<繰り返し処理 *2>で作成した配列(*5)
            l_srvDetailsResponse.hashList = l_hashExecKeys; 
            //送信パラメータ一覧=<繰り返し処理 *3>で作成した配列(*5) 
            l_srvDetailsResponse.paramList = l_paramExecKeys; 
        }
        
        log.exiting(STR_METHOD_NAME);         
        
        return l_srvDetailsResponse;
    }
}
@
