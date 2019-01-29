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
filename	WEB3RuitoOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���ݓ������}�l�[�W���N���X(WEB3RuitoOrderManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ��O�� (���u) �V�K�쐬
                   2004/12/02 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoOrderManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3TradedDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �g���ݓ������}�l�[�W���N���X<BR>
 */
public class WEB3RuitoOrderManager extends RuitoOrderManagerImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3RuitoOrderManager.class);

    /**
     * �ivalidateNewOrder�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �ݓ����t�E��񒍕��̔����R�����s���B<BR>
     * <BR>
     * �P�j�@@����.is���t == true�̏ꍇ<BR>
     * �@@�|validate�V�K�����i���t�j()���R�[������B<BR>
     * �@@�@@�mvalidate�V�K�����i���t�j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�g���ݓ������F ����.�g���ݓ�����<BR>
     * �@@�@@�@@�������ʁF ����.��������<BR>
     * �@@�@@�@@�w����@@�F ����.�w����@@<BR>
     * �@@�|validate�V�K�����i���t�j()������I�������ꍇ�́A<BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�����R�����ʁF ProcessingResult.SUCCESS_RESULT<BR>
     * �@@�|validate�V�K�����i���t�j()����O���X���[�����ꍇ�́A<BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�����R�����ʁF <BR>
     *               ��O.getValidationResult().getProcessingResult()<BR>
     * <BR>
     * �Q�j�@@����.is���t == false�̏ꍇ<BR>
     * �@@�|validate�V�K�����i���j()���R�[������B<BR>
     * �@@�@@�mvalidate�V�K�����i���j�ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�g���ݓ������F ����.�g���ݓ�����<BR>
     * �@@�@@�@@�������ʁF ����.��������<BR>
     * �@@�@@�@@��n���@@�F ����.��n���@@<BR>
     * �@@�@@�@@�w����@@�F ����.�w����@@<BR>
     * �@@�|validate�V�K�����i���j()������I�������ꍇ�́A<BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�����R�����ʁF ProcessingResult.SUCCESS_RESULT<BR>
     * �@@�|validate�V�K�����i���j()����O���X���[�����ꍇ�́A<BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�����R�����ʁF <BR>
     *          ��O.getValidationResult().getProcessingResult()<BR>
     * @@param l_subAccount - �⏕����L<BR>
     * @@param l_expansionRuitoProduct - �g���ݓ�����<BR>
     * @@param l_orderQuantity - ��������<BR>
     * @@param l_isBuy - ���t�̏ꍇ��true�A���̏ꍇ��false���w�肷��B<BR>
     * @@param l_strPaymentMethod - ��n���@@<BR>
     * <BR>
     * ���̏ꍇ�̂ݎg�p�B<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * <BR>
     * ���̏ꍇ�̂ݎg�p�B<BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationRes
     * ult
     * @@roseuid 406B70F3011D
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct,
        double l_dbOrderQuantity,
        boolean isBuy,
        String l_strPaymentMethod,
        String l_strDesignateMethod)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateNewOrder(" +
            "SubAccount l_subAccount,WEB3RuitoProduct " +
            "l_ruitoProduct,double l_dbOrderQuantity,boolean isBuy," +
            "String l_strPaymentMethod,String l_strDesignateMethod)";
            
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_ruitoProduct == null)
        {
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //�P�j�@@����.is���t == true�̏ꍇ
            if (isBuy)
            {          
                //�|validate�V�K�����i���t�j()���R�[������B
                this.validateNewOrderBuy(
                    l_subAccount,
                    l_ruitoProduct,
                    l_dbOrderQuantity,
                    l_strDesignateMethod);
                //�|validate�V�K�����i���t�j()������I�������ꍇ�́A
                NewOrderValidationResult l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                return l_newOrderValidationResult;
            }
            // �Q�j�@@����.is���t == false�̏ꍇ
            else
            {
                log.debug("����.is���t == false�̏ꍇ");
                //�|validate�V�K�����i���j()���R�[������B
                this.validateNewOrderSell(
                    l_subAccount,
                    l_ruitoProduct,
                    l_dbOrderQuantity,
                    l_strPaymentMethod,
                    l_strDesignateMethod);
                //�|validate�V�K�����i���j()������I�������ꍇ�́A
                NewOrderValidationResult l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("l_newOrderValidationResult = " + l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
                return l_newOrderValidationResult;
            }
        }
        catch (OrderValidationException l_ex)
        {
            //�|validate�V�K����()����O���X���[�����ꍇ�́A
            NewOrderValidationResult l_newOrderValidationResult =
                new NewOrderValidationResult(
                    l_ex.getValidationResult().getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            return l_newOrderValidationResult;
        }
    }

    /**
     * �ivalidateCancelOrder�̎����j <BR>
     * <BR>
     * �V�[�P���X�}�u(�ݓ�)�����E���G���e�B�e�B�^(�ݓ�)�����R��(���)�v <BR>
     * <BR>
     * @@param l_subAccount - �⏕����ID<BR>
     * @@param l_cancelOrderSpec - �ݓ�����������e<BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 406B70F3016B
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec validateCancelOrder)
    {
        String STR_METHOD_NAME =
            "validateCancelOrder(SubAccount l_subAccount, " + "CancelOrderSpec l_cancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        NewOrderValidationResult l_newOrderValidationResult = null;
        try
        {
            if (l_subAccount == null || validateCancelOrder == null)
            {
                 throw new WEB3BaseException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + "." + STR_METHOD_NAME);
            }            
            
            //2. �ݓ������I�u�W�F�N�g���擾����B 
            OrderUnit[] l_orderUnits = 
                this.getOrderUnits(validateCancelOrder.getOrderId());
                
            RuitoOrderUnit l_ruitoOrderUnit = (RuitoOrderUnit)l_orderUnits[0];

            log.debug("l_ruitoOrderUnit.getOrderUnitId() = " + l_ruitoOrderUnit.getOrderUnitId());
            
            //3. �w�肳�ꂽ����������\���`�F�b�N����B
            WEB3RuitoOrderManagerReusableValidationsCheck l_orderManagerReusableValidationsCheck =
                new WEB3RuitoOrderManagerReusableValidationsCheck();

            l_orderManagerReusableValidationsCheck.validateCancelPossible(l_ruitoOrderUnit);

            // NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B           
            l_newOrderValidationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
        }

        catch (OrderValidationException l_ex)
        {
            //NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B
            l_newOrderValidationResult =
                new NewOrderValidationResult(
                    l_ex.getValidationResult().getProcessingResult());
            return l_newOrderValidationResult;
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in validateCancelOrder", l_ex);
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * validate�V�K�����i���t�j<BR>
     * <BR>
     * �V�[�P���X�}�u(�ݓ�)�����E���G���e�B�e�B/(�ݓ�)�����R��(���t)�v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_expansionRuitoProduct - �g���ݓ�����<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 407E4A4C008C
     */
    public void validateNewOrderBuy(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct,
        double l_dblOrderQuantity,
        String l_strDesignateMethod)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateNewOrderBuy(" +
            "SubAccount l_subAccount,WEB3RuitoProduct l_ruitoProduct," +
            "double l_dblOrderQuantity,String l_strDesignateMethod)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ruitoProduct == null)
        {
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 1.1�@@�w����@@�`�F�b�N         
        WEB3RuitoOrderManagerReusableValidationsCheck l_validCheck =
            new WEB3RuitoOrderManagerReusableValidationsCheck();
        l_validCheck.validateDesignateMethod(l_ruitoProduct, l_strDesignateMethod);

        // 1.2�@@����\�����`�F�b�N
        l_validCheck.validateTradedProduct(l_ruitoProduct, true);

        // 1.3�@@�ݐϓ��������`�F�b�N        
        l_validCheck.validateRuitoAccount(l_subAccount, l_ruitoProduct);

        // 1.4�@@���ꔭ���������`�F�b�N
        l_validCheck.validateSameOrderDateTrade(l_subAccount, 
            WEB3BuySellTypeDef.BUY, l_ruitoProduct);

        //1.5 �g���ݓ������}�l�[�W��( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        WEB3RuitoProductManager l_ruitoProductMgr = 
            (WEB3RuitoProductManager) l_tm.getProductManager();
        
        // 1.6�@@��n���擾
        Date l_datDelivertDate = 
            l_ruitoProductMgr.getDeliveryDate(
                    l_ruitoProduct.getInstitution(),
                    l_ruitoProduct.getProductCode(),
                    true);

        // 1.7�@@������z�`�F�b�N
        l_validCheck.validateTradedPrice(l_dblOrderQuantity, l_ruitoProduct, true);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate�V�K�����i���j<BR>
     * <BR>
     * �V�[�P���X�}�u(�ݓ�)�����E���G���e�B�e�B/(�ݓ�)�����R��(���)�v�Q�� <BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_expansionRuitoProduct - �g���ݓ�����<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_strPaymentMethod - ��n���@@<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException
     * @@roseuid 407E645903A9
     */
    public void validateNewOrderSell(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct,
        double l_dblOrderQuantity,
        String l_strPaymentMethod,
        String l_strDesignateMethod)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateNewOrderSell(" +
            "SubAccount l_subAccount,WEB3RuitoProduct l_ruitoProduct," +
            "double l_dblOrderQuantity,String l_strPaymentMethod," +
            "String l_strDesignateMethod)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_ruitoProduct == null)
        {
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3RuitoOrderManagerReusableValidationsCheck l_validCheck =
            new WEB3RuitoOrderManagerReusableValidationsCheck();
        
        //1.1�@@����\�����`�F�b�N
        l_validCheck.validateTradedProduct(l_ruitoProduct, false);

        //1.2�@@����.��n���@@�̒l���h1�F��s�U���h�̏ꍇ�A
        if (l_strPaymentMethod.equals(WEB3PaymentMethodDef.BANK_TRANSFER))
        {
            //1.2.1 ��s�U��������o�^�`�F�b�N���s���B
            l_validCheck.validateBankTransferAccountInsert(l_subAccount);
        }

        //1.3�@@����.�w����@@���h�S���h�̏ꍇ�A
        if (l_strDesignateMethod.equals(WEB3DesignateMethodDef.ALL))
        {
            log.debug("�w����@@���h�S���h�̏ꍇ");
            //1.3.1 ���ɉ�񒍕���������Ă��Ȃ����`�F�b�N����B
            l_validCheck.validateAllSell(l_subAccount, l_ruitoProduct);
        }
        //1.4�@@����.�w����@@���h�S���h�łȂ��ꍇ
        else if ((l_strDesignateMethod.equals(WEB3SellDivDef.MONEY_DESIGNATE)) || 
                (l_strDesignateMethod.equals(WEB3SellDivDef.COUNT_DESIGNATE)))
        {
			log.debug("�w����@@���h�S���h�łȂ��ꍇ");
			//1.4.1 ��������`�F�b�N���s���B
			l_validCheck.validateSameOrderDateSell(
				l_subAccount,
				l_ruitoProduct,
				l_strDesignateMethod);
				
            //1.4.2 ���\�c���`�F�b�N���s���B
            l_validCheck.validateSellPossibleBalance(
                l_subAccount,
                l_ruitoProduct,
                l_dblOrderQuantity);

            //1.4.3�@@������z�`�F�b�N<BR>
            l_validCheck.validateTradedPrice(l_dblOrderQuantity, l_ruitoProduct, false);
        }
        
		//1.5 �ݓ������R���ʃ`�F�b�N�i���ꔭ���������`�F�b�N�j
		String l_strTradedDiv = null;
		if (l_strDesignateMethod.equals(WEB3DesignateMethodDef.ALL))
		{
			l_strTradedDiv = WEB3TradedDivDef.ALL_SELL;
		}
		else if ((l_strDesignateMethod.equals(WEB3SellDivDef.MONEY_DESIGNATE)) || 
				(l_strDesignateMethod.equals(WEB3SellDivDef.COUNT_DESIGNATE)))
		{
			l_strTradedDiv = WEB3TradedDivDef.PARTIALLY_SELL;
		}
		l_validCheck.validateSameOrderDateTrade
				(l_subAccount, l_strTradedDiv, l_ruitoProduct);
				
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ݓ������P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�A�J�E���g�}�l�[�W��.getInstitution()���R�[�����A<BR>
     *      �،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�mgetInstitution�ɓn���p�����^�n<BR>
     * �@@�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j�@@���X�I�u�W�F�N�g���擾����B<BR>
     * �@@�A�J�E���g�}�l�[�W��.getBranch()���R�[�����āA<BR>
     *      ���X�I�u�W�F�N�g���擾����B<BR>
     * �@@�mgetBranch�ɓn���p�����^�n<BR>
     * �@@�@@�،���ЁF �擾�����،���ЃI�u�W�F�N�g<BR>
     * �@@�@@���X�R�[�h�F ����.���X�R�[�h<BR>
     * <BR>
     * �R�j�@@�ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�|�ȉ��̏����ŗݓ������P�ʃI�u�W�F�N�g���������A<BR>
     *      �ݓ������P��Params�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@���XID = �擾�������X�I�u�W�F�N�g.getBranchId()�̖߂�l AND<BR>
     * �@@�@@�@@���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �@@�|this.getOrderUnit()���R�[�����A<BR>
     *        �ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getOrderUnit�ɓn���p�����^]<BR>
     * �@@�@@�@@�����P��ID�F �ݓ������P��Params.getOrderUnitId()�̖߂�l<BR>
     * <BR>
     * �S�j�@@�擾�����ݓ������P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_strRequestNumber - ���ʃR�[�h<BR>
     * @@return RuitoOrderUnit
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 40851B8A0157
     */
    public RuitoOrderUnit getRuitoOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber)
        throws NotFoundException,WEB3BaseException
    {
        String STR_METHOD_NAME = "getRuitoOrderUnit(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        // 1)�،���ЃI�u�W�F�N�g���擾����B
        FinApp l_finApp;        
        l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        Institution l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
        log.debug("�،���ЃI�u�W�F�N�g���擾����B");
        log.debug("InstitutionCode =  " + l_institution.getInstitutionCode());
        
        // 2)���X�I�u�W�F�N�g���擾����B
        Branch l_branch = 
            l_accMgr.getBranch(l_institution, l_strBranchCode);
        log.debug("���X�I�u�W�F�N�g���擾����B");
        log.debug("BranchCode = " + l_branch.getBranchCode());
        
        RuitoOrderUnit l_ruitoOrderUnit = null;
        try
        {
            // 3)�ݓ������P�ʃI�u�W�F�N�g���擾����B
            String l_whereClause = 
                "branch_id = ? and order_request_number = ?";
            
            log.debug("branch_id = " + l_branch.getBranchId());
            log.debug("order_request_number = " + l_strRequestNumber);
            
            List l_lisRows = null;
            Object l_bindVars[] =
                { new Long(l_branch.getBranchId()), new String(l_strRequestNumber)};

            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);

			int l_intCount = l_lisRows.size();
			log.debug("l_intCount = " + l_intCount);
            if (l_intCount == 1)
            {
                RuitoOrderUnitParams[] l_ruitoOrderUnitParams = new RuitoOrderUnitParams[l_intCount];
                l_lisRows.toArray(l_ruitoOrderUnitParams);
                
                log.debug("l_lisRows.size = " + l_lisRows.size());            
                log.debug("l_ruitoOrderUnitParams[0].getOrderUnitId() = " + 
                    l_ruitoOrderUnitParams[0].getOrderUnitId());
    
                l_ruitoOrderUnit = (RuitoOrderUnit) this.getOrderUnit(
                        l_ruitoOrderUnitParams[0].getOrderUnitId());
            }
			else if (l_lisRows == null || l_intCount == 0)
			{
				log.debug("__�e�[�u���ɊY������f�[�^������܂���__");
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			else if (l_intCount > 1)
			{
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80004,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"�e�[�u���ɏd������Y���f�[�^�����݂��܂��B");
			}
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);

        }
        log.exiting(STR_METHOD_NAME);
        // 4)�擾�����ݓ������P�ʃI�u�W�F�N�g��Ԃ��B
        return l_ruitoOrderUnit;
    }

    /**
     * �ݓ������P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�|�ȉ��̏����ŗݓ������P�ʃI�u�W�F�N�g���������A<BR>
     *        �ݓ������P��Params�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@����ID = ����.����ID AND<BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����ID AND<BR>
     * �@@�@@�@@���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �@@�|this.getOrderUnit()���R�[�����A<BR>
     *          �ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getOrderUnit�ɓn���p�����^]<BR>
     * �@@�@@�@@�����P��ID�F �ݓ������P��Params.getOrderUnitId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�擾�����ݓ������P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_lngAccountID - ����ID<BR>
     * @@param l_lngSubAccountID - �⏕����ID<BR>
     * @@param l_strRequestNumber - ���ʃR�[�h<BR>
     * @@return RuitoOrderUnit
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 4085D54D018C
     */
    public RuitoOrderUnit getRuitoOrderUnit(
        long l_lngAccountID,
        long l_lngSubAccountID,
        String l_strRequestNumber)
        throws NotFoundException,WEB3BaseException
    {
        String STR_METHOD_NAME = "getRuitoOrderUnit(" +
            "long l_lngAccountID, long l_lngSubAccountID, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        RuitoOrderUnit l_ruitoOrderUnit = null;
        try
        {
            // 1)�ݓ������P�ʃI�u�W�F�N�g���擾����B 
            String l_whereClause =
                "account_id = ? and sub_account_id = ? " + 
                "and order_request_number = ?";
            Object l_bindVars[] =
                {
                    new Long(l_lngAccountID),
                    new Long(l_lngSubAccountID),
                    new String(l_strRequestNumber)};
                    
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);
                    
            int l_intCount = l_lisRows.size();
            log.debug("l_intCount = " + l_intCount);
            if (l_intCount == 1)
            {
                RuitoOrderUnitParams[] l_orderUnitParams = new RuitoOrderUnitParams[l_intCount];
                l_lisRows.toArray(l_orderUnitParams);
                l_ruitoOrderUnit =
                    (RuitoOrderUnit) this.getOrderUnit
                    (l_orderUnitParams[0].getOrderUnitId());
            }
			else if (l_lisRows == null || l_intCount == 0)
			{
				log.debug("__�e�[�u���ɊY������f�[�^������܂���__");
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			else if (l_intCount > 1)
			{
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80004,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"�e�[�u���ɏd������Y���f�[�^�����݂��܂��B");
			}
        }        
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);         
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);   
        }
        // 2)�擾�����ݓ������P�ʃI�u�W�F�N�g��Ԃ��B           
        log.exiting(STR_METHOD_NAME);
        return l_ruitoOrderUnit;
    }

    /**
     * (get�ݓ������P�ʈꗗ)<BR>
     * <BR>
     * �w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * (getOrderUnits�̃I�[�o�[���[�h)<BR>
     * <BR>
     * �P)�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q)�@@���������𐶐�����B<BR>
     * �@@�Q�|�P)�@@"account_id = ?�@@<BR>
     *               and�@@sub_account_id = ?<BR>
     *               and product_type = ? <BR>
     *               and biz_date >= ? and<BR>
     * �@@�@@�@@�@@�@@�@@  (order_type = ? or order_type = ?)<BR>
     *                and (ruito_type = ?<BR>
     *                 or ruito_type = ?)"<BR>
     * �@@�@@�@@�@@�Ō���������������쐬����B<BR>
     * <BR>
     * �@@�Q�|�Q)�@@�ȉ��̃p�����[�^�Ō��������f�[�^�R���e�i���쐬����B<BR>
     * �@@�@@�@@�@@1.����ID(�⏕�����I�u�W�F�N�g.getAccountId( )�Ŏ擾��)<BR>
     * �@@�@@�@@�@@2.�⏕����ID(�⏕�����I�u�W�F�N�g.getSubAccountId( )�Ŏ擾��)<BR>
     * �@@�@@�@@�@@3.ProductTypeEnum.RUITO(�ݐϓ���)<BR>
     * �@@�@@�@@�@@4.�����i�Ɩ����t�j���w��(*1)<BR>
     * �@@�@@�@@�@@5.OrderTypeEnum.RUITO_BUY(�ݓ�������)<BR>
     * �@@�@@�@@�@@6.OrderTypeEnum.RUITO_SELL(�ݓ�������)<BR>
     * �@@�@@�@@�@@7.RuitoTypeEnum.�������t�@@���h<BR>
     * �@@�@@�@@�@@8.RuitoTypeEnum.MMF<BR>
     * <BR>
     * �R)�@@QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     *        �����P�ʃI�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery( �ݓ������P��Row.TYPE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�|�P)�̌�������������,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����̃\�[�g����,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@null,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�|�Q)�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     * �S)�@@�������ʂ�ԋp����B<BR>
     * <BR>
     * (*1)�@@�Ɩ����t�̎擾<BR>
     * �@@TradingSystem.getBizDate()�ɂĎ擾�����Ɩ�(�o�b�`)���t�̖߂�l<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strSortCond - �ݓ��\�[�g�L�[�I�u�W�F�N�g<BR>
     * @@return List
     * @@roseuid 40865F090048
     */
    public List getOrderUnits(SubAccount l_subAccount, String l_strSortCond)
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getOrderUnits(" +
            "SubAccount l_subAccount, String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���������𐶐�����B
        List l_lisRows = null;
        List l_lisReturnOrderUnit = null;
        String l_strWhereClause;
        l_strWhereClause =
            "account_id = ? AND sub_account_id = ? AND "
                + "product_type = ? AND biz_date >= ? AND "
                + "(order_type = ? OR order_type = ?) AND "
                + "(ruito_type = ? OR ruito_type = ?)";
        try
        {
			//(*1)�@@�Ɩ����t�̎擾   
			//TradingSystem.getBizDate()�ɂĎ擾�����Ɩ�(�o�b�`)���t�̖߂�l
			Date l_dateBizDate =
				GtlUtils.getTradingSystem().getBizDate();

            String l_strBizDate = 
                WEB3DateUtility.formatDate(l_dateBizDate, "yyyyMMdd");
            
            log.debug("�Ɩ����t: l_strBizDate = " + l_strBizDate);
            
            long l_lngAccount = l_subAccount.getAccountId();
            long l_lngSubAccount = l_subAccount.getSubAccountId();
            
            Object[] l_bindValues = new Object[8];
            l_bindValues[0] = new Long(l_lngAccount);
            l_bindValues[1] = new Long(l_lngSubAccount);
            l_bindValues[2] = ProductTypeEnum.RUITO;
            l_bindValues[3] = l_strBizDate;
            l_bindValues[4] = OrderTypeEnum.RUITO_BUY;
            l_bindValues[5] = OrderTypeEnum.RUITO_SELL;
            l_bindValues[6] = RuitoTypeEnum.CHUUKOKU_FUND;
            l_bindValues[7] = RuitoTypeEnum.MMF;

            // �p�����[�^�Ō��������f�[�^�R���e�i���쐬����B              
            log.debug("account_id = " + l_lngAccount + 
                " AND sub_account_id = " + l_lngSubAccount + 
                " AND product_type = " + ProductTypeEnum.RUITO.intValue() +
                " AND biz_date >= " + l_strBizDate +
                " AND (order_type = " + OrderTypeEnum.RUITO_BUY +
                " OR order_type = " + OrderTypeEnum.RUITO_SELL + ") AND " +
                "(ruito_type = " + RuitoTypeEnum.CHUUKOKU_FUND + 
                " OR ruito_type = " + RuitoTypeEnum.MMF + ")");
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_strSortCond,
                    null,
                    l_bindValues);
            
            //�����P�ʃI�u�W�F�N�g
            int l_intCount = l_lisRows.size();
            RuitoOrderUnitParams[] l_ruitoOrderUnitParams = 
                    new RuitoOrderUnitParams[l_intCount];
            l_lisRows.toArray(l_ruitoOrderUnitParams);  
             
            l_lisReturnOrderUnit = new ArrayList();
            log.debug("order unit count = " + l_intCount);
            
            for (int i = 0; i < l_intCount; i++)
            {
                log.debug("OrderUnitId = " + 
                    l_ruitoOrderUnitParams[i].getOrderUnitId());
               
                l_lisReturnOrderUnit.add(
                    this.getOrderUnit(l_ruitoOrderUnitParams[i].getOrderUnitId()));
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnOrderUnit;
    }
}
@
