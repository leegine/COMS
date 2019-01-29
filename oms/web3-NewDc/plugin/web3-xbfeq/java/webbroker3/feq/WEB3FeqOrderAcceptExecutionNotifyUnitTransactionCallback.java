head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�o���ʒm�ꌏTransactionCallback(WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ꎉ� (���u) �V�K�쐬
                 : 2006/10/12 �����q(���u) ���f���@@No.285�Ή�
                   2006/10/17 �����(���u) ���f���@@No.287,292�Ή�
                   2006/11/20 �����(���u) ���f���@@No.299�Ή�
                   2006/12/14 ꎉ� (���u)  ���f���@@No.311�Ή�
                   2006/12/19 ꎉ� (���u)  ���f���@@No.317�Ή�
                   2006/12/19 ꎉ� (���u)  ���f���@@No.321�Ή�
                   2006/12/20 ꎉ� (���u)  ���f���@@No.324�Ή�
Revesion History : 2007/08/10 �ؕk (���u)  ���f���@@No.354�Ή�
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2008/02/26 �g�C��(���u) ���f��No.400
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.467 �i�c�a�X�V�d�l�jNo.095�A096
Revesion History : 2010/03/05 ���g (���u)�y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.542
*/

package webbroker3.feq;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.feq.service.delegate.WEB3FeqExecutionNotifyUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptUnitService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������t�o���ʒm�ꌏTransactionCallback)<BR>
 * �ǊO������������t�o���ʒm�ꌏ�g�����U�N�V�������������{����N���X<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback.class);
    
    /**
     * ThreadLocal���hLAST_UPDATER�h�̕ϐ����B
     */
    private static final String LAST_UPDATER = "last_updater";
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     */
    private WEB3FeqOrderUnit orderUnit;
    
    /**
     * (�O�������RCVD_Q)<BR>
     * �O�������RCVD_Q<BR>
     */
    private SleRcvdQParams sleRvcdQParams;
    
    /**
     * �g�����U�N�V�������������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������������t�o���ʒm�ꌏ�g�����U�N�V�����jprocess�v�Q�ƁB<BR>
     * <BR>
     * @@return Object
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@roseuid 4214980A032E
     */
    public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);
       
        WEB3GentradeCurrency l_currency = null;
        double l_dblFxRate = 0D;
        boolean l_blnIsExec = false;
        
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams = null;
        
        //1.1 setAttribute
        ThreadLocalSystemAttributesRegistry.setAttribute(LAST_UPDATER, 
        	this.sleRvcdQParams.getLastUpdater());
        
        QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
        
        //1.2 this.�O�������RCVD_Q.get�o�H�敪() == (0:�o���ʒm or 1:�o������ or 2:��茋�ʈꊇ����)�̏ꍇ
        String l_strRouteDiv = this.sleRvcdQParams.getRouteDiv();
        if (WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strRouteDiv)||
        	WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(l_strRouteDiv)||
        	WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(l_strRouteDiv))
        {
        	WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
        		(WEB3FeqTypeOrderManagerReusableValidations)
        		WEB3FeqTypeOrderManagerReusableValidations.getInstance();
        	
            try
			{
            	// validate�����בփ��[�g()
            	l_blnIsExec = l_orderMgrResVal.validateDayExchange(this.orderUnit);
            	
            	//1.2.1.1 get�ʉ�
				l_currency = this.orderUnit.getCurrency();
			}
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_ex);
            }
			
			//1.2.1.2is���t
			boolean l_blnIsBuy = this.orderUnit.isBuy();
            
			//1.2.1.3 get�בփ��[�g
            if (l_currency != null)
            {
            	l_dblFxRate = l_currency.getExchangeRate(l_blnIsBuy, l_blnIsExec , 0);
            }				
        	
        	//1.2.2 �O���o���ʒm�L���[Params�𐶐����A�v���p�e�B�ɒl���Z�b�g����
            l_hostFeqOrderExecNotifyParams = new HostFeqOrderExecNotifyParams();
            
            l_hostFeqOrderExecNotifyParams.setInstitutionCode(this.orderUnit.getInstitutionCode());
            try
			{
				l_hostFeqOrderExecNotifyParams.setBranchCode(this.orderUnit.getBranchCode());
			}
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }
            
			l_hostFeqOrderExecNotifyParams.setAccountCode(this.orderUnit.getAccountCode());
			
			String l_strOrderRequestNumber = 
				((FeqOrderUnitRow)this.orderUnit.getDataSourceObject()).getOrderRequestNumber();
			
		    l_hostFeqOrderExecNotifyParams.setOrderRequestNumber(l_strOrderRequestNumber);
			
			l_hostFeqOrderExecNotifyParams.setOrderEmpCode(this.orderUnit.getOrderEmpCode());
			
			if (!this.sleRvcdQParams.getExecQtyIsNull())
			{
				l_hostFeqOrderExecNotifyParams.setExecQuantity(this.sleRvcdQParams.getExecQty());
			}
	
			if (!this.sleRvcdQParams.getExecPriceIsNull())
			{
				l_hostFeqOrderExecNotifyParams.setExecPrice(this.sleRvcdQParams.getExecPrice());
			}
			
            Timestamp l_tisExecTimestamp = null;
            
            if (this.sleRvcdQParams.getExecTimestamp() == null)
            {
                l_tisExecTimestamp = GtlUtils.getSystemTimestamp();
            }
            else
            {
                l_tisExecTimestamp = this.sleRvcdQParams.getExecTimestamp();
            }
            
            l_hostFeqOrderExecNotifyParams.setExecTimestamp(l_tisExecTimestamp);
            
			l_hostFeqOrderExecNotifyParams.setOrderBizDate(
				WEB3DateUtility.getDate(this.orderUnit.getBizDate(), "yyyyMMdd"));
			
            if (this.sleRvcdQParams.getFDeliveryDate() == null)
            {
                if (l_tisExecTimestamp != null)
                {
                    WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tisExecTimestamp);
                    try
                    {
                        Timestamp l_tisFDeliveryDate = l_bizDate.roll(3);
                        l_hostFeqOrderExecNotifyParams.setFDeliveryDate(l_tisFDeliveryDate);
                    }
                    catch (WEB3SystemLayerException l_ex)
                    {
                        log.error(l_ex.getErrorMessage(), l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new DataCallbackException(
                            l_ex.getErrorMessage(),
                            l_ex);
                    }
                }
            }
            else
            {
                l_hostFeqOrderExecNotifyParams.setFDeliveryDate(this.sleRvcdQParams.getFDeliveryDate());
            }
            
			l_hostFeqOrderExecNotifyParams.setFxRate(new Double(l_dblFxRate));
            
            l_hostFeqOrderExecNotifyParams.setExecSerialNo(this.sleRvcdQParams.getExecSerialNo());

            //���SEQ�F�@@this.�O�������RCVD_Q.�C���f�N�X�E�i���o�[
            if (this.sleRvcdQParams.getRepliesIndexIsNull())
            {
                l_hostFeqOrderExecNotifyParams.setExecutionSeqNo(null);
            }
            else
            {
                l_hostFeqOrderExecNotifyParams.setExecutionSeqNo((int)(this.sleRvcdQParams.getRepliesIndex()));
            }

			//�O�������o���ʒm�P���T�[�r�X
			WEB3FeqExecutionNotifyUnitService l_serivce = 
	        	(WEB3FeqExecutionNotifyUnitService) Services.getService(
	        		WEB3FeqExecutionNotifyUnitService.class);

            //notify���(�O�����������P��, �O���o���ʒm�L���[Params, �O�������RCVD_Q)
            //���P���������s���B
            //[notify���()�Ɏw�肷�����]
            //�@@�����P�ʁF�@@this.�����P��
            //�@@�O���o���ʒm�L���[�F�@@���������O���o���ʒm�L���[Params
            //�@@�O�������RCVD_Q�F this.�O�������RCVD_Q
			try
			{
				l_serivce.notifyOrder(
					this.orderUnit,
					l_hostFeqOrderExecNotifyParams,
                    this.sleRvcdQParams,
                    Boolean.valueOf(l_blnIsExec));
			}
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_ex);
            }
        }
        
        //1.3 this.�O�������RCVD_Q.get�o�H�敪() == (3:������t or 4:������t����F�� or 
        //5:������t���ʈꊇ����)�̏ꍇ
        if (WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT.equals(l_strRouteDiv)||
        	WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_CANCEL_AUTHENTICATE.equals(l_strRouteDiv)||
        	WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD.equals(l_strRouteDiv))
        {
        	//1.3.1 �O������������t������
        	WEB3FeqOrderAcceptCancelUnit l_feqOrderAcceptCancelUnit = 
        		new WEB3FeqOrderAcceptCancelUnit();
        	
        	//1.3.2 ���������O������������t������I�u�W�F�N�g�̃v���p�e�B�ɒl���Z�b�g����
        	l_feqOrderAcceptCancelUnit.orderId = this.orderUnit.getOrderId() + "";
        	
        	l_feqOrderAcceptCancelUnit.aftChangeAcceptDiv = this.sleRvcdQParams.getAcceptDiv();
        	
        	//[notify������t()�Ɏw�肷�����]
            //�@@�����P�ʁF�@@this.�����P��
            //�@@�O������������t������F�@@���������O������������t������I�u�W�F�N�g
            //�@@�O�������RCVD_Q�F this.�O�������RCVD_Q

        	WEB3FeqOrderAcceptUnitService l_feqOrderAcceptUnitService =
	        	(WEB3FeqOrderAcceptUnitService) Services.getService(
	        		WEB3FeqOrderAcceptUnitService.class);

        	try
			{
				l_feqOrderAcceptUnitService.notifyOrderAccept(
					this.orderUnit,
					l_feqOrderAcceptCancelUnit,
                    this.sleRvcdQParams);
			}
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_ex);
            }
        }
        
        // this.�O�������RCVD_Q.get�o�H�敪() == (0:�o���ʒm or 1:�o������ or 2:��茋�ʈꊇ����)�̏ꍇ
        if (WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strRouteDiv) || 
        	WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(l_strRouteDiv) || 
        	WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(l_strRouteDiv))
        {
        	FeqOrderUnit l_feqOrderUnit = null;
        	FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        	TradingModule l_tradingModule = 
        		l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        	WEB3FeqOrderManager l_orderManager = 
        		(WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        	try 
        	{
        		// �X�V��̒����P�ʂ��Ď擾����B
        		l_feqOrderUnit = 
        			(FeqOrderUnit)l_orderManager.getOrderUnit(this.orderUnit.getOrderUnitId());
			} 
        	catch (NotFoundException l_ex) 
        	{
        		log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
			}
        	
        	FeqOrderUnitRow l_feqOrderUnitRow = 
        		(FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        	FeqOrderUnitParams l_feqOrderUnitParams = new FeqOrderUnitParams(l_feqOrderUnitRow);

        	// validate�����בփ��[�g( ) == false�̏ꍇ
        	if (l_blnIsExec == false)
        	{
        		// �L���[�e�[�u���̏����敪�A�X�V���t���X�V����
        		// �X�V���t�F�@@���ݓ���(��GtlUtils.getSystemTimestamp())
        		// �����敪�F�@@"��菈����"
                this.sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.EXEC_PROCESSING);
            	this.sleRvcdQParams.setLastUpdatedTimestamp(
                	GtlUtils.getSystemTimestamp());
                
                l_queryProcesser.doUpdateQuery(this.sleRvcdQParams);
                
                // �O�������P�ʃe�[�u��.�����t���O��"1�F�����"�ɍX�V����
                l_feqOrderUnitParams.setTemporaryExecutionFlag(WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC);
                l_queryProcesser.doUpdateQuery(l_feqOrderUnitParams);
                
                ThreadLocalSystemAttributesRegistry.setAttribute(LAST_UPDATER, null);
                log.exiting(STR_METHOD_NAME);
                return null;
        	}
        	
        	// �L���[�e�[�u���̏����敪�A�X�V���t���X�V����
        	// �@@�X�V���t�F�@@���ݓ���(��GtlUtils.getSystemTimestamp())
        	//�@@�����敪�F�@@"�����ς�"
            this.sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.PROCESSED);
        	this.sleRvcdQParams.setLastUpdatedTimestamp(
            	GtlUtils.getSystemTimestamp());
            l_queryProcesser.doUpdateQuery(this.sleRvcdQParams);
            
            // �O�������RCVD_Q�e�[�u�����A�ȉ��̏����œǂݍ���
            // �^�p�R�[�h = �����P��.�^�p�R�[�h
            // �����敪 = "7 : ��菈����"
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_emp_code = ? ");
            l_sbWhere.append(" and status = ? ");
            
            Object[] l_objWhere = new Object[2];
            l_objWhere[0] = l_feqOrderUnitRow.getOrderEmpCode();
            l_objWhere[1] = SleRcvdqProcStatusEnum.EXEC_PROCESSING.intValue() + "";
            
            List l_listSearchResult = l_queryProcesser.doFindAllQuery(
            	SleRcvdQRow.TYPE, 
            	l_sbWhere.toString(), 
            	l_objWhere);
            
            // �����P�ʃe�[�u��.�����t���O���X�V����
            // �O�������RCVD_Q�e�[�u���̌������ʂ�0���̏ꍇ
            if (l_listSearchResult == null || l_listSearchResult.isEmpty())
            {
            	// �O�������P�ʃe�[�u��.�����t���O��"0�FDEFAULT"�ɍX�V
                l_feqOrderUnitParams.setTemporaryExecutionFlag(WEB3TemporaryExecutionFlagDef.DEFAULT);
            }
            // ��L�ȊO�̏ꍇ
            else
            {
            	// �O�������P�ʃe�[�u��.�����t���O��"1�F�����"�ɍX�V
                l_feqOrderUnitParams.setTemporaryExecutionFlag(WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC);
            }
            l_queryProcesser.doUpdateQuery(l_feqOrderUnitParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(LAST_UPDATER, null);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //1.4 �L���[�e�[�u���̏����敪�A�X�V���t���X�V����
        this.sleRvcdQParams.setStatus(SleRcvdqProcStatusEnum.PROCESSED);
    	this.sleRvcdQParams.setLastUpdatedTimestamp(
        	GtlUtils.getSystemTimestamp());
        
        l_queryProcesser.doUpdateQuery(this.sleRvcdQParams);
        
        //1.5 setAttribute
        ThreadLocalSystemAttributesRegistry.setAttribute(LAST_UPDATER, null);
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (�O������������t�o���ʒm�ꌏTransactionCallback)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_tradingRCVD_QParams - (�O�������RCVD_Q)<BR>
     * �O�������RCVD_Q<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 4214980A032E
     */
    public WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(WEB3FeqOrderUnit l_orderUnit, 
    	SleRcvdQParams l_tradingRCVD_QParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
        	"WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback" +
        		"(WEB3FeqOrderUnit, SleRcvdQParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null || l_tradingRCVD_QParams == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^Null�o���Ȃ��B");
        }
        this.orderUnit = l_orderUnit;
        this.sleRvcdQParams = l_tradingRCVD_QParams;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
