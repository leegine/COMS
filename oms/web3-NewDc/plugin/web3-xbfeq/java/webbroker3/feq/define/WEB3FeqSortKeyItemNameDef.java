head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSortKeyItemNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L�[���ڂ̍��ږ� �萔��`�C���^�t�F�C�X(WEB3FeqSortKeyItemNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �Г� (���u) �V�K�쐬
                   2005/07/25 �Г� (���u) �ǉ�
                   2006/11/20 ����� (���u) ��Q�Ǘ� K00004,K00005
*/
package webbroker3.feq.define;

/**
 * �L�[���ڂ̍��ږ� ��`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqSortKeyItemNameDef
{
    /**
     * �^�p�R�[�h
     */
    public static final String ORDER_EMP_CODE = "managementCode";
    
    /**
     * �����ԍ�
     */
    public static final String ORDER_NO = "requestNumber";

    /**
     * ���X�R�[�h
     */
    public static final String BRANCH_CODE = "branchCode";
    
    /**
     * �ڋq�R�[�h
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * ��������敪
     */
    public static final String TAX_TYPE_DIV = "taxType";
    
    /**
     * ��������
     */
    public static final String ORDER_TIME = "orderDate";

    /**
     * ���ϋ敪
     */
    public static final String SETTLE_DIV = "settleDiv";
    
    /**
     * �s��R�[�h
     */
    public static final String MARKET_CODE = "marketCode";
    
    /**
     * �����R�[�h
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * �����敪
     */
    public static final String BUY_SELL_DIV = "dealingType";
    
    /**
     * ������
     */
    public static final String BIZ_DATE = "orderBizDate";

    /**
     * ���n�����R�[�h
     */
    public static final String OFFSHORE_PRODUCT_CODE = "localProductCode";

    /**
     * ���s����
     */
    public static final String EXEC_COND_TYPE = "execCondType";

    /**
     * ��������
     */
    public static final String ORDER_COND_TYPE = "orderCondType";

    /**
     * �����L������
     */
    public static final String EXPIRATION_DATE = "expirationDate"; 
    
    /**
     * �����敪
     */
    public static final String TRANSACTION_DIV = "transactionDiv"; 
    
    /**
     * �쐬���t
     */
    public static final String CREATED_TIMESTAMP = "createTimeStamp";

    /**
     * �X�V���t
     */
    public static final String LAST_UPDATED_TIMESTAMP = "updateTimeStamp";

    /**
     * �T�Z�]���z(�c������)
     */
    public static final String ESTIMATED_ASSET_BALANCE_QUANTITY = 
        "estimatedAssetBalanceQuantity";

    /**
     * �T�Z�]�����v(�c������) 
     */
    public static final String ESTIMATED_APPRAISAL_PROFIT_LOSS_BALANCE_QUANTITY = 
        "estimatedAppraisalProfitLossBalanceQuantity";

    /**
     * ������
     */
    public static final String PRODUCT_NAME = "standardNameKana";
    
    /**
     * �X�V�҃R�[�h
     */
    public static final String UPDATER_CODE = "updaterCode";
    
    /**
     * �o�H�敪
     */
    public static final String ORDER_ROOT_DIV = "orderRootDiv";
    
    /**
     * ��t�敪
     */
    public static final String ACCEPT_DIV = "acceptDiv";
}
@
