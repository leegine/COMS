head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderDLServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ϒ���DL�T�[�r�XImpl�iWEB3AdminEquityForcedSettleOrderDLServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 ���n(���u) �V�K�쐬���f��171 191 194
Revision History : 2008/03/11 �����F(���u) ���f��202
Revision History : 2008/11/07 �I�O(���u) ���f��211
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminEquityForcedSettleOrderDLCsv;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderDLService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�������ϒ���DL�T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�������ϒ���DL�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderDLServiceImpl implements WEB3AdminEquityForcedSettleOrderDLService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLServiceImpl.class);

    /**
     * @@roseuid 479031F7037A
     */
    public WEB3AdminEquityForcedSettleOrderDLServiceImpl()
    {

    }

    /**
     * �Ǘ��ҁE�������ϒ���DL�T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     * get���͉��()<BR>
     * get�_�E�����[�h�t�@@�C��()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 476630F90061
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
        //get���͉��
        if (l_request instanceof WEB3AdminForcedSettleDownloadInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminForcedSettleDownloadInputRequest)l_request);
        }
        //get�_�E�����[�h�t�@@�C��
        else if (l_request instanceof WEB3AdminForcedSettleDownloadRequest)
        {
            l_response = this.getDownloadFile((WEB3AdminForcedSettleDownloadRequest)l_request);
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
     * ���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁE�������ϒ���DL�jget���͉�ʁv �Q��<BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :get�������ϒ����ꗗ()�̖߂�l == null�̏ꍇ<BR>
     * �@@�@@�@@�@@�u�����ɊY�����鋭�����ϒ���������܂���v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class       :WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag         :BUSINESS_ERROR_02985<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminForcedSettleDownloadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 476634E40198
     */
    protected WEB3AdminForcedSettleDownloadInputResponse getInputScreen(
        WEB3AdminForcedSettleDownloadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminForcedSettleDownloadInputRequest)";
        log.entering(STR_METHOD_NAME);

        // validate
        l_request.validate();

        //getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, false);

        //get�،����( )
        Institution l_institution = l_administrator.getInstitution();

        //get�������ϒ����ꗗ(�،����, �Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g)
        AdminEqForcedSettleOrderRow[] l_orderRows =
            WEB3AdminPMEquityDataManager.getForcedSettleOrderList(l_institution, l_request);

        //get�������ϒ����ꗗ()�̖߂�l == null�̏ꍇ
        if (l_orderRows == null)
        {
            log.debug("�����ɊY�����鋭�����ϒ���������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02985,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY�����鋭�����ϒ���������܂���B");
        }

        //WEB3PageIndexInfo
        WEB3PageIndexInfo l_pageIndexInfo = null;
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        l_pageIndexInfo =
            new WEB3PageIndexInfo(l_orderRows, 1, l_intPageSize);

        //createResponse
        WEB3AdminForcedSettleDownloadInputResponse l_response =
            (WEB3AdminForcedSettleDownloadInputResponse)l_request.createResponse();
        //���y�[�W��
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        //�����R�[�h��
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C���擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁE�������ϒ���DL�jget�_�E�����[�h�t�@@�C���v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminForcedSettleDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 476635200371
     */
    protected WEB3AdminForcedSettleDownloadResponse getDownloadFile(
        WEB3AdminForcedSettleDownloadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminForcedSettleDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        // validate
        l_request.validate();

        //getInstanceFrom���O�C�����
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, false);

        //validate���X����(���X�R�[�h : String[])
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ
        l_administrator.validateBranchPermission(l_request.branchCodeList);

        //get�،����( )
        Institution l_institution = l_administrator.getInstitution();

        //get�������ϒ����ꗗ(�،����, �Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g)
        AdminEqForcedSettleOrderRow[] l_orderRows =
            WEB3AdminPMEquityDataManager.getForcedSettleOrderList(l_institution, l_request);

        //get�������ϒ����ꗗ()�̖߂�l == null�̏ꍇ
        if (l_orderRows == null)
        {
            log.debug("�����ɊY�����鋭�����ϒ���������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02985,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY�����鋭�����ϒ���������܂���B");
        }

        //WEB3PageIndexInfo
        WEB3PageIndexInfo l_pageIndexInfo = null;
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        l_pageIndexInfo =
            new WEB3PageIndexInfo(l_orderRows, l_intPageIndex, l_intPageSize);

        //�_�E�����[�h���镪�̋������ϒ����ꗗ�̔z����擾����B
        AdminEqForcedSettleOrderRow[] l_adminEqForcedSettleOrderRows =
            (AdminEqForcedSettleOrderRow[])l_pageIndexInfo.getArrayReturned(AdminEqForcedSettleOrderRow.class);

        //�������ϒ����_�E�����[�hCSV�C���X�^���X�𐶐�����B
        WEB3AdminEquityForcedSettleOrderDLCsv l_orderDLCsv = new WEB3AdminEquityForcedSettleOrderDLCsv();

        int l_intProductStopSize = l_adminEqForcedSettleOrderRows.length;
        //getArrayReturned()�̗v�f�����ALoop����
        for (int i = 0; i < l_intProductStopSize; i++)
        {
            AdminEqForcedSettleOrderRow l_adminEqForcedSettleOrderRow =
                l_adminEqForcedSettleOrderRows[i];
            //add���׍s
            int l_intSize = l_orderDLCsv.addRow();
            //set���X�R�[�h
            l_orderDLCsv.setBranchCode(l_intSize, l_adminEqForcedSettleOrderRow.getBranchId());
            //set�ڋq
            l_orderDLCsv.setAccount(l_intSize,
                l_adminEqForcedSettleOrderRow.getAccountCode(),
                l_adminEqForcedSettleOrderRow.getAccountId());
            //set�������ϗ��R
            l_orderDLCsv.setForcedSettleReason(l_intSize, l_adminEqForcedSettleOrderRow.getForcedSettleReasonType());
            //set�s�ꖼ
            l_orderDLCsv.setMarketName(l_intSize, l_adminEqForcedSettleOrderRow.getMarketId());
            //set����
            l_orderDLCsv.setProduct(l_intSize,
                l_adminEqForcedSettleOrderRow.getProductCode(),
                l_adminEqForcedSettleOrderRow.getProductId());
            //set�ŋ敪
            l_orderDLCsv.setTaxType(l_intSize, l_adminEqForcedSettleOrderRow.getTaxType());
            //set���敪
            l_orderDLCsv.setContractType(l_intSize, l_adminEqForcedSettleOrderRow.getContractType());
            //set�ٍϋ敪
            l_orderDLCsv.setRepaymentDiv(l_intSize, l_adminEqForcedSettleOrderRow.getRepaymentType());
            //set����
            l_orderDLCsv.setOpenDate(l_intSize, l_adminEqForcedSettleOrderRow.getOpenDate());
            //set���ϊ���
            l_orderDLCsv.setCloseDate(l_intSize, l_adminEqForcedSettleOrderRow.getCloseDate());
            //set������
            l_orderDLCsv.setContractQuantity(l_intSize,
                String.valueOf(l_adminEqForcedSettleOrderRow.getContractQuantity()));
            //set���P��
            l_orderDLCsv.setContractPrice(l_intSize,
                String.valueOf(l_adminEqForcedSettleOrderRow.getContractPrice()));
            //set�����
            l_orderDLCsv.setContractExecPrice(l_intSize,
                WEB3StringTypeUtility.formatNumber(l_adminEqForcedSettleOrderRow.getContractAmount()));
            //set�ۏ؋���
            if (l_adminEqForcedSettleOrderRow.getMarginMaintenanceRateIsNull())
            {
                l_orderDLCsv.setMarginDepositRate(l_intSize, null);
            }
            else
            {
                l_orderDLCsv.setMarginDepositRate(l_intSize,
                    String.valueOf(l_adminEqForcedSettleOrderRow.getMarginMaintenanceRate()));
            }
            //set�Ǐؔ�����
            l_orderDLCsv.setAdditionalMarginDate(l_intSize, l_adminEqForcedSettleOrderRow.getAdditionalMarginDate());
            //set�o�ߓ���
            if (l_adminEqForcedSettleOrderRow.getAdditionalMarginAccruedDaysIsNull())
            {
                l_orderDLCsv.setMarginAccruedDays(
                    l_intSize, null, l_adminEqForcedSettleOrderRow.getForcedSettleReasonType());
            }
            else
            {
                l_orderDLCsv.setMarginAccruedDays(l_intSize,
                    String.valueOf(l_adminEqForcedSettleOrderRow.getAdditionalMarginAccruedDays()),
                    l_adminEqForcedSettleOrderRow.getForcedSettleReasonType());
            }
            //set�쐬����
            l_orderDLCsv.setCreatedTimestamp(l_intSize, l_adminEqForcedSettleOrderRow.getCreatedTimestamp());
            //set��������
            l_orderDLCsv.setProcessTime(l_intSize, l_adminEqForcedSettleOrderRow.getApproveTimestamp());
            //set���F���
            l_orderDLCsv.setApproveStatus(l_intSize,
                l_adminEqForcedSettleOrderRow.getApproveStatusType(),
                l_adminEqForcedSettleOrderRow.getErrorReasonCode(),
                l_adminEqForcedSettleOrderRow.getForcedSettleReasonType());
            //set���F��
            l_orderDLCsv.setApprover(l_intSize, l_adminEqForcedSettleOrderRow.getApproverCode());
        }

        //createResponse
        WEB3AdminForcedSettleDownloadResponse l_response =
            (WEB3AdminForcedSettleDownloadResponse)l_request.createResponse();
        l_response.downloadFile = l_orderDLCsv.getCsvFileLines();
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
