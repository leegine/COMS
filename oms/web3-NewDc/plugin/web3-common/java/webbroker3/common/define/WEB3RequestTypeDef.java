head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RequestTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3RequestTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
Revesion History : 2006/07/12 �h�C (���u)�y�敨�I�v�V�����z�d�l�ύX�E�c�a���C�A�E�gNo.043
Revesion History : 2006/11/28 �h�C (���u)�y�����z�d�l�ύX�E�c�a���C�A�E�gNo.133
Revesion History : 2006/12/11 �h�C (���u)�y�敨�I�v�V�����z�d�l�ύX�E�c�a���C�A�E�gNo.057
*/
package webbroker3.common.define;

/**
 * ���N�G�X�g�^�C�v�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3RequestTypeDef
{
    /**
     * 0 : DEFAULT(���/HOST)
     */
    public static final String DEFAULT = "0";

    /**
     * 1 : �����T�[�o
     */
    public static final String QUOTE_SERVER = "1";

    /**
     * 2�F�ؑ֊���
     */
    public static final String TRANSFERED = "2";

    /**
     * 5�F����
     */
    public static final String INVALIDATE = "5";

    /**
     * 8�F�������s
     */
    public static final String ORDER_FAILURE = "8";
}
@
