head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �Ǘ��ҁE�L���[�e�[�u���������̓��X�|���X�N���X(WEB3AdminDirSecHostTableSearchInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  �юu��(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (�Ǘ��ҁE�L���[�e�[�u���������̓��X�|���X)<BR>
 * �Ǘ��ҁE�L���[�e�[�u���������̓��X�|���X�N���X�B
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableSearchInputResponse extends WEB3AdminDirSecHostTableUpdateCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_search_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�B
     */
    public String branchCode;
    
    /**
     * @@roseuid 442A1C86032C
     */
    public WEB3AdminDirSecHostTableSearchInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminDirSecHostTableSearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
