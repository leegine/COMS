head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍϒ����������X�|���X(WEB3FuturesCloseMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����w���敨�ԍϒ����������X�|���X)<BR>
 * �����w���敨�ԍϊ������X�|���X�N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191548L;

    /**
     * (�X�V����)<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * (���ʔԍ�)<BR>
     * ���������h�c
     */
    public String orderActionId;

    /**
     * @@roseuid 40F7AE180251
     */
    public WEB3FuturesCloseMarginCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FuturesCloseMarginCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
