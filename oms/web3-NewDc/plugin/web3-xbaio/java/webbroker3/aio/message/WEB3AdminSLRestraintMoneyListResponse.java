head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLRestraintMoneyListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���o���S�����ꗗ���X�|���X(WEB3AdminSLRestraintMoneyListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��764
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�،��S�ۃ��[���o���S�����ꗗ���X�|���X)<BR>
 * �،��S�ۃ��[���o���S�����ꗗ���X�|���X�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminSLRestraintMoneyListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_restraint_money_list";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709131759L;

    /**
     * (�o����~���ꗗ)<BR>
     */
    public WEB3SLCashOutStopInfoUnit[] cashOutStopInfoList;

    /**
     * (���y�[�W�ԍ�)<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     */
    public String pageIndex;

    public WEB3AdminSLRestraintMoneyListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminSLRestraintMoneyListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
