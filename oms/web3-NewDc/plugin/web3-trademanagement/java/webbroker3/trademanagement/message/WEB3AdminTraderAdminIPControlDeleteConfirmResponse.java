head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlDeleteConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�o�^���폜�m�F���X�|���X(WEB3AdminTraderAdminIPControlDeleteConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�Ǘ��ҁE���O�C������IP�o�^���폜�m�F���X�|���X)<BR>
 * �Ǘ��ҁE���O�C������IP�o�^���폜�m�F���X�|���X�N���X�B<BR>
 */
public class WEB3AdminTraderAdminIPControlDeleteConfirmResponse
    extends WEB3AdminTraderAdminIPControlUpdateCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_delete_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221739L;

    /**
     * @@roseuid 48D75CD702CD
     */
    public WEB3AdminTraderAdminIPControlDeleteConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminTraderAdminIPControlDeleteConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
