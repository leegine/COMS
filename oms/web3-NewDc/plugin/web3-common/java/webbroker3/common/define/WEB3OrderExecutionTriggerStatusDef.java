head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderExecutionTriggerStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���g���K�[�e�[�u��������� �萔��`�C���^�t�F�C�X(WEB3OrderExecutionTriggerStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 �����@@�ǘa(SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ���g���K�[�e�[�u��������ԁ@@�萔��`�C���^�t�F�C�X
 *
 * @@author �����@@�ǘa(SRA)
 * @@version 1.0
 */
public interface WEB3OrderExecutionTriggerStatusDef
{
    /**
     * ���ғ�
     */
    public static final String THREAD_IDLE = "0";

    /**
     * �ғ�
     */
    public static final String THREAD_PROCESSING = "1";

    /**
     * �g���K�[�iAP�ďo���j
     */
    public static final String THREAD_API_CALL = "2";
}
@
