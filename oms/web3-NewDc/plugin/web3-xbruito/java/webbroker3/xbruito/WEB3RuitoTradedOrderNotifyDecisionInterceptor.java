head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradedOrderNotifyDecisionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ����������ʒm�m��C���^�Z�v�^(WEB3RuitoTradedOrderNotifyDecisionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BalanceAddFlagDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �ݓ����������ʒm�m��C���^�Z�v�^�B<BR>
 */
public class WEB3RuitoTradedOrderNotifyDecisionInterceptor
    extends WEB3RuitoDefaultRuitoOrderDecisionInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3RuitoTradedOrderNotifyDecisionInterceptor.class);

    /**
     * ��n��<BR>
     */
    private Timestamp deliveryDate;

    /**
     * �󒍓���<BR>
     */
    private Timestamp acceptedDate;

    /**
     * ���ʃR�[�h<BR>
     */
    private String requestNumber;

    /**
     * 1�F��s�U���݁A2�F�،���������<BR>
     */
    private String paymentMethod;

    /**
     * 0:���̑��@@1:MMF�@@2:�������t�@@���h�@@3:MRF<BR>
     */
    private RuitoTypeEnum ruitoTypeEnum;

    /**
     * 2:�S���w��@@3:���z�w��@@4:�����w��<BR>
     */
    private String ruitoSellDiv;

    /**
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C��<BR>
     */
    private String orderChannel;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 408F3D2A03DC
     */

    public WEB3RuitoTradedOrderNotifyDecisionInterceptor()
    {

    }

    /**
     * �imutate�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����ŗ^����ꂽ�ݓ������P��Params�ɒl��ݒ肵�A<BR>
     *      �ݓ������P��Params��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�������̐ݒ���s��<BR>
     * �@@�|�ݓ������P��Params.setBizDate()���R�[�����A<BR>
     *        �������̐ݒ���s���B<BR>
     * �@@�@@[setBizDate�ɓn���p�����^]<BR>
     * �@@�@@�@@�������F this.get�󒍓���()�̖߂�l��<BR>
     *              yyyyMMdd�`���ɕϊ�����������<BR>
     * <BR>
     * �Q�j�@@���҃R�[�h(SONAR)�̐ݒ���s���B<BR>
     * �@@�|�ݓ������P��Params.getAccountId()��<BR>
     *         �Ή�����ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�|�ݓ������P��Params.setSonarTraderCode()���R�[�����A<BR>
     *        ���҃R�[�h(SONAR)�̐ݒ���s���B<BR>
     * �@@�@@�msetSonarTraderCode�ɓn���p�����^�n<BR>
     * �@@�@@�@@���҃R�[�h(SONAR)�F 
     * �ڋq.getDataSourceObject().getSonarTraderCode()�̖߂�l<BR>
     * <BR>
     * �R�j�@@�c���v��t���O�̐ݒ���s���B<BR>
     * �@@�|�ݓ������P��Params.set�c���v��t���O()���R�[�����A<BR>
     *           �c���v��t���O�̐ݒ���s���B<BR>
     * �@@�@@[set�c���v��t���O�ɓn���p�����^]<BR>
     * �@@�@@�@@�c���v��t���O�F �h0�F�c���v�㖢�ρh<BR>
     * <BR>
     * �S�j�@@�ݓ������P��Params�ɁA�ȉ��̐ݒ���s���B<BR>
     * <BR>
     * �@@�@@�ݓ������P��Params.setOrderRootDiv(WEB3OrderRootDivDef.HOST)<BR>
     * �@@�@@�ݓ������P��Params.setErrorReasonCode(null)<BR>
     * �@@�@@�ݓ������P��Params.setMrfOrderRequestNumber(null)<BR>
     * �@@�@@�ݓ������P��Params.setReturnMethod(null)<BR>
     * <BR>
     * �T�j�@@���I�u�W�F�N�g�ɐݒ肳��Ă���l��ݓ������P��Params�ɐݒ肷��B<BR>
     * <BR>
     * �@@�@@�ݓ������P��Params.seDeliveryDate(this.get��n��())<BR>
     * �@@�@@�ݓ������P��Params.setReceivedDateTime(this.get�󒍓���())<BR>
     * �@@�@@�ݓ������P��Params.setOrderRequestNumber(this.get���ʃR�[�h())<BR>
     * �@@�@@�ݓ������P��Params.setPaymentMethod(this.get��n���@@())<BR>
     * �@@�@@�ݓ������P��Params.setRuitoType(this.get�ݓ��^�C�v())<BR>
     * �@@�@@�ݓ������P��Params.setGpSellDiv(this.get�ݓ����敪())<BR>
     * �@@�@@�ݓ������P��Params.setOrderChanel(this.get�����`���l��())<BR>
     * <BR>
     * �U�j�@@�����ŗ^����ꂽ�ݓ������P��Params��Ԃ��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - 
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������Ȃ�<BR>
     * @@param l_unitParams - �i�����O�̗ݓ������P��Params<BR>
     * @@return RuitoOrderUnitParams
     * @@roseuid 408F3D0D0274
     */
    public RuitoOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderUnitParams l_unitParams)
    {
        String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_persistenceType,"
                + "OrderManagerPersistenceContext l_persistenceContext, "
                + "RuitoOrderUnitParams l_unitParams)";
        log.entering(STR_METHOD_NAME);
        try
        {
            if (l_unitParams == null)
            {
                log.debug(" �p�����[�^�l��NULL ");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^�l��NULL");
            }
            //1)�������̐ݒ���s��            
            String l_strBizDate = WEB3DateUtility.formatDate(this.getAcceptedDate(), "yyyyMMdd");
            log.debug("������ = " + l_strBizDate);
            l_unitParams.setBizDate(l_strBizDate);
           
            //2)���҃R�[�h(SONAR)�̐ݒ���s���B
            long l_lngAccountId = l_unitParams.getAccountId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            AccountManager l_accMgr = l_finApp.getAccountManager();
            
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow = (MainAccountRow)
                l_acc.getDataSourceObject();
                
            String l_strSonarTraderCode = l_mainAccountRow.getSonarTraderCode();
            log.debug("���҃R�[�h = " + l_strSonarTraderCode);
            l_unitParams.setSonarTraderCode(l_strSonarTraderCode);

            //3)�c���v��t���O�̐ݒ���s���B
            l_unitParams.setBalanceAddFlag(
                WEB3BalanceAddFlagDef.BALANCE_OUTSTANDING);

            //4)�ݓ������P��Params�ɁA�ȉ��̐ݒ���s���B
            l_unitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            l_unitParams.setErrorReasonCode(null);
            l_unitParams.setMrfOrderRequestNumber(null);
            l_unitParams.setReturnMethod(null);

            //5)���I�u�W�F�N�g�ɐݒ肳��Ă���l��ݓ������P��Params�ɐݒ肷��B            
            l_unitParams.setDeliveryDate(this.getDeliveryDate());
            l_unitParams.setReceivedDateTime(this.getAcceptedDate());
            l_unitParams.setOrderRequestNumber(this.getRequestNumber());
            l_unitParams.setPaymentMethod(this.getPaymentMethod());
            l_unitParams.setRuitoType(this.getRuitoTypeEnum());
            l_unitParams.setGpSellDiv(this.getRuitoSellDiv());
            l_unitParams.setOrderChanel(this.getOrderChannel());
        }        
        catch (NotFoundException l_ex)
        {
               log.error("�Y������ڋq�I�u�W�F�N�g������܂���"); 
               throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        //6)�����ŗ^����ꂽ�ݓ������P��Params��Ԃ��B
        return l_unitParams;
    }

    /**
     * �imutate�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����ŗ^����ꂽ�ݓ���������Params�ɒl��ݒ肵�A<BR>
     *     �ݓ���������Params��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * �@@�|�ݓ���������Params.set�����G���[���R�R�[�h()���R�[�����A<BR>
     *         �����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * �@@�@@�mset�����G���[���R�R�[�h�ɓn���p�����^�n<BR>
     * �@@�@@�@@�����G���[���R�R�[�h�F null<BR>
     * <BR>
     * �Q�j�@@�����ŗ^����ꂽ�ݓ���������Params��Ԃ��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - <BR>
     *      �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������Ȃ�<BR>
     * @@param l_unitParams - �i�����O�̗ݓ���������Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 4091A0E20257
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_unitParams)
    {
        //1)�����G���[���R�R�[�h�̐ݒ���s���B        
        l_unitParams.setErrorReasonCode(null);
        //2)�����ŗ^����ꂽ�ݓ���������Params��Ԃ��B
        return l_unitParams;
    }

    /**
     * ��n���̐ݒ���s���B<BR>
     * @@param l_deliveryDate - ��n��<BR>
     * @@roseuid 408F3D5A0023
     */
    public void setDeliveryDate(Timestamp l_deliveryDate)
    {
        deliveryDate = l_deliveryDate;
    }

    /**
     * this.��n����Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 408F3D6802A3
     */
    public Timestamp getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * �󒍓����̐ݒ���s���B<BR>
     * @@param l_acceptedDate - �󒍓���<BR>
     * @@roseuid 408F3D770274
     */
    public void setAcceptedDate(Timestamp l_acceptedDate)
    {
        acceptedDate = l_acceptedDate;
    }

    /**
     * this.�󒍓�����Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 408F3D8C011D
     */
    public Timestamp getAcceptedDate()
    {
        return acceptedDate;
    }

    /**
     * �ݓ������P�ʂ̎��ʃR�[�h��ݒ肷��B<BR>
     * @@param l_strRequestNumber - �ݓ������P�ʂ̎��ʃR�[�h<BR>
     * @@roseuid 4091A0E200D0
     */
    public void setRequestNumber(String l_strRequestNumber)
    {
        requestNumber = l_strRequestNumber;
    }

    /**
     * this.���ʃR�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4091A0E200FF
     */
    public String getRequestNumber()
    {
        return requestNumber;
    }

    /**
     * ��n���@@�̐ݒ���s���B<BR>
     * @@param l_strPaymentMethod - ��n���@@<BR>
     * @@roseuid 4091A0E2015D
     */
    public void setPaymentMethod(String l_strPaymentMethod)
    {
        paymentMethod = l_strPaymentMethod;
    }

    /**
     * this.��n���@@��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4091A0E201DA
     */
    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * �ݓ��^�C�v�̐ݒ���s���B<BR>
     * @@param l_ruitoTypeEnum - �ݓ��^�C�v<BR>
     * @@roseuid 4091A0E2017C
     */
    public void setRuitoTypeEnum(RuitoTypeEnum l_ruitoTypeEnum)
    {
        ruitoTypeEnum = l_ruitoTypeEnum;
    }

    /**
     * this.�ݓ��^�C�v��Ԃ��B<BR>
     * @@return RuitoTypeEnum
     * @@roseuid 4091A0E201EA
     */
    public RuitoTypeEnum getRuitoTypeEnum()
    {
        return ruitoTypeEnum;
    }

    /**
     * �ݓ����敪�̐ݒ���s���B<BR>
     * @@param l_strRuitoSellDiv - �ݓ����敪<BR>
     * @@roseuid 4091A0E2018C
     */
    public void setRuitoSellDiv(String l_strRuitoSellDiv)
    {
        ruitoSellDiv = l_strRuitoSellDiv;
    }

    /**
     * this.�ݓ����敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4091A0E20209
     */
    public String getRuitoSellDiv()
    {
        return ruitoSellDiv;
    }

    /**
     * �����`���l���̐ݒ���s���B<BR>
     * @@param l_strOrderChannel - �����`���l��<BR>
     * @@roseuid 4091A0E20276
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        orderChannel = l_strOrderChannel;
    }

    /**
     * this.�����`���l����Ԃ��B<BR>
     * @@return String
     * @@roseuid 4091A0E20286
     */
    public String getOrderChannel()
    {
        return orderChannel;
    }

}
@
