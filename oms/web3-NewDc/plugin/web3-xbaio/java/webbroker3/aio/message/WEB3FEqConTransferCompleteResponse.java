head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�֊������X�|���X(WEB3FEqConTransferCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/17 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import java.util.Date;

/**
 * (�O�������ւ̐U�֊������X�|���X)<BR>
 * �O�������ւ̐U�֊������X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String orderId;
    
    /**
     * (��t����)<BR>
     * ��t����
     */
    public Date receptionDate;
    
    /**
     * (��n�\���)<BR>
     * ��n�\���
     */
    public Date deliveryScheduledDate;
    
    /**
     * @@roseuid 42354D7901B5
     */
    public WEB3FEqConTransferCompleteResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FEqConTransferCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
