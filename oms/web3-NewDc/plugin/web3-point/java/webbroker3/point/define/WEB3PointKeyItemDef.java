head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.03.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3PointKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.define;

/**
 * �L�[����
 *                                                                     
 * @@author �A�C��
 * @@version 1.1
 */
public interface WEB3PointKeyItemDef
{

    /**
     * "branchCode" : ���X�R�[�h
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * "accountCode" : �ڋq�R�[�h
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * "premiumNo" : �i�i�ԍ�
     */
    public static final String PREMINUM_NO = "premiumNo";

    /**
     * "premiumName" : �i�i��
     */
    public static final String PREMIUM_NAME = "premiumName";

    /**
     * "applyDate" : �\������
     */
    public static final String APPLY_TIMESTAMP = "applyDate";
    
    /**
     * "acceptDiv" : ��t�敪
     */
    public static final String APPLY_ACCEPT_DIV = "acceptDiv";    
        
    /**
     * "updateTimeStamp" : �X�V����
     */
    public static final String LAST_UPDATED_TIMESTAMP = "updateTimeStamp";

    /**
     * "acceptUser" : ��t���[�U
     */
    public static final String APPLY_ACCEPT_USER = "acceptUser";

    /**
     * "cancelDiv" : ����敪
     */
    public static final String APPLY_CANCEL_DIV = "cancelDiv";
    
}@
