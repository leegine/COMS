head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������X�V�C�x���g�C���^�Z�v�^(WEB3FeqUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  鰊](���u) �V�K�쐬
                 : 2005/07/25  ���I(���u) ���r���[
                 : 2006/09/18  ����(���u) �d�l�ύX�E���f��236  
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������X�V�C�x���g�C���^�Z�v�^)<BR>
 * �O�������X�V�C�x���g�C���^�Z�v�^<BR>
 * 
 * @@ author 鰊]<BR> 
 * @@ version 1.0 <BR>
 */
public class WEB3FeqUpdateInterceptor implements FeqOrderManagerPersistenceEventInterceptor 
{
    
    /**
     * ThreadLocal���hLAST_UPDATER�h�̕ϐ����B
     */
    private static final String LAST_UPDATER = "last_updater";
    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqUpdateInterceptor.class);
        
    /**
     * @@roseuid 42D1CBA90177
     */
    public WEB3FeqUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�i���������j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <br>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾 <BR>
     * <BR>
     * �����̒�������Params.����ID�A�����P�ʂh�c��<BR>
     * �Y�����钍���P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g <BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɁA<BR>
     * �@@�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A�ԋp����B <BR>
     * @@param l_updateType
     * @@param l_context
     * @@param l_feqOrderActionParams
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams
     * @@roseuid 428043C703A2
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderActionParams l_feqOrderActionParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_feqOrderActionParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() +"."+ STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        
        long l_lngOrderUnitId = l_feqOrderActionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingMod.getOrderManager();
        FeqOrderUnit l_feqOrderUnit = null;
        
        try 
        {
            // �����P�ʃI�u�W�F�N�g�擾
            l_feqOrderUnit = (FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        
        //��������
        l_feqOrderActionParams.setOrderConditionType(l_feqOrderUnitRow.getOrderConditionType());
        
        //�����������Z�q
        l_feqOrderActionParams.setOrderCondOperator(l_feqOrderUnitRow.getOrderCondOperator());
        
        //�t�w�l��l
        if(l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setStopOrderPrice(l_feqOrderUnitRow.getStopOrderPrice());   
        }
        
        //�iW�w�l�j�����w�l
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_feqOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setWLimitPrice(l_feqOrderUnitRow.getWLimitPrice());
        }
        
        //�����������t
        l_feqOrderActionParams.setExpirationDate(l_feqOrderUnitRow.getExpirationDate());
        
        //�T�Z��n���
        if (l_feqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setEstimatedPrice(l_feqOrderUnitRow.getEstimatedPrice());
        }
        
        //�T�Z��n����i�O�݁j
        if (l_feqOrderUnitRow.getFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setFEstimatedPrice(l_feqOrderUnitRow.getFEstimatedPrice());
        }
        
        //���������E����敪
        l_feqOrderActionParams.setModifyCancelType(l_feqOrderUnitRow.getModifyCancelType());
        
        //�����o�H�敪
        l_feqOrderActionParams.setOrderRootDiv(l_feqOrderUnitRow.getOrderRootDiv());
        
        //�s�ꂩ��m�F�ς݂̒����P��
        if (l_feqOrderUnitRow.getConfirmedOrderPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(l_feqOrderUnitRow.getConfirmedOrderPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̊T�Z��n���
        if (l_feqOrderUnitRow.getConfirmedEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(l_feqOrderUnitRow.getConfirmedEstimatedPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̊T�Z��n����i�O�݁j
        if (l_feqOrderUnitRow.getConfirmedFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(l_feqOrderUnitRow.getConfirmedFEstimatedPrice());
        }
        
        //�s�ꂩ��m�F�ς݂̎��s����
        l_feqOrderActionParams.setConfirmedExecConditionType(l_feqOrderUnitRow.getConfirmedExecConditionType());
        
        //�����G���[���R�R�[�h
        l_feqOrderActionParams.setErrorReasonCode(l_feqOrderUnitRow.getErrorReasonCode());
        
        //�X�V�҃R�[�h
        l_feqOrderActionParams.setLastUpdater(l_feqOrderUnitRow.getLastUpdater());
        
        log.exiting(STR_METHOD_NAME);
        
        return l_feqOrderActionParams;
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����iͯ�ށj�e�[�u���X�V<BR>
     * <BR>
     * this.�i�����j�X�V�l�ݒ�()���R�[������B<BR>
     * <BR>
     * [�i�����j�X�V�l�ݒ�()�Ɏw�肷�����]<BR>
     * �X�V�^�C�v�F�@@�X�V�^�C�v<BR>
     * �����h�c�F�@@�����P��Params.�����h�c<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_context - (����)<BR>
     * ����<BR>
     * @@param l_feqOrderUnitParams - (�����P�ʍs)<BR>
     * �����P�ʍs�i�F�����P��Params�j<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 42A69080030D
     */
    public FeqOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams)  
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_feqOrderUnitParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
               
        try
        {
            //this.�i�����j�X�V�l�ݒ�()���R�[������
            this.mutate(l_updateType, l_feqOrderUnitParams.order_id);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(), 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }
    
    /**
     * (�i�����j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h��overload�j <BR>
     * �����iͯ�ށj�e�[�u���X�V<BR>
     * <BR>
     * �P�j�����h�c�ɊY�����钍���s�i�F����Params�j���擾����B<BR>
     * <BR>
     * �Q�j�擾���������s�i�F����Params�j�̍X�V�҃R�[�h���ȉ��̒ʂ�X�V�iDbUpdate�j����B<BR>
     * <BR>
     * �@@�i�P�j�@@ThreadLocal���hLAST_UPDATER�h�����̒l���擾�ł���ꍇ�A<BR>
     * �@@�@@�@@�擾�����l���Z�b�g����B<BR>
     * �@@�@@�@@���擾�ł��Ȃ��ꍇ�́A�ȉ��̏������s�Ȃ��B<BR>
     * <BR>
     * �@@�i�Q�j�@@���O�C���Z�L�����e�B�T�[�r�X�iOpLoginSecurityService�j�������h�c���擾����B<BR>
     * <BR>
     * �@@�i�R�j�@@�����h�c���擾�ł����ꍇ�A���Z�I�u�W�F�N�g�}�l�[�W��.getTraderByLoginId()�ɂĈ��҂��擾��<BR>
     * �@@�@@�@@�ȉ��̒ʂ�A�X�V�҃R�[�h�ɒl���Z�b�g����B<BR>
     * �@@�@@�@@�|�㗝���͂̏ꍇ�i���� != null�j�A����.getTraderCode()<BR>
     * �@@�@@�@@�|�ڋq���͂̏ꍇ�i���� == null�j�A�����h�c�ɊY�����ڋq<BR>
     * �@@�@@�@@.getAccountCode()<BR>
     * <BR>
     * �@@�i�S�j�@@�����h�c���擾�ł��Ȃ������ꍇ�A���O�C���h�c���擾�� <BR>
     * �@@�@@�@@�擾�������O�C���h�c�ɂĊǗ��҃e�[�u���������A�Y���s<BR>
     * �@@�@@�@@�̊Ǘ��҃R�[�h���Z�b�g����B<BR>
     * �@@�@@�@@�����O�C��ID���擾�ł��Ȃ������ꍇ�́Anull���Z�b�g����B<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * �X�V�^�C�v<BR>
     * @@param l_lngOrderId - (�����h�c)<BR>
     * �����h�c<BR>
     * @@roseuid 42A694850170
     */
    protected void mutate(OrderManagerPersistenceType l_updateType, long l_lngOrderId) throws WEB3BaseException 
    {
		final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, long)";
		log.entering(STR_METHOD_NAME);   
                       
		OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
		long l_lngAccountId = 0;
		//���O�C���h�c���擾
		long l_lngLoginId = 0;

		FinApp l_finApp = null;
		QueryProcessor l_qp = null;
		FeqOrderRow l_feqOrderRow = null;
		FeqOrderParams l_feqOrderParams = null;
		Trader l_trader = null;
		String l_strAccountCode = null;

		try
		{
            l_qp = Processors.getDefaultProcessor();
            l_feqOrderRow = FeqOrderDao.findRowByOrderId(l_lngOrderId);
            l_feqOrderParams = new FeqOrderParams(l_feqOrderRow);
            
            //�i�P�j�@@ThreadLocal���hLAST_UPDATER�h�����̒l���擾�ł���ꍇ�A
            //������ԃR���e�L�X�g�̎擾
            String l_strLastUpdater = 
                (String) ThreadLocalSystemAttributesRegistry.getAttribute(LAST_UPDATER);
            if (!WEB3StringTypeUtility.isEmpty(l_strLastUpdater))
            {
                l_feqOrderParams.setLastUpdater(l_strLastUpdater);
                l_qp.doUpdateQuery(l_feqOrderParams);
            }
            else
            {
                try
                {
                    if (l_opLoginSec.isAccountIdSet())
                    //�����h�c���擾�ł����ꍇ
                    {  
                        l_lngAccountId = l_opLoginSec.getAccountId();  
                        l_finApp = (FinApp) Services.getService(FinApp.class);
                        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                    
                        try
                        {
                            l_lngLoginId = l_opLoginSec.getLoginId();
                            l_trader = l_gentradeFinObjectManager.getTraderByLoginId(l_lngLoginId);
                        }
                        catch (IllegalSessionStateException l_ex)
                        {
                            l_trader = null;
                        }
                        catch (NotFoundException l_ex)
                        {
                            l_trader = null;
                        }

                    
                        if (l_trader != null)
                        {
                            //�㗝���͂̏ꍇ�i���� != null�j�A����.getTraderCode()
                            l_feqOrderParams.setLastUpdater(l_trader.getTraderCode());
                        }
                        else
                        {
                            //�ڋq���͂̏ꍇ�i���� == null�j�A�����h�c�ɊY�����ڋq
                            try
                            {
                                l_strAccountCode = l_finApp.getAccountManager().getMainAccount(l_lngAccountId).getAccountCode();
                            }
                            catch (NotFoundException l_ex)
                            {
                                log.error(STR_METHOD_NAME, l_ex);
                                log.exiting(STR_METHOD_NAME);
                                throw new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                    this.getClass().getName() + "." + STR_METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
                            }
                            l_feqOrderParams.setLastUpdater(l_strAccountCode);
                        }
                
                    }
                    //�����h�c���擾�ł��Ȃ������ꍇ
                    else
                    {

                            l_lngLoginId = l_opLoginSec.getLoginId();
                            AdministratorRow l_AdministratorRow = null;
                            l_AdministratorRow = AdministratorDao.findRowByLoginId(l_lngLoginId);
                            if  (l_AdministratorRow == null)
                            {
                                l_feqOrderParams.setLastUpdater(null);
                            }
                            else
                            {
                                //�Ǘ��҃R�[�h���擾
                                String l_strAdministratorCode = l_AdministratorRow.getAdministratorCode();
                                l_feqOrderParams.setLastUpdater(l_strAdministratorCode);
                            }
                    }


                    l_qp.doUpdateQuery(l_feqOrderParams);
                }
                catch (IllegalSessionStateException l_isse)
                {
                    l_feqOrderParams.setLastUpdater(null);
                    l_qp.doUpdateQuery(l_feqOrderParams);
                }
            }
		}
		catch (DataFindException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch (DataQueryException l_ex)
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

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor#mutate(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext, com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams)
     */
    public FeqOrderExecutionParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, FeqOrderExecutionParams arg2)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManagerPersistenceEventInterceptor#getQueryToExecute(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType, java.lang.Class)
     */
    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
@
