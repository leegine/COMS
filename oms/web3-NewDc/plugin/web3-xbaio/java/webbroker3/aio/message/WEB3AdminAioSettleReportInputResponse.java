head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϘA�g���|�[�g���̓��X�|���X�N���X(WEB3AdminAioSettleReportInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (���ϘA�g���|�[�g���̓��X�|���X)<BR>
 * ���ϘA�g���|�[�g���̓��X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioSettleReportInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_settle_report_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121148L;   
       
    /**
     * (���X�R�[�h)<BR>
     * ���݂̕��X�R�[�h
     */
    public String branchCode;

    /**
     * (���ϋ@@�ֈꗗ)<BR>
     * �Y������،���Ђ��I���\�Ȍ��ϋ@@�ւ̃��X�g
     */
    public WEB3AioSettleInstitutionUnit[] settleInstitutionUnits;
    
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminAioSettleReportInputResponse(WEB3AdminAioSettleReportInputRequest l_request) 
    {
        super(l_request);
    }
    
}
@
