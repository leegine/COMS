head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenAgreementResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�ݎ�����Ӄ��X�|���X(WEB3FEqConAccountOpenAgreementResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������J�ݎ�����Ӄ��X�|���X)<BR>
 * �O�������J�ݎ�����Ӄ��X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenAgreementResponse extends WEB3GenResponse 
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
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;
    
    /**
     * (������ꗗ)<BR>
     * ������ꗗ
     */
    public WEB3FEqConAccountOpenQuestionInfo[] questionInfoList;
    
    /**
     * @@roseuid 423552A901F4
     */
    public WEB3FEqConAccountOpenAgreementResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FEqConAccountOpenAgreementResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
