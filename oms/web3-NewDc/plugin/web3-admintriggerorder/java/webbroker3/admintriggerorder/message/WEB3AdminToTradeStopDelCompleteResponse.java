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
filename	WEB3AdminToTradeStopDelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�폜�������X�|���X(WEB3AdminToTradeStopDelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���q��(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�폜�������X�|���X)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�폜�������X�|���X�N���X<BR>
 * 
 * @@author ���q��
 * @@version 1.0  
 */
public class WEB3AdminToTradeStopDelCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_del_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200604031700L;
    
    /**
     * (���ݎ���)<BR>
     * ���ݎ���<BR>
     */
    public Date currentTime;
    
    /**
     * @@roseuid 4430D2D10399
     */
    public WEB3AdminToTradeStopDelCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     */
    public WEB3AdminToTradeStopDelCompleteResponse(WEB3AdminToTradeStopDelCompleteRequest l_request)
    {
        super(l_request);
    } 
}@