head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteCommonInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Җ�苤�ʃC���^�Z�v�^(WEB3AdminBondExecuteCommonInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
*/

package webbroker3.bd;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

/**
 * (���Ǘ��Җ�苤�ʃC���^�Z�v�^)<BR>
 * ���Ǘ��Җ�苤�ʃC���^�Z�v�^�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteCommonInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCommonInterceptor.class);
    
    /**
     * (���Ǘ��Җ�苤�ʃC���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D096E10021
     */
    public WEB3AdminBondExecuteCommonInterceptor() 
    {
     
    }
    
    /**
     * (�i���j�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR> 
     * <BR>
     * ���Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     *  <BR>
     * �P�j�g�����ڃZ�b�g<BR>
     *   �p�����[�^.���Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * �@@���ڐݒ���e��<BR>
     * �@@�u���V�K���_�����e�[�u��DB�X�V�d�l.xls�v<BR>
     * �@@�Q�ƁB<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderExecutionParams
     * @@roseuid 44D9680F008C
     */
    public BondOrderExecutionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderExecutionParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderExecutionParams)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_params == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        long l_lngOrderUnitId = l_params.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_orderManager =
            (WEB3BondOrderManager) l_tradingMod.getOrderManager();
        BondOrderUnit l_bondOrderUnit = null;
        
        // �����P�ʃI�u�W�F�N�g�擾
        l_bondOrderUnit = (BondOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        
        BondOrderUnitRow l_bondOrderUnitRow = 
            (BondOrderUnitRow) l_bondOrderUnit.getDataSourceObject();
        l_params.setDeliveryDate(l_bondOrderUnitRow.getDeliveryDate());
        l_params.setBizDate(l_bondOrderUnitRow.getBizDate());
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
