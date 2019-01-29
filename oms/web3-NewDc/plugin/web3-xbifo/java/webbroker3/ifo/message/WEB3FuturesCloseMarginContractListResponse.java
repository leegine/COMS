head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginContractListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍό��ʈꗗ���X�|���X(WEB3FuturesCloseMarginContractListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����w���敨�ԍό��ʈꗗ���X�|���X)<BR>
 * �����w���敨�ԍό��ʈꗗ���X�|���X�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginContractListResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginContractList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191514L;

    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;

    /**
     * (������)<BR>
     */
    public String productName;

    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;

    /**
     * (���Ϗ���)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     */
    public String closingOrder;

    /**
     * (�ԍό��ʈꗗ���׍s)<BR>
     */
    public WEB3FuturesCloseMarginContractGroup[] closeMarginContractGroups;

    /**
     * @@roseuid 40F7AE1701F4
     */
    public WEB3FuturesCloseMarginContractListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
         */
    protected WEB3FuturesCloseMarginContractListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
