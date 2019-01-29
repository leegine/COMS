head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Җ�����X�V�C���^�Z�v�^(WEB3AdminBondExecuteCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
                 : 2006/10/12 ꎉ�   (���u) WEB�V�J���W���̌������̑Ή��inewBigDecimal�����j
*/

package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (���Ǘ��Җ�����X�V�C���^�Z�v�^)<BR>
 * ���Ǘ��Җ�����X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminBondExecuteCancelUpdateInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteCancelUpdateInterceptor.class);
    
    /**
     * (�g���������P��)<BR>
     * �g���������P��<BR>
     */
    private WEB3BondOrderUnit bondOrderUnit;
    
    /**
     * (���Ǘ��Җ�����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44D96A5D038A
     */
    public WEB3AdminBondExecuteCancelUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�i�����P�ʁj�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * �P�j�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.�������P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * �@@���ڐݒ���e�� �u�������_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44D96A5D03A9
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_params == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            if (WEB3DateUtility.compare(
                WEB3DateUtility.getDate(l_params.getBizDate(), "yyyyMMdd"),
                WEB3GentradeTradingTimeManagement.getOrderBizDate()) < 0)
            {
                //��萔��
                l_params.setExecutedQuantity(
                    new BigDecimal(String.valueOf(l_params.getExecutedQuantity())).add(
                        new BigDecimal(
                        	String.valueOf(this.getBondOrderUnit().getExecutedQuantity()))).doubleValue());
                
                //host���M�敪
                l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
            }
            else
            {
                //���v�����z
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
                WEB3BondBizLogicProvider l_provider =
                    (WEB3BondBizLogicProvider)l_tradingModule.getBizLogicProvider();
                BigDecimal l_bdQuantity = 
                	new BigDecimal(String.valueOf(this.getBondOrderUnit().getExecutedQuantity()));
                BigDecimal l_bdPrice = new BigDecimal(String.valueOf(this.getBondOrderUnit().getExecutedPrice()));
                BigDecimal l_bdTradePrice = 
                    l_provider.calcTradingPrice(l_bdQuantity, l_bdPrice, this.getBondProduct());
                if (l_bdTradePrice != null)
                {    
                    double l_dblTradePrice = l_bdTradePrice.doubleValue();
                    l_params.setExecutedAmount(l_params.getExecutedAmount() - l_dblTradePrice);
                }
                
                //host���M�敪
                l_params.setHostSendDiv(WEB3HostSendDivDef.NOT_SEND);
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        
        //0�F�����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);
          
        //�Ǘ���.get�Ǘ��҃R�[�h()
        l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (get�g���������P��)<BR>
     * get�g���������P��<BR>
     * <BR>
     * �@@�g���������P�ʂ�Ԃ�<BR>
     * @@return webbroker3.bd.WEB3BondOrderUnit
     * @@roseuid 44E05DFB0372
     */
    public WEB3BondOrderUnit getBondOrderUnit() 
    {
        return this.bondOrderUnit;
    }
    
    /**
     * (set�g���������P��)<BR>
     * set�g���������P��<BR>
     * <BR>
     * �@@�g���������P�ʂ��Z�b�g����<BR>
     * @@param l_bondOrderUnit - (�g���������P��)<BR>
     * �g���������P��<BR>
     * @@roseuid 44E05E2D014F
     */
    public void setBondOrderUnit(WEB3BondOrderUnit l_bondOrderUnit) 
    {
        this.bondOrderUnit = l_bondOrderUnit;
    }
}
@
