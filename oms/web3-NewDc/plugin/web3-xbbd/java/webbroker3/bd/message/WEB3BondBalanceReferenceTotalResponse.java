head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�c�����v���X�|���X(WEB3BondBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 ���g (���u) �V�K�쐬 ���f��206
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (���c���Ɖ�c�����v���X�|���X)<BR>
 * ���c���Ɖ�c�����v���X�|���X�N���X
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceTotalResponse extends WEB3GenResponse
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceTotalResponse.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference_total";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707171902L;

    /**
     * (�T�Z�]���z�i�~�݁j���v)<BR>
     * �T�Z�]���z�i�~�݁j���v<BR>
     */
    public String yenEstimatedTotalAsset;

    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondBalanceReferenceTotalResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondBalanceReferenceTotalResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
