head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FutureOpAccountDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����J�݋敪(�ڋq�}�X�^�[) �萔��`�C���^�t�F�C�X(WEB3FutureOpAccountDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
Revesion History : 2004/10/10 � ��(sinocom)�@@JavaDoc���C��
*/
package webbroker3.common.define;

/**
 * �敨OP�����J�݋敪(�ڋq�}�X�^�[)�@@�萔��`�C���^�t�F�C�X <BR>
 *   �敨OP�����J�݋敪�i���؁j <BR>
 *   �敨OP�����J�݋敪�i��؁j <BR>
 *   �敨OP�����J�݋敪�i���؁j <BR>
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3FutureOpAccountDef
{
    /**
     * 0 : DEFAULT�i�����Ȃ��j
     */
    public static final String DEFAULT = "0";

    /**
     * 1 : OP�����J��
     */
    public static final String OP_ACCOUNT_ESTABLISH = "1";

    /**
     * 2 : �敨�����J��
     */
    public static final String FUTURE_ACCOUNT_ESTABLISH = "2";

    /**
     * 3 : �敨OP�����J
     */
    public static final String FUTURE_OP_ACCOUNT_ESTABLISH = "3";

}
@
