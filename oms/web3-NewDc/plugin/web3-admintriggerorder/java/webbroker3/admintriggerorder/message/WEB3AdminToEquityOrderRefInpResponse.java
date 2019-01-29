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
filename	WEB3AdminToEquityOrderRefInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE���������Ɖ���̓��X�|���X(WEB3AdminToEquityOrderRefInpResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/03�@@鰐V(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�g���K�[�����Ǘ��ҁE���������Ɖ���̓��X�|���X)<BR>
 * 
 * @@author 鰐V<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminToEquityOrderRefInpResponse extends WEB3GenResponse
{       
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_equity_order_ref_lnp";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603031700L;

    /**
     * (�s��ꗗ)<BR>
     * �戵�\�Ȏs��̔z�� <BR>
     * <BR>
     */
    public String[] marketList;

    /**
     * (����������ʈꗗ)<BR>
     * �������̎�ʈꗗ�̔z�� <BR>
     * <BR>
     * 4�F�@@�t�w�l����<BR>
     * 5�F�@@W�w�l����<BR>
     * <BR>
     */
    public String[] triggerOrderTypeList;

    /**
     * (�������ꗗ)<BR>
     * �������̔z�� <BR>
     * <BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F1B3C700AB
     */
    public WEB3AdminToEquityOrderRefInpResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminToEquityOrderRefInpResponse(WEB3AdminToEquityOrderRefInpRequest l_request)
    {
        super(l_request);
    } 
}
@
