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
filename	WEB3FeqExecStatusTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����ԋ敪 �萔��`�C���^�t�F�C�X(WEB3FeqExecStatusTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �Г�(���u) �V�K�쐬
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.464
*/

package webbroker3.feq.define;

/**
 *����ԋ敪�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqExecStatusTypeDef
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
    
    /**
     * 3:��菈����(�ꕔ����)
     */
    public final static String EXEC_PROCESSING_ONE_COMPLETE = "3";
    
    /**
     * 4:��菈����(�S������)
     */
    public final static String EXEC_PROCESSING_ALL_COMPLETE = "4";
}
@
