head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderRefRefResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�X�|���X(WEB3AdminToIfoOrderRefRefRespons.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�X�|���X)<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefRefResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_ifo_order_ref_ref";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
    
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
     * (�敨OP�����Ɖ�Unit�ꗗ)<BR>
     */
    public WEB3AdminToIfoOrderRefUnit[] ifoOrderList;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C9007D
     */
    public WEB3AdminToIfoOrderRefRefResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminToIfoOrderRefRefResponse(WEB3AdminToIfoOrderRefRefRequest l_request)
    {
        super(l_request);
    } 
}
@
