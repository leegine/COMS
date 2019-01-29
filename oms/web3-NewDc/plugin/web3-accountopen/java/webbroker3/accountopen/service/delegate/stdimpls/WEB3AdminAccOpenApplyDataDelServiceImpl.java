head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�XImpl(WEB3AdminAccOpenApplyDataDelServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 ���m�a(���u) �V�K�쐬 ���f��No.159�ANo.161
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyDataDelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�XImpl)<BR>
 * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelServiceImpl implements WEB3AdminAccOpenApplyDataDelService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelServiceImpl.class);

    /**
     * �����J�ݎ��������f�[�^�폜���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g�̏ꍇ<BR>
     * �@@�|get�������()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@�|validate�폜()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�폜()���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C2665A0340
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

        WEB3GenResponse l_response;

        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminAccOpenApplyDataDelSearchRequest)
        {
            l_response =
                this.getSearchScreen(
                    (WEB3AdminAccOpenApplyDataDelSearchRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccOpenApplyDataDelCnfRequest)
        {
            l_response =
                this.validateDelete(
                    (WEB3AdminAccOpenApplyDataDelCnfRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminAccOpenApplyDataDelCmpRequest)
        {
            l_response =
                this.submitDelete(
                    (WEB3AdminAccOpenApplyDataDelCmpRequest)l_request);
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
     * (get�������)<BR>
     * �����J�ݎ��������f�[�^�폜������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i���������f�[�^�폜�jget������ʁv�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyDataDelSearchResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminAccOpenApplyDataDelSearchResponse getSearchScreen(
        WEB3AdminAccOpenApplyDataDelSearchRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminAccOpenApplyDataDelSearchRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"A0404"(�����J�ݎ��������f�[�^�폜)
        //is�X�V�F�@@true
        //���@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_DATA_DEL, true);

        //createResponse()
        WEB3AdminAccOpenApplyDataDelSearchResponse l_response =
            (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�폜)<BR>
     * �����J�ݎ��������f�[�^�폜�m�F�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i���������f�[�^�폜�jvalidate�폜�v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �@@�@@��̈ʒu : �����J�݌����q�I�u�W�F�N�g�������ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_01318<BR>
     * ==========================================================<BR>
     * �@@�@@��̈ʒu : (*)�폜�s�̏ꍇ�iis�폜�\() == false�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_03142<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyDataDelCnfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminAccOpenApplyDataDelCnfResponse validateDelete(
        WEB3AdminAccOpenApplyDataDelCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDelete(WEB3AdminAccOpenApplyDataDelCnfRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"A0404"(�����J�ݎ��������f�[�^�폜)
        //is�X�V�F�@@true
        //���@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_DATA_DEL, true);

        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //�����J�݌����q�I�u�W�F�N�g�𐶐�����B
        WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = null;

        try
        {
            l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_strInstitutionCode, l_request.requestNumber);
        }
        catch (NotFoundException l_ex)
        {
            //����t���[
            //�����J�݌����q�I�u�W�F�N�g�������ł��Ȃ��ꍇ�A��O���X���[����B
            log.error("�����J�݌����q�����f�[�^�����݂��Ȃ��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01318,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�����J�݌����q�f�[�^���폜�\�𔻒肷��B
        boolean l_blnIsDeletePossible = l_accOpenExpAccountOpen.isDeletePossible();
        // �폜�s�̏ꍇ�iis�폜�\() == false�j�A��O���X���[����B
        if (!l_blnIsDeletePossible)
        {
            log.debug("�����J�݌����q�f�[�^�͍폜�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݌����q�f�[�^�͍폜�ł��܂���B");
        }

        //createResponse( )
        WEB3AdminAccOpenApplyDataDelCnfResponse l_response =
            (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();

        //���X�|���X�f�[�^�v���p�e�B�ɒl���Z�b�g����B
        //�ڋq���i�����j�F�����J�݌����q�I�u�W�F�N�g.get�ڋq���i�����j�i�j
        l_response.accountFamilyName = l_accOpenExpAccountOpen.getAccountFamilyName();
        //�ڋq���i�����j�F�����J�݌����q�I�u�W�F�N�g.get�ڋq���i�����j�i�j
        l_response.accountName = l_accOpenExpAccountOpen.getAccountName();
        //�ڋq���i�J�i�j�F�����J�݌����q�I�u�W�F�N�g.get�ڋq���i�J�i�j�i�j
        l_response.accountFamilyNameKana = l_accOpenExpAccountOpen.getFamilyNameAlt1();
        //�ڋq���i�J�i�j�F�����J�݌����q�I�u�W�F�N�g.get�ڋq���i�J�i�j�i�j
        l_response.accountNameKana = l_accOpenExpAccountOpen.getGivenNameAlt1();
        //�Z���P�F�����J�݌����q�I�u�W�F�N�g.get�Z���P�i�j
        l_response.address1 = l_accOpenExpAccountOpen.getAddressLine1();
        //�Z���Q�F�����J�݌����q�I�u�W�F�N�g.get�Z���Q�i�j
        l_response.address2 = l_accOpenExpAccountOpen.getAddressLine2();
        //�Z���R�F�����J�݌����q�I�u�W�F�N�g.get�Z���R�i�j
        l_response.address3 = l_accOpenExpAccountOpen.getAddressLine3();
        //�Z���P�i�J�i�j�F�����J�݌����q�I�u�W�F�N�g.get�Z���P�i�J�i�j�i�j
        l_response.addressKana1 = l_accOpenExpAccountOpen.getAddressKana1();
        //�Z���Q�i�J�i�j�F�����J�݌����q�I�u�W�F�N�g.get�Z���Q�i�J�i�j�i�j
        l_response.addressKana2 = l_accOpenExpAccountOpen.getAddressKana2();
        //�Z���R�i�J�i�j�F�����J�݌����q�I�u�W�F�N�g.get�Z���R�i�J�i�j�i�j
        l_response.addressKana3 = l_accOpenExpAccountOpen.getAddressKana3();
        //�d�b�ԍ��F�����J�݌����q�I�u�W�F�N�g.get�d�b�ԍ��i�j
        l_response.telephone = l_accOpenExpAccountOpen.getTelephone();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�폜)<BR>
     * �����J�ݎ��������f�[�^�폜�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�����J�݁i���������f�[�^�폜�jsubmit�폜�v�Q�ƁB<BR>
     * ==========================================================<BR>
     * �@@�@@��̈ʒu : (*)�����J�݌����q�I�u�W�F�N�g�������ł��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_01318<BR>
     * ==========================================================<BR>
     * �@@�@@��̈ʒu : �폜�s�̏ꍇ�iis�폜�\() == false�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_03142<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3AdminAccOpenApplyDataDelCmpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminAccOpenApplyDataDelCmpResponse submitDelete(
        WEB3AdminAccOpenApplyDataDelCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitDelete(WEB3AdminAccOpenApplyDataDelCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"A0404"(�����J�ݎ��������f�[�^�폜)
        //is�X�V�F�@@true
        //���@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_DATA_DEL, true);

        //�،���ЃR�[�h���擾����B
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        //validate����p�X���[�h(�p�X���[�h : String)
        //�Ïؔԍ��̏ƍ����s���B
        //[validate����p�X���[�h()�Ɏw�肷�����]
        //�p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_administrator.validateTradingPassword(l_request.password);

        //�����J�݌����q�I�u�W�F�N�g�𐶐�����B
        //[�R���X�g���N�^�̈���]
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()
        //���ʃR�[�h�F�@@���N�G�X�g�f�[�^.���ʃR�[�h
        WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = null;

        try
        {
            l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_strInstitutionCode, l_request.requestNumber);
        }
        catch (NotFoundException l_ex)
        {
            //����t���[
            //�����J�݌����q�I�u�W�F�N�g�������ł��Ȃ��ꍇ�A��O���X���[����B
            log.error("�����J�݌����q�����f�[�^�����݂��Ȃ��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01318,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�����J�݌����q�f�[�^���폜�\�𔻒肷��B
        boolean l_blnIsDeletePossible = l_accOpenExpAccountOpen.isDeletePossible();
        //�폜�s�̏ꍇ�iis�폜�\() == false�j�A��O���X���[����
        if (!l_blnIsDeletePossible)
        {
            log.debug("�����J�݌����q�f�[�^�͍폜�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݌����q�f�[�^�͍폜�ł��܂���B");
        }

        //�����J�݌����q�e�[�u���̃��R�[�h�𕨗��폜����B
        l_accOpenExpAccountOpen.deleteAccOpenExpAccountOpen();

        //createResponse( )
        WEB3AdminAccOpenApplyDataDelCmpResponse l_response =
            (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
