head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ڍ׃��X�|���X(WEB3AdminMailInfoDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (���[�����ڍ׃��X�|���X)<BR>
 * ���[�����ڍ׃��X�|���X�N���X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public class WEB3AdminMailInfoDetailsResponse extends WEB3AdminMailInfoCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_details";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (�ŏI�X�V��)<BR>
     */
    public String lastUpdater;

    /**
     * (�ŏI�X�V��)<BR>
     */
    public Date lastUpdateTime;

    /**
     * (���[�����ڍ׃��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 413C441E036B
     */
    public WEB3AdminMailInfoDetailsResponse()
    {

    }
    /**
     * (���[�����ڍ׃��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminMailInfoDetailsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
