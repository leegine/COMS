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
filename	WEB3AdminToTradeStopInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~���̓��X�|���X(WEB3AdminToTradeStopInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/05 ���@@�F(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~���̓��X�|���X)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~���̓��X�|���X�N���X<BR>
 * 
 * @@author ���@@�F
 * @@version 1.0
 */
public class WEB3AdminToTradeStopInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_input";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * @@roseuid 4430D3BA01A5
     */
    public WEB3AdminToTradeStopInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminToTradeStopInputResponse(WEB3AdminToTradeStopInputRequest l_request)
    {
        super(l_request);
    } 
}
@
