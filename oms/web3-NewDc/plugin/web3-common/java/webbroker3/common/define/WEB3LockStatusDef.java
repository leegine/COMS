head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LockStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������b�N�敪�萔��`�C���^�t�F�C�X(WEB3LockStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 �h�C (���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �������b�N�敪�@@�萔��`�C���^�t�F�C�X
 *
 * @@author �h�C (���u)
 * @@version 1.0
 */
public interface WEB3LockStatusDef
{
    /**
     * �O�F���̑�
     */
    public static final String OTHER = "0";

    /**
     * 1�F������
     */
    public static final String RELEASING = "1";

    /**
     * 2�F���b�N��
     */
    public static final String LOCKING = "2";
}
@