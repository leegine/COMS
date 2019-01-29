head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�ԍϓ��͉�ʃ��X�|���X(WEB3SuccFuturesCloseInputResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse;

/**
 * (�i�A���j�����w���敨�ԍϓ��͉�ʃ��X�|���X)<BR>
 * �i�A���j�����w���敨�ԍϓ��͉�ʃ��X�|���X�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseInputResponse extends WEB3FuturesCloseMarginInputResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121829L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_input";

    /**
     * (��������)<BR>
     * ��������<BR>
     * �i���Ύ���̏ꍇ�̂݃Z�b�g�j<BR>
     */
    public String orderQuantity;

    /**
     * @@roseuid 47D6593801C5
     */
    public WEB3SuccFuturesCloseInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * <BR>
     * @@param l_request -���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccFuturesCloseInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
