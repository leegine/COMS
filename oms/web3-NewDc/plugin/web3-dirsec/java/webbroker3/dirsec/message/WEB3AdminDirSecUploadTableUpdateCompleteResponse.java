head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableUpdateCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������X�|���X(WEB3AdminDirSecUploadTableUpdateCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11����(���u) �V�K�쐬
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������X�|���X)<BR>
 * �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������X�|���X�N���X�B
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableUpdateCompleteResponse extends WEB3GenResponse
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "adminDirsec_UploadTable_Complete";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200608111321L;
	
	public WEB3AdminDirSecUploadTableUnit[] uploadTables;
	
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminDirSecUploadTableUpdateCompleteResponse(
		WEB3AdminDirSecUploadTableUpdateCompleteRequest l_request) 
    {
        super(l_request);
    }
}
@
