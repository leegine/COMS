head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplicationInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\�����̓T�[�r�XImpl(WEB3SrvRegiApplicationInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28 ���o�� �V�K�쐬
Revesion History : 2007/06/05 �����F (���u) ���f��242,243
Revesion History : 2007/06/26 �����Q (���u) ���f��272
Revesion History : 2007/11/01 ���� (���u) ���f��304
Revesion History : 2008/02/18 ���n�m (���u) �d�l�ύX�E���f��311,325
Revesion History : 2008/03/03 ���g (���u) �d�l�ύX ���f��331
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiConsDoc;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiApplyKindDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiFreeAttributeApplyDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputResponse;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.message.WEB3SrvRegiConsentRequest;
import webbroker3.srvregi.message.WEB3SrvRegiConsentResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiApplicationInputService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�\�����̓T�[�r�XImpl)<BR>
 * �T�[�r�X���p�\�����̓T�[�r�X�����N���X<BR>
 *
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiApplicationInputServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiApplicationInputService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiApplicationInputServiceImpl.class);

    /**
     * @@roseuid 416F3925000F
     */
    public WEB3SrvRegiApplicationInputServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�\�����͏������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̃N���X�ɂ���āA���ӏ���ʃ��N�G�X�g( )���\�b�h�A<BR>
     * �܂��͗��p�\�����̓��N�G�X�g( )���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5F20D032D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if(l_request instanceof WEB3SrvRegiConsentRequest)
        {
            WEB3SrvRegiConsentResponse l_response =
                docScreenRequest((WEB3SrvRegiConsentRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3SrvRegiApplyInputRequest)
        {
            WEB3SrvRegiApplyInputResponse l_response =
                useAppliInputRequest((WEB3SrvRegiApplyInputRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (���ӏ���ʃ��N�G�X�g)<BR>
     * �T�[�r�X���p�\�����ӏ���ʏƉ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j���ӏ���ʃ��N�G�X�g�v�Q��<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.13.���N�G�X�g�f�[�^.���������\���敪 = '1' �̏ꍇ<BR>
     * �@@�@@�@@�@@1.13.1.�ȉ��̏����̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@get�T�[�r�X�\���������() == null �̏ꍇ�A���́A<BR>
     * �@@�@@�@@�@@get�T�[�r�X�\���������().�\�������敪 == '2'(�\���s��)<BR>
     * �@@�@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02805  <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.13.3.get�����Ώۊ���( ) == null<BR>�@@
     * �@@�@@�@@�@@�̏ꍇ��O���X���[����<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02806 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.14.���N�G�X�g�f�[�^.���������\���敪 == null �̏ꍇ<BR>
     * �@@�@@�@@�@@1.14.1.get�T�[�r�X�\���������().�\�������敪 == <BR>
     * �@@�@@�@@�@@'1'(�����Ώ�)�@@�Ⴕ����<BR>
     * �@@�@@�@@�@@'2'(�\���s��) �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02807 <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p���ӏ����N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiConsentResponse
     * @@roseuid 412567DC02D1
     */
    protected WEB3SrvRegiConsentResponse docScreenRequest(WEB3SrvRegiConsentRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " docScreenRequest(WEB3SrvRegiConsentRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate
        l_request.validate();

        //1.2 validate������t�\
        log.debug("validate������t�\");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.5 validate����\�ڋq
        log.debug("validate����\�ڋq");
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            WEB3SrvRegiConsentResponse l_response = (WEB3SrvRegiConsentResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.6 get�T�[�r�X�}�X�^�[
        log.debug("get�T�[�r�X�}�X�^�[");
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.7  is�ڋq�\���\
        boolean l_blnAccountAppliPossible =
            l_srvRegiServiceInfoManagement.isAccountAppliPossible((WEB3GentradeSubAccount)l_subAccount, l_srvRegiServiceMaster);
        log.debug("is�ڋq�\���\");
        if (!l_blnAccountAppliPossible)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.8 validate�d�q������( )
        l_srvRegiServiceInfoManagement.validateBatoAgreement(l_srvRegiServiceMaster);//WEB3BaseException

        //�T�[�r�X�}�X�^.���ꏈ���敪 = null�i�ʏ�T�[�r�X�j �ȊO�̏ꍇ
        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
        if (l_strSpecialProcessDiv != null)
        {
            //�擾�����⏕�����I�u�W�F�N�g
            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;

            //is�V�K�\��(�⏕����, String)
            //[����]
            //�⏕���� = �擾�����⏕�����I�u�W�F�N�g
            //�T�[�r�X�敪 = ���N�G�X�g�f�[�^.�h�c
            boolean l_blnIsNewApply =
                l_srvRegiServiceInfoManagement.isNewApply(
                    l_gentradeSubAccount,
                    l_request.serviceDiv);

            //validate����\��(�T�[�r�X�}�X�^�[, String, String, String, boolean)
            //[����]
            //�T�[�r�X�}�X�^ = �擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g
            //�،���ЃR�[�h = �⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
            //���X�R�[�h = �⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
            //�����R�[�h = �⏕�����I�u�W�F�N�g.getMainAccount( ).getAccountCode( )
            //�V�K�\���敪 = is�V�K�\��( ) �̖߂�l
            l_srvRegiServiceInfoManagement.validateSpecialApply(
                l_srvRegiServiceMaster,
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode(),
                l_blnIsNewApply);
        }

        //U00859  start
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        String l_strProvidModel = l_srvRegiApplicationRequiredService.getSupplyDiv();

        if (WEB3SupplyDivDef.FREE_SUPPLY.equals(l_strProvidModel))
        {
            if (!l_srvRegiServiceInfoManagement.isCommCond((WEB3GentradeSubAccount)l_subAccount, l_srvRegiServiceMaster))
            {
                log.debug("�\����ʋ敪�w��G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01179,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        //U00859  end

        //get�T�[�r�X�\���������(String, String, String, String, String)
        List l_lisServiceAppliAttributeInfo =
            l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                l_strInstitutionCode,
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_request.serviceDiv,
                null);

        WEB3SrvRegiConsentResponse l_response = (WEB3SrvRegiConsentResponse)l_request.createResponse();

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;

        //���N�G�X�g�f�[�^.���������\���敪 = '1' �̏ꍇ
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //get�T�[�r�X�\���������() == null �̏ꍇ�A
            //���́A
            //get�T�[�r�X�\���������().�\�������敪 == '2'(�\���s��) �̏ꍇ�A
            //��O���X���[����B
            if (l_lisServiceAppliAttributeInfo == null
                || WEB3AppliAttributeDef.CANNOT_APPLI.equals(
                    ((SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0)).getAppliAttribute()))
            {
                log.debug("�T�[�r�X�\���������null�A�܂��͐\�������敪��'2'(�\���s��)�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02805,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�T�[�r�X�\���������null�A�܂��͐\�������敪��'2'(�\���s��)�ł��B");
            }

            //get�����Ώۊ���( )
            String l_strFreeTargetPeriod =
                l_srvRegiApplicationRequiredService.getFreeTargetPeriod();

            //get�����Ώۊ���( ) == null�@@�̏ꍇ��O���X���[����B
            if (l_strFreeTargetPeriod == null)
            {
                log.debug("�����Ώۊ��Ԃ����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02806,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����Ώۊ��Ԃ����w��ł��B");
            }
            l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0);
            //�v���p�e�B�Z�b�g
            //�\�������敪 = get�T�[�r�X�\���������().�\�������敪
            l_response.applyAttributeDiv = l_srvAppliAttributeRow.getAppliAttribute();
            //�\����������From = get�T�[�r�X�\���������().�K�p����From
            l_response.applyAttributePeriodFrom = l_srvAppliAttributeRow.getAppliStartDate();
            //�\����������To = get�T�[�r�X�\���������().�K�p����To
            l_response.applyAttributePeriodTo = l_srvAppliAttributeRow.getAppliEndDate();
            //���������\���敪 = '1'�i���������\���j
            l_response.freeAttributeApplyDiv =
                WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY;
        }

        //1.8 get���ӏ�����
        log.debug("get���ӏ�����");
        WEB3SrvRegiConsDoc l_srvRegiConsDoc = l_srvRegiServiceMaster.getConsDoc(false);

        //1.9 create���X�|���X
        l_response.consentSentence = l_srvRegiConsDoc.getCons();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (���p�\�����̓��N�G�X�g)<BR>
     * �T�[�r�X���p�\�����͉�ʏƉ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j���p�\�����̓��N�G�X�g�v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j���p�\�����̓��N�G�X�g�v): <BR>
     *         1.7.isMainAccountAppli(�⏕����, WEB3SrvRegiServiceMaster) <BR>
     *          is�ڋq�\���\( )=false�̏ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01000<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j���p�\�����̓��N�G�X�g�v): <BR>
     *         1.8.2.2  <�\����ʋ敪="�����\��"�ł���A����is�萔������( )=false�̏ꍇ�A<BR>
     *       �i�\����ʋ敪�w��G���[�j�Ƃ��ė�O���X���[����B><BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01179<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j���p�\�����̓��N�G�X�g�v): <BR>
     *         1.8.3.1  get�񋟌`��( )=null �ł���A����<BR>
     *        ���N�G�X�g�f�[�^.�\����ʋ敪="�����\��"�̏ꍇ�A<BR>
     *        �\����ʋ敪�w��G���[�Ƃ��ė�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01179<BR>
     * ==========================================================<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.10.���N�G�X�g�f�[�^.���������\���敪 = '1' �̏ꍇ<BR>
     * �@@�@@�@@�@@1.10.1.�ȉ��̏����̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@get�T�[�r�X�\���������() == null �̏ꍇ�A���́A<BR>
     * �@@�@@�@@�@@get�T�[�r�X�\���������().�\�������敪 == '2'(�\���s��)<BR>
     * �@@�@@�@@�@@�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02805  <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.10.3.get�����Ώۊ���( ) == null<BR>�@@
     * �@@�@@�@@�@@�̏ꍇ��O���X���[����<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02806 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :1.11.���N�G�X�g�f�[�^.���������\���敪 == null �̏ꍇ<BR>
     * �@@�@@�@@�@@1.11.1.get�T�[�r�X�\���������().�\�������敪 == <BR>
     * �@@�@@�@@�@@'1'(�����Ώ�)�@@�Ⴕ����<BR>
     * �@@�@@�@@�@@'2'(�\���s��) �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class         :  WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag            :  BUSINESS_ERROR_02807 <BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�\�����̓��N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyInputResponse
     * @@roseuid 4125674A01A9
     */
    protected WEB3SrvRegiApplyInputResponse useAppliInputRequest(WEB3SrvRegiApplyInputRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " useAppliInputRequest(WEB3SrvRegiApplyInputRequest)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate
        l_request.validate();

        //1.2 validate������t�\
        log.debug("validate������t�\");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.5 validate����\�ڋq
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        log.debug("validate����\�ڋq");
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            WEB3SrvRegiApplyInputResponse l_response = (WEB3SrvRegiApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.6 get�T�[�r�X�}�X�^�[
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.7  is�ڋq�\���\
        boolean l_blnAccountAppliPossible =
            l_srvRegiServiceInfoManagement.isAccountAppliPossible((WEB3GentradeSubAccount)l_subAccount, l_srvRegiServiceMaster);
        log.debug("is�ڋq�\���\");
        if (!l_blnAccountAppliPossible)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.7.1 get�\���v�T�[�r�X
        log.debug(" get�\���v�T�[�r�X");
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        //1.7.1.1 get���I�ݒ�
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();
        
        //1.8 validate�d�q������( )
        l_srvRegiServiceInfoManagement.validateBatoAgreement(l_srvRegiServiceMaster);//WEB3BaseException

        //�T�[�r�X�}�X�^.���ꏈ���敪 = null�i�ʏ�T�[�r�X�j �ȊO�̏ꍇ
        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
        if (l_strSpecialProcessDiv != null)
        {
            //�擾�����⏕�����I�u�W�F�N�g
            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;

            //is�V�K�\��(�⏕����, String)
            //[����]
            //�⏕���� = �擾�����⏕�����I�u�W�F�N�g
            //�T�[�r�X�敪 = ���N�G�X�g�f�[�^.�h�c
            boolean l_blnIsNewApply =
                l_srvRegiServiceInfoManagement.isNewApply(
                    l_gentradeSubAccount,
                    l_request.serviceDiv);

            //validate����\��(�T�[�r�X�}�X�^�[, String, String, String, boolean)
            //[����]
            //�T�[�r�X�}�X�^ = �擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g
            //�،���ЃR�[�h = �⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
            //���X�R�[�h = �⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
            //�����R�[�h = �⏕�����I�u�W�F�N�g.getMainAccount( ).getAccountCode( )
            //�V�K�\���敪 = is�V�K�\��( ) �̖߂�l
            l_srvRegiServiceInfoManagement.validateSpecialApply(
                l_srvRegiServiceMaster,
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode(),
                l_blnIsNewApply);
        }

        //get�T�[�r�X�\���������(String, String, String, String, String)
        List l_lisServiceAppliAttributeInfo =
            l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                l_strInstitutionCode,
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_request.serviceDiv,
                null);

        //�����Ώۊ���
        String l_strFreeTargetPeriod = null;

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
        if (l_lisServiceAppliAttributeInfo != null)
        {
            l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0);
        }

        //���N�G�X�g�f�[�^.���������\���敪 = '1'�@@�̏ꍇ
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //get�T�[�r�X�\���������() == null �̏ꍇ�A
            //���́A
            //get�T�[�r�X�\���������().�\�������敪 == '2'(�\���s��) �̏ꍇ�A
            //��O���X���[����B
            if (l_lisServiceAppliAttributeInfo == null
                || WEB3AppliAttributeDef.CANNOT_APPLI.equals(
                    l_srvAppliAttributeRow.getAppliAttribute()))
            {
                log.debug("�T�[�r�X�\���������null�A�܂��͐\�������敪��'2'(�\���s��)�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02805,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�T�[�r�X�\���������null�A�܂��͐\�������敪��'2'(�\���s��)�ł��B");
            }
            //get�����Ώۊ���( )
            l_strFreeTargetPeriod =
                l_srvRegiApplicationRequiredService.getFreeTargetPeriod();

            //get�����Ώۊ���( ) == null�@@�̏ꍇ��O���X���[����B
            if (l_strFreeTargetPeriod == null)
            {
                log.debug("�����Ώۊ��Ԃ����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02806,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����Ώۊ��Ԃ����w��ł��B");
            }
        }

        //1.8  <���򏈗� *1>
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo = null;
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfoBefore = null;
        WEB3SrvRegiChargeInfo[] l_srvRegiChargeInfo = null;
        boolean l_blnTrialAppliPossible = true;
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("//1.8.1 get�񋟌`��");
            //1.8.1 get�񋟌`��
            String l_strProvidModel = l_srvRegiApplicationRequiredService.getSupplyDiv();

            //1.8.2  <get�񋟌`��!=null�̏ꍇ>
            boolean l_blnCommCond = false;
            if (l_strProvidModel != null)
            {
                log.debug("<get�񋟌`��!=null�̏ꍇ>");
                //1.8.2.1 is�萔������
                l_blnCommCond =
                    l_srvRegiServiceInfoManagement.isCommCond((WEB3GentradeSubAccount)l_subAccount, l_srvRegiServiceMaster);

                //1.8.2.2  <�\����ʋ敪="�����\��"�ł���A����is�萔������( )=false�̏ꍇ�A�i�\����ʋ敪�w��G���[�j�Ƃ��ė�O���X���[����B>
                if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv) &&
                    !l_blnCommCond)
                {
                    log.debug("�\����ʋ敪�w��G���[");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01179,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
            
            //��Q�Ή� NO_U1570
            // �ȉ��̏����𖞂����Ă���ꍇ�A�ȉ��̏������X�L�b�v����B
            // �@@is�萔������() = true
            // �A���N�G�X�g.�\����ʋ敪 != 1(�p���\��)
            if (!l_blnCommCond || (WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(l_request.applyKindDiv)))
            {
                //get�񋟌`��( )=null �ł���A�����N�G�X�g�f�[�^.�\����ʋ敪="�����\��"�̏ꍇ
                if (l_strProvidModel == null && WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv))
                {
                    log.debug("�����\��");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01179,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //���N�G�X�g�f�[�^.���������\���敪 == null �̏ꍇ
                if (l_request.freeAttributeApplyDiv == null)
                {
                    //1.8.1  is���p�\���\
                    log.debug("is���p�\���\");
                    l_blnTrialAppliPossible = l_srvRegiServiceInfoManagement.isTrialAppliPossible
                        (l_strInstitutionCode, l_strBranchCode, l_request.serviceDiv, l_strAccountCode);
                    
                    //1.8.2 get�T�[�r�X���p���ԗ����ꗗ
                    WEB3SrvRegiServiceUsePeriodAmt[] l_srvRegiServiceUsePeriodAmt = l_srvRegiServiceMaster.getSrvUseTermAmtList();
                    
                    //1.8.3  <�J��Ԃ����� *2>
                    //1.8.3.1 �T�[�r�X���p���ԗ������
                    int l_intLength = l_srvRegiServiceUsePeriodAmt.length;
                    l_srvRegiChargeInfo = new WEB3SrvRegiChargeInfo[l_intLength];
                    for (int i = 0; i < l_intLength; i++)
                    {
                        log.debug("//1.8.3.2  <�v���p�e�B�E�Z�b�g>");
                        l_srvRegiChargeInfo[i] = new WEB3SrvRegiChargeInfo();
                        //1.8.3.2  <�v���p�e�B�E�Z�b�g>
                        //�����p����ID=�擾�����T�[�r�X���p���ԗ����I�u�W�F�N�g.get�ʔ�( )
                        l_srvRegiChargeInfo[i].chargeId =
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmt[i].getConsecutiveNumbers());
                        //�����p���ԒP�ʋ敪=�擾�����T�[�r�X���p���ԗ����I�u�W�F�N�g.get���p���ԋ敪( )
                        l_srvRegiChargeInfo[i].chargeDiv = l_srvRegiServiceUsePeriodAmt[i].getSrvUsePeriodDiv();
                        //�����p����=�擾�����T�[�r�X���p���ԗ����I�u�W�F�N�g.get���p����( )
                        l_srvRegiChargeInfo[i].chargePeriod =
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmt[i].getSrvUsePeriod());
                        //�����p����=�擾�����T�[�r�X���p���ԗ����I�u�W�F�N�g.get���p����( )
                        l_srvRegiChargeInfo[i].chargeAmt =
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmt[i].getUseAmt());
                        //�������敪="�L��"
                        l_srvRegiChargeInfo[i].invalidDiv = false;
                    }
                }
            }
        }
        //1.9 <���򏈗� *3>
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("//1.9.1 get�T�[�r�X���I���");
            //1.9.1 get�T�[�r�X���I���
            Timestamp l_ts = GtlUtils.getTradingSystem( ).getSystemTimestamp();
            l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_ts, 0);

            //1.9.2 get�T�[�r�X���I���
            l_srvRegiServiceLotInfoBefore =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_ts, -1);
        }
        //1.10 create���X�|���X
        WEB3SrvRegiApplyInputResponse l_response = (WEB3SrvRegiApplyInputResponse)l_request.createResponse();

        //1.11 <���X�|���X�E�Z�b�g>
        //�����I�ݒ�=�擾�����\���v�T�[�r�X�I�u�W�F�N�g.get���I�ݒ�( )
        l_response.lotteryDiv = l_srvRegiApplicationRequiredService.getLotDiv();
        //���T�[�r�X����=�擾�����T�[�r�X�}�X�^�[�I�u�W�F�N�g.get�T�[�r�X����( )
        l_response.serviceName = l_srvRegiServiceMaster.getSrvName();
        //���T�[�r�X���e=�擾�����\���v�T�[�r�X�I�u�W�F�N�g.get�T�[�r�X���e( )
        l_response.serviceContent = l_srvRegiApplicationRequiredService.getSrvContents();
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_srvRegiApplicationRequiredService.getLotDiv()))
        {
            log.debug("//���^�p�敪=(*1) ");
            //���^�p�敪=(*1)
            //(*1-1) �\���v�T�[�r�X�I�u�W�F�N�g.get���I�ݒ�( )="��"�̏ꍇ�Anul�����Z�b�g����B
            l_response.useDiv = null;
            // *---<���I�ݒ�="��"�̏ꍇ>---*
            if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv)
                || (l_lisServiceAppliAttributeInfo != null && l_strFreeTargetPeriod != null))
            {
                log.debug("<���I�ݒ�=���̏ꍇ");
                l_response.chargeInfo = null;
                l_response.trialDiv = null;
                l_response.trialPeriod = null;
            }
            else
            {
                log.debug("�����p���ԗ������ =<*2 �J��Ԃ�����>�ō쐬�����z��");
                //�����p���ԗ������ =<*2 �J��Ԃ�����>�ō쐬�����z��
                l_response.chargeInfo = l_srvRegiChargeInfo;
                
                //�d�l�ύX�Ή� NO_201
				String l_trialDiv = l_srvRegiApplicationRequiredService.getTrialPeriodDiv();
				Integer l_trialPeriod = l_srvRegiApplicationRequiredService.getTrialPeriod();
				
				//�T�[�r�X�\���v�e�[�u���Ɏ��p�\���敪����Ԃ��ݒ肳��Ă���ꍇ
				if(WEB3StringTypeUtility.isNotEmpty(l_trialDiv) &&
					(l_trialPeriod != null) && !(l_trialPeriod.equals("")))
				{
					log.debug("trialDiv != null && trialPeriod != null �̏ꍇ ");
					//�T�[�r�X���Ǘ�.is���p�\���\( )=true�̏ꍇ
					if (l_blnTrialAppliPossible)
					{
						log.debug("is���p�\���\( )=true�̏ꍇ ");
						l_response.trialDiv = l_trialDiv;
						l_response.trialPeriod = l_trialPeriod.toString();
					}
					//�T�[�r�X���Ǘ�.is���p�\���\( )=false�̏ꍇ
					else
					{
						log.debug("is���p�\���\( )=false�̏ꍇ ");
						l_response.trialDiv = l_trialDiv;
						l_response.trialPeriod = null;
					}
				}
				//�T�[�r�X�\���v�e�[�u���Ɏ��p�\���敪����Ԃ��ݒ肳��Ă��Ȃ��ꍇ
				else
				{
					log.debug("trialDiv = null && trialPeriod = null �̏ꍇ ");
					l_response.trialDiv = null;
					l_response.trialPeriod = null;
				}
            }

            //����W�g�@@�@@�@@�@@�@@�@@�@@ =null
            l_response.applyMax = null;
            //���\�����ԁi���j�@@�@@�@@ =null
            l_response.applyStartDate = null;
            //���\�����ԁi���j�@@�@@�@@ =null
            l_response.applyEndDate = null;
            //�����I���@@�@@�@@�@@�@@�@@�@@ =null
            l_response.lotteryDate = null;
            //���K�p�J�n���@@�@@�@@�@@ =null
            l_response.trialStartDate = null;
            //���K�p�I�����@@�@@�@@�@@ =null
            l_response.trialEndDate = null;
            //�����p�����@@�@@�@@�@@�@@�@@ =null
            l_response.chargeAmt = null;
            //�����D�P�ʁ@@�@@�@@�@@�@@�@@ =null
            l_response.biddingPriceUnit = null;
            //���O��̍ō����D�z =null
            l_response.maxSuccBidding = null;
            //���O��̍Œᗎ�D�z =null
            l_response.minSuccBidding = null;
            //���O��̉��d���ϊz =null
            l_response.weightedAverage = null;

            //[���N�G�X�g�f�[�^.���������\���敪 == '1'(���������\��)�̏ꍇ]
            if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
                l_request.freeAttributeApplyDiv))
            {
                //���\�������敪     = get�T�[�r�X�\���������().�\�������敪
                l_response.applyAttributeDiv = l_srvAppliAttributeRow.getAppliAttribute();
                //�������Ώۊ���     = get�����Ώۊ���()�̖߂�l
                l_response.freeTargetPeriod = l_strFreeTargetPeriod;
                //�����������\���敪 = '1'
                l_response.freeAttributeApplyDiv =
                    WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY;
            }
        }
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_srvRegiApplicationRequiredService.getLotDiv()))
        {
            log.debug("//(*1-2) �\���v�T�[�r�X�I�u�W�F�N�g.get���I�ݒ�( )=�L�̏ꍇ");
            //(*1-2) �\���v�T�[�r�X�I�u�W�F�N�g.get���I�ݒ�( )="�L"�̏ꍇ
            // �擾�����T�[�r�X���I���I�u�W�F�N�g.get�^�p�敪( )���Z�b�g����B
            l_response.useDiv = l_srvRegiServiceLotInfo.getInvestDiv();

            // *---<���I�ݒ�="�L"�̏ꍇ>---*
            //�����p���ԗ������ =null
            l_response.chargeInfo = null;
            //�����p���ԒP�ʋ敪 =null
            l_response.trialDiv = null;
            //�����p���ԁ@@�@@�@@�@@�@@�@@ =null
            l_response.trialPeriod = null;
            //����W�g�@@�@@�@@�@@�@@�@@�@@ =�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get��W�g( )
            log.debug("��W�g");
            if (l_srvRegiServiceLotInfo.getPublicOfferingQty() == null)
            {
                l_response.applyMax = null;
            }
            else
            {
                l_response.applyMax = l_srvRegiServiceLotInfo.getPublicOfferingQty().toString();
            }

            //���\�����ԁi���j�@@�@@�@@ =�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get�\�����ԁi���j( )
            l_response.applyStartDate = l_srvRegiServiceLotInfo.getAppliDateFrom();
            //���\�����ԁi���j�@@�@@�@@ =�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get�\�����ԁi���j( )
            l_response.applyEndDate = l_srvRegiServiceLotInfo.getAppliDateTo();
            //�����I���@@�@@�@@�@@�@@�@@�@@ =�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get���I��( )
            l_response.lotteryDate = l_srvRegiServiceLotInfo.getLotDate();
            //���K�p�J�n���@@�@@�@@�@@ =�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get�K�p�J�n��( )
            l_response.trialStartDate = l_srvRegiServiceLotInfo.getAppliStartDate();
            //���K�p�I�����@@�@@�@@�@@ =�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get�K�p�I����( )
            l_response.trialEndDate = l_srvRegiServiceLotInfo.getAppliEndDate();
            //�����p�����@@�@@�@@�@@�@@�@@ =�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get���p����( )
            if (l_srvRegiServiceLotInfo.isAuctionSetting() &&
                l_srvRegiServiceLotInfo.getUseAmt() > 0)
            {
                //�ŗ��I�u�W�F�N�g�𐶐�����B
                log.debug("�،���ЃR�[�h = " + l_subAccount.getInstitution().getInstitutionCode() + ", ������ = " + l_srvRegiServiceLotInfo.getPaymentDate());
                WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3DutyTypeDef.CONSUMPTION_TAX,
                    l_srvRegiServiceLotInfo.getPaymentDate());

                //WEB3-SRVREGI-A-�e�s-0136
                //�u�i���R�[�h�̗��p���� / �i�P�{�ŗ��I�u�W�F�N�g.get�ŗ�()�̖߂�l�^100�j�̌v�Z���ʂ̏��������l�̌ܓ��������́v
                l_response.chargeAmt = WEB3StringTypeUtility.formatNumber(Math.rint(l_srvRegiServiceLotInfo.getUseAmt() / (1 + l_taxRate.getTaxRate() / 100)));
            }
            else
            {
                log.debug("�@@(*3-2) �T�[�r�X���I���.is�I�[�N�V�����ݒ�( )==false�̏ꍇ�|�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get���p����( )���Z�b�g����B ");
                l_response.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo.getUseAmt());
            }
            //�����D�P�ʁ@@�@@�@@�@@�@@�@@ =�擾�����\���ΏۂƂȂ�T�[�r�X���I���.get���D�P��( )
            if (l_srvRegiServiceLotInfo.getBiddingPrice() == null)
            {
                l_response.biddingPriceUnit = null;
            }
            else
            {
                l_response.biddingPriceUnit = l_srvRegiServiceLotInfo.getBiddingPrice().toString();
            }
            //���\�������敪   = null
            l_response.applyAttributeDiv = null;
            //�������Ώۊ���  = null
            l_response.freeTargetPeriod = null;
            //�����������\���敪 = null
            l_response.freeAttributeApplyDiv = null;

            //*---<�O�񕪂̃T�[�r�X���I���=null�̏ꍇ>---*
            if (l_srvRegiServiceLotInfoBefore == null)
            {
                log.debug("�O�񕪂̃T�[�r�X���I���=null�̏ꍇ");
                //���O��̍ō����D�z =null
                l_response.maxSuccBidding = null;
                //���O��̍Œᗎ�D�z =null
                l_response.minSuccBidding = null;
                //���O��̉��d���ϊz =null
                l_response.weightedAverage = null;
            }
            //*---<�O�񕪂̃T�[�r�X���I���!=null�̏ꍇ>---*
            else
            {
                log.debug("�O�񕪂̃T�[�r�X���I���!=null�̏ꍇ");
                //���O��̍ō����D�z =�擾�����O�񕪂̃T�[�r�X���I���.get�ō����D�z( )
                if (l_srvRegiServiceLotInfoBefore.getHighSuccessBid() == null)
                {
                    l_response.maxSuccBidding = null;
                }
                else
                {
                    l_response.maxSuccBidding = l_srvRegiServiceLotInfoBefore.getHighSuccessBid().toString();
                }

                //���O��̍Œᗎ�D�z =�擾�����O�񕪂̃T�[�r�X���I���.get�Œᗎ�D�z( )
                if (l_srvRegiServiceLotInfoBefore.getLowSuccessBid() == null)
                {
                    l_response.minSuccBidding = null;
                }
                else
                {
                    l_response.minSuccBidding = l_srvRegiServiceLotInfoBefore.getLowSuccessBid().toString();
                }

                //���O��̉��d���ϊz =�擾�����O�񕪂̃T�[�r�X���I���.get���d���ϊz( )
                if (l_srvRegiServiceLotInfoBefore.getAverageSuccessBid() == null)
                {
                    l_response.weightedAverage = null;
                }
                else
                {
                    l_response.weightedAverage = l_srvRegiServiceLotInfoBefore.getAverageSuccessBid().toString();
                }

            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
