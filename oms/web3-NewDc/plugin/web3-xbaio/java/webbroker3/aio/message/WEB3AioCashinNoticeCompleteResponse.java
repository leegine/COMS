head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���������X�|���X�N���X(WEB3AioCashinNoticeCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (�����A���������X�|���X)<BR>
 * �����A���������X�|���X�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCompleteResponse extends WEB3AioCashinNoticeCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_notice_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111111L;       
    
    /**
     * (�X�V����)<BR>
     * DB�X�V����
     */
    public Date lastUpdatedTimestamp;       
    
    /**
    * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
    * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    *<BR>
    * @@param l_request ���N�G�X�g�I�u�W�F�N�g
    */
    protected WEB3AioCashinNoticeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
