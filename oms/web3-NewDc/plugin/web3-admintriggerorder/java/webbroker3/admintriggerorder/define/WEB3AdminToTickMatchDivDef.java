head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTickMatchDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ݒl�ƍ��敪��`�C���^�t�F�C�X(WEB3AdminToTickMatchDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/23 �юu�� (���u) �V�K�쐬
*/
package webbroker3.admintriggerorder.define;

/**
 * ���ݒl�ƍ��敪 ��`�C���^�t�F�C�X
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public interface WEB3AdminToTickMatchDivDef
{

    /**
     * 0: ����
     */
    public static final String NORMAL = "0";
    
    /**
     * 1�F�@@�������^��
     */
    public static final String NOT_ORDER_SUSPICION = "1";
    
    /**
     * 2�F�@@�s�������^��
     */
    public static final String ERROR_ORDER_SUSPICION = "2";
    
    /**
     * 3�F�@@�����x���^��
     */
    public static final String ORDER_DELAY_SUSPICION = "3";
    
    /**
     * 9�F�@@�S�ẴG���[
     */
    public static final String ALL_ERROR = "9";
    
}
@
