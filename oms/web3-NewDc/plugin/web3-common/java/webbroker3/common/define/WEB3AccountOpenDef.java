head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountOpenDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J��(WEB3AccountOpenDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 ����� (���u) �V�K�쐬
Revesion History : 2006/03/17 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g355
*/

package webbroker3.common.define;

/**
 * �����J�� �萔��`�C���^�t�F�C�X
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3AccountOpenDef
{

    /**
     * 0�F���J��
     */
    public final static String NOT_OPEN  = "0";

    /**
     * 1�F�J��
     */
    public final static String OPEN  = "1";
    
    /**
     * 2�F��������
     */
    public final static String DELETED  = "2";

    /**
     * 3�F�U�֒�~
     */
    public final static String TRANSFER_STOP  = "3";
}@
