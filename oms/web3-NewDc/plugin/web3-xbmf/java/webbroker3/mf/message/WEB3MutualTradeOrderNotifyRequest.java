head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������ʒm���N�G�X�g�N���X(WEB3MutualTradeOrderNotifyRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * �����M�����������ʒm���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualTradeOrderNotifyRequest extends WEB3BackRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_trade_order_notify";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408130925L;
        
    /**
     * (���M���������ʒm���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40A9A13C00B7
     */
    public WEB3MutualTradeOrderNotifyRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j <BR>
     * <BR>
     * ���M���������ʒm���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * @@return WEB3BackResponse
     * @@roseuid 40A4326A02DD
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MutualTradeOrderNotifyResponse(this);
    }
}
@
