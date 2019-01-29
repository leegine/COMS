head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �d�q��t�T�[�r�X�o�^�E�ύX�T�[�r�XImpl(WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.278 281 282 �c�a�X�V�d�l064 065
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.accountinfo.WEB3AccInfoAccopenConditionRegAcceptVoucher;
import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.define.WEB3AccInfoEleDeliveryFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoEleDeliveryInfo;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoElecDeliveryRegisterChangeService;
import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PosReportCycleDivDef;
import webbroker3.common.define.WEB3PosReportDivDef;
import webbroker3.common.define.WEB3ReportDivDef;
import webbroker3.common.define.WEB3ReportRegDivDef;
import webbroker3.common.define.WEB3TradingReportDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.EleDeliveryManagementDao;
import webbroker3.gentrade.data.EleDeliveryManagementParams;
import webbroker3.gentrade.data.EleDeliveryManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�d�q��t�T�[�r�X�o�^�E�ύX�T�[�r�XImpl)<BR>
 * �d�q��t�T�[�r�X�o�^�E�ύX�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryRegisterChangeServiceImpl extends WEB3AccInfoClientRequestService 
    implements WEB3AccInfoElecDeliveryRegisterChangeService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.class);

    /**
     * �d�q��t�T�[�r�X�o�^�E�ύX���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�d�q��t�T�[�r�X�o�^�E�ύX���̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�d�q��t�T�[�r�X�o�^�E�ύX�������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�ύX()���R�[������B <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�d�q��t���ꗗ���N�G�X�g�̏ꍇ <BR>
     * �@@�|get�d�q��t���ꗗ()���R�[������B <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        if (l_request instanceof WEB3AccInfoElecDeliveryRegisterChangeInputRequest)
        {
            l_response = this.getInputScreen((WEB3AccInfoElecDeliveryRegisterChangeInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)
        {
            l_response = this.submitChangeScreen((WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AccInfoElecDeliveryApyReferenceRequest)
        {
            l_response = this.getEleDeliveryInfoList((WEB3AccInfoElecDeliveryApyReferenceRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���͉��)<BR>
     * �d�q��t�T�[�r�X�o�^�E�ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�d�q��t�T�[�r�X�o�^�E�ύX�jget���͉�ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AccInfoElecDeliveryRegisterChangeInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoElecDeliveryRegisterChangeInputResponse getInputScreen(
        WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="getInputScreen(WEB3AccInfoElecDeliveryRegisterChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AccInfoElecDeliveryRegisterChangeInputResponse l_response = null;

        //validate( )
        l_request.validate();

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get�ڋq( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();

        //get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //[����]
        //�@@�،���ЃR�[�h = get�ڋq().getInstitution().getInstitutionCode()�̖߂�l
        //�@@���X�R�[�h = get�ڋq().getBranch().getBranchCode()�̖߂�l
        //�@@�ڋq�R�[�h = get�ڋq().getAccountCode()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_accManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode());

        //update�d�q��t�Ǘ�(�ڋq, ���N�G�X�g�f�[�^, String)
        //[update�d�q��t�Ǘ�()�Ɏw�肷�����]
        //�ڋq�F�@@get�ڋq()�̖߂�l
        //���N�G�X�g�f�[�^�F�@@null
        //���҃R�[�h�Fget�㗝���͎ҁi�j.���҃R�[�h
        String l_strTraderCode = null;
        if (l_trader != null)
        {
            l_strTraderCode = l_trader.getTraderCode();
        }
        this.updateElecDeliveryManagement(l_mainAccount, null, l_strTraderCode);

        l_response = (WEB3AccInfoElecDeliveryRegisterChangeInputResponse)l_request.createResponse();

        //���N�G�X�g.�d�q��t�\���t���O���h0�h�̏ꍇ�A���̂܂�return
        if (WEB3AccInfoEleDeliveryFlagDef.NOT_APPLY.equals(l_request.eleDeliveryFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //create�d�q��t���(�ڋq)
        WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = this.createEleDeliveryInfo(l_mainAccount);

        //�v���p�e�B�Z�b�g
        l_response.eleDeliveryInfo = l_eleDeliveryInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX���)<BR>
     * �d�q��t�T�[�r�X�o�^�E�ύX�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�d�q��t�T�[�r�X�o�^�E�ύX�jsubmit�ύX�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse submitChangeScreen(
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="submitChangeScreen(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse l_response = null;

        //validate( )
        l_request.validate();

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get�ڋq( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();

        //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //[����]
        //�@@�،���ЃR�[�h = get�ڋq().getInstitution().getInstitutionCode()�̖߂�l
        //�@@���X�R�[�h = get�ڋq().getBranch().getBranchCode()�̖߂�l
        //�@@�ڋq�R�[�h = get�ڋq().getAccountCode()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_accManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode());

        //get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //get�d�q��t�Ǘ�(�ڋq)
        EleDeliveryManagementParams l_eleDeliveryManagementParams = this.getEleDeliveryManagement(l_mainAccount);

        //validate�ύX����(�d�q��t�Ǘ�Param, �d�q��t�T�[�r�X�o�^�E�ύX�������N�G�X�g, boolean)
        //�d�q��t�Ǘ��s�F�@@get�d�q��t�Ǘ��i�j�̖߂�l
        //���N�G�X�g�f�[�^�F�@@�����̃��N�G�X�g�f�[�^
        //��s���̓t���O�F�@@get�㗝���͎ҁi�j��null�̏ꍇ�Afalse��Ԃ�B
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�̏ꍇ�́Atrue��Ԃ�B
        boolean l_blnInputFlag = false;
        if (l_trader != null)
        {
            l_blnInputFlag = true;
        }
        this.validateChangeItem(l_eleDeliveryManagementParams, l_request, l_blnInputFlag);

        // update�d�q��t�Ǘ�(�ڋq, ���N�G�X�g�f�[�^, String)
        String l_strTraderCode = null;
        if (l_trader != null)
        {
            l_strTraderCode = l_trader.getTraderCode();
        }
        this.updateElecDeliveryManagement(l_mainAccount, l_request, l_strTraderCode);

        //is�d�q��t���ύX�iSONAR�j(�d�q��t�T�[�r�X�o�^�E�ύX�������N�G�X�g)
        boolean l_blnIsEleDeliveryInfoChangeSonar = this.isEleDeliveryInfoChangeSonar(l_request);

        //is�d�q��t���ύX�iSONAR�j()�̖߂�l��true�̏ꍇ�A�ȉ��������s�Ȃ�
        if (l_blnIsEleDeliveryInfoChangeSonar)
        {
            //get�V�K���ʃR�[�h(�،���ЃR�[�h : String, ���X�R�[�h : String, �����^�C�v : ProductTypeEnum)
            WEB3HostReqOrderNumberManageService l_orderNumberManagerService =
                (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
            String l_strNewNumber = l_orderNumberManagerService.getNewNumber(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                ProductTypeEnum.OTHER);

            //create���E��c�d�q��t�E��������o�^�s(�d�q��t�Ǘ�Param,
            //�@@�d�q��t�T�[�r�X�o�^�E�ύX�������N�G�X�g, String)
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                this.createHostConditionRegVoucher(
                    l_eleDeliveryManagementParams,
                    l_request,
                    l_strTraderCode);

            //���E��c�d�q��t�E��������o�^
            WEB3AccInfoAccopenConditionRegAcceptVoucher l_conditionRegAcceptVoucher =
                new WEB3AccInfoAccopenConditionRegAcceptVoucher(
                    l_hostConditionRegVoucherParams,
                    l_strNewNumber);

            //save���E��c�d�q��t�E��������o�^�iGI311�j�L���[( )
            l_conditionRegAcceptVoucher.saveHostConditionRegVoucherParams();
        }

        l_response = (WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (update�d�q��t�Ǘ�)<BR>
     * �d�q��t�Ǘ��e�[�u���̍X�V���s�Ȃ��B<BR>
     * <BR>
     * [��������] <BR>
     * �@@�@@�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h <BR>
     * �@@�@@���X�R�[�h�F�ڋq.���X�R�[�h <BR>
     * �@@�@@�����R�[�h�F�ڋq.�����R�[�h <BR>
     * �@@�@@<BR>
     *  �������ʂ��擾�ł���ꍇ�A <BR>
     * �@@�c�a�X�V���s���B <BR>
     * �@@���͂̏ꍇ�A<BR>
     * �@@�u�d�q��t�T�[�r�X�o�^�E�ύX_�d�q��t�Ǘ��e�[�u��.xls#�d�q��t�Ǘ��e�[�u���i���́j�v�Q�ƁB<BR>
     *   �@@�@@���d�q��t�\���`�F�b�N�t���O�ɂ��āA<BR>
     * �@@�@@�@@�����������R�[�g�̓d�q��t�\���`�F�b�N�t���O="0�F���ς�"�̏ꍇ�A<BR>
     * �@@�@@�@@�Y�����R�[�g�̓d�q��t�\���`�F�b�N�t���O�u1�F�ς݁v�ɍX�V����܂��B<BR>
     * �@@�@@�@@�ȊO�̏ꍇ�A�X�V����Ȃ��B<BR>
     * �@@�����̏ꍇ�A<BR>
     * �@@�u�d�q��t�T�[�r�X�o�^�E�ύX_�d�q��t�Ǘ��e�[�u��.xls#�d�q��t�Ǘ��e�[�u���i�����j�v�Q�ƁB<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_strTraderCode - (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     * @@throws WEB3BaseException
     */
    protected void updateElecDeliveryManagement(
        WEB3GentradeMainAccount l_mainAccount,
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request,
        String l_strTraderCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="updateElecDeliveryManagement(" +
            "WEB3GentradeMainAccount, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, String)";
        log.entering(STR_METHOD_NAME);

        //�d�q��t�Ǘ��e�[�u���̍X�V���s�Ȃ��B
        //[��������]
        //�@@�@@�،���ЃR�[�h�F�ڋq.�،���ЃR�[�h
        //�@@�@@���X�R�[�h�F�ڋq.���X�R�[�h
        //�@@�@@�����R�[�h�F�ڋq.�����R�[�h
        EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
        try
        {
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode());
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_eleDeliveryManagementRow == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        else
        {
            EleDeliveryManagementParams l_params = new EleDeliveryManagementParams(l_eleDeliveryManagementRow);
            //���͂̏ꍇ
            //�u�d�q��t�T�[�r�X�o�^�E�ύX_�d�q��t�Ǘ��e�[�u��.xls#�d�q��t�Ǘ��e�[�u���i���́j�v�Q�ƁB
            if (l_request == null)
            {
                //�d�q��t�Ǘ�.�d�q��t�\���`�F�b�N�t���O="0�F���ς�"�̏ꍇ�A�@@�u1�F�ς݁v
                //�ȊO�̏ꍇ�A�����l
                if (l_params.getEleDelRegiFlag() == 0)
                {
                    l_params.setEleDelRegiFlag(1);
                }
                //�d�q��t�\���`�F�b�N����:��������
                l_params.setEleDelRegiUpdDate(GtlUtils.getSystemTimestamp());
                //�X�V�҃R�[�h:�ڋq���O�C�����F�����R�[�h
                //             ��s���́F���҃R�[�h
                if (l_strTraderCode == null)
                {
                    l_params.setLastUpdater(l_mainAccount.getAccountCode());
                }
                else
                {
                    l_params.setLastUpdater(l_strTraderCode);
                }
                //�X�V���t:��������
                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }
            else
            {
                //�����̏ꍇ
                //�u�d�q��t�T�[�r�X�o�^�E�ύX_�d�q��t�Ǘ��e�[�u��.xls#�d�q��t�Ǘ��e�[�u���i�����j�v�Q�ƁB

                if (l_request.tradingReportDiv != null)
                {
                    //����񍐏���t�敪
                    //���N�G�X�g.����񍐏���t�敪��null�̏ꍇ�A���N�G�X�g.����񍐏���t�敪
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setTradingReportDiv(l_request.tradingReportDiv);
                    //����񍐏��\���敪
                    //���N�G�X�g.����񍐏���t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setTradingReportRegDiv(WEB3ReportRegDivDef.APPLYING);
                    //����񍐏���t�敪�X�V����
                    //���N�G�X�g.����񍐏���t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setTradingReportDivUpdDate(GtlUtils.getSystemTimestamp());
                }

                if (l_request.positionReportDiv != null)
                {
                    //����c���񍐏���t�敪
                    //���N�G�X�g.����c���񍐏���t�敪��null�̏ꍇ�A
                    //�@@�@@-���N�G�X�g.����c���񍐏���t�敪="0�F�X�֔z�z"�A�u0�F�X�֔z�z�v���Z�b�g����B
                    //�@@�@@-���N�G�X�g.����c���񍐏���t�敪="1�F�d�q�z�z"�A�u9�F�d�q�z�z�v���Z�b�g����B
                    //�ȊO�̏ꍇ�A�����l
                    if (WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))
                    {
                        l_params.setPositionReportDiv(WEB3PosReportDivDef.MAIL_DISTRIBUTION);
                    }
                    else if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.positionReportDiv))
                    {
                        l_params.setPositionReportDiv(WEB3PosReportDivDef.ELECTRON_DISTRIBUTION);
                    }
                    //����c���񍐏��\���敪
                    //���N�G�X�g.����c���񍐏���t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setPositionReportRegDiv(WEB3ReportRegDivDef.APPLYING);
                    //����c���񍐏���t�敪�X�V����
                    //���N�G�X�g.����c���񍐏���t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setPositionReportDivUpdDate(GtlUtils.getSystemTimestamp());
                }

                if (l_request.opeReportDiv != null)
                {
                    //�^�p�񍐏���t�敪
                    //���N�G�X�g.�^�p�񍐏���t�敪��null�̏ꍇ�A���N�G�X�g.�^�p�񍐏���t�敪
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setOpeReportDiv(l_request.opeReportDiv);
                    //�^�p�񍐏��\���敪
                    //���N�G�X�g.�^�p�񍐏���t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setOpeReportRegDiv(WEB3ReportRegDivDef.APPLYING);
                    //�^�p�񍐏���t�敪�X�V����
                    //���N�G�X�g.�^�p�񍐏���t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setOpeReportDivUpdDate(GtlUtils.getSystemTimestamp());
                }

                if (l_request.ordRulReportDiv != null)
                {
                    //�񊼁E�K��W�񍐏���t�敪
                    //���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null�̏ꍇ�A���N�G�X�g.�񊼁E�K��W�񍐏���t�敪
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setOrdRulReportDiv(l_request.ordRulReportDiv);
                    //�񊼁E�K��W�񍐏��\���敪
                    //���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setOrdRulRepRegDiv(WEB3ReportRegDivDef.APPLYING);
                    //�񊼁E�K��W�񍐏���t�敪�X�V����
                    //���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setOrdRulReportDivUpdDate(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div1 != null)
                {
                    //���ʂP��t�敪
                    //���N�G�X�g.���ʂP��t�敪��null�̏ꍇ�A���N�G�X�g.���ʂP��t�敪
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDiv1(l_request.report_div1);
                    //���ʂP�\���敪
                    //���N�G�X�g.���ʂP��t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportRegDiv1(WEB3ReportRegDivDef.APPLYING);
                    //���ʂP��t�敪�X�V����
                    //���N�G�X�g.���ʂP��t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDivUpdDate1(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div2 != null)
                {
                    //���ʂQ��t�敪
                    //���N�G�X�g.���ʂQ��t�敪��null�̏ꍇ�A���N�G�X�g.���ʂQ��t�敪
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDiv2(l_request.report_div2);
                    //���ʂQ�\���敪
                    //���N�G�X�g.���ʂQ��t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportRegDiv2(WEB3ReportRegDivDef.APPLYING);
                    //���ʂQ��t�敪�X�V����
                    //���N�G�X�g.���ʂQ��t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDivUpdDate2(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div3 != null)
                {
                    //���ʂR��t�敪
                    //���N�G�X�g.���ʂR��t�敪��null�̏ꍇ�A���N�G�X�g.���ʂR��t�敪
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDiv3(l_request.report_div3);
                    //���ʂR�\���敪
                    //���N�G�X�g.���ʂR��t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportRegDiv3(WEB3ReportRegDivDef.APPLYING);
                    //���ʂR��t�敪�X�V����
                    //���N�G�X�g.���ʂR��t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDivUpdDate3(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div4 != null)
                {
                    //���ʂS��t�敪
                    //���N�G�X�g.���ʂS��t�敪��null�̏ꍇ�A���N�G�X�g.���ʂS��t�敪
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDiv4(l_request.report_div4);
                    //���ʂS�\���敪
                    //���N�G�X�g.���ʂS��t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportRegDiv4(WEB3ReportRegDivDef.APPLYING);
                    //���ʂS��t�敪�X�V����
                    //���N�G�X�g.���ʂS��t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDivUpdDate4(GtlUtils.getSystemTimestamp());
                }

                if (l_request.report_div5 != null)
                {
                    //���ʂT��t�敪
                    //���N�G�X�g.���ʂT��t�敪��null�̏ꍇ�A���N�G�X�g.���ʂT��t�敪
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDiv5(l_request.report_div5);
                    //���ʂT�\���敪
                    //���N�G�X�g.���ʂT��t�敪��null�̏ꍇ�A0:�\�����B
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportRegDiv5(WEB3ReportRegDivDef.APPLYING);
                    //���ʂT��t�敪�X�V����
                    //���N�G�X�g.���ʂT��t�敪��null�̏ꍇ�A��������
                    //�ȊO�̏ꍇ�A�����l
                    l_params.setReportDivUpdDate5(GtlUtils.getSystemTimestamp());
                }

                //�X�V�҃R�[�h
                //�ڋq���O�C�����F�����R�[�h
                //��s���́F���҃R�[�h
                if (l_strTraderCode == null)
                {
                    l_params.setLastUpdater(l_mainAccount.getAccountCode());
                }
                else
                {
                    l_params.setLastUpdater(l_strTraderCode);
                }
                //�X�V���t
                //��������
                l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_params);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�ύX����)<BR>
     * �o�^���ꂽ���ڂ������������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g.����񍐏���t�敪��null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().����񍐏���t�敪 �� ���N�G�X�g.����񍐏���t�敪<BR>
     * �@@�Aget�d�q��t�Ǘ�().����񍐏��\���敪�@@���@@"0:�\����"<BR>
     * �@@�Bget�d�q��t�Ǘ�().����񍐏��\���敪�@@���@@"9:SONAR���M��"<BR>
     * �@@�C��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.����񍐏� �� "0�F�X�֔z�z"<BR>
     * �@@<BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * �Q�j�@@���N�G�X�g.����c���񍐏���t�恂null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().����c���񍐏���t�敪=="0�F�X�֔z�z" <BR>
     * ���� ���N�G�X�g.����c���񍐏���t�敪=="0�F�X�֔z�z"<BR>
     * �@@�Aget�d�q��t�Ǘ�().����c���񍐏���t�敪=="1�F�X�֔z�z�i��n�s�x�쐬�j" <BR>
     * ���� ���N�G�X�g.����c���񍐏���t�敪=="0�F�X�֔z�z"<BR>
     * �@@�Bget�d�q��t�Ǘ�().����c���񍐏���t�敪=="9�F�d�q�z�z" <BR>
     * ���� ���N�G�X�g.����c���񍐏���t�敪=="1�F�d�q�z�z"<BR>
     * �@@�Cget�d�q��t�Ǘ�().����c���񍐏��\���敪�@@���@@"0:�\����"<BR>
     * �@@�Dget�d�q��t�Ǘ�().����c���񍐏��\���敪�@@���@@"9:SONAR���M��"<BR>
     * �@@�E��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.����c���񍐏���t�敪 �� "0�F�X�֔z�z"<BR>
     * <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * �R�j�@@���N�G�X�g.�^�p�񍐏���t�敪��null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().�^�p�񍐏���t�敪 �� ���N�G�X�g.�^�p�񍐏���t�敪<BR>
     * �@@�Aget�d�q��t�Ǘ�().�^�p�񍐏��\���敪�@@���@@"0:�\����"<BR>
     * �@@�Bget�d�q��t�Ǘ�().�^�p�񍐏��\���敪�@@���@@"9:SONAR���M��"<BR>
     * �@@�C��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.�^�p�񍐏���t�敪 �� "0�F�X�֔z�z"<BR>
     * <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * �S�j�@@���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().�񊼁E�K��W�񍐏���t�敪 �� ���N�G�X�g.�񊼁E�K��W�񍐏���t�敪<BR>
     * �@@�Aget�d�q��t�Ǘ�().�񊼁E�K��W�񍐏��\���敪�@@���@@"0:�\����"<BR>
     * �@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.�񊼁E�K��W�񍐏���t�敪 �� "0�F�X�֔z�z"<BR>
     * <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * �T�j�@@���N�G�X�g.���ʂP��t�敪��null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().���ʂP��t�敪 �� ���N�G�X�g.���ʂP��t�敪<BR>
     * �@@�Aget�d�q��t�Ǘ�().���ʂP�\���敪�@@���@@"0:�\����"<BR>
     * �@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂP��t�敪 �� "0�F�X�֔z�z"<BR>
     * <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * �U�j�@@���N�G�X�g.���ʂQ��t�敪��null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().���ʂQ��t�敪 �� ���N�G�X�g.���ʂQ��t�敪<BR>
     * �@@�Aget�d�q��t�Ǘ�().���ʂQ�\���敪�@@���@@"0:�\����"<BR>
     * �@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂQ��t�敪 �� "0�F�X�֔z�z"<BR>
     * <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * �V�j�@@���N�G�X�g.���ʂR��t�敪��null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().���ʂR��t�敪 �� ���N�G�X�g.���ʂR��t�敪<BR>
     * �@@�Aget�d�q��t�Ǘ�().���ʂR�\���敪�@@���@@"0:�\����"<BR>
     * �@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂR��t�敪 �� "0�F�X�֔z�z"<BR>
     * <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * �W�j�@@���N�G�X�g.���ʂS��t�敪��null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().���ʂS��t�敪 �� ���N�G�X�g.���ʂS��t�敪<BR>
     * �@@�Aget�d�q��t�Ǘ�().���ʂS�\���敪�@@���@@"0:�\����"<BR>
     * �@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂS��t�敪 �� "0�F�X�֔z�z"<BR>
     * <BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * �X�j�@@���N�G�X�g.���ʂT��t�敪��null�̏ꍇ�Ŋ���<BR>
     * �@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�@@get�d�q��t�Ǘ�().���ʂT��t�敪 �� ���N�G�X�g.���ʂT��t�敪<BR>
     * �@@�Aget�d�q��t�Ǘ�().���ʂT�\���敪�@@���@@"0:�\����"<BR>
     * �@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂT��t�敪 �� "0�F�X�֔z�z"<BR>
     * class:�@@WEB3BusinessLayerException<BR>
     * tag�@@:�@@ BUSINESS_ERROR_03210<BR>
     * @@param l_params - (�d�q��t�Ǘ��s)<BR>
     * �d�q��t�Ǘ��s<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_blnInputFlag - (��s���̓t���O)<BR>
     * ��s���̓t���O<BR>
     * @@throws WEB3BaseException
     */
    protected void validateChangeItem(
        EleDeliveryManagementParams l_params,
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request,
        boolean l_blnInputFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validateChangeItem(" +
            "EleDeliveryManagementParams, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, boolean)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���N�G�X�g.����񍐏���t�敪��null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().����񍐏���t�敪 �� ���N�G�X�g.����񍐏���t�敪
        //�@@�Aget�d�q��t�Ǘ�().����񍐏��\���敪�@@���@@"0:�\����"
        //�@@�Bget�d�q��t�Ǘ�().����񍐏��\���敪�@@���@@"9:SONAR���M��"
        //�@@�C��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.����񍐏� �� "0�F�X�֔z�z"
        if (l_request.tradingReportDiv != null
            && (l_request.tradingReportDiv.equals(l_params.getTradingReportDiv())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getTradingReportRegDiv())
                || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_params.getTradingReportRegDiv())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.tradingReportDiv))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        //�Q�j�@@���N�G�X�g.����c���񍐏���t�恂null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().����c���񍐏���t�敪=="0�F�X�֔z�z"
        //���� ���N�G�X�g.����c���񍐏���t�敪=="0�F�X�֔z�z"
        //�@@�Aget�d�q��t�Ǘ�().����c���񍐏���t�敪=="1�F�X�֔z�z�i��n�s�x�쐬�j"
        //���� ���N�G�X�g.����c���񍐏���t�敪=="0�F�X�֔z�z"
        //�@@�Bget�d�q��t�Ǘ�().����c���񍐏���t�敪=="9�F�d�q�z�z"
        //���� ���N�G�X�g.����c���񍐏���t�敪=="1�F�d�q�z�z"
        //�@@�Cget�d�q��t�Ǘ�().����c���񍐏��\���敪�@@���@@"0:�\����"
        //�@@�Dget�d�q��t�Ǘ�().����c���񍐏��\���敪�@@���@@"9:SONAR���M��"
        //�@@�E��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.����c���񍐏���t�敪 �� "0�F�X�֔z�z"
        if (l_request.positionReportDiv != null
            && ((WEB3PosReportDivDef.MAIL_DISTRIBUTION.equals(l_params.getPositionReportDiv())
                    && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))
                || (WEB3PosReportDivDef.MAIL_DISTRIBUTION_EACH_TIME.equals(l_params.getPositionReportDiv())
                    && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))
                || (WEB3PosReportDivDef.ELECTRON_DISTRIBUTION.equals(l_params.getPositionReportDiv())
                    && WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.positionReportDiv))
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getPositionReportRegDiv())
                || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_params.getPositionReportRegDiv())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        //�R�j�@@���N�G�X�g.�^�p�񍐏���t�敪��null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().�^�p�񍐏���t�敪 �� ���N�G�X�g.�^�p�񍐏���t�敪
        //�@@�Aget�d�q��t�Ǘ�().�^�p�񍐏��\���敪�@@���@@"0:�\����"
        //�@@�Bget�d�q��t�Ǘ�().�^�p�񍐏��\���敪�@@���@@"9:SONAR���M��"
        //�@@�C��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.�^�p�񍐏���t�敪 �� "0�F�X�֔z�z"
        if (l_request.opeReportDiv != null
            && (l_request.opeReportDiv.equals(l_params.getOpeReportDiv())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getOpeReportRegDiv())
                || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_params.getOpeReportRegDiv())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.opeReportDiv))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        //�S�j�@@���N�G�X�g.�񊼁E�K��W�񍐏���t�敪��null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().�񊼁E�K��W�񍐏���t�敪 �� ���N�G�X�g.�񊼁E�K��W�񍐏���t�敪
        //�@@�Aget�d�q��t�Ǘ�().�񊼁E�K��W�񍐏��\���敪�@@���@@"0:�\����"
        //�@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.�񊼁E�K��W�񍐏���t�敪 �� "0�F�X�֔z�z"
        if (l_request.ordRulReportDiv != null
            && (l_request.ordRulReportDiv.equals(l_params.getOrdRulReportDiv())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getOrdRulRepRegDiv())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.ordRulReportDiv))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        //�T�j�@@���N�G�X�g.���ʂP��t�敪��null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().���ʂP��t�敪 �� ���N�G�X�g.���ʂP��t�敪
        //�@@�Aget�d�q��t�Ǘ�().���ʂP�\���敪�@@���@@"0:�\����"
        //�@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂP��t�敪 �� "0�F�X�֔z�z"
        if (l_request.report_div1 != null
            && (l_request.report_div1.equals(l_params.getReportDiv1())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv1())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div1))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        //�U�j�@@���N�G�X�g.���ʂQ��t�敪��null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().���ʂQ��t�敪 �� ���N�G�X�g.���ʂQ��t�敪
        //�@@�Aget�d�q��t�Ǘ�().���ʂQ�\���敪�@@���@@"0:�\����"
        //�@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂQ��t�敪 �� "0�F�X�֔z�z"
        if (l_request.report_div2 != null
            && (l_request.report_div2.equals(l_params.getReportDiv2())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv2())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div2))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        //�V�j�@@���N�G�X�g.���ʂR��t�敪��null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().���ʂR��t�敪 �� ���N�G�X�g.���ʂR��t�敪
        //�@@�Aget�d�q��t�Ǘ�().���ʂR�\���敪�@@���@@"0:�\����"
        //�@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂR��t�敪 �� "0�F�X�֔z�z"
        if (l_request.report_div3 != null
            && (l_request.report_div3.equals(l_params.getReportDiv3())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv3())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div3))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        //�W�j�@@���N�G�X�g.���ʂS��t�敪��null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().���ʂS��t�敪 �� ���N�G�X�g.���ʂS��t�敪
        //�@@�Aget�d�q��t�Ǘ�().���ʂS�\���敪�@@���@@"0:�\����"
        //�@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂS��t�敪 �� "0�F�X�֔z�z"
        if (l_request.report_div4 != null
            && (l_request.report_div4.equals(l_params.getReportDiv4())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv4())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div4))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        //�X�j�@@���N�G�X�g.���ʂT��t�敪��null�̏ꍇ�Ŋ���
        //�@@���L�����������ꂩ�ɊY������ꍇ�́A��O���X���[����B
        //�@@�@@get�d�q��t�Ǘ�().���ʂT��t�敪 �� ���N�G�X�g.���ʂT��t�敪
        //�@@�Aget�d�q��t�Ǘ�().���ʂT�\���敪�@@���@@"0:�\����"
        //�@@�B��s���̓t���O��"false"�ł���ꍇ�́A���N�G�X�g.���ʂT��t�敪 �� "0�F�X�֔z�z"
        if (l_request.report_div5 != null
            && (l_request.report_div5.equals(l_params.getReportDiv5())
                || WEB3ReportRegDivDef.APPLYING.equals(l_params.getReportRegDiv5())
                || (!l_blnInputFlag && WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.report_div5))))
        {
            log.debug("�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03210,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q��t�T�[�r�X�\�����s�����Ƃ��ł��܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�d�q��t���ύX�iSONAR�j)<BR>
     * ���Y�ڋq���ASONAR�Ɋ֘A�̓d�q��t����ύX���邩�ǂ����𔻒肷��B <BR>
     * <BR>
     * �P�j�@@�ȉ������������ꂩ�������ꍇ�Atrue��ԋp����B <BR>
     * <BR>
     * �@@�@@�@@�@@���N�G�X�g.����񍐏���t�敪��null <BR>
     * �@@�@@�A�@@���N�G�X�g.����c���񍐏���t�敪��null <BR>
     * �@@�@@�B�@@���N�G�X�g.�^�p�񍐏���t�敪��null <BR>
     * <BR>
     * �Q�j�@@�P�j�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return boolean
     */
    protected boolean isEleDeliveryInfoChangeSonar(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME="isEleDeliveryInfoChangeSonar(" +
            "WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�ȉ������������ꂩ�������ꍇ�Atrue��ԋp����B
        //�@@�@@�@@���N�G�X�g.����񍐏���t�敪��null
        //�@@�A�@@���N�G�X�g.����c���񍐏���t�敪��null
        //�@@�B�@@���N�G�X�g.�^�p�񍐏���t�敪��null
        if (l_request.tradingReportDiv != null
            || l_request.positionReportDiv != null
            || l_request.opeReportDiv != null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            //�Q�j�@@�P�j�ȊO�̏ꍇ�Afalse��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (create���E��c�d�q��t�E��������o�^�s)<BR>
     * ���E��c�d�q��t�E��������o�^�V�K�s�𐶐�����B <BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g���� <BR>
     * �@@���E��c�d�q��t�E��������o�^Params�I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �@@�����E��c�d�q��t�E��������o�^Params��DDL��莩����������B <BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B <BR>
     * �@@�P�j�Ő����������E��c�d�q��t�E��������o�^Params�I�u�W�F�N�g�̃v���p�e�B�ɁA<BR>
     * �ȉ��̒ʂ�Z�b�g���s���B<BR>
     * <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h �� (����)�d�q��t�Ǘ��s.�،���ЃR�[�h <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.���X�R�[�h �� (����)�d�q��t�Ǘ��s.���X�R�[�h <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h �� (����)�d�q��t�Ǘ��s.�ڋq�R�[�h <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.���҃R�[�h �� (����) ���҃R�[�h <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�d�q��t�@@���z���E���ҋ� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����Ǘ����� �� null <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j ��<BR>
     * �@@�|(����)���N�G�X�g.����c���񍐏���t�敪 �� null�̏ꍇ�Anull <BR>
     * �@@�|(����)���N�G�X�g.����c���񍐏���t�敪 �� 0�F�X�֔z�z�̏ꍇ�A0�F�s�쐬<BR>
     * �@@�|(����)���N�G�X�g.����c���񍐏���t�敪 �� 1�F�d�q�z�z�̏ꍇ�A9�F�d�q��t<BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� �� <BR>
     * �@@�|(����)���N�G�X�g.����񍐏���t�敪 �� null �̏ꍇ�Anull <BR>
     * �@@�|(����)���N�G�X�g.����񍐏���t�敪 �� 0�F�X�֔z�z�̏ꍇ�A�O�F�񏳑�<BR>
     * �@@�|(����)���N�G�X�g.����񍐏���t�敪 �� 1�F�d�q�z�z�̏ꍇ�A�P�F���� <BR>
     * �@@���E��c�d�q��t�E��������o�^Params.�d�q��t�@@���M�^�p�񍐏� �� <BR>
     * �@@�|(����)���N�G�X�g.�^�p�񍐏���t�敪 �� null �̏ꍇ�Anull <BR>
     * �@@�|(����)���N�G�X�g.�^�p�񍐏���t�敪 �� 0�F�X�֔z�z�̏ꍇ�A�O�F�񏳑�<BR>
     * �@@�|(����)���N�G�X�g.�^�p�񍐏���t�敪 �� 1�F�d�q�z�z�̏ꍇ�A�P�F���� <BR>
     * �R�j�@@���������s�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_params - (�d�q��t�Ǘ��s)<BR>
     * �d�q��t�Ǘ��s<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_strTraderCode - (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     * @@return HostConditionRegVoucherParams
     */
    protected HostConditionRegVoucherParams createHostConditionRegVoucher(
        EleDeliveryManagementParams l_params,
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request,
        String l_strTraderCode)
    {
        final String STR_METHOD_NAME="createHostConditionRegVoucher(" +
            "EleDeliveryManagementParams, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�s�I�u�W�F�N�g����
        //���E��c�d�q��t�E��������o�^Params�I�u�W�F�N�g�𐶐�����B
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams = new HostConditionRegVoucherParams();

        //�Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
        //�P�j�Ő����������E��c�d�q��t�E��������o�^Params�I�u�W�F�N�g�̃v���p�e�B�ɁA
        //�@@�ȉ��̒ʂ�Z�b�g���s���B
        //�@@���E��c�d�q��t�E��������o�^Params.�،���ЃR�[�h �� (����)�d�q��t�Ǘ��s.�،���ЃR�[�h
        l_hostConditionRegVoucherParams.setInstitutionCode(l_params.getInstitutionCode());
        //�@@���E��c�d�q��t�E��������o�^Params.���X�R�[�h �� (����)�d�q��t�Ǘ��s.���X�R�[�h
        l_hostConditionRegVoucherParams.setBranchCode(l_params.getBranchCode());
        //�@@���E��c�d�q��t�E��������o�^Params.�ڋq�R�[�h �� (����)�d�q��t�Ǘ��s.�ڋq�R�[�h
        l_hostConditionRegVoucherParams.setAccountCode(l_params.getAccountCode());
        //�@@���E��c�d�q��t�E��������o�^Params.���҃R�[�h �� (����) ���҃R�[�h
        l_hostConditionRegVoucherParams.setTraderCode(l_strTraderCode);
        //�@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@��� �� null
        l_hostConditionRegVoucherParams.setPosReportTermDiv(null);
        //�@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�a��� �� null
        l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv(null);
        //�@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�v�Z�� �� null
        l_hostConditionRegVoucherParams.setPosReportAccStateDiv(null);
        //�@@���E��c�d�q��t�E��������o�^Params.�d�q��t�@@���z���E���ҋ� �� null
        l_hostConditionRegVoucherParams.setRefundEReportDiv(null);
        //�@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� �� null
        l_hostConditionRegVoucherParams.setEquityTaxDiv(null);
        //�@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@�J�ݓ� �� null
        l_hostConditionRegVoucherParams.setEquitySpAccOpenDat(null);
        //�@@���E��c�d�q��t�E��������o�^Params.�i�����j��������@@���� �� null
        l_hostConditionRegVoucherParams.setEquityTaxDivNext(null);
        //�@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� �� null
        l_hostConditionRegVoucherParams.setMarginTaxDiv(null);
        //�@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@�J�ݓ� �� null
        l_hostConditionRegVoucherParams.setMarginSpAccOpenDat(null);
        //�@@���E��c�d�q��t�E��������o�^Params.�i�M�p�j��������@@���� �� null
        l_hostConditionRegVoucherParams.setMarginTaxDivNext(null);
        //�@@���E��c�d�q��t�E��������o�^Params.����Ǘ����� �� null
        l_hostConditionRegVoucherParams.setSpMngAccOpenDiv(null);
        //�@@���E��c�d�q��t�E��������o�^Params.����c���񍐏��@@�d�q��t�i�s�x�j ��
        //�@@�|(����)���N�G�X�g.����c���񍐏���t�敪 �� null�̏ꍇ�Anull
        //�@@�|(����)���N�G�X�g.����c���񍐏���t�敪 �� 0�F�X�֔z�z�̏ꍇ�A0�F�s�쐬
        //�@@�|(����)���N�G�X�g.����c���񍐏���t�敪 �� 1�F�d�q�z�z�̏ꍇ�A9�F�d�q��t
        if (l_request.positionReportDiv == null)
        {
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(null);
        }
        else if (WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.positionReportDiv))
        {
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(WEB3PosReportCycleDivDef.NOT_CREATE);
        }
        else if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.positionReportDiv))
        {
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(WEB3PosReportCycleDivDef.ELECTRONIC_DELIVER);
        }
        //�@@���E��c�d�q��t�E��������o�^Params.�d�q��t�@@����񍐏� ��
        //�@@�|(����)���N�G�X�g.����񍐏���t�敪 �� null �̏ꍇ�Anull
        //�@@�|(����)���N�G�X�g.����񍐏���t�敪 �� 0�F�X�֔z�z�̏ꍇ�A�O�F�񏳑�
        //�@@�|(����)���N�G�X�g.����񍐏���t�敪 �� 1�F�d�q�z�z�̏ꍇ�A�P�F����
        if (l_request.tradingReportDiv == null)
        {
            l_hostConditionRegVoucherParams.setTradingEReportDiv(null);
        }
        else if (WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.tradingReportDiv))
        {
            l_hostConditionRegVoucherParams.setTradingEReportDiv(WEB3ReportDivDef.NOT_ACCEPT);
        }
        else if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.tradingReportDiv))
        {
            l_hostConditionRegVoucherParams.setTradingEReportDiv(WEB3ReportDivDef.ACCEPT);
        }
        //�@@���E��c�d�q��t�E��������o�^Params.�d�q��t�@@���M�^�p�񍐏� ��
        //�@@�|(����)���N�G�X�g.�^�p�񍐏���t�敪 �� null �̏ꍇ�Anull
        //�@@�|(����)���N�G�X�g.�^�p�񍐏���t�敪 �� 0�F�X�֔z�z�̏ꍇ�A�O�F�񏳑�
        //�@@�|(����)���N�G�X�g.�^�p�񍐏���t�敪 �� 1�F�d�q�z�z�̏ꍇ�A�P�F����
        if (l_request.opeReportDiv == null)
        {
            l_hostConditionRegVoucherParams.setInvEReportDiv(null);
        }
        else if (WEB3TradingReportDivDef.MAIL_DISTRIBUTION.equals(l_request.opeReportDiv))
        {
            l_hostConditionRegVoucherParams.setInvEReportDiv(WEB3ReportDivDef.NOT_ACCEPT);
        }
        else if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_request.opeReportDiv))
        {
            l_hostConditionRegVoucherParams.setInvEReportDiv(WEB3ReportDivDef.ACCEPT);
        }

        //�R�j�@@���������s�I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_hostConditionRegVoucherParams;
    }

    /**
     * (create�d�q��t���)<BR>
     * �ڋq�I�u�W�F�N�g���A�d�q��t��񃁃b�Z�[�W�f�[�^���쐬����B <BR>
     * <BR>
     * �P�j�@@�d�q��t���I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@�d�q��t�Ǘ��s�̎擾 <BR>
     * �@@�@@�@@�d�q��t�Ǘ��e�[�u���ɂĈȉ��̏����Ō�������B <BR>
     * �@@�@@�@@���Y���s���Ȃ������ꍇ�́Anull��ԋp <BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�d�q��t�Ǘ��e�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() <BR>
     * �@@�@@�@@�d�q��t�Ǘ��e�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode() <BR>
     * �@@�@@�@@�d�q��t�Ǘ��e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode() <BR>
     * <BR>
     * �R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �@@�@@�d�q��t���.����񍐏���t��ԋ敪 = �d�q��t�Ǘ��s.����񍐏���t�敪 <BR>
     * �@@�@@�d�q��t���.����񍐏��\����ԋ敪= <BR>
     * �@@�@@ �|�i�d�q��t�Ǘ��s.����񍐏��\���敪 == 0�F�@@�\���� �@@<BR>
     * �@@�@@�@@�@@�@@�@@�܂��́@@9�F�@@SONAR���M�ρj�̏ꍇ�A0�F�@@�\���� �B<BR> 
     * �@@�@@ �|�i�d�q��t�Ǘ��s.����񍐏��\���敪 == 1�F�@@�\������ �@@<BR>
     * �@@�@@�@@�@@�@@�@@�܂��́@@2�F�@@��������j�̏ꍇ�A 1�F�@@�\�������B<BR>
     * �@@�@@�d�q��t���.����񍐏���t��ԋ敪�X�V���� = �d�q��t�Ǘ��s.����񍐏���t�敪�X�V���� <BR>
     * �@@�@@�d�q��t���.����c���񍐏���t��ԋ敪 = <BR>
     * �@@�@@ �|�i�d�q��t�Ǘ��s.����c���񍐏���t�敪 == 0�F�X�֔z�z�j�̏ꍇ�A0�F�X�֔z�z�B <BR>
     * �@@�@@ �|�i�d�q��t�Ǘ��s.����c���񍐏���t�敪 == 1�F�X�֔z�z�i��n�s�x�쐬�j�j�̏ꍇ�A0�F�X�֔z�z�B <BR>
     * �@@�@@ �|�i�d�q��t�Ǘ��s.����c���񍐏���t�敪 == 9�F�d�q�z�z�j�̏ꍇ�A1�F�d�q�z�z�B <BR>
     * �@@�@@�d�q��t���.����c���񍐏��\����ԋ敪 = <BR>
     * �@@�@@ �|�i�d�q��t�Ǘ��s.����c���񍐏��\���敪 == 0�F�@@�\���� �@@<BR>
     * �@@�@@�@@�@@�@@�@@�܂��́@@9�F�@@SONAR���M�ρj�̏ꍇ�A0�F�@@�\���� �B <BR>
     * �@@�@@ �|�i�d�q��t�Ǘ��s.����c���񍐏��\���敪 == 1�F�@@�\������ �@@<BR>
     * �@@�@@�@@�@@�@@�@@�܂��́@@2�F�@@��������j�̏ꍇ�A 1�F�@@�\�������B<BR>
     * �@@�@@�d�q��t���.����c���񍐏���t��ԋ敪�X�V���� = �d�q��t�Ǘ��s.����c���񍐏���t�敪�X�V���� <BR>
     * �@@�@@�d�q��t���.�^�p�񍐏���t��ԋ敪 = �d�q��t�Ǘ��s.�^�p�񍐏���t�敪 <BR>
     * �@@�@@�d�q��t���.�^�p�񍐏��\����ԋ敪 = <BR>
     * �@@�@@ �|�i�d�q��t�Ǘ��s.�^�p�񍐏��\���敪 == 0�F�@@�\���� �@@<BR>
     * �@@�@@�@@�@@�@@�@@�܂��́@@9�F�@@SONAR���M�ρj�̏ꍇ�A0�F�@@�\���� �B<BR>
     * �@@�@@ �|�i�d�q��t�Ǘ��s.�^�p�񍐏��\���敪 == 1�F�@@�\������ �@@<BR>
     * �@@�@@�@@�@@�@@�@@�܂��́@@2�F�@@��������j�̏ꍇ�A 1�F�@@�\�������B<BR>
     * �@@�@@�d�q��t���.�^�p�񍐏���t��ԋ敪�X�V���� = �d�q��t�Ǘ��s.�^�p�񍐏���t�敪�X�V���� <BR>
     * �@@�@@�d�q��t���.�񊼁E�K��W�񍐏���t��ԋ敪 = �d�q��t�Ǘ��s.�񊼁E�K��W�񍐏���t�敪 <BR>
     * �@@�@@�d�q��t���.�񊼁E�K��W�񍐏��\����ԋ敪 = �d�q��t�Ǘ��s.�񊼁E�K��W�񍐏��\���敪 <BR>
     * �@@�@@�d�q��t���.�񊼁E�K��W�񍐏���t��ԋ敪�X�V���� = �d�q��t�Ǘ��s.�K��W�񍐏���t�敪�X�V���� <BR>
     * �@@�@@�d�q��t���.���ʂP��t�敪 = �d�q��t�Ǘ��s.���ʂP��t�敪 <BR>
     * �@@�@@�d�q��t���.���ʂP�\���敪 = �d�q��t�Ǘ��s.���ʂP�\���敪 <BR>
     * �@@�@@�d�q��t���.���ʂP��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂP��t�敪�X�V���� <BR>
     * �@@�@@�d�q��t���.���ʂQ��t�敪 = �d�q��t�Ǘ��s.���ʂQ��t�敪 <BR>
     * �@@�@@�d�q��t���.���ʂQ�\���敪 = �d�q��t�Ǘ��s.���ʂQ�\���敪 <BR>
     * �@@�@@�d�q��t���.���ʂQ��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂQ��t�敪�X�V���� <BR>
     * �@@�@@�d�q��t���.���ʂR��t�敪 = �d�q��t�Ǘ��s.���ʂR��t�敪 <BR>
     * �@@�@@�d�q��t���.���ʂR�\���敪 = �d�q��t�Ǘ��s.���ʂR�\���敪 <BR>
     * �@@�@@�d�q��t���.���ʂR��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂR��t�敪�X�V���� <BR>
     * �@@�@@�d�q��t���.���ʂS��t�敪 = �d�q��t�Ǘ��s.���ʂS��t�敪 <BR>
     * �@@�@@�d�q��t���.���ʂS�\���敪 = �d�q��t�Ǘ��s.���ʂS�\���敪 <BR>
     * �@@�@@�d�q��t���.���ʂS��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂS��t�敪�X�V���� <BR>
     * �@@�@@�d�q��t���.���ʂT��t�敪 = �d�q��t�Ǘ��s.���ʂT��t�敪 <BR>
     * �@@�@@�d�q��t���.���ʂT�\���敪 = �d�q��t�Ǘ��s.���ʂT�\���敪 <BR>
     * �@@�@@�d�q��t���.���ʂT��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂT��t�敪�X�V����<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@return WEB3AccInfoEleDeliveryInfo
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoEleDeliveryInfo createEleDeliveryInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME="createEleDeliveryInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�d�q��t���I�u�W�F�N�g�𐶐�����B
        WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = new WEB3AccInfoEleDeliveryInfo();

        //�Q�j�@@�d�q��t�Ǘ��s�̎擾
        //�@@�@@�@@�d�q��t�Ǘ��e�[�u���ɂĈȉ��̏����Ō�������B
        //�@@�@@�@@���Y���s���Ȃ������ꍇ�́Anull��ԋp
        //�@@�@@�@@[����]
        //�@@�@@�@@�d�q��t�Ǘ��e�[�u��.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()
        //�@@�@@�@@�d�q��t�Ǘ��e�[�u��.���X�R�[�h = �ڋq.getBranch().getBranchCode()
        //�@@�@@�@@�d�q��t�Ǘ��e�[�u��.�ڋq�R�[�h = �ڋq.getAccountCode()
        EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
        try
        {
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode());
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //���Y���s���Ȃ������ꍇ�́Anull��ԋp
        if (l_eleDeliveryManagementRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g���A�ԋp����B 
        //�d�q��t���.����񍐏���t��ԋ敪 = �d�q��t�Ǘ��s.����񍐏���t�敪
        l_eleDeliveryInfo.tradingReportDiv = l_eleDeliveryManagementRow.getTradingReportDiv();
        //�d�q��t���.����񍐏��\����ԋ敪=
        //�|�i�d�q��t�Ǘ��s.����񍐏��\���敪 == 0�F�@@�\���� �@@�܂��́@@9�F�@@SONAR���M�ρj�̏ꍇ�A0�F�@@�\���� �B
        //�|�i�d�q��t�Ǘ��s.����񍐏��\���敪 == 1�F�@@�\������ �@@�܂��́@@2�F�@@��������j�̏ꍇ�A 1�F�@@�\�������B
        if (WEB3ReportRegDivDef.APPLYING.equals(l_eleDeliveryManagementRow.getTradingReportRegDiv())
            || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_eleDeliveryManagementRow.getTradingReportRegDiv()))
        {
            l_eleDeliveryInfo.tradingReportRegDiv = WEB3ReportRegDivDef.APPLYING;
        }
        else if (WEB3ReportRegDivDef.APPLIED.equals(l_eleDeliveryManagementRow.getTradingReportRegDiv())
            || WEB3ReportRegDivDef.CANCELLED.equals(l_eleDeliveryManagementRow.getTradingReportRegDiv()))
        {
            l_eleDeliveryInfo.tradingReportRegDiv = WEB3ReportRegDivDef.APPLIED;
        }
        //�d�q��t���.����񍐏���t��ԋ敪�X�V���� = �d�q��t�Ǘ��s.����񍐏���t�敪�X�V����
        l_eleDeliveryInfo.tradingReportDivUpdateDate = l_eleDeliveryManagementRow.getTradingReportDivUpdDate();
        //�d�q��t���.����c���񍐏���t��ԋ敪 =
        // �|�i�d�q��t�Ǘ��s.����c���񍐏���t�敪 == 0�F�X�֔z�z�j�̏ꍇ�A0�F�X�֔z�z�B
        // �|�i�d�q��t�Ǘ��s.����c���񍐏���t�敪 == 1�F�X�֔z�z�i��n�s�x�쐬�j�j�̏ꍇ�A0�F�X�֔z�z�B
        // �|�i�d�q��t�Ǘ��s.����c���񍐏���t�敪 == 9�F�d�q�z�z�j�̏ꍇ�A1�F�d�q�z�z�B
        if (WEB3PosReportDivDef.MAIL_DISTRIBUTION.equals(
            l_eleDeliveryManagementRow.getPositionReportDiv()))
        {
            l_eleDeliveryInfo.positionReportDiv = WEB3TradingReportDivDef.MAIL_DISTRIBUTION;
        }
        else if (WEB3PosReportDivDef.MAIL_DISTRIBUTION_EACH_TIME.equals(
            l_eleDeliveryManagementRow.getPositionReportDiv()))
        {
            l_eleDeliveryInfo.positionReportDiv = WEB3TradingReportDivDef.MAIL_DISTRIBUTION;
        }
        else if (WEB3PosReportDivDef.ELECTRON_DISTRIBUTION.equals(
            l_eleDeliveryManagementRow.getPositionReportDiv()))
        {
            l_eleDeliveryInfo.positionReportDiv = WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION;
        }
        //�d�q��t���.����c���񍐏��\����ԋ敪 =
        //�|�i�d�q��t�Ǘ��s.����c���񍐏��\���敪 == 0�F�@@�\���� �@@�܂��́@@9�F�@@SONAR���M�ρj�̏ꍇ�A0�F�@@�\���� �B
        //�|�i�d�q��t�Ǘ��s.����c���񍐏��\���敪 == 1�F�@@�\������ �@@�܂��́@@2�F�@@��������j�̏ꍇ�A 1�F�@@�\�������B
        if (WEB3ReportRegDivDef.APPLYING.equals(l_eleDeliveryManagementRow.getPositionReportRegDiv())
            || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_eleDeliveryManagementRow.getPositionReportRegDiv()))
        {
            l_eleDeliveryInfo.positionReportRegDiv = WEB3ReportRegDivDef.APPLYING;
        }
        else if (WEB3ReportRegDivDef.APPLIED.equals(l_eleDeliveryManagementRow.getPositionReportRegDiv())
            || WEB3ReportRegDivDef.CANCELLED.equals(l_eleDeliveryManagementRow.getPositionReportRegDiv()))
        {
            l_eleDeliveryInfo.positionReportRegDiv = WEB3ReportRegDivDef.APPLIED;
        }
        //�d�q��t���.����c���񍐏���t��ԋ敪�X�V���� = �d�q��t�Ǘ��s.����c���񍐏���t�敪�X�V����
        l_eleDeliveryInfo.positionReportDivUpdateDate = l_eleDeliveryManagementRow.getPositionReportDivUpdDate();
        //�d�q��t���.�^�p�񍐏���t��ԋ敪 = �d�q��t�Ǘ��s.�^�p�񍐏���t�敪
        l_eleDeliveryInfo.opeReportDiv = l_eleDeliveryManagementRow.getOpeReportDiv();
        //�d�q��t���.�^�p�񍐏��\����ԋ敪 =
        //�|�i�d�q��t�Ǘ��s.�^�p�񍐏��\���敪 == 0�F�@@�\���� �@@�܂��́@@9�F�@@SONAR���M�ρj�̏ꍇ�A0�F�@@�\���� �B
        //�|�i�d�q��t�Ǘ��s.�^�p�񍐏��\���敪 == 1�F�@@�\������ �@@�܂��́@@2�F�@@��������j�̏ꍇ�A 1�F�@@�\�������B
        if (WEB3ReportRegDivDef.APPLYING.equals(l_eleDeliveryManagementRow.getOpeReportRegDiv())
            || WEB3ReportRegDivDef.SONAR_MAIL_SENDED.equals(l_eleDeliveryManagementRow.getOpeReportRegDiv()))
        {
            l_eleDeliveryInfo.opeReportRegDiv = WEB3ReportRegDivDef.APPLYING;
        }
        else if (WEB3ReportRegDivDef.APPLIED.equals(l_eleDeliveryManagementRow.getOpeReportRegDiv())
            || WEB3ReportRegDivDef.CANCELLED.equals(l_eleDeliveryManagementRow.getOpeReportRegDiv()))
        {
            l_eleDeliveryInfo.opeReportRegDiv = WEB3ReportRegDivDef.APPLIED;
        }
        //�d�q��t���.�^�p�񍐏���t��ԋ敪�X�V���� = �d�q��t�Ǘ��s.�^�p�񍐏���t�敪�X�V����
        l_eleDeliveryInfo.opeReportDivUpdateDate = l_eleDeliveryManagementRow.getOpeReportDivUpdDate();
        //�d�q��t���.�񊼁E�K��W�񍐏���t��ԋ敪 = �d�q��t�Ǘ��s.�񊼁E�K��W�񍐏���t�敪
        l_eleDeliveryInfo.ordRulReportDiv = l_eleDeliveryManagementRow.getOrdRulReportDiv();
        //�d�q��t���.�񊼁E�K��W�񍐏��\����ԋ敪 = �d�q��t�Ǘ��s.�񊼁E�K��W�񍐏��\���敪
        l_eleDeliveryInfo.ordRulRepRegDiv = l_eleDeliveryManagementRow.getOrdRulRepRegDiv();
        //�d�q��t���.�񊼁E�K��W�񍐏���t��ԋ敪�X�V���� = �d�q��t�Ǘ��s.�K��W�񍐏���t�敪�X�V����
        l_eleDeliveryInfo.ordRulReportDivUpdateDate = l_eleDeliveryManagementRow.getOrdRulReportDivUpdDate();
        //�d�q��t���.���ʂP��t�敪 = �d�q��t�Ǘ��s.���ʂP��t�敪
        l_eleDeliveryInfo.reportDiv1 = l_eleDeliveryManagementRow.getReportDiv1();
        //�d�q��t���.���ʂP�\���敪 = �d�q��t�Ǘ��s.���ʂP�\���敪
        l_eleDeliveryInfo.reportRegDiv1 = l_eleDeliveryManagementRow.getReportRegDiv1();
        //�d�q��t���.���ʂP��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂP��t�敪�X�V����
        l_eleDeliveryInfo.reportDivUpdateDate1 = l_eleDeliveryManagementRow.getReportDivUpdDate1();
        //�d�q��t���.���ʂQ��t�敪 = �d�q��t�Ǘ��s.���ʂQ��t�敪
        l_eleDeliveryInfo.reportDiv2 = l_eleDeliveryManagementRow.getReportDiv2();
        //�d�q��t���.���ʂQ�\���敪 = �d�q��t�Ǘ��s.���ʂQ�\���敪
        l_eleDeliveryInfo.reportRegDiv2 = l_eleDeliveryManagementRow.getReportRegDiv2();
        //�d�q��t���.���ʂQ��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂQ��t�敪�X�V����
        l_eleDeliveryInfo.reportDivUpdateDate2 = l_eleDeliveryManagementRow.getReportDivUpdDate2();
        //�d�q��t���.���ʂR��t�敪 = �d�q��t�Ǘ��s.���ʂR��t�敪
        l_eleDeliveryInfo.reportDiv3 = l_eleDeliveryManagementRow.getReportDiv3();
        //�d�q��t���.���ʂR�\���敪 = �d�q��t�Ǘ��s.���ʂR�\���敪
        l_eleDeliveryInfo.reportRegDiv3 = l_eleDeliveryManagementRow.getReportRegDiv3();
        //�d�q��t���.���ʂR��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂR��t�敪�X�V����
        l_eleDeliveryInfo.reportDivUpdateDate3 = l_eleDeliveryManagementRow.getReportDivUpdDate3();
        //�d�q��t���.���ʂS��t�敪 = �d�q��t�Ǘ��s.���ʂS��t�敪
        l_eleDeliveryInfo.reportDiv4 = l_eleDeliveryManagementRow.getReportDiv4();
        //�d�q��t���.���ʂS�\���敪 = �d�q��t�Ǘ��s.���ʂS�\���敪
        l_eleDeliveryInfo.reportRegDiv4 = l_eleDeliveryManagementRow.getReportRegDiv4();
        //�d�q��t���.���ʂS��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂS��t�敪�X�V����
        l_eleDeliveryInfo.reportDivUpdateDate4 = l_eleDeliveryManagementRow.getReportDivUpdDate4();
        //�d�q��t���.���ʂT��t�敪 = �d�q��t�Ǘ��s.���ʂT��t�敪
        l_eleDeliveryInfo.reportDiv5 = l_eleDeliveryManagementRow.getReportDiv5();
        //�d�q��t���.���ʂT�\���敪 = �d�q��t�Ǘ��s.���ʂT�\���敪
        l_eleDeliveryInfo.reportRegDiv5 = l_eleDeliveryManagementRow.getReportRegDiv5();
        //�d�q��t���.���ʂT��t�敪�X�V���� = �d�q��t�Ǘ��s.���ʂT��t�敪�X�V����
        l_eleDeliveryInfo.reportDivUpdateDate5 = l_eleDeliveryManagementRow.getReportDivUpdDate5();

        log.exiting(STR_METHOD_NAME);
        return l_eleDeliveryInfo;
    }

    /**
     * (get�d�q��t���ꗗ)<BR>
     * �d�q��t���ꗗ�\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u���q�l���i�d�q��t�T�[�r�X�o�^�E�ύX�jget�d�q��t���ꗗ�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AccInfoElecDeliveryApyReferenceResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccInfoElecDeliveryApyReferenceResponse getEleDeliveryInfoList(
        WEB3AccInfoElecDeliveryApyReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME="getEleDeliveryInfoList(WEB3AccInfoElecDeliveryApyReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoElecDeliveryApyReferenceResponse l_response = null;

        //validate������t�\( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get�ڋq( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)getMainAccount();

        //create�d�q��t���(�ڋq)
        WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = this.createEleDeliveryInfo(l_mainAccount);

        //�d�q��t���ꗗ���X�|���X
        l_response = (WEB3AccInfoElecDeliveryApyReferenceResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //�d�q��t���:create�d�q��t���̖߂�l
        l_response.eleDeliveryInfo = l_eleDeliveryInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�d�q��t�Ǘ�)<BR>
     * �ڋq�ɊY������d�q��t�Ǘ����擾����B <BR>
     * <BR>
     * �P�j�@@�d�q��t�Ǘ��e�[�u������ <BR>
     * �@@�ȉ��̏����ŁA�d�q��t�Ǘ��e�[�u������������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And <BR>
     * �@@���X�R�[�h = �ڋq.getBranch().getBranchCode() And <BR>
     * �@@�����R�[�h = �ڋq.getAccountCode() <BR>
     * <BR>
     * �Q�j�@@�d�q��t�Ǘ��I�u�W�F�N�g�ԋp <BR>
     * �@@�擾�����e�s�I�u�W�F�N�g�ɂ��āA�d�q��t�Ǘ��I�u�W�F�N�g�𐶐����ԋp����B <BR>
     * �@@�s���擾�ł��Ȃ������ꍇ�A���� <BR>
     * �@@�s���������擾�ł����ꍇ�́A�f�[�^�s�����Ɣ��肵�A��O���X���[����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@return EleDeliveryManagementParams
     * @@throws WEB3BaseException
     */
    protected EleDeliveryManagementParams getEleDeliveryManagement(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME="getEleDeliveryManagement(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        EleDeliveryManagementRow l_eleDeliveryManagementRow = null;

        //�P�j�@@�d�q��t�Ǘ��e�[�u������
        //�@@�ȉ��̏����ŁA�d�q��t�Ǘ��e�[�u������������B
        //�@@[����]
        //�@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
        //�@@���X�R�[�h = �ڋq.getBranch().getBranchCode() And
        //�@@�����R�[�h = �ڋq.getAccountCode()
        try
        {
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode());
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�s���擾�ł��Ȃ������ꍇ�A����
        //�@@�s���������擾�ł����ꍇ�́A�f�[�^�s�����Ɣ��肵�A��O���X���[����B
        if (l_eleDeliveryManagementRow == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        log.exiting(STR_METHOD_NAME);
        return new EleDeliveryManagementParams(l_eleDeliveryManagementRow);
    }
}
@
