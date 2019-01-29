head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҉��菈�������N���T�[�r�XImpl(WEB3AdminDirSecAPMngForcedStartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21  ���� (���u) �V�K�쐬���f�� 132
Revision History : 2008/07/23  ���� (���u) ���f�� 134
Revision History : 2008/07/23  ���� (���u) ���f�� 135
Revision History : 2008/07/30  ���� (���u) ���f�� 136
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.data.ApManagementParams;
import webbroker3.dirsec.data.ApManagementRow;
import webbroker3.dirsec.define.WEB3AdminDirSecSortKeyItemDef;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInfoUnit;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartSortKey;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAPMngForcedStartService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҉��菈�������N���T�[�r�XImpl)<BR>
 * �Ǘ��҉��菈�������N���T�[�r�X�����N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartServiceImpl implements WEB3AdminDirSecAPMngForcedStartService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminDirSecAPMngForcedStartServiceImpl.class);

    /**
     * @@roseuid 488437FD0288
     */
    public WEB3AdminDirSecAPMngForcedStartServiceImpl()
    {

    }

    /**
     * �Ǘ��ҁE���菈�������N�����J�n����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A �ȉ��̃��\�b�h���Ăѕ�����B <BR>
     * <BR>
     * ���Ǘ��ҁE���菈���ꗗ���N�G�X�g�̏ꍇ <BR>
     * �@@this.get���菈���ꗗ()���R�[������B <BR>
     * <BR>
     * ���Ǘ��ҁE���菈�������N�����̓��N�G�X�g�̏ꍇ <BR>
     * �@@this.get���菈�������N������()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���菈�������N���m�F���N�G�X�g�̏ꍇ <BR>
     * �@@this.validate���菈�������N���m�F()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE���菈�������N���������N�G�X�g�̏ꍇ <BR>
     * �@@this.submit���菈�������N������()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 487596B201DF
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
        
        //���Ǘ��ҁE���菈���ꗗ���N�G�X�g�̏ꍇ
        //this.get���菈���ꗗ()���R�[������B
        if (l_request instanceof WEB3AdminDirSecAPMngListRequest)
        {
            l_response = this.getAPMngList(
                (WEB3AdminDirSecAPMngListRequest)l_request);
        }

        //���Ǘ��ҁE���菈�������N�����̓��N�G�X�g�̏ꍇ
        //this.get���菈�������N������()���R�[������B
        else if (l_request instanceof WEB3AdminDirSecAPMngForcedStartInpRequest)
        {
            l_response = this.getAPMngForcedStartInp(
                (WEB3AdminDirSecAPMngForcedStartInpRequest)l_request);
        }

        //���Ǘ��ҁE���菈�������N���m�F���N�G�X�g�̏ꍇ
        //this.validate���菈�������N���m�F()���R�[������B
        else if (l_request instanceof WEB3AdminDirSecAPMngForcedStartCnfRequest)
        {
            l_response = this.validateAPMngForcedStartCnf(
                (WEB3AdminDirSecAPMngForcedStartCnfRequest)l_request);
        }

        //���Ǘ��ҁE���菈�������N���������N�G�X�g�̏ꍇ
        //this.submit���菈�������N������()���R�[������B
        else if (l_request instanceof WEB3AdminDirSecAPMngForcedStartCmpRequest)
        {
            l_response = this.submitAPMngForcedStartCmp(
                (WEB3AdminDirSecAPMngForcedStartCmpRequest)l_request);
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
     * (get���菈���ꗗ)<BR>
     * �Ǘ��҉��菈���ꗗ��ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjget���菈���ꗗ�v�Q�ƁB <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�Ǘ��ҁE���菈�������N�� / �i�Ǘ��ҁjget���菈���ꗗ <BR>
     * ��̈ʒu�FisDIR�Ǘ���( )<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00857 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�Ǘ��ҁE���菈�������N�� / �i�Ǘ��ҁjget���菈���ꗗ <BR>
     * ��̈ʒu�FdoFindAllQuery(rowType : RowType, where : String,<BR>
     * �@@�@@�@@�@@orderBy : String, condition : String, ���X�g : Object[],<BR>
     * �@@�@@�@@�@@�y�[�W�T�C�Y : int, �y�[�W�ԍ� : int)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00398 <BR>
     * ======================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���菈���ꗗ���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAPMngListResponse
     * @@throws WEB3BaseException
     * @@roseuid 487596B201E1
     */
    protected WEB3AdminDirSecAPMngListResponse getAPMngList(WEB3AdminDirSecAPMngListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAPMngList(WEB3AdminDirSecAPMngListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101"(�V�X�e���Ǘ�.���菈�������N��)
        //is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���()
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //ArrayList�I�u�W�F�N�g�̐���
        List l_lisArrays = new ArrayList();

        try
        {
            //createAP���菈���Ǘ��\�[�g����(�\�[�g�L�[�m�n)
            //[createAP���菈���Ǘ��\�[�g����()�Ɏw�肷�����]
            //�\�[�g�L�[�@@�F�@@���N�G�X�g�I�u�W�F�N�g.�\�[�g�L�[
            String l_strAPMngForcedStartSortKey =
                this.createAPMngManageSortCondition(l_request.sortKeys);

            // DB����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisArrays = l_queryProcessor.doFindAllQuery(
                ApManagementRow.TYPE,
                null,
                l_strAPMngForcedStartSortKey,
                null,
                null,
                Integer.parseInt(l_request.pageSize),
                Integer.parseInt(l_request.pageIndex) - 1);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
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

        //���R�[�h���擾�ł��Ȃ��ꍇ�A��O��throw����B
        if (l_lisArrays.size() == 0)
        {
            log.debug("�Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        WEB3PageIndexInfo l_pageInfo = null;

        l_pageInfo = new WEB3PageIndexInfo(
            l_lisArrays,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //ArrayList()
        List l_lisaPMngForcedStartInfoUnit = new ArrayList();

        int l_intLength = l_lisArrays.size();
        
        //�擾�������R�[�h��LOOP�������s���B
        for (int i = 0; i < l_intLength; i++)
        {
            //���菈�����()
            WEB3AdminDirSecAPMngForcedStartInfoUnit l_aPMngForcedStartInfoUnit =
                new WEB3AdminDirSecAPMngForcedStartInfoUnit();

            //(*1)�v���p�e�B�Z�b�g
            //(*1)�ȉ��̒ʂ�v���p�e�B���Z�b�g�B
            ApManagementParams l_apManagementParams =
                (ApManagementParams)l_lisArrays.get(i);

            //���菈�����.�`�[�R�[�h    �� AP���菈���Ǘ�Params.�`�[�R�[�h
            l_aPMngForcedStartInfoUnit.requestCode = l_apManagementParams.getRequestCode();
                
            //���菈�����.PTYPE    �� AP���菈���Ǘ�Params.PTYPE
            l_aPMngForcedStartInfoUnit.pType = l_apManagementParams.getPtype();

            //���菈�����.AP���菈����  �� AP���菈���Ǘ�Params.AP���菈����
            l_aPMngForcedStartInfoUnit.apName = l_apManagementParams.getApName();

            //���菈�����.���ʃR�[�h�L���敪��AP���菈���Ǘ�Params.���ʃR�[�h�L���敪
            l_aPMngForcedStartInfoUnit.requestNumberDiv = l_apManagementParams.getOrderRequestNumberDiv();

            //���菈�����.�X���b�h�ԍ��L���敪��AP���菈���Ǘ�Params.�X���b�h�ԍ��L���敪
            l_aPMngForcedStartInfoUnit.threadNumberDiv = l_apManagementParams.getThreadNumberDiv();

            //add(arg0 : Object)
            //[����]
            //arg0�F ���菈�����I�u�W�F�N�g
            l_lisaPMngForcedStartInfoUnit.add(l_aPMngForcedStartInfoUnit);
        }

        //toArray()
        WEB3AdminDirSecAPMngForcedStartInfoUnit[] l_AadminDirSecAPMngForcedStartInfoUnits =
            new WEB3AdminDirSecAPMngForcedStartInfoUnit[l_lisaPMngForcedStartInfoUnit.size()];
        l_lisaPMngForcedStartInfoUnit.toArray(l_AadminDirSecAPMngForcedStartInfoUnits);

        //getTotalPages()
        int l_intTotalPages = l_pageInfo.getTotalPages();

        //getTotalRecords()
        int l_intTotalRecords = l_pageInfo.getTotalRecords();

        //createResponse()
        WEB3AdminDirSecAPMngListResponse l_response =
            (WEB3AdminDirSecAPMngListResponse)l_request.createResponse();

        //(*2)�v���p�e�B�Z�b�g
        //(*2)���X�|���X�f�[�^�Ɉȉ��̓��e���Z�b�g����B
        //���y�[�W��   �� WEB3StringTypeUtility.formatNumber(totalPages()�̖߂�l)
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_intTotalPages);

        //�����R�[�h��  �� WEB3StringTypeUtility.formatNumber(totalSize()�̖߂�l)
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_intTotalRecords);

        //�\���y�[�W�ԍ� �� ���N�G�X�g�I�u�W�F�N�g.�\���y�[�W�ԍ�
        l_response.pageIndex = l_request.pageIndex;

        //���菈�����ꗗ    �� ���菈�����̔z��
        l_response.apMngInfoList = l_AadminDirSecAPMngForcedStartInfoUnits;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get���菈�������N������)<BR>
     * �Ǘ��҉��菈�������N�����͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjget���菈�������N�����́v�Q�ƁB <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�Ǘ��ҁE���菈�������N�� / �i�Ǘ��ҁjget���菈�������N������ <BR>
     * ��̈ʒu�FisDIR�Ǘ���( )<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00857 <BR>
     * ======================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���菈�������N�����̓��N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 487C46780308
     */
    protected WEB3AdminDirSecAPMngForcedStartInpResponse getAPMngForcedStartInp(
        WEB3AdminDirSecAPMngForcedStartInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAPMngList(WEB3AdminDirSecAPMngListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101"(�V�X�e���Ǘ�.���菈�������N��)
        //is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���()
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //AP���菈���Ǘ��e�[�u���������ɊY�����郌�R�[�h���������A
        //[validateAP���菈���Ǘ�()�Ɏw�肷�����]
        //PTYPE�F���N�G�X�g�f�[�^.PTYPE
        this.validateAPMngManage(l_request.pType);

        //createResponse()
        WEB3AdminDirSecAPMngForcedStartInpResponse l_response =
            (WEB3AdminDirSecAPMngForcedStartInpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (validate���菈�������N���m�F)<BR>
     * �Ǘ��҉��菈�������N���m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjvalidate���菈�������N���m�F�v�Q�ƁB <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�Ǘ��ҁE���菈�������N�� / �i�Ǘ��ҁjsubmit���菈�������N������ <BR>
     * ��̈ʒu�FisDIR�Ǘ���( )<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00857 <BR>
     * ======================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���菈�������N���m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 487596B201E7
     */
    protected WEB3AdminDirSecAPMngForcedStartCnfResponse validateAPMngForcedStartCnf(
        WEB3AdminDirSecAPMngForcedStartCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAPMngForcedStartCnf(WEB3AdminDirSecAPMngForcedStartCnfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101"(�V�X�e���Ǘ�.���菈�������N��)
        //is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���()
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //AP���菈���Ǘ��e�[�u���������ɊY�����郌�R�[�h���������A
        //[validateAP���菈���Ǘ�()�Ɏw�肷�����]
        //PTYPE�F���N�G�X�g�f�[�^.PTYPE
        this.validateAPMngManage(l_request.pType);

        //createResponse()
        WEB3AdminDirSecAPMngForcedStartCnfResponse l_response =
            (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (submit���菈�������N������)<BR>
     * �Ǘ��҉��菈�������N��������ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�Ǘ��ҁjsubmit���菈�������N�������v�Q�ƁB <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F�Ǘ��ҁE���菈�������N�� / �i�Ǘ��ҁjsubmit���菈�������N������ <BR>
     * ��̈ʒu�FisDIR�Ǘ���( )<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00857 <BR>
     * ======================================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE���菈�������N���������N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 487596B201EE
     */
    protected WEB3AdminDirSecAPMngForcedStartCmpResponse submitAPMngForcedStartCmp(
        WEB3AdminDirSecAPMngForcedStartCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAPMngForcedStartCmp(WEB3AdminDirSecAPMngForcedStartCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101"(�V�X�e���Ǘ�.���菈�������N��)
        //is�X�V�Ftrue
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR�Ǘ���()
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR�Ǘ��҈ȊO�̏ꍇ�iisDIR�Ǘ���()==false�j��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //validate����p�X���[�h(�p�X���[�h : String)
        //[validate����p�X���[�h()�Ɏw�肷�����]
        //�p�X���[�h�@@�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        //AP���菈���Ǘ��e�[�u���������ɊY�����郌�R�[�h���������A
        //[validateAP���菈���Ǘ�()�Ɏw�肷�����]
        //PTYPE�F���N�G�X�g�f�[�^.PTYPE
        this.validateAPMngManage(l_request.pType);

        //createResponse()
        WEB3AdminDirSecAPMngForcedStartCmpResponse l_response =
            (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (createAP���菈���Ǘ��\�[�g����)<BR>
     * AP���菈���Ǘ������\�[�g�������쐬����B <BR>
     * <BR>
     * �P�j�@@�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�@@�@@�쐬�����\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �@@�E�u�`�[�R�[�h�v	���@@AP���菈���Ǘ�.�`�[�R�[�h<BR>
     * �@@�E�uPTYPE�v	���@@AP���菈���Ǘ�.PTYPE<BR>
     * �@@�E�uAP���菈�����v	���@@AP���菈���Ǘ�.AP���菈����<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)<BR>
     * �@@�@@�@@�@@���\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �R�j�@@�쐬�����\�[�g�����������ԋp����B <BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[�N���X�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 487F0087016B
     */
    private String createAPMngManageSortCondition(WEB3AdminDirSecAPMngForcedStartSortKey[] l_sortKeys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHostManageSortCondition(WEB3AdminDirSecAPMngForcedStartSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B
        StringBuffer l_sbSortQueryString = new StringBuffer();

        //�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A
            //�쐬�����\�[�g����������ɒǉ�����B
            //�E�u�`�[�R�[�h�v  ���@@AP���菈���Ǘ�.�`�[�R�[�h
            if (WEB3AdminDirSecSortKeyItemDef.REQUEST_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("request_code ");
            }
            //�E�uPTYPE�v  ���@@AP���菈���Ǘ�.PTYPE
            else if (WEB3AdminDirSecSortKeyItemDef.PTYTE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("ptype ");
            }
            //�E�uAP���菈�����v    ���@@AP���菈���Ǘ�.AP���菈����
            else if (WEB3AdminDirSecSortKeyItemDef.AP_NAME.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("ap_name ");
            }
            else 
            {
                continue;
            }

            //�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)
            //���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("asc,");
            }
            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("desc,");
            }
        }

        //�쐬�����\�[�g�����������ԋp����B
        String l_strSortString = l_sbSortQueryString.toString();

        if ((!"".equals(l_strSortString)) && l_strSortString.charAt(l_strSortString.length() - 1) == ',')
        {
            l_strSortString = l_strSortString.substring(0, l_strSortString.length() - 1);
        }

        log.exiting(STR_METHOD_NAME);

        return l_strSortString;
    }

    /**
     * (validateAP���菈���Ǘ�)<BR>
     * AP���菈���Ǘ��e�[�u���������ɊY�����郌�R�[�h���������A<BR>
     * �f�[�^���擾�ł��Ȃ��ꍇ�A��O��throw����B<BR>
     * <BR>
     * �P�j�@@AP���菈���Ǘ��e�[�u������ <BR>
     * �@@�ȉ��̏����ŁAAP���菈���Ǘ��e�[�u������������B<BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�@@PTYPE�F�@@����.PTYPE<BR>
     * <BR>
     * �Q�j�@@���R�[�h���擾�ł��Ȃ��ꍇ�A��O��throw����B<BR>
     * �@@�@@�G���[���b�Z�[�W�uBUSINESS_ERROR_00398�i�Y������f�[�^����<BR>
     * �݂��܂���B�j�v<BR>
     * @@param l_strPtype - (PTYPE)<BR>
     * PTYPE�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 487716D701B8
     */
    private void validateAPMngManage(String l_strPtype) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAPMngManage(String)";
        log.entering(STR_METHOD_NAME);

        //��������������̐���
        String l_strWhere = " ptype = ? ";

        //���������R���e�i�̐���
        Object[] l_values = {l_strPtype};

        //ArrayList�I�u�W�F�N�g�̐���
        List l_lisArrays = new ArrayList();

        try
        {
            //AP���菈���Ǘ��e�[�u������
            //�ȉ��̏����ŁAAP���菈���Ǘ��e�[�u������������B
            //[��������]
            //PTYPE�F�@@����.PTYPE
            l_lisArrays =
                Processors.getDefaultProcessor().doFindAllQuery(
                    ApManagementRow.TYPE,
                    l_strWhere,
                    l_values);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
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

        //���R�[�h���擾�ł��Ȃ��ꍇ�A��O��throw����B
        //�G���[���b�Z�[�W�uBUSINESS_ERROR_00398�i�Y������f�[�^�����݂��܂���B�j�v
        if (l_lisArrays.isEmpty())
        {
            log.debug("�Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
