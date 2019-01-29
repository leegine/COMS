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
filename	WEB3MarginSwapOrderNotifyDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n���͒ʒm�f�[�^�A�_�v�^(WEB3MarginSwapOrderNotifyDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �X�� (SRA) �V�K�쐬
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3SwapTradeTypeDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������n���͒ʒm�f�[�^�A�_�v�^�j�B<BR>
 * <BR>
 * �M�p����������n���͒ʒm�f�[�^�A�_�v�^�N���X
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyDataAdapter
{
    /**
     * (���O�o�̓��[�e�B���e�B)�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyDataAdapter.class);


    /**
     * (�����������n���͒ʒm�L���[Params)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z�̂P���R�[�h�B<BR>
     */
    private HostEqtypeSwapReceiptParams eqtypeSwapReceiptParams;


    /**
     * (�R���X�g���N�^)�B<BR>
     */
    private WEB3MarginSwapOrderNotifyDataAdapter()
    {
    }


    /**
     * (create)�B<BR>
     * <BR>
     * �M�p����������n���͒ʒm�f�[�^�A�_�v�^�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�����B <BR>
     * �Q�j�@@���������C���X�^���X�Ɉ����̃L���[�f�[�^���Z�b�g����B <BR>
     * �R�j�@@�C���X�^���X��ԋp����B <BR>
     * <BR>
     * �i�f�t�H���g�R���X�g���N�^��private�Ƃ��A<BR>
     * �@@�{���\�b�h�ɂ���ăC���X�^���X������悤�ɐ�������j <BR>
     * <BR>
     * @@param eqtypeSwapReceiptParams - �y�������n���͒ʒm�L���[�e�[�u���z�f�[�^�I�u�W�F�N�g<BR>
     * �y�����������n���͒ʒm�L���[�e�[�u���z�f�[�^�I�u�W�F�N�g<BR>
     * @@return WEB3MarginSwapOrderNotifyDataAdapter<BR>
     */
    public static WEB3MarginSwapOrderNotifyDataAdapter create(HostEqtypeSwapReceiptParams l_eqtypeSwapReceiptParams)
    {
        final String STR_METHOD_NAME = "create(HostEqtypeSwapReceiptParams) ";
        log.entering(STR_METHOD_NAME);

        WEB3MarginSwapOrderNotifyDataAdapter l_adapter = new WEB3MarginSwapOrderNotifyDataAdapter();
        l_adapter.eqtypeSwapReceiptParams = l_eqtypeSwapReceiptParams;

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }


    /**
     * (get�s��)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z�s��R�[�h�iSONAR�j���A <BR>
     * AP�w�Ŏg�p����s��I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR( )�ɈϏ�����B <BR>
     * <BR>
     * ------------------------------------------------------------- <BR>
     * ���g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �،���ЃR�[�h�F�@@�������n���͒ʒm�L���[Params.�،���ЃR�[�h <BR>
     * �s��R�[�h(SONAR)�F�@@�������n���͒ʒm�L���[Params.�s��R�[�h(SONAR) <BR>
     * ------------------------------------------------------------- <BR>
     * <BR>
     * @@return Market<BR>
     * @@throws WEB3SystemLayerException
     */
    public Market getMarket() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);

        //--------------------
        //�g�����Z�I�u�W�F�N�g�}�l�[�W�����擾����B
        //--------------------
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //--------------------
        //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��BySONAR( )�ɈϏ�����B
        //--------------------
        Market l_market = l_gentradeFinObjectManager.getMarketBySONAR(
            this.eqtypeSwapReceiptParams.getInstitutionCode(),
            this.eqtypeSwapReceiptParams.getSonarMarketCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_market;
    }


    /**
     * (get�ŋ敪)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z�ŋ敪�i��������敪�j�i�ȉ��u�ŋ敪�v�j���A <BR>
     * AP�w�Ŏg�p����ŋ敪�iTaxTypeEnum�j��ԋp����B <BR>
     * <BR>
     * �P�j ��ʌ����̃Z�b�g <BR>
     * �@@�ŋ敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B <BR>
     * <BR>
     * �Q�j�@@��������̃Z�b�g <BR>
     * �@@�ŋ敪���h����h�̏ꍇ�A�ȉ��̏������s���B <BR>
     * <BR>
     * �Q�|�P�j�@@��n�����擾����B <BR>
     * <BR>
     * �@@�@@�@@�@@�g���v���_�N�g�}�l�[�W��.get�������( )�̖߂�l.getDailyDeliveryDate( )�� <BR>
     * �@@�@@�@@�@@��n���Ƃ��Ďg�p����B <BR>
     * <BR>
     * �@@�@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@�@@��get�������( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�@@�،���ЁF�@@�g���A�J�E���g�}�l�[�W��.getInstitution( <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������n���͒ʒm�L���[Params.�،���ЃR�[�h) <BR>
     * �@@�@@�@@�@@�����R�[�h�F�@@�������n���͒ʒm�L���[Params.�����R�[�h <BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@this.get�s��().getMarketCode() <BR>
     * �@@�@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �Q�|�Q�j�@@�ڋq.get��n���M�p����ŋ敪( )�ɂāA��n���̐M�p����ŋ敪���擾����B <BR>
     * <BR>
     * �@@�@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@�@@��get��n���M�p����ŋ敪( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�@@��n���F�@@�Q�|�P�j�Ŏ擾������n�� <BR>
     * �@@�@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �@@�@@�@@�@@(*)�ڋq�I�u�W�F�N�g�̎擾�F�g���A�J�E���g�}�l�[�W��.get�ڋq( <BR>
     * �@@�@@�@@�@@�@@�@@�@@�������n���͒ʒm�L���[Params.�،���ЃR�[�h,  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�������n���͒ʒm�L���[Params.�����R�[�h)�̖߂�l�I�u�W�F�N�g���g�p����B <BR>
     * <BR>
     * �Q�|�R�j�@@�Q�|�Q�j�Ŏ擾�����M�p����ŋ敪���A <BR>
     * �@@�@@�@@�@@�@@�iTaxTypeEnum.SPECIAL�i����j�ASPECIAL_WITHHOLD�i���肩���򒥎��j�j�� <BR>
     * �@@�@@�@@�@@�@@�����ꂩ�̏ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@TaxTypeEnum.SPECIAL�i����j��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�ȊO�́u�M�p�̓�������J�݂Ȃ��v�̗�O��throw����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@ WEB3ErrorCatalog.BUSINESS_ERROR_01703<BR>
     * <BR>
     * @@return TaxTypeEnum<BR>
     * @@throws WEB3BaseException
     */
    public TaxTypeEnum getTaxType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTaxType() ";
        log.entering(STR_METHOD_NAME);
        

        //--------------------
        // �P�j ��ʌ����̏ꍇ
        //--------------------
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(this.eqtypeSwapReceiptParams.getTaxType()))
        {
            log.exiting(STR_METHOD_NAME);
            return TaxTypeEnum.NORMAL;
        }


        //--------------------
        // �Q�j ��������̏ꍇ
        //--------------------
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();


        //--------------------
        // �Q�|�P�j ��n�����擾����
        //--------------------
        Institution l_institution               = null;

        //�،���Ђ��擾
        try {
            l_institution = l_accountManager
                .getInstitution(eqtypeSwapReceiptParams.getInstitutionCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //��n�����擾
        Date l_datDeliveryDate = WEB3DateUtility.getDate(
            eqtypeSwapReceiptParams.getDeliveryDate(), "yyyyMMdd");
        
        
        //--------------------
        // �Q�|�Q�j �ڋq�Dget��n���M�p����ŋ敪()�ɂĎ�n���̐M�p����ŋ敪���擾����
        //--------------------
        WEB3GentradeMainAccount l_mainAccount   = null;
        try {
            //�ڋq�I�u�W�F�N�g�擾
            l_mainAccount
                = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(),
                    eqtypeSwapReceiptParams.getBranchCode(),
                    eqtypeSwapReceiptParams.getAccountCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //��n���M�p����ŋ敪�擾
        TaxTypeEnum l_taxTypeEnum = l_mainAccount.getDeliveryDateMarginTaxType(l_datDeliveryDate);
        

        //--------------------
        // �Q�|�R�j TaxTypeEnum.SPECIAL�i����j��Ԃ��B 
        //--------------------
        if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum)
            || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
        {
            log.exiting(STR_METHOD_NAME);
            return TaxTypeEnum.SPECIAL;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01703,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }


    /**
     * (get�ŋ敪�i�������n�j)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z�ŋ敪�i�������n������������敪�j�i�ȉ��u�ŋ敪�v�j���A <BR>
     * AP�w�Ŏg�p����ŋ敪�iTaxTypeEnum�j��ԋp����B <BR>
     * <BR>
     * �P�j ��ʌ����̃Z�b�g <BR>
     * �@@�ŋ敪���h��ʁh�̏ꍇ�ATaxTypeEnum.�h��ʁh��ԋp����B <BR>
     * <BR>
     * �Q�j�@@��������̃Z�b�g <BR>
     * �@@�ŋ敪���h����h�̏ꍇ�A�ȉ��̏������s���B <BR>
     * <BR>
     * �Q�|�P�j�@@��n�����擾����B <BR>
     * <BR>
     * �@@�@@�@@�@@�g���v���_�N�g�}�l�[�W��.get�������( )�̖߂�l.getDailyDeliveryDate( )�� <BR>
     * �@@�@@�@@�@@��n���Ƃ��Ďg�p����B <BR>
     * <BR>
     * �@@�@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@�@@��get�������( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�@@�،���ЁF�@@�g���A�J�E���g�}�l�[�W��.getInstitution( <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������n���͒ʒm�L���[Params.�،���ЃR�[�h) <BR>
     * �@@�@@�@@�@@�����R�[�h�F�@@�������n���͒ʒm�L���[Params.�����R�[�h <BR>
     * �@@�@@�@@�@@�s��R�[�h�F�@@this.get�s��().getMarketCode() <BR>
     * �@@�@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �Q�|�Q�j�@@�ڋq.get��n���ŋ敪( )�ɂāA��n���̐ŋ敪���擾����B <BR>
     * <BR>
     * �@@�@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@�@@��get��n���ŋ敪( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@�@@��n���F�@@�Q�|�P�j�Ŏ擾������n�� <BR>
     * �@@�@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �@@�@@�@@�@@(*)�ڋq�I�u�W�F�N�g�̎擾�F�g���A�J�E���g�}�l�[�W��.get�ڋq( <BR>
     * �@@�@@�@@�@@�@@�@@�@@�������n���͒ʒm�L���[Params.�،���ЃR�[�h,  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�������n���͒ʒm�L���[Params.�����R�[�h)�̖߂�l�I�u�W�F�N�g���g�p����B <BR>
     * <BR>
     * �Q�|�R�j�@@�Q�|�Q�j�Ŏ擾�����ŋ敪���A <BR>
     * �@@�@@�@@�@@�@@�iTaxTypeEnum.SPECIAL�i����j�ASPECIAL_WITHHOLD�i���肩���򒥎��j�j�� <BR>
     * �@@�@@�@@�@@�@@�����ꂩ�̏ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@TaxTypeEnum.SPECIAL�i����j��Ԃ��B  <BR>
     * �@@�@@�@@�@@�@@�ȊO�́u�����̓�������J�݂Ȃ��v�̗�O��throw����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@ WEB3ErrorCatalog.BUSINESS_ERROR_00637<BR>
     * <BR>
     * @@return TaxTypeEnum<BR>
     * @@throws WEB3BaseException
     */
    public TaxTypeEnum getSwapTaxType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSwapTaxType() ";
        log.entering(STR_METHOD_NAME);
        

        //--------------------
        // �P�j ��ʌ����̏ꍇ
        //--------------------
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(this.eqtypeSwapReceiptParams.getSwapTaxType()))
        {
            log.exiting(STR_METHOD_NAME);
            return TaxTypeEnum.NORMAL;
        }

        //--------------------
        // �Q�j ��������̏ꍇ
        //--------------------
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();


        //--------------------
        // �Q�|�P�j ��n�����擾����
        //--------------------
        Institution l_institution               = null;

        //�،���Ђ��擾
        try {
            l_institution = l_accountManager
                .getInstitution(eqtypeSwapReceiptParams.getInstitutionCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //��n�����擾
        Date l_datDeliveryDate = WEB3DateUtility.getDate(
            eqtypeSwapReceiptParams.getDeliveryDate(), "yyyyMMdd");
        
        
        //--------------------
        // �Q�|�Q�j �ڋq�Dget��n���ŋ敪()�ɂĎ�n���̐ŋ敪���擾����
        //--------------------
        WEB3GentradeMainAccount l_mainAccount   = null;
        try {
            //�ڋq�I�u�W�F�N�g�擾
            l_mainAccount
                = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(),
                    eqtypeSwapReceiptParams.getBranchCode(),
                    eqtypeSwapReceiptParams.getAccountCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //��n���ŋ敪�擾
        TaxTypeEnum l_taxTypeEnum = l_mainAccount.getDeliveryDateTaxType(l_datDeliveryDate);
        

        //--------------------
        // �Q�|�R�j TaxTypeEnum.SPECIAL�i����j��Ԃ��B 
        //--------------------
        if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum)
            || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
        {
            log.exiting(STR_METHOD_NAME);
            return TaxTypeEnum.SPECIAL;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00637,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    

    /**
     * (get�ٍϋ敪)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z�ٍϋ敪�iSONAR�j���A <BR>
     * AP�w�Ŏg�p����ٍϋ敪��ԋp����B <BR>
     * <BR>
     * [�ٍϋ敪�iSONAR�j�@@���@@�h1�F���x�M�p�i�X���ȊO�܂��͓X���ٍ̕ϊ��Ԃ�6�����j�h�̏ꍇ] <BR>
     * �@@"1"(���x�M�p)��ԋp����B <BR>
     * <BR>
     * [�ٍϋ敪�iSONAR�j�@@>=�@@�h3�F��ʐM�p�i�S�s��j�h�̏ꍇ] <BR>
     * �@@"2"(��ʐM�p)��ԋp����B <BR>
     * <BR>
     * [�ٍϋ敪�iSONAR�j����L�ȊO�̏ꍇ] <BR>
     * �@@��O��throw����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@ WEB3ErrorCatalog.BUSINESS_ERROR_00641<BR>
     * <BR>
     * @@return String<BR>
     * @@throws WEB3BaseException
     */
    public String getRepaymentType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRepaymentType() ";
        log.entering(STR_METHOD_NAME);
        final int l_iSonnarRepayDivMgnSys = 1;
        final int l_iSonnarRepayDivMgnSysAllMarket = 3;
        int l_iSonnarRepayDiv;


        //--------------------
        //�ٍϋ敪(Sonnar)���擾
        //--------------------
        String l_strRepaymentType = eqtypeSwapReceiptParams.getSonarRepaymentType();
        try
        {
            l_iSonnarRepayDiv = Integer.parseInt(l_strRepaymentType);
        }
        catch (NumberFormatException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME);
        }
        

        //--------------------
        //�ٍϋ敪(Sonnar)�̒l�ɂ��ٍϋ敪��Ԃ��B
        //--------------------
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
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00641,
                STR_METHOD_NAME);
        }
        
        
        log.exiting(STR_METHOD_NAME);
        return l_strRepaymentType;
    }
    

    /**
     * (get�ٍϊ����l)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z�ٍϋ敪�iSONAR�j���A <BR>
     * AP�w�Ŏg�p����ٍϊ����l��ԋp����B <BR>
     * <BR>
     * �P�j�@@�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g(*1)���擾����B <BR>
     * <BR>
     * �Q�j�@@�擾�����戵�����I�u�W�F�N�g�̐��������ȉ��̏��������[�v����B <BR>
     * <BR>
     * �@@�Q�|�P�j�ȉ��̏����ɊY������戵�����I�u�W�F�N�g���擾����B <BR>
     * �@@�@@[����] <BR>
     * �@@�@@�擾�����戵�����I�u�W�F�N�g.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@���@@this.�������n���͒ʒm�L���[Params.�،���ЃR�[�h <BR>
     * �@@�@@�擾�����戵�����I�u�W�F�N�g.���X�R�[�h<BR>
     * �@@�@@�@@�@@���@@this.�������n���͒ʒm�L���[Params.get���X�R�[�h <BR>
     * �@@�@@�擾�����戵�����I�u�W�F�N�g.�s��R�[�h<BR>
     * �@@�@@�@@�@@���@@this.get�s��().getMarketCode() <BR>
     * �@@�@@�擾�����戵�����I�u�W�F�N�g.�ٍϋ敪�iSONAR�j<BR>
     * �@@�@@�@@�@@���@@this.�������n���͒ʒm�L���[Params.�ٍϋ敪�iSONAR�j <BR>
     * <BR>
     * �R�j�@@�Y���f�[�^�Ȃ��i���R�[�h����==0�j�̏ꍇ�́A <BR>
     * �@@�@@�@@�u�ٍϊ����l����s�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00661<BR>
     * �@@�@@�@@�܂��A�Q�|�P�j�ɂĎ擾�������R�[�h���� >= 2�i���Y���f�[�^������������j�̏ꍇ���A <BR>
     * �@@�@@�@@�u�ٍϊ����l����s�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00661<BR>
     * �@@�@@�@@�ȊO�A�擾�����戵�����I�u�W�F�N�g.�ٍϊ����l��ԋp����B <BR>
     * <BR>
     * (*1) <BR>
     * �i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g.get�i���X�s��ٍϕʁj�戵����( )�ɂ��擾 <BR>
     * <BR>
     * [get�i���X�s��ٍϕʁj�戵����( )�Ɏw�肷�����] <BR>
     * ���X�F�@@�g���A�J�E���g�}�l�[�W��.get���X(this.�،���ЃR�[�h, this.���X�R�[�h) <BR>
     * <BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
    public double getRepaymentNum() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRepaymentNum()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        

        //--------------------
        //�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g���擾����B
        //--------------------
        WEB3GentradeBranch l_branch = null;
        WEB3GentradeBranchMarketRepayDealtCond[] l_conditionList = null;

        try
        {
            l_branch = (WEB3GentradeBranch) l_accountManager.getWeb3GenBranch(
                eqtypeSwapReceiptParams.institution_code, eqtypeSwapReceiptParams.branch_code);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME);
        }
        l_conditionList = WEB3GentradeBranchMarketRepayDealtCond
            .getBranchMarketRepayDealtCond(l_branch);
        

        //--------------------
        //�擾�����i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g�̐����������[�v����
        //--------------------
        BranchMarketRepayDealtCondRow l_conditionRow = null;
        BranchMarketRepayDealtCondRow l_retConditionRow = null;
        int l_iConditionsCount;

        //--------------------
        //�Y���f�[�^�����̏ꍇ�ٍ͕ϊ����l����s��O���X���[����
        //--------------------
        if (l_conditionList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00661,
                STR_METHOD_NAME);
        }
        
        l_iConditionsCount = l_conditionList.length;
        for (int i = 0; i < l_iConditionsCount; i++)
        {
            l_conditionRow
                = (BranchMarketRepayDealtCondRow) l_conditionList[i].getDataSourceObject();
            
            //--------------------
            //�ȉ������ɊY������戵�����I�u�W�F�N�g���擾����
            //--------------------
            if (l_conditionRow.getInstitutionCode().equals(eqtypeSwapReceiptParams.institution_code)
                && l_conditionRow.getBranchCode().equals(eqtypeSwapReceiptParams.branch_code)
                && l_conditionRow.getMarketCode().equals(this.getMarket().getMarketCode())
                && l_conditionRow.getSonarRepaymentType().equals(eqtypeSwapReceiptParams.sonar_repayment_type))
            {
                if (l_retConditionRow != null)
                {
                    //--------------------
                    //�Y���f�[�^��2���ȏ�̏ꍇ���ٍϊ����l����s��O���X���[����
                    //--------------------
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00661,
                        STR_METHOD_NAME);
                }
                else
                {
                    l_retConditionRow = l_conditionRow;
                }
            }
        }

        //--------------------
        //�Y���f�[�^�����̏ꍇ�ٍ͕ϊ����l����s��O���X���[����
        //--------------------
        if (l_retConditionRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00661,
                STR_METHOD_NAME);
        }
        

        log.exiting(STR_METHOD_NAME);
        return l_retConditionRow.getRepaymentNum();
    }
    

    /**
     * (is����)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z�����敪���AAP�w�Ŏg�p����is������ԋp����B <BR>
     * <BR>
     * �����敪��"2�F����"�̏ꍇ��true���A <BR>
     * �����敪��"1�F���n"�̏ꍇ��false���A <BR>
     * ���ꂼ��ԋp����B <BR>
     * <BR>
     * ��L�ȊO�̏ꍇ�͗�O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00651<BR>
     * <BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    public boolean isLong() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        boolean l_blnIsLong;
        String l_strTradeType = eqtypeSwapReceiptParams.getTradeType();
        
        //�����敪="2:����"�̂Ƃ���true
        if (WEB3SwapTradeTypeDef.RECIPT.equals(l_strTradeType))
        {
            l_blnIsLong = true;
        }
        //�����敪="1:���n"�̂Ƃ���false
        else if (WEB3SwapTradeTypeDef.DELIVERY.equals(l_strTradeType))
        {
            l_blnIsLong = false;
        }
        //��L�ȊO�̏ꍇ�͗�O���X���[
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00651,
                STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsLong;
    }
    

    /**
     * (is����)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z�����敪���AAP�w�Ŏg�p����is������ԋp����B <BR>
     * <BR>
     * �����敪��"1�F���n"�̏ꍇ��true���A <BR>
     * �����敪��"2�F����"�̏ꍇ��false���A <BR>
     * ���ꂼ��ԋp����B <BR>
     * <BR>
     * ��L�ȊO�̏ꍇ�͗�O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00652<BR>
     * <BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    public boolean isShort() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsLong = this.isLong();
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsLong ? false : true;
    }


    /**
     * (getDataSourseObject)�B<BR>
     * <BR>
     * �y�������n���͒ʒm�L���[�e�[�u���z���R�[�h��Ԃ��B
     * <BR>
     * @@return HostEqtypeSwapReceiptParams<BR>
     */
    public HostEqtypeSwapReceiptParams getDataSourseObject()
    {
        return eqtypeSwapReceiptParams;
    }
}
@
