head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoScheduleDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3IpoScheduleDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11  ���o��(sinocom)�@@�V�K�쐬
*/
package webbroker3.ipo.define;

/**
 * IPO�X�P�W���[��
 *                                                                     
 * @@author ���o��
 * @@version 1.0
 */
public interface WEB3IpoScheduleDef
{

    /**
     * 1�F�@@�u�b�N�r���f�B���O�J�n�O
     */
    public static final String BOOKBUIDING_START_BEFORE = "1";

    /**
     * 2�F�@@�u�b�N�r���f�B���O���Ԓ�
     */
    public static final String BOOKBUIDING_TERM = "2";
    
    /**
     * 3�F�@@�u�b�N�r���f�B���O�I��
     */
    public static final String BOOKBUIDING_END = "3";
    
    /**
     * 4�F�@@���J���i����
     */
    public static final String PUBLIC_PRICE_DECISION = "4";
    
    /**
     * 5�F�@@���I�I��
     */
    public static final String LOTTERY_END = "5";
    
    /**
     * 6�F�@@�w���\�����Ԓ�
     */
    public static final String PURCHASE_APPLICATION_TERM = "6";
    
    /**
     * 7�F�@@�w���\���I��
     */
    public static final String PURCHASE_APPLICATION_END = "7";
    
    /**
     * 8�F�@@���J
     */
    public static final String PUBLIC = "8";
    
}
@
