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
filename	WEB3IfoExecStatusTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����ԋ敪 �萔��`�C���^�t�F�C�X(WEB3IfoExecStatusTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 ������(���u) �쐬
*/

package webbroker3.ifo.define;

/**
 * ����ԋ敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ������
 * @@version 1.0
 */
public interface WEB3IfoExecStatusTypeDef
{
    /**
     * 0:�����
     */
    public final static String EXEC_TYPE_NOT_PROMISE = "0";
     
    /**
     * 1:�ꕔ����
     */
    public final static String EXEC_TYPE_ONE_COMPLETE = "1";
    
    /**
     * 2:�S������
     */
    public final static String EXEC_TYPE_ALL_COMPLETE = "2";
    
}
@
