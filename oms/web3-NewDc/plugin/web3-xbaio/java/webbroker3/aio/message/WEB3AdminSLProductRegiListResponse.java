head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegiListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^�ꗗ���X�|���X�N���X(WEB3AdminSLProductRegiListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �����F (���u) �V�K�쐬 ���f�� 760
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�S�ۖ����o�^�ꗗ���X�|���X)<BR>
 * �S�ۖ����o�^�ꗗ���X�|���X�N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminSLProductRegiListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_sl_product_regi_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709141545L;

    /**
     * (�����o�^���ꗗ)<BR>
     * �S�ۖ����o�^���ꗗ<BR>
     */
    public WEB3SLProductInfoUnit[] stockLoanProductInfoList;

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
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * @@roseuid 46E8908402FA
     */
    public WEB3AdminSLProductRegiListResponse() 
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminSLProductRegiListResponse(WEB3AdminSLProductRegiListRequest l_request)
    {
        super(l_request);
    }
}
@
