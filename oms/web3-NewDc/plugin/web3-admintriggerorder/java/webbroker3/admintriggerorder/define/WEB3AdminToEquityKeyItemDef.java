head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToEquityKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����L�[���ڒ�`�C���^�t�F�C�X(WEB3AdminToEquityKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/8 ������(���u) �V�K�쐬
*/
package webbroker3.admintriggerorder.define;

/**
 * �����L�[���� ��`�C���^�t�F�C�X<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public interface WEB3AdminToEquityKeyItemDef 
{
    /**
     * branchCode: ���X�R�[�h
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * accountCode: �ڋq�R�[�h
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * productCode: �����R�[�h
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * marketCode: �s��R�[�h
     */
    public static final String MARKET_CODE = "marketCode";

    /**
     * taxType: �����敪
     */
    public static final String TAX_TYPE = "taxType";
    
    /**
     * tradingType: ����敪
     */
    public static final String TRADING_TYPE = "tradingType";

    /**
     * repaymentType: �ٍ�
     */
    public static final String REPAYMENT_TYPE = "repaymentType";

    /**
     * priceConditionType: �l�i����
     */
    public static final String PRICE_CONDITION_TYPE = "priceConditionType";
    
    /**
     * execCondType: ���s����
     */
    public static final String EXEC_COND_TYPE = "execCondType";

    /**
     * expirationDate: �����L������
     */
    public static final String EXPIRATION_DATE = "expirationDate";

    /**
     * orderDate: ��������
     */
    public static final String ORDER_DATE = "orderDate";

    /**
     * orderBizDate: ������
     */
    public static final String ORDER_BIZ_DATE = "orderBizDate";

    /**
     * deliveryDate: ��n��
     */
    public static final String DELIVERY_DATE = "deliveryDate";
}
@
