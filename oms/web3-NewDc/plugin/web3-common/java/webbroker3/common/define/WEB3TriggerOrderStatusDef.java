head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TriggerOrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����󋵋敪 �萔��`�C���^�t�F�C�X(WEB3TriggerOrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03 �X�� ���� (SRA) �V�K�쐬
Revesion History : 2006/07/18 �h�C (���u)�C���^�[�t�F�C�X�\���˗�084��Ή�
Revesion History : 2006/07/20 �h�C (���u)�敨OP�\����Ή�
*/

package webbroker3.common.define;

/**
 * �����󋵋敪�@@�萔��`�C���^�t�F�C�X<BR>
 * 
 * @@author  �X�� �����iSRA�j
 * @@version 1.0
 */
public class WEB3TriggerOrderStatusDef
{
    /**
     * 1�F�@@�ҋ@@��
     */
    public final static String ORDER_WAITING = "1";
     
    /**
     * �Q�F�@@������
     */
    public final static String ORDERING = "2";
    
    /**
     * 3�F�@@��������
     */
    public final static String ORDER_COMPLETE = "3";
    
    /**
     * 8�F�@@�����R���G���[
     */
    public final static String ORDER_VALIDATE_ERROR = "8";
    
    /**
     * 9�F�@@�����x���G���[
     */
    public final static String ORDER_DELAY_ERROR = "9";
    
    /**
     * 13�F �X�g�b�v��������
     */
    public final static String STOP_ORDER_INVALIDATION = "13";
    
    /**
     * 99�F�@@���̑�
     */
    public final static String OTHER = "99";
}
@
