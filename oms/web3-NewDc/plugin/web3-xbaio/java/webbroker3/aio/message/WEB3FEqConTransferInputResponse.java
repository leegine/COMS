head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�֓��̓��X�|���X(WEB3FEqConTransferInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������ւ̐U�֓��̓��X�|���X)<BR>
 * �O�������ւ̐U�֓��̓��X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    
    /**
     * (��������ԍ�)<BR>
     * ��������ԍ�
     */
    public String tradeAccountCode;
    
    /**
     * (�U�։\�z)<BR>
     * �U�։\�z
     */
    public String changePossAmt;
    
    /**
     * @@roseuid 42354D7702DE
     */
    public WEB3FEqConTransferInputResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FEqConTransferInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
