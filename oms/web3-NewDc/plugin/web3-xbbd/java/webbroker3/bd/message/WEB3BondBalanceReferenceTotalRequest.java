head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceTotalRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�c�����v���N�G�X�g(WEB3BondBalanceReferenceTotalRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 ���g (���u) �V�K�쐬 ���f��206
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (���c���Ɖ�c�����v���N�G�X�g)<BR>
 * ���c���Ɖ�c�����v���N�G�X�g�N���X
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceTotalRequest extends WEB3BondBalanceReferenceCommonRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceTotalRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference_total";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707171901L;

    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondBalanceReferenceTotalRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondBalanceReferenceTotalResponse(this);
    }
}
@
