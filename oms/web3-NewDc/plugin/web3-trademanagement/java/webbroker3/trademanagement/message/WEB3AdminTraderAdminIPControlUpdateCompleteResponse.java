head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlUpdateCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�o�^���ύX�������X�|���X(WEB3AdminTraderAdminIPControlUpdateCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�Ǘ��ҁE���O�C������IP�o�^���ύX�������X�|���X)<BR>
 * �Ǘ��ҁE���O�C������IP�o�^���ύX�������X�|���X�N���X�B<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlUpdateCompleteResponse
    extends WEB3AdminTraderAdminIPControlUpdateCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_update_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221827L;

    /**
     * @@roseuid 48D75CD7026F
     */
    public WEB3AdminTraderAdminIPControlUpdateCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminTraderAdminIPControlUpdateCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
