head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����M�����}�l�[�W���N���X(WEB3MutualFundOrderManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 ��O�� (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2004/12/06 ������ (���u) �c�Ή�
Revesion History : 2005/10/18 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/03/08 �ʉ� (SRA) �d�l�ύX(���f��)�F400
Revesion History : 2006/06/19 ��� (SRA) �{�ԏ�QH00126�Ή�                        
Revesion History : 2006/06/26 ���� (���u) �d�l�ύX(���f��)�F419  
Revesion History : 2006/09/11 ���� �d�l�ύX�E���f��488
Revesion History : 2007/01/05 ������ (���u)�@@���f��530
Revesion History : 2007/04/09 ��іQ (���u) ���f�� 554,561
Revesion History : 2007/10/15 ���^�] (���u) ���f�� 581
*/

package webbroker3.mf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrder;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3ExemptionDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mf.data.MfExemptionAccountDao;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �g�����M�����}�l�[�W���N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundOrderManager extends MutualFundOrderManagerImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundOrderManager.class);
    /**
     * (validate�V�K����)<BR>
     * �ivalidateNewOrder�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ���M���t�E��񒍕��̔����R�����s���B<BR>
     * <BR>
     * �P�j�@@����.�����敪�̒l���h1�F���t�h�̏ꍇ <BR>
     * �@@�|validate�V�K�����i���t�j()���R�[������B <BR>
     * �@@�@@�mvalidate�V�K�����i���t�j�ɓn���p�����^�n <BR>
     * �@@�@@�@@�⏕�����F ����.�⏕���� <BR>
     * �@@�@@�@@�g�����M�����F ����.�g�����M���� <BR>
     * �@@�@@�@@�������ʁF ����.�������� <BR>
     *       �����敪�F ����.������<BR>
     * �@@�@@�@@�w����@@�F ����.�w����@@<BR>
     * �@@�@@�@@���ϕ��@@�F ����.���ϕ��@@<BR>
     * �@@�|validate�V�K�����i���t�j()������I�������ꍇ�́A <BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n <BR>
     * �@@�@@�@@�@@�����R�����ʁF ProcessingResult.SUCCESS_RESULT <BR>
     * �@@�|validate�V�K�����i���t�j()����O���X���[�����ꍇ�́A <BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n <BR>
     * �@@�@@�@@�@@�����R�����ʁF ��O.getValidationResult().getProcessingResult() <BR>
     * <BR>
     * �Q�j�@@����.�����敪�̒l���h2�F���h�܂��́h4�F����h�ň���.�抷�������null�̏ꍇ <BR>
     * �@@�|validate�V�K�����i���j()���R�[������B <BR>
     * �@@�@@�mvalidate�V�K�����i���j�ɓn���p�����^�n <BR>
     * �@@�@@�@@�⏕�����F ����.�⏕���� <BR>
     * �@@�@@�@@�g�����M�����F ����.�g�����M���� <BR>
     * �@@�@@�@@�������ʁF ����.�������� <BR>
     * �@@�@@�@@�����敪�F ����.�����敪<BR>
     * �@@�@@�@@��n���@@�F ����.��n���@@ <BR>
     * �@@�@@�@@�w����@@�F ����.�w����@@ <BR>
     *       �ŋ敪�F ����.�ŋ敪<BR>
     * �@@�@@�@@���ϕ��@@�F ����.���ϕ��@@<BR>
     * �@@�|validate�V�K�����i���j()������I�������ꍇ�́A <BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n <BR>
     * �@@�@@�@@�@@�����R�����ʁF ProcessingResult.SUCCESS_RESULT <BR>
     * �@@�|validate�V�K�����i���j()����O���X���[�����ꍇ�́A <BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n <BR>
     * �@@�@@�@@�@@�����R�����ʁF ��O.getValidationResult().getProcessingResult() <BR>
     * <BR>
     * �R�j�@@����.�����敪�̒l���h2�F���h�܂��́h4�F����h�ň���.�抷�������null�łȂ��ꍇ <BR>
     * �@@�|validate�V�K�����i�抷�j()���R�[������B <BR>
     * �@@�@@�mvalidate�V�K�����i�抷�j�ɓn���p�����^�n <BR>
     * �@@�@@�@@�⏕�����F ����.�⏕���� <BR>
     * �@@�@@�@@�g�����M�����F ����.�g�����M���� <BR>
     * �@@�@@�@@�������ʁF ����.�������� <BR>
     * �@@�@@�@@�����敪�F ����.�����敪<BR>
     * �@@�@@�@@�w����@@�F ����.�w����@@<BR>
     * �@@�@@�@@�抷������F ����.�抷�����<BR>
     *       �ŋ敪�F ����.�ŋ敪 <BR>
     * �@@�|validate�V�K�����i�抷�j()������I�������ꍇ�́A <BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n <BR>
     * �@@�@@�@@�@@�����R�����ʁF ProcessingResult.SUCCESS_RESULT <BR>
     * �@@�|validate�V�K�����i�抷�j()����O���X���[�����ꍇ�́A <BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n <BR>
     * �@@�@@�@@�@@�����R�����ʁF ��O.getValidationResult().getProcessingResult() <BR>
     * <BR>
     * �S�j�@@����.�����敪�̒l���h5�F��W�h�̏ꍇ <BR>
     * �@@�|validate�V�K�����i��W�j()���R�[������B  <BR>
     * �@@�@@�mvalidate�V�K�����i��W�j�ɓn���p�����^�n  <BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����  <BR>
     * �@@�@@�@@�g�����M�����F ����.�g�����M����  <BR>
     * �@@�@@�@@�������ʁF ����.��������  <BR>
     *       �����敪�F ����.�����敪 <BR>
     * �@@�@@�@@�w����@@�F ����.�w����@@ <BR>
     * �@@�|validate�V�K�����i��W�j()������I�������ꍇ�́A  <BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B  <BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n  <BR>
     * �@@�@@�@@�@@�����R�����ʁF ProcessingResult.SUCCESS_RESULT  <BR>
     * �@@�|validate�V�K�����i��W�j()����O���X���[�����ꍇ�́A  <BR>
     * �@@�@@�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B  <BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n  <BR>
     * �@@�@@�@@�@@�����R�����ʁF ��O.getValidationResult().getProcessingResult()  <BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_web3MutualFundProduct - �g�����M����<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * 1�F���t�@@2�F���@@3�F�抷�@@4�F���� 5�F��W <BR>
     * @@param l_strPaymentMethod - (��n���@@)<BR>
     * 1�F��s�U���݁@@2�F�،���������<BR>
     * <BR>
     * @@param l_strDesignateMethod - (�w����@@)<BR>
     * 2�F�S���@@3�F���z�@@4�F����<BR>
     * @@param l_switchingSubject - (�抷�����)<BR>
     * �抷��̊g�����M�����I�u�W�F�N�g<BR>
     * @@param l_taxType - (�ŋ敪) <BR>
     * <BR>
     * @@param l_strSettleDiv - (���ϕ��@@)<BR>
     * 1:�~�� 2:�O��<BR>
     * <BR>
     * @@return NewOrderValidationResult
     * @@roseuid 40B15E2F00B2
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strPaymentMethod,
        String l_strDesignateMethod,
        WEB3MutualFundProduct l_switchingSubject,
        TaxTypeEnum l_taxType,
        String l_strSettleDiv)
    {
        String STR_METHOD_NAME = "validateNewOrder()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_web3MutualFundProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�����R������
        NewOrderValidationResult l_newOrderValidationResult = null;

        try
        {
            log.debug("����.�����敪 = " + l_strProcessDiv);
            //�P�j����.�����敪�̒l���h1�F���t�h�̏ꍇ
            if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv))
            {
                log.debug("����.�����敪�̒l���h1�F���t�h�̏ꍇ");
                //validate�V�K�����i���t�j()���R�[������
                this.validateBuyNewOrder(
                    l_subAccount,
                    l_web3MutualFundProduct,
                    l_dblOrderQuantity,
                    l_strProcessDiv,
                    l_strDesignateMethod,
                    l_strSettleDiv);
                //validate�V�K�����i���t�j()������I�������ꍇ
                l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("�i���t�j�����R�����ʁF" + 
                    l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
            }
            //�Q�j����.�����敪�̒l���h2�F���h�܂��́h4�F����h��
            //    ����.�抷�������null�̏ꍇ 
            else if ((WEB3ProcessDivDef.SELL.equals(l_strProcessDiv)
                    || WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
                    && l_switchingSubject == null)

            {
                log.debug("����.�����敪=2�F���h||�h4�F����h&& ����.�抷�����=null");
                //validate�V�K�����i���j()���R�[������
                this.validateSellNewOrder(
                    l_subAccount,
                    l_web3MutualFundProduct,
                    l_dblOrderQuantity,
                    l_strProcessDiv,
                    l_strPaymentMethod,
                    l_strDesignateMethod,
                    l_taxType,
                    l_strSettleDiv);
                //validate�V�K�����i���j()������I�������ꍇ
                l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("�i���j�����R�����ʁF" + 
                    l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
            }
            //�R�j����.�����敪�̒l���h2�F���h�܂��́h4�F����h��
            //    ����.�抷�������null�łȂ��ꍇ
            else if ((WEB3ProcessDivDef.SELL.equals(l_strProcessDiv)
                    || WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
                    && l_switchingSubject != null)
            {
                log.debug("����.�����敪=2�F���h||�h4�F����h&& ����.�抷����� !=null");
                //�|validate�V�K�����i�抷�j()���R�[������B  
                //�@@�mvalidate�V�K�����i�抷�j�ɓn���p�����^�n  
                //    �⏕�����F ����.�⏕����  
                //�@@  �g�����M�����F ����.�g�����M����  
                //�@@�@@�������ʁF ����.��������  
                //�@@�@@�����敪�F ����.�����敪 
                //�@@�@@�w����@@�F ����.�w����@@ 
                //�@@�@@�抷������F ����.�抷����� 
                //�@@�@@�ŋ敪�F ����.�ŋ敪 
                this.validateSwitchingNewOrder(
                    l_subAccount, 
                    l_web3MutualFundProduct, 
                    l_dblOrderQuantity, 
                    l_strProcessDiv, 
                    l_strDesignateMethod, 
                    l_switchingSubject, 
                    l_taxType);
                
                //validate�V�K�����i�抷�j()������I�������ꍇ
                l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("�i�抷�j�����R�����ʁF" + 
                    l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());
            }
            
            //�S�j�@@����.�����敪�̒l���h5�F��W�h�̏ꍇ
            else if (WEB3ProcessDivDef.RECRUIT.equals(l_strProcessDiv))                
            {
                //�|validate�V�K�����i��W�j()���R�[������B   
                //�⏕�����F ����.�⏕����  
                //�g�����M�����F ����.�g�����M����  
                //�������ʁF ����.��������
                //�w����@@�F ����.�w����@@
                //�����敪�F ����.�����敪
                
                this.validateRecruitNewOrder(
                    l_subAccount, 
                    l_web3MutualFundProduct, 
                    l_dblOrderQuantity, 
                    l_strDesignateMethod, 
                    l_strProcessDiv
                    );
                
                //�|validate�V�K�����i��W�j()������I�������ꍇ�́A  
                l_newOrderValidationResult =
                    new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
                log.debug("�i��W�j�����R�����ʁF" + 
                    l_newOrderValidationResult.getProcessingResult().isSuccessfulResult());                
            }
        }
        catch (OrderValidationException l_ex)
        {
            //validate�V�K��������O���X���[�����ꍇ
            log.debug("validate�V�K��������O���X���[�����ꍇ");
            l_newOrderValidationResult =
                new NewOrderValidationResult(l_ex.getValidationResult().getProcessingResult());
            
            return l_newOrderValidationResult;
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in validate�V�K����");
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * (validate�������)<BR>
     * �ivalidateCancelOrder�̎����j<BR>
     * <BR>
     * ���M����̔����R�����s���B<BR>
     * <BR>
     * �P�j�@@this.getOrder()���R�[�����A���M�����I�u�W�F�N�g���擾����B<BR>
     * �@@�mgetOrder�ɓn���p�����^�n<BR>
     * �@@�@@����ID�F ����.���M����������e.getOrderId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�擾�������M�����I�u�W�F�N�g.getOrderUni��s()���R�[�����A<BR>
     * ���M�����P�ʃI�u�W�F�N�g�̔z����擾����B<BR>
     * <BR>
     * �R�j�@@�w�肳�ꂽ����������\���`�F�b�N����B<BR>
     * �@@�|���M�����R���ʃ`�F�b�N.validate����\()���R�[������B<BR>
     * �@@�@@�mvalidate����\�ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F �����̕⏕����<BR>
     * �@@�@@�@@���M�����P�ʁF �擾�������M�����P�ʃI�u�W�F�N�g�̔z��[0]<BR>
     * <BR>
     * �@@�|validate����\()����O���X���[�����ꍇ�́A<BR>
     * NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * �@@�@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�����R�����ʁF ��O.getValidationResult().getProcessingResult()<BR>
     * <BR>
     * �S�j�@@NewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * �@@�@@�mNewOrderValidationResult�̃R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�����R�����ʁF ProcessingResult.SUCCESS_RESULT <BR>
     * @@param l_subAccount - �⏕����ID<BR>
     * @@param l_mutualCancelOrderSpec - ���M����������e<BR>
     * @@return OrderValidationResult
     * @@roseuid 40B15E2F00B9
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_mutualCancelOrderSpec)
    {
        String STR_METHOD_NAME = "validateCancelOrder()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mutualCancelOrderSpec == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();

        //�����R������
        NewOrderValidationResult l_newOrderValidationResult = null;

        try
        {            
            //�P�jthis.getOrder()���R�[�����A���M�����I�u�W�F�N�g���擾����
            MutualFundOrder l_mfOrder = null;
            l_mfOrder = (MutualFundOrder) this.getOrder(l_mutualCancelOrderSpec.getOrderId());

            //�Q�j�擾�������M�����I�u�W�F�N�g.getOrderUnits()���R�[�����A
            //���M�����P�ʃI�u�W�F�N�g�̔z����擾����            
            OrderUnit l_orderUnit[] = (OrderUnit[]) l_mfOrder.getOrderUnits();
            MutualFundOrderUnit l_mfOrderUnit = (MutualFundOrderUnit) l_orderUnit[0];

            log.debug("�擾�������M�����P��.orderUnitId = " + l_mfOrderUnit.getOrderUnitId());
            //�R�j�w�肳�ꂽ����������\���`�F�b�N����
            //���M�����R���ʃ`�F�b�N.validate����\()���R�[������
            l_validationsCheck.validateCancelPossible(l_subAccount, l_mfOrderUnit);

            //�S�jNewOrderValidationResult�I�u�W�F�N�g�𐶐����ĕԂ�
            l_newOrderValidationResult =
                new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in �����I�u�W�F�N�g���擾");
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005));            
        }        
        catch (WEB3BaseException l_ex)
        {
            log.error("Error in validate����\()");
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * validate�V�K�����i���t�j<BR>
     * <BR>
     * ���M���t�����̔����R�����s���B<BR>
     * <BR>
     * �P�j�@@���t���ʃ`�F�b�N<BR>
     * �@@�P�|�P�j�@@����.�������� �� 10000000000 �̏ꍇ��O���X���[����B <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00077<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�V�K���t���ǉ����t���̔���<BR>
     * �@@�@@�����t�������V�K���t���ǉ����t���̔��f���s���B<BR>
     * �@@�@@�|�g�����M�|�W�V�����}�l�[�W��.getAsset()���R�[�����A�ۗL���Y�I�u�W�F�N�g<BR>
     * �@@�@@�@@���擾����B<BR>
     * �@@�@@�mgetAsset�ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�����F�@@����.�g�����M�����I�u�W�F�N�g<BR>
     * �@@�@@�|getAsset()��NotFoundException���X���[�����ꍇ�͐V�K�����A�����łȂ��ꍇ��<BR>
     * �@@�@@�@@�ǉ������Ɣ��f����<BR>
     * <BR>
     * �@@�P�|�R�j�@@�V�K�����̏ꍇ�͈ȉ��̏������s���B<BR>
     * �@@�@@�|���t�Œᐔ�ʂ��擾����B<BR>
     * �@@�@@�@@(*) ����.�w����@@���h3�F���z�h�̏ꍇ�͊g�����M����.get�Œ���z<BR>
     * �i�V�K�����j()�̖߂�l���擾����B<BR>
     * �@@�@@�@@(*) ����.�w����@@���h4�F�����h�̏ꍇ�͊g�����M����.get�Œ����<BR>
     * �i�V�K�����j()�̖߂�l���擾����B<BR>
     * �@@�@@�|����.�������� �� ���t�Œᐔ�ʂ̏ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@�i���t�Œᐔ�ʃG���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00360<BR>
     * �@@�@@�|���t�P�ʐ��ʂ��擾����<BR>
     * �@@�@@�@@(*) ����.�w����@@���h3�F���z�h�̏ꍇ�͊g�����M����.get�P�ʋ��z<BR>
     * �i�V�K�����j()�̖߂�l���擾����B<BR>
     * �@@�@@�@@(*) ����.�w����@@���h4�F�����h�̏ꍇ�͊g�����M����.get�P�ʌ���<BR>
     * �i�V�K�����j()�̖߂�l���擾����B<BR>
     * �@@�@@�|����.�������ʂ����t�P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@�i���t�P�ʐ��ʃG���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00361<BR>
     * <BR>
     * �@@�P�|�S�j�@@�ǉ������̏ꍇ�͈ȉ��̏������s���B<BR>
     * �@@�@@�|���t�Œᐔ�ʂ��擾����B<BR>
     * �@@�@@�@@(*) ����.�w����@@���h3�F���z�h�̏ꍇ�͊g�����M����.get�Œ���z<BR>
     * �i�ǉ������j()�̖߂�l���擾����B<BR>
     * �@@�@@�@@(*) ����.�w����@@���h4�F�����h�̏ꍇ�͊g�����M����.get�Œ����<BR>
     * �i�ǉ������j()�̖߂�l���擾����B<BR>
     * �@@�@@�|����.�������� �� ���t�Œᐔ�ʂ̏ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@�����t�Œᐔ�ʃG���[<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00360<BR>
     * �@@�@@�|���t�P�ʐ��ʂ��擾����<BR>
     * �@@�@@�@@(*) ����.�w����@@���h3�F���z�h�̏ꍇ�͊g�����M����.get�P�ʋ��z<BR>
     * �i�ǉ������j()�̖߂�l���擾����B<BR>
     * �@@�@@�@@(*) ����.�w����@@���h4�F�����h�̏ꍇ�͊g�����M����.get�P�ʌ���<BR>
     * �i�ǉ������j()�̖߂�l���擾����B<BR>
     * �@@�@@�|����.�������ʂ����t�P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@�i���t�P�ʐ��ʃG���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00361<BR>
     * <BR>
     * �Q�j�@@�戵�\�����`�F�b�N<BR>
     * �@@�|����.�g�����M����.is�V�X�e���戵()���R�[������B<BR>
     * �@@�|is�V�X�e���戵()�� false ��Ԃ��ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�i�戵�s�����G���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00362<BR>
     * <BR>
     * �R�j�@@�������擾 <BR>
     * �@@�|���M������ԊǗ�.get���M������()���R�[�����A���������擾����B <BR>
     * <BR>
     * �S�j�@@����\�����`�F�b�N<BR>
     * �@@�|����.�g�����M����.is���t�\()���R�[������B<BR>
     * �@@�@@�mis���t�\�ɓn���p�����^�n<BR>
     * �@@�@@�@@�������F �擾����������<BR>
     * �@@�|is���t�\()�� false ��Ԃ��ꍇ�͗�O���X���[����B<BR> 
     * �@@�@@�i����s�����G���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00363<BR>
     * �@@�|����.�g�����M����.is���t�����L��()�� true ��Ԃ��ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�i����s�����G���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00363<BR>
     * <BR>
     * �T�j�@@�ً}��~�`�F�b�N<BR>
     * �@@�|���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[������B<BR>
     * �@@�@@�mvalidate�ً}��~�ɓn���p�����^�n<BR>
     * �@@�@@�@@�g�����M�����F ����.�g�����M����<BR>
     * �@@�@@�@@�����敪�F ����.�����敪<BR>
     * �@@�|�`�F�b�N�G���[�̏ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�i�ً}��~�G���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00012<BR>
     * <BR>
     * �U�j�@@�����~���ԃ`�F�b�N<BR>
     * �@@�|���M������ԊǗ�.validete������t�\()���R�[������B<BR>
     * �@@�|�`�F�b�N�G���[�̏ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�i�����~���ԃG���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00365<BR>
     * <BR>
     * �V�j  �O���،������J�݃`�F�b�N<BR>
     *    ����.����.is�O�����M() == true or ����.����.isFWF() == true �̏ꍇ�A���{ 
     * �@@�@@�i�O���،��������J�݃G���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01341<BR>
     * <BR>
     * �W�j  �ݓ������J�݃`�F�b�N<BR>
     *    ����.����.is�ē�������() == true �̏ꍇ�A���{ 
     * �@@�@@�i�ݓ��������J�݃G���[�j<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00249<BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_mutualFundProduct - �g�����M����<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * 1�F���t�@@2�F���@@3�F�抷�@@4�F����<BR>
     * @@param l_strDesignateMethod - (�w����@@)<BR>
     * 3�F���z�@@4�F����<BR>
     * @@param l_strSettleDiv - (���ϕ��@@)<BR>
     * 1:�~�� 2:�O��<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40B15E2F00C2
     */
    public void validateBuyNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_mutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strDesignateMethod,
        String l_strSettleDiv)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateBuyNewOrder()";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_mutualFundProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���t�Œᐔ��
        long l_lngNewBuyMinQty = 0;
        //���t�P�ʐ���
        long l_lngNewBuyUnitQty = 0;
        //�P�j�V�K���t���ǉ����t���̔��� 
        try
        {
            // �ۗL���Y�e�[�u��������
            List l_lisAssets = new ArrayList();
            String l_strWhere =
                "account_id = ? and sub_account_id = ? and product_id = ? ";
            Object[] l_objWhereValues = 
            {
                new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mutualFundProduct.getProductId())
            };
                               
            // -�ۗL���Y�e�[�u�����������A�ۗL���YParams��List���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAssets =
                l_queryProcessor.doFindAllQuery(
                    AssetRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
                    
            if (l_lisAssets.size() > 0)
            {
                log.debug("����.�w����@@ = " + l_strDesignateMethod);
                //�P�|�R�j�ǉ������̏ꍇ�͈ȉ��̏������s���B
                //(*) ����.�w����@@���h3�F���z�h������.���ϕ��@@���h1:�~�݁h�̏ꍇ
                if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod)
                    && WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
                {
                    //���t�Œᐔ�ʂ��擾����
                    l_lngNewBuyMinQty = l_mutualFundProduct.getAddBuyMinAmt();
                    //���t�P�ʐ��ʂ��擾����
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getAddBuyUnitAmt();
                }

                //(*) ����.�w����@@���h3�F���z�h������.���ϕ��@@���h2:�O�݁h�̏ꍇ
                if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod)
                    && WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
                {
                    //���t�Œᐔ�ʂ��擾����
                    l_lngNewBuyMinQty = l_mutualFundProduct.getFrgnAddBuyMinAmt();
                    //���t�P�ʐ��ʂ��擾����
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getFrgnAddBuyUnitAmt();
                }

                //(*) ����.�w����@@���h4�F�����h�̏ꍇ
                if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strDesignateMethod))
                {
                    //���t�Œᐔ�ʂ��擾����
                    l_lngNewBuyMinQty = l_mutualFundProduct.getAddBuyMinQty();
                    //���t�P�ʐ��ʂ��擾����                                        
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getAddBuyUnitQty();
                }
                //����.�������� �� ���t�Œᐔ�ʂ̏ꍇ�͗�O���X���[����
                log.debug("����.�������� = " + l_dblOrderQuantity);
                log.debug("���t�Œᐔ�� = " + l_lngNewBuyMinQty);
                if (l_dblOrderQuantity < l_lngNewBuyMinQty)
                {
                    log.debug(" __�ǉ�����:���t�Œᐔ�ʃG���[__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00360,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " __�ǉ�����:���t�Œᐔ�ʃG���[__");
                }
                //����.�������ʂ����t�P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����
                log.debug("���t�P�ʐ��� = " + l_lngNewBuyUnitQty);
                if (l_dblOrderQuantity % l_lngNewBuyUnitQty != 0)
                {
                    log.debug(" __�ǉ�����:���t�P�ʐ��ʃG���[__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00361,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " __�ǉ�����:���t�P�ʐ��ʃG���[__");
                }
            }
            else if (l_lisAssets.size() == 0)
            {
                log.debug("����.�w����@@ = " + l_strDesignateMethod);
                //�P�|�Q�j�V�K�����̏ꍇ�͈ȉ��̏������s��
                //����.�w����@@���h3�F���z�h������.���ϕ��@@���h1:�~�݁h�̏ꍇ
                if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod)
                    && WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
                {
                    //���t�Œᐔ�ʂ��擾����
                    l_lngNewBuyMinQty = l_mutualFundProduct.getNewBuyMinAmt();
                    //���t�P�ʐ��ʂ��擾����
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getNewBuyUnitAmt();
                }

                // ����.�w����@@���h3�F���z�h������.���ϕ��@@���h2:�O�݁h�̏ꍇ
                if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod)
                    && WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
                {
                    //���t�Œᐔ�ʂ��擾����
                    l_lngNewBuyMinQty = l_mutualFundProduct.getFrgnNewBuyMinAmt();
                    //���t�P�ʐ��ʂ��擾����
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getFrgnNewBuyUnitAmt();
                }
                //(*) ����.�w����@@���h4�F�����h�̏ꍇ
                else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strDesignateMethod))
                {
                    //���t�Œᐔ�ʂ��擾����
                    l_lngNewBuyMinQty = l_mutualFundProduct.getNewBuyMinQty();
                    //���t�P�ʐ��ʂ��擾����                                        
                    l_lngNewBuyUnitQty = l_mutualFundProduct.getNewBuyUnitQty();
                }
                //����.�������� �� ���t�Œᐔ�ʂ̏ꍇ�͗�O���X���[����
                log.debug("����.�������� = " + l_dblOrderQuantity);
                log.debug("���t�Œᐔ�� = " + l_lngNewBuyMinQty);
                if (l_dblOrderQuantity < l_lngNewBuyMinQty)
                {
                    log.debug(" __�V�K����:���t�Œᐔ�ʃG���[__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00360,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " __�V�K����:���t�Œᐔ�ʃG���[__");
                }
                //����.�������ʂ����t�P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����
                log.debug("���t�P�ʐ��� = " + l_lngNewBuyUnitQty);
                if (l_dblOrderQuantity % l_lngNewBuyUnitQty != 0)
                {
                    log.debug(" __�V�K����:���t�P�ʐ��ʃG���[__");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00361,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }      
            }
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }    
        
        //�Q�j�戵�\�����`�F�b�N 
        //����.�g�����M����.is�V�X�e���戵()���R�[������
        if (!l_mutualFundProduct.isSystemHandling())
        {
            log.debug(" __�戵�s�����G���[__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " __�V�K����:���t�P�ʐ��ʃG���[__");
        }
        
        //�R�j�������擾
        //���M������ԊǗ�.get���M������()���R�[�����A���������擾����B 
        Date l_datOrderBizDate = null;
        l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
        log.debug("���M������ = " + l_datOrderBizDate);

        //�S�j�@@����\�����`�F�b�N 
        //����.�g�����M����.is���t�\()���R�[������
        boolean l_blnAcquiredPossible = false; //���t�\
        l_blnAcquiredPossible = l_mutualFundProduct.isAcquiredPossible(l_datOrderBizDate);
        log.debug("����.�g�����M����.is���t�\() = " + l_blnAcquiredPossible);

        //is���t�\()�� false ��Ԃ��ꍇ�͗�O���X���[����
        if (!l_blnAcquiredPossible)
        {
            log.debug(" __����s�����G���[__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " __����s�����G���[__");
        }
        
        boolean l_blnAcquiredDeregExistence = false; //���t�����L��
        l_blnAcquiredDeregExistence = l_mutualFundProduct.isAcquiredDeregExistence();
        log.debug("����.�g�����M����.is���t�����L��()" + l_blnAcquiredDeregExistence);
        
        //is���t�����L��()�� true ��Ԃ��ꍇ�͗�O���X���[����
        if (l_blnAcquiredDeregExistence)
        {
            log.debug(" __����s�����G���[__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " __����s�����G���[__");
        }
        //�T�j�ً}��~�`�F�b�N
        //���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[������
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck =
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        try
        {
            l_validationsCheck.validateEmergencyStop(l_mutualFundProduct, l_strProcessDiv);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("�ً}��~�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getErrorMessage(),
                l_ex);           
        }
        //�U�j�����~���ԃ`�F�b�N
        //���M������ԊǗ�.validete������t�\()���R�[������
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //�V�j�O���،������J�݃`�F�b�N
        if (l_mutualFundProduct.isForeignFund()
            || l_mutualFundProduct.isFWF()
            || l_mutualFundProduct.isFrgnMmf())
        {
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            if (!l_mainAccount.isForeignAccountOpen())
            {
                log.debug(" __�O���،��������J�݃G���[__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " __�O���،��������J�݃G���[__");
            }
        }

        //�W�j�ݓ������J�݃`�F�b�N
        if (l_mutualFundProduct.isPlowbackProduct())
        {
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            if (!l_mainAccount.isRuitoAccountOpen())
            {
                log.debug(" __�ݓ��������J�݃G���[__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " __�ݓ��������J�݃G���[__");
            }
        }

        //validate�O��MMF��d����(�⏕����, �g�����M����, Date)
        //�mvalidate�O��MMF��d�����ɓn���p�����^�n
        //      �⏕����       �F ����.�⏕����
        //      �g�����M���� �F ����.�g�����M����
        //      ������          �F �擾����������
        l_validationsCheck.validateFrgnMmfDoubleOrder(
            l_subAccount,
            l_mutualFundProduct,
            l_datOrderBizDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate�V�K�����i���j<BR>
     * <BR>
     * validate�V�K�����i���j <BR>s
     * �V�[�P���X�}�i���M�j�����R���i���j���Q�� <BR>
     * <BR>
     * =============================================== <BR>
     * 1.1 is�V�X�e���戵()�� false ��Ԃ��ꍇ�͗�O���X���[����B <BR>
     *       �i�戵�s�����G���[�j <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00362 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.3 is���抷�\()�� false ��Ԃ��ꍇ�͗�O���X���[����B 
     *      �i����s�����G���[�j <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00363 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.8.1 is�~�ݐU����i��s�����j�o�^( )�̖߂�l��false�̏ꍇ�͗�O���X���[����B<BR>
     * �@@�@@�@@�i�U����i��s�����j�o�^���~�ݓo�^�ł͂���܂���B�j<BR>
     * �@@�@@�@@�@@class        : WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag          : BUSINESS_ERROR_02751<BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.8.2.1 �ڋq�̐U������Z�@@�փI�u�W�F�N�g���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�i�U������Z�@@�ւ��o�^����Ă��܂���B�j<BR>
     * �@@�@@�@@�@@class        : WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@tag          : BUSINESS_ERROR_01937<BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.10 �@@����.�g�����M����.getMutualFundType() == MutualFundTypeEnum.�����̏ꍇ�A<BR>
     *        ����.�������� �� 10000000000 �̏ꍇ��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00077 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.10 �B����.�������� �� ���Œᐔ�ʂ̏ꍇ�͗�O���X���[����B <BR>
     * �@@�@@�i���Œᐔ�ʃG���[�j <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00368 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.10 �C�|����.�������ʂ����P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B <BR>
     * �@@�@@�@@�i���P�ʐ��ʃG���[�j <BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00369 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_espMutualFundProduct - �g�����M����<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * 1�F���t�@@2�F���@@3�F�抷�@@4�F����<BR>
     * @@param l_strPaymentMethod - (��n���@@)<BR>
     * 1�F��s�U���݁@@2�F�،���������<BR>
     * <BR>
     * @@param l_strDesignateMethod - (�w����@@)<BR>
     * 2�F�S���@@3�F���z�@@4�F����<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * @@param l_strSettleDiv - (���ϕ��@@)<BR>
     * 1:�~�� 2:�O��<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40B15E2F00C6
     */
    public void validateSellNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strPaymentMethod,
        String l_strDesignateMethod,
        TaxTypeEnum l_taxType,
        String l_strSettleDiv)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSellNewOrder()";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_web3MutualFundProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        //1.1 is�V�X�e���戵( )
        //����.�g�����M����.is�V�X�e���戵()���R�[������
        log.debug("����.�g�����M����.is�V�X�e���戵() = " + 
            l_web3MutualFundProduct.isSystemHandling());
            
        //is�V�X�e���戵()�� false ��Ԃ��ꍇ�͗�O���X���[����B 
        //�i�戵�s�����G���[�j 
        if (!l_web3MutualFundProduct.isSystemHandling())
        {
            log.debug("�戵�s�����G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵�s�����G���[");
        }
        //1.2 �������擾
        //���M������ԊǗ�.get���M������()���R�[�����A���������擾����B
        Date l_datOrderBizDate = null;
        l_datOrderBizDate = WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();

        //1.3 is���抷�\(Date) 
        Date l_datArgIsSellSwitchingPossible = null;
        long l_lngProductId = l_web3MutualFundProduct.getProductId();
        if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
        {
        	l_datArgIsSellSwitchingPossible = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        else
        {
        	l_datArgIsSellSwitchingPossible = l_datOrderBizDate;
        }

        //����.�g�����M����.is���抷�\()���R�[������
        boolean l_blnSellSwitchingPossible = 
            l_web3MutualFundProduct.isSellSwitchingPossible(l_datArgIsSellSwitchingPossible);

        log.debug("����.�g�����M����.is���抷�\() = " + l_blnSellSwitchingPossible);
        if (!l_blnSellSwitchingPossible)
        {
            log.debug(" __����s�����G���[__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " __����s�����G���[__");
        }

        //1.4 �������@@�`�F�b�N
        boolean l_blnBuyingRequestPossible = false;
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        
        //1.4.1 ����.�g�����M����.is�������M�i�j�̖߂�l==true
        //      AND ����.�����敪���h4�F����h�̏ꍇ 
        boolean l_blnIsDomesticFund = l_web3MutualFundProduct.isDomesticFund();       
        log.debug("����.�����敪 = " + l_strProcessDiv);
        if (l_blnIsDomesticFund && WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
        {
            l_blnBuyingRequestPossible =
                l_validationsCheck.isBuyingRequestPossible(
                    l_datOrderBizDate,
                    l_subAccount,
                    l_web3MutualFundProduct);
            log.debug("is���搿���\ = " + l_blnBuyingRequestPossible);
            if (!l_blnBuyingRequestPossible)
            {
                log.debug("����s�����G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����s�����G���[");
            }
        }
            
        try
        {
            //1.6 �ً}��~�`�F�b�N 
            //���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[������
            l_validationsCheck.validateEmergencyStop(
                l_web3MutualFundProduct, l_strProcessDiv);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("�ً}��~�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getErrorMessage(),
                l_ex);           
        }
        //1.7 �����~���ԃ`�F�b�N 
        //���M������ԊǗ�.validete������t�\()���R�[������
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.8 �U����i��s�����j�`�F�b�N
        //����.��n���@@�̒l���h1�F��s�U���݁h�̏ꍇ
        log.debug("����.��n���@@ = " + l_strPaymentMethod);
        if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(l_strPaymentMethod))
        {
            MainAccount l_mainAccount = null;
            //�ڋq�I�u�W�F�N�g���擾����
            l_mainAccount = l_subAccount.getMainAccount();

            //1.8.1 is�~�ݐU����i��s�����j�o�^( )
            WEB3GentradeMainAccount l_genMainAccount = (WEB3GentradeMainAccount)l_mainAccount;

            boolean l_blnIsJapCurBankAccountRegi =
                l_genMainAccount.isJapaneseCurrencyBankAccountRegi();

            //is�~�ݐU����i��s�����j�o�^( )�̖߂�l��false�̏ꍇ�͗�O���X���[����B
            if (!l_blnIsJapCurBankAccountRegi)
            {
                log.debug("�U����i��s�����j�o�^���~�ݓo�^�ł͂���܂���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02751,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�U����i��s�����j�o�^���~�ݓo�^�ł͂���܂���B");
            }
            //is�~�ݐU����i��s�����j�o�^( )�̖߂�l ==true �̏ꍇ�A
            //�U������Z�@@�փe�[�u�����`�F�b�N����
            else
            {
                //1.8.2.1 get�U������Z�@@��( )
                //�ڋq�̐U������Z�@@�փI�u�W�F�N�g���擾����B
                //�擾�ł��Ȃ������ꍇ�́A��O���X���[����B
                if (l_genMainAccount.getTransferedFinInstitution() == null)
                {
                    log.debug("�U������Z�@@�ւ��o�^����Ă��܂���B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01937,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�U������Z�@@�ւ��o�^����Ă��܂���B");
                }
            }
        }

        //1.9 �S����񎞁A�S����񂪉\���`�F�b�N����
        //����.�w����@@�̒l���h2�F�S���h�̏ꍇ�A�S�����\�����`�F�b�N����B 
        log.debug("����.�w����@@ = " + l_strDesignateMethod);
        
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_strDesignateMethod))
        {
            //1.9.1 �����򁄓������������̏ꍇ
        	if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
            {
                //1.9.1.1 validate�S�����抷�\(�⏕����, �g�����M����, TaxTypeEnum)
                //�mvalidate�S�����抷�\�ɓn���p�����^�n 
                //�@@�@@�@@�⏕�����F ����.�⏕���� 
                //�@@�@@�@@�g�����M�����F ����.�g�����M���� 
                //�@@�@@�@@�ŋ敪�F ����.�ŋ敪

                l_validationsCheck.validateUnitTypeProductAllSellPoss(
                    l_subAccount,
                    l_web3MutualFundProduct,
                    l_taxType);
            }
            //1.9.2 �����򁄓������������ȊO�̏ꍇ
            else
            {
                //1.9.2.1 validate�S�����抷�\(�⏕����, �g�����M����, TaxTypeEnum)
                //�mvalidate�S�����抷�\�ɓn���p�����^�n 
                //�@@�@@�@@�⏕�����F ����.�⏕���� 
                //�@@�@@�@@�g�����M�����F ����.�g�����M���� 
                //�@@�@@�@@�ŋ敪�F ����.�ŋ敪
                //�@@�@@�@@�������Fget���M������()�̖߂�l

                l_validationsCheck.validateAllSellSwtPoss(
                    l_subAccount,
                    l_web3MutualFundProduct, 
                    l_taxType,
                    l_datOrderBizDate);

            }
        	
        }

        //1.10 ��񐔗ʃ`�F�b�N
        long l_lngSellMinQty = 0; //���Œᐔ��
        long l_lngSellUnitQty = 0; //���P�ʐ���

        //����.�w����@@�̒l���h2�F�S���h�łȂ��ꍇ
        if (!WEB3SellDivDef.ALL_DESIGNATE.equals(l_strDesignateMethod))
        {
           //�@@���Œᐔ�ʂ��擾����B
            // ����.�w����@@���h3�F���z�h������.���ϕ��@@���h1:�~�݁h�̏ꍇ
            if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod)
                && WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettleDiv))
            {
                l_lngSellMinQty = l_web3MutualFundProduct.getSellMinAmt();
                l_lngSellUnitQty = l_web3MutualFundProduct.getSellUnitAmt();
                log.debug("���Œᐔ�� = " + l_lngSellMinQty);
                log.debug("���P�ʐ��� = " + l_lngSellUnitQty);
            }
            //����.�w����@@���h3�F���z�h������.���ϕ��@@���h2:�O�݁h�̏ꍇ
            else if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod)
                && WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv))
            {
                l_lngSellMinQty = l_web3MutualFundProduct.getFrgnSellMinAmt();
                l_lngSellUnitQty = l_web3MutualFundProduct.getFrgnSellUnitAmt();
                log.debug("���Œᐔ�� = " + l_lngSellMinQty);
                log.debug("���P�ʐ��� = " + l_lngSellUnitQty);
            }
            //����.�w����@@���h4�F�����h�̏ꍇ
            else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
            {
                l_lngSellMinQty = l_web3MutualFundProduct.getSellMinQty();
                l_lngSellUnitQty = l_web3MutualFundProduct.getSellUnitQty();
                log.debug("���Œᐔ�� = " + l_lngSellMinQty);
                log.debug("���P�ʐ��� = " + l_lngSellUnitQty);
            }
            //�A����.�������� �� ���Œᐔ�ʂ̏ꍇ�͗�O���X���[����B 
            //�@@�i���Œᐔ�ʃG���[�j 
            if (l_dblOrderQuantity < l_lngSellMinQty)
            {
                log.debug("����.�������� �� ���Œᐔ�ʂ̏ꍇ�͗�O���X���[����B");
                log.debug("���Œᐔ�ʃG���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00368,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���Œᐔ�ʃG���[");
            }
            //�B���P�ʐ��ʂ��擾���� 
            //�|����.�������ʂ����P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B 
            // �i���P�ʐ��ʃG���[�j 
            if (l_dblOrderQuantity % l_lngSellUnitQty != 0)
            {
                log.debug("����.�������ʂ����P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B");
                log.debug("���P�ʐ��ʃG���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00369,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���P�ʐ��ʃG���[");
            }
        }

        //validate�O��MMF��d����(�⏕����, �g�����M����, Date)
        //�mvalidate�O��MMF��d�����ɓn���p�����^�n
        //    �⏕����       �F ����.�⏕����
        //    �g�����M���� �F ����.�g�����M����
        //    ������          �F �擾����������
        l_validationsCheck.validateFrgnMmfDoubleOrder(
            l_subAccount,
            l_web3MutualFundProduct,
            l_datOrderBizDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�V�K�����i�抷�j)<BR>
     * <BR>
     * ���M�抷�̔����R�����s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�����R���i�抷�j�v�Q�ƁB<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_web3MutualFundProduct - �g�����M����<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * 1�F���t�@@2�F���@@3�F�抷�@@4�F����<BR>
     * @@param l_strDesignateMethod - (�w����@@)<BR>
     * 2�F�S���@@3�F���z�@@4�F����<BR>
     * @@param l_switchingSubjectProduct - (�抷�����)<BR>
     * @@param l_taxType - (�ŋ敪)<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40BD67EF0262
     */
    public void validateSwitchingNewOrder(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        double l_dblOrderQuantity,
        String l_strProcessDiv,
        String l_strDesignateMethod,
        WEB3MutualFundProduct l_switchingSubjectProduct, 
        TaxTypeEnum l_taxType)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSwitchingNewOrder()";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_web3MutualFundProduct == null ||
            l_switchingSubjectProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 �抷���������擾����B
        //���M������ԊǗ�.get�抷������()���R�[�����A�抷���������擾����B  
        //[����]
        //�抷�������R�[�h�F�@@����.�g�����M����
        //�抷������R�[�h�F�@@����.�抷�����
        Date l_datSwtOrderBizDate = 
            WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(
                l_web3MutualFundProduct.getProductCode(), l_switchingSubjectProduct.getProductCode());
        log.debug("�抷������ = " + l_datSwtOrderBizDate);

        //1.2 validate�抷�\����(SubAccount, �g�����M����, �g�����M����, Date)
        //�抷���Ə抷��̂��ꂼ��̖����ɂ��āA�抷�\���ǂ������`�F�b�N����B 
        //[����] 
        //�⏕�����F ����.�⏕���� 
        //�抷�������F ����.�g�����M���� 
        //�抷������F ����.�抷����� 
        //�������F get�抷������()�̖߂�l 
        
        this.validateSwitchingPossProduct(
            l_subAccount, 
            l_web3MutualFundProduct, 
            l_switchingSubjectProduct, 
            l_datSwtOrderBizDate);

        //1.3 is���搿���\(Date, SubAccount, �g�����M����)
        //����.�g�����M����.is�������M�i�j�̖߂�l==true AND 
        //�����敪���h4�F����h�̏ꍇ�A�������@@�`�F�b�N�̃`�F�b�N������B 
        //�mis���搿���\�ɓn���p�����^�n 
        //�@@�@@�@@�������F get�抷������()�̖߂�l
        //�@@�@@�@@�⏕�����F����.�⏕���� 
        //�@@�@@�@@�g�����M�����F����.�g�����M����
        boolean l_blnBuyingRequestPossible = false;
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();        
        
        if (l_web3MutualFundProduct.isDomesticFund() &&
            WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
        {            
            l_blnBuyingRequestPossible =
                l_validationsCheck.isBuyingRequestPossible(
                    l_datSwtOrderBizDate, 
                    l_subAccount, 
                    l_web3MutualFundProduct);

            log.debug("����.�g�����M����.is���搿���\() = " + l_blnBuyingRequestPossible);   
            if (!l_blnBuyingRequestPossible)
            {
                log.debug("����s�����G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����s�����G���[");
            }
        }
        
        //1.4 validate�S�����抷�\(�⏕����, �g�����M����, TaxTypeEnum)
        //����.�w����@@�̒l���h2�F�S���h�̏ꍇ�A�S���抷���\���ǂ����`�F�b�N����B 
        
        log.debug("����.�w����@@ = " + l_strDesignateMethod);
        if (WEB3SellDivDef.ALL_DESIGNATE.equals(l_strDesignateMethod))
        {
            //�mvalidate�S�����抷�\�ɓn���p�����^�n 
            //�@@�@@�@@�⏕�����F ����.�⏕���� 
            //�@@�@@�@@�g�����M�����F ����.�g�����M���� 
            //�@@�@@�@@�ŋ敪�F ����.�ŋ敪
            //�@@�@@�@@�������F get�抷������()�̖߂�l                
            l_validationsCheck.validateAllSellSwtPoss(
                l_subAccount, 
                l_web3MutualFundProduct, 
                l_taxType,
                l_datSwtOrderBizDate);
        }
        //1.5 �w����@@�̒l���h2�F�S���h�łȂ��ꍇ�A�ȉ��̏������s���B
        else 
        {
            long l_lngNewSwtMinQty = 0L; //�抷�Œᐔ��
            long l_lngNewSwtUnitQty = 0L; //�抷�P�ʐ���
            
            //(*) ����.�w����@@���h3�F���z�h�̏ꍇ
            if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod))
            {
                //�抷�Œᐔ�ʂ��擾����
                l_lngNewSwtMinQty = l_web3MutualFundProduct.getSwitchingMinAmt();
                
                //�抷�P�ʐ��ʂ��擾����                      
                l_lngNewSwtUnitQty = l_web3MutualFundProduct.getSwitchingUnitAmt();
            }
            //(*) ����.�w����@@���h4�F�����h�̏ꍇ
            if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
            {
                //�抷�Œᐔ�ʂ��擾����
                l_lngNewSwtMinQty = l_web3MutualFundProduct.getSwitchingMinQty();
                
                //�抷�P�ʐ��ʂ��擾����                                        
                l_lngNewSwtUnitQty = l_web3MutualFundProduct.getSwitchingUnitQty();
            }

            log.debug("����.�������� " + l_dblOrderQuantity);
            log.debug("����.�抷�Œᐔ�� " + l_lngNewSwtMinQty);
            log.debug("����.�抷�P�ʐ��� " + l_lngNewSwtUnitQty);

            //����.�������� �� �抷�Œᐔ�ʂ̏ꍇ�͗�O���X���[����B
            if (l_dblOrderQuantity < l_lngNewSwtMinQty)
            {
                log.debug("�抷�Œᐔ�ʃG���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00370,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�抷�Œᐔ�ʃG���[");
            }
            
            //����.�������ʂ��抷�P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B
            if (l_dblOrderQuantity % l_lngNewSwtUnitQty != 0)
            {
                log.debug("�抷�P�ʐ��ʃG���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00371,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�抷�P�ʐ��ʃG���[");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����P��)<BR>
     * ���M�����P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�A�J�E���g�}�l�[�W��.getInstitution()���R�[�����A�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�mgetInstitution�ɓn���p�����^�n<BR>
     * �@@�@@�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j�@@���X�I�u�W�F�N�g���擾����B<BR>
     * �@@�A�J�E���g�}�l�[�W��.getBranch()���R�[�����āA���X�I�u�W�F�N�g���擾����B<BR>
     * �@@�mgetBranch�ɓn���p�����^�n<BR>
     * �@@�@@�،���ЁF �擾�����،���ЃI�u�W�F�N�g<BR>
     * �@@�@@���X�R�[�h�F ����.���X�R�[�h<BR>
     * <BR>
     * �R�j�@@���M�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�|�ȉ��̏����œ��M�����P�ʃe�[�u�����������A���M�����P��Params�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@���XID = �擾�������X�I�u�W�F�N�g.getBranchId()�̖߂�l AND<BR>
     * �@@�@@�@@���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �@@�|this.getOrderUnit()���R�[�����A���M�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getOrderUnit�ɓn���p�����^]<BR>
     * �@@�@@�@@�����P��ID�F ���M�����P��Params.getOrderUnitId()�̖߂�l<BR>
     * <BR>
     * �S�j�@@�擾�������M�����P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_strRequestNumber - ���ʃR�[�h<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 40B15E2F00CC
     */
    public MutualFundOrderUnit getOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strRequestNumber)
        throws NotFoundException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getOrderUnit(String l_strInstitutionCode, "
                + "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);

        // 1)�،���ЃI�u�W�F�N�g���擾����B
        FinApp l_finApp;
        l_finApp = (FinApp) Services.getService(FinApp.class);

        AccountManager l_accMgr;
        l_accMgr = l_finApp.getAccountManager();
        Institution l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
        // 2)���X�I�u�W�F�N�g���擾����B
        Branch l_branch;
        l_branch = l_accMgr.getBranch(l_institution, l_strBranchCode);
        long l_lngBranchId = 0;
        l_lngBranchId = l_branch.getBranchId();

        MutualFundOrderUnit l_mfOrderUnit = null;
        try
        {
            log.debug("[��������] ���XID  = " + l_lngBranchId);
            log.debug("[��������] ���ʃR�[�h = " + l_strRequestNumber);
            // 3)�ݓ������P�ʃI�u�W�F�N�g���擾����B
            String l_whereClause;
            l_whereClause = "branch_id = ? and order_request_number = ?";

            List l_lisRows = new Vector();
            Object l_bindVars[] = { new Long(l_lngBranchId), l_strRequestNumber};
            //�ȉ��̏����œ��M�����P�ʃe�[�u�����������A
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);
            if (l_lisRows == null || l_lisRows.size() ==0)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                //���M�����P��Params�I�u�W�F�N�g���擾����
                MutualFundOrderUnitParams l_mfOrderUnitParams = 
                    (MutualFundOrderUnitParams)l_lisRows.get(0);

                //this.getOrderUnit()���R�[�����A���M�����P�ʃI�u�W�F�N�g���擾����
                l_mfOrderUnit =
                    (MutualFundOrderUnit) this.getOrderUnit(
                    l_mfOrderUnitParams.getOrderUnitId());
                log.debug("this.getOrderUnit().getOrderUnitId() = " + l_mfOrderUnit.getOrderUnitId());
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
        //�S�j�擾�������M�����P�ʃI�u�W�F�N�g��Ԃ�
        return l_mfOrderUnit;
    }

    /**
     * (get�����P��)<BR>
     * ���M�����P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@���M�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�|�ȉ��̏����œ��M�����P�ʃe�[�u�����������A���M�����P��Params�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[��������]<BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����ID AND<BR>
     * �@@�@@�@@���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �@@�|this.getOrderUnit()���R�[�����A���M�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@[getOrderUnit�ɓn���p�����^]<BR>
     * �@@�@@�@@�����P��ID�F ���M�����P��Params.getOrderUnitId()�̖߂�l<BR>
     * <BR>
     * �Q�j�@@�擾�������M�����P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_lngSubAccountId - �⏕����ID<BR>
     * @@param l_strDiscriminationCode - ���ʃR�[�h<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 40B15E2F00D0
     */
    public MutualFundOrderUnit getOrderUnit(
            long l_lngSubAccountId, String l_strRequestNumber)
            throws NotFoundException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getOrderUnit(" + "long l_lngSubAccountId, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);

        //�P�j���M�����P�ʃI�u�W�F�N�g���擾����
        MutualFundOrderUnit l_mfOrderUnit = null;

        String l_whereClause;
        try
        {
            log.debug("@@param �⏕����ID = " + l_lngSubAccountId);
            log.debug("@@param ���ʃR�[�h = " + l_strRequestNumber);
            l_whereClause = "sub_account_id = ? and order_request_number = ?";
            Object l_bindVars[] = { new Long(l_lngSubAccountId), 
                                    l_strRequestNumber};

            List l_lisRows = new Vector();
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);

            log.debug("find MutualFundOrderUnit record size = " + l_lisRows.size());
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.debug("__�e�[�u���ɊY������f�[�^������܂���__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                //���M�����P��Params�I�u�W�F�N�g���擾����
                MutualFundOrderUnitParams l_mfOrderUnitParams =
                    (MutualFundOrderUnitParams)l_lisRows.get(0);
                
                //this.getOrderUnit()���R�[�����A���M�����P�ʃI�u�W�F�N�g���擾����
                l_mfOrderUnit =
                    (MutualFundOrderUnit) this.getOrderUnit(
                        l_mfOrderUnitParams.getOrderUnitId());
                log.debug("this.getOrderUnit().getOrderUnitId() = " + l_mfOrderUnit.getOrderUnitId());
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
        //�Q�j�擾�������M�����P�ʃI�u�W�F�N�g��Ԃ�
        return l_mfOrderUnit;
    }

    /**
     * (get�����P�ʈꗗ)<BR>
     * �w������Ɉ�v���钍���̓��M�����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * (getOrderUnits�̃I�[�o�[���[�h)<BR>
     * <BR>
     * �P)�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q)�@@���M�����P�ʃe�[�u�����������A���M�����P��Params��List���擾����B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@ ����ID = ����ID(�⏕�����I�u�W�F�N�g.getAccountId( )�Ŏ擾��) and<BR>
     * �@@�@@�@@ �⏕����ID = �⏕����ID(�⏕�����I�u�W�F�N�g.getSubAccountId( )�Ŏ擾��) and<BR>
     * �@@�@@�@@ �����^�C�v = ProductTypeEnum.MUTUAL_FUND(�����M��) and<BR>
     * �@@�@@�@@((������ >= ����(*1))<BR>
     * �@@�@@�@@or<BR>
     * �@@�@@�@@(������ < ����(*1) and <BR>
     * �@@�@@�@@ ����� = "��蒆"))<BR>
     * <BR>
     * �@@�Q-�P�j���M��O��MMF�\���敪 = "���M�̂�"�̏ꍇ<BR>
     * �@@�@@" and ���M�^�C�v <> MutualFundTypeEnum.�O��MMF " ��ǉ�<BR>
     * <BR>
     * �@@�Q-�Q�j���M��O��MMF�\���敪 = "�O��MMF�̂�"�̏ꍇ<BR>
     * �@@�@@" and ���M�^�C�v = MutualFundTypeEnum.�O��MMF " ��ǉ�<BR>
     * <BR>
     *  (*1)TradingSystem.getBizDate()�ɂĎ擾�����Ɩ�(�o�b�`)���t�̖߂�l<BR>
     * <BR>
     * �R)�@@�������ʂ�ԋp����B<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strMutualFrgnMmfDisplayDiv - ���M��O��MMF�\���敪<BR>
     * ���M��O��MMF�\���敪  <BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪<BR>
     * <BR>
     * 0:���M�̂� <BR>
     * 1:�O��MMF�̂� <BR>
     * 2:���� <BR>
     * <BR>
     * ��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���<BR>
     * @@return List
     * @@roseuid 40B15E2F00D3
     */
    public List getOrderUnitList(
        SubAccount l_subAccount,
        String l_strMutualFrgnMmfDisplayDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getOrderUnitList(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //���������𐶐�����B
        List l_lisRows = new Vector();
        List l_lisReturnOrderUnit = null;
        String l_strWhereClause;
        l_strWhereClause =
            "account_id = ? and sub_account_id = ? and "                                                                                                                        
            + "product_type = ? and ((biz_date >= ?) or "                                                                                                                     
            + "(biz_date < ? and exec_status = ?))";

        //���M��O��MMF�\���敪 = "���M�̂�"�̏ꍇ
        //  " and ���M�^�C�v <> MutualFundTypeEnum.�O��MMF " ��ǉ�
        if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(
            l_strMutualFrgnMmfDisplayDiv))
        {
            l_strWhereClause = l_strWhereClause + " and fund_type <> ? ";
        }

        //���M��O��MMF�\���敪 = "�O��MMF�̂�"�̏ꍇ
        // " and ���M�^�C�v = MutualFundTypeEnum.�O��MMF " ��ǉ�
        if (WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(
            l_strMutualFrgnMmfDisplayDiv))
        {
            l_strWhereClause = l_strWhereClause + " and fund_type = ? ";
        }

        try
        {
            //����ID(�⏕�����I�u�W�F�N�g.getAccountId()�Ŏ擾��)
            long l_lngAccount = l_subAccount.getAccountId();

            //�⏕����ID(�⏕�����I�u�W�F�N�g.getSubAccountId( )�Ŏ擾��)
            long l_lngSubAccount = l_subAccount.getSubAccountId();

            //(*1)�@@�Ɩ����t�̎擾   
              //TradingSystem.getBizDate()�ɂĎ擾�����Ɩ�(�o�b�`)���t�̖߂�l
              Date l_dateBizDate =
                  GtlUtils.getTradingSystem().getBizDate();

              SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
              String l_strBizDate;
              l_strBizDate = l_format.format(l_dateBizDate);
              log.debug("�Ɩ����t���擾 = " + l_strBizDate);

            //����� = "��蒆") 
            String l_strExecStatus = null;
            l_strExecStatus = WEB3ExecStatusDef.EXECUTED_IN_PROCESS;            

            int l_intLength = 6;
            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv)
                || WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_intLength = 7;
            }

            Object[] l_bindValues = new Object[l_intLength];
            l_bindValues[0] = new Long(l_lngAccount);
            l_bindValues[1] = new Long(l_lngSubAccount);
            l_bindValues[2] = ProductTypeEnum.MUTUAL_FUND;
            l_bindValues[3] = l_strBizDate;
            l_bindValues[4] = l_strBizDate;
            l_bindValues[5] = l_strExecStatus;

            if (WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND.equals(l_strMutualFrgnMmfDisplayDiv)
                || WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF.equals(l_strMutualFrgnMmfDisplayDiv))
            {
                l_bindValues[6] = MutualFundTypeEnum.FOREIGN_MMF;
            }

            // �p�����[�^�Ō��������f�[�^�R���e�i���쐬����B
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindValues);
            log.debug("����MutualFundOrderUnit Record Size = " + l_lisRows.size());

            //���M�����P��Params��List���擾����
            int l_intCount = l_lisRows.size();
            MutualFundOrderUnitParams[] l_mfOrderUnitParams =
                new MutualFundOrderUnitParams[l_intCount];
            l_lisRows.toArray(l_mfOrderUnitParams);
                
            l_lisReturnOrderUnit = new ArrayList();
    
            for (int i = 0; i < l_intCount; i++)
            {
                log.debug("for i = " + i);                    
                l_lisReturnOrderUnit.add(
                   this.getOrderUnit(l_mfOrderUnitParams[i].getOrderUnitId()));
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
        //�R)�@@�������ʂ�ԋp����
        return l_lisReturnOrderUnit;
    }

    /**
     * (calc�T�Z��n���)<BR>
     * �����w�莞�͊T�Z��n����A���z�w�莞�͊T�Z�����������Z�o���āA<BR>
     * �T�Z��n����I�u�W�F�N�g�ɐݒ肵�ĕԋp����B<BR>
     * <BR>
     * �icalc�T�Z��n����V�[�P���X�}�Q�Ɓj<BR>
     * <BR>
     * <BR>
     * (1) �T�Z��n����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@<BR>
     * <BR>
     * (2) �w����@@�� �����̏ꍇ�A�T�Z��n������Z�o����B<BR>
     * <BR>
     * �@@(2-1) �T�Z����������Z�o���āA�T�Z��n����I�u�W�F�N�g�̊T�Z��������ɃZ�b�g����B<BR>
     * �@@�@@�@@�icalc�T�Z�������()���R�[������j<BR>
     * <BR>
     * �@@�@@�@@[calc�T�Z�������()�̈���]<BR>
     * �@@�@@�@@�����敪�@@�@@�@@�@@�@@�@@�F�����̏����敪<BR>
     * �@@�@@�@@�������ʁ@@�@@�@@�@@�@@�@@�F�����̒�������<BR>
     * �@@�@@�@@���ϕ��@@�@@�@@�@@�@@�@@�@@�F�����̌��ϕ��@@<BR>
     * �@@�@@�@@�T�Z��n����@@�@@�@@ �F�T�Z��n����I�u�W�F�N�g<BR>
     * �@@�@@�@@�g�����M�����@@�@@�@@ �F�����̊g�����M�����I�u�W�F�N�g<BR>
     * <BR>
     * <BR>
     * �@@(2-2) �����S���������������T�Z��n������Z�o���āA<BR>
     * �T�Z��n����I�u�W�F�N�g�̊T�Z��n����ɃZ�b�g����B<BR>
     * �@@�@@�@@�icalc�����S����()���R�[������j<BR>
     * <BR>
     * �@@�@@�@@�@@[calc�����S����()�̈���]<BR>
     * �@@�@@�@@�@@�����敪�@@�@@�@@�@@�F�����̏����敪<BR>
     * �@@�@@�@@�@@�w����@@�@@�@@�@@�@@�F�����̎w����@@<BR>
     * �@@�@@�@@�@@���ʁ@@�@@�@@�@@�@@�@@�@@�F�T�Z��n����I�u�W�F�N�g�̊T�Z�������<BR>
     * �@@�@@�@@�@@�⏕�����@@�@@�@@�@@�F�����̕⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�T�Z��n����@@�F�T�Z��n����I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�g�����M�����@@�F�g�����M�����I�u�W�F�N�g<BR>
     * <BR>
     * �@@(2-3) �T�Z���������ɁA�����̒������ʂ��Z�b�g����B<BR>�@@�@@
     * <BR>
     * <BR>
     * (3) �w����@@�����z�w��̏ꍇ�A�T�Z�����������Z�o����B<BR>
     * <BR>
     * �@@(3-1) �T�Z�����������Z�o����B<BR>
     * �@@�@@�@@�icalc�T�Z��������()���R�[������j<BR>
     * <BR>
     * �@@�@@�@@[calc�T�Z���������̈���]<BR>
     * �@@�@@�@@�����敪�@@�@@�@@�@@�F�����̏����敪<BR>
     * �@@�@@�@@�������ʁ@@�@@�@@�@@�F�����̒�������
     * �@@�@@�@@�g�����M�����F�����̊g�����M�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�T�Z��n����F�T�Z��n����I�u�W�F�N�g<BR>
     *      
     * <BR>
     * �@@(3-2) �����S���������������T�Z�����������Z�o���āA<BR>
     * �T�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����B<BR>
     * �@@�@@�@@�icalc�����S����()���R�[������j<BR>
     * <BR>
     * �@@�@@�@@�@@[calc�����S����()�̈���]<BR>
     * �@@�@@�@@�@@�����敪�@@�@@�@@�@@�F�����̏����敪<BR>
     * �@@�@@�@@�@@�w����@@�@@�@@�@@�@@�F�����̎w����@@<BR>
     * �@@�@@�@@�@@���ʁ@@�@@�@@�@@�@@�@@�@@�F�T�Z��n����I�u�W�F�N�g�̊T�Z��������<BR>
     * �@@�@@�@@�@@�⏕�����@@�@@�@@�@@�F�����̕⏕�����I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�T�Z��n����@@�F�T�Z��n����I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�g�����M�����@@�F�g�����M�����I�u�W�F�N�g<BR>
     * <BR>
     * �@@(3-3) �T�Z��n����ɁA�����̒������ʂ��Z�b�g����B<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_web3MutualFundProduct - (�g�����M����)<BR>
     * @@param l_strProcessDiv - �����敪<BR>
     * <BR>
     * �P�F���t<BR>
     * �Q�F���<BR>
     * �R�F�抷<BR>
     * �S�F��W<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * <BR>
     * �����w��̏ꍇ�͒��������A���z�w��̏ꍇ�͒������z<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * <BR>
     * �R�F���z�w��<BR>
     * �S�F�����w��<BR>
     * @@param l_strSettlementMethod - ���ϕ��@@<BR>
     * �P�F�~��<BR>
     * �Q�F�O��<BR>
     * @@return webbroker3.mf.WEB3MutualFundEstimatedPrice
     * @@roseuid 40B4759D0231
     */
    protected WEB3MutualFundEstimatedPrice calcEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_web3MutualFundProduct,
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettlementMethod)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimateDeliveryAmount()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_web3MutualFundProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        //(1) �T�Z��n����I�u�W�F�N�g�𐶐�����
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = new WEB3MutualFundEstimatedPrice();
        //���M�̌v�Z�T�[�r�X
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();        
        
        //(2) �w����@@�� �����̏ꍇ�A�T�Z��n������Z�o����
        log.debug("�w����@@ = " + l_strDesignateMethod);
        if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
        {
            log.debug("�w����@@�� �����̏ꍇ");
    
            //(2-1) �T�Z����������Z�o���āA
            //      �T�Z��n����I�u�W�F�N�g�̊T�Z��������ɃZ�b�g����
            //      �icalc�T�Z�������()���R�[������j

            l_mfBizLogicProvider.calcEstimatedTradeAmount(
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_strSettlementMethod,
                l_web3MutualFundProduct,
                l_mfEstimatedPrice);       
            
            //(2-2) �����S���������������T�Z��n������Z�o���āA
            //      �T�Z��n����I�u�W�F�N�g�̊T�Z��n����ɃZ�b�g����B 
            //      �icalc�����S����()���R�[������j 
            l_mfBizLogicProvider.calcIncreaseRestraintPriceInRatio(
                l_strProcessDiv,
                l_strDesignateMethod,
                l_mfEstimatedPrice.getEstimatedTradeAmount(),
                l_subAccount,
                l_mfEstimatedPrice,
                l_web3MutualFundProduct);
                
            //(2-3) �T�Z���������ɁA�����̒������ʂ��Z�b�g����
            l_mfEstimatedPrice.setEstimatedQty(l_dblOrderQuantity);
        }
        //(3) �w����@@�����z�w��̏ꍇ�A�T�Z�����������Z�o����
        if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod))
        {
            log.debug("�w����@@�����z�w��̏ꍇ");
            log.debug("�T�Z�����������Z�o����");
            //(3-1) �T�Z�����������Z�o����icalc�T�Z��������()���R�[������j
            l_mfBizLogicProvider.calcEstimatedQty(
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_web3MutualFundProduct,
                l_mfEstimatedPrice);
            
            //(3-2) �����S���������������T�Z�����������Z�o���āA
            //      �T�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����B 
            //      �icalc�����S����()���R�[������j 
            log.debug("�����S���������������T�Z�����������Z�o����");
            l_mfBizLogicProvider.calcIncreaseRestraintPriceInRatio(
                l_strProcessDiv,
                l_strDesignateMethod,
                l_mfEstimatedPrice.getEstimatedQty(),
                l_subAccount,
                l_mfEstimatedPrice,
                l_web3MutualFundProduct);

            //(3-3) �T�Z��n����ɁA�����̒������ʂ��Z�b�g����
            l_mfEstimatedPrice.setEstimatedPrice(l_dblOrderQuantity);
            
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_mfEstimatedPrice;
    }

    /**
     * (get�ŏI��������)<BR>
     * �w�肵������ID�ɊY�����钍���P�ʂ̒��������ŏI�ʔԂɊY������<BR>
     * ���M���������I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@this.getOrder()���R�[�����A���M�����I�u�W�F�N�g���擾����B<BR>
     * �@@�mgetOrder�ɓn���p�����^�n<BR>
     * �@@�@@����ID�F ����.����ID<BR>
     * <BR>
     * �Q�j�@@�擾�������M�����I�u�W�F�N�g.getOrderUnits()���R�[�����A���M�����P��<BR>
     * �@@�@@�I�u�W�F�N�g�̔z����擾����B<BR>
     * <BR>
     * �R�j�@@�擾�������M�����P�ʃI�u�W�F�N�g�̔z��[0].getOrderActions()���R�[�����A<BR>
     * �@@�@@���M���������I�u�W�F�N�g�̔z����擾����B<BR>
     * <BR>
     * �S�j�@@�擾�������M�����P�ʃI�u�W�F�N�g�̔z��[0].getDataSourceObject()
     *      .getLastOrderActionSerialNo()<BR>
     * �@@�@@���R�[�����A���������ŏI�ʔԂ��擾����B<BR>
     * <BR>
     * �T�j�@@�擾�������M���������I�u�W�F�N�g�̔z��̒�����A�擾�������������ŏI�ʔԂ�<BR>
     * �@@�@@��������������ԍ��̓��M���������I�u�W�F�N�g���擾���ĕԂ��B<BR>
     * @@param l_lngOrderId - ����ID<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderAction
     * @@roseuid 40B53C0200ED
     */
    public MutualFundOrderAction getFinalOrderAction(long l_lngOrderId) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getFinalOrderAction()";
        log.entering(STR_METHOD_NAME);

        MutualFundOrder l_mfOrder = null;
        MutualFundOrderAction l_mfReturnOrderAction = null;
        try
        {
            //�P�jthis.getOrder()���R�[�����A���M�����I�u�W�F�N�g���擾����
            l_mfOrder = (MutualFundOrder) this.getOrder(l_lngOrderId);

            //�Q�j�擾�������M�����I�u�W�F�N�g.getOrderUnits()���R�[�����A
            //���M�����P�ʃI�u�W�F�N�g�̔z����擾����
            OrderUnit l_orderUnit[] = l_mfOrder.getOrderUnits();
            int l_intSize = 0;
            l_intSize = l_orderUnit.length;
            log.debug("getOrderUnits.length = " + l_intSize);
            
            MutualFundOrderUnit[] l_mfOrderUnits =
                new MutualFundOrderUnit[l_intSize]; 

            for(int i = 0; i < l_intSize; i++)
            {
                log.debug("l_mfOrderUnits enter for i= " + i);
                MutualFundOrderUnit l_mfOrderUnit = (MutualFundOrderUnit)l_orderUnit[i];
                l_mfOrderUnits[i] = l_mfOrderUnit;
            }

            //�R�j�擾�������M�����P�ʃI�u�W�F�N�g�̔z��[0].getOrderActions()���R�[�����A
            //���M���������I�u�W�F�N�g�̔z����擾����
            OrderAction l_orderAction[] = l_mfOrderUnits[0].getOrderActions();
            
            MutualFundOrderAction l_mfOrderActions[] =
                new MutualFundOrderAction[l_orderAction.length];
            
            for (int i = 0; i < l_orderAction.length; i++)
            {
                log.debug("l_mfOrderActions enter for i= " + i);
                MutualFundOrderAction l_mfOrderAction = (MutualFundOrderAction)l_orderAction[i];
                l_mfOrderActions[i] = l_mfOrderAction;
            }           
                
            //�S�j�擾�������M�����P�ʃI�u�W�F�N�g�̔z��[0].getDataSourceObject()
            MutualFundOrderUnitRow l_mfOrderUnitRow =
                (MutualFundOrderUnitRow) l_mfOrderUnits[0].getDataSourceObject();
            //���������ŏI�ʔԂ��擾����
            int l_intLastOrderActionSerialNo =  
                l_mfOrderUnitRow.getLastOrderActionSerialNo();
            log.debug("���������ŏI�ʔ� l_mfOrderUnitRow.getLastOrderActionSerialNo() = " + 
                l_intLastOrderActionSerialNo);
            //�T�j�擾�������M���������I�u�W�F�N�g�̔z��̒�����A�擾�������������ŏI�ʔԂ� 
            //  ��������������ԍ��̓��M���������I�u�W�F�N�g���擾���ĕԂ�
           
            int i = 0;
            for (i = 0; i < l_mfOrderActions.length; i++)
            {
                log.debug("enter for i= " + i);
                log.debug("��������ԍ� l_mfOrderAction[i].getOrderActionSerialNo() = " + 
                    l_mfOrderActions[i].getOrderActionSerialNo());
                if (l_mfOrderActions[i].getOrderActionSerialNo() == 
                    l_intLastOrderActionSerialNo)
                {
                    log.debug("���������ŏI�ʔ� = ��������ԍ�");
                    l_mfReturnOrderAction = l_mfOrderActions[i];
                }
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(" __NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_mfReturnOrderAction;

    }

    /**
     * (get�������ʋ敪)<BR>
     * ���M�����P�ʂ��A���̒����̒������ʋ敪�𔻒肵�ĕԋp����B<BR>
     * <BR>
     * �P)�@@���M�����P��.�������ʃ^�C�v��"����"�̏ꍇ�A"0"�����^�[������B<BR>
     * <BR>
     * �Q)�@@���M�����P��.�������ʃ^�C�v��"���z"�̏ꍇ<BR>
     * �@@�Q�|�P)�@@���M�����P��.���ϋ敪��"�~��"�̏ꍇ�A"T0"�����^�[������B<BR>
     * <BR>
     * �@@�Q�|�Q)�@@���M�����P��.���ϋ敪��"�O��"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@���M�����P��.getProduct( ).get�ʉ݃R�[�h( )�̖߂�l�����^�[������B<BR>
     * @@param l_mutualFundOrderUnit - ���M�����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 40BD92380174
     */
    public String getOrderQuantityDiv(MutualFundOrderUnit l_mutualFundOrderUnit)            
    {
        String STR_METHOD_NAME = "getOrderQuantityDiv()";
        log.entering(STR_METHOD_NAME);
       
        if (l_mutualFundOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        String l_strOrderQtyType = null;    //�������ʃ^�C�v
        String l_strSettlementDiv = null;   //���ϋ敪
        QuantityTypeEnum l_orderQtyType = null;   //�������ʃ^�C�v
        
        MutualFundOrderUnitRow l_mfOrderUnitRow  = 
            (MutualFundOrderUnitRow)l_mutualFundOrderUnit.getDataSourceObject();
        l_orderQtyType = l_mfOrderUnitRow.getQuantityType();
        l_strSettlementDiv = l_mfOrderUnitRow.getSettlementDiv();
        
        log.debug("���M�����P��.�������ʃ^�C�v = " + l_orderQtyType);
        //�P)�@@���M�����P��.�������ʃ^�C�v��"����"�̏ꍇ
        //"0"�����^�[������
        if (QuantityTypeEnum.QUANTITY.equals(l_orderQtyType))
        {
            l_strOrderQtyType = WEB3MFOrderQuantityType.QTY;
        }
        //�Q)�@@���M�����P��.�������ʃ^�C�v��"���z"�̏ꍇ
        if (QuantityTypeEnum.AMOUNT.equals(l_orderQtyType))
        {
            log.debug("���M�����P��.���ϋ敪 = " + l_strSettlementDiv);
            //�Q�|�P)�@@���M�����P��.���ϋ敪��"�~��"�̏ꍇ�A"T0"�����^�[������
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettlementDiv))
            {
                l_strOrderQtyType = WEB3MFOrderQuantityType.EN;
            }
            //�Q�|�Q)�@@���M�����P��.���ϋ敪��"�O��"�̏ꍇ
            //���M�����P��.getProduct( ).get�ʉ݃R�[�h( )�̖߂�l�����^�[������
            if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettlementDiv))
            {
                MutualFundProductRow l_mfProductRow = null;
                l_mfProductRow = (MutualFundProductRow)
                    l_mutualFundOrderUnit.getProduct().getDataSourceObject();
                l_strOrderQtyType = l_mfProductRow.getCurrencyCode();
            }
        }
        log.debug("return �������ʋ敪 = " + l_strOrderQtyType);
        log.exiting(STR_METHOD_NAME);
        //�������ʋ敪�����^�[������
        return l_strOrderQtyType;
    }

    /**
     * (get�T�Z��n����ʉ݃R�[�h)<BR>
     * ���M�����P�ʂ��A���̒����̊T�Z���ʋ敪�𔻒肵�ĕԋp����B<BR>
     * <BR>
     * �P)�@@this.get�������ʋ敪( )���R�[������B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@����:���M�����P��<BR>
     * <BR>
     * �Q)�@@�߂�l�Z�b�g<BR>
     * �@@�Q�|�P)�@@�P)�̖߂�l��"0"(����)�̏ꍇ<BR>
     * �@@�@@�Q�|�P�|�P)�@@���M�����P��.���ϋ敪��"�~��"�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@"T0"�����^�[������B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q)�@@���M�����P��.���ϋ敪��"�O��"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@���M�����P��.getProduct( ).get�ʉ݃R�[�h( )�����^�[������B<BR>
     *   �Q�|�P)�@@�P)�̖߂�l��"0"�ȊO�̏ꍇ
     *      �P)�̖߂�l�����^�[������B
     * @@param l_mutualFundOrderUnit - ���M�����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 40BD92690164
     */
    public String getEstimateDeliveryAmountCurrencyCode(
        MutualFundOrderUnit l_mutualFundOrderUnit)
    {
        String STR_METHOD_NAME = "getEstimateDeliveryAmountCurrencyCode()";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }       
        
        //�P)this.get�������ʋ敪()���R�[������
        String l_strOrderQuantityDiv = this.getOrderQuantityDiv(l_mutualFundOrderUnit);
        //�Q)�@@�߂�l�Z�b�g
        String l_strCurrencyCode = null;
                
        //�Q�|�P)�@@�P)�̖߂�l��"0"(����)�̏ꍇ
        log.debug("this.get�������ʋ敪()�̖߂�l = " + l_strOrderQuantityDiv);        
        if (WEB3MFOrderQuantityType.QTY.equals(l_strOrderQuantityDiv))
        {
            MutualFundOrderUnitRow l_mfOrderUnitRow = null;
            l_mfOrderUnitRow = (MutualFundOrderUnitRow)
                l_mutualFundOrderUnit.getDataSourceObject();
            String l_strSettlementDiv = l_mfOrderUnitRow.getSettlementDiv();
            log.debug("���M�����P��.���ϋ敪 = " + l_strSettlementDiv);
            
            //�Q�|�P�|�P)�@@���M�����P��.���ϋ敪��"�~��"�̏ꍇ"T0"�����^�[������
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettlementDiv))
            {
                l_strCurrencyCode = WEB3MFOrderQuantityType.EN;
                log.debug("get�T�Z��n����ʉ݃R�[�h(return) = " + l_strCurrencyCode);
            }
            //�Q�|�P�|�Q)�@@���M�����P��.���ϋ敪��"�O��"�̏ꍇ�A 
            //���M�����P��.getProduct( ).get�ʉ݃R�[�h( )�����^�[������
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettlementDiv))
            {
                MutualFundProductRow l_mfProductRow = null;
                l_mfProductRow = (MutualFundProductRow)
                    l_mutualFundOrderUnit.getProduct().getDataSourceObject();
                l_strCurrencyCode = l_mfProductRow.getCurrencyCode();
                log.debug("get�T�Z��n����ʉ݃R�[�h(return) = " + l_strCurrencyCode);
            }
        }
        else
        {
            log.debug("�߂�l��'0'�ȊO�̏ꍇ");
            l_strCurrencyCode = l_strOrderQuantityDiv;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strCurrencyCode;
    }

    /**
     * (get�����敪�i���M�j)<BR>
     * ����:���M�����P�ʂ��A�����敪���擾����"���t"�A"���"�A"�抷"�A"��W"�̂����ꂩ��ԋp����B<BR>
     * <BR>
     * �P)�@@"���t"����<BR>
     * �@@���M�����P�ʂ��ȉ��̏����ɍ��v����ꍇ�A"���t"�����^�[������B<BR>
     * �@@���������="�����M��������"�ł���<BR>
     * <BR>
     * �Q)�@@"���"���� <BR>
     * �@@���M�����P�ʂ��ȉ��̏����ɍ��v����ꍇ�A"���"�����^�[������B <BR>
     * �@@���������="�����M��������"�ł��� <BR>
     * <BR>
     * �R)�@@"�抷"���� <BR>
     * �@@���M�����P�ʂ��ȉ��̏����ɍ��v����ꍇ�A"�抷"�����^�[������B <BR>
     * �@@���������="�����M���抷����"�ł��� <BR>
     * <BR>
     * �S)�@@"��W"���� <BR>
     * �@@���M�����P�ʂ��ȉ��̏����ɍ��v����ꍇ�A"��W"�����^�[������B <BR>
     * �@@���������="�����M����W����"�ł��� <BR>
     * <BR>
     * @@param l_mutualFundOrderUnit - ���M�����P�ʃI�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 40DBE07C01E3
     */
    public String getMutualTradeDiv(MutualFundOrderUnit l_mutualFundOrderUnit)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getMutualTradeDiv()";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strMfTradeDiv = null;   //�����敪
        MutualFundOrderUnitRow l_mfOrderUnitRow  = 
            (MutualFundOrderUnitRow)l_mutualFundOrderUnit.getDataSourceObject();
        //�P)�@@"���t"����
        //���������="�����M��������"�ł���       
        log.debug("������� = " + l_mutualFundOrderUnit.getOrderType()); 
        if (OrderTypeEnum.MF_BUY.equals(l_mutualFundOrderUnit.getOrderType()))
        {
            l_strMfTradeDiv = WEB3ProcessDivDef.BUY;
            log.debug("�����敪 = " + l_strMfTradeDiv);
        }        
        //�Q)�@@"���"����
        //���M�����P�ʂ��ȉ��̏����ɍ��v����ꍇ�A"���"�����^�[������B 
        //���������="�����M��������"�ł��� 
        log.debug("�����R�[�h = " + l_mfOrderUnitRow.getSwtProductCode());
        if (OrderTypeEnum.MF_SELL.equals(l_mutualFundOrderUnit.getOrderType()))
        {
            l_strMfTradeDiv = WEB3ProcessDivDef.SELL;
            log.debug("�����敪 = " + l_strMfTradeDiv);
        }        
        //�R)�@@"�抷"����
        //���M�����P�ʂ��ȉ��̏����ɍ��v����ꍇ�A"�抷"�����^�[������B
        //���������="�����M���抷����"�ł��� 
        if (OrderTypeEnum.MF_SWITCHING.equals(l_mutualFundOrderUnit.getOrderType()))
        {
            l_strMfTradeDiv = WEB3ProcessDivDef.SWITCHING;
            log.debug("�����敪 = " + l_strMfTradeDiv);
        }
        //�S)�@@"��W"���� 
        //���M�����P�ʂ��ȉ��̏����ɍ��v����ꍇ�A"��W"�����^�[������B 
        //�@@���������="�����M����W����"�ł��� 
        if (OrderTypeEnum.MF_RECRUIT.equals(l_mutualFundOrderUnit.getOrderType()))
        {
            l_strMfTradeDiv = WEB3ProcessDivDef.RECRUIT;
            log.debug("�����敪 = " + l_strMfTradeDiv);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strMfTradeDiv;
    }
    
    /**
     * (validate�V�K�����i��W�j)
     * validate�V�K�����i��W�j <BR>
     *<BR>
     * ���M��W�̔����R�����s���B <BR>
     *<BR>
     * �V�[�P���X�}�u�����R���i��W�j�v�Q�ƁB <BR>
     * 1.1 ��W���ʃ`�F�b�N
     * =============================================== <BR>
     * �|����.�������� �� ��W�Œᐔ�ʂ̏ꍇ�͗�O���X���[����B <BR>
     * �@@�@@�@@�i��W�Œᐔ�ʃG���[�j<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02227 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * �|����.�������ʂ���W�P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B <BR>
     * �@@�@@�@@�i��W�P�ʐ��ʃG���[�j<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_02228 <BR>
     * =============================================== <BR>
     *<BR>
     * 1.8 �ݓ������J�݃`�F�b�N
     * =============================================== <BR>
     * �|�������ē��������ŗݓ��������J�݂̏ꍇ�͗�O���X���[����B <BR>
     * �@@�@@�@@�i�ݓ��������J�݃G���[�j<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00249 <BR>
     * =============================================== <BR>
     *<BR>
     * ===============================================<BR>
     *       �V�[�P���X�} :�i���M�j�����R���i��W�j<BR>
     *       ��̈ʒu    : 1.9. ��d�����`�F�b�N <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�D������������̑��݃`�F�b�N<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�P�D�Ŏ擾�������R�[�h������0�ȊO�̏ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B�i��d�����G���[�j<BR>       
     *       class       : WEB3BusinessLayerException <BR>
     *       tag          : BUSINESS_ERROR_02648 <BR>
     * ===============================================<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_dblOrderQuantity - ��������
     * @@param l_strDesignateMethod - (�w����@@)
     * 3�F���z�@@4�F����
     * @@param l_strProcessDiv - (�����敪)
     * 1�F���t�@@2�F���@@3�F�抷�@@4�F����@@5�F��W
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40B15E2F00C2
     */
   public void validateRecruitNewOrder(
       SubAccount l_subAccount,
       WEB3MutualFundProduct l_mutualFundProduct,
       double l_dblOrderQuantity,
       String l_strDesignateMethod, 
       String l_strProcessDiv)
           throws OrderValidationException, WEB3BaseException
   {
       String STR_METHOD_NAME = "validateRecruitNewOrder(" +
            "SubAccount, WEB3MutualFundProduct, double, String, String)";
       log.entering(STR_METHOD_NAME);
       
       if (l_subAccount == null || l_mutualFundProduct == null)
       {
           log.debug(" �p�����[�^�l��NULL ");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       //1.1 ��W���ʃ`�F�b�N        
       //�ȉ��̏������s���B 
       //�|��W�Œᐔ�ʂ��擾����B           
       long l_lngRecruitMinQty = 0L;
       
       //(*) ����.�w����@@���h3�F���z�h�̏ꍇ�͊g�����M����.get�Œ���z�i��W�j()�̖߂�l���擾����B
       if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod))
       {
           //��W�Œᐔ�ʂ��擾����
           l_lngRecruitMinQty = Long.parseLong(l_mutualFundProduct.getRecruitMinAmt());               
       }
       //(*) ����.�w����@@���h4�F�����h�̏ꍇ�͊g�����M����.get�Œ�����i��W�j()�̖߂�l���擾����B 
       else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
       {
           //��W�Œᐔ�ʂ��擾����
           l_lngRecruitMinQty = Long.parseLong(l_mutualFundProduct.getRecruitMinQty());
       }
       log.debug("����.�������� = " + l_dblOrderQuantity);
       log.debug("��W�Œᐔ�� = " + l_lngRecruitMinQty);
       
       //�|����.�������� �� ��W�Œᐔ�ʂ̏ꍇ�͗�O���X���[����B 
       //�@@�i��W�Œᐔ�ʃG���[�j 
       if (l_dblOrderQuantity < l_lngRecruitMinQty)
       {
           log.debug("��W�Œᐔ�ʃG���[");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02227,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "��W�Œᐔ�ʃG���[");
       }
       
       //�|��W�P�ʐ��ʂ��擾���� 
       long l_lngRecruitUnitQty = 0L;
       
       //�@@(*) ����.�w����@@���h3�F���z�h�̏ꍇ�͊g�����M����.get�P�ʋ��z�i��W�j()�̖߂�l���擾����B 
       if (WEB3SellDivDef.MONEY_DESIGNATE.equals(l_strDesignateMethod))
       {
           //��W�P�ʐ��ʂ��擾����
           l_lngRecruitUnitQty = Long.parseLong(l_mutualFundProduct.getRecruitUnitAmt());               
       }
       //�@@(*) ����.�w����@@���h4�F�����h�̏ꍇ�͊g�����M����.get�P�ʌ����i��W�j()�̖߂�l���擾����B 
       else if (WEB3SellDivDef.COUNT_DESIGNATE.equals(l_strDesignateMethod))
       {
           //��W�P�ʐ��ʂ��擾����
           l_lngRecruitUnitQty = Long.parseLong(l_mutualFundProduct.getRecruitUnitQty());
       }
       log.debug("����.�������� = " + l_dblOrderQuantity);
       log.debug("��W�P�ʐ��� = " + l_lngRecruitUnitQty);
     
       //�|����.�������ʂ���W�P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B 
       //�@@�i��W�P�ʐ��ʃG���[�j 
       if (l_dblOrderQuantity % l_lngRecruitUnitQty != 0)
       {
           log.debug("����.�������ʂ���W�P�ʐ��ʂŊ���؂�Ȃ��ꍇ�͗�O���X���[����B");
           log.debug("��W�P�ʐ��ʃG���[");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02228,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "��W�P�ʐ��ʃG���[");
       }
       
       //1.2 is�V�X�e���戵( )
       //�戵�\�����`�F�b�N������ 
       //����.�g�����M����.is�V�X�e���戵()���R�[������B 
       //is�V�X�e���戵()�� false ��Ԃ��ꍇ�͗�O���X���[����B 
       //�i�戵�s�����G���[�j
       if (!l_mutualFundProduct.isSystemHandling())
       {
           log.debug("�戵�s�����G���[");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00362,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�戵�s�����G���[");
       }
       
       //1.3 get������( )
       //������ԊǗ�.get������()���R�[�����A���������擾����B
       Date l_datBizDate = WEB3MutualFundTradingTimeManagement.getOrderBizDate();
       
       //1.4 is��W�\(Date)
       //����\�����`�F�b�N 
       //����.�g�����M����.is��W�\()���R�[������B 
       //�m�����n 
       //�������F �擾���������� 
       //is��W�\()�� false ��Ԃ��ꍇ�͗�O���X���[����B�i����s�����G���[�j
       boolean l_blnIsRecruitPoss = l_mutualFundProduct.isRecruitPossible(l_datBizDate);
       
       if (!l_blnIsRecruitPoss)
       {
           log.debug("����s�����G���[");
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00363,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "����s�����G���[");
       }
       
       //1.5 getInstance( )
       WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
           (WEB3MutualFundOrderManagerReusableValidationsCheck)
               MutualFundProductTypeOrderManagerReusableValidations.getInstance();

       //1.6 validate�ً}��~(�g�����M����, String)
       //�ً}��~�`�F�b�N 
       //���M�����R���ʃ`�F�b�N.validate�ً}��~()���R�[������B 
       //�m�����n 
       //�g�����M�����F ����.�g�����M���� 
       //�����敪�F ����.�����敪 
       //�`�F�b�N�G���[�̏ꍇ�́A��O���X���[����B�i�ً}��~�G���[�j
       try
       {           
           l_validationsCheck.validateEmergencyStop(l_mutualFundProduct, l_strProcessDiv);
       }
       catch(WEB3BaseException l_ex)
       {
           log.error("�ً}��~�G���[");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00012,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getErrorMessage(),
               l_ex);           
       }
       
       //1.7 validate������t�\( )
       //�����~���ԃ`�F�b�N 
       //������ԊǗ�.validete������t�\()���R�[������B 
       //�`�F�b�N�G���[�̏ꍇ�́A��O���X���[����B�i�����~���ԃG���[�j
       
       WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.8 �ݓ������J�݃`�F�b�N
        //(*) ����.�g�����M����.is�ē�������()�̖߂�l == true �̏ꍇ
        if (l_mutualFundProduct.isPlowbackProduct())
        {
            // 1.8.1 getMainAccount()
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

            // 1.8.2 is�ݓ������J��()
            if (!l_mainAccount.isRuitoAccountOpen())
            {
                log.debug(" __�ݓ��������J�݃G���[__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " __�ݓ��������J�݃G���[__");
            }
        }
        
        //1.9.��d�����`�F�b�N
        //��d�����`�F�b�N 
        //�P�D��������������擾 
        //    �ȉ��̏����Œ����P�ʃe�[�u������������B  
        //    [��������]  
        StringBuffer l_strWhere = new StringBuffer();
        //    ����ID = ����.�⏕����.getAccountId() and  
        l_strWhere.append("account_id = ?");    
        //    �⏕����ID = ����.�⏕����.getSubAccountId() and 
        l_strWhere.append(" and sub_account_id = ?");
        //    ����ID = ����.�g�����M����.getProductId() and 
        l_strWhere.append(" and product_id = ?");
        //    ������� = OrderTypeEnum.�����M����W���� and 
        l_strWhere.append(" and order_type = ?");
        //    ( ������� = OrderStatusEnum.��t��(�V�K����) 
        //      or 
        //      ������� = OrderStatusEnum.������(�V�K����) 
        //      or 
        //      ������� = OrderStatusEnum.�������s�i��������j ) 
        l_strWhere.append(" and order_status in (?, ?, ?) ");    
        
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        long l_lngProductId = l_mutualFundProduct.getProductId();
        long l_lngOrderType = OrderTypeEnum.MF_RECRUIT.intValue();
        long l_lngOrderStatusOne = OrderStatusEnum.ACCEPTED.intValue();
        long l_lngOrderStatusTwo = OrderStatusEnum.ORDERED.intValue();
        long l_lngOrderStatusThree = OrderStatusEnum.NOT_ORDERED.intValue();
        Object[] l_objQuerys = 
            new Object[]{
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngProductId),
                new Long(l_lngOrderType),
                new Long(l_lngOrderStatusOne),
                new Long(l_lngOrderStatusTwo),
                new Long(l_lngOrderStatusThree)};
        List l_lisOrderUnitRow = null;
        try
        {            
            QueryProcessor l_queryProcessor = 
                Processors.getDefaultProcessor();
            l_lisOrderUnitRow = 
                l_queryProcessor.doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE, 
                    l_strWhere.toString(), 
                    null,
                    l_objQuerys);
                    
        } 
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundProductCategory");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�D������������̑��݃`�F�b�N 
        //    �P�D�Ŏ擾�������R�[�h������0�ȊO�̏ꍇ�A��O���X���[����B�i��d�����G���[�j 
        if (l_lisOrderUnitRow != null && !l_lisOrderUnitRow.isEmpty())
        {
            log.debug("��d�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02648,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��d�����G���[�B");
        }

    log.exiting(STR_METHOD_NAME);
   }
   
   /**
     * (calc�T�Z��n���)
     * �T�Z��n����i�T�Z��������A�T�Z���������j���Z�o���A <BR>
     * �T�Z��n����I�u�W�F�N�g�ɐݒ肵�ĕԋp����B <BR>
     * <BR>
     * �P�j����.����.is�O��MMF�@@= true�̏ꍇ<BR>
     * <BR>
     * �P-�P�j�@@this.calc�O��MMF�T�Z��n���()�ɏ������Ϗ�����B<BR>
     * <BR>
     * �Q)����.����.is�O��MMF�@@= false�̏ꍇ�A�ȉ������s����B<BR>
     * <BR>
     * �Q-�P�j���Y�ڋq�̏،���Ђ��T�Z����Z�o���Ɏ萔�����l�����邩�ǂ����𔻒肷��B <BR>
     * <BR>
     *    ����.�⏕����.getBranch().is���M�萔���v�Z()���R�[������B<BR> 
     * <BR>
     * �Q-�Q�j�i �Q-�P�j�̖߂�l �j == true�i�萔���v�Z�v�j �̏ꍇ <BR>
     * <BR>
     *    �i���M�j�v�Z�T�[�r�X.calc�T�Z��n���()�ɏ������Ϗ�����B <BR>
     * <BR>
     *    [����] <BR>
     *    �ڋq�F ����.�⏕����.getMainAccount()�̖߂�l <BR>
     *    �����F ����.���� <BR>
     *    �����i�抷��j�F ����.�����i�抷��j <BR>
     *    ����敪�F ����.�����敪 <BR>
     *    �����`���l���F ����.�����`���l�� <BR>
     *    �w��敪�F ����.�w����@@ <BR>
     *    �������ʁF ����.�������� <BR>
     *    �����敪�F ����.�������@@ <BR>
     *    �����敪�F ����.�����敪 <BR>
     *    �������F ����.������ <BR>
     * <BR>
     * �Q-�R�j�i �Q-�P�j�̖߂�l �j == false�i�萔���v�Z�s�v�j �̏ꍇ <BR>
     * <BR>
     *    this.calc�T�Z��n���()�ɏ������Ϗ�����B <BR>
     * <BR>
     *    [����] <BR>
     *    �⏕�����F ����.�⏕���� <BR>
     *    �g�����M�����F ����.���� <BR>
     *    �����敪�F ����.�����敪 <BR>
     *    �������ʁF ����.�������� <BR>
     *    �w����@@�F ����.�w����@@ <BR>
     *    ���ϕ��@@�F ����.���ϕ��@@ <BR>
     *  <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_mutualFundProduct - �g�����M����
     * @@param l_swtProduct - �����i�抷��j
     * @@param l_strProcessDiv - �����敪
     * @@param l_dblOrderQuantity - ��������
     * @@param l_strDesignateMethod - �w����@@
     * @@param l_strSettlementMethod - ���ϕ��@@
     * @@param l_strRequestMethod - �������@@
     * @@param l_strAccountDiv - �����敪
     * @@param l_strOrderChannel - �����`���l��
     * @@param l_datBizDate - ������
     * @@throws WEB3BaseException
     * @@roseuid 40B15E2F00C2
     */
    public WEB3MutualFundEstimatedPrice calcEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_mutualFundProduct,
        WEB3MutualFundProduct l_swtProduct, 
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettlementMethod, 
        String l_strRequestMethod, 
        String l_strAccountDiv, 
        String l_strOrderChannel, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcEstimateDeliveryAmount(SubAccount, " +
            "WEB3MutualFundProduct, WEB3MutualFundProduct, String," +
            "double, String, String, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
   
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //����.����.is�O��MMF�@@= true�̏ꍇ
        //this.calc�O��MMF�T�Z��n���()�ɏ������Ϗ�����B
        if (l_mutualFundProduct.isFrgnMmf())
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcFrgnMmfEstimateDeliveryAmount(
                l_subAccount,
                l_mutualFundProduct,
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_strDesignateMethod,
                l_strSettlementMethod);
        }

        //����.����.is�O��MMF�@@= false�̏ꍇ�A�ȉ������s����
        //�P�j���Y�ڋq�̏،���Ђ��T�Z����Z�o���Ɏ萔�����l�����邩�ǂ����𔻒肷��B 
        //����.�⏕����.getBranch().is���M�萔���v�Z()���R�[������B
        
        WEB3GentradeBranch l_gentradeBranch = 
            (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
        
        boolean l_blnIsMfCommCalc = l_gentradeBranch.isCommissionCalc();
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = null;
        
        //�Q�j�i �P�j�̖߂�l �j == true�i�萔���v�Z�v�j �̏ꍇ 
        //    �i���M�j�v�Z�T�[�r�X.calc�T�Z��n���()�ɏ������Ϗ�����B 
        //    [����] 
        //    �ڋq�F ����.�⏕����.getMainAccount()�̖߂�l 
        //    �����F ����.���� 
        //    �����i�抷��j�F ����.�����i�抷��j 
        //    ����敪�F ����.�����敪 
        //    �����`���l���F ����.�����`���l�� 
        //    �w��敪�F ����.�w����@@ 
        //    �������ʁF ����.�������� 
        //    �����敪�F ����.�������@@ 
        //    �����敪�F ����.�����敪 
        //    ���ϋ敪�F ����.���ϕ��@@ 
        //    �������F ����.������ 
        if (l_blnIsMfCommCalc)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
                (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();        
            
            l_mfEstimatedPrice = 
                l_mfBizLogicProvider.calcEstimatedPrice(
                    (WEB3GentradeMainAccount)l_subAccount.getMainAccount(), 
                    l_mutualFundProduct, 
                    l_swtProduct, 
                    l_strProcessDiv, 
                    l_strOrderChannel, 
                    l_strDesignateMethod, 
                    l_dblOrderQuantity, 
                    l_strRequestMethod, 
                    l_strAccountDiv, 
                    l_strSettlementMethod, 
                    l_datBizDate);
        }
        //�R�j�i �P�j�̖߂�l �j == false�i�萔���v�Z�s�v�j �̏ꍇ 
        //  this.calc�T�Z��n���()�ɏ������Ϗ�����B 
        //  [����] 
        //  �⏕�����F ����.�⏕���� 
        //  �g�����M�����F ����.���� 
        //  �����敪�F ����.�����敪 
        //  �������ʁF ����.�������� 
        //  �w����@@�F ����.�w����@@ 
        //  ���ϕ��@@�F ����.���ϕ��@@ 
        else
        {
            l_mfEstimatedPrice = 
                this.calcEstimateDeliveryAmount(
                    l_subAccount, 
                    l_mutualFundProduct, 
                    l_strProcessDiv, 
                    l_dblOrderQuantity, 
                    l_strDesignateMethod, 
                    l_strSettlementMethod);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mfEstimatedPrice;        
    }   

    /**
     * (validate�抷�\����)<BR>
     * �抷���Ə抷��̂��ꂼ��̖����ɂ��āA�抷�\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR> 
     * <BR>
     * �V�[�P���X�}�uvalidate�抷�\�����v�Q�� <BR>
     * =============================================== <BR>
     * 1.4 is�V�X�e���戵( ) <BR>
     * �@@�@@�߂�l == false �̏ꍇ�A(�戵�s�����G���[)��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00362 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.5 is���t�\(Date) <BR>
     * �@@�@@�߂�l == false �̏ꍇ�A(����s�����G���[)��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00363 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.8.2  is�O���،������J��( ) <BR>
     * �@@�@@�߂�l == false �̏ꍇ�A(�O���،��������J�݃G���[)��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_01341 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 1.9.2  is�ݓ������J��( ) <BR>
     * �@@�@@�߂�l == false �̏ꍇ�A(�ݓ��������J�݃G���[)��O���X���[����B<BR>
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00249 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_swtOriginProduct - �抷������<BR>
     * @@param l_swtPointProduct - �抷�����<BR>
     * @@param l_datBizDate - ������<BR>
     * @@return String
     * @@roseuid 40DBE07C01E3
     */
    public void validateSwitchingPossProduct(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_mfSwtProduct, 
        Date l_datBizDate) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSwitchingPossProduct(" +
                "SubAccount, WEB3MutualFundProduct, WEB3MutualFundProduct, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mfProduct == null || l_mfSwtProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //1.1 reset�����R�[�h(�����R�[�h : String)
        //����J�����_�R���e�L�X�g�̖����R�[�h���X�V����B 
        //[����] 
        //�����R�[�h�F ����.�抷�����.�����R�[�h 
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_mfSwtProduct.getProductCode());
       
        //1.2  reset������t�g�����U�N�V����(������t�g�����U�N�V���� : String)
        //����J�����_�R���e�L�X�g�̒�����t�g�����U�N�V�������X�V����B 
        //[����] 
        //������t�g�����U�N�V�����F �h���t�h 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        
        //1.3 setTimestamp( )
        //��t�����A���t���[�����Z�b�g����B
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        //1.4 is�V�X�e���戵( )(�g�����M����::is�V�X�e���戵)
        //�戵�\�����`�F�b�N���s���B
        boolean l_blnIsSystemHandle = l_mfSwtProduct.isSystemHandling();
        
        //�߂�l == false �̏ꍇ�A(�戵�s�����G���[)��O���X���[����B
        if(!l_blnIsSystemHandle)
        {
            log.debug("�戵�s�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵�s�����G���[�B");
        }
        
        //1.5 is���t�\(Date)(�g�����M����::is���t�\)
        //����\�����`�F�b�N���s���B 
        //[����] 
        //�������F ����.������ 
        boolean l_blnBuyPoss = l_mfSwtProduct.isAcquiredPossible(l_datBizDate);
        
        //�߂�l == false �̏ꍇ�A(����s�����G���[)��O���X���[����B
        if (!l_blnBuyPoss)
        {
            log.debug("����s�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����s�����G���[�B");
        }
        
        //1.6 validate�ً}��~(�g�����M����, String)
        //�ً}��~�`�F�b�N������ 
        // �m�����n 
        //�g�����M�����F ����.�抷����� 
        //�����敪�F �h���t�h 
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();
        
        l_validationsCheck.validateEmergencyStop(
            l_mfSwtProduct, 
            WEB3ProcessDivDef.BUY);

        //1.7 validate������t�\( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.8 (*) ����.�抷�����.is�O�����M()�̖߂�l == true or 
        //       ����.�抷�����.isFWF()�̖߂�l == true �̏ꍇ
        if (l_mfSwtProduct.isForeignFund() || l_mfSwtProduct.isFWF())
        {
            log.debug("����.�抷�����.is�O�����M()�̖߂�l == true or " +
                "����.�抷�����.isFWF()�̖߂�l == true �̏ꍇ");
            
            //1.8.1 getMainAccount( )
            //�ڋq�I�u�W�F�N�g���擾����B
            WEB3GentradeMainAccount l_genMainAccount = 
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
            
            //1.8.2  is�O���،������J��( )
            //�O���،��������J�݂���Ă��邩���`�F�b�N����B
            boolean l_blnForergnAccountOpen = l_genMainAccount.isForeignAccountOpen();
            
            //�߂�l == false �̏ꍇ�A(�O���،��������J�݃G���[)��O���X���[����B
            if (!l_blnForergnAccountOpen)
            {
                log.debug("�O���،��������J�݃G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O���،��������J�݃G���[�B");
            }
            
        }

        //1.9 (*) ����.�抷�����.is�ē�������()�̖߂�l == true �̏ꍇ
        if (l_mfSwtProduct.isPlowbackProduct())
        {
            log.debug("����.�抷�����.is�ē�������()�̖߂�l == true �̏ꍇ");
            
            // 1.9.1 getMainAccount()
            //�ڋq�I�u�W�F�N�g���擾����B
            WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount) l_subAccount.getMainAccount();

            // 1.9.2 is�ݓ������J��()
            //�ݓ��������J�݂���Ă��邩���`�F�b�N����B
            boolean l_blnRuitoAccountOpen = l_genMainAccount.isRuitoAccountOpen();

            //�߂�l == false �̏ꍇ�A(�ݓ��������J�݃G���[)��O���X���[����B
            if (!l_blnRuitoAccountOpen)
            {
                log.debug("�ݓ��������J�݃G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00249,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ݓ��������J�݃G���[�B");
            }
        }

        //1.10 reset�����R�[�h(�����R�[�h : String)
        //����J�����_�R���e�L�X�g�̖����R�[�h���X�V����B 
        //[����] 
        //�����R�[�h�F ����.�抷������.�����R�[�h 
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_mfProduct.getProductCode());
        
        //1.11 reset������t�g�����U�N�V����(������t�g�����U�N�V���� : String)
        //����J�����_�R���e�L�X�g�̒�����t�g�����U�N�V�������X�V����B 
        //[����] 
        //������t�g�����U�N�V�����F �h���t�h 
        WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
            WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        
        //1.12 setTimestamp( )
        //��t�����A���t���[�����Z�b�g����B
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        //1.13 is�V�X�e���戵( )(�g�����M����::is�V�X�e���戵)
        //�戵�\�����`�F�b�N������
        boolean l_blnIsSystemHandleOrigin = l_mfProduct.isSystemHandling();
        
        //�߂�l == false �̏ꍇ,��O(�戵�s�����G���[)���X���[����B
        if(!l_blnIsSystemHandleOrigin)
        {
            log.debug("�戵�s�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵�s�����G���[�B");
        }
        
        //1.14 is���抷�\(Date)(�g�����M����::is���抷�\)
        //      ����\�����`�F�b�N������ 
        //�mis���抷�\�ɓn���p�����^�n 
        //�@@�@@�@@�������F �擾�������M������
        boolean l_blnSellSwtPoss = 
            l_mfProduct.isSellSwitchingPossible(l_datBizDate);
        
        //�߂�l == false �̏ꍇ,��O(����s�����G���[)���X���[����B
        if (!l_blnSellSwtPoss)
        {
            log.debug("����s�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����s�����G���[�B");
        }
        
        //1.15 is�抷�\( )
        boolean l_blnIsSwitchingAble = l_mfProduct.isSwitchingAble();
        
        //�߂�l == false �̏ꍇ,��O(�抷�s�����G���[)���X���[����B
        if (!l_blnIsSwitchingAble)
        {
            log.debug("�抷�s�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00375,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�抷�s�����G���[�B");
        }
        
        //1.16 validate�ً}��~(�g�����M����, String)
        //      �ً}��~�`�F�b�N������ 
        //�mvalidate�ً}��~�ɓn���p�����^�n 
        //�@@�@@�@@�g�����M�����F �抷���̓��M���� 
        //�@@�@@�@@�����敪�F �h3�F�抷�h
        l_validationsCheck.validateEmergencyStop(
            l_mfProduct, 
            WEB3ProcessDivDef.SWITCHING);
        
        //1.17 validate������t�\( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * (get���򒥎��S����)<BR>
     * ���򒥎��̍S�����̃`�F�b�N���s���B<BR> 
     * <BR>
     * �P�j�ȉ��̏����𖞂����ꍇ�́A�ȍ~�̏������s���B <BR>
     *    �������Ȃ��ꍇ�́ADouble.NaN��ԋp����B <BR>
     * <BR>
     *    [����] <BR>
     *    ����.�⏕��������擾�����ڋq�̐ŋ敪 == �h������������򒥎��h and <BR>
     *    ����.�ۗL���YID����擾�����ۗL���Y.�ŋ敪 == �h����h <BR>
     * <BR>
     * �Q�j���򒥎��S�����̎擾 <BR>
     * <BR>
     *    ����.�⏕��������擾�������X�I�u�W�F�N�g.get���M���򒥎��S����()���R�[������B <BR>
     * <BR>
     *    �l���ԋp���ꂽ�ꍇ�́A���̐��l�����򒥎��S�����Ƃ���B <BR>
     *    Double.NaN���ԋp���ꂽ�ꍇ�́ADouble.NaN��ԋp����B <BR>
     * <BR>
     * �R�j�S�����̎Z�o <BR>
     * <BR>
     *    ����.�T�Z��n��� �~ �擾�������򒥎��S�����i�����_�ȉ��l�̌ܓ��j <BR>
     * <BR>
     *    ��ԋp����B <BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_strRequestDiv - �����敪<BR>
     * @@param l_strAssetId - �ۗL���YID<BR>
     * @@param l_dblEstimateDeliveryAmount - �T�Z��n���<BR>
     * @@return Double
     * @@throws WEB3BaseException
     * @@roseuid 40DBE07C01E3
     */
    public Double getWithholdingTaxRestriction(
        SubAccount l_subAccount, 
        String l_strRequestDiv, 
        String l_strAssetId, 
        double l_dblEstimateDeliveryAmount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getWithholdingTaxRestriction(" +
            "SubAccount, String, String, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //�P�j�ȉ��̏����𖞂����ꍇ�́A�ȍ~�̏������s���B 
        //�������Ȃ��ꍇ�́ADouble.NaN��ԋp����B 

        //[����] 
        //����.�⏕��������擾�����ڋq�̐ŋ敪 == �h������������򒥎��h and 
        //����.�ۗL���YID����擾�����ۗL���Y.�ŋ敪 == �h����h        
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_mfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule
                (ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        AssetRow l_assetRow = null;
        try
        {
            Asset l_asset = l_mfPositionMgr.getAsset(Long.parseLong(l_strAssetId));
            l_assetRow = (AssetRow) l_asset.getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        TaxTypeEnum l_taxType = l_mainAccountRow.getTaxType();
        if (TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType) && 
            TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()))
        {
            //�Q�j���򒥎��S�����̎擾 
            //����.�⏕��������擾�������X�I�u�W�F�N�g.get���M���򒥎��S����()���R�[������B 

            //�l���ԋp���ꂽ�ꍇ�́A���̐��l�����򒥎��S�����Ƃ���B 
            //Double.NaN���ԋp���ꂽ�ꍇ�́Anull��ԋp����B 
            WEB3GentradeBranch l_genBranch = 
                (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
            
            double l_dblRestrictionRate = l_genBranch.getWithholdingtaxRestrictionRate();
            log.debug("���M���򒥎��S���� = " + l_dblRestrictionRate);
            
            if (Double.isNaN(l_dblRestrictionRate))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("2) get���򒥎��S����() = null" );
                return null;
            }            
            //�R�j�S�����̎Z�o 
            //����.�T�Z��n��� �~ �擾�������򒥎��S�����i�����_�ȉ��l�̌ܓ��j��ԋp����B             
            double l_dblWithholdingTaxRestriction = 
                Math.round(l_dblEstimateDeliveryAmount * l_dblRestrictionRate);
            
            log.exiting(STR_METHOD_NAME);   
            log.debug("3) get���򒥎��S����() = " + l_dblWithholdingTaxRestriction);
            return new Double(l_dblWithholdingTaxRestriction);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);    
            log.debug("1) get���򒥎��S����() = null" );
            return null;
        }
    }
    
    /**
     * (validate�抷�攃�t�Œ���z)<BR>
     * �抷���̊T�Z��n������抷������̔��t�Œ���z�ȏォ�̃`�F�b�N���s���B<BR> 
     * <BR>
     * �P�j���`�F�b�N�����̗v�ۂ̊m�F <BR>
     * <BR>
     *    ����.�⏕��������擾�������X�I�u�W�F�N�g.is���M�抷�攃�t�Œ���z<BR> 
     *      �`�F�b�N���{()���R�[������B <BR>
     * <BR>
     *    �E�߂�l == true�i�`�F�b�N�v�j �̏ꍇ�A�ȍ~�̏������s���B <BR>
     *    �E�߂�l == false�i�`�F�b�N�s�v�j �̏ꍇ�A���������ɏ������I������B <BR>
     * <BR>
     * �Q�j���t�Œ���z�̃`�F�b�N <BR>
     * <BR>
     * �Q�|�P�j�V�K���t���ǉ����t���̔�����s���B<BR> 
     * <BR>
     *    �ۗL���Y�e�[�u�����������A0���̏ꍇ�͐V�K�����A<BR> 
     * 1���ȏ゠��ꍇ�͒ǉ������Ƃ���B <BR>
     * <BR>
     *    [����] <BR>
     *    �⏕����ID�F ����.�⏕����.�⏕����ID <BR>
     *    ����ID�F ����.�⏕��������擾��������ID <BR>
     *    ����ID�F ����.�抷�����.����ID <BR>
     * <BR>
     * �Q�|�Q�j�V�K���t�̏ꍇ <BR>
     * <BR>
     *    ����.�T�Z��n��� < ����.�抷�����.get�Œ���z�i�V�K���t�j()�̖߂�l <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR> 
     * <BR> 
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00343 <BR>
     * <BR>
     * �Q�|�R�j�ǉ����t�̏ꍇ <BR>
     * <BR>
     *    ����.�T�Z��n��� < ����.�抷�����.get�Œ���z�i�ǉ����t�j()�̖߂�l <BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B <BR>
     * <BR> 
     *          class        : WEB3BusinessLayerException <BR>
     *          tag          : BUSINESS_ERROR_00347 <BR>
     * <BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_strDesignDiv - �w��敪<BR>
     * @@param l_dblEstimateDeliveryAmount - �T�Z��n���<BR>
     * @@param l_swtProduct - �抷�����<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40DBE07C01E3
     */
    public void validateSwtBuyMinAmt(
        SubAccount l_subAccount, 
        String l_strDesignDiv, 
        double l_dblEstimateDeliveryAmount, 
        WEB3MutualFundProduct l_swtProduct) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateSwtBuyMinAmt(" +
            "SubAccount, String, double, WEB3MutualFundProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_swtProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //�P�j���`�F�b�N�����̗v�ۂ̊m�F 
        //����.�⏕��������擾�������X�I�u�W�F�N�g.is���M�抷�攃�t�Œ���z�`�F�b�N���{()���R�[������B 

        //�E�߂�l == true�i�`�F�b�N�v�j �̏ꍇ�A�ȍ~�̏������s���B 
        //�E�߂�l == false�i�`�F�b�N�s�v�j �̏ꍇ�A���������ɏ������I������B
       
        WEB3GentradeBranch l_genBranch = 
            (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        
        boolean l_blnBuyMinAmtCheck = 
            l_genBranch.isBuyingMinimumAmountCheck();
        
        if (l_blnBuyMinAmtCheck)
        {
            log.debug("is���M�抷�攃�t�Œ���z() = true");
            
            //�Q�j���t�Œ���z�̃`�F�b�N                 
            //�Q�|�P�j�V�K���t���ǉ����t���̔�����s���B 

            //   �ۗL���Y�e�[�u�����������A0���̏ꍇ�͐V�K���t�A1���ȏ゠��ꍇ�͒ǉ����t�Ƃ���B 
            //   [����] 
            //   �⏕����ID�F ����.�⏕����.�⏕����ID 
            //   ����ID�F ����.�⏕��������擾��������ID 
            //   ����ID�F ����.�抷�����.����ID 
                
            List l_lisAssets = new ArrayList();
            try
            {
                // �ۗL���Y�e�[�u���������B
                String l_strWhere = 
                    "account_id = ? and sub_account_id = ? "
                    + "and product_id = ? ";

                Object[] l_objWhereValues = {
                    new Long(l_subAccount.getAccountId()),
                    new Long(l_subAccount.getSubAccountId()),
                    new Long(l_swtProduct.getProductId())
                };
                // -�ۗL���Y�e�[�u�����������A�ۗL���YParams��List���擾����B
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisAssets = l_queryProcessor.doFindAllQuery(
                    AssetRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("Error In �ۗL���Y�e�[�u���������� ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            catch (DataQueryException l_ex)
            {
                log.error("Error In �ۗL���Y�e�[�u���������� ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex
                );
            }
            //0���̏ꍇ�͐V�K����
            if (l_lisAssets == null || l_lisAssets.size() == 0)
            {
                log.debug("0���̏ꍇ�͐V�K����");
                //�Q�|�Q�j�V�K���t�̏ꍇ 
                //����.�T�Z��n��� < ����.�抷�����.get�Œ���z�i�V�K���t�j()�̖߂�l 
                //�̏ꍇ�A��O���X���[����B 
                if (l_dblEstimateDeliveryAmount < l_swtProduct.getNewBuyMinAmt())
                {
                    log.debug("�抷�攃�t�Œ���z�i�V�K���t�j�G���[�B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02337,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�抷�攃�t�Œ���z�i�V�K���t�j�G���[�B");
                }

            }
            //1���ȏ゠��ꍇ�͒ǉ�����
            else
            {
                log.debug("1���ȏ゠��ꍇ�͒ǉ�����");
                //�Q�|�R�j�ǉ����t�̏ꍇ 
                //����.�T�Z��n��� < ����.�抷�����.get�Œ���z�i�ǉ����t�j()�̖߂�l 
                //�̏ꍇ�A��O���X���[����B 
                if (l_dblEstimateDeliveryAmount < l_swtProduct.getAddBuyMinAmt())
                {
                    log.debug("�抷�攃�t�Œ���z�i�ǉ����t�j�G���[�B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02338,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�抷�攃�t�Œ���z�i�ǉ����t�j�G���[�B");
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is�萔�������ڋq)<BR>
     * ����.�ڋq���A�����M������ɂ����Ď萔�����������ǂ����𔻒肷��B<BR> 
     * <BR>
     * �P�j�ȉ��̏����ŁA���M�T���Ώیڋq�e�[�u�����烌�R�[�h���擾����B<BR> 
     * <BR>
     *�@@�@@[����]<BR> 
     *�@@�@@�،���ЃR�[�h = ����.�ڋq.getInstitution().getInstitutionCode()�̖߂�l<BR> 
     *�@@�@@���X�R�[�h = ����.�ڋq.getBranch().getBranchCode()�̖߂�l <BR>
     *�@@�@@�ڋq�R�[�h = ����.�ڋq.getAccountCode()�̖߂�l <BR>
     *�@@�@@�T���敪 = �h�萔�������h <BR>
     * <BR>
     * �Q�j�P�j�Ń��R�[�h���擾�ł��Ȃ������ꍇ��false��ԋp����B<BR> 
     * <BR>
     * �R�j�P�j�Ń��R�[�h���擾�ł����ꍇ�́A�ȉ��̏����ŁA<BR>
     *�@@�@@ ���X�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR> 
     * <BR>
     * �@@[����]<BR> 
     *�@@���XID = ����.�ڋq.getBranch().getBranchId()�̖߂�l<BR> 
     *�@@�v���t�@@�����X�� = "mf.commission.free.product" <BR>
     *   <BR>
     *   �R-�P�j�R�j���擾�ł����ꍇ�A�擾�����e�v���t�@@�����X�̒l�ɂ���<BR>
     *   �@@�@@���L�̏����̃`�F�b�N���s���A<BR> 
     *   �@@�@@�@@�@@1�ł�true�ł����true���A���ׂ�false�ł����false��ԋp����B <BR>
     *   <BR>
     *   �@@�@@�@@�@@����.����.�����R�[�h.startWith(�v���t�@@�����X[i]�̒l)<BR> 
     *   <BR>
     *   �R-�Q�j�R�j���擾�ł��Ȃ������ꍇ�Atrue��ԋp����B<BR> 
     * <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g <BR>
     * @@param l_mutualFundProduct - (����)<BR>
     * �����I�u�W�F�N�g <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isCommissionFreeAccount(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mutualFundProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isCommissionFreeAccount(WEB3GentradeMainAccount, WEB3MutualFundProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_mutualFundProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strInstituionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strAccountCode = l_mainAccount.getAccountCode();
        try
        {
            MfExemptionAccountDao.findRowByPk(
                l_strInstituionCode,
                l_strBranchCode,
                l_strAccountCode,
                WEB3ExemptionDivDef.COMMISSION_FREE);
        }
        catch (DataFindException l_ex)
        {
            //�Q�j�P�j�Ń��R�[�h���擾�ł��Ȃ������ꍇ��false��ԋp����B
            log.debug("���M�T���Ώیڋq�e�[�u����" 
                + "�،���ЃR�[�h = " + l_strInstituionCode
                + " ���X�R�[�h = " + l_strBranchCode
                + " �ڋq�R�[�h = " + l_strAccountCode
                + " �T���敪 = " + "�萔������"
                + " �̃��R�[�h������");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" branch_id = ?");
        l_sbWhere.append(" and name = ?");
        
        long l_lngBranchId = l_mainAccount.getBranch().getBranchId();
        String l_strPreferencesName = WEB3BranchPreferencesNameDef.MF_COMMISSION_FREE_PRODUCT;
        List l_lisBranchPreferencesRows = new ArrayList();
        int l_intRecord = 0;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBranchPreferencesRows = l_queryProcessor.doFindAllQuery(
                BranchPreferencesRow.TYPE,
                l_sbWhere.toString(),
                new Object[]{new Long(l_lngBranchId), l_strPreferencesName});
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�R-�Q�j�R�j���擾�ł��Ȃ������ꍇ�Atrue��ԋp����B
        if (l_lisBranchPreferencesRows == null || l_lisBranchPreferencesRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�R-�P�j�R�j���擾�ł����ꍇ�A�擾�����e�v���t�@@�����X�̒l�ɂ���
        //      ���L�̏����̃`�F�b�N���s���A
        //1�ł�true�ł����true���A���ׂ�false�ł����false��ԋp����B
        for (int i = 0; i < l_lisBranchPreferencesRows.size(); i++)
        {         
            BranchPreferencesRow l_branchPreferencesRow = 
                (BranchPreferencesRow)l_lisBranchPreferencesRows.get(i);
            BranchPreferencesParams l_branchPreferencesParams = 
                new BranchPreferencesParams(l_branchPreferencesRow);
            
            //����.����.�����R�[�h.startWith(�v���t�@@�����X[i]�̒l)
            boolean l_blnIsValue = l_mutualFundProduct.getProductCode().startsWith(
                l_branchPreferencesParams.getValue());
            if (l_blnIsValue)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            
            l_intRecord++;
        }
        
        if (l_intRecord == l_lisBranchPreferencesRows.size())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (is��ېŌڋq)<BR>
     * <BR>
     * ����.�ڋq���A�����M������ɂ����Ĕ�ېł��ǂ����𔻒肷��B <BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA���M�T���Ώیڋq�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h = ����.�ڋq.getInstitution().getInstitutionCode()�̖߂�l<BR>
     * ���X�R�[�h = ����.�ڋq.getBranch().getBranchCode()�̖߂�l<BR>
     * �ڋq�R�[�h = ����.�ڋq.getAccountCode()�̖߂�l<BR>
     * �T���敪 = �h��ېŁh<BR>
     * <BR>
     * �Q�j���R�[�h���擾�ł����ꍇ��true���A�擾�ł��Ȃ������ꍇ��false��ԋp����B<BR>
     * <BR>
     * @@param l_mainAccount - �ڋq<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isTaxFreeAccount(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTaxFreeAccount(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        String l_strInstituionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strAccountCode = l_mainAccount.getAccountCode();
        try
        {
            MfExemptionAccountDao.findRowByPk(
                l_strInstituionCode,
                l_strBranchCode,
                l_strAccountCode,
                WEB3ExemptionDivDef.TAX_FREE);
        }
        catch (DataFindException e)
        {
            //�Q�j���R�[�h���擾�ł����ꍇ��true���A�擾�ł��Ȃ������ꍇ��false��ԋp����B
            log.debug("���M�T���Ώیڋq�e�[�u����" 
                + "�،���ЃR�[�h = " + l_strInstituionCode
                + " ���X�R�[�h = " + l_strBranchCode
                + " �ڋq�R�[�h = " + l_strAccountCode
                + " �T���敪 = " + "��ې�"
                + " �̃��R�[�h������");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (calc�O��MMF�T�Z��n���)<BR>
     * �����w�莞�͊T�Z��n����A���z�w�莞�͊T�Z�����������Z�o���āA<BR>
     * �T�Z��n����I�u�W�F�N�g�ɐݒ肵�ĕԋp����B <BR>
     * <BR>
     * (1) �T�Z��n����I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * <BR>
     * (2) �w����@@�������̏ꍇ�A�T�Z��n������Z�o����B<BR> 
     * <BR>
     * �@@(2-1) �T�Z����������Z�o���āA�T�Z��n����I�u�W�F�N�g�̊T�Z��������ɃZ�b�g����B<BR> 
     * �@@�@@�@@�i�v�Z�T�[�r�X��calc�O��MMF�T�Z�������()���R�[������j <BR>
     * <BR>
     * �@@�@@�@@�@@[calc�O��MMF�T�Z��n���()�̈���] <BR>
     * �@@�@@�@@�@@�����敪           �F�����̏����敪 <BR>
     * �@@�@@�@@�@@��������           �F�����̒������� <BR>
     * �@@�@@�@@�@@���ϕ��@@           �F�����̌��ϕ��@@ <BR>
     * �@@�@@�@@�@@�g�����M����       �F�����̊g�����M�����I�u�W�F�N�g <BR>
     * �@@�@@�@@�@@�T�Z��n���       �F�T�Z��n����I�u�W�F�N�g <BR>
     * <BR>
     * �@@(2-3)�T�Z��n����I�u�W�F�N�g�� �T�Z���������Ɉ����̒������ʂ��Z�b�g����B<BR>
     * <BR>
     * <BR>
     * (3) �w����@@�����z�w��̏ꍇ�A�T�Z�����������Z�o����B <BR>
     * <BR>
     * �@@(3-1) �T�Z�����������Z�o���āA�T�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����B<BR>
     * �@@�@@�@@�i�v�Z�T�[�r�X��calc�O��MMF�T�Z��������()���R�[������j <BR>
     * <BR>
     * �@@�@@�@@�@@[calc�O��MMF�T�Z���������̈���] <BR>
     * �@@�@@�@@�@@�����敪           �F�����̏����敪 <BR>
     * �@@�@@�@@�@@��������           �F�����̒������� <BR>
     * �@@�@@�@@�@@���ϕ��@@           �F�����̌��ϕ��@@ <BR>
     * �@@�@@�@@�@@�g�����M����       �F�����̊g�����M�����I�u�W�F�N�g <BR>
     * �@@�@@�@@�@@�T�Z��n���       �F�T�Z��n����I�u�W�F�N�g <BR>
     * <BR>
     * �@@(3-3) �T�Z��n����I�u�W�F�N�g�̊T�Z��n����ɁA�����̒������ʂ��Z�b�g����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_mutualFundProduct - (�g�����M����)<BR>
     * �g�����M����<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * �����敪 <BR>
     * <BR>
     * �P�F���t <BR>
     * �Q�F��� <BR>
     * �R�F�抷 <BR>
     * �S�F��W<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * ��������<BR>
     * <BR>
     * �����w��̏ꍇ�͒��������A���z�w��̏ꍇ�͒������z<BR>
     * @@param l_strDesignateMethod - (�w����@@)<BR>
     * �w����@@<BR>
     * <BR>
     * �R�F���z�w�� <BR>
     * �S�F�����w��<BR>
     * @@param l_strSettleDiv - (���ϋ敪)<BR>
     * ���ϕ��@@ <BR>
     * �P�F�~�� <BR>
     * �Q�F�O��<BR>
     * @@return WEB3MutualFundEstimatedPrice
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFundEstimatedPrice calcFrgnMmfEstimateDeliveryAmount(
        SubAccount l_subAccount,
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strProcessDiv,
        double l_dblOrderQuantity,
        String l_strDesignateMethod,
        String l_strSettleDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcFrgnMmfEstimateDeliveryAmount(" +
            "SubAccount, WEB3MutualFundProduct, String, double, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_mutualFundProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�T�Z��n����I�u�W�F�N�g�𐶐�����B
        WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice =
            new WEB3MutualFundEstimatedPrice();

        //���M�̌v�Z�T�[�r�X
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundBizLogicProvider l_mfBizLogicProvider =
            (WEB3MutualFundBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getBizLogicProvider();

        //�w����@@�������̏ꍇ�A�T�Z��n������Z�o����B
        //�T�Z����������Z�o���āA�T�Z��n����I�u�W�F�N�g�̊T�Z��������ɃZ�b�g����B
        //�i�v�Z�T�[�r�X��calc�O��MMF�T�Z�������()���R�[������j
        //[calc�O��MMF�T�Z��n���()�̈���]
        //�����敪           �F�����̏����敪
        //��������           �F�����̒�������
        //���ϕ��@@           �F�����̌��ϕ��@@
        //�g�����M����       �F�����̊g�����M�����I�u�W�F�N�g
        //�T�Z��n���       �F�T�Z��n����I�u�W�F�N�g
        if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_strDesignateMethod))
        {
            l_mfBizLogicProvider.calcFrgnMmfEstimatedTradeAmount(
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_strSettleDiv,
                l_mutualFundProduct,
                l_mutualFundEstimatedPrice);
            //�T�Z��n����I�u�W�F�N�g�� �T�Z���������Ɉ����̒������ʂ��Z�b�g����B
            l_mutualFundEstimatedPrice.setEstimatedQty(l_dblOrderQuantity);
        }

        //�w����@@�����z�w��̏ꍇ�A�T�Z�����������Z�o����B
        //�T�Z�����������Z�o���āA�T�Z��n����I�u�W�F�N�g�̊T�Z���������ɃZ�b�g����B
        //�i�v�Z�T�[�r�X��calc�O��MMF�T�Z��������()���R�[������j
        //[calc�O��MMF�T�Z���������̈���]
        //�����敪           �F�����̏����敪
        //��������           �F�����̒�������
        //���ϕ��@@           �F�����̌��ϕ��@@
        //�g�����M����       �F�����̊g�����M�����I�u�W�F�N�g
        //�T�Z��n���       �F�T�Z��n����I�u�W�F�N�g
        if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_strDesignateMethod))
        {
            l_mfBizLogicProvider.calcFrgnMmfEstimatedQty(
                l_strProcessDiv,
                l_dblOrderQuantity,
                l_strSettleDiv,
                l_mutualFundProduct,
                l_mutualFundEstimatedPrice);
            //�T�Z��n����I�u�W�F�N�g�̊T�Z��n����ɁA�����̒������ʂ��Z�b�g����B
            l_mutualFundEstimatedPrice.setEstimatedPrice(l_dblOrderQuantity);
        }

        log.exiting(STR_METHOD_NAME);
        return l_mutualFundEstimatedPrice;
    }
}
@
