head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L�[���ڒ�`�C���^�t�F�C�X(WEB3ToSuccKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17  ������(sinocom)�@@�V�K�쐬
*/
package webbroker3.triggerorder.define;

/**
 * �L�[����
 *                                                                     
 * @@author ������
 * @@version 1.0
 */
public interface WEB3ToSuccKeyItemDef
{

    /**
     * ���i�敪
     */
    public static final String COMMODITY_TYPE = "commodityType";
    
    /**
     * �����R�[�h
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * �s��R�[�h
     */
    public static final String MARKET_CODE = "marketCode";

    /**
     * �����敪
     */
    public static final String TAX_TYPE = "taxType";
    
    /**
     * ����敪
     */
    public static final String TRADING_TYPE = "tradingType";

    /**
     * �ٍϋ敪
     */
    public static final String REPAYMENT_DIV = "repaymentDiv";
    
    /**
     * �l�i����
     */
    public static final String PRICE_COND_TYPE = "priceCondType";
        
    /**
     * ���s����
     */
    public static final String EXEC_COND_TYPE = "execCondType";
    
    /**
     * ���������敪
     */
    public static final String ORDER_COND_TYPE = "orderCondType";
    
    /**
     * �������� 
     */
    public static final String ORDER_DATE = "orderDate";
    
    /**
     * ������
     */
    public static final String ORDER_BIZ_DATE = "orderBizDate";
    
    /**
     * �����L������
     */
    public static final String EXPIRATION_DATE = "expirationDate";

}
@
