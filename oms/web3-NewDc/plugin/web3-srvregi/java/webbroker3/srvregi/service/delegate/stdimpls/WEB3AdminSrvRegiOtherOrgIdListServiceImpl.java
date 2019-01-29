head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖﻰ�޽Impl(WEB3AdminSrvRegiOtherOrgIdListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/20 ���n�m (���u) �V�K�쐬�E���f��No.335, No.350, No.351, No.356
Revision History : 2008/03/27 ���g  (���u) �����̖��003
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdListService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖﻰ�޽Impl)<BR>
 * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���n�m<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminSrvRegiOtherOrgIdListServiceImpl
    implements WEB3AdminSrvRegiOtherOrgIdListService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdListServiceImpl.class);

    /**
     * @@roseuid 47D1113002D1
     */
    public WEB3AdminSrvRegiOtherOrgIdListServiceImpl()
    {

    }

    /**
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A<BR>
     * �@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ظ��Ă̏ꍇ<BR>
     * �@@�|get�ꗗ�������()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ظ��Ă̏ꍇ<BR>
     * �@@�|get�ꗗ�Ɖ���()���R�[������B<BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A<BR>
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j<BR>
     * �@@�����X�|���X�f�[�^.errorMessage�ɃZ�b�g����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B931050100
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B
        if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdListSearchRequest)
        {
            // �� �����̃��N�G�X�g�f�[�^���A
            // �@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ظ��Ă̏ꍇ
            // �@@�|get�ꗗ�������()���R�[������BBR>
            WEB3AdminSrvRegiOtherOrgIdListSearchResponse l_response =
                getListSearchScreen(
                    (WEB3AdminSrvRegiOtherOrgIdListSearchRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)
        {
            // �� �����̃��N�G�X�g�f�[�^���A
            // �@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ظ��Ă̏ꍇ
            // �@@�|get�ꗗ�Ɖ���()���R�[������B
            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                getListReferenceScreen(
                    (WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
    }

    /**
     * (get�ꗗ�������)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�T�[�r�X���p)�O���A�gID�ꗗ�Ɖ�Eget�ꗗ������ʁv�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdListSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B931170112
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchResponse getListSearchScreen(
        WEB3AdminSrvRegiOtherOrgIdListSearchRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getListSearchScreen(WEB3AdminSrvRegiOtherOrgIdListSearchRequest)";
        log.entering(STR_METHOD_NAME);

        // getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        // validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        // [����]
        // �@@�\�J�e�S���R�[�h�F�@@�\�J�e�S���R�[�h.�T�[�r�X���p�Ǘ�(�O���A�g)
        // is�X�V�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SRVREGI_OTHERORG,
            false);

        // get�،���ЃR�[�h()
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get���ꏈ���T�[�r�X�}�X�^�[�ꗗ(String, String)
        // [����]
        // �@@�،���ЃR�[�h=�Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h( )�̖߂�l
        // �@@���ꏈ���敪=���N�G�X�g�f�[�^.���ꏈ���敪
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
            new WEB3SrvRegiServiceInfoManagement();

        WEB3SrvRegiServiceMaster[] l_srvRegiServiceMasters =
            l_srvRegiServiceInfoManagement.getSpecialProcessSrvMasterList(
                l_strInstitutionCode,
                l_request.specialProcessDiv);

        int l_intLength = l_srvRegiServiceMasters.length;

        // �T�[�r�X�敪
        String[] l_strSrvDivs = new String[l_intLength];

        // �T�[�r�X����
        String[] l_strSrvNames = new String[l_intLength];

        // �擾�����T�[�r�X�}�X�^�[�̌�������LOOP
        for (int i = 0; i < l_intLength; i++)
        {
            // get�T�[�r�X�敪()
            l_strSrvDivs[i] = l_srvRegiServiceMasters[i].getSrvDiv();

            // get�T�[�r�X����()
            l_strSrvNames[i] = l_srvRegiServiceMasters[i].getSrvName();
        }

        // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ��������ڽ��ݽ()
        // [�R���X�g���N�^�̈���]
        // l_request�F�@@���N�G�X�g�f�[�^
        WEB3AdminSrvRegiOtherOrgIdListSearchResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdListSearchResponse)l_request.createResponse();

        // �v���p�e�B�Z�b�g
        // �T�[�r�X�敪
        l_response.serviceDiv = l_strSrvDivs;

        // �T�[�r�X����
        l_response.serviceName = l_strSrvNames;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ꗗ�Ɖ���)<BR>
     * �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�T�[�r�X���p)�O���A�gID�ꗗ�Ɖ�Eget�ꗗ�Ɖ��ʁv�Q�ƁB<BR>
     * <BR>
     * ==========================================================<BR>
     * �V�[�P���X�}(�u(�T�[�r�X���p)�O���A�gID�ꗗ�Ɖ�Eget�ꗗ�Ɖ��ʁv): <BR>
     * �@@�@@�@@1.9: <�y�[�W���O����><BR>
     * �@@�@@�@@�@@�@@�Q�j�ݒ��A���X�|���X.���y�[�W����0 �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@���X�|���X.�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s<BR>
     * �@@�@@�@@�@@�@@(�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s[ ])<BR>
     * �@@�@@�@@�@@�@@��null���Z�b�g����O���X���[����B<BR>
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_03053<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BaseException
     * @@roseuid 47B94A6502D4
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceResponse getListReferenceScreen(
        WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getListReferenceScreen(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getInstanceFrom���O�C�����()
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

        int l_intLength = l_otherOrgInfoAdminParams.length;

        // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s
        WEB3AdminSrvRegiOtherOrgIdGroup[] l_adminSrvRegiOtherOrgIdGroups =
            new WEB3AdminSrvRegiOtherOrgIdGroup[l_intLength];

        // �擾�����O���A�g���Params�I�u�W�F�N�g�̔z��̌�������LOOP
        for (int i = 0; i < l_intLength; i++)
        {
            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s()(
            WEB3AdminSrvRegiOtherOrgIdGroup l_adminSrvRegiOtherOrgIdGroup =
                new WEB3AdminSrvRegiOtherOrgIdGroup();

            // �v���p�e�B�Z�b�g
            // (*1.1)�ȉ��̒ʂ�A�v���p�e�B�Z�b�g���s���B

            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�ʔ�=
            // �擾�����O���A�g���Params�I�u�W�F�N�g.get�ʔ�( )
            // ��String�^�ɕϊ����ăZ�b�g
            l_adminSrvRegiOtherOrgIdGroup.seqNumber =
                String.valueOf(l_otherOrgInfoAdminParams[i].getSequenceNumber());


            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.ID=
            // �擾�����O���A�g���Params�I�u�W�F�N�g.getID( )
            l_adminSrvRegiOtherOrgIdGroup.id =
                l_otherOrgInfoAdminParams[i].getId();

            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�p�X���[�h=
            // �擾�����O���A�g���Params�I�u�W�F�N�g.get�p�X���[�h( )
            l_adminSrvRegiOtherOrgIdGroup.password =
                l_otherOrgInfoAdminParams[i].getPassword();

            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�X�e�[�^�X=
            // �擾�����O���A�g���Params�I�u�W�F�N�g.get�X�e�[�^�X( )
            String l_strStatus = l_otherOrgInfoAdminParams[i].getStatus();
            l_adminSrvRegiOtherOrgIdGroup.status = l_strStatus;

            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.���X�R�[�h=
            // �擾�����O���A�g���Params�I�u�W�F�N�g.get���X�R�[�h( )
            l_adminSrvRegiOtherOrgIdGroup.branchCode =
                l_otherOrgInfoAdminParams[i].getBranchCode();

            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�����R�[�h=
            // �擾�����O���A�g���Params�I�u�W�F�N�g.get�����R�[�h( ).substring(0,6)
            // �@@�i7����6���j
            if (l_otherOrgInfoAdminParams[i].getAccountCode() != null)
            {
                l_adminSrvRegiOtherOrgIdGroup.accountCode =
                    l_otherOrgInfoAdminParams[i].getAccountCode().substring(0, 6);
            }
            else
            {
                l_adminSrvRegiOtherOrgIdGroup.accountCode = null;
            }

            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�K�p����From=
            // �擾�����O���A�g���Params�I�u�W�F�N�g.get�K�p����From( )
            l_adminSrvRegiOtherOrgIdGroup.appliStartDate =
                l_otherOrgInfoAdminParams[i].getAppliStartDate();

            // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�K�p����To=
            // �擾�����O���A�g���Params�I�u�W�F�N�g.get�K�p����To( )
            l_adminSrvRegiOtherOrgIdGroup.appliEndDate =
                l_otherOrgInfoAdminParams[i].getAppliEndDate();

            if (WEB3OtherOrgStatusDef.DEFAULT.equals(l_strStatus))
            {
                // �擾�����O���A�g���Params�I�u�W�F�N�g.get�X�e�[�^�X( )=
                // '9�F���g�p'�̏ꍇ
                // �@@�@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�ŏI�X�V��=null
                l_adminSrvRegiOtherOrgIdGroup.lastUpdateTime = null;

                // �@@�@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�ŏI�X�V��=null
                l_adminSrvRegiOtherOrgIdGroup.lastUpdater = null;
            }
            else
            {
                // �擾�����O���A�g���Params�I�u�W�F�N�g.get�X�e�[�^�X( )=
                // '9�F���g�p'�ȊO�̏ꍇ
                // �@@�@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�ŏI�X�V��=
                // �@@�@@�擾�����O���A�g���Params�I�u�W�F�N�g.get�X�V���t( )
                l_adminSrvRegiOtherOrgIdGroup.lastUpdateTime =
                    l_otherOrgInfoAdminParams[i].getLastUpdatedTimestamp();

                // �@@�@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s.�ŏI�X�V��=
                // �@@�@@�擾�����O���A�g���Params�I�u�W�F�N�g.get�X�V�҃R�[�h( )
                l_adminSrvRegiOtherOrgIdGroup.lastUpdater =
                    l_otherOrgInfoAdminParams[i].getLastUpdater();
            }

            l_adminSrvRegiOtherOrgIdGroups[i] = l_adminSrvRegiOtherOrgIdGroup;
        }

        // �T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ڽ��ݽ()
        // [�R���X�g���N�^�̈���]
        // l_request�F�@@���N�G�X�g�f�[�^
        WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
            (WEB3AdminSrvRegiOtherOrgIdListReferenceResponse)l_request.createResponse();

        // <�y�[�W���O����>
        // �@@�P�j���X�|���X�̈ȉ��̍��ڂ�ݒ肷��B
        //
        // �@@�����X�|���X.���y�[�W����
        // �@@�@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̗v�f��
        // �@@�@@�����N�G�X�g.�y�[�W���\���s��
        // �@@�@@�@@���]�肪�o��ꍇ�́A�{�P�����l��ݒ�B
        // �@@�@@�@@���T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̗v�f����0
        // �@@�@@�@@(�\���Ώۃf�[�^�Ȃ�)�̏ꍇ�A0���Z�b�g�B
        // �@@�����X�|���X.�����R�[�h��:
        // �@@�@@�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̗v�f��
        // �@@�����X�|���X.�\���y�[�W�ԍ�(�\�������y�[�W�ڂɂ����邩):
        // �@@�@@�@@�@@�ȉ��̏����ɍ��v����̂ł���΁A���N�G�X�g.�v���y�[�W�ԍ��B
        // [�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̗v�f�� >
        // (���N�G�X�g.�y�[�W���\���s���~(���N�G�X�g.�v���y�[�W�ԍ�-1) )]
        // �@@�@@�@@�@@��L�ȊO�̏ꍇ�́A���X�|���X.���y�[�W�������̂܂ܐݒ�B
        // �@@�@@�@@���������ʂ�PR�w����̗v���y�[�W�ԍ��ɖ����Ȃ��ꍇ�́A
        // �@@�@@�@@�@@�ŏI�y�[�W�ɊY������f�[�^�����X�|���X�ɐݒ肷��B
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_adminSrvRegiOtherOrgIdGroups,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

        // �\���y�[�W�ԍ�
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());

        // ���y�[�W��
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        l_response.totalPages = String.valueOf(l_intTotalPages);

        // �����R�[�h��
        l_response.totalRecords =
            String.valueOf(l_pageIndexInfo.getTotalRecords());

        // �@@�Q�j�ݒ��A���X�|���X.���y�[�W����0 �̏ꍇ�́A
        // �@@���X�|���X.�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s
        // �@@(�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s[ ])
        // �@@��null���Z�b�g����O���X���[����B
        //
        // �y�m�肵���T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̂����A
        // �@@���X�|���X�ɐݒ肷�閾�ׂ����߂�B�z
        if (l_intTotalPages == 0)
        {
            l_response.otherOrgIdList = null;

            log.debug("�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s���擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03053,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s���擾�ł��܂���B");
        }

        // �@@�@@�P)�@@(���N�G�X�g.�y�[�W���\���s���~(���X�|���X.�\���y�[�W�ԍ�-1)�����A
        // �@@�@@�@@�@@�@@�m�肵���T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̗v�f��
        // �@@�@@�@@�@@�@@�X�L�b�v����B
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        int l_intResponsePageIndex = Integer.parseInt(l_response.pageIndex);
        int l_intRolledCount = l_intRequestPageSize * (l_intResponsePageIndex - 1);
        int l_intValidRecordCount = l_adminSrvRegiOtherOrgIdGroups.length - l_intRolledCount;
        if (l_intValidRecordCount > l_intRequestPageSize)
        {
            l_intValidRecordCount = l_intRequestPageSize;
        }

        // �@@�@@�Q)�@@��L�Ō��肵���T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̗v�f�ԍ��`
        // �@@�@@�@@�@@(�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̗v�f�ԍ�
        // �@@�@@�@@�@@ �{���N�G�X�g.�y�[�W���\���s��)
        // �@@�@@�@@�@@�܂łɊY������T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ��A
        // �@@�@@�@@�@@���X�|���X�f�[�^.�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s�Z�b�g����B
        //
        // �y���X�|���X�̐ݒ�z
        // �@@�����X�|���X.�T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�׍s��
        // �@@�y�[�W���O������s���Ċm�肳�����T�[�r�X���p�Ǘ��ҊO���A�gID�ꗗ�Ɖ�ׂ̔z��
        WEB3AdminSrvRegiOtherOrgIdGroup[] l_adminSrvRegiOtherOrgIdGroupsValidRecordCount =
            new WEB3AdminSrvRegiOtherOrgIdGroup[l_intValidRecordCount];

        for (int i = 0; i < l_intValidRecordCount; i++)
        {
            l_adminSrvRegiOtherOrgIdGroupsValidRecordCount[i] =
                l_adminSrvRegiOtherOrgIdGroups[l_intRolledCount + i];
        }

        l_response.otherOrgIdList = l_adminSrvRegiOtherOrgIdGroupsValidRecordCount;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
