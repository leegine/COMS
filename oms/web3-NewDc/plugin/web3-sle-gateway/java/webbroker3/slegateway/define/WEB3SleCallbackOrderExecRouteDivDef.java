head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleCallbackOrderExecRouteDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�H�敪��`�C���^�t�F�C�X(WEB3SleCallbackOrderExecRouteDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/23 �� (FTL) �V�K�쐬
*/
package webbroker3.slegateway.define;

/**
 * ���o�H�敪��`�C���^�t�F�C�X
 *
 * @@author ���iFTL)
 * @@version 1.0
 */
public interface WEB3SleCallbackOrderExecRouteDivDef
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
     * 3�F������t
     */
    public static final String ORDER_ACCEPT = "3";
    
    /**
     * 4�F������t����F��
     */
    public static final String ORDER_ACCEPT_CANCEL_CONFIRM = "4";
    
    /**
     * 5�F������t���ʈꊇ����
     */
    public static final String ORDER_ACCEPT_RESULT_UPLOAD = "5";
    
    /**
     * 9�F������
     */
    public static final String ORDER_AND_EXEC_INPUT = "9";
}
@
