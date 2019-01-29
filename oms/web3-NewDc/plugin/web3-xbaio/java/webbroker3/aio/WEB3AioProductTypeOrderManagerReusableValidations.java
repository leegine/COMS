head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioProductTypeOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�������R���ʃ`�F�b�N(WEB3AioProductTypeOrderManagerReusableValidations)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���E (���u) �V�K�쐬      
                   2004/10/25 ������ (���u) ���r���[    
                   2005/10/20 ��O�� (���u) ���M�t�B�f���e�B�Ή�         
                   2006/03/24 �ʉ��iSRA�j �d�l�ύX�i���f���j522
                   2006/03/29 ���iSRA�j �d�l�ύX�i���f���j524
                   2006/05/10 �����i���u�j �d�l�ύX�i���f���j561�A566               
                   2006/11/02 ��� (SCS) ���f��No680�Ή�
Revesion History : 2008/09/22 ���u�� (���u) �d�l�ύX�i���f���j992,1004,1023,1044
Revesion History : 2009/03/12 �đo�g (���u) �d�l�ύX�i���f���j1109,1156
Revesion History : 2009/03/18 �Ԑi (���u) �d�l�ύX�i���f���j1121,1159
*/
package webbroker3.aio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioProductTypeOrderManagerReusableValidations;

import webbroker3.aio.data.AmountRangeDao;
import webbroker3.aio.data.AmountRangeRow;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3CfdAccOpenDivDef;
import webbroker3.common.define.WEB3FeqFirstTransferFlagDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3FundTypeDef;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.define.WEB3FxSystemDivDef;
import webbroker3.common.define.WEB3MrfAccOpenDivDef;
import webbroker3.common.define.WEB3MrfAllowDivDef;
import webbroker3.common.define.WEB3NameSerialNoDef;
import webbroker3.common.define.WEB3OnlineAccOpenDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransactionTypeDef;
import webbroker3.common.define.WEB3FxDeliveryDateInsertCheckDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���o�������R���ʃ`�F�b�N)<BR>
 * ���o�������R���ʃ`�F�b�N�N���X<BR>
 * �iAioProductTypeOrderManagerReusableValidations�̊g���N���X�j<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioProductTypeOrderManagerReusableValidations extends AioProductTypeOrderManagerReusableValidations 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioProductTypeOrderManagerReusableValidations.class); 
    
    /**
     * (validate������z)<BR>
     * �����z���A������z�𒴂��Ă��Ȃ������`�F�b�N����B<BR>
     * <BR>
     * �ȉ��̏����ɓ��Ă͂܂�ꍇ�A��O���X���[����B<BR>
     *   ����.���z > ����.������z
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00252<BR>
     * <BR>
     * @@param l_dblCreditAmount - (�����z)<BR>
     * ��ʂɂē��͂��ꂽ�����z
     * @@param l_dblMaxAmount - (������z)
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40E929120275
     */
    public void validateMaxAmount(double l_dblCreditAmount, double l_dblMaxAmount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateMaxAmount(double l_dblCreditAmount, " +
            "double l_dblMaxAmount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_dblCreditAmount > l_dblMaxAmount)
        {
            log.debug("����.���z > ����.������z");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����.���z[=" + l_dblCreditAmount +"] > ����.������z[=" + l_dblMaxAmount + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������z)<BR>
     * �����z���A�������z�𒴂��Ă��Ȃ������`�F�b�N����B<BR>
     * <BR>
     * �ȉ��̏����ɓ��Ă͂܂�ꍇ�A��O���X���[����B<BR>
     *   ����.���z < ����.�������z<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00253<BR>
     * <BR>
     * @@param l_dblCreditAmount - (�����z)<BR>
     * ��ʂɂē��͂��ꂽ�����z
     * @@param l_dblMinAmount - (�������z)
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40E9291D00FE
     */
    public void validateMinAmount(double l_dblCreditAmount, double l_dblMinAmount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateMinAmount(double l_dblCreditAmount, " +
            "double l_dblMinAmount) ";
        log.entering(STR_METHOD_NAME);

        if(l_dblCreditAmount < l_dblMinAmount)
        {
            log.debug("����.���z < ����.�������z");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00253,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����.���z[=" + l_dblCreditAmount +"] < ����.�������z[=" + l_dblMinAmount + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (validate�ŏ��P��)<BR>
     * �����z���ŏ��P�ʂŊ���؂�邩�ǂ������`�F�b�N����B<BR>
     * �����z �� �ŏ��P��<BR>
     * ��L�v�Z���̌��ʂ�����؂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00752<BR>
     * <BR>
     * @@param l_dblCreditAmount - (�����z)<BR>
     * ��ʂɂē��͂��ꂽ�����z
     * @@param l_dblSmallestUnit - (�ŏ��P��)
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40E929300051
     */
    public void validateSmallestUnit(double l_dblCreditAmount, double l_dblSmallestUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateSmallestUnit(double l_dblCreditAmount, " +
            "double l_dblSmallestUnit) ";
        log.entering(STR_METHOD_NAME);
        
        if(l_dblCreditAmount % l_dblSmallestUnit != 0)
        {
            log.debug("�����z �� �ŏ��P��,�v�Z���̌��ʂ�����؂�Ȃ��ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00752,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����z[=" + l_dblCreditAmount + "] �� �ŏ��P��[=" + l_dblSmallestUnit + 
                "],�v�Z���̌��ʂ�����؂�Ȃ�");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�����)<BR>
     * 1��������̓����񐔂�����񐔂��z���ĂȂ������`�F�b�N����B<BR>
     * �ȉ��̏����ƈ�v����ꍇ�A��O���X���[����B<BR>
     *    ����.�����_������+1 > ����.�����<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00753<BR>
     * <BR>
     * @@param l_lngPresentOrderCount - (�����_������)<BR>
     * �����_�ł�1���̓���������<BR>
     * @@param l_lngMaxCount - (1���̓��������)<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40E9295903AB
     */
    public void validateMaxCount(long l_lngPresentOrderCount, long l_lngMaxCount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateMaxCount(long l_lngPresentOrderCount, " +
            "long l_lngMaxCount)";
        log.entering(STR_METHOD_NAME);
         
        if((l_lngPresentOrderCount + 1) > l_lngMaxCount)
        {
            log.debug("����.�����_������+1 > ����.����񐔏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00753,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����.�����_������[=" + l_lngPresentOrderCount + 
                "] + 1  > ����.�����[=" + l_lngMaxCount + "]");
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate����������z )<BR>
     * 1��������̓������z�𒴂��ĂȂ������`�F�b�N����B<BR>
     * �ȉ��̏����ƈ�v����ꍇ�A��O���X���[����B<BR>
     *    ����.�����_�������z + ����.�����z > ����.������z<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00754<BR>
     * <BR>
     * @@param l_dblPresentCreditTotalAmount - (�����_�������z)<BR>
     * �����_�ł�1���̓������z
     * @@param l_dblCreditAmount - (�����z)<BR>
     * ����̓����z<BR>
     * @@param l_dblMaxTotalAmount - (������z)<BR>
     * 1���̓�������z<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 40F28FBD0161
     */
    public void validateTotalCreditMaxAmount(double l_dblPresentCreditTotalAmount,
        double l_dblCreditAmount, double l_dblMaxTotalAmount) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateTotalCreditMaxAmount(double l_dblPresentCreditTotalAmount, " +
            "double l_dblCreditAmount, double l_dblMaxTotalAmount)";
        log.entering(STR_METHOD_NAME);
     
        if(l_dblPresentCreditTotalAmount + l_dblCreditAmount > l_dblMaxTotalAmount)
        {
            log.debug("����.�����_�������z + ����.�����z > ����.������z�ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00754,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����.�����_�������z[=" + l_dblPresentCreditTotalAmount + 
                "] + ����.�����z[=" + l_dblCreditAmount + "] > ����.������z[=" + l_dblMaxTotalAmount + "]");
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�o�����z)<BR>
     * �o�����z�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���o�������P�ʃe�[�u�����������A���������̏o���z�̑��v���v�Z����B <BR>
     * �@@�|���o�������P�ʃe�[�u�����������A���o�������P��Params��List���擾����B<BR> 
     * �@@�@@[��������]  <BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId()�̖߂�l  <BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����.getSubAccountId()�̖߂�l  <BR>
     * �@@�@@�@@������� = 1001�i�o�������j  <BR>
     * �@@�@@�@@�����^�C�v = 5(�����j <BR>
     * �@@�@@�@@������� = 1�i��t�ρj  <BR>
     * �@@�@@�@@�����L����� = 1�i�I�[�v���j  <BR>
     * �@@�@@�@@��n�� = ����.��n��  <BR>
     * �@@�@@�@@�o���\���敪 = null or mf(���M���j <BR>
     * �@@�@@�@@�U�փ^�C�v = 2(�o���j <BR>
     * <BR>
     * �@@�|�擾�������o�������P��Params�̒������ʁiget��������()�ɂĎ擾�j<BR>
     *      �̑��v���v�Z����B <BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�����������ʂ̑��v + ����.���z ���v�Z����B <BR>
     * <BR>
     * �R�j  �Q�j�̌v�Z���� > 9���̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �S�j�ȉ��̏����ŁA��Еʎ�����z�e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     *   [��������] <BR>
     *   �،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l <BR>
     *   ���i�敪�F "5"�i�o���j <BR>
     *   �����敪�F "5"�i���̑��j <BR>
     * <BR>
     *   ���R�[�h���擾�ł��Ȃ������ꍇ�́A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00755<BR>
     * <BR>
     * �T�j�����������R�[�h�̎��������z�A����������z�ɂ��āA<BR>
     *      �ȉ��̏����̏ꍇ��O���X���[����B <BR>
     * <BR>
     *   ���������z < �Q�j�̌v�Z���� or ����������z > �Q�j�̌v�Z����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00756<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_dblNetAmount - (���z)<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 412DD8B100E9
     */
    public void validatePaymentAmount(
        SubAccount l_subAccount, 
        double l_dblNetAmount, 
        Date l_datDeliveryDate) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validatePaymentAmount(" +
            "SubAccount, double, Date) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null )
        {
            log.debug("�⏕������NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        //�P�j�@@���o�������P�ʃe�[�u�����������A���������̏o���z�̑��v���v�Z����B 
        //�|���o�������P�ʃe�[�u�����������A���o�������P��Params��List���擾����B 
        //�@@[��������]  
        //�@@�@@����ID = ����.�⏕����.getAccountId()�̖߂�l  
        //�@@�@@�⏕����ID = ����.�⏕����.getSubAccountId()�̖߂�l  
        //�@@�@@������� = 1001�i�o�������j  
        //�@@�@@�����^�C�v = 8(���o���j 
        //�@@�@@������� = 1�i��t�ρj  
        //�@@�@@�����L����� = 1�i�I�[�v���j  
        //�@@�@@��n�� = ����.��n��  
        //�@@�@@�o���\���敪 = null or mf(���M���j 
        //�@@�@@�U�փ^�C�v = 2(�o���j 
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append("account_id = ? ");
        l_sbWhere.append("and sub_account_id = ? ");
        l_sbWhere.append("and order_type = ? ");
        l_sbWhere.append("and product_type = ? ");
        l_sbWhere.append("and order_status = ? ");
        l_sbWhere.append("and order_open_status = ? ");
        l_sbWhere.append("and delivery_date = ? ");
        l_sbWhere.append("and (payment_application_div is null ");
        l_sbWhere.append("or payment_application_div = ? )");
        l_sbWhere.append("and transfer_type = ? ");
        
        Object[] l_objAioWhere = new Object[] {
            new Long(l_lngAccountId),
            new Long(l_lngSubAccountId),
            OrderTypeEnum.CASH_OUT,
            ProductTypeEnum.CASH,
            OrderStatusEnum.ACCEPTED,
            OrderOpenStatusEnum.OPEN,
            l_datDeliveryDate,
            WEB3AioPaymentApplicationDivDef.MF,
            AssetTransferTypeEnum.CASH_OUT};
        
        List l_listAioOrderUnitRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_listAioOrderUnitRows = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objAioWhere);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
        //�|�擾�������o�������P��Params�̒������ʁiget��������()�ɂĎ擾�j�̑��v���v�Z����B 
        double l_dblQuantityTotal = 0;       
        if (l_listAioOrderUnitRows != null && l_listAioOrderUnitRows.size() > 0)
        {           
            for (int i = 0; i < l_listAioOrderUnitRows.size(); i ++)
            {                
                AioOrderUnitRow l_orderUnitRow = 
                    (AioOrderUnitRow)l_listAioOrderUnitRows.get(i);
                
                l_dblQuantityTotal += l_orderUnitRow.getQuantity();
            }
        }
        
        //�Q�j�P�j�Ŏ擾�����������ʂ̑��v + ����.���z ���v�Z����B
        double l_dblCalcResult = 0; 
        l_dblCalcResult = l_dblQuantityTotal + l_dblNetAmount;
        log.debug("�����o�����z���v + ����.���z" + l_dblCalcResult);            
                  
        //�R�j  �Q�j�̌v�Z���� > 9���̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.formatNumber(l_dblCalcResult).length() > 9)
        {
            log.debug("�������ʂ�������ʂ𒴂��܂����B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00144,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ�������ʂ𒴂��܂����B");
        }      
        
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        log.debug("InstitutionCode = " + l_strInstitutionCode);
        try
        {
            //�S�j�ȉ��̏����ŁA��Еʎ�����z�e�[�u�����烌�R�[�h���擾����
            //[��������]
            //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
            //���i�敪�F "5"�i�o���j<BR>
            //�����敪�F "5"�i���̑��j<BR>
            AmountRangeRow l_amountRangeRow = 
                AmountRangeDao.findRowByInstitutionCodeFundTypeTransactionType(
                    l_strInstitutionCode, 
                    WEB3FundTypeDef.PAYMENT, 
                    WEB3TransactionTypeDef.DEFAULT);
            
            // ���R�[�h���擾�ł��Ȃ������ꍇ�́A��O���X���[����B
            if(l_amountRangeRow == null)
            {
                log.debug("��Еʎ�����z�e�[�u�����R�[�h���擾�ł��Ȃ������ꍇ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00755,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��Еʎ�����z�e�[�u�����R�[�h���擾�ł��Ȃ�����");                
            }
            log.debug("AmountRangeRow::InstitutionCode = " + l_amountRangeRow.getInstitutionCode());
            
            //�T�j�����������R�[�h�̎��������z�A����������z�ɂ��āA�ȉ��̏����̏ꍇ��O���X���[����B 
            //���������z < �Q�j�̌v�Z���� or ����������z > �Q�j�̌v�Z���� 

            long l_lngMaxAmount = l_amountRangeRow.getMaxAmount();
            long l_lngMinAmount = l_amountRangeRow.getMinAmount();
            if(l_lngMaxAmount < l_dblCalcResult || l_lngMinAmount > l_dblCalcResult)
            {
                log.debug("�o��������z�͈͊O�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00756,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���������z[=" + l_lngMaxAmount + "] < �o�����z���v[=" + l_dblCalcResult + 
                    "] or ����������z[=" + l_lngMinAmount + "] > �o�����z���v[=" + l_dblCalcResult + "]");
            }
            
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
   /*
    * (validate�o�����z)
    * �o�����z�̃`�F�b�N���s���B  <BR>
    * <BR>
    * �P�j�ȉ��̏����ŁA��Еʎ�����z�e�[�u�����烌�R�[�h���擾����B  <BR>
    * <BR>
    * [��������]  <BR>
    * �،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l <BR>
    * ���i�敪�F "5"�i�o���j  <BR>
    * �����敪�F "5"�i���̑��j<BR>  
    * <BR>
    * ���R�[�h���擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>  
    * <BR>
    *         class: WEB3BusinessLayerException<BR>
    *         tag:   BUSINESS_ERROR_00755<BR>
    * <BR>
    * �Q�j�����������R�[�h�̎��������z�A����������z�ɂ��āA<BR>
    * �ȉ��̏����̏ꍇ��O���X���[����B  <BR>
    * <BR>
    * ���������z < ����.���z or ����������z > ����.���z <BR>
    * <BR>
    *         class: WEB3BusinessLayerException<BR>
    *         tag:   BUSINESS_ERROR_00756<BR>
    * <BR>
    * @@param l_subAccount - (�⏕����)
    * @@param l_dblNetAmount - (���z)<BR>
    * @@throws WEB3BaseException
    * @@roseuid 412DD8B100E9
    */
   
   public void validatePaymentAmount(SubAccount l_subAccount, double l_dblNetAmount) 
       throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "validatePaymentAmount(" +
           "SubAccount l_subAccount, double l_dblNetAmount) ";
       log.entering(STR_METHOD_NAME);
       
       if (l_subAccount == null )
       {
           log.debug("�⏕������NULL");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }       
       
       String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
       try
       {
           //�P�j�ȉ��̏����ŁA��Еʎ�����z�e�[�u�����烌�R�[�h���擾����
           //[��������]
           //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
           //���i�敪�F "5"�i�o���j<BR>
           //�����敪�F "5"�i���̑��j<BR>
           AmountRangeRow l_amountRangeRow = AmountRangeDao.findRowByInstitutionCodeFundTypeTransactionType(
               l_strInstitutionCode, 
               WEB3FundTypeDef.PAYMENT, 
               WEB3TransactionTypeDef.DEFAULT);
           
           if(l_amountRangeRow == null)
           {
               log.debug("��Еʎ�����z�e�[�u�����R�[�h���擾�ł��Ȃ������ꍇ");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00755,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "��Еʎ�����z�e�[�u�����R�[�h���擾�ł��Ȃ�����");                
           }
           
           //�Q�j�����������R�[�h�̎��������z�A����������z�ɂ��āA�ȉ��̏����̏ꍇ��O���X���[����B
           //���������z < ����.���z or ����������z > ����.���z
           long l_lngMaxAmount = l_amountRangeRow.getMaxAmount();
           long l_lngMinAmount = l_amountRangeRow.getMinAmount();
           if(l_lngMaxAmount < l_dblNetAmount || l_lngMinAmount > l_dblNetAmount)
           {
               log.debug("�o��������z�͈͊O�G���[");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00756,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "���������z[=" + l_lngMaxAmount + "] < ����.���z[=" + l_dblNetAmount + 
                   "] or ����������z[=" + l_lngMinAmount + "] > ����.���z[=" + l_dblNetAmount + "]");
           }           
       }
       catch (DataException l_ex)
       {
           log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }
   }
    
    /**
     * (validate�o���d������)<BR>
     * �����̎�n���Ɠ������ɂ��łɏo���������o�ĂȂ����ǂ������`�F�b�N����B<BR>
     * <BR>
     * ���d����������Ȃ��̂́A�ʏ�̏o�������̂݁B<BR>
     * <BR>
     * �P�j�ȉ��̏����ɂĒ����P�ʃe�[�u���̃��R�[�h���擾����B<BR>
     * <BR>
     *    [��������]<BR>
     *    ����ID = ����.�⏕����.getAccountId()�̖߂�l<BR>
     *    �⏕����ID = ����.�⏕����.getSubAccountId()�̖߂�l<BR>
     *    ������� = 1001�i�o�������j<BR>
     *    ������� = 1�i��t�ρj or 2�i�������j or 3�i�����ρj<BR>
     *    �����L����� = 1�i�I�[�v���j<BR>
     *    ��n�� = ����.��n��<BR>
     *    �o���\���敪 = null<BR>
     * <BR>
     * �Q�j�擾�������R�[�h���� > 0 �̏ꍇ�́A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00757<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_datDeliveryDate - (��n��)<BR>
     * �����̎�n��
     * @@throws WEB3BusinessLayerException
     * @@roseuid 412DD96601D3
     */
    public void validateCashoutDuplicateOrder(SubAccount l_subAccount, Date l_datDeliveryDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCashoutDuplicateOrder(SubAccount l_subAccount, " +
            "Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null 
            || l_datDeliveryDate == null)
        {
            log.debug("�⏕����/��n����NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        
        try
        {
            //�P�j�ȉ��̏����ɂĒ����P�ʃe�[�u���̃��R�[�h���擾����
            long l_lngAccountId = l_subAccount.getAccountId();
            long l_lngSubAccountId = l_subAccount.getSubAccountId();   
            log.debug("SubAccount::AccountId = " + l_lngAccountId);
            log.debug("SubAccount::SubAccountId = " + l_lngSubAccountId);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("account_id = ? ");
            l_sbWhere.append("and sub_account_id = ? ");
            l_sbWhere.append("and order_type = ? ");
            l_sbWhere.append("and (order_status = ? or order_status = ? or order_status = ? ) ");
            l_sbWhere.append("and order_open_status = ? ");
            l_sbWhere.append("and delivery_date = ? ");
            l_sbWhere.append("and payment_application_div is null");
            
            Object[] l_objAioWhere = new Object[] {
                    String.valueOf(l_lngAccountId),
                    String.valueOf(l_lngSubAccountId),
                    String.valueOf(OrderTypeEnum.IntValues.CASH_OUT),
                    Integer.toString(OrderStatusEnum.IntValues.ACCEPTED),
                    Integer.toString(OrderStatusEnum.IntValues.ORDERING),
                    Integer.toString(OrderStatusEnum.IntValues.ORDERED),
                    String.valueOf(OrderOpenStatusEnum.IntValues.OPEN),
                    l_datDeliveryDate};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listFindAllQuery = l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAioWhere);
            
            log.debug("AioOrderUnitRow.size() = " + l_listFindAllQuery.size());
            //�Q�j�擾�������R�[�h���� > 0 �̏ꍇ�́A��O���X���[����
            if(l_listFindAllQuery.size() > 0)
            {
                log.debug("�w�肵�����U���\����Ɠ����U�����̏o���\�������łɓo�^����Ă��܂��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00757,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�擾�������R�[�h����[=" + l_listFindAllQuery.size() + "] > 0");                
            }
            
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�敨��������J��)<BR>
     * �ڋq���敨����������J�݂��Ă��邩���`�F�b�N����B <BR>
     * <BR>
     * �P�j�ڋq�I�u�W�F�N�g�擾 <BR>
     *   �⏕����.getMainAccount()�ɂČڋq�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�����J�݋敪�`�F�b�N <BR>
     * <BR>
     *   �ȉ��̍��ڂ̉��ꂩ���h�敨�����J�݁h�܂��́h�敨OP�����J�݁h�ɂȂ��Ă��邱�ƁB <BR>
     *   ���ׂĂ̍��ڂ��hDEFAULT�i�����Ȃ��j�h�܂��́hOP�����J�݁h�ł���Η�O���X���[����B <BR>
     *     �ڋq.�敨OP�����J�݋敪�i���؁j <BR>
     *     �ڋq.�敨OP�����J�݋敪�i��؁j <BR>
     *     �ڋq.�敨OP�����J�݋敪�i���؁j <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00284<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4134447B01F9
     */
    public void validateOpenFuturesTradedAccount(SubAccount l_subAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOpenFuturesTradedAccount(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�⏕������NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�ڋq�I�u�W�F�N�g�擾
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        
        //�Q�j�����J�݋敪�`�F�b�N
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //�敨OP�����J�݋敪�i���؁j
        String l_strIfoAccOpenDivTokyo =  l_mainAccountRow.getIfoAccOpenDivTokyo();

        //�敨OP�����J�݋敪�i��؁j
        String l_strIfoAccOpenDivOsaka =  l_mainAccountRow.getIfoAccOpenDivOsaka();

        //�敨OP�����J�݋敪�i���؁j
        String l_strIfoAccOpenDivNagoya =  l_mainAccountRow.getIfoAccOpenDivNagoya();
        
        boolean l_blnTokyo = 
            WEB3FutureOpAccountDef.DEFAULT.equals(l_strIfoAccOpenDivTokyo) 
            || WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo);
        
        boolean l_blnOsaka = 
            WEB3FutureOpAccountDef.DEFAULT.equals(l_strIfoAccOpenDivOsaka) 
            || WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka);

        boolean l_blnNagoya = 
            WEB3FutureOpAccountDef.DEFAULT.equals(l_strIfoAccOpenDivNagoya) 
            || WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya);
        log.debug("l_blnTokyo = " + l_blnTokyo + "  l_blnOsaka = " + l_blnOsaka + "  l_blnNagoya = " + l_blnNagoya);
        if(l_blnTokyo && l_blnOsaka && l_blnNagoya)
        {
            log.debug("���q�l�͐敨�������J�݂���Ă���܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00284,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq.�敨OP�����J�݋敪�i���؁j�ƌڋq.�敨OP�����J�݋敪�i��؁j��" +
                "�ڋq.�敨OP�����J�݋敪�i���؁j�̍��ڂ��hDEFAULT�i�����Ȃ��j�h�܂��́hOP�����J�݁h");             
        }
    }
    /**
     * ()
     * ���N���X�̃C���X�^���X��o�^����static���\�b�h�B<BR>
     * <BR>
     * �P�j ���o�������R���ʃ`�F�b�N.setInstance()���R�[������B<BR>
     * �@@�msetInstance�ɓn���p�����^�n<BR>
     * �@@�@@�C���X�^���X�F new ���o�������R���ʃ`�F�b�N()<BR>
     * @@roseuid 40C6A9540350
     */
    public static void register()
    {
        final String STR_METHOD_NAME = "register()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j ���o�������R���ʃ`�F�b�N.setInstance()���R�[������
        WEB3AioProductTypeOrderManagerReusableValidations.setInstance(
            new WEB3AioProductTypeOrderManagerReusableValidations());
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateFX�����J�݉\)
     * <BR>
     * �ڋq��FX��������J�݉\�ł��邩�`�F�b�N���s���B <BR>
     * <BR>
     * �P�jFX�f�[�^����T�[�r�X.getFX�ڋq()���R�[������B <BR>
     * <BR>
     * �@@�P�|�P�jDB����<BR>
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode()<BR>
     * �@@�@@�@@FX�V�X�e���R�[�h�F����.��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h<BR>
     * �@@�@@�@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode()<BR>
     * <BR>
     * �@@�P�|�Q�j��O����<BR>
     * �@@�@@getFX�ڋq()��FX�ڋqParams���擾�ł����ꍇ�ȉ��̏����ŗ�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�P�|�Q�|�P�j���L�����ɂė�O��throw����B<BR>
     * �@@�@@�@@������.��Е�FX�V�X�e������Params.FX�V�X�e���敪==2�iCFD�V�X�e���j�̏ꍇ��<BR>
     * �@@�@@�@@�uCFD�����J�ݏ������G���[�v�Ƃ��ė�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@: BUSINESS_ERROR_03133<BR>
     * �@@�@@�@@������.��Е�FX�V�X�e������Params.FX�V�X�e���敪==2�iCFD�V�X�e���j�ȊO�̏ꍇ��<BR>
     * �@@�@@�@@�u�����J�ݏ������G���[�v�Ƃ��ė�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02423<BR>
     * <BR>
     *  �Q�j<BR>
     * �@@�@@����.��Е�FX�V�X�e������Params.�I�����C�������J�ݎ��{�敪<BR>
     * �@@�@@== �O�F�I�����C�������J�ݖ����{ �̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�Q-�P�jFX�f�[�^����T�[�r�X.getGFT�����J�ݏ�()���R�[������B<BR>
     * <BR>
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@��������������F" institution_code=? and branch_code=?<BR>
     * �@@�@@�@@�@@and account_code=? and account_open_status_div in (?,?) "<BR>
     * �@@�@@�@@���������f�[�^�R���e�i�F�i�ȉ��̗v�f�̔z��j<BR>
     * �@@�@@�@@�@@�@@�@@����.�⏕����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@�@@����.�⏕����.get����X().getBranchCode()<BR>
     * �@@�@@�@@�@@�@@�@@����.�⏕����.getMainAccount().getAccountCode()<BR>
     * �@@�@@�@@�@@�@@�@@�����J�ݏ󋵋敪."�����J�ݒ�"<BR>
     * �@@�@@�@@�@@�@@�@@�����J�ݏ󋵋敪."�_�E�����[�h��"<BR>
     * �@@�@@�@@�\�[�g�����F null<BR>
     * <BR>
     * �@@�@@�Q-�Q�j��O��throw<BR>
     * �@@�@@�@@�Q-�P�j�ɂă��R�[�h���擾�ł����ꍇ�ȉ��̏����ŗ�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�Q�|�Q-�P�j���L�����ɂė�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@������.��Е�FX�V�X�e������Params.FX�V�X�e���敪==2�iCFD�V�X�e���j�̏ꍇ��<BR>
     * �@@�@@�@@�uCFD�����J�ݏ������G���[�v�Ƃ��ė�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@: BUSINESS_ERROR_03133<BR>
     * �@@�@@�@@������.��Е�FX�V�X�e������Params.FX�V�X�e���敪==2�iCFD�V�X�e���j�ȊO�̏ꍇ��<BR>
     * �@@�@@�@@�u�����J�ݏ������G���[�v�Ƃ��ė�O��throw����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02423<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g <BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateFXAccOpenPossible(
            SubAccount l_subAccount, 
            CompFxConditionParams l_compFxConditionParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateFXAccOpenPossible(SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_compFxConditionParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�jFX�f�[�^����T�[�r�X.getFX�ڋq()���R�[������B
        //�P�|�P�jDB����
        //[�����̐ݒ�]
        //�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h
        //���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F����.��Е�FX�V�X�e������Params.FX�V�X�e���R�[�h
        //�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode()
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
            l_fxDataControlService.getFXAccount(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_compFxConditionParams.getFxSystemCode(),
                l_subAccount.getMainAccount().getAccountCode());

            //getFX�ڋq()��FX�ڋqParams���擾�ł����ꍇ�ȉ��̏����ŗ�O��throw����B
            //����.��Е�FX�V�X�e������Params.FX�V�X�e���敪==2�iCFD�V�X�e���j�̏ꍇ
            //�uCFD�����J�ݏ������G���[�v�Ƃ��ė�O��throw����B
            if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(l_compFxConditionParams.getFxSystemDiv()))
            {
                log.debug("CFD�����J�ݏ������ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03133,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "CFD�����J�ݏ������ł��B");
            }
            //������.��Е�FX�V�X�e������Params.FX�V�X�e���敪==2�iCFD�V�X�e���j�ȊO�̏ꍇ��
            else
            {
                log.debug("���݁AFX�����J�ݏ������ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02423,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���݁AFX�����J�ݏ������ł��B");
            }
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__" + l_ex.getMessage());
        }

        //�Q�j����.��Е�FX�V�X�e������Params.�I�����C�������J�ݎ��{�敪
        //== �O�F�I�����C�������J�ݖ����{ �̏ꍇ�́A�ȉ��̏������s���B
        if (WEB3OnlineAccOpenDef.ONLINE_ACC_OPEN_NOT_ENFORCEMENT.equals(
            l_compFxConditionParams.getOnlineAccOpen()))
        {
        	//�Q-�P�jFX�f�[�^����T�[�r�X.getGFT�����J�ݏ�()���R�[������B
        	//��������������F" institution_code=? and branch_code=?
        	//and account_code=? and account_open_status_div in (?,?) "
        	//���������f�[�^�R���e�i�F�i�ȉ��̗v�f�̔z��j
        	//����.�⏕����.�،���ЃR�[�h
        	//����.�⏕����.get����X().getBranchCode()
            //����.�⏕����.getMainAccount().getAccountCode()
        	//�����J�ݏ󋵋敪."�����J�ݒ�"
        	//�����J�ݏ󋵋敪."�_�E�����[�h��"
        	//�\�[�g�����F null
        	String l_strWhere = " institution_code=? and branch_code=? "
                + "and account_code=? and account_open_status_div in (?,?) ";
        	String[] l_strQueryContainers = {
                l_subAccount.getInstitution().getInstitutionCode(),
        	    l_subAccount.getMainAccount().getBranch().getBranchCode(),
        	    l_subAccount.getMainAccount().getAccountCode(),
        	    WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING,
        	    WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE};

            GftAccountOpenStatusParams[] l_gftAccountOpenStatusParams =
                l_fxDataControlService.getGFTAccountOpenStatuses(
                    l_strWhere,
                    l_strQueryContainers,
                    null);

        	//��O��throw
            //�Q-�P�j�ɂă��R�[�h���擾�ł����ꍇ�ȉ��̏����ŗ�O��throw����B
            if (l_gftAccountOpenStatusParams.length > 0)
            {
            	//������.��Е�FX�V�X�e������Params.FX�V�X�e���敪==2�iCFD�V�X�e���j�̏ꍇ��
            	//�uCFD�����J�ݏ������G���[�v�Ƃ��ė�O��throw����B
            	if (WEB3FxSystemDivDef.CFD_SYSTEM.equals(
                    l_compFxConditionParams.getFxSystemDiv()))
            	{
                    log.debug("CFD�����J�ݏ������ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03133,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "CFD�����J�ݏ������ł��B");
            	}
                //������.��Е�FX�V�X�e������Params.FX�V�X�e���敪==2�iCFD�V�X�e���j�ȊO�̏ꍇ��
            	else
            	{
            	    //�u�����J�ݏ������G���[�v�Ƃ��ė�O��throw����B
                    log.debug("���݁AFX�����J�ݏ������ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02423,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���݁AFX�����J�ݏ������ł��B");
            	}
            }
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validateFX�U�։\)
     * <BR>
     *�ڋq��FX�U�֎���\�ł��邩�`�F�b�N���s���B<BR>
     *<BR>
     *�P�jFX�����J�݃`�F�b�N<BR>
     *<BR>
     *�@@FX�f�[�^����T�[�r�X.getFX�ڋq()���R�[������B<BR>
     *<BR>
     *�@@[�����̐ݒ�]<BR>
     *�@@�@@�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h<BR>
     *�@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode()<BR>
     *�@@�@@FX�V�X�e���R�[�h�F�@@����.FX�V�X�e���R�[�h<BR>
     *�@@�@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode()<BR>
     *<BR>
     *�@@getFX�ڋq()��FX�ڋqParams���擾�ł��Ȃ������ꍇ(FX�������J�݁j�A��O��throw����B<BR>
     *<BR>
     *�@@getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 = �h2�F�U�֒�~�h�̏ꍇ�A<BR>
     *�@@��O��throw����B�iBUSINESS_ERROR_02440�j<BR>
     *<BR>
     *�@@getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 != �h1�F�J�ݍρh�̏ꍇ�A<BR>
     *�@@��O��throw����B�iBUSINESS_ERROR_01867�j<BR>
     *<BR>
     *�Q�jMRF�����J�݃`�F�b�N<BR>
     *<BR>
     *�@@�Q)-�P�@@�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B <BR>
     *<BR>
     *�@@[����] <BR>
     *�@@�@@���XID = �⏕����.getBranch().���XID <BR>
     *�@@�@@�v���t�@@�����X�� = "fx.deliverydate.insert.check" <BR>
     *�@@�@@�v���t�@@�����X���̘A�� = 1 <BR>
     *<BR>
     *�@@�Q)-�Q�@@�擾�����v���t�@@�����X�l=="��n�����Z�b�g���Ȃ�"�̏ꍇ�A <BR>
     *�@@�@@�@@�@@�@@����.�⏕����.getBranch().isFX_MRF�����J�݃`�F�b�N���{()���R�[������B<BR>
     *<BR>
     *  �߂�l == true �̏ꍇ�́A�Q�j�|�R�̏������s���B<BR>
     *  �߂�l == false �̏ꍇ�́A�Q�j�|�R�̏������X�L�b�v����B<BR>
     *<BR>
     *�@@�Q)�|�R�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂ��ڋq�I�u�W�F�N�g���擾����B<BR>
     *<BR>
     *�@@[�����̐ݒ�]<BR>
     *�@@�@@����ID�F�@@����.�⏕����.getAccountId()<BR>
     *<BR>
     *�@@�ڋq.MRF�����J�݋敪 != �h0�FDEFAULT(�����Ȃ�)�h�̏ꍇ(MRF�����J�ݍ�)�A��O��throw����B<BR>
     *<BR>
     *�R�jFX�����J�݃`�F�b�N<BR>
     *<BR>
     *  �ڋq.FX�����J�݋敪 != �h�����J�݁h �̏ꍇ�A��O��throw����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g <BR>
     * @@param l_strFXSystemCode - FX�V�X�e���R�[�h <BR>
     * @@throws WEB3BaseException
     */
    public void validateFXTransferPossible(
            SubAccount l_subAccount, 
            String l_strFXSystemCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXTransferPossible(" +
            "SubAccount l_subAccount, String l_strFXSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�jFX�����J�݃`�F�b�N 
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
            //NotFoundException
            FxAccountParams l_fxAccountParams = 
                l_fxDataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_strFXSystemCode, 
                    l_subAccount.getMainAccount().getAccountCode());
            
            //getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 = �h2�F�U�֒�~�h�̏ꍇ�A
            //��O��throw����B�iBUSINESS_ERROR_02440�j
            if (WEB3AioAccountDivDef.TRANSFER_STOP.equals(l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("�U�֒�~���G���[�B");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02440,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�U�֒�~���G���[�B");
            }
            
            //getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 != �h1�F�J�ݍρh�̏ꍇ�A
            //��O��throw����B�iBUSINESS_ERROR_01867�j
            if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("�ב֕ۏ؋�����������s�̏�Ԃł��B");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01867,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ב֕ۏ؋�����������s�̏�Ԃł��B");
            }
            		
        }
        //getFX�ڋq()��FX�ڋqParams���擾�ł��Ȃ������ꍇ(FX�������J�݁j�A��O��throw����B 
        catch (NotFoundException l_ex)
        {
            log.debug("�ב֕ۏ؋����������J�݂ł��B", l_ex);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ב֕ۏ؋����������J�݂ł��B");
        }        
        try
        {
            //�g���A�J�E���g�}�l�[�W���擾����   
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //�Q�jMRF�����J�݃`�F�b�N 
            MainAccount l_mainAccount = 
                l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());
            
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_mainAccount.getDataSourceObject();   
            
            WEB3GentradeBranch l_genBranch = 
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();

            //�@@�Q)-�P�@@�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����
            //�@@[����]
            // ���XID = �⏕����.getBranch().���XID
            //�@@�v���t�@@�����X�� = "fx.deliverydate.insert.check"
            //�@@�v���t�@@�����X���̘A�� = 1
            long l_lngBranchId = l_genBranch.getBranchId();

            BranchPreferencesRow l_branchReferencesRow = null;
            try
            {
                l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_lngBranchId,
                    WEB3BranchPreferencesNameDef.FX_DELIVERY_DATE_INSERT_CHECK,
                    1);
            }
            catch (DataNetworkException l_dqex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);

            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�Q)-�Q�@@�擾�����v���t�@@�����X�l=="��n�����Z�b�g���Ȃ�"�̏ꍇ�A <BR>
            //�@@�@@   ����.�⏕����.getBranch().isFX_MRF�����J�݃`�F�b�N���{()���R�[������B<BR>
            if (l_branchReferencesRow == null ||
                WEB3FxDeliveryDateInsertCheckDef.DEFAULT.equals(l_branchReferencesRow.getValue()))
            {

            // �߂�l == true �̏ꍇ�́A�Q�j�|�R�̏������s���B<BR>
            // �߂�l == false �̏ꍇ�́A�Q�j�|�R�̏������X�L�b�v����B<BR>

                if(l_genBranch.isFxMrfAccountOpenCheck())
                {           
                    //�Q)�|�R�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂ��ڋq�I�u�W�F�N�g���擾����B 
                    //
                    //�@@[�����̐ݒ�] 
                    //�@@�@@����ID�F�@@����.�⏕����.getAccountId() 
                
                    //�ڋq.MRF�����J�݋敪 != �h0�FDEFAULT(�����Ȃ�)�h�̏ꍇ(MRF�����J�ݍ�)�A
                    //��O��throw����B

                    if (!WEB3AccountOpenDef.NOT_OPEN.equals(
                            l_mainAccountRow.getMrfAccOpenDiv()))
                    {
                        log.debug("MRF�������J�ݍς݂ł��B");
                        log.exiting(STR_METHOD_NAME); 
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01868,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "MRF�������J�ݍς݂ł��B");
                    }            
                }
            }
            
			//�R�jFX�����J�݃`�F�b�N
			//�ڋq.FX�����J�݋敪 != �h�����J�݁h �̏ꍇ�A��O��throw����B            
			if (!WEB3AccountOpenDef.OPEN.equals(
					l_mainAccountRow.getFxAccOpenDiv()))
			{
				log.debug("�ב֕ۏ؋����������J�݂ł��B");
				log.exiting(STR_METHOD_NAME); 
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01866,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"�ב֕ۏ؋����������J�݂ł��B");
			}

        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            //��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validate�O�������J�݉\)
     * <BR>
     * �ڋq���O����������J�݉\�ł��邩�`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�O���U�֘A�g�f�[�^����T�[�r�XImpl.get�O�������ڋq()���R�[������B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �@@ �،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h <BR>
     * �@@ ���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() <BR>
     * �@@ �ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() <BR>
     * <BR>
     * get�O�������ڋq()�ŊO�������ڋqParams���擾�ł����ꍇ(�O�������J�ݍ�)�A��O��throw����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01941<BR>
     * <BR>
     * �Q�jget�O�������ڋq()�ŊO�������ڋq�Ƀf�[�^�����݂��Ȃ��A���A <BR>
     * UWG�����J�ݏ󋵃e�[�u���Ƀf�[�^�����݂���ꍇ(*1)�A��O��throw����B<BR> 
     * <BR>
     * (*1)�ȉ��̃f�[�^������ <BR>
     * ����M�敪���u�R�F��M�G���[�v <BR>
     * �����J�ݏ󋵋敪���u�Q�F�����J�݃G���[�v���́u�X�F�폜�v <BR>
     * <BR>
     * �Q�|�P�j����������������쐬����B <BR>
     * �hinstitution_code = ? and branch_code = ? and account_code = ? �h <BR>
     * <BR>
     * �Q�|�Q�j���������f�[�^�R���e�i���쐬����B <BR>
     * <BR>
     * �Q�|�R�j���ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�|�S�j����.�⏕����.�،���ЃR�[�h���Q�|�Q�|�P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �Q�|�T�j����.�⏕����.get����X.getBranchCode()���Q�|�Q�|�P�j��List�ɒǉ�����B<BR> 
     * <BR>
     * �Q�|�U�j����.�⏕����.getMainAccount().getAccountCode()���Q�|�Q�|�P�j<BR>
     *       ��List�ɒǉ�����B<BR> 
     * <BR>
     * �Q�|�V�jList����z����擾����B <BR>
     * <BR>
     * �Q�|�W�j�O���U�֘A�g�f�[�^����T�[�r�XImpl.getUWG�����J�ݏ�()���R�[������B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �@@ ��������������F �Q�|�P�j�ō쐬������������������ <BR>
     * �@@ ���������f�[�^�R���e�i�F �Q�|�V�j�Ŏ擾�������������f�[�^�R���e�i <BR>
     * �@@ �\�[�g�����F null <BR>
     * <BR>
     * �Q�|�X�j�擾�������R�[�h����Loop���������{����B <BR>
     * <BR>
     *  ����M�敪 != �u�R�F��M�G���[�v and <BR>
     *  �����J�ݏ󋵋敪 != �u�Q�F�����J�݃G���[�v and <BR>
     *  �����J�ݏ󋵋敪 != �u�X�F�폜�v <BR>
     *  �̃f�[�^�����݂���ꍇ(�O�������J�ݍ�)�A��O��throw����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01941<BR>
     * <BR>
     * �R�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �@@ �،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h <BR>
     * �@@ ���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() <BR>
     * �@@ �ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() <BR>
     * <BR>
     * �R�|�P�j�ڋq.�O�������A�g�����J�݋敪 == �h�����J�݁h �̏ꍇ�A��O��throw����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01941<BR>
     * <BR>
     * �R�|�Q�j�ڋq.�O���،������J�݋敪 == �h���J�݁h �̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * �R�|�R�j�ڋq.email�A�h���X == null �̏ꍇ�A��O��throw����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01943<BR>
     * <BR>
     * 
     * @@param l_subAccount - (�⏕����)
     * @@throws WEB3BaseException
     */
    public void validateFeqConAccOpenPossible(SubAccount l_subAccount)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConAccountOpenPossible(" +
                "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�O���U�֘A�g�f�[�^����T�[�r�XImpl.get�O�������ڋq()���R�[������B 
        // [�����̐ݒ�] 
        // �،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h 
        // ���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() 
        // �ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        FeqAccountParams l_feqAccountParams = null;
        
        try
        {
            l_feqAccountParams = 
                l_feqConTransferDataControlService.getFeqAccountByAccountCode(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
            
            if (l_feqAccountParams != null)
            {
                log.debug("�O�����������͊��ɊJ�݂���Ă��܂��B");
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01941,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�����������͊��ɊJ�݂���Ă��܂��B");
            }
        }
        //get�O�������ڋq()�ŊO�������ڋqParams���擾�ł����ꍇ(�O�������J�ݍ�)�A��O��throw����B
        catch (NotFoundException l_ex)
        {        
            log.debug("__NotFoundException__" + l_ex.getMessage());
        }
        
        //�Q�jget�O�������ڋq()�ŊO�������ڋq�Ƀf�[�^�����݂��Ȃ��A���A 
        //  UWG�����J�ݏ󋵃e�[�u���Ƀf�[�^�����݂���ꍇ(*1)�A��O��throw����B
        //  (*1)�ȉ��̃f�[�^������ 
        //  ����M�敪���u�R�F��M�G���[�v 
        //  �����J�ݏ󋵋敪���u�Q�F�����J�݃G���[�v���́u�X�F�폜�v 


        //�Q�|�P�j����������������쐬����B 
        //�hinstitution_code = ? and branch_code = ? and account_code = ? �h
        String l_strCondition = 
            "institution_code = ? and branch_code = ? and account_code = ? ";
        
        //�Q�|�Q�j���������f�[�^�R���e�i���쐬����B
        
        //�Q�|�R�j���ArrayList�𐶐�����B
        List l_lisValue = new ArrayList();
        
        //�Q�|�S�j����.�⏕����.�،���ЃR�[�h���Q�|�Q�|�P�j��List�ɒǉ�����B 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();           
        l_lisValue.add(l_strInstitutionCode);
        
        //�Q�|�T�j����.�⏕����.get����X.getBranchCode()���Q�|�Q�|�P�j��List�ɒǉ�����B 
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        l_lisValue.add(l_strBranchCode);
        
        //�Q�|�U�j����.�⏕����.getMainAccount().getAccountCode()���Q�|�Q�|�P�j��List�ɒǉ�����B 
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        l_lisValue.add(l_strAccountCode);
        
        //�Q�|�V�jList����z����擾����B 
        String[] l_strValue = new String[l_lisValue.size()];
        l_lisValue.toArray(l_strValue);        

        //�Q�|�W�j�O���U�֘A�g�f�[�^����T�[�r�XImpl.getUWG�����J�ݏ�()���R�[������B 
        //  [�����̐ݒ�] 
        //  ��������������F �Q�|�P�j�ō쐬������������������ 
        //  ���������f�[�^�R���e�i�F �Q�|�V�j�Ŏ擾�������������f�[�^�R���e�i 
        //  �\�[�g�����F null 
        UwgAccountOpenStatusParams[] l_accountOpenStatusParams =
            l_feqConTransferDataControlService.getUwgAccountOpenStatus(
                l_strCondition,
                l_strValue,
                null);
        
        //�Q�|�X�j�擾�������R�[�h����Loop���������{����B 
        //  ����M�敪 != �u�R�F��M�G���[�v and 
        //  �����J�ݏ󋵋敪 != �u�Q�F�����J�݃G���[�v and 
        //  �����J�ݏ󋵋敪 != �u�X�F�폜�v 
        //  �̃f�[�^�����݂���ꍇ(�O�������J�ݍ�)�A��O��throw����B
        
        if (l_accountOpenStatusParams != null)
        {
            UwgAccountOpenStatusParams l_params = null;      
            for (int i = 0; i < l_accountOpenStatusParams.length; i++)
            {           
                l_params = l_accountOpenStatusParams[i];
                
                if (!WEB3SendRcvDivDef.RECEIVE_ERROR.equals(
                        l_params.getSendRcvDiv())
                    && !WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(
                        l_params.getAccountOpenStatusDiv())
                    && !WEB3AccountOpenStatusDivDef.DELETE.equals(
                        l_params.getAccountOpenStatusDiv()))
                {
                    log.debug("�O�����������͊��ɊJ�݂���Ă��܂��B");
                    log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01941,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�O�����������͊��ɊJ�݂���Ă��܂��B");
                }
            }
        }

        //�g���A�J�E���g�}�l�[�W���擾����   
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //�R�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B 
        // [�����̐ݒ�] 
        // �،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h 
        // ���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() 
        // �ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() 
        //NotFoundException
        MainAccount l_mainAccount = 
            l_web3GentradeAccountManager.getMainAccount(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_subAccount.getMainAccount().getAccountCode());
       
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //�R�|�P�j�ڋq.�O�������A�g�����J�݋敪 == �h�����J�݁h �̏ꍇ�A��O��throw����B 
        if (WEB3AccountOpenDef.OPEN.equals(
                l_mainAccountRow.getFeqConAccOpenDiv()))
        {
            log.debug("�ڋq.�O�������A�g�����J�݋敪 == �h�����J�݁h �̏ꍇ�B");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01941,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�����������͊��ɊJ�݂���Ă��܂��B");
        }
		//�R�|�Q�j�ڋq.�O���،������J�݋敪 == �h���J�݁h �̏ꍇ�A��O��throw����B 
		if (WEB3ForeignSecAccOpenDiv.NOT_OPEN.equals(
				l_mainAccountRow.getForeignSecAccOpenDiv()))
		{
			log.debug("�ڋq.�O���،������J�݋敪 == �h���J�݁h �̏ꍇ�B");
			log.exiting(STR_METHOD_NAME); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01341,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"���Y�ڋq�͊O���،������J�݂Ȃ��B");
		}
        //�R�|�R�j�ڋq.email�A�h���X == null �̏ꍇ�A��O��throw����B 
        if (l_mainAccountRow.getEmailAddress() == null)
        {
            log.debug("�ڋq��email�A�h���X�����w��ł��B");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01943,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq��email�A�h���X�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�O���U�։\)
     * <BR>
     * �ڋq���O���U�֎���\�ł��邩�`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�O�������J�݃`�F�b�N <BR>
     * <BR>
     * �@@�O���U�֘A�g�f�[�^����T�[�r�XImpl.get�O�������ڋq()���R�[������B <BR>
     * <BR>
     * �@@[�����̐ݒ�] <BR>
     * �@@�@@�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h <BR>
     * �@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() <BR>
     * �@@�@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() <BR>
     * <BR>
     * �@@get�O�������ڋq()�ŊO�������ڋqParams���擾�ł��Ȃ������ꍇ(�O���������J�݁j�A<BR>
     *          ��O��throw����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * �@@get�O�������ڋq()�Ŏ擾�����O�������ڋqParams.�O�����������敪 != �h�J�ݍρh�̏ꍇ�A<BR>
     *          ��O��throw����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * �Q�j�O���،������J�݃`�F�b�N <BR>
     * <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂ��ڋq�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@[�����̐ݒ�] <BR>
     * �@@�@@����ID�F�@@����.�⏕����.getAccountId() <BR>
     * <BR>
     * �ڋq.�O���،������J�݋敪 == �h���J�݁h �̏ꍇ�A��O��throw����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01341<BR>
     * <BR>
     * �R�j�O�������A�g�����J�݃`�F�b�N <BR>
     * <BR>
     * �ڋq.�O�������A�g�����J�݋敪 != �h�����J�݁h �̏ꍇ�A��O��throw����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@throws WEB3BaseException
     */
    public void validateFeqConTransferPossible(
            SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConTransferPossible(" +
                "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�O�������J�݃`�F�b�N 
        //�@@�O���U�֘A�g�f�[�^����T�[�r�XImpl.get�O�������ڋq()���R�[������B 
        //  [�����̐ݒ�] 
        //�@@�@@�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h 
        //�@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() 
        //�@@�@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);  
        
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_feqConTransferDataControlService.getFeqAccountByAccountCode(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
        }
        //get�O�������ڋq()�ŊO�������ڋqParams���擾�ł��Ȃ������ꍇ(�O���������J�݁j�A��O��throw����B
        catch(NotFoundException l_ex)
        {        
            log.debug("�O�����������J�݂ł��B", l_ex);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01944,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�����������J�݂ł��B");
        }
        //get�O�������ڋq()�Ŏ擾�����O�������ڋqParams.�O�����������敪 != �h�J�ݍρh�̏ꍇ�A
        //��O��throw����B
        if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(
                l_feqAccountParams.getAccountOpenDiv()))
        {
            log.debug("�O�����������J�݂ł��B");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01944,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�����������J�݂ł��B");
        }
        
        MainAccount l_mainAccount = null;
        try
        {
            //�g���A�J�E���g�}�l�[�W���擾����   
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂ��ڋq�I�u�W�F�N�g���擾����B 
            //[�����̐ݒ�] 
            //����ID�F�@@����.�⏕����.getAccountId()   
            //NotFoundException
            l_mainAccount = 
                l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__", l_ex);
            //��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        
		//�Q�j�O���،������J�݃`�F�b�N 
			  //�ڋq.�O���،������J�݋敪 == �h���J�݁h �̏ꍇ�A��O��throw����B
		if (WEB3ForeignSecAccOpenDiv.NOT_OPEN.equals(
				l_mainAccountRow.getForeignSecAccOpenDiv()))
		{
			log.debug("���Y�ڋq�͊O���،������J�݂Ȃ��B");
			log.exiting(STR_METHOD_NAME); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01341,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"���Y�ڋq�͊O���،������J�݂Ȃ��B");
		}
            
        //�R�j�O�������A�g�����J�݃`�F�b�N 
        //�ڋq.�O�������A�g�����J�݋敪 != �h�����J�݁h �̏ꍇ�A��O��throw����B
        if (!WEB3AccountOpenDef.OPEN.equals(
                l_mainAccountRow.getFeqConAccOpenDiv()))
        {
			log.debug("�O�����������J�݂ł��B");
			log.exiting(STR_METHOD_NAME); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01944,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�O�����������J�݂ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *(validate�O������U��)
     * <BR>
     * �O���U�ւ�����̏ꍇ�̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�O���ڋq�s�I�u�W�F�N�g�̎擾 <BR>
     * <BR>
     * �O���U�֘A�g�f�[�^����T�[�r�XImpl.get�O�������ڋq()���R�[������B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h <BR>
     * ���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() <BR>
     * �ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() <BR>
     * <BR>
     * �Q�j����U�ւ̃`�F�b�N <BR>
     * <BR>
     * �P�j�Ŏ擾�����O�������ڋqParams.����U�փt���O == �h�����{�h and <BR>
     * ����.�U�֋��z < 50000 <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B  <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01946<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_dblTransferAmount - (�U�֋��z)
     * @@throws WEB3BaseException
     */
    public void validateFeqConFirstTransfer(
            SubAccount l_subAccount, double l_dblTransferAmount)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFeqConFirstTransfer(" +
                "SubAccount l_subAccount, double l_dblTransferAmount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�O���ڋq�s�I�u�W�F�N�g�̎擾 
        //�O���U�֘A�g�f�[�^����T�[�r�XImpl.get�O�������ڋq()���R�[������B 
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() 
        //�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_feqConTransferDataControlService.getFeqAccountByAccountCode(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("NotFoundException: ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j����U�ւ̃`�F�b�N 
        //  �P�j�Ŏ擾�����O�������ڋqParams.����U�փt���O == �h�����{�h and 
        //   ����.�U�֋��z < 50000 �̏ꍇ�A��O���X���[����B 
        if (WEB3FeqFirstTransferFlagDef.NOT_TRANSFER.equals(
                l_feqAccountParams.getFirstTransferFlag())
            && (l_dblTransferAmount < 50000))
        {
            log.debug("�O�������ڋqParams.����U�փt���O == �h�����{�h and " +
                    "����.�U�֋��z < 50000 �̏ꍇ");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01946,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����U�ւ̏ꍇ�A�U�֋��z���������z�ɒB���Ă��܂���B");
        }    
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateFX�����J��)
     * <BR>
     * �ڋq��FX��������J�ݍςł��邩�`�F�b�N���s���B<BR> 
     * <BR>
     * �P�jFX�ڋq���擾����<BR> 
     * <BR>
     * FX�f�[�^�}�l�[�W��.getFX�ڋq()���R�[������B<BR> 
     * <BR>
     * [����] <BR>
     * �@@�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h <BR>
     * �@@���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode()<BR> 
     * �@@FX�V�X�e���R�[�h�F�@@����.FX�V�X�e���R�[�h <BR>
     * �@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode()<BR> 
     * <BR>
     * getFX�ڋq()��FX�ڋqParams���擾�o���Ȃ��ꍇ(FX�������J��)�A<BR>
     * ��O��throw����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_01866<BR>
     * <BR>
     * �Q�j�P�j�Ŏ擾����FX�ڋq.FX�����敪==1:�J�ݍςłȂ��ꍇ(FX�������J�݁j�A<BR>
     * ��O��throw����B <BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_01866<BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g <BR>
     * @@param l_strFXSystemCode - FX�V�X�e���R�[�h <BR>
     * @@throws WEB3BaseException
     */
    public void validateFXAccOpen(
        SubAccount l_subAccount, 
        String l_strFXSystemCode)
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateFXAccOpen(" +
            "SubAccount l_subAccount, String l_strFXSystemCode)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jFX�ڋq���擾���� 
        //FX�f�[�^�}�l�[�W��.getFX�ڋq()���R�[������B 
        //[����] 
        //�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode() 
        //FX�V�X�e���R�[�h�F�@@����.FX�V�X�e���R�[�h 
        //�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode() 
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        
        try
        {
            FxAccountParams l_fxAccountParams = 
                l_fxDataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_strFXSystemCode,
                    l_subAccount.getMainAccount().getAccountCode());
            
            //getFX�ڋq()��FX�ڋqParams���擾�o���Ȃ��ꍇ(FX�������J��)�A
            //��O��throw����B 
            if (l_fxAccountParams == null)
            {
                log.debug("getFX�ڋq()��FX�ڋqParams���擾�o���Ȃ�");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
					"getFX�ڋq()��FX�ڋqParams���擾�o���Ȃ�");                
            }
            
            //�Q�j�P�j�Ŏ擾����FX�ڋq.FX�����敪==1:�J�ݍςłȂ��ꍇ(FX�������J�݁j�A
            //��O��throw����B 
            if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("�擾����FX�ڋq.FX�����敪==1:�J�ݍςłȂ�");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
					"�擾����FX�ڋq.FX�����敪==1:�J�ݍςłȂ�");   
            }            
        }
        catch (NotFoundException l_nfex)
        {
            log.debug("getFX�ڋq()��FX�ڋqParams���擾�o���Ȃ�", l_nfex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
				"getFX�ڋq()��FX�ڋqParams���擾�o���Ȃ�");
        }
        
        log.exiting(STR_METHOD_NAME);        
    }

    /**
     * (validateCFD�U�։\)<BR>
     * �ڋq��CFD�U�֎���\�ł��邩�`�F�b�N���s���B<BR>
     * <BR>
     * �P�jCFD�����J�݃`�F�b�N<BR>
     * <BR>
     * �@@FX�f�[�^����T�[�r�X.getFX�ڋq()���R�[������B<BR>
     * <BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�@@�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode()<BR>
     * �@@�@@FX�V�X�e���R�[�h�F�@@����.FX�V�X�e���R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode()<BR>
     * <BR>
     * �@@getFX�ڋq()��FX�ڋqParams���擾�ł��Ȃ������ꍇ(CFD�������J�݁j�A��O��throw����B<BR>
     * �@@�@@�@@�@@class�F�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@�@@BUSINESS_ERROR_01866<BR>
     * <BR>
     * �@@getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 = �h2�F�U�֒�~�h�̏ꍇ�A<BR>
     * �@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�F�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@�@@BUSINESS_ERROR_02440<BR>
     * <BR>
     * �@@getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 != �h1�F�J�ݍρh�̏ꍇ�A<BR>
     * �@@��O��throw����B<BR>
     * �@@�@@�@@�@@class�F�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@�@@BUSINESS_ERROR_01867<BR>
     * <BR>
     * �Q�jMRF�����J�݃`�F�b�N<BR>
     * <BR>
     * �@@�Q)-�P�@@�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���XID = �⏕����.getBranch().���XID<BR>
     * �@@�@@�v���t�@@�����X�� = "fx.deliverydate.insert.check"<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �@@�Q)-�Q�@@�擾�����v���t�@@�����X�l=="��n�����Z�b�g���Ȃ�"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@����.�⏕����.getBranch().isFX_MRF�����J�݃`�F�b�N���{()���R�[������B<BR>
     * <BR>
     * �߂�l == true �̏ꍇ�́A�Q�j�|�R�̏������s���B<BR>
     * �߂�l == false �̏ꍇ�́A�Q�j�|�R�̏������X�L�b�v����B<BR>
     * <BR>
     * �@@�Q�j�|�R�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂ��ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�@@����ID�F�@@����.�⏕����.getAccountId()<BR>
     * <BR>
     * �@@�ڋq.MRF�����J�݋敪 != �h0�FDEFAULT(�����Ȃ�)�h�̏ꍇ(MRF�����J�ݍ�)�A��O��throw����B<BR>
     * �@@�@@�@@�@@class�F�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@�@@BUSINESS_ERROR_01868<BR>
     * <BR>
     * �R�jCFD�����J�݃`�F�b�N<BR>
     * <BR>
     * �ڋq.CFD�����J�݋敪 != �h�����J�݁h �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@�@@class�F�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@�@@BUSINESS_ERROR_01866<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strFxSystemCode - (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void validateCFDChangePoss(
        SubAccount l_subAccount, String l_strFxSystemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCFDChangePoss(SubAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�jCFD�����J�݃`�F�b�N
        //FX�f�[�^�}�l�[�W��.getFX�ڋq()���R�[������B
        //[�����̐ݒ�]
        //�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h
        //���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode()
        //FX�V�X�e���R�[�h�F�@@����.FX�V�X�e���R�[�h
        //�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode()
        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);

        try
        {
            FxAccountParams l_fxAccountParams =
                l_fxDataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_strFxSystemCode,
                    l_subAccount.getMainAccount().getAccountCode());

            //getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 = �h2�F�U�֒�~�h�̏ꍇ�A
            //��O��throw����B
            if (WEB3AioAccountDivDef.TRANSFER_STOP.equals(
                l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("�U�֒�~���G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02440,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�U�֒�~���G���[�B");
            }

            //getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 != �h1�F�J�ݍρh�̏ꍇ�A
            //��O��throw����B
            if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(
                l_fxAccountParams.getFxAccountDiv()))
            {
                log.debug("�ב֕ۏ؋�����������s�̏�Ԃł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01867,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ב֕ۏ؋�����������s�̏�Ԃł��B");
            }
        }
        //getFX�ڋq()��FX�ڋqParams���擾�ł��Ȃ������ꍇ(CFD�������J�݁j�A��O��throw����B
        catch (NotFoundException l_ex)
        {
            log.error("�ב֕ۏ؋����������J�݂ł��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ב֕ۏ؋����������J�݂ł��B");
        }

        try
        {
            //�g���A�J�E���g�}�l�[�W���擾����
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            WEB3GentradeBranch l_genBranch =
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();

            //�Q�jMRF�����J�݃`�F�b�N
            //�Q)-�P�@@�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
            //[����]
            //���XID = �⏕����.getBranch().���XID
            //�v���t�@@�����X�� = "fx.deliverydate.insert.check"
            //�v���t�@@�����X���̘A�� = 1
            long l_lngBranchId = l_genBranch.getBranchId();
            BranchPreferencesRow l_branchReferencesRow = null;

            MainAccount l_mainAccount =
                l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());

            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();

            try
            {
                l_branchReferencesRow =
                	BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
	                    l_lngBranchId,
	                    WEB3BranchPreferencesNameDef.FX_DELIVERY_DATE_INSERT_CHECK,
	                    Integer.parseInt(WEB3NameSerialNoDef.SERIAL_NO));
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�Q)-�Q�@@�擾�����v���t�@@�����X�l=="��n�����Z�b�g���Ȃ�"�̏ꍇ�A
            //����.�⏕����.getBranch().isFX_MRF�����J�݃`�F�b�N���{()���R�[������B
            if (l_branchReferencesRow == null
                || WEB3FxDeliveryDateInsertCheckDef.DEFAULT.equals(
                    l_branchReferencesRow.getValue()))
            {
                //�߂�l == true �̏ꍇ�́A�Q�j�|�R�̏������s���B
                //�߂�l == false �̏ꍇ�́A�Q�j�|�R�̏������X�L�b�v����B
                if (l_genBranch.isFxMrfAccountOpenCheck())
                {
                    //�Q�j�|�R�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂ��ڋq�I�u�W�F�N�g���擾����B
                    //[�����̐ݒ�]
                    //����ID�F�@@����.�⏕����.getAccountId()

                    //�ڋq.MRF�����J�݋敪 != �h0�FDEFAULT(�����Ȃ�)�h�̏ꍇ(MRF�����J�ݍ�)�A
                    //��O��throw����B
                    if (!WEB3MrfAccOpenDivDef.DEFAULT.equals(
                        l_mainAccountRow.getMrfAccOpenDiv()))
                    {
                        log.debug("MRF�������J�ݍς݂ł��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01868,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "MRF�������J�ݍς݂ł��B");
                    }
                }
            }

            //�R�jCFD�����J�݃`�F�b�N
            //�ڋq.CFD�����J�݋敪 != �h�����J�݁h �̏ꍇ�A��O��throw����B
            if (!WEB3CfdAccOpenDivDef.ACCOUNT_OPEN.equals(l_mainAccountRow.getCfdAccOpenDiv()))
            {
                log.debug("�ב֕ۏ؋����������J�݂ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ב֕ۏ؋����������J�݂ł��B");
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�U�֎���\)<BR>
     * �ڋq���U�֎���\�ł��邩�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�����J�݃`�F�b�N<BR>
     * �@@�@@1-1) FX�f�[�^����T�[�r�X.getFX�ڋq()���R�[������B<BR>
     * �@@�@@�@@�����̐ݒ�]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode()<BR>
     * �@@�@@�@@FX�V�X�e���R�[�h�F�@@����.FX�V�X�e���R�[�h<BR>
     * �@@�@@�@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode()<BR>
     * <BR>
     * �@@�@@�@@getFX�ڋq()��FX�ڋqParams���擾�ł��Ȃ������ꍇ(FX�������J�݁j�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_01866<BR>
     * <BR>
     * �@@�@@�@@getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 = �h2�F�U�֒�~�h�̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02440<BR>
     * <BR>
     * �@@�@@�@@getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 != �h1�F�J�ݍρh�̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_01867<BR>
     * <BR>
     * �Q�jMRF�����J�݃`�F�b�N<BR>
     * �@@�@@2-1�j����.��Е�FX�V�X�e������Params.MRF�������敪 = "�O�F�s��"�̏ꍇ<BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂ��ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@����ID�F�@@����.�⏕����.getAccountId()<BR>
     * <BR>
     * �@@�@@�@@�ڋq.MRF�����J�݋敪 �I= �h0�FDEFAULT(�����Ȃ�)�h�̏ꍇ(MRF�����J�ݍ�)�A<BR>
     * �@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_01868<BR>
     * <BR>
     * �R�j�����J�݋敪�`�F�b�N<BR>
     * �@@�@@3-1) �����J�݋敪�I�u�W�F�N�g�𐶐��B<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@����ID = ����.�⏕����.�����h�c<BR>
     * �@@�@@�@@������� = ��Е�FX�V�X�e�������e�[�u��.�������<BR>
     * <BR>
     * �@@�@@3-2) �����J�݋敪.get�����J�݋敪()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@get�����J�݋敪()�߂�l �I= �h1:�J�ݍρh �̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_01866<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_compFxConditionParams - (��Е�FX�V�X�e������Params)<BR>
     * ��Е�FX�V�X�e������Params<BR>
     * @@throws WEB3BaseException
     */
    public void validateTransferTradePossible(
        SubAccount l_subAccount,
        CompFxConditionParams l_compFxConditionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTransferTradePossible(SubAccount, CompFxConditionParams)";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_fXDataControlService =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);

        //�����J�݃`�F�b�N
        //FX�f�[�^����T�[�r�X.getFX�ڋq()���R�[������B
        // �����̐ݒ�]
        //  �،���ЃR�[�h�F ����.�⏕����.�،���ЃR�[�h
        //  ���X�R�[�h�F�@@����.�⏕����.get����X.getBranchCode()
        //  FX�V�X�e���R�[�h�F�@@����.FX�V�X�e���R�[�h
        //  �ڋq�R�[�h�F�@@����.�⏕����.getMainAccount().getAccountCode()
        FxAccountParams l_fxAccountParams = null;
        try
        {
            MainAccount l_mainAccountFromSubAccount = l_subAccount.getMainAccount();
            l_fxAccountParams =
                l_fXDataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_mainAccountFromSubAccount.getBranch().getBranchCode(),
                    l_compFxConditionParams.getFxSystemCode(),
                    l_mainAccountFromSubAccount.getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            l_fxAccountParams = null;
        }

        //getFX�ڋq()��FX�ڋqParams���擾�ł��Ȃ������ꍇ(FX�������J�݁j�A
        // ��O��throw����B
        if (l_fxAccountParams == null)
        {
            log.debug("�ב֕ۏ؋����������J�݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ב֕ۏ؋����������J�݂ł��B");
        }

        String l_strFxAccountDiv = l_fxAccountParams.getFxAccountDiv();
        //getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 = �h2�F�U�֒�~�h�̏ꍇ�A
        // ��O��throw����B
        if (WEB3AioAccountDivDef.TRANSFER_STOP.equals(l_strFxAccountDiv))
        {
            log.debug("�U�֒�~���G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02440,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�U�֒�~���G���[�B");
        }

        //getFX�ڋq()�Ŏ擾����FX�ڋqParams.FX�����敪 != �h1�F�J�ݍρh�̏ꍇ�A
        // ��O��throw����B 
        if (!WEB3AioAccountDivDef.OPEN_COMPLETE.equals(l_strFxAccountDiv))
        {
            log.debug("�ב֕ۏ؋�����������s�̏�Ԃł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01867,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ב֕ۏ؋�����������s�̏�Ԃł��B");
        }

        //�g���A�J�E���g�}�l�[�W���擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //MRF�����J�݃`�F�b�N
        // ����.��Е�FX�V�X�e������Params.MRF�������敪 = "�O�F�s��"�̏ꍇ
        if (WEB3MrfAllowDivDef.DISABLED.equals(l_compFxConditionParams.getMrfAllowDiv()))
        {
            //�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂ��ڋq�I�u�W�F�N�g���擾����B
            //[�����̐ݒ�]
            // ����ID�F�@@����.�⏕����.getAccountId()
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount =
                    l_gentradeAccountManager.getMainAccount(
                        l_subAccount.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            //�ڋq.MRF�����J�݋敪 �I= �h0�FDEFAULT(�����Ȃ�)�h�̏ꍇ(MRF�����J�ݍ�)�A
            // ��O��throw����B
            if (!WEB3MrfAccOpenDivDef.DEFAULT.equals(
                l_mainAccountRow.getMrfAccOpenDiv()))
            {
                log.debug("MRF�������J�ݍς݂ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01868,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "MRF�������J�ݍς݂ł��B");
            }
        }

        //�����J�݋敪�`�F�b�N
        //�����J�݋敪�I�u�W�F�N�g�𐶐��B
        //[����]
        // ����ID = ����.�⏕����.�����h�c
        // ������� = ��Е�FX�V�X�e�������e�[�u��.�������
        WEB3GentradeAccOpenDiv l_gentradeAccOpenDiv =
            new WEB3GentradeAccOpenDiv(
                l_subAccount.getAccountId(),
                l_compFxConditionParams.getAccType());

        //�����J�݋敪.get�����J�݋敪()���R�[������B
        String l_strAccOpenDiv = l_gentradeAccOpenDiv.getAccOpenDiv();

        //get�����J�݋敪()�߂�l �I= �h1:�J�ݍρh �̏ꍇ�A��O��throw����B
        if (!WEB3AccountOpenDef.OPEN.equals(l_strAccOpenDiv))
        {
            log.debug("�ב֕ۏ؋����������J�݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01866,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ב֕ۏ؋����������J�݂ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
