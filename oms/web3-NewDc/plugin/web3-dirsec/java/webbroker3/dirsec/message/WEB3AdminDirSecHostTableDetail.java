head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L���[�e�[�u�����R�[�h�ڍ�(WEB3AdminDirSecHostTableDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 ����(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�L���[�e�[�u�����R�[�h�ڍ�)<BR>
 * �L���[�e�[�u�����R�[�h�ڍ׃N���X�B<BR>
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableDetail extends Message
{
    
    /**
     * (��ЃR�[�h)<BR>
     * ��ЃR�[�h<BR>
     */
    public String institutionCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String identityCode;
    
    /**
     * (�X�V�O�X�e�[�^�X)<BR>
     * �X�V�O�X�e�[�^�X�B<BR>
     */
    public String beforeStatus;
    
    /**
     * (�쐬���t)<BR>
     * �쐬���t�B<BR>
     */
    public Date createDate;
    
    /**
     * @@roseuid 442A1C85036B
     */
    public WEB3AdminDirSecHostTableDetail() 
    {
     
    }
}
@
