head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderExecRouteDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�H�敪��`�C���^�t�F�C�X(WEB3FeqOrderExecRouteDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/06 �Г� (���u) �V�K�쐬
*/
package webbroker3.feq.define;

/**
 * ���o�H�敪��`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqOrderExecRouteDivDef
{
    /**
     * 0�F�o���ʒm�iDefault�j
     */
    public static final String EXEC_NOTIFY = "0";
    
    /**
     * 1�F�o������
     */
    public static final String EXEC_INPUT = "1";

    /**
     * 2�F��茋�ʈꊇ����
     */
    public static final String EXECUTED_RESULT_UPLOAD = "2";
    
    /**
     * 3: ������t  
     */
    public static final String ORDER_ACCEPT = "3";
    
    /**
     * 4: ������t����F��  
     */
    public static final String ORDER_ACCEPT_CANCEL_AUTHENTICATE = "4";
    
    /**
     * 5: ������t���ʈꊇ����  
     */
    public static final String ORDER_ACCEPT_RESULT_UPLOAD = "5";

    /**
     * 9�F������
     */
    public static final String ORDER_AND_EXEC_INPUT = "9";
}
@
