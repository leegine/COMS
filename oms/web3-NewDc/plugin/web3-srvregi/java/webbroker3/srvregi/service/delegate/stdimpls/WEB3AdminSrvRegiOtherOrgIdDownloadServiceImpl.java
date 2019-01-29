head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽(WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/20 ���n�m (���u) �V�K�쐬�E���f��No.336, No.351, No.356
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdDownloadService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadResponse;
import webbroker3.srvregi.WEB3AdminSrvRegiOtherOrgIdDownloadCsv;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽Impl)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�޻��޽�����N���X<BR>
 * <BR>
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl
    implements WEB3AdminSrvRegiOtherOrgIdDownloadService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl.class);

    /**
     * @@roseuid 47D1112F01D7
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�ޏ������s���B<BR>
     * <BR>
     * �P�j�@@�Ɩ������ݒ�<BR>
     * �@@������ԊǗ�.setBusinessTimestamp()���R�[������B<BR>
     * <BR>
     * �Q�j�@@get�_�E�����[�h�t�@@�C��()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B943240129
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            String l_strErrorMessage = "�p�����[�^�l�s���B";
            log.debug(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j�@@�Ɩ������ݒ�
        //�@@������ԊǗ�.setBusinessTimestamp()���R�[������B
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();

        // �Q�j�@@get�_�E�����[�h�t�@@�C��()���R�[������B
        if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdDownloadRequest)
        {

            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                getDownloadFile(
                    (WEB3AdminSrvRegiOtherOrgIdDownloadRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            String l_strErrorMessage = "�p�����[�^�^�C�v�s���B";
            log.debug(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
    }

    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰�ރt�@@�C���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�T�[�r�X���p)�O���A�gID�Ɖ��޳�۰�ށEget�_�E�����[�h�t�@@�C���v�Q��<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B94328036B
     */
    protected WEB3AdminSrvRegiOtherOrgIdDownloadResponse getDownloadFile(
        WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDownloadFile(WEB3AdminSrvRegiOtherOrgIdDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        // [����]
        // �@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�T�[�r�X���p�Ǘ�(�O���A�g)
        // is�X�V�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
            false);

        // validate���X����(���X�R�[�h : String[])
        // [����]
        // �@@���X�R�[�h�F���N�G�X�g�f�[�^.���X�R�[�h��z��Ƃ��Ĉ����n��
        l_administrator.validateBranchPermission(l_request.branchCode);

        // get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get�O���A�g���ꗗ(
        //    long, String, String, String, String, String[],
        //    String, Timestamp, Timestamp, �T�[�r�X���p�\�[�g�L�[[ ])
        // [����]
        // �@@�@@�ʔԁF���N�G�X�g�f�[�^.�ʔ�
        // �@@�@@�T�[�r�X�敪�F���N�G�X�g�f�[�^.�T�[�r�X�敪
        // �@@�@@ID�F���N�G�X�g�f�[�^.ID
        // �@@�@@�X�e�[�^�X�F���N�G�X�g�f�[�^.�X�e�[�^�X
        // �@@�@@�،���ЃR�[�h�Fget�،���ЃR�[�h( )�̖߂�l
        // �@@�@@���X�R�[�h�F���N�G�X�g�f�[�^.���X�R�[�h�̔z��
        // �@@�@@�����R�[�h�F���N�G�X�g�f�[�^.�����R�[�h
        // �@@�@@�K�p�J�n���i���j�F���N�G�X�g�f�[�^.�K�p�J�n���i���j
        // �@@�@@�K�p�J�n���i���j�F���N�G�X�g�f�[�^.�K�p�J�n���i���j
        // �@@�@@�\�[�g�����F���N�G�X�g�f�[�^.�T�[�r�X���p�\�[�g�L�[
        WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
            (WEB3SrvRegiOtherOrgService)Services.getService(
                WEB3SrvRegiOtherOrgService.class);

        Timestamp l_tsAppliStartFrom = null;
        if (l_request.appliStartFrom != null)
        {
            l_tsAppliStartFrom =
                new Timestamp(l_request.appliStartFrom.getTime());
        }

        Timestamp l_tsAppliStartTo = null;
        if (l_request.appliStartTo != null)
        {
            l_tsAppliStartTo =
                new Timestamp(l_request.appliStartTo.getTime());
        }

        OtherOrgInfoAdminParams[] l_otherOrgInfoAdminParams =
            l_srvRegiOtherOrgService.getOtherOrgInfoList(
                l_request.seqNumber,
                l_request.serviceDiv,
                l_request.id,
                l_request.status,
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode,
                l_tsAppliStartFrom,
                l_tsAppliStartTo,
                l_request.sortKeys);

        // �O���A�gID�Ɖ��޳�۰��CSV( )
        // create�J�����w�b�_( )
        WEB3AdminSrvRegiOtherOrgIdDownloadCsv l_adminSrvRegiOtherOrgIdDownloadCsv =
            new WEB3AdminSrvRegiOtherOrgIdDownloadCsv();

        int l_intLength = l_otherOrgInfoAdminParams.length;

        // �_�E�����[�h�t�@@�C��
        String[] l_strDownloadFiles = new String[l_intLength];

        // �擾�����O���A�g���Params�I�u�W�F�N�g�̔z��
        // �iget�O���A�g���ꗗ()�̖߂�l�j��������LOOP
        for (int i = 0; i < l_intLength; i++)
        {
            // add���׍s( )
            int l_intLineNo = l_adminSrvRegiOtherOrgIdDownloadCsv.addRow();

            // set�ʔ�(int, long)
            // [����]
            // �s�ԍ��@@�@@       �F�@@add���׍s()�̖߂�l
            // �ʔ�              �F�@@�O���A�g���Params[index].get�ʔ�()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setSequenceNumber(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getSequenceNumber() + "");

            // setID(int, String)
            // [����]
            // �s�ԍ��@@�@@       �F�@@add���׍s()�̖߂�l
            // ID                 �F�@@�O���A�g���Params[index].getID()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setId(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getId());

            // set�X�e�[�^�X(int, String)
            // [����]
            // �s�ԍ��@@�@@       �F�@@add���׍s()�̖߂�l
            // �X�e�[�^�X         �F�@@�O���A�g���Params[index].get�X�e�[�^�X()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setStatus(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getStatus());

            // set�،���ЃR�[�h(int, String)
            // [����]
            // �s�ԍ��@@�@@       �F�@@add���׍s()�̖߂�l
            // �،���ЃR�[�h �F�@@�O���A�g���Params[index].get�،���ЃR�[�h()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setInstitutionCode(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getInstitutionCode());

            // set���X�R�[�h(int, String)
            // [����]
            // �s�ԍ��@@�@@       �F�@@add���׍s()�̖߂�l
            // ���X�R�[�h       �F�@@�O���A�g���Params[index].get���X�R�[�h()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setBranchCode(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getBranchCode());

            // set�����R�[�h(int, String)
            // [����]
            // �s�ԍ��@@�@@       �F�@@add���׍s()�̖߂�l
            // �����R�[�h       �F�@@�O���A�g���Params[index].get�����R�[�h().substring(0,6)
            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i7����6���j
            if (l_otherOrgInfoAdminParams[i].getAccountCode() != null)
            {
                l_adminSrvRegiOtherOrgIdDownloadCsv.setAccountCode(
                    l_intLineNo,
                    l_otherOrgInfoAdminParams[i].getAccountCode().substring(0,6));
            }
            else
            {
                l_adminSrvRegiOtherOrgIdDownloadCsv.setAccountCode(
                    l_intLineNo,
                    null);
            }

            // set�K�p����From(int, Date)
            // [����]
            // �s�ԍ��@@�@@       �F�@@add���׍s()�̖߂�l
            // �K�p����From  �F�@@�O���A�g���Params[index].get�K�p����From()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setAppliStartDate(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getAppliStartDate());

            // set�K�p����To(int, Date)
            // [����]
            // �s�ԍ��@@�@@       �F�@@add���׍s()�̖߂�l
            // �K�p����To     �F�@@�O���A�g���Params[index].get�K�p����To()
            l_adminSrvRegiOtherOrgIdDownloadCsv.setAppliEndDate(
                l_intLineNo,
                l_otherOrgInfoAdminParams[i].getAppliEndDate());
        }

        // getCSV�t�@@�C���s( )
        l_strDownloadFiles = l_adminSrvRegiOtherOrgIdDownloadCsv.getCsvFileLines();

        // �T�[�r�X���p�Ǘ��ҊO���A�gID�Ɖ��޳�۰��ڽ��ݽ( )
        //[�R���X�g���N�^�̈���]
        // l_request�F�@@���N�G�X�g�f�[�^
        WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdDownloadResponse)l_request.createResponse();

        // �v���p�e�B�Z�b�g
        // �_�E�����[�h�t�@@�C��
        l_response.lines = l_strDownloadFiles;

        // ���ݓ���
        l_response.currentDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
