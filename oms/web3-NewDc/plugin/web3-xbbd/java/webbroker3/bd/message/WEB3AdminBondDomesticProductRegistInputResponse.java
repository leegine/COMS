head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������o�^���̓��X�|���X(WEB3AdminBondDomesticProductRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ���n�m (���u) �V�K�쐬 ���f��192
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҍ����������o�^���̓��X�|���X)<BR>
 * �Ǘ��ҍ����������o�^���̓��X�|���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_regist_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * (������{���)<BR>
     * ������{���<BR>
     */
    public WEB3BondDomesticProductBasicInfo productBasicInfo;

    /**
     * (�����X�V���)<BR>
     * �����X�V���<BR>
     */
    public WEB3BondDomesticProductUpdateInfo productUpdateInfo;

    /**
     * (�Ǘ��ҍ����������o�^���̓��X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 46637B6002ED
     */
    public WEB3AdminBondDomesticProductRegistInputResponse()
    {

    }
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondDomesticProductRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
