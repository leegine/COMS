head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������������ꗗ���X�|���X(WEB3BondDomesticApplyProductListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 ���f��No.227
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (��������������ꗗ���X�|���X)<BR>
 * ��������������ꗗ���X�|���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductListResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_product_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * (��������������ꗗ)<BR>
     * ��������������ꗗ<BR>
     */
    public WEB3BondDomesticApplyProductInfo[] bondDomesticApplyProductList;

    /**
     * (��������������ꗗ���X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 466643810174
     */
    public WEB3BondDomesticApplyProductListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondDomesticApplyProductListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
