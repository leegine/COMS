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
filename	WEB3MutualFundInstCommission.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M��Еʎ萔��(WEB3MutualFundInstCommission)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 ��O�� (���u) �V�K�쐬
                   2006/06/26 ���� (���u) �v�Z����  No.022
                   2006/07/18 ���{ (SRA) �v�Z����  No.023
*/

package webbroker3.mf;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.mf.data.MutualFundInstCommissionParams;
import webbroker3.mf.data.MutualFundInstCommissionRow;
import webbroker3.mf.define.WEB3MFCommissionDivDef;
import webbroker3.mf.define.WEB3MFDealDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * ���M��Еʎ萔��<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundInstCommission
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundInstCommission.class);

    /**
     * ���M��Еʎ萔���s�I�u�W�F�N�g <BR>
     */
    private MutualFundInstCommissionParams mfInstCommissionParams;
    
    /**
     * (���M��Еʎ萔��)
     * �R���X�g���N�^ <BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA���M��Еʎ萔���e�[�u������������B <BR>
     * <BR>
     * [��������] <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h <BR>
     * �����R�[�h = ����.�����R�[�h <BR>
     * ����敪 = ����.����敪 <BR>
     * �����`���l�� = ����.�����`���l�� <BR>
     * �K�p�J�n�N���� <= ����.������ <BR>
     * �K�p�I���N���� > ����.������ <BR>
     * <BR>
     * [�\�[�g��] <BR>
     * �K�p�J�n�N�����̍~�� <BR>
     * <BR>
     * �Q�j�擾���ꂽ���R�[�h��this.���M��Еʎ萔���s�ɃZ�b�g����B <BR>
     * <BR>
     *    �����R�[�h�������擾�ł����ꍇ�́A�擪�̂��́i�K�p�J�n�N���������߂̂��́j���Z�b�g����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strDealDiv - ����敪
     * @@param l_strOrderChanel - �����`���l��
     * @@param l_datBizDate - ������
     * @@roseuid 4010AF2C0227
     */

    public WEB3MutualFundInstCommission(
        String l_strInstitutionCode, 
        String l_strProductCode, 
        String l_strDealDiv, 
        String l_strOrderChanel, 
        Date l_datBizDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB3MutualFundInstCommission(String l_strInstitutionCode, " +
            "String l_strProductCode, String l_strDealDiv, " +
            "String l_strOrderChanel, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ȉ��̏����ŁA���M��Еʎ萔���e�[�u������������B 
        //[��������] 
        //�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�����R�[�h = ����.�����R�[�h 
        //����敪 = ����.����敪 
        //�����`���l�� = ����.�����`���l�� 
        //�K�p�J�n�N���� <= ����.������ 
        //�K�p�I���N���� > ����.������ 

        //[�\�[�g��] 
        //�K�p�J�n�N�����̍~�� 
        
        List l_lisRows = new ArrayList();

        String l_strWhere = 
            "institution_code = ? and product_code = ? and deal_div = ? " +
            "and order_chanel = ? and valid_date_from <= ? and valid_date_to > ?";
            
        String l_strSortCond =
            " valid_date_from desc ";        

        Object[] l_bindVars = {
            l_strInstitutionCode,
            l_strProductCode,
            l_strDealDiv, 
            l_strOrderChanel, 
            l_datBizDate, 
            l_datBizDate};        

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                MutualFundInstCommissionRow.TYPE,
                l_strWhere,
                l_strSortCond, 
                null, 
                l_bindVars);
        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundInstCommission");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search MutualFundInstCommission");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        if(l_lisRows == null || l_lisRows.size() == 0)
        {
            //�|�������ʂ̌���=0���̏ꍇ�B
            log.debug("�e�[�u���ɊY������f�[�^������܂���!"); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }
        //�Q�j�擾���ꂽ���R�[�h��this.���M��Еʎ萔���s�ɃZ�b�g����B 
        //�����R�[�h�������擾�ł����ꍇ�́A�擪�̂��́i�K�p�J�n�N���������߂̂��́j���Z�b�g����B 
        
        MutualFundInstCommissionRow l_mfInstCommissionRow = 
            (MutualFundInstCommissionRow)l_lisRows.get(0);
        
        MutualFundInstCommissionParams l_mfInstCommissionParams = 
            new MutualFundInstCommissionParams(l_mfInstCommissionRow);
        
        this.mfInstCommissionParams = l_mfInstCommissionParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�萔���敪)
     * �萔���敪���擾����B <BR>
     * <BR>
     * this.���M��Еʎ萔��.�萔���敪��ԋp����B<BR> 
     * @@roseuid 40AD92050133
     */
    public String getCommisionDiv() 
    {
        return this.mfInstCommissionParams.getCommissionDiv();
    }
    
    /**
     * (get����敪)
     * ����敪���擾����B <BR> 
     * <BR> 
     * this.���M��Еʎ萔��.����敪��ԋp����B <BR> 
     * <BR> 
     * @@roseuid 40AD92050133
     */
    public String getDealDiv() 
    {
        return this.mfInstCommissionParams.getDealDiv();
    }
    
    /**
     * (get�萔���P���A��)
     * �萔�����A�P�����擾����B <BR> 
     * <BR> 
     * �ڍׂ́A�v�Z�����Q�ƁB<BR>
     * <BR> 
     * @@param l_mainAccount - �ڋq
     * @@param l_mfProduct - ����
     * @@param l_swtProduct - �抷��̖���
     * @@param l_strDesignDiv - �w��敪
     * @@param l_bdFindValue - �����l
     * @@param l_datBizDate - ������
     * @@return BigDecimal
     * @@roseuid 40AD92050133
     */
    public BigDecimal getCommisionPriceRate(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_swtProduct, 
        String l_strDesignDiv, 
        BigDecimal l_bdFindValue, 
        Date l_datBizDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCommisionPriceRate(MainAccount l_mainAccount, " +
            "WEB3MutualFundProduct l_mfProduct, String l_strDesignDiv, " +
            "double l_dblFindValue, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        MFCommisionPriceRate l_commisionPriceRate = 
            new MFCommisionPriceRate(
                this.mfInstCommissionParams, 
                l_mainAccount.getInstitution().getInstitutionCode(), 
                l_datBizDate,
                l_bdFindValue);

		//  �g�����M�����}�l�[�W�����擾����
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        
        //�萔���v�Z�Ɏg�p����萔���P���A��
        BigDecimal l_bdCommisionPriceRate = new BigDecimal("0");
        
        WEB3MutualFundProduct l_wkMfProduct = null;
        //�@@ ����.�抷��̖�����null�̏ꍇ
        if (l_swtProduct == null)
        {
            log.debug("�@@ ����.�抷��̖�����null�̏ꍇ");
        	l_wkMfProduct = l_mfProduct;
        }
        //�A ����.�抷��̖�����null�ȊO�̏ꍇ
        else
        {
        	l_wkMfProduct = l_swtProduct;
            log.debug("�A ����.�抷��̖�����null�ȊO�̏ꍇ");
        }
        
		//�B �g�����M�����}�l�[�W��.is�萔�������ڋq(����.�ڋq,����.����) �� true �̏ꍇ
		//�萔���P���A���Ƃ��āA0��ԋp����B
		if (l_orderManager.isCommissionFreeAccount(l_mainAccount, l_wkMfProduct))
		{
			log.debug("�B�萔�������ڋq�̏ꍇ");
		}
		//�C �g�����M�����}�l�[�W��.is�萔�������ڋq(����.�ڋq,����.����) �� false �̏ꍇ
		else
		{
			log.debug("�C�萔�������ڋq�łȂ��ꍇ");
			//�P�j ����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ     
	        if (l_mfProduct.isForeignFund() || l_mfProduct.isFWF())
	        {
				log.debug("�P�j����.����.is�O�����M() �� true or ����.����.isFWF() �� true �̏ꍇ");
	            //(a)
	            //this.�萔���敪���g��n����i�萔�����j�h 
	            //and �i this.����敪���g���t�h or �g��W�h �j   
	            //and ����.�w��敪���g�����w��h�̏ꍇ       
	            if (WEB3MFCommissionDivDef.PAYMENT_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv())
	                && (WEB3MFDealDivDef.BUY.equals(this.getDealDiv()) 
	                || WEB3MFDealDivDef.RECRUIT.equals(this.getDealDiv())) 
	                && WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
	            {          
					log.debug("(a) this.�萔���敪���g��n����i�萔�����j" +
						"and �i this.����敪���g���t�h or �g��W�h �j" +
						"and ����.�w��敪���g�����w��h�̏ꍇ");
	                //�������� �� ����.�����l �� this.���ʔ͈́i���j �~ 
	                //�i �P �| this.�萔���P���A�� �~ �i �P �{  ����ŗ�(*)  �j �j
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_1);
	            }
	            //(b)
	            //this.�萔���敪���g��������i�萔�����j�h 
	            //and �i this.����敪���g���t�h or �g��W�h �j 
	            //and ����.�w��敪���g���z�w��h
				else if (WEB3MFCommissionDivDef.TRADE_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv())
	                && (WEB3MFDealDivDef.BUY.equals(this.getDealDiv()) 
	                || WEB3MFDealDivDef.RECRUIT.equals(this.getDealDiv())) 
	                && WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
	            {
					log.debug("(b) this.�萔���敪���g��������i�萔�����j" +
						"and �i this.����敪���g���t�h or �g��W�h �j" +
						"and ����.�w��敪���g���z�w��h�̏ꍇ");
	                //�������� �� ����.�����l �� this.���ʔ͈́i���j �~ 
	                //�i �P �{ this.�萔���P���A�� �~ �i �P �{ ����ŗ�(*)  �j �j
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_2);
	            }
	            //(c)��L�̏����ɓ��Ă͂܂�Ȃ��ꍇ�A�����������ȉ��̂��̂Ƃ���B
	            else
	            {
					log.debug("(c) ����ȊO�̏ꍇ");
	                //�������� �� this.���ʔ͈́i���j �� ����.�����l �� this.���ʔ͈́i���j
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_3);
	            }               
	        }
	        //�Q�j ��L�ȊO�̏ꍇ
	        else
	        {            
				log.debug("�Q�j��L�ȊO�̏ꍇ");
	            //(a) 
	            //�i �i this.�萔���敪���g��n����i�萔�����j�h and 
	            //�i this.����敪���g���t�h or �g��W�h �j and ����.�w��敪���g�����w��h �j or
	            //�i this.�萔���敪���g��������i�萔�����j�h and 
	            //�ithis.����敪���g���h or �g�抷�h �j and ����.�w��敪���g���z�w��h�j�j
				if (((WEB3MFCommissionDivDef.PAYMENT_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv()) &&
	                (WEB3MFDealDivDef.BUY.equals(this.getDealDiv()) || 
	                WEB3MFDealDivDef.RECRUIT.equals(this.getDealDiv())) && 
	                WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv)) ||
	                (WEB3MFCommissionDivDef.TRADE_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv()) &&
	                (WEB3MFDealDivDef.SELL.equals(this.getDealDiv()) || 
	                WEB3MFDealDivDef.SWITCHING.equals(this.getDealDiv())) && 
	                WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv)))) 
	            {
					log.debug("(a) �i �ithis.�萔���敪���g��n����i�萔�����j�h " +
						"and �ithis.����敪���g���t�h or �g��W�h�j " +
						"and ����.�w��敪���g�����w��h�j or" +
						"�ithis.�萔���敪���g��������i�萔�����j�h " +
						"and �ithis.����敪���g���h or �g�抷�h�j " +
						"and ����.�w��敪���g���z�w��h�j�j�̏ꍇ");
	                //�������� �� ����.�����l �� this.���ʔ͈́i���j �~ 
	                //�i �P �| this.�萔���P���A�� �~ �i �P �{ ����ŗ�(*) �j �j
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_1);
	            }
	            //(b)
	            //�i �i this.�萔���敪���g��n����i�萔�����j�h and 
	            //�i this.����敪���g���h or �g�抷�h �j 
	            //    and ����.�w��敪���g�����w��h �j or
	            //�i this.�萔���敪���g��������i�萔�����j�h and 
	            //�i this.����敪���g���t�h or �g��W�h �j 
	            //    and ����.�w��敪���g���z�w��h �j �j
	            else if (((WEB3MFCommissionDivDef.PAYMENT_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv()) &&
	                (WEB3MFDealDivDef.SELL.equals(this.getDealDiv()) || 
	                WEB3MFDealDivDef.SWITCHING.equals(this.getDealDiv())) &&
	                WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv)) ||
	                (WEB3MFCommissionDivDef.TRADE_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv()) &&
	                (WEB3MFDealDivDef.BUY.equals(this.getDealDiv()) || 
	                WEB3MFDealDivDef.RECRUIT.equals(this.getDealDiv())) &&
	                WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))))
	            {
					log.debug("(b) �i �ithis.�萔���敪���g��n����i�萔�����j�h " +
						"and �ithis.����敪���g���h or �g�抷�h�j " +
						"and ����.�w��敪���g�����w��h�j or" +
						"�ithis.�萔���敪���g��������i�萔�����j�h " +
						"and �ithis.����敪���g���t�h or �g��W�h�j " +
						"and ����.�w��敪���g���z�w��h�j �j�̏ꍇ");
	                //�������� �� ����.�����l �� this.���ʔ͈́i���j �~ 
	                //�i �P �{ this.�萔���P���A�� �~ �i �P �{ ����ŗ�(*) �j �j
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_2);
	            }
	            //(c)��L�̏����ɓ��Ă͂܂�Ȃ��ꍇ�A�����������ȉ��̂��̂Ƃ���B
	            else
	            {
					log.debug("(c) ����ȊO�̏ꍇ");
	                //�������� �� this.���ʔ͈́i���j �� ����.�����l �� this.���ʔ͈́i���j
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_3);
	            }
	        }
		}
		
        log.exiting(STR_METHOD_NAME);        
        return l_bdCommisionPriceRate;
    }
    
    private class MFCommisionPriceRate
    {
        /**
         * �������� �� ����.�����l �� this.���ʔ͈́i���j �~
         *  �i �P �| this.�萔���P���A�� �~ �i �P �{ this.����ŗ� �j �j
         */
        public static final String CONDITION_1 = "condition_1";
        
        /**
         * �������� �� ����.�����l �� this.���ʔ͈́i���j �~
         *  �i �P �{ this.�萔���P���A�� �~ �i �P �{ this.����ŗ� �j �j
         */
        public static final String CONDITION_2 = "condition_2";
        
        /**
         * �������� �� this.���ʔ͈́i���j �� ����.�����l �� this.���ʔ͈́i���j
         */
        public static final String CONDITION_3 = "condition_3";
        
        /**
         * ���M��Еʎ萔���s�I�u�W�F�N�g <BR>
         */
        private MutualFundInstCommissionParams mfInstCommissionParams;
        
        /**
         * �萔���P���A���I�u�W�F�N�g <BR>
         */
        private Map commisionPriceRate;
        
        private String institutionCode;
      
        private Date bizDate;
        
        private  BigDecimal l_bdNewFindValue;
        
        public MFCommisionPriceRate(
            MutualFundInstCommissionParams l_mfInstCommissionParams, 
            String l_strInstitutionCode, 
            Date l_datBizDate,
            BigDecimal l_bdFindValue)
        {
            this.mfInstCommissionParams = l_mfInstCommissionParams;
            this.institutionCode = l_strInstitutionCode;
            this.bizDate = l_datBizDate;
            commisionPriceRate = new HashMap();
            this.l_bdNewFindValue = l_bdFindValue;
        }

        public BigDecimal getMFCommisionPriceRate(String l_strType) throws WEB3BaseException
        {
            BigDecimal l_bdMFCommisionPriceRate = null;

            if (CONDITION_1.equals(l_strType))
            {
                //�������� �� ����.�����l �� this.���ʔ͈́i���j �~
                //�i �P �| this.�萔���P���A�� �~ �i �P �{ ����ŗ�(*)  �j �j
                this.setMFCommisionPriceRate(l_bdNewFindValue, CONDITION_1);
                if (commisionPriceRate.get(CONDITION_1) != null)
                {
                    l_bdMFCommisionPriceRate = (BigDecimal)commisionPriceRate.get(CONDITION_1);
                }
            }
            else if (CONDITION_2.equals(l_strType))
            {
                //�������� �� ����.�����l �� this.���ʔ͈́i���j �~
                //�i �P �{ this.�萔���P���A�� �~ �i �P �{ ����ŗ�(*) �j �j
                this.setMFCommisionPriceRate(l_bdNewFindValue, CONDITION_2);
                if (commisionPriceRate.get(CONDITION_2) != null)
                {
                    l_bdMFCommisionPriceRate = (BigDecimal)commisionPriceRate.get(CONDITION_2);
                }       
            }
            else if (CONDITION_3.equals(l_strType))
            {
                //�������� �� this.���ʔ͈́i���j �� ����.�����l �� this.���ʔ͈́i���j
                this.setMFCommisionPriceRate(l_bdNewFindValue, CONDITION_3);
                if (commisionPriceRate.get(CONDITION_3) != null)
                {
                    l_bdMFCommisionPriceRate = (BigDecimal)commisionPriceRate.get(CONDITION_3);
                } 
            }
            return l_bdMFCommisionPriceRate;
        }
        
        public void setMFCommisionPriceRate(BigDecimal l_bdSearchValue, String l_strFalg) throws WEB3BaseException
        {
            //���ʔ͈�(��)
            String l_strAmountFrom = "";
            //���ʔ͈�(��)
            String l_strAmountTo = "";
            //�萔���P���A��
            String l_strCommissionPriceRate = "";
            
            //����ŗ�(*)            
            WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(
                this.institutionCode, 
                WEB3DutyTypeDef.CONSUMPTION_TAX, 
                new Timestamp(this.bizDate.getTime()));
        
            //�ŗ����擾����B        
            //�擾�����ŗ��C���X�^���X.get�ŗ�()���\�b�h���R�[������B            
			BigDecimal l_bdConsumptionTaxRate = new BigDecimal(Double.toString(l_taxRate.getTaxRate())).multiply(
				new BigDecimal("0.01"));
            
            for (int i = 1; i <= 10; i++)
            {
                if (i < 10)
                {
                    l_strAmountFrom = "amount_from_" + "0" + i;
                    l_strAmountTo = "amount_to_" + "0" + i;
                    l_strCommissionPriceRate = "commission_price_rate_" + "0" + i;
                }
                else
                {
                    l_strAmountFrom = "amount_from_" + i;
                    l_strAmountTo = "amount_to_" + i;
                    l_strCommissionPriceRate = "commission_price_rate_" + i;
                }
                
                //���ʔ͈�(��)
                long l_lngAmountFrom = 0;
                if (this.mfInstCommissionParams.getColumn(l_strAmountFrom) != null)
                {
                    l_lngAmountFrom = 
                        ((Long)this.mfInstCommissionParams.getColumn(l_strAmountFrom)).longValue(); 
                }
                
                //���ʔ͈́i��)
                long l_lngAmountTo = 0;
                if (this.mfInstCommissionParams.getColumn(l_strAmountTo) != null)
                {
                    l_lngAmountTo = 
                        ((Long)this.mfInstCommissionParams.getColumn(l_strAmountTo)).longValue(); 
                }
                
                //�萔���P���A��
				BigDecimal l_bdCommissionPriceRate = null;
                if (this.mfInstCommissionParams.getColumn(l_strCommissionPriceRate) != null)
                {
                	if(WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(this.mfInstCommissionParams.getCommissionDiv()))
                	{
						l_bdCommissionPriceRate = 
							new BigDecimal(
								((Double)this.mfInstCommissionParams.getColumn(l_strCommissionPriceRate)).toString());
                	}
                	else
                	{
						l_bdCommissionPriceRate = 
							new BigDecimal(
								((Double)this.mfInstCommissionParams.getColumn(l_strCommissionPriceRate)).toString());
						l_bdCommissionPriceRate = 
							l_bdCommissionPriceRate.multiply(new BigDecimal("0.01"));
                	}
                }

                //�������� �� ����.�����l �� this.���ʔ͈́i���j �~
                //�i �P �| this.�萔���P���A�� �~ �i �P �{ ����ŗ�(*)  �j �j
                if (CONDITION_1.equals(l_strFalg))
                {
					BigDecimal l_bdSearchValueTmp =
						new BigDecimal(l_lngAmountTo).multiply(
							new BigDecimal("1").subtract(
								l_bdCommissionPriceRate.multiply(new BigDecimal("1").add(l_bdConsumptionTaxRate))));

					if (l_bdSearchValue.compareTo(l_bdSearchValueTmp) <= 0)
					{
						if (!commisionPriceRate.containsKey(CONDITION_1))
						{
							commisionPriceRate.put(CONDITION_1, l_bdCommissionPriceRate);
						}
					}
                }
				//�������� �� ����.�����l �� this.���ʔ͈́i���j �~
				//�i �P �{ this.�萔���P���A�� �~ �i �P �{ ����ŗ�(*)  �j �j
                else if (CONDITION_2.equals(l_strFalg))
                {
					BigDecimal l_bdSearchValueTmp =
						new BigDecimal(l_lngAmountTo).multiply(
							new BigDecimal("1").add(
								l_bdCommissionPriceRate.multiply(new BigDecimal("1").add(l_bdConsumptionTaxRate))));
                    
					if (l_bdSearchValue.compareTo(l_bdSearchValueTmp) <= 0)
                    {
                        if (!commisionPriceRate.containsKey(CONDITION_2))
                        {
                            commisionPriceRate.put(CONDITION_2, l_bdCommissionPriceRate);
                        }
                    }
                }
				//�������� �� this.���ʔ͈́i���j �� ����.�����l �� this.���ʔ͈́i���j
                else if (CONDITION_3.equals(l_strFalg))
                {
					if (l_bdSearchValue.compareTo(new BigDecimal(l_lngAmountFrom)) >= 0 &&
						l_bdSearchValue.compareTo(new BigDecimal(l_lngAmountTo)) <= 0)
                    {
                        if (!commisionPriceRate.containsKey(CONDITION_3))
                        {
                            commisionPriceRate.put(CONDITION_3, l_bdCommissionPriceRate);
                        }
                    }
                }
            }           
        }
    }
}
@
