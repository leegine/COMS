head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.40.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenCheckStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �R���󋵋敪 �萔��`�C���^�t�F�C�X(WEB3AccOpenCheckStateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/11 ���� (���u) �V�K�쐬
*/

package webbroker3.accountopen.define;

/**
 * �R���󋵋敪 �萔��`�C���^�t�F�C�X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3AccOpenCheckStateDivDef
{
    /**
     * 1�F�R���҂�
     */
    public static final String JUDGE_WAITING = "1";
    
    /**
     * 2�F�R���ς�
     */
    public static final String JUDGE_COMPLETE = "2";
    
    /**
     * 3�F���F��
     */
    public static final String APPROVAL_COMPLETE = "3";
    
    /**
     * 4�F�۔F��
     */
    public static final String OPEN_COMPLETE = "4";
}
@
