head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҥ�A�b�v���[�h�e�[�u�����R�[�h�ꗗ���X�|���X(WEB3AdminDirSecUploadTableListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11����(���u) �V�K�쐬
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҥ�A�b�v���[�h�e�[�u�����R�[�h�ꗗ���X�|���X)<BR>
 * �Ǘ��ҁE�A�b�v���[�h�e�[�u�����R�[�h�ꗗ���X�|���X�N���X�B
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableListResponse extends WEB3GenResponse 
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "adminDirsec_UploadTable_List";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200608111321L;
	
	/**
	 * �A�b�v���[�h�e�[�u�����R�[�h�ڍ�
	 */
	public WEB3AdminDirSecUploadTableUnit[] uploadTables;
	
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminDirSecUploadTableListResponse(
		WEB3AdminDirSecUploadTableListRequest l_request) 
    {
        super(l_request);
    }
}
@
