head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesOpenInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�V�K���������͉�ʃ��X�|���X(WEB3SuccFuturesOpenInputResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,302
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputResponse;

/**
 * (�i�A���j�����w���敨�V�K���������͉�ʃ��X�|���X)<BR>
 * �i�A���j�����w���敨�V�K���������͉�ʃ��X�|���X�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenInputResponse extends WEB3FuturesOpenMarginInputResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121901L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_open_input";

    /**
     * (��������)<BR>
     * ��������<BR>
     * �i���Ύ���̏ꍇ�̂݃Z�b�g�j<BR>
     */
    public String orderQuantity;

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request -���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccFuturesOpenInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
