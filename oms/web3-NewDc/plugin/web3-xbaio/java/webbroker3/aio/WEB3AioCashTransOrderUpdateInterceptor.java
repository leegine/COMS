head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�������X�V�C���^�Z�v�^(WEB3AioCashTransOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���E (���u) �V�K�쐬
                   2004/10/23 ������ (���u) ���r���[
                   2004/11/28 ��O�� (���u) �t�B�f���e�B�Ή�
                   2006/04/13 �юu�� (���u) �d�l�ύX�E���f��528
                   2006/04/14 �юu�� (���u) �d�l�ύX�E���f��539
                   2006/10/17 ���G�� (���u) �d�l�ύX�E���f��626
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (���o�������X�V�C���^�Z�v�^)<BR>
 * ���o�������X�V�C���^�Z�v�^�N���X
 *
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashTransOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor
{

    /**
     * (���o���������e)
     */
    protected WEB3AioNewOrderSpec cashTransOrderSpec;

    /**
     * (������)
     */
    protected Date bizDate;

    /**
     * (��n��)
     */
    protected Date deliveryDate;

    /**
     * (.com�f�r�b�g���ώ���ԍ�)
     */
    protected String comDebitNumber;

    /**
     * (�ۏ؋��敪)
     */
    protected String guaranteeDiv;

    /**
     * (�o���\���敪)
     */
    protected String paymentApplicationDiv;

    /**
     * (���ʃR�[�h)
     */
    protected String orderRequestNumber;

    /**
     * (�����o�H�敪)<BR>
     * 1�Fcall <BR>
     * 2�FPC <BR>
     * 3�F�X�����O�V���b�g <BR>
     * 4�Fi-mode <BR>
     * 5�Fvodafone <BR>
     * 6�Gau <BR>
     * 7�F�X�����O�V���b�g�i�����j <BR>
     * 9�FHOST <BR>
     * A�F�Ǘ��� <BR>
     * B�F�ۏ؋������U�փo�b�`<BR>
     */
    protected String orderRootDiv;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransOrderUpdateInterceptor.class);
    
    /**
     * (���o�������X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B
     * @@param l_cashTransOrderSpec - (���o���������e�I�u�W�F�N�g)
     * @@return WEB3AioCashTransOrderUpdateInterceptor
     * @@roseuid 40E2A42B0272
     */
    public WEB3AioCashTransOrderUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec)
    {
        final String STR_METHOD_NAME = "WEB3AioCashTransOrderUpdateInterceptor" +
            "(WEB3AioNewOrderSpec l_cashTransOrderSpec)";
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
     * �@@�v���p�e�B�̓��e���A<BR>
     * �p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B <BR>
     * <BR>
     * �P�j�����o�H�敪�ɂ���<BR>
     *    �ȉ��̂悤�ɐݒ肷��B<Br>
     * <BR>
     *    �����P��.������� = 1001(�o������) �̏ꍇ�A<BR>
     *    �Z�b�V�������擾���������ڂ̒l���Z�b�g����B<BR>
     *    �Z�b�V�������擾�ł��Ȃ��ꍇ�́A2�iPC�j���Z�b�g����B<BR>
     *<BR>
     * �Q�jMQ�X�e�[�^�X�ɂ���<BR> 
     *   �ȉ��̂悤�ɐݒ肷��B <BR>
     *<BR>
     *   �����P��.������� = 1001(�o������) �̏ꍇ�A0�i�����M�j���Z�b�g����B <BR>
     *   �����P��.������� = 1002(��������) �̏ꍇ�A1�i���M�ς݁j���Z�b�g����B<BR> 
     *<BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�o���\��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>
     * �u�������ʒʒm_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB <BR>
     * @@param l_updateType - ((�X�V�^�C�v)<BR>)<BR>
     * INSERT�܂��́AUPDATE<BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X
     * @@return AioOrderUnitParams
     * @@roseuid 40E2A4350204
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
                "OrderManagerPersistenceContext l_process, AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }     

        //�����J�e�S���̐ݒ���s���B
        l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);  
        
        //������
        l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            this.bizDate, "yyyyMMdd"));
        
        //��n��
        l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
        // 1)�󒍓���
        l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        // 2)���҃R�[�h(SONAR)�̐ݒ���s���B
        long l_lngAccountId = l_orderUnitParams.getAccountId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();            
        MainAccount l_acc = null;
        try
        {
            l_acc = l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("_____not MainAccount", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_acc.getDataSourceObject();
        log.debug("MainAccountRow" + l_mainAccountRow);
        
        if(l_mainAccountRow == null)
        {
            log.error("___not MainAccountRow___");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
        l_orderUnitParams.setSonarTraderCode(l_strSonarTraderCode);
        
        
        l_orderUnitParams.setOrderRequestNumber(this.orderRequestNumber);

        // 4).com�f�r�b�g���ώ���ԍ��̐ݒ���s���B
        l_orderUnitParams.setComDebitNumber(this.comDebitNumber);
        
        // 5)�ۏ؋��敪�̐ݒ���s���B
        l_orderUnitParams.setGuaranteeDiv(this.guaranteeDiv);
        
        // 6)�o���\���敪�̐ݒ���s���B
        l_orderUnitParams.setPaymentApplicationDiv(this.paymentApplicationDiv);
        
        // 7)��������敪�̐ݒ���s���B
        l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        // 8)�����o�H�敪�̐ݒ���s���B
        //�������o�H�敪�ɂ���
        //�ȉ��̂悤�ɐݒ肷��B
        //�����P��.������� = 1001(�o������) �̏ꍇ�A�Z�b�V�������擾���������ڂ̒l���Z�b�g����B
        //�Z�b�V�������擾�ł��Ȃ��ꍇ�́A2�iPC�j���Z�b�g����B 
        if (OrderTypeEnum.CASH_OUT.equals(l_orderUnitParams.getOrderType()))
        {
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
            String l_strRootDiv = null;            
            boolean l_blnIsLoginRootDiv = true;
            try
            {
                l_strRootDiv = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            }
            catch (IllegalSessionStateException l_ex)
            {
                l_blnIsLoginRootDiv = false;
            }

            if(l_blnIsLoginRootDiv && l_strRootDiv != null)
            {
                l_orderUnitParams.setOrderRootDiv(l_strRootDiv);
            }
            else
            {
                l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.PC);
            }            
            
            // 10)MQ�X�e�[�^�X�̐ݒ���s���B
            //�����P��.������� = 1001(�o������) �̏ꍇ�A0�i�����M�j���Z�b�g����B
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
        }
        
        // 10)MQ�X�e�[�^�X�̐ݒ���s���B
        //�����P��.������� = 1002(��������) �̏ꍇ�A1�i���M�ς݁j���Z�b�g����
        //�Z�b�V������蒍���o�H�敪��ݒ肷��
        else if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()))
        {            
            l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
            
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        }
        
        // 9)�����G���[���R�R�[�h�̐ݒ���s���B
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
   
        log.exiting(STR_METHOD_NAME); 
    
        return l_orderUnitParams;
    }
    
    /**
     * (set.com�f�r�b�g���ώ���ԍ�)<BR>
     * .com�f�r�b�g���ώ���ԍ����v���p�e�B�ɃZ�b�g����B
     * @@param l_strComDebitNumber - (.com�f�r�b�g���ώ���ԍ�)
     * @@roseuid 40F511520091
     */
    public void setComDebitNumber(String l_strComDebitNumber) 
    {
        final String STR_METHOD_NAME = "setComDebitNumber(String l_strComDebitNumber)";
        log.entering(STR_METHOD_NAME);
  
        this.comDebitNumber = l_strComDebitNumber;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B
     * @@param l_datBizDate - (������)
     * @@roseuid 40F631EE03B0
     */
    public void setBizDate(Date l_datBizDate) 
    {
        final String STR_METHOD_NAME = "setBizDate(Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
   
        this.bizDate = l_datBizDate;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (setDeliveryDate)<BR>
     * ��n�����Z�b�g����B
     * @@param l_strDeliveryDate - (��n��)
     * @@roseuid 4125B3600055
     */
    public void setDeliveryDate(Date l_strDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(Date l_strDeliveryDate)";
        log.entering(STR_METHOD_NAME);
  
        this.deliveryDate = l_strDeliveryDate;
        
        log.exiting(STR_METHOD_NAME);          
    }

    /**
     * (setGuaranteeDiv)<BR>
     * �ۏ؋��敪���Z�b�g����B
     * @@param l_strGuaranteeDiv - (�ۏ؋��敪)
     * @@roseuid 4125B3600055
     */
    public void setGuaranteeDiv(String l_strGuaranteeDiv) 
    {
        final String STR_METHOD_NAME = "setGuaranteeDiv(String l_strGuaranteeDiv)";
        log.entering(STR_METHOD_NAME);
        
        this.guaranteeDiv = l_strGuaranteeDiv;
        
        log.exiting(STR_METHOD_NAME);          
    }

    /**
     * (set�o���\���敪)<BR>
     * �ۏ؋��敪���Z�b�g����B
     * @@param l_strPaymentApplicationDiv - (�o���\���敪)
     * @@roseuid 4125B3600055
     */
    public void setPaymentApplicationDiv(String l_strPaymentApplicationDiv) 
    {
        final String STR_METHOD_NAME = "setPaymentApplicationDiv(String l_strPaymentApplicationDiv)";
        log.entering(STR_METHOD_NAME);
        
        this.paymentApplicationDiv = l_strPaymentApplicationDiv;
        
        log.exiting(STR_METHOD_NAME);          
    }

    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        final String STR_METHOD_NAME = "setOrderRequestNumber(String l_strOrderRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        this.orderRequestNumber = l_strOrderRequestNumber;
        
        log.exiting(STR_METHOD_NAME);          
    }
    
    /**
     * (set�����o�H�敪)<BR>
     * �����o�H�敪���Z�b�g����B<BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪)
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        final String STR_METHOD_NAME = "setOrderRootDiv(String l_strOrderRootDiv)";
        log.entering(STR_METHOD_NAME);
        
        this.orderRootDiv = l_strOrderRootDiv;
        
        log.exiting(STR_METHOD_NAME); 

    }
}@
