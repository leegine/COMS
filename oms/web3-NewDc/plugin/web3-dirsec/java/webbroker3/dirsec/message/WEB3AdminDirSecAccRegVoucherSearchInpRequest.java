head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherSearchInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�ڋq���o�^�`�[�������̓��N�G�X�g(WEB3AdminDirSecAccRegVoucherSearchInpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12  �����q (���u) �V�K�쐬 �d�l�ύX ���f��No.098
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�ڋq���o�^�`�[�������̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�ڋq���o�^�`�[�������̓��N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherSearchInpRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_search_inp";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

    /**
     * @@roseuid 466E0B6B01E0
     */
    public WEB3AdminDirSecAccRegVoucherSearchInpRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherSearchInpResponse(this);
    }
}
@
