head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityLoanUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���X�V�C���^�Z�v�^ (WEB3AioSecurityLoanUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ������ (���u) �V�K�쐬                                     
*/

package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�،��S�ۃ��[���X�V�C���^�Z�v�^)
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityLoanUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityLoanUpdateInterceptor.class); 
    
    /**
     * (���o���������e)<BR>
     * ���o���������e <BR>
     */
    private WEB3AioNewOrderSpec cashTransOrderSpec;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    private Date bizDate;
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    private Date deliveryDate;
    
    /**
     * (�����o�H�敪)<BR>
     * �����o�H�敪 <BR>
     *<BR>
     *1�Fcall <BR>
     *2�FPC <BR>
     *3�F�X�����O�V���b�g <BR>
     *4�Fi-mode <BR>
     *5�Fvodafone <BR>
     *6�Fau <BR>
     *7�F�X�����O�V���b�g�i����<BR>
     *9�FHOST <BR>
     *A�F�Ǘ��� <BR>
     *B�F�ۏ؋������U�փo�b�`<BR>
     */
    protected String orderRootDiv;
    
    /**
     * (�،��S�ۃ��[���X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�����̓��o���������e���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param cashTransOrderSpec - (���o���������e�I�u�W�F�N�g)
     * @@roseuid 4508EC8D012C
     */
    public WEB3AioSecurityLoanUpdateInterceptor(WEB3AioNewOrderSpec cashTransOrderSpec) 
    {
        final String STR_METHOD_NAME = 
            "WEB3AioSecurityLoanUpdateInterceptor(WEB3AioNewOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        this.cashTransOrderSpec = cashTransOrderSpec;
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR> 
     * �����P��Params�Ɋg������(*)��ݒ肵�ԋp����B<BR> 
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR> 
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �@@�v���p�e�B�̓��e���A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR> 
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR> 
     *  �u�،��S�ۃ��[��_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE <BR>
     * <BR>
     * �iOrderManagerPersistenceType�ɂĒ萔��`�B�j 
     * <BR>
     * @@param l_process - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * <BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * �����P�ʂ̃p�����[�^�N���X 
     * @@return AioOrderUnitParams
     * @@roseuid 4508ECD202B2
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process,
        AioOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate()";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.debug("�����P��Params��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        
        //�������,   order_type,  
        // 1019�F�U�֒���(�a��������؋�), 
        l_orderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK);
           
        //�����J�e�S��,   order_categ,   
        //"9�F���o��    �iOrderCategEnum.CASH_IN_OUT�j",
        l_orderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);
               
        // �����^�C�v,   product_type,  
        //  5�F����,   
        l_orderUnitParams.setProductType(ProductTypeEnum.CASH);
               
        // ��������,   quantity,   
        // ���o���������e.getQuantity()�̖߂�l,
        l_orderUnitParams.setQuantity(this.cashTransOrderSpec.getQuantity());
               
        // ������,   biz_date,   
        // �،��S�ۃ��[���X�V�C���^�Z�v�^.������, 
        l_orderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.bizDate, "yyyyMMdd"));
               
        // ��n��,   delivery_date,   
        // �،��S�ۃ��[���X�V�C���^�Z�v�^.��n��,
        l_orderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //�~�j���敪    mini_stock_div
        //0�FFALSE�i�~�j���łȂ��j
        l_orderUnitParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        
        // �󒍓���,   received_date_time,   
        // ��������,   
        l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
              
        // ���҃R�[�h�iSONAR�j,   sonar_trader_code,   
        // null,   
        l_orderUnitParams.setSonarTraderCode(null);
              
        // ���ʃR�[�h,   order_request_number,   
        // null,   
        l_orderUnitParams.setOrderRequestNumber(null);
              
        // .com�f�r�b�g���ώ���ԍ�,   com_debit_number,   
        // null,   
        l_orderUnitParams.setComDebitNumber(null);
               
        // �ۏ؋��敪,   guarantee_div,   
        // null,   
        l_orderUnitParams.setGuaranteeDiv(null);
               
        // �o���\���敪,   payment_application_div,  
        // null,   
        l_orderUnitParams.setPaymentApplicationDiv(null);
               
        // ��������敪,   cancel_type,   
        //0�F�����l, 
        l_orderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        // �����o�H�敪,   order_root_div,  
        // E:�U�֒���(�a��������؋�),   
        l_orderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.FROM_DEPOSIT_AMOUNT_DSK);
               
        // �����G���[���R�R�[�h,   error_reason_code,  
        //  0000�F����,   
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
               
        // MQ�X�e�[�^�X,   mq_status,  
        //  null,   
        l_orderUnitParams.setMqStatus(null);
        
        //�ʉ݃R�[�h    currency_code
        //null
        l_orderUnitParams.setCurrencyCode(null);
        
        //���o�����z(�~���Z�z)    convert_amount
        //null
        l_orderUnitParams.setConvertAmount(null);
        
        return l_orderUnitParams;
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B<BR>
     * @@param l_datOrderBizDate - (������)<BR>
     * ������<BR>
     * @@roseuid 4508ED1203CB
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        this.bizDate = l_datOrderBizDate;
    }
    
    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@roseuid 4508ED1203DB
     */
    public void setDeliveryDate(Date l_datDeliveryDate) 
    {
        this.deliveryDate = l_datDeliveryDate;      
    }
    
    /**
     * (set�����o�H�敪)<BR>
     * �����o�H�敪���Z�b�g����B<BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪)
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {        
        this.orderRootDiv = l_strOrderRootDiv; 
    }
}
@
