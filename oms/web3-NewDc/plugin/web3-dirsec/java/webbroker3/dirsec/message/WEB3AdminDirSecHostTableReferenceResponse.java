head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �Ǘ��ҁE�L���[�e�[�u���ꗗ���X�|���X�N���X(WEB3AdminDirSecHostTableReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30  �юu��(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�L���[�e�[�u���ꗗ���X�|���X)<BR>
 * �Ǘ��ҁE�L���[�e�[�u���ꗗ���X�|���X�N���X�B
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_host_table_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W���B<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h���B<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ��B<BR>
     */
    public String pageIndex;
    
    /**
     * (���׈ꗗ)<BR>
     */
    public WEB3AdminDirSecHostTableUnit[] hostTables;
    
    /**
     * @@roseuid 442A29A00251
     */
    public WEB3AdminDirSecHostTableReferenceResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminDirSecHostTableReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
