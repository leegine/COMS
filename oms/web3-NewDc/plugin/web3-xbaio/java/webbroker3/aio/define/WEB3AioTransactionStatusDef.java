head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTransactionStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AioTransactionStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ��O�� (���u) �V�K�쐬
*/
package webbroker3.aio.define;

/**
 * ���ϘA�g���|�[�g�ꗗ�̏�����ԁ@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ��O��
 * @@version 1.0
 */
public interface WEB3AioTransactionStatusDef
{      
    /**
     * 0 : ������
     */
    public static final String NOT_TRANSACTION = "0";

    /**
     * 1 : ���ϊ���
     */
    public static final String SETTLE_COMPLETE = "1";    
    
    /**
     * 2 : ���ϒ��~
     */
    public static final String SETTLE_STOP = "2";
    
    /**
     * 3 : �G���[
     */
    public static final String ERROR = "3";  
    
    /**
     * 4 : �S��
     */
    public static final String ALL = "4";   
    
}
@
