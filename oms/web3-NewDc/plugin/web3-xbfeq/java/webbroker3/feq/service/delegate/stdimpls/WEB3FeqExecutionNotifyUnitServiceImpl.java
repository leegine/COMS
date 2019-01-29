head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���ʒm�P���T�[�r�XImpl(WEB3FeqExecutionNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ꎉ� (���u) �V�K�쐬   
Revesion History : 2006/10/12 �����q(���u) ���f���@@No.286�Ή�
Revesion History : 2006/10/17 �����(���u) ���f�� No.288�Ή�
Revesion History : 2006/12/14 ꎉ� (���u) ���f�� No.311�Ή�
Revesion History : 2006/12/15 ꎉ� (���u) ���f�� No.312�Ή�
Revesion History : 2006/12/19 ꎉ� (���u) ���f�� No.319�Ή�  �c�a�X�V�d�l 078      
Revesion History : 2006/12/20 ꎉ� (���u) ���f�� No.323�Ή�
Revesion History : 2007/01/09 ���� (���u) ���f�� No.329�Ή�
Revesion History : 2007/04/02 ꎉ� (���u) ���f�� No.349�Ή�
Revesion History : 2007/08/08 �ؕk (���u) ���f�� No.354�Ή� �c�a�X�V�d�l087
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.468
Revesion History : 2010/01/25 �����F (���u) �d�l�ύX���f��536 539
Revesion History : 2010/02/22 ���g (���u) �����̖��No.033
Revesion History : 2010/03/05 ���g (���u)�y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.541
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.service.delegate.WEB3FeqExecutionNotifyUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������o���ʒm�P���T�[�r�XImpl)<BR>
 * �O�������o���ʒm�P���T�[�r�X�����N���X<BR>
 *   
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3FeqExecutionNotifyUnitServiceImpl implements WEB3FeqExecutionNotifyUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecutionNotifyUnitServiceImpl.class);
    
	public WEB3FeqExecutionNotifyUnitServiceImpl()
	{

	}
  
    /**
     * (notify���)<BR>
     * ��菈�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     *�u�i�O�������o���ʒm�T�[�r�X�j�o���ʒm�P�������v�Q�ƁB<BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�u�i�O�������o���ʒm�T�[�r�X�j�o���ʒm�P�������v)<BR>
     * �@@�@@:  1.4 ���b�Z�[�W (*) �o���I����iis�o���I��() == true�j�̏ꍇ�A<BR> 
     * �@@�@@�u�o���I�������ς݂Ȃ̂ŁA�o���s�ł��v��O���X���[����B<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02162<BR>
     * ========================================================<BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[)<BR>
     * �O���o���ʒm�L���[<BR>
     * @@param l_sleRvcdQParams - (�O�������RCVD_Q)<BR>
     * �O�������RCVD_Q<BR>
     * @@param l_todayLoginFlag - (�����ב֓o�^�t���O)<BR>
     * �����ב֓o�^�t���O<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91034C
     */
    public void notifyOrder(
    	WEB3FeqOrderUnit l_feqOrderUnit, 
    	HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams,
    	SleRcvdQParams l_sleRvcdQParams,
        Boolean l_todayLoginFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " notifyOrder(WEB3FeqOrderUnit, " +
        		"HostFeqOrderExecNotifyParams, SleRcvdQParams, boolean) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null || l_hostFeqOrderExecNotifyParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3FeqOrderManager l_orderManager =
		    (WEB3FeqOrderManager) l_finApp.getTradingModule(
		    ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
		
        WEB3FeqPositionManager l_positionManager =
		    (WEB3FeqPositionManager) l_finApp.getTradingModule(
		    ProductTypeEnum.FOREIGN_EQUITY).getPositionManager();

		WEB3FeqOrderUnit l_orderUnit = null;
        try
		{
        	//1.1 getOrderUnit
        	log.debug("feqOrderUnitId==" + l_feqOrderUnit.getOrderUnitId());
        	l_orderUnit = (WEB3FeqOrderUnit) l_orderManager.getOrderUnit(l_feqOrderUnit.getOrderUnitId());
		}
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2 �O�������RCVD_Q.���͌o�H�敪��0�i�F�o���ʒm�j�̏ꍇ
        String l_strFeqInputRouteDiv = l_sleRvcdQParams.getRouteDiv();
        if (WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strFeqInputRouteDiv) &&
            SleRcvdqProcStatusEnum.TODO.equals(l_sleRvcdQParams.getStatus()))
        {
            //validate�������(�O�����������P��,�O���o���ʒm�L���[Params)
            this.validateOrderStatus(l_orderUnit, l_hostFeqOrderExecNotifyParams);
        }

        //1.5 is�o���I��
        if (l_orderUnit.isExecEnd())
        {
            log.debug("�o���I�������ς݂Ȃ̂ŁA�o���s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02162,
                getClass().getName() + STR_METHOD_NAME,
                "�o���I�������ς݂Ȃ̂ŁA�o���s�ł��B");
        }
        
        // �O�������RCVD_Q.�����敪 == "������"�̏ꍇ
        if (SleRcvdqProcStatusEnum.TODO.equals(l_sleRvcdQParams.getStatus()))
        {
        	WEB3FeqOrderUnit l_updatedOrderUnit = null;
        	
            //1.7 validate����
            l_orderManager.validateExecutionDate(l_orderUnit, 
            	l_hostFeqOrderExecNotifyParams.getExecTimestamp());
            
            //1.8 validate��萔��
            l_orderManager.validateExecutedQuantity(l_orderUnit,
            	l_hostFeqOrderExecNotifyParams.getExecQuantity());
            
            // �O�������RCVD_Q�D���͌o�H�敪��0�i�F�o���ʒm�j�ȊO�̏ꍇ
            if (!WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strFeqInputRouteDiv))
            {
                //validate���P��
                l_orderManager.validateExecutedPrice(l_orderUnit,
                	l_hostFeqOrderExecNotifyParams.getExecPrice());
            }
            
            //1.10 validate���n��n��(�O�����������P��, Date)
            l_orderManager.validateFDeliveryDate(l_orderUnit,
                l_hostFeqOrderExecNotifyParams.getFDeliveryDate());
            
            //1.11 ThreadLocalSystemAttributesRegistry�ɑ�����ݒ肷��
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV,
                l_strFeqInputRouteDiv);
            
            //1.12 update�����
            WEB3FeqOrderAndExecutionUpdateService l_orderAndExecutionUpdateService = 
            	(WEB3FeqOrderAndExecutionUpdateService) Services.getService(
            		WEB3FeqOrderAndExecutionUpdateService.class);
            
            l_orderAndExecutionUpdateService.updateExecuteUnit(l_hostFeqOrderExecNotifyParams);
            
            //1.13 update�g�����U�N�V����
            l_positionManager.updateTransaction(l_orderUnit.getOrderUnitId(), false);
                        
            try
    		{
            	//�X�V��̒����P�ʂ��擾����
            	l_updatedOrderUnit =
            		(WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_feqOrderUnit.getOrderUnitId());
    		}
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.16  update�T�Z��n���
            l_orderManager.updateEstimatedPrice(l_updatedOrderUnit,
            	l_hostFeqOrderExecNotifyParams.getExecTimestamp());

            //  �O�������RCVD_Q�D���͌o�H�敪��0�i�F�o���ʒm�j�ȊO�̏ꍇ
            if (!WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strFeqInputRouteDiv))
            {
                //�����^����G���[�������s(long)
                //�����������^������ł��S�o���̏ꍇ�A����������^����G���[�̏�ԂɍX�V����B 
                //[�����^����G���[�������s()�Ɏw�肷�����] 
                //�@@�����P��ID�F�@@�擾�����X�V��̒����P��.getOrderUnitId()�̖߂�l
                l_orderManager.executeChangeCancelOrderRejected(l_updatedOrderUnit.getOrderUnitId());
            }
        }

        // �O�������RCVD_Q.�����敪 == "��菈����"
        if (SleRcvdqProcStatusEnum.EXEC_PROCESSING.equals(l_sleRvcdQParams.getStatus()))
        {
            try 
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                
                // �����P�ʃe�[�u���ɕR�Â����e�[�u����S�Ď擾����
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" order_unit_id = ? ");
                Object[] l_objWhere = new Object[1];
                l_objWhere[0] = l_feqOrderUnit.getOrderUnitId() + "";
                
                List l_lisFeqOrderExecution = l_queryProcesser.doFindAllQuery(
                        FeqOrderExecutionRow.TYPE, 
                        l_sbWhere.toString(), 
                        l_objWhere);
                
                if (l_lisFeqOrderExecution == null || l_lisFeqOrderExecution.isEmpty())
                {
                    log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME);
                }
                // get�g�����U�N�V����
                WEB3FeqFinTransactionManager l_feqFinTransactionManager = 
                    (WEB3FeqFinTransactionManager)l_finApp.getTradingModule(
                        ProductTypeEnum.FOREIGN_EQUITY).getFinTransactionManager();
                
                List l_lisFeqFinTransaction = 
                    l_feqFinTransactionManager.getTransactions(l_orderUnit);
                
                if (l_lisFeqFinTransaction == null || l_lisFeqFinTransaction.isEmpty())
                {
                    log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME);
                }
                
                // �擾�������e�[�u���y�сA�g�����U�N�V�����e�[�u���S�Ă��X�V����
                int l_intSize = l_lisFeqOrderExecution.size();
                for (int i = 0; i < l_intSize; i++)
                {
                    FeqOrderExecutionRow l_feqOrderExecutionRow = 
                        (FeqOrderExecutionRow)l_lisFeqOrderExecution.get(i);
                    FeqOrderExecutionParams l_feqOrderExecutionParams = 
                        new FeqOrderExecutionParams(l_feqOrderExecutionRow);
                    
                    // �בփ��[�g�F�@@�O���o���ʒm�L���[Params.�בփ��[�g�ōX�V
                    l_feqOrderExecutionParams.setFxRate(l_hostFeqOrderExecNotifyParams.getFxRate());
                    l_queryProcesser.doUpdateQuery(l_feqOrderExecutionParams);
                }
                
                l_intSize = l_lisFeqFinTransaction.size();
                for (int i = 0; i < l_intSize; i++)
                {
                    FeqFinTransactionParams l_feqFinTransactionParams = 
                        (FeqFinTransactionParams)l_lisFeqFinTransaction.get(i);
                    
                    // �K�p�בփ��[�g�F�@@�O���o���ʒm�L���[Params.�בփ��[�g�ōX�V
                    l_feqFinTransactionParams.setFxRate(l_hostFeqOrderExecNotifyParams.getFxRate());
                    l_queryProcesser.doUpdateQuery(l_feqFinTransactionParams);
                }
                
            }
            catch (DataFindException l_ex) 
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            } 
            catch (DataNetworkException l_ex) 
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            } 
            catch (DataQueryException l_ex) 
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }

            boolean l_blnIsDayTradeAdoption = false;
            boolean l_blnIsDayTradeMarket = false;
            try
            {
                WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_feqOrderUnit.getAccountId());
                FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_feqOrderUnitRow.getMarketId()));
                WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_mainAccount.getInstitution();
                l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                l_blnIsDayTradeMarket = l_market.isDayTradeMarket();
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //is���v�����̗p() == true ���@@is���v��s��() == true�@@���@@���t�����i�����P��.is���t() == true�j�̏ꍇ
            if (l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket && l_orderUnit.isBuy())
            {
                //update�ۗL���Y(�O�����������P��)
                l_positionManager.updateAsset(l_orderUnit);
            }

            // update�g�����U�N�V����
            l_positionManager.updateTransaction(l_orderUnit.getOrderUnitId(), true);
            
            // update�T�Z��n���
            l_orderManager.updateEstimatedPrice(
                l_orderUnit, 
                l_hostFeqOrderExecNotifyParams.getExecTimestamp());
            
            // update���v�����z�i�~�j
            l_orderManager.updateExecutedAmountYen(l_orderUnit.getOrderUnitId());
        }

        //�]�͍Čv�Z
        WEB3TPTradingPowerReCalcService l_tradingPowerReCalcService =
            (WEB3TPTradingPowerReCalcService)Services.getService(
                WEB3TPTradingPowerReCalcService.class);
        l_tradingPowerReCalcService.reCalcTradingPower(l_orderUnit.getSubAccount());

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������)<BR>
     * �Ώے������A���^������ɂ��X�V���s���ėǂ���Ԃł��邩�`�F�b�N����B <BR>
     * <BR>
     * �i�`�F�b�N���e�j<BR>
     * �P�D����.�����P��.�s�ꂩ��m�F�ς̐���==null�̏ꍇ�A<BR>
     * <BR>
     * �@@�u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01975<BR>
     * <BR>
     * �Q�D�i����.�����P��.���SEQ  !=�@@null�j�@@����<BR>
     * �@@�@@�i����.�����P��.���SEQ ���� �O���o���ʒm�L���[Params.���SEQ�j�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@�u���f�[�^�d���v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02891<BR>
     * <BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[Params)<BR>
     * �O���o���ʒm�L���[Params<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91034C
     */
    public void validateOrderStatus(
        WEB3FeqOrderUnit l_feqOrderUnit,
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderStatus(WEB3FeqOrderUnit, HostFeqOrderExecNotifyParams)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnit == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }

    	FeqOrderUnitRow l_feqOrderUnitRow =
    		(FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();

    	if (l_feqOrderUnitRow.getConfirmedQuantityIsNull())
    	{
            log.debug("�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
        }

        //�Q�D�i����.�����P��.���SEQ  !=�@@null�j�@@����
        //�@@�i����.�����P��.���SEQ ���� �O���o���ʒm�L���[Params.���SEQ�j�̏ꍇ�A
        //�u���f�[�^�d���v�̗�O��throw����B
        if (l_feqOrderUnit.getExecutionSeqNo() != null
            && Integer.parseInt(l_feqOrderUnit.getExecutionSeqNo()) >=
                l_hostFeqOrderExecNotifyParams.getExecutionSeqNo())
        {
            log.debug("���f�[�^�d��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02891,
                this.getClass().getName() + STR_METHOD_NAME,
                "���f�[�^�d��");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
