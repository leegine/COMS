head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���X�V�C���^�Z�v�^(WEB3AioSonarCashTransUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 ���E (���u) �V�K�쐬  
                   2004/10/23 ������ (���u) ���r���[   
                   2004/12/20 ���E (���u) �c�Ή� 
                   2006/08/28 �Ԑi (���u) �d�l�ύX�E���f��No.620             
*/
package webbroker3.aio;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
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
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (SONAR���o���X�V�C���^�Z�v�^)<BR>
 * SONAR���o���X�V�C���^�Z�v�^�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioSonarCashTransUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    
    /**
     * (���o���������e)
     */
    protected WEB3AioNewOrderSpec cashTransOrderSpec;
    
    /**
     * (��n��)
     */
    protected Date deliveryDate;
    
    /**
     * (�󒍓���)
     */
    protected Date receivedDateTime;
    

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSonarCashTransUpdateInterceptor.class); 
    
    /**
     * (SONAR���o���X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
     * @@param l_cashTransOrderSpec - (���o���������e)<BR>
     * ���o���������e�I�u�W�F�N�g
     * @@return WEB3AioSonarCashTransUpdateInterceptor
     * @@roseuid 4105B7B902FF
     */
    public WEB3AioSonarCashTransUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = "WEB3AioSonarCashTransUpdateInterceptor(" +
            "WEB3AioNewOrderSpec l_cashTransOrderSpec)";
        log.entering(STR_METHOD_NAME);

        this.cashTransOrderSpec = l_cashTransOrderSpec;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>�ԋp����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �uSONAR���o��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j<BR>
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X
     * @@return AioOrderUnitParams
     * @@roseuid 4105B7B9030F
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
         OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType, " +
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
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
            
            //��n��
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);

            //=========remain zhou-yong NO.1 begin ============
            
            //�ŋ敪
            l_orderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            
            //=========remain zhou-yong NO.1 end ============            
            
            // 1)�󒍓���
            l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);
            
            // 2)���҃R�[�h(SONAR)�̐ݒ���s���B
            long l_lngAccountId = l_orderUnitParams.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();            
            MainAccount l_acc = l_accountManager.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
            
            // 3)���ʃR�[�h�̐ݒ���s���B
            l_orderUnitParams.setOrderRequestNumber(null);
            
            // 4).com�f�r�b�g���ώ���ԍ��̐ݒ���s���B
            l_orderUnitParams.setComDebitNumber(null);
            
            // 5)�ۏ؋��敪�̐ݒ���s���B
            l_orderUnitParams.setGuaranteeDiv(null);
            
            // 6)�o���\���敪�̐ݒ���s���B
            l_orderUnitParams.setPaymentApplicationDiv(null);
            
            // 7)��������敪�̐ݒ���s���B
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // 8)�����o�H�敪�̐ݒ���s���B
            l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            
            // 9)�����G���[���R�R�[�h�̐ݒ���s���B
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 10)MQ�X�e�[�^�X�̐ݒ���s���B
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
       
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
     * (set��n��)<BR>
     * ��n�����Z�b�g����B
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 4105B7B9033E
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        
        this.deliveryDate = l_datDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�󒍓���)<BR>
     * �󒍓������Z�b�g����B
     * @@param l_dateReceivedDateTime - (�󒍓���)
     * @@roseuid 4105B7B9033E
     */
    public void setReceivedDateTime(Date l_dateReceivedDateTime) 
    {
        final String STR_METHOD_NAME = "setReceivedDateTime(Date l_dateReceivedDateTime)";
        log.entering(STR_METHOD_NAME);
          
        this.receivedDateTime = l_dateReceivedDateTime;
        
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
