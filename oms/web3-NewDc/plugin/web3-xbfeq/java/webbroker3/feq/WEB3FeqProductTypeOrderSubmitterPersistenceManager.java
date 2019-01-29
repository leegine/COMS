head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductTypeOrderSubmitterPersistenceManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������X�V�}�l�[�W��(WEB3FeqProductTypeOrderSubmitterPersistenceManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 ���U (���u) �V�K�쐬
*/
package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.FeqOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqProductTypeOrderSubmitterPersistenceManager;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�����������X�V�}�l�[�W��)<BR>
 * �O�����������X�V�}�l�[�W��<BR>
 * 
 * @@author ���U(���u)
 * @@version 1.0 
 */
public class WEB3FeqProductTypeOrderSubmitterPersistenceManager extends FeqProductTypeOrderSubmitterPersistenceManager 
{
    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    protected static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqProductTypeOrderSubmitterPersistenceManager.class);

    /**
     * @@roseuid 42CE39EB037A
     */
    public WEB3FeqProductTypeOrderSubmitterPersistenceManager() 
    {
     
    }
    
    /**
     * �X�[�p�[�N���X�Ɏ��g�̃C���X�^���X��o�^����B <BR>
     * <BR>
     * �i�v���O�C�����������ɃR�[�������j <BR>
     * <BR>
     * --- <BR>
     * super.setInstance(this); <BR>
     * --- <BR>
     * @@roseuid 42B25A3402C9
     */
    public void register() 
    {
        super.setInstance(this);         
    }
    
    /**
     * (create���)<BR>
     * �����P�ʍs�^�������A���s�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �P�jsuper.getInitializedOrderExecutionParams()���R�[��������B<BR>
     * <BR>
     * [getInitializedOrderExecutionParams()�Ɏw�肷�����]<BR>
     * arg0�i�ForderUnitRow�j�F�@@�����P�ʍs<BR>
     * arg1�i�FexecQty�j�F�@@��萔��<BR>
     * arg2�i�FexecPrice�j�F�@@���P��<BR>
     * arg3�i�Ffxate�j�F�@@���בփ��[�g<BR>
     * <BR>
     * �Q�j�߂�l�̍s�I�u�W�F�N�g.�������Ɉ���.���������Z�b�g����B<BR>
     * <BR>
     * �R�j�s�I�u�W�F�N�g������I�u�W�F�N�g�𐶐����i<BR>
     * OrderManager.toOrderExecution()�j�ԋp����B<BR>
     * @@param l_orderUnitRow - (�����P�ʍs)<BR>
     * �����P�ʍs�I�u�W�F�N�g<BR>
     * @@param l_dblQuantity - (��萔��)<BR>
     * ��萔��<BR>
     * @@param l_dblPrice - (���P��)<BR>
     * ���P��<BR>
     * @@param l_datExecDate - (������)<BR>
     * ������<BR>
     * @@param l_dblExecRate - (���בփ��[�g)<BR>
     * ���בփ��[�g<BR>
     * @@return webbroker3.feq.WEB3FeqOrderExecution
     * @@throws WEB3SystemLayerException
     * @@roseuid 42B25C200038
     */
    public WEB3FeqOrderExecution createExecution(
        FeqOrderUnitRow l_orderUnitRow, 
        double l_dblQuantity, 
        double l_dblPrice,
        Date l_datExecDate,
        double l_dblExecRate) throws WEB3SystemLayerException 
    {
        String STR_METHOD_NAME = 
            "createExecution(FeqOrderUnitRow, double, double, double)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        try
        {
            //[getInitializedOrderExecutionParams()�Ɏw�肷�����] 
            //arg0�i�ForderUnitRow�j�F�����P�ʍs 
            //arg1�i�FexecQty�j�F��萔�� 
            //arg2�i�FexecPrice�j�F���P�� 
            //arg3�i�Ffxate�j�F���בփ��[�g
            FeqOrderExecutionParams l_feqOrderExecutionParams = super.getInitializedOrderExecutionParams(
                l_orderUnitRow, 
                l_dblQuantity, 
                l_dblPrice, 
                l_dblExecRate);

            // �Q�j�߂�l�̍s�I�u�W�F�N�g.�������Ɉ���.���������Z�b�g����B
            l_feqOrderExecutionParams.setExecTimestamp(l_datExecDate);

            WEB3FeqOrderExecution l_orderExecution = 
                (WEB3FeqOrderExecution)l_tradingModule.getOrderManager().toOrderExecution(l_feqOrderExecutionParams); 

            log.exiting(STR_METHOD_NAME);
            return l_orderExecution;
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
    }   

    /**
     * (handle���X�V)<BR>
     * �����f�[�^�ɖ������X�V����B<BR>
     * <BR>
     * this.handleOrderFillMarketUpdates()�ɈϏ�����B<BR>
     * @@param l_lngOrderId - (�����h�c)<BR>
     * �����h�c<BR>
     * @@param l_execCallbackMessage - (���R�[���o�b�N���b�Z�[�W)<BR>
     * ���R�[���o�b�N���b�Z�[�W<BR>
     * @@return OrderExecution
     * @@roseuid 42B7D67003AA
     */
    public OrderExecution handleExecutionUpdate(
        long l_lngOrderId, 
        FeqOrderFillMarketResponseMessage l_execCallbackMessage) 
    {
        return (OrderExecution) this.handleOrderFillMarketUpdates(l_lngOrderId, l_execCallbackMessage);
    }
}
@
