head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAcceptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ��t�敪�@@�萔��`�C���^�t�F�C�X (WEB3EquityAcceptTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/27 �Ջ`�g(���u) �쐬
*/
package webbroker3.equity.define;

/**
 * ��t�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �Ջ`�g
 * @@version 1.0
 */
public interface WEB3EquityAcceptTypeDef {
    /**
     * 0:��t����
     */
    public final static String EXEC_TYPE_NAN = "0";
    
    /**
     * 1:��t��
     */
    public final static String EXEC_TYPE_NOT_NAN = "1";
    
    /**
     * 2:��t�G���[
     */
    public final static String EXEC_TYPE_ERROR = "2";

    /**
     * 4:�����҂�
     */
    public final static String EXEC_TYPE_WAITING = "4";
    
    /**
     * 5:�����x��
     */
    public final static String EXEC_TYPE_DELAY = "5";
    
    /**
     * 6:���c�Ɠ�����
     */
    public final static String EXEC_TYPE_NEXT_ORDER = "6";
    
    /**
     * 7:����������
     */
    public final static String EXEC_TYPE_UNORDER = "7";
    
}
@
