head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���X�V�C���^�Z�v�^(WEB3AioCashTransUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ���E (���u) �V�K�쐬     
                   2004/10/23 ������ (���u) ���r���[ 
                   2004/12/06 ���E (���u) �c�Ή�         
                   2006/09/04 ���G��(���u) ���f�� 634�A642      
Revesion History : 2007/08/08 �����F(���u) ���f�� 751
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (���o���X�V�C���^�Z�v�^)<BR>
 * ���o���X�V�C���^�Z�v�^�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashTransUpdateInterceptor implements AioOrderManagerPersistenceEventInterceptor 
{
    
    /**
     * (���o���X�V�C���^�Z�v�^)
     * @@return webbroker3.aio.WEB3AioCashTransUpdateInterceptor
     * @@roseuid 40E2A309034C
     */
    public WEB3AioCashTransUpdateInterceptor() 
    {
     
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransUpdateInterceptor.class);   
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾 <BR>
     * <BR>
     * �����̒�������Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g <BR>
     * <BR>
     * �Q�|�P�j ��������敪�A�����G���[���R�R�[�h <BR>
     * �����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g����B <BR>
     * <BR>
     * �Q�|�Q�j �X�V�� <BR>
     * �Z�b�V�������烍�O�C��ID���擾�ł���ꍇ�A���O�C��ID���Z�b�g����B <BR>
     * �擾�ł��Ȃ��ꍇ�A�hSYSTEM�h���Z�b�g����B  <BR>
     * <BR>
     * �Q�|�R�j�@@�ʉ݃R�[�h�A���o�����z�i�~���Z�z�j<BR>
     * �����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g����B<BR>
     * �������A�������ڂ�null�̏ꍇ��null���Z�b�g����B<BR>
     * <BR>
     * @@param l_updateType - ((�X�V�^�C�v))<BR>
     * INSERT�܂��́AUPDATE
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderActionParams - (��������Params)
     * <BR>
     * ���������̃p�����[�^�N���X
     * @@return AioOrderActionParams
     * @@roseuid 40E2A370035C
     */
    public AioOrderActionParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process, AioOrderActionParams l_orderActionParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
                         "OrderManagerPersistenceContext l_process, " +
                         "AioOrderActionParams l_orderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderActionParams == null)
        {
            log.debug("��������Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //�P�j�@@�����P�ʃI�u�W�F�N�g�擾 
            //�����̒�������Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B
            long l_lngOrdrUnitId = l_orderActionParams.getOrderUnitId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
            OrderUnit l_orderUnit = l_aioOrderManager.getOrderUnit(l_lngOrdrUnitId);
            log.debug("(AioOrderUnitRow)l_orderUnit.getDataSourceObject() = " + 
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject());
            
            //===========remain zhou-yong 2004/12/7 NO.1 begin===========
            
            //�Q�j�@@�g�����ڃZ�b�g 
            //�Q�|�P�j ��������敪�A�����G���[���R�R�[�h 
            //�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g����B 
            
            // 2-1)��������敪
            l_orderActionParams.setCancelType(
                ((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getCancelType());
            
            // 2-2)�����G���[���R�R�[�h
            l_orderActionParams.setErrorReasonCode(
                ((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getErrorReasonCode());
            
            //�Q�|�Q�j �X�V�� 
            //�Z�b�V�������烍�O�C��ID���擾�ł���ꍇ�A���O�C��ID���Z�b�g����B 
            //�擾�ł��Ȃ��ꍇ�A�hSYSTEM�h���Z�b�g����B
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);

            long l_lngLoginId = 0;
            
            boolean l_blnIsLogin = true;
            try
            {
                l_lngLoginId = l_opLoginSec.getLoginId();
            }
            catch (IllegalSessionStateException l_ex)
            {
                l_blnIsLogin = false;
            }
            log.debug("�Z�b�V�������烍�O�C��ID = " + l_lngLoginId);
            
            if(l_blnIsLogin && l_lngLoginId != 0)
            {
                l_orderActionParams.setLastUpdatedUser(Long.toString(l_lngLoginId));
            }
            else
            {
                l_orderActionParams.setLastUpdatedUser("SYSTEM");
            }
            
            //===========remain zhou-yong 2004/12/7 NO.1 end===========
            
            //�Q�|�R�j�@@�ʉ݃R�[�h�A���o�����z�i�~���Z�z�j 
            //�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g����B
            //�������A�������ڂ�null�̏ꍇ��null���Z�b�g����B
            l_orderActionParams.setCurrencyCode(
                ((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getCurrencyCode());
            
            if (((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getConvertAmountIsNull())
            {
                l_orderActionParams.setConvertAmount(null);
            }
            else
            {
                l_orderActionParams.setConvertAmount(
                    ((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getConvertAmount());
            }

        }
        catch(NotFoundException l_ex)
        {
            log.error("�Y�����钍���P�ʃI�u�W�F�N�g������܂���", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@return BatchedQuery
     * @@roseuid 415A762000E2
     */
    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@return AioOrderUnitParams
     * @@roseuid 415A7814023F
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, AioOrderUnitParams arg2) 
    {
     return arg2;
    }
}
@
