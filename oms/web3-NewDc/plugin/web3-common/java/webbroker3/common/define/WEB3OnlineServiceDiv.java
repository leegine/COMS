head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OnlineServiceDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �I�����C���T�[�r�X�敪�@@�萔��`�C���^�t�F�C�X(WEB3OnlineServiceDiv)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/05/09 羐� (���u) �V�K�쐬
Revesion History : 2006/03/16 ������ (���u) ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.352��Ή�
Revesion History : 2006/09/20 �h�C (���u) ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.415��Ή�
Revesion History : 2007/04/23 �h�C (���u) ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.479�A480��Ή�
Revesion History : 2007/06/22 �h�C (���u) ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.511��Ή�
Revesion History : 2007/11/21 �h�C (���u) ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.567��Ή�
Revesion History : 2008/01/23 �h�C (���u) ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.589��Ή�
Revesion History : 2008/01/28 �h�C (���u) ���ʎd�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.594��Ή�
*/
package webbroker3.common.define;

/**
 * �I�����C�����s���ʃe�[�u���̃I�����C���T�[�r�X�敪�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author 羐�
 * @@version 1.0
 */
public interface WEB3OnlineServiceDiv
{
    /**
     * 1�F�o���I���ʒm 
     */
    public static final String ORDER_EXEC_END = "1";
    
    /**
     * 2�F�����J�z
     */
    public static final String ORDER_CARRY_OVER = "2";
    
    /**
     * 3�F�蓮����
     */
    public static final String MANUAL_EXPIRE = "3";

    /**
     * 4�F�������
     */
    public static final String AUTO_EXECUTE = "4";

    /**
     * 5�F�������ρi�I�����C���J�n�O�j
     */
    public static final String FORCED_SETTLE_BEFORE_ONLINE = "5";

    /**
     * 6�F�������ρi���F�j
     */
    public static final String FORCED_SETTLE_APPROVAL = "6";

    /**
     * 7�F�������ρi�񏳔F�j
     */
    public static final String FORCED_SETTLE_UNAPPROVAL = "7";

    /**
     * 8�F�[��O�����J�z
     */
    public static final String BEFORE_EVENING_SESSION_ORDER_CARRY_OVER = "8";

    /**
     * 9�F�[��O�o���I��
     */
    public static final String BEFORE_EVENING_SESSION_ORDER_EXEC_END = "9";

    /**
     * A�F(PTS)�o���I���ʒm
     */
    public static final String PTS_ORDER_EXEC_END = "A";

    /**
     * B�F�������ρi��ԁj
     */
    public static final String FORCED_SETTLE_MARKET = "B";
}
@
