head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesOpenChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�����V�K���m�F���X�|���X(WEB3SuccFuturesOpenChangeConfirmResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,302
 */
package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmResponse;

/**
 * (�i�A���j�����w���敨�����V�K���m�F���X�|���X)<BR>
 * �i�A���j�����w���敨�����V�K���m�F���X�|���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenChangeConfirmResponse extends WEB3FuturesOpenMarginChangeConfirmResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121841L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_open_change_confirm";

    /**
     * (������P��)<BR>
     * ������P���B <BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 47D65937037B
     */
    public WEB3SuccFuturesOpenChangeConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request -���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccFuturesOpenChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
