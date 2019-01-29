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
filename	WEB3GentradeTaxRate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ŗ��N���X(WEB3GentradeTaxRate.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/04 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�ŗ��j<BR>
 *<BR>
 * �e��ŗ���\������B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public class WEB3GentradeTaxRate implements WEB3GentradeTaxType
{

    /**
     * ���t�̃t�H�[�}�b�^�B<BR>
     */
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * where �����F�،���ЃR�[�h���w�肷��ꍇ<BR>
     */
    private final static String WHERE_CLAUSE_1 = 
        "institution_code=? and tax_type=? and (appli_start_date<=? and appli_end_date>=?)";

    /**
     * where �����F�،���ЃR�[�h���w�肵�Ȃ��ꍇ<BR>
     */
    private final static String WHERE_CLAUSE_2 = 
        "tax_type=? and (appli_start_date<=? and appli_end_date>=?)";

    /**
     * �G���[���b�Z�[�W�F�ŗ��e�[�u���ɊY������f�[�^���d�����Ă��܂�<BR>
     */
    private final static String ERROR_MESSAGE_1 = 
        "�ŗ��e�[�u���ɊY������f�[�^������܂���B";

    /**
     * �G���[���b�Z�[�W�F�ŗ��e�[�u���ɊY������f�[�^���d�����Ă��܂�<BR>
     */
    private final static String ERROR_MESSAGE_2 = 
        "�ŗ��e�[�u���ɊY������f�[�^���d�����Ă��܂��B";

    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeTaxRate.class);

    /**
     * �ŋ��̎�ށB<BR>
     */
    private String taxType;

    /**
     * �Ŏ�ނɑ΂���ŗ��i���j�B<BR>
     */
    private double taxRate;

    /**
     * �،���ЃR�[�h�B<BR>
     */
    private String institutionCode;

    /**
     * �������B<BR>
     */
    private Timestamp orderBizDate;

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_strInstitutuinCode �،���ЃR�[�h
     * @@param l_strTaxType �Ŏ��
     * @@param l_tsOrderBizDate ������
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GentradeTaxRate(String l_strInstitutionCode, String l_strTaxType, Timestamp l_tsOrderBizDate) throws WEB3BaseException
    {
        institutionCode = l_strInstitutionCode;
        taxType = l_strTaxType;
        orderBizDate = l_tsOrderBizDate;

        final String STR_METHOD_NAME = "WEB3GentradeTaxRate(String, String, Timestamp)";

        log.entering(STR_METHOD_NAME);

        List l_list = null;
        int l_intSize = 0;
        TaxRateTableRow l_row = null;

        try
        {
            SimpleDateFormat l_formatter = GtlUtils.getThreadSafeSimpleDateFormat(DATE_FORMAT);
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_list = l_qp.doFindAllQuery(
                TaxRateTableRow.TYPE,
                WHERE_CLAUSE_1,
                new Object[] { institutionCode, taxType, l_formatter.format(orderBizDate), l_formatter.format(orderBizDate) });
        }
        catch (DataQueryException exp)
        {
            // DB�A�N�Z�X�G���[
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                exp.getMessage(), exp);
        }
        catch (DataNetworkException exp)
        {
            // DB�A�N�Z�X�G���[
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                exp.getMessage(), exp);
        }

        l_intSize = l_list.size();
        if (l_intSize < 1)
        {
            // �Y������f�[�^�������ꍇ
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                ERROR_MESSAGE_1);
        }
        else if (l_intSize == 1)
        {
            l_row = (TaxRateTableRow)l_list.get(0);
            log.debug("�،���ЃR�[�h�F[" + l_row.getInstitutionCode() + "]");
            log.debug("�Ŏ�ށF[" + l_row.getTaxType() + "]");
            log.debug("�K�p�J�n�N�����F[" + l_row.getAppliStartDate().toString() + "]");
            log.debug("�K�p�I���N�����F[" + l_row.getAppliEndDate().toString() + "]");
            log.debug("�ŗ��F[" + l_row.getTaxRate() + "]");
        }
        else
        {
            // �Y������f�[�^���d�����Ă���ꍇ
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80004, 
                this.getClass().getName() + "." + STR_METHOD_NAME,  
                ERROR_MESSAGE_2);
        }
        taxRate = l_row.getTaxRate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �R���X�g���N�^�B<BR>
     *<BR>
     * @@param l_strTaxType �Ŏ��
     * @@param l_tsOrderBizDate ������
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3GentradeTaxRate(String l_strTaxType, Timestamp l_tsOrderBizDate) throws WEB3BaseException
    {
        taxType = l_strTaxType;
        orderBizDate = l_tsOrderBizDate;

        final String STR_METHOD_NAME = "WEB3GentradeTaxRate(String, Timestamp)";

        log.entering(STR_METHOD_NAME);

        List l_list = null;
        int l_intSize = 0;
        TaxRateTableRow l_row = null;

        try
        {
            SimpleDateFormat l_formatter = GtlUtils.getThreadSafeSimpleDateFormat(DATE_FORMAT);
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_list = l_qp.doFindAllQuery(
                TaxRateTableRow.TYPE,
                WHERE_CLAUSE_2,
                new Object[] { taxType, l_formatter.format(orderBizDate), l_formatter.format(orderBizDate) });
        }
        catch (DataQueryException dqe)
        {
            // DB�A�N�Z�X�G���[
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                dqe.getMessage(), dqe);
        }
        catch (DataNetworkException dne)
        {
            // DB�A�N�Z�X�G���[
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                dne.getMessage(), dne);
        }

        l_intSize = l_list.size();
        if (l_intSize < 1)
        {
            // �Y������f�[�^�������ꍇ
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                ERROR_MESSAGE_1);
        }
        else
        {
            l_row = (TaxRateTableRow)l_list.get(0);
            log.debug("�،���ЃR�[�h�F[" + l_row.getInstitutionCode() + "]");
            log.debug("�Ŏ�ށF[" + l_row.getTaxType() + "]");
            log.debug("�K�p�J�n�N�����F[" + l_row.getAppliStartDate().toString() + "]");
            log.debug("�K�p�I���N�����F[" + l_row.getAppliEndDate().toString() + "]");
            log.debug("�ŗ��F[" + l_row.getTaxRate() + "]");
        }
        taxRate = l_row.getTaxRate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ŗ����擾����B<BR>
     *<BR>
     * @@return �ŗ��i���j
     */
    public double getTaxRate()
    {
        return taxRate;
    }

    /**
     * �Ŏ�ނ��擾����B<BR>
     *<BR>
     * @@return �Ŏ��
     */
    public String getTaxType()
    {
        return taxType;
    }

    /**
     * �،���ЃR�[�h���擾����B<BR>
     *<BR>
     * @@return �،���ЃR�[�h
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * ���������擾����B<BR>
     *<BR>
     * @@return ������
     */
    public Timestamp getOrderBizDate()
    {
        return orderBizDate;
    }
}
@
