head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍώ�����ʃ��X�|���X(WEB3SLRepayCancelCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�،��S�ۃ��[���ԍώ�����ʃ��X�|���X)<BR>
 * �S�ۃ��[���ԍώ�����ʃ��X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayCancelCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_common";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121540L;

    /**
     * (����ID)<BR>
     * ����ΏۂƂȂ��Ă��钍���̒���ID<BR>
     */
    public String orderId;

    /**
     * (�ԍϗ\���)<BR>
     * ����ΏۂƂȂ��Ă��钍���̕ԍϗ\���<BR>
     */
    public Date repayScheduledDate;

    /**
     * (�ԍϊz)<BR>
     * ����ΏۂƂȂ��Ă��钍���̕ԍϊz<BR>
     */
    public String repayAmt;

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SLRepayCancelCommonResponse(WEB3SLRepayCancelCommonRequest l_request)
    {
        super(l_request);
    }
}
@
