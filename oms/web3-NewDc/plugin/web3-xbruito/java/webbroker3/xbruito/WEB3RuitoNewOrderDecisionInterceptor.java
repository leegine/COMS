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
filename	WEB3RuitoNewOrderDecisionInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ��V�K�����m��C���^�Z�v�^(WEB3RuitoNewOrderDecisionInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;

import java.util.Date;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.define.WEB3BalanceAddFlagDef;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;


/**
 * �ݓ��V�K�����m��C���^�Z�v�^<BR>
 */
public class WEB3RuitoNewOrderDecisionInterceptor
    extends WEB3RuitoDefaultRuitoOrderDecisionInterceptor
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3RuitoMRFCancelAcceptUnitServiceInterceptor.class);

    /**
     * ���ʃR�[�h<BR>
     */
    private String requestNumber;

    /**
     * �ݓ��̔��t��MRF�̎��������s���ꍇ�A�Ή����钍���̎��ʃR�[�h��ݒ肷��B<BR>
     */
    private String MRFOrderRequestNumber;

    /**
     * �Ԋҕ��@@<BR>
     * 1:�������@@3:�L���b�V���O(�쑺MMF�̎�)<BR>
     */
    private String returnMethod;

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
     * 1�F�R�[���Z���^�[�@@2�F�o�b�@@3:�X�����O�V���b�g<BR>
     * 4�Fi-mode�@@5�FVodafone�@@6�FAU�@@9�FHOST<BR>
     * �i�R�[���Z���^�[�̎��̂ݎg�p�j<BR>
     */
    private String orderRootDiv;

    /**
     * 0�F�c�ƓX�@@1�F�C���^�[�l�b�g�@@2�F�R�[���Z���^�@@3�F���o�C��<BR>
     */
    private String orderChannel;

    /**
     * �imutate�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����ŗ^����ꂽ�ݓ������P��Params�ɒl��ݒ肵�A<BR>
     *        �ݓ������P��Params��Ԃ��B<BR>
     * <BR>
     * �P�j�@@��n���̐ݒ���s��<BR>
     * �@@�|�ݓ������P��Params.getBranchId()�ɊY������<BR>
     *       �،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�|�g���ݓ������}�l�[�W��.getProduct()���R�[�����A<BR>
     *      �����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetProduct�ɓn���p�����^�n<BR>
     * �@@�@@�@@����ID�F �ݓ������P��Params.getProductId()�̖߂�l<BR>
     * �@@�|�g���ݓ������}�l�[�W��.toProduct()���R�[�����āA<BR>
     *          �g���ݓ������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mtoProduct�ɓn���p�����^�n<BR>
     * �@@�@@�@@����Row�F �擾���������I�u�W�F�N�g.getDataSourceObject()<BR>
     *       �̖߂�l<BR>
     * �@@�|�g���ݓ������}�l�[�W��.get�ݓ��������()���R�[�����A<BR>
     *         �g���ݓ���������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mget�ݓ���������ɓn���p�����^�n<BR>
     * �@@�@@�@@�،���ЁF �擾�����،���ЃI�u�W�F�N�g<BR>
     * �@@�@@�@@�����R�[�h�F <BR>
     *        �擾�����g���ݓ������I�u�W�F�N�g.getProductCode()�̖߂�l<BR>
     * �@@�|�g���ݓ��������.get��n��()���R�[�����A��n�����擾����B<BR>
     * �@@�@@�mget��n���ɓn���p�����^�n<BR>
     * �@@�@@�@@is���t�F <BR>
     * �@@�@@�@@�@@(*) �ݓ������P��Params.getOrderType()�̖߂�l��<BR>
     * OrderTypeEnum.RUITO_BUY�̏ꍇ��true��ݒ肷��B<BR>
     * �@@�@@�@@�@@(*) �ݓ������P��Params.getOrderType()�̖߂�l��<BR>
     * OrderTypeEnum.RUITO_SELL�̏ꍇ��false��ݒ肷��B<BR>
     * �@@�|�ݓ������P��Params.setDeliveryDate()���R�[�����A<BR>
     * ��n����ݒ肷��B<BR>
     * �@@�@@�msetDeliveryDate�ɓn���p�����^�n<BR>
     * �@@�@@�@@��n���F �擾������n��<BR>
     * <BR>
     * �Q�j�@@�󒍓����̐ݒ���s���B<BR>
     * �@@�|ThreadLocalSystemAttributesRegistry.getAttribute( )���R�[�����A<BR>
     *      �����������擾����B<BR>
     * �@@�@@�mgetAttribute�ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݒ�L�[�F �hxblocks.gtl.attributes.systemtimestamp�h<BR>
     * �@@�|�ݓ������P��Params.setReceivedDateTime()���R�[�����A<BR>
     *      �󒍓����̐ݒ���s���B<BR>
     * �@@�@@�msetReceivedDateTime�ɓn���p�����^�n<BR>
     * �@@�@@�@@�󒍓����F �擾������������<BR>
     * <BR>
     * �R�j�@@���҃R�[�h(SONAR)�̐ݒ���s���B<BR>
     * �@@�|�ݓ������P��Params.getAccountId()��<BR>
     *      �Ή�����ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�|�ݓ������P��Params.setSonarTraderCode()���R�[�����A<BR>
     *      ���҃R�[�h(SONAR)�̐ݒ���s���B<BR>
     * �@@�@@�msetSonarTraderCode�ɓn���p�����^�n<BR>
     * �@@�@@�@@���҃R�[�h(SONAR)�F <BR>
     *      �ڋq.getDataSourceObject().getSonarTraderCode()<BR>
     *          �̖߂�l<BR>
     * �S�j�@@�����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * �@@�|�ݓ������P��Params.set�����G���[���R�R�[�h()���R�[�����A<BR>
     *       �����G���[���R�R�[�h�̐ݒ���s���B<BR>
     * �@@�@@�mset�����G���[���R�R�[�h�ɓn���p�����^�n<BR>
     * �@@�@@�@@�����G���[���R�R�[�h�F null<BR>
     * <BR>
     * �T�j�@@�c���v��t���O�̐ݒ���s���B<BR>
     * �@@�|�ݓ������P��Params.set�c���v��t���O()���R�[�����A<BR>
     *      �c���v��t���O�̐ݒ���s���B<BR>
     * �@@�@@[set�c���v��t���O�ɓn���p�����^]<BR>
     * �@@�@@�@@�c���v��t���O�F �h0�F�c���v�㖢�ρh<BR>
     * <BR>
     * �U�j�@@���I�u�W�F�N�g�ɐݒ肳��Ă���l��<BR>
     *     �ݓ������P��Params�ɐݒ肷��B<BR>
     * <BR>
     * �@@�@@�ݓ������P��Params.setOrderRequestNumber<BR>
     *          (this.get���ʃR�[�h())<BR>
     * �@@�@@�ݓ������P��Params.setPaymentMethod(this.get��n���@@)<BR>
     * �@@�@@�ݓ������P��Params.setMrfOrderRequestNumber<BR>
     *              (this.getMRF���ʃR�[�h())<BR>
     * �@@�@@�ݓ������P��Params.setRuitoType(this.get�ݓ��^�C�v())<BR>
     * �@@�@@�ݓ������P��Params.setGpSellDiv(this.get�ݓ����敪())<BR>
     * �@@�@@�ݓ������P��Params.setReturnMethod(this.get�Ԋҕ��@@())<BR>
     * �@@�@@�ݓ������P��Params.setOrderRootDiv(this.get�����o�H�敪())<BR>
     * �@@�@@�ݓ������P��Params.setOrderChanel(this.get�����`���l��())<BR>
     * <BR>
     * �V�j�@@�����ŗ^����ꂽ�ݓ������P��Params��Ԃ��B<BR>
     * @@param l_persistenceType - �f�[�^�̍X�V�܂��͑}�����ɌĂԂ��ǂ����w��<BR>
     * @@param l_persistenceContext - 
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������Ȃ�<BR>
     * @@param l_unitParams - �i�����O�̗ݓ������P��Params<BR>
     * @@return RuitoOrderUnitParams
     * @@roseuid 4070B277028B
     */
    public RuitoOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderUnitParams l_unitParams)
    {
        String STR_METHOD_NAME =
            "mutate("
                + "OrderManagerPersistenceType l_persistenceType,"
                + "OrderManagerPersistenceContext l_persistenceContext,"
                + "RuitoOrderUnitParams l_unitParams)";

        WEB3GentradeBranch l_branch = null;
        Institution l_institution = null;
        RuitoOrderUnitParams l_orderUnitParams = null;
        l_orderUnitParams = l_unitParams;

        try
        {
            if (l_unitParams == null)
            {
                // --------- Start
                // Modify by Alan Wang 2004/08/13 according to �\�[�X�R�[�h�`�F�b�N�w�E����(Ruito 20040802�Ńx�[�X).xls NO.8
//                throw new WEB3BaseException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
//                    this.getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
                // ------------ End
            }
            //FinApp�T�[�r�X���擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gAccMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            //�،���Ў擾 NotFoundException
            l_branch = (WEB3GentradeBranch) l_gAccMgr.getBranch(
                l_orderUnitParams.getBranchId());
            l_institution = l_branch.getInstitution();

            //�����I�u�W�F�N�g���擾����B 
            WEB3RuitoProductManager l_ruitoProductManager =
                (WEB3RuitoProductManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getProductManager();
            
            //NotFoundException
            WEB3RuitoProduct l_ruitoProduct = (WEB3RuitoProduct)
                l_ruitoProductManager.getProduct(l_orderUnitParams.getProductId());

//            //�|�g���ݓ������}�l�[�W��.toProduct()���R�[������
//            WEB3RuitoProduct l_ruitoProduct = null;
//            l_ruitoProduct =
//                (WEB3RuitoProduct) l_ruitoProductManager.toProduct(
//                    (Row) l_product.getDataSourceObject());

            //�|�g���ݓ������}�l�[�W��.get�ݓ��������()���R�[����
            WEB3RuitoTradedProduct l_ruitoTradeProduct = null;            
            l_ruitoTradeProduct =
                (WEB3RuitoTradedProduct) l_ruitoProductManager.getRuitoTradedProduct(
                l_institution,
                l_ruitoProduct.getProductCode());

            //�|�g���ݓ��������.get��n��()���R�[�����A��n�����擾����B            
            boolean isBuy = false;
            if (OrderTypeEnum.RUITO_BUY.equals(l_orderUnitParams.getOrderType()))
            {
                isBuy = true;
            }
            if (OrderTypeEnum.RUITO_SELL.equals(l_orderUnitParams.getOrderType()))
            {
                isBuy = false;
            }

            Date l_datDeliveryDate = l_ruitoTradeProduct.getDailyDeliveryDate(isBuy);
            l_orderUnitParams.setDeliveryDate(l_datDeliveryDate);

            //�Q�j�@@�󒍓����̐ݒ���s���B
            String l_strSystemTs = null;
            l_strSystemTs = "xblocks.gtl.attributes.systemtimestamp";
            l_datDeliveryDate =
                (Date) ThreadLocalSystemAttributesRegistry.getAttribute(l_strSystemTs);
            l_orderUnitParams.setReceivedDateTime(l_datDeliveryDate);

            // �R�j�@@���҃R�[�h(SONAR)�̐ݒ���s���B
            String l_sonarTraderCode;

            //�ڋq�I�u�W�F�N�g���擾����
            MainAccount l_acc = null;

            AccountManager l_accMgr = l_finApp.getAccountManager();
            l_acc = l_accMgr.getMainAccount(l_orderUnitParams.getAccountId());
            MainAccountRow l_accRow = (MainAccountRow) l_acc.getDataSourceObject();
            l_sonarTraderCode = l_accRow.getSonarTraderCode();
            l_orderUnitParams.setSonarTraderCode(l_sonarTraderCode);

            //�S�j�@@�����G���[���R�R�[�h�̐ݒ���s���B
            l_orderUnitParams.setErrorReasonCode(null);

            //�T�j�@@�c���v��t���O�̐ݒ���s���B
            l_orderUnitParams.setBalanceAddFlag(
                WEB3BalanceAddFlagDef.BALANCE_OUTSTANDING);

            //�U�j�@@���I�u�W�F�N�g�ɐݒ肳��Ă���l��ݓ������P��Params�ɐݒ肷��B
            l_orderUnitParams.setOrderRequestNumber(this.getRequestNumber());
            l_orderUnitParams.setPaymentMethod(this.getPaymentMethod());
            l_orderUnitParams.setMrfOrderRequestNumber(this.MRFOrderRequestNumber);
            l_orderUnitParams.setRuitoType(this.getRuitoTypeEnum());
            l_orderUnitParams.setGpSellDiv(this.getRuitoSellDiv());
            l_orderUnitParams.setReturnMethod(this.getReturnMethod());
            l_orderUnitParams.setOrderRootDiv(this.getOrderRootDiv());
            l_orderUnitParams.setOrderChanel(this.getOrderChannel());

        }
        catch (NotFoundException l_ex)
        {         
         // --------- Start
            // Modify by Alan Wang 2004/08/13 according to �\�[�X�R�[�h�`�F�b�N�w�E����(Ruito 20040802�Ńx�[�X).xls No.8
//            log.error(
//                "__NotFoundException__",
//                new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                    this.getClass().getName() + STR_METHOD_NAME,
//                    l_ex.getMessage(),
//                    l_ex));

            log.error("�Y������،���Ђ܂��͖����I�u�W�F�N�g�܂���" +
                    "�ݓ���������܂��͌ڋq�I�u�W�F�N�g������܂���"); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            // ------------ End
        }
        // --------- Start
        // Modify by Alan Wang 2004/08/13 according to �\�[�X�R�[�h�`�F�b�N�w�E����(Ruito 20040802�Ńx�[�X).xls No.8
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(
//                "__parameter_error__",
//                new WEB3SystemLayerException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
//                    this.getClass().getName() + STR_METHOD_NAME,
//                    l_ex.getMessage(),
//                    l_ex));
//        }
        // ------------ End

        //�V�j�@@�����ŗ^����ꂽ�ݓ������P��Params��Ԃ��B
        return l_orderUnitParams;
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
     * @@param l_persistenceContext - 
     * �Ăяo�����̃R���e�L�X�g���w��B�Ⴆ�Ό����������Ȃ�<BR>
     * @@param l_actionParams - �i�����O�̗ݓ���������Params<BR>
     * @@return RuitoOrderActionParams
     * @@roseuid 408CAA44000A
     */
    public RuitoOrderActionParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        RuitoOrderActionParams l_actionParams)
    {
        //�P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B
        //RuitoOrderActionParams l_ruitoOrderActionParams = null;
        //l_ruitoOrderActionParams.setErrorReasonCode(null);

        //�Q�j�@@�����ŗ^����ꂽ�ݓ���������Params��Ԃ��B        
        //return l_ruitoOrderActionParams;
        
        //�P�j�@@�����G���[���R�R�[�h�̐ݒ���s���B
        l_actionParams.setErrorReasonCode(null);
        //�Q�j�@@�����ŗ^����ꂽ�ݓ���������Params��Ԃ��B        
        return l_actionParams;
    }

    /**
     * �ݓ������P�ʂ̎��ʃR�[�h��ݒ肷��B<BR>
     * @@param l_strRequestNumber - �ݓ������P�ʂ̎��ʃR�[�h<BR>
     * @@roseuid 4070B4A40308
     */
    public void setRequestNumber(String l_strRequestNumber)
    {
        requestNumber = l_strRequestNumber;
    }

    /**
     * this.���ʃR�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4070B6290318
     */
    public String getRequestNumber()
    {
        return requestNumber;
    }

    /**
     * MRF�������ʃR�[�h��ݒ肷��B<BR>
     * �ݓ��̔��t������MRF�̎��������s���ꍇ�A<BR>
     * �Ή����鎯�ʃR�[�h��ݒ肷��B<BR>
     * @@param l_strMRFRequestNumber - MRF���ʃR�[�h<BR>
     * @@roseuid 4070B4B201A1
     */
    public void setMRFOrderRequestNumber(String l_strMRFRequestNumber)
    {
        MRFOrderRequestNumber = l_strMRFRequestNumber;
    }

    /**
     * this.MRF���ʃR�[�h��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4070B6390114
     */
    public String getMRFOrderRequestNumber()
    {
        return MRFOrderRequestNumber;
    }

    /**
     * �Ԋҕ��@@�̐ݒ���s���B<BR>
     * @@param l_strReturnMethod - �Ԋҕ��@@<BR>
     * @@roseuid 40767684000F
     */
    public void setReturnMethod(String l_strReturnMethod)
    {
        returnMethod = l_strReturnMethod;
    }

    /**
     * this.�Ԋҕ��@@��Ԃ��B<BR>
     * @@return String
     * @@roseuid 407676DF0203
     */
    public String getReturnMethod()
    {
        return returnMethod;
    }

    /**
     * ��n���@@�̐ݒ���s���B<BR>
     * @@param l_strPaymentMethod - ��n���@@<BR>
     * @@roseuid 407A4A4A00BB
     */
    public void setPaymentMethod(String l_strPaymentMethod)
    {
        paymentMethod = l_strPaymentMethod;
    }

    /**
     * this.��n���@@��Ԃ��B<BR>
     * @@return String
     * @@roseuid 407A4B9D0157
     */
    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * �ݓ��^�C�v�̐ݒ���s���B<BR>
     * @@param ruitoType - �ݓ��^�C�v<BR>
     * @@roseuid 407A4AF3033C
     */
    public void setRuitoTypeEnum(RuitoTypeEnum l_ruitoTypeEnum)
    {
        ruitoTypeEnum = l_ruitoTypeEnum;
    }

    /**
     * this.�ݓ��^�C�v��Ԃ��B<BR>
     * @@return RuitoTypeEnum
     * @@roseuid 407A4BAA01E4
     */
    public RuitoTypeEnum getRuitoTypeEnum()
    {
        return ruitoTypeEnum;
    }

    /**
     * �ݓ����敪�̐ݒ���s���B<BR>
     * @@param l_strRuitoSellDiv - �ݓ����敪<BR>
     * @@roseuid 407A4B04003E
     */
    public void setRuitoSellDiv(String l_strRuitoSellDiv)
    {
        ruitoSellDiv = l_strRuitoSellDiv;
    }

    /**
     * this.�ݓ����敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 407A4BB90157
     */
    public String getRuitoSellDiv()
    {
        return ruitoSellDiv;
    }

    /**
     * �����o�H�敪�̐ݒ���s���B<BR>
     * @@param l_strOrderRootDiv - �����o�H�敪<BR>
     * @@roseuid 407C90A40186
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        orderRootDiv = l_strOrderRootDiv;
    }

    /**
     * this.�����o�H�敪��Ԃ��B<BR>
     * @@return String
     * @@roseuid 407C90D30119
     */
    public String getOrderRootDiv()
    {
        return orderRootDiv;
    }

    /**
     * �����`���l���̐ݒ���s���B<BR>
     * @@param l_strOrderChannel - �����`���l��<BR>
     * @@roseuid 408CD3AB01B0
     */
    public void setOrderChannel(String l_strOrderChannel)
    {
        orderChannel = l_strOrderChannel;
    }

    /**
     * this.�����`���l����Ԃ��B<BR>
     * @@return String
     * @@roseuid 408CD3B203B4
     */
    public String getOrderChannel()
    {
        return orderChannel;
    }

//    /**
//     * @@param l_persistenceType
//     * @@param l_persistenceContext
//     * @@param l_executionParams
//     * @@return RuitoOrderExecutionParams
//     * @@roseuid 40C019F102CA
//     */
//    /*public RuitoOrderExecutionParams setUpdatePrice(
//        OrderManagerPersistenceType l_persistenceType,
//        OrderManagerPersistenceContext l_persistenceContext,
//        RuitoOrderExecutionParams l_executionParams)
//    {
//        return null;
//    }*/
//
//    /**
//     * @@param l_persistenceType
//     * @@param l_tableRow
//     * @@return com.fitechlabs.xtrade.kernel.data.BatchedQuery
//     * @@roseuid 40C019F201DB
//     */
//    /*public BatchedQuery getQueryToExecute(
//        OrderManagerPersistenceType l_persistenceType,
//        Class l_tableRow)
//    {
//        return null;
//    }*/
//
//    /**
//     * @@param arg0
//     * @@param arg1
//     * @@param arg2
//     * @@return RuitoOrderUnitParams
//     * @@roseuid 40C473EE02FD
//     */
//    /*public RuitoOrderUnitParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderUnitParams arg2)
//    {
//        return null;
//    }*/
//
//    /**
//     * @@param arg0
//     * @@param arg1
//     * @@param arg2
//     * @@return RuitoOrderExecutionParams
//     * @@roseuid 40C473F000EA
//     */
//    /*public RuitoOrderExecutionParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderExecutionParams arg2)
//    {
//        return null;
//    }*/
//
//    /**
//     * @@param arg0
//     * @@param arg1
//     * @@param arg2
//     * @@return RuitoOrderActionParams
//     * @@roseuid 40C473F102AF
//     */
//    /*public RuitoOrderActionParams mutate(
//        OrderManagerPersistenceType arg0,
//        OrderManagerPersistenceContext arg1,
//        RuitoOrderActionParams arg2)
//    {
//        return null;
//    }*/
}
@
