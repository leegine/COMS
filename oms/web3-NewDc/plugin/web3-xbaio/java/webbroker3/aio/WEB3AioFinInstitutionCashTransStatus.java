head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioFinInstitutionCashTransStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Z�@@�֘A�g���o���󋵃N���X(WEB3AioFinInstitutionCashTransStatus)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 ���z (���u) �V�K�쐬
                   2004/10/22 ��O�� (���u) ���r���[ 
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.aio.data.BankCashTransferStatusDao;
import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;


/**
 * (���Z�@@�֘A�g���o����)<BR>
 * ���Z�@@�֘A�g���o���󋵃N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0 
 */
public class WEB3AioFinInstitutionCashTransStatus implements BusinessObject 
{
    /**
    * ���O���[�e�B���e�B
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioFinInstitutionCashTransStatus.class);
        
    /**
     * (���Z�@@�֘A�g���o����Row)<BR>
     * ���Z�@@�֘A�g���o���󋵍s�I�u�W�F�N�g
     */
    private BankCashTransferStatusParams bankCashTransferStatusParams;
    
    /**
     * (���Z�@@�֘A�g���o����)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �������L�[�Ƃ��āA���Z�@@�֘A�g���o���󋵃e�[�u����<BR>
     * ���R�[�h���擾���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)
     * @@return WEB3AioFinInstitutionCashTransStatus
     * @@throws WEB3BaseException
     * @@roseuid 40F7BB060399
     */
    public WEB3AioFinInstitutionCashTransStatus(           
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strOrderRequestNumber)
        throws WEB3BaseException
    {
        String l_strMethodName = "WEB3AioFinInstitutionCashTransStatus("
            + "String l_strInstitutionCode," 
            + "String l_strBranchCode," 
            + "String l_strOrderRequestNumber)";
        log.entering(l_strMethodName);
        
        try
        {
            // �������L�[�Ƃ��āA���Z�@@�֘A�g���o���󋵃e�[�u���̃��R�[�h���擾����
            BankCashTransferStatusRow l_row = 
                BankCashTransferStatusDao.findRowByInstitutionCodeBranchCodeOrderRequestNumber(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strOrderRequestNumber);
            if (l_row == null)
            {
                log.debug("���Z�@@�֘A�g���o���󋵃e�[�u���̃��R�[�h���擾���Ȃ�");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName);
            }
            bankCashTransferStatusParams = new BankCashTransferStatusParams(l_row);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set����FLAG�i�����j)<BR>
     * ����FLAG�i�����j���Z�b�g����B
     * @@param l_strFlag - (����FLAG)
     * @@roseuid 40E8D4E50183
     */
    public void setOrderStatusFlag(String l_strFlag) 
    {
        String l_strMethodName = "setOrderStatusFlag(String l_strFlag)";
        log.entering(l_strMethodName);
        
        //����FLAG�i�����j���Z�b�g����
        bankCashTransferStatusParams.setOrderStatusFlag(l_strFlag);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�������ԁi�����v���j)<BR>
     * �������ԁi�����v���j���Z�b�g����B
     * @@param l_datTransactionTime - (��������)
     * @@roseuid 40E8D6C60057
     */
    public void setOrderRequestTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setOrderRequestTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        // �������ԁi�����v���j���Z�b�g����
        bankCashTransferStatusParams.setOrderRequestTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�������ԁi���������j)<BR>
     * �������ԁi���������j���Z�b�g����B
     * @@param l_datTransactionTime - (��������)
     * @@roseuid 40E8D6D60289
     */
    public void setOrderResponseTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setOrderResponseTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //�������ԁi���������j���Z�b�g����
        bankCashTransferStatusParams.setOrderResponseTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set����FLAG�i���ϊJ�n�j)<BR>
     * ����FLAG�i���ϊJ�n�j���Z�b�g����B
     * @@param l_strFlag - (����FLAG)
     * @@roseuid 40E8D6E403D0
     */
    public void setStartStatusFlg(String l_strFlag) 
    {
        String l_strMethodName = "setStartStatusFlg(String l_strFlag)";
        log.entering(l_strMethodName);
        //����FLAG�i���ϊJ�n�j���Z�b�g����      
        bankCashTransferStatusParams.setStartStatusFlag(l_strFlag);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�������ԁi���ϊJ�n�v���j)<BR>
     * �������ԁi���ϊJ�n�v���j���Z�b�g����B
     * @@param l_datTransactionTime - (��������)
     * @@roseuid 40E8D6FF03CF
     */
    public void setStartRequestTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setStartRequestTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //�������ԁi���ϊJ�n�v���j���Z�b�g����        
        bankCashTransferStatusParams.setStartRequestTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�������ԁi���ϊJ�n�����j)<BR>
     * �������ԁi���ϊJ�n�����j���Z�b�g����B
     * @@param l_datTransactionTime - (��������)
     * @@roseuid 40E8D710012F
     */
    public void setStartResponseTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setStartResponseTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //�������ԁi���ϊJ�n�����j���Z�b�g����
        bankCashTransferStatusParams.setStartResponseTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set����FLAG�i���ό��ʁj)<BR>
     * ����FLAG�i���ό��ʁj���Z�b�g����B
     * @@param l_strFlag - (����FLAG)
     * @@roseuid 40E8D71F0082
     */
    public void setResultStatusFlag(String l_strFlag) 
    {
        String l_strMethodName = "setResultStatusFlag(String l_strFlag)";
        log.entering(l_strMethodName);
        //����FLAG�i���ό��ʁj���Z�b�g����
        bankCashTransferStatusParams.setResultStatusFlag(l_strFlag);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�������ԁi���ό��ʒʒm�j)<BR>
     * �������ԁi���ό��ʒʒm�j���Z�b�g����B
     * @@param l_datTransactionTime - (��������)
     * @@roseuid 40E8D72B02B4
     */
    public void setResultRequestTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setResultRequestTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //�������ԁi���ό��ʒʒm�j���Z�b�g����           
        bankCashTransferStatusParams.setResultRequestTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�������ԁi���ό��ʉ����j)<BR>
     * �������ԁi���ό��ʉ����j���Z�b�g����B
     * @@param l_datTransactionTime - (��������)
     * @@roseuid 40E8D7500236
     */
    public void setResultResponseTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setResultResponseTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //�������ԁi���ό��ʉ����j���Z�b�g����
        bankCashTransferStatusParams.setResultResponseTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�����敪)<BR>
     * �����敪���Z�b�g����B
     * @@param l_strTransactionTime - (�����敪)
     * @@roseuid 4118ACC80179
     */
    public void setStatus(String l_strTransactionTime) 
    {
        String l_strMethodName = "setStatus(String l_strTransactionTime)";
        log.entering(l_strMethodName);
        //�����敪���Z�b�g����
        bankCashTransferStatusParams.setTransactionStatus(l_strTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (��������)<BR>
     * �������Ԃ��Z�b�g����B
     * @@param l_datTransactionTime - (��������)
     * @@roseuid 4119AF7502D0
     */
    public void setTransactionTime(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setTransactionTime(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //�������Ԃ��Z�b�g����
        bankCashTransferStatusParams.setTransactionTime(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (.com�f�r�b�g���ώ���ԍ�)<BR>
     * .com�f�r�b�g���ώ���ԍ����Z�b�g����B
     * @@param l_strTradeNumber - (����ԍ�)<BR>
     * .com�f�r�b�g���ώ���ԍ�
     * @@roseuid 4118B25C025B
     */
    public void setComDebitNumber(String l_strTradeNumber) 
    {
        String l_strMethodName = "setComDebitNumber(String l_strTradeNumber)";
        log.entering(l_strMethodName);
        //.com�f�r�b�g���ώ���ԍ����Z�b�g����
        bankCashTransferStatusParams.setCenterPayId(l_strTradeNumber);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (set�ŏI�X�V����)<BR>
     * �ŏI�X�V�������Z�b�g����B
     * @@param l_datTransactionTime - (��������)
     * @@roseuid 4119BA3C0040
     */
    public void setLastUpdateTimestamp(Date l_datTransactionTime) 
    {
        String l_strMethodName = "setLastUpdateTimestamp(Date l_datTransactionTime)";
        log.entering(l_strMethodName);
        //�ŏI�X�V�������Z�b�g����
        bankCashTransferStatusParams.setLastUpdateTimestamp(l_datTransactionTime);
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * this.���Z�@@�֘A�g���o����Row��ԋp����B
     * @@return Object
     * @@roseuid 4118B4FA03B3
     */
    public Object getDataSourceObject() 
    {
        //this.���Z�@@�֘A�g���o����Row��ԋp����
        return bankCashTransferStatusParams;
    }
    
    /**
     * this.���Z�@@�֘A�g���o����Row���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬����iclone�j�B <BR>
     * �쐬�����R�s�[�����g��this.���Z�@@�֘A�g���o����Row�ɃZ�b�g����B
     * @@roseuid 4133F9CF0279
     */
    public void createForUpdateParams() 
    {
        String l_strMethodName = "createForUpdateParams()";
        log.entering(l_strMethodName);
        //this.���Z�@@�֘A�g���o����Row���R�s�[����
        BankCashTransferStatusParams l_params = new BankCashTransferStatusParams(this.bankCashTransferStatusParams);
        //�쐬�����R�s�[�����g��this.���Z�@@�֘A�g���o����Row�ɃZ�b�g����
        this.bankCashTransferStatusParams = l_params;
        
        log.exiting(l_strMethodName);
    }

}
@
