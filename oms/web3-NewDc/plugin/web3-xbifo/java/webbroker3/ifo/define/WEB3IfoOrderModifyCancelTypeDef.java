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
filename	WEB3IfoOrderModifyCancelTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������E����敪(WEB3IfoOrderModifyCancelTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  ���Ō� (���u) �V�K�쐬
*/
package webbroker3.ifo.define;

/**
 * ���������E����敪
 *                                                                     
 * @@author wang-xiaojie
 * @@version 1.0
 */
public interface WEB3IfoOrderModifyCancelTypeDef
{
    /**
     * 4:������s
     */
    public static final String CANCEL_FAILED = "4";

    /**
     * 5:������
     */
    public static final String ORDER_REVISING = "5";
    
    /**
     * 6:�ꕔ��������
     */
    public static final String ORDER_REVISED_PARTLY = "6";
    
    /**
     * 7:��������
     */
    public static final String ORDER_REVISED_ALL = "7"; 
    
    /**
     * 8:�������s
     */
    public static final String CHANGE_FAILED = "8"; 
       
    /**
     * 0000:����
     */
    public static final String ERROR_STATUS_NORMAL = "0000";       
}
@
