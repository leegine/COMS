head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�b�v���[�h�e�[�u�����R�[�h�ڍ�(WEB3AdminDirSecUploadTableUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11����(���u) �V�K�쐬
*/
package webbroker3.dirsec.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ�<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableUnit extends Message
{
	/**
	 * (�A�b�v���[�hID)<BR>
	 * �A�b�v���[�hID�B
	 */
	public String administratorUploadId;
	
	/**
	 * (�،���ЃR�[�h)<BR>
	 * �،���ЃR�[�h�B
	 */
	public String institutionCode;
	
	/**
	 * (���X�R�[�h)<BR>
	 * ���X�R�[�h�B
	 */
	public String branchCode;
	
	/**
	 * (�����^�C�v)<BR>
	 * �����^�C�v�B
	 */
	public String productType;
	
	/**
	 * (�A�b�v���[�h�J�n����)<BR>
	 * �A�b�v���[�h�J�n�����B
	 */
	public String uploadStartTimestamp;
	
	/**
	 * (�A�b�v���[�h�I������)<BR>
	 * �A�b�v���[�h�I�������B
	 */
	public String uploadEndTimestamp;
	
	/**
	 * (���b�Z�[�W�R�[�h)<BR>
	 * ���b�Z�[�W�R�[�h�B
	 */
	public String messageCode;
	
	/**
	 * (�A�b�v���[�h����)<BR>
	 * �A�b�v���[�h�����B
	 */
	public String uploadCount;
	
	/**
	 * (�X�V�҃R�[�h)<BR>
	 * �X�V�҃R�[�h�B
	 */
	public String lastUpdater;
	
	/**
	 * (�f�[�^�L�[)<BR>
	 * �f�[�^�L�[�B
	 */
	public String uploadKey;
}
@
