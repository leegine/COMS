head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��X�|���X(WEB3AdminAccInfoLockAccountSearchInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��X�|���X)<BR>
 * �Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��X�|���X<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountSearchInputResponse extends WEB3GenResponse 
{
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_lockAccountSearchInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
    /**
     * (�o�^���i���j)<BR>
     * �o�^���i���j<BR>
     * 
     */
    public Date registDateFrom;
    
    /**
     * (�o�^���i���j)<BR>
     * �o�^���i���j<BR>
     * 
     */
    public Date registDateTo;
    
    public WEB3AdminAccInfoLockAccountSearchInputResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l��񃍃b�N�ڋq�⍇���ꗗ���̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse
     * @@roseuid 416657DA0191
     */
    public WEB3AdminAccInfoLockAccountSearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
