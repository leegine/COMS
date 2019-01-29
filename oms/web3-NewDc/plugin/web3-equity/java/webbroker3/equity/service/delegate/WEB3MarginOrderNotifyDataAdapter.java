head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������͒ʒm�f�[�^�A�_�v�^(WEB3MarginOrderNotifyDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 䈋� (���u) �V�K�쐬
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������͒ʒm�f�[�^�A�_�v�^�j�B<BR>
 * <BR>
 * �M�p����������͒ʒm�f�[�^�A�_�v�^�N���X
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyDataAdapter
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOrderNotifyDataAdapter.class);

    /**
     * (�����������͒ʒm�L���[Params)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�̂P���R�[�h�B
     */
    private HostEqtypeOrderReceiptParams eqtypeOrderReceiptParams;

    /**
     * @@roseuid 4142B32E00A1
     */
    private WEB3MarginOrderNotifyDataAdapter()
    {

    }

    /**
     * (create)<BR>
     * �M�p����������͒ʒm�f�[�^�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B<BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃L���[�f�[�^���Z�b�g����B<BR>
     * �R�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A<BR>
     *  �{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j<BR>
     * @@param l_eqtypeOrderReceiptParams - (�����������͒ʒm�L���[Params)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3MarginOrderNotifyDataAdapter<BR>
     * @@roseuid 40EA67B70278<BR>
     */
    public static WEB3MarginOrderNotifyDataAdapter create(HostEqtypeOrderReceiptParams l_eqtypeOrderReceiptParams)
    {
        final String STR_METHOD_NAME =
            "create(HostEqtypeOrderReceiptParams l_eqtypeOrderReceiptParams) ";
        log.entering(STR_METHOD_NAME);
        WEB3MarginOrderNotifyDataAdapter l_adapter =
            new WEB3MarginOrderNotifyDataAdapter();
        l_adapter.eqtypeOrderReceiptParams = l_eqtypeOrderReceiptParams;
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (get�s��R�[�h)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�s��R�[�h�iSONAR�j���A<BR>
     * Web�V�̎s��R�[�h��Ԃ��B <BR>
     * <BR>
     * �P�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR( )�ɂ��A<BR>
     * �s��I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ----------------------------------------------------------<BR>
     * ��get�s��BySONAR( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �،���ЃR�[�h�F�@@�����������͒ʒm�L���[Params.�،���ЃR�[�h<BR>
     * �s��R�[�h�iSONAR�j�F�@@�����������͒ʒm�L���[Params.�s��R�[�h�iSONAR�j<BR>
     * ----------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�擾�����s��I�u�W�F�N�g.�s��R�[�h ��Ԃ��B <BR>
     * <BR>
     * @@return String<BR>
     * @@roseuid 40EA67B7027A
     */
    public String getMarketCode() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarketCode()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        Market l_market = l_gentradeFinObjectManager.getMarketBySONAR(
            this.eqtypeOrderReceiptParams.getInstitutionCode(),
            this.eqtypeOrderReceiptParams.getSonarMarketCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_market.getMarketCode();
    }

    /**
     * (get���s����)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z���s����(SONAR)�ɉ�����<BR>
     * EqTypeExecutionConditionType��Ԃ��B<BR>
     * 
     * �g�����������}�l�[�W��.get���s����(���������ʒm�L���[Params.���s����(SONAR))��<BR>
     * delegate����B<BR>
     * <BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@roseuid 40EA67B70268
     */
    public EqTypeExecutionConditionType getExecutionCondition() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionCondition() ";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                
        log.exiting(STR_METHOD_NAME);
        return l_orderManager.getExecutionConditionType(eqtypeOrderReceiptParams.getExecutionCondition());
    }

    /**
     * (get�ŋ敪)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�ŋ敪�i��������敪�j<BR>
     * �i�ȉ��u�ŋ敪�v�j���A<BR>
     * AP�w�Ŏg�p����ŋ敪�iTaxTypeEnum�j��ԋp����B<BR>
     * <BR>
     * �P�j ��ʌ����̃Z�b�g<BR>
     * �@@�ŋ敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��������̃Z�b�g<BR>
     * �ŋ敪���h����h�̏ꍇ�A�ڋq�I�u�W�F�N�g(*1).get��n���M�p����ŋ敪( )(*2)�ɂ��<BR>
     * �ŋ敪���擾����B<BR>
     * �擾�����M�p����ŋ敪��TaxTypeEnum.�h����h�܂��́A<BR>
     * TaxTypeEnum.�h������������򒥎��h�ł���΁ATaxTypeEnum.�h����h��ԋp����B <BR>
     * �ȊO�͗�O���X���[����B<BR> 
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01703<BR>
     * <BR>
     * (*1) �ڋq�I�u�W�F�N�g�́A�g���A�J�E���g�}�l�[�W��.getMainAccount(�،����ID, ���X�R�[�h,<BR>
     * �����R�[�h)�ɂ��擾����B<BR>
     * �،����ID�́y�����������͒ʒm�L���[�e�[�u���z�،���ЃR�[�h�ɊY������<BR>
     * �،����.�،����ID���A<BR>
     * ���X�R�[�h�A�����R�[�h�́y�����������͒ʒm�L���[�e�[�u���z�̓����ڂ��A<BR>
     * ���ꂼ��Z�b�g����B<BR>
     * <BR>
     * (*2) get��n���M�p����ŋ敪( )�F�����̎�n���̎擾���@@ <BR>
     * �g���v���_�N�g�}�l�[�W��.get�������(�،����, this.�����������͒ʒm�L���[Params.�����R�[�h,<BR>
     * this.get�s��R�[�h()).getDailyDeliveryDate( )���Z�b�g�B<BR>
     * �،���ЃI�u�W�F�N�g�́A�g���A�J�E���g�}�l�[�W��.getInstitution(<BR>
     * this.�����������͒ʒm�L���[Params.�،���ЃR�[�h)�Ŏ擾�B<BR>
     * <BR>
     * @@return TaxTypeEnum<BR>
     * @@roseuid 40EA67B70277
     */
    public TaxTypeEnum getTaxType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTaxType() ";
        log.entering(STR_METHOD_NAME);
        TaxTypeEnum l_taxTypeEnum = null;
        // �P�j ��ʌ����̃Z�b�g
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(
                Integer.toString(eqtypeOrderReceiptParams.getTaxType())))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else
        {
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

                AccountManager l_accountManager = l_finApp.getAccountManager();                
                    
                Institution l_institution =
                    l_accountManager.getInstitution(
                        eqtypeOrderReceiptParams.getInstitutionCode());
                // (*1)�ڋq�I�u�W�F�N�g�擾
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                        l_institution.getInstitutionId(),
                        eqtypeOrderReceiptParams.getBranchCode(),
                        eqtypeOrderReceiptParams.getAccountCode());

                // (*2)get��n���M�p����ŋ敪( )�F�����̎�n���̎擾���@@
                WEB3EquityProductManager l_productManager = 
                    (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                // �����̎�n���̎擾
                Date l_deliveryDate = l_productManager.getTradedProduct(
                    l_institution,
                    this.eqtypeOrderReceiptParams.product_code,
                    this.getMarketCode()).getDailyDeliveryDate();
                
                //�M�p����ŋ敪�̎擾
                l_taxTypeEnum = l_mainAccount.getDeliveryDateMarginTaxType(l_deliveryDate);
                
                // �Q�j�@@��������̃Z�b�g
                if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum) ||
                    TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
                {
                    l_taxTypeEnum = TaxTypeEnum.SPECIAL;
                }
                else
                {
                    log.debug(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01703,
                        STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_nfe)
            {
                log.debug(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_taxTypeEnum;
    }

    /**
     * (get�ٍϋ敪)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�ٍϋ敪���A<BR>
     *  AP�w�Ŏg�p����ٍϋ敪��ԋp����B<BR>
     * <BR>
     * [�ٍϋ敪�@@���@@�h1�F���x�M�p<BR>
     *  �i�X���ȊO�܂��͓X���ٍ̕ϊ��Ԃ�6�����j�h�̏ꍇ]<BR>
     * �@@"1"(���x�M�p)��ԋp����B<BR>
     * <BR>
     * [�ٍϋ敪�@@>=�@@�h3�F��ʐM�p�i�S�s��j�h�̏ꍇ]<BR>
     * �@@"2"(��ʐM�p)��ԋp����B<BR>
     * <BR>
     * [�ٍϋ敪����L�ȊO�̏ꍇ]<BR>
     * �@@��O��throw����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00641<BR>
     * <BR>
     * @@return String<BR>
     * @@roseuid 40EA8C4F0023
     */
    public String getRepaymentType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRepaymentType() ";
        log.entering(STR_METHOD_NAME);
        String l_strPayType = eqtypeOrderReceiptParams.getSonarRepaymentType();
        String l_strRepaymentType = null;
        final int l_iSonnarRepayDivMgnSys = 1;
        final int l_iSonnarRepayDivMgnSysAllMarket = 3;
        int l_iSonnarRepayDiv;
        
        try
        {
            l_iSonnarRepayDiv = Integer.parseInt(l_strPayType);
        }
        catch (NumberFormatException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME);
        }
       
        if (l_iSonnarRepayDiv == l_iSonnarRepayDivMgnSys)
        {
            l_strRepaymentType = WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
        }
        else if (l_iSonnarRepayDiv >= l_iSonnarRepayDivMgnSysAllMarket)
        {
            l_strRepaymentType = WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00641,
                STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strRepaymentType;
    }

    /**
     * (get�ٍϊ����l)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�ٍϋ敪���A<BR>
     *  AP�w�Ŏg�p����ٍϊ�����ԋp����B<BR>
     * <BR>
     * �P�j�@@�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g(*1)���擾����B<BR>
     * <BR>
     * �Q�j�@@�擾�����戵�����I�u�W�F�N�g�̐��������ȉ��̏��������[�v����B<BR>
     * <BR>
     * �@@�Q�|�P�j�ȉ��̏����ɊY������戵�����I�u�W�F�N�g���擾����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�擾�����戵�����I�u�W�F�N�g.�،���ЃR�[�h�@@���@@this.�����������͒�<BR>
     * �m�L���[Params.�،���ЃR�[�h<BR>
     * �@@�@@�擾�����戵�����I�u�W�F�N�g.���X�R�[�h�@@�@@�@@���@@this.�����������͒�<BR>
     * �m�L���[Params.get���X�R�[�h<BR>
     * �@@�@@�擾�����戵�����I�u�W�F�N�g.�s��R�[�h�@@�@@�@@���@@this.get�s��R�[�h()<BR>
     * �@@�@@�擾�����戵�����I�u�W�F�N�g.�ٍϋ敪�iSONAR�j���@@this.����������<BR>
     * �͒ʒm�L���[Params.�ٍϋ敪<BR>
     * <BR>
     * �R�j�@@�Q�|�P�j�ɂĎ擾�������R�[�h���� >= <BR>
     *       2�i���Y���f�[�^������������j�̏ꍇ�́A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00661<BR>
     * �@@�@@�@@�ȊO�A�擾�����戵�����I�u�W�F�N�g.�ٍϊ����l��ԋp����B<BR>
     * <BR>
     * (*1)<BR>
     * �i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g.get�i���X�s��ٍϕʁj<BR>
     *  �戵����( )�ɂ��擾<BR>
     * <BR>
     * [get�i���X�s��ٍϕʁj�戵����( )�Ɏw�肷�����]<BR>
     * ���X�F�@@�g���A�J�E���g�}�l�[�W��.get���X<BR>
     *  (this.�،���ЃR�[�h, this.���X�R�[�h)<BR>
     * <BR>
     * @@return double<BR>
     * @@roseuid 40EAAA260060
     */
    public double getRepaymentNum() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRepaymentNum()";
        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            WEB3GentradeInstitution l_institution =
                (WEB3GentradeInstitution)l_accountManager.getInstitution(
                    eqtypeOrderReceiptParams.institution_code);

            WEB3GentradeBranch l_branch =
                (WEB3GentradeBranch)l_accountManager.getBranch(
                    l_institution,
                    eqtypeOrderReceiptParams.branch_code);
            WEB3GentradeBranchMarketRepayDealtCond[] l_conditions =
                WEB3GentradeBranchMarketRepayDealtCond
                    .getBranchMarketRepayDealtCond(
                    l_branch);
            //* �Q�j�@@�擾�����戵�����I�u�W�F�N�g�̐��������ȉ��̏��������[�v����B<BR>
            //    * <BR>
            //    * �@@�Q�|�P�j�ȉ��̏����ɊY������戵�����I�u�W�F�N�g���擾����B<BR>
            //    * �@@�@@[����]<BR>
            //    * �@@�@@�擾�����戵�����I�u�W�F�N�g.�،���ЃR�[�h�@@���@@this.�����������͒�<BR>
            //    * �m�L���[Params.�،���ЃR�[�h<BR>
            //    * �@@�@@�擾�����戵�����I�u�W�F�N�g.���X�R�[�h�@@�@@�@@���@@this.�����������͒�<BR>
            //    * �m�L���[Params.get���X�R�[�h<BR>
            //    * �@@�@@�擾�����戵�����I�u�W�F�N�g.�s��R�[�h�@@�@@�@@���@@this.get�s��R�[�h()<BR>
            //    * �@@�@@�擾�����戵�����I�u�W�F�N�g.�ٍϋ敪�iSONAR�j���@@this.����������<BR>
            //    * �͒ʒm�L���[Params.�ٍϋ敪<BR>
            int l_intIndex = -1;
            int l_intCount = 0;
            for (int i = 0; i < l_conditions.length; i++)
            {
                BranchMarketRepayDealtCondRow l_conditionRow =
                    (BranchMarketRepayDealtCondRow)l_conditions[i]
                        .getDataSourceObject();
                if (l_conditionRow
                    .getInstitutionCode()
                    .equals(eqtypeOrderReceiptParams.institution_code)
                    && l_conditionRow.getBranchCode().equals(
                        eqtypeOrderReceiptParams.branch_code)
                    && l_conditionRow.getMarketCode().equals(this.getMarketCode())
                    && l_conditionRow.getSonarRepaymentType().equals(
                        eqtypeOrderReceiptParams.sonar_repayment_type))
                {
                    l_intIndex = i;
                    l_intCount++;
                }
            }
            if (l_intCount == 0 || l_intCount >= 2)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00661,
                    STR_METHOD_NAME);
            }
            BranchMarketRepayDealtCondRow l_conditionRow =
                (BranchMarketRepayDealtCondRow)l_conditions[l_intIndex]
                    .getDataSourceObject();
            return l_conditionRow.getRepaymentNum();
        }
        catch (NotFoundException l_nex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_nex.getMessage(),
                l_nex);
        }
        catch (WEB3SystemLayerException l_slex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_slex.getMessage(),
                l_slex);
        }
    }

    /**
     * (is����)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�����敪���A<BR>
     *  AP�w�Ŏg�p����is������ԋp����B<BR>
     * <BR>
     * �����敪��"2�F����"�@@and�@@<BR>
     * �i����R�[�h(SONAR)��"51�F�M�p��" or "52�F�M�p��"�j<BR>
     * �̏ꍇ��true���A<BR>
     * �����敪��"1�F����"�@@and�@@<BR>
     * �i����R�[�h(SONAR)��"51�F�M�p��" or "52�F�M�p��"�j<BR>
     * �̏ꍇ��false���A<BR>
     * ���ꂼ��ԋp����B<BR>
     * <BR>
     * ��L�ȊO�̏ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00651<BR>
     * @@return boolean<BR>
     * @@roseuid 40EA67B70269
     */
    public boolean isLong() 
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        boolean l_isLong = true;
        if (WEB3TradeTypeDef
            .OPEN_LONG_MARGIN
            .equals(eqtypeOrderReceiptParams.getTradeType()))
        {
            if (WEB3TransactionTypeSONARDef
                .OPEN_CONTRACT
                .equals(eqtypeOrderReceiptParams.getSonarTradedCode())
                || WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(
                    eqtypeOrderReceiptParams.getSonarTradedCode()))
            {
                l_isLong = true;
            }
        }
        else if (
            WEB3TradeTypeDef.OPEN_SHORT_MARGIN.equals(
                eqtypeOrderReceiptParams.getTradeType()))
        {
            if (WEB3TransactionTypeSONARDef
                .OPEN_CONTRACT
                .equals(eqtypeOrderReceiptParams.getSonarTradedCode())
                || WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(
                    eqtypeOrderReceiptParams.getSonarTradedCode()))
            {
                l_isLong = false;
            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00651,
                STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_isLong;
    }

    /**
     * (is����)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�����敪���A<BR>
     *  AP�w�Ŏg�p����is������ԋp����B<BR>
     * �����敪��"2�F����"�@@and�@@<BR>
     * �i����R�[�h(SONAR)��"51�F�M�p��" or "52�F�M�p��"�j<BR>
     * �̏ꍇ��false���A<BR>
     * �����敪��"1�F����"�@@and�@@<BR>
     * �i����R�[�h(SONAR)��"51�F�M�p��" or "52�F�M�p��"�j<BR>
     * �̏ꍇ��true���A<BR>
     * ���ꂼ��ԋp����B<BR>
     * <BR>
     * ��L�ȊO�̏ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00651<BR>
     * @@return boolean<BR>
     * @@roseuid 40F4D42F0375
     */
    public boolean isShort() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isShort()";
        log.entering(STR_METHOD_NAME);
        boolean l_isShort = true;
        if (WEB3TradeTypeDef
            .OPEN_LONG_MARGIN
            .equals(eqtypeOrderReceiptParams.getTradeType()))
        {
            if (WEB3TransactionTypeSONARDef
                .OPEN_CONTRACT
                .equals(eqtypeOrderReceiptParams.getSonarTradedCode())
                || WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(
                    eqtypeOrderReceiptParams.getSonarTradedCode()))
            {
                l_isShort = false;
            }
        }
        else if (
            WEB3TradeTypeDef.OPEN_SHORT_MARGIN.equals(
                eqtypeOrderReceiptParams.getTradeType()))
        {
            if (WEB3TransactionTypeSONARDef
                .OPEN_CONTRACT
                .equals(eqtypeOrderReceiptParams.getSonarTradedCode())
                || WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(
                    eqtypeOrderReceiptParams.getSonarTradedCode()))
            {
                l_isShort = true;
            }
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00651,
                STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_isShort;
    }
    /**
     * (is�V�K������)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z����R�[�h�iSONAR�j���A<BR>
     * AP�w�Ŏg�p����is�V�K��������ԋp����B<BR>
     * <BR>
     * ����R�[�h�iSONAR�j��"51�F�M�p��"�̏ꍇ��true��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * @@return boolean<BR>
     * @@roseuid 40EA6A3D01DB
     */
    public boolean isOpenMarginOrder()
    {
        final String STR_METHOD_NAME = "isOpenMarginOrder()";
        log.entering(STR_METHOD_NAME);
        boolean l_isOpenMarginOrder = true;
        if (WEB3TransactionTypeSONARDef
            .OPEN_CONTRACT
            .equals(eqtypeOrderReceiptParams.getSonarTradedCode()))
        {
            l_isOpenMarginOrder = true;
        }
        else
        {
            l_isOpenMarginOrder = false;
        }
        log.exiting(STR_METHOD_NAME);
        return l_isOpenMarginOrder;
    }
    /**
     * (is�ԍϒ���)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z����R�[�h�iSONAR�j���A<BR>
     * AP�w�Ŏg�p����is�ԍϒ�����ԋp����B<BR>
     * <BR>
     * ����R�[�h�iSONAR�j��"52�F�M�p��"�̏ꍇ��true��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * @@return boolean<BR>
     * @@roseuid 40F653640146
     */
    public boolean isCloseMarginOrder()
    {
        final String STR_METHOD_NAME = "isCloseMarginOrder()";
        log.entering(STR_METHOD_NAME);
        boolean l_isCloseMarginOrder = true;
        if (WEB3TransactionTypeSONARDef
            .SETTLE_CONTRACT
            .equals(eqtypeOrderReceiptParams.getSonarTradedCode()))
        {
            l_isCloseMarginOrder = true;
        }
        else
        {
            l_isCloseMarginOrder = false;
        }
        log.exiting(STR_METHOD_NAME);
        return l_isCloseMarginOrder;
    }
    
    public HostEqtypeOrderReceiptParams getDataSourseObject()
    {
        return eqtypeOrderReceiptParams;
    }
    
    /**
     * (get�l�i����)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�l�i����(SONAR)�ɉ�����<BR>
     * WEB�V�ɂ�����l�i������Ԃ��B<BR>
     * <BR>
     * �g�����������}�l�[�W��.get�l�i����(���������ʒm�L���[Params.�l�i����(SONAR))��<BR>
     * delegate����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPriceConditionType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPriceConditionType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        String l_strPriceConditionType =
            l_orderMgr.getPriceConditionType(this.eqtypeOrderReceiptParams.price_condition_type);

        log.exiting(STR_METHOD_NAME);
        return l_strPriceConditionType;
    }
    
    /**
     * (is�󔄂�K���Ώ�)<BR>
     * �y�����������͒ʒm�L���[�e�[�u���z�󔄃t���O���A<BR>
     * �󔄂�K���Ώۂ��ǂ�����ԋp����B<BR>
     * <BR>
     * �󔄃t���O��"5�F���i�K���Ώ�"�̏ꍇ��true��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public boolean isShortSellingRestraint() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isShortSellingRestraint()";
        log.entering(STR_METHOD_NAME);

        boolean l_isShortSellingRestraint = false;
        if (eqtypeOrderReceiptParams.getShortSellOrderFlag().equals(
                WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT))
        {
            l_isShortSellingRestraint = true;
        }
        else
        {
            l_isShortSellingRestraint = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_isShortSellingRestraint;
    }
    
}
@
