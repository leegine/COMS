head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DealedTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3DealedTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
Revesion History : 2004/09/13 �� ��(sinocom) CANCEL�̓��{�ꖼ��ύX
*/
package webbroker3.common.define;

/**
 * �o���ʒm�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3DealedTypeDef
{
    /**
     * 0 : ���
     */
    public static final String EXECUTED = "0";

    /**
     * 1 : �S�����
     */
    public static final String FULLY_EXECUTED = "1";

    /**
     * 2 : �ꕔ���
     */
    public static final String PARTIALLY_EXECUTED = "2";

    /**
     * 4 : �����
     */
    public static final String CANCEL = "4";

}
@
