head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginTypeAttributeKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C���^�C�v��������`�N���X(WEB3LoginTypeAttributeKeyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �e�n(SRA) �V�K�쐬
Revesion History : 2007/06/13 �h�C(���u) ���O�C���d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo013
Revesion History : 2007/06/29 �h�C(���u) ���O�C���d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo015
*/
package webbroker3.common.define;

/**
 * ���O�C���^�C�v��������`�N���X
 *
 * @@author �e�n(SRA)
 * @@version 1.0
 */
public interface WEB3LoginTypeAttributeKeyDef
{
    /**
     * �Z�L�����e�B�E���x��
     */
    public static final String SECURITY_LEVEL = "SECURITY_LEVEL";
    
    /**
     * ����p�X���[�h�Z�L�����e�B�E���x��
     */
    public static final String TRADING_PWD_SECURITY_LEVEL = "TRADING_PWD_SECURITY_LEVEL";
    
    /**
     * ����p�X���[�h�ݒ�
     */
    public static final String TRADING_PWD_ENV = "TRADING_PWD_ENV";
    
    /**
     * �p�X���[�h�ŏ���
     */
    public static final String PASSWORD_MIN_LENGTH = "PASSWORD_MIN_LENGTH";
    
    /**
     * �p�X���[�h�ő咷
     */
    public static final String PASSWORD_MAX_LENGTH = "PASSWORD_MAX_LENGTH";
    
    /**
     * �p�X���[�h�`�F�b�N����
     */
    public static final String PASSWORD_CHECK_MODE = "PASSWORD_CHECK_MODE";
    
    /**
     * ���O�C���G���[��
     */
    public static final String LOGIN_ERROR_MAX = "LOGIN_ERROR_MAX";
    
    /**
     * ����p�X���[�h�ύX���{�t���O
     */
    public static final String PWDCHANGE_FIRST_FLAG = "PWDCHANGE_FIRST_FLAG";
    
    /**
     * �����Ԍo�ߌ�p�X���[�h�ύX���{�t���O
     */
    public static final String PWDCHANGE_INTERVAL_FLAG = "PWDCHANGE_INTERVAL_FLAG";
    
    /**
     * �p�X���[�h�L������
     */
    public static final String PASSWORD_INTERVAL = "PASSWORD_INTERVAL";
    
    /**
     * �p�X���[�h�ύX���ă��O�C�����{�t���O
     */
    public static final String PWDCHANGE_RELOGIN_FLAG = "PWDCHANGE_RELOGIN_FLAG";
    
    /**
     * �ڋq���肷�܂����p�X���[�h�`�F�b�N�L��
     */
    public static final String SETACCOUNT_PWDCHECK_FLAG = "SETACCOUNT_PWDCHECK_FLAG";

    /**
     * ���q�l���ʔԍ��`�F�b�N�v��
     */
    public static final String DISCRIMINATION_CD_CHECK = "DISCRIMINATION_CD_CHECK_";
}@
