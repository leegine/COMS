head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceListInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�XImpl(WEB3SrvRegiServiceListInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 ���o�� �V�K�쐬
Revesion History : 2007/06/05 ���^�](���u) �d�l�ύX���f��No.241
Revesion History : 2007/06/08 ����(���u) �d�l�ύX���f��No.261
Revesion History : 2007/11/01 ����(���u) �d�l�ύX���f��No.304
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiChargeParams;
import webbroker3.srvregi.define.WEB3SrvRegiFreeAttributeApplyDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.message.WEB3SrvRegiLotteryGroup;
import webbroker3.srvregi.message.WEB3SrvRegiNoLotteryGroup;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceRequest;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceListInquiryService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

/**
 * (�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X�����N���X<BR>
 *
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiServiceListInquiryServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiServiceListInquiryService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceListInquiryServiceImpl.class);

    /**
     * @@roseuid 416F39270167
     */
    public WEB3SrvRegiServiceListInquiryServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j���p�T�[�r�X�ꗗ�v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F7831B0234
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3SrvRegiReferenceRequest l_srvRegiReferenceRequest = (WEB3SrvRegiReferenceRequest)l_request;

		//�{�ԏ�Q�Ή�
        //1.1 getCommonOrderValidator - �폜 -

        //1.2  get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

		//�{�ԏ�Q�Ή�
        //1.3 validate����\�ڋq - �폜 -

        //1.4  validate������t�\
        log.debug("validate������t�\");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.5  get�T�[�r�X�}�X�^�[�ꗗ
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster[] l_srvMasterList =
            l_srvRegiServiceInfoManagement.getSrvMasterList(l_strInstitutionCode, WEB3SrvRegiOfferingDivDef.REQUIRE);

        List l_lisSrvRegiNoLotteryGroup = new ArrayList();
        List l_lisSrvRegiLotteryGroup = new ArrayList();

        //1.6 <�J��Ԃ����� *1>
        int l_intLength = l_srvMasterList.length;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("is�ڋq�\���\");
            //1.6.1  is�ڋq�\���\
            boolean l_blnAccountAppliPossible =
                l_srvRegiServiceInfoManagement.isAccountAppliPossible((WEB3GentradeSubAccount)l_subAccount, l_srvMasterList[i]);

            //1.6.2 is�񋟒�
            boolean l_blnProviding = l_srvMasterList[i].isProviding();

            //1.6.3  <���򏈗� *1>
            if (l_blnProviding)
            {
                log.debug("l_blnProviding");
                //1.6.3.1 get�\���v�T�[�r�X
                WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
                    l_srvMasterList[i].getAppliRequiredSrv(false);
                if (l_srvRegiApplicationRequiredService == null)
                {
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //1.6.3.1.1 get���I�ݒ�
                String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

                //1.6.3.2 <���򏈗� *2>
                WEB3SrvRegiRegistService l_srvRegiRegistService =
                    (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
                String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
                String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();

                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
                {
                    log.debug("get�񋟌`��");
                    //1.6.3.2.1  get�񋟌`��
                    String l_strProvidModel = l_srvRegiApplicationRequiredService.getSupplyDiv();
                    
                    //��Q�Ή� NO_2147                   
					//1.6.3.2.2 get�T�[�r�X�\���o�^
					WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
						l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode,
						l_srvMasterList[i].getSrvDiv(), l_strAccountCode, WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
						WEB3EffectiveDivDef.EFFECTIVE, false);

                    //get�T�[�r�X�\���������(�،���ЃR�[�h : String, ���X�R�[�h : String,
                    // �����R�[�h : String, �T�[�r�X�敪 : String, �A�b�v���[�h�敪 :)
                    List l_lisAppliAttributeInfo = l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                        l_srvMasterList[i].getInstitutionCode(),
                        l_strBranchCode,
                        l_strAccountCode,
                        l_srvMasterList[i].getSrvDiv(),
                        null);

                    boolean l_blnCommCond = false;

                    //1.6.3.2.3 <get�񋟌`��( )!= null�̏ꍇ>
                    if (l_strProvidModel != null)
                    {
                        log.debug("is�萔������");
                        //is�萔������
                        l_blnCommCond =
                            l_srvRegiServiceInfoManagement.isCommCond((WEB3GentradeSubAccount)l_subAccount, l_srvMasterList[i]);
                        
                        //��Q�Ή� NO_U01568
                        //��Q�Ή� NO_2147
                        //�񋟌`����0:(����) && is�萔������()�̖߂�l��false �̏ꍇ�A���́A
                        //�񋟌`����2:(����) && get�T�[�r�X�\���������()��null�̏ꍇ
                        
                        if ((WEB3SupplyDivDef.FREE_SUPPLY.equals(l_strProvidModel) && !l_blnCommCond) || 
                            (WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(l_strProvidModel) && l_lisAppliAttributeInfo == null))
                        {
                        	continue;
                        }
                    }

                    //1.6.3.2.4 �T�[�r�X���p���I���T�[�r�X���׏��ꗗ�s
                    WEB3SrvRegiNoLotteryGroup l_srvRegiNoLotteryGroup = new WEB3SrvRegiNoLotteryGroup();

                    //1.6.3.2.5 �����ʍ��ڃZ�b�g��
                    //��ID=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )
                    l_srvRegiNoLotteryGroup.serviceDiv = l_srvMasterList[i].getSrvDiv();
                    //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )
                    l_srvRegiNoLotteryGroup.serviceName = l_srvMasterList[i].getSrvName();
                    //���X�e�[�^�X=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�X�e�[�^�X( )
                    l_srvRegiNoLotteryGroup.serviceStatus = l_srvMasterList[i].getStatus();
                    //���\���\���Ԑݒ�=(*1)
                    //(*1-1) �\���v�T�[�r�X�I�u�W�F�N�g.get�\���\���ԁi���j( )�Aget�\���\���ԁi���j( )
                    //�̖߂�l�������Ƃ�null�������ꍇ�A"��"���Z�b�g����B
                    if (l_srvRegiApplicationRequiredService.getAppliDateFrom() == null &&
                        l_srvRegiApplicationRequiredService.getAppliDateTo() == null)
                    {
                        log.debug("�����Z�b�g����");
                        l_srvRegiNoLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT;
                    }
                    else
                    {
                        log.debug("�L���Z�b�g����");
                        l_srvRegiNoLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE;
                    }

                    //���T�[�r�X����URL=�\���v�T�[�r�X�I�u�W�F�N�g.get�T�[�r�X����URL( )
                    l_srvRegiNoLotteryGroup.explainURL = l_srvRegiApplicationRequiredService.getSrvExplanUrl();

                    // ���o�^��=(*2)
                    //(*2-1) �擾�����T�[�r�X�\���o�^�I�u�W�F�N�g=null�̏ꍇ�Anull���Z�b�g����B
                    if (l_gentradeSrvRegiApplication == null)
                    {
                        log.debug("�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g=null�̏ꍇ");
                        l_srvRegiNoLotteryGroup.registDate = null;
                        //(*2-1) �擾�����T�[�r�X�\���o�^�I�u�W�F�N�g=null�̏ꍇ�Anull���Z�b�g����
                        l_srvRegiNoLotteryGroup.useLimitDate = null;
                    }
                    else
                    {
                        log.debug("���o�^��");
                        //�T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�J�n��( )���Z�b�g����B
                        l_srvRegiNoLotteryGroup.registDate = l_gentradeSrvRegiApplication.getAppliStartDate();
                        //�T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I����( )���Z�b�g����
                        l_srvRegiNoLotteryGroup.useLimitDate = l_gentradeSrvRegiApplication.getAppliEndDate();
                    }

                    //���d�q���ݒ�敪=�擾�����\���v�T�[�r�X�I�u�W�F�N�g.is�d�q�������ݒ�( )
                    l_srvRegiNoLotteryGroup.elePigeonDiv = l_srvRegiApplicationRequiredService.isElectricIssue();

                    //���p���\���\�敪=(*4)              
					//��Q�Ή��@@NO_U01563
					//(*4-1)�񋟌`���������񋟂̏ꍇ�́Afalse ���Z�b�g����B 
					if(WEB3SupplyDivDef.FREE_SUPPLY.equals(l_strProvidModel)){						
						l_srvRegiNoLotteryGroup.continuationDiv = false;
					}
					else {
						
						Timestamp l_tsAppliEndDate = null;
						Calendar l_cal = new GregorianCalendar();
						Date l_datAppliEndDateBefore = null;
						if (l_gentradeSrvRegiApplication != null)
						{
							l_tsAppliEndDate = l_gentradeSrvRegiApplication.getAppliEndDate();
							l_cal.setTime(l_tsAppliEndDate);
							l_cal.add(Calendar.MONTH, -1);
							l_datAppliEndDateBefore = l_cal.getTime();
						}
						Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
						
						//��Q�Ή� NO_U01563
						//��Q�Ή� NO_U02061
						//(*4-2) is�ڋq�\���\()=TRUE &&
						//�u�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( ) �� "���p�\��"�v && 
						//�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I����( )�̈ꃖ���O�̓��t �� 
						//���ݓ��t(*) �� �擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I����( )�v
						//�̏ꍇ�Atrue���Z�b�g����B
	                    if (l_gentradeSrvRegiApplication != null && l_blnAccountAppliPossible &&
	                        !WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()) &&
	                        WEB3DateUtility.compareToDay(l_datAppliEndDateBefore, l_tsSystemTimestamp) <= 0 &&
	                        WEB3DateUtility.compareToDay(l_tsSystemTimestamp, l_tsAppliEndDate) <= 0)
	                    {
							log.debug("l_srvRegiNoLotteryGroup.continuationDiv = true;");
							l_srvRegiNoLotteryGroup.continuationDiv = true;
	                    }
						//(*4-3) ��L�ȊO�̏ꍇ�Afalse���Z�b�g����B
	                    else
	                    {
	                        log.debug("l_srvRegiNoLotteryGroup.continuationDiv = false;");
	                        l_srvRegiNoLotteryGroup.continuationDiv = false;
	                    }
					}
					
					//��Q�Ή�  NO_U01724
                    //���T�[�r�X���p�\�敪=(*5)
                    //(*5-1) �擾�����T�[�r�X�\���o�^�I�u�W�F�N�g=null �܂��́A
					//(*5-2) �T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X���pURL=null�̏ꍇ�Afalse���Z�b�g����B
                    if (l_gentradeSrvRegiApplication == null || l_srvMasterList[i].getSrvUrl() == null)
                    {
                        log.debug("l_gentradeSrvRegiApplication == null");
                        l_srvRegiNoLotteryGroup.useAbleDiv = false;
                    }
                    else
                    {
                        log.debug("�T�[�r�X���p�\�敪");
                        l_srvRegiNoLotteryGroup.useAbleDiv = l_srvRegiRegistService.isUsePossible(
                            l_gentradeSrvRegiApplication.getInstitutionCode(),
                            l_gentradeSrvRegiApplication.getBranchCode(),
                            l_gentradeSrvRegiApplication.getSrvDiv(),
                            l_gentradeSrvRegiApplication.getAccountCode(),
                            l_gentradeSrvRegiApplication.getRegistId());
                    }

                    //1.6.3.2.6 ���ʏ�񋟂̏ꍇ��
                    // is�萔������()=false�̏ꍇ
                    if (!l_blnCommCond)
                    {

                        //1.6.3.2.6.1 get�T�[�r�X���p���ԗ����ꗗ
                        WEB3SrvRegiServiceUsePeriodAmt[] l_srvUseTermAmtList = l_srvMasterList[i].getSrvUseTermAmtList();

                        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
                        if (l_lisAppliAttributeInfo != null)
                        {
                            l_srvAppliAttributeRow = (SrvAppliAttributeRow) l_lisAppliAttributeInfo.get(0);
                        }

                        // get�T�[�r�X�\���������I�u�W�F�N�g.�\�������敪 = '�����Ώ�' �̏ꍇ
                        String l_strFreeTargetPeriod = null;
                        if (l_srvAppliAttributeRow != null)
                        {
                            if ((WEB3AppliAttributeDef.FREE_OBJECT).equals(l_srvAppliAttributeRow.getAppliAttribute()))
                            {
                                // get�����Ώۊ���( )
                                l_strFreeTargetPeriod = l_srvRegiApplicationRequiredService.getFreeTargetPeriod();
                            }
                        }
                        
                        //1.6.3.2.6.2 <�v���p�e�B�E�Z�b�g *1-1>

                        //�����p���ԗ������=(*1)
                        //(*1-1) �T�[�r�X�}�X�^�[�I�u�W�F�N�g.�T�[�r�X���p���ԗ����ꗗ( )���R�[�����A
                        //�߂�l�̌������ȉ����J��Ԃ��A�T�[�r�X���p���ԗ������̔z����쐬����B
                        int l_intListLength = l_srvUseTermAmtList.length;
                        WEB3SrvRegiChargeInfo[] l_srvRegiChargeInfo = new WEB3SrvRegiChargeInfo[l_intListLength];
                        for (int j = 0; j < l_intListLength; j++)
                        {
                            log.debug("�T�[�r�X���p���ԗ������̔z����쐬����");
                            l_srvRegiChargeInfo[j] = new WEB3SrvRegiChargeInfo();
                            //(*1-2) �\���v�T�[�r�X���p���ԗ������N���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B
                            SrvRegiChargeParams l_srvUsePeriodAmtParams = (SrvRegiChargeParams)l_srvUseTermAmtList[j].getDataSourceObject();
                            //�����p����ID=�T�[�r�X���p���ԗ���Params.get�ʔ�( )
                            l_srvRegiChargeInfo[j].chargeId = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getConsecutiveNumbers());
                            //�����p���ԒP�ʋ敪=�T�[�r�X���p���ԗ���Params.get���p���ԋ敪( )
                            l_srvRegiChargeInfo[j].chargeDiv = l_srvUsePeriodAmtParams.getSrvUsePeriodDiv();
                            //�����p����=�T�[�r�X���p���ԗ���Params.get���p����( )
                            l_srvRegiChargeInfo[j].chargePeriod = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getSrvUsePeriod());
                            //�����p����=�T�[�r�X���p���ԗ���Params.get���p����( )
                            l_srvRegiChargeInfo[j].chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getUseAmt());
                            //�������敪="�L��"
                            l_srvRegiChargeInfo[j].invalidDiv = false;
                        }
                        l_srvRegiNoLotteryGroup.chargeInfo = l_srvRegiChargeInfo;

                        //�����p���ԒP�ʁE���p����=(*2)
                        //(*2-1) �T�[�r�X���Ǘ�.is���p�\���\( )�̖߂�l��true�������ꍇ�A
                        if (l_srvRegiServiceInfoManagement.isTrialAppliPossible(l_strInstitutionCode, l_strBranchCode,
                            l_srvMasterList[i].getSrvDiv(), l_strAccountCode))
                        {
                            log.debug("�߂�l��true�������ꍇ");
                            //�����p���ԒP��=�\���v�T�[�r�X�I�u�W�F�N�g.get���p���ԋ敪( )
                            l_srvRegiNoLotteryGroup.trialDiv = l_srvRegiApplicationRequiredService.getTrialPeriodDiv();
                            //�����p����=�\���v�T�[�r�X�I�u�W�F�N�g.get���p����( )
                            if (l_srvRegiApplicationRequiredService.getTrialPeriod() == null)
                            {
                                log.debug("l_srvRegiNoLotteryGroup.trialPeriod = null;");
                                l_srvRegiNoLotteryGroup.trialPeriod = null;
                            }
                            else
                            {
                                log.debug("���p����");
                                l_srvRegiNoLotteryGroup.trialPeriod = l_srvRegiApplicationRequiredService.getTrialPeriod().toString();
                            }
                        }
                        else
                        {
                            log.debug("���p���ԒP�ʁE���p����");
                            //�����p���ԒP��=null
                            l_srvRegiNoLotteryGroup.trialDiv = null;
                            //�����p����=null
                            l_srvRegiNoLotteryGroup.trialPeriod = null;
                        }

                        //���\���\�敪=(*3)
                        //(*3-1) �T�[�r�X���Ǘ�.is�ڋq�\���\( )�̖߂�l��false�������ꍇ�A
                        if (!l_blnAccountAppliPossible)
                        {
                            log.debug("is�ڋq�\���\( )�̖߂�l��false�������ꍇ");
                            l_srvRegiNoLotteryGroup.applyAbleDiv = false;
                        }
                        //(*3-2) �擾�����T�[�r�X�\���o�^�I�u�W�F�N�g!=null�ł���A����
                        //�T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )�̖߂�l��"���p�\��"�ȊO�̏ꍇ�A
                        else if (l_gentradeSrvRegiApplication != null &&
                            !WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()))
                        {
                            log.debug("���p�\���ȊO�̏ꍇ");
                            l_srvRegiNoLotteryGroup.applyAbleDiv = false;
                        }
                        else
                        {
                            log.debug("else");
                            l_srvRegiNoLotteryGroup.applyAbleDiv = true;
                        }
                        // �������\���敪
                        l_srvRegiNoLotteryGroup.noChargeAbleDiv = false;

                        //�\�������敪�A�\����������From�A�\����������To�A���������\���敪=(*4)
                        //(�T�[�r�X���Ǘ�.is�ڋq�\���\( )=true)
                        //����
                        //(*4-1)(get�T�[�r�X�\���������I�u�W�F�N�g.�\�������敪 = '�����Ώ�'
                        //����
                        //get�T�[�r�X�\���������I�u�W�F�N�g.�\�������敪 = '�\���s��' �̏ꍇ�A�ȉ����Z�b�g����.
                        if (l_srvAppliAttributeRow != null)
                        {
                            if ((l_blnAccountAppliPossible)
								&& (WEB3AppliAttributeDef.FREE_OBJECT).equals(l_srvAppliAttributeRow.getAppliAttribute())
                                || ((WEB3AppliAttributeDef.CANNOT_APPLI).equals(
                                    l_srvAppliAttributeRow.getAppliAttribute())))
                            {
                            	
                            	//(*4-1-1) 
                            	//�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g != null
                            	//����
                            	//�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( ) == "���p�\��"
                            	//����
                            	//get�T�[�r�X�\���������I�u�W�F�N�g.�\�������敪 = '�����Ώ�'
                            	//����
                            	//�擾�����T�[�r�X�\���o�^�I�u�W�F�N�g.get�K�p�I������1��������
                            	//*4-1-1�̏����𖞂����Ă��Ȃ��ꍇ�́A�ȉ��̏������s���B
                            	Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();

                        		Calendar l_cal = new GregorianCalendar();
                        		Date l_datSystemTimestampMonthAfter = null;
								l_cal.setTime(l_tsSystemTimestamp);                  		
								l_cal.add(Calendar.MONTH, 1);
								l_datSystemTimestampMonthAfter = l_cal.getTime();

                            	if (!(l_gentradeSrvRegiApplication != null &&
                            		WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()) &&
                            		(WEB3AppliAttributeDef.FREE_OBJECT).equals(l_srvAppliAttributeRow.getAppliAttribute()) &&
                            		WEB3DateUtility.compareToDay(l_gentradeSrvRegiApplication.getAppliEndDate(), l_datSystemTimestampMonthAfter) > 0))
                            	{
                            		//(*4-1-2)
                                    // �\�������敪
                                    // get�T�[�r�X�\���������().�\�������敪���Z�b�g����B
                                    l_srvRegiNoLotteryGroup.applyAttributeDiv =
                                        l_srvAppliAttributeRow.getAppliAttribute();

                                    // �\����������From
                                    // get�T�[�r�X�\���������().�K�p����From���Z�b�g����B
                                    l_srvRegiNoLotteryGroup.applyAttributePeriodFrom =
                                        l_srvAppliAttributeRow.getAppliStartDate();

                                    // �\����������To
                                    // get�T�[�r�X�\���������().�K�p����To���Z�b�g����B
                                    l_srvRegiNoLotteryGroup.applyAttributePeriodTo =
                                        l_srvAppliAttributeRow.getAppliEndDate();
                                    
                                    //(*4-1-3)
                                    //(�T�[�r�X���Ǘ�.is�ڋq�\���\( )=true)
                                    //����
                                    //get�T�[�r�X�\���������I�u�W�F�N�g.�\�������敪 = '�����Ώ�'
                                    //����
                                    //get�����Ώۊ���()�̖߂�l != null �̏ꍇ�A�ȉ����Z�b�g����B
                                    if ((l_blnAccountAppliPossible)
                                    	&&	(WEB3AppliAttributeDef.FREE_OBJECT).equals(l_srvAppliAttributeRow.getAppliAttribute())
                                        && l_strFreeTargetPeriod != null)
                                    {
                                        //���������\���敪='1'
                                        l_srvRegiNoLotteryGroup.freeAttributeApplyDiv =
                                            WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY;
                                    }   
                            	}
                            }
                        }
                    }

                    //1.6.3.2.7 �������񋟂̏ꍇ��
                    // is�萔������()=true�̏ꍇ
                    else if (l_blnCommCond)
                    {
                        //1.6.3.2.7.1 <�v���p�e�B�E�Z�b�g *1-2>
                        
						//�����p���ԒP�ʁE���p����=null
						log.debug("���p���ԒP�ʁE���p����");
						//�����p���ԒP��=null
						l_srvRegiNoLotteryGroup.trialDiv = null;
						//�����p����=null
						l_srvRegiNoLotteryGroup.trialPeriod = null;

						//���\���\�敪=false
						l_srvRegiNoLotteryGroup.applyAbleDiv = false;

                        //�������\���敪
                        if(l_gentradeSrvRegiApplication == null)
                        {
                            l_srvRegiNoLotteryGroup.noChargeAbleDiv = true;
                        }
                        else
                        {
                            l_srvRegiNoLotteryGroup.noChargeAbleDiv = false;
                        }
                        
						//�����p���ԗ������
						if(WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY.equals(l_strProvidModel) &&
							 l_gentradeSrvRegiApplication != null){
							
							//1.7.3.2.7.1 get�T�[�r�X���p���ԗ����ꗗ
							WEB3SrvRegiServiceUsePeriodAmt[] l_srvUseTermAmtList = l_srvMasterList[i].getSrvUseTermAmtList();

							//1.7.3.2.7.2 <�v���p�e�B�E�Z�b�g *1-1>

							//�����p���ԗ������=(*1)
							//(*1-1) �T�[�r�X�}�X�^�[�I�u�W�F�N�g.�T�[�r�X���p���ԗ����ꗗ( )���R�[�����A
							//�߂�l�̌������ȉ����J��Ԃ��A�T�[�r�X���p���ԗ������̔z����쐬����B
							int l_intListLength = l_srvUseTermAmtList.length;
							WEB3SrvRegiChargeInfo[] l_srvRegiChargeInfo = new WEB3SrvRegiChargeInfo[l_intListLength];
							for (int j = 0; j < l_intListLength; j++)
							{
								log.debug("�T�[�r�X���p���ԗ������̔z����쐬����");
								l_srvRegiChargeInfo[j] = new WEB3SrvRegiChargeInfo();
								//(*1-2) �\���v�T�[�r�X���p���ԗ������N���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B
								SrvRegiChargeParams l_srvUsePeriodAmtParams = (SrvRegiChargeParams)l_srvUseTermAmtList[j].getDataSourceObject();
								//�����p����ID=�T�[�r�X���p���ԗ���Params.get�ʔ�( )
								l_srvRegiChargeInfo[j].chargeId = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getConsecutiveNumbers());
								//�����p���ԒP�ʋ敪=�T�[�r�X���p���ԗ���Params.get���p���ԋ敪( )
								l_srvRegiChargeInfo[j].chargeDiv = l_srvUsePeriodAmtParams.getSrvUsePeriodDiv();
								//�����p����=�T�[�r�X���p���ԗ���Params.get���p����( )
								l_srvRegiChargeInfo[j].chargePeriod = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getSrvUsePeriod());
								//�����p����=�T�[�r�X���p���ԗ���Params.get���p����( )
								l_srvRegiChargeInfo[j].chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getUseAmt());
								//�������敪="�L��"
								l_srvRegiChargeInfo[j].invalidDiv = false;
							}
							l_srvRegiNoLotteryGroup.chargeInfo = l_srvRegiChargeInfo;
						}
						else
						{
							l_srvRegiNoLotteryGroup.chargeInfo = null;
						}
                    }
                    l_lisSrvRegiNoLotteryGroup.add(l_srvRegiNoLotteryGroup);
                }
                //1.6.3.3  <���򏈗� *3>
                else if(WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
                {
                    log.debug("<���򏈗� *3>");
                    //��Q�Ή� NO_2049
                    //1.6.3.3.1 get�T�[�r�X�\���o�^�ꗗ
                    SrvRegiApplicationParams[] l_srvRegiApplicationParams =
                        l_srvRegiRegistService.getServiceRegistLists(l_strInstitutionCode, l_strBranchCode,
                        l_srvMasterList[i].getSrvDiv(), l_strAccountCode, true);

                    //1.6.3.3.2 get�T�[�r�X���I���ꗗ
                    WEB3SrvRegiServiceLotInfo[] l_srvRegiServiceLotInfo = l_srvMasterList[i].getSrvLotInfoList();

                    int l_intInfoLength = l_srvRegiServiceLotInfo.length;
                    if (l_intInfoLength > 0)
                    {
                        //1.6.3.3.3 <�}�b�`���O����>
                        //1.6.3.3.3.1 <�v���p�e�B�E�Z�b�g *2>
                        //1) �T�[�r�X���p���I�L�T�[�r�X���׏��ꗗ�s�I�u�W�F�N�g�𐶐�����B
                        WEB3SrvRegiLotteryGroup l_srvRegiLotteryGroup = new WEB3SrvRegiLotteryGroup();

                        //2) �T�[�r�X�\���o�^�ꗗ�ƃT�[�r�X���I���̑S�Ă̗v�f�ɑ΂��āA�ȉ������{����B
                        //2-1) �T�[�r�X���I���ꗗ[n].get�\�����ԁi���j( )�Aget�\�����ԁi���j( )��
                        //�T�[�r�X�\���o�^�ꗗ[m].get�\����( )���r�B
                        boolean l_blnDiv = false;
                        for (int n = 0; n < l_intInfoLength; n++)
                        {
                            if (l_srvRegiApplicationParams != null)
                            {
                                log.debug("l_srvRegiApplicationParams != null");
                                int l_intParamsLength = l_srvRegiApplicationParams.length;
                                for (int m = 0; m < l_intParamsLength; m++)
                                {
                                    log.debug("l_intParamsLength");
                                    if (WEB3DateUtility.compareToSecond(l_srvRegiServiceLotInfo[n].getAppliDateFrom(),
                                        l_srvRegiApplicationParams[m].getAppliDate()) <= 0 &&
                                        WEB3DateUtility.compareToSecond(l_srvRegiApplicationParams[m].getAppliDate(),
                                        l_srvRegiServiceLotInfo[n].getAppliDateTo()) <= 0)
                                    {
                                        log.debug("l_blnDiv = true;");

                                        log.debug("<�S�p�^�[������>");
                                        l_srvRegiLotteryGroup = new WEB3SrvRegiLotteryGroup();
                                        //*---<�S�p�^�[������>---*
                                        //��ID=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )
                                        l_srvRegiLotteryGroup.serviceDiv = l_srvMasterList[i].getSrvDiv();
                                        //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )
                                        l_srvRegiLotteryGroup.serviceName = l_srvMasterList[i].getSrvName();
                                        //���X�e�[�^�X=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�X�e�[�^�X( )
                                        l_srvRegiLotteryGroup.serviceStatus = l_srvMasterList[i].getStatus();
                                        //���^�p�敪=�T�[�r�X���I���ꗗ[n].get�^�p�敪( )
                                        l_srvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo[n].getInvestDiv();
                                        //���\���\���Ԑݒ�=(*1)
                                        //(*1-1) �\���v�T�[�r�X�I�u�W�F�N�g.get�\���\���ԁi���j( )�Aget�\���\���ԁi���j( )
                                        //�̖߂�l�������Ƃ�null�������ꍇ�A"��"���Z�b�g����B
                                        if (l_srvRegiApplicationRequiredService.getAppliDateFrom() == null &&
                                            l_srvRegiApplicationRequiredService.getAppliDateTo() == null)
                                        {
                                            log.debug("WEB3ConditionsValueDivDef.HAVE_NOT");
                                            l_srvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT;
                                        }
                                        else
                                        {
                                            log.debug("WEB3ConditionsValueDivDef.HAVE");
                                            l_srvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE;
                                        }

                                        //���T�[�r�X����URL=�\���v�T�[�r�X�I�u�W�F�N�g.get�T�[�r�X����URL( )
                                        l_srvRegiLotteryGroup.explainURL = l_srvRegiApplicationRequiredService.getSrvExplanUrl();
                                        //���d�q���ݒ�敪=�擾�����\���v�T�[�r�X�I�u�W�F�N�g.is�d�q�������ݒ�( )
                                        l_srvRegiLotteryGroup.elePigeonDiv = l_srvRegiApplicationRequiredService.isElectricIssue();

                                        l_blnDiv = true;
                                        //*---<�p�^�[��1>---*
                                        //���\�����ԁi���j=�T�[�r�X���I���ꗗ[n].get�\�����ԁi���j( )
                                        l_srvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo[n].getAppliDateFrom();
                                        //���\�����ԁi���j=�T�[�r�X���I���ꗗ[n].get�\�����ԁi���j( )
                                        l_srvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo[n].getAppliDateTo();
                                        //�����I��=�T�[�r�X���I���ꗗ[n].get���I��( )
                                        l_srvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo[n].getLotDate();
                                        //�����p����=(*2)
                                        //(*2-1) �T�[�r�X�\���o�^�ꗗ[m].get�\�����I�敪( )��"�\��"�A
                                        //"���I�^�{�\��"�A"�������I"�̂����ꂩ�̏ꍇ,�T�[�r�X�\���o�^�ꗗ[m].get���p����( )���Z�b�g����B
                                        if (WEB3AppliLotDivDef.APPLI.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()) ||
                                            WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()) ||
                                            WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()))
                                        {
                                            //���K�p�J�n��=�T�[�r�X�\���o�^�ꗗ[m].get�K�p�J�n��( )
                                            l_srvRegiLotteryGroup.trialStartDate = l_srvRegiApplicationParams[m].getAppliStartDate();
                                            //���K�p�I����=�T�[�r�X�\���o�^�ꗗ[m].get�K�p�I����( )
                                            l_srvRegiLotteryGroup.trialEndDate = l_srvRegiApplicationParams[m].getAppliEndDate();

                                            if (l_srvRegiApplicationParams[m].getUseAmtIsNull())
                                            {
                                                log.debug("l_srvRegiLotteryGroup.chargeAmt = null;");
                                                l_srvRegiLotteryGroup.chargeAmt = null;
                                            }
                                            else
                                            {
                                                log.debug("get���p����");
                                                l_srvRegiLotteryGroup.chargeAmt =
                                                    WEB3StringTypeUtility.formatNumber(l_srvRegiApplicationParams[m].getUseAmt());
                                            }

                                        }
                                        //(*2-2) �T�[�r�X�\���o�^�ꗗ[m].get�\�����I�敪( )="���I"�̏ꍇ
                                        else if(WEB3AppliLotDivDef.DEFEAT.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()))
                                        {
                                            log.debug("���I");

                                            //���K�p�J�n��=�T�[�r�X���I���ꗗ[n].get�K�p�J�n��( )
                                            l_srvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo[n].getAppliStartDate();
                                            //���K�p�I����=�T�[�r�X���I���ꗗ[n].get�K�p�I����( )
                                            l_srvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo[n].getAppliEndDate();
                                            //���p����=�T�[�r�X���I���ꗗ[n].get���p����( )���Z�b�g����B
                                            l_srvRegiLotteryGroup.chargeAmt =
                                                WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo[n].getUseAmt());
                                        }

                                        //�����D�P��=�T�[�r�X���I���ꗗ[n].get���D�P��( )
                                        if (l_srvRegiServiceLotInfo[n].getBiddingPrice() == null)
                                        {
                                            log.debug("l_srvRegiLotteryGroup.biddingPriceUnit = null;");
                                            l_srvRegiLotteryGroup.biddingPriceUnit = null;
                                        }
                                        else
                                        {
                                            log.debug("�����D�P��");
                                            l_srvRegiLotteryGroup.biddingPriceUnit =
                                                l_srvRegiServiceLotInfo[n].getBiddingPrice().toString();
                                        }

                                        //���\����=(*3)
                                        //(*3-1) �T�[�r�X�\���o�^�ꗗ[m].get�\�����I�敪( )="���I"�̏ꍇ,null���Z�b�g����B
                                        if (WEB3AppliLotDivDef.DEFEAT.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()))
                                        {
                                            log.debug("l_srvRegiLotteryGroup.applyDate = null;");
                                            l_srvRegiLotteryGroup.applyDate = null;
                                        }
                                        //(*3-2) ��L�ȊO�̏ꍇ�A�T�[�r�X�\���o�^�ꗗ[m].get�\����( )���Z�b�g����B
                                        else
                                        {
                                            log.debug("get�\����");
                                            l_srvRegiLotteryGroup.applyDate = l_srvRegiApplicationParams[m].getAppliDate();
                                        }
                                        //���\���\�敪=false
                                        l_srvRegiLotteryGroup.applyAbleDiv = false;
                                        //������\�敪=�T�[�r�X���p�\���o�^�T�[�r�X.is����\( )�̖߂�l
                                        l_srvRegiLotteryGroup.cancelAbleDiv =
                                            l_srvRegiRegistService.isCancelPossible(l_srvRegiApplicationParams[m].getInstitutionCode(),
                                            l_srvRegiApplicationParams[m].getBranchCode(),
                                            l_srvRegiApplicationParams[m].getSrvDiv(),
                                            l_srvRegiApplicationParams[m].getAccountCode(),
                                            l_srvRegiApplicationParams[m].getRegistId());
                                        //���T�[�r�X���p�\�敪=�T�[�r�X���p�\���o�^�T�[�r�X.is���p�\( )�̖߂�l
                                        l_srvRegiLotteryGroup.useAbleDiv =
                                            l_srvRegiRegistService.isUsePossible(l_srvRegiApplicationParams[m].getInstitutionCode(),
                                            l_srvRegiApplicationParams[m].getBranchCode(),
                                            l_srvRegiApplicationParams[m].getSrvDiv(),
                                            l_srvRegiApplicationParams[m].getAccountCode(),
                                            l_srvRegiApplicationParams[m].getRegistId());

                                        l_lisSrvRegiLotteryGroup.add(l_srvRegiLotteryGroup);
                                    }
                                }
                            }


                            if (l_blnDiv)
                            {
                                log.debug("l_blnDiv = false;");
                                l_blnDiv = false;
                            }
                            else
                            {
                                log.debug("<�S�p�^�[������>");
                                l_srvRegiLotteryGroup = new WEB3SrvRegiLotteryGroup();
                                //*---<�S�p�^�[������>---*
                                //��ID=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X�敪( )
                                l_srvRegiLotteryGroup.serviceDiv = l_srvMasterList[i].getSrvDiv();
                                //���T�[�r�X����=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )
                                l_srvRegiLotteryGroup.serviceName = l_srvMasterList[i].getSrvName();
                                //���X�e�[�^�X=�T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�X�e�[�^�X( )
                                l_srvRegiLotteryGroup.serviceStatus = l_srvMasterList[i].getStatus();
                                //���^�p�敪=�T�[�r�X���I���ꗗ[n].get�^�p�敪( )
                                l_srvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo[n].getInvestDiv();
                                //���\���\���Ԑݒ�=(*1)
                                //(*1-1) �\���v�T�[�r�X�I�u�W�F�N�g.get�\���\���ԁi���j( )�Aget�\���\���ԁi���j( )
                                //�̖߂�l�������Ƃ�null�������ꍇ�A"��"���Z�b�g����B
                                if (l_srvRegiApplicationRequiredService.getAppliDateFrom() == null &&
                                    l_srvRegiApplicationRequiredService.getAppliDateTo() == null)
                                {
                                    log.debug("WEB3ConditionsValueDivDef.HAVE_NOT");
                                    l_srvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT;
                                }
                                else
                                {
                                    log.debug("WEB3ConditionsValueDivDef.HAVE");
                                    l_srvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE;
                                }

                                //���T�[�r�X����URL=�\���v�T�[�r�X�I�u�W�F�N�g.get�T�[�r�X����URL( )
                                l_srvRegiLotteryGroup.explainURL = l_srvRegiApplicationRequiredService.getSrvExplanUrl();
                                //���d�q���ݒ�敪=�擾�����\���v�T�[�r�X�I�u�W�F�N�g.is�d�q�������ݒ�( )
                                l_srvRegiLotteryGroup.elePigeonDiv = l_srvRegiApplicationRequiredService.isElectricIssue();

                                log.debug("<�p�^�[��2>");
                                // *---<�p�^�[��2>---*
                                //���\�����ԁi���j=�T�[�r�X���I���ꗗ[n].get�\�����ԁi���j( )
                                l_srvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo[n].getAppliDateFrom();
                                //���\�����ԁi���j=�T�[�r�X���I���ꗗ[n].get�\�����ԁi���j( )
                                l_srvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo[n].getAppliDateTo();
                                //�����I��=�T�[�r�X���I���ꗗ[n].get���I��( )
                                l_srvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo[n].getLotDate();
                                //���K�p�J�n��=�T�[�r�X���I���ꗗ[n].get�K�p�J�n��( )
                                l_srvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo[n].getAppliStartDate();
                                //���K�p�I����=�T�[�r�X���I���ꗗ[n].get�K�p�I����( )
                                l_srvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo[n].getAppliEndDate();
                                //�����p����=�T�[�r�X���I���ꗗ[n].get���p����( )
                                l_srvRegiLotteryGroup.chargeAmt =
                                    WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo[n].getUseAmt());
                                //�����D�P��=�T�[�r�X���I���ꗗ[n].get���D�P��( )
                                if (l_srvRegiServiceLotInfo[n].getBiddingPrice() == null)
                                {
                                    log.debug("l_srvRegiLotteryGroup.biddingPriceUnit = null;");
                                    l_srvRegiLotteryGroup.biddingPriceUnit = null;
                                }
                                else
                                {
                                    log.debug("�����D�P��");
                                    l_srvRegiLotteryGroup.biddingPriceUnit = l_srvRegiServiceLotInfo[n].getBiddingPrice().toString();
                                }

                                //���\����=null
                                l_srvRegiLotteryGroup.applyDate = null;
                                //���\���\�敪=�E�T�[�r�X���I���ꗗ[n].get�\�����ԁi���j( )
                                //�����ݓ��t���T�[�r�X���I���ꗗ[n].get�\�����ԁi���j( )�̏ꍇ�A
                                //�T�[�r�X���Ǘ�.is�ڋq�\���\( )�̖߂�l���Z�b�g�B
                                Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
                                Timestamp l_tsAppliDateFrom = l_srvRegiServiceLotInfo[n].getAppliDateFrom();
                                Timestamp l_tsAppliDateTo = l_srvRegiServiceLotInfo[n].getAppliDateTo();

                                if (WEB3DateUtility.compareToSecond(l_tsAppliDateFrom, l_tsSystemTimestamp) <= 0 &&
                                    WEB3DateUtility.compareToSecond(l_tsSystemTimestamp, l_tsAppliDateTo) <= 0)
                                {
                                    l_srvRegiLotteryGroup.applyAbleDiv = l_blnAccountAppliPossible;
                                }
                                //��L�ȊO�̏ꍇ�Afalse���Z�b�g
                                else
                                {
                                    l_srvRegiLotteryGroup.applyAbleDiv = false;
                                }
                                //������\�敪=false
                                l_srvRegiLotteryGroup.cancelAbleDiv = false;
                                //���T�[�r�X���p�\�敪=false
                                l_srvRegiLotteryGroup.useAbleDiv = false;

                                l_lisSrvRegiLotteryGroup.add(l_srvRegiLotteryGroup);
                            }
                        }
                    }
                }
            }

        }
        //1.7 create���X�|���X
        WEB3SrvRegiReferenceResponse l_srvRegiReferenceResponse = (WEB3SrvRegiReferenceResponse)l_srvRegiReferenceRequest.createResponse();

        WEB3SrvRegiNoLotteryGroup[] l_srvRegiNoLotteryGroup = new WEB3SrvRegiNoLotteryGroup[l_lisSrvRegiNoLotteryGroup.size()];
        WEB3SrvRegiLotteryGroup[] l_srvRegiLotteryGroup = new WEB3SrvRegiLotteryGroup[l_lisSrvRegiLotteryGroup.size()];
        l_lisSrvRegiNoLotteryGroup.toArray(l_srvRegiNoLotteryGroup);
        l_lisSrvRegiLotteryGroup.toArray(l_srvRegiLotteryGroup);

        l_srvRegiReferenceResponse.noLotList = l_srvRegiNoLotteryGroup;
        l_srvRegiReferenceResponse.lotList = l_srvRegiLotteryGroup;

        log.exiting(STR_METHOD_NAME);
        return l_srvRegiReferenceResponse;
    }
}@
