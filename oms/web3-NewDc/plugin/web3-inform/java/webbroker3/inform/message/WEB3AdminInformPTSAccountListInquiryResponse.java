head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListInquiryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁEPTS�\���q�ꗗ�⍇���������X�|���X�N���X(WEB3AdminInformPTSAccountListInquiryResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �Ӑ�(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁEPTS�\���q�ꗗ�⍇���������X�|���X�N���X)<BR>
 * �Ǘ��ҁEPTS�\���q�ꗗ�⍇���������X�|���X�N���X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListInquiryResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_pts_account_list_inquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802281635L;

    /**
     * �\�������i���j
     */
    public Date applyDateFrom;

    /**
     * �\�������i���j
     */
    public Date applyDateTo;

    /**
     * @@roseuid 47C522D402EA
     */
    public WEB3AdminInformPTSAccountListInquiryResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminInformPTSAccountListInquiryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
