head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTDocumentListReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʏƉ�T�[�r�XImpl(WEB3AdminFPTDocumentListReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.040
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTBatoProductCodeManagement;
import webbroker3.docadmin.WEB3AdminFPTDocCategoryManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDivManagement;
import webbroker3.docadmin.define.WEB3AdminFPTSortKeyItemDef;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSortKey;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentUpdateInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentListReferenceService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҋ����@@��t���ʏƉ�T�[�r�XImpl)<BR>
 * �Ǘ��ҋ����@@��t���ʏƉ�T�[�r�X�����N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentListReferenceServiceImpl implements WEB3AdminFPTDocumentListReferenceService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListReferenceServiceImpl.class);

    /**
     * @@roseuid 47CBC5AD0177
     */
    public WEB3AdminFPTDocumentListReferenceServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * �����@@��t���ʏƉ�������{����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʏƉ���̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get��t���ʏƉ������()���R�[������B  <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʏƉ�ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@�|get��t���ʏƉ�ꗗ()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C26233001F
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
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʏƉ���̓��N�G�X�g�̏ꍇ
        if (l_request instanceof WEB3AdminFPTDocumentListSearchInputRequest)
        {
            l_response = this.getDocumentReferenceSearchInput(
                (WEB3AdminFPTDocumentListSearchInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��ҋ����@@��t���ʏƉ�ꗗ���N�G�X�g�̏ꍇ
        else if (l_request instanceof WEB3AdminFPTDocumentListReferenceRequest)
        {
            l_response = this.getDocumentReferenceList(
                (WEB3AdminFPTDocumentListReferenceRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get��t���ʏƉ������)<BR>
     * �����@@��t���ʏƉ�����͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �uget��t���ʏƉ�����́v�Q�ƁB <BR>
     * @@param l_request - �Ǘ��ҋ����@@��t���ʏƉ�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3AdminFPTDocumentListSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C3E19501B7
     */
    protected WEB3AdminFPTDocumentListSearchInputResponse getDocumentReferenceSearchInput(
        WEB3AdminFPTDocumentListSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentReferenceSearchInput(WEB3AdminFPTDocumentListSearchInputRequest)";

        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        // ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"G0104"(�����@@��t���ʍX�V�Ɖ�)
        //is�X�V�Ffalse
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, false);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get���X�R�[�h( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //���ʋ敪�Ǘ�(String, String, String, String)
        //[�w�肷�����]
        // �،���ЃR�[�h �F �Ǘ���.get�،���ЃR�[�h() �̖߂�l
        // ���X�R�[�h �F �Ǘ���.get���X�R�[�h() �̖߂�l
        // ���ʋ敪 �F null
        // ���ʎ�ރR�[�h �F null
        WEB3AdminFPTDocDivManagement l_adminFPTDocDivManagement =
            new WEB3AdminFPTDocDivManagement(l_strInstitutionCode, l_strBranchCode, null, null);

        //get���ʋ敪�Ǘ��ꗗ()
        WEB3FPTDocumentDivAdminInfoUnit[] l_fptDocumentDivAdminInfoUnits =
            l_adminFPTDocDivManagement.getDocDivManagementLists();

        WEB3AdminFPTDocumentListSearchInputResponse l_response =
            (WEB3AdminFPTDocumentListSearchInputResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        l_response.documentDivList = l_fptDocumentDivAdminInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get��t���ʏƉ�ꗗ)<BR>
     * �����@@��t���ʏƉ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �uget��t���ʏƉ�ꗗ�v�Q�ƁB <BR>
     * ======================================================== <BR>
     * ��̈ʒu�F�����Ώۃ��R�[�h�����݂��Ȃ�<BR>
     * �iget�d�q�������R�[�h�Ǘ��ꗗ()�̖߂�lList�̒��� == 0�j�ꍇ�A��O���X���[����B<BR>
     * �@@�@@class: WEB3BaseException <BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_00398 <BR>
     * ======================================================== <BR>
     * @@param l_request - �Ǘ��ҋ����@@��t���ʏƉ�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3AdminFPTDocumentListReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C3E29200A1
     */
    protected WEB3AdminFPTDocumentListReferenceResponse getDocumentReferenceList(
        WEB3AdminFPTDocumentListReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentReferenceList(WEB3AdminFPTDocumentListReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        // ���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"G0104"(�����@@��t���ʍX�V�Ɖ�)
        //is�X�V�Ffalse
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, false);

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get���X�R�[�h( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //create��������������
        //�擾�����̕�����𐶐�����B
        //[����]
        //���ʋ敪�Ǘ��ꗗ�F ���N�G�X�g�f�[�^.���ʋ敪�Ǘ��ꗗ
        String l_strQueryString = this.createQueryString(l_request.documentDivList);

        //create���������f�[�^�R���e�i
        //�擾�����ɃZ�b�g����l�̔z��𐶐�����B
        //[����]
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //���X�R�[�h�F get���X�R�[�h�i�j�̖߂�l
        //���ʋ敪�Ǘ��ꗗ�F ���N�G�X�g�f�[�^.���ʋ敪�Ǘ��ꗗ
        Object[] l_objQueryDataContainers = this.createQueryDataContainer(
            l_strInstitutionCode,
            l_strBranchCode,
            l_request.documentDivList);

        // create�\�[�g�L�[
        //�\�[�g�����������ҏW����B
        //[����]
        //�\�[�g�L�[�F ���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortKeys = this.createSortKeys(l_request.sortKeys);

        //get�d�q�������R�[�h�Ǘ��ꗗ
        //�d�q�������R�[�h�Ǘ��e�[�u���Ɍ������s���B
        //[�w�肷�����]
        //  ����������F create����������() �̖߂�l
        //  �����f�[�^�R���e�i�F create�����f�[�^�R���e�i() �̖߂�l
        //  �\�[�g�L�[�F create�\�[�g�L�[() �̖߂�l
        List l_lisBatoProductManagement = WEB3AdminFPTBatoProductCodeManagement.getBatoProductManagementList(
            l_strQueryString,
            l_objQueryDataContainers,
            l_strSortKeys);

        //   �����Ώۃ��R�[�h�����݂��Ȃ��iget�d�q�������R�[�h�Ǘ��ꗗ()�̖߂�lList�̒��� == 0�j�ꍇ�A
        //   �u�Y������f�[�^�����݂��܂���v
        //   (BUSINESS_ERROR_00398)��O���X���[����B
        if (l_lisBatoProductManagement == null || l_lisBatoProductManagement.size() == 0)
        {
            log.debug("�Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");
        }

        //create���ʈꗗ
        //   ���ʎ�ވꗗ���쐬����B
        //   [����]
        //   �d�q�������R�[�h�Ǘ��s�F �d�q�������R�[�h�Ǘ�.get�d�q�������R�[�h�Ǘ��ꗗ() �̖߂�l
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit =
            this.createDocumentList(l_lisBatoProductManagement);

        WEB3AdminFPTDocumentListReferenceResponse l_response =
            (WEB3AdminFPTDocumentListReferenceResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        l_response.documentCategoryList = l_documentUpdateInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�\�[�g�L�[)<BR>
     * �\�[�g�����������ҏW����B<BR>
     * <BR>
     * �e�[�u���񕨗������g�p���A�ȉ��̒ʂ�A�\�[�g����������iorderby��j��ҏW����B<BR>
     * <BR>
     * �P�j����:�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A�\�[�g������������쐬����B<BR>
     * <BR>
     * <BR>
     * �@@�P�|�P�j�����\�[�g�̏ꍇ<BR>
     * <BR>
     * �@@�@@�P�|�P�|�P�j�\�[�g�����[ = �d�q�������R�[�h �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@substr(�d�q�������R�[�h�Ǘ��e�[�u��.bato_product_code,1,3)ASC,<BR>
     * �@@�@@�@@�@@�@@�@@�@@substr(�d�q�������R�[�h�Ǘ��e�[�u��.bato_product_code,4,4)DESC<BR>
     * <BR>
     * <BR>
     * �@@�@@�P�|�P�|�Q�j�\�[�g�����[ = �L���敪�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d�q�������R�[�h�Ǘ��e�[�u��.valid_flag ASC<BR>
     * <BR>
     * <BR>
     * �@@�P�|�Q�j�~���\�[�g�̏ꍇ <BR>
     * <BR>
     * �@@�@@�P�|�Q�|�P�j�\�[�g�����[ = �d�q�������R�[�h �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@substr(�d�q�������R�[�h�Ǘ��e�[�u��.bato_product_code,1,3)DESC,<BR>
     * �@@�@@�@@�@@�@@�@@�@@substr(�d�q�������R�[�h�Ǘ��e�[�u��.bato_product_code,4,4) ASC<BR>
     * <BR>
     * <BR>
     * �@@�@@�P�|�Q�|�Q�j�\�[�g�����[ = �L���敪�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�d�q�������R�[�h�Ǘ��e�[�u��.valid_flag DESC<BR>
     * <BR>
     * <BR>
     * �Q�j �쐬�����\�[�g�����������ԋp����B  <BR>
     * @@param l_sortKeys - �\�[�g�L�[
     * @@return String
     * @@roseuid 47C3E7630361
     */
    private String createSortKeys(WEB3AdminFPTSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortKeys(WEB3AdminFPTSortKey[])";

        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortKey = new StringBuffer();

        //�P�j����:�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��A�\�[�g������������쐬����B
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //�P�|�P�j�����\�[�g�̏ꍇ
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                //�\�[�g�����[ = �d�q�������R�[�h �̏ꍇ
                //substr(�d�q�������R�[�h�Ǘ��e�[�u��.bato_product_code,1,3)ASC,
                //substr(�d�q�������R�[�h�Ǘ��e�[�u��.bato_product_code,4,4)DESC
                if (WEB3AdminFPTSortKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortKey.append(" substr(bato_product_code,1,3) ASC, substr(bato_product_code,4,4) DESC ");
                }
                //�P�|�P�|�Q�j�\�[�g�����[ = �L���敪�̏ꍇ
                //�d�q�������R�[�h�Ǘ��e�[�u��.valid_flag ASC
                if (WEB3AdminFPTSortKeyItemDef.VALID_FLAG.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortKey.append(" valid_flag ASC ");
                }
            }
            //�P�|�Q�j�~���\�[�g�̏ꍇ
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                //�P�|�Q�|�P�j�\�[�g�����[ = �d�q�������R�[�h �̏ꍇ<
                //substr(�d�q�������R�[�h�Ǘ��e�[�u��.bato_product_code,1,3)DESC,
                //substr(�d�q�������R�[�h�Ǘ��e�[�u��.bato_product_code,4,4) ASC
                if (WEB3AdminFPTSortKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortKey.append(" substr(bato_product_code,1,3) DESC, substr(bato_product_code,4,4) ASC ");
                }
                //�P�|�Q�|�Q�j�\�[�g�����[ = �L���敪�̏ꍇ<BR>
                //�d�q�������R�[�h�Ǘ��e�[�u��.valid_flag DESC<BR>
                if (WEB3AdminFPTSortKeyItemDef.VALID_FLAG.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortKey.append(" valid_flag DESC ");
                }
            }

            l_sbSortKey.append(" , ");
        }
        //�쐬�����\�[�g�����������ԋp����B
        String l_strSortKey = l_sbSortKey.toString();

        if (!WEB3StringTypeUtility.isEmpty(l_strSortKey))
        {
            l_strSortKey = l_strSortKey.substring(0, l_strSortKey.length() - 2);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSortKey;
    }

    /**
     * (create���ʈꗗ)<BR>
     * ���ʈꗗ���쐬����B<BR>
     * <BR>
     * �P�j �ԋp�pArrayList���쐬����B<BR>
     * <BR>
     * <BR>
     * �Q�j ����.�d�q�������R�[�h�Ǘ��s�̒�����Loop���s���B�i�C���f�b�N�X�Findex�j<BR>
     * <BR>
     * �@@�Q-�P�j ���ʍX�V���C���X�^���X�𐶐����A�ȉ����Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���ʋ敪 = ����.�d�q�������R�[�h�Ǘ��s[index].get���ʋ敪()<BR>
     * �@@�@@�@@�@@�@@���ʎ�ރR�[�h = <BR>
     * �@@�@@�@@�@@�@@�@@�@@����.�d�q�������R�[�h�Ǘ��s[index].get�d�q�������R�[�h() �̍��R��<BR>
     * �@@�@@�@@�@@�@@���ʒʔ� = ����.�d�q�������R�[�h�Ǘ��s[index].get�d�q�������R�[�h()�̉E�S��<BR>
     * �@@�@@�@@�@@�@@�L���敪 = ����.�d�q�������R�[�h�Ǘ��s[index].get�L���敪()<BR>
     * �@@�@@�@@�@@�@@�E�v = ����.�d�q�������R�[�h�Ǘ��s[index].get�E�v()<BR>
     * �@@�@@�@@�@@�@@�d�q�������R�[�h = ����.�d�q�������R�[�h�Ǘ��s[index].get�d�q�������R�[�h()<BR>
     * <BR>
     * �@@�Q-�Q�j ���ʎ�ޖ��̂��擾����B<BR>
     * <BR>
     * �@@�@@�Q-�Q-�P�j ���ʎ�ޖ��̃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h�F����.�d�q�������R�[�h�Ǘ��s[index].get�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@���X�R�[�h�F ����.�d�q�������R�[�h�Ǘ��s[index].get���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@���ʎ�ރR�[�h�F����.�d�q�������R�[�h�Ǘ��s[index].get�d�q�������R�[�h() �̍��R��<BR>
     * <BR>
     * �@@�@@�Q-�Q-�Q�j ���ʍX�V���.���ʎ�ޖ��̂��Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@���ʎ�ޖ��� = ���ʎ�ޖ���.get���ʎ�ޖ���() �̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���߂�l��null�̏ꍇ��null���Z�b�g����B<BR>
     * <BR>
     * �@@�Q-�R�j ���ʍX�V���C���X�^���X��ArrayList�֒ǉ�����B<BR>
     * <BR>
     * �R�j ArrrayList�����ʍX�V���[]�ɕϊ����A�ԋp����B<BR>
     * @@param l_lisBatoProductCodeManagementParams - �d�q�������R�[�h�Ǘ��s
     * @@return WEB3FPTDocumentUpdateInfoUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47C3EC2601BB
     */
    private WEB3FPTDocumentUpdateInfoUnit[] createDocumentList(List l_lisBatoProductCodeManagementParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createDocumentList(List)";

        log.entering(STR_METHOD_NAME);

        //�ԋp�pArrayList���쐬����B
        ArrayList l_arrayList = new ArrayList();

        int l_intbatoProductCodeManagementParamsCnt = l_lisBatoProductCodeManagementParams.size();

        for (int i = 0; i < l_intbatoProductCodeManagementParamsCnt; i++)
        {
            BatoProductManagementParams l_batoProductManagementParams =
                (BatoProductManagementParams)l_lisBatoProductCodeManagementParams.get(i);
            //���ʍX�V���C���X�^���X�𐶐����A�ȉ����Z�b�g����B
            WEB3FPTDocumentUpdateInfoUnit l_documentUpdateInfoUnit = new WEB3FPTDocumentUpdateInfoUnit();

            //���ʋ敪 = ����.�d�q�������R�[�h�Ǘ��s[index].get���ʋ敪()
            l_documentUpdateInfoUnit.documentDiv = l_batoProductManagementParams.getDocumentDiv();

            //���ʎ�ރR�[�h = ����.�d�q�������R�[�h�Ǘ��s[index].get�d�q�������R�[�h() �̍��R��
            l_documentUpdateInfoUnit.documentCategory =
                l_batoProductManagementParams.getBatoProductCode().substring(0, 3);

            //���ʒʔ� = ����.�d�q�������R�[�h�Ǘ��s[index].get�d�q�������R�[�h()�̉E�S��
            int l_intBatoProductCodeLength = l_batoProductManagementParams.getBatoProductCode().length();
            l_documentUpdateInfoUnit.documentNumber = l_batoProductManagementParams.getBatoProductCode().substring(
                l_intBatoProductCodeLength - 4, l_intBatoProductCodeLength);

            //�L���敪 = ����.�d�q�������R�[�h�Ǘ��s[index].get�L���敪()
            l_documentUpdateInfoUnit.validFlag = l_batoProductManagementParams.getValidFlag();

            //�E�v = ����.�d�q�������R�[�h�Ǘ��s[index].get�E�v()
            l_documentUpdateInfoUnit.remarks = l_batoProductManagementParams.getRemarks();

            //�d�q�������R�[�h = ����.�d�q�������R�[�h�Ǘ��s[index].get�d�q�������R�[�h()
            l_documentUpdateInfoUnit.batoProductCode = l_batoProductManagementParams.getBatoProductCode();

            //�Q-�Q�j ���ʎ�ޖ��̂��擾����B
            //�Q-�Q-�P�j ���ʎ�ޖ��̃I�u�W�F�N�g�𐶐�����B
            //[����]
            //�،���ЃR�[�h�F����.�d�q�������R�[�h�Ǘ��s[index].get�،���ЃR�[�h
            //���X�R�[�h�F ����.�d�q�������R�[�h�Ǘ��s[index].get���X�R�[�h
            //���ʎ�ރR�[�h�F����.�d�q�������R�[�h�Ǘ��s[index].get�d�q�������R�[�h() �̍��R��
            String[] l_strBranchCodes = new String[1];
            l_strBranchCodes[0] = l_batoProductManagementParams.getBranchCode();
            WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement(
                    l_batoProductManagementParams.getInstitutionCode(),
                    l_strBranchCodes,
                    l_batoProductManagementParams.getBatoProductCode().substring(0, 3));

            //�Q-�Q-�Q�j ���ʍX�V���.���ʎ�ޖ��̂��Z�b�g����B
            //���ʎ�ޖ��� = ���ʎ�ޖ���.get���ʎ�ޖ���() �̖߂�l
            //���߂�l��null�̏ꍇ��null���Z�b�g����B
            l_documentUpdateInfoUnit.documentCategoryName = l_docCategoryManagement.getDocumentCateName();

            //���ʍX�V���C���X�^���X��ArrayList�֒ǉ�����B
            l_arrayList.add(l_documentUpdateInfoUnit);
        }

        //ArrrayList�����ʍX�V���[]�ɕϊ����A�ԋp����B
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit =
            new WEB3FPTDocumentUpdateInfoUnit[l_arrayList.size()];
        l_arrayList.toArray(l_documentUpdateInfoUnit);

        log.exiting(STR_METHOD_NAME);
        return l_documentUpdateInfoUnit;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����l�̔z��𐶐�����B <BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h <BR>
     * <BR>
     * �@@�@@����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h <BR>
     * <BR>
     * �@@�@@����.���X�R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j ���ʋ敪�A���ʎ�ރR�[�h<BR>
     * �@@�S-�P�j ����.���ʋ敪�Ǘ��ꗗ�̒��� == 1 �̏ꍇ�A<BR>
     * �@@�@@�@@���ʋ敪�Ǘ��ꗗ.���ʋ敪�A���ʋ敪�Ǘ��ꗗ.���ʎ�ރR�[�h���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�S-�Q�j ����.���ʋ敪�Ǘ��ꗗ�̒��� > 1 �̏ꍇ�A<BR>
     * �@@�@@�@@�z��̑S�Ă̗v�f�� ���ʋ敪�A���ʎ�ރR�[�h���A�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j�������ꂽList����z����擾���A�ԋp����B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_documentDivLists - ���ʋ敪�Ǘ��ꗗ
     * @@return Object[]
     * @@roseuid 47C3F696036B
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode,
        String l_strBranchCode,
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, String, WEB3FPTDocumentDivAdminInfoUnit[])";
        log.entering(STR_METHOD_NAME);

        //�P�j���ArrayList�𐶐�����B
        List l_lisQueryContainers = new ArrayList();

        //�Q�j�،���ЃR�[�h
        //����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B
        l_lisQueryContainers.add(l_strInstitutionCode);

        //�R�j���X�R�[�h
        //����.���X�R�[�h ���P�j��List�ɒǉ�����B
        l_lisQueryContainers.add(l_strBranchCode);

        //�S�j ���ʋ敪�A���ʎ�ރR�[�h
        //�S-�P�j ����.���ʋ敪�Ǘ��ꗗ�̒��� == 1 �̏ꍇ�A
        //���ʋ敪�Ǘ��ꗗ.���ʋ敪�A���ʋ敪�Ǘ��ꗗ.���ʎ�ރR�[�h���P�j��List�ɒǉ�����B
        if (l_documentDivLists.length == 1)
        {
            l_lisQueryContainers.add(l_documentDivLists[0].documentDiv);
            l_lisQueryContainers.add(l_documentDivLists[0].documentCategory);
        }
        //�S-�Q�j ����.���ʋ敪�Ǘ��ꗗ�̒��� > 1 �̏ꍇ�A
        //�z��̑S�Ă̗v�f�� ���ʋ敪�A���ʎ�ރR�[�h���A�P�j��List�ɒǉ�����B
        else if (l_documentDivLists.length > 1)
        {
            for (int i = 0; i < l_documentDivLists.length; i++)
            {
                l_lisQueryContainers.add(l_documentDivLists[i].documentDiv);
                l_lisQueryContainers.add(l_documentDivLists[i].documentCategory);
            }
        }

        //�T�j�������ꂽList����z����擾���A�ԋp����B
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (create��������������)<BR>
     * �擾�����̕�����𐶐�����B <BR>
     * <BR>
     * �P�j ��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j �،���ЃR�[�h <BR>
     * <BR>
     * �@@�@@�@@"institution_code = ? " ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j ���X�R�[�h<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and branch_code = ? " <BR>
     * <BR>
     * �S�j ���ʋ敪�E���ʎ�ރR�[�h<BR>
     * �@@�S-�P�j ����.���ʋ敪�Ǘ��ꗗ�̒��� == 1 �̏ꍇ�A<BR>
     * �@@�@@�@@���ʋ敪�A���ʎ�ރR�[�h(�d�q�������R�[�h���R��)����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += "and document_div = ? and bato_product_code like ?% " <BR>
     * <BR>
     * �@@�S-�Q�j ����.���ʋ敪�Ǘ��ꗗ�̒��� > 1 �̏ꍇ�A<BR>
     * �@@�@@�@@�z��̗v�f�����A���ʋ敪�A���ʎ�ރR�[�h(�d�q�������R�[�h���R��)�̃y�A��<BR>
     * �@@�@@�@@��������������ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �@@�@@�@@�������������� += "and ((document_div = ? and bato_product_code like ?%) or (�c))" <BR>
     * <BR>
     * <BR>
     * �T�j �쐬������������������C���X�^���X��ԋp����B<BR>
     * @@param l_documentDivLists - ���ʋ敪�Ǘ��ꗗ
     * @@return String
     * @@roseuid 47C3F6980243
     */
    private String createQueryString(WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists)
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3FPTDocumentDivAdminInfoUnit[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��̕�����𐶐�����B
        StringBuffer l_sbQueryString = new StringBuffer();

        //�Q�j �،���ЃR�[�h "institution_code = ? " ���P�j�̕�����ɒǉ�����B
        l_sbQueryString.append(" institution_code = ? ");

        //�R�j ���X�R�[�h �������������� += "and branch_code = ? "
        l_sbQueryString.append(" and branch_code = ? ");

        //�S�j ���ʋ敪�E���ʎ�ރR�[�h
        //�S-�P�j ����.���ʋ敪�Ǘ��ꗗ�̒��� == 1 �̏ꍇ�A
        //���ʋ敪�A���ʎ�ރR�[�h(�d�q�������R�[�h���R��)����������������ɒǉ�����B
        //�������������� += "and document_div = ? and bato_product_code like ?% " 
        if (l_documentDivLists.length == 1)
        {
            l_sbQueryString.append(" and document_div = ? and bato_product_code like ? || '%' ");
        }
        //�S-�Q�j ����.���ʋ敪�Ǘ��ꗗ�̒��� > 1 �̏ꍇ�A
        else if (l_documentDivLists.length > 1)
        {
            l_sbQueryString.append(" and ((document_div = ? and bato_product_code like ? || '%') ");
            //�z��̗v�f�����A���ʋ敪�A���ʎ�ރR�[�h(�d�q�������R�[�h���R��)�̃y�A����������������ɒǉ�����B
            //�������������� += "and ((document_div = ? and bato_product_code like ?%) or (�c))"
            for (int i = 1; i < l_documentDivLists.length; i++)
            {
                l_sbQueryString.append(" or (document_div = ? and bato_product_code like ? || '%') ");
            }
            l_sbQueryString.append(")");
        }
        String l_strQueryString = l_sbQueryString.toString();

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
}
@
