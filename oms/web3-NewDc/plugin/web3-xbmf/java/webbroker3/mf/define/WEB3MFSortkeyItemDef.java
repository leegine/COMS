head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFSortkeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3MFSortkeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �����(���u)�@@�V�K�쐬
                 : 2004/12/01 �����(���u)�@@�ύX
                   2006/03/08 ��� (SRA) �d�l�ύX�i���f���j�F403
*/
package webbroker3.mf.define;

/**
 * (���M�Ǘ��Җ����\�������o�^���͉�ʗv���N���X)���M�\�[�g�L�[�L�[����<BR>
 *  �萔��`�C���^�t�F�C�X
 *                                                                   
 * @@author �����
 * @@version 1.0
 */
public interface WEB3MFSortkeyItemDef
{
    /**
     * taxType : ����
     */
    public static final String TAX_TYPE = "taxType";

    /**
     * marketValue : �]���z
     */
    public static final String MARKET_VALUE = "marketValue";

    /**
     * appraisalProfitLoss : �]�����v
     */
    public static final String APPRAISAL_PROFIT_LOSS = "appraisalProfitLoss";
    
    /**
     * orderCloseTime : ������t���؎���
     */
    public static final String ORDER_CLOSE_TIME = "orderCloseTime";

    /**
     * mutualDealingType : �����@@ 
     */
    public static final String MUTUAL_DEALING_TYPE = "mutualDealingType";

    /**
     * orderDate : ���������@@  
     */
    public static final String ORDER_DATE = "orderDate";
    
    /**
     * sellBuyDiv : �������@@�@@  
     */
    public static final String SELL_BUY_DIV = "sellBuyDiv";    

    /**
     * current_display_order : ���ݕ\�����@@  
     */
    public static final String DISPLAY_ORDER = "displayOrder";

    /**
     * product_code : �����R�[�h�@@  
     */
    public static final String PRODUCT_CODE = "mutualProductCode";

    /**
     * mutual_assoc_product_code : ���M��������R�[�h�@@  
     */
    public static final String MUTUAL_ASSOC_PRODUCT_CODE = "mutualAssocProductCode";

    /**
     * mutual_category_code1 : ���M�����J�e�S���[�R�[�h�P�@@  
     */
    public static final String MUTUAL_CATEGORY_CODE_1 = "categoryCode1";

    /**
     * mutual_category_code2 : ���M�����J�e�S���[�R�[�h�Q�@@  
     */
    public static final String MUTUAL_CATEGORY_CODE_2 = "categoryCode2";

    /**
     * mutual_category_code3 : ���M�����J�e�S���[�R�[�h�R
     */
    public static final String MUTUAL_CATEGORY_CODE_3 = "categoryCode3";
    
    /**
     * mutualProductId : ����ID
     */
    public static final String MUTUAL_PRODUCT_ID = "mutualProductId";
    
}
@
