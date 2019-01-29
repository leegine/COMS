head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨��������ʒm�����ʒm�ꌏTransactionCallback(WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/11 ���u��(���{���u) �V�K�쐬
Revesion History : 2007/04/24 �Ј���(���u) �d�l�ύX���f��No.637
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�敨��������ʒm�����ʒm�ꌏTransactionCallback�j�B<BR>
 * <BR>
 * �g�����U�N�V�������������{��������N���X�B<BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback implements TransactionCallback
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback.class);

    /**
      * �敨OP��������ʒm�X�V�C���^�Z�v�^�I�u�W�F�N�g�B<BR>
      */
    private WEB3IfoChangeCancelNotifyUpdateInterceptor ifoChangeCancelNotifyUpdateInterceptor;

    /**
      * �敨OP��������ʒm�L���[Row�I�u�W�F�N�g�B<BR>
      */
    private HostFotypeOrderClmdNotifyRow hostFotypeOrderClmdNotifyRow;

    /**
     * �R���X�g���N�^�B<BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * @@params l_ifoChangeCancelNotifyUpdateInterceptor - (�敨OP��������ʒm�X�V�C���^�Z�v�^)
     * @@params l_hostFotypeOrderClmdNotifyRow - (�敨OP��������ʒm�L���[Row)
     */
    public WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallback(
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_ifoChangeCancelNotifyUpdateInterceptor,
        HostFotypeOrderClmdNotifyRow l_hostFotypeOrderClmdNotifyRow)
    {
        ifoChangeCancelNotifyUpdateInterceptor = l_ifoChangeCancelNotifyUpdateInterceptor;
        hostFotypeOrderClmdNotifyRow = l_hostFotypeOrderClmdNotifyRow;
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

        WEB3FuturesChangeCancelNotifyUnitService l_futuresChangeCancelNotifyUnitService =
            (WEB3FuturesChangeCancelNotifyUnitService) Services.getService(WEB3FuturesChangeCancelNotifyUnitService.class);
        OrderUnit l_reOrderUnit = null;
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            // lock����
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            
            l_accountManager.lockAccount(
                hostFotypeOrderClmdNotifyRow.getInstitutionCode(),
                hostFotypeOrderClmdNotifyRow.getBranchCode(),
                hostFotypeOrderClmdNotifyRow.getAccountCode());
            
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingMod.getOrderManager();
            
            //get�����P��
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(hostFotypeOrderClmdNotifyRow.getInstitutionCode(),
                hostFotypeOrderClmdNotifyRow.getBranchCode(), 
                ProductTypeEnum.IFO, 
                hostFotypeOrderClmdNotifyRow.getOrderRequestNumber());
            
            // SONAR���͂̒�������ʒm�F
            // ��������ʒm�L���[.�����㔭���o�H�敪��null�̏ꍇ�A
            // SONAR���͂̒�������ʒm�ł���Ɣ��肷��B
            String l_strModSubmitOrderRouteDiv =
                hostFotypeOrderClmdNotifyRow.getModSubmitOrderRouteDiv();

            // ���̂����ꂩ�̏����ɊY������ꍇ�A�������X�L�b�v����
            // �P�D�������ρF�����P��.�s��m�F�ςݐ���==null�̏ꍇ
            // �Q�D��������ʒm==SONAR���͂̎���ʒm�ł���A����
            // �擾���������P�ʂ�WEB�V�ɂ�����������ł���A����
            // ��������ʒm�L���[.�����㔭���o�H�敪�������P��.�����o�H�敪�̏ꍇ
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (l_orderUnitRow.getConfirmedQuantityIsNull())
            {
                log.debug("��������t���ςȂ̂ŁA���Y�����̒�������ʒm�������X�L�b�v����B");
                Map l_mapChanges = new HashMap();
                l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(hostFotypeOrderClmdNotifyRow.getPrimaryKey(),l_mapChanges);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            if (l_orderUnitRow.getConfirmedQuantityIsNull()
                || (l_strModSubmitOrderRouteDiv != null
                    && !l_strModSubmitOrderRouteDiv.equals(l_orderUnitRow.getSubmitOrderRouteDiv())
                    && (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus())
                        || OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus())
                        || OrderStatusEnum.MODIFYING.equals(l_orderUnitRow.getOrderStatus())
                        || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderUnitRow.getOrderStatus()))))
            {
                log.debug(
                    "SONAR���͂̒�������ʒm�i�قȂ锭���o�H����̒�������j���� " +
                    "WEB�V�ɂ�����������̏ꍇ�A���Y�����̒�������ʒm�������X�L�b�v����B");
                Map l_mapChanges = new HashMap();
                l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(hostFotypeOrderClmdNotifyRow.getPrimaryKey(),l_mapChanges);
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //notify����
            l_futuresChangeCancelNotifyUnitService.notifyChange(
                l_orderUnit,
                ifoChangeCancelNotifyUpdateInterceptor);       
            l_reOrderUnit = l_orderMgr.getOrderUnit(l_orderUnit.getOrderUnitId());       
         }
        catch (NotFoundException l_exp)
        {
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getMessage(),
                l_errorInfo);
        }
        catch (WEB3BaseException l_exp)
        {
            ErrorInfo l_errorInfo = l_exp.getErrorInfo();
            l_errorInfo.setErrorClass(l_exp.getClass().getName());
            throw new DataCallbackException(
                l_exp.getErrorMessage(),
                l_errorInfo);
        }

        Map l_mapChanges = new HashMap();
        //17:  �L���[�e�[�u���ɏ����敪�Z�b�g
        // ����ʒm�ŏo���҂����ʂ���(*)�̏ꍇ�A5�F�������B
        // �ȊO�̏ꍇ�A�P�F������(�A���A��������ʒm�敪���h5�F�G���[�h�̏ꍇ�A�܂��͏������ɗ�O�����������ꍇ�A�h9�F�G���[�h)
        // (*)����ʒm�ŏo���҂����ʂ���̏ꍇ
        // �E��������ʒm�敪 == �h��������h &&
        // �E��������ʒm�敪 ==  �h������s�h &&
        // �E�����㐔�� > �����P��.��萔��
        double l_dblModifiedQuantity = hostFotypeOrderClmdNotifyRow.getModifiedQuantity();
        if (Double.isNaN(l_dblModifiedQuantity))
        {
            l_dblModifiedQuantity = 0;
        }
        double l_dblExecutedQuantity = l_reOrderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }

        if ((WEB3CanmodReceiptTypeDef.CANCEL.equals(hostFotypeOrderClmdNotifyRow.getCanmodReceiptType())
            || WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(hostFotypeOrderClmdNotifyRow.getCanmodReceiptType()))
            && l_dblModifiedQuantity > l_dblExecutedQuantity)
        {
            l_mapChanges.put("status", WEB3StatusDef.DEALING);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        }
        else if (WEB3CanmodReceiptTypeDef.ERROR.equals(hostFotypeOrderClmdNotifyRow.getCanmodReceiptType()))
        {
            l_mapChanges.put("status", WEB3StatusDef.DATA_ERROR);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        }
        else
        {
            l_mapChanges.put("status", WEB3StatusDef.DEALT);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        }

        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateQuery(hostFotypeOrderClmdNotifyRow.getPrimaryKey(),l_mapChanges);
        log.exiting(STR_METHOD_NAME);

        return null;
    }
}
@
