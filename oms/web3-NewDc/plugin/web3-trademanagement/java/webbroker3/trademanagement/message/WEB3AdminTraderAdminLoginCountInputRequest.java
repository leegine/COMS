head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginCountInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������̓��N�G�X�g(WEB3AdminTraderAdminLoginCountInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.005
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������̓��N�G�X�g)<BR>
 * �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������̓��N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginCountInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_login_count_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221807L;

    /**
     * @@roseuid 48D75CD60211
     */
    public WEB3AdminTraderAdminLoginCountInputRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminLoginCountInputResponse(this);
    }
}
@
