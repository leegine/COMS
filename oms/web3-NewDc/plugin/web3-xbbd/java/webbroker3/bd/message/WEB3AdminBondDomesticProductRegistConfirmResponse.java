head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������o�^�m�F���X�|���X(WEB3AdminBondDomesticProductRegistConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ���n�m (���u) �V�K�쐬 ���f��192
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҍ����������o�^�m�F���X�|���X)<BR>
 * �Ǘ��ҍ����������o�^�m�F���X�|���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_regist_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * (�Ǘ��ҍ����������o�^�m�F���X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 46637CCD000F
     */
    public WEB3AdminBondDomesticProductRegistConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondDomesticProductRegistConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
