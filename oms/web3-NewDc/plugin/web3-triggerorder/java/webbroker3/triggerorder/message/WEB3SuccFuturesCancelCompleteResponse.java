head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨��������������X�|���X(WEB3SuccFuturesCancelCompleteResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesCancelCompleteResponse;

/**
 * (�i�A���j�����w���敨��������������X�|���X)<BR>
 * �i�A���j�����w���敨��������������X�|���X�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesCancelCompleteResponse extends WEB3FuturesCancelCompleteResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121728L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_cancel_complete";

    /**
     * @@roseuid 47D6593602A0
     */
    public WEB3SuccFuturesCancelCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request -���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccFuturesCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
