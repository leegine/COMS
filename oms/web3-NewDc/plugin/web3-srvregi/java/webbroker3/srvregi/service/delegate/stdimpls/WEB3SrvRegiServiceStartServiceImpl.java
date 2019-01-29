head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceStartServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�T�[�r�X�N���T�[�r�XImpl(WEB3SrvRegiServiceStartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 ���o�� �V�K�쐬
Revesion History : 2005/10/05 ��؁@@���R�I(SRA) �g�����X�����N�Ή�
Revesion History : 2008/02/18 ���V�� �d�l�ύX���f��No.312
Revesion History : 2008/02/29 ���V�� �d�l�ύX���f��No.329
Revesion History : 2008/03/04 ���g �d�l�ύX���f��No.341
Revesion History : 2008/03/06 ���g (���u) ���f��344
Revesion History : 2008/05/20 �Ԑi (���u) ���f��369
Revision History : 2008/07/18 �g�C�� (���u) ���f��No.399
Revision History : 2008/08/11 �g�C�� (���u) ���f��No.402
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.util.Date;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.WEB3SrvRegiReservedWordChange;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiMobileFlagDef;
import webbroker3.srvregi.define.WEB3SrvRegiSrvRegiExecErrDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiExecRequest;
import webbroker3.srvregi.message.WEB3SrvRegiExecResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceStartService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�T�[�r�X���p�T�[�r�X�N���T�[�r�XImpl)<BR>
 * �T�[�r�X���p�T�[�r�X�N���T�[�r�X�����N���X<BR>
 *
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiServiceStartServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiServiceStartService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceStartServiceImpl.class);

    /**
     * @@roseuid 416F3927009C
     */
    public WEB3SrvRegiServiceStartServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�T�[�r�X�N���������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�j�T�[�r�X�N���v�Q��<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�T�[�r�X�N���v): <BR>
     *         1.6.1is���p�\( )<BR>
     *          ���p�\�`�F�b�N<BR>
     *          is���p�\( )==false�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01011<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�T�[�r�X�N���v): <BR>
     * �@@�@@�@@�@@�@@1.7.3is�O���،������J��( ) == false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01341<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�T�[�r�X�N���v): <BR>
     * �@@�@@�@@�@@�@@1.7.4 is�@@�l( ) == true �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02884<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�T�[�r�X�N���v): <BR>
     * �@@�@@�@@�@@�@@1.7.5 �ڋq.get�ڋq�s ().���Z�^�񋏏Z�敪 != 0:�Z���҂̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02708<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u�i�T�[�r�X���p�j�T�[�r�X�N���v): <BR>
     * �@@�@@�@@�@@�@@1.9.2get�O���A�g���()�̖߂�l�� null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F784770169
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

        WEB3SrvRegiExecRequest l_srvRegiExecRequest = (WEB3SrvRegiExecRequest)l_request;

        //1.1 validate
        l_srvRegiExecRequest.validate();

        //1.2 validate������t�\
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

		String l_strInstitutionCode = null; 
		String l_strBranchCode = null;
		String l_strAccountCode = null;
		SubAccount l_subAccount = null;
		
		Trader l_trader = null;
		String l_strLoginChannel = null;

		//�I���b�N�X.�����J�݂��T�[�r�X�N���̏ꍇ
		if(l_srvRegiExecRequest.institutionCode != null || l_srvRegiExecRequest.branchCode != null)
		{
			l_strInstitutionCode = l_srvRegiExecRequest.institutionCode;
			l_strBranchCode = l_srvRegiExecRequest.branchCode;
		}
		//�ʏ�̃T�[�r�X�N��
		else
		{
			//1.3 getCommonOrderValidator
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
			WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

			//1.4 get�⏕����
			l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

			l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
			l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
			l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
			
			//get�㗝���͎�
			l_trader = this.getTrader();

			//get���O�C���`���l��
			l_strLoginChannel = this.getLoginChannel();
		}

        //1.5 get�T�[�r�X�}�X�^�[
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_srvRegiExecRequest.serviceDiv, false);

        // is�񋟒�
        if (!l_srvRegiServiceMaster.isProviding())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01927,
                getClass().getName() + STR_METHOD_NAME);
        }

        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
        //get�T�[�r�X�}�X�^�[.���ꏈ���敪 = 2 �i���A�g�̏ꍇ�j && �⏕���� != null�̏ꍇ
        if (WEB3SpecialProcessDivDef.STREAM.equals(l_strSpecialProcessDiv) &&
            l_subAccount != null)
        {
        	FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("�����~�ڋq�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����~�ڋq�G���[");
            }

            //getMainAccount
            WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            // �ڋq.get�ڋq�s ().���Z�^�񋏏Z�敪 != 0:�Z���҂̏ꍇ�A��O���X���[
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_genMainAccount.getDataSourceObject();
            if (!WEB3ResidentDef.RESIDENT.equals(l_mainAccountRow.getResident()))
            {
                log.debug("�񋏏Z�҂͐\���ނ��Ƃ��ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02708,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�񋏏Z�҂͐\���ނ��Ƃ��ł��܂���B");
             }

            //get�㗝���͎ҁi�j�̖߂�l = null
            if (this.getTrader() == null)
            {
                if (!l_genMainAccount.isForeignAccountOpen())
                {
                    log.debug("���Y�ڋq�͊O���،������J�݂Ȃ�");
                    throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���Y�ڋq�͊O���،������J�݂Ȃ��B");
                }

                //is�@@�l( )
                if (l_genMainAccount.isCorporation())
                {
                    log.debug("�@@�l�ڋq�G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02884,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�@@�l�ڋq�G���[�B");
                }
            }

        }

        //1.6 is�\���v
        boolean l_blnIsAppliRequired = l_srvRegiServiceMaster.isAppliRequired();

        if (l_blnIsAppliRequired && l_srvRegiExecRequest.applyCheckDiv)
        {
            log.debug("is�\���v()=true�̏ꍇ && ���N�G�X�g�f�[�^.�\���`�F�b�N�敪=true�̏ꍇ");

            //1.7.1 get�T�[�r�X�\���o�^
            WEB3SrvRegiRegistService l_srvRegiRegistService =
                (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
                l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_srvRegiExecRequest.serviceDiv,
                    l_strAccountCode, WEB3SrvRegiCancelDivDef.USUAL_DEFAULT, WEB3EffectiveDivDef.EFFECTIVE, false);

            //get�T�[�r�X�\���o�^()=null���A���N�G�X�g.���o�C���t���O=null�̏ꍇ�A��O���X���[����B
            if (l_gentradeSrvRegiApplication == null && (l_srvRegiExecRequest.mobileFlag == null))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00908,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //get�T�[�r�X�\���o�^()=null���A���N�G�X�g.���o�C���t���O=1�̏ꍇ
            if (l_gentradeSrvRegiApplication == null
                && WEB3SrvRegiMobileFlagDef.MOBILE.equals(l_srvRegiExecRequest.mobileFlag))
            {
                WEB3SrvRegiExecResponse l_srvRegiExecResponse =
                    (WEB3SrvRegiExecResponse)l_srvRegiExecRequest.createResponse();
                //��URL=#�i���f�[�^�j
                l_srvRegiExecResponse.url = "#";
                //�����M���@@�敪=#�i���f�[�^�j
                l_srvRegiExecResponse.sendHowToDiv = "#";
                //�����M�敪�p�����[�^�敪�ꗗ=#�i���f�[�^�j
                l_srvRegiExecResponse.sendParamList = new String[]{"#"};
                //���G���[�敪=1�i���\���j
                l_srvRegiExecResponse.srvRegiExecErrDiv = WEB3SrvRegiSrvRegiExecErrDivDef.UNAPPLY_ERROR;

                log.exiting(STR_METHOD_NAME);
                return l_srvRegiExecResponse;
            }

            //1.7.2 get�\���o�^ID
            long l_lngRegistId = l_gentradeSrvRegiApplication.getRegistId();

            //1.7.3 is���p�\
            boolean l_blnUsePossible =
                l_srvRegiRegistService.isUsePossible(l_strInstitutionCode, l_strBranchCode, l_srvRegiExecRequest.serviceDiv,
                l_strAccountCode, l_lngRegistId);
            if (!l_blnUsePossible)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01011,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        //get�T�[�r�X�}�X�^-.���ꏈ���敪 = 1(�O���A�g�T�[�r�X�j�̏ꍇ
        WEB3SrvRegiOtherOrgInfoAdmin l_svRegiOtherOrgInfoAdmin = null;
        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);

            //get�O���A�g���(String, String, String, String, boolean)
            //�T�[�r�X�敪 = get�T�[�r�X�}�X�^�[().�T�[�r�X�敪
            //�،���ЃR�[�h = �擾�����⏕�����I�u�W�F�N�g.getInstitution( ).getInstitutionCode( )
            //���X�R�[�h = �擾�����⏕�����I�u�W�F�N�g.getBranch( ).getBranchCode( )
            //�����R�[�h = �擾�����⏕�����I�u�W�F�N�g.getMainAccoutn( ).getAccountCode( )
            //is�s���b�N = false
            l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_svRegiOtherOrgInfoAdmin =
                l_srvRegiOtherOrgService.getOtherOrgInfo(
                    l_srvRegiServiceMaster.getSrvDiv(),
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    false);

            //get�O���A�g���()�̖߂�l�� null �̏ꍇ�A��O���X���[����B
            if (l_svRegiOtherOrgInfoAdmin == null)
            {
                log.debug("�O���A�g�����擾�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                    getClass().getName() + STR_METHOD_NAME,
                    "�O���A�g�����擾�ł��܂���B");
            }
        }
		//�d�l�ύX NO_195 docId�폜
        //1.8��URL�擾��
        //1.8.1�����N�G�X�g�f�[�^.Token��Null�̏ꍇ��
        String l_strSrvUrl = null;
        if (l_srvRegiExecRequest.token == null)
        {
            //1.8.1.1 get�T�[�r�X���pURL
            log.debug("1.9.1.1 get�T�[�r�X���pURL");
            l_strSrvUrl = l_srvRegiServiceMaster.getSrvUrl();
            log.debug("l_strSrvUrl = " + l_strSrvUrl);
        }
        else
        {
            //1.8.2.1get��QURL
            log.debug("1.9.2.1get��QURL");
            l_strSrvUrl = l_srvRegiServiceMaster.getUrl2();
            log.debug("l_strSrvUrl = " + l_strSrvUrl);
        }

		//��Q�Ή� Q8 
        //1.9�����ݓ��t�̎擾��
		Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());

		//�I���b�N�X�@@�����J�ݑΉ��ňړ���
        //1.10 get�㗝���͎�
        //1.11 get���O�C���`���l��

        //1.12 �T�[�r�X���p�\���ϊ�
        String l_strProductCode = l_srvRegiExecRequest.productCode;
        String l_strTraderCode = null;
        log.debug("l_trader = " + l_trader);
        if (l_trader != null)
        {
            log.debug("if (l_trader != null)");
            l_strTraderCode = l_trader.getTraderCode();
        }
        // get�n�b�V���v�Z�����敪
        String l_strHashCalHowToDiv = l_srvRegiServiceMaster.getHashCalHowToDiv();
        // replace�n�b�V���v�Z����
        WEB3SrvRegiStartInfoService l_srvRegiStartInfoService =
            (WEB3SrvRegiStartInfoService)Services.getService(WEB3SrvRegiStartInfoService.class);
        String l_strHashCalHowTo = l_srvRegiStartInfoService.replaceHashCalHowTo(l_strHashCalHowToDiv);

		//�d�l�ύX NO_195 docId�폜
        //get�T�[�r�X�}�X�^.���ꏈ���敪 = 1(�O���A�g�T�[�r�X)�̏ꍇ
        //Id=get�O���A�g���().ID
        //Pass=get�O���A�g���().Pass
        String l_strQtpId = null;
        String l_strQtpPass = null;
        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            l_strQtpId = l_svRegiOtherOrgInfoAdmin.getId();

            l_strQtpPass = l_svRegiOtherOrgInfoAdmin.getPassword();

        }
        log.debug("QtpId=get�O���A�g���().ID ===" + l_strQtpId);
        log.debug("QtpId=get�O���A�g���().Pass ===" + l_strQtpPass);
        WEB3SrvRegiReservedWordChange l_srvRegiReservedWordChange =
            new WEB3SrvRegiReservedWordChange(l_strInstitutionCode, l_srvRegiExecRequest.serviceDiv, l_strBranchCode,
            l_strAccountCode, l_strProductCode, l_srvRegiExecRequest.token, l_strLoginChannel,
            l_strTraderCode, l_srvRegiExecRequest.marginTaxDiv, l_srvRegiExecRequest.futOpTaxDiv, l_tsSystemTimestamp,
            l_srvRegiExecRequest.marketCode, l_srvRegiExecRequest.type, l_strHashCalHowTo,
            l_srvRegiExecRequest.ssidValue, l_strQtpId, l_strQtpPass, l_subAccount);

        //1.13���擾����URL�ɁA"%%�`%%"�ŋ�؂�ꂽ�\��ꂪ�܂܂�Ă����ꍇ��
        if (isRequiredFormat(l_strSrvUrl))
        {
            //1.13.1replace�\���
            log.debug("1.14.1replace�\���");
            l_strSrvUrl = l_srvRegiReservedWordChange.replaceReservedWord(l_strSrvUrl);
            log.debug("l_strSrvUrl = " + l_strSrvUrl);
        }

        //1.16  get���M�p�����[�^�敪
        String l_strSendParamDiv = l_srvRegiServiceMaster.getSendParamDiv();

        String[] l_strUseKey = null;
        //1.17��get���M�p�����[�^�敪()�̖߂�l="�L"�̏ꍇ��
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strSendParamDiv))
        {
            log.debug("1.18.1 get���M�p�����[�^�ꗗ");
            //1.17.1 get���M�p�����[�^�ꗗ
            WEB3SrvRegiServiceUseKey[] l_srvRegiServiceUseKey = l_srvRegiServiceMaster.getParamList();

            //1.17.2 ���J��Ԃ�������
            int l_int = l_srvRegiServiceUseKey.length;
            l_strUseKey = new String[l_int];
            for (int i = 0; i < l_int; i++)
            {
                log.debug("1.18.2.1 �����򏈗���");
                //1.17.2.1 �����򏈗���
                String l_strSrvUseKey = l_srvRegiServiceUseKey[i].getSrvUseKey();
                if (isRequiredFormat(l_strSrvUseKey))
                {
                    String l_strReservedWordUseKey = l_srvRegiReservedWordChange.replaceReservedWord(l_strSrvUseKey);
                    l_strUseKey[i] = l_strReservedWordUseKey;
                    log.debug("l_strUseKey[i] = l_strReservedWordUseKey;");
                }
                else
                {
                    l_strUseKey[i] = l_strSrvUseKey;
                    log.debug("l_strUseKey[i] = l_strSrvUseKey;");
                }
            }
        }

        //1.18 get���M���@@�敪
        String l_strSendHowToDiv = l_srvRegiServiceMaster.getSendHowToDiv();

        //1.19 create���X�|���X
        WEB3SrvRegiExecResponse l_response = (WEB3SrvRegiExecResponse)l_srvRegiExecRequest.createResponse();

        l_response.url = l_strSrvUrl;
        l_response.sendHowToDiv = l_strSendHowToDiv;
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strSendParamDiv))
        {
            l_response.sendParamList = null;
            log.debug("l_response.sendParamList = null;");
        }
        else
        {
            l_response.sendParamList = l_strUseKey;
            log.debug("l_response.sendParamList = l_strUseKey;");
        }

        //���G���[�敪=null
        l_response.srvRegiExecErrDiv = WEB3SrvRegiSrvRegiExecErrDivDef.NOT_ERROR;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    private boolean isRequiredFormat(String l_strUrl)
    {
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_strUrl))
        {
            return false;
        }

        final String l_strDoublePercents = "%%";

        int l_intUrlLength = l_strUrl.length();

        for (int i = 0; i < l_intUrlLength - 1; i++)
        {
            if (l_strDoublePercents.equals(l_strUrl.substring(i, i + 2)))
            {
                for (int j = i + 2; j < l_intUrlLength - 1; j++)
                {
                    if (l_strDoublePercents.equals(l_strUrl.substring(j, j+2)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
@
