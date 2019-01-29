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
filename	WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғd�q����Q�t���O�X�VImpl(WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/25 ���O(���u) �V�K�쐬 ���f��No.117�ANo.125�ANo.126�ANo.128
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecBatoPreferenceRecordDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecBatoTroubleFlagUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BatoInstBranchPrefRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ғd�q����Q�t���O�X�VImpl)<BR>
 * �Ǘ��ғd�q����Q�t���O�X�V�T�[�r�X�����N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl implements WEB3AdminDirSecBatoTroubleFlagUpdateService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class);

    /**
     * @@roseuid 481155FD01DC
     */
    public WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl()
    {

    }

    /**
     * �d�q����Q�t���O�X�V�������J�n����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���Ǘ��ҁE�ғ��󋵈ꗗ���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�ꗗ���()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�ғ��󋵕ύX�m�F���N�G�X�g�̏ꍇ<BR>
     * �@@this.validate�ύX�m�F���()���R�[������B<BR>
     * <BR>
     * ���Ǘ��ҁE�ғ��󋵕ύX�������N�G�X�g�̏ꍇ<BR>
     * �@@this.submit�ύX�������()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47CE39CA03CE
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
        //���Ǘ��ҁE�ғ��󋵈ꗗ���N�G�X�g�̏ꍇ
        // �@@this.get�ꗗ���()���R�[������B
        if (l_request instanceof WEB3AdminDirSecWorkingListRequest)
        {
            l_response =
                this.getListScreen((WEB3AdminDirSecWorkingListRequest)l_request);
        }
        // ���Ǘ��ҁE�ғ��󋵕ύX�m�F���N�G�X�g�̏ꍇ
        //�@@this.validate�ύX�m�F���()���R�[������B
        else if (l_request instanceof WEB3AdminDirSecWorkingConfirmRequest)
        {
            l_response =
                this.validateChangeConfirmScreen((WEB3AdminDirSecWorkingConfirmRequest)l_request);
        }
        // ���Ǘ��ҁE�ғ��󋵕ύX�������N�G�X�g�̏ꍇ
        //�@@this.submit�ύX�������()���R�[������B
        else if (l_request instanceof WEB3AdminDirSecWorkingCompleteRequest)
        {
            l_response =
                this.submitChangeCompleteScreen((WEB3AdminDirSecWorkingCompleteRequest)l_request);
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
     * (get�ꗗ���)<BR>
     * �ғ��󋵈ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁjget�ꗗ��ʁv�Q�ƁB<BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : ���O�C���Ǘ��҂�"�،���ЊǗ���"�iisDIR�Ǘ���()==false�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ғ��󋵈ꗗ���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecWorkingListResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C24913021D
     */
    protected WEB3AdminDirSecWorkingListResponse getListScreen(
        WEB3AdminDirSecWorkingListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3AdminDirSecWorkingListRequest)";
        log.exiting(STR_METHOD_NAME);

        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N���s���B
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101" �i�V�X�e���Ǘ� �d�q����Q�t���O�X�V�j
        //is�X�V�Ffalse
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            false);

        //�Ǘ��҂�"DIR�Ǘ���"�ł���ꍇ�Atrue��ԋp����B
        //�Ǘ��҂�"�،���ЊǗ���"�ł���ꍇ�Afalse��ԋp����B
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();
        //���O�C���Ǘ��҂�"�،���ЊǗ���"
        //�iisDIR�Ǘ���()==false�j�̏ꍇ�A ��O���X���[����B
        //DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h������������쐬����B
        //[create��������������Ɏw�肷�����]
        //���X�ꗗ�F���N�G�X�g�f�[�^.���X�ꗗ
        String l_strQueryString = this.createQueryString(l_request.branchCode);

        //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�����f�[�^�R���e�i���쐬����B
        //[create���������f�[�^�R���e�i()�Ɏw�肷�����]
        //�،���ЃR�[�h�Fget�،���ЃR�[�h()�̖߂�l
        //���X�ꗗ�F���N�G�X�g�f�[�^.���X�ꗗ
        Object[] l_queryDataContainers = this.createQueryDataContainer(l_strInstitutionCode, l_request.branchCode);

        //create�����\�[�g����( )
        //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�����\�[�g�������쐬����B
        String l_strQuerySortCond = this.createQuerySortCond();

        //get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h(Object[], String, String)
        //�Ǘ��҂������������R�[�h���������A�擾�������R�[�h��List�ŕԋp����B
        //[get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h()�Ɏw�肷�����]
        //���������f�[�^�R���e�i�Fcreate���������f�[�^�R���e�i()�̖߂�l
        //��������������Fcreate��������������()�̖߂�l
        //�����\�[�g������Fcreate�����\�[�g����()�̖߂�l
        List l_lisBatoPreferenceRecords = this.getBatoPreferenceRecord(
            l_queryDataContainers,
            l_strQueryString,
            l_strQuerySortCond);

        //get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�̖߂�l(�ғ��󋵃��R�[�h�ڍ�List)���A
        //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ׌^�z����쐬����B
        //[create�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�()�Ɏw�肷�����]
        //�ғ��󋵃��R�[�h�ڍ�List�Fget�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h()�̖߂�l
        WEB3AdminDirSecBatoPreferenceRecordDetail[] l_adminDirSecBatoPreferenceRecordDetails =
            this.createBatoPreferenceRecordDetail(l_lisBatoPreferenceRecords);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminDirSecWorkingListResponse l_response =
            (WEB3AdminDirSecWorkingListResponse)l_request.createResponse();

        //�v���p�e�B�Z�b�g
        l_response.batoPreferenceRecord = l_adminDirSecBatoPreferenceRecordDetails;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�ύX�m�F���)<BR>
     * �ғ��󋵕ύX�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁjvalidate�X�V�m�F��ʁv�Q�ƁB<BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : ���O�C���Ǘ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��"�،���ЊǗ���"�iisDIR�Ǘ���()==false�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * <BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : ���N�G�X�g�f�[�^�̃V�X�e����Q�t���O�ɕύX<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���Ȃ������ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_02680<BR>
     * ==================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ғ��󋵕ύX�m�F���N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecWorkingConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C2680301FC
     */
    protected WEB3AdminDirSecWorkingConfirmResponse validateChangeConfirmScreen(
        WEB3AdminDirSecWorkingConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeConfirmScreen(WEB3AdminDirSecWorkingConfirmRequest)";
        log.exiting(STR_METHOD_NAME);

        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N���s���B
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101" �i�V�X�e���Ǘ� �d�q����Q�t���O�X�V�j
        //is�X�V�FTRUE
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            true);

        //�Ǘ��҂�"DIR�Ǘ���"�ł���ꍇ�Atrue��ԋp����B
        //�Ǘ��҂�"�،���ЊǗ���"�ł���ꍇ�Afalse��ԋp����B
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();
        
        //���O�C���Ǘ��҂�"�،���ЊǗ���"
        //�iisDIR�Ǘ���()==false�j�̏ꍇ�A ��O���X���[����B
        //DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //���N�G�X�g�f�[�^�̗v�f��loop���������{�B
        //[�\���v�f]
        //�����N�G�X�g�f�[�^
        //�@@���N�G�X�g�f�[�^.�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u���ڍ�(�ύX��)
        int l_intBatoPreferenceRecordCnt = l_request.batoPreferenceRecord.length;

        boolean l_blnChangeDiv = true;
        for (int i = 0; i < l_intBatoPreferenceRecordCnt; i++)
        {
            //�A�C�e���̒�`
            //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h������������쐬����B
            String l_strQueryString = this.createQueryString();

            //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�����f�[�^�R���e�i���쐬����B
            //[create���������f�[�^�R���e�i()�Ɏw�肷�����]
            //�،���ЃR�[�h�Fget�،���ЃR�[�h()�̖߂�l
            //���X�R�[�h�F���N�G�X�g�f�[�^.�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�(�ύX��).���X�R�[�h
            Object[] l_queryDataContainers = this.createQueryDataContainer(
                l_strInstitutionCode, l_request.batoPreferenceRecord[i].branchCode);

            //���݂̓d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h���擾����B
            //[get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h()�Ɏw�肷�����]
            //���������f�[�^�R���e�i�Fcreate���������f�[�^�R���e�i()�̖߂�l
            //��������������Fcreate��������������()�̖߂�l
            //�����\�[�g������Fnull
            List l_lisBatoPreferenceRecords =
                this.getBatoPreferenceRecord(l_queryDataContainers, l_strQueryString, null);

            //���N�G�X�g�f�[�^�̃V�X�e����Q�t���O�ɕύX�����邱�Ƃ��`�F�b�N����B
            //[��r�f�[�^]
            //���ύX�O�f�[�^
            //�@@get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��()�̖߂�l.�V�X�e����Q�t���O
            //���ύX��f�[�^
            //�@@���N�G�X�g�f�[�^.�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�(�ύX��).�V�X�e����Q�t���O
            //�ύX���m�F���ꂽ�i�K��loop������break����B
            BatoInstBranchPrefRow l_batoInstBranchPrefRow = (BatoInstBranchPrefRow)l_lisBatoPreferenceRecords.get(0);
            //���ύX�O�f�[�^
            String l_strBeforeChangeFlag = l_batoInstBranchPrefRow.getSystemFailureFlag();
            //���ύX��f�[�^
            String l_strAfterChangeFlag = l_request.batoPreferenceRecord[i].systemTroubleDiv;

            if (!l_strBeforeChangeFlag.equals(l_strAfterChangeFlag))
            {
                l_blnChangeDiv = false;
                break;
            }
        }

        if (l_blnChangeDiv)
        {
            log.debug("�ύX���ڂ�����܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02680,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX���ڂ�����܂���B");
        }

        WEB3AdminDirSecWorkingConfirmResponse l_response =
            (WEB3AdminDirSecWorkingConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�ύX�������)<BR>
     * �ғ��󋵍X�V������ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�Ǘ��ҁjsubmit�X�V������ʁv�Q�ƁB<BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : ���O�C���Ǘ��҂�"�،���ЊǗ���"�iisDIR�Ǘ���()==false�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * <BR>
     * ==================================================<BR>
     * �@@�@@��̈ʒu : ���N�G�X�g�f�[�^�̃V�X�e����Q�t���O�ɕύX���Ȃ������ꍇ��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@class�@@: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@: BUSINESS_ERROR_02680<BR>
     * ==================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�ғ��󋵕ύX�������N�G�X�g�N���X�B<BR>
     * @@return WEB3AdminDirSecWorkingCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C26838011A
     */
    protected WEB3AdminDirSecWorkingCompleteResponse submitChangeCompleteScreen(
        WEB3AdminDirSecWorkingCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChangeCompleteScreen(WEB3AdminDirSecWorkingCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        l_request.validate();

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N���s���B
        //[validate�����i�j�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F"Z0101" �i�V�X�e���Ǘ� �d�q����Q�t���O�X�V�j
        //is�X�V�FTRUE
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            true);

        //�Ǘ��҂�"DIR�Ǘ���"�ł���ꍇ�Atrue��ԋp����B
        //�Ǘ��҂�"�،���ЊǗ���"�ł���ꍇ�Afalse��ԋp����B
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //���O�C���Ǘ��҂�"�،���ЊǗ���"
        //DIR�Ǘ��҈ȊO�iisDIR�Ǘ���()==false�j�̏ꍇ�A��O���X���[����B
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR�Ǘ��Ҍ����`�F�b�N�G���[�B");
        }

        //validate����p�X���[�h()
        l_administrator.validateTradingPassword(l_request.password);

        // get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get�Ǘ��҃R�[�h( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //���N�G�X�g�f�[�^�̗v�f��loop���������{�B
        //[�\���v�f]
        //�����N�G�X�g�f�[�^
        //�@@���N�G�X�g�f�[�^.�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u���ڍ�(�ύX��)
        int l_intBatoPreferenceRecordCnt = l_request.batoPreferenceRecord.length;

        boolean l_blnChangeDiv = true;
        for (int i = 0; i < l_intBatoPreferenceRecordCnt; i++)
        {

            //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h������������쐬����B
            String l_strQueryString = this.createQueryString();

            //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�����f�[�^�R���e�i���쐬����B
            //[create���������f�[�^�R���e�i()�Ɏw�肷�����]
            //�،���ЃR�[�h�Fget�،���ЃR�[�h()�̖߂�l
            //���X�R�[�h�F
            // ���N�G�X�g�f�[�^.�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�(�ύX��).���X�R�[�h
            Object[] l_queryDataContainers = this.createQueryDataContainer(
                l_strInstitutionCode, l_request.batoPreferenceRecord[i].branchCode);

            //���݂̓d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h���擾���A
            // �擾�������R�[�h��List�ŕԋp����B
            //[get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h()�Ɏw�肷�����]
            //���������f�[�^�R���e�i�Fcreate���������f�[�^�R���e�i()�̖߂�l
            //��������������Fcreate��������������()�̖߂�l
            //�����\�[�g������Fnull
            List l_lisBatoPreferenceRecords =
                this.getBatoPreferenceRecord(
                    l_queryDataContainers,
                    l_strQueryString,
                    null);

            //���N�G�X�g�f�[�^�̃V�X�e����Q�t���O�ɕύX�����邱�Ƃ��`�F�b�N���A
            //��r�f�[�^�ɍ���������ꍇ�A�㑱���������s����B
            //[��r�f�[�^]
            //���ύX�O�f�[�^
            //�@@get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��()�̖߂�l.�V�X�e����Q�t���O
            //���ύX��f�[�^
            // ���N�G�X�g�f�[�^.�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�(�ύX��).�V�X�e����Q�t���O
            BatoInstBranchPrefRow l_batoInstBranchPrefRow =
                (BatoInstBranchPrefRow)l_lisBatoPreferenceRecords.get(0);
            //���ύX�O�f�[�^
            String l_strBeforeChangeFlag = l_batoInstBranchPrefRow.getSystemFailureFlag();
            //���ύX��f�[�^
            String l_strAfterChangeFlag = l_request.batoPreferenceRecord[i].systemTroubleDiv;

            if (!l_strBeforeChangeFlag.equals(l_strAfterChangeFlag))
            {
                //�Y�����R�[�h���X�V����B
                //[update�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��()�Ɏw�肷�����]
                //�V�X�e����Q�t���O�F
                // ���N�G�X�g�f�[�^.�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�(�ύX��).�V�X�e����Q�t���O
                //�X�V����������Fcreate��������������()�̖߂�l
                //�X�V�����f�[�^�R���e�i�Fcreate���������f�[�^�R���e�i()�̖߂�l
                //�X�V�҃R�[�h�Fget�Ǘ��҃R�[�h()�̖߂�l
                this.updateBatoPreference(
                    l_strAfterChangeFlag,
                    l_strQueryString,
                    l_queryDataContainers,
                    l_strAdministratorCode);

                l_blnChangeDiv = false;
            }
        }

        if (l_blnChangeDiv)
        {
            log.debug("�ύX���ڂ�����܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02680,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ύX���ڂ�����܂���B");
        }

        WEB3AdminDirSecWorkingCompleteResponse l_response =
            (WEB3AdminDirSecWorkingCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h������������쐬����B<BR>
     * <BR>
     * �P�j��̕�������쐬����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * �@@�@@"institution_code=?"�@@���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * �@@�@@" and branch_code in (?,?,?�c)" (��)�@@���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@(���@@?�@@��(����)���X�R�[�h�̗v�f�����L�q)<BR>
     * <BR>
     * �S�j�������ԋp����B<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     * @@return String
     * @@roseuid 47CDF643027A
     */
    private String createQueryString(String[] l_strBranchCodes)
    {
        final String STR_METHOD_NAME = "createQueryString(String[])";
        log.entering(STR_METHOD_NAME);
        //��̕�������쐬����B
        StringBuffer l_sbSql = new StringBuffer();

        //�،���ЃR�[�h
        // "institution_code=?"�@@���P�j�̕�����ɒǉ�����B
        l_sbSql.append(" institution_code = ? ");

        //���X�R�[�h
        //" and branch_code in (?,?,?�c)" (��)�@@���P�j�̕�����ɒǉ�����B
        if (l_strBranchCodes.length > 0)
        {
            l_sbSql.append(" and branch_code in ( ? ");
            for (int i = 1; i < l_strBranchCodes.length; i++)
            {
                l_sbSql.append(" , ? ");
            }
            l_sbSql.append(" ) ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbSql.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�����f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�j���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>
     * <BR>
     * �Q�j���������̒ǉ�<BR>
     * �@@�Q�|�P�jList�I�u�W�F�N�g�Ɉȉ���ǉ�<BR>
     * �@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@(����)�،���ЃR�[�h<BR>
     * <BR>
     * �@@�Q�|�Q�jList�I�u�W�F�N�g�Ɉȉ���ǉ�<BR>
     * �@@�@@�@@((����)���X�R�[�h�̗v�f�����A�ȉ������{)<BR>
     * �@@�@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@(����)���X�R�[�h<BR>
     * <BR>
     * �R�jArrayList�C���X�^���X��Object�z��ɕϊ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     * @@return Object[]
     * @@roseuid 47D766370122
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode, String[] l_strBranchCodes)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, String[])";
        log.entering(STR_METHOD_NAME);

        //���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisQueryContainers =  new ArrayList();

        //List�I�u�W�F�N�g�Ɉȉ���ǉ�
        //[add()�Ɏw�肷�����]
        //(����)�،���ЃR�[�h
        l_lisQueryContainers.add(l_strInstitutionCode);

        //List�I�u�W�F�N�g�Ɉȉ���ǉ�
        //((����)���X�R�[�h�̗v�f�����A�ȉ������{)
        //[add()�Ɏw�肷�����]
        //(����)���X�R�[�h
        if (l_strBranchCodes.length > 0)
        {
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                l_lisQueryContainers.add(l_strBranchCodes[i]);
            }
        }

        //ArrayList�C���X�^���X��Object�z��ɕϊ�
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (create�����\�[�g����)<BR>
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�����\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B<BR>
     * <BR>
     * �Q�j�\�[�g�L�[���ڂ��P�j�ō쐬����������ɒǉ�����B<BR>
     * �@@�@@�\�[�g�L�[���ځF���X�R�[�h(�񕨗����Fbranch_code)<BR>
     * <BR>
     * �R�j�쐬�����\�[�g�����������ԋp����B<BR>
     * @@return String
     * @@roseuid 47D771A3011D
     */
    private String createQuerySortCond()
    {
        final String STR_METHOD_NAME = "createQuerySortCond()";
        log.entering(STR_METHOD_NAME);

        //�\�[�g����������I�u�W�F�N�g(�FString)���쐬����B
        StringBuffer l_strQuerySortCond = new StringBuffer();

        //�\�[�g�L�[���ڂ��P�j�ō쐬����������ɒǉ�����B
        //�\�[�g�L�[���ځF���X�R�[�h(�񕨗����Fbranch_code)
        l_strQuerySortCond.append(" branch_code ");

        log.exiting(STR_METHOD_NAME);
        return l_strQuerySortCond.toString();
    }

    /**
     * (get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h)<BR>
     * �Ǘ��҂������������R�[�h���������A�擾�������R�[�h��List�ŕԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@arg0�F�@@�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��RowType<BR>
     * �@@�@@�@@arg1�F�@@(����)��������������<BR>
     * �@@�@@�@@arg2�F�@@(����)�����\�[�g����<BR>
     * �@@�@@�@@arg3�F�@@null<BR>
     * �@@�@@�@@arg4�F�@@(����)���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�@@���������ʂ�0���̏ꍇ�A�G���[��ԋp����B<BR>
     * �@@�@@�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037�j<BR>
     * <BR>
     * �Q�j�P�j �̖߂�l��ԋp����B<BR>
     * @@param l_queryDataContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i�B<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������B<BR>
     * @@param l_strQuerySortCond - (�����\�[�g����)<BR>
     * �����\�[�g�����B<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 47C4FE5E0232
     */
    private List getBatoPreferenceRecord(
        Object[] l_queryDataContainers,
        String l_strQueryString,
        String l_strQuerySortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoPreferenceRecord(Object[], String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisBatoPreferenceRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
            //arg0�F�@@�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��RowType
            //arg1�F�@@(����)��������������
            //arg2�F�@@(����)�����\�[�g����
            //arg3�F�@@null
            //arg4�F�@@(����)���������f�[�^�R���e�i
            l_lisBatoPreferenceRecords = l_queryProcessor.doFindAllQuery(
                BatoInstBranchPrefRow.TYPE,
                l_strQueryString,
                l_strQuerySortCond,
                null,
                l_queryDataContainers);
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

        //�������ʂ�0���̏ꍇ�A�G���[��ԋp����B
        //�G���[���b�Z�[�W�u�����ɊY������f�[�^�����݂��Ȃ��B�v�iBUSINESS_ERROR_01037)
        if (l_lisBatoPreferenceRecords.size() == 0)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisBatoPreferenceRecords;
    }

    /**
     * (create�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�)<BR>
     * get�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�̖߂�l<BR>
     * (�ғ��󋵃��R�[�h�ڍ�List)���A�A�b�v���[�h�e�[�u�����R�[�h�ڍ׌^�z����쐬����B<BR>
     * <BR>
     * �P�jArrayList�I�u�W�F�N�g�̐����B<BR>
     * <BR>
     * �Q�j(����)�ғ��󋵃��R�[�h�ڍ�List�̗v�f���ALoop�����������Ȃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ׃N���X<BR>
     * �@@�@@�@@�̃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@(����)�ғ��󋵃��R�[�h�ڍ�List.���X�R�[�h<BR>
     * �@@�@@�@@(����)�ғ��󋵃��R�[�h�ڍ�List.�V�X�e����Q�t���O<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�P�j�Ő�������ArrayList�I�u�W�F�N�g��<BR>
     * �@@�@@�@@�d�q���V�X�e����Е��X�ʃv���t�����X�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g��add()����B<BR>
     * <BR>
     * �R�j�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ׃N���X�^<BR>
     * �@@�@@�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B<BR>
     * <BR>
     * �S�jtoArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B<BR>
     * <BR>
     * �@@�@@ArrayList�I�u�W�F�N�g.toArray(�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��<BR>
     * �@@�@@���R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g);<BR>
     * <BR>
     * �T�j�ϊ������z��I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_lisWorkingRecordDetailLists - (�ғ��󋵃��R�[�h�ڍ�List)<BR>
     * �ғ��󋵃��R�[�h�ڍ�List�B<BR>
     * @@return WEB3AdminDirSecBatoPreferenceRecordDetail[]
     * @@roseuid 47C393060206
     */
    private WEB3AdminDirSecBatoPreferenceRecordDetail[] createBatoPreferenceRecordDetail(
        List l_lisWorkingRecordDetailLists)
    {
        final String STR_METHOD_NAME = "createBatoPreferenceRecordDetail(List)";
        log.entering(STR_METHOD_NAME);

        //�P�jArrayList�I�u�W�F�N�g�̐����B
        List l_lisBatoPreferenceRecordDetails = new ArrayList();

        //(����)�ғ��󋵃��R�[�h�ڍ�List�̗v�f���ALoop�����������Ȃ��B
        Iterator l_iterator = l_lisWorkingRecordDetailLists.iterator();
        WEB3AdminDirSecBatoPreferenceRecordDetail l_adminDirSecBatoPreferenceRecordDetail = null;
        BatoInstBranchPrefRow l_batoInstBranchPrefRow = null;
        while (l_iterator.hasNext())
        {
            //�Q�|�P�j�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ׃N���X�̃I
            //�u�W�F�N�g�𐶐�����B
            l_adminDirSecBatoPreferenceRecordDetail = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoInstBranchPrefRow = (BatoInstBranchPrefRow)l_iterator.next();

            //�Q�|�P�j�Ő��������I�u�W�F�N�g�Ɉȉ��̓��e���Z�b�g����B
            //(����)�ғ��󋵃��R�[�h�ڍ�List.���X�R�[�h
            //(����)�ғ��󋵃��R�[�h�ڍ�List.�V�X�e����Q�t���O
            l_adminDirSecBatoPreferenceRecordDetail.branchCode = l_batoInstBranchPrefRow.getBranchCode();
            l_adminDirSecBatoPreferenceRecordDetail.systemTroubleDiv =
                l_batoInstBranchPrefRow.getSystemFailureFlag();

            //�P�j�Ő�������ArrayList�I�u�W�F�N�g��
            //�d�q���V�X�e����Е��X�ʃv���t�����X�e�[�u�����R�[�h�ڍ׃I�u�W�F�N�g��add()����B
            l_lisBatoPreferenceRecordDetails.add(l_adminDirSecBatoPreferenceRecordDetail);
        }

        //�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ׃N���X�^
        //�̔z��I�u�W�F�N�g��ArrayList�I�u�W�F�N�g�̃T�C�Y�Ő�������B
        WEB3AdminDirSecBatoPreferenceRecordDetail[] l_adminDirSecBatoPreferenceRecordDetails =
            new WEB3AdminDirSecBatoPreferenceRecordDetail[l_lisBatoPreferenceRecordDetails.size()];

        //toArray()�ŁA���X�g���̗v�f���i�[����z��I�u�W�F�N�g�ɕϊ�����B
        //ArrayList�I�u�W�F�N�g.toArray(
        // �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ׃N���X�^�̔z��I�u�W�F�N�g);
        l_lisBatoPreferenceRecordDetails.toArray(l_adminDirSecBatoPreferenceRecordDetails);

        //�ϊ������z��I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_adminDirSecBatoPreferenceRecordDetails;
    }

    /**
     * (create��������������)<BR>
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h������������쐬����B<BR>
     * <BR>
     * �P�j��̕�������쐬����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * �@@�@@"institution_code=?"�@@���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * �@@�@@" and branch_code=?"�@@���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �S�j�������ԋp����B<BR>
     * @@return String
     * @@roseuid 47D77E4B02C1
     */
    private String createQueryString()
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);

        //��̕�������쐬����B
        StringBuffer l_sbSql = new StringBuffer();

        //�،���ЃR�[�h
        //"institution_code=?"�@@���P�j�̕�����ɒǉ�����B
        l_sbSql.append(" institution_code = ? ");

        //���X�R�[�h
        //" and branch_code=?"�@@���P�j�̕�����ɒǉ�����B
        l_sbSql.append(" and branch_code = ? ");

        log.exiting(STR_METHOD_NAME);
        return l_sbSql.toString();
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�����f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�j���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�<BR>
     * <BR>
     * �Q�j���������̒ǉ�<BR>
     * �@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@(����)�،���ЃR�[�h<BR>
     * �@@�@@[add()�Ɏw�肷�����]<BR>
     * �@@�@@�@@(����)���X�R�[�h<BR>
     * <BR>
     * �R�jArrayList�C���X�^���X��Object�z��ɕϊ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     * @@return Object[]
     * @@roseuid 47D7802B00DE
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, String)";
        log.entering(STR_METHOD_NAME);

        //���������R���e�i�ҏW�p�C���X�^���X�i�FArrayList�j�𐶐�
        List l_lisQueryContainers = new ArrayList();

        //���������̒ǉ�
        //[add()�Ɏw�肷�����]
        //(����)�،���ЃR�[�h
        //[add()�Ɏw�肷�����]
        //(����)���X�R�[�h
        l_lisQueryContainers.add(l_strInstitutionCode);
        l_lisQueryContainers.add(l_strBranchCode);

        //ArrayList�C���X�^���X��Object�z��ɕϊ�
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (update�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��)<BR>
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u���̊Y�����R�[�h���X�V����B<BR>
     * <BR>
     * [where����]<BR>
     * ���،���ЃR�[�h�F(����)�X�V�����f�[�^�R���e�i.�،���ЃR�[�h<BR>
     * �����X�R�[�h�F(����)�X�V�����f�[�^�R���e�i.���X�R�[�h<BR>
     * <BR>
     * [�X�V�J����]<BR>
     * ���V�X�e����Q�t���O�F(����)�V�X�e����Q�t���O<BR>
     * <BR>
     * �P�j�X�V���e�i�[�p�C���X�^���X�i�FMap�j�𐶐�<BR>
     * <BR>
     * �Q�j�X�V���e�̒ǉ�<BR>
     * �@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@key�F"system_failure_flag"<BR>
     * �@@�@@�@@value�F(����)�V�X�e����Q�t���O<BR>
     * �@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@key�F"last_updater"<BR>
     * �@@�@@�@@value�F(����)�X�V�҃R�[�h<BR>
     * �@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@key�F"last_updated_timestamp"<BR>
     * �@@�@@�@@value�FTradingSystem#getSystemTimestamp()�̖߂�l<BR>
     * <BR>
     * �R�j�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u���̍X�V<BR>
     * �@@�@@QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������B<BR>
     * �@@�@@[doUpdateAllQuery()�Ɏw�肷�����]<BR>
     * �@@�@@�@@arg0�F�@@�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��RowType<BR>
     * �@@�@@�@@arg1�F�@@(����)�X�V����������<BR>
     * �@@�@@�@@arg2�F�@@(����)�X�V�����f�[�^�R���e�i<BR>
     * �@@�@@�@@arg3�F�@@�Q�j �ō쐬����Map<BR>
     * @@param l_strSystemTroubleDiv - (�V�X�e����Q�t���O)<BR>
     * �V�X�e����Q�t���O�B<BR>
     * @@param l_strUpdateString - (�X�V����������)<BR>
     * �X�V����������B<BR>
     * @@param l_updateDataContainers - (�X�V�����f�[�^�R���e�i)<BR>
     * �X�V�����f�[�^�R���e�i�B<BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3A45902A4
     */
    private void updateBatoPreference(
        String l_strSystemTroubleDiv,
        String l_strUpdateString,
        Object[] l_updateDataContainers,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateBatoPreference(String, String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�X�V���e�i�[�p�C���X�^���X�i�FMap�j�𐶐�
        Map l_map = new HashMap();

        //�Q�j �X�V���e�̒ǉ�
        //[put()�Ɏw�肷�����]
        //  key�F"system_failure_flag"
        //  value�F(����)�V�X�e����Q�t���O
        //[put()�Ɏw�肷�����]
        //  key�F"last_updater"
        //  value�F(����)�X�V�҃R�[�h
        //[put()�Ɏw�肷�����]
        //  key�F"last_updated_timestamp"
        //  value�FTradingSystem#getSystemTimestamp()�̖߂�l
        l_map.put("system_failure_flag", l_strSystemTroubleDiv);
        l_map.put("last_updater", l_strUpdaterCode);
        l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //�R�j �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u���̍X�V
        //QueryProcessor.doUpdateAllQuery()���\�b�h���R�[������B
        //[doUpdateAllQuery()�Ɏw�肷�����]
        //arg0�F�@@�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u��RowType
        //arg1�F�@@(����)�X�V����������
        //arg2�F�@@(����)�X�V�����f�[�^�R���e�i
        //arg3�F  �Q�j �ō쐬����Map
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                BatoInstBranchPrefRow.TYPE,
                l_strUpdateString,
                l_updateDataContainers,
                l_map);
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
    }
}@
