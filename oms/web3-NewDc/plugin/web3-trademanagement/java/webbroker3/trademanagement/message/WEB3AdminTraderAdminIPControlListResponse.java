head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�ꗗ���X�|���X(WEB3AdminTraderAdminIPControlListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE���O�C������IP�ꗗ���X�|���X)<BR>
 * �Ǘ��ҁE���O�C������IP�ꗗ���X�|���X�N���X�B<BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221751L;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ��B<BR>
     */
    public String pageIndex;

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
     * (���O�C������IP�ꗗ)<BR>
     */
    public WEB3AdminTraderAdminIPControlReferenceUnit[] ipControlList;

    /**
     * @@roseuid 48D75CD603D6
     */
    public WEB3AdminTraderAdminIPControlListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminTraderAdminIPControlListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
