head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AcceptStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * ������t���ʁ@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3AcceptStatusDef
{
    /**
     * 1 : ������t����
     */
    public static final String OVER = "1";

    /**
     * 2 : �G���[
     */
    public static final String ERROR = "2";

    /**
     * 3 : �O���t���ԊO�G���[
     */
    public static final String MORN_SESS_ACCEPT_OVERTIME_ERROR = "3";
}
@
