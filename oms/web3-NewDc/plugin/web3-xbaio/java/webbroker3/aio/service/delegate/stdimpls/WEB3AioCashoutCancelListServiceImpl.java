head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������ꗗ�T�[�r�XImpl(WEB3AioCashoutCancelListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 ���� (���u) �V�K�쐬
                   2004/10/22 ���E(���u) ���r���[
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashoutCancelListResponse;
import webbroker3.aio.message.WEB3AioCashoutCancelUnit;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o������ꗗ�T�[�r�XImpl)<BR>
 * �o������ꗗ�T�[�r�X�����N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutCancelListServiceImpl 
    extends WEB3ClientRequestService implements WEB3AioCashoutCancelListService 
{
    
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCancelListServiceImpl.class);
    
    /**
     * �o������ꗗ�T�[�r�X���������{����B <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o������ꗗ�j�ꗗ��ʕ\���f�[�^�擾�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F63677016E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P.�P�jget�⏕����(SubAccountTypeEnum)
        //�⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);  
        
        //�P.�Q�jvalidate����(SubAccount)
        //�ȉ��̃`�F�b�N���s���B 
        //�|��t���ԃ`�F�b�N 
        //�|�V�X�e����~���`�F�b�N 
        //�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l     
                  
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //���o�������}�l�[�W���N���X���擾����B
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //�`�F�b�N���s��
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //�P.�R�jget������( )
        //���������擾����B 
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("���������擾���� " + l_datOrderBizDate);       
         
        try
        {
            //�P.�S�jgetDefaultProcessor( )
            //�N�G���v���Z�b�T���擾����B
            //===========DataNetworkException======= DataFindException========
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();         
            
            //�P.�T)doFindAllQuery(RowType, String, String, String, Object[], int, int) 
            //�����P�ʃe�[�u������A�ȉ��̏����̃��R�[�h���擾����B 
            //[����] 
            //�⏕����ID = �⏕����.getSubAccountId()�̖߂�l 
            //������� = 1001�i�o�������j 
            //������� = 1�i��t�ρj 
            //�����L����� = 1�i�I�[�v���j 
            //�����敪 = 1�i�I�[�v���j 
            //�o���\���敪 = null 
            //[����] 
            //Row�^�C�v�F AioOrderUnitRow.TYPE 
            //Where�F "sub_account_id=? and order_type=? 
            // and order_status=? and order_open_status=? and 
            // expiration_status=? and payment_application_div=null" 
            //orderBy�F "received_date_time" 
            //condition�F null 
            //���X�g�F �ȉ��̍��ڂ̃��X�g 
            //�⏕����.getSubAccountId()�̖߂�l 
            //1001�i�o�������j 
            //1�i��t�ρj 
            //1�i�I�[�v���j 
            //1�i�I�[�v���j
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" sub_account_id = ?");     
            l_strWhere.append(" and order_type = ?");  
            l_strWhere.append(" and order_status = ? ");     
            l_strWhere.append(" and order_open_status = ? ");   
            l_strWhere.append(" and expiration_status = ? ");   
            l_strWhere.append(" and payment_application_div is null ");   
            String l_strOrderBy = " received_date_time "; 
            Object[] l_objMutualFrgncalWhere =
            {
                new Long(l_subAccount.getSubAccountId()),           
                OrderTypeEnum.CASH_OUT,
                OrderStatusEnum.ACCEPTED,
                OrderOpenStatusEnum.OPEN,
                OrderExpirationStatusEnum.OPEN
            };
            List l_lisAioOrderUnitRows = 
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhere.toString(),
                    l_strOrderBy,
                    null,
                    l_objMutualFrgncalWhere);
            int l_intSize = 0;
            if (l_lisAioOrderUnitRows != null && !l_lisAioOrderUnitRows.isEmpty())
            {
                l_intSize = l_lisAioOrderUnitRows.size();
            }
            
            //�P.�U�jArrayList( )
            //ArrayList�̃C���X�^���X�𐶐�����B
            List  l_lisCashoutCancelUnit = new Vector();

            //�P.�V�j���b�Z�[�W �����P��Params�̗v�f����Loop
            for (int i= 0; i < l_intSize; i++)
            {
                //�P.�V.�P�j���b�Z�[�W �����P��Params�̗v�f����Loop
                //�����P��Params.MQ�X�e�[�^�X = "0"�i�������j�̏ꍇ�ȉ��̏������s��
                
                //�����P��Params
                AioOrderUnitRow l_aioOrderUnitRow = 
                       (AioOrderUnitRow) l_lisAioOrderUnitRows.get(i);
                AioOrderUnitParams l_aioOrderUnitParams =  
                    new AioOrderUnitParams(l_aioOrderUnitRow);
                log.debug("MQ�X�e�[�^�X���擾���� " + l_aioOrderUnitParams.getMqStatus());
                
                if ((WEB3MqStatusDef.NOT_SEND_MAIL).equals(l_aioOrderUnitParams.getMqStatus()))
                {
                    //�P.�V.�P.�P�j�o��������׃C���X�^���X�𐶐�����B
                    WEB3AioCashoutCancelUnit l_aioCashoutCancelUnit = 
                        new WEB3AioCashoutCancelUnit(); 
                    
                    //�P.�V.�P.�Q�j(*2) �v���p�e�B�Z�b�g
                    //(*2)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
                    
                    //�o���������.����ID = �����P��Params.����ID
                    l_aioCashoutCancelUnit.orderId = 
                        String.valueOf(l_aioOrderUnitParams.getOrderId());
                    
                    //�o���������.��t���� = �����P��Params.��t����
                    l_aioCashoutCancelUnit.receptionDate = 
                        l_aioOrderUnitParams.getReceivedDateTime();
                   
                    //�o���������.�U���\��� = �����P��Params.�U�֗\���
                    l_aioCashoutCancelUnit.transScheduledDate = 
                        l_aioOrderUnitParams.getEstTransferDate();
                    
                    //�o���������.�o�����z = �����P��Params.����
                    l_aioCashoutCancelUnit.cashoutAmt = 
                        WEB3StringTypeUtility.formatNumber(
                            l_aioOrderUnitParams.getQuantity());
                    
                    //�P.�V.�P.�R�jArrayList�ɗv�f��ǉ�����B 
                    //[����] arg0�F �o��������׃I�u�W�F�N�g
                    l_lisCashoutCancelUnit.add(l_aioCashoutCancelUnit);                     
                }
            }    
                
            //�P.�W�jtoArray( )
            //�o��������ׂ̔z���ԋp����B
            WEB3AioCashoutCancelUnit[] l_aioCashoutCancelUnit = 
                new WEB3AioCashoutCancelUnit[l_lisCashoutCancelUnit.size()];
            l_lisCashoutCancelUnit.toArray(l_aioCashoutCancelUnit);
                
            //�P.�X�jcreateResponse( )���X�|���X�f�[�^�𐶐�����B
            WEB3AioCashoutCancelListResponse 
                l_aioCashoutCancelListResponse =
                    (WEB3AioCashoutCancelListResponse) l_request.createResponse(); 

            //�P.�P�O�j(*3) �v���p�e�B�Z�b�g
            //(*3) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B
            //���X�|���X.�o������ꗗ = �o���������[]  
            l_aioCashoutCancelListResponse.cashoutCancelUnits = l_aioCashoutCancelUnit;
            
            log.exiting(STR_METHOD_NAME);
            return l_aioCashoutCancelListResponse;     
        }
        catch (DataException  l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
