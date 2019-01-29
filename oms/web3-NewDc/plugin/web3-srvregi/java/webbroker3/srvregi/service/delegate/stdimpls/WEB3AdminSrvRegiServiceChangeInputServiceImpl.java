head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス変更入力サービスImpl(WEB3AdminSrvRegiServiceChangeInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
Revesion History : 2007/06/05 孟亜南(中訊) 仕様変更モデルNo.250 No.253 No.257
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
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
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
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCommissionCondition;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.message.WEB3SrvRegiExecKey;
import webbroker3.srvregi.message.WEB3SrvRegiLotteryInfo;
import webbroker3.srvregi.message.WEB3SrvRegiSetAbleCommissionCondition;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeInputService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者サービス変更入力サービスImpl)<BR>
 * サービス利用管理者サービス変更入力サービス実装クラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeInputServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceChangeInputService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceChangeInputServiceImpl.class);

    /**
     * @@roseuid 416F392B02BF
     */
    public WEB3AdminSrvRegiServiceChangeInputServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者サービス変更入力処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）サービス変更入力」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス変更入力」): <BR>
     *         1.7:isAppliRequired( )<BR>
     *         1.8.isDIR管理者( )<BR>
     *         管理者オブジェクト.isDIR管理者( )=false、<BR>
     *         is申込要( )=falseの場合、例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00985<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5173F0008
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
        WEB3AdminSrvRegiServiceChangeInputRequest l_srvChangeInputRequest = (WEB3AdminSrvRegiServiceChangeInputRequest)l_request;
        l_srvChangeInputRequest.validate();
        
        //1.3:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE,true);
        
        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6:getサービスマスター(String, String, boolean)
        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvMaster = 
            l_srvInfoManagement.getSrvMaster(l_strInstitutionCode, l_srvChangeInputRequest.serviceDiv, false);
        
        //1.7:is申込要( )
        boolean l_blnIsAppliRequired = l_srvMaster.isAppliRequired();
        
        //1.8:isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();
        
        if ((!l_blnIsAppliRequired) && (!l_blnIsDirAdmin))
        {
            log.debug("1.7:is申込要( )=false and 1.8:isDIR管理者( )=false");
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00985, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3SrvRegiApplicationRequiredService l_appliRequiredSrv = null;
        WEB3SrvRegiChargeInfo[] l_chargeInfos = null;
        WEB3SrvRegiLotteryInfo[] l_lotteryInfos = null;
        WEB3SrvRegiSetAbleCommissionCondition[] l_ableCommissionConditions = null;
        WEB3SrvRegiApplyCommissionCondition[] l_applyCommissionConditions = null;
        String l_strFreeTargetPeriod = null;
        
        //1.9:<is申込要( )=trueの場合>
        if (l_blnIsAppliRequired)
        {
            log.debug("1.9:<is申込要( )=trueの場合>");
            
            //1.9.1: get申込要サービス(boolean)
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
                
                if (l_intSrvUsePeriodAmtCnt > 0)
                {                    
                    l_chargeInfos = new WEB3SrvRegiChargeInfo[l_intSrvUsePeriodAmtCnt];
                
                    for (int i = 0; i < l_intSrvUsePeriodAmtCnt; i++)
                    {   
                        log.debug("loop count:" + i);
                        
                        //1.9.2.2.1: サービス利用期間料金情報( )
                        l_chargeInfos[i] = new WEB3SrvRegiChargeInfo();
                    
                        //1.9.2.2.2:<プロパティ・セット *1>
                        l_chargeInfos[i].chargeId = String.valueOf(l_srvUsePeriodAmts[i].getConsecutiveNumbers());
                        l_chargeInfos[i].chargeDiv = l_srvUsePeriodAmts[i].getSrvUsePeriodDiv();
                        l_chargeInfos[i].chargePeriod = String.valueOf(l_srvUsePeriodAmts[i].getSrvUsePeriod());
                        l_chargeInfos[i].chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmts[i].getUseAmt());
                        l_chargeInfos[i].invalidDiv = false;
                    }                    
                }
                log.debug("get手数料条件");
            
                //1.9.2.3:get手数料条件一覧(String)
                List l_lisCommCondMasterList = l_srvInfoManagement.getCommCondList(l_strInstitutionCode);
                int l_intCommCondMasterCnt = l_lisCommCondMasterList.size();
            
                log.debug("l_intCommCondMasterCnt:" + l_intCommCondMasterCnt);
              
                if (l_intCommCondMasterCnt > 0)
                {
                    l_ableCommissionConditions = new WEB3SrvRegiSetAbleCommissionCondition[l_intCommCondMasterCnt];
                    //1.9.2.4: <繰り返し処理 *4>
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

                if ( l_lisCommCondRowList != null && l_lisCommCondRowList.size() > 0)
                {
                    int l_intCommCondRowCnt = l_lisCommCondRowList.size();
                
                    l_applyCommissionConditions = new WEB3SrvRegiApplyCommissionCondition[l_intCommCondRowCnt];
                    //1.9.2.6: <繰り返し処理 *5>
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
            
 
            //1.9.3:<分岐処理 *2>
            if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
            {
                log.debug("1.9.2.3:<分岐処理 *2>");
                
                //1.9.3.1 getサービス抽選情報一覧( )
                WEB3SrvRegiServiceLotInfo[] l_srvLotInfos = l_srvMaster.getSrvLotInfoList();
                    
                int l_intSrvLotInfoCnt = l_srvLotInfos.length;
                
                log.debug("l_intSrvLotInfoCnt:" + l_intSrvLotInfoCnt);
                    
                if (l_intSrvLotInfoCnt > 0)
                {
                    WEB3SrvRegiLotteryInfo[] l_lotteryInfos2 = 
                        new WEB3SrvRegiLotteryInfo[l_intSrvLotInfoCnt];
                
                    int l_intCount = 0;
                    
                    //1.9.3.2:<繰り返し処理 *2>
                    for (int i = 0; i < l_intSrvLotInfoCnt; i++)
                    {
                        log.debug("loop count:" + i);
                        
                        Timestamp l_tsAppliDateFrom = l_srvLotInfos[i].getAppliDateFrom();
                        int l_intCompareTo = 
                            WEB3DateUtility.compareToSecond(l_tsAppliDateFrom,GtlUtils.getTradingSystem( ).getSystemTimestamp( ));
                        Timestamp l_tsAppliDateTo = 
                            l_srvLotInfos[i].getAppliDateTo();
                        int l_intCompareTo2 = 
                            WEB3DateUtility.compareToSecond(l_tsAppliDateTo,GtlUtils.getTradingSystem( ).getSystemTimestamp( ));
                        
                        //1.9.3.2.1:<分岐処理 *3>
                        if ((l_blnIsDirAdmin  && l_intCompareTo2 >= 0) || (!l_blnIsDirAdmin && l_intCompareTo > 0))
                        {
                            
                            log.debug("1.9.2.3.2.1:<分岐処理 *3>");
                            
                            //1.9.3.2.1.1:サービス利用抽選情報( )
                            l_lotteryInfos2[i] = new WEB3SrvRegiLotteryInfo();
                            
                            //1.9.3.2.1.2: <プロパティ・セット *2>
                            l_lotteryInfos2[i].lotteryId = String.valueOf(l_srvLotInfos[i].getConsecutiveNumbers());
                            l_lotteryInfos2[i].useDiv = l_srvLotInfos[i].getInvestDiv();
                            l_lotteryInfos2[i].applyStartDate = l_srvLotInfos[i].getAppliDateFrom();
                            l_lotteryInfos2[i].applyEndDate = l_srvLotInfos[i].getAppliDateTo();
                            l_lotteryInfos2[i].lotteryDate = l_srvLotInfos[i].getLotDate();
                            l_lotteryInfos2[i].trialStartDate = l_srvLotInfos[i].getAppliStartDate();
                            l_lotteryInfos2[i].trialEndDate = l_srvLotInfos[i].getAppliEndDate();
                            l_lotteryInfos2[i].chargeAmt = String.valueOf(l_srvLotInfos[i].getUseAmt());
                            
                            if (l_srvLotInfos[i].getBiddingPrice() == null)
                            {
                                l_lotteryInfos2[i].biddingPriceUnit = null;
                            }
                            else
                            {
                                l_lotteryInfos2[i].biddingPriceUnit = l_srvLotInfos[i].getBiddingPrice().toString();
                            }
                            
                            l_lotteryInfos2[i].paymentDate = l_srvLotInfos[i].getPaymentDate();
                            
                            if (l_srvLotInfos[i].getPublicOfferingQty() == null)
                            {
                                l_lotteryInfos2[i].applyMax = null;
                            }
                            else
                            {
                                l_lotteryInfos2[i].applyMax = l_srvLotInfos[i].getPublicOfferingQty().toString();
                            }
                            
                            l_lotteryInfos2[i].invalidDiv = false;
                        }
                        else
                        {
                            l_lotteryInfos2[i] = null;
                            l_intCount++;
                        }
                    }
                    
                    if (l_intSrvLotInfoCnt-l_intCount > 0)
                    {
                        l_lotteryInfos = new WEB3SrvRegiLotteryInfo[l_intSrvLotInfoCnt-l_intCount];
                        int l_intCount2 = 0;
                        for (int i = 0; i < l_intSrvLotInfoCnt; i++)
                        {
                            if (l_lotteryInfos2[i] != null)
                            {
                                l_lotteryInfos[l_intCount2] = l_lotteryInfos2[i];
                                l_intCount2++;
                            }
                        } 
                    } 
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
                //1.10.2:<繰り返し処理 *2>
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
                    //1.10.4.1:サービス利用起動キー( )
                    l_paramExecKeys[i] = new WEB3SrvRegiExecKey();

                    //1.10.4.2:<プロパティ・セット *3>
                    l_paramExecKeys[i].keyKindDiv = l_useKeys2[i].getSrvUseKeyType();
                    l_paramExecKeys[i].keyId = String.valueOf(l_useKeys2[i].getSrvUseKeyId());
                    l_paramExecKeys[i].key = l_useKeys2[i].getSrvUseKey();
                    l_paramExecKeys[i].invalidDiv = false;
                }
            }
        }

        //1.11:get確認メール情報( )
        WEB3GentradeMailInfo l_comfirmMailInfo = l_srvMaster.getConfirmMailInfo();
        
        //1.12:get契約期限メール情報( )
        WEB3GentradeMailInfo l_endMailInfo = l_srvMaster.getEndMaiDivInfo();
        
        //1.17:createレスポンス( )
        WEB3AdminSrvRegiServiceChangeInputResponse l_srvChangeInputResponse = 
            (WEB3AdminSrvRegiServiceChangeInputResponse)l_srvChangeInputRequest.createResponse();
        
        //1.18:<レスポンス・セット>
        l_srvChangeInputResponse.serviceDiv = l_srvMaster.getSrvDiv();
        l_srvChangeInputResponse.serviceName = l_srvMaster.getSrvName();
        l_srvChangeInputResponse.serviceStatus = l_srvMaster.getStatus();
        l_srvChangeInputResponse.consentSentence = l_srvMaster.getConsDoc(false).getCons();

        if (l_comfirmMailInfo == null)
        {   
            log.debug("l_comfirmMailInfo == null");
            
            l_srvChangeInputResponse.confirmMailFrom = null;
            l_srvChangeInputResponse.confirmMailSubject = null;
            l_srvChangeInputResponse.confirmMailHeader = null;
            l_srvChangeInputResponse.confirmMailBody = null;
            l_srvChangeInputResponse.confirmMailFooter = null;
        }
        else
        {
            log.debug("l_comfirmMailInfo != null");
            
            l_srvChangeInputResponse.confirmMailFrom = l_comfirmMailInfo.getMailSender();
            l_srvChangeInputResponse.confirmMailSubject = l_comfirmMailInfo.getSubject();
            l_srvChangeInputResponse.confirmMailHeader = l_comfirmMailInfo.getMailHeader();
            l_srvChangeInputResponse.confirmMailBody = l_comfirmMailInfo.getMailText();
            l_srvChangeInputResponse.confirmMailFooter = l_comfirmMailInfo.getMailFooter();
        }
        
        if (l_endMailInfo == null)
        {   
            log.debug("l_endMailInfo == null");
            
            l_srvChangeInputResponse.noticeMailFrom = null;
            l_srvChangeInputResponse.noticeMailSubject = null;
            l_srvChangeInputResponse.noticeMailHeader = null;
            l_srvChangeInputResponse.noticeMailBody = null;
            l_srvChangeInputResponse.noticeMailFooter = null;
        }
        else
        {
            log.debug("l_endMailInfo != null");
            
            l_srvChangeInputResponse.noticeMailFrom = l_endMailInfo.getMailSender();
            l_srvChangeInputResponse.noticeMailSubject = l_endMailInfo.getSubject();
            l_srvChangeInputResponse.noticeMailHeader = l_endMailInfo.getMailHeader();
            l_srvChangeInputResponse.noticeMailBody = l_endMailInfo.getMailText();
            l_srvChangeInputResponse.noticeMailFooter = l_endMailInfo.getMailFooter();
        }          
        
        //is申込要( )＝"要"だった場合
        if (l_blnIsAppliRequired)
        {
            log.debug("is申込要( )＝要だった場合");
            
            l_srvChangeInputResponse.summary = l_appliRequiredSrv.getSummary();
            l_srvChangeInputResponse.applyDiv = WEB3SrvRegiOfferingDivDef.REQUIRE;
            l_srvChangeInputResponse.elePigeonDiv = l_appliRequiredSrv.isElectricIssue();
        
            l_srvChangeInputResponse.chargeInfo = l_chargeInfos;
            l_srvChangeInputResponse.trialDiv = l_appliRequiredSrv.getTrialPeriodDiv();
            
            if (l_appliRequiredSrv.getTrialPeriod() == null)
            {
                l_srvChangeInputResponse.trialPeriod = null;
            }
            else
            {
                l_srvChangeInputResponse.trialPeriod = l_appliRequiredSrv.getTrialPeriod().toString();
            }
            
            if (l_appliRequiredSrv.getAppliDateFrom() == null)
            {
                l_srvChangeInputResponse.applyAbleStartDate = null;                
            }
            else
            {
                l_srvChangeInputResponse.applyAbleStartDate = l_appliRequiredSrv.getAppliDateFrom().toString();
            }

            if (l_appliRequiredSrv.getAppliDateTo() == null)
            {
                l_srvChangeInputResponse.applyAbleEndDate = null;                
            }
            else
            {
                l_srvChangeInputResponse.applyAbleEndDate = l_appliRequiredSrv.getAppliDateTo().toString();
            }
            
            l_srvChangeInputResponse.serviceContent = l_appliRequiredSrv.getSrvContents();
            l_srvChangeInputResponse.explainURL = l_appliRequiredSrv.getSrvExplanUrl();
            
            l_srvChangeInputResponse.confirmMailDiv = l_appliRequiredSrv.getStartMailDiv();
            
            l_srvChangeInputResponse.noticeMailDiv = l_appliRequiredSrv.getEndMailDiv();
            
            if (l_appliRequiredSrv.getSendMailInterval() == null)
            {
                l_srvChangeInputResponse.noticeMailDate =null;                
            }
            else
            {
                l_srvChangeInputResponse.noticeMailDate = l_appliRequiredSrv.getSendMailInterval().toString();
            }
            
            l_srvChangeInputResponse.lotteryDiv = l_appliRequiredSrv.getLotDiv();
            l_srvChangeInputResponse.applyInfo = l_lotteryInfos;
            
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_appliRequiredSrv.getLotDiv()))
            {
                l_srvChangeInputResponse.commissionAttainTotal = l_appliRequiredSrv.getMinCommAmt();
                l_srvChangeInputResponse.offerType = l_appliRequiredSrv.getSupplyDiv();
                l_srvChangeInputResponse.setAbleCommissionConditions = l_ableCommissionConditions;
                l_srvChangeInputResponse.applyCommissionConditions = l_applyCommissionConditions;
                //無料対象期間
                l_srvChangeInputResponse.freeTargetPeriod = l_strFreeTargetPeriod;
            }
            else
            {
                l_srvChangeInputResponse.commissionAttainTotal = null;
                l_srvChangeInputResponse.offerType = null;
                l_srvChangeInputResponse.setAbleCommissionConditions = null;
                l_srvChangeInputResponse.applyCommissionConditions = null;
                l_srvChangeInputResponse.freeTargetPeriod = null;
            }
            

            
        }
        else
        {
            log.debug("is申込要( )!=要だった場合");
            
            l_srvChangeInputResponse.summary = null;
            l_srvChangeInputResponse.applyDiv = WEB3SrvRegiOfferingDivDef.NOT_REQUIRE;
            l_srvChangeInputResponse.elePigeonDiv = false;
        
            l_srvChangeInputResponse.chargeInfo = null;
            l_srvChangeInputResponse.trialDiv = null;
            l_srvChangeInputResponse.trialPeriod =  null;
            l_srvChangeInputResponse.applyAbleStartDate = null;
            l_srvChangeInputResponse.applyAbleEndDate = null;
        
            l_srvChangeInputResponse.serviceContent = null;
            l_srvChangeInputResponse.explainURL = null;
        
            l_srvChangeInputResponse.confirmMailDiv = null;
        
            l_srvChangeInputResponse.noticeMailDiv = null;
            l_srvChangeInputResponse.noticeMailDate = null;
            
            l_srvChangeInputResponse.lotteryDiv = null;
            l_srvChangeInputResponse.applyInfo = null;
            
            l_srvChangeInputResponse.commissionAttainTotal = null;
            l_srvChangeInputResponse.offerType = null;
            l_srvChangeInputResponse.setAbleCommissionConditions = null;
            l_srvChangeInputResponse.applyCommissionConditions = null;
        }
        
        if (l_blnIsDirAdmin)
        {
            l_srvChangeInputResponse.url = l_srvMaster.getSrvUrl();
            //第２URL=サービスマスターオブジェクト.get第２URL( )
            l_srvChangeInputResponse.url2 = l_srvMaster.getUrl2();
            //ハッシュ計算方式区分=サービスマスターオブジェクト.getハッシュ計算方式区分( ) 
            l_srvChangeInputResponse.hashCalHowToDiv = l_srvMaster.getHashCalHowToDiv();
            //ハッシュ計算手順区分=サービスマスターオブジェクト.getハッシュ計算手順区分( )
            l_srvChangeInputResponse.hashCalOrderDiv = l_srvMaster.getHashCalOrderDiv(); 
            //送信方法@区分=サービスマスターオブジェクト.get送信方法@区分( )
            l_srvChangeInputResponse.sendHowToDiv = l_srvMaster.getSendHowToDiv();
            //送信パラメータ区分=サービスマスターオブジェクト.get送信パラメータ区分( )
            l_srvChangeInputResponse.sendParamDiv = l_srvMaster.getSendParamDiv(); 
            //暗号化顧客コード区分=サービスマスターオブジェクト.get暗号化顧客コード区分( )
            l_srvChangeInputResponse.cryptAccountCodeDiv = l_srvMaster.getCryptAccountCodeDiv(); 
            //ハッシュ値一覧=<繰り返し処理 *2>で作成した配列(*5)
            l_srvChangeInputResponse.hashList = l_hashExecKeys; 
            //送信パラメータ一覧=<繰り返し処理 *3>で作成した配列(*5) 
            l_srvChangeInputResponse.paramList = l_paramExecKeys; 
        }
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_srvChangeInputResponse;
    }
}
@
