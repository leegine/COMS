head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddressRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃��[���A�h���X�o�^�T�[�r�XImpl(WEB3AccOpenMailAddressRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 �И��� (���u) �V�K�쐬 ���f�� No.166 No.169 �c�a�X�V�d�l 039 043
Revision History : 2009/08/26 �����F (���u) ���f�� No.189
Revision History : 2009/09/03 �����F (���u) ���f�� No.207 �c�a�X�V�d�l 051
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.MailAddressRegiDao;
import webbroker3.accountopen.data.MailAddressRegiParams;
import webbroker3.accountopen.data.MailAddressRegiRow;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenMailAddressRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3AccOpenSendMailStatusDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMailAddressDuplicationCheck;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.WEB3GentradeMobileMailAddressCheck;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�݃��[���A�h���X�o�^�T�[�r�XImpl)<BR>
 * �����J�݃��[���A�h���X�o�^�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AccOpenMailAddressRegistServiceImpl implements WEB3AccOpenMailAddressRegistService
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddressRegistServiceImpl.class);

    /**
     * �����J�݃��[���A�h���X�o�^�T�[�r�XImpl
     */
    public WEB3AccOpenMailAddressRegistServiceImpl()
    {
    }

    /**
     * �����J�݃��[���A�h���X�o�^���������{����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����J�݃��[���A�h���X�o�^���̓��N�G�X�g�̏ꍇ<BR>
     * �@@�|get���͉��()���R�[������B<BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�����J�݃��[���A�h���X�o�^�������N�G�X�g�̏ꍇ<BR>
     * �@@�|submit�o�^()���R�[������B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
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

        //�����̃��N�G�X�g�f�[�^���A�����J�݃��[���A�h���X�o�^���̓��N�G�X�g�̏ꍇ
        //get���͉��()
        if (l_request instanceof WEB3AccOpenMailAddrRegInputRequest)
        {
            WEB3AccOpenMailAddrRegInputResponse l_response =
                getInputScreen((WEB3AccOpenMailAddrRegInputRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //�����̃��N�G�X�g�f�[�^���A�����J�݃��[���A�h���X�o�^�������N�G�X�g�̏ꍇ
        //submit�o�^()
        else if (l_request instanceof WEB3AccOpenMailAddrRegCompleteRequest)
        {
            WEB3AccOpenMailAddrRegCompleteResponse l_response =
                submitRegist((WEB3AccOpenMailAddrRegCompleteRequest)l_request);
            log.exiting(STR_METHOD_NAME);
            return l_response;
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
    }

    /**
     * (get���͉��)<BR>
     * �����J�݃��[���A�h���X�o�^���͉�ʕ\���������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i���[���A�h���X�o�^�jget���͉�ʁv�Q�ƁB <BR>
     * <BR>
     * @@param l_request - �����J�݃��[���A�h���X�o�^���̓��N�G�X�g
     * @@return WEB3AccOpenMailAddrRegInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenMailAddrRegInputResponse getInputScreen(WEB3AccOpenMailAddrRegInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AccOpenMailAddrRegInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //createResponse
        WEB3AccOpenMailAddrRegInputResponse l_response =
            (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o�^)<BR>
     * �����J�݃��[���A�h���X�o�^�����������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i���[���A�h���X�o�^�jsubmit�o�^�v�Q�ƁB<BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(�����J�݃��[���A�h���X�o�^ / �����J�݁i���[���A�h���X�o�^�jsubmit�o�^) <BR>
     * ��̈ʒu�F(���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00777 <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_request - �����J�݃��[���A�h���X�o�^�������N�G�X�g
     * @@return WEB3AccOpenMailAddrRegCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AccOpenMailAddrRegCompleteResponse submitRegist(WEB3AccOpenMailAddrRegCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitRegist(WEB3AccOpenMailAddrRegCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //isMailAddress
        boolean l_blnIsMailAddress = WEB3StringTypeUtility.isMailAddress(l_request.mailAddress);

        //���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����B
        if (!l_blnIsMailAddress)
        {
            log.debug("���[���A�h���X�`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "���[���A�h���X�`�F�b�N�G���[�B");
        }

        //validate�g�уA�h���X
        WEB3GentradeMobileMailAddressCheck.validateMobileAddress(l_request.mailAddress);
        //validate�d�����[���A�h���X
        this.validateRepeatAddress(l_request.institutionCode, l_request.branchCode, l_request.mailAddress);
        //doInsertQuery
        //�}������s�̓��e�́ADB�X�V�d�l�u���[���A�h���X�o�^DB�X�V�iInsert�j�d�l.xls�v�Q��
        MailAddressRegiParams l_mailAddressRegiParams = new MailAddressRegiParams();
        try
        {
            long l_lngValue = MailAddressRegiDao.newPkValue();
            //���[���A�h���X�o�^ID = xTrade�ɂ�莩���̔Ԃ��ꂽ�l
            l_mailAddressRegiParams.setMailAddressRegiId(l_lngValue);
            //�،���ЃR�[�h = ���N�G�X�g�f�[�^.�،���ЃR�[�h
            l_mailAddressRegiParams.setInstitutionCode(l_request.institutionCode);
            //���X�R�[�h = ���N�G�X�g�f�[�^.���X�R�[�h
            l_mailAddressRegiParams.setBranchCode(l_request.branchCode);
            //email�A�h���X = ���N�G�X�g�f�[�^.���[���A�h���X
            l_mailAddressRegiParams.setEmailAddress(l_request.mailAddress);
            //�ڋq���i�����j = ���N�G�X�g�f�[�^.��
            l_mailAddressRegiParams.setFamilyName(l_request.accountFamilyName);
            //�ڋq���i�����j = ���N�G�X�g�f�[�^.��
            l_mailAddressRegiParams.setGivenName(l_request.accountName);
            //����ƈ��҃R�[�h = ���N�G�X�g�f�[�^.����ƈ��҃R�[�h
            l_mailAddressRegiParams.setBrokerageTraderCode(l_request.brokerageCode);
            //�����N�����ʃR�[�h = ���N�G�X�g�f�[�^.�����N�����ʃR�[�h
            l_mailAddressRegiParams.setLinkDistinctionCode(l_request.linkCode);
            //�����敪 = ���N�G�X�g�f�[�^.�����敪
            l_mailAddressRegiParams.setAccountDiv(l_request.accountType);
            //�폜�t���O = 0�F�L��
            l_mailAddressRegiParams.setDeleteFlag(BooleanEnum.FALSE);
            //�X�V�҃R�[�h = ���[���A�h���X�o�^ID�ixTrade�ɂ�莩���̔Ԃ��ꂽ�l�j
            l_mailAddressRegiParams.setLastUpdater(String.valueOf(l_lngValue));
            //�쐬���� = ��������
            l_mailAddressRegiParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //�X�V���� = ��������
            l_mailAddressRegiParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            Processors.getDefaultProcessor().doInsertQuery(l_mailAddressRegiParams);
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

        try
        {
            //���[��(�،���ЃR�[�h : String, ���M���[���敪 : String, ����ID : String)
            //����ID�F���N�G�X�g.�����敪���u0�F�l�A�J�E���g�v�̏ꍇ�A"1"
            //�@@�@@�@@�@@�@@���N�G�X�g.�����敪���u1�F�@@�l�A�J�E���g�v�̏ꍇ�A"2"
            String l_strDiscernmentId = "";
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_request.accountType))
            {
                l_strDiscernmentId = "1";
            }
            else if (WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_request.accountType))
            {
                l_strDiscernmentId = "2";
            }
            WEB3GentradeMailInfo l_mailInfo =
                new WEB3GentradeMailInfo(
                    l_request.institutionCode,
                    WEB3SendmailDivDef.ACCOPEN_APPLICATION_MAIL,
                    l_strDiscernmentId);

            //doInsertQuery
            //�}������s�̓��e�́ADB�X�V�d�l�u���[���A�h���X�o�^_���[�����M�e�[�u��.xls�v�Q��
            MailProcParams l_mailProcParams = new MailProcParams();
            //�،���ЃR�[�h = ���[���A�h���X�o�^�e�[�u��.�،���ЃR�[�h
            l_mailProcParams.setInstitutionCode(l_mailAddressRegiParams.getInstitutionCode());
            //���X�R�[�h = ���[���A�h���X�o�^�e�[�u��.���X�R�[�h
            l_mailProcParams.setBranchCode(l_mailAddressRegiParams.getBranchCode());
            //���M���[���敪 = �����J�ݐ\�����[���i0201�j
            l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.ACCOPEN_APPLICATION_MAIL);
            //����ID  ���N�G�X�g�f�[�^.�����敪��0�F�l�A�J�E���g�̏ꍇ�A"1"
            //           ���N�G�X�g�f�[�^.�����敪��1�F�@@�l�A�J�E���g�̏ꍇ�A"2"
            l_mailProcParams.setDiscernmentId(l_strDiscernmentId);
            //�����R�[�h = "----"
            l_mailProcParams.setAccountCode("----");
            //���[��ID = ���[���A�h���X�o�^�e�[�u��.���[���A�h���X�o�^ID
            l_mailProcParams.setMailId(l_mailAddressRegiParams.getMailAddressRegiId());
            //�N�����P = �������iTradingSystem.getBizDate()�j
            l_mailProcParams.setDate1(GtlUtils.getTradingSystem().getBizDate());
            //ID = ���[���A�h���X�o�^�e�[�u��.���[���A�h���X�o�^ID
            l_mailProcParams.setOrderId(l_mailAddressRegiParams.getMailAddressRegiId());
            //����1 = ���[���A�h���X�o�^�e�[�u��.�ڋq���i�����j
            l_mailProcParams.setName1(l_mailAddressRegiParams.getFamilyName());
            //����2 = ���[���A�h���X�o�^�e�[�u��.�ڋq���i�����j
            l_mailProcParams.setName2(l_mailAddressRegiParams.getGivenName());
            //�d�q���[�����M�X�e�C�^�X = "0�F�������iEmail�����M�j"
            l_mailProcParams.setStatus(WEB3AccOpenSendMailStatusDef.EMAIL_NOT_SEND);
            //email�A�h���X = ���[���A�h���X�o�^�e�[�u��.���[���A�h���X
            l_mailProcParams.setEmailAddress(l_mailAddressRegiParams.getEmailAddress());
            //���[���{�� = ���[���A�h���X�o�^�e�[�u��.���[���A�h���X
            l_mailProcParams.setMailText(l_mailAddressRegiParams.getEmailAddress());
            //�폜�t���O = "0:FALSE�i�L���j"
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
            //�쐬���� = ���ݓ����iGtlUtils.getSystemTimestamp()�j
            l_mailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //�X�V���� = ���ݓ����iGtlUtils.getSystemTimestamp()�j
            l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //doInsertQuery(Row)
            Processors.getDefaultProcessor().doInsertQuery(l_mailProcParams);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug("���[���I�u�W�F�N�g���擾�Ȃ�." + l_ex.getErrorMessage());
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse
        WEB3AccOpenMailAddrRegCompleteResponse l_response =
            (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
        //(*) �v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //���[���A�h���X�o�^�h�c�F�@@���[���A�h���X�o�^�e�[�u����xTrade�ɂ�莩���̔Ԃ��ꂽ���[���A�h���X�o�^�h�c
        l_response.mailRegiID = String.valueOf(l_mailAddressRegiParams.getMailAddressRegiId());
        //���ݓ����F�@@TradingSystem.getSystemTimestamp
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�d�����[���A�h���X)<BR>
     * ���͂������[���A�h���X���o�^����Ă��Ȃ����`�F�b�N����B <BR>
     * <BR>
     * �P�j �ڋq�}�X�^�e�[�u���Ƀ��[���A�h���X�����݂��邩�`�F�b�N����B <BR>
     * �@@�@@�P�|�P�j webbroker3.gentrade.���[���A�h���X�d���`�F�b�N�N���X���g�p����B <BR>
     * <BR>
     * �@@�@@[���[���A�h���X�d���`�F�b�N.get�d���A�h���X()�Ɏw�肷�����] <BR>
     * �@@�@@���[���A�h���X�F����.���[���A�h���X <BR>
     * �@@�@@�ڋq�R�[�h�Fnull <BR>
     * �@@�@@���X�R�[�h�F����.���X�R�[�h <BR>
     * �@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h <BR>
     * <BR>
     * �@@�@@�P�|�Q�j �d���A�h���X�����݂���<BR>
     * �@@�@@�i���[���A�h���X�d���`�F�b�N.get�d���A�h���X()�̖߂�l�̒���>0�j�ꍇ�A�G���[���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02443<BR>
     * <BR>
     * �Q�j �����J�݌����q�e�[�u���Ƀ��[���A�h���X�����݂��邩�`�F�b�N���� <BR>
     * �@@�@@�Q�|�P�j �����J�݌����q�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B <BR>
     * <BR>
     * �@@�@@[�d���A�h���X��������] <BR>
     * �@@�@@�����J�݌����q.�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�@@AND �����J�݌����q.���X�R�[�h = ����.���X�R�[�h <BR>
     * �@@�@@AND �����J�݌����q.email�A�h���X = ����.���[���A�h���X <BR>
     * <BR>
     * �@@�@@�Q�|�Q�j �Y���s�����݂���ꍇ�A�G���[���X���[����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02443<BR>
     * <BR>
     * �R�j ���[���A�h���X�o�^�e�[�u���Ƀ��[���A�h���X�����݂��邩�`�F�b�N���� <BR>
     * �@@�@@�R�|�P�j���[���A�h���X�o�^�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B <BR>
     * <BR>
     * �@@�@@[�d���A�h���X��������] <BR>
     * �@@�@@���[���A�h���X�o�^.�،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �@@�@@AND ���[���A�h���X�o�^.���X�R�[�h = ����.���X�R�[�h <BR>
     * �@@�@@AND ���[���A�h���X�o�^.email�A�h���X = ����.���[���A�h���X <BR>
     * �@@�@@AND ���[���A�h���X�o�^.�폜�t���O = "0�F�L��" <BR>
     * <BR>
     * �@@�@@�R�|�Q�j �Y���s�����݂���ꍇ�A�G���[���X���[����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02443<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strMailAddress - ���[���A�h���X
     * @@throws WEB3BaseException
     */
    private void validateRepeatAddress(String l_strInstitutionCode, String l_strBranchCode, String l_strMailAddress)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRepeatAddress(String, String, String)";
        log.entering(STR_METHOD_NAME);
        //webbroker3.gentrade.���[���A�h���X�d���`�F�b�N�N���X���g�p����B
        Object[] l_objs =
            WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
                l_strMailAddress, null, l_strBranchCode, l_strInstitutionCode);
        //�d���A�h���X�����݂���i���[���A�h���X�d���`�F�b�N.get�d���A�h���X()�̖߂�l�̒���>0�j�ꍇ�A�G���[���X���[����B
        if (l_objs != null && l_objs.length > 0)
        {
            log.debug("���[���A�h���X�͊��ɓo�^�ς݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02443,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "���[���A�h���X�͊��ɓo�^�ς݂ł��B");
        }
        //�����J�݌����q�e�[�u���Ƀ��[���A�h���X�����݂��邩�`�F�b�N����
        //[�d���A�h���X��������]
        //�����J�݌����q.�،���ЃR�[�h = ����.�،���ЃR�[�h
        //AND �����J�݌����q.���X�R�[�h = ����.���X�R�[�h
        //AND �����J�݌����q.email�A�h���X = ����.���[���A�h���X
        String l_strWhere = " institution_code = ? and branch_code = ? and email_address = ? ";
        Object[] l_objConds =  new Object[]{l_strInstitutionCode, l_strBranchCode, l_strMailAddress};
        QueryProcessor l_queryProcessor = null;
        List l_lisRecordexcs = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordexcs = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strWhere,
                l_objConds);
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
        //�Y���s�����݂���ꍇ�A�G���[���X���[����B
        if (!l_lisRecordexcs.isEmpty())
        {
            log.debug("���[���A�h���X�͊��ɓo�^�ς݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02443,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "���[���A�h���X�͊��ɓo�^�ς݂ł��B");
        }

        //���[���A�h���X�o�^�e�[�u���Ƀ��[���A�h���X�����݂��邩�`�F�b�N����
        //[�d���A�h���X��������]
        //���[���A�h���X�o�^.�،���ЃR�[�h = ����.�،���ЃR�[�h
        //AND ���[���A�h���X�o�^.���X�R�[�h = ����.���X�R�[�h
        //AND ���[���A�h���X�o�^.email�A�h���X = ����.���[���A�h���X
        //AND ���[���A�h���X�o�^.�폜�t���O = "0�F�L��"
        String l_strWhere1 = " institution_code = ? and branch_code = ? and email_address = ? and delete_flag = ? ";
        Object[] l_objConds1 =  new Object[]{l_strInstitutionCode, l_strBranchCode, l_strMailAddress, BooleanEnum.FALSE};
        List l_lisRecordexcs1 = null;
        try
        {
            l_lisRecordexcs1 = l_queryProcessor.doFindAllQuery(
                MailAddressRegiRow.TYPE,
                l_strWhere1,
                l_objConds1);
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
        //�Y���s�����݂���ꍇ�A�G���[���X���[����B
        if (!l_lisRecordexcs1.isEmpty())
        {
            log.debug("���[���A�h���X�͊��ɓo�^�ς݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02443,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "���[���A�h���X�͊��ɓo�^�ς݂ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
