head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3StatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
Revesion History : 2006/5/25 ������ (���u) �C���^�[�t�F�C�X�\���ENo079
Revesion History : 2009/8/21 ��іQ �y�����J�݁z�d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g058
*/
package webbroker3.common.define;

/**
 * �����敪�@@�萔��`�C���^�t�F�C�X
 *                                                                   
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3StatusDef
{
    /**
     * 0 : ������
     */
    public static final String NOT_DEAL = "0";

    /**
     * 1 : ������
     */
    public static final String DEALT = "1";
    
    /**
     * 2 : ����������
     */
    public static final String NEXT_NOT_DEAL = "2";    

    /**
     * 3�F��
     */
    public static final String TEMPORARY = "3";

    /**
     * 5 : ������
     */
    public static final String DEALING = "5";

    /**
     * 8 : �v���O�����G���[
     */
    public static final String PROGRAM_ERROR = "8";

    /**
     * 9 : �f�[�^�G���[
     */
    public static final String DATA_ERROR = "9";

    /**
     * 4�F�Ǘ��Ҏ蓮������
     */
    public static final String ADMIN_MANUAL_EXPIRED = "4";
}
@
