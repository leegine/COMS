head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeMailSendServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�����[�����M�T�[�r�X�����N���X(WEB3AioCashinNoticeMailSendServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ���E (���u) �V�K�쐬
                   2004/10/25 ���� (���u) ���r���[ 
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.data.DepositInformParams;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeMailSendService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MailSendDef;
import webbroker3.common.define.WEB3ResendStatusDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.gentrade.data.FinInstitutionDao;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A�����[�����M�T�[�r�XImpl)<BR>
 * �����A�����[�����M�T�[�r�X�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AioCashinNoticeMailSendServiceImpl 
    implements WEB3AioCashinNoticeMailSendService 
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeMailSendServiceImpl.class);

    /**
     * (create���[��)<BR>
     * �����A�����[���s��DB�ɓo�^����B <BR>
     * <BR>
     * �P�j���[���e�[�u�����烌�R�[�h���擾����B <BR>
     *<BR> 
     *  [����] <BR>
     *      �،���ЃR�[�h = ����.�����A��Params.�،���ЃR�[�h <BR>
     *      ���M���[���敪 = "0701" <BR>
     *      ����ID = "----" <BR>
     * <BR>
     * �Q�j���[�����M�s�I�u�W�F�N�g���쐬���ADB�ɑ}������B <BR>
     * <BR>
     * ���[�����M�s�I�u�W�F�N�g�̕ҏW���e�́A <BR>
     * DB�X�V�d�l�u�����A��_���[�����M�e�[�u��.xls�v ���Q�ƁB <BR>
     * <BR>
     * @@param l_depositInformParams - (�����A���s)<BR>
     * �����A���s�I�u�W�F�N�g<BR>
     * @@param l_emailAddress - (���[���A�h���X)<BR>
     * �����A���s�I�u�W�F�N�g<BR>
     * @@throws WEB3SystemLayerException
     * @@param l_mail - (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     * @@roseuid 40EE8D850176
     */
    public void createMail(
            DepositInformParams l_depositInformParams, 
            String l_strEmailAddress) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "createMail(DepositInformParams l_depositInformParams, " +
            "String l_strEmailAddress) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_depositInformParams == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        List l_lisMailInfoRows = null;
        FinInstitutionRow l_finInstitutionRow = null;
        MainAccountRow l_mainAccountRow = null;
        try
        {
            String l_strWhereClause = "institution_code = ? " +
                    "and sendmail_div = ? and discernment_id = ?";
            Object l_bindVars[] = {
                    l_depositInformParams.getInstitutionCode(), 
                    WEB3SendmailDivDef.AIO_DEPOSIT_INFORM,
                    "----"};
            
            //�P�j���[���e�[�u�����烌�R�[�h���擾����B 
            //[����] 
            //�،���ЃR�[�h = ����.�����A��Params.�،���ЃR�[�h 
            //���M���[���敪 = "0701" 
            //����ID = "----" 
            
            l_lisMailInfoRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    MailInfoRow.TYPE,
                    l_strWhereClause,                    
                    null,
                    l_bindVars);
            
            // �����A��.���Z�@@�փR�[�h�ɑΉ�������Z�@@�փe�[�u��
            l_finInstitutionRow = FinInstitutionDao.findRowByPk(
                l_depositInformParams.getInstitutionCode(),
                l_depositInformParams.getFinInstitutionCode());
            
            // �����A��.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h����擾�����ڋq�I�u�W�F�N�g
            l_mainAccountRow = MainAccountDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_depositInformParams.getInstitutionCode(),
                l_depositInformParams.getBranchCode(),
                l_depositInformParams.getAccountCode());
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MailInfoRow l_mailInfoRow = (MailInfoRow) l_lisMailInfoRows.get(0);
        
        MailProcParams l_mailProcParams = new MailProcParams();
        
        //�،���ЃR�[�h
        l_mailProcParams.setInstitutionCode(l_depositInformParams.getInstitutionCode());
        
        //���X�R�[�h
        l_mailProcParams.setBranchCode(l_depositInformParams.getBranchCode());
        
        //���M���[���敪
        l_mailProcParams.setSendmailDiv(l_mailInfoRow.getSendmailDiv());
        
        //����ID
        l_mailProcParams.setDiscernmentId(l_mailInfoRow.getDiscernmentId());
        
        //�ڋq�R�[�h
        l_mailProcParams.setAccountCode(l_depositInformParams.getAccountCode());
        
        //���[��ID
        l_mailProcParams.setMailId(Long.parseLong(l_depositInformParams.getOrderRequestNumber()));
        
        //�N�����P
        l_mailProcParams.setDate1(l_depositInformParams.getTransferDate());
             
        //�N�����Q
        l_mailProcParams.setDate2(null);
        
        //�N�����R
        l_mailProcParams.setDate3(null);
        
        //�N�����S
        l_mailProcParams.setDate4(null);
        
        //����
        l_mailProcParams.setQuantity(null);
        
        //���z
        l_mailProcParams.setAmount(l_depositInformParams.getAmount());
        
        //ID
        l_mailProcParams.setOrderId(null);
        
        //�敪
        l_mailProcParams.setDivision(null);
        
        //����1
        //�����A��.���Z�@@�փR�[�h�ɑΉ�������Z�@@�֖�
        //�����Z�@@�փe�[�u��.���Z�@@�֖��i�����j
        l_mailProcParams.setName1(l_finInstitutionRow.getFinInstitutionNameKanji());
        
        //����2
        //�����A��.�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h����擾�����ڋq�I�u�W�F�N�g��get�ڋq�\����()�ɂē���ꂽ�ڋq��
        l_mailProcParams.setName2(l_mainAccountRow.getFamilyName());
        
        //�d�q���[�����M�X�e�C�^�X
        l_mailProcParams.setStatus(WEB3MailSendDef.NOT_ENFORCEMENT);
        
        //�d�q���[�����M����
        l_mailProcParams.setSendProcessDateTime(null);
        
        //�đ��敪
        l_mailProcParams.setResendStatus(WEB3ResendStatusDef.DEFAULT);
        
        //�d�q���[���đ�����
        l_mailProcParams.setResendProcessDateTime(null);
        
        //email�A�h���X
        l_mailProcParams.setEmailAddress(null);
        
        //���Memail�A�h���X
        l_mailProcParams.setSendEmailAddress(l_strEmailAddress);
        
        //����
        l_mailProcParams.setSubject(null);
        
        //���[���{��
//        l_mailProcParams.setMailText(null);
        
        //�폜�t���O
        l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
        
        //�쐬����
        l_mailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //�X�V����
        l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            //�����A�����[�����M�e�[�u��DB�ɑ}��
            WEB3DataAccessUtility.insertRow(l_mailProcParams);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
