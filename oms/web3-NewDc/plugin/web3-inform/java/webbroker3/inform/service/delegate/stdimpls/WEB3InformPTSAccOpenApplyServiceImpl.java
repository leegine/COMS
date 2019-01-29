head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����J�ݐ\���T�[�r�XImpl(WEB3InformPTSAccOpenApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.124,127
Revision History : 2008/03/04 �Ӑ� (���u) ���f��No.131
Revision History : 2008/03/26 ���u�� (���u) ���f��No.133,134,�c�a�X�V�d�lNo.022
Revision History : 2009/02/12 SCS�哈 ���f��No.137
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExtDiv1Def;
import webbroker3.common.define.WEB3ExtDiv2Def;
import webbroker3.common.define.WEB3PTSAccOpenDivDef;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.QuestionRow;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.WEB3InformClientRequestService;
import webbroker3.inform.define.WEB3InformQuestionAnswerDef;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpResponse;
import webbroker3.inform.message.WEB3InformPTSTradeAgreementUnit;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.inform.service.delegate.WEB3InformPTSAccOpenApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (PTS�����J�ݐ\���T�[�r�XImpl)<BR>
 * PTS�����J�ݐ\���T�[�r�X�����N���X
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyServiceImpl extends WEB3InformClientRequestService
    implements WEB3InformPTSAccOpenApplyService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformPTSAccOpenApplyServiceImpl.class);

    /**
     * @@roseuid 47B9271A000F
     */
    public WEB3InformPTSAccOpenApplyServiceImpl()
    {

    }

    /**
     * PTS�����J�ݐ\���T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A013220328
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3InformPTSAccOpenApplyInpRequest)
        {
            //get���͉��
            l_response = this.getInputScreen((WEB3InformPTSAccOpenApplyInpRequest)l_request);
        }
        else if (l_request instanceof WEB3InformPTSAccOpenApplyCnfRequest)
        {
            //validate�\��
            l_response = this.validateApply((WEB3InformPTSAccOpenApplyCnfRequest)l_request);
        }
        else if (l_request instanceof WEB3InformPTSAccOpenApplyCmpRequest)
        {
            //submit�\��
            l_response = this.submitApply((WEB3InformPTSAccOpenApplyCmpRequest)l_request);
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
     * PTS�����J�ݐ\�����͉�ʂ̎擾���s���B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iPTS�����J�ݐ\���jget���͉�ʁv �Q�ƁB<BR>
     * ======================================================== <BR>
     * �@@�@@��̈ʒu�F  �ڋq�s.PTS�����J�݋敪 == "1�F�����J�� "�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@classes  :�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag      :�@@BUSINESS_ERROR_03024<BR>
     * ======================================================== <BR>
     * ======================================================== <BR>
     * �@@�@@��̈ʒu�F  get�e��A���̖߂�l != null ���@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�e��A��.get�敪�Q() == "0�F���J��"�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@classes  :�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag      :�@@BUSINESS_ERROR_03025<BR>
     * ======================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3InformPTSAccOpenApplyInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A013350249
     */
    protected WEB3InformPTSAccOpenApplyInpResponse getInputScreen(WEB3InformPTSAccOpenApplyInpRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3InformPTSAccOpenApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //validate����(�⏕����)
        this.validateOrder(l_subAccount);

        //�ڋq�I�u�W�F�N�g���擾����
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //�ڋq�s�I�u�W�F�N�g���擾����
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�ڋq�s.PTS�����J�݋敪 == "1�F�����J�� "�̏ꍇ�A��O��throw����B
        if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getPtsAccOpenDiv()))
        {
            log.debug("���Ɍ����J�ݍς݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03024,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Ɍ����J�ݍς݂ł��B");
        }

        //get�e��A��(�ڋq, String)
        //�ڋq�F�@@�ڋq�I�u�W�F�N�g
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        WEB3Inform l_inform =
            WEB3Inform.getVariousInform(l_mainAccount, l_request.informType);

        //get�e��A���̖߂�l != null ����
        //�e��A��.get�敪�Q() == "0�F���J��"�̏ꍇ�A��O��throw����
        if (l_inform != null && WEB3ExtDiv2Def.NOT_OPEN.equals(l_inform.getExtDiv2()))
        {
            log.debug("�����J�݂̐\������t�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03025,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݂̐\������t�ł��܂���B");
        }

        //get����(String, String)
        //�،���ЃR�[�h�F �ڋq.�،���ЃR�[�h
        //���X�R�[�h�F�@@�ڋq.���X�R�[�h
        QuestionParams[] l_questionParams = this.getQuestion(
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode());

        WEB3InformPTSTradeAgreementUnit[] l_informPTSTradeAgreementUnits = null;
        if (l_questionParams != null)
        {
            List l_lisInformPTSTradeAgreementUnits = new ArrayList();
            for (int i = 0; i < l_questionParams.length; i++)
            {
                WEB3InformPTSTradeAgreementUnit l_informPTSTradeAgreementUnit = new WEB3InformPTSTradeAgreementUnit();
                l_informPTSTradeAgreementUnit.questionNumber = l_questionParams[i].getQuestionNo();
                l_informPTSTradeAgreementUnit.questionContents = l_questionParams[i].getQuestion();

                l_lisInformPTSTradeAgreementUnits.add(l_informPTSTradeAgreementUnit);
            }

            l_informPTSTradeAgreementUnits =
                new WEB3InformPTSTradeAgreementUnit[l_lisInformPTSTradeAgreementUnits.size()];
            l_lisInformPTSTradeAgreementUnits.toArray(l_informPTSTradeAgreementUnits);
        }

        //���X�|���X�f�[�^�𐶐�����
        WEB3InformPTSAccOpenApplyInpResponse l_response =
            (WEB3InformPTSAccOpenApplyInpResponse)l_request.createResponse();

        //�ڋq���F�@@�ڋq�s.���O�i�c���j
        l_response.accountName = l_mainAccountRow.getFamilyName();
        //�@@PTS������ӎ�����ꗗ�F
        //�@@�|get����̖߂�l == null �̏ꍇ�Anull
        //�@@�|get����̖߂�l != null �̏ꍇ�A��L�ŕҏW����PTS������ӎ�����̔z��
        l_response.ptsTradeAgreementList = l_informPTSTradeAgreementUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�\��)<BR>
     * PTS�����J�ݐ\���m�F�������s���B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iPTS�����J�ݐ\���jvalidate�\���v �Q�ƁB<BR>
     * =============================================== <BR>
     * �@@��̈ʒu : �ڋq�s.PTS�����J�݋敪 == "1�F�����J�� "�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03024<BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �@@��̈ʒu :get�e��A���̖߂�l != null���� <BR>
     * �@@�@@�@@�@@�@@�@@�e��A��.get�敪�Q() == "0�F���J��"�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException  <BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03025<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3InformPTSAccOpenApplyCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A01373026B
     */
    protected WEB3InformPTSAccOpenApplyCnfResponse validateApply(WEB3InformPTSAccOpenApplyCnfRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApply(WEB3InformPTSAccOpenApplyCnfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        // get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //validate����(�⏕����)
        this.validateOrder(l_subAccount);
        //getMainAccount( )
        //�ڋq�I�u�W�F�N�g���擾����
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        //�ڋq�s�I�u�W�F�N�g���擾����
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�ڋq�s.PTS�����J�݋敪 == "1�F�����J�� "�̏ꍇ�A��O��throw����
        if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getPtsAccOpenDiv()))
        {
            log.debug("���Ɍ����J�ݍς݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03024,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Ɍ����J�ݍς݂ł��B");
        }

        //get�e��A��(�ڋq, String)
        WEB3Inform l_inform = WEB3Inform.getVariousInform(
            (WEB3GentradeMainAccount)l_mainAccount, l_request.informType);

        //get�e��A���̖߂�l != null ���@@�e��A��.get�敪�Q() == "0�F���J��"�̏ꍇ�A��O��throw����B
        if (l_inform != null && WEB3ExtDiv2Def.NOT_OPEN.equals(l_inform.getExtDiv2()))
        {
            log.debug("�����J�݂̐\������t�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03025,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݂̐\������t�ł��܂���B");
        }

        //���N�G�X�g�f�[�^.PTS������ӎ�����ꗗ != null�̏ꍇ�A�ȉ��̏������s��
        if (l_request.ptsTradeAgreementList != null)
        {
            //validatePTS�����J�ݎ���(PTS������ӎ�����[])
            //[����]
            // PTS������ӎ�����ꗗ�F ���N�G�X�g�f�[�^.PTS������ӎ�����ꗗ
            this.validatePTSAccOpenQuestion(l_request.ptsTradeAgreementList);
        }

        //�d�q���`�F�b�N���s��
        //���N�G�X�g.�d�q���`�F�b�N�t���O == true�̏ꍇ
        String[] l_tradingDocReadHistorys = null;
        if (l_request.batoCheckFlag)
        {
            //validatePTS����h�L�������g�{������
            l_tradingDocReadHistorys =
                this.validatePTSTradingDocReadHistory(l_request.typeCode, l_request.productCode);
        }

        WEB3InformPTSAccOpenApplyCnfResponse l_response =
            (WEB3InformPTSAccOpenApplyCnfResponse)l_request.createResponse();
        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //validatePTS����h�L�������g�{������()�̖߂�l�z��inull�̏ꍇ�̓Z�b�g���Ȃ��j
        //�����N�G�X�g.�d�q���`�F�b�N�t���O == false�̏ꍇ�Z�b�g���Ȃ�
        if (l_tradingDocReadHistorys != null && l_request.batoCheckFlag)
        {
            l_response.productCode = l_tradingDocReadHistorys;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�\��)<BR>
     * PTS�����J�ݐ\�������������s���B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iPTS�����J�ݐ\���jsubmit�\���v �Q�ƁB<BR>
     * ======================================================== <BR>
     * �@@�@@��̈ʒu�F  �ڋq�s.PTS�����J�݋敪 == "1�F�����J�� "�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@classes  :�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag      :�@@BUSINESS_ERROR_03024<BR>
     * ======================================================== <BR>
     * ======================================================== <BR>
     * �@@�@@��̈ʒu�F  get�e��A���̖߂�l != null ���@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�e��A��.get�敪�Q() == "0�F���J��"�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@classes  :�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag      :�@@BUSINESS_ERROR_03025<BR>
     * ======================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3InformPTSAccOpenApplyCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A0137602F7
     */
    protected WEB3InformPTSAccOpenApplyCmpResponse submitApply(WEB3InformPTSAccOpenApplyCmpRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitApply(WEB3InformPTSAccOpenApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //get����
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        
        //�ڋq�s�I�u�W�F�N�g���擾����
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //FinApp, GenTradeAccountManager, OpLoginSecurityService
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();   	
        
        //lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        l_accManager.lockAccount(
                l_mainAccountRow.getInstitutionCode(),
                l_mainAccountRow.getBranchCode(),
                l_mainAccountRow.getAccountCode());
        
        //get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //validate����(�⏕����)
        this.validateOrder(l_subAccount);

        //get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //validate����p�X���[�h
        //�㗝���͎ҁF get�㗝���͎�()�̖߂�l
        //�⏕�����F get�⏕����()�̖߂�l
        //�p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        WEB3GentradeOrderValidator l_validator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_validator.validateTradingPassword(l_trader, l_subAccount, l_request.password);

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug(STR_METHOD_NAME
                + l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }



        //�ڋq�s.PTS�����J�݋敪 == "1�F�����J�� "�̏ꍇ�A��O��throw����
        if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getPtsAccOpenDiv()))
        {
            log.debug("���Ɍ����J�ݍς݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03024,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Ɍ����J�ݍς݂ł��B");
        }

        //get�e��A��(�ڋq, String)
        //�ڋq�F�@@�ڋq�I�u�W�F�N�g
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        WEB3Inform l_inform =
            WEB3Inform.getVariousInform(l_mainAccount, l_request.informType);

        //get�e��A���̖߂�l != null ����
        //�e��A��.get�敪�Q() == "0�F���J��"�̏ꍇ�A��O��throw����
        if (l_inform != null && WEB3ExtDiv2Def.NOT_OPEN.equals(l_inform.getExtDiv2()))
        {
            log.debug("�����J�݂̐\������t�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03025,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݂̐\������t�ł��܂���B");
        }

        //get�V�K���ʃR�[�h
        //[����]
        //�،���ЃR�[�h�F �ڋq�s.�،���ЃR�[�h
        //�A����ʁF ���N�G�X�g�f�[�^.�A�����
        WEB3InformHostReqOrderNumberManageService l_informHostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);
        String l_strNewOrderRequestCode = l_informHostReqOrderNumberManageService.getNewOrderRequestCode(
            l_mainAccountRow.getInstitutionCode(),
            l_request.informType);

        //���N�G�X�g�f�[�^.PTS������ӎ�����ꗗ != null�̏ꍇ�A�ȉ��̏������s��
        if (l_request.ptsTradeAgreementList != null)
        {
            //validatePTS�����J�ݎ���(PTS������ӎ�����[])
            //PTS������ӎ�����ꗗ�F ���N�G�X�g�f�[�^.PTS������ӎ�����ꗗ
            this.validatePTSAccOpenQuestion(l_request.ptsTradeAgreementList);

            //get�V�K���ʃR�[�h
            //[�����̐ݒ�]
            //�،���ЃR�[�h�F �ڋq�s.�،���ЃR�[�h
            //���X�R�[�h�F�@@�ڋq�s.���X�R�[�h
            //�����^�C�v�F ProductTypeEnum.���̑�
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);

            String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_mainAccountRow.getInstitutionCode(),
                l_mainAccountRow.getBranchCode(),
                ProductTypeEnum.OTHER);

            //insert�����(String, String, String, PTS������ӎ�����[])
            //[����]
            //�،���ЃR�[�h�F �ڋq.�،���ЃR�[�h
            //���X�R�[�h�F �ڋq.���X�R�[�h
            //���ʃR�[�h�F �������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�̖߂�l
            //������ꗗ�F ���N�G�X�g�f�[�^.PTS������ӎ�����ꗗ
            this.insertQuestionAnswer(
                l_mainAccountRow.getInstitutionCode(),
                l_mainAccountRow.getBranchCode(),
                l_strNewNumber,
                l_request.ptsTradeAgreementList);
        }

        String l_strLastUpdater = null;
        if (l_trader != null)
        {
            //�|�㗝���͂̏ꍇ�A�㗝���͎�.���҃R�[�h
            l_strLastUpdater = l_trader.getTraderCode();
        }
        else
        {
            //�@@�|�ڋq���͂̏ꍇ�A�ڋq�s.�����R�[�h
            l_strLastUpdater = l_mainAccountRow.getAccountCode().substring(0, 6);
        }

        //get�e��A���̖߂�l != null �̏ꍇ�A�ȉ��̏������s���B
        if (l_inform != null)
        {
            //�ȉ��̓��e��Map�I�u�W�F�N�g�𐶐�����B
            Map l_map = new HashMap();
            //�敪�P�F�@@"0"�i�����j
            l_map.put("ext_div1", WEB3ExtDiv1Def.INVALIDITY);

            //�X�V�҃R�[�h�F�@@�i�ȉ��̂Ƃ���j
            //�@@�|�ڋq���͂̏ꍇ�A�ڋq�s.�����R�[�h�̓�6��
            //�@@�|�㗝���͂̏ꍇ�A�㗝���͎�.���҃R�[�h
            l_map.put("last_updater", l_strLastUpdater);

            //�X�V�����F�@@��������
            l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            //updatePTS�e��A��(Map)
            l_inform.updatePTSVariousInform(l_map);
        }

        //createNew�e��A��
        //�ڋq�F�@@�ڋq�I�u�W�F�N�g
        //�A����ʁF�@@���N�G�X�g�f�[�^.�A�����
        //PTS�����J�݋敪�F�@@���N�G�X�g�f�[�^.PTS�����J�݋敪
        //�X�V�҃R�[�h�F�@@�i�ȉ��̂Ƃ���j
        // �|�ڋq���͂̏ꍇ�A�ڋq�s.�����R�[�h�̓�6��
        // �|�㗝���͂̏ꍇ�A�㗝���͎�.���҃R�[�h
        //���ʃR�[�h�F�@@�A���Ǘ����ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h()�̖߂�l
        WEB3Inform l_newInform = WEB3Inform.createNewVariousInform(
            l_mainAccount,
            l_request.informType,
            l_request.ptsAccOpenDiv,
            l_strLastUpdater,
            l_strNewOrderRequestCode);

        //saveNew�e��A��
        l_newInform.saveNewVariousInform();

        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�ڋq�}�X�^�̈ȉ��̍��ڂ��X�V����B
            //�y��Trade�z�⑫����.DB�X�V�uPTS�����J�ݐ\��_�ڋq�}�X�^�[�v�Q��
            Map l_mainAccountMap = new HashMap();
            //PTS�����J�݋敪�F�@@���N�G�X�g�f�[�^.PTS�����J�݋敪
            l_mainAccountMap.put("pts_acc_open_div", l_request.ptsAccOpenDiv);
            //PTS�����J�݋敪�X�V�҃R�[�h�F�@@�i�ȉ��̂Ƃ���j
            //�ڋq���͂̏ꍇ�A�ڋq�}�X�^�e�[�u��.�����R�[�h�̓�6��
            //�㗝���͂̏ꍇ�A�㗝���͎�.���҃R�[�h
            l_mainAccountMap.put("pts_acc_open_div_last_updater", l_strLastUpdater);
            //PTS�����J�݋敪�X�V�����F�@@��������
            l_mainAccountMap.put("pts_acc_open_div_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

            //primaryKey
            MainAccountPK l_mainAccountPK = new MainAccountPK(l_mainAccountRow.getAccountId());
            //doUpdateQuery(arg0 : PrimaryKey, arg1 : Map)
            l_queryProcessor.doUpdateQuery(l_mainAccountPK, l_mainAccountMap);
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

        WEB3InformPTSAccOpenApplyCmpResponse l_response =
            (WEB3InformPTSAccOpenApplyCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����)<BR>
     * �������ʃ`�F�b�N�����{����B<BR>
     * <BR>
     * �P�j�@@��t���ԃ`�F�b�N�E�V�X�e�������~�`�F�b�N���s���B<BR>
     * �@@�P�|�P�j�@@������ԊǗ�.validate������t�\()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�ڋq�ʎ����~�`�F�b�N���s���B<BR>
     * �@@�Q�|�P�j�@@GtlUtils.getCommonOrderValidator()���\�b�h���R�[�����A<BR>
     * �@@�@@�����`�F�b�N�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����`�F�b�N.validate����\�ڋq()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[validate����\�ڋq()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 47B11D8A024B
     */
    private void validateOrder(SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //��t���ԃ`�F�b�N�E�V�X�e�������~�`�F�b�N���s���B
        // ������ԊǗ�.validate������t�\()���R�[������B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //�ڋq�ʎ����~�`�F�b�N���s���B
        // �@@GtlUtils.getCommonOrderValidator()���\�b�h���R�[�����A
        // �@@�@@�����`�F�b�N�I�u�W�F�N�g���擾����B
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

        //�����`�F�b�N.validate����\�ڋq()���\�b�h���R�[������
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug(STR_METHOD_NAME
                + l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get����)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�Ɉ�v����<BR>
     * ����Params��ԋp����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@����e�[�u��(question)���ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h=����.���X�R�[�h<BR>
     * �@@����敪="0003"�iPTS�j<BR>
     * <BR>
     * �Q�j�������ʂ��u����ԍ��v���ڂ̏����Ń\�[�g���A�ԋp����B<BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@return QuestionParams[]
     * @@throws WEB3BaseException
     * @@roseuid 47A2C3110250
     */
    private QuestionParams[] getQuestion(String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getQuestion(String, String)";
        log.entering(STR_METHOD_NAME);

        //DB����<BR>
        //����e�[�u��(question)���ȉ��̏����Ō�������B
        // �@@[����]
        // �@@�،���ЃR�[�h=����.�،���ЃR�[�h
        // �@@���X�R�[�h=����.���X�R�[�h
        // �@@����敪="0003"�iPTS�j
        StringBuffer l_sbWhere = new StringBuffer();

        l_sbWhere.append(" institution_code = ?");
        l_sbWhere.append(" and branch_code = ?");
        l_sbWhere.append(" and question_div = ?");

        Object[] l_sqlValues = {
            l_strInstitutionCode,
            l_strBranchCode,
            WEB3QuestionDivDef.PTS};

        //�������ʂ��u����ԍ��v���ڂ̏����Ń\�[�g���A�ԋp����B
        String l_strOrderBy = " question_no asc";

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                QuestionRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_sqlValues);
        }
        catch (DataNetworkException l_dnEx)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dnEx);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dnEx.getMessage(),
                l_dnEx);
        }
        catch (DataQueryException l_dqEx)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqEx);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqEx.getMessage(),
                l_dqEx);
        }

        //���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        QuestionParams[] l_questionParams = new QuestionParams[l_lisRecords.size()];
        for (int i = 0; i < l_lisRecords.size(); i++)
        {
            QuestionRow l_questionRow = (QuestionRow)l_lisRecords.get(i);
            l_questionParams[i] = new QuestionParams(l_questionRow);
        }

        log.exiting(STR_METHOD_NAME);
        return l_questionParams;
    }

    /**
     * (validatePTS�����J�ݎ���)<BR>
     * PTS�����J�ݎ���ɑ΂���񓚂̐��������`�F�b�N����B <BR>
     * <BR>
     * ����.PTS������ӎ�����ꗗ�̗v�f���Ƃ�Loop�����ɂāA <BR>
     * �ȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * PTS������ӎ�����.����񓚁��h1�F���Ӂh�̏ꍇ�A��O��thorw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_03026<BR>
     * <BR>
     * @@param l_ptsTradeAgreementList - (PTS������ӎ�����ꗗ)<BR>
     * PTS������ӎ�����ꗗ
     * @@throws WEB3BaseException
     * @@roseuid 47A8174302AC
     */
    private void validatePTSAccOpenQuestion(WEB3InformPTSTradeAgreementUnit[] l_ptsTradeAgreementList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSAccOpenQuestion(WEB3InformPTSTradeAgreementUnit[])";
        log.entering(STR_METHOD_NAME);

        //����.PTS������ӎ�����ꗗ�̗v�f���Ƃ�Loop�����ɂāA
        for (int i = 0; i < l_ptsTradeAgreementList.length; i++)
        {
            //PTS������ӎ�����.����񓚁��h1�F���Ӂh�̏ꍇ�A��O��thorw����B
            if (!WEB3InformQuestionAnswerDef.YES.equals(l_ptsTradeAgreementList[i].questionAnswer))
            {
                log.debug("����񓚂͓��ӂł͂���܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03026,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����񓚂͓��ӂł͂���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�����)<BR>
     * PTS������ӎ�����̓��e�Ŏ���񓚃e�[�u��(question_answer)�ɍs��insert���s���B
     * <BR>
     * ������.PTS������ӎ�����ꗗ�̗v�f������Loop�������s���A�v�f���Ƃɍs��insert���s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �uPTS�����J�ݐ\��_����񓚃e�[�u��.xls�v<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     * @@param l_ptsTradeAgreementList - (PTS������ӎ�����ꗗ)<BR>
     * PTS������ӎ�����ꗗ
     * @@throws WEB3BaseException
     * @@roseuid 47A9793003BD
     */
    private void insertQuestionAnswer(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber,
        WEB3InformPTSTradeAgreementUnit[] l_ptsTradeAgreementList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertQuestionAnswer(String, String, String, WEB3InformPTSTradeAgreementUnit[])";
        log.entering(STR_METHOD_NAME);

        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //����.PTS������ӎ�����ꗗ�̗v�f������Loop�������s���A�v�f���Ƃɍs��insert���s��
            for (int i = 0; i < l_ptsTradeAgreementList.length; i++)
            {
                QuestionAnswerParams l_questionAnswerParams = new QuestionAnswerParams();
                //�،���ЃR�[�h
                l_questionAnswerParams.setInstitutionCode(l_strInstitutionCode);
                //���X�R�[�h
                l_questionAnswerParams.setBranchCode(l_strBranchCode);
                //����敪
                l_questionAnswerParams.setQuestionDiv(WEB3QuestionDivDef.PTS);
                //���ʃR�[�h
                l_questionAnswerParams.setOrderRequestNumber(l_strRequestNumber);
                //����ԍ�
                l_questionAnswerParams.setQuestionNo(l_ptsTradeAgreementList[i].questionNumber);
                //�����
                l_questionAnswerParams.setQuestionAnswer(l_ptsTradeAgreementList[i].questionAnswer);
                //�X�V�҃R�[�h
                l_questionAnswerParams.setLastUpdater(null);
                //�쐬���t
                l_questionAnswerParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //�X�V���t
                l_questionAnswerParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //insert���s��
                l_queryProcessor.doInsertQuery(l_questionAnswerParams);
            }
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePTS����h�L�������g�{������)<BR>
     * �d�q���V�X�e���֐ڑ����A�Y���ڋq�̉{�����������݂��邩�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ژ_�����{���`�F�b�N<BR>
     * �@@�P�|�P�j�@@ArrayList�̍쐬�B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�����R�[�h�̗v�f�����A�ȉ��̏��������{�B<BR>
     * <BR>
     * �@@�@@�P�|�Q�|�P�j�@@�d�q���V�X�e���ڑ��T�[�r�XImpl.validate�ژ_�����{�������{�B<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@��ʃR�[�h�F����.��ʃR�[�h<BR>
     * �@@�@@�@@�@@�����R�[�h�F����.�����R�[�h[index]<BR>
     * <BR>
     * �@@�@@�P�|�Q�|�Q�j�@@�擾�����ژ_�����{���`�F�b�N����.�`�F�b�N���ʂ�<BR>
     * �@@�@@�u1�F �{�����ρv�̏ꍇ�AArrayList�ɖ����R�[�h��ǉ��B<BR>
     * <BR>
     * �Q�j�@@�ژ_�����{���`�F�b�N���ʂ̊m�F<BR>
     * �@@�Q�|�P�j�@@�쐬����ArrayList�i�P-�P�ō쐬�j�̗v�f�����u�O�v�̏ꍇ�A<BR>
     * �@@�uNULL�v��ԋp����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�쐬����ArrayList�i�P-�P�ō쐬�j�̗v�f�����u�O�v�łȂ��ꍇ�A<BR>
     * �@@�z��ɕϊ����������R�[�h��ԋp����B<BR>
     * @@param l_strTypeCode - (��ʃR�[�h)<BR>
     * ��ʃR�[�h
     * @@param l_strProductCodes - (�����R�[�h)<BR>
     * �����R�[�h
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 47ABE1D8016E
     */
    private String[] validatePTSTradingDocReadHistory(String l_strTypeCode, String[] l_strProductCodes)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePTSTradingDocReadHistory(String, String[])";
        log.entering(STR_METHOD_NAME);

        //ArrayList�̍쐬�B
        List l_lisProductCodes = new ArrayList();

        WEB3GentradeBatoClientService l_batoClientServiceImpl =
            (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);
        WEB3GentradeProspectusResult l_prospectusResult = null;

        //�d�q���V�X�e���ڑ��T�[�r�XImpl.validate�ژ_�����{�������{�B
        //        [����]
        //        ��ʃR�[�h�F����.��ʃR�[�h
        //        �����R�[�h�F����.�����R�[�h[index]
        for (int i = 0; i <l_strProductCodes.length; i++)
        {
            l_prospectusResult = l_batoClientServiceImpl.validateProspectus(l_strTypeCode, l_strProductCodes[i]);
            //�擾�����ژ_�����{���`�F�b�N����.�`�F�b�N���ʂ�
            //�u1�F �{�����ρv�̏ꍇ�AArrayList�ɖ����R�[�h��ǉ��B
            if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(l_prospectusResult.checkResult))
            {
                l_lisProductCodes.add(l_strProductCodes[i]);
            }
        }

        //�ژ_�����{���`�F�b�N���ʂ̊m�F
        // �@@�쐬����ArrayList�̗v�f�����u�O�v�̏ꍇ�ANULL�v��ԋp����B
        String[] l_strProductCodeReturns = null;
        if (l_lisProductCodes.size() != 0)
        {
            //�쐬����ArrayList�̗v�f�����u�O�v�łȂ��ꍇ�A
            // �@@�z��ɕϊ����������R�[�h��ԋp����B
            l_strProductCodeReturns = new String[l_lisProductCodes.size()];
            l_lisProductCodes.toArray(l_strProductCodeReturns);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strProductCodeReturns;
    }
}
@
