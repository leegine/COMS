head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountSearchListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���X�|���X(WEB3AdminAccInfoLockAccountSearchListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���X�|���X)<BR>
 * �Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���X�|���X<BR>
 * 
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountSearchListResponse extends WEB3GenResponse 
{
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_lockAccountSearchList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     * 
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     * 
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (��~���ꗗ )<BR>
     * ��~���ꗗ <BR>
     */
    public WEB3AccInfoStopInfoUnit[] stopInfoList;
    
    public WEB3AdminAccInfoLockAccountSearchListResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse
     * @@roseuid 416657DA0191
     */
    protected WEB3AdminAccInfoLockAccountSearchListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
