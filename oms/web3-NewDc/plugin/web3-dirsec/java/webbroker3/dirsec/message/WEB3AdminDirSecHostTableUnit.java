head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L���[�e�[�u���ꗗ(WEB3AdminDirSecHostTableUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 ����(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�L���[�e�[�u���ꗗ)<BR>
 * �L���[�e�[�u���ꗗ�N���X�B<BR>
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableUnit extends Message 
{
    
    /**
     * (�e�[�u����)<BR>
     * �e�[�u�����i�a���j�B
     */
    public String tableJpnName;
    
    /**
     * (�e�[�u��������)<BR>
     * �e�[�u���������B
     */
    public String tableName;
    
    /**
     * @@roseuid 442A1C8802AF
     */
    public WEB3AdminDirSecHostTableUnit() 
    {
     
    }
}
@
