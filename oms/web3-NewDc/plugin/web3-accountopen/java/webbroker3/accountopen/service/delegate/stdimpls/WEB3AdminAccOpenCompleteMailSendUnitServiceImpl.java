head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݊������[�����MUnitServiceImpl(WEB3AdminAccOpenCompleteMailSendUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�݊������[�����MUnitServiceImpl)<BR>
 * �����J�݊������[�����MUnitService�����N���X<BR>
 * �i�g�����U�N�V��������=TX_CREATE_NEW�j<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendUnitServiceImpl implements WEB3AdminAccOpenCompleteMailSendUnitService 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendUnitServiceImpl.class);
    
    /**
     * @@roseuid 41B45E7101D4
     */
    public WEB3AdminAccOpenCompleteMailSendUnitServiceImpl() 
    {
     
    }
    
    /**
     * (sendMailProcess)<BR>
     * �w��ڋq�Ɍ����J�݊������[���𑗐M����B<BR>
     * <BR>
     * �P�j�@@���[�����M�e�[�u���X�V<BR>
     * �@@DB�X�V�d�l�̒ʂ�ɁA���[�����M�e�[�u���ɍs��ǉ��iDB-insert�j����B<BR>
     * <BR>
     * �@@DB�X�V�d�l�u�������[�����M_���[�����M�e�[�u��.xls�v�Q��<BR>
     * <BR>
     * �Q�j�@@�ڋq�}�X�^�X�V<BR>
     * �@@DB�X�V�d�l�̒ʂ�ɁA�ڋq�}�X�^���X�V�iDB-update�j����B<BR>
     * <BR>
     * �@@DB�X�V�d�l�u�������[�����M_�ڋq�}�X�^.xls�v�Q��<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@roseuid 41A54BFB00BC
     */
    public void sendMailProcess(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " sendMailProcess(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���[�����M�e�[�u���X�V
        MailProcParams l_mailProcParams = new MailProcParams();
        
        MainAccountParams l_mainAccountParams = new MainAccountParams((MainAccountParams)l_mainAccount.getDataSourceObject());        
        
        //�،���ЃR�[�h
        l_mailProcParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
        
        //���X�R�[�h
        l_mailProcParams.setBranchCode(l_mainAccountParams.getBranchCode());
        
        //���M���[���敪  �����J�݊������[���i0202�j
        l_mailProcParams.setSendmailDiv(WEB3SendmailDivDef.ACCOPEN_COMPLETE_MAIL);
        
        //����ID
        l_mailProcParams.setDiscernmentId("----");
        
        //�����R�[�h
        l_mailProcParams.setAccountCode(l_mainAccountParams.getAccountCode());
        
        //���[��ID
        //�،���ЃR�[�h�C���X�R�[�h�C�����R�[�h�C
        //���M���[���敪�ɂĊ����f�[�^������ꍇ�͊����f�[�^�̍ő�l�{�P�B�ȊO�A0�B
        l_mailProcParams.setMailId(0);
        
        //�N�����P
        l_mailProcParams.setDate1(l_mainAccountParams.getAccountOpenDate());
        
        //�N�����Q
        l_mailProcParams.setDate2(null);
        
        //�N�����R
        l_mailProcParams.setDate3(null);
        
        //�N�����S
        l_mailProcParams.setDate4(null);
        
        //����
        l_mailProcParams.setQuantity(null);
        
        //���z
        l_mailProcParams.setAmount(null);
        
        //ID
        l_mailProcParams.setOrderId(null);
        
        //�敪
        l_mailProcParams.setDivision(null);
        
        //�d�q���[�����M�X�e�C�^�X
        l_mailProcParams.setStatus(WEB3CarryoverEndTypeDef.NOT_STARTED_PROCESS);
        
        //�d�q���[�����M����  
        l_mailProcParams.setSendProcessDateTime(null);
        
        //�đ��敪 
        l_mailProcParams.setResendStatus(null);
        
        //�d�q���[���đ�����
        l_mailProcParams.setResendProcessDateTime(null);
        
        //email�A�h���X 
        l_mailProcParams.setEmailAddress(l_mainAccountParams.getEmailAddress());
        
        //�폜�t���O
        l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        //�쐬����
        l_mailProcParams.setCreatedTimestamp(l_tsSystemTime);
        
        //�X�V����
        l_mailProcParams.setLastUpdatedTimestamp(l_tsSystemTime);
        
        try
        {
            //DB�X�V�d�l�̒ʂ�ɁA���[�����M�e�[�u���ɍs��ǉ��iDB-insert�j����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            l_queryProcessor.doInsertQuery(l_mailProcParams);//DataNetworkException,DataQueryException
        
            //�Q�j�ڋq�}�X�^�X�V
            //�����J�݊������[�����M�X�e�[�^�X
            //1�F�����ρiEmail���M�ρj           
            l_mainAccountParams.setAccOpenSendMailStatus(WEB3CarryoverEndTypeDef.COMPLETE_PROCESS);
            
            l_queryProcessor.doUpdateQuery(l_mainAccountParams);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }        
        
        log.exiting(STR_METHOD_NAME);        
    }
}@
