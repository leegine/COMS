head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��X�|���X(WEB3AdminAccOpenStateInquiryInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
                   2006/09/11 �ęԍg (���u) ���f��No.102
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݏ󋵖⍇�����̓��X�|���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
 
public class WEB3AdminAccOpenStateInquiryInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081606L;
    
    /**
     * (��p�U����������ꗗ)<BR>
     * ��p�U����������ꗗ<BR>
     */
    public WEB3AccOpenExclusiveAccountInfo[] exclusiveAccountInfoList;

    /**
     * @@roseuid 41B45E76002E
     */
    public WEB3AdminAccOpenStateInquiryInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenStateInquiryInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
