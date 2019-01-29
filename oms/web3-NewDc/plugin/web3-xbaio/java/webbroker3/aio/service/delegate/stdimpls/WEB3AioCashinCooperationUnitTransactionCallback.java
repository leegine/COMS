head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.33.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationUnitTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�g�����U�N�V�����R�[���o�b�N(WEB3AioCashinCooperationUnitTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/12 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.define.WEB3AioBankDepositNotifyCashTransferDivDef;
import webbroker3.aio.define.WEB3AioBankDepositNotifyTradeDivDef;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationNotifyUnitService;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����A�g�g�����U�N�V�����R�[���o�b�N�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ��O��
 * @@version 1.0
 */
public class WEB3AioCashinCooperationUnitTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationUnitTransactionCallback.class);
    
    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3AioCashinCooperationUnitTransactionCallback()
    {
        
    }

   /**
     * �����ʒm�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u(�����A�g�ʒm�񓯊��T�[�r�XImpl)run()�v �Q�� <BR>
     * <BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        //1.1.1.1.1 ��s�����ʒm�e�[�u������
        //�ȉ��̏����Ō����B

        //�����敪 = ������
        String l_strWhere = " status = ? and cash_transfer_div = ? and trade_div = ? ";

        Object[] l_bindVars = {
                WEB3StatusDef.NOT_DEAL, 
                WEB3AioBankDepositNotifyCashTransferDivDef.CASH_IN,
                WEB3AioBankDepositNotifyTradeDivDef.PAY_BY_TRANSFER,
                };
        
        List l_lisRows = 
            Processors.getDefaultProcessor().doFindAllQuery(
                BankDepositNotifyRow.TYPE, 
                l_strWhere, 
                null, 
                l_bindVars);        
        
        int l_intResultSize = 0;

        if (l_lisRows != null && l_lisRows.size() != 0)
        {
            l_intResultSize = l_lisRows.size();
            log.debug("l_intResultSize = " + l_intResultSize);
        }
        
        //1.1.1.1.2 �������ꂽ�����ʒm���R�[�h������LOOP        

        for (int i = 0; i < l_intResultSize; i++)
        {
           
            BankDepositNotifyRow l_bankDepositNotifyRow = 
                (BankDepositNotifyRow) l_lisRows.get(i);
            
            BankDepositNotifyParams l_bankDepositNotifyParams = 
                new BankDepositNotifyParams(l_bankDepositNotifyRow);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
            WEB3GentradeMainAccount l_mainAccount = null;
            
            try
            {
                //1.1.1.1.2.1
                //�����ʒm���R�[�h.���X�R�[�h != null�@@���� 
                //�����ʒm���R�[�h.�ڋq�R�[�h != null �̏ꍇ
                if (l_bankDepositNotifyParams.getBranchCode() != null &&
                    l_bankDepositNotifyParams.getAccountCode() != null)
                {
                    //1.1.1.1.2.1.1 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String) 
                    //�ڋq�I�u�W�F�N�g���擾���ԋp�B
                    //�����F
                    //�،���ЃR�[�h = �����ʒm���R�[�h.�،���ЃR�[�h
                    //���X�R�[�h = �����ʒm���R�[�h.���X�R�[�h
                    //�ڋq�R�[�h = �����ʒm���R�[�h.�ڋq�R�[�h
                    
                    l_mainAccount = l_accountManager.getMainAccount(
                        l_bankDepositNotifyParams.getInstitutionCode(),
                        l_bankDepositNotifyParams.getBranchCode(),
                        l_bankDepositNotifyParams.getAccountCode());            
                }
                //1.1.1.1.2.2 (1.1.1.2.1�ȊO)
                else
                {                
                    //1.1.1.1.2.2.1
					//��p�U��������e�[�u�����ȉ��̏����Ō����B
					//�،���ЃR�[�h=�����ʒm���R�[�h.�،���ЃR�[�h
					//��s�R�[�h = �����ʒm���R�[�h.��s�R�[�h
					//�x�X�R�[�h = �����ʒm���R�[�h.��s�x�X�R�[�h
					//�����ԍ� = �����ʒm���R�[�h.�����ԍ�                   
                    StringBuffer l_strBfWhere = new StringBuffer();
                    l_strBfWhere.append(" institution_code = ? ");
                    l_strBfWhere.append(" and fin_institution_code = ? ");
                    l_strBfWhere.append(" and fin_branch_code = ? ");
                    l_strBfWhere.append(" and fin_account_no = ? ");
                            
                    Object[] l_objWhereValues = {                
                        l_bankDepositNotifyRow.getInstitutionCode(), 
                        l_bankDepositNotifyRow.getBankCode(), 
                        l_bankDepositNotifyRow.getBankBranchCode(), 
                        l_bankDepositNotifyRow.getDepositDataTransPersonCode()};               
                    
    
                    List l_lisExclusiveUseAccountRows =
                        l_queryProcessor.doFindAllQuery(
                                ExclusiveUseAccountRow.TYPE,
                            l_strBfWhere.toString(),
                            null,
                            l_objWhereValues);

                    ////1.1.1.1.2.2.2
                    //[�������ʃ��X�g== null���邢�͌������ʌ��� < 1�̏ꍇ]
                     //"�Y���f�[�^�Ȃ�"�G���[��O���X���[����B
                    
                    if((l_lisExclusiveUseAccountRows == null) ||
                            (l_lisExclusiveUseAccountRows.size() < 1))
                    {
                        log.debug("�e�[�u���ɊY������f�[�^������܂���B�i��p�U��������j");
                        throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "�e�[�u���ɊY������f�[�^������܂���B�i��p�U��������j");
                        
                    }
                    
                    ////1.1.1.1.2.3
                    //[�������ʌ��� > 1�̏ꍇ]
                    //"�Y���f�[�^�d���B"�G���[��O���X���[����B                    
                    if (l_lisExclusiveUseAccountRows.size() > 1)
                    {
                        log.debug("�e�[�u���ɏd������Y���f�[�^�����݂��܂��B�i��p�U��������j");
                        throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                "�e�[�u���ɏd������Y���f�[�^�����݂��܂��B�i��p�U��������j");
                    }
                    
                    ExclusiveUseAccountRow l_exclusiveUseAccountRow = 
                        (ExclusiveUseAccountRow) l_lisExclusiveUseAccountRows.get(0);                
                
                    //1.1.1.1.2.2.3 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
                    //�ڋq�I�u�W�F�N�g���擾���ԋp�B
                    //�����F
                    //�،���ЃR�[�h = �U������Z�@@�փe�[�u��.�،���ЃR�[�h
                    //���X�R�[�h = �U������Z�@@�փe�[�u��.���X�R�[�h
                    //�ڋq�R�[�h = �U������Z�@@�փe�[�u��.�ڋq�R�[�h
                    l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                            l_exclusiveUseAccountRow.getAccountId());
                    
                }
                
                //1.1.1.1.2.3
                //notify�����A�g(�����ʒmParams, �ڋq)            
                WEB3AioCashinCooperationNotifyUnitService l_notifyUnitService = 
                    (WEB3AioCashinCooperationNotifyUnitService)Services.getService(
                        WEB3AioCashinCooperationNotifyUnitService.class);
                
                l_notifyUnitService.notifyCashinCooperation(
                    l_bankDepositNotifyParams, 
                    l_mainAccount);
                
            }
            catch (Exception l_ex)
            {
                log.debug("�����ʒm�P���������O������................... ", l_ex);
                //1.1.1.1.2.4 ��O������
	            //�����A�g�ʒm1���ُ펞�g�����U�N�V�����R�[���o�b�N���쐬��
	            //�V�K�g�����U�N�V������
	            //���Y�����ʒm���R�[�h���X�V�A
	            //�����ʒm�����G���[�����e�[�u����1���ǉ�����B
	            //[�X�V���e]
	            //�����敪 = "�G���["
	            //�G���[�R�����g = ��O.��O���b�Z�[�W
	            //1.1.1.1.2.4.1 �����A�g�ʒm1���ُ펞�g�����U�N�V�����R�[���o�b�N(�����ʒmParams, Exception)
	            WEB3AioCashinCooperationNotifyUnitExpTransactionCallback l_expTransactionCallback = 
	                new WEB3AioCashinCooperationNotifyUnitExpTransactionCallback(
	                    l_bankDepositNotifyParams, 
	                    l_ex);
	            //1.1.1.1.2.4.2 doTransaction(arg0 : int, arg1 : TransactionCallback)               
	            l_queryProcessor.doTransaction(l_expTransactionCallback);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}




@
