head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�o�^�m�F���N�G�X�g(WEB3AdminTraderAdminIPControlRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE���O�C������IP�o�^�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE���O�C������IP�o�^�m�F���N�G�X�g�N���X�B<BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlRegistConfirmRequest
    extends WEB3AdminTraderAdminIPControlRegistCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_regist_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221858L;

    /**
     * @@roseuid 48D75CD7008B
     */
    public WEB3AdminTraderAdminIPControlRegistConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�jsuper.validate()���R�[������B<BR>
     * @@roseuid 48C0C4F8018A
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        super.validate();
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlRegistConfirmResponse(this);
    }
}
@
