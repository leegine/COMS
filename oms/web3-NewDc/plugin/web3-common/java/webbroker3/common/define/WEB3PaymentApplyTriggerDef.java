head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentApplyTriggerDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3PaymentApplyTriggerDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 �����(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �،���Ѓe�[�u���̏o���\���g���K���s�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3PaymentApplyTriggerDef
{
    /**
     * 0�F�^�C�}�[���{�i�]�̓`�F�b�N�Ȃ��j�@@
     */
    public static final String ENFORCEMENT_NOTCHECK = "0";

    /**
     * 1�F�^�C�}�[���{�i�]�̓`�F�b�N����j
     */
    public static final String ENFORCEMENT_CHECK = "1";    

    /**
     * 2�F���A���^�C�����{�@@�@@
     */
    public static final String REAL_ENFORCEMENT = "2";    
}
@
