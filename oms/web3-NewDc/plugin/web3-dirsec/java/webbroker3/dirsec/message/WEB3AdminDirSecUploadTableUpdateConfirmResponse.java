head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecUploadTableUpdateConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�m�F���X�|���X (WEB3AdminDirSecUploadTableListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11����(���u) �V�K�쐬
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�m�F���X�|���X )<BR>
 * �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�m�F���X�|���X�N���X�B
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecUploadTableUpdateConfirmResponse extends WEB3GenResponse 
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "adminDirsec_UploadTable_Confirm";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200608111321L;
	
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminDirSecUploadTableUpdateConfirmResponse(WEB3AdminDirSecUploadTableUpdateConfirmRequest l_request) 
    {
        super(l_request);
    }
}
@