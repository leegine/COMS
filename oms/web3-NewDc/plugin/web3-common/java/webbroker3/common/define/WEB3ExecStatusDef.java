head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExecStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3ExecStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 du-min(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * ����ԁ@@�萔��`�C���^�t�F�C�X
 *
 * @@author du-min
 * @@version 1.0
 */
public interface WEB3ExecStatusDef
{

    /**
     * 1 : ��蒆
     */
    public static final String EXECUTED_IN_PROCESS = "1";

    /**
     * 2 : ����
     */
    public static final String EXECUTED_COMPLETE = "2";

    /**
     * NULL : �����l
     */
    public static final String INITIALIZE_PRICE = null;

}
@
