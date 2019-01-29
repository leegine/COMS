head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�N���X(WEB3GentradeBizLogicProvider.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/04 �����@@���F(SRA) �V�K�쐬
Revesion History : 2004/05/14 �R�w��(���u) �ύX
Revesion History : 2004/06/12 �R�w��(���u) �ύX
Revesion History : 2006/05/12 ������ (���u)�y���ʁz�d�l�ύX�E���f��No.187
Revesion History : 2006/05/12 ������ (���u)�y���ʁz�d�l�ύX�E�v�Z����No.012
Revesion History : 2006/05/29 ������ (���u) ��Q�Ǘ� U02843�Ή�
Revesion History : 2007/02/09 �h�C (���u)�y���ʁz�d�l�ύX�E�v�Z����No.013
Revesion History : 2007/10/10 �h�C (���u)�y���ʁz�d�l�ύX�E�v�Z����No.014
*/
package webbroker3.gentrade;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommissionExecutionConditionDef;
import webbroker3.common.define.WEB3CommissionFeeCondFlagDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.data.EquityCommAccountCondMstDao;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.EquityCommCondMstRow;
import webbroker3.gentrade.data.EquityCommCondRow;
import webbroker3.gentrade.data.EquityCommRevMstRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeNumberConstDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�v�Z�T�[�r�X)<BR>
 * �e��v�Z��񋟂��郆�[�e�B���e�B�N���X�B<BR>
 * �]�͌v�Z�́AxTrade���񋟂���C���^�t�F�C�X�ȊO�ɂ��p�����[�^���K�v�B(�v����)<BR>
 */
public class WEB3GentradeBizLogicProvider implements GlobalBizLogicProvider
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBizLogicProvider.class);
    
    /**
     * @@roseuid 40A3495902F0
     */
    public WEB3GentradeBizLogicProvider()
    {

    }
    
    public  OrderValidationResult checkTradingPower(SubAccount subaccount, OrderSpec orderspec)
    {
        return null;
    }
    
    /**
     * @@param l_dblData
     * @@return double
     * @@roseuid 40A3495903AE
     */
    public double calcSalesTax(double l_dblData)
    {   
        return 0.0;
    }
    
    /**
     * @@roseuid 40A3495902F0
     */    
    public ConsolidatedCommissionInfo calcCommission(Object[] l_objs) {
        return null;
    }
        
    /**
     * (calc�����)<BR>
     * <BR>
     * �w����z�ɑ΂������ł�ԋp����B<BR> 
     *�icalcSalesTax�̎����j<BR> 
     * <BR>
     * ����{�݌v�v�Z�����i���ʁj.doc�u�U�D����Łv���Q��<BR> 
     * <BR>
     * @@param l_dblAmount - (���z)<BR>
     *   ����ł��Z�o����Ώۂ̋��z<BR>
     * @@param l_tsBaseDate - (���)<BR>
     *   ����ł��Z�o�������A�����̏ꍇ�͔������ɂ�����B<BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * @@return double
     * @@throws WEB3BaseException ����ł̌v�Z�Ɏ��s�����ꍇ
     * @@roseuid 3FFBC420033B
     */
    public double calcSalesTax(double l_dblAmount, Timestamp l_tsBaseDate, SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        long l_lngSalesTax = 0L;
        double l_dblSalesTax = 0.0;
        WEB3GentradeTaxRate l_taxRate = null;
        
        final String STR_METHOD_NAME = "calcSalesTax(double, Timestamp, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_dblAmount < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���z = " + l_dblAmount);
        }
        
        MainAccount l_mainAccount = l_subAccount.getMainAccount();       
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
       
       //�����I�u�W�F�N�g.���Z�^�񋏏Z�敪���i 1:���ʔ�Z���� or
       // 2:��Z���ҁj�̏ꍇ�́A����łƂ��ĂO��Ԃ��B
        String l_strResident = l_mainAccountRow.getResident();
        if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_strResident)
            || WEB3ResidentDef.NON_RESIDENT.equals(l_strResident))
        {
            log.debug("�����I�u�W�F�N�g.���Z�^�񋏏Z�敪��" +
                "�i 1:���ʔ�Z���� or 2:��Z���ҁj�̏ꍇ�́A����łƂ��ĂO��Ԃ�");
            return 0;
        }
       
        // ����ŗ����擾����
        l_taxRate = new WEB3GentradeTaxRate(
            l_subAccount.getInstitution().getInstitutionCode(),
            WEB3GentradeTaxRate.CONSUMPTION_TAX, 
            l_tsBaseDate);           
       
        // ����� �� ���z �~ ����ŗ�
        //BigDecimal l_oneHandred = new BigDecimal("100");
        BigDecimal l_oneHandred = new BigDecimal(WEB3GentradeNumberConstDef.HYAKU);
        //int l_intScale = 10;
        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
        
        BigDecimal l_bdAmount = new BigDecimal(l_dblAmount);
        BigDecimal l_bdTaxRate = 
            new BigDecimal(l_taxRate.getTaxRate()).divide(l_oneHandred, l_intScale, BigDecimal.ROUND_HALF_EVEN);
        l_dblSalesTax = l_bdAmount.multiply(l_bdTaxRate).doubleValue();
        
        log.debug("���z�F[" + l_bdAmount.doubleValue() + "]");
        log.debug("����ŗ��F[" + l_bdTaxRate.doubleValue() + "]");
        log.debug("����� �� ���z �~ ����ŗ��F[" + l_dblSalesTax + "]");
        // �����_�ȉ��؂�̂�
        l_lngSalesTax = (long)l_dblSalesTax;

        log.exiting(STR_METHOD_NAME);
        return (double)l_lngSalesTax;
    }
  
    /**
     * (get�ϑ��萔���R�[�X)<BR>
     * <BR>
     *�ڋq�ɊY�����錻�݂̈ϑ��萔���R�[�X���擾����B<BR>
     * <BR>
     * �P�j�ϑ��萔���ڋq�����o�^�}�X�^�[���ȉ��̏����œǂ݁A<BR>
     * �萔��No.���擾����B<BR>
     *  <BR>
     * [����] <BR>
     * �ϑ��萔���ڋq�����o�^�}�X�^�[.�،���ЃR�[�h =  <BR>
     *    �ڋq.getInstitution().getInstitutionCode() <BR>
     * �ϑ��萔���ڋq�����o�^�}�X�^�[.���X�h�c =  <BR>
     *    �ڋq.getBranch().getBranchId() <BR>
     * �ϑ��萔���ڋq�����o�^�}�X�^�[.�����h�c = �ڋq.getAccountId() <BR>
     * �ϑ��萔���ڋq�����o�^�}�X�^�[.�萔�����i�R�[�h =  <BR>
     *    �萔�����i�R�[�h <BR>
     * �ϑ��萔���ڋq�����o�^�}�X�^�[.�L���� = �L����<BR>
     *  <BR>
     * �Q�j�ȉ��̏����ňϑ��萔�������o�^�}�X�^�[��ǂ݁A<BR>
     * �萔���R�[�X�R�[�h���擾���ԋp����B<BR>
     * �����s�擾�ł����ꍇ�́A1���ڂ̃��R�[�h�� <BR>
     * �萔���R�[�X�R�[�h��ԋp����B<BR>
     *  <BR>
     * [����]
     * �ϑ��萔�������o�^�}�X�^�[.�،���ЃR�[�h =  <BR>
     *     �ڋq.getInstitution().getInstitutionCode() <BR> 
     * And �ϑ��萔�������o�^�}�X�^�[.�萔�����i�R�[�h = 
     *    �萔�����i�R�[�h <BR>
     * �ϑ��萔�������o�^�}�X�^�[.�o�^No = �萔��No. + ' ' <BR>
     * And �ϑ��萔�������o�^�}�X�^�[.�K�p�J�n�N���������L����<BR>
     * And �ϑ��萔�������o�^�}�X�^�[.�K�p�I���N���� >= �L����<BR>
     * <BR>
     * @@param l_genMainAccount - (�ڋq)<BR>
     * @@param l_strCommProductCode - (�萔�����i�R�[�h) <BR>
     *    10�F��ꊔ��  11�F�X������  <BR>
     *    12�F�~�j����    40�F�O������  <BR>
     *    50�F�敨         51�F�����w���n�o <BR>
     * @@param l_validUntilBizDate - (�L����)<BR>
     * @@return String
     * @@throws WEB3SystemLayerException
     */
    public String getCommissionCourseDiv(
        WEB3GentradeMainAccount l_genMainAccount,
        String l_strCommProductCode,
        Date l_validUntilBizDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getCommissionCourseDiv(WEB3GentradeMainAccount, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        String l_strValidUntilBizDate =
            WEB3DateUtility.formatDate(
                l_validUntilBizDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        
        //�P�j�ϑ��萔���ڋq�����o�^�}�X�^�[���ȉ��̏����œǂ݁A
        //�萔��No.���擾����B
        EquityCommAccountCondMstRow l_equityCommAccountCondMstRow;
        try
        {
            l_equityCommAccountCondMstRow =
                EquityCommAccountCondMstDao.findRowByPk(
                    l_genMainAccount.getInstitution().getInstitutionCode(),
                    l_genMainAccount.getBranch().getBranchId(),
                    l_genMainAccount.getAccountId(),
                    l_strCommProductCode,
                    l_strValidUntilBizDate);
        }
        catch (DataFindException dfe)
        {
            // �Y���f�[�^�Ȃ�
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataException de)
        {
            // DB�A�N�Z�X�G���[
            log.error(STR_METHOD_NAME,de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�Q�j�ȉ��̏����ňϑ��萔�������o�^�}�X�^�[��ǂ݁A
        //�萔���R�[�X�R�[�h���擾���ԋp����B
        //�����s�擾�ł����ꍇ�́A1���ڂ̃��R�[�h�� 
        //�萔���R�[�X�R�[�h��ԋp����
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and comm_product_code = ? ");
        l_sbWhere.append(" and reg_no = ? ");
//        l_sbWhere.append(" and appli_start_date <= ? ");
//        l_sbWhere.append(" and appli_end_date >= ? ");
        Object[] l_objWhere =
        {   l_genMainAccount.getInstitution().getInstitutionCode(),//�،���ЃR�[�h
            l_strCommProductCode, //�萔�����i�R�[�h
            l_equityCommAccountCondMstRow.getCommissionNo() + ' '//�萔��No.
//            l_validUntilBizDate, //�L����
//            l_validUntilBizDate //�L����
        };
        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                EquityCommCondMstRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            // DB�A�N�Z�X�G���[
            log.error(STR_METHOD_NAME,de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        int l_intSize = l_lisRecords.size();
        EquityCommCondMstRow l_equityCommCondMstRow = null;
        for(int i = 0; i < l_intSize; i ++)
        {
            EquityCommCondMstRow l_tmpEquityCommCondMstRow =  
                (EquityCommCondMstRow)l_lisRecords.get(i);
            Timestamp l_tsStart = l_tmpEquityCommCondMstRow.getAppliStartDate();
            Timestamp l_tsEnd = l_tmpEquityCommCondMstRow.getAppliEndDate();
            if((WEB3DateUtility.compare(l_tsStart,l_validUntilBizDate) <= 0) 
                && (WEB3DateUtility.compare(l_tsEnd,l_validUntilBizDate) >= 0))
            {
                l_equityCommCondMstRow = l_tmpEquityCommCondMstRow;
                break;
            }
        }
        //�����`�F�b�N
        if (l_equityCommCondMstRow == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ϑ��萔�������o�^�}�X�^�[�����F ���� = 0");
        }

        log.exiting(STR_METHOD_NAME);
        return l_equityCommCondMstRow.getCommissionCourseDiv();
    }
  
    
    /**
     * (calc�ϑ��萔��)<BR>
     * <BR>
     * ���o��v�Z�p����ɑ΂���ϑ��萔�����Z�o���܂��B<BR> 
     *�icalcCommission�̎����j<BR> 
     * <BR>
     * ����{�݌v�v�Z�����i���ʁj.doc�u�T�D�ϑ��萔���v���Q��<BR> 
     * <BR>
     * �����̎萔���I�u�W�F�N�g�� <BR>
     * �萔�����z�A�萔��No�A�萔��No�}�ԁA�萔���R�[�X�R�[�h�A�Œ�萔����ݒ肵�Areturn����B<BR>
     * <BR>
     * @@param l_commission - (�萔��)<BR>
     *     �萔�������肷�邽�߂ɕK�v�ȏ��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@throws WEB3BaseException �ϑ��萔���̌v�Z�Ɏ��s�����ꍇ
     * @@roseuid 3FFBBEFD02BE
     */
    public void calcCommission(WEB3GentradeCommission l_commission, SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        List l_list = null;
        int l_intSize;

        String l_strErrorMessage1 = "��Е��X���i�e�[�u���ɊY������f�[�^������܂���B";
        //String l_strErrorMessage4 = "�ϑ��萔���ڋq�����o�^�}�X�^�[�ɊY������f�[�^������܂���B";
        //String l_strErrorMessage7 = "�ϑ��萔���}�ԓo�^�}�X�^�[�ɊY������f�[�^������܂���B";
        //String l_strErrorMessage8 = "�ϑ��萔���}�ԓo�^�}�X�^�[�ŊY������f�[�^���d�����Ă��܂�";
        String l_strErrorMessage10 = "�ϑ��萔�������e�[�u���ɊY������f�[�^������܂���B";
        String l_strErrorMessage11 =  "�ϑ��萔�������e�[�u���ŊY������f�[�^���d�����Ă��܂�";
        String l_strErrorMessage13 = "�ϑ��萔�������o�^�}�X�^�[�ɊY������f�[�^������܂���B";
        String l_strErrorMessage14 = "�ϑ��萔�������o�^�}�X�^�[�ŊY������f�[�^���d�����Ă��܂�";
        
        QueryProcessor l_qp = null;
        
        String l_strSqlDateFormat = "yyyyMMdd";
        SimpleDateFormat l_sqlDateFormatter = GtlUtils.getThreadSafeSimpleDateFormat(l_strSqlDateFormat);
 
        BigDecimal l_oneHandred = new BigDecimal(WEB3GentradeNumberConstDef.HYAKU);
        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
        
        final String SPACE = " ";
       
        final String STR_METHOD_NAME = "calcCommission(WEB3EquityCommission , SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_commission == null || l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.debug("�،���ЃR�[�h�F[" + l_commission.getInstitutionCode() + "]");
        log.debug("���XID�F[" + l_commission.getBranchId() + "]");
        log.debug("�萔�����i�R�[�h�F[" + l_commission.getCommissionProductCode() + "]");
        log.debug("�����`�����l���F[" + l_commission.getOrderChannel() + "]");
        log.debug("����R�[�h(SONAR)�F[" + l_commission.getSonarTradedCode() + "]");
        log.debug("���s�����F[" + l_commission.isLimitPrice() + "]");
        log.debug("�������F[" + l_sqlDateFormatter.format(l_commission.getOrderBizDate()) + "]");
        log.debug("�ٍϋ敪�F[" + l_commission.getPayType() + "]");
        
        //(1) �萔���̓o�^No.�̌���
        //�ϑ��萔���́A�萔��No.�{�}�Ԃɂ���Č��肳�ꂽ�o�^No.�ɕR�t�����Ă���B
        //�ȉ��̎菇�œo�^No.�����肷��B
        //
        //�@@�������萔��No.�����ݒ�̏ꍇ�A���񒍕��Ɣ��f���A(2)�̌v�Z�菇�����{����B
        //
        //�A�������萔��No.�ɒl���ݒ肳��Ă���ꍇ�A�����������邢�͖�莞�Ɣ��f���A
        //�ȉ��̔��菈�������{����B
        // a.��Е��X���i�e�[�u���̎萔����������FLAG���A�uL�F�ŏI�����̏����v��
        //�ꍇ�́A(2)�̌v�Z�菇�����{����B
        // b.��Е��X���i�e�[�u���̎萔����������FLAG���A�uF:�������̏����v�̏ꍇ�́A
        //�������̓o�^No.�Ŏ萔�������肷�邽�߁A�ݒ肳�ꂽ�������萔��No.�����
        //�������萔��No.�}�Ԃ���o�^No.�����肷��B����āA(2)�̌v�Z�菇�����{����
        //�ɂ�����A(2)-�B�̏������X�L�b�v����B
        
        //(2) �萔���̌v�Z�菇
        
        //�@@�萔����������FLAG���擾����B
        //�y�Ώۃe�[�u���z
        //  ��Е��X���i�e�[�u��
        //�y�擾���ځz
        //  �萔����������FLAG
        //�y�擾�����z
        //  ���XID�@@���@@���͌ڋq�̕��XID�@@and
        //�萔�����i�R�[�h�@@���@@���͌ڋq�̎萔�����i�R�[�h
        InstBranchProductRow l_instBranchProductRow;
        try
        {
            l_instBranchProductRow =
                InstBranchProductDao.findRowByPk(
                    l_commission.getBranchId(),
                    l_commission.getCommissionProductCode());
        }
        catch (DataFindException dfe)
        {
            // �Y���f�[�^�Ȃ�
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataException de)
        {
            // DB�A�N�Z�X�G���[�B
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        if (l_instBranchProductRow == null)
        {
            // �Y������f�[�^�������B
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage1);
        }
        log.debug(
            "��Е��X���i�e�[�u���F�萔����������FLAG�F["
                + l_instBranchProductRow.getCommissionFeeCondFlag()
                + "]");
       
        // �A�萔��No.�A�ڋq���������擾����B
        //�������ɑΉ�����萔��No�ƌڋq���������擾����B
        //�L������YYYYMMDD�ŕۗL���Ă��邽�߁A�������̌^�ƈقȂ邽�߁A�ϊ����K�v�B
        //�y�Ώۃe�[�u���z
        //   �ϑ��萔���ڋq�����o�^�}�X�^�[
        //�y�擾���ځz
        //   �萔��No
        //   �ڋq������
        //�y�擾�����z
        //   �،���ЃR�[�h�@@���@@�،���ЃR�[�h�@@and
        //   ���XID�@@���@@���͌ڋq�̕��XID�@@and 
        //   �萔�����i�R�[�h�@@���@@���͌ڋq�̎萔�����i�R�[�h and
        //   ����ID�@@���@@���͌ڋq�̌���ID�@@and
        //   �L�����@@���@@������
        EquityCommAccountCondMstRow l_equityCommAccountCondMstRow = null;
        try
        {
            l_equityCommAccountCondMstRow = EquityCommAccountCondMstDao.findRowByPk(
                l_commission.getInstitutionCode(),
                l_commission.getBranchId(),
                l_subAccount.getAccountId(),
                l_commission.getCommissionProductCode(),
                l_sqlDateFormatter.format(l_commission.getOrderBizDate()));
        }
        catch (DataFindException dfe)
        {
            // �Y���f�[�^�Ȃ�
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataException de)
        {
            // DB�A�N�Z�X�G���[�B
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        log.debug("�ϑ��萔���ڋq�����o�^�}�X�^�[�F�萔��No�F["
            + l_equityCommAccountCondMstRow.getCommissionNo() + "]");
        log.debug("�ϑ��萔���ڋq�����o�^�}�X�^�[�F�ڋq�������F["
            + l_equityCommAccountCondMstRow.getAccountChargeRatio() + "]");
            
        // �B�}�Ԃ��擾����B
        //�y�Ώۃe�[�u���z
        //    �ϑ��萔���}�ԓo�^�}�X�^�[
        //�y�擾���ځz
        //    �}��
        //�y�擾�����z�i��S�j
        //�@@  �،���ЃR�[�h�@@���@@���͌ڋq�̏،���ЃR�[�h�@@and
        //    �萔�����i�R�[�h�@@���@@�萔�����i�R�[�h  and
        //    �����`���l���@@���@@(�����`���l�� or �g �h)�@@and
        //�@@  ����R�[�h(SONAR)�@@���@@(����R�[�h(SONAR) or �g �h)�@@and
        //�@@�@@���s����(��S1)�@@���@@(���s�����i1�F���s�@@2�F�w�l�j or �g �h)�@@and
        //�@@�@@�K�p�J�n�N�����@@���@@�������@@���@@�K�p�I���N�����@@and
        //�@@�@@�ٍϋ敪(��S2)�@@���@@(�ٍϋ敪 or �g �h) �@@and
        //�@@�@@�s��R�[�h(SONAR) (��S3)�@@���@@(�s��R�[�h(SONAR) or �g �h)
        //	  �����Y�����R�[�h(��S4)�@@���@@(�����Y�����R�[�h�i0005�FTOPIX�@@0016�F���o300�@@0018�F���o225�@@
        //	  	0019�F�~�j���o225�@@or �g �h�j
        //	  ���v��敪(��S4)�@@���@@(���v��敪�i1�F���v��łȂ��@@5�F���v��@@�@@or �g �h�j
        //�y�擾�����z
        //		�����`���l���A����R�[�h(SONAR)�A���s�����A�ٍϋ敪�A�s��R�[�h(SONAR)�A�����Y�����R�[�h�A
        //		���v��敪�̍~���w��
        //		�i�g �h���A�擾�����Ɏw�肵��������ɊY�����郌�R�[�h�̂ق���D�悵�Ď擾����j
        //�i���j�Ώۂ̃f�[�^��������Ȃ��ꍇ�́A�}�Ԃ��󔒂Ƃ��āA
        //�u�C�o�^No.�����肷��B�v�ȍ~�̏����𑱍s����B
        //�܂��A�Ώۂ̃f�[�^�����������������ꍇ�́A�P���R�[�h�ڂ��̗p����B
        //
        //�i��S�j�����`���l���A����R�[�h(SONAR)�A���s�����A�ٍϋ敪�A�s��R�[�h(SONAR)�A�����Y�����R�[�h�A
        //		���v��敪�̎擾�����ior�j�ɂ́A
        //		�g �h�i�P���̔��p�󔒕�����j���w�肷��B
        //�i��S1�j���s�����́u���s�^�w�l�v�̕ʂ̔���́A�萔���I�u�W�F�N�g.is�w�l()�ɂčs���B
        //�i��S2�j�y�擾�����z�ɂ�����ٍϋ敪�́A�萔���I�u�W�F�N�g.�ٍϋ敪���A�ȉ��̌����Ƃ����s�����l���w�肷��B
        //�@@�@@�@@ �萔���I�u�W�F�N�g.�ٍϋ敪��00�F���̑� �� �g �h�i�P���̔��p�󔒕�����j�ɕϊ�
        //�@@�@@�@@ �萔���I�u�W�F�N�g.�ٍϋ敪��00�F���̑� �� �萔���I�u�W�F�N�g.�ٍϋ敪�̂Q���ڂ݂̂̂P��������ɕϊ�
        //        ���������A�萔���I�u�W�F�N�g.�ٍϋ敪���P���̏ꍇ�́A
        //        ���萔���I�u�W�F�N�g.�ٍϋ敪�����̂܂܎g�p����B
        //        ���i�萔���I�u�W�F�N�g.�ٍϋ敪���P���̏ꍇ�́A
        //        ���@@�u(���X�s��ٍϕ�)�戵�����e�[�u���v��
        //        ���@@�u�ٍϋ敪(SONAR)�v�Ɠ����R�[�h���ݒ肳��Ă���B�j
        //�i��S3�j�s��R�[�h(SONAR)�̎w��͈ȉ��̒ʂ�Ƃ���B
        //�@@�@@�@@�萔���I�u�W�F�N�g.�s��R�[�h(SONAR)��null 
        //          �� (�萔���I�u�W�F�N�g.�s��R�[�h(SONAR) or �g �h �i�P���̔��p�󔒕�����j) ���w�肵�Č�������B
        //�@@�@@�@@�萔���I�u�W�F�N�g.�s��R�[�h(SONAR)��null
        //          ���@@�g �h�i�P���̔��p�󔒕�����j�݂̂��w�肵�Č�������B
        //�@@�@@�i��S4�j�����Y�����R�[�h�A���v��敪�̎w��͈ȉ��̒ʂ�Ƃ���B
        //�@@�@@�@@�萔���I�u�W�F�N�g. �����Y�����R�[�h�A���v��敪��null
        //          ���@@�g �h�i�P���̔��p�󔒕�����j�݂̂��w�肵�Č�������B
        
        StringBuffer l_sbEquityCommRevMstWhere = new StringBuffer();
        l_sbEquityCommRevMstWhere.append(" institution_code = ? ");//�،���ЃR�[�h
        l_sbEquityCommRevMstWhere.append(" and comm_product_code = ? ");//�萔�����i�R�[�h
        l_sbEquityCommRevMstWhere.append(" and (order_channel = ? or order_channel = ? ) ");//�����`���l��
        l_sbEquityCommRevMstWhere.append(" and (transaction_type = ? or transaction_type = ? ) ");//����R�[�h(SONAR)
        l_sbEquityCommRevMstWhere.append(" and (exec_cond_type = ? or exec_cond_type = ? ) ");//���s����
        l_sbEquityCommRevMstWhere.append(" and (appli_start_date <= ? and appli_end_date >= ?) ");//�K�p�N����
        l_sbEquityCommRevMstWhere.append(" and (pay_type = ? or pay_type = ? ) ");//�ٍϋ敪
        l_sbEquityCommRevMstWhere.append(" and (sonar_market_code = ? or sonar_market_code = ? ) ");//�s��R�[�h(SONAR)
        l_sbEquityCommRevMstWhere.append(" and (underlying_product_code = ? or underlying_product_code = ? ) ");//�����Y�����R�[�h
        l_sbEquityCommRevMstWhere.append(" and (day_trade_type = ? or day_trade_type = ? ) ");//���v��敪
        
        //get ���s�����F���s�����́u���s�^�w�l�v�̕ʂ̔���́A�萔���I�u�W�F�N�g.is�w�l()�ɂčs���B
        String l_strExecCondType;
        if(l_commission.isLimitPrice())
        {
            l_strExecCondType = WEB3CommissionExecutionConditionDef.LIMIT_PRICE;
        }
        else
        {
            l_strExecCondType = WEB3CommissionExecutionConditionDef.MARKET_PRICE;
        }
        //get �ٍϋ敪
        String l_strPayType;
        if(WEB3PayTypeDef.OTHER.equals(l_commission.getPayType()))
        {
            l_strPayType = SPACE;
        }
        else
        {
            //�萔���I�u�W�F�N�g.�ٍϋ敪�́A�Q���ł���΁A������ϊ����s��
            if(l_commission.getPayType().length() == 2)
            {
                l_strPayType = l_commission.getPayType().substring(1,2);
            }
            else
            {
                l_strPayType = l_commission.getPayType();
            }
        }
        //get �s��R�[�h(SONAR)
        String l_strSonarMarketCode;
        if(l_commission.getSonarMarketCode() == null)
        {
            l_strSonarMarketCode = SPACE;
        }
        else
        {
            l_strSonarMarketCode = l_commission.getSonarMarketCode();
        }
        
        //�����Y�����R�[�h���擾
        String l_strUnderlyingProductCode;
        if(l_commission.getUnderlyingProductCode() == null)
        {
        	l_strUnderlyingProductCode = SPACE;
        }
        else
        {
        	l_strUnderlyingProductCode = l_commission.getUnderlyingProductCode();
        }
        
        //���v��敪���擾
        String l_strDayTradeType;
        if(l_commission.getDayTradeType() == null)
        {
        	l_strDayTradeType = SPACE;
        }
        else
        {
        	l_strDayTradeType = l_commission.getDayTradeType();
        }
        
        Object[] l_objEquityCommRevMst =
            new Object[] {
                l_commission.getInstitutionCode(),//�،���ЃR�[�h
                l_commission.getCommissionProductCode(),//�萔�����i�R�[�h
                l_commission.getOrderChannel(),//�����`���l��
                new String(" "),//�����`���l�� == ' '
                l_commission.getSonarTradedCode(),//����R�[�h(SONAR)
                new String(" "),//����R�[�h(SONAR) == ' '
                l_strExecCondType,//���s����
                new String(" "),//���s���� == ' '
                l_sqlDateFormatter.format(l_commission.getOrderBizDate()),//�K�p�N����
                l_sqlDateFormatter.format(l_commission.getOrderBizDate()),//�K�p�N����
                l_strPayType,//�ٍϋ敪
                new String(" "),//�ٍϋ敪 == ' '
                l_strSonarMarketCode,//�s��R�[�h(SONAR)
                new String(" "),    //�s��R�[�h(SONAR) == ' '
                l_strUnderlyingProductCode, //�����Y�����R�[�h
                new String(" "),            //�����Y�����R�[�h == ' '
                l_strDayTradeType,          //���v��敪
                new String(" ")};          //���v��敪 == ' '
        
        //�y�擾�����z
        //�@@�@@�����`���l���A����R�[�h(SONAR)�A���s�����A�ٍϋ敪�A�s��R�[�h(SONAR)�A�����Y�����R�[�h�A
        //		���v��敪�̍~���w��
        String l_strOrderBy = "order_channel desc , transaction_type desc , exec_cond_type desc , " +
            "pay_type desc, sonar_market_code desc, underlying_product_code desc, day_trade_type desc";
        
        String l_strCommissionNo = null;// �ԋp���F�萔��No
        String l_strRevision = null;      // �ԋp���F�萔��No�}��
        
        //�������萔��No.�����ݒ�̏ꍇ�A���񒍕��Ɣ��f���A(2)�̌v�Z�菇�����{����B        
        if (l_commission.getOrgCommissionNo() == null)
        {
            // ���񒍕�
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_list =
                    l_qp.doFindAllQuery(
                        EquityCommRevMstRow.TYPE,
                        l_sbEquityCommRevMstWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_objEquityCommRevMst);
            }
            catch (DataException de)
            {
                // DB�A�N�Z�X�G���[
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            l_intSize = l_list.size();
            if (l_intSize < 1)
            {
                // �Ώۂ̃f�[�^��������Ȃ��ꍇ�́A�}�Ԃ��󔒂Ƃ��āA
                //�u�C�o�^No.�����肷��B�v�ȍ~�̏����𑱍s����B
                l_strRevision = SPACE; 
                l_strCommissionNo = l_equityCommAccountCondMstRow.getCommissionNo();
            }
            else 
            {
                //�Ώۂ̃f�[�^�����������������ꍇ�́A�P���R�[�h�ڂ��̗p����B
                EquityCommRevMstRow l_equityCommRevMstRow = (EquityCommRevMstRow)l_list.get(0);
                l_strCommissionNo = l_equityCommAccountCondMstRow.getCommissionNo();
                log.debug("�萔��No = �ϑ��萔���ڋq�����o�^�}�X�^�[�D�萔��No�F["
                    + l_equityCommAccountCondMstRow.getCommissionNo() + "]");
                l_strRevision = l_equityCommRevMstRow.getRevision();
                log.debug("�萔��No�}�� = �ϑ��萔���}�ԓo�^�}�X�^�[�D�}�ԁF["
                    + l_equityCommRevMstRow.getRevision() + "]");
            }

        }
        //�������萔��No.�ɒl���ݒ肳��Ă���ꍇ
        else
        {
            if (WEB3CommissionFeeCondFlagDef.FIRST_ORDER.equals(l_instBranchProductRow.getCommissionFeeCondFlag()))
            {
                // �萔���������u�������̏����v�̏ꍇ
                l_strCommissionNo = l_commission.getOrgCommissionNo();
                l_strRevision = l_commission.getOrgCommissionRevNo();
                log.debug("�萔���������u�������̏����v�̏ꍇ");
                log.debug("�萔��No = �������萔��No�F["
                    + l_commission.getOrgCommissionNo() + "]");
                log.debug("�萔��No�}�� = �������萔��No�}�ԁF["
                    + l_commission.getOrgCommissionRevNo() + "]");
            }
            else
            {
                // ��Е��X���i�e�[�u���̎萔����������FLAG���A
                //�uL�F�ŏI�����̏����v�̏ꍇ�́A(2)�̌v�Z�菇�����{����B
                try
                {
                    l_qp = Processors.getDefaultProcessor();
                    l_list =
                        l_qp.doFindAllQuery(
                            EquityCommRevMstRow.TYPE,
                            l_sbEquityCommRevMstWhere.toString(),
                            l_strOrderBy,
                            null,
                            l_objEquityCommRevMst);
                }
                catch (DataException de)
                {
                    // DB�A�N�Z�X�G���[
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }
                l_intSize = l_list.size();
                if (l_intSize < 1)
                {
                    // �Ώۂ̃f�[�^��������Ȃ��ꍇ�́A�}�Ԃ��󔒂Ƃ��āA
                    //�u�C�o�^No.�����肷��B�v�ȍ~�̏����𑱍s����B
                    l_strRevision = SPACE; 
                    l_strCommissionNo = l_equityCommAccountCondMstRow.getCommissionNo();
                }
                else 
                {
                    //�Ώۂ̃f�[�^�����������������ꍇ�́A�P���R�[�h�ڂ��̗p����B
                    EquityCommRevMstRow l_equityCommRevMstRow = (EquityCommRevMstRow)l_list.get(0);
                    l_strCommissionNo = l_equityCommAccountCondMstRow.getCommissionNo();
                    log.debug("�萔��No = �ϑ��萔���ڋq�����o�^�}�X�^�[�D�萔��No�F["
                        + l_equityCommAccountCondMstRow.getCommissionNo() + "]");
                    l_strRevision = l_equityCommRevMstRow.getRevision();
                    log.debug("�萔��No�}�� = �ϑ��萔���}�ԓo�^�}�X�^�[�D�}�ԁF["
                        + l_equityCommRevMstRow.getRevision() + "]");
                }
            }
        }
        
        // �C�o�^No.�����肷��B
        //   �萔��No.�{�}�Ԃ�茈�肵���ԍ���o�^No.�Ƃ���B
        log.debug("�o�^No�F[" + l_strCommissionNo + l_strRevision + "]");  

        // �D - 1�萔�������擾����B
        //�y�Ώۃe�[�u��
        //   �ϑ��萔�������e�[�u��
        // �y�擾���ځz
        //   ������
        //   �t�����z
        //   �萔���搔
        // �y�擾�����z
        //    �،���ЃR�[�h�@@���@@���͌ڋq�̏،���ЃR�[�h�@@and
        //    �萔�����i�R�[�h�@@���@@�萔�����i�R�[�h and
        //    �o�^No.�@@���@@�C�Ō��肵���o�^No.�@@and
        //    ��������i���j�@@���@@���o��v�Z�p����i���P�j�@@���@@��������i���j�@@and
        //    �K�p�J�n�N�����@@���@@�������@@���@@�K�p�I���N����  
        //----------------------------------------------------------------
        //  �i���P�j�������́u�S����������v���A���莞�́u��������v���A��莞�́u������v�̂悤�ɁA
        // �ϑ��萔���Z�o�̊�ƂȂ������u���o��v�Z�p����v�Ƃ��Ďg�p����B
        // �ϑ��萔�������e�[�u���́u��������i���j�v�u��������i���j�v�͖��~�P�ʂŐݒ肳��Ă��邽�߁A
        // �擾�����ɂ́A�u���o��v�Z�p����i���P�j�v���h10000�h�Ŋ������l�i�����_�ȉ����L���j���w�肷��
        // ���Ƃɒ��ӁB
        //----------------------------------------------------------------       
        //�i���j�}�ԂɗL���������ݒ肳��Ă��鎞�i�󔒂ł͂Ȃ���ԁj�ɁA
        //  �o�^No.�Ō������đΏۂ̃f�[�^��������Ȃ��ꍇ�́A�}�Ԃ��󔒂ɂ���
        //  �萔��No�����Ō�������B
        EquityCommCondRow l_equityCommCondRow = null;
        String l_strWhereClause2 = "institution_code=? and comm_product_code=? "
            + "and reg_no=? and (min_turnover<? and max_turnover>=?) "
            + "and (appli_start_date<=? and appli_end_date>=?)"; 
        BigDecimal l_bdTurnover =
            new BigDecimal(l_commission.getExpensesCalcAmount()).divide(
                new BigDecimal(WEB3GentradeNumberConstDef.MAN),
                l_intScale,
                BigDecimal.ROUND_HALF_EVEN);    
        try
        {
            if (l_qp == null)
            {
                l_qp = Processors.getDefaultProcessor();
            }
            l_list = l_qp.doFindAllQuery(
                EquityCommCondRow.TYPE,
                l_strWhereClause2,
                new Object[] { l_commission.getInstitutionCode(),
                    l_commission.getCommissionProductCode(),
                    l_strCommissionNo + l_strRevision,
                    l_bdTurnover,
                    l_bdTurnover,
                    l_sqlDateFormatter.format(l_commission.getOrderBizDate()),
                    l_sqlDateFormatter.format(l_commission.getOrderBizDate()) });
        }
        catch (DataException de)
        {
            // DB�A�N�Z�X�G���[
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        l_intSize = l_list.size();
        if (l_intSize < 1)
        {
            // �Y������f�[�^�������ꍇ
            //�}�ԂɗL���������ݒ肳��Ă��鎞�i�󔒂ł͂Ȃ���ԁj�ɁA
            //  �o�^No.�Ō������đΏۂ̃f�[�^��������Ȃ��ꍇ�́A�}�Ԃ��󔒂ɂ���
            //  �萔��No�����Ō�������B
            log.debug("�}�ԂɗL���������ݒ肳��Ă��鎞�i�󔒂ł͂Ȃ���ԁj�ɁA" +
                "�o�^No.�Ō������đΏۂ̃f�[�^��������Ȃ��ꍇ�́A" +
                "�}�Ԃ��󔒂ɂ��Ď萔��No�����Ō�������B");
            try 
            {
                l_strRevision = SPACE;
                l_list = l_qp.doFindAllQuery(
                    EquityCommCondRow.TYPE,
                    l_strWhereClause2,
                    new Object[] { l_commission.getInstitutionCode(),
                        l_commission.getCommissionProductCode(),
                        l_strCommissionNo + l_strRevision,
                        l_bdTurnover,
                        l_bdTurnover,
                        l_sqlDateFormatter.format(l_commission.getOrderBizDate()),
                        l_sqlDateFormatter.format(l_commission.getOrderBizDate()) });
            }
            catch (DataException de)
            {
                // DB�A�N�Z�X�G���[
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            l_intSize = l_list.size();
            if (l_intSize < 1)
            {
                // �Y������f�[�^�������ꍇ
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage10);
            }
            else if (l_intSize == 1)
            {
                l_equityCommCondRow = (EquityCommCondRow)l_list.get(0);
            }
            else
            {
                // �Y������f�[�^���d�����Ă���ꍇ
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage11);
            }
        }
        else if (l_intSize == 1)
        {
            l_equityCommCondRow = (EquityCommCondRow)l_list.get(0);
        }
        else
        {
            // �Y������f�[�^���d�����Ă���ꍇ
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage11);
        }
        log.debug("�ϑ��萔�������e�[�u���F�������F["
            + l_equityCommCondRow.getChargeRatio() + "]");
        log.debug("�ϑ��萔�������e�[�u���F�t�����z�F["
            + l_equityCommCondRow.getAddedPrice() + "]");
        log.debug("�ϑ��萔�������e�[�u���F�萔���搔�F["
                + l_equityCommCondRow.getCommitionPerUnit() + "]");
        
        // �D - 2 �萔�������擾����B
        //�y�Ώۃe�[�u���z
        //   �ϑ��萔�������o�^�}�X�^�[
        //�y�擾���ځz
		// 	  �Œ�萔��
		//    �ō��萔��
		//    ���ʐݒ�J�n�N����
		//    ���ʐݒ�I���N����
		//    ���ʒ�����
		//    ���ʍŒ�萔��
		//    ���ʍō��萔��
        //    �萔���^�C�v
        //�y�擾�����z
        //    �،���ЃR�[�h�@@���@@���͌ڋq�̏،���ЃR�[�h�@@and
        //�@@�@@�萔�����i�R�[�h�@@���@@�萔�����i�R�[�h and
        //�@@�@@�o�^No.�@@���@@�C�Ō��肵���o�^No.�@@and
        //    �K�p�J�n�N�����@@���@@�������@@���@@�K�p�I���N����
        //----------------------------------------------------------
        //�i���j�}�ԂɗL���������ݒ肳��Ă��鎞�i�󔒂ł͂Ȃ���ԁj�ɁA
        //  �o�^No.�Ō������đΏۂ̃f�[�^��������Ȃ��ꍇ�́A
        //  �}�Ԃ��󔒂ɂ��Ď萔��No�����Ō�������B
        EquityCommCondMstRow l_eccmRow = null;
        String l_strWhereClause3 = 
            "institution_code=? and comm_product_code=? and reg_no=? and (appli_start_date<=? and appli_end_date>=?)";
        try
        {
            l_list = l_qp.doFindAllQuery(
                EquityCommCondMstRow.TYPE,
                l_strWhereClause3,
                new Object[] { l_commission.getInstitutionCode(),
                    l_commission.getCommissionProductCode(),
                    l_strCommissionNo + l_strRevision,
                    l_sqlDateFormatter.format(l_commission.getOrderBizDate()),
                    l_sqlDateFormatter.format(l_commission.getOrderBizDate()) });
        }
        catch (DataException de)
        {
            // DB�A�N�Z�X�G���[
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        l_intSize = l_list.size();
        if (l_intSize < 1)
        {
            // �Y������f�[�^�������ꍇ
            //�}�ԂɗL���������ݒ肳��Ă��鎞�i�󔒂ł͂Ȃ���ԁj�ɁA
            //  �o�^No.�Ō������đΏۂ̃f�[�^��������Ȃ��ꍇ�́A�}�Ԃ��󔒂ɂ���
            //  �萔��No�����Ō�������B
            log.debug("�}�ԂɗL���������ݒ肳��Ă��鎞�i�󔒂ł͂Ȃ���ԁj�ɁA" +
                "�o�^No.�Ō������đΏۂ̃f�[�^��������Ȃ��ꍇ�́A" +
                "�}�Ԃ��󔒂ɂ��Ď萔��No�����Ō�������B");
            try 
            {
                l_strRevision = SPACE;
                l_list = l_qp.doFindAllQuery(
                    EquityCommCondMstRow.TYPE,
                    l_strWhereClause3,
                    new Object[] { l_commission.getInstitutionCode(),
                        l_commission.getCommissionProductCode(),
                        l_strCommissionNo + l_strRevision,
                        l_sqlDateFormatter.format(l_commission.getOrderBizDate()),
                        l_sqlDateFormatter.format(l_commission.getOrderBizDate()) });
            }
            catch (DataException de)
            {
                // DB�A�N�Z�X�G���[
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            l_intSize = l_list.size();
            if (l_intSize < 1)
            {
                // �Y������f�[�^�������ꍇ
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage13);
            }
            else if (l_intSize == 1)
            {
                l_eccmRow = (EquityCommCondMstRow)l_list.get(0);
            }
            else
            {
                // �Y������f�[�^���d�����Ă���ꍇ
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage14);
            }
        }
        else if (l_intSize == 1)
        {
            l_eccmRow = (EquityCommCondMstRow)l_list.get(0);
        }
        else
        {
            // �Y������f�[�^���d�����Ă���ꍇ
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_strErrorMessage14);
        }
        log.debug("�ϑ��萔�������o�^�}�X�^�[�F�Œ�萔���F["
                + l_eccmRow.getMinCommission() + "]");
        log.debug("�ϑ��萔�������o�^�}�X�^�[�F���ʒ������F["
                + l_eccmRow.getSpcChargeRatio() + "]");
        log.debug("�ϑ��萔�������o�^�}�X�^�[�F���ʍŒ�萔���F["
                + l_eccmRow.getSpcMinCommission() + "]");
        log.debug("�ϑ��萔�������o�^�}�X�^�[�F�萔���R�[�X�R�[�h�F["
                + l_eccmRow.getCommissionCourseDiv() + "]");
        

        //�E�ϑ��萔�����v�Z����B
        //
        //a.�ʏ�̏ꍇ�̎萔�������߂�B
		//�@@a-1�D�萔���搔�ɒl���ݒ肳��Ă���ꍇ�i��ZERO�j
		//   �@@�@@�萔��a �� �萔���I�u�W�F�N�g.���� �~ �萔���搔
		//  a-2�D�萔���搔�ɒl���ݒ肳��Ă��Ȃ��ꍇ�i��ZERO�j
		//   �@@�@@�萔��a �� ���o��v�Z�p��� �~ ������ �{ �t�����z�@@�i�����_�ȉ��؂�̂āj
        //  ���萔��a�̌v�Z���ʂ��A�Œ�萔����菭�z�̏ꍇ�́A�Œ�萔�����萔��a�Ƃ���B
        //  ���萔���搔�ɒl���ݒ肳��Ă���ꍇ�̂݁A�萔���I�u�W�F�N�g.�萔���搔�փZ�b�g����B
        //
        //b.���������������ʒ��������Ԃ̏ꍇ�̒ǉ��v�Z������B
        //   �萔��b �� �萔��a �~ ���ʒ������@@�i�����_�ȉ��؂�̂āj
        //   ���萔��b�̌v�Z���ʂ��A���ʍŒ�萔����菭�z�̏ꍇ�́A���ʍŒ�萔�����萔��b�Ƃ���B
        // �@@���萔��b�̌v�Z���ʂ��A���ʍō��萔����葽�z�̏ꍇ�́A���ʍō��萔�����萔��b�Ƃ���B
        //
        //c.�ϑ��萔�� �� �萔��a�ib.�̌v�Z�������ꍇ�͎萔��b�j �~ �ڋq�������@@�i�����_�ȉ��؂�̂āj
        //�@@���ϑ��萔���̌v�Z���ʂ��A�ō��萔����葽�z�̏ꍇ�́A�ō��萔�����ϑ��萔���Ƃ���B
        //
        //d.����O�����̏ꍇ�A�O�~�i�Œ�j�Ƃ���B
        //
        //�i���j���Y���������O�����ł������ꍇ�́A�ϑ��萔�����O�Ƃ��邪�A
        //�萔���m���D����ю萔���m���D�}�ԁA�萔���R�[�X�R�[�h���̎萔�����ɂ��ĕԋp���K�v�Ȃ��߁A
        // �ϑ��萔�������o�^�}�X�^�[�̎擾���������{����B
        
        //����O�����̏ꍇ
        if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_commission.getSonarTradedCode()))
        {
            // ���Y���������O�����̏ꍇ�́A�ϑ��萔�����[���Ƃ���
            l_commission.setCommission(0.0);
            //�萔���m���D
            l_commission.setCommissionNo(l_strCommissionNo);
            //�萔���m���D�}��
            l_commission.setCommissionRevNo(l_strRevision);
            //set �萔���R�[�X�R�[�h
            l_commission.setCommissionCourseDiv(l_eccmRow.getCommissionCourseDiv());
            log.debug("����O�����̏ꍇ �F �ϑ��萔�� = [ 0 ]");
            return;
        }

        double l_dblCommission = 0.0;     // �ԋp���F�萔�����z
        
        //a.�ʏ�̏ꍇ�̎萔�������߂�
        BigDecimal l_bdCommissionA = null;       //�萔��a

        //�@@a-1�D�萔���搔�ɒl���ݒ肳��Ă���ꍇ�i��ZERO�j
        if (l_equityCommCondRow.getCommitionPerUnit() != 0)
        {
        	//���ʂ��擾
            BigDecimal l_bdQuantity = new BigDecimal(l_commission.getQuantity());
            //�萔���搔���擾
            BigDecimal l_bdCommitionPerUnit = new BigDecimal(l_equityCommCondRow.getCommitionPerUnit());
            //�萔���I�u�W�F�N�g.�萔���搔�փZ�b�g����
            l_commission.setCommitionPerUnit(l_equityCommCondRow.getCommitionPerUnit());
    		//�萔��a �� �萔���I�u�W�F�N�g.���� �~ �萔���搔
            l_bdCommissionA = l_bdQuantity.multiply(l_bdCommitionPerUnit);

            log.debug("�����I�u�W�F�N�g.���ʁF[" + l_bdQuantity.doubleValue() + "]");
            log.debug("�萔���搔�F[" + l_bdCommitionPerUnit.doubleValue() + "]");
            log.debug("�萔��a �� �萔���I�u�W�F�N�g.���� �~ �萔���搔 �F[" + l_bdCommissionA.doubleValue() + "]");
        }
		//  a-2�D�萔���搔�ɒl���ݒ肳��Ă��Ȃ��ꍇ�i��ZERO�j
        else
        {
        	//���o��v�Z�p������擾
        	BigDecimal l_bdRestraintTurnover = new BigDecimal(l_commission.getExpensesCalcAmount());
        	//���������擾
        	BigDecimal l_bdChargeRatio = new BigDecimal(l_equityCommCondRow.getChargeRatio()).divide(
            	l_oneHandred, l_intScale, BigDecimal.ROUND_HALF_EVEN);
        	//�t�����z���擾
        	BigDecimal l_bdAddedPrice = new BigDecimal(l_equityCommCondRow.getAddedPrice());
    		//�萔��a �� ���o��v�Z�p��� �~ ������ �{ �t�����z�@@�i�����_�ȉ��؂�̂āj
            l_bdCommissionA = l_bdRestraintTurnover.multiply(l_bdChargeRatio).add(l_bdAddedPrice);

            //�����_�ȉ��؂�̂�
            long l_lngCommissionA = (long)l_bdCommissionA.doubleValue();
            l_bdCommissionA = new BigDecimal(l_lngCommissionA);

            log.debug("���o��v�Z�p����F[" + l_bdRestraintTurnover.doubleValue() + "]");
            log.debug("�������F[" + l_bdChargeRatio.doubleValue() + "]");
            log.debug("�t�����z�F[" + l_bdAddedPrice.doubleValue() + "]");
            log.debug("�萔��a �� ���o��v�Z�p��� �~ ������ �{ �t�����z�@@�i�����_�ȉ��؂�̂āj�F[" + l_bdCommissionA.doubleValue() + "]");
        }

        //�Œ�萔�����擾
        BigDecimal l_bdMinCommission = new BigDecimal(l_eccmRow.getMinCommission());
        //�ō��萔�����擾
        BigDecimal l_bdMaxCommission = new BigDecimal(l_eccmRow.getMaxCommission());

        BigDecimal l_bdAccountChargeRatio = new BigDecimal(
        	l_equityCommAccountCondMstRow.getAccountChargeRatio()).divide(
        		l_oneHandred, l_intScale, BigDecimal.ROUND_HALF_EVEN);
        
        if (l_bdCommissionA.compareTo(l_bdMinCommission) == -1)
        {
            // �萔��a�̌v�Z���ʂ��A�Œ�萔����菭�z�̏ꍇ��
        	// �Œ�萔�����萔��a�Ƃ���
            l_bdCommissionA = l_bdMinCommission;
            log.debug("�萔�����̌v�Z���ʂ��Œ�萔����菬�z�̏ꍇ�F[" + l_bdCommissionA + "]");
        }
        
        //b.���������������ʒ��������Ԃ̏ꍇ�̒ǉ��v�Z������B
        BigDecimal l_bdCommissionB = null;
        if (l_eccmRow.getSpcStartDate() != null  && l_eccmRow.getSpcEndDate() != null)
        {
            SimpleDateFormat l_simpleDateFormat =
                new SimpleDateFormat(l_strSqlDateFormat);
            String l_strSpcStartDate =
                l_simpleDateFormat.format(l_eccmRow.getSpcStartDate());
            String l_strSpcEndDate =
                l_simpleDateFormat.format(l_eccmRow.getSpcEndDate());
            String l_strOrderBizDate =
                l_simpleDateFormat.format(l_commission.getOrderBizDate());
            if (l_strOrderBizDate.compareTo(l_strSpcStartDate) >= 0 && l_strOrderBizDate.compareTo(l_strSpcEndDate) <= 0)
            {
                // ���������������ʒ��������Ԃ̏ꍇ�̒ǉ��v�Z������
                // �萔���� �� �萔���� �~ ���ʒ�����
                BigDecimal l_bdSpcChargeRatio =
                    new BigDecimal(l_eccmRow.getSpcChargeRatio()).divide(
                        l_oneHandred,
                        l_intScale,
                        BigDecimal.ROUND_HALF_EVEN);
                        
                log.debug("�ݒ�J�n�N����(���ʒ���) �F [" + l_strSpcStartDate + "]");
                log.debug("�ݒ�I���N����(���ʒ���) �F [" + l_strSpcEndDate + "]");
                log.debug("���������� �F [" + l_strOrderBizDate + "]");
                l_bdCommissionB = l_bdCommissionA.multiply(l_bdSpcChargeRatio);
                //�����_�ȉ��؂�̂�
                long l_lngCommissionB = (long)l_bdCommissionB.doubleValue();
                l_bdCommissionB = new BigDecimal(l_lngCommissionB);
                log.debug("�萔�����F���������������ʒ��������Ԃ̏ꍇ�̒ǉ��v�Z������i�����_�ȉ��؂�̂āj");
                log.debug("���ʒ������F[" + l_bdSpcChargeRatio + "]");
                log.debug("�萔���� �� �萔���� �~ ���ʒ������F[" + l_bdCommissionB + "]");
                //���ʍŒ�萔��
                BigDecimal l_bdSpcMinCommission = new BigDecimal(l_eccmRow.getSpcMinCommission());
                //���ʍō��萔��
                BigDecimal l_bdSpcMaxCommission = new BigDecimal(l_eccmRow.getSpcMaxCommission());
                
                if (l_bdCommissionB.compareTo(l_bdSpcMinCommission) == -1)
                {
                    // �萔�����̌v�Z���ʂ����ʍŒ�萔����菬�z�̏ꍇ��
                    // ���ʍŒ�萔�����萔�����Ƃ���
                    l_bdCommissionB = l_bdSpcMinCommission;
                    log.debug("�萔�����̌v�Z���ʂ����ʍŒ�萔����菬�z�̏ꍇ�F[" + l_bdCommissionB + "]");
                }
                else if (l_bdCommissionB.compareTo(l_bdSpcMaxCommission) == 1)
                {
                    // �萔��b�̌v�Z���ʂ��A���ʍō��萔����葽�z�̏ꍇ��
                    // ���ʍō��萔�����萔��b�Ƃ���
                    l_bdCommissionB = l_bdSpcMaxCommission;
                    log.debug("�萔��b�̌v�Z���ʂ��A���ʍō��萔����葽�z�̏ꍇ�F[" + l_bdCommissionB + "]");
                }
            }
        }

        //c.�ϑ��萔�� �� �萔��a�ib.�̌v�Z�������ꍇ�͎萔��b�j �~ �ڋq������
        BigDecimal l_bdCommissionC;
        if(l_bdCommissionB == null)
        {
            l_bdCommissionC = l_bdCommissionA.multiply(l_bdAccountChargeRatio);
        }
        else
        {
            l_bdCommissionC = l_bdCommissionB.multiply(l_bdAccountChargeRatio);
        }
        //�����_�ȉ��؂�̂�
        long l_lngCommissionC = (long)l_bdCommissionC.doubleValue();
        l_bdCommissionC = new BigDecimal(l_lngCommissionC);

        log.debug("�ڋq�������F[" + l_bdAccountChargeRatio.doubleValue() + "]");
        log.debug("�ϑ��萔��c���v�Z����i�����_�ȉ��؂�̂āj");
        log.debug("�ϑ��萔��c �� �萔��a�ib.�̌v�Z�������ꍇ�͎萔��b�j �~ �ڋq������ �F[" + l_bdCommissionC + "]");

        if (l_bdCommissionC.compareTo(l_bdMaxCommission) == 1)
        {
            //�ϑ��萔���̌v�Z���ʂ��A�ō��萔����葽�z�̏ꍇ�́A�ō��萔�����ϑ��萔���Ƃ���B
            l_bdCommissionC = l_bdMaxCommission;
            log.debug("�ϑ��萔���̌v�Z���ʂ��A�ō��萔����葽�z�̏ꍇ�F[" + l_bdCommissionC + "]");
        }
        l_dblCommission = l_bdCommissionC.doubleValue();
        
        // �ԋp���̐ݒ�
        //set �萔�����z
        l_commission.setCommission(l_dblCommission);
        //set �萔��No
        l_commission.setCommissionNo(l_strCommissionNo);
        //set �萔��No�}��
        l_commission.setCommissionRevNo(l_strRevision);
        //set �萔���R�[�X�R�[�h
        l_commission.setCommissionCourseDiv(l_eccmRow.getCommissionCourseDiv());
        //set �Œ�萔��
        l_commission.setMinCommission(l_eccmRow.getMinCommission());
        
        log.exiting(STR_METHOD_NAME);
        
    }
}
@
