head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DirAdminFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �c�h�q�Ǘ��҃t���O�萔��`�C���^�t�F�C�X(WEB3DirAdminFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/24 �h�C(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �c�h�q�Ǘ��҃t���O �萔��`�C���^�t�F�C�X
 *
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3DirAdminFlagDef
{
    /**
     * 0�F�ʏ�Ǘ��ҁFDEFAULT
     */
    public static final String NORMAL_ADMINISTRATOR = "0";

    /**
     * 1 : DIR�Ǘ���
     */
    public static final String DIR_ADMINISTRATOR = "1";

    /**
     * 2�F�ʏ�Ǘ���(�\����)
     */
    public static final String NORMAL_ADMINISTRATOR_APPLICATIONER = "2";

    /**
     * 3�F�ʏ�Ǘ��ҁi���F�ҁj
     */
    public static final String NORMAL_ADMINISTRATOR_APPROVALER = "3";

}
@
