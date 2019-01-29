head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�XImpl(WEB3AdminIfoDepShortageReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.005,No.009,No.010
Revision History : 2009/03/09 ����(���u) ���f��No.012
Revision History : 2009/03/16 �����F(���u) ���f��No.015,���f��No.016
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.ifoadmin.WEB3AdminIfoDataManager;
import webbroker3.ifoadmin.define.WEB3AdminIfoCurNonPayAmtDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoSortKeyItemNameDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoUnCancelDivDef;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepAccountCodeComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepBranchCodeComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepClaimAmountComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepCurNonPayAmtComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepCurIfoDepositNecessaryAmtComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepContractExistFlagComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepOrderExistFlagComparator;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceResponse;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageInfo;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageSortKey;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageReferenceService;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcCondition;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageReferenceServiceImpl implements WEB3AdminIfoDepShortageReferenceService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageReferenceServiceImpl.class);

    /**
     * @@roseuid 49A748560271
     */
    public WEB3AdminIfoDepShortageReferenceServiceImpl()
    {

    }

    /**
     * (get���͉��)<BR>
     * �Ǘ��ҏ؋����s���󋵏Ɖ���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҏ؋����s���󋵏Ɖ�T�[�r�X�jget���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminIfoDepShortageRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 498FD76F00BF
     */
    protected WEB3AdminIfoDepShortageRefInputResponse getInputScreen(
        WEB3AdminIfoDepShortageRefInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminIfoDepShortageRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;

        //getInstanceFrom���O�C�����
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DEPOSIT_SHORTAGE_REFERENCE, false);

        //createResponse
        WEB3AdminIfoDepShortageRefInputResponse l_response =
            (WEB3AdminIfoDepShortageRefInputResponse)l_request.createResponse();

        //���X�|���X�f�[�^�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //�������t�ꗗ �� �O�c�Ɠ��Ɠ��c�Ɠ��̔z��
        //���c�Ɠ��FGtlUtils.getTradingSystem().getBizDate()
        //�O�c�Ɠ��F�c�Ɠ��v�Z�Droll()���\�b�h�ɂđO�c�Ɠ����擾����
        Date[] l_datList = new Date[2];

        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        l_datList[0] = l_bizDate.roll(-1);
        l_datList[1] = l_datBizDate;
        l_response.searchDateList = l_datList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * �Ǘ��ҏ؋����s���󋵏Ɖ�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҏ؋����s���󋵏Ɖ�T�[�r�X�jget�Ɖ��ʁv�Q��<BR>
     * ===================================================<BR>
     * �@@�V�[�P���X�}�@@:�i�Ǘ��ҏ؋����s���󋵏Ɖ�T�[�r�X�jget�Ɖ���<BR>
     * �@@��̈ʒu�@@�@@�@@: is�؋����s�����[�����M��(String, String)<BR>
     * �@@�@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^ �D�������t ���@@�u���c�Ɠ��v(*)�@@�����@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@is�؋����s�����[�����M�ρi�j�̖߂�l == false�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u�{���̏؋����s���͂܂��m�F���Ă��܂���B�v�̗�O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_03157<BR>
     * ===================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminIfoDepShortageReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 498FDBA80092
     */
    protected WEB3AdminIfoDepShortageReferenceResponse getReferenceScreen(
        WEB3AdminIfoDepShortageReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminIfoDepShortageReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();

        //getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DEPOSIT_SHORTAGE_REFERENCE, false);

        //get�،����
        Institution l_institution = l_administrator.getInstitution();
        String l_strInstitutionCode = l_institution.getInstitutionCode();

        //validate���X����(���X�R�[�h : String[])
        //[����]
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ
        l_administrator.validateBranchPermission(l_request.branchCode);

        //���c�Ɠ��FGtlUtils.getTradingSystem().getBizDate()
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //is�؋����s�����[�����M��
        //[����]
        //�،���ЃR�[�h�F�@@get�،����()�̖߂�l�D�،���ЃR�[�h
        //���X�R�[�h �F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ[0]
        boolean l_blnIfoDepositMailFlag =
            WEB3AdminIfoDataManager.isIfoDepositMailFlag(l_strInstitutionCode, l_request.branchCode[0]);

        //���N�G�X�g�f�[�^ �D�������t ���@@�u���c�Ɠ��v�@@�����@@
        //is�؋����s�����[�����M�ρi�j�̖߂�l == false�̏ꍇ
        //�u�{���̏؋����s���͂܂��m�F���Ă��܂���B�v�̗�O���X���[����
        if (WEB3DateUtility.compareToDay(l_datBizDate, l_request.searchDate) == 0 && !l_blnIfoDepositMailFlag)
        {
            log.debug("�{���̏؋����s���͂܂��m�F���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03157,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�{���̏؋����s���͂܂��m�F���Ă��܂���B");
        }

        //get�؋������ꗗ
        //[����]
        // �،���ЁF�@@get�،����()�̖߂�l
        // ���N�G�X�g�f�[�^�F�@@���N�G�X�g�f�[�^
        IfoDepositRow[] l_ifoDepositRows =
            WEB3AdminIfoDataManager.getDepositInfoList(l_institution, l_request);

        //create�؋����s���󋵈ꗗ
        //[����]
        //�،���ЃR�[�h�Fget�،����()�̖߂�l�D�،���ЃR�[�h
        //�؋������ꗗ�F�@@get�؋������ꗗ()�̖߂�l
        //���N�G�X�g�f�[�^�F���N�G�X�g�f�[�^
        WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos =
            this.createDepShortageList(l_strInstitutionCode, l_ifoDepositRows, l_request);

        //(*)create�؋����s���󋵈ꗗ�̖߂�l�̗v�f����0�̏ꍇ
        //��̃��X�|���X�𐶐����A�����l���Z�b�g���ĕԋp
        //���X�|���X�f�[�^.�\������ =  TradingSystem.getSystemTimestamp()
        if (l_ifoDepShortageInfos.length == 0)
        {
            //createResponse
            WEB3AdminIfoDepShortageReferenceResponse l_response =
                (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();

            l_response.dispDate = GtlUtils.getSystemTimestamp();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //sort�؋����s���󋵈ꗗ
        //[����]
        //�؋����s���󋵈ꗗ �Fcreate�؋����s���󋵈ꗗ()�̖߂�l
        //�\�[�g�L�[�z��F���N�G�X�g�f�[�^.�\�[�g�L�[
        WEB3IfoDepShortageInfo[] l_sortIfoDepShortageInfos =
            this.sortDepShortageList(l_ifoDepShortageInfos, l_request.sortKeys);

        //WEB3PageIndexInfo
        //l_objs�Fthis.sort�؋����s���󋵈ꗗ()�̖߂�l
        //l_intRequestPageIndex�F���N�G�X�g�̗v���y�[�W�ԍ�
        //l_intRequestPageSize�F���N�G�X�g�̃y�[�W���\���s��
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_sortIfoDepShortageInfos,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //create���ӏ��ꗗ
        //createResponse
        WEB3AdminIfoDepShortageReferenceResponse l_response =
            (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();

        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
        //getArrayReturned(l_classType : Class)
        //��ʂɕ\�����镪�̏؋����s���󋵈ꗗ�̔z����擾����B
        l_response.ifoDepShortageInfos =
            (WEB3IfoDepShortageInfo[])l_pageIndexInfo.getArrayReturned(WEB3IfoDepShortageInfo.class);
        //���X�|���X�f�[�^.�\������
        l_response.dispDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �Ǘ��ҁE�؋����s���󋵏Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A <BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * ���Ǘ��ҁE�؋����s���󋵏Ɖ���̓��N�G�X�g�̏ꍇ <BR>
     * �@@this.get���͉��()���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g�̏ꍇ <BR>
     * �@@this.get�Ɖ���()���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 498FFDCF0285
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
        if (l_request instanceof WEB3AdminIfoDepShortageRefInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminIfoDepShortageRefInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminIfoDepShortageReferenceRequest)
        {
           l_response = this.getReferenceScreen((WEB3AdminIfoDepShortageReferenceRequest)l_request);
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
     * (create�؋����s���󋵈ꗗ)<BR>
     * �����̏؋������ꗗ���A�؋����s���󋵈ꗗ�� <BR>
     * �쐬���A�ԋp����B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҏ؋����s���󋵏Ɖ�T�[�r�X�jcreate�؋����s���󋵈ꗗ�v�Q��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_depInfoList - (�؋������ꗗ)<BR>
     * �؋������ꗗ<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3IfoDepShortageInfo[]
     * @@throws WEB3BaseException
     * @@roseuid 49912D2E02B9
     */
    protected WEB3IfoDepShortageInfo[] createDepShortageList(
        String l_strInstitutionCode,
        IfoDepositRow[] l_depInfoList,
        WEB3AdminIfoDepShortageReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createDepShortageList(String, IfoDepositRow[], WEB3AdminIfoDepShortageReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lisIfoDepShortageInfos = new ArrayList();

        //is�؋����s�����[�����M��
        //[����]
        //�،���ЃR�[�h�F�@@�����D�،���ЃR�[�h
        //���X�R�[�h �F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ[0]
        boolean l_blnIfoDepositMailFlag =
            WEB3AdminIfoDataManager.isIfoDepositMailFlag(l_strInstitutionCode, l_request.branchCode[0]);

        //is���Z�l�����M��
        //[����]
        //�،���ЃR�[�h�F�@@�����D�،���ЃR�[�h
        //���X�R�[�h �F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ[0]
        boolean l_blnIsQuickReportReceived =
            WEB3AdminIfoDataManager.isQuickReportReceived(l_strInstitutionCode, l_request.branchCode[0]);

        for (int i = 0; i < l_depInfoList.length; i++)
        {
            IfoDepositRow l_ifoDepositRow = l_depInfoList[i];

            //�؋����v�Z�T�[�r�X���擾
            WEB3IfoDepositCalcService l_service =
                (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            //�⏕�����F�@@�����Ώۂ̗v�f�̏،���ЃR�[�h�C���X�R�[�h�C
            //�ڋq�R�[�h�ɊY������ڋq�I�u�W�F�N�g.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT)
            WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_ifoDepositRow.getInstitutionCode(),
                l_ifoDepositRow.getBranchCode(),
                l_ifoDepositRow.getAccountCode());
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�؋����v�Z�I�u�W�F�N�g���擾
            WEB3IfoDepositCalc l_ifoDepCalc = l_service.getIfoDepositCalc(l_subAccount);

            //get�؋����v�Z����( )
            WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition =
                l_ifoDepCalc.getIfoDepositCalcCondition();

            //get�؋����v�Z���( )
            int l_intIfoDepositBaseDate = l_ifoDepositCalcCondition.getIfoDepositBaseDate();

            //�؋����s���󋵏��( )
            WEB3IfoDepShortageInfo l_ifoDepShortageInfo = new WEB3IfoDepShortageInfo();
            //���X�R�[�h                ��  �؋���Row.���X�R�[�h
            l_ifoDepShortageInfo.branchCode = l_ifoDepositRow.getBranchCode();
            //�ڋq�R�[�h                ��  �؋���Row.�ڋq�R�[�h�̍�6byte
            l_ifoDepShortageInfo.accountCode = l_ifoDepositRow.getAccountCode().substring(0, 6);
            //�ڋq��                    ���@@getMainAccount().get�ڋq�\����()
            l_ifoDepShortageInfo.accountName = l_mainAccount.getDisplayAccountName();

            //�����z                    ��  (*1)
            //���N�G�X�g�f�[�^.�������t���u���c�Ɠ��v�̏ꍇ�A calc���������z( )�̖߂�l���Z�b�g
            //���N�G�X�g�f�[�^.�������t���u�O�c�Ɠ��v�̏ꍇ�Aget���������z( )�̖߂�l���Z�b�g
            //���c�Ɠ�
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            if (WEB3DateUtility.compareToDay(l_datBizDate, l_request.searchDate) == 0)
            {
                //calc���������z
                double l_dblNextBizDateDemandAmount = l_ifoDepCalc.calcNextBizDateDemandAmount();
                l_ifoDepShortageInfo.claimAmount = WEB3StringTypeUtility.formatNumber(l_dblNextBizDateDemandAmount);
            }

            //�O�c�Ɠ�
            Date l_datBeforeBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(-1);
            if (WEB3DateUtility.compareToDay(l_datBeforeBizDate, l_request.searchDate) == 0)
            {
                //get���������z
                double l_dblCurrentBizDateDemandAmount = l_ifoDepCalc.getCurrentBizDateDemandAmount();
                l_ifoDepShortageInfo.claimAmount = WEB3StringTypeUtility.formatNumber(l_dblCurrentBizDateDemandAmount);
            }

            //���ݖ������z              ��  (*2)
            // �i is���Z�l�����M��()== true && is�؋����s�����[�����M��()== false�j
            //�@@|| (���N�G�X�g�f�[�^.�������t ���u�O�c�Ɠ��v&& is�؋����s�����[�����M��()==true)
            // �@@�̏ꍇ�� null���Z�b�g
            // ��L�ȊO�̏ꍇ�@@calc�������z( )�̖߂�l���Z�b�g�B
            if ((l_blnIsQuickReportReceived && !l_blnIfoDepositMailFlag)
                || (WEB3DateUtility.compareToDay(l_datBeforeBizDate, l_request.searchDate) == 0
                    && l_blnIfoDepositMailFlag))
            {
                l_ifoDepShortageInfo.curNonPayAmt = null;
            }
            else
            {
                //calc�������z
                double l_dblNonPayAmount = l_ifoDepCalc.calcNonPayAmount();
                l_ifoDepShortageInfo.curNonPayAmt = WEB3StringTypeUtility.formatNumber(l_dblNonPayAmount);
            }

            //calc�؋������v�z
            double l_dblIfoDepositRequiredAmount = l_ifoDepCalc.calcIfoDepositRequiredAmount();
            //���ݏ؋������v�z          ���@@calc�؋������v�z( )
            l_ifoDepShortageInfo.curIfoDepositNecessaryAmt =
                WEB3StringTypeUtility.formatNumber(l_dblIfoDepositRequiredAmount);

            //[a]------ calc���|�W�V��������()
            //[�����̐ݒ�]
            //�w����F�@@get�؋����v�Z���( )�̖߂�l
            BigDecimal l_bdBuyContractQty =
                new BigDecimal(l_ifoDepCalc.calcBuyContractQty(l_intIfoDepositBaseDate) + "");

            // �@@[b]------ calc���|�W�V��������()
            //[�����̐ݒ�]
            //�w����F�@@get�؋����v�Z���( )�̖߂�l
            BigDecimal l_bdSellContractQty =
                new BigDecimal(l_ifoDepCalc.calcSellContractQty(l_intIfoDepositBaseDate) + "");

            // �@@[c]------ calc���������|�W�V��������()
            //[�����̐ݒ�]
            //�w����F�@@get�؋����v�Z���( )�̖߂�l
            BigDecimal l_bdBuyOrderQty =
                new BigDecimal(l_ifoDepCalc.calcBuyOrderQty(l_intIfoDepositBaseDate) + "");

            // �@@[d]------ calc���������|�W�V��������()
            //[�����̐ݒ�]
            //�w����F�@@get�؋����v�Z���( )�̖߂�l
            BigDecimal l_bdSellOrderQty =
                new BigDecimal(l_ifoDepCalc.calcSellOrderQty(l_intIfoDepositBaseDate) + "");

            //���ʗL���t���O            ��  (*3)
            //a-c >0�܂���b-d >0�̏ꍇ�@@true���Z�b�g�i���j
            //��L�ȊO�̏ꍇ�@@false���Z�b�g
            if (l_bdBuyContractQty.subtract(l_bdBuyOrderQty).doubleValue() > 0
                || l_bdSellContractQty.subtract(l_bdSellOrderQty).doubleValue() > 0)
            {
                l_ifoDepShortageInfo.contractExistFlag = true;
            }
            else
            {
                l_ifoDepShortageInfo.contractExistFlag = false;
            }

            //�����L���t���O            ��  (*4)
            //  c >0�܂���d >0�̏ꍇ�@@true���Z�b�g�i���j
            //��L�ȊO�̏ꍇ�@@false���Z�b�g
            if (l_bdBuyOrderQty.doubleValue() > 0 || l_bdSellOrderQty.doubleValue() > 0)
            {
                l_ifoDepShortageInfo.orderExistFlag = true;
            }
            else
            {
                l_ifoDepShortageInfo.orderExistFlag = false;
            }

            //�i*�j(���N�G�X�g�f�[ �^�D�������q�敪��[0�F�������q] &&
            // ( ���ݖ������z == 0  || ���ݖ������z == null ) ) �ȊO�̏ꍇ
            if (!(WEB3AdminIfoUnCancelDivDef.UN_CANCEL.equals(l_request.uncancelDiv)
                && (l_ifoDepShortageInfo.curNonPayAmt == null
                    || WEB3AdminIfoCurNonPayAmtDef.ZERO.equals(l_ifoDepShortageInfo.curNonPayAmt))))
            {
                l_lisIfoDepShortageInfos.add(l_ifoDepShortageInfo);
            }
        }

        WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos =
            new WEB3IfoDepShortageInfo[l_lisIfoDepShortageInfos.size()];
        l_lisIfoDepShortageInfos.toArray(l_ifoDepShortageInfos);

        log.exiting(STR_METHOD_NAME);
        return l_ifoDepShortageInfos;
    }

    /**
     * (sort�؋����s���󋵈ꗗ)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A<BR>
     * ���~���ɂ��ǂ��ď؋����s���󋵈ꗗ�f�[�^�̃\�[�g���s���B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�@@�@@�@@�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r����Comparator�𐶐�����B <BR>
     * �@@�@@�@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~�� <BR>
     * <BR>
     * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B <BR>
     * <BR>
     * �R�jArrayList����Comparator�̔z����쐬���A<BR>
     * WEB3ArraysUtility.sort()���\�b�h���R�[������B <BR>
     * <BR>
     * �S)�\�[�g�����؋����s���󋵈ꗗ�̔z���ԋp����B <BR>
     * @@param l_sortDepShortageList - (�؋����s���󋵈ꗗ)<BR>
     * �؋����s���󋵈ꗗ<BR>
     * @@param l_sortKeys - (�\�[�g�L�[�z��)<BR>
     * �\�[�g�L�[�z��<BR>
     * @@return WEB3IfoDepShortageInfo[]
     * @@roseuid 499907CC03D0
     */
    protected WEB3IfoDepShortageInfo[] sortDepShortageList(
        WEB3IfoDepShortageInfo[] l_sortDepShortageList, WEB3IfoDepShortageSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "sortDepShortageList(WEB3IfoDepShortageInfo[], WEB3IfoDepShortageSortKey[])";
        log.entering(STR_METHOD_NAME);

        //ArrayList�𐶐�����B
        List l_lisSorts = new ArrayList();

        int l_intSortKeyLength = l_sortKeys.length;
        //�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //�@@�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r����Comparator�𐶐�����B
            //�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]
            //�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~��
            //�AArrayList�ɐ�������Comparator��ǉ�����B
            String l_strKeyItem = l_sortKeys[i].keyItem;

            if (WEB3AdminIfoSortKeyItemNameDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepBranchCodeComparator l_comparator =
                    new WEB3AdminIfoDepBranchCodeComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepAccountCodeComparator l_comparator =
                    new WEB3AdminIfoDepAccountCodeComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.CLAIM_AMOUNT.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepClaimAmountComparator l_comparator =
                    new WEB3AdminIfoDepClaimAmountComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.CUR_NON_PAY_AMT.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepCurNonPayAmtComparator l_comparator =
                    new WEB3AdminIfoDepCurNonPayAmtComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.CUR_IFO_DEPOSIT_NECESSARY_AMT.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepCurIfoDepositNecessaryAmtComparator l_comparator =
                    new WEB3AdminIfoDepCurIfoDepositNecessaryAmtComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.CONTRACT_EXIST_FLAG.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepContractExistFlagComparator l_comparator =
                    new WEB3AdminIfoDepContractExistFlagComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
            else if (WEB3AdminIfoSortKeyItemNameDef.ORDER_EXIST_FLAG.equals(l_strKeyItem))
            {
                WEB3AdminIfoDepOrderExistFlagComparator l_comparator =
                    new WEB3AdminIfoDepOrderExistFlagComparator(l_sortKeys[i].ascDesc);
                l_lisSorts.add(l_comparator);
            }
        }

        //ArrayList����Comparator�̔z����쐬���A
        // WEB3ArraysUtility.sort()���\�b�h���R�[������B
        Comparator[] l_comparatorList = new Comparator[l_lisSorts.size()];
        l_lisSorts.toArray(l_comparatorList);
        WEB3ArraysUtility.sort(l_sortDepShortageList, l_comparatorList);

        log.exiting(STR_METHOD_NAME);
        return l_sortDepShortageList;
    }
}
@
