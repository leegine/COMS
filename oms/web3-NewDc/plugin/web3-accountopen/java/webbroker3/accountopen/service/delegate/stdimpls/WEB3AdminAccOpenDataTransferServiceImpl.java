head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�XImpl(WEB3AdminAccOpenDataTransferServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 �И���(���u) �V�K�쐬 ���f�� 181   187
Revision History : 2009/08/26 �И���(���u) ���f�� 192
Revision History : 2009/08/31 ���g(���u) ���f�� 196,199,202,209�C212
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenTempRow;
import webbroker3.accountopen.define.WEB3UploadFileIdDef;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

/**
 * (�Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�XImpl)<BR>
 * �Ǘ��Ҍ����J�݃f�[�^�ڊǃT�[�r�X�����N���X<BR>
 * ���̃T�[�r�X�Ɂu�����J�݃T�[�r�X�C���^�Z�v�^�v��ݒ肷��<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferServiceImpl implements WEB3AdminAccOpenDataTransferService
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferServiceImpl.class);

    /**
     * @@roseuid 4A8A083403D8
     */
    public WEB3AdminAccOpenDataTransferServiceImpl()
    {

    }

    /**
     * �����J�݃f�[�^�ڊǏ��������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��N�G�X�g�̏ꍇ <BR>
     * �@@�|get���͉��()���R�[������B <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�݃f�[�^�ڊǊ������N�G�X�g�̏ꍇ <BR>
     * �@@�|submit�f�[�^�ڊ�()���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4A8220860290
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

        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�݃f�[�^�ڊǓ��̓��N�G�X�g�̏ꍇ
        //get���͉��()���R�[������B
        if (l_request instanceof WEB3AdminAccOpenDataTransferInputRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminAccOpenDataTransferInputRequest)l_request);
        }
        //�����̃��N�G�X�g�f�[�^���A�Ǘ��Ҍ����J�݃f�[�^�ڊǊ������N�G�X�g�̏ꍇ
        //submit�f�[�^�ڊ�()���R�[������
        else if (l_request instanceof WEB3AdminAccOpenDataTransferCompleteRequest)
        {
            l_response =
                this.submitDataTransfer(
                    (WEB3AdminAccOpenDataTransferCompleteRequest)l_request);
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
     * ���͉�ʂ̕\�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�f�[�^�ڊǁjget���͉�ʁv �Q�� <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminAccOpenDataTransferInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4A8221CA004E
     */
    protected WEB3AdminAccOpenDataTransferInputResponse getInputScreen(
        WEB3AdminAccOpenDataTransferInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminAccOpenDataTransferInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�����J��
        //is�X�V�F�@@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ACC_OPEN, true);

        //createResponse( )
        WEB3AdminAccOpenDataTransferInputResponse l_response =
            (WEB3AdminAccOpenDataTransferInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�f�[�^�ڊ�)<BR>
     * �f�[�^�ڊǂ̏������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�f�[�^�ڊǁjsubmit�f�[�^�ڊǁv �Q�� <BR>
     * =============================================== <BR>
     * �@@�@@�@@�@@��̈ʒu    :isDIR�Ǘ���( )<BR>
     * �@@�@@�@@�@@isDIR�Ǘ��� = false�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�G���[�u���@@�\�́ADIR�Ǘ��҂̂ݎ��s�ł��܂��B�v���X���[����B<BR>
     * �@@�@@�@@�@@class      :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag      :  BUSINESS_ERROR_00985<BR>
     * =============================================== <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminAccOpenDataTransferCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4A8221D20109
     */
    protected WEB3AdminAccOpenDataTransferCompleteResponse submitDataTransfer(
        WEB3AdminAccOpenDataTransferCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitDataTransfer(WEB3AdminAccOpenDataTransferCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[validate����()�Ɏw�肷�����]
        // �@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�����J��
        // is�X�V�F�@@true
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ACC_OPEN, true);

        //isDIR�Ǘ���( )
        //�Ǘ��҂�"DIR�Ǘ���"�ł���ꍇ�Atrue��ԋp����B
        //�Ǘ��҂�"�،���ЊǗ���"�ł���ꍇ�Afalse��ԋp����B
        if (!l_admin.isDirAdministrator())
        {
            log.debug("���@@�\�́ADIR�Ǘ��҂̂ݎ��s�ł��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00985,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���@@�\�́ADIR�Ǘ��҂̂ݎ��s�ł��܂��B");
        }

        //validate����p�X���[�h(�p�X���[�h : String)
        //[validate����p�X���[�h()�Ɏw�肷�����]
        // �p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_admin.validateTradingPassword(l_request.password);

        //validate�����A�b�v���[�h(�Ǘ��� : �Ǘ���)
        //[����]
        // �Ǘ��ҁF�擾�����Ǘ���
        this.validateSameTimeUpload(l_admin);

        //save�A�b�v���[�h�J�n(�Ǘ��� : �Ǘ���)
        //[����]
        // �Ǘ��ҁF�擾�����Ǘ���
        long l_lngUpload = this.saveUploadStart(l_admin);
        try
        {
            //�V�X�e���v���t�@@�����X���疈��̏����������擾����B
            int l_intPreferences = this.getPreferences();
            log.debug("�V�X�e���v���t�@@�����X���疈��̏����������擾���� " + l_intPreferences);

            //�����J�݌����q�ꎞ�e�[�u���̃f�[�^�������擾����B 
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();

            int l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(
                ExpAccountOpenTempRow.TYPE,
                " institution_code = ? and status = ?  ",
                new Object[]{l_admin.getInstitutionCode(), WEB3StatusDef.NOT_DEAL});
            log.debug("�����J�݌����q�ꎞ�e�[�u���̃f�[�^�������擾���� " + l_intReturnRecordCnt);

            // ���ׂ��y������ׂɁALOOP�����B
            int l_intLoopCount = 0;
            if (l_intPreferences != 0)
            {
                l_intLoopCount = (l_intReturnRecordCnt + l_intPreferences - 1) / l_intPreferences;
            }
            log.debug("���[�v�� " + l_intLoopCount);

            for (int i = 0; i < l_intLoopCount; i++)
            {
                //���񏈗��̃f�[�^���擾����B
                //arg0�F�@@�����J�݌����q�ꎞ�e�[�u��.TYPE
                //arg1�F�@@" institution_code = ? and status = ?  "
                //arg2�F�@@null
                //arg3�F�@@null
                //arg4�F�@@�Ǘ��҂���،���ЃR�[�h�@@�Ɓ@@0:������
                //arg5�F�@@get�v���t�@@�����X( )�̖߂�l
                //arg6�F�@@0
                List l_lisExpAccountOpenTempRows = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        ExpAccountOpenTempRow.TYPE,
                        " institution_code = ? and status = ? ",
                        null,
                        null,
                        new Object[]{l_admin.getInstitutionCode(), WEB3StatusDef.NOT_DEAL} ,
                        l_intPreferences,
                        0);

                //�����������R�[�h�����ALOOP����
                int l_intSenLoopCount = l_lisExpAccountOpenTempRows.size();
                ExpAccountOpenParams l_expAccountOpenParams = null;
                WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = null;
                if (l_intSenLoopCount > 0)
                {
                    l_expAccountOpenParams = new ExpAccountOpenParams();
                }

                for (int j = 0; j < l_intSenLoopCount; j++)
                {
                    //�����J�݌����q�ꎞrow����u�����敪�v�Ɓu�G���[���v�ȊO�̑������N���[������B
                    ExpAccountOpenTempRow l_expAccountOpenTempRow =
                        (ExpAccountOpenTempRow)l_lisExpAccountOpenTempRows.get(j);

                    //�،���ЃR�[�h
                    l_expAccountOpenParams.setInstitutionCode(
                        l_expAccountOpenTempRow.getInstitutionCode());

                    //�،����ID
                    l_expAccountOpenParams.setInstitutionId(
                        l_expAccountOpenTempRow.getInstitutionId());

                    //���X�h�c
                    l_expAccountOpenParams.setBranchId(
                            l_expAccountOpenTempRow.getBranchId());

                    //���X�R�[�h
                    l_expAccountOpenParams.setBranchCode(
                        l_expAccountOpenTempRow.getBranchCode());

                    //���ʃR�[�h
                    l_expAccountOpenParams.setAccOpenRequestNumber(
                        l_expAccountOpenTempRow.getAccOpenRequestNumber());

                    //�����R�[�h
                    l_expAccountOpenParams.setAccountCode(
                        l_expAccountOpenTempRow.getAccountCode());

                    //���҃R�[�h�iSONAR�j
                    l_expAccountOpenParams.setSonarTraderCode(
                        l_expAccountOpenTempRow.getSonarTraderCode());

                    //���������t���O
                    l_expAccountOpenParams.setExAccountFlag(
                        l_expAccountOpenTempRow.getExAccountFlag());

                    //�����������X��
                    l_expAccountOpenParams.setExBranchName(
                        l_expAccountOpenTempRow.getExBranchName());

                    //���������R�[�h
                    l_expAccountOpenParams.setExAccountCode(
                        l_expAccountOpenTempRow.getExAccountCode());

                    //�����敪
                    l_expAccountOpenParams.setAccountDiv(
                        l_expAccountOpenTempRow.getAccountDiv());

                    //���͋敪
                    l_expAccountOpenParams.setOrderDiv(
                        l_expAccountOpenTempRow.getOrderDiv());

                    //������������
                    l_expAccountOpenParams.setInfomationClaimDatetime(
                        l_expAccountOpenTempRow.getInfomationClaimDatetime());

                    //�����o�^��
                    l_expAccountOpenParams.setAccountOpenDate(
                        l_expAccountOpenTempRow.getAccountOpenDate());

                    //�����p�X���[�h
                    WEB3Crypt l_crypt = new WEB3Crypt();
                    l_expAccountOpenParams.setInitialPassword(
                        l_crypt.encrypt(l_expAccountOpenTempRow.getInitialPassword()));

                    //�ڋq���i�����j
                    l_expAccountOpenParams.setFamilyName(
                        l_expAccountOpenTempRow.getFamilyName());

                    //�ڋq���i�����j
                    l_expAccountOpenParams.setGivenName(
                        l_expAccountOpenTempRow.getGivenName());

                    //�ڋq���i�J�i�j
                    l_expAccountOpenParams.setFamilyNameAlt1(
                        l_expAccountOpenTempRow.getFamilyNameAlt1());

                    //�ڋq���i�J�i�j
                    l_expAccountOpenParams.setGivenNameAlt1(
                        l_expAccountOpenTempRow.getGivenNameAlt1());

                    //����
                    l_expAccountOpenParams.setSex(
                        l_expAccountOpenTempRow.getSex());

                    //���N�����N��
                    l_expAccountOpenParams.setEraBorn(
                        l_expAccountOpenTempRow.getEraBorn());

                    //���N����
                    l_expAccountOpenParams.setBornDate(
                        l_expAccountOpenTempRow.getBornDate());

                    //email�A�h���X
                    l_expAccountOpenParams.setEmailAddress(
                        l_expAccountOpenTempRow.getEmailAddress());

                    //email�A�h���X�P
                    l_expAccountOpenParams.setEmailAddressAlt1(
                        l_expAccountOpenTempRow.getEmailAddressAlt1());

                    //�X�֔ԍ�
                    l_expAccountOpenParams.setZipCode(
                        l_expAccountOpenTempRow.getZipCode());

                    //�Z���P
                    l_expAccountOpenParams.setAddressLine1(
                        l_expAccountOpenTempRow.getAddressLine1());

                    //�Z���Q
                    l_expAccountOpenParams.setAddressLine2(
                        l_expAccountOpenTempRow.getAddressLine2());

                    //�Z���R
                    l_expAccountOpenParams.setAddressLine3(
                        l_expAccountOpenTempRow.getAddressLine3());

                    //�Z���P�i�J�i�j
                    l_expAccountOpenParams.setAddressLine1Kana(
                        l_expAccountOpenTempRow.getAddressLine1Kana());

                    //�Z���Q�i�J�i�j
                    l_expAccountOpenParams.setAddressLine2Kana(
                        l_expAccountOpenTempRow.getAddressLine2Kana());

                    //�Z���R�i�J�i�j
                    l_expAccountOpenParams.setAddressLine3Kana(
                        l_expAccountOpenTempRow.getAddressLine3Kana());

                    //�d�b�ԍ�
                    l_expAccountOpenParams.setTelephone(
                        l_expAccountOpenTempRow.getTelephone());

                    //�A����d�b�ԍ��i�g�сj
                    l_expAccountOpenParams.setMobile(
                        l_expAccountOpenTempRow.getMobile());

                    //�e�`�w�ԍ�
                    l_expAccountOpenParams.setFax(
                        l_expAccountOpenTempRow.getFax());

                    //�E�Ƌ敪
                    l_expAccountOpenParams.setOccupationDiv(
                        l_expAccountOpenTempRow.getOccupationDiv());

                    //�Ζ��於��
                    l_expAccountOpenParams.setOffice(
                        l_expAccountOpenTempRow.getOffice());

                    //�Ζ���X�֔ԍ�
                    l_expAccountOpenParams.setOfficeZipCode(
                        l_expAccountOpenTempRow.getOfficeZipCode());

                    //�Ζ���Z��
                    l_expAccountOpenParams.setOfficeAddress(
                        l_expAccountOpenTempRow.getOfficeAddress());

                    //�Ζ���d�b�ԍ�
                    l_expAccountOpenParams.setOfficeTelephone(
                        l_expAccountOpenTempRow.getOfficeTelephone());

                    //�Ζ���FAX�ԍ�
                    l_expAccountOpenParams.setOfficeFax(
                        l_expAccountOpenTempRow.getOfficeFax());

                    //��������
                    l_expAccountOpenParams.setDepartment(
                        l_expAccountOpenTempRow.getDepartment());

                    //��E
                    l_expAccountOpenParams.setPost(
                        l_expAccountOpenTempRow.getPost());

                    //�A����Z��
                    l_expAccountOpenParams.setContactAddress(
                        l_expAccountOpenTempRow.getContactAddress());

                    //�A����d�b�ԍ�
                    l_expAccountOpenParams.setContactTelephone(
                        l_expAccountOpenTempRow.getContactTelephone());

                    //�����敪
                    l_expAccountOpenParams.setFamilyRelationship(
                        l_expAccountOpenTempRow.getFamilyRelationship());

                    //�����敪�i���̑��j
                    l_expAccountOpenParams.setFamilyRelationshipEtc(
                        l_expAccountOpenTempRow.getFamilyRelationshipEtc());

                    //���ю喼�i�����j
                    l_expAccountOpenParams.setHouseholder(
                        l_expAccountOpenTempRow.getHouseholder());

                    //���ю喼�i�J�i�j
                    l_expAccountOpenParams.setHouseholderKana(
                        l_expAccountOpenTempRow.getHouseholderKana());

                    //���ю�E�Ƌ敪
                    l_expAccountOpenParams.setHouseholderOccupationDiv(
                        l_expAccountOpenTempRow.getHouseholderOccupationDiv());

                    //���ю�Ζ���
                    l_expAccountOpenParams.setHouseholderOffice(
                        l_expAccountOpenTempRow.getHouseholderOffice());

                    //���ю�Ζ���Z��
                    l_expAccountOpenParams.setHouseholderOfficeAddress(
                        l_expAccountOpenTempRow.getHouseholderOfficeAddress());

                    //���ю及������
                    l_expAccountOpenParams.setHouseholderDepartment(
                        l_expAccountOpenTempRow.getHouseholderDepartment());

                    //���ю�Ζ���d�b�ԍ�
                    l_expAccountOpenParams.setHouseholderOfficeTel(
                        l_expAccountOpenTempRow.getHouseholderOfficeTel());

                    //���ю�Ζ���FAX�ԍ�
                    l_expAccountOpenParams.setHouseholderOfficeFax(
                        l_expAccountOpenTempRow.getHouseholderOfficeFax());

                    //���ю��E
                    l_expAccountOpenParams.setHouseholderPost(
                        l_expAccountOpenTempRow.getHouseholderPost());

                    //���Z�^�񋏏Z�敪
                    l_expAccountOpenParams.setResident(
                        l_expAccountOpenTempRow.getResident());

                    //�U������Z�@@�֏��   �U�֋敪
                    l_expAccountOpenParams.setTransferDiv(
                        l_expAccountOpenTempRow.getTransferDiv());

                    //��s�R�[�h
                    l_expAccountOpenParams.setFinInstitutionCode(
                        l_expAccountOpenTempRow.getFinInstitutionCode());

                    //��s��
                    l_expAccountOpenParams.setFinInstitutionName(
                        l_expAccountOpenTempRow.getFinInstitutionName());

                    //�x�X�R�[�h
                    l_expAccountOpenParams.setFinBranchCode(
                        l_expAccountOpenTempRow.getFinBranchCode());

                    //�x�X��
                    l_expAccountOpenParams.setFinBranchName(
                        l_expAccountOpenTempRow.getFinBranchName());

                    //�a���敪
                    l_expAccountOpenParams.setFinSaveDiv(
                        l_expAccountOpenTempRow.getFinSaveDiv());

                    //�����ԍ�
                    l_expAccountOpenParams.setFinAccountNo(
                        l_expAccountOpenTempRow.getFinAccountNo());

                    //�ʒ��L��
                    l_expAccountOpenParams.setPostalSaveCode(
                        l_expAccountOpenTempRow.getPostalSaveCode());

                    //�ʒ��ԍ�
                    l_expAccountOpenParams.setPostalSaveNo(
                        l_expAccountOpenTempRow.getPostalSaveNo());

                    //�������`�l
                    l_expAccountOpenParams.setFinAccountName(
                        l_expAccountOpenTempRow.getFinAccountName());

                    //�U�֎萔���敪
                    l_expAccountOpenParams.setTransCommission(
                        l_expAccountOpenTempRow.getTransCommission());

                    //��������
                    l_expAccountOpenParams.setExperienceDivEquity(
                        l_expAccountOpenTempRow.getExperienceDivEquity());

                    //�M�p���
                    l_expAccountOpenParams.setExperienceDivMargin(
                        l_expAccountOpenTempRow.getExperienceDivMargin());

                    //��
                    l_expAccountOpenParams.setExperienceDivBond(
                        l_expAccountOpenTempRow.getExperienceDivBond());

                    //�]���Ѝ�
                    l_expAccountOpenParams.setExperienceDivWt(
                        l_expAccountOpenTempRow.getExperienceDivWt());

                    //�����M���i�����j
                    l_expAccountOpenParams.setExperienceDivFundSk(
                        l_expAccountOpenTempRow.getExperienceDivFundSk());

                    //�����M���i���Ѝj
                    l_expAccountOpenParams.setExperienceDivFundBd(
                        l_expAccountOpenTempRow.getExperienceDivFundBd());

                    //�敨�E�I�v�V����
                    l_expAccountOpenParams.setExperienceDivFo(
                        l_expAccountOpenTempRow.getExperienceDivFo());

                    //�O���،�
                    l_expAccountOpenParams.setExperienceDivFEquity(
                        l_expAccountOpenTempRow.getExperienceDivFEquity());

                    //���̑�
                    l_expAccountOpenParams.setExperienceDivEtc(
                        l_expAccountOpenTempRow.getExperienceDivEtc());

                    //���������t���O
                    l_expAccountOpenParams.setExperienceFlagEquity(
                        l_expAccountOpenTempRow.getExperienceFlagEquity());

                    //�M�p����t���O
                    l_expAccountOpenParams.setExperienceFlagMargin(
                        l_expAccountOpenTempRow.getExperienceFlagMargin());

                    //���t���O
                    l_expAccountOpenParams.setExperienceFlagBond(
                        l_expAccountOpenTempRow.getExperienceFlagBond());

                    //�]���Ѝt���O
                    l_expAccountOpenParams.setExperienceFlagWt(
                        l_expAccountOpenTempRow.getExperienceFlagWt());

                    //�����M���i�����j�t���O
                    l_expAccountOpenParams.setExperienceFlagFundSk(
                        l_expAccountOpenTempRow.getExperienceFlagFundSk());

                    //�����M���i���Ѝj�t���O
                    l_expAccountOpenParams.setExperienceFlagFundBd(
                        l_expAccountOpenTempRow.getExperienceFlagFundBd());

                    //�敨�E�I�v�V�����t���O
                    l_expAccountOpenParams.setExperienceFlagFo(
                        l_expAccountOpenTempRow.getExperienceFlagFo());

                    //�O���،��t���O
                    l_expAccountOpenParams.setExperienceFlagFEquity(
                        l_expAccountOpenTempRow.getExperienceFlagFEquity());

                    //���̑��t���O
                    l_expAccountOpenParams.setExperienceFlagEtc(
                        l_expAccountOpenTempRow.getExperienceFlagEtc());

                    //�o���N���i���j
                    l_expAccountOpenParams.setExperienceFrom(
                        l_expAccountOpenTempRow.getExperienceFrom());

                    //�o���N���i���j
                    l_expAccountOpenParams.setExperienceTo(
                        l_expAccountOpenTempRow.getExperienceTo());

                    //���������t���O
                    l_expAccountOpenParams.setInterestFlagEquity(
                        l_expAccountOpenTempRow.getInterestFlagEquity());

                    //�����~�j�����t���O
                    l_expAccountOpenParams.setInterestFlagMinistock(
                        l_expAccountOpenTempRow.getInterestFlagMinistock());

                    //�M�p����t���O
                    l_expAccountOpenParams.setInterestFlagMargin(
                        l_expAccountOpenTempRow.getInterestFlagMargin());

                    //���t���O
                    l_expAccountOpenParams.setInterestFlagBond(
                        l_expAccountOpenTempRow.getInterestFlagBond());

                    //�����M���t���O
                    l_expAccountOpenParams.setInterestFlagFund(
                        l_expAccountOpenTempRow.getInterestFlagFund());

                    //�敨�E�I�v�V�����t���O
                    l_expAccountOpenParams.setInterestFlagFo(
                        l_expAccountOpenTempRow.getInterestFlagFo());

                    //�O���،��t���O
                    l_expAccountOpenParams.setInterestFlagFEquity(
                        l_expAccountOpenTempRow.getInterestFlagFEquity());

                    //���̑��t���O
                    l_expAccountOpenParams.setInterestFlagEtc(
                        l_expAccountOpenTempRow.getInterestFlagEtc());

                    //�����ړI�敪
                    l_expAccountOpenParams.setInvestPurposeDiv(
                        l_expAccountOpenTempRow.getInvestPurposeDiv());

                    //������@@�敪
                    l_expAccountOpenParams.setAppliMotivatDiv(
                        l_expAccountOpenTempRow.getAppliMotivatDiv());

                    //�N���敪
                    l_expAccountOpenParams.setAnnualIncomeDiv(
                        l_expAccountOpenTempRow.getAnnualIncomeDiv());

                    //�N���i���j
                    l_expAccountOpenParams.setAnnualIncomeFrom(
                        l_expAccountOpenTempRow.getAnnualIncomeFrom());

                    //�N���i���j
                    l_expAccountOpenParams.setAnnualIncomeTo(
                        l_expAccountOpenTempRow.getAnnualIncomeTo());

                    //���Z���Y�敪
                    l_expAccountOpenParams.setAssetValueDiv(
                        l_expAccountOpenTempRow.getAssetValueDiv());

                    //���Z���Y�i���j
                    l_expAccountOpenParams.setAssetValueFrom(
                        l_expAccountOpenTempRow.getAssetValueFrom());

                    //���Z���Y�i���j
                    l_expAccountOpenParams.setAssetValueTo(
                        l_expAccountOpenTempRow.getAssetValueTo());

                    //�^�p�\��z
                    l_expAccountOpenParams.setFundBudgetAmountDiv(
                        l_expAccountOpenTempRow.getFundBudgetAmountDiv());

                    //�����̐��i
                    l_expAccountOpenParams.setFundBudgetDiv(
                        l_expAccountOpenTempRow.getFundBudgetDiv());

                    //�����̐��i�i���̑��j
                    l_expAccountOpenParams.setFundBudgetEtc(
                        l_expAccountOpenTempRow.getFundBudgetEtc());

                    //�����p�敪
                    l_expAccountOpenParams.setIdConfirmFlag(
                        l_expAccountOpenTempRow.getIdConfirmFlag());

                    //�{�l�m�F���ދ敪
                    l_expAccountOpenParams.setIdConfirmDocDiv(
                        l_expAccountOpenTempRow.getIdConfirmDocDiv());

                    //�{�l�m�F���ދ敪�i���̑��j
                    l_expAccountOpenParams.setIdConfirmDocEtc(
                        l_expAccountOpenTempRow.getIdConfirmDocEtc());

                    //��������敪
                    l_expAccountOpenParams.setSpecialAcc(
                        l_expAccountOpenTempRow.getSpecialAcc());

                    //�M�p�����������敪
                    l_expAccountOpenParams.setSpecialAccMargin(
                        l_expAccountOpenTempRow.getSpecialAccMargin());

                    //�����ғo�^�t���O
                    l_expAccountOpenParams.setInsiderFlag(
                        l_expAccountOpenTempRow.getInsiderFlag());

                    //�����Җ�����
                    l_expAccountOpenParams.setProductName(
                        l_expAccountOpenTempRow.getProductName());

                    //���t��X�֔ԍ�
                    l_expAccountOpenParams.setSendZipCode(
                        l_expAccountOpenTempRow.getSendZipCode());

                    //���t��Z���P
                    l_expAccountOpenParams.setSendAddressLine1(
                        l_expAccountOpenTempRow.getSendAddressLine1());

                    //���t��Z���Q
                    l_expAccountOpenParams.setSendAddressLine2(
                        l_expAccountOpenTempRow.getSendAddressLine2());

                    //���t��Z���R
                    l_expAccountOpenParams.setSendAddressLine3(
                        l_expAccountOpenTempRow.getSendAddressLine3());

                    //�e�Њg�����ځi�敪�P�j
                    l_expAccountOpenParams.setExtItemDiv1(
                        l_expAccountOpenTempRow.getExtItemDiv1());

                    //�e�Њg�����ځi�敪�Q�j
                    l_expAccountOpenParams.setExtItemDiv2(
                        l_expAccountOpenTempRow.getExtItemDiv2());

                    //�e�Њg�����ځi�敪�R�j
                    l_expAccountOpenParams.setExtItemDiv3(
                        l_expAccountOpenTempRow.getExtItemDiv3());

                    //�e�Њg�����ځi�敪�S�j
                    l_expAccountOpenParams.setExtItemDiv4(
                        l_expAccountOpenTempRow.getExtItemDiv4());

                    //�e�Њg�����ځi�敪�T�j
                    l_expAccountOpenParams.setExtItemDiv5(
                        l_expAccountOpenTempRow.getExtItemDiv5());

                    //�e�Њg�����ځi�敪�U�j
                    l_expAccountOpenParams.setExtItemDiv6(
                        l_expAccountOpenTempRow.getExtItemDiv6());

                    //�e�Њg�����ځi�敪�V�j
                    l_expAccountOpenParams.setExtItemDiv7(
                        l_expAccountOpenTempRow.getExtItemDiv7());

                    //�e�Њg�����ځi�敪�W�j
                    l_expAccountOpenParams.setExtItemDiv8(
                        l_expAccountOpenTempRow.getExtItemDiv8());

                    //�e�Њg�����ځi�敪�X�j
                    l_expAccountOpenParams.setExtItemDiv9(
                        l_expAccountOpenTempRow.getExtItemDiv9());

                    //�e�Њg�����ځi�敪�P�O�j
                    l_expAccountOpenParams.setExtItemDiv10(
                        l_expAccountOpenTempRow.getExtItemDiv10());

                    //�e�Њg�����ځi�t���O1�j
                    l_expAccountOpenParams.setExtItemFlag1(
                        l_expAccountOpenTempRow.getExtItemFlag1());

                    //�e�Њg�����ځi�t���O2�j
                    l_expAccountOpenParams.setExtItemFlag2(
                        l_expAccountOpenTempRow.getExtItemFlag2());

                    //�e�Њg�����ځi�t���O3�j
                    l_expAccountOpenParams.setExtItemFlag3(
                        l_expAccountOpenTempRow.getExtItemFlag3());

                    //�e�Њg�����ځi�t���O4�j
                    l_expAccountOpenParams.setExtItemFlag4(
                        l_expAccountOpenTempRow.getExtItemFlag4());

                    //�e�Њg�����ځi�t���O5�j
                    l_expAccountOpenParams.setExtItemFlag5(
                        l_expAccountOpenTempRow.getExtItemFlag5());

                    //�e�Њg�����ځi�t���O�U�j
                    l_expAccountOpenParams.setExtItemFlag6(
                        l_expAccountOpenTempRow.getExtItemFlag6());

                    //�e�Њg�����ځi�t���O�V�j
                    l_expAccountOpenParams.setExtItemFlag7(
                        l_expAccountOpenTempRow.getExtItemFlag7());

                    //�e�Њg�����ځi�t���O�W�j
                    l_expAccountOpenParams.setExtItemFlag8(
                        l_expAccountOpenTempRow.getExtItemFlag8());

                    //�e�Њg�����ځi�t���O�X�j
                    l_expAccountOpenParams.setExtItemFlag9(
                        l_expAccountOpenTempRow.getExtItemFlag9());

                    //�e�Њg�����ځi�t���O�P�O�j
                    l_expAccountOpenParams.setExtItemFlag10(
                        l_expAccountOpenTempRow.getExtItemFlag10());

                    //�e�Њg�����ځi�e�L�X�g�P�j
                    l_expAccountOpenParams.setExtItemText1(
                        l_expAccountOpenTempRow.getExtItemText1());

                    //�e�Њg�����ځi�e�L�X�g�Q�j
                    l_expAccountOpenParams.setExtItemText2(
                        l_expAccountOpenTempRow.getExtItemText2());

                    //�e�Њg�����ځi�e�L�X�g�R�j
                    l_expAccountOpenParams.setExtItemText3(
                        l_expAccountOpenTempRow.getExtItemText3());

                    //�e�Њg�����ځi�e�L�X�g�S�j
                    l_expAccountOpenParams.setExtItemText4(
                        l_expAccountOpenTempRow.getExtItemText4());

                    //�e�Њg�����ځi�e�L�X�g�T�j
                    l_expAccountOpenParams.setExtItemText5(
                        l_expAccountOpenTempRow.getExtItemText5());

                    //�e�Њg�����ځi�e�L�X�g�U�j
                    l_expAccountOpenParams.setExtItemText6(
                        l_expAccountOpenTempRow.getExtItemText6());

                    //�e�Њg�����ځi�e�L�X�g�V�j
                    l_expAccountOpenParams.setExtItemText7(
                        l_expAccountOpenTempRow.getExtItemText7());

                    //�e�Њg�����ځi�e�L�X�g�W�j
                    l_expAccountOpenParams.setExtItemText8(
                        l_expAccountOpenTempRow.getExtItemText8());

                    //�e�Њg�����ځi�e�L�X�g�X�j
                    l_expAccountOpenParams.setExtItemText9(
                        l_expAccountOpenTempRow.getExtItemText9());

                    //�e�Њg�����ځi�e�L�X�g�P�O�j
                    l_expAccountOpenParams.setExtItemText10(
                        l_expAccountOpenTempRow.getExtItemText10());

                    //�X�V�҃R�[�h
                    l_expAccountOpenParams.setLastUpdater(
                        l_expAccountOpenTempRow.getLastUpdater());

                    //�쐬����
                    l_expAccountOpenParams.setCreatedTimestamp(
                        l_expAccountOpenTempRow.getCreatedTimestamp());

                    //�X�V����
                    l_expAccountOpenParams.setLastUpdatedTimestamp(
                        l_expAccountOpenTempRow.getLastUpdatedTimestamp());

                    //�쐬�҃R�[�h
                    l_expAccountOpenParams.setCreator(
                        l_expAccountOpenTempRow.getCreator());

                    //��p�U��������ԍ�
                    l_expAccountOpenParams.setExclusiveUseAccountNo(
                        l_expAccountOpenTempRow.getExclusiveUseAccountNo());

                    //HULFT���M����
                    l_expAccountOpenParams.setSendTimestamp(
                        l_expAccountOpenTempRow.getSendTimestamp());

                    //�ڋq�������̍쐬�敪
                    l_expAccountOpenParams.setRealNameVoucherDiv(
                        l_expAccountOpenTempRow.getRealNameVoucherDiv());

                    //�ڋq�������̂P
                    l_expAccountOpenParams.setRealName1(
                        l_expAccountOpenTempRow.getRealName1());

                    //�ڋq�������̂Q
                    l_expAccountOpenParams.setRealName2(
                        l_expAccountOpenTempRow.getRealName2());

                    //�i�����ҁj�쐬�敪
                    l_expAccountOpenParams.setInsiderVoucherDiv(
                        l_expAccountOpenTempRow.getInsiderVoucherDiv());

                    //�i�����ҁj�����R�[�h
                    l_expAccountOpenParams.setInsiderProductCode(
                        l_expAccountOpenTempRow.getInsiderProductCode());

                    //�i�����ҁj�֌W�敪
                    l_expAccountOpenParams.setInsiderRelationDiv(
                        l_expAccountOpenTempRow.getInsiderRelationDiv());

                    //�i�����ҁj������
                    l_expAccountOpenParams.setInsiderOfficerName(
                        l_expAccountOpenTempRow.getInsiderOfficerName());

                    //�i�����ҁj��E���R�[�h
                    l_expAccountOpenParams.setInsiderPostCode(
                        l_expAccountOpenTempRow.getInsiderPostCode());

                    //�i�����ҁj��E��
                    l_expAccountOpenParams.setInsiderPostName(
                        l_expAccountOpenTempRow.getInsiderPostName());

                    //�i�f�o�j�쐬�敪
                    l_expAccountOpenParams.setGpVoucherDiv(
                        l_expAccountOpenTempRow.getGpVoucherDiv());

                    //�i�f�o�j�R�[�X
                    l_expAccountOpenParams.setGpCourse(
                        l_expAccountOpenTempRow.getGpCourse());

                    //�i�f�o�j�v����
                    l_expAccountOpenParams.setGpPlan(
                        l_expAccountOpenTempRow.getGpPlan());

                    //�i�f�o�j�ڕW�z
                    l_expAccountOpenParams.setGpTargetFigure(
                        l_expAccountOpenTempRow.getGpTargetFigure());

                    //�i�f�o�j�ڕW�N
                    l_expAccountOpenParams.setGpTargetYear(
                        l_expAccountOpenTempRow.getGpTargetYear());

                    //�i�f�o�j�ڕW��
                    l_expAccountOpenParams.setGpTargetMonth(
                        l_expAccountOpenTempRow.getGpTargetMonth());

                    //�i�f�o�j�ϗ��z
                    l_expAccountOpenParams.setGpInstallmentFigure(
                        l_expAccountOpenTempRow.getGpInstallmentFigure());

                    //�i�f�o�j��������
                    l_expAccountOpenParams.setGpDepositCycle(
                        l_expAccountOpenTempRow.getGpDepositCycle());

                    //�i�f�o�j��n�o�H
                    l_expAccountOpenParams.setGpPaymentRoot(
                        l_expAccountOpenTempRow.getGpPaymentRoot());

                    //�i�f�o�j�ē����敪
                    l_expAccountOpenParams.setGpReinvestDiv(
                        l_expAccountOpenTempRow.getGpReinvestDiv());

                    //�i�f�o�j�ŋ敪
                    l_expAccountOpenParams.setGpTaxDiv(
                        l_expAccountOpenTempRow.getGpTaxDiv());

                    //�i�f�o�j�i�D�j���x�z
                    l_expAccountOpenParams.setGpTaxfreeLimit(
                        l_expAccountOpenTempRow.getGpTaxfreeLimit());

                    //�i�f�o�j�i���D�j���x�z
                    l_expAccountOpenParams.setGpSpecialTaxfreeLimit(
                        l_expAccountOpenTempRow.getGpSpecialTaxfreeLimit());

                    //�i�f�o�j�����E�v
                    l_expAccountOpenParams.setGpSubscrSummary(
                        l_expAccountOpenTempRow.getGpSubscrSummary());

                    //�i�f�o�j�����R�[�h
                    l_expAccountOpenParams.setGpProductCode(
                        l_expAccountOpenTempRow.getGpProductCode());

                    //�i�f�o�j�S�ۋq
                    l_expAccountOpenParams.setGpMortgageCustomer(
                        l_expAccountOpenTempRow.getGpMortgageCustomer());

                    //�i�f�o�j�~�b�N�X�q
                    l_expAccountOpenParams.setGpMixCustomer(
                        l_expAccountOpenTempRow.getGpMixCustomer());

                    //�i�f�o�j�_��
                    l_expAccountOpenParams.setGpContract(
                        l_expAccountOpenTempRow.getGpContract());

                    //�i���O���j�쐬�敪
                    l_expAccountOpenParams.setStkVoucherDiv(
                        l_expAccountOpenTempRow.getStkVoucherDiv());

                    //�i���O���j���n
                    l_expAccountOpenParams.setStkTaxationTranDiv(
                        l_expAccountOpenTempRow.getStkTaxationTranDiv());

                    //�i���O���j�Z���i�J�i�j
                    l_expAccountOpenParams.setStkAddressLineKana(
                        l_expAccountOpenTempRow.getStkAddressLineKana());

                    //�i���O���j����
                    l_expAccountOpenParams.setStkTransferDiv(
                        l_expAccountOpenTempRow.getStkTransferDiv());

                    //�i���O���j��s�R�[�h
                    l_expAccountOpenParams.setStkFinInstitutionCode(
                        l_expAccountOpenTempRow.getStkFinInstitutionCode());

                    //�i���O���j�x�X�R�[�h
                    l_expAccountOpenParams.setStkFinBranchCode(
                        l_expAccountOpenTempRow.getStkFinBranchCode());

                    //�i���O���j�a���敪
                    l_expAccountOpenParams.setStkFinSaveDiv(
                        l_expAccountOpenTempRow.getStkFinSaveDiv());

                    //�i���O���j�����ԍ�
                    l_expAccountOpenParams.setStkFinAccountNo(
                        l_expAccountOpenTempRow.getStkFinAccountNo());

                    //����ƈ��҃R�[�h
                    l_expAccountOpenParams.setBrokerageTraderCode(
                        l_expAccountOpenTempRow.getBrokerageTraderCode());

                    //�e�Њg�����ځi�敪1�P�j
                    l_expAccountOpenParams.setExtItemDiv11(
                        l_expAccountOpenTempRow.getExtItemDiv11());

                    //�e�Њg�����ځi�敪�P2�j
                    l_expAccountOpenParams.setExtItemDiv12(
                        l_expAccountOpenTempRow.getExtItemDiv12());

                    //�e�Њg�����ځi�敪�P3�j
                    l_expAccountOpenParams.setExtItemDiv13(
                        l_expAccountOpenTempRow.getExtItemDiv13());

                    //�e�Њg�����ځi�敪�P4�j
                    l_expAccountOpenParams.setExtItemDiv14(
                        l_expAccountOpenTempRow.getExtItemDiv14());

                    //���o�C����p�����敪
                    l_expAccountOpenParams.setExtItemDiv15(
                        l_expAccountOpenTempRow.getExtItemDiv15());

                    //�����ԍ��i�O�݁j
                    l_expAccountOpenParams.setForeignAccountNo(
                        l_expAccountOpenTempRow.getForeignAccountNo());

                    //�������`�l�i�O�݁j
                    l_expAccountOpenParams.setForeignAccountName(
                        l_expAccountOpenTempRow.getForeignAccountName());

                    //�������`�l�p���i�O�݁j
                    l_expAccountOpenParams.setForeignAccountNameEng(
                        l_expAccountOpenTempRow.getForeignAccountNameEng());

                    //�a���敪�i�O�݁j
                    l_expAccountOpenParams.setForeignSaveDiv(
                        l_expAccountOpenTempRow.getForeignSaveDiv());

                    //�폜�t���O
                    l_expAccountOpenParams.setDeleteFlag(
                        l_expAccountOpenTempRow.getDeleteFlag());

                    //�폜����
                    l_expAccountOpenParams.setDeleteTimestamp(
                        l_expAccountOpenTempRow.getDeleteTimestamp());

                    //����t���O
                    l_expAccountOpenParams.setPrintFlag(
                        l_expAccountOpenTempRow.getPrintFlag());

                    //��̃t���O
                    l_expAccountOpenParams.setReceiptFlag(
                        l_expAccountOpenTempRow.getReceiptFlag());

                    //�����t���O
                    l_expAccountOpenParams.setAgreementFlag(
                        l_expAccountOpenTempRow.getAgreementFlag());

                    // �O���l�t���O
                    l_expAccountOpenParams.setForeignFlag(
                        l_expAccountOpenTempRow.getForeignFlag());

                    //�t���K�i1
                    l_expAccountOpenParams.setAgencyAccNameKana1(
                        l_expAccountOpenTempRow.getAgencyAccNameKana1());

                    //�t���K�i2
                    l_expAccountOpenParams.setAgencyAccNameKana2(
                        l_expAccountOpenTempRow.getAgencyAccNameKana2());

                    //����1
                    l_expAccountOpenParams.setAgencyAccName1(
                        l_expAccountOpenTempRow.getAgencyAccName1());

                    //����2
                    l_expAccountOpenParams.setAgencyAccName2(
                        l_expAccountOpenTempRow.getAgencyAccName2());

                    //�Z��1
                    l_expAccountOpenParams.setAgencyAddressLine1(
                        l_expAccountOpenTempRow.getAgencyAddressLine1());

                    //�Z��2
                    l_expAccountOpenParams.setAgencyAddressLine2(
                        l_expAccountOpenTempRow.getAgencyAddressLine2());

                    //��\�҂̖�E
                    l_expAccountOpenParams.setAgencyRepPost(
                        l_expAccountOpenTempRow.getAgencyRepPost());

                    //��\�҂̃t���K�i1
                    l_expAccountOpenParams.setAgencyRepNameKana1(
                        l_expAccountOpenTempRow.getAgencyRepNameKana1());

                    //��\�҂̃t���K�i2
                    l_expAccountOpenParams.setAgencyRepNameKana2(
                        l_expAccountOpenTempRow.getAgencyRepNameKana2());

                    //��\�҂̎���1
                    l_expAccountOpenParams.setAgencyRepName1(
                        l_expAccountOpenTempRow.getAgencyRepName1());

                    //��\�҂̎���2
                    l_expAccountOpenParams.setAgencyRepName2(
                        l_expAccountOpenTempRow.getAgencyRepName2());

                    l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                        l_expAccountOpenParams);

                    //�����J�݈ڊǂ̏������s���B
                    WEB3AdminAccOpenDataTransferUnitService l_accOpenDataTransferUnitService = 
                        (WEB3AdminAccOpenDataTransferUnitService)Services.getService(
                                WEB3AdminAccOpenDataTransferUnitService.class);

                    l_accOpenDataTransferUnitService.process(l_accOpenExpAccountOpen);
                }
            }
        }
        catch(Exception l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
        }
        try
        {
            //�w��̃A�b�v���[�h�s�ɃA�b�v���[�h�I�������X�V����B
            saveUploadEnd(l_lngUpload);
            log.exiting(STR_METHOD_NAME);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorInfo().getErrorMessage(), l_ex);
        }
        //createResponse( )
        WEB3AdminAccOpenDataTransferCompleteResponse l_response =
            (WEB3AdminAccOpenDataTransferCompleteResponse)l_request.createResponse();

        return l_response;
    }

    /**
     * (save�A�b�v���[�h�J�n)<BR>
     * �A�b�v���[�h�e�[�u���ɐV�K�s��}�����A�A�b�v���[�h�h�c��ԋp����B <BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g���� <BR>
     * �@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��Params�𐶐����ANotNull���ڈȊO�̊e���ڂ�Null�ŏ���������B <BR>
     * <BR>
     * �@@���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��Params�N���X��DDL��莩����������B <BR>
     * <BR>
     * �Q�j�@@�ȉ��̒ʂ�A�s�I�u�W�F�N�g�ɒl���Z�b�g����B <BR>
     * <BR>
     * �@@�A�b�v���[�h�h�c = �i�V�K�̔ԁj(*1) <BR>
     * �@@�،���ЃR�[�h = ����.�Ǘ��҂���،���ЃR�[�h() <BR>
     * �@@���X�R�[�h = ����.�Ǘ��҂��畔�X�R�[�h() <BR>
     * �@@�����^�C�v = 0�F���̑��iProductTypeEnum�ɂĒ�`�j<BR>
     * �@@�A�b�v���[�h�t�@@�C���h�c = "�����J�݃f�[�^�ڊ�"<BR>
     * �@@�A�b�v���[�h�J�n���� = System.currentTimeMillis() <BR>
     * �@@�X�V�҃R�[�h = ����.�Ǘ��҂���Ǘ��҃R�[�h<BR>
     * <BR>
     * �@@(*1)�@@�A�b�v���[�h�h�c�V�K�̔� <BR>
     * �@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��DAO.newPkValue()���\�b�h�ɂĎ擾����B <BR>
     * �@@���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��DAO�N���X��DDL��莩����������B <BR>
     * <BR>
     * �R�j�@@TransactionCallback��������B <BR>
     * �@@�A�b�v���[�hTransactionCallback�N���X�𐶐����A�Q�j�ō쐬�����s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B <BR>
     * <BR>
     * �S�j�@@DB-insert <BR>
     * �@@QueryProcessor.doTransaction()�ɂ�DB-insert�����s����B <BR>
     * <BR>
     * �@@[doTransaction()�Ɏw�肷�����] <BR>
     * �@@�g�����U�N�V���������F�@@TX_CREATE_NEW <BR>
     * �@@�g�����U�N�V�����R�[���o�b�N�F�@@�R�j�Ő�������TransactionCallback<BR>
     * <BR>
     * �T�j�@@�V�K�̔Ԃ����A�b�v���[�h�h�c��ԋp����B<BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 4A8229940138
     */
    private long saveUploadStart(WEB3Administrator l_admin)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveUploadStart(WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        //�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��Params�𐶐����ANotNull���ڈȊO�̊e���ڂ�Null�ŏ���������B
        AdministratorUploadParams l_administratorUploadParams =
            new AdministratorUploadParams();

        try
        {
            //�ȉ��̒ʂ�A�s�I�u�W�F�N�g�ɒl���Z�b�g����B
            //�A�b�v���[�h�h�c = �i�V�K�̔ԁj
            long l_lngNewPkValue = AdministratorUploadDao.newPkValue();
            l_administratorUploadParams.setAdministratorUploadId(l_lngNewPkValue);

            //�،���ЃR�[�h = ����.�Ǘ��҂���،���ЃR�[�h()
            l_administratorUploadParams.setInstitutionCode(l_admin.getInstitutionCode());

            //���X�R�[�h = ����.�Ǘ��҂��畔�X�R�[�h()
            l_administratorUploadParams.setBranchCode(l_admin.getBranchCode());

            //�����^�C�v = 0�F���̑��iProductTypeEnum�ɂĒ�`�j
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);

            //�A�b�v���[�h�t�@@�C���h�c = "�����J�݃f�[�^�ڊ�"
            l_administratorUploadParams.setUploadFileId(WEB3UploadFileIdDef.ACCOUNT_OPEN_DATA_TRANSFER);

            //�A�b�v���[�h�J�n���� = System.currentTimeMillis()
            l_administratorUploadParams.setUploadStartTimestamp(new Timestamp(System.currentTimeMillis()));

            //�X�V�҃R�[�h = ����.�Ǘ��҂���Ǘ��҃R�[�h
            l_administratorUploadParams.setLastUpdater(l_admin.getAdministratorCode());

            //TransactionCallback��������B
            UploadTransactionCallback l_uploadTransactionCallback = new UploadTransactionCallback();

            //�A�b�v���[�hTransactionCallback�N���X�𐶐����A�Q�j�ō쐬�����s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B
            l_uploadTransactionCallback.administratorUploadParams = l_administratorUploadParams;

            //DB-insert
            //QueryProcessor.doTransaction()�ɂ�DB-insert�����s����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_uploadTransactionCallback);

            //�V�K�̔Ԃ����A�b�v���[�h�h�c��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_lngNewPkValue;
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
    }

    /**
     * (validate�����A�b�v���[�h)<BR>
     * ���Ǘ��҂��g�p���łȂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����Łu�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v����������B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h = ����.�Ǘ��҂���،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.�Ǘ��҂��畔�X�R�[�h<BR>
     * �@@�����^�C�v = 0�F���̑��iProductTypeEnum�ɂĒ�`�j<BR>
     * �@@�A�b�v���[�h�t�@@�C���h�c = "�����J�݃f�[�^�ڊ�"<BR>
     * �@@�A�b�v���[�h�I������ = null<BR>
     * <BR>
     * �Q�j�@@�s���擾�ł���ꍇ�A�G���[�u�����J�݃f�[�^�ڊǏ������v���X���[����B<BR>
     * �@@�@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_03179<BR>
     * <BR>
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ���<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A82956400FA
     */
    private void validateSameTimeUpload(WEB3Administrator l_admin)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSameTimeUpload(WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = l_admin.getInstitutionCode();
        String l_strBranchCode = l_admin.getBranchCode();

        //�P�j�@@�ȉ��̏����Łu�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v����������B
        //[����]
        //�@@�،���ЃR�[�h = ����.�Ǘ��҂���،���ЃR�[�h
        //�@@���X�R�[�h = ����.�Ǘ��҂��畔�X�R�[�h
        //�@@�����^�C�v = 0�F���̑��iProductTypeEnum�ɂĒ�`�j
        //�@@�A�b�v���[�h�t�@@�C���h�c = "�����J�݃f�[�^�ڊ�"
        //�@@�A�b�v���[�h�I������ = null
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and upload_file_id = ? ");
        l_sbWhere.append(" and upload_end_timestamp is null ");

        Object[] l_objAdministratorUploadWhere =
            {l_strInstitutionCode,
            l_strBranchCode,
            ProductTypeEnum.OTHER,
            WEB3UploadFileIdDef.ACCOUNT_OPEN_DATA_TRANSFER};

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);
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

        //�s���擾�ł���ꍇ�A�G���[�u�����J�݃f�[�^�ڊǏ������v���X���[����B
        if (!l_lisRecords.isEmpty())
        {
            log.debug("�����J�݃f�[�^�ڊǏ������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03179,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�݃f�[�^�ڊǏ������B");
        }
        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (get�v���t�@@�����X)<BR>
     * �V�X�e���v���t�@@�����X���疈��̏����������擾����B<BR>
     * <BR>
     * �P�j�V�X�e���v���t�@@�����XRow���擾����B<BR>
     * ���������F<BR>
     * ���́i���ϐ����j = "web3.adminAccountOpenAccTransfer.MaxAmount"<BR>
     * <BR>
     * �Q�j�擾�����f�[�^�s�̒l��int�^�ɕϊ����ԋp����B<BR>
     * <BR>
     * �R�j�擾�ł��Ȃ��ꍇ�A�f�t�H���g�l�u1000�v��ԋp����B<BR>
     * @@throws WEB3BaseException
     * @@return int
     * @@roseuid 4A82668600EA
     */
    private int getPreferences() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPreferences() ";
        log.entering(STR_METHOD_NAME);

        int l_intMaxAmount = 1000;

        SystemPreferencesRow l_sysPreRow = null;

        try
        {
            //�V�X�e���v���t�@@�����XRow���擾����B
            l_sysPreRow = SystemPreferencesDao.findRowByName(
                WEB3SystemPreferencesNameDef.ADMIN_ACCOUNT_OPEN_ACC_TRANSFER_MAX_AMOUNT);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�擾�����f�[�^�s�̒l��int�^�ɕϊ����ԋp����B
        if (l_sysPreRow != null)
        {
            String l_strValue = l_sysPreRow.getValue();
            if (l_strValue != null)
            {
                l_intMaxAmount = Integer.parseInt(l_strValue);
            }

        }

        log.exiting(STR_METHOD_NAME);

        //�擾�ł��Ȃ��ꍇ�A�f�t�H���g�l�u1000�v��ԋp����B
        return l_intMaxAmount;
    }

    /**
     * (save�A�b�v���[�h�I��)<BR>
     * �w��̃A�b�v���[�h�s�ɃA�b�v���[�h�I�������X�V����B<BR>
     * <BR>
     * �@@this.�A�b�v���[�h�h�c�ɊY������s�ɂ���<BR>
     * �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�I������ = System.currentTimeMillis()<BR>
     * <BR>
     * @@param l_lngUploadID - (�A�b�v���[�h�h�c)<BR>
     * �A�b�v���[�h�h�c<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A827A5C01D4
     */
    private void saveUploadEnd(long l_lngUploadID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveUploadEnd()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���
            //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            AdministratorUploadRow l_administratorUploadRow = 
                (AdministratorUploadRow)
                    AdministratorUploadDao.findRowByAdministratorUploadId(l_lngUploadID);

            if (l_administratorUploadRow == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            AdministratorUploadParams l_administratorUploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);

            //�A�b�v���[�h�I������ = System.currentTimeMillis() 
            Timestamp l_tsSystemTime = (new Timestamp(System.currentTimeMillis()));
            l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);

            QueryProcessor l_queryProcessor =
                 Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataException l_ex)
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

    /**
     * (�A�b�v���[�hTransactionCallback) <BR>
     * <BR>
     * �A�b�v���[�h�e�[�u���ɍs���쐬����TransactionCallback�N���X<BR>
     */
    public class UploadTransactionCallback implements TransactionCallback
    {
        /**
         * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�s<BR>
         * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�s�I�u�W�F�N�g<BR>
         * <BR>
         * ���@@�i�Ǘ��ҋ��ʁj�A�b�v���[�hParams�N���X��DDL�ɂĎ�����������B<BR>
         */
        public AdministratorUploadParams administratorUploadParams;

        /**
         * @@roseuid 4107644702DE
         */
        public UploadTransactionCallback()
        {

        }

        /**
         * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���ɍs��insert����B<BR>
         * <BR>
         * this.�i�Ǘ��ҋ��ʁj�A�b�v���[�h�s �̓��e��DB�ɍs��}���iinsert�j����B<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            AdministratorUploadParams l_administratorUploadParams =
                this.administratorUploadParams;

            //this.�i�Ǘ��ҋ��ʁj�A�b�v���[�h�s �̓��e��DB�ɍs��}���iinsert�j����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_administratorUploadParams);

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
