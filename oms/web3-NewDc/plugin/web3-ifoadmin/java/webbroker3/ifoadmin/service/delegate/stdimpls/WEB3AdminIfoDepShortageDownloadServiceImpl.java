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
filename	WEB3AdminIfoDepShortageDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�XImpl(WEB3AdminIfoDepShortageDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27�@@����(���u) �V�K�쐬 ���f��No.006
Revision History : 2009/03/09�@@����(���u) �V�K�쐬 ���f��No.014
Revision History : 2009/04/10�@@�����F(���u) ���f��No.017
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.ifoadmin.WEB3AdminIfoDataManager;
import webbroker3.ifoadmin.WEB3AdminIfoDepShortageDownloadCsv;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageDownloadResponse;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageInfo;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageDownloadService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�XImpl)<BR>
 * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageDownloadServiceImpl extends WEB3AdminIfoDepShortageReferenceServiceImpl
    implements WEB3AdminIfoDepShortageDownloadService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageDownloadServiceImpl.class);

    /**
     * @@roseuid 49A76E52003E
     */
    public WEB3AdminIfoDepShortageDownloadServiceImpl()
    {

    }

    /**
     * �Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X�������s���B <BR>
     * <BR>
     * �ȉ��̃��\�b�h���R�[������B <BR>
     * <BR>
     * get�_�E�����[�h�t�@@�C��() <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 499B92EA01BE
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

        if (!(l_request instanceof WEB3AdminIfoDepShortageDownloadRequest))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3AdminIfoDepShortageDownloadRequest l_depShortageDownloadRequest =
            (WEB3AdminIfoDepShortageDownloadRequest)l_request;

        //get�_�E�����[�h�t�@@�C��()
        WEB3AdminIfoDepShortageDownloadResponse l_response =
            this.getDownloadFile(l_depShortageDownloadRequest);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C���擾�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�jget�_�E�����[�h�t�@@�C���v �Q�� <BR>
     * ===================================================<BR>
     * �@@�V�[�P���X�} : (�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X)get�_�E�����[�h�t�@@�C��<BR>
     * �@@��̈ʒu : is�؋����s�����[�����M��(String, String)<BR>
     * �@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^ �D�������t ���@@�u���c�Ɠ��v(*)�@@�����@@<BR>
     * �@@�@@�@@�@@�@@�@@is�؋����s�����[�����M�ρi�j�̖߂�l == false�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�u�{���̏؋����s���͂܂��m�F���Ă��܂���B�v�̗�O���X���[����B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag : BUSINESS_ERROR_03157<BR>
     * ===================================================<BR>
     * ===================================================<BR>
     * �@@�V�[�P���X�} : (�Ǘ��ҁE�؋����s���󋵃_�E�����[�h�T�[�r�X)get�_�E�����[�h�t�@@�C��<BR>
     * �@@��̈ʒu : create�؋����s���󋵈ꗗ�̖߂�l�̗v�f����0�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�u�����ɊY������؋����s���󋵏�񂪂���܂���v�̗�O��throw����<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag : BUSINESS_ERROR_03158<BR>
     * ===================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminIfoDepShortageDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 499B94240224
     */
    protected WEB3AdminIfoDepShortageDownloadResponse getDownloadFile(
        WEB3AdminIfoDepShortageDownloadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminIfoDepShortageDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�؋����s���󋵏Ɖ�
        //is�X�V�F�@@false�i�X�V�Ȃ��j
        l_admin.validateAuthority(WEB3TransactionCategoryDef.DEPOSIT_SHORTAGE_REFERENCE, false);

        //get�،����()
        Institution l_institution = l_admin.getInstitution();

        //validate���X����(���X�R�[�h : String[])
        //[����]
        //���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ
        l_admin.validateBranchPermission(l_request.branchCode);

        //is�؋����s�����[�����M��(String, String)
        //[����]
        //�،���ЃR�[�h�F�@@get�،����()�̖߂�l�D�،���ЃR�[�h
        //���X�R�[�h �F�@@���N�G�X�g�f�[�^.���X�R�[�h�ꗗ[0]
        boolean l_blnIfoDepositMailFlag =
            WEB3AdminIfoDataManager.isIfoDepositMailFlag(
                l_institution.getInstitutionCode(),
                l_request.branchCode[0]);

        //���c�Ɠ�
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //���N�G�X�g�f�[�^ �D�������t ���@@�u���c�Ɠ��v(*)�@@����
        //is�؋����s�����[�����M�ρi�j�̖߂�l == false�̏ꍇ
        //�u�{���̏؋����s���͂܂��m�F���Ă��܂���B�v�̗�O���X���[����B
        if (WEB3DateUtility.compareToDay(l_request.searchDate, l_datBizDate) == 0 && !l_blnIfoDepositMailFlag)
        {
            log.debug("�{���̏؋����s���͂܂��m�F���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03157,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�{���̏؋����s���͂܂��m�F���Ă��܂���B");
        }

        //get�؋������ꗗ(�،����, �Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g)
        //[����]
        //�،���ЁF�@@get�،����()�̖߂�l
        //���N�G�X�g�f�[�^�F�@@�؋����s���󋵃_�E�����[�h���N�G�X�g
        IfoDepositRow[] l_ifoDepositRows =
            WEB3AdminIfoDataManager.getDepositInfoList(l_institution, l_request);

        //create�؋����s���󋵈ꗗ(String, �؋���Row[], �Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g)
        //[����]
        //�،���ЃR�[�h�Fget�،����()�̖߂�l�D�،���ЃR�[�h
        //�؋������ꗗ�F�@@get�؋������ꗗ()�̖߂�l
        //���N�G�X�g�f�[�^�F�؋����s���󋵃_�E�����[�h���N�G�X�g
        WEB3IfoDepShortageInfo[] l_ifoDepShortageInfos =
            this.createDepShortageList(
                l_institution.getInstitutionCode(),
                l_ifoDepositRows,
                l_request);

        //(*)create�؋����s���󋵈ꗗ�̖߂�l�̗v�f����0�̏ꍇ
        if (l_ifoDepShortageInfos.length == 0)
        {
            log.debug("�����ɊY������؋����s���󋵏�񂪂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03158,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������؋����s���󋵏�񂪂���܂���B");
        }

        //sort�؋����s���󋵈ꗗ(�؋����s���󋵏��[], �؋����s���󋵃\�[�g�L�[[])
        //[����]
        //�؋����s���󋵈ꗗ �Fcreate�؋����s���󋵈ꗗ()�̖߂�l
        //�\�[�g�L�[�z��F���N�G�X�g�f�[�^.�\�[�g�L�[
        WEB3IfoDepShortageInfo[] l_sortIfoDepShortageInfos =
            this.sortDepShortageList(l_ifoDepShortageInfos, l_request.sortKeys);

        //�؋����s���󋵃_�E�����[�hCSV()
        WEB3AdminIfoDepShortageDownloadCsv l_depShortageDownloadCsv =
            new WEB3AdminIfoDepShortageDownloadCsv(l_request.searchDate);

        //(*)sort�؋����s���󋵈ꗗ�̖߂�l�̗v�f�����ALoop����
        int l_intLength = l_sortIfoDepShortageInfos.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //add���׍s()
            int l_intAddRow = l_depShortageDownloadCsv.addRow();

            //set���X�R�[�h(int, String)
            //[����]
            //�s�ԍ��F add���׍s()�̖߂�l
            //���X�R�[�h�Fsort�؋����s���󋵈ꗗ�̖߂�l[index].���X�R�[�h
            l_depShortageDownloadCsv.setBranchCode(
                l_intAddRow, l_sortIfoDepShortageInfos[i].branchCode);

            //set�ڋq�R�[�h(int, String)
            //[����]
            //�s�ԍ��F add���׍s()�̖߂�l
            //�ڋq�R�[�h�Fsort�؋����s���󋵈ꗗ�̖߂�l[index].�ڋq�R�[�h
            l_depShortageDownloadCsv.setAccountCode(
                l_intAddRow, l_sortIfoDepShortageInfos[i].accountCode);

            //set�ڋq��(int, String)
            //[����]
            //�s�ԍ��F add���׍s()�̖߂�l
            //�ڋq���F�@@sort�؋����s���󋵈ꗗ�̖߂�l[index].�ڋq��
            l_depShortageDownloadCsv.setAccountName(
                l_intAddRow, l_sortIfoDepShortageInfos[i].accountName);

            //set�����z(int, String)
            //[����]
            //�s�ԍ��F add���׍s()�̖߂�l
            //�����z�F�@@sort�؋����s���󋵈ꗗ�̖߂�l[index].�����z
            l_depShortageDownloadCsv.setClaimAmount(
                l_intAddRow, l_sortIfoDepShortageInfos[i].claimAmount);

            //set���ݖ������z(int, String)
            //[����]
            //�s�ԍ��F add���׍s()�̖߂�l
            //���ݖ������z�F�@@sort�؋����s���󋵈ꗗ�̖߂�l[index].���ݖ������z
            l_depShortageDownloadCsv.setCurNonPayAmt(
                l_intAddRow, l_sortIfoDepShortageInfos[i].curNonPayAmt);

            //set���ݏ؋������v�z(int, String)
            //[����]
            //�s�ԍ��F add���׍s()�̖߂�l
            //���ݏ؋������v�z�F�@@sort�؋����s���󋵈ꗗ�̖߂�l[index].���ݏ؋������v�z
            l_depShortageDownloadCsv.setCurIfoDepositNecessaryAmt(
                l_intAddRow, l_sortIfoDepShortageInfos[i].curIfoDepositNecessaryAmt);

            //set���ʗL���t���O(int, boolean)
            //[����]
            //�s�ԍ��F add���׍s()�̖߂�l
            //���ʗL���t���O�F�@@sort�؋����s���󋵈ꗗ�̖߂�l[index].���ʗL���t���O
            l_depShortageDownloadCsv.setContractExistFlag(
                l_intAddRow, l_sortIfoDepShortageInfos[i].contractExistFlag);

            //set�����L���t���O(int, boolean)
            //[����]
            //�s�ԍ��F add���׍s()�̖߂�l
            //�����L���t���O�F�@@sort�؋����s���󋵈ꗗ�̖߂�l[index].�����L���t���O
            l_depShortageDownloadCsv.setOrderExistFlag(
                l_intAddRow, l_sortIfoDepShortageInfos[i].orderExistFlag);
        }

        //getCSV�t�@@�C���s()
        String[] l_strCsvFileLines = l_depShortageDownloadCsv.getCsvFileLines();

        //createResponse()
        WEB3AdminIfoDepShortageDownloadResponse l_response =
            (WEB3AdminIfoDepShortageDownloadResponse)l_request.createResponse();

        //(*)�v���p�e�B�Z�b�g
        l_response.downloadFile = l_strCsvFileLines;
        l_response.dispDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
