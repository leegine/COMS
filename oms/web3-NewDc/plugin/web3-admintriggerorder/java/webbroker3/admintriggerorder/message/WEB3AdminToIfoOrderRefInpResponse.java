head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderRefInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ���̓��X�|���X(WEB3AdminToIfoOrderRefInpResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ���̓��X�|���X)<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderRefInpResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_ifo_order_ref_inp";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602141850L;
    
    /**
     * (�������ꗗ)<BR>
     * �������̔z��<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (����������ʈꗗ)<BR>
     * ����������ʂ̔z��<BR>
     */
    public String[] triggerOrderTypeList;

    /**
     * (�w����ʈꗗ)<BR>
     * �w����ʂ̔z��<BR>
     */
    public String[] targetProductList;    
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C80399
     */
    public WEB3AdminToIfoOrderRefInpResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminToIfoOrderRefInpResponse(WEB3AdminToIfoOrderRefInpRequest l_request)
    {
        super(l_request);
    } 
}
@
