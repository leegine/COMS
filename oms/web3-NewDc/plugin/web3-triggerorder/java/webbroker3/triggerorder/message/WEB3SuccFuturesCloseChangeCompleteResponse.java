head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�����ԍϊ������X�|���X(WEB3SuccFuturesCloseChangeCompleteResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteResponse;

/**
 * (�i�A���j�����w���敨�����ԍϊ������X�|���X)<BR>
 * �i�A���j�����w���敨�����ԍϊ������X�|���X�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseChangeCompleteResponse extends WEB3FuturesCloseMarginChangeCompleteResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121753L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_change_complete";

    /**
     * @@roseuid 47D659380158
     */
    public WEB3SuccFuturesCloseChangeCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request -���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccFuturesCloseChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
