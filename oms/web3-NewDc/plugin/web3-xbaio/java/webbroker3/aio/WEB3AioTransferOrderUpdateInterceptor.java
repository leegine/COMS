head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioTransferOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֒����X�V�C���^�Z�v�^(WEB3AioTransferOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ���E (���u) �V�K�쐬       
                   2004/10/23 ������ (���u) ���r���[    
                   2005/01/07 ���E (���u) �c�Ή�
Revesion History : 2007/08/02 �����F(���u)�d�l�ύX���f��749 �c�a�X�V�d�l144 147
Revesion History : 2009/03/31 ���u��(���u)�c�a�X�V�d�l226,227
*/
package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�U�֒����X�V�C���^�Z�v�^)<BR>
 * �U�֒����X�V�C���^�Z�v�^�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioTransferOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
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
     * (MQ�X�e�[�^�X)
     */
    private String mqStatus;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioTransferOrderUpdateInterceptor.class); 
    
    /**
     * (�U�֒����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
     * @@param cashTransOrderSpec - (���o���������e�I�u�W�F�N�g)
     * @@return WEB3AioTransferOrderUpdateInterceptor
     * @@roseuid 413583820016
     */
    public WEB3AioTransferOrderUpdateInterceptor(WEB3AioNewOrderSpec cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = "WEB3AioTransferOrderUpdateInterceptor" +
            "(WEB3AioNewOrderSpec cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);
   
        this.cashTransOrderSpec = cashTransOrderSpec;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j  <BR>
     * �����P��Params�Ɋg������(*)��ݒ肵�ԋp����B  <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB  <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB  <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �P�j�����o�H�敪�ɂ���  <BR>
     * �ȉ��̂悤�ɐݒ肷��B  <BR>
     * <BR>
     * �����P��.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j �̏ꍇ�A <BR>
     * �Z�b�V�������擾���������ڂ̒l���Z�b�g����B �Z�b�V�������擾�ł��Ȃ��ꍇ�́A<BR>
     * 2�iPC�j���Z�b�g����B<BR>
     * �����P��.������� = 1005�ȊO�̏ꍇ�A�Z�b�V�������擾���������ڂ̒l���Z�b�g����B  <BR>
     * <BR>
     * <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     *  �u�؋�������U��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB  <BR>
     *  �u�M�p�ۏ؋��ւ̐U��_�����P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_updateType - ((�X�V�^�C�v)<BR>)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j
     * 
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * 
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X
     * 
     * @@return AioOrderUnitParams
     * @@roseuid 413583820025
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
         OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
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
            //�����J�e�S��
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);  

            //������
            l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                this.bizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD));

            //��n��
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);

            //�ŋ敪
            //�f�t�H���g�F0
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            //�~�j���敪
            //�f�t�H���g�F0
            l_orderUnitParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
            
            // 2)�󒍓����̐ݒ���s���B
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            // 3)���҃R�[�h(SONAR)�̐ݒ���s���B
            long l_lngAccountId = l_orderUnitParams.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();            
            MainAccount l_acc = l_accountManager.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
            
            //���ʃR�[�h
            l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);
            
            // 4).com�f�r�b�g���ώ���ԍ��̐ݒ���s���B
            l_orderUnitParams.setComDebitNumber(null);
            
            // 5)�ۏ؋��敪�̐ݒ���s���B
            l_orderUnitParams.setGuaranteeDiv(null);
            
            // 6)�o���\���敪�̐ݒ���s���B
            l_orderUnitParams.setPaymentApplicationDiv(null);
            
            // 7)��������敪�̐ݒ���s���B
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // �����o�H�敪�ɂ��Ĉȉ��̂悤�ɐݒ肷��B
            //�����P��.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j �̏ꍇ�A
            //�Z�b�V�������擾���������ڂ̒l���Z�b�g����B
            //    �Z�b�V�������擾�ł��Ȃ��ꍇ�́A2�iPC�j���Z�b�g����B
            //�����P��.������� = 1005�ȊO�̏ꍇ�A�Z�b�V�������擾���������ڂ̒l���Z�b�g����B
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            String l_strRootDiv = null;
            if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderUnitParams.getOrderType()))
            {
                try
                {
                    l_strRootDiv = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                    // WEB3-AIO-A-FT-0245�ɂ��
                    if (WEB3StringTypeUtility.isEmpty(l_strRootDiv))
                    {
                        l_strRootDiv = WEB3OrderRootDivDef.PC;
                    }
                }
                catch (IllegalSessionStateException l_ex)
                {
                    l_strRootDiv = WEB3OrderRootDivDef.PC;
                }
            }
            else
            {
                l_strRootDiv = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            }
            l_orderUnitParams.setOrderRootDiv(l_strRootDiv);
            
            // 9)�����G���[���R�R�[�h�̐ݒ���s���B
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 10)MQ�X�e�[�^�X�̐ݒ���s���B
            l_orderUnitParams.setMqStatus(this.mqStatus);

            //�ʉ݃R�[�h
            l_orderUnitParams.setCurrencyCode(null);

            //���o�����z(�~���Z�z)
            l_orderUnitParams.setConvertAmount(null);

            //�E�v�R�[�h
            l_orderUnitParams.setRemarkCode(null);

            //�E�v��
            l_orderUnitParams.setRemarkName(null);
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
     * @@param l_datBizDate - (������)
     * @@roseuid 413D514B0140
     */
    public void setBizDate(Date l_datBizDate) 
    {
        final String STR_METHOD_NAME = "setBizDate(Date l_datBizDate) ";
        log.entering(STR_METHOD_NAME);
         
        this.bizDate = l_datBizDate;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 413D514B0314
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
  
        this.deliveryDate  = l_datDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);     
     
    }
    
    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B
     * @@param l_orderRequestNumber - (���ʃR�[�h)
     * @@roseuid 413FF8900136
     */
    public void setOrderRequestNumber(String l_orderRequestNumber) 
    {
        final String STR_METHOD_NAME = "setOrderRequestNumber(" +
            "String l_orderRequestNumber)";
        log.entering(STR_METHOD_NAME);
   
        this.orderRequestNumber  = l_orderRequestNumber;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    //===========remain zhou-yong NO.1 begin ========

    /**
     * (setMQ�X�e�[�^�X)<BR>
     * MQ�X�e�[�^�X���Z�b�g����B
     * @@param l_mqStatus - (MQ�X�e�[�^�X)
     * @@roseuid 413FF8900136
     */
    public void setMqStatus(String l_mqStatus) 
    {
        final String STR_METHOD_NAME = "setMqStatus(String l_mqStatus)";
        log.entering(STR_METHOD_NAME);
   
        this.mqStatus  = l_mqStatus;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    
    
    //===========remain zhou-yong NO.1 end ========
}
@
