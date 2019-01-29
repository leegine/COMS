head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ����Ɖ�T�[�r�XImpl(WEB3AdminEquityForcedSettleReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ��іQ (���u) �V�K�쐬 ���f��No.129
Revision History : 2007/07/24 �����q (���u) �V�K�쐬 ���f��No.159
Revision History : 2008/01/17 �И��� (���u) �d�l�ύX ���f��No.181
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedSettleReasonType;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReasonUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleReferenceService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�Ǘ��ҁE�������ϒ����Ɖ�T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�������ϒ����Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleReferenceServiceImpl implements WEB3AdminEquityForcedSettleReferenceService
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleReferenceServiceImpl.class);

    /**
     * @@roseuid 462CA423019B
     */
    public WEB3AdminEquityForcedSettleReferenceServiceImpl()
    {

    }

    /**
     * �������ϒ����Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���A<BR>
     * [�Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g�̏ꍇ]<BR>
     * �@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * [�Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g�̏ꍇ]<BR>
     * �@@this.get�Ɖ���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4601E9290128
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
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

        //�Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g�̏ꍇ
        //this.get���͉��()���R�[������B
        if (l_request instanceof WEB3AdminForcedSettleRefInputRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminForcedSettleRefInputRequest)l_request);
        }
        //�Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g�̏ꍇ
        //this.get�Ɖ���()���R�[������B
        else if (l_request instanceof WEB3AdminForcedSettleReferenceRequest)
        {
            l_response =
                this.getReferenceScreen(
                    (WEB3AdminForcedSettleReferenceRequest)l_request);
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
     * �������ϒ����Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҋ������ϒ����Ɖ�T�[�r�X�jget���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4601E99B0176
     */
    protected WEB3AdminForcedSettleRefInputResponse getInputScreen(WEB3AdminForcedSettleRefInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminForcedSettleRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���������i�������ρj
        //is�X�V�F�@@false�i�X�V�Ȃ��j
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, false);

        //getBranch
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;

        try
        {
            l_branch = l_gentradeAccountManager.getWeb3GenBranch(
                l_admin.getInstitutionCode(),
                l_admin.getBranchCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get�戵�\�s��(���X : ���X, �ٍϋ敪 : String, �ٍϊ����l : double)
        //���X�F�@@�Ǘ��҂̏،���ЃR�[�h�A���X�R�[�h�ɊY�����镔�X�I�u�W�F�N�g
        //�ٍϋ敪�F�@@"DEFAULT"�i�w��Ȃ��j
        //�ٍϊ����l�F�@@"DEFAULT"�i�w��Ȃ��j
        String[] l_strHandlingPossibleMarkets =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
            l_branch, WEB3GentradeRepaymentDivDef.DEFAULT, 0D);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //create�������ϊ����ꗗ(String, String[])
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�ꗗ�F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ
        Date[] l_datForcedSettleCloseDateList = this.createForcedSettleCloseDateList(
            l_strInstitutionCode,
            l_request.branchCodeList);

        //create�������ϗ��R���ꗗ(���X)
        //���X�F�@@�Ǘ��҂̏،���ЃR�[�h�A���X�R�[�h�ɊY�����镔�X�I�u�W�F�N�g
        WEB3AdminForcedSettleReasonUnit[] l_adminForcedSettleReasonUnits =
            this.createForcedSettleReasonUnitList(l_branch);

        // createResponse( )
        WEB3AdminForcedSettleRefInputResponse l_response =
            (WEB3AdminForcedSettleRefInputResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        //�������ꗗ     ���@@���c�Ɠ� + ���c�Ɠ��i�Ɩ����t�j���܂߂�
        //�ߋ�30���̊Ԃ̉c�Ɠ����~���ŃZ�b�g�B
        Date[] l_datOrderBizDateList = this.createOrderBizDateList();

        l_response.orderBizDateList = l_datOrderBizDateList;
        //���ϊ����ꗗ      ���@@create�������ϊ����ꗗ()�̖߂�l
        l_response.settleTimeLimitList = l_datForcedSettleCloseDateList;

        //�s��R�[�h�ꗗ     ���@@get�戵�\�s��()�̖߂�l
        l_response.marketCodeList = l_strHandlingPossibleMarkets;

        //�������ϗ��R�ꗗ        ���@@create�������ϗ��R���ꗗ()�̖߂�l
        l_response.forcedSettleReasonList = l_adminForcedSettleReasonUnits;

        //�����G���[���R�R�[�h�ꗗ
        //    (*1)�ȉ��̒l�̔z��i�R�[�h�l�̎Q�Ƃ́A�C���^�[�t�F�C�X��`�N���X���g�p���邱�Ɓj
        //�E"�����c���s���G���["
        //�E"������~�����G���["
        //�E"���ϊ��������σG���["
        //�E"�����E���n�����o�^�σG���["
        //�E"���̑��G���["
        String[] l_strErrorReason =
        {
            WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR,
            WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR,
            WEB3ErrorReasonCodeDef.SETTLEDAY_CAME_ERROR,
            WEB3ErrorReasonCodeDef.SWAP_MARGIN_REGISTED_ERROR,
            WEB3ErrorReasonCodeDef.OTHRE_ERROR
        };
        l_response.errorReason = l_strErrorReason;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * �������ϒ����Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҋ������ϒ����Ɖ�T�[�r�X�jget�Ɖ��ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 4601E9EB00AB
     */
    protected WEB3AdminForcedSettleReferenceResponse getReferenceScreen(WEB3AdminForcedSettleReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminForcedSettleReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���������i�������ρj
        //is�X�V�F�@@false�i�X�V�Ȃ��j
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, false);

        //validate���X����(���X�R�[�h : String[])
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ
        l_admin.validateBranchPermission(l_request.branchCodeList);

        //get�،����( )
        Institution l_institution = l_admin.getInstitution();

        //get�������ϒ����ꗗ(�،����, �Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g)
        //�،���ЁF�@@get�،����()�̖߂�l
        //���N�G�X�g�f�[�^�F�@@���N�G�X�g�f�[�^
        AdminEqForcedSettleOrderRow[] l_forcedSettleOrderList =
            WEB3AdminPMEquityDataManager.getForcedSettleOrderList(
                l_institution,
                l_request);

        int l_intPageIndex = 0;
        int l_intTotalPages = 0;
        int l_intTotalRecords = 0;

        if (l_forcedSettleOrderList == null
            || l_forcedSettleOrderList.length == 0)
        {
            //��̃��X�|���X�f�[�^�𐶐����A�����l���Z�b�g���ĕԋp����B
            WEB3AdminForcedSettleReferenceResponse l_response =
                (WEB3AdminForcedSettleReferenceResponse)l_request.createResponse();

            //���y�[�W��
            l_response.totalPages = l_intTotalPages + "";

            //�����R�[�h��
            l_response.totalRecords = l_intTotalRecords + "";

            //�\���y�[�W�ԍ�
            l_response.pageIndex = l_intPageIndex + "";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //WEB3PageIndexInfo(l_objs : �_���r���[::java::lang::Object[],
        //l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //arg0�F�@@get�������ϒ����ꗗ()�̖߂�l
        //arg1�F�@@���N�G�X�g�f�[�^.�v���y�[�W�ԍ�
        //arg2�F�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_forcedSettleOrderList,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //getPageIndex( )
        l_intPageIndex = l_pageIndexInfo.getPageIndex();

        //getTotalPages( )
        l_intTotalPages = l_pageIndexInfo.getTotalPages();

        //getTotalRecords( )
        l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        //getArrayReturned(l_classType : Class)
        //arg0�F�������ϒ���Row.class
        AdminEqForcedSettleOrderRow[] l_adminEqForcedSettleOrderRows =
            (AdminEqForcedSettleOrderRow[])l_pageIndexInfo.getArrayReturned(
                AdminEqForcedSettleOrderRow.class);

        //create�������ϒ����Ɖ���ꗗ(�������ϒ���Row[], String)
        //�������ϒ����ꗗ�F�@@getArrayReturned()�̖߂�l
        //���F�敪�F�@@���N�G�X�g�f�[�^.���F�敪
        WEB3AdminForcedSettleTemporaryOrderUnit[] l_forcedSettleTemporaryOrderUnitList =
            WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(
            l_adminEqForcedSettleOrderRows,
            l_request.approveType);

        //createResponse( )
        WEB3AdminForcedSettleReferenceResponse l_response =
            (WEB3AdminForcedSettleReferenceResponse)l_request.createResponse();

        //���y�[�W��     ���@@getTotalPages()�̖߂�l
        l_response.totalPages = l_intTotalPages + "";

        //�����R�[�h��      ���@@getTotalRecords()�̖߂�l
        l_response.totalRecords = l_intTotalRecords + "";

        //�\���y�[�W�ԍ�     ���@@getPageIndex()�̖߂�l
        l_response.pageIndex = l_intPageIndex + "";

        //�������ϒ����ꗗ        ���@@create�������ϒ����Ɖ���ꗗ()�̖߂�l
        l_response.forcedSettleTemporaryOrderList = l_forcedSettleTemporaryOrderUnitList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�������ϊ����ꗗ)<BR>
     * �������ϊ����ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�@@���XID�̎擾<BR>
     * �@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get���XID�ꗗ()��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �@@[get���XID�ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�ꗗ�F�@@�p�����[�^.���X�R�[�h�ꗗ<BR>
     * <BR>
     * �Q�j�@@�ȉ��̏����ŋ������ϒ����e�[�u����<BR>
     * �@@��������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���XID in (�擾�������XID�ꗗ)<BR>
     * <BR>
     * �@@[�\�[�g����]<BR>
     * �@@�@@�����@@�~��<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����B<BR>
     * <BR>
     * �R�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �S�j�@@�Q�j�̖߂�l�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * �@@�S�|�P�j�@@��������ArrayList.contains() == false�̏ꍇ�A<BR>
     * �@@�@@��������ArrayList.add()���R�[������B<BR>
     * <BR>
     * �@@�@@[contains()�Aadd()�Ɏw�肷�����]<BR>
     * �@@�@@�@@arg0�F�@@�����Ώۂ̗v�f.����<BR>
     * <BR>
     * �T�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodeList - (���X�R�[�h�ꗗ)<BR>
     * ���X�R�[�h�ꗗ<BR>
     * @@return Date[]
     * @@throws WEB3BaseException
     * @@roseuid 46103A2B0143
     */
    protected Date[] createForcedSettleCloseDateList(String l_strInstitutionCode, String[] l_strBranchCodeList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForcedCloseDateList(String,  String[])";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMEquityDataManager l_adminPMEquityDataManager = new WEB3AdminPMEquityDataManager();

        List l_lisAdminEqForcedSettleOrderList = new ArrayList();
        List l_lisArrayList = new ArrayList();

        try
        {
            //���i�Ǘ��i�����j�f�[�^�}�l�[�W��.get���XID�ꗗ()���R�[������B
            String[] l_strBranchIdList =
                l_adminPMEquityDataManager.getBranchId(l_strInstitutionCode, l_strBranchCodeList);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            StringBuffer l_sbQuery = new StringBuffer();

            l_sbQuery.append(" branch_id in ( ");

            String l_strSortCond = " close_date desc ";

            if (l_strBranchIdList != null && l_strBranchIdList.length != 0)
            {
                int l_intBranchIdListLength = l_strBranchIdList.length;
                for (int i = 0; i < l_intBranchIdListLength; i++)
                {
                    l_sbQuery.append(" ? ,");
                    l_lisArrayList.add(l_strBranchIdList[i]);
                }
            }

            l_sbQuery.deleteCharAt(l_sbQuery.length() - 1);
            l_sbQuery.append(") ");

            String[] l_strValues = new String[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_strValues);

            l_lisAdminEqForcedSettleOrderList = l_queryProcessor.doFindAllQuery(
                AdminEqForcedSettleOrderRow.TYPE,
                l_sbQuery.toString(),
                l_strSortCond,
                null,
                l_strValues);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�������ʂ��擾�ł��Ȃ������ꍇ��null��ԋp����
        if (l_lisAdminEqForcedSettleOrderList == null
            || l_lisAdminEqForcedSettleOrderList.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //ArrayList�𐶐�����B
        List l_lisCloseDateList = new ArrayList();

        //�̖߂�l�ɂ��āA�ȉ��̏������J��Ԃ��B
        int l_intEqForcedSettleOrderListSize = l_lisAdminEqForcedSettleOrderList.size();
        for (int i = 0; i < l_intEqForcedSettleOrderListSize; i++)
        {
            //�@@��������ArrayList.contains() == false�̏ꍇ
            //��������ArrayList.add()���R�[������B
            AdminEqForcedSettleOrderRow l_adminEqForcedSettleOrderRow =
                (AdminEqForcedSettleOrderRow)l_lisAdminEqForcedSettleOrderList.get(i);

            if (!l_lisCloseDateList.contains(l_adminEqForcedSettleOrderRow.getCloseDate()))
            {
                //��������ArrayList.add()���R�[������B
                l_lisCloseDateList.add(l_adminEqForcedSettleOrderRow.getCloseDate());
            }
        }

        //��������ArrayList.toArray()�̖߂�l��ԋp����B
        Date[] l_datForcedSettleCloseDateList = new Date[l_lisCloseDateList.size()];
        l_lisCloseDateList.toArray(l_datForcedSettleCloseDateList);

        log.exiting(STR_METHOD_NAME);
        return l_datForcedSettleCloseDateList;
    }

    /**
     * (create�������ϗ��R���ꗗ)<BR>
     * �������ϗ��R���̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�@@�߂�l���i�[����ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�������ϗ��R�F���ϊ��������̍쐬<BR>
     * �@@�Q�|�P�j�@@�������ϗ��R���C���^�X�^���X�𐶐�����B<BR>
     * �@@�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�������ϗ��R�敪�F�@@"��������"<BR>
     * �@@�@@�@@���X�F�@@����.���X<BR>
     * <BR>
     * �Q�|�Q�j�@@�Q�|�P�j�̖߂�l��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �R�j�@@�������ϗ��R�F�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j�̍쐬<BR>
     * �@@�R�|�P�j�@@�������ϗ��R�����쐬����B<BR>
     * �@@�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�������ϗ��R�敪�F�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"<BR>
     * �@@�@@�@@���X�F�@@����.���X<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�R�|�P�j�̖߂�l��null�łȂ��ꍇ�AArrayList�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �S�j�@@�������ϗ��R�F�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j�̍쐬<BR>
     * �@@�S�|�P�j�@@�������ϗ��R�����쐬����B<BR>
     * �@@�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�������ϗ��R�敪�F�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j"<BR>
     * �@@�@@�@@���X�F�@@����.���X<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�S�|�P�j�̖߂�l��null�łȂ��ꍇ�AArrayList�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �T�j�@@�������ϗ��R�F�ۏ؋��ێ������i�I�����C���J�n�O�E�@@��j�̍쐬 <BR>
     * �@@�T�|�P�j�@@�������ϗ��R�����쐬����B <BR>
     * �@@�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�������ϗ��R�敪�F�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j" <BR>
     * �@@�@@�@@���X�F�@@����.���X<BR>
     * <BR>
     * �@@�T�|�Q�j�@@�T�|�P�j�̖߂�l��null�łȂ��ꍇ�AArrayList�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �U�j�@@�������ϗ��R�F�ۏ؋��ێ������i��ԁj�̍쐬<BR>
     * �@@�U�|�P�j�@@�������ϗ��R�����쐬����B<BR>
     * �@@�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�������ϗ��R�敪�F�@@"�ۏ؋��ێ�������i��ԁj"<BR>
     * �@@�@@�@@���X�F�@@����.���X<BR>
     * <BR>
     * �@@�U�|�Q�j�@@�U�|�P�j�̖߂�l��null�łȂ��ꍇ�AArrayList�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �V�j�@@�������ϗ��R�F�蓮�������ς̍쐬<BR>
     * �@@�V�|�P�j�@@�������ϗ��R���C���^�X�^���X�𐶐�����B<BR>
     * �@@�@@���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�������ϗ��R�敪�F�@@"�蓮��������"<BR>
     * �@@�@@�@@���X�F�@@����.���X<BR>
     * <BR>
     * �@@�V�|�Q�j�@@�V�|�P�j�̖߂�l��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �W�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_branch - (���X)<BR>
     * ���X�I�u�W�F�N�g<BR>
     * @@return WEB3AdminForcedSettleReasonUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 46022E76006C
     */
    protected WEB3AdminForcedSettleReasonUnit[] createForcedSettleReasonUnitList(Branch l_branch)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForcedSettleReasonUnitList(Branch)";
        log.entering(STR_METHOD_NAME);

        // �P�j�߂�l���i�[����ArrayList�𐶐�����B
        List l_lisArrayList = new ArrayList();

        // �Q�j�@@�������ϗ��R�F���ϊ��������̍쐬
        // �Q�|�P�j�@@�������ϗ��R�����쐬����B
        // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B
        // �������ϗ��R�敪�F�@@"��������"
        // ���X�F�@@����.���X 
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.FIXED_DATE_COMING,
                l_branch);

        // �Q�|�Q�j�@@�Q�|�P�j�̖߂�l��ArrayList�ɒǉ�����B
        l_lisArrayList.add(l_forcedSettleReasonUnit);

        // �R�j�@@�������ϗ��R�F�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j�̍쐬
        // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B
        // �������ϗ��R�敪�F�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j"
        // ���X�F�@@����.���X
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit1 =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS,
                l_branch);

        // �@@�R�|�Q�j�@@�R�|�P�j�̖߂�l��null�łȂ��ꍇ�AArrayList�ɒǉ�����B
        if (l_forcedSettleReasonUnit1 != null)
        {
            l_lisArrayList.add(l_forcedSettleReasonUnit1);
        }

        // �S�j�@@�������ϗ��R�F�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j�̍쐬
        // �S�|�P�j�@@�������ϗ��R�����쐬����B
        // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B
        // �������ϗ��R�敪�F�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j"
        // ���X�F�@@����.���X
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit2 =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS,
                l_branch);

        // �S�|�Q�j�@@�S�|�P�j�̖߂�l��null�łȂ��ꍇ�AArrayList�ɒǉ�����B
        if (l_forcedSettleReasonUnit2 != null)
        {
            l_lisArrayList.add(l_forcedSettleReasonUnit2);
        }

        // �T�j�@@�������ϗ��R�F�ۏ؋��ێ������i�I�����C���J�n�O�E�@@��j�̍쐬
        // �T�|�P�j�@@�������ϗ��R�����쐬����B
        // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B
        // �������ϗ��R�敪�F�@@"�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j"
        // ���X�F�@@����.���X
        WEB3AdminForcedSettleReasonUnit l_adminForcedSettleReasonUnit =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL,
                l_branch);

        // �T�|�Q�j�@@�T�|�P�j�̖߂�l��null�łȂ��ꍇ�AArrayList�ɒǉ�����B
        if (l_adminForcedSettleReasonUnit != null)
        {
            l_lisArrayList.add(l_adminForcedSettleReasonUnit);
        }

        // �U�j�@@�������ϗ��R�F�ۏ؋��ێ������i��ԁj�̍쐬
        // �U�|�P�j�@@�������ϗ��R�����쐬����B
        // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B
        // �������ϗ��R�敪�F�@@"�ۏ؋��ێ�������i��ԁj"
        // ���X�F�@@����.���X
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit3 =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.GUARANTEE_MAINTENANCE_BREAK_MARKET,
                l_branch);

        // �U�|�Q�j�@@�U�|�P�j�̖߂�l��null�łȂ��ꍇ�AArrayList�ɒǉ�����B
        if (l_forcedSettleReasonUnit3 != null)
        {
            l_lisArrayList.add(l_forcedSettleReasonUnit3);
        }

        // �V�j�@@�������ϗ��R�F�蓮�������ς̍쐬
        // �@@�V�|�P�j�@@�������ϗ��R�����쐬����B
        // ���i�Ǘ��i�����j�f�[�^�}�l�[�W��.create�������ϗ��R���()��call����B
        // �������ϗ��R�敪�F�@@"�蓮��������"
        // ���X�F�@@����.���X
        WEB3AdminForcedSettleReasonUnit l_forcedSettleReasonUnit4 =
            WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                WEB3ForcedSettleReasonType.MANUAL_FORCED_SETTLE,
                l_branch);

        // �V�|�Q�j�@@�V�|�P�j�̖߂�l��ArrayList�ɒǉ�����B
        if (l_forcedSettleReasonUnit4 != null)
        {
            l_lisArrayList.add(l_forcedSettleReasonUnit4);
        }

        // �W�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3AdminForcedSettleReasonUnit[] l_forcedSettleReasonUnitList =
            new WEB3AdminForcedSettleReasonUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_forcedSettleReasonUnitList);

        log.exiting(STR_METHOD_NAME);
        return l_forcedSettleReasonUnitList;
    }
    
    /**
     * ���c�Ɠ� + ���c�Ɠ��i�Ɩ����t�j���܂߂�
     * �ߋ�30���̊Ԃ̉c�Ɠ����~���B
     * @@return Date[]
     * @@throws WEB3BaseException
     */
    private Date[] createOrderBizDateList()
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderBizDateList()";
        log.entering(STR_METHOD_NAME);

        //���c�Ɠ�
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //���c�Ɠ�
        Date l_datNextBizDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);

        List l_lisArrayList = new ArrayList();

        l_lisArrayList.add(l_datNextBizDate);
        l_lisArrayList.add(l_datBizDate);

        //�ߋ�30���̊Ԃ̉c�Ɠ����~���B
        Date[] l_datOrderBizDates = new Date[30];
        for(int i = 0; i < 30; i++)
        {
            l_datOrderBizDates[i] = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(-(i+1));

            Date l_datOrderBizDate = WEB3DateUtility.addDay(l_datOrderBizDates[i], 30);
            int l_intReturn = WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datBizDate);
            if (l_intReturn < 0)
            {
                break;
            }

            l_lisArrayList.add(l_datOrderBizDates[i]);
        }

        Date[] l_datOrderBizDateList = new Date[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_datOrderBizDateList);

        log.exiting(STR_METHOD_NAME);
        return l_datOrderBizDateList;
    }
}
@
