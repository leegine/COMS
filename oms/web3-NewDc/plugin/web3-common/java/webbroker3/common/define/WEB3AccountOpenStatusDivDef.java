head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountOpenStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AccountOpenStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/21 ���z (���u) �V�K�쐬
Revesion History : 2006/02/16 ������ (���u) ���o���d�l�ύX�Ǘ��䒠No.80(�c�a���C�A�E�g)
*/

package webbroker3.common.define;

/**
 * �����J�ݏ󋵋敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ���z
 * @@version 1.0
 */
public interface WEB3AccountOpenStatusDivDef
{
    /**
     * 0�F�����J�ݒ��@@�@@
     */
    public static final String ACCOUNT_OPENING = "0";
    
    /**
     * 1�F�����J�݊���
     */
    public static final String ACCOUNT_OPEN_COMPLETE = "1";
    
    /**
     * 2�F�����J�݃G���[
     */
    public static final String ACCOUNT_OPEN_ERROR = "2";

    /**
     * 3:�_�E�����[�h��
     */
    public static final String DOWNLOAD_COMPLETE = "3";
    
    /**
     * 9�F�폜
     */
    public static final String DELETE = "9";
}
@
