head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������ʔ�����~���͉�ʃ��X�|���X(WEB3AdminRuitoTradeStopInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import java.util.Date;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�ݓ������ʔ�����~���͉�ʃ��X�|���X)<BR>
 * �ݓ������ʔ�����~���͉�ʃ��X�|���X�N���X
 */
public class WEB3AdminRuitoTradeStopInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_ruito_trade_stop_input";
    
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;
    
    /**
     * (�ݓ������ʔ�����~���͉�ʃ��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922CB302AF
     */
    public WEB3AdminRuitoTradeStopInputResponse()
    {
    }
    
    /**
     * (�������ꗗ)<BR>
     * �������ꗗ
     */
    public WEB3AdminRuitoTradeInfo[] productInfoList;
    
    /**
     * (���ݓ�����̔�����)<BR>
     * ���ݓ�����̔�����
     */
    public Date curBizDate;
    
    /**
     * (���ݓ�����̗��c�Ɠ�)<BR>
     * ���ݓ�����̗��c�Ɠ�
     */
    public Date nextBizDate;
    
    /**
     * (�I�y���[�V�������t)<BR>
     * �I�y���[�V�������t<BR>
     * ���͉�ʎ擾���̌��ݓ��t
     */
    public Date operationDate;
    
    /**
     * (�ݓ������ʔ�����~���͉�ʃ��X�|���X)<BR>
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminRuitoTradeStopInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}

@
