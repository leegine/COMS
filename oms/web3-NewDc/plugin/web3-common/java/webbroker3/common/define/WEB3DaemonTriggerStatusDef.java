head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DaemonTriggerStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �f�[�����g���K�[�e�[�u��������� �萔��`�C���^�t�F�C�X(WEB3DaemonTriggerStatusDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/02/07 ��(FLJ) �V�K�쐬
 Revesion History : 2006/03/21 ������(���u) �c�a���C�A�E�g�ɂ���āA�C�����܂���
 */

package webbroker3.common.define;

/**
 * �f�[�����g���K�[�e�[�u��������ԁ@@�萔��`�C���^�t�F�C�X
 *
 * @@author ��(FLJ)
 * @@version 1.0
 */
public interface WEB3DaemonTriggerStatusDef
{
    /**
     * 0:���ғ�
     */
    public static final String THREAD_IDLE = "0";

    /**
     * 1:������
     */
    public static final String THREAD_PROCESSING = "1";

    /**
     * 2:�g���K�[�iAP�ďo���j
     */
    public static final String THREAD_API_CALL = "2";
}
@
