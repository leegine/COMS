head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3EquityKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20  WuYanFei(sinocom)�@@�V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
*/
package webbroker3.equity.define;

/**
 * �L�[����
 *                                                                     
 * @@author Wu Yan Fei
 * @@version 1.1
 */
public interface WEB3EquityKeyItemDef
{
    /**
     * �����敪
     */
    public static final String ACCOUNTTYPE = "taxType";
    
	/**
	 * �ٍϋ敪
	 */
	public static final String REPAYMENT_DIV = "repaymentDiv";
    
    /**
     * �ٍϊ����l
     */
    public static final String REPAYMENTNUM = "repaymentTimeLimit";

    /**
     * ����
     */
    public static final String OPEN_DATE = "openDate";

    /**
     * �]�����v
     */
    public static final String INCOME = "appraisalProfitLoss";
    
	/**
	 * �]�����v�i���o��l���j
	 */
	public static final String INCOME_COST = "appraisalProfitLossCost";
	
    /**
     * ���敪
     */
    public static final String CONTRACT_DIVISION = "contractType";

    /**
     * �����R�[�h
     */
    public static final String PRODUCTCODE = "productCode";

    /**
     * ����
     */
    public static final String CLOSEDATE = "closeDate";
    
    /**
     * ����敪
     */
    public static final String TRADETYPE = "tradingType";
    
    /**
     * ���s����
     */
    public static final String EXECUTE_COND = "execCondType";
    
    /**
     * ��������
     */
    public static final String SEND_COND = "orderCondType";
    
    /**
     * ��������
     */
    public static final String ORDER_TIME = "orderDate";
    
    /**
     * ��������
     */
    public static final String ORDER_TIMELIMIT = "expirationDate";

    /**
     * �s��R�[�h
     */
    public static final String TRADEMARKET = "marketCode";
    
    /**
     * �����敪
     */
    public static final String DEALINGTYPE = "dealingType";
    
    /**
     * �l�i����
     */
    public static final String PRICE_COND = "priceCondType";
    
    /**
     * ������
     */
    public static final String SEND_DATE = "orderBizDate";
    
    /**
     * ��t�J�n����
     */
    public static final String ORDER_START_DATE_TIME = "orderStartDatetime";
    
    /**
     * ��t�I������
     */
    public static final String ORDER_END_DATE_TIME = "orderEndDatetime";
    
    /**
     * �T�Z�]���z(�c������)
     */
    public static final String ESTIMATED_ASSET_BALANCE_QUANTITY = "estimatedAssetBalanceQuantity";
    
    /**
     * �T�Z�]�����v(�c������)
     */
    public static final String ESTIMATED_INCOME_BALANCE_QUANTITY = "estimatedAppraisalProfitLossBalanceQuantity";
    
    /**
     * ���P��
     */
    public static final String CONTRACT_PRICE = "contractPrice";
}
@
