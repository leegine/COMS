head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�XImpl(WEB3AdminSrvRegiAccountRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
Revesion History : 2008/02/26 ���g �d�l�ύX ���f��322
Revesion History : 2008/03/03 ���g �d�l�ύX ���f��332
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
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
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3PaymentDivDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�XImpl)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X�����N���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountRegistServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountRegistService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountRegistServiceImpl.class);

    /**
     * @@roseuid 416F3928029F
     */
    public WEB3AdminSrvRegiAccountRegistServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate�o�^( )�܂��́A<BR>
     * submit�o�^( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E04D00CC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3AdminSrvRegiCustomerRegistConfirmRequest)
        {
            log.debug("WEB3AdminSrvRegiCustomerRegistConfirmRequest");
            WEB3AdminSrvRegiCustomerRegistConfirmResponse l_srvRegiCustomerRegistConfirmResponse =
                this.validateRegist((WEB3AdminSrvRegiCustomerRegistConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiCustomerRegistCompleteRequest)
        {
            log.debug("WEB3AdminSrvRegiCustomerRegistCompleteRequest");
            WEB3AdminSrvRegiCustomerRegistCompleteResponse l_srvRegiCustomerRegistCompleteResponse =
                this.submitRegist((WEB3AdminSrvRegiCustomerRegistCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistCompleteResponse;
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
     * (validate�o�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�R���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�o�^�R���v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E11B02C0
     */
    protected WEB3AdminSrvRegiCustomerRegistConfirmResponse validateRegist(WEB3AdminSrvRegiCustomerRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminSrvRegiCustomerRegistConfirmRequest)";
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

        //1.4 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);

        //1.5 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);

        //1.6 get�،���ЃR�[�h()
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //���X�R�[�h
        String l_strBranchCode = l_request.branchCode;

        //�ڋq�R�[�h
        String l_strAccountCode = l_request.accountCode;

        //�g���A�J�E���g�}�l�[�W��
        //�U���̃��N�G�X�g�f�[�^.�ڋq�R�[�h����V���̌ڋq�R�[�h���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //1.7 get�ڋq(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

        //1.8 getAccountCode()
        String l_strDbAccountCode = l_mainAccount.getAccountCode();

        //1.9 create�T�[�r�X���p�V�K�\�����e(String, String, String, String,
        //   Timestamp, Timestamp, Timestamp, String, Double, Timestamp)
        //�T�[�r�X�敪
        String l_strServiceDiv  = l_request.serviceDiv;

        //�K�p�J�n��
        Timestamp l_tsTrialStartDate = new Timestamp(l_request.trialStartDate.getTime());

        //�K�p�I����
        Timestamp l_tsTrialEndDate = new Timestamp(l_request.trialEndDate.getTime());

        //�\����
        //get���I�ݒ�()="��"�ł���A���N�G�X�g�f�[�^.�\����==null�̏ꍇ�A���N�G�X�g�f�[�^.�K�p�J�n�����Z�b�g����B
        //��L�ȊO�̏ꍇ�A���N�G�X�g�f�[�^.�\�������Z�b�g����B
        Timestamp l_tsApplyDate = null;

        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);

        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_srvRegiApplicationRequiredService.getLotDiv())
            && l_request.applyDate == null)
        {
            l_tsApplyDate = new Timestamp(l_request.trialStartDate.getTime());
        }
        else
        {
            if (l_request.applyDate != null)
            {
                l_tsApplyDate = new Timestamp(l_request.applyDate.getTime());
            }
        }

        //�o�^�敪
        String l_strRegistDiv = l_request.registDiv;

        //���p����
        String l_strChargeAmt = l_request.chargeAmt;
        Double l_dblChargeAmt = null;

        if (l_strChargeAmt  != null)
        {
            l_dblChargeAmt = Double.valueOf(l_strChargeAmt);
        }

        //�o����
        Timestamp l_tsPaymentDate = null;
        if(l_request.paymentDate != null)
        {
            l_tsPaymentDate = new Timestamp(l_request.paymentDate.getTime());
        }

        WEB3SrvRegiNewAppliSpec l_srvRegiNewAppliSpec=
            WEB3SrvRegiNewAppliSpec.createSrvRegiNewAppliSpec(l_strInstitutionCode, l_strServiceDiv,
            l_strBranchCode, l_strDbAccountCode, l_tsTrialStartDate, l_tsTrialEndDate, l_tsApplyDate,
            l_strRegistDiv, l_dblChargeAmt, l_tsPaymentDate, WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI);

        //1.10 validate�\���o�^(�T�[�r�X���p�V�K�\�����e)
        this.validateAppliRegist(l_srvRegiNewAppliSpec);

        //1.11 create���X�|���X( )
        WEB3AdminSrvRegiCustomerRegistConfirmResponse l_srvRegiCustomerRegistConfirmResponse =
            (WEB3AdminSrvRegiCustomerRegistConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_srvRegiCustomerRegistConfirmResponse;
    }

    /**
     * (submit�o�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁj�ڋq�o�^�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E16202A1
     */
    protected WEB3AdminSrvRegiCustomerRegistCompleteResponse submitRegist(WEB3AdminSrvRegiCustomerRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminSrvRegiCustomerRegistCompleteRequest)";
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

        //1.5 validate���X����(String[])
        l_admin.validateBranchPermission(l_request.branchCode);

        //1.6 validate����p�X���[�h
        l_admin.validateTradingPassword(l_request.password);

        //1.7 get�،���ЃR�[�h
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //���X�R�[�h
        String l_strBranchCode = l_request.branchCode;

        //�ڋq�R�[�h
        String l_strAccountCode = l_request.accountCode;

        // �g���A�J�E���g�}�l�[�W��
        //�U���̃��N�G�X�g�f�[�^.�ڋq�R�[�h����V���̌ڋq�R�[�h���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //1.8 get�ڋq(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

        //1.9 getAccountCode()
        String l_strDbAccountCode = l_mainAccount.getAccountCode();

        //1.10 create�T�[�r�X���p�V�K�\�����e(String, String, String, String, Timestamp, Timestamp, Timestamp, String, Double, Timestamp)
        //�T�[�r�X�敪
        String l_strServiceDiv  = l_request.serviceDiv;

        //�K�p�J�n��
        Timestamp l_tsTrialStartDate = new Timestamp(l_request.trialStartDate.getTime());

        //�K�p�I����
        Timestamp l_tsTrialEndDate = new Timestamp(l_request.trialEndDate.getTime());

        //�\����
        //get���I�ݒ�()="��"�ł���A���N�G�X�g�f�[�^.�\����==null�̏ꍇ�A���N�G�X�g�f�[�^.�K�p�J�n�����Z�b�g����B
        //��L�ȊO�̏ꍇ�A���N�G�X�g�f�[�^.�\�������Z�b�g����B
        Timestamp l_tsApplyDate = null;

        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);

        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_srvRegiApplicationRequiredService.getLotDiv())
            && l_request.applyDate == null)
        {
            l_tsApplyDate = new Timestamp(l_request.trialStartDate.getTime());
        }
        else
        {
            if (l_request.applyDate != null)
            {
                l_tsApplyDate = new Timestamp(l_request.applyDate.getTime());
            }
        }

        //�o�^�敪
        String l_strRegistDiv = l_request.registDiv;

        //���p����
        String l_strChargeAmt = l_request.chargeAmt;
        Double l_dblChargeAmt = null;

        if (l_strChargeAmt  != null)
        {
            l_dblChargeAmt = Double.valueOf(l_strChargeAmt);
        }

        //�o����
        Timestamp l_tsPaymentDate = null;
        if(l_request.paymentDate != null)
        {
            l_tsPaymentDate = new Timestamp(l_request.paymentDate.getTime());
        }

        WEB3SrvRegiNewAppliSpec l_srvRegiNewAppliSpec=
            WEB3SrvRegiNewAppliSpec.createSrvRegiNewAppliSpec(l_strInstitutionCode, l_strServiceDiv,
            l_strBranchCode, l_strDbAccountCode, l_tsTrialStartDate, l_tsTrialEndDate, l_tsApplyDate,
            l_strRegistDiv, l_dblChargeAmt, l_tsPaymentDate, WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI);

        //1.11 validate�\���o�^(�T�[�r�X���p�V�K�\�����e)
        this.validateAppliRegist(l_srvRegiNewAppliSpec);

        //1.12 ���򏈗� *1
        WEB3SrvRegiRegistService l_srvRegiRegistService =
                (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

        Long l_orderId = null;
        if (WEB3PaymentDivDef.CHARGE.equals(l_request.registDiv) && Double.parseDouble(l_strChargeAmt) > 0)
        {
            SubAccount l_subAccount;
            try
            {
                //1.12.2 getSubAccount(arg0 : SubAccountTypeEnum)
                //[����]  �⏕�����^�C�v="������������i�a����j"
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT); //NotFoundException

                //�⏕����
                WEB3GentradeSubAccount l_GentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
                
                //get����p�X���[�h(�⏕����, ���N�G�X�g�f�[�^.�Ïؔԍ��j
                String l_strTradingPassword = l_srvRegiRegistService.getTradingPassword(l_subAccount, l_request.password);
                
                //1.12.3  submit�]�͍S��(�⏕����, Trader, double, Timestamp, String, String, String)
                l_orderId = new Long(l_srvRegiRegistService.submitRemainingPowerRestraint(
                    l_GentradeSubAccount, null, l_dblChargeAmt.doubleValue(),
                    l_tsPaymentDate, l_strServiceDiv, null,
                    l_strTradingPassword));

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

        //1.13 submit�T�[�r�X�\���o�^
        l_srvRegiRegistService.submitServiceRegist(l_srvRegiNewAppliSpec,l_orderId);

        //���򏈗���2�@@�T�[�r�X�}�X�^�[.���ꏈ���敪 = 1�̏ꍇ
        SrvRegiMasterRow l_srvRegiMasterRow =
            (SrvRegiMasterRow)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterRow.getSpecialProcessDiv();
        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            //submit�O���A�g���(String, String, String, String, Timestamp, Timestamp, boolean)
            //�،���ЃR�[�h = get�،���ЃR�[�h()�̖߂�l
            //���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
            //�����R�[�h = getAccountCode( )�̖߂�l
            //�T�[�r�X�敪 = ���N�G�X�g�f�[�^.�T�[�r�X�敪
            //�K�p�J�n�� = ���N�G�X�g�f�[�^�K�p�J�n��
            //�K�p�I���� = ���N�G�X�g�f�[�^�K�p�I����
            //�V�K�\���敪 = true
            WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
            l_srvRegiOtherOrgService.submitOtherOrgInfo(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strDbAccountCode,
                l_strServiceDiv,
                l_tsTrialStartDate,
                l_tsTrialEndDate,
                true);
        }

        //1.14 create���X�|���X( )
        WEB3AdminSrvRegiCustomerRegistCompleteResponse l_srvRegiCustomerRegistCompleteResponse =
            (WEB3AdminSrvRegiCustomerRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_srvRegiCustomerRegistCompleteResponse;
    }

    /**
     * (validate�\���o�^)<BR>
     * �ڋq�o�^�����̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *         1.1getSrvMaster(String, String, boolean)<BR>
     *         get�T�[�r�X�}�X�^�[( )�̖߂�l==null�̏ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00982<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *         1.2.isAppliPossible( )<BR>
     *        <�o�^�ۃ`�F�b�N><BR>
     *        �\�����\�ȃT�[�r�X�����`�F�b�N����B<BR>
     *        �iis�\���\�̖߂�l==false�̏ꍇ�A��O���X���[����B�j<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01000<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *         1.3.1.getLotDiv( )<BR>
     *        ��e���t�̘_���`�F�b�N��<BR>
     *       ��get���I�ݒ�( )=="��"�A���A����.�������e.�\����!=null�̏ꍇ�A��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01001<BR>
     *       ��get���I�ݒ�( )!="��"�A���A����.�������e.�\����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00847<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *         1.4.1getServiceRegist(String, String, String, String, String, String, )<BR>
     *         get�T�[�r�X�\���o�^( )�̖߂�l != null�A����<BR>
     *         �T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )=="���I�^�{�\��"<BR>
     *         �̏ꍇ�A��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01002<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *         1.5.1getSrvLotInfo(String, String, Timestamp, int)<BR>
     *        ����.�������e.�\�������A���I���̐\�����ԓ��ɂ��邩���`�F�b�N����B<BR>
     *        �iget�T�[�r�X���I���( )�̖߂�l==null�̏ꍇ�A��O���X���[����B�j<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01003<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *         1.6.1<�����_���`�F�b�N><BR>
     *         <�����_���`�F�b�N><BR>
     *         �ȉ��̏ꍇ�A��O���X���[����B <BR>
     *          ������.�������e.���p����==0�̏ꍇ<BR>
     *          ������.�������e.�o����==null�̏ꍇ<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01004<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *         1.6.2.1isBizDate(Timestamp)<BR>
     *         is�c�Ɠ�( )==false�̏ꍇ�A��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00990<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *         1.6.2.2 get������( )<BR>
     *         get������( )������.�������e.�o�����̏ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01005<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�Ǘ��Ҍڋq�o�^ / �i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *        1.6.2.4. �o������get������()�̏ꍇ�A��O���X���[����B<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_01835<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�}(�u�Ǘ��Ҍڋq�o�^ / �i�T�[�r�X���p�Ǘ��ҁjvalidate�\���o�^�v): <BR>
     *        1.6.2.5 get������()�̗��c�Ɠ��iroll()�̖߂�l�j������.�������e.�o�����̏ꍇ�A<BR>
     *        ��O���X���[����B<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_01836<BR>
     * ==========================================================<BR>
     * @@param l_orderSpec - (�������e)<BR>
     * @@roseuid 413E60AE0394
     */
    private void validateAppliRegist(WEB3SrvRegiNewAppliSpec l_orderSpec) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " validateAppliRegist(WEB3SrvRegiNewAppliSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_orderSpec == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //1.1 get�T�[�r�X�}�X�^�[
        //�،���ЃR�[�h
        String l_strInstitutionCode = l_orderSpec.getInstitutionCode();

        //�T�[�r�X�敪
        String l_strServiceDiv = l_orderSpec.getSrvDiv();

        // �T�[�r�X���Ǘ�
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);

        if (l_srvRegiServiceMaster == null)
        {
            //�T�[�r�X�}�X�^�[�f�[�^���擾�ł��܂���B
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.2 is�\���\( )
        if (!l_srvRegiServiceMaster.isAppliPossible())
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 get�\���v�T�[�r�X(boolean)
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

        //validate����\��(�T�[�r�X�}�X�^�[, String, String, String, boolean)
        //�T�[�r�X�}�X�^�[ = get�T�[�r�X�}�X�^�[�i�j�̖߂�l
        //�،���ЃR�[�h = ����.�������e.�،���ЃR�[�h
        //���X�R�[�h = ����.�������e.���X�R�[�h
        //�����R�[�h = ����.�������e.�����R�[�h
        //�V�K�\���敪 = ture
        l_srvRegiServiceInfoManagement.validateSpecialApply(
            l_srvRegiServiceMaster,
            l_orderSpec.getInstitutionCode(),
            l_orderSpec.getBranchCode(),
            l_orderSpec.getAccountCode(),
            true);

        //1.3.1 get���I�ݒ�( )
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        //get���I�ݒ�( )!="��"�A���A����.�������e.�\����==null�̏ꍇ�A��O���X���[����B
        if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) && l_orderSpec.getAppliDate() == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00847,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.4<���򏈗� *1>
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = null;
        WEB3SrvRegiRegistService l_srvRegiRegistService = null;

        //WEB3AdminSrvRegiAccountRegistService
        //�T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X
        l_srvRegiRegistService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            //1.4.1 get�T�[�r�X�\���o�^(String, String, String, String, String, String, )
            l_gentradeSrvRegiApplication =
                l_srvRegiRegistService.getServiceRegist(l_orderSpec.getInstitutionCode(),
                l_orderSpec.getBranchCode(), l_orderSpec.getSrvDiv(),
                l_orderSpec.getAccountCode(), WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                WEB3EffectiveDivDef.EFFECTIVE, false);

            // get�T�[�r�X�\���o�^( )�̖߂�l != null�A����
            // �T�[�r�X�\���o�^�I�u�W�F�N�g.get�\�����I�敪( )=="���I�^�{�\��"
            // �̏ꍇ�A��O���X���[����B
            if (l_gentradeSrvRegiApplication != null
                && WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()))
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

        }

        //1.5<���򏈗� *2>
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            //1.5.1 get�T�[�r�X���I���
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            String l_strAdminInstitutionCode = l_admin.getInstitutionCode();
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo = l_srvRegiServiceInfoManagement.getSrvLotInfo(
                l_strAdminInstitutionCode, l_srvRegiServiceMaster.getSrvDiv(),
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

            //1.5.2 validate�K�p����(String, String, String, String, Timestamp, Timestamp, Long)
            l_srvRegiRegistService.validateAppliPeriod(l_orderSpec.getInstitutionCode(),
                l_orderSpec.getSrvDiv(),l_orderSpec.getBranchCode(), l_orderSpec.getAccountCode(),
                new Timestamp(WEB3DateUtility.toDay(l_orderSpec.getAppliStartDate()).getTime()),
                new Timestamp(WEB3DateUtility.toDay(l_orderSpec.getAppliEndDate()).getTime()), null);

        }

        //1.6 <���򏈗� *3>
        if (WEB3PaymentDivDef.CHARGE.equals(l_orderSpec.getPaymentDiv()))
        {
            //1.6.1<�����_���`�F�b�N>
            if (l_orderSpec.getUseAmt() == null ||
                l_orderSpec.getUseAmt().doubleValue() == 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01030,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            if (l_orderSpec.getPaymentDate() == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.6.2 <�o�����̃`�F�b�N>
            //1.6.2.1 is�c�Ɠ�(Timestamp)
            if (!WEB3SrvRegiTradingTimeManagement.isBizDate(l_orderSpec.getPaymentDate()))
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00990,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.6.2.2 get������( )
            log.debug("WEB3SrvRegiTradingTimeManagement.getOrderBizDate()==="+WEB3SrvRegiTradingTimeManagement.getOrderBizDate());

            Date l_date = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();

            //get������()������.�������e.�o�����̏ꍇ�A��O���X���[����B
            if(WEB3DateUtility.compareToSecond(l_orderSpec.getPaymentDate(), l_date) <= 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01835,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.6.3 get�ڋq(String, String, String)
            //�g���A�J�E���g�}�l�[�W��
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //���X�R�[�h
            String l_strBranchCode = l_orderSpec.getBranchCode();

            //�����R�[�h
            String l_strAccountCode = l_orderSpec.getAccountCode();

            //�ڋq
            WEB3GentradeMainAccount l_gentradeMainAccount = null;

            //�ڋq
            SubAccount l_subAccount;

            WEB3GentradeSubAccount l_gentradeSubAccount = null;
            try
            {
                //1.6.3 get�ڋq(String, String, String)
                l_gentradeMainAccount = l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

                //1.6.4 getSubAccount(arg0 : SubAccountTypeEnum)
                //[����]  �⏕�����^�C�v="������������i�a����j"
                l_subAccount = l_gentradeMainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT); //NotFoundException

                //�⏕����
                l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            }
            catch(NotFoundException l_nfd)
            {
                log.error(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //���p����
            Double l_useAmt = l_orderSpec.getUseAmt();

            //�o����
            Timestamp l_tsPaymentDate = l_orderSpec.getPaymentDate();

            //1.6.6 validate����]��(�⏕����, Trader, double, Timestamp, String, String, String)
            l_srvRegiRegistService.validateTradingPower(
                l_gentradeSubAccount, null, l_useAmt.doubleValue(), l_tsPaymentDate,
                l_srvRegiServiceMaster.getSrvDiv(), null);
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
