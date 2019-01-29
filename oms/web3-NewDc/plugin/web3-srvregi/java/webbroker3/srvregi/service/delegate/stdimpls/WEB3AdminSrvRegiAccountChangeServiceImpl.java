head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�XImpl(WEB3AdminSrvRegiAccountChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
Revesion History : 2008/02/26 ���g �d�l�ύX ���f��323
Revesion History : 2008/03/03 ���g �d�l�ύX ���f��333
Revesion History : 2008/03/19 ���g �d�l�ύX ���f��355,358
Revesion History : 2008/03/28 ���g �d�l�ύX ���f��364
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeGroup;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X�����N���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountChangeServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountChangeService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountChangeServiceImpl.class);

    /**
     * @@roseuid 416F39280167
     */
    public WEB3AdminSrvRegiAccountChangeServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�ύX( )�܂��́A<BR>
     * submit�ύX( )���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E36001C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3AdminSrvRegiCustomerChangeConfirmRequest)
        {
            log.debug("WEB3AdminSrvRegiCustomerChangeConfirmRequest");
            WEB3AdminSrvRegiCustomerChangeConfirmResponse l_srvRegiCustomerRegistConfirmResponse =
                validateChange((WEB3AdminSrvRegiCustomerChangeConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiCustomerChangeCompleteRequest)
        {
            log.debug("WEB3AdminSrvRegiCustomerChangeCompleteRequest");
            WEB3AdminSrvRegiCustomerChangeCompleteResponse l_srvRegiCustomerRegistCompleteResponse =
                submitChange((WEB3AdminSrvRegiCustomerChangeCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistCompleteResponse;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate�ύX)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�ύX�R���v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return WEB3AdminSrvRegiCustomerChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E3780233
     */
    protected WEB3AdminSrvRegiCustomerChangeConfirmResponse validateChange(WEB3AdminSrvRegiCustomerChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminSrvRegiCustomerChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate( )
        l_request.validate();

        //1.3 validate������t�\( )
        //WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);

        //1.5 �،���ЃR�[�h
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.6<�J��Ԃ�����>
        WEB3AdminSrvRegiCustomerChangeGroup[] chgCustomerList = l_request.chgCustomerList;

        int l_intArrayLengh = chgCustomerList.length;

        for (int i = 0; i < l_intArrayLengh; i++)
        {
            //���X�R�[�h
            String l_strBranchCode = l_request.chgCustomerList[i].branchCode;

            //�ڋq�R�[�h
            String l_strAccountCode = l_request.chgCustomerList[i].accountCode;

            //�g���A�J�E���g�}�l�[�W��
            //�U���̃��N�G�X�g�f�[�^.�ڋq�R�[�h����V���̌ڋq�R�[�h���擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //1.6.1 get�ڋq(String, String, String)
            WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

            //1.6.2 getAccountCode()
            String l_strDbAccountCode = l_mainAccount.getAccountCode();

            //�K�p�J�n��
            Timestamp l_tsTrialStartDate = new Timestamp(l_request.chgCustomerList[i].trialStartDate.getTime());

            //�K�p�I����
            Timestamp l_tsTrialEndDate = new Timestamp(l_request.chgCustomerList[i].trialEndDate.getTime());

            //�\����
            Timestamp l_tsApplyDate = null;
            if (l_request.chgCustomerList[i].applyDate != null)
            {
                l_tsApplyDate = new Timestamp(l_request.chgCustomerList[i].applyDate.getTime());
            }

            //���p����
            Double l_dblChargeAmt = null;
            if(l_request.chgCustomerList[i].chargeAmt != null)
            {
                l_dblChargeAmt = new Double(l_request.chgCustomerList[i].chargeAmt);
            }

            //1.7.1 create�T�[�r�X���p�ύX�\�����e
            WEB3SrvRegiChangeAppliSpec l_srvRegiChangeAppliSpec = new WEB3SrvRegiChangeAppliSpec();

            l_srvRegiChangeAppliSpec = WEB3SrvRegiChangeAppliSpec.createSrvRegiChangeAppliSpec(
                Long.parseLong(l_request.chgCustomerList[i].applyRegId), l_strInstitutionCode,
                l_request.serviceDiv, l_request.chgCustomerList[i].branchCode,
                l_strDbAccountCode, l_tsTrialStartDate,
                l_tsTrialEndDate, l_request.chgCustomerList[i].applyLotteryDiv,
                l_tsApplyDate, l_request.chgCustomerList[i].registDiv,
                l_dblChargeAmt);

            //1.7.2 validate�\���ύX(�T�[�r�X���p�ύX�\�����e)
            validateAppliChange(l_srvRegiChangeAppliSpec, l_admin);
        }

        //1.8 create���X�|���X( )
        WEB3AdminSrvRegiCustomerChangeConfirmResponse l_srvRegiCustomerChangeConfirmResponse =
                (WEB3AdminSrvRegiCustomerChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_srvRegiCustomerChangeConfirmResponse;
    }

    /**
     * (submit�ύX)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�ύX�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E37D0168
     */
    protected WEB3AdminSrvRegiCustomerChangeCompleteResponse submitChange(WEB3AdminSrvRegiCustomerChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminSrvRegiCustomerChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate( )
        l_request.validate();

        //1.3 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);

        //1.5 validate����p�X���[�h(String)
        l_admin.validateTradingPassword(l_request.password);

        //1.6 �،���ЃR�[�h
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.7 <�J��Ԃ�����>
        WEB3AdminSrvRegiCustomerChangeGroup[] l_chgCustomerList = l_request.chgCustomerList;

        int l_intArrayLengh = l_chgCustomerList.length;

        for (int i = 0; i < l_intArrayLengh; i++)
        {
            //���X�R�[�h
            String l_strBranchCode = l_request.chgCustomerList[i].branchCode;

            //�ڋq�R�[�h
            String l_strAccountCode = l_request.chgCustomerList[i].accountCode;

            //get�T�[�r�X�}�X�^�[(String, String, boolean)
            //�@@�،���ЃR�[�h=�Ǘ��҃I�u�W�F�N�g���擾�����،���ЃR�[�h 
            //�T�[�r�X�敪=���N�G�X�g�f�[�^.�T�[�r�X�敪 
            //is�s���b�N=false
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                l_srvRegiServiceInfoManagement.getSrvMaster(
                    l_strInstitutionCode, l_request.serviceDiv, false);
            //�g���A�J�E���g�}�l�[�W��
            //�U���̃��N�G�X�g�f�[�^.�ڋq�R�[�h����V���̌ڋq�R�[�h���擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //1.7.1 get�ڋq(String, String, String)
            WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

            //1.7.2 getAccountCode()
            String l_strDbAccountCode = l_mainAccount.getAccountCode();
            
			//���������b�N����B 
			//�|�g���A�J�E���g�}�l�[�W��.lock����(�،���ЃR�[�h, ���X�R�[�h, �����R�[�h)���R�[������B  
			WEB3GentradeAccountManager l_gentradeAccMgr = 
				(WEB3GentradeAccountManager) l_finApp.getAccountManager();
				
			l_gentradeAccMgr.lockAccount(
				l_strInstitutionCode,
			    l_strBranchCode,
			    l_strDbAccountCode);

            //�K�p�J�n��
            Timestamp l_tsTrialStartDate = new Timestamp(l_request.chgCustomerList[i].trialStartDate.getTime());

            //�K�p�I����
            Timestamp l_tsTrialEndDate = new Timestamp(l_request.chgCustomerList[i].trialEndDate.getTime());

            //�\����
            Timestamp l_tsApplyDate = null;
            if (l_request.chgCustomerList[i].applyDate != null)
            {
                l_tsApplyDate = new Timestamp(l_request.chgCustomerList[i].applyDate.getTime());
            }

            //���p����
            Double l_dblChargeAmt = null;
            if(l_request.chgCustomerList[i].chargeAmt != null)
            {
                l_dblChargeAmt = new Double(l_request.chgCustomerList[i].chargeAmt);
            }

            //1.7.3 create�T�[�r�X���p�ύX�\�����e
            WEB3SrvRegiChangeAppliSpec l_srvRegiChangeAppliSpec = new WEB3SrvRegiChangeAppliSpec();

            l_srvRegiChangeAppliSpec = WEB3SrvRegiChangeAppliSpec.createSrvRegiChangeAppliSpec(
                Long.parseLong(l_request.chgCustomerList[i].applyRegId), l_strInstitutionCode,
                l_request.serviceDiv, l_request.chgCustomerList[i].branchCode,
                l_strDbAccountCode, l_tsTrialStartDate,
                l_tsTrialEndDate, l_request.chgCustomerList[i].applyLotteryDiv,
                l_tsApplyDate, l_request.chgCustomerList[i].registDiv,
                l_dblChargeAmt);

            //1.7.4 validate�\���ύX(�T�[�r�X���p�ύX�\�����e)
            validateAppliChange(l_srvRegiChangeAppliSpec, l_admin);

            //�T�[�r�X���p�\���o�^�T�[�r�X
            WEB3SrvRegiRegistService l_srvRegiRegistService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

            //1.7.5 get�T�[�r�X�\���o�^
            //WEB3GentradeSrvRegiApplication
            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode,
                l_request.chgCustomerList[i].branchCode,
                l_request.serviceDiv, l_strDbAccountCode,
                Long.parseLong(l_request.chgCustomerList[i].applyRegId), false);

            //1.7.5.1 get����ID
            Long l_orderId = l_gentradeSrvRegiApplication.getOrderId();

			//��Q�Ή� NO_2060
			//��Q�Ή� NO_2082
            //1.7.6 <���򏈗� *1>
            if (((WEB3AppliLotDivDef.APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())
				|| (WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())))
                && WEB3AppliLotDivDef.DEFEAT.equals(l_request.chgCustomerList[i].applyLotteryDiv))
                || (WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())
                && WEB3DateUtility.compareToDay(l_gentradeSrvRegiApplication.getCancelLimitDate(), GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0
                && WEB3AppliLotDivDef.DEFEAT.equals(l_request.chgCustomerList[i].applyLotteryDiv)))
            {
                if (l_orderId != null)
                {
                    try
                    {
                        //1.7.6.1.1 getSubAccount
                        SubAccount l_subAccount = l_gentradeAccountManager.getSubAccount(
                            l_mainAccount.getAccountId(), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException

                        //�⏕����
                        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
                        
                        // get����p�X���[�h�i�⏕����, ���N�G�X�g�f�[�^.�Ïؔԍ��j
                        String l_strTradingPassword = l_srvRegiRegistService.getTradingPassword(l_subAccount, l_request.password); 

                        //1.7.6.1.2 submit�]�͉��
                        l_srvRegiRegistService.submitRemainingPowerRelease(l_gentradeSubAccount, l_orderId.longValue(),
                            l_strTradingPassword);
                    }
                    catch(NotFoundException l_nfd)
                    {
                        log.error(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                }

            }

            //1.7.7 submit�T�[�r�X�\���ύX
            l_srvRegiRegistService.submitServiceRegistChange(l_srvRegiChangeAppliSpec);

            //���򏈗���2�@@�T�[�r�X�}�X�^�[.���ꏈ���敪 = 1�̏ꍇ
            SrvRegiMasterRow l_srvRegiMasterRow =
                (SrvRegiMasterRow)l_srvRegiServiceMaster.getDataSourceObject();
            String l_strSpecialProcessDiv = l_srvRegiMasterRow.getSpecialProcessDiv();
            if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
            {
                //submit�O���A�g���(String, String, String, String, String, Timestamp, Timestamp, boolean)
                //�،���ЃR�[�h = get�،���ЃR�[�h()�̖߂�l
                //���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
                //�����R�[�h = getAccountCode( )�̖߂�l
                //�\�����I�敪 = ���N�G�X�g.�ύX�ڋq�ꗗ.�\�����I�敪
                //�T�[�r�X�敪 = ���N�G�X�g�f�[�^.�T�[�r�X�敪
                //�K�p�J�n�� = ���N�G�X�g�f�[�^�K�p�J�n��
                //�K�p�I���� = ���N�G�X�g�f�[�^�K�p�I����
                //�V�K�\���敪 = false
                WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                    (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                l_srvRegiOtherOrgService.submitOtherOrgInfo(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strDbAccountCode,
                    l_request.chgCustomerList[i].applyLotteryDiv,
                    l_request.serviceDiv,
                    l_tsTrialStartDate,
                    l_tsTrialEndDate,
                    false);
            }
        }

        //1.8 create���X�|���X( )
        WEB3AdminSrvRegiCustomerChangeCompleteResponse l_srvRegiCustomerChangeCompleteResponse =
                (WEB3AdminSrvRegiCustomerChangeCompleteResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiCustomerChangeCompleteResponse;
    }

    /**
     * (validate�\���ύX)<BR>
     * �ڋq�ύX�����̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jvalidate�\���ύX�v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���ύX�v): <BR>
     *         1.1.getServiceRegist(String, String, String, String, long, )<BR>
     *           �T�[�r�X�\���o�^���擾����B<BR>
     *           �擾�ł��Ȃ������ꍇ�A��O���X���[�B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00908<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���ύX�v): <BR>
     *         1.3.<�\�����I�敪�`�F�b�N><BR>
     *             <�\�����I�敪�`�F�b�N><BR>
     *             ��get���I�ݒ�( )�̖߂�l="��"�̏ꍇ<BR>
     *          �@@�@@���N�G�X�g�f�[�^.�������e.�\�����I�敪���A<BR>
     *             �ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     *        �@@�@@�@@�u���I�^�{�\���v�u���p�v<BR>
     *              class:WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_01007<BR>
     * <BR>
     *             ��get���I�ݒ�( )�̖߂�l="��"�ȊO�̏ꍇ<BR>
     *           �@@�@@���N�G�X�g�f�[�^.�������e.�\�����I�敪���A<BR>
     *              �ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     *         �@@�@@�@@�u���I�^�{�\���v�u���I�v<BR>
     *              class:WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_01008<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�jvalidate�\���ύX�v): <BR>
     *         1.4.1.getSrvLotInfo(String, String, Timestamp, int)<BR>
     *          ����.�������e.�\�������A���I���̐\�����ԓ��ɂ��邩���`�F�b�N����B<BR>
     *          �iget�T�[�r�X���I���( )�̖߂�l==null�̏ꍇ�A��O���X���[����B�j<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01003<BR>
     * ==========================================================<BR>
     * @@param l_orderSpec - (�������e)<BR>
     * @@roseuid 413E60F20307
     */
    private void validateAppliChange(WEB3SrvRegiChangeAppliSpec l_orderSpec, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliChange(WEB3SrvRegiChangeAppliSpec, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        //�،���ЃR�[�h
        String l_strInstitutionCode = l_orderSpec.getInstitutionCode();

        //���X�R�[�h
        String l_strBranchCode = l_orderSpec.getBranchCode();

        //�T�[�r�X�敪
        String l_strSrvDiv = l_orderSpec.getSrvDiv();

        //�����R�[�h
        String l_strAccountCode = l_orderSpec.getAccountCode();

        //�\���o�^ID
        long l_lngRegistId = Long.parseLong(l_orderSpec.getRegistId());

        //1.1 get�T�[�r�X�\���o�^(String, String, String, String, long,)
        //�T�[�r�X���p�\���o�^�T�[�r�X
        WEB3SrvRegiRegistService l_srvRegiRegistService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        WEB3GentradeSrvRegiApplication l_gtradeSrvRegiApplication = null;

        l_gtradeSrvRegiApplication = l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode,
            l_strBranchCode, l_strSrvDiv, l_strAccountCode, l_lngRegistId, false);

        if (l_gtradeSrvRegiApplication == null)
        {
            //�T�[�r�X�\���o�^�f�[�^���擾�ł��܂���B
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00908,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.2 validate���X����(String[])
        l_admin.validateBranchPermission(l_gtradeSrvRegiApplication.getBranchCode());

        //1.3 get�T�[�r�X�}�X�^�[(String, String, boolean)
        //�T�[�r�X���Ǘ�
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();

        //�T�[�r�X�}�X�^�[
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //1.3.1 get�\���v�T�[�r�X(boolean)
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        if(l_srvRegiApplicationRequiredService == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.3.1.1 get���I�ݒ�()
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        //1.5<���򏈗�>
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            //�،���ЃR�[�h
            String l_strAdminInstitutionCode = l_admin.getInstitutionCode();

            //1.5.1 get�T�[�r�X���I���(String, String, Timestamp, int)
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strAdminInstitutionCode,
                l_srvRegiServiceMaster.getSrvDiv(),
                new Timestamp(WEB3DateUtility.toDay(l_orderSpec.getAppliDate()).getTime()), 0);

            //get�T�[�r�X���I���( )�̖߂�l==null�̏ꍇ�A��O���X���[����B
            if (l_srvRegiServiceLotInfo == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.6 validate�K�p����(String, String, String, String, Timestamp, Timestamp, Long)
        //�K�p�J�n��
        Timestamp l_tsAppliStartDate = l_orderSpec.getAppliStartDate();

        //�K�p�I����
        Timestamp l_tsAppliEndDate = l_orderSpec.getAppliEndDate();
        try
        {
            l_srvRegiRegistService.validateAppliPeriod(l_strInstitutionCode, l_strSrvDiv,
                l_strBranchCode, l_strAccountCode, l_tsAppliStartDate, l_tsAppliEndDate,
                new Long(l_lngRegistId));//WEB3BaseException
        }
        catch(WEB3BaseException l_ex)
        {
            //1.7 <��O���X���[���ꂽ�ꍇ>
            log.debug(getClass().getName() + STR_METHOD_NAME);

            throw new WEB3BaseException(
                 l_ex.getErrorInfo(),
                 this.getClass().getName() + STR_METHOD_NAME,
                 l_strAccountCode,
                 l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
