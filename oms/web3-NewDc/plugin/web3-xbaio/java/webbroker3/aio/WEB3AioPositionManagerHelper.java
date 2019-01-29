head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���|�W�V�����w���p�[(WEB3AioPositionManagerHelper)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ����  (���u) �V�K�쐬
                   2004/10/27 ���z (���u) ���r���[
                   2008/12/03 SCS�哈�@@CFD�A�g�Ή�
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioPositionManagerHelper;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (���o���|�W�V�����w���p�[)<BR>
 * ���o���|�W�V�����w���p�[�N���X<BR>
 * �iAioPositionManagerHelper�̊g���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AioPositionManagerHelper extends AioPositionManagerHelper
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioPositionManagerHelper.class);
        
    /**
     * @@param arg0
     */
    public WEB3AioPositionManagerHelper(ProductTypeEnum arg0)
    {
        super(arg0);
    }
    /**
     * (is���o��)<BR>
     * �iisCashTransfer�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �����̒����P�ʂ��A�ȉ��̒�����ʂ̏ꍇ��true���A����ȊO��<BR>
     * �ꍇ��false��ԋp����B<BR>
     * <BR>
     *    1001�F �o������<BR>
     *    1002�F ��������<BR>
     *    1005�F �U�֒���(�a�������M�p�ۏ؋�)<BR>
     *    1006�F �U�֒���(�M�p�ۏ؋�����a���)<BR>
     *    1007�F �U�֒���(�a������犔��؋���)<BR>
     *    1008�F �U�֒���(����؋�������a���)<BR>
     *    1011�F �ב֕ۏ؋��U�֒���(�a�������ב֕ۏ؋�) <BR>
     *    1012�F �ב֕ۏ؋��U�֒���(�ב֕ۏ؋�����a���) <BR>
     *    1017�F ���̑��U�֒����i�a�������X�j<BR>
     *    1018�F ���̑��U�֒����iX����a����j<BR> 
     *    1021�FCFD�U�֒����i�a�������CFD�����j
     *    1022�FCFD�U�֒����iCFD��������a����j
     *    
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * @@return boolean
     * @@roseuid 413EDC8B0336
     */
    public boolean isCashTransfer(AioOrderUnit l_orderUnit)
    {
        final String STR_METHOD_NAME =
            "isCashTransfer(AioOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            log.debug("�����P�ʃI�u�W�F�N�g�I�u�W�F�N�gNull�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //===========�����P�ʃI�u�W�F�N�g������ʃ^�C�v���擾����==================
        log.debug("�����P�ʃI�u�W�F�N�g������ʃ^�C�v���擾���� = " + l_orderUnit.getOrderType());
        
        if (!((OrderTypeEnum.CASH_OUT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.CASH_IN.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(l_orderUnit.getOrderType()))
            || (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(l_orderUnit.getOrderType()))
        ))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    /**
     * (set���o�����z)<BR>
     * �isetAssetNetAmount�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ���o���z�̌v�Z���s���A�l���g�����U�N�V�����̍��ڂɃZ�b�g����B<BR>
     * <BR>
     * �P�j�g�����U�N�V����.�g�����U�N�V�����J�e�S�� = 10�i���o���j�̏ꍇ<BR>
     * 1�|�P�j <BR>
     * �g�����U�N�V����.�g�����U�N�V�����^�C�v = 10�i���������j �̏ꍇ <BR>
     * <BR>
     * ���o�����z = <BR>
     *      �g�����U�N�V����.���� - �g�����U�N�V����.�萔�� - �g�����U�N�V����.�萔������� <BR>
     * <BR>
     * 1�|�Q�j <BR>
     * �g�����U�N�V����.�g�����U�N�V�����^�C�v = 20�i�o�������j �̏ꍇ <BR>
     * <BR>
     * ���o�����z = <BR>
     *      -1 �~ �g�����U�N�V����.���� - �g�����U�N�V����.�萔�� - �g�����U�N�V����.�萔�������<BR>
     * <BR>
     * �Q�j�g�����U�N�V����.�g�����U�N�V�����J�e�S�� = 100�i�U�ցj�̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j�g�����U�N�V����.�⏕����ID����⏕�����I�u�W�F�N�g���擾���A<BR>
     *         �⏕�����^�C�v���擾����B<BR>
     * <BR>
     * �Q�|�Q�j�⏕�����^�C�v = 1�i�a��������j�̏ꍇ<BR>
     * <BR>
     * �Q�|�Q�|�P�j<BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1005<BR>
     *      �i�U�֒����i�a�������M�p�ۏ؋��j�j or<BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1007<BR>
     *      �i�U�֒����i�a������犔��؋����j�j or <BR> 
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1011 <BR>
     *      �i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j �̏ꍇ <BR>
     * <BR>
     *    ���o�����z = -1 �~ �g�����U�N�V����.���� - �g�����U�N�V����.<BR>
     * �萔�� - �g�����U�N�V����.�萔�������<BR>
     * <BR>
     * �Q�|�Q�|�Q�j<BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1006 <BR>
     *      �i�U�֒����i�M�p�ۏ؋�����a����j�j or <BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1008 <BR>
     *      �i�U�֒����i����؋�������a����j�j or <BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1012 <BR>
     *      �i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j �̏ꍇ <BR>
     * <BR>
     *    ���o�����z = �g�����U�N�V����.���� - �g�����U�N�V����.<BR>
     * �萔�� - �g�����U�N�V����.�萔�������<BR>
     * <BR>
     * �Q�|�R�j�⏕�����^�C�v = 2�i�ۏ؋������j�̏ꍇ<BR>
     * <BR>
     * �Q�|�R�|�P�j<BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1005<BR>
     * �i�U�֒����i�a�������M�p�ۏ؋��j�j �̏ꍇ<BR>
     * <BR>
     *    ���o�����z = �g�����U�N�V����.���� - �g�����U�N�V����.<BR>
     * �萔�� - �g�����U�N�V����.�萔�������<BR>
     * <BR>
     * �Q�|�R�|�Q�j<BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1006<BR>
     * �i�U�֒����i�M�p�ۏ؋�����a����j�j �̏ꍇ<BR>
     * <BR>
     *    ���o�����z = -1 �~ �g�����U�N�V����.���� - �g�����U�N�V����.<BR>
     * �萔�� - �g�����U�N�V����.�萔�������<BR>
     * <BR>
     * �Q�|�S�j�⏕�����^�C�v = 7�i�؋��������j�̏ꍇ<BR>
     * <BR>
     * �Q�|�S�|�P�j<BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1007<BR>
     * �i�U�֒����i�a������犔��؋����j�j �̏ꍇ<BR>
     * <BR>
     *    ���o�����z = �g�����U�N�V����.���� - �g�����U�N�V����.<BR>
     * �萔�� - �g�����U�N�V����.�萔�������<BR>
     * <BR>
     * �Q�|�S�|�Q�j<BR>
     *    �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1008<BR>
     * �i�U�֒����i����؋�������a����j�j �̏ꍇ<BR>
     * <BR>
     *    ���o�����z = -1 �~ �g�����U�N�V����.���� - �g�����U�N�V����.<BR>
     * �萔�� - �g�����U�N�V����.�萔�������<BR>
     * <BR>
     * �R�j�g�����U�N�V����.���z�ɁA���o�����z�̒l���Z�b�g����B<BR>
     * <BR>
     * @@param l_finTransactionParams - (�g�����U�N�V����)<BR>
     * �g�����U�N�V����Params�I�u�W�F�N�g<BR>
     * @@roseuid 413EDCB80123
     */
    protected void setAssetNetAmount(AioFinTransactionParams l_finTransactionParams)
    {
        final String STR_METHOD_NAME =
            "setAssetNetAmount(AioFinTransactionParams l_finTransactionParams)";
        log.entering(STR_METHOD_NAME);
        if (l_finTransactionParams == null)
        {
            log.debug("�g�����U�N�V����Params�I�u�W�F�N�gNull�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�g�����U�N�V����.�g�����U�N�V�����J�e�S�� = 10�i���o���j�̏ꍇ
        //  �X�[�p�[�N���X�̃��\�b�h���R�[������B
        if (FinTransactionCateg.CASH_IN_OUT.equals(
                l_finTransactionParams.getFinTransactionCateg()))
        {
            //1�|�P�j 
            //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 10�i���������j �̏ꍇ 
            //���o�����z = �g�����U�N�V����.���� - 
            //  �g�����U�N�V����.�萔�� - �g�����U�N�V����.�萔������� 
                        
            if (FinTransactionType.CREDIT.equals(
                    l_finTransactionParams.getFinTransactionType()))
            {
                double netAmount = l_finTransactionParams.getQuantity() - 
                        l_finTransactionParams.getCommissionFee() - 
                        l_finTransactionParams.getCommissionFeeTax();
                l_finTransactionParams.setNetAmount(netAmount);
            }           
            //1�|�Q�j 
            //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 20�i�o�������j �̏ꍇ 
            //���o�����z = -1 �~ �g�����U�N�V����.���� - 
            //  �g�����U�N�V����.�萔�� - �g�����U�N�V����.�萔������� 
            else if (FinTransactionType.DEBIT.equals(
                    l_finTransactionParams.getFinTransactionType()))
            {
                double netAmount = -1 * l_finTransactionParams.getQuantity() - 
                        l_finTransactionParams.getCommissionFee() - 
                        l_finTransactionParams.getCommissionFeeTax();
                l_finTransactionParams.setNetAmount(netAmount);
            }           
        }
        
        //�Q�j�g�����U�N�V����.�g�����U�N�V�����J�e�S�� = 100�i�U�ցj�̏ꍇ
        if (FinTransactionCateg.CASH_TRANSFER.equals(
                l_finTransactionParams.getFinTransactionCateg()))
        {
            // �Q�|�P�j�g�����U�N�V����.�⏕����ID����⏕�����I�u�W�F�N�g���擾���A
            // �⏕�����^�C�v���擾����B
            SubAccount l_subAccount = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //�g���A�J�E���g�}�l�[�W���擾
            WEB3GentradeAccountManager l_gentradeAccountManaer =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            try
            {
                l_subAccount =
                    l_gentradeAccountManaer.getSubAccount(
                        l_finTransactionParams.getAccountId(),
                        l_finTransactionParams.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(
                    "Not Found �Y���̕⏕����  with "
                        + "(�⏕����ID)l_lngSubAccountId =  "
                        + l_finTransactionParams.getSubAccountId()
                        + " and (����ID)l_lngAccountId = "
                        + l_finTransactionParams.getAccountId(), l_ex);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
           
           //=============���o�����z�^�C�v���擾����==================
            //���o�����z     
            double l_dblNetAmount = 0;
            int l_intCflag = -1;
            //�g�����U�N�V����.���ʂ��擾����
            double l_dblQuantity = l_finTransactionParams.getQuantity();
            //�g�����U�N�V����.�萔�����擾����
            double l_dblCommissionFee = l_finTransactionParams.getCommissionFee();
            //�g�����U�N�V����.�萔������ł��擾����
            double l_dblCommissionFeeTax = 
                l_finTransactionParams.getCommissionFeeTax();
            //=============���o�����z�^�C�v���擾����==================
               
            //�Q�|�Q�j�⏕�����^�C�v = 1�i�a��������j�̏ꍇ
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(
                    l_subAccount.getSubAccountType()))
            {
                //�Q�|�Q�|�P�j
                //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 1005
                //  �i�U�֒����i�a�������M�p�ۏ؋��j�j or
                //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 1007
                //  �i�U�֒����i�a������犔��؋����j�j or 
                //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 1011
                //  �i�ב֕ۏ؋��U�֒����i�a�������ב֕ۏ؋��j�j 
                //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 1021
            	//�@@�iCFD�U�֒����i�a�������CFD�����j�j�j �̏ꍇ 

                if ((FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                        l_finTransactionParams.getFinTransactionType())) || 
                    (FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
                        l_finTransactionParams.getFinTransactionType())) ||
                    (FinTransactionType.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType()))||
                    (FinTransactionType.CFD_FROM_DEPOSIT_AMOUNT.equals(
                                l_finTransactionParams.getFinTransactionType())))
                {
                    //���o�����z = -1 �~ �g�����U�N�V����.���� - �g�����U�N�V����.
                    // �萔�� - �g�����U�N�V����.�萔�������
                    l_dblNetAmount =
                        l_intCflag * l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
                    
                //�Q�|�Q�|�Q�j
                //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 1006
                //�i�U�֒����i�M�p�ۏ؋�����a����j�j or
                // �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1008
                //�i�U�֒����i����؋�������a����j�j or
                //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 1012
                //�i�ב֕ۏ؋��U�֒����i�ב֕ۏ؋�����a����j�j
                //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 1022
                //�iCFD�U�֒����iCFD��������a����j�j�j�̏ꍇ
                if ((FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType())) ||
                    (FinTransactionType.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType())) || 
                    (FinTransactionType.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                        l_finTransactionParams.getFinTransactionType()))|| 
                    (FinTransactionType.DEPOSIT_AMOUNT_FROM_CFD.equals(
                        l_finTransactionParams.getFinTransactionType())))
                {
                    //���o�����z = �g�����U�N�V����.���� - �g�����U�N�V����.
                    //�萔�� - �g�����U�N�V����.�萔�������
                    l_dblNetAmount =
                        l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
            }
                
            //�Q�|�R�j�⏕�����^�C�v = 2�i�ۏ؋������j�̏ꍇ
            else if (
                SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(
                    l_subAccount.getSubAccountType()))
            {
                //�Q�|�R�|�P�j
                // �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1005
                //�i�U�֒����i�a�������M�p�ۏ؋��j�j �̏ꍇ
                if (FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                        l_finTransactionParams.getFinTransactionType()))
                {
                    //���o�����z = �g�����U�N�V����.���� - �g�����U�N�V����.
                    // �萔�� - �g�����U�N�V����.�萔�������
                    l_dblNetAmount =
                        l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
                    
                // �Q�|�R�|�Q�j
                // �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1006
                //�i�U�֒����i�M�p�ۏ؋�����a����j�j �̏ꍇ
                if (FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType()))
                {
                    // ���o�����z = -1 �~ �g�����U�N�V����.���� - �g�����U�N�V����.
                    // �萔�� - �g�����U�N�V����.�萔�������
                    l_dblNetAmount =
                        l_intCflag * l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
            }
                
            // �Q�|�S�j�⏕�����^�C�v = 7�i�؋��������j�̏ꍇ
            else if (
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(
                    l_subAccount.getSubAccountType()))
            {
                // �Q�|�S�|�P�j
                //�g�����U�N�V����.�g�����U�N�V�����^�C�v = 1007
                //�i�U�֒����i�a������犔��؋����j�j �̏ꍇ
                if (FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
                        l_finTransactionParams.getFinTransactionType()))
                {
                    //���o�����z = �g�����U�N�V����.���� - �g�����U�N�V����.
                    //�萔�� - �g�����U�N�V����.�萔�������
                    l_dblNetAmount =
                        l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
                    
                // �Q�|�S�|�Q�j
                // �g�����U�N�V����.�g�����U�N�V�����^�C�v = 1008
                //�i�U�֒����i����؋�������a����j�j �̏ꍇ
                if (FinTransactionType.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
                        l_finTransactionParams.getFinTransactionType()))
                {
                    //���o�����z = -1 �~ �g�����U�N�V����.���� - �g�����U�N�V����.
                    //�萔�� - �g�����U�N�V����.�萔�������
                    l_dblNetAmount =
                        l_intCflag * l_dblQuantity - l_dblCommissionFee - l_dblCommissionFeeTax;
                }
            }
                
            // �R�j�g�����U�N�V����.���z�ɁA���o�����z�̒l���Z�b�g����B
            l_finTransactionParams.setNetAmount(l_dblNetAmount);    
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
