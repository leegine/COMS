head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginAttributeKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C�������L�[�萔��`�N���X(WEB3LoginAttributeKeyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/26 �e�n(SRA) �V�K�쐬
Revesion History : 2007/06/13 �h�C(���u) ���O�C���d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo014
Revesion History : 2007/06/29 �h�C(���u) ���O�C���d�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo016
*/
package webbroker3.common.define;

/**
 * ���O�C�������L�[�萔��`�N���X
 *
 * @@author �e�n(SRA)
 * @@version 1.0
 */
public interface WEB3LoginAttributeKeyDef
{
    /**
     * �������\�����p�X���[�h
     */
    public static final String INITIAL_PASSWORD = "WEB3_ENCRYPTED_INITIAL_PASSWORD";
    
    /**
     * �������\�p�X���[�h
     */
    public static final String PASSWORD = "WEB3_ENCRYPTED_PASSWORD";
    
    /**
     * �P����O�p�X���[�h
     */
    public static final String PASSWORD_OLD1 = "PASSWORD_OLD1";
    
    /**
     * �Q����O�p�X���[�h
     */
    public static final String PASSWORD_OLD2 = "PASSWORD_OLD2";
    
    /**
     * �R����O�p�X���[�h
     */
    public static final String PASSWORD_OLD3 = "PASSWORD_OLD3";
    
    /**
     * �P����O����p�X���[�h
     */
    public static final String TRADING_PASSWORD_OLD1 = "TRADING_PASSWORD_OLD1";
    
    /**
     * �Q����O����p�X���[�h
     */
    public static final String TRADING_PASSWORD_OLD2 = "TRADING_PASSWORD_OLD2";
    
    /**
     * �R����O����p�X���[�h
     */
    public static final String TRADING_PASSWORD_OLD3 = "TRADING_PASSWORD_OLD3";
    
    /**
     * �ŏI���O�C������
     */
    public static final String LAST_LOGIN = "LAST_LOGIN_TIME";
    
    /**
     * �O��p�X���[�h�X�V�҃R�[�h
     */
    public static final String LAST_PASSWORDCHANGE_UPDATER = "LAST_PASSWORDCHANGE_UPDATER";
    
    /**
     * �O��p�X���[�h�ύX���t
     */
    public static final String LAST_PWDCHANGE = "LAST_PASSWORDCHANGE_DATE";

    /**
     * �������\���q�l���ʔԍ�
     */
    public static final String WEB3_ENCRYPTED_DISCRIMINATION_CD = "WEB3_ENCRYPTED_DISCRIMINATION_CD";
}@
