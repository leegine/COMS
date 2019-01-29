head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L�[���ڒ�`�C���^�t�F�C�X(WEB3IpoKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10  qi-lin(sinocom)�@@�V�K�쐬
*/
package webbroker3.ipo.define;

/**
 * �L�[����
 *                                                                     
 * @@author qi-lin
 * @@version 1.0
 */
public interface WEB3IpoKeyItemDef
{

    /**
     * branchCode : ���X�R�[�h
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * accountCode : �ڋq�R�[�h
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * accountName : �ڋq��
     */
    public static final String ACCOUNT_NAME = "accountName";

    /**
     * lotResultDiv : ���I���ʋ敪
     */
    public static final String LOT_RESULT_DIV = "lotResultDiv";

    /**
     * prizeQuantity : ���I����
     */
    public static final String PRIZE_QUANTITY = "prizeQuantity";
        
    /**
     * offerQuantity : �w���\������
     */
    public static final String OFFER_QUANTITY = "offerQuantity";

    /**
     * offerCancelDate : �w���\�����ޓ���
     */
    public static final String OFFER_CANCEL_DATE = "offerCancelDate";

    /**
     * offerStateDiv : �w���\���󋵋敪
     */
    public static final String OFFER_STATE_DIV = "offerStateDiv";
    
    /**
     * receiveStateDiv : ��t��ԋ敪
     */
    public static final String RECEIVE_STATE_DIV = "receiveStateDiv";
    
    /**
     * priority : �D�揇��
     */
    public static final String PRIORITY = "priority";
    
}@
