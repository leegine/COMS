head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherStatUpdCompResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������X�|���X(WEB3AdminDirSecAccRegVoucherStatUpdCompResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 �����q (���u) �V�K�쐬 �d�l�ύX ���f��No.098
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������X�|���X)<BR>
 * �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������X�|���X�N���X�B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdCompResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_stat_upd_comp";

    /**
     * (�ڋq���o�^�`�[���R�[�h)
     */
    public WEB3AdminDirSecAccVoucherRecordDetail[] accVoucherRecord;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

    /**
     * @@roseuid 466E0B6B00B8
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdCompResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdCompResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
