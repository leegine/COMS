head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PaymentReserveDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3PaymentReserveDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 �����(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �،���Ѓe�[�u���̏o���\����{�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3PaymentReserveDef
{
    /**
     * 0 : �����{
     */
    public static final String NOT_ENFORCEMENT = "0";

    /**
     * 1 : ���߂̐U�����ȍ~1���ڂ܂ŉ\
     */
    public static final String ENFORCEMENT_AFTER_1DAY = "1";    

    /**
     * 2 : ���߂̐U�����ȍ~2���ڂ܂ŉ\
     */
    public static final String ENFORCEMENT_AFTER_2DAY = "2";    

    /**
     * 3 : ���߂̐U�����ȍ~3���ڂ܂ŉ\
     */
    public static final String ENFORCEMENT_AFTER_3DAY = "3";    

    /**
     * 4 : ���߂̐U�����ȍ~4���ڂ܂ŉ\
     */
    public static final String ENFORCEMENT_AFTER_4DAY = "4";    
}
@
