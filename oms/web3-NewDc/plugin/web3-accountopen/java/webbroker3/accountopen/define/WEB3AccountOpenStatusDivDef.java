head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
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
File Name        : �����J�ݏ󋵋敪 �萔��`�C���^�t�F�C�X(WEB3AccountOpenStatusDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 ����� (���u) �V�K�쐬
                 : 2006/06/08 ����(���u) �d�l�ύX�E���f��050
*/
package webbroker3.accountopen.define;

/**
 * �����J�ݏ󋵋敪 �萔��`�C���^�t�F�C�X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AccountOpenStatusDivDef
{
    /**
     * 0�F�@@DEFAULT�i���J�݁j
     */
    public static final String DEFAULT = "0";
    
    /**
     * 1�F�@@�J�ݒ�
     */
    public static final String OPENING = "1";
    
    /**
     * 2�F�@@�G���[���� 
     */
    public static final String ERROR = "2";
    
    /**
     * 3�F�@@�J�ݍ� 
     */
    public static final String OPEN_COMPLETE = "3";
    
    /**
     * 4�F  �R���� 
     */
    public static final String JUDGEING = "4";
    
    /**
     * 5�F  �p�� 
     */
    public static final String REJECTION = "5";
}
@
