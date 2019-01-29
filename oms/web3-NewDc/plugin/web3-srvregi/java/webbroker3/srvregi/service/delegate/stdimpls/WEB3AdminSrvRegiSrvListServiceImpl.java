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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�XImpl(WEB3AdminSrvRegiSrvListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���o�� �V�K�쐬
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
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiSrvListServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiSrvListService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
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
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ꗗ�v�Q��<BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁj�T�[�r�X�ꗗ�v): <BR>
     *   1.5.: �����򏈗��������`�F�b�N���ʂ̔���<BR>
     *   �@@�\�J�e�S���R�[�h���u�T�[�r�X�v�̏ꍇ���A�u�ڋq�v�̏ꍇ���`�F�b�N�G���[��<BR> 
     *   �Ȃ����ꍇ�́A��O���X���[����B�u�����G���[�v<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01057<BR>
     * ==========================================================<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
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
        
        //1.2 getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����
        int l_intValidateCnt = 0;
        try
        { 
            log.debug("validate����1");
            l_administrator.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, false); //WEB3BaseException
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("validate���� error 1");
            log.debug(STR_METHOD_NAME,l_ex);
            l_intValidateCnt = l_intValidateCnt + 1;
        }
        
        //1.4 validate����
        try
        {
            log.debug("validate����2");
            l_administrator.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, false); //WEB3BaseException
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("validate���� error 2");   
            log.debug(STR_METHOD_NAME,l_ex);
            l_intValidateCnt = l_intValidateCnt + 1; 
        }
        
        //1.5 �����򏈗��������`�F�b�N���ʂ̔���
        if (l_intValidateCnt == 2)
        {
            log.debug("�����`�F�b�N���ʂ̔���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01057,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 get�،���ЃR�[�h
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.7 isDIR�Ǘ���
        boolean l_blnDirAdministrator = l_administrator.isDirAdministrator();
        
        //1.8 get�T�[�r�X�}�X�^�[�ꗗ
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
        
        //1.9 <���ݓ����̎擾>
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //1.10  <�J��Ԃ����� *1>       
        List l_lisAdminSrvRegiNoLotteryGroup = new ArrayList();
        List l_lisAdminSrvRegiLotteryGroup = new ArrayList();
        List l_lisAdminSrvRegiNoApplyGroup = new ArrayList();
        
        int l_intLength = l_srvRegiServiceMasters.length;
        for (int i = 0; i < l_intLength; i++)
        { 
            //1.10.2 <���򏈗� *1>  
            if (l_srvRegiServiceMasters[i].isAppliRequired())
            {
                log.debug("<���򏈗� *1>");
                //1.10.2.1 get�\���v�T�[�r�X
                WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
                    l_srvRegiServiceMasters[i].getAppliRequiredSrv(false);
                if (l_srvRegiApplicationRequiredService == null)
                {
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + STR_METHOD_NAME);    
                }    
                
                //1.10.2.1.1get���I�ݒ� 
                String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();
                
                //1.10.2.2 <���򏈗� *1>
                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
                {                   
                    log.debug("1.10.2.2 <���򏈗� *1>");
                    //1.10.2.2.1�T�[�r�X���p�Ǘ��Ғ��I���T�[�r�X���׏��ꗗ�s
                    WEB3AdminSrvRegiNoLotteryGroup l_adminSrvRegiNoLotteryGroup = new WEB3AdminSrvRegiNoLotteryGroup();
                    
                    //1.10.2.2.2 get�T�[�r�X���p���ԗ����ꗗ( )
                    WEB3SrvRegiServiceUsePeriodAmt[] l_srvRegiServiceUsePeriodAmts = 
                        l_srvRegiServiceMasters[i].getSrvUseTermAmtList();
                    
                    //10.2.2.3  <�J��Ԃ����� *2>
                    //10.2.2.3.1 �T�[�r�X���p���ԗ������
                    int l_intLen = l_srvRegiServiceUsePeriodAmts.length;
                    WEB3SrvRegiChargeInfo[] l_srvRegiChargeInfos = new WEB3SrvRegiChargeInfo[l_intLen];
                    
                    for (int j = 0; j < l_intLen; j++)
                    {
                        log.debug("<�v���p�e�B�E�Z�b�g *1>");
                        l_srvRegiChargeInfos[j] = new WEB3SrvRegiChargeInfo();
                        //10.2.2.3.2 <�v���p�e�B�E�Z�b�g *1>
                        //�����p����ID=�擾�����T�[�r�X���p���ԗ����I�u�W�F�N�g.get�ʔ�( )
                        l_srvRegiChargeInfos[j].chargeId = 
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmts[j].getConsecutiveNumbers()); 
                        //�����p���ԒP�ʋ敪=�擾�����T�[�r�X���p���ԗ����I�u�W�F�N�g.get���p���ԋ敪( )
                        l_srvRegiChargeInfos[j].chargeDiv = l_srvRegiServiceUsePeriodAmts[j].getSrvUsePeriodDiv(); 
                        //�����p����=�擾�����T�[�r�X���p���ԗ����I�u�W�F�N�g.get���p����( )
                        l_srvRegiChargeInfos[j].chargePeriod = 
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmts[j].getSrvUsePeriod()); 
                        //�����p����=�擾�����T�[�r�X���p���ԗ����I�u�W�F�N�g.get���p����( )
                        l_srvRegiChargeInfos[j].chargeAmt = 
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmts[j].getUseAmt()); 
                        //�������敪="�L��"
                        l_srvRegiChargeInfos[j].invalidDiv = false;
                    }
                    
                    //1.10.2.2.4<�v���p�e�B�E�Z�b�g *2>
                    //��ID=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )
                    l_adminSrvRegiNoLotteryGroup.serviceDiv = l_srvRegiServiceMasters[i].getSrvDiv(); 
                    //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )
                    l_adminSrvRegiNoLotteryGroup.serviceName = l_srvRegiServiceMasters[i].getSrvName(); 
                    //���X�e�[�^�X=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�X�e�[�^�X( )
                    l_adminSrvRegiNoLotteryGroup.serviceStatus = l_srvRegiServiceMasters[i].getStatus(); 
                    //���\���\���Ԑݒ�=(*1) 
                    //(*1-1) �\���v�T�[�r�X�I�u�W�F�N�g.get�\���\���ԁi���j( )�Aget�\���\���ԁi���j( ) 
                    //�̖߂�l�������Ƃ�null�������ꍇ�A"��"���Z�b�g����B
                    if (l_srvRegiApplicationRequiredService.getAppliDateFrom() == null &&
                        l_srvRegiApplicationRequiredService.getAppliDateTo() == null)
                    {
                        log.debug("�����Ƃ�null�������ꍇ�A�����Z�b�g����");
                        l_adminSrvRegiNoLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT; 
                    }
                    //(*1-2) �\���v�T�[�r�X�I�u�W�F�N�g.get�\���\���ԁi���j( )�Aget�\���\���ԁi���j( ) 
                    //�̖߂�l�������Ƃ�null�ȊO�������ꍇ�A"�L"���Z�b�g����B
                    if (l_srvRegiApplicationRequiredService.getAppliDateFrom() != null &&
                        l_srvRegiApplicationRequiredService.getAppliDateTo() != null)
                    {
                        log.debug("�߂�l�������Ƃ�null�ȊO�������ꍇ�A�L���Z�b�g����");
                        l_adminSrvRegiNoLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE; 
                    } 
                    //�����p���ԗ������=<�J��Ԃ����� *2>�ō쐬�����z��
                    l_adminSrvRegiNoLotteryGroup.chargeInfo = l_srvRegiChargeInfos;                     
                    //�����p���ԒP�ʋ敪=�\���v�T�[�r�X�I�u�W�F�N�g.get���p���ԋ敪( )
                    l_adminSrvRegiNoLotteryGroup.trialDiv = l_srvRegiApplicationRequiredService.getTrialPeriodDiv();                    
                    //�����p����=�\���v�T�[�r�X�I�u�W�F�N�g.get���p����( )
                    if (l_srvRegiApplicationRequiredService.getTrialPeriod() == null)
                    {
                        l_adminSrvRegiNoLotteryGroup.trialPeriod = null; 
                        log.debug("get���p���� == null");
                    }
                    else
                    {
                        log.debug("get���p���� != null");
                        l_adminSrvRegiNoLotteryGroup.trialPeriod = 
                            l_srvRegiApplicationRequiredService.getTrialPeriod().toString(); 
                    }
   
                        
                    l_lisAdminSrvRegiNoLotteryGroup.add(l_adminSrvRegiNoLotteryGroup);                         
                }
                //1.10.2.3 <���򏈗� *3>   
                else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
                {
                    log.debug("1.10.2.3 <���򏈗� *3> ");
                    //1.10.2.3.1 get�T�[�r�X���I���
                    WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                        l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvRegiServiceMasters[i].getSrvDiv(), l_tsSystemTimestamp, 0);
                        
                    //1.10.2.3.2  <���򏈗� *4>  
                    WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo2 = null;
                    if (l_srvRegiServiceLotInfo == null)
                    {
                        //1.10.2.3.2.1 get�T�[�r�X���I���
                        log.debug("1.10.2.3.2.1 get�T�[�r�X���I���");  
                        l_srvRegiServiceLotInfo2 =
                            l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvRegiServiceMasters[i].getSrvDiv(), l_tsSystemTimestamp, 1);
                    }
                    
                    //1.10.2.3.3 <���򏈗� *5>
                    WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo3 = null;
                    if (l_srvRegiServiceLotInfo == null && l_srvRegiServiceLotInfo2 == null)
                    {
                        //1.10.2.3.3.1 get�T�[�r�X���I���
                        log.debug("<���򏈗� *5>");
                        l_srvRegiServiceLotInfo3 = 
                            l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_srvRegiServiceMasters[i].getSrvDiv(), l_tsSystemTimestamp, -1);
                    }
                    
                    //1.10.2.3.4 �T�[�r�X���p�Ǘ��Ғ��I�L�T�[�r�X���׏��ꗗ�s
                    WEB3AdminSrvRegiLotteryGroup l_adminSrvRegiLotteryGroup = new WEB3AdminSrvRegiLotteryGroup();
                    
                    //1.10.2.3.6  <�v���p�e�B�E�Z�b�g *3>
                    //��ID=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )
                    l_adminSrvRegiLotteryGroup.serviceDiv = l_srvRegiServiceMasters[i].getSrvDiv(); 
                    //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )
                    l_adminSrvRegiLotteryGroup.serviceName = l_srvRegiServiceMasters[i].getSrvName(); 
                    //���X�e�[�^�X=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�X�e�[�^�X( ) 
                    l_adminSrvRegiLotteryGroup.serviceStatus = l_srvRegiServiceMasters[i].getStatus();
                    //���\���\���Ԑݒ�=true 
                    l_adminSrvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE;
                    
                    //�T�[�r�X���I���I�u�W�F�N�g���擾�ł����ꍇ
                    if (l_srvRegiServiceLotInfo != null)
                    {
                        log.debug("�T�[�r�X���I���I�u�W�F�N�g���擾�ł����ꍇ");
                        //���^�p�敪=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�^�p�敪( )
                        l_adminSrvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo.getInvestDiv(); 
                        //���\�����ԁi���j=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�\�����ԁi���j( )
                        l_adminSrvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo.getAppliDateFrom(); 
                        //���\�����ԁi���j=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�\�����ԁi���j( ) 
                        l_adminSrvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo.getAppliDateTo();
                        //�����I��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���I��( ) 
                        l_adminSrvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo.getLotDate();
                        //���K�p�J�n��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�K�p�J�n��( ) 
                        l_adminSrvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo.getAppliStartDate();
                        //���K�p�I����=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�K�p�I����( ) 
                        l_adminSrvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo.getAppliEndDate();
                        //�����p����=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���p����( ) 
                        l_adminSrvRegiLotteryGroup.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo.getUseAmt());
                        //�����D�P��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���D�P��( )  
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
                        //���^�p�敪=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�^�p�敪( )
                        l_adminSrvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo2.getInvestDiv(); 
                        //���\�����ԁi���j=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�\�����ԁi���j( )
                        l_adminSrvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo2.getAppliDateFrom(); 
                        //���\�����ԁi���j=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�\�����ԁi���j( ) 
                        l_adminSrvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo2.getAppliDateTo();
                        //�����I��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���I��( ) 
                        l_adminSrvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo2.getLotDate();
                        //���K�p�J�n��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�K�p�J�n��( ) 
                        l_adminSrvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo2.getAppliStartDate();
                        //���K�p�I����=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�K�p�I����( ) 
                        l_adminSrvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo2.getAppliEndDate();
                        //�����p����=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���p����( ) 
                        l_adminSrvRegiLotteryGroup.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo2.getUseAmt());
                        //�����D�P��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���D�P��( )  
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
                        //���^�p�敪=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�^�p�敪( )
                        l_adminSrvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo3.getInvestDiv(); 
                        //���\�����ԁi���j=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�\�����ԁi���j( )
                        l_adminSrvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo3.getAppliDateFrom(); 
                        //���\�����ԁi���j=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�\�����ԁi���j( ) 
                        l_adminSrvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo3.getAppliDateTo();
                        //�����I��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���I��( ) 
                        l_adminSrvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo3.getLotDate();
                        //���K�p�J�n��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�K�p�J�n��( ) 
                        l_adminSrvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo3.getAppliStartDate();
                        //���K�p�I����=�擾�����T�[�r�X���I���I�u�W�F�N�g.get�K�p�I����( ) 
                        l_adminSrvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo3.getAppliEndDate();
                        //�����p����=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���p����( ) 
                        l_adminSrvRegiLotteryGroup.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo3.getUseAmt());
                        //�����D�P��=�擾�����T�[�r�X���I���I�u�W�F�N�g.get���D�P��( )  
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
                        // �T�[�r�X���I���I�u�W�F�N�g���擾�ł��Ȃ������ꍇ 
                        log.debug("�T�[�r�X���I���I�u�W�F�N�g���擾�ł��Ȃ������ꍇ");
                        //���^�p�敪=null 
                        l_adminSrvRegiLotteryGroup.useDiv = null;
                        //���\�����ԁi���j=null
                        l_adminSrvRegiLotteryGroup.applyStartDate = null; 
                        //���\�����ԁi���j=null
                        l_adminSrvRegiLotteryGroup.applyEndDate = null; 
                        //�����I��=null
                        l_adminSrvRegiLotteryGroup.lotteryDate = null;
                        //���K�p�J�n��=null
                        l_adminSrvRegiLotteryGroup.trialStartDate = null; 
                        //���K�p�I����=null
                        l_adminSrvRegiLotteryGroup.trialEndDate = null; 
                        //�����p����=null
                        l_adminSrvRegiLotteryGroup.chargeAmt = null; 
                        //�����D�P��=null
                        l_adminSrvRegiLotteryGroup.biddingPriceUnit = null;
                    }
                    l_lisAdminSrvRegiLotteryGroup.add(l_adminSrvRegiLotteryGroup);
                }
            }
            //1.10.2.4 <���򏈗� *6>
            else if(!l_srvRegiServiceMasters[i].isAppliRequired())
            {   
                log.debug("<���򏈗� *6>");
                //1.10.2.4.1 �T�[�r�X���p�Ǘ��Ґ\���s�v�T�[�r�X���׏��ꗗ�s
                WEB3AdminSrvRegiNoApplyGroup l_adminSrvRegiNoApplyGroup = new WEB3AdminSrvRegiNoApplyGroup();
                //1.10.2.4.2 <�v���p�e�B�E�Z�b�g *4>
                //��ID=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )
                l_adminSrvRegiNoApplyGroup.serviceDiv = l_srvRegiServiceMasters[i].getSrvDiv(); 
                //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( ) 
                l_adminSrvRegiNoApplyGroup.serviceName = l_srvRegiServiceMasters[i].getSrvName();
                //���X�e�[�^�X=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�X�e�[�^�X( ) 
                l_adminSrvRegiNoApplyGroup.serviceStatus = l_srvRegiServiceMasters[i].getStatus();
                //���\���\���Ԑݒ�=false
                l_adminSrvRegiNoApplyGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT;
                l_lisAdminSrvRegiNoApplyGroup.add(l_adminSrvRegiNoApplyGroup);
            }
        }
        //create���X�|���X
        log.debug("create���X�|���X");
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
