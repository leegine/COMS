head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderExecStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3OrderExecStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * ��n����@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3OrderExecStatusDef
{
    /**
     * 0 : �����
     */
    public static final String UNEXECUTED = "0";

    /**
     * 1 : �ꕔ���
     */
    public static final String PARTIALLY_EXECUTED = "1";

    /**
     * 2 : ����
     */
    public static final String EXECUTED = "2";

    /**
     * 3 : ����
     */
    public static final String CLOSE = "3";

    /**
     * 4 : �ꕔ����
     */
    public static final String PARTIALLY_CLOSE = "4";
}
@
