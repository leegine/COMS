head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L�[���ڒ�`�C���^�t�F�C�X(WEB3IfoKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10  ZouRui(sinocom)�@@�V�K�쐬
*/
package webbroker3.ifo.define;

/**
 * �L�[����
 *                                                                     
 * @@author Zou Rui & zhang wei
 * @@version 1.1
 */
public interface WEB3IfoKeyItemDef
{

    /**
     * ������
     */
    public static final String PRODUCT_NAME = "opProductName";

    /**
     * ����s��
     */
    public static final String TRADE_MARKET = "marketCode";

    /**
     * ����敪
     */
    public static final String TRADE_DIVISION = "tradingType";

    /**
     * ��������
     */
    public static final String ORDER_TIME = "orderDate";

    /**
     * �����L������
     */
    public static final String ORDER_EXPIRATION_DATE = "expirationDate";

    /**
     * ����
     */
    public static final String OPEN_DATE = "openDate";

    /**
     * ���v
     */
    public static final String INCOME = "income";
    
    /**
     * ���v�i���o��j
     */
    public static final String INCOME_COST = "incomeCost";
    
    /**
     * ���敪
     */
    public static final String CONTRACT_DIVISION = "contractType";
    
    /**
     * OP�����R�[�h
     */
    public static final String PRODUCTCODE = "opProductCode";
    
    /**
     * ���Ϗ�ԋ敪
     */
    public static final String SETTLEMENT_STATUS_DIVISION = "settlementType";
    
    /**
     * �敨�����R�[�h
     */
    public static final String FUTPRODUCTCODE = "futProductCode";
    
    /**
     * �c���Ɖ�����R�[�h
     */
    public static final String BR_PRODUCTCODE = "productCode";
    
    /**
     * ������
     */
    public static final String BIZ_DATE = "orderBizDate";
    
    /**
     * ���P��
     */
    public static final String CONTRACT_PRICE = "contractPrice";    
}@
