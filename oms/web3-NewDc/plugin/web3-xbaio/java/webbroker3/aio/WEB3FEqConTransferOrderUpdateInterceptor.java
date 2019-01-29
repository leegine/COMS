head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FEqConTransferOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֒����X�V�C���^�Z�v�^�N���X(WEB3FEqConTransferOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/17 ��O�� (���u) �V�K�쐬       
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���U�֒����X�V�C���^�Z�v�^)
 * �O���U�֒����X�V�C���^�Z�v�^�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    
    /**
     * (���o���������e)
     */
    private WEB3AioNewOrderSpec cashTransOrderSpec;
    
    /**
     * (������)
     */
    private Date bizDate;
    
    /**
     * (��n��)
     */
    private Date deliveryDate;
    
    /**
     * (���ʃR�[�h)
     */
    private String orderRequestNumber;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioTransferNotifyUpdateInterceptor.class);    
    
    /**
     * @@roseuid 42363FCD008C
     */
    public WEB3FEqConTransferOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�O���U�֒����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
     * @@param l_cashTransOrderSpec - ���o���������e�I�u�W�F�N�g
     * @@roseuid 41E4FF0100DC
     */
    public WEB3FEqConTransferOrderUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = "WEB3FEqConTransferOrderUpdateInterceptor(" +
                "WEB3AioNewOrderSpec l_cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_cashTransOrderSpec == null)
        {
            log.debug("���o���������e��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }     
        this.cashTransOrderSpec = l_cashTransOrderSpec;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�O�������ւ̐U��_�����P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_updateType - INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j<BR>
     * 
     * @@param l_process - �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param �����P��Params - �����P�ʂ̃p�����[�^�N���X
     * 
     * @@return AioOrderUnitParams
     * @@roseuid 41E4FF0100EB
     */
    public AioOrderUnitParams mutate(
            OrderManagerPersistenceType l_updateType, 
            OrderManagerPersistenceContext l_process, 
            AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(" +
            "OrderManagerPersistenceType l_updateType, " +
            "OrderManagerPersistenceContext l_process, " +
            "AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }         
        try
        {
            // 1)�����J�e�S�� = 16�F�O�������U��
            l_orderUnitParams.setOrderCateg(OrderCategEnum.FEQ_TRANSFER);
            
            // 2)������ = �C���^�Z�v�^.������
            l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate,
                "yyyyMMdd"));
            
            // 3)��n�� = �C���^�Z�v�^.��n��
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);
            
            // 4)�ŋ敪 = �f�t�H���g�F0
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            // 5)�󒍓��� = ThreadLocalSystemAttributesRegistry.getAttributes(
            //  �hxblocks.gtl.attributes.systemtimestamp�h)�̖߂�l
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
            // 6)���҃R�[�h(SONAR) = �ڋq.���҃R�[�h
            long l_lngAccountId = l_orderUnitParams.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();            
            MainAccount l_acc = l_accountManager.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            
            l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
            
            // 7)���ʃR�[�h = �C���^�Z�v�^.���ʃR�[�h
            l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);
            
            // 8).com�f�r�b�g���ώ���ԍ� = null
            l_orderUnitParams.setComDebitNumber(null);
            
            // 9)�ۏ؋��敪 = null
            l_orderUnitParams.setGuaranteeDiv(null);
            
            // 10)�o���\���敪 = "FT"
            l_orderUnitParams.setPaymentApplicationDiv(WEB3AioPaymentApplicationDivDef.FEQ_TRANSFER);
            
            // 11)��������敪 = 0:�����l
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // 12)�����o�H�敪 = �Z�b�V�������擾���������ڂ̒l
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) 
                Services.getService(OpLoginSecurityService.class);
            
            String l_strRootDiv = l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            
            l_orderUnitParams.setOrderRootDiv(l_strRootDiv);
            
            // 13)�����G���[���R�R�[�h = 0000�F����
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 14)MQ�X�e�[�^�X = 0�F�����M
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
        
        }
        catch(NotFoundException l_ex)
        {
            log.error("�Y������ڋq�I�u�W�F�N�g������܂���", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B
     * @@param l_datOrderBizDate - ������
     * @@roseuid 41E4FF01010A
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        bizDate = l_datOrderBizDate;
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B
     * @@return Date
     * @@roseuid 41E4FF01011A
     */
    public Date getOrderBizDate() 
    {
        return bizDate;
    }
    
    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B
     * @@param l_datDeliveryDate - ��n��
     * @@roseuid 41E4FF010139
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        deliveryDate = l_datDeliveryDate;
    }
    
    /**
     * (get��n��)<BR>
     * ��n�����擾����B
     * @@return Date
     * @@roseuid 41E4FF010159
     */
    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }
    
    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B
     * @@param l_strOrderRequestNumber - ���ʃR�[�h
     * @@roseuid 41E4FF010178
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        orderRequestNumber = l_strOrderRequestNumber;
    }
    
    /**
     * (get���ʃR�[�h)<BR>
     * ���ʃR�[�h���擾����B
     * @@return String
     * @@roseuid 41E4FF010197
     */
    public String getOrderRequestNumber() 
    {
        return orderRequestNumber;
    }
}
@
