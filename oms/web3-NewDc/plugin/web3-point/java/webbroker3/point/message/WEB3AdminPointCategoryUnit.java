head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �J�e�S���[����(WEB3AdminPointCategoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�J�e�S���[����)<BR>
 * �J�e�S���[���׃N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointCategoryUnit extends Message
{
    
    /**
     * (�J�e�S���[�ԍ�)<BR>
     * �J�e�S���[�ԍ�<BR>
     */
    public String categoryNo;
    
    /**
     * (�J�e�S���[��)<BR>
     * �J�e�S���[�̖���<BR>
     */
    public String categoryName;
    
    /**
     * (�J�e�S���[�T�v)<BR>
     * �J�e�S���[�̊T�v<BR>
     */
    public String categoryOutline;
    
    /**
     * (�J�e�S���[����)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4187248E03DF
     */
    public WEB3AdminPointCategoryUnit() 
    {
     
    }
}
@
