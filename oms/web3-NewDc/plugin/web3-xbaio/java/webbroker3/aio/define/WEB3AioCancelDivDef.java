head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCancelDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3CancelDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ��O�� (���u) �V�K�쐬
*/
package webbroker3.aio.define;

/**
 * �o���\���⍇�����ׂ̎���敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ��O��
 * @@version 1.0
 */
public interface WEB3AioCancelDivDef
{  
    /**
     * 0 : �����l
     */
    public static final String INITIAL_VALUE = "0";

    /**
     * 1 : �����
     */
    public static final String CANCELING = "1";    
    
    /**
     * 2 : �����
     */
    public static final String CANCElED = "2";    
    
    /**
     * 3 : ������s
     */
    public static final String CANCEL_FAIL = "3";  
    
    /**
     * 4 : �G���[
     */
    public static final String ERROR = "4";
    
    /**
     * 5 : ����s��
     */
    public static final String CANCEL_NOT = "5";
    
}
@
