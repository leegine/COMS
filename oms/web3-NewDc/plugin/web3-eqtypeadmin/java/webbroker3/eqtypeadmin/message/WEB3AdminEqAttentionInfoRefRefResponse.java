head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefRefResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���ӏ��Ɖ�X�|���X(WEB3AdminEqAttentionInfoRefRefResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �И��� (���u) �V�K�쐬 ���f��No.217
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE���ӏ��Ɖ�X�|���X)<BR>
 * �Ǘ��ҁE���ӏ��Ɖ�X�|���X�N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefRefResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_eq_attention_info_ref_ref";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812301409L;

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
     * (���ӏ��Ɖ�ꗗ)<BR>
     * ���ӏ��Ɖ�ꗗ<BR>
     */
    public WEB3AdminEqAttentionInfoRefDetail[] attentionInfoRefList;

    /**
     * @@roseuid 49588AEF037A
     */
    public WEB3AdminEqAttentionInfoRefRefResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminEqAttentionInfoRefRefResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
