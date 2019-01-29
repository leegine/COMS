head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^�Ώیڋq�ꗗ���X�|���X(WEB3CCOperatorAccountListResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 ���n�m (���u) �V�K�쐬�E���f��No.039
*/

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (CC�I�y���[�^�Ώیڋq�ꗗ���X�|���X)<BR>
 * CC�I�y���[�^�Ώیڋq�ꗗ���X�|���X�N���X
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListResponse extends WEB3GenResponse
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3CCOperatorAccountListResponse.class);

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231120L;

    /**
     * TAGNAME<BR>
     */
    public static final String TAGNAME = "response";

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "cc_operator_account_list";

    /**
     * (�Ώیڋq�ꗗ)<BR>
     * �Ώیڋq�ꗗ
     */
    public WEB3TraderAccountInfo[] traderAccoutInfos;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�
     */
    public String pageIndex;

    /**
     * (CC�I�y���[�^�Ώیڋq�ꗗ���X�|���X)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3CCOperatorAccountListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)
     */
    protected WEB3CCOperatorAccountListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
