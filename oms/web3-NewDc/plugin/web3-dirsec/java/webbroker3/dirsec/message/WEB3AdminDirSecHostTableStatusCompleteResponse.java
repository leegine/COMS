head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableStatusCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������X�|���X�N���X(WEB3AdminDirSecHostTableStatusCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  �юu��(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (�Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������X�|���X)<BR>
 * �Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������X�|���X�N���X�B
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableStatusCompleteResponse extends WEB3AdminDirSecHostTableUpdateCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_status_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (�L���[�e�[�u�����R�[�h�ڍ�)
     */
    public WEB3AdminDirSecHostTableDetail[] hostTableDetails;
    
    /**
     * @@roseuid 442A1C88007D
     */
    public WEB3AdminDirSecHostTableStatusCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminDirSecHostTableStatusCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
