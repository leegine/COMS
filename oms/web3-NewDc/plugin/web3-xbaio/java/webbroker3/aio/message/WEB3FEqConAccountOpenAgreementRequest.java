head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenAgreementRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�ݎ�����Ӄ��N�G�X�g(WEB3FEqConAccountOpenAgreementRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������J�ݎ�����Ӄ��N�G�X�g)<BR>
 * �O�������J�ݎ�����Ӄ��N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenAgreementRequest extends WEB3GenRequest 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_account_open_agreement";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    
    /**
     * @@roseuid 423552A900FA
     */
    public WEB3FEqConAccountOpenAgreementRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O�������J�ݎ�����Ӄ��X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FEqConAccountOpenAgreementResponse(this);
    }
}
@
