head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�ɒʒm���폈���ꌏTransactionCallback(WEB3AioOutputNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/19 ��O�� (���u) �V�K�쐬
*/


package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

import webbroker3.aio.data.HostSecDeliveryTransferRow;
import webbroker3.aio.data.HostTransferPaymentRow;
import webbroker3.aio.define.WEB3HostTransferPaymentStatusDef;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;




/**
 * �i�،��U�֒ʒm���폈���ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ��O��
 * @@version 1.0
 */
public class WEB3AioOutputNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyNormalTransactionCallback.class);

    /**
      * �U�֐����`�[�L���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostTransferPaymentRow hostTransferPaymentRow;
    
    /**
     * �،��o�ɐ����`�[�L���[Row�I�u�W�F�N�g�B<BR>
     */
    private HostSecDeliveryTransferRow hostSecDeliveryTransferRow;
   
    /**
     * is�U�֐����`�[�L���[Row�B<BR>
     */
    private boolean isTransferPayment;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostTransferPaymentRow - (�U�֐����`�[�L���[Row)
     * @@params l_blnIsTransferPayment - (is�U�֐����`�[�L���[)
     */
    public WEB3AioOutputNotifyNormalTransactionCallback(
        HostTransferPaymentRow l_hostTransferPaymentRow,
        boolean l_blnIsTransferPaymentRow)
    {
        hostTransferPaymentRow = l_hostTransferPaymentRow;
        isTransferPayment = l_blnIsTransferPaymentRow;
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_hostTransferPaymentRow - (�،��o�ɐ����`�[�L���[Row)
     * @@params l_blnIsTransferPayment - (is�U�֐����`�[�L���[)
     */
    public WEB3AioOutputNotifyNormalTransactionCallback(
        HostSecDeliveryTransferRow l_hostSecDeliveryTransferRow,
        boolean l_blnIsTransferPayment)
    {
        hostSecDeliveryTransferRow = l_hostSecDeliveryTransferRow;
        isTransferPayment = l_blnIsTransferPayment;
    }
    
    

   /**
     * �g�����U�N�V�������������{����B<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        if (isTransferPayment)
        {
            HostTransferPaymentRow l_hostTransferPaymentRow = hostTransferPaymentRow;       
    
            //�،���ЃR�[�h�F �U�֐����`�[�L���[�e�[�u��.�،���ЃR�[�h 
            String l_strInstatutionCode =
                l_hostTransferPaymentRow.getInstitutionCode();
            
            //���X�R�[�h�F �U�֐����`�[�L���[�e�[�u��.���X�R�[�h 
            String l_strBranchCode =
                l_hostTransferPaymentRow.getBranchCode();
            
            //�ڋq�R�[�h�F �U�֐����`�[�L���[�e�[�u��.�ڋq�R�[�h 
            String l_strAccountCode =
                l_hostTransferPaymentRow.getAccountCode();
            
            //�����R�[�h�F �U�֐����`�[�L���[�e�[�u��.�����R�[�h 
            String l_strProductCode =
                l_hostTransferPaymentRow.getProductCode();
            
            //�ۊǋ敪�F �U�֐����`�[�L���[�e�[�u��.�ۊǋ敪 
            String l_strCustodyDiv =
                l_hostTransferPaymentRow.getCustodyDiv();
            
            //��������敪�F �U�֐����`�[�L���[�e�[�u��.��������敪 
            String l_strSpecificDiv = l_hostTransferPaymentRow.getSpecificDiv();
            
            //���ʁF �U�֐����`�[�L���[�e�[�u��.����
            Long l_lngQuantity = 
                new Long(l_hostTransferPaymentRow.getQuantity());
                
            //���i�敪�F �U�֐����`�[�L���[�e�[�u��.���i�敪
            String l_strCommodityDiv =
			    l_hostTransferPaymentRow.getCommodityDiv();
			
			//����敪�F null
			String l_strCancelDiv = null;
            
            //1.2.1�j notify�o�ɒʒm
            String l_strNotifyOutputNotify = null;

            try
            {
                //�o�ɒʒmUnitService���擾����B
                WEB3AioOutputNotifyUnitService
                    l_aioOutputNotifyUnitService =
                        (WEB3AioOutputNotifyUnitService) Services.getService(
                            WEB3AioOutputNotifyUnitService.class);
                
                l_strNotifyOutputNotify = l_aioOutputNotifyUnitService.notifyOutputNotify(
                    l_strInstatutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strProductCode,
                    l_strCustodyDiv,
                    l_strSpecificDiv,
                    l_lngQuantity,
				    l_strCommodityDiv,
				    l_strCancelDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }        
             
            HashMap l_map = new HashMap();
            
            //1.2.2)�i���Q�j�L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
            if (WEB3HostTransferPaymentStatusDef.DEALT.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.DEALT);               
            }
            else if(WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT);  
            }
            else if (WEB3HostTransferPaymentStatusDef.NO_ASSET.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.NO_ASSET);   
            } 
                               
			// �����Ώۂ̐U�֐����`�[�L���[�e�[�u��.�����敪��ݒ�
			String l_strUpdateWhereA = "rowid = ? ";

			String[] l_objUpdateWhereValuesA = {l_hostTransferPaymentRow.getRowid()};      
    
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    
            //  1.2.2 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
            l_queryProcessor.doUpdateAllQuery(
                HostTransferPaymentRow.TYPE,
                l_strUpdateWhereA.toString(),
                l_objUpdateWhereValuesA,
                l_map);
        }
        else
        {
            //�،���ЃR�[�h�F �،��o�ɐ����`�[�L���[�e�[�u��.�،���ЃR�[�h 
            String l_strInstatutionCode =
                hostSecDeliveryTransferRow.getInstitutionCode();
            
            //���X�R�[�h�F �،��o�ɐ����`�[�L���[�e�[�u��.���X�R�[�h 
            String l_strBranchCode =
                hostSecDeliveryTransferRow.getBranchCode();
            
            //�ڋq�R�[�h�F �،��o�ɐ����`�[�L���[�e�[�u��.�ڋq�R�[�h 
            String l_strAccountCode =
                hostSecDeliveryTransferRow.getAccountCode();
            
            //�����R�[�h�F �،��o�ɐ����`�[�L���[�e�[�u��.�����R�[�h 
            String l_strProductCode =
                hostSecDeliveryTransferRow.getProductCode();
            
            //�ۊǋ敪�F �،��o�ɐ����`�[�L���[�e�[�u��.�ۊǋ敪 
            String l_strCustodyDiv =
                hostSecDeliveryTransferRow.getCustodyDiv();
            
            //��������敪�F �،��o�ɐ����`�[�L���[�e�[�u��.��������敪 
            String l_strSpecificDiv = hostSecDeliveryTransferRow.getSpecificDiv();
            
            //���ʁF �،��o�ɐ����`�[�L���[�e�[�u��.����
            Long l_lngQuantity = 
                new Long(hostSecDeliveryTransferRow.getQuantity());
                
			//���i�敪�F �،��o�ɐ����`�[�L���[�e�[�u��.���i�敪
			String l_strCommodityDiv =
			    hostSecDeliveryTransferRow.getCommodityDiv();
			
			//����敪�F �،��o�ɐ����`�[�L���[�e�[�u��.����敪
			String l_strCancelDiv = 
			    hostSecDeliveryTransferRow.getCancelDiv();
            
            //1.4.1�j notify�o�ɒʒm
            String l_strNotifyOutputNotify = null;

            try
            {
                //�o�ɒʒmUnitService���擾����B
                WEB3AioOutputNotifyUnitService
                    l_aioOutputNotifyUnitService =
                        (WEB3AioOutputNotifyUnitService) Services.getService(
                            WEB3AioOutputNotifyUnitService.class);
                
                l_strNotifyOutputNotify = l_aioOutputNotifyUnitService.notifyOutputNotify(
                    l_strInstatutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strProductCode,
                    l_strCustodyDiv,
                    l_strSpecificDiv,
                    l_lngQuantity,
				    l_strCommodityDiv,
				    l_strCancelDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }        
                
            HashMap l_map = new HashMap();
            
            //1.4.2)�i���Q�j�L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V   
            if (WEB3HostTransferPaymentStatusDef.DEALT.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.DEALT);               
            }
            else if(WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT);  
            }
            else if (WEB3HostTransferPaymentStatusDef.NO_ASSET.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.NO_ASSET);   
            }
            
			// �����Ώۂ̏،��o�ɐ����`�[�L���[�e�[�u��.�����敪��ݒ�p
			String l_strUpdateWhereB = "rowid = ? ";

			String[] l_objUpdateWhereValuesB = {hostSecDeliveryTransferRow.getRowid()};
           
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //  1.4.2 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
            l_queryProcessor.doUpdateAllQuery(
                HostSecDeliveryTransferRow.TYPE,
                l_strUpdateWhereB.toString(),
                l_objUpdateWhereValuesB,
                l_map);            
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}




@
