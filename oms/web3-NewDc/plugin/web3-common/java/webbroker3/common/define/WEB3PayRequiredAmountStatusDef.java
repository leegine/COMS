head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PayRequiredAmountStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪�萔��`�C���^�t�F�C�X(WEB3PayRequiredAmountStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/13 �h�C(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �ԍϕK�v�z�f�[�^�e�[�u���̏����敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3PayRequiredAmountStatusDef
{
    /**
     * 1:����I��
     */
    public static final String NORMAL_END = "1";

    /**
     * 2:�����ڋq�ȊO
     */
    public static final String EQTYPE_COSTOMER_EXCEPT = "2";

    /**
     * 3:�،��S�ۃ��[�����J��
     */
    public static final String SECURITIES_WARRANTY_LOAN_NOT_OPEN = "3";

    /**
     * 9:�G���[
     */
    public static final String ERROR = "9";
}
@
