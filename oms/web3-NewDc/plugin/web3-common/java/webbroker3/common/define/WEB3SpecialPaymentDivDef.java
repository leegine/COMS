head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SpecialPaymentDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ꗘ���敪�萔��`�C���^�t�F�C�X(WEB3SpecialPaymentDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 �h�C(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ���ꗘ���敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3SpecialPaymentDivDef
{
    /**
     * 0�F���ꗘ��������
     */
    public static final String NO_SPECIAL_PAYMENT_DAY = "0";

    /**
     * 1�F�����E�I���X�L�b�v
     */
    public static final String START_END_SKIP = "1";

    /**
     * 2�F�����X�L�b�v
     */
    public static final String START_SKIP = "2";

    /**
     * 3�F�I���X�L�b�v
     */
    public static final String END_SKIP = "3";

    /**
     * 4�F�����s��
     */
    public static final String START_NOT_PAYMENT = "4";

    /**
     * 5�F�������z����
     */
    public static final String START_FULL_PAYMENT = "5";

    /**
     * 6�F�V���[�g�C���^���X�g
     */
    public static final String SHORT_INTEREST = "6";
}
@
