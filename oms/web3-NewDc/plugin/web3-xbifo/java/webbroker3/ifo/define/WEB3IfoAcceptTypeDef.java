head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoAcceptTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ��t�敪�@@�萔��`�C���^�t�F�C�X (WEB3IfoAcceptTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/11/26 ������(���u) �쐬
*/
package webbroker3.ifo.define;

/**
 * ��t�敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ������
 * @@version 1.0
 */
public interface WEB3IfoAcceptTypeDef {
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
