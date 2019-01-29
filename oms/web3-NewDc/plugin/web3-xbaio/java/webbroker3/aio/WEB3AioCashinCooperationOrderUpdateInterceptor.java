head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.34.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinCooperationOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�����X�V�C���^�Z�v�^ �N���X(WEB3AioCashinCooperationOrderUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/12 ��O�� (���u) �V�K�쐬
                   2006/08/30 �Ԑi�@@(���u)���f��No.621�Ή�
                   2006/09/18 ���G�́@@(���u)���f��No.647�Ή�
*/

package webbroker3.aio;

import java.util.Date;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

/**
 * (�����A�g�����X�V�C���^�Z�v�^ )
 * �����A�g�����X�V�C���^�Z�v�^�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioCashinCooperationOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationOrderUpdateInterceptor.class);  
    
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
     * (�����o�H)
     */
    private String bizChannel;
    
    /**
     * (�U�֋L�q)
     */
    private String description;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     */
    private String currencyCode;
    
    /**
     * ���o�����z(�~���Z�z)<BR>
     */
    private Double convertAmount;
    
    /**
     * (�����A�g�����X�V�C���^�Z�v�^)<BR>
     * �f�t�H���g�R���X�g���N�^�B <BR>
     * <BR>
     * @@param l_cashTransOrderSpec  - (���o���������e�I�u�W�F�N�g)
     * @@roseuid 41E4FF0100DC
     */
    public WEB3AioCashinCooperationOrderUpdateInterceptor(WEB3AioNewOrderSpec l_cashTransOrderSpec) 
    {
        this.cashTransOrderSpec = l_cashTransOrderSpec;
    }
    
    /**
     * �imutate���\�b�h�̎����j  
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B  <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB  <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB  <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A<BR>
     * �ԋp����B <BR> 
     * <BR>
     * �P�jMQ�X�e�[�^�X�ɂ��� <BR>
     *    1�i���M�ς݁j���Z�b�g����B <BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V  <BR>
     * �u��s�����ʒm_�����P�ʃe�[�u���d�l.xls�v <BR>
     * �u��s�����ʒm_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB  <BR>
     * �u�����A�g_�����P�ʃe�[�u���d�l.xls�v���Q�ƁB <BR>
     * 
     * @@param l_updateType - �X�V�^�C�v
     * @@param l_process - ����
     * @@param l_orderUnitParams - �����P�ʂ̃p�����[�^�N���X
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
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }         
        //�y��Trade�z�⑫����.DB�X�V  
        //�u��s�����ʒm_�����P�ʃe�[�u���d�l.xls�v 

        try
        {
            // 1)������� = 1002�F��������
            l_orderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);
            
            // 2)�����^�C�v = 5�F����
            l_orderUnitParams.setProductType(ProductTypeEnum.CASH);
            
            // 3)�������� = ���o���������e.getQuantity()�̖߂�l
            l_orderUnitParams.setQuantity(this.cashTransOrderSpec.getQuantity());
            
            // 4)������ = �C���^�Z�v�^.������
            //�U�����������y�ѓ����ȑO��SONAR�ւ̑��M���ԓ��ɔ��������`�[�̏ꍇ�A���������B
            //�U�����������y�ѓ����ȑO��SONAR�ւ̑��M���ԊO�ɔ��������`�[�̏ꍇ�A���c�Ɠ������B
            //�U�����������ȍ~�ŐU�������c�Ɠ��̏ꍇ�A�U���������B
            //�U�����������ȍ~�ŐU��������c�Ɠ��̏ꍇ�A�U�������c�Ɠ������B
            
            l_orderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));
            
            // 5)��n�� = �C���^�Z�v�^.��n���������Ɠ���
            l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
            // 6)���҃R�[�h�iSONAR�j= �ڋq.���҃R�[�h
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
            
            // 10)�o���\���敪 = null
            l_orderUnitParams.setPaymentApplicationDiv(null);
            
            // 11)��������敪 = 0:�����l
            l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            
            // 12)�����o�H�敪
            l_orderUnitParams.setOrderRootDiv(this.bizChannel);
            
            // 13)�����G���[���R�R�[�h = 0000�F����
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            
            // 14)MQ�X�e�[�^�X = 1:���M�ς�
            l_orderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            // 15)�����J�e�S�� = 9�F���o��
            l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
            
            // 16)�U�֋L�q
            l_orderUnitParams.setDescription(this.description);
            
            //17)�󒍓��� 
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            //18 �ʉ݃R�[�h
            l_orderUnitParams.setCurrencyCode(this.currencyCode);
            
            //19 ���o�����z(�~���Z�z)
            l_orderUnitParams.setConvertAmount(this.convertAmount);
        }
        catch(NotFoundException l_ex)
        {
            log.error("�Y������ڋq�I�u�W�F�N�g������܂���", l_ex); 
            log.exiting(STR_METHOD_NAME);
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
     * ���������Z�b�g����B<BR>
     * @@param l_datOrderBizDate - ������
     * @@roseuid 41E4FF01010A
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        bizDate = l_datOrderBizDate;
    }
    
    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B<BR>
     * @@param l_datDeliveryDate - ��n��
     * @@roseuid 41E4FF010139
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        deliveryDate = l_datDeliveryDate;
    }
    
    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B<BR>
     * @@param l_strOrderRequestNumber - ���ʃR�[�h
     * @@roseuid 41E4FF010178
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        orderRequestNumber = l_strOrderRequestNumber;
    }
    
    /**
     * (set�����o�H)<BR>
     * �����o�H���Z�b�g����B<BR>
     * @@param l_strBizChannel - �����o�H
     * @@roseuid 41E4FF010178
     */
    public void setBizChannel(String l_strBizChannel) 
    {
        bizChannel = l_strBizChannel;
    }
    
    /**
     * (set�U�֋L�q)<BR>
     * �U�֋L�q���Z�b�g����B <BR>
     * (*�����A�g�ł́A���Z�@@�փR�[�h���Z�b�g�����j<BR>
     * @@param l_strDescription - �U�֋L�q
     * @@roseuid 41E4FF010178
     */
    public void setDescription(String l_strDescription) 
    {
        description = l_strDescription;
    }
    
    /**
     * (set�ʉ݃R�[�h )<BR>
     * �ʉ݃R�[�h���Z�b�g����B <BR>
     * @@param l_strCurrencyCode - �ʉ݃R�[�h
     * @@roseuid 41E4FF010178
     */
    public void setCurrencyCode(String l_strCurrencyCode) 
    {
        this.currencyCode = l_strCurrencyCode;
    }
    
    /**
     * (set���o�����z�i�~���Z�z�j)<BR>
     * ���o�����z�i�~���Z�z�j���Z�b�g����B <BR>
     * @@param l_dblConvertAmount - ���o�����z�i�~���Z�z�j
     * @@roseuid 41E4FF010178
     */
    public void setConvertAmount(Double l_dblConvertAmount) 
    {
        this.convertAmount = l_dblConvertAmount;
    }
}
@
