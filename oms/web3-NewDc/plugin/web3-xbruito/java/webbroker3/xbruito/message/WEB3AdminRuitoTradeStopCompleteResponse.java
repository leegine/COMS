head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������ʔ�����~�������X�|���X(WEB3AdminRuitoTradeStopCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�ݓ������ʔ�����~�������X�|���X)<BR>
 * �ݓ������ʔ�����~�������X�|���X�N���X
 */
public class WEB3AdminRuitoTradeStopCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_ruito_trade_stop_complete";
    
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;
    
    /**
     * (�ݓ������ʔ�����~�������X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922CB302AF
     */
    public WEB3AdminRuitoTradeStopCompleteResponse()
    {
    }
    
    /**
     * (�ݓ������ʔ�����~�������X�|���X)<BR>
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminRuitoTradeStopCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
