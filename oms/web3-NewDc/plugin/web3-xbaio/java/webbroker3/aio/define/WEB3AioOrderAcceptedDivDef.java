head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOrderAcceptedDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AioOrderAcceptedDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ��O�� (���u) �V�K�쐬
*/
package webbroker3.aio.define;

/**
 * �o���\���⍇���ꗗ���N�G�X�g�̒�����t�敪��
 * �o���\���⍇�����׃N���X�̒�����t�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ��O��
 * @@version 1.0
 */
public interface WEB3AioOrderAcceptedDivDef
{      
    
    /**
     * 0 : ��t����
     */
    public static final String NOT_ACCEPTED = "0";

    /**
     * 1 : ��t��
     */
    public static final String ACCEPTED = "1";    
    
    /**
     * 2 : ��t�G���[
     */
    public static final String ACCEPT_ERROR = "2";
    
    /**
     * 3 : �S��
     */
    public static final String ALL = "3"; 
    
    /**
     * 4 : ��t��
     */
    public static final String ACCEPTING = "4"; 
    
    
}
@
