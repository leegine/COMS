head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminExecTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminExecTypeDef(WEB3AdminExecTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/10/02 ���V(SRA) �y�Ǘ��Ғ������Ɖ�z�d�l�ύX�Ǘ��䒠�i���f���jNo.128�A129�A130
*/
package webbroker3.adminorderexecinquiry.define;

/**
 * ����ԋ敪 �萔��`�C���^�t�F�C�X<BR>
 * WEB3AdminExecTypeDef
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public interface WEB3AdminExecTypeDef
{
    /**
     * EXEC_TYPE_NOT_PROMISE
     */
    public final static String EXEC_TYPE_NOT_PROMISE = "0";

    /**
     * EXEC_TYPE_ONE_COMPLETE
     */
    public final static String EXEC_TYPE_ONE_COMPLETE = "1";
    /**
     * EXEC_TYPE_ALL_COMPLETE
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

}@
