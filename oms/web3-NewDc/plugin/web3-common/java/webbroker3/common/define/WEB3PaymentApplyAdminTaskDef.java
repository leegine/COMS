head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentApplyAdminTaskDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3PaymentApplyAdminTaskDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 �����(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �،���Ѓe�[�u���̏o���\���Ǘ��҃^�X�N�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3PaymentApplyAdminTaskDef
{
    /**
     * 0�F�ꗗ�\���̂݁@@
     */
    public static final String ONLY_LIST = "0";

    /**
     * 1�F�o�����{�@@
     */
    public static final String OUT_CASH_ENFORCEMENT = "1";    

    /**
     * 2�F������{�@@
     */
    public static final String CANCEL_ENFORCEMENT = "2";    

    /**
     * 3�F�������{
     */
    public static final String BOTH_ENFORCEMENT = "3";    
}

@
