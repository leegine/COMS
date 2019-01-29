head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����T�[�r�XImpl(WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/06 ���z(���u) �V�K�쐬 ���f��No.027
Revision History : 2008/10/10 ���z(���u) �d�l�ύX ���f��No.029 030
Revision History : 2008/10/14 ���z(���u) �d�l�ύX ���f��No.031 033
Revision History : 2008/10/17 ���z(���u) �d�l�ύX ���f��No.038
Revision History : 2008/10/27 ���z(���u) �d�l�ύX ���f��No.041
Revision History : 2008/11/05 ����(���u) �d�l�ύX ���f��No.043 044
*/

package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountAttributeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionAccountDetailInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpoweradmin.WEB3AdminTPPaymentRequisitionCustomerSearchCSV;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPClaimReasonDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPCustomerAttributeDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPDaysDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPShortfallGenerationStateDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPaAdditionalGenerationStateDivDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFirstAdditionalInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionCommonRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPSecondAdditionalInfo;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPShortfallGenerationInfo;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionCustomerSearchService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���������ڋq�����T�[�r�XImpl)<BR>
 * �i���������ڋq�����T�[�r�XImpl�j<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl extends WEB3ClientRequestService
    implements WEB3AdminTPPaymentRequisitionCustomerSearchService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class);

    /**
     * @@roseuid 48E9E2520249
     */
    public WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * <BR>
     * �V�[�P���X�}�uexecute�v�Q��<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48E9E25202C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        //���������ڋq�������̓��N�G�X�g�ł������ꍇ
        if (l_request instanceof WEB3AdminTPPaymentRequisitionInputRequest)
        {
            //get���������ڋq��������(���������ڋq�������̓��N�G�X�g)
            l_response = getPaymentRequisitionCustomerSearchInput(
                (WEB3AdminTPPaymentRequisitionInputRequest)l_request);
        }
        //���������ڋq�����ꗗ���N�G�X�g�ł������ꍇ
        else if (l_request instanceof WEB3AdminTPPaymentRequisitionListRequest)
        {
            //get���������ڋq�����ꗗ(���������ڋq�����ꗗ���N�G�X�g)
            l_response = getPaymentRequisitionCustomerSearchList(
                (WEB3AdminTPPaymentRequisitionListRequest)l_request);
        }
        //���������ڋq�����ڍ׃��N�G�X�g�ł������ꍇ
        else if (l_request instanceof WEB3AdminTPPaymentRequisitionDetailRequest)
        {
            //get���������ڋq�����ڍ�(���������ڋq�����ڍ׃��N�G�X�g)
            l_response = getPaymentRequisitionCustomerSearchDetail(
                (WEB3AdminTPPaymentRequisitionDetailRequest)l_request);
        }
        //���������ڋq�����_�E�����[�h���N�G�X�g�ł������ꍇ
        else if (l_request instanceof WEB3AdminTPPaymentRequisitionDownLoadRequest)
        {
            //get���������ڋq�����_�E�����[�h(���������ڋq�����_�E�����[�h���N�G�X�g)
            l_response = getPaymentRequisitionCustomerSearchDownLoad(
                (WEB3AdminTPPaymentRequisitionDownLoadRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������ڋq��������)<BR>
     * (get���������ڋq��������)<BR>
     * <BR>
     * �V�[�P���X�}�uget���������ڋq�������́v�Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminTPPaymentRequisitionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C9FF830140
     */
    private WEB3AdminTPPaymentRequisitionInputResponse getPaymentRequisitionCustomerSearchInput(
        WEB3AdminTPPaymentRequisitionInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchInput(WEB3AdminTPPaymentRequisitionInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N���s���B
        //�Ǘ��Ҍ����`�F�b�N
        //�E�J�e�S���R�[�h="A0201"�i�]�͊Ǘ��ҁj
        //�E�X�V�t���O=false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADING_POWER, false);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTPPaymentRequisitionInputResponse l_response =
            (WEB3AdminTPPaymentRequisitionInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������ڋq�����ꗗ)<BR>
     * (get���������ڋq�����ꗗ)<BR>
     * <BR>
     * �V�[�P���X�}�uget���������ڋq�����ꗗ�v�Q��<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminTPPaymentRequisitionListResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C9D243037A
     */
    private WEB3AdminTPPaymentRequisitionListResponse getPaymentRequisitionCustomerSearchList(
        WEB3AdminTPPaymentRequisitionListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchList(WEB3AdminTPPaymentRequisitionListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N���s���B
        //�Ǘ��Ҍ����`�F�b�N
        //�E�J�e�S���R�[�h="A0201"�i�]�͊Ǘ��ҁj
        //�E�X�V�t���O=false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADING_POWER, false);

        //get���������ڋq���Params�ꗗ(���������ڋq�������ʃ��N�G�X�g)
        //���������Ǘ��e�[�u���̃f�[�^���擾����B
        //[����]
        //�E���N�G�X�g�f�[�^
        List l_lisPaymentRequisitMngParams = getPaymentRequisitMngParamsList(l_request);

        //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //�y�[�W�����N���X�̃C���X�^���X����
        //�Eget���������ڋq���Params�ꗗ()�̖߂�l
        //�E���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
        //�E���N�G�X�g�f�[�^.�y�[�W���\���s��
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisPaymentRequisitMngParams,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        List l_lisUnits = new ArrayList();
        Object[] l_objUnits = l_pageIndexInfo.getArrayReturned();
        for (int i = 0; i < l_objUnits.length; i++)
        {
            l_lisUnits.add(l_objUnits[i]);
        }

        PaymentRequisitMngParams[] l_paymentRequisitMngParams =
            new PaymentRequisitMngParams[l_lisUnits.size()];
        l_lisUnits.toArray(l_paymentRequisitMngParams);

        //create���������ڋq�����ꗗ���j�b�g(���������Ǘ�Params[])
        //���������ڋq�����ꗗ���j�b�g�Ƀv���p�e�B���Z�b�g����B
        //[����]
        //�E�@@WEB3PageIndexInfo�̖߂�l.getArrayReturned()
        WEB3AdminTPPaymentRequisitionListUnit[] l_paymentRequisitionListUnits =
            createPaymentRequisitionListUnit(l_paymentRequisitMngParams);

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTPPaymentRequisitionListResponse l_response =
            (WEB3AdminTPPaymentRequisitionListResponse)l_request.createResponse();

        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�����R�[�h���O���̏ꍇ�́u�O�c�Ɠ��v���Z�b�g����B
        if (l_lisPaymentRequisitMngParams.size() == 0)
        {
            Timestamp l_tsSysTimestamp = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsSysTimestamp);
            l_response.calcDate = l_genBizDate.roll(-1);
        }
        else
        {
            //�v�Z��         ���@@get���������ڋq���Params�ꗗ()�̖߂�l�̂P���R�[�h�ڂ̌v�Z��
            l_response.calcDate = WEB3DateUtility.toDay(
                ((PaymentRequisitMngParams)l_lisPaymentRequisitMngParams.get(0)).getCalcDate());
        }
        //�������R            ���@@���N�G�X�g�f�[�^.�������R
        l_response.claimReason = l_request.claimReason;
        //����          ���@@���N�G�X�g�f�[�^.����
        l_response.days = l_request.days;
        //���y�[�W��       ���@@WEB3PageIndexInfo�̖߂�l.getTotalPages()
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        //�\���y�[�W�ԍ�     ���@@WEB3PageIndexInfo�̖߂�l.getPageIndex()
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        //�����R�[�h��      ���@@WEB3PageIndexInfo�̖߂�l.getTotalRecords()
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        //���������ڋq�������ꗗ    ���@@create���������ڋq�����ꗗ���j�b�g()�̖߂�l�̔z��
        l_response.paymentRequisitionListUnit = l_paymentRequisitionListUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������ڋq�����ڍ�)<BR>
     * (get���������ڋq�����ڍ�)<BR>
     * <BR>
     * �V�[�P���X�}�uget���������ڋq�����ڍׁv�Q��<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminTPPaymentRequisitionDetailResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C9D3260171
     */
    private WEB3AdminTPPaymentRequisitionDetailResponse getPaymentRequisitionCustomerSearchDetail(
        WEB3AdminTPPaymentRequisitionDetailRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchDetail(WEB3AdminTPPaymentRequisitionDetailRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N���s���B
        //�Ǘ��Ҍ����`�F�b�N
        //�E�J�e�S���R�[�h="A0201"�i�]�͊Ǘ��ҁj
        //�E�X�V�t���O=false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADING_POWER, false);

        //get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //�ڋq�I�u�W�F�N�g���擾����B
        //[����]
        //�،���ЃR�[�h = �Z�b�V������.�،���ЃR�[�h
        //���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        //�ڋq�R�[�h = ���N�G�X�g�f�[�^.�ڋq�R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount =
            l_gentradeAccountManager.getMainAccount(
                l_administrator.getInstitutionCode(),
                l_request.branchCode,
                l_request.accountCode);

        //get���������ڋq�ڍ׏��(�ڋq : �ڋq)
        //�Y������ڋq�̓��������ڋq�����擾����B
        //[����]
        //get�ڋq()
        WEB3TPPaymentRequisitionManageService l_paymentRequisitionManageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);

        WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
            l_paymentRequisitionManageService.getPaymentRequisitionAccountDetailInfo(l_mainAccount);

        //getAccountId( )
        //�擾�����ڋq�̃A�J�E���gID���擾����B
        long l_lngAccountId = l_mainAccount.getAccountId();

        //get�ڋq�]�͏���Params(Long)
        //�擾�����ڋq.����ID����Ɍڋq�]�͏������擾����B
        //[����]
        //�EgetAccountId()�Ŏ擾�����A�J�E���gID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            getTradingPowerCalcConditionParams(new Long(l_lngAccountId));

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTPPaymentRequisitionDetailResponse l_response =
            (WEB3AdminTPPaymentRequisitionDetailResponse)l_request.createResponse();

        //�C���X�^���X�����i�s�����������j
        //�s�����������I�u�W�F�N�g�̃C���X�^���X�𐶐�����B
        WEB3AdminTPShortfallGenerationInfo l_shortfallGenerationInfo =
            new WEB3AdminTPShortfallGenerationInfo();

        //�v���p�e�B�Z�b�g�i�s�����������j
        //get���������ڋq�ڍ׏��()�Ŏ擾����
        //���������ڋq�ڍ׏��.�s�����������̊e���ڒl��
        //���������s�����������ɃZ�b�g����B
        //�P�j�s�����������̃Z�b�g
        //�s�����������.����(T+0) = get���������ڋq�ڍ׏��.�s�����������.����(T+0)
        l_shortfallGenerationInfo.closeDate0 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate0);
        //�s�����������.����(T+1) = get���������ڋq�ڍ׏��.�s�����������.����(T+1)
        l_shortfallGenerationInfo.closeDate1 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate1);
        //�s�����������.����(T+2) = get���������ڋq�ڍ׏��.�s�����������.����(T+2)
        l_shortfallGenerationInfo.closeDate2 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate2);
        //�s�����������.����(T+3) = get���������ڋq�ڍ׏��.�s�����������.����(T+3)
        l_shortfallGenerationInfo.closeDate3 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate3);
        //�s�����������.����(T+4) = get���������ڋq�ڍ׏��.�s�����������.����(T+4)
        l_shortfallGenerationInfo.closeDate4 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate4);
        //�s�����������.����(T+5) = get���������ڋq�ڍ׏��.�s�����������.����(T+5)
        l_shortfallGenerationInfo.closeDate5 =
            WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().closeDate5);
        //�s�����������.�K�v�����z(T+0) = get���������ڋq�ڍ׏��.�s�����������.�K�v�����z(T+0)
        l_shortfallGenerationInfo.requiredPayAmt0 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt0);
        //�s�����������.�K�v�����z(T+1) = get���������ڋq�ڍ׏��.�s�����������.�K�v�����z(T+1)
        l_shortfallGenerationInfo.requiredPayAmt1 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt1);
        //�s�����������.�K�v�����z(T+2) = get���������ڋq�ڍ׏��.�s�����������.�K�v�����z(T+2)
        l_shortfallGenerationInfo.requiredPayAmt2 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt2);
        //�s�����������.�K�v�����z(T+3) = get���������ڋq�ڍ׏��.�s�����������.�K�v�����z(T+3)
        l_shortfallGenerationInfo.requiredPayAmt3 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt3);
        //�s�����������.�K�v�����z(T+4) = get���������ڋq�ڍ׏��.�s�����������.�K�v�����z(T+4)
        l_shortfallGenerationInfo.requiredPayAmt4 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt4);
        //�s�����������.�K�v�����z(T+5) = get���������ڋq�ڍ׏��.�s�����������.�K�v�����z(T+5)
        l_shortfallGenerationInfo.requiredPayAmt5 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().requiredPayAmt5);
        //�s�����������.���v��S����(T+0) = get���������ڋq�ڍ׏��.�s�����������.���v��S����(T+0)
        l_shortfallGenerationInfo.dayTradeRestraint0 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().dayTradeRestraint0);
        //�s�����������.���v��S����(T+1) = get���������ڋq�ڍ׏��.�s�����������.���v��S����(T+1)
        l_shortfallGenerationInfo.dayTradeRestraint1 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().dayTradeRestraint1);
        //�s�����������.�ۏ؋�����̐U�֊z(T+0) = get���������ڋq�ڍ׏��.�s�����������.�ۏ؋�����̐U�֊z(T+0)
        l_shortfallGenerationInfo.transferFromMarginDeposit0 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().transferFromMarginDeposit0);
        //�s�����������.�ۏ؋�����̐U�֊z(T+1) = get���������ڋq�ڍ׏��.�s�����������.�ۏ؋�����̐U�֊z(T+1)
        l_shortfallGenerationInfo.transferFromMarginDeposit1 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().transferFromMarginDeposit1);
        //�s�����������.���Z�z(T+0) = get���������ڋq�ڍ׏��.�s�����������.���Z�z(T+0)
        l_shortfallGenerationInfo.adjustedAmt0 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().adjustedAmt0);
        //�s�����������.���Z�z(T+1) = get���������ڋq�ڍ׏��.�s�����������.���Z�z(T+1)
        l_shortfallGenerationInfo.adjustedAmt1 =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().adjustedAmt1);

        //get���������ڋq�ڍ׏��().�ڋq���� = 2(�M�p) �̏ꍇ
        if (WEB3AccountAttributeDef.MARGIN.equals(
            l_paymentRequisitionAccountDetailInfo.getAccountAttribute()))
        {
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                l_paymentRequisitionAccountDetailInfo.getAdddepositGenerationInfo();

            //get���������ڋq�ڍ׏��().���������ڋq�ڍ׏��.�Ǐؔ������ != null and
            //get���������ڋq�ڍ׏��().���������ڋq�ڍ׏��.�Ǐؔ������.�Ǐ؏�� != null�̏ꍇ
            if (l_adddepositGenerationInfo != null
                && l_adddepositGenerationInfo.getAdddepositInfo() != null)
            {
                //get���������ڋq�ڍ׏��().�Ǐؔ������.�Ǐ؏�񂪑�ꐅ���Ǐ؏��̃C���X�^���X�̏ꍇ
                if (l_adddepositGenerationInfo.getAdddepositInfo() instanceof WEB3TPFirstAdddepositInfo)
                {
                    WEB3TPFirstAdddepositInfo l_firstAdddepositInfo =
                        (WEB3TPFirstAdddepositInfo)l_adddepositGenerationInfo.getAdddepositInfo();

                    //�C���X�^���X�����i��ꐅ���Ǐ؏��j
                    //��ꐅ���Ǐ؏��I�u�W�F�N�g�̃C���X�^���X�𐶐�����B
                    WEB3AdminTPFirstAdditionalInfo l_firstAdditionalInfo = new WEB3AdminTPFirstAdditionalInfo();

                    //�v���p�e�B�Z�b�g�i��ꐅ���Ǐ؏��j
                    //get���������ڋq�ڍ׏��()�Ŏ擾����
                    //���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��̊e���ڒl��
                    //����������ꐅ���Ǐ؏��ƃ��X�|���X�f�[�^�ɃZ�b�g����B

                    //�P�j��ꐅ���Ǐ؏��̃Z�b�g
                    //��ꐅ���Ǐ؏��.�o�ߓ��� = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.�o�ߓ���
                    l_firstAdditionalInfo.firstDepositPassDay =
                        l_firstAdddepositInfo.firstDepositPassDay + "";
                    //��ꐅ���Ǐ؏��.�L���o�ߓ��� = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.�L���o�ߓ���
                    l_firstAdditionalInfo.firstDepositPassDayValid =
                        l_firstAdddepositInfo.firstDepositPassDayValid + "";
                    //��ꐅ���Ǐ؏��.������ = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.������
                    l_firstAdditionalInfo.firstDepositOccurredDate =
                        WEB3DateUtility.toDay(l_firstAdddepositInfo.firstDepositOccurredDate);
                    //��ꐅ���Ǐ؏��.�ۏ؋��� = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.�ۏ؋���
                    l_firstAdditionalInfo.firstMarginDepositRate =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstMarginDepositRate);
                    //��ꐅ���Ǐ؏��.�ۏ؋��ێ��� = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.�ۏ؋��ێ���
                    l_firstAdditionalInfo.firstDepositRate =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstDepositRate);
                    //��ꐅ���Ǐ؏��.�Ǐ؋��z = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.�Ǐ؋��z
                    l_firstAdditionalInfo.firstDepositAmount =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstDepositAmount);
                    //��ꐅ���Ǐ؏��.�Ǐ،��ϕK�v�z = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.�Ǐ،��ϕK�v�z
                    l_firstAdditionalInfo.firstSettlement =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstSettlement);
                    //��ꐅ���Ǐ؏��.�ۏ؋����� = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.�ۏ؋�����
                    l_firstAdditionalInfo.firstMarginDepositInDe =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstMarginDepositInDe);
                    //��ꐅ���Ǐ؏��.�ۏ؋�����(�������z) = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.�ۏ؋�����(�������z)
                    l_firstAdditionalInfo.firstMarginDepositInDeExpect =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstMarginDepositInDeExpect);
                    //��ꐅ���Ǐ؏��.���ύό��� = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.���ύό���
                    l_firstAdditionalInfo.firstSettledContract =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstSettledContract);
                    //��ꐅ���Ǐ؏��.���������z = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.���������z
                    l_firstAdditionalInfo.firstUncancelAmt =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstUncancelAmt);
                    //��ꐅ���Ǐ؏��.���������ϕK�v�z = get���������ڋq�ڍ׏��.��ꐅ���Ǐ؏��.���������ϕK�v�z
                    l_firstAdditionalInfo.firstUncancelSettleRequiredAmt =
                        WEB3StringTypeUtility.formatNumber(l_firstAdddepositInfo.firstUncancelSettleRequiredAmt);

                    //�Q�j���X�|���X�f�[�^�̃Z�b�g
                    //���X�|���X�f�[�^.�Ǐؔ����� = 1
                    l_response.additionalGenerationStateDiv =
                        WEB3AdminTPaAdditionalGenerationStateDivDef.FIRST_LEVEL_OCCUR;
                    //���X�|���X�f�[�^.��ꐅ���Ǐ؏�� = ��L�P�j�Ńv���p�e�B�Z�b�g������ꐅ���Ǐ؏��
                    l_response.firstAdditionalInfo = l_firstAdditionalInfo;
                    //���X�|���X�f�[�^.��񐅏��Ǐ؏�� = null
                    l_response.secondAdditionalInfo = null;
                }

                //get���������ڋq�ڍ׏��().�Ǐؔ������.�Ǐ؏�񂪑�񐅏��Ǐ؏��̃C���X�^���X�̏ꍇ
                else if (l_adddepositGenerationInfo.getAdddepositInfo() instanceof WEB3TPSecondAdddepositInfo)
                {
                    WEB3TPSecondAdddepositInfo l_secondAdddepositInfo =
                        (WEB3TPSecondAdddepositInfo)l_adddepositGenerationInfo.getAdddepositInfo();

                    //�C���X�^���X�����i��񐅏��Ǐ؏��j
                    //��񐅏��Ǐ؏��I�u�W�F�N�g�̃C���X�^���X�𐶐�����B
                    WEB3AdminTPSecondAdditionalInfo l_secondAdditionalInfo = new WEB3AdminTPSecondAdditionalInfo();

                    //get���������ڋq�ڍ׏��()�Ŏ擾����
                    //���������ڋq�ڍ׏��.��񐅏��Ǐ؏��̊e���ڒl��
                    //����������񐅏��Ǐ؏��ƃ��X�|���X�f�[�^�ɃZ�b�g����B
                    //�P�j��񐅏��Ǐ؏��̃Z�b�g
                    //��񐅏��Ǐ؏��.����(����2) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.����(����2)
                    String l_strSecondCloseDate2 =
                        WEB3DateUtility.formatDate(
                            l_secondAdddepositInfo.secondCloseDate2,
                            WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    l_secondAdditionalInfo.closeDate2 =
                        WEB3DateUtility.getDate(l_strSecondCloseDate2,
                            WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    //��񐅏��Ǐ؏��.����(����1) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.����(����1)
                    String l_strSecondCloseDate1 =
                        WEB3DateUtility.formatDate(
                            l_secondAdddepositInfo.secondCloseDate1,
                            WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    l_secondAdditionalInfo.closeDate1 =
                        WEB3DateUtility.getDate(l_strSecondCloseDate1,
                        WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    //��񐅏��Ǐ؏��.����(��������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.����(��������)
                    String l_strSecondCloseDateExpect =
                        WEB3DateUtility.formatDate(
                            l_secondAdddepositInfo.secondCloseDateExpect,
                            WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    l_secondAdditionalInfo.closeDateExpect =
                        WEB3DateUtility.getDate(l_strSecondCloseDateExpect,
                        WEB3GentradeTimeDef.DATE_SPLIT_YMD + " " + WEB3GentradeTimeDef.TIME_PARSE_HM);
                    //��񐅏��Ǐ؏��.������(����2) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.������(����2)
                    l_secondAdditionalInfo.secondDepositOccurredDate2 =
                        WEB3DateUtility.toDay(l_secondAdddepositInfo.secondDepositOccurredDate2);
                    //��񐅏��Ǐ؏��.������(����1) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.������(����1)
                    l_secondAdditionalInfo.secondDepositOccurredDate1 =
                        WEB3DateUtility.toDay(l_secondAdddepositInfo.secondDepositOccurredDate1);
                    //��񐅏��Ǐ؏��.������(��������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.������(��������)
                    l_secondAdditionalInfo.secondDepositOccurredDateExpect =
                        WEB3DateUtility.toDay(l_secondAdddepositInfo.secondDepositOccurredDateExpect);
                    //��񐅏��Ǐ؏��.�ۏ؋��ێ��� = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�ۏ؋��ێ���
                    l_secondAdditionalInfo.secondDepositRate =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDepositRate);
                    //��񐅏��Ǐ؏��.�ۏ؋��߂��ێ��� = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�ۏ؋��߂��ێ���
                    l_secondAdditionalInfo.secondDepositBackRate =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDepositBackRate);
                    //��񐅏��Ǐ؏��.�ۏ؋���(����2) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�ۏ؋���(����2)
                    l_secondAdditionalInfo.secondMarginDepositRate2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositRate2);
                    //��񐅏��Ǐ؏��.�ۏ؋���(����1) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�ۏ؋���(����1)
                    l_secondAdditionalInfo.secondMarginDepositRate1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositRate1);
                    //��񐅏��Ǐ؏��.�ۏ؋���(��������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�ۏ؋���(��������)
                    l_secondAdditionalInfo.secondMarginDepositRateExpect =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositRateExpect);
                    //��񐅏��Ǐ؏��.�Ǐ؋��z(������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�Ǐ؋��z(������)
                    l_secondAdditionalInfo.secondDepositNonPay =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDepositNonPay);
                    //��񐅏��Ǐ؏��.�Ǐ؋��z(����2) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�Ǐ؋��z(����2)
                    l_secondAdditionalInfo.secondDeposit2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDeposit2);
                    //��񐅏��Ǐ؏��.�Ǐ؋��z(����1) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�Ǐ؋��z(����1)
                    l_secondAdditionalInfo.secondDeposit1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondDeposit1);
                    //��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(������)
                    l_secondAdditionalInfo.secondSettlementNonPay =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondSettlementNonPay);
                    //��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����2) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����2)
                    l_secondAdditionalInfo.secondSettlement2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondSettlement2);
                    //��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����1) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�Ǐ،��ϕK�v�z(����1)
                    l_secondAdditionalInfo.secondSettlement1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondSettlement1);
                    //��񐅏��Ǐ؏��.�ۏ؋����� = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�ۏ؋�����
                    l_secondAdditionalInfo.secondMarginDepositInDe =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositInDe);
                    //��񐅏��Ǐ؏��.�ۏ؋�����(�������z) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.�ۏ؋�����(�������z)
                    l_secondAdditionalInfo.secondMarginDepositInDeExpect =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondMarginDepositInDeExpect);
                    //��񐅏��Ǐ؏��.���ύό��� = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���ύό���
                    l_secondAdditionalInfo.secondSettledContract =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondSettledContract);
                    //��񐅏��Ǐ؏��.���������z(������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���������z(������)
                    l_secondAdditionalInfo.secondUncancelAmtNonPay =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelAmtNonPay);
                    //��񐅏��Ǐ؏��.���������z(����2) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���������z(����2)
                    l_secondAdditionalInfo.secondUncancelAmt2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelAmt2);
                    //��񐅏��Ǐ؏��.���������z(����1) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���������z(����1)
                    l_secondAdditionalInfo.secondUncancelAmt1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelAmt1);
                    //��񐅏��Ǐ؏��.���������z(��������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���������z(��������)
                    l_secondAdditionalInfo.secondUncancelAmtExpect =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelAmtExpect);
                    //��񐅏��Ǐ؏��.���������ϕK�v�z(������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���������ϕK�v�z(������)
                    l_secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay =
                        WEB3StringTypeUtility.formatNumber(
                            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay);
                    //��񐅏��Ǐ؏��.���������ϕK�v�z(����2) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���������ϕK�v�z(����2)
                    l_secondAdditionalInfo.secondUncancelSettleRequiredAmt2 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2);
                    //��񐅏��Ǐ؏��.���������ϕK�v�z(����1) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���������ϕK�v�z(����1)
                    l_secondAdditionalInfo.secondUncancelSettleRequiredAmt1 =
                        WEB3StringTypeUtility.formatNumber(l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1);
                    //��񐅏��Ǐ؏��.���������ϕK�v�z(��������) = get���������ڋq�ڍ׏��.��񐅏��Ǐ؏��.���������ϕK�v�z(��������)
                    l_secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect =
                        WEB3StringTypeUtility.formatNumber(
                            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect);

                    //�Q�j���X�|���X�f�[�^�̃Z�b�g
                    //���X�|���X�f�[�^.�Ǐؔ����� = 2
                    l_response.additionalGenerationStateDiv =
                        WEB3AdminTPaAdditionalGenerationStateDivDef.SECOND_LEVEL_OCCUR;
                    //���X�|���X�f�[�^.��ꐅ���Ǐ؏�� = null
                    l_response.firstAdditionalInfo = null;
                    //���X�|���X�f�[�^.��񐅏��Ǐ؏�� = ��L�P�j�Ńv���p�e�B�Z�b�g������񐅏��Ǐ؏��
                    l_response.secondAdditionalInfo = l_secondAdditionalInfo;
                }
            }
        }

        //���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�i�s������Ǐ؂Ɋւ��郌�X�|���X�̍��ڂ͏����j
        //���X�|���X�f�[�^.�v�Z�� = get���������ڋq�ڍ׏��.�v�Z��
        l_response.calcDate = WEB3DateUtility.toDay(l_paymentRequisitionAccountDetailInfo.getCalcDate());
        //���X�|���X�f�[�^.���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
        l_response.branchCode = l_request.branchCode;
        //���X�|���X�f�[�^.�ڋq�R�[�h = ���N�G�X�g�f�[�^.�ڋq�R�[�h
        l_response.accountCode = l_request.accountCode;
        //���X�|���X�f�[�^.�ڋq�� = �ڋq.���O�i�c���j
        l_response.accountName = l_mainAccount.getMainAccountRow().getFamilyName();
        //���X�|���X�f�[�^.���҃R�[�h = �ڋq.���҃R�[�h(SONAR)
        l_response.traderCode = l_mainAccount.getMainAccountRow().getSonarTraderCode();
        //���X�|���X�f�[�^.���� = get���������ڋq�ڍ׏��.�ڋq����
        l_response.attribute = l_paymentRequisitionAccountDetailInfo.getAccountAttribute();
        //���X�|���X�f�[�^.�����~�敪 = get�ڋq�]�͏���Params.�����~�敪
        l_response.tradeStopDiv = l_tradingpowerCalcConditionParams.getTradingStop();
        //���X�|���X�f�[�^.�Ǐؖ������敪 = get�ڋq�]�͏���Params.�Ǐؖ������敪
        l_response.additionalDepositStop = l_tradingpowerCalcConditionParams.getAdditionalDepositStop();
        //���X�|���X�f�[�^.�M�p�V�K���]�͋敪 = get�ڋq�]�͏���Params.�M�p�V�K���]�͋敪
        l_response.marginOpenPositionStop = l_tradingpowerCalcConditionParams.getMarginOpenPositionStop();
        //���X�|���X�f�[�^.�敨OP�V�K���]�͋敪 = get�ڋq�]�͏���Params.�敨OP�V�K���]�͋敪
        l_response.ifoOpenPositionStop = l_tradingpowerCalcConditionParams.getIfoOpenPositionStop();
        //���X�|���X�f�[�^.�o���]�͋敪 = get�ڋq�]�͏���Params.�o���]�͋敪
        l_response.paymentStop = l_tradingpowerCalcConditionParams.getPaymentStop();
        //���X�|���X�f�[�^.���̑����i���t�]�͋敪 = get�ڋq�]�͏���Params.���̑����i���t�]�͋敪
        l_response.otherTradingStop = l_tradingpowerCalcConditionParams.getOtherTradingStop();
        //���X�|���X�f�[�^.���֋� = get���������ڋq�ڍ׏��.�s�����������.���֋�
        l_response.debitAmount =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().debitAmount);
        //���X�|���X�f�[�^.���ʗ��֋� = get���������ڋq�ڍ׏��.�s�����������.���ʗ��֋�
        l_response.specialDebitAmount =
            WEB3StringTypeUtility.formatNumber(
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo().specialDebitAmount);
        //���X�|���X�f�[�^.�ۏ؋������U�֌㔻��t���O = get���������ڋq�ڍ׏��.�ۏ؋������U�֌㔻��t���O
        l_response.autoTransferAfterJudgeFlag =
            l_paymentRequisitionAccountDetailInfo.isDepositAutoTransferDivFlag();

        //���X�|���X�f�[�^.�s����������
        //�E�s�����������.�K�v�����z(T+0)�`(T+5)���S��0�̏ꍇ�A
        if ("0".equals(l_shortfallGenerationInfo.requiredPayAmt0)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt1)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt2)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt3)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt4)
            && "0".equals(l_shortfallGenerationInfo.requiredPayAmt5))
        {
            //0(�s����������)���Z�b�g����B
            l_response.shortfallGenerationStateDiv =
                WEB3AdminTPShortfallGenerationStateDivDef.SHORTFALL_NOT_OCCUR;
        }
        //�Eget���������ڋq�ڍ׏��().�ڋq���� = 0(����/�O����)�@@or 1(����/�a��،��]����) �̏ꍇ�A
        else if (WEB3AccountAttributeDef.EQUITY_NOT_ASSET_EVAL.equals(
            l_paymentRequisitionAccountDetailInfo.getAccountAttribute())
            || WEB3AccountAttributeDef.EQUITY_ASSET_EVAL.equals(
            l_paymentRequisitionAccountDetailInfo.getAccountAttribute()))
        {
            //1�i�s��������<�����ڋq>�j���Z�b�g����B
            l_response.shortfallGenerationStateDiv =
                WEB3AdminTPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_EQUITY_CUST;
        }
        //�Eget���������ڋq�ڍ׏��().�ڋq���� = 2(�M�p) �̏ꍇ�A
        else if (WEB3AccountAttributeDef.MARGIN.equals(
            l_paymentRequisitionAccountDetailInfo.getAccountAttribute()))
        {
            //2�i�s��������<�M�p�ڋq>�j���Z�b�g����B
            l_response.shortfallGenerationStateDiv =
                WEB3AdminTPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_MARGIN_CUST;
        }

        //���X�|���X�f�[�^.�s����������� = ��L�P�j�Ńv���p�e�B�Z�b�g�����s�������������Z�b�g����B
        l_response.shortfallGenerationInfo = l_shortfallGenerationInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������ڋq�����_�E�����[�h)<BR>
     * (get���������ڋq�����_�E�����[�h)<BR>
     * <BR>
     * �V�[�P���X�}�uget���������ڋq�����_�E�����[�h�v�Q��<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminTPPaymentRequisitionDownLoadResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C9D35C0170
     */
    private WEB3AdminTPPaymentRequisitionDownLoadResponse getPaymentRequisitionCustomerSearchDownLoad(
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchDownLoad(WEB3AdminTPPaymentRequisitionDownLoadRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�Ǘ��҂̌����`�F�b�N���s���B
        //�Ǘ��Ҍ����`�F�b�N
        //�E�J�e�S���R�[�h="A0201"�i�]�͊Ǘ��ҁj
        //�E�X�V�t���O=false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.TRADING_POWER, false);

        //get���������ڋq���Params�ꗗ(���������ڋq�������ʃ��N�G�X�g)
        //���������Ǘ��e�[�u���̃f�[�^���擾����B
        //[����]
        //�E���N�G�X�g�f�[�^
        List l_lisPaymentRequisitMngParams = getPaymentRequisitMngParamsList(l_request);

        //���������ڋq����CSV( )
        //CSV�f�[�^���f���𐶐�����B
        WEB3AdminTPPaymentRequisitionCustomerSearchCSV l_paymentRequisitionCustomerSearchCSV =
            new WEB3AdminTPPaymentRequisitionCustomerSearchCSV();

        //get���������ڋq���Params�ꗗ�Ŏ擾�������R�[�h������LOOP
        int l_intLength = l_lisPaymentRequisitMngParams.size();
        for (int i = 0; i < l_intLength; i++)
        {
            PaymentRequisitMngRow l_paymentRequisitMngRow =
                (PaymentRequisitMngRow)l_lisPaymentRequisitMngParams.get(i);

            //add���׍s( )
            int l_intLineNumber = l_paymentRequisitionCustomerSearchCSV.addRow();

            //set���X�R�[�h(int, String)
            l_paymentRequisitionCustomerSearchCSV.setBranchCode(
                l_intLineNumber,
                l_paymentRequisitMngRow.getBranchCode());

            //set�ڋq�R�[�h(int, String)
            l_paymentRequisitionCustomerSearchCSV.setAccountCode(
                l_intLineNumber,
                l_paymentRequisitMngRow.getAccountCode());

            //set�ڋq��(int, String)
            l_paymentRequisitionCustomerSearchCSV.setFamilyName(
                l_intLineNumber,
                l_paymentRequisitMngRow.getFamilyName());

            //set���҃R�[�h(int, String)
            l_paymentRequisitionCustomerSearchCSV.setTraderCode(
                l_intLineNumber,
                l_paymentRequisitMngRow.getTraderCode());

            //set����(int, String)
            l_paymentRequisitionCustomerSearchCSV.setAttribute(
                l_intLineNumber,
                l_paymentRequisitMngRow.getAccountAttribute());

            //set���֋�(int, String)
            l_paymentRequisitionCustomerSearchCSV.setDebitAmount(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getDebitAmount()));

            //set���ʗ��֋�(int, String)
            l_paymentRequisitionCustomerSearchCSV.setSpecialDebitAmount(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getSpecialDebitAmount()));

            //set�K�v�����z(int, String)
            l_paymentRequisitionCustomerSearchCSV.setRequiredPayAmt(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getLackAccountBalance()));

            //set��ꐅ���Ǐ؋��z(int, String)
            l_paymentRequisitionCustomerSearchCSV.setFirstDepositAmount(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getFirstDepositAmount()));

            //set��ꐅ���Ǐ،o�ߓ���(int, String)
            String l_strFirstDepositPassDay = null;
            if (!l_paymentRequisitMngRow.getFirstDepositPassDayIsNull())
            {
                l_strFirstDepositPassDay =
                    WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getFirstDepositPassDay());
            }
            l_paymentRequisitionCustomerSearchCSV.setFirstDepositPassDay(
                l_intLineNumber,
                l_strFirstDepositPassDay);

            //set��񐅏��Ǐؐ����i1�j(int, String)
            l_paymentRequisitionCustomerSearchCSV.setSecondDeposit1(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getSecondDeposit1()));

            //set��񐅏��Ǐؐ����i2�j(int, String)
            l_paymentRequisitionCustomerSearchCSV.setSecondDeposit2(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getSecondDeposit2()));

            //set��񐅏��Ǐؖ�����(int, String)
            l_paymentRequisitionCustomerSearchCSV.setSecondDepositNonPay(
                l_intLineNumber,
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngRow.getSecondDepositNonPay()));
        }

        //getCSV�t�@@�C���s( )
        //CSV�t�@@�C���s���擾����B
        String[] l_strCsvFileLines = l_paymentRequisitionCustomerSearchCSV.getCsvFileLines();

        //createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminTPPaymentRequisitionDownLoadResponse l_response =
            (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //�ȉ��̒ʂ�A�v���p�e�B�Z�b�g���s���B
        //�@@�|���X�|���X�f�[�^.�_�E�����[�h�t�@@�C�� = getCSV�t�@@�C���̖߂�l
        l_response.downloadFile = l_strCsvFileLines;
        //�@@�|���X�|���X�f�[�^.���ݓ��� = TradingSystem.getSystemTimestamp()�̖߂�l
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get���������ڋq���Params�ꗗ)<BR>
     * ���������Ǘ��e�[�u�����ȉ��̏����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �P�j���������Ǘ��e�[�u��.�v�Z�� = �O�c�Ɠ��i�c�Ɠ��v�Z���[�e�B���e�B�N���X�𗘗p���Ď擾�j<BR>
     * <BR>
     * �Q�j���������Ǘ��e�[�u��.��ЃR�[�h = �Ǘ��҃N���X.get�،����<BR>
     * <BR>
     * �R�j���������Ǘ��e�[�u��.���X�R�[�h = ���N�G�X�g.���X�R�[�h<BR>
     * <BR>
     * �S�j���N�G�X�g.�ڋq�R�[�h��"null�ȊO"�̏ꍇ<BR>
     * �@@���������Ǘ��e�[�u��.�ڋq�R�[�h like ���N�G�X�g.�ڋq�R�[�h<BR>
     * �@@��Like�����Łu���N�G�X�g.�ڋq�R�[�h�v�̌�̕��������C���h�J�[�h�Ƃ���B<BR>
     * <BR>
     * �T�j���N�G�X�g.���҃R�[�h��"null�ȊO"�̏ꍇ<BR>
     * �@@���������Ǘ��e�[�u��.���҃R�[�h = ���N�G�X�g.���҃R�[�h<BR>
     * <BR>
     * �U�j�ڋq�����Ɋւ�������ݒ�<BR>
     * �@@�U�|�P�j���N�G�X�g.�ڋq������"0"�i�����j�̏ꍇ<BR>
     * �@@�@@�@@�@@���������Ǘ��e�[�u��.�ڋq���� = "0"(����/�O����) or "1"(����/�a��،��]����)<BR>
     * �@@�U�|�Q�j���N�G�X�g.�ڋq������"1"�i�M�p�j�̏ꍇ<BR>
     * �@@�@@�@@�@@���������Ǘ��e�[�u��.�ڋq���� = "2"(�M�p)<BR>
     * <BR>
     * �V�j�������R ����ѓ����Ɋւ�������ݒ�<BR>
     * �@@�V�|�P�j���N�G�X�g.�������R��"1"�i���֋�/���ʗ��֋��j�̏ꍇ<BR>
     * �@@�@@�@@�@@���������Ǘ��e�[�u��.���֋� > 0 or ���������Ǘ��e�[�u��.���ʗ��֋� > 0<BR>
     * �@@�V�|�Q�j���N�G�X�g.�������R��"2"�i�s�����i�����j�j�̏ꍇ<BR>
     * �@@�@@�@@�@@���������Ǘ��e�[�u��.�a����s���z(T+0) > 0<BR>
     * �@@�V�|�R�j���N�G�X�g.�������R��"3"�i��ꐅ���Ǐ؁j�̏ꍇ<BR>
     * �@@�@@�@@a)���N�G�X�g.������"8"(��ꐅ���w��ő����)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��ꐅ���Ǐ،o�ߓ��� >= ���N�G�X�g.����<BR>
     * �@@�@@�@@b)���N�G�X�g.������"0"(�E�v�������ׂ�)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��ꐅ���Ǐ،o�ߓ��� > ���N�G�X�g.����<BR>
     * �@@�@@�@@c)���N�G�X�g.��������L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��ꐅ���Ǐ،o�ߓ��� = ���N�G�X�g.����<BR>
     * �@@�V�|�S�j���N�G�X�g.�������R��"4"�i��񐅏��Ǐ؁j�̏ꍇ<BR>
     * �@@�@@�@@a)���N�G�X�g.������"3"(��񐅏��w��ő����)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؖ����� > 0<BR>
     * �@@�@@�@@b)���N�G�X�g.������"2"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(2) > 0<BR>
     * �@@�@@�@@c)���N�G�X�g.������"1"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(1) > 0<BR>
     * �@@�@@�@@d)���N�G�X�g.������"0"(�E�v�������ׂ�)�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؖ����� > 0 or<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(2) > 0 or<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(1) > 0<BR>
     * �@@�V�|�T�j���N�G�X�g.�������R"5"�i�w��Ȃ��j���I�����ꂽ�ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.���֋� > 0 or<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.���ʗ��֋� > 0 or<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.�a����s���z(T+0) > 0 or<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��ꐅ���Ǐ،o�ߓ��� > 0 or<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؖ����� > 0 or<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(2) > 0 or<BR>
     * �@@�@@�@@�@@�@@�@@���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(1) > 0<BR>
     * <BR>
     * �W�j���������Ǘ��e�[�u������L�P�j�`�V�j�̏��������ƂɌ�������B<BR>
     * �@@�e���������́AAND�ŘA������B<BR>
     * �@@�\�[�g���͌ڋq�R�[�h�̏����Ƃ���B<BR>
     * <BR>
     * �X�j�擾�������R�[�h��List�Ɋi�[���A�ԋp����B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 48D0AD0D022F
     */
    private List getPaymentRequisitMngParamsList(
        WEB3AdminTPPaymentRequisitionCommonRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitMngParamsList(WEB3AdminTPPaymentRequisitionCommonRequest)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbQueryCond = new StringBuffer();
        List l_lisArrayList = new ArrayList();

        //�P�j���������Ǘ��e�[�u��.�v�Z�� = �O�c�Ɠ��i�c�Ɠ��v�Z���[�e�B���e�B�N���X�𗘗p���Ď擾�j
        l_sbQueryCond.append("calc_date = ? ");
        Timestamp l_tsSysTimestamp = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsSysTimestamp);
        l_lisArrayList.add(l_genBizDate.roll(-1));

        //�Q�j���������Ǘ��e�[�u��.��ЃR�[�h = �Ǘ��҃N���X.get�،����
        l_sbQueryCond.append("and institution_code = ? ");
        l_lisArrayList.add(WEB3Administrator.getInstanceFromLoginInfo().getInstitutionCode());

        //�R�j���������Ǘ��e�[�u��.���X�R�[�h = ���N�G�X�g.���X�R�[�h
        l_sbQueryCond.append("and branch_code = ? ");
        l_lisArrayList.add(l_request.branchCode);

        //�S�j���N�G�X�g.�ڋq�R�[�h��"null�ȊO"�̏ꍇ
        if (l_request.accountCode != null)
        {
            //���������Ǘ��e�[�u��.�ڋq�R�[�h like ���N�G�X�g.�ڋq�R�[�h
            //��Like�����Łu���N�G�X�g.�ڋq�R�[�h�v�̌�̕��������C���h�J�[�h�Ƃ���B
            l_sbQueryCond.append("and account_code like ? || '%' ");
            l_lisArrayList.add(l_request.accountCode);
        }

        //�T�j���N�G�X�g.���҃R�[�h��"null�ȊO"�̏ꍇ
        if (l_request.traderCode != null)
        {
            //���������Ǘ��e�[�u��.���҃R�[�h = ���N�G�X�g.���҃R�[�h
            l_sbQueryCond.append("and trader_code = ? ");
            l_lisArrayList.add(l_request.traderCode);
        }

        //�U�j�ڋq�����Ɋւ�������ݒ�
        //�@@�U�|�P�j���N�G�X�g.�ڋq������"0"�i�����j�̏ꍇ
        if (WEB3AdminTPCustomerAttributeDef.EQUITY_CUST.equals(l_request.customerAttribute))
        {
            //���������Ǘ��e�[�u��.�ڋq���� = "0"(����/�O����) or "1"(����/�a��،��]����)
            l_sbQueryCond.append("and account_attribute in (0, 1) ");
        }
        //�@@�U�|�Q�j���N�G�X�g.�ڋq������"1"�i�M�p�j�̏ꍇ
        else if (WEB3AdminTPCustomerAttributeDef.MARGIN_CUST.equals(l_request.customerAttribute))
        {
            //���������Ǘ��e�[�u��.�ڋq���� = "2"(�M�p)
            l_sbQueryCond.append("and account_attribute = 2 ");
        }

        //�V�j�������R ����ѓ����Ɋւ�������ݒ�

        //�@@�V�|�P�j���N�G�X�g.�������R��"1"�i���֋�/���ʗ��֋��j�̏ꍇ
        if (WEB3AdminTPClaimReasonDef.DEBIT_AMOUNT_SPECIAL.equals(l_request.claimReason))
        {
            //���������Ǘ��e�[�u��.���֋� > 0 or ���������Ǘ��e�[�u��.���ʗ��֋� > 0
            l_sbQueryCond.append("and (debit_amount > 0 or special_debit_amount > 0) ");
        }

        //�@@�V�|�Q�j���N�G�X�g.�������R��"2"�i�s�����i�����j�j�̏ꍇ
        else if (WEB3AdminTPClaimReasonDef.SHORT_FALL_GENERATION_TODAY.equals(l_request.claimReason))
        {
            //���������Ǘ��e�[�u��.�a����s���z(T+0) > 0
            l_sbQueryCond.append("and lack_account_balance > 0 ");
        }

        //�V�|�R�j���N�G�X�g.�������R��"3"�i��ꐅ���Ǐ؁j�̏ꍇ
        else if (WEB3AdminTPClaimReasonDef.BREAK30_ADDITIONAL.equals(l_request.claimReason))
        {
            //a)���N�G�X�g.������"8"(��ꐅ���w��ő����)�̏ꍇ
            if (WEB3AdminTPDaysDef.DAYS_8.equals(l_request.days))
            {
                //���������Ǘ��e�[�u��.��ꐅ���Ǐ،o�ߓ��� >= ���N�G�X�g.����
                l_sbQueryCond.append("and first_deposit_pass_day >= ? ");
                l_lisArrayList.add(l_request.days);
            }
            //b)���N�G�X�g.������"0"(�E�v�������ׂ�)�̏ꍇ
            else if (WEB3AdminTPDaysDef.DAYS_0.equals(l_request.days))
            {
                //���������Ǘ��e�[�u��.��ꐅ���Ǐ،o�ߓ��� > ���N�G�X�g.����
                l_sbQueryCond.append("and first_deposit_pass_day > ? ");
                l_lisArrayList.add(l_request.days);
            }
            //c)���N�G�X�g.��������L�ȊO�̏ꍇ
            else
            {
                //���������Ǘ��e�[�u��.��ꐅ���Ǐ،o�ߓ��� = ���N�G�X�g.����
                l_sbQueryCond.append("and first_deposit_pass_day = ? ");
                l_lisArrayList.add(l_request.days);
            }
        }

        //�V�|�S�j���N�G�X�g.�������R��"4"�i��񐅏��Ǐ؁j�̏ꍇ
        else if (WEB3AdminTPClaimReasonDef.BREAK20_ADDITIONAL.equals(l_request.claimReason))
        {
            //a)���N�G�X�g.������"3"(��񐅏��w��ő����)�̏ꍇ
            if (WEB3AdminTPDaysDef.DAYS_3.equals(l_request.days))
            {
                //���������Ǘ��e�[�u��.��񐅏��Ǐؖ����� > 0
                l_sbQueryCond.append("and second_deposit_non_pay > 0 ");
            }
            //b)���N�G�X�g.������"2"�̏ꍇ
            else if (WEB3AdminTPDaysDef.DAYS_2.equals(l_request.days))
            {
                //���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(2) > 0
                l_sbQueryCond.append("and second_deposit_2 > 0 ");
            }
            //c)���N�G�X�g.������"1"�̏ꍇ
            else if (WEB3AdminTPDaysDef.DAYS_1.equals(l_request.days))
            {
                //���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(1) > 0
                l_sbQueryCond.append("and second_deposit_1 > 0 ");
            }
            //d)���N�G�X�g.������"0"(�E�v�������ׂ�)�̏ꍇ
            else if (WEB3AdminTPDaysDef.DAYS_0.equals(l_request.days))
            {
                //���������Ǘ��e�[�u��.��񐅏��Ǐؖ����� > 0 or
                //���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(2) > 0 or
                //���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(1) > 0
                l_sbQueryCond.append("and (second_deposit_non_pay > 0 or ");
                l_sbQueryCond.append("second_deposit_2 > 0 or ");
                l_sbQueryCond.append("second_deposit_1 > 0) ");
            }
        }

        //�V�|�T�j���N�G�X�g.�������R"5"�i�w��Ȃ��j���I�����ꂽ�ꍇ
        else if (WEB3AdminTPClaimReasonDef.DEFAULT.equals(l_request.claimReason))
        {
            //���������Ǘ��e�[�u��.���֋� > 0 or
            l_sbQueryCond.append("and (debit_amount > 0 or ");
            //���������Ǘ��e�[�u��.���ʗ��֋� > 0 or
            l_sbQueryCond.append("special_debit_amount > 0 or ");
            //���������Ǘ��e�[�u��.�a����s���z(T+0) > 0 or
            l_sbQueryCond.append("lack_account_balance > 0 or ");
            //���������Ǘ��e�[�u��.��ꐅ���Ǐ،o�ߓ��� > 0 or
            l_sbQueryCond.append("first_deposit_pass_day > 0 or ");
            //���������Ǘ��e�[�u��.��񐅏��Ǐؖ����� > 0 or
            l_sbQueryCond.append("second_deposit_non_pay > 0 or ");
            //���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(2) > 0 or
            l_sbQueryCond.append("second_deposit_2 > 0 or ");
            //���������Ǘ��e�[�u��.��񐅏��Ǐؐ���(1) > 0
            l_sbQueryCond.append("second_deposit_1 > 0) ");
        }

        //�W�j���������Ǘ��e�[�u������L�P�j�`�V�j�̏��������ƂɌ�������B
        //�@@�e���������́AAND�ŘA������B
        //�@@�\�[�g���͌ڋq�R�[�h�̏����Ƃ���B
        String l_strOrderBy = " account_code ";

        Object[] l_objValues = new Object[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_objValues);

        List l_lisPaymentRequisitMngParams = null;

        try
        {
            l_lisPaymentRequisitMngParams =
                Processors.getDefaultProcessor().doFindAllQuery(
                    PaymentRequisitMngParams.TYPE,
                    l_sbQueryCond.toString(),
                    l_strOrderBy,
                    null,
                    l_objValues);
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

        //�X�j�擾�������R�[�h��List�Ɋi�[���A�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisPaymentRequisitMngParams;
    }

    /**
     * (create���������ڋq�����ꗗ���j�b�g)<BR>
     * get���������ڋq���Params�ꗗ�Ŏ擾��������<BR>
     * ���������ڋq�����ꗗ���j�b�g�ɃZ�b�g����B<BR>
     * <BR>
     * �P�j����.���������ڋq���Params�̔z��̐������쐬����<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g�̔z��ɁA�ȉ��̒ʂ�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.���X�R�[�h = ���������Ǘ�Params.���X�R�[�h<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.�ڋq�R�[�h = ���������Ǘ�Params.�ڋq�R�[�h<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.�ڋq�� = ���������Ǘ�Params.�ڋq��<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.���҃R�[�h = ���������Ǘ�Params.���҃R�[�h<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.���� = ���������Ǘ�Params.�ڋq����<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.���֋� = ���������Ǘ�Params.���֋�<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.���ʗ��֋� = ���������Ǘ�Params.���ʗ��֋�<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.�K�v�����z =<BR>
     * �@@�@@�@@�@@���������Ǘ�Params.�a����s���z(T+0)<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.��ꐅ���Ǐ؋��z =<BR>
     * �@@�@@�@@�@@���������Ǘ�Params.��ꐅ���Ǐ؋��z<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.��ꐅ���Ǐ،o�ߓ��� =<BR>
     * �@@�@@�@@�@@���������Ǘ�Params.��ꐅ���Ǐ،o�ߓ���<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.��񐅏��Ǐؐ���(1) =<BR>
     * �@@�@@�@@�@@���������Ǘ�Params.��񐅏��Ǐؐ���(1)<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.��񐅏��Ǐؐ���(2) =<BR>
     * �@@�@@�@@�@@���������Ǘ�Params.��񐅏��Ǐؐ���(2)<BR>
     * �@@�@@���������ڋq�����ꗗ���j�b�g.��񐅏��Ǐؖ����� =<BR>
     * �@@�@@�@@�@@���������Ǘ�Params.��񐅏��Ǐؖ�����<BR>
     * <BR>
     * �R�j�����������������ڋq�����ꗗ���j�b�g�̔z���ԋp����B<BR>
     * @@param l_paymentRequisitMngParams - (���������Ǘ�Params)<BR>
     * @@return WEB3AdminTPPaymentRequisitionListUnit[]
     * @@roseuid 48DB5C52023F
     */
    private WEB3AdminTPPaymentRequisitionListUnit[] createPaymentRequisitionListUnit(
        PaymentRequisitMngParams[] l_paymentRequisitMngParams)
    {
        final String STR_METHOD_NAME =
            "createPaymentRequisitionListUnit(PaymentRequisitMngParams[])";
        log.entering(STR_METHOD_NAME);

        //�P�j����.���������ڋq���Params�̔z��̐������쐬����
        //�@@�@@���������ڋq�����ꗗ���j�b�g�̔z��ɁA�ȉ��̒ʂ�l���Z�b�g����B
        int l_intLength = l_paymentRequisitMngParams.length;

        WEB3AdminTPPaymentRequisitionListUnit[] l_paymentRequisitionListUnits =
            new WEB3AdminTPPaymentRequisitionListUnit[l_intLength];

        for (int i = 0; i < l_intLength; i++)
        {
            l_paymentRequisitionListUnits[i] = new WEB3AdminTPPaymentRequisitionListUnit();

            //���������ڋq�����ꗗ���j�b�g.���X�R�[�h = ���������Ǘ�Params.���X�R�[�h
            l_paymentRequisitionListUnits[i].branchCode =
                l_paymentRequisitMngParams[i].getBranchCode();

            //���������ڋq�����ꗗ���j�b�g.�ڋq�R�[�h = ���������Ǘ�Params.�ڋq�R�[�h
            l_paymentRequisitionListUnits[i].accountCode =
                l_paymentRequisitMngParams[i].getAccountCode();

            //���������ڋq�����ꗗ���j�b�g.�ڋq�� = ���������Ǘ�Params.�ڋq��
            l_paymentRequisitionListUnits[i].accountName =
                l_paymentRequisitMngParams[i].getFamilyName();

            //���������ڋq�����ꗗ���j�b�g.���҃R�[�h = ���������Ǘ�Params.���҃R�[�h
            l_paymentRequisitionListUnits[i].traderCode =
                l_paymentRequisitMngParams[i].getTraderCode();

            //���������ڋq�����ꗗ���j�b�g.���� = ���������Ǘ�Params.�ڋq����
            l_paymentRequisitionListUnits[i].attribute =
                l_paymentRequisitMngParams[i].getAccountAttribute();

            //���������ڋq�����ꗗ���j�b�g.���֋� = ���������Ǘ�Params.���֋�
            l_paymentRequisitionListUnits[i].debitAmount =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getDebitAmount());

            //���������ڋq�����ꗗ���j�b�g.���ʗ��֋� = ���������Ǘ�Params.���ʗ��֋�
            l_paymentRequisitionListUnits[i].specialDebitAmount =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getSpecialDebitAmount());

            //���������ڋq�����ꗗ���j�b�g.�K�v�����z = ���������Ǘ�Params.�a����s���z(T+0)
            l_paymentRequisitionListUnits[i].requiredPayAmt =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getLackAccountBalance());

            //���������ڋq�����ꗗ���j�b�g.��ꐅ���Ǐ؋��z = ���������Ǘ�Params.��ꐅ���Ǐ؋��z
            l_paymentRequisitionListUnits[i].firstDepositAmount =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getFirstDepositAmount());

            //���������ڋq�����ꗗ���j�b�g.��ꐅ���Ǐ،o�ߓ��� = ���������Ǘ�Params.��ꐅ���Ǐ،o�ߓ���
            String l_strFirstDepositPassDay = null;
            if (!l_paymentRequisitMngParams[i].getFirstDepositPassDayIsNull())
            {
                l_strFirstDepositPassDay =
                    WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getFirstDepositPassDay());
            }
            l_paymentRequisitionListUnits[i].firstDepositPassDay = l_strFirstDepositPassDay;

            //���������ڋq�����ꗗ���j�b�g.��񐅏��Ǐؐ���(1) = ���������Ǘ�Params.��񐅏��Ǐؐ���(1)
            l_paymentRequisitionListUnits[i].secondDeposit1 =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getSecondDeposit1());

            //���������ڋq�����ꗗ���j�b�g.��񐅏��Ǐؐ���(2) = ���������Ǘ�Params.��񐅏��Ǐؐ���(2)
            l_paymentRequisitionListUnits[i].secondDeposit2 =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getSecondDeposit2());

            //���������ڋq�����ꗗ���j�b�g.��񐅏��Ǐؖ����� = ���������Ǘ�Params.��񐅏��Ǐؖ�����
            l_paymentRequisitionListUnits[i].secondDepositNonPay =
                WEB3StringTypeUtility.formatNumber(l_paymentRequisitMngParams[i].getSecondDepositNonPay());
        }

        //�R�j�����������������ڋq�����ꗗ���j�b�g�̔z���ԋp����B
        return l_paymentRequisitionListUnits;
    }

    /**
     * (get�ڋq�]�͏���Params)<BR>
     * �ڋq�]�͏����e�[�u�����A�ȉ��̏����Ń��R�[�h�����������ʂ�Ԃ��B<BR>
     * <BR>
     * �����F<BR>
     * ����ID = �����̃A�J�E���gID<BR>
     * @@param l_accountId - (�A�J�E���gID)<BR>
     * @@return TradingpowerCalcConditionParams
     * @@throws WEB3BaseException
     * @@roseuid 48E0671C0145
     */
    private TradingpowerCalcConditionParams getTradingPowerCalcConditionParams(
        Long l_accountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradingPowerCalcConditionParams(Long)";
        log.entering(STR_METHOD_NAME);

        TradingpowerCalcConditionRow l_tradingpowerCalcConditionRow = null;

        try
        {
            l_tradingpowerCalcConditionRow =
                TradingpowerCalcConditionDao.findRowByAccountId(l_accountId.longValue());
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
        return new TradingpowerCalcConditionParams(l_tradingpowerCalcConditionRow);
    }

}
@
