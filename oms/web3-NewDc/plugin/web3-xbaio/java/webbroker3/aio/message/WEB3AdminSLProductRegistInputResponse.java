head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^���̓��X�|���X(WEB3AdminSLProductRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �И��� (���u) �V�K�쐬 ���f��No.760
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�S�ۖ����o�^���̓��X�|���X)<BR>
 * �S�ۖ����o�^���̓��X�|���X�N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminSLProductRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_input";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709141335L;

    /**
     * (���c�Ɠ����t)<BR>
     * ���c�Ɠ����t<BR>
     */
    public Date nextBizDate;

    /**
     * @@roseuid 46E890840183
     */
    public WEB3AdminSLProductRegistInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - �i���N�G�X�g�I�u�W�F�N�g�j<BR>
     * ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminSLProductRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
