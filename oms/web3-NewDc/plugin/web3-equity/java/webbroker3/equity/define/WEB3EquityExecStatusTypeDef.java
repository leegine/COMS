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
filename	WEB3EquityExecStatusTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����ԋ敪 �萔��`�C���^�t�F�C�X(WEB3EquityExecStatusTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/27 �Ջ`�g(���u) �쐬
*/

package webbroker3.equity.define;

/**
 *����ԋ敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �Ջ`�g
 * @@version 1.0
 */
public interface WEB3EquityExecStatusTypeDef
{
    /**
     * ����ԋ敪:
     * 0:�����
     */
    public final static String EXEC_TYPE_NOT_PROMISE = "0";
     
    /**
     * ����ԋ敪:
     * 1:�ꕔ����
     */
    public final static String EXEC_TYPE_ONE_COMPLETE = "1";
    
    /**
     * ����ԋ敪:
     * 2:�S������
     */
    public final static String EXEC_TYPE_ALL_COMPLETE = "2";
    
    /**
     * ����ԋ敪:
     * 3:����
     */
    public final static String EXEC_TYPE_LAPSE = "3"; 
    
    /**
     * ����ԋ敪:
     * 4:�ꕔ����
     */
    public final static String EXEC_TYPE_ONE_LAPSE = "4"; 
    
    /**
     * ����ԋ敪:
     * 5:�����
     */
    public final static String EXEC_TYPE_PROMISE_CANCEL = "5";    

}
@
