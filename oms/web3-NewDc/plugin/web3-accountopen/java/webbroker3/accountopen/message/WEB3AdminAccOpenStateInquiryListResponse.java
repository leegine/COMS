head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���X�|���X(WEB3AdminAccOpenStateInquiryListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 �s�p (���u) �V�K�쐬
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���X�|���X)<BR>
 * �Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���X�|���X<BR>
 *     
 * @@author �s�p
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081605L;

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
     * (�����J�ݏ󋵈ꗗ)<BR>
     * �����J�ݏ󋵈ꗗ<BR>
     */
    public WEB3AccOpenStateUnit[] accountOpenStateList;

    /**
     * @@roseuid 41B45E760203
     */
    public WEB3AdminAccOpenStateInquiryListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAccOpenStateInquiryListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
