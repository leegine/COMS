head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleGLRejectTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : GL SLE�̋��ۃ^�C�v��`�C���^�t�F�C�X(SleGLRejectTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/13 �� (FTL) �V�K�쐬
*/

package webbroker3.slebase.utils;


/**
 * GL SLE�̋��ۃ^�C�v��`�C���^�t�F�C�X
 *
 * @@author ���iFTL)
 * @@version 1.0
 */
public interface SleGLRejectTypeDef
{
	/**
	 * GL022:�S���������������
	 */
	public static final String CHANGE_REJECT_AF_ALL_EXEC = "GL 022";
    
	/**
	 * GL007�F�ꕔ���������������
	 */
	public static final String CHANGE_REJECT_AF_PART_EXEC = "GL 007";

	/**
	 * M****�FSuspend�G���[�R�[�h1����
	 */
	public static final String FIRST_CHAR_SUSPEND = "M";

}
@
